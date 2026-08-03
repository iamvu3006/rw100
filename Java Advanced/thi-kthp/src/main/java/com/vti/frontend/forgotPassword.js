function sendEmail() {
    var v_email = $("#inputEmail").val();
    var data = {
        email: v_email,
    };
    // call api gửi email
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/v1/auth/forgot-password",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (response) {
            alert("Đã gửi email thành công");
        },
        error: function (error) {
            alert("Gửi email lỗi");
        },
    });
}

function changePassword() {
    // check newpassword và reNewpassword

    var params = new URLSearchParams(window.location.search);
    var v_id = params.get("id");
    var v_token = params.get("token");

    var data = {
        id: v_id,
        token: v_token,
        newPassword: $("#inputReNewPassword").val(),
    };

    // call api
    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/v1/auth/change-password",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (response) {
            alert("Đã đổi mật khẩu, chuyển về trang đăng nhập.");
            window.open("./login.html", "_self");
        },
    });
}