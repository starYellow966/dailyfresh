// 首页js
$(function (){

    // 2.获得全部商品类型
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

    // 3.获取headline滚动商品
    $.ajax({
        url: '/headline/',
        type: 'GET',
        success: function (response) {
            if (response.success){
                text = "";
                response.data.forEach(function (item) {
                    text += "<li>" +
                        "      <a href='#'>" +
                        "        <img src='" + item.imgAddr + "' alt='" + item.spu.spuName + "'>" +
                        "      </a>" +
                        "    </li>";
                });
                $(".slide_pics").append(text);
            }else {
                alert("headline错误:" + response.errMsg);
            }
        }
    });

    // 4.获取全部商品类型的前4个商品
    $.ajax({
        url: '/spu/4',
        type: 'GET',
        success: function (response) {
            if (response.success){
                text = "";
                // 解析数据
                for (var typeStr in response.data){  // 每种类型
                    var typeObj = JSON.parse(typeStr);

                    // 纯文本式分类商品展示信息
                    text += "<div class=\"list_title clearfix\">\n" +
                        "        <h3 class=\"fl\" >" + typeObj.typeName + "</h3>\n" +
                        "        <div class=\"subtitle fl\">\n" +
                        "        </div>\n" +
                        "        <a href=\"#\" class=\"goods_more fr\" id=\"#\">查看更多 ></a>\n" +
                        "    </div>";

                    // 图片式分类商品展示信息
                    text += "<div class=\"goods_con clearfix\">\n" +
                        "        <div class=\"goods_banner fl\"><img src='" + typeObj.imgAddr + "'></div>\n" +
                        "        <ul class=\"goods_list fl\">";

                    response.data[typeStr].forEach(function (spu) {  // 每种类型的每一个spu
                        text += "<li>\n" +
                            "       <h4><a href='/good/detail?spuid=" + spu.spuId + "'>" + spu.spuName + "</a></h4>\n" +
                            "       <a href='/good/detail?spuid=" + spu.spuId + "'><img src='" + spu.imgAddr + "' ></a>\n" +
                            // "       <div class=\"prize\">¥ " + spu + "</div>\n" +
                            "    </li>";
                    });
                    text += "</ul>\n" +
                        "    </div>";
                }

                $(".list_model").append(text);
            }else
                alert("错误：" + response.errMsg);
        }
    });

    // 5.获取购物车信息

    // 5. 我也不知道干什么？
    // BCSlideshow('focuspic');
    // var oFruit = document.getElementById('fruit_more');
    // var oShownum = document.getElementById('show_count');
    //
    // var hasorder = localStorage.getItem('order_finish');
    //
    // if(hasorder)
    // {
    //     oShownum.innerHTML = '2';
    // }
    //
    // oFruit.onclick = function(){
    //     window.location.href = 'list.html';
    // }
});