$(function () {

    // 1.请求购物车数据并加载购物车数据，设置控件的事件监听器
    $.ajax({
        url: '/cart/list',
        type: 'GET',
        success: function (data) {
            if (data.success){
                text = "";
                // 遍历每个购物车记录，并为每个购物车记录生成一个card控件
                data.data.forEach(function (item) {
                    text += "<ul class=\"cart_list_td clearfix\">\n" +
                        "        <li class=\"col01\"><input type=\"checkbox\" id=\"sku_id\" value='" + item.goodSku.skuId + "' checked></li>\n" +
                        "        <li class=\"col02\"><img src='" + item.goodSku.skuImgAddr + "'></li>\n" +
                        "        <li class=\"col03\" >" + item.goodSku.skuName.substring(0, 20) + "..</li>\n" +
                        "        <li class=\"col04\">" + item.goodSku.spec + "</li>\n" +
                        "        <li class=\"col05\">" + item.goodSku.price + "</li>\n" +
                        "        <li class=\"col06\">\n" +
                        "            <div class=\"num_add\">\n" +
                        "                <a href=\"javascript:;\" class=\"add fl\">+</a>\n" +
                        "                <input type=\"text\" sku_id='" + item.goodSku.skuId + "' id='count' class=\"num_show fl\" value='" + item.num + "'>\n" +
                        "                <a href=\"javascript:;\" class=\"minus fl\">-</a>\n" +
                        "            </div>\n" +
                        "        </li>\n" +
                        "        <li class=\"col07\">" + (item.goodSku.price * item.num) + "</li>\n" +
                        "        <li class=\"col08\"><a href='#' onclick='delCart(" + item.goodSku.skuId + ")'>删除</a></li>\n" +
                        "    </ul>"
                });
                $("#cart-content").append(text);

                update_total_amount(); // 计算合计
                update_total_count();  // 计算购物车中商品的总件数
                setCheckBoxOnChange();  // 设置每项的复选框的变化事件监听器
                setSymbolOnClick();    // 加号，减号的点击事件
            }
        }
    });

    // 2.监听全选按钮变化事件
    // 有两个任务：1）与每一项的checkbox同步；2）改变合计数据
    $('.settlements').find(':checkbox').change(function () {
        is_checked = $(this).prop('checked');  // 全选按钮的状态
        $('.cart_list_td').find(':checkbox').each(function () { // 遍历购物车每一项的
            $(this).prop('checked', is_checked);  // 让每一项的checkbox与全选框同步
        });

        // 重新计算合计数据
        update_total_amount();
    });

});

/**
 * 删除某一项购物车记录
 * @param skuId
 */
function delCart(skuId) {
    $.ajax({
        url: '/cart/del',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            'skuid': skuId
        }),
        success: function (data) {
            if (data.success){
                location.reload();
            }else
                alert("错误:" + data.errMsg);
        }
    })
}

/**
 * 设置监听复选框变化事件【PS：通过ajax请求得到数据并加载好数据才能执行该项设置】
 * 主要任务：1）取消任一复选框，全选不勾选；2）全部复选框选中，自动勾选全选
 */
function setCheckBoxOnChange() {

    $('.cart_list_td').find(':checkbox').change(function () {
        // 获取数据项条数
        all_len = $('.cart_list_td').length;
        // 获取选中项的条数
        checked_len = $('.cart_list_td').find(':checked').length;
        is_checked = true;
        if(all_len > checked_len){ // 存在没有选中的项
            is_checked = false;
        }

        // 更新全选框的值,但不会激发全选框的 change() 事件
        $('.settlements').find(':checkbox').prop('checked', is_checked);

        update_total_amount();
    });
}

/**
 * 设置 “加号”，“减号”控件的点击事件
 */
