$(function () {
    // 1.获取URL的参数
    orderId = getQueryVariable("orderid");

    console.log(orderId);
    console.log(JSON.stringify({orderid:orderId}));

    // 2.请求数据，并加载
    $.ajax({
       url: '/order/order',
       type: 'POST',
       contentType: 'application/json;charset=UTF-8',
       data: JSON.stringify({
           orderid: orderId
       }),
       success: function (response) {
           if (response.success){
               console.log(response.data);
               order = response.data;
               text = "<h3 class=\"common_title2\">订单评价</h3>\n" +
                   "        <ul class=\"order_list_th w978 clearfix\">\n" +
                   "            <li class=\"col01\">" + transformTime(order.createTime) + "</li>\n" +
                   "            <li class=\"col02\">订单号：" + order.orderId + "</li>\n" +
                   "            <li class=\"col02 stress\">" + order.payStatus + "</li>\n" +
                   "        </ul>";

               text += "<input type=\"hidden\" id=\"order_id\" value='" + order.orderId + "'>\n";

               // 遍历订单中每个商品
               i = 1;
               order.orderGoodDtos.forEach(function (sku) {
                   text += "<table class=\"order_list_table w980\">\n" +
                       "                <tbody>\n" +
                       "                <tr>\n" +
                       "                    <td width=\"80%\">\n" +
                       "                        <ul class=\"order_goods_list clearfix\">\n" +
                       "                            <li class=\"col01\"><img src='" + sku.imgAddr + "'></li>\n" +
                       "                            <li class=\"col02\">" + sku.skuName.substring(0, 14) + "<em>" + sku.price + "/" + transformSpec(sku.spec) + "</em></li>\n" +
                       "                            <li class=\"col03\">" + sku.count + "</li>\n" +
                       "                        </ul>\n" +
                       "                    </td>\n" +
                       "                    <td width=\"20%\">" + (sku.price * sku.count) + "元</td>\n" +
                       "                </tr>\n" +
                       "                </tbody>\n" +
                       "            </table>\n" +
                       "            <div class=\"site_con\">\n" +
                       "                <input type=\"hidden\" id=\"sku_" + i + "\" value='" + sku.skuId + "'>\n" +
                       "                <div class=\"form_group form_group2\">\n" +
                       "                    <label>评价内容：</label>\n" +
                       "                    <textarea class=\"site_area\" id=\"content_" + i + "\"></textarea>\n" +
                       "                </div>\n" +
                       "            </div>";
                   i += 1;
               });

               text += "<input type=\"submit\" name=\"\" value=\"提交\" class=\"info_submit\" onclick='submitComment(" + (i-1) + ")'>\n";

               $(".right_content").html(text);
           }else
               alert("错误:" + response.errMsg);
       }
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

/**
 * 时间戳转时间字符串
 * @param timestamp
 * @returns {string}
 */
function transformTime(timestamp) {
    var date = new Date(timestamp)
    var Y = date.getFullYear();
    var M = date.getMonth() + 1;
    var d = date.getDate();
    var h = date.getHours();
    var m = date.getMinutes();
    var s = date.getSeconds();
    return Y + "/" + M + "/" + d + " " + h + ":" + m + ":" + s;
}

/**
 * 将字典格式的spec转成字符串
 * @param spec
 */
function transformSpec(spec) {
    var jsonObj = JSON.parse(spec);
    var result = '';
    for (var key in jsonObj){
        result += jsonObj[key] + "|";
    }
    if (result.length != 0)
        result = result.substring(0, result.length-1);
    return result;
}

/**
 * 提交评论
 */
function submitComment(n) {
    var skuids = new Array();
    var comments = new Array();
    for (var i=1; i<=n; i++){
        skuId = "#sku_" + i;
        commentId = "#content_" + i;
        // console.log($("#order_id").val() + " | " + $(skuId).val() + " | " + $(commentId).val().trim());
        skuids.push($(skuId).val());
        comments.push($(commentId).val().trim());
    }

    $.ajax({
        url: '/comment/add',
        type: 'POST',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({
            orderId: $("#order_id").val(),
            skuIds: skuids,
            comments: comments
        }),
        success: function (response) {
            if (response.success){
                console.log(response.data);
            }else
                alert("错误:" + response.errMsg);
        }
    })
}