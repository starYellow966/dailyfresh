$(function () {
    // 设置登录按钮的响应事件
    $('.input_submit').click(function () {
        // 发送提交请求
        $.ajax({
            url: '/user/checkuser',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            cache:false,
            data:JSON.stringify({
                userName: $("#userName").val(),
                pwd:$("#pwd").val()  // TODO:待加密
            }),
            success:function (data) {
                if (data.success){
                    // $.toast("登录成功");
                    window.history.back();
                    // window.location.href = '/';
                }else {
                    alert("登录失败," + data.errMsg);
                }
            }
        })
    });
});