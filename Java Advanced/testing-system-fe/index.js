var accounts = []; // mảng chứa account
var pageNumber = 0; // quản lý trang hiện tại (mặc định là trang đầu tiên - chỉ số 0)
var v_idUpdate = -1;
var vTheme = "";
var baseUrl = "http://localhost:8080/api/v1/accounts";
var baseUrlDepartment = "http://localhost:8080/api/v1/departments";
var baseUrlPosition = "http://localhost:8080/api/v1/positions";
var baseAvt =
    "https://images2.thanhnien.vn/528068263637045248/2024/1/25/e093e9cfc9027d6a142358d24d2ee350-65a11ac2af785880-17061562929701875684912.jpg";

loadData(); // load ra ds account
loadDepartment();
loadPosition();

// load màu nên ở localStorage
vTheme = localStorage.getItem("theme");
changeTheme(vTheme);

function changeTheme(themeValue) {
    if (themeValue === "dark") {
        // thêm class .dark-theme vào body
        $("body").addClass("dark-theme");
    } else {
        $("body").removeClass("dark-theme");
    }
    localStorage.setItem("theme", themeValue);
}

function loadData() {
    // lấy ra các gtri cần tìm kiếm
    var usernameSearch = $("#usernameSearch").val();
    var fullNameSearch = $("#fullNameSearch").val();
    var emailSearch = $("#emailSearch").val();
    var departmentIdSearch = $("#deparmentSearchID").val();
    var positionIdSearch = $("#positionSearchID").val();

    //lấy ra các giá trị liên quan đến phân trang
    var size = $("#numberOfRecordId").val();

    var subUrl = `?username=${usernameSearch}&departmentId=${departmentIdSearch}&fullName=${fullNameSearch}&email=${emailSearch}&positionId=${positionIdSearch}&size=${size}&page=${pageNumber}`;
    console.log(baseUrl + subUrl);

    // call api đến mockapi.io đe lấy ds account
    // jqAjax
    $.ajax({
        type: "GET",
        url: baseUrl + subUrl,
        dataType: "JSON",
        success: function (response) {
            // call api thanh cong
            accounts = response.content;
            var tableContent = "";
            for (let i = 0; i < accounts.length; i++) {
                tableContent += "<tr>";
                tableContent += "<td>" + accounts[i].id + "</td>";
                tableContent +=
                    "<td><img src=" +
                    baseAvt +
                    " style='height: 50px' alt='Image' /></td>";
                tableContent += "<td>" + accounts[i].username + "</td>";
                tableContent += "<td>" + accounts[i].fullName + "</td>";
                tableContent += "<td>" + accounts[i].email + "</td>";
                tableContent += "<td>" + accounts[i].departmentName + "</td>";
                tableContent += "<td>" + accounts[i].positionName + "</td>";
                tableContent +=
                    "<td><button onclick='onHandleEdit(" +
                    accounts[i].id +
                    ")'>Edit</button> " +
                    " <button onclick='onDelete(" +
                    accounts[i].id +
                    ")'>Delete</button></td>";
                tableContent += "</tr>";
            }
            $("#tableBoby").empty();
            // jqAppend
            $("#tableBoby").append(tableContent);

            // hiển thị thông tin paging  pagingId
            $("#pagingId").empty();

            // Nút TRƯỚC (<<)
            var prevPageClick = response.first
                ? ""
                : `onclick="changePage(${pageNumber - 1})"`;
            $("#pagingId").append(
                `<li ${response.first == true ? `class="disabled"` : ``}><a href="#" ${prevPageClick}>&laquo;</a></li>`,
            );

            // load các trang tương ứng ra
            var totalPage = response.totalPages;
            for (let i = 0; i < totalPage; i++) {
                if (i == pageNumber) {
                    // Thêm class="active" để nút sáng màu xanh chuẩn Bootstrap 3
                    $("#pagingId").append(
                        `<li class="active"><a href="#">${i + 1}</a></li>`,
                    );
                } else {
                    $("#pagingId").append(
                        `<li><a href="#" onclick="changePage(${i})">${i + 1}</a></li>`,
                    );
                }
            }

            // Nút SAU (>>)
            var nextPageClick = response.last
                ? ""
                : `onclick="changePage(${pageNumber + 1})"`;
            $("#pagingId").append(
                `<li ${response.last == true ? `class="disabled"` : ``}><a href="#" ${nextPageClick}>&raquo;</a></li>`,
            );
        },
        error: function (error) {
            alert("Call api get accounts thất bại");
        },
    });
}

