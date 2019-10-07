$(function () {
    // 1.加载当前用户的所有订单
    $.ajax({
        url: '/order/all',
        type: 'GET',
        success: function (response) {
            if (response.success){
                allOrder = response.data;
                console.log(allOrder);
                text = "";
                allOrder.forEach(function (order) {
                   text += "<ul class=\"order_list_th w978 clearfix\">\n" +
                       "            <li class=\"col01\">" + order.createTime + "</li>\n" +
                       "            <li class=\"col02\">订单号：" + order.orderId + "</li>\n" +
                       "            <li class=\"col02 stress\">" + order.payStatus + "</li>\n" +
                       "        </ul>";

                   text += "<table class=\"order_list_table w980\">\n" +
                       "            <tbody>\n" +
                       "            <tr>\n" +
                       "                <td width=\"55%\">";

                   order.orderGoodDtos.forEach(function (sku) {
                      text += "<ul class=\"order_goods_list clearfix\">\n" +
                          "                        <li class=\"col01\"><img src='" + sku.imgAddr + "'></li>\n" +
                          "                        <li class=\"col02\">" + sku.skuName.substring(0, 10) + "<em>" + sku.price + "元</em></li>\n" +
                          "                        <li class=\"col03\">" + sku.count + "</li>\n" +
                          "                        <li class=\"col04\">" + (sku.price * sku.count) + "元</li>\n" +
                          "                    </ul>"
                   });

                   text += "</td>\n" +
                       "                <td width=\"15%\">" + order.totalAmount + "元<br>(含运费:" + (order.totalAmount + order.freight) + ")</td>\n" +
                       "                <td width=\"15%\">" + order.payStatus + "</td>\n" +
                       "                <td width=\"15%\">\n" +
                       "                    <a href=\"#\" order_id='" + order.orderId + "' class=\"oper_btn\" onclick='operBtnClick(this)'>" + order.payStatus + "\n</a>" +
                       "                </td>\n" +
                       "            </tr>\n" +
                       "            </tbody>\n" +
                       "        </table>"
                });
                $("#order-list").html(text);
            }else
                alert("错误:" + response.errMsg);
        }
    })
});

/**
 * “待支付”，“待评价”按钮的点击事件
 */
function operBtnClick(btn) {
    var status = $(btn).text().trim();
    var orderId = $(btn).attr("order_id");
    console.log(status + "," + orderId);
    switch (status) {
        case "待支付":
            console.log(status);
            break;
        case "待评价":
            window.location.href = '/order/comment?orderid=' + orderId;
            break;
        default:
            break;
    }
}