function setSymbolOnClick() {
    // '加号'按钮点击事件
    // 发送加件数的请求,并刷新控件值
    $('.add').click(function () {
        // 获取商品id 和数量
        sku_id = $(this).next().attr('sku_id');
        count = $(this).next().val();
        // csrf = $('input[name="csrfmiddlewaretoken"]').val();
        count = parseInt(count)+1;

        // 发送请求并更新
        update_remote_cart_info_request(sku_id, count, $(this));
    });

    // '减号'按钮点击事件
    // 发送加件数的请求,并刷新控件值
    $('.minus').click(function () {
        // 1.获取商品id 和数量
        sku_id = $(this).prev().attr('sku_id');
        count = $(this).prev().val();
        // csrf = $('input[name="csrfmiddlewaretoken"]').val();
        count = parseInt(count)-1;

        console.log(sku_id, count);

        // 2.验证
        if (count <= 0)
            count = 1;

        // 3.发送请求并更新
        update_remote_cart_info_request(sku_id, count, $(this));
    });
}

/**
 * 发送更新购物车请求
 * @param skuId
 * @param count
 * @param control 控件
 */
function update_remote_cart_info_request(skuId, count, control) {
    $.ajax({
       url: '/cart/update',
       type: 'POST',
       contentType: 'application/json',
       async: false,
       data:JSON.stringify({
         "skuid": skuId,
         "count": count,
       }),
       success: function (data) {
           if (data.success){
               control.parents('ul').find('.num_show').val(count);

               // 先更新小计
               update_each_amount();
               // 再更新合计中的总价格和总件数
               update_total_amount();
               // 再更新顶部的总件数
               update_total_count();

           }
           else{
               alert("错误:" + data.errMsg);
           }
       }
    });
}

/**
 * 计算选中商品的总价格，同时更新合计部分的控件的值
 */
function update_total_amount() {
    // 获取所有被选中的商品的checkbox
    // 获取所有被选中的商品所在的ul元素
    total_count = 0;
    total_amount = 0;
    // 找出选中的项,并统计价格
    $('.cart_list_td').find(':checked').parents('ul').each(function () { // 遍历每一项
        //获取数量和小计
        count = $(this).find('.num_show').val(); // 数量
        amount = $(this).children('.col07').text(); // 小计
        total_count += parseInt(count);
        total_amount += parseFloat(amount);
    });
    $('.settlements').find('em').text(total_amount.toFixed(2));
    $('.settlements').find('b').text(total_count);
}

/**
 * 计算每一项的小计
 */
function update_each_amount() {
    $('.cart_list_td').each(function () { // 遍历每一项
        // 获取数量和单价
        count = $(this).find('.num_show').val();
        price = $(this).children('.col05').text();

        // 计算小计
        amount = parseFloat(price) * parseInt(count);
        // 更新控件值
        $(this).children('.col07').text(amount.toFixed(2)+'元');
    });
}

/**
 * 更新购物车中全部商品的件数（包含选中和未选中）
 */
function update_total_count() {
    var total_count = 0;

    $('.cart_list_td').each(function () { // 遍历每一项
        // 获取数量和单价
        count = $(this).find('.num_show').val();
        total_count += parseInt(count);
    });

    $(".total_count em").text(total_count);
}

/**
 * 提交订单（即结算）
 */
function submitOrder() {
    // 1.收集数据
    var selectedSku = new Array();
    // 找出选中的项,并收集
    $('.cart_list_td').find(':checked').parents('ul').each(function () { // 遍历每一项
        //获取id和count
        selectedSku[selectedSku.length] = {
            skuId: $("#sku_id").val(),
            count: $("#count").val()
        }
    });

    console.log(JSON.stringify(selectedSku));
    if (selectedSku.length > 0){
        // 2.发送请求
        $.ajax({
           url: '/order/settle',
           type: 'POST',
           contentType: 'application/json;charset=UTF-8',
           data: JSON.stringify(selectedSku),
           success:function (response) {
               if (response.success){
                   console.log(response.data)
                   window.location.href = response.data;
               }else
                   alert("错误:" + response.errMsg);
           }
        });
    }

}