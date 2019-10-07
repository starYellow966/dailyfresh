$(function () {
    // 1.获取请求URL中的搜索条件
    q = getQueryVariable("q");

    // 2.发送请求，并加载数据
    $.ajax({
        url: '/spu/search?q='+q,
        type: 'GET',
        success: function (response) {
            if (response.success){
                goodList = response.list;
                console.log(goodList);
                text = "";
                if (goodList != null && goodList.length > 0){
                    goodList.forEach(function (spu) {
                        text += "<li>\n" +
                            "      <a href='/good/detail?spuid=" + spu.spuId + "'><img src='" + spu.imgAddr + "'></a>\n" +
                            "      <h4><a href='/good/detail?spuid=" + spu.spuId + "'>" + spu.spuName + "</a></h4>\n" +
                            "      <div class=\"operate\">\n" +
                            // "         <span class=\"prize\">￥0.00</span>\n" +
                            "         <span class=\"unit\"></span>\n" +
                            // "         <a href=\"#\" class=\"add_goods\" title=\"加入购物车\"></a>\n" +
                            "      </div>\n" +
                            "    </li>"
                    });
                }else {
                    text = "没有匹配的结果"
                }
                $(".goods_type_list").html(text);
            }else
                alert("错误:" + response.errMsg);
        }
    });

    // 3.加载所有商品类型
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

    // 4.加载最新3个spu
    $.ajax({
        url: '/spu/new',
        type:'GET',
        success:function (response) {
            if (response.success){
                text = "";
                goodList = response.list;
                if (goodList != null && goodList.length > 0){
                    goodList.forEach(function (spu) {
                        text += "<li>\n" +
                            "        <a href='/good/detail?spuid=" + spu.spuId + "'><img src='" + spu.imgAddr + "'></a>\n" +
                            "        <h4><a href='/good/detail?spuid=" + spu.spuId + "'>" + spu.spuName + "</a></h4>\n" +
                            // "        <div class=\"prize\">￥{{ sku.price }}</div>\n" +
                            "    </li>\n";
                    });
                }
                $("#new-spu-list").html(text);
            }else
                alert("错误:" + response.errMsg);
        }
    })

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