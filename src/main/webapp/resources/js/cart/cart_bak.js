$(function () {
   // sui框架初始化
   $.init();

   $.ajax({
      url: '/cart/list',
      type: 'GET',
      success: function (data) {
         if (data.success){
            text = "";

            // 遍历每个购物车记录，并为每个购物车记录生成一个card控件
            data.data.forEach(function (item) {
               var spec = "[";
               console.log(item.goodSku.spec);
               text +=
                   "<div class=\"card\">\n" +
                   "    <div class=\"card-header\"><input type=\"checkbox\" name=\"colors\" skuId='" + item.goodSku.skuId + "' value='" + item.num + "' />" + item.goodSku.goodSPU.spuName + "</div>" +
                   "    <div class=\"card-content\">\n" +
                   "      <div class=\"list-block media-list\">\n" +
                   "        <ul>\n" +
                   "          <li class=\"item-content\">\n" +
                   "            <div class=\"item-media\">\n" +
                   "              <img src='" + item.goodSku.skuImgAddr + "' width=\"44\">\n" +
                   "            </div>\n" +
                   "            <div class=\"item-inner\">\n" +
                   "              <div class=\"item-title-row\">\n" +
                   "                <div class=\"item-title\">" + item.goodSku.spec + "</div>\n" +
                   "              </div>\n" +
                   "              <div class=\"item-subtitle\"><span text-aliggn='right'>x" + item.num + "</span></div>\n" +
                   "            </div>\n" +
                   "          </li>\n" +
                   "        </ul>\n" +
                   "      </div>\n" +
                   "    </div>\n" +
                   "    <div class=\"card-footer\">\n" +
                   "      <span></span>\n" +
                   "      <span><a onclick='delCart(" + item.goodSku.skuId + ")'>删除</a></span>\n" +
                   "    </div>\n" +
                   "</div>"
            });
            $(".cart-content").append(text);
         }
      }
   });
});

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