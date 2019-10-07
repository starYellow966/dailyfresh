$(function () {

    // 2.获取所有商品分类
    $.ajax({
        url: '/goodtype/all',
        type: 'GET',
        success: function (response) {
            if (response.success){
                text = "";
                response.data.forEach(function (item) {
                    text += "<li>" +
                        "       <a href='#' class='" + item.typeEnName + "'>" + item.typeName + "</a>" +
                        "    </li>";
                });
                $(".subnav").append(text);
            }else {
                alert("goodtype错误:" + response.errMsg);
            }
        }
    });


    // 3.获取商品详情
    // 获取url中spuId
    var spuId = getQueryVariable("spuid");
    var allSku = null;
   // 加载数据
   $.ajax({
       url: '/sku/detail/' + spuId,
       type: 'GET',
       async: false, // 重点
       success: function (response) {
           if (response.success){
               // console.log(response.data);
               if (response.data.length > 0){
                   data = response.data;

                   // 设置导航栏
                   $(".breadcrumb").append("<a href=\"#\">全部分类</a>\n" +
                       "    <span>></span>\n" +
                       "    <a href=\"#\">" + data[0].goodSPU['spuName'] + "</a>\n" +
                       "    <span>></span>\n" +
                       "    <a href=\"#\">商品详情</a>");

                   // 设置商品详情
                   $(".goods_detail_pic").append("<img src='" + data[0].skuImgAddr + "'>"); // 图片
                   text = "";
                   text += "<h3 id='skuName'>" + data[0].skuName + "</h3>\n" +
                       "        <div class=\"prize_bar\">\n" +
                       "\n" +
                       "            <span class=\"show_pirze\">¥<em>" + data[0].price + "</em></span>\n" +
                       "            <span class=\"show_unit\">库  存：" + data[0].sock + "</span>" +
                       "        </div>\n" +
                       "        <div class=\"goods_num clearfix\">\n" +
                       "            <div class=\"num_name fl\">数 量：</div>\n" +
                       "            <div class=\"num_add fl\">\n" +
                       "                <input type=\"text\" class=\"num_show fl\" value=\"1\" >\n" +
                       "                <a href=\"javascript:;\" class=\"add fr\">+</a>\n" +
                       "                <a href=\"javascript:;\" class=\"minus fr\">-</a>\n" +
                       "            </div>\n" +
                       "        </div>" +
                       "        <div>\n" +
                       "            <div class=\"num_name f1\">商品规格：</div>\n" +
                       "            <ul>";

                   // 遍历所有sku
                   data.forEach(function (sku) {
                       text += "<li><a href=\"javascript:\" onclick='changeSku(" + JSON.stringify(sku) + ")'>" + sku.spec.substring(1, sku.spec.length-1) + "</a></li>"
                   });
                   text += "</ul>\n" +
                       "    </div>";

                   text += "<div class=\"total\">总价：<em>" + data[0].price + "</em></div>\n" +
                       "        <div class=\"operate_btn\">\n" +
                       "            <a href=\"javascript:\" class=\"buy_btn\" onclick='buyClick()'>立即购买</a>\n" +
                       "            <a href=\"javascript:\" sku_id='" + data[0].skuId + "' class=\"add_cart\" id=\"add_cart\" onclick='addCartClick()'>加入购物车</a>\n" +
                       "    </div>";

                   $(".goods_detail_list").append(text);

                   // 设置商品介绍
                   $("#tab_detail").append("<dl>\n" +
                       "                        <dt>商品详情：</dt>\n" +
                       "                        <dd>" + data[0].goodSPU['spuDetail'] + "</dd>\n" +
                       "                    </dl>");
               }
           }else
               alert("数据请求失败");
       }
   });


    // 4.设置加号、减少的点击事件
    setAddAndMinusClick();

    // 5.tab切换事件
    changeTab();

    // 6.新品推荐
    $.ajax({
        url: '/spu/new',
        type: 'GET',
        success: function (response) {
            if (response.success){
                text = "<h3>新品推荐</h3>\n" +
                    "            <ul>";

                response.list.forEach(function (spu) {
                    text += "<li>\n" +
                        "                    <a href='/good/detail?spuid=" + spu.spuId + "'><img src='" + spu.imgAddr + "'></a>\n" +
                        "                    <h4><a href=\"#\">" + spu.spuName + "</a></h4>\n" +
                        // "                    <div class=\"prize\">￥{{ new_sku.price }}</div>\n" +
                        "                </li>";
                });
                $(".new_goods").html(text);
            }else
                alert("错误:" + response.errMsg);
        }
    })
});

