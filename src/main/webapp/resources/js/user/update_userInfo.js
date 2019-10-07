$(function () {
    // 初始化控件的值
    $("#title").text('修改' + decodeURIComponent(getQueryVariable("key")));
    $("#key").text(decodeURIComponent(getQueryVariable("key")));
    $("#value").val(getQueryVariable("value"));

    $("#submit").click(function () {

        var field = "";
        switch ($("#key").text()) {
            case "用户名":
                field = "name";break;
            case "邮箱":
                field = "email";break;
            case "手机号":
                field = "phone";break;
            default:
                return;
        }

        $.ajax({
            url: '/user/updateinfo',
            type: 'POST',
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify({
                field: field,
                value: $("#value").val()
            }),
            success:function (data) {
                if (!data.success)
                    alert("修改失败," + data.errMsg);
                window.location.href = '/user/center';
            }
        })
    });

    $.init();  // 默认必须要执行$.init(),实际业务里一般不会在HTML文档里执行，通常是在业务页面代码的最后执行
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