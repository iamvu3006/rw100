var baseUrl = "http://localhost:8080/api/v1/auth";

function handleLogin() {
    // lấy username + password
    var inputUsername = $("#inputUsername").val();
    var inputPassword = $("#inputPassword").val();
    var acc = {
        username: inputUsername,
        password: inputPassword,
    };

    // call api login
    $.ajax({
        type: "POST",
        url: baseUrl + "/login",
        contentType: "application/json",
        data: JSON.stringify(acc),
        success: function (response) {
            var acc = {
                username: inputUsername,
                token: response.token,
            };
            localStorage.setItem("loginInfor", JSON.stringify(acc));
            alert("Đăng nhập thành công");
            window.open("./index.html", "_self");
        },
        error: function (error) {
            alert("Sai thông tin đăng nhập");
        },
    });
}

function forgotPassword() {}