//==========定义函数==============
// 获得get中的URL参数
function getQueryVariable(variable)
{
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return(false);
}

/**
 * 计算商品的总价格
 */
function update_goods_amount() {
    // 获取商品的单价和数量
    price = $('.show_pirze').children('em').text();
    count = $('.num_show').val();
    // 计算商品的总价
    price = parseFloat(price);
    count = parseInt(count);
    amount = price*count;
    // 设置商品的总价
    $('.total').children('em').text(amount.toFixed(2)+'元');
}

/**
 * 设置加号、减号的点击事件
 */
function setAddAndMinusClick() {
    // '增加商品数量'按钮
    $('.add').click(function () {
        // 获取目前数量
        count = $('.num_show').val();
        // 数量 +1
        count = parseInt(count) +1;
        // 更新数量和总价
        $('.num_show').val(count);
        update_goods_amount();
    });

    // '减少商品数量'按钮
    $('.minus').click(function () {
        // 获取目前数量
        count = $('.num_show').val();
        // 数量 +1
        count = parseInt(count) - 1;
        if(count < 1)
            count = 1;
        // 更新数量和总价
        $('.num_show').val(count);
        update_goods_amount();
    });
}

/**
 * tab切换
 */
function changeTab() {
    // 切换到评论内容显示
    $('#tag_comment').click(function () {
        $('#tag_detail').removeClass('active');
        $(this).addClass('active');
        $('#tab_detail').hide();
        $('#tab_comment').show();
    });

    // 切换到商品详情
    $('#tag_detail').click(function () {
        $('#tag_comment').removeClass('active');
        $(this).addClass('active');
        $('#tab_detail').show();
        $('#tab_comment').hide();
    });
}

/**
 * 切换规格
 * @param sku
 */
function changeSku(sku) {
    $("#skuName").html(sku['skuName']);
    $(".show_pirze").html("￥<em>" + sku['price'] + "</em>");
    $(".show_unit").html("库  存：" + sku['sock'] + "");
    $(".total").html("总价：<em>" + sku['price'] + "元</em>");
    $(".goods_detail_pic").html("<img src='" + sku['skuImgAddr'] + "'>");
    $(".num_show").val(1);

    $(".operate_btn").html(
        "<a href=\"javascript:\" class=\"buy_btn\" onclick='buyClick()'>立即购买</a>\n" +
        "<a href=\"javascript:\" sku_id='" + sku['skuId'] + "' class=\"add_cart\" id=\"add_cart\" onclick='addCartClick()'>加入购物车</a>");

}

/**
 * 立即购买的点击事件
 */
function buyClick() {
    // 1.获取值
    skuId = $(".add_cart").attr("sku_id");
    count = $(".num_show").val();
    console.log(skuId + "," + count);

    // 2.发送请求
}

/**
 * 加入购物车点击事件
 */
function addCartClick() {
    // 1.获取值
    skuId = $(".add_cart").attr("sku_id");
    count = $(".num_show").val();
    console.log(skuId + "," + count);

    // 1* 值的验证

    // 2.发送请求
    $.ajax({
        url: '/cart/add',
        type: 'POST',
        data: JSON.stringify({
            skuid: skuId,
            count: count
        }),
        contentType: 'application/json;charset=UTF-8',
        success: function (response) {
            if (response.success){
                window.location.reload();
            }else {
                alert("错误:" + response.errMsg);
            }
        }
    })
}