function onDelete(idDelete) {
    var check = confirm("Bạn có chắc chắn xóa account này?");
    if (check) {
        $.ajax({
            type: "DELETE",
            url: baseUrl + "/" + idDelete,
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

function onCreate() {
    if (v_idUpdate > 0) {
        alert("Đang update, ko thể tạo mới dc");
        return;
    }
    var v_avatar = $("#inputAvatar").val();
    var v_username = $("#inputUsername").val();
    var v_fullName = $("#inputFullname").val();
    var v_email = $("#inputEmail").val();
    var v_departmentID = $("#inputDepartmentName").val();
    var v_positionID = $("#inputPositionName").val();

    var account = {
        username: v_username,
        fullName: v_fullName,
        email: v_email,
        departmentId: v_departmentID,
        positionId: v_positionID,
    };

    $.ajax({
        type: "POST",
        url: baseUrl,
        data: JSON.stringify(account),
        contentType: "application/json",
        success: function (response) {
            alert("Thêm dữ liệu thành công");
            loadData();
            $("#inputAvatar").val("");
            $("#inputUsername").val("");
            $("#inputFullname").val("");
            $("#inputEmail").val("");
            $("#modal-id").modal("hide");
        },
        error: function (error) {
            alert("Call api thêm mới thất bại");
        },
    });
}

$("#submit").click(function (e) {
    if (v_idUpdate <= 0) {
        onCreate();
    } else {
        onUpdate();
    }
});

function resetForm() {
    $(".modal-title").empty();
    $(".modal-title").append("<div>Create Account</div>");
    $("#inputAvatar").val("");
    $("#inputUsername").val("");
    $("#inputFullname").val("");
    $("#inputAge").val("");
    v_idUpdate = -1;
}

function onHandleEdit(idUpdate) {
    $("#modal-id").modal("show");
    $.ajax({
        type: "GET",
        url: baseUrl + "/" + idUpdate,
        dataType: "JSON",
        success: function (response) {
            $(".modal-title").empty();
            $(".modal-title").append("<div>Update Account</div>");
            $("#inputAvatar").val(response.avatar);
            $("#inputUsername").val(response.username);
            $("#inputFullname").val(response.fullName);
            $("#inputEmail").val(response.email);
            $("#inputDepartmentName").val(response.departmentId);
            $("#inputPositionName").val(response.positionId);
            v_idUpdate = idUpdate;
        },
        error: function (error) {
            alert("Call api lấy thông tin thất bại");
        },
    });
}

function onUpdate() {
    var v_avatar = $("#inputAvatar").val();
    var v_username = $("#inputUsername").val();
    var v_fullName = $("#inputFullname").val();
    var v_email = $("#inputEmail").val();
    var v_departmentID = $("#inputDepartmentName").val();
    var v_positionID = $("#inputPositionName").val();
    var accountUpdate = {
        username: v_username,
        fullName: v_fullName,
        email: v_email,
        departmentId: v_departmentID,
        positionId: v_positionID,
    };
    $.ajax({
        type: "PUT",
        url: baseUrl + "/" + v_idUpdate,
        data: JSON.stringify(accountUpdate),
        contentType: "application/json",
        success: function (response) {
            alert("Update dữ liệu thành công");
            loadData();
            v_idUpdate = -1;
            $("#inputAvatar").val("");
            $("#inputUsername").val("");
            $("#inputFullname").val("");
            $("#inputAge").val("");
            $("#modal-id").modal("hide");
        },
        error: function (error) {
            alert("Call api update thất bại");
        },
    });
}

function loadDepartment() {
    $.ajax({
        type: "GET",
        url: baseUrlDepartment,
        dataType: "JSON",
        success: function (response) {
            var content = "";
            for (let i = 0; i < response.length; i++) {
                content += `<option value="${response[i].id}">${response[i].name}</option>`;
            }
            $("#inputDepartmentName").empty();
            $("#inputDepartmentName").append(content);
            $("#deparmentSearchID").empty();
            $("#deparmentSearchID").append("<option value=''>Tất cả</option>");
            $("#deparmentSearchID").append(content);
        },
        error: function (error) {
            alert("Call api get department thất bại");
        },
    });
}

function loadPosition() {
    $.ajax({
        type: "GET",
        url: baseUrlPosition,
        dataType: "JSON",
        success: function (response) {
            var content = "";
            for (let i = 0; i < response.length; i++) {
                content += `<option value="${response[i].id}">${response[i].name}</option>`;
            }
            $("#inputPositionName").empty();
            $("#inputPositionName").append(content);
            $("#positionSearchID").empty();
            $("#positionSearchID").append("<option value=''>Tất cả</option>");
            $("#positionSearchID").append(content);
        },
        error: function (error) {
            alert("Call api get position thất bại");
        },
    });
}

// Hàm chuyển trang khi click vào số trang hoặc nút trước/sau
function changePage(page) {
    pageNumber = page; // Cập nhật trang hiện tại
    loadData(); // Gọi lại API để tải dữ liệu mới
}

// Bắt sự kiện thay đổi số dòng hiển thị
$("#numberOfRecordId").change(function () {
    pageNumber = 0; // Reset về trang đầu tiên
    loadData();
});
