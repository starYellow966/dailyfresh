$(function () {
    // 设置注册按钮的响应事件
    $('#submit').click(function () {
        // 1.判断邮箱
        var reg = new RegExp("^[\\w.\\-]+@(?:[a-z0-9]+(?:-[a-z0-9]+)*\\.)+[a-z]{2,3}$");
        if (!reg.test($("#user-email").val())){
            alert("请输入正确的邮箱地址");
            return;
        }

        // 2.完整性判断
        if ('' == $("#user-pwd").val().trim() || '' == $("#user-email").val().trim() || '' == $("#user-name").val().trim()){
            alert("请填写完整信息");
            return;
        }

        // 发送提交请求 TODO:密码待加密
        $.ajax({
            url: '/user/register',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            cache:false,
            data:JSON.stringify({
                userName: $("#user-name").val(),
                pwd:$("#user-pwd").val(),
                email: $("#user-email").val()
            }),
            success:function (data) {
                if (data.success){
                    // $.toast("登录成功");
                    window.location.href = '/';
                }else {
                    alert("注册失败," + data.errMsg);
                }
            }
        })
    });
});