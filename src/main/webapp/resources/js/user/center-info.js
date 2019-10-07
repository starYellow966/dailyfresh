$(function () {
    // 1.获取当前session的登录用户
    $.ajax({
        url: '/user/curuser',
        type: 'POST',
        success: function (response) {
            if (response.success){
                // 1.1 修改网页顶部元素
                text = "";
                if (response.user != null){
                    // console.log(response.user);
                    text += "<div class=\"login_btn\">\n" +
                        "      欢迎您：<em>" + response.user.userName+ "</em>\n" +
                        "      <span>|</span>\n" +
                        "      <a href=\"/user/logout\">退出</a>\n";
                }
                else {
                    text += "    </div>\n" +
                        "           <div class=\"login_btn\">\n" +
                        "            <a href=\"/user/\">登录</a>\n" +
                        "            <span>|</span>\n" +
                        "            <a href=\"/user/reg\">注册</a>\n" +
                        "        </div>";
                }
                $("#login-div").append(text);

                // 1.2 设置网页主要内容部分
                text = "<li><span>用户名：</span>" + response.user.userName + "</li>\n" +
                    "                <li><span>联系方式：</span>" + response.user.phone + "</li>\n" +
                    "                <li><span>email  ：</span>" + response.user.email + "</li>\n";
                $(".user_info_list").append(text);
            }else {
                alert("user错误：" + data.errMsg);
            }
        }
    });

    // 2.获取最近浏览信息
});