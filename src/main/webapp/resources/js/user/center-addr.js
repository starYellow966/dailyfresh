$(function () {
    // 1.获取所有当前用户的地址
    $.ajax({
        url: '/user/address/list',
        type: 'GET',
        success:function (response) {
            if (response.success){
                text = "";
                response.list.forEach(function (addr) {
                    text += "<dl>" +
                        "       <dt> " +
                        "           <dd>" + addr.address + "\t（" + addr.addressee + " 收）\t" + addr.phone + "<a href='#'>\t删除</a></dd>" +
                        "       </dt>" +
                        "   </dl>";
                });

                $("#all-addr").html(text);

                console.log(response.list);
            }else
                alert("错误:" + response.errMsg);
        }
    });

    // 2.设置提交新地址
    $(".info_submit").click(function () {
        // 1.检查

        // 2.发送请求
        $.ajax({
            url: '/user/address/add',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            cache:false,
            data: JSON.stringify({
                addressee: $("#username").val(),
                phone: $("#phone").val(),
                address: $("#address").val(),
            }),
            success: function (response) {
                if (response.success)
                    window.location.reload();
                else
                    alert("错误:" + response.errMsg);
            }
        })
    })
})