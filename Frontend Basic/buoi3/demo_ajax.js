var accounts = [];
var idUpdate = -1;

const API_URL = "https://6a3bc82de4a07f202e15d764.mockapi.io/api/v1/account";

loadData();

function loadData() {
    $.ajax({
        type: "GET",
        url: API_URL,
        dataType: "json",
        success: function (response) {
            accounts = response;
            let tableContent = "";
            for (let i = 0; i < accounts.length; i++) {
                tableContent += "<tr>";
                tableContent += "<td>" + accounts[i].id + "</td>";
                tableContent +=
                    "<td><img src='" +
                    accounts[i].avatar +
                    "' style='height:50px'></td>";
                tableContent += "<td>" + accounts[i].username + "</td>";
                tableContent += "<td>" + accounts[i].fullName + "</td>";
                tableContent += "<td>" + accounts[i].age + "</td>";
                tableContent += "<td>";
                tableContent +=
                    "<button class='btn btn-secondary btn-sm me-1' onclick='onHandleUpdate(" +
                    accounts[i].id +
                    ")'>Edit</button>";
                tableContent +=
                    "<button class='btn btn-danger btn-sm' onclick='onDelete(" +
                    accounts[i].id +
                    ")'>Delete</button>";
                tableContent += "</td>";
                tableContent += "</tr>";
            }
            $("#tblAccount").html(tableContent);
        },
        error: function (xhr) {
            console.log(xhr);
            alert("Load data failed!");
        },
    });
}

function onDelete(idDelete) {
    var check = confirm("Bạn có chắc chắn xóa account này?");

    if (check) {
        $.ajax({
            type: "DELETE",
            url:
                "https://6a3bc82de4a07f202e15d764.mockapi.io/api/v1/account/" +
                idDelete,

            success: function (response) {
                alert("Xóa thành công!");
                loadData();
            },

            error: function (error) {
                alert("Call api xóa thất bại");
            },
        });
    }
}

function onHandleUpdate(id) {
    idUpdate = id;
    $.ajax({
        type: "GET",
        url: API_URL + "/" + idUpdate,
        dataType: "json",

        success: function (response) {
            console.log(response);

            $("#username").val(response.username);
            $("#fullName").val(response.fullName);
            $("#age").val(response.age);

            // Nếu avatar là input text
            $("#avatar").val(response.avatar);
            // Tự động mở modal khi bấm nút Edit ở bảng dữ liệu
            $("#modal-id").modal("show");
        },

        error: function (error) {
            console.log(error);
            alert("Không lấy được dữ liệu!");
        },
    });
}

function onUpdate() {
    if (idUpdate == -1) {
        alert("Vui lòng chọn account cần sửa!");
        return;
    }

    var accountUpdate = {
        avatar: $("#avatar").val(),
        username: $("#username").val(),
        fullName: $("#fullName").val(),
        age: $("#age").val(),
    };

    $.ajax({
        type: "PUT",
        url: API_URL + "/" + idUpdate,
        data: JSON.stringify(accountUpdate),
        contentType: "application/json",

        success: function (response) {
            alert("Update dữ liệu thành công!");
            loadData();
            idUpdate = -1;
            clearForm();
        },

        error: function (error) {
            alert("Call api update thất bại");
        },
    });
}

// function onCreate() {

//     var avatar = $("#avatar").val();
//     var username = $("#username").val();
//     var fullName = $("#fullName").val();
//     var age = $("#age").val();

//     if (
//         avatar == "" ||
//         username == "" ||
//         fullName == "" ||
//         age == ""
//     ) {
//         alert("Vui lòng nhập đầy đủ thông tin");
//         return;
//     }

//     var newAccount = {
//         avatar: avatar,
//         username: username,
//         fullName: fullName,
//         age: age
//     };

//     $.ajax({
//         type: "POST",
//         url: "https://6a3bc82de4a07f202e15d764.mockapi.io/api/v1/account",
//         data: JSON.stringify(newAccount),
//         contentType: "application/json",
//         success: function (response) {
//             alert("Thêm mới thành công!");
//             clearForm();
//             loadData();
//         },
//         error: function (error) {
//             alert("Call API thất bại");
//         }
//     });
// }

// Sự kiện Click vào nút Submit trên Modal (Giống dòng 65 trong hình của thầy)
$("#submit").click(function (e) {
    // Kiểm tra xem có phải đang ở trạng thái Update hay không (nếu idUpdate > 0)
    if (idUpdate > 0) {
        alert("Đang update, không thể tạo mới được");
        return;
    }

    var avatar = $("#avatar").val();
    var username = $("#username").val();
    var fullName = $("#fullName").val();
    var age = $("#age").val();

    if (avatar == "" || username == "" || fullName == "" || age == "") {
        alert("Vui lòng nhập đầy đủ thông tin");
        return;
    }

    var newAccount = {
        avatar: avatar,
        username: username,
        fullName: fullName,
        age: age,
    };

    // Gọi API POST để thêm mới
    $.ajax({
        type: "POST",
        url: API_URL,
        data: JSON.stringify(newAccount),
        contentType: "application/json",

        success: function (response) {
            alert("Thêm mới dữ liệu thành công!");

            // Xóa sạch dữ liệu trên form nhập
            clearForm();

            // Ẩn modal sau khi thêm thành công
            $("#modal-id").modal("hide");

            // Tải lại bảng dữ liệu
            loadData();
        },
        error: function (error) {
            alert("Call API thêm mới thất bại");
        },
    });
});

function clearForm() {
    $("#avatar").val("");
    $("#username").val("");
    $("#fullName").val("");
    $("#age").val("");
    v_idUpdate = -1;
}

function changeTheme(theme) {
    if (theme === "dark") {
        $("html").attr("data-bs-theme", "dark");
    } else {
        $("html").attr("data-bs-theme", "light");
    }
    localStorage.setItem("theme", theme);
}

// Load and apply theme on page load
const savedTheme = localStorage.getItem("theme") || "light";
changeTheme(savedTheme);