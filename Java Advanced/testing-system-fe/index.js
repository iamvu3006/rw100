var accounts = []; // mảng chứa account
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

    var subUrl = `?username=${usernameSearch}&departmentId=${departmentIdSearch}&fullName=${fullNameSearch}&email=${emailSearch}&positionId=${positionIdSearch}`;

    // call api đến mockapi.io đe lấy ds account
    // jqAjax
    $.ajax({
        type: "GET",
        url: baseUrl + subUrl,
        dataType: "JSON",
        success: function (response) {
            // call api thanh cong
            accounts = response;
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
            //         {
            //     "id": 1,
            //     "username": "annguyen1",
            //     "fullName": "Nguyễn Văn An",
            //     "departmentName": "Marketing",
            //     "positionName": "DEV"
            // },
            // trước khi show data thì clear bảng trước
            //jqEmpty
            $("#tableBoby").empty();
            // jqAppend
            $("#tableBoby").append(tableContent);
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

function onCreate(idDelete) {
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

    // đưa các dữ liệu trên vào object // object của js
    var account = {
        username: v_username,
        fullName: v_fullName,
        email: v_email,
        departmentId: v_departmentID,
        positionId: v_positionID,
    };
    //https://images2.thanhnien.vn/528068263637045248/2024/1/25/e093e9cfc9027d6a142358d24d2ee350-65a11ac2af785880-17061562929701875684912.jpg
    // call api dể thêm mới account
    $.ajax({
        type: "POST",
        url: baseUrl,
        data: JSON.stringify(account), // chuyển account từ obejct của JS thành JSON
        contentType: "application/json",
        success: function (response) {
            alert("Thêm dữ liệu thành công");
            // hiển thị lại ds account
            loadData();
            // clear dữ lieu 3 ô username, fullName, age ở tren
            //jqValSet
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

// jqSubmit
// $("#accountForm").submit(function (e) {
//     e.preventDefault();

// });

$("#submit").click(function (e) {
    // nếu v_idUpdate <= 0    thì sẽ tạo mới
    // nếu v_idUpdate > 0 thì sẽ update
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
    // mo modal
    $("#modal-id").modal("show");
    // call api get by id đẻ lấy lấy dữ liệu ra để hiển thị lên các ô input
    $.ajax({
        type: "GET",
        url: baseUrl + "/" + idUpdate,
        dataType: "JSON",
        success: function (response) {
            $(".modal-title").empty();
            $(".modal-title").append("<div>Update Account</div>");
            // hien thi ra cac o input tuong ung
            $("#inputAvatar").val(response.avatar);
            $("#inputUsername").val(response.username);
            $("#inputFullname").val(response.fullName);
            $("#inputEmail").val(response.email);
            // cho các ô select chọn đúng gtri của phòng ban và chức vụ
            $("#inputDepartmentName").val(response.departmentId);
            $("#inputPositionName").val(response.positionId);
            v_idUpdate = idUpdate; // lưu lại id cần update
        },
        error: function (error) {
            alert("Call api lấy thông tin thất bại");
        },
    });
}

function onUpdate(idDelete) {
    var v_avatar = $("#inputAvatar").val();
    var v_username = $("#inputUsername").val();
    var v_fullName = $("#inputFullname").val();
    var v_email = $("#inputEmail").val();
    var v_departmentID = $("#inputDepartmentName").val();
    var v_positionID = $("#inputPositionName").val();
    // lay ra doi tuong can update
    var accountUpdate = {
        username: v_username,
        fullName: v_fullName,
        email: v_email,
        departmentId: v_departmentID,
        positionId: v_positionID,
    };
    // call api update
    $.ajax({
        type: "PUT",
        url: baseUrl + "/" + v_idUpdate,
        data: JSON.stringify(accountUpdate),
        contentType: "application/json",
        success: function (response) {
            alert("Update dữ liệu thành công");
            // hiển thi ls account
            loadData();
            //jqValSet
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
    //call api den BE lay ds department
    $.ajax({
        type: "GET",
        url: baseUrlDepartment,
        dataType: "JSON",
        success: function (response) {
            //
            var content = "";
            for (let i = 0; i < response.length; i++) {
                // content += "<option value=''>" + response[i].name + "</option>";
                content += `<option value="${response[i].id}">${response[i].name}</option>`;
            }
            $("#inputDepartmentName").empty();
            $("#inputDepartmentName").append(content);
            // load cho ô tìm kiếm
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
            //
            var content = "";
            for (let i = 0; i < response.length; i++) {
                // content += "<option value=''>" + response[i].name + "</option>";
                content += `<option value="${response[i].id}">${response[i].name}</option>`;
            }
            $("#inputPositionName").empty();
            $("#inputPositionName").append(content);
            // load cho ô tìm kiếm
            $("#positionSearchID").empty();
            $("#positionSearchID").append("<option value=''>Tất cả</option>");
            $("#positionSearchID").append(content);
        },
        error: function (error) {
            alert("Call api get position thất bại");
        },
    });
}
