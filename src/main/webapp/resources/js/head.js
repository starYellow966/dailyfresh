// 网页顶部的js
$(function () {
    // 1.查询是否登录
    $.ajax({
        url: '/user/curuser',
        type: 'POST',
        success: function (response) {
            if (response.success){
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
                        "            <a href=\"/user/login\">登录</a>\n" +
                        "            <span>|</span>\n" +
                        "            <a href=\"/user/reg\">注册</a>\n" +
                        "        </div>";
                }
                $("#login-div").append(text);
            }else {
                alert("user错误：" + data.errMsg);
            }
        }
    });
});