$(function () {

    // 1.获取url参数
    k = getQueryVariable("k");

    // 2.加载订单商品数据并加载
    $.ajax({
        url: '/order/getsettle',
        type: 'POST',
        contentType: 'application/json;charset=UTF-8',
        async: false,
        data: JSON.stringify({
            key: k
        }),
        success: function (response) {
            if (response.success){
                console.log(response.data);
                text = "";
                i = 1;
                total_amount = 0;
                total_count = 0;
                response.data.forEach(function (orderGood) {
                    total_amount += (orderGood.price * orderGood.count);
                    total_count += orderGood.count;
                    text += "<ul class=\"goods_list_td clearfix\" sku_id='" + orderGood.skuId + "' count='" + orderGood.count + "' price='" + orderGood.price + "'>\n" +
                        "\t\t<li class=\"col01\">" + i + "</li>\n" +
                        "\t\t<li class=\"col02\"><img src='" + orderGood.imgAddr + "'></li>\n" +
                        "\t\t<li class=\"col03\">" + orderGood.skuName + "</li>\n" +
                        "\t\t<li class=\"col04\">" + orderGood.spec + "</li>\n" +
                        "\t\t<li class=\"col05\">" + orderGood.price + "元</li>\n" +
                        "\t\t<li class=\"col06\">" + orderGood.count + "</li>\n" +
                        "\t\t<li class=\"col07\">" + (orderGood.price * orderGood.count) + "元</li>\n" +
                        "\t</ul>";
                    i = i + 1;
                });
                $("#goods_list_div").html(text);
                $(".total_goods_count").html("共<em>" + total_count + "</em>件商品，总金额<b>" + total_amount + "元</b>");
                $(".total_pay").html("实付款：<b>" + (total_amount+10) + "元</b>");
            }else
                alert("错误:" + response.errMsg);
        }
    });


    // 3.加载收货地址
    $.ajax({
        url: '/user/address/list',
        type: 'GET',
        async: false,
        success: function (response) {
            if (response.success){
                text = "<dl>" + "<dt>寄送到：</dt>";
                i = 1;
                console.log(response.list);
                response.list.forEach(function (addr) {
                    if (i == 1)
                        text += "<dd><input type=\"radio\" name=\"addr_id\" value='" + addr.addrId + "' checked=''>" + addr.address + " （" + addr.addressee + " 收） " + addr.phone + "</dd>\n";
                    else
                        text += "<dd><input type=\"radio\" name=\"addr_id\" value='" + addr.addrId + "' >" + addr.address + " （" + addr.addressee + " 收） " + addr.phone + "</dd>\n";
                    i += 1;
                });
                text += "</dl>\n" +
                    "\t<a href=\"#\" class=\"edit_site\">编辑收货地址</a>";
                console.log(text);
                $("#address_list").html(text);
            }else
                alert("错误:" + response.errMsg);
        }
    })

    // 3.提交订单按钮
    $('#order_btn').click(function() {
        // 获取用户选择的收获地址id
        addr_id = $('input[name="addr_id"]:checked').val();
        // 获取用户选择的支付方式
        pay_method = $('input[name="pay_style"]:checked').val();
        // 获取用户购买商品id
        sku_ids = '';
        counts = '';
        prices = '';
        $('.goods_list_td').each(function () {
            sku_ids += $(this).attr('sku_id') + ',';
            counts += $(this).attr("count") + ',';
            prices += $(this).attr("price") + ",";
        });

        // 组织信息
        params = {
            'addr_id': addr_id,
            'pay_method': pay_method,
            'sku_ids': sku_ids,
            'counts': counts,
            'prices': prices,
        };

        console.log(params);

        $.ajax({
            url: '/order/commit',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(params),
            success:function (response) {
                if (response.success){
                    window.location.href = '/user/center/order';
                }else
                    alert("错误:" + response.errMsg);
            }
        })

        // $.post('/order/commit/', params, function (data) {
        //     if(parseInt(data.code) == 0){ // 证明提交成功
        //         localStorage.setItem('order_finish',2);
        //         $('.popup_con').fadeIn('fast', function() {
        //             setTimeout(function () {
        //                 $('.popup_con').fadeOut('fast', function () {
        //                     window.location.href = '/user/order';
        //                 });
        //             }, 3000)
        //         });
        //     }else{ // 证明提交失败
        //         alert(data.msg);
        //     }
        // });


    });
});

// 获得get中的URL参数
function getQueryVariable(variable)
{
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return decodeURIComponent(pair[1]);}
    }
    return(false);
}