var accounts = []; // mảng chứa account
var v_idUpdate = -1;
var vTheme = "";
var baseUrl = "http://localhost:8080/api/v1/accounts";
var baseUrlDepartment = "http://localhost:8080/api/v1/departments";
var baseUrlPosition = "http://localhost:8080/api/v1/positions";
var baseAvt = ".\\img\\";
var pageNumber = 0;
var emailRules = {
    name: "Email",
    required: true,
    length: 100,
    pattern: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
};
var usernameRules = {
    name: "Username",
    required: true,
    length: 100,
};
var fullnameRules = {
    name: "Fullname",
    required: true,
    length: 100,
};
var loginInfor = JSON.parse(localStorage.getItem("loginInfor"));

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
function changePage(i) {
    pageNumber = i;
    loadData();
}

function changeSize() {
    pageNumber = 0;
    loadData();
}

function loadData() {
    // lấy ra các gtri cần tìm kiếm
    var usernameSearch = $("#usernameSearch").val();
    var fullNameSearch = $("#fullNameSearch").val();
    var emailSearch = $("#emailSearch").val();
    var departmentIdSearch = $("#deparmentSearchID").val();
    var positionIdSearch = $("#positionSearchID").val();

    // lấy ra các gtri lien quan đến phân trang
    var size = $("#numberOfRecordId").val();

    var subUrl = `?username=${usernameSearch}&departmentId=${departmentIdSearch}&fullName=${fullNameSearch}&email=${emailSearch}&positionId=${positionIdSearch}&size=${size}&sort=id,desc&page=${pageNumber}`;
    // call api đến mockapi.io đe lấy ds account
    // jqAjax
    $.ajax({
        type: "GET",
        url: baseUrl + subUrl,
        dataType: "JSON",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", `Bearer ${loginInfor.token}`);
        },
        headers: {
            Authorization: "Basic " + btoa("admin:123456"),
        },
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
                    accounts[i].avatarUrl +
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
            // trước khi show data thì clear bảng trước
            //jqEmpty
            $("#tableBoby").empty();
            // jqAppend
            $("#tableBoby").append(tableContent);

            // hiển thị thông tin paging  pagingId
            $("#pagingId").empty();
            // if (response.first) {
            //     $("#pagingId").append(`<li class="disabled"><a href="#" aria-disabled="true">&laquo;</a></li>`); // <<
            // } else {
            //     $("#pagingId").append(`<li><a href="#" onclick="changePage(${pageNumber - 1})">&laquo;</a></li>`); // <<
            // }

            $("#pagingId").append(
                `<li ${response.first == true ? `class="disabled"` : ``} ><a href="#" ${response.first == true ? `aria-disabled="true"` : `onclick="changePage(${pageNumber - 1})"`} >&laquo;</a></li>`,
            ); // <<

            // load các trang tương ứng ra
            var totalPage = response.totalPages;
            for (let i = 0; i < totalPage; i++) {
                if (i == pageNumber) {
                    $("#pagingId").append(
                        `<li><a href="#" style="background-color: #4a90e2">${i + 1}</a></li>`,
                    );
                } else {
                    $("#pagingId").append(
                        `<li><a href="#" onclick="changePage(${i})" >${i + 1}</a></li>`,
                    );
                }
            }
            // if (response.last) {
            //     $("#pagingId").append(`<li class="disabled"><a href="#" aria-disabled="true">&raquo;</a></li>`); // >>
            // } else {
            //     $("#pagingId").append(`<li><a href="#" onclick="changePage(${pageNumber + 1})">&raquo;</a></li>`); // >>
            // }
            $("#pagingId").append(
                `<li ${response.last == true ? `class="disabled"` : ``} ><a href="#" ${response.last == true ? `aria-disabled="true"` : `onclick="changePage(${pageNumber + 1})"`} >&raquo;</a></li>`,
            ); // <<
        },
        error: function (error) {},
    });
}

function onDelete(idDelete) {
    var check = confirm("Bạn có chắc chắn xóa account này?");
    if (check) {
        $.ajax({
            type: "DELETE",
            url: baseUrl + "/" + idDelete,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(
                    "Authorization",
                    `Bearer ${loginInfor.token}`,
                );
            },
            success: function (response) {
                alert("Xóa thành công!");
                loadData();
            },
            error: function (error) {},
        });
    }
}

function onCreate() {
    if (
        !validationField("usernameDangerId", "inputUsername", usernameRules) ||
        !validationField("fullNameDangerId", "inputFullname", fullnameRules) ||
        !validationField("emailDangerId", "inputEmail", emailRules)
    ) {
        return;
    } else {
        if (v_idUpdate > 0) {
            alert("Đang update, ko thể tạo mới dc");
            return;
        }

        var file = $("#inputAvatar")[0].files[0];

        // FormData
        var formData = new FormData();
        formData.append("username", $("#inputUsername").val());
        formData.append("fullName", $("#inputFullname").val());
        formData.append("email", $("#inputEmail").val());
        formData.append("departmentId", $("#inputDepartmentName").val());
        formData.append("positionId", $("#inputPositionName").val());
        if (file) formData.append("avatar", file);

        // call api dể thêm mới account
        $.ajax({
            type: "POST",
            url: baseUrl,
            data: formData, // chuyển account từ obejct của JS thành JSON
            contentType: false,
            processData: false,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(
                    "Authorization",
                    `Bearer ${loginInfor.token}`,
                );
            },
            success: function (response) {
                alert("Thêm dữ liệu thành công");
                // hiển thị lại ds account
                loadData();
                // clear dữ lieu 3 ô username, fullName, age ở tren
                //jqValSet
                hideAndResetModal();
            },
            error: function (error) {
                alert(error.responseJSON.message);
            },
        });
    }
}

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
    $("#inputEmail").val("");
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
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", `Bearer ${loginInfor.token}`);
        },
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
        error: function (error) {},
    });
}

function onUpdate(idDelete) {
    if (
        !validationField("usernameDangerId", "inputUsername", usernameRules) ||
        !validationField("fullNameDangerId", "inputFullname", fullnameRules) ||
        !validationField("emailDangerId", "inputEmail", emailRules)
    ) {
        return;
    } else {
        var file = $("#inputAvatar")[0].files[0];
        // FormData
        var formData = new FormData();
        formData.append("username", $("#inputUsername").val());
        formData.append("fullName", $("#inputFullname").val());
        formData.append("email", $("#inputEmail").val());
        formData.append("departmentId", $("#inputDepartmentName").val());
        formData.append("positionId", $("#inputPositionName").val());
        if (file) formData.append("avatar", file);
        // call api update
        $.ajax({
            type: "PUT",
            url: baseUrl + "/" + v_idUpdate,
            data: formData,
            contentType: false,
            processData: false,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(
                    "Authorization",
                    `Bearer ${loginInfor.token}`,
                );
            },
            success: function (response) {
                alert("Update dữ liệu thành công");
                // hiển thi ls account
                loadData();
                //jqValSet
                v_idUpdate = -1;
                hideAndResetModal();
            },
            error: function (error) {
                alert(error.responseJSON.message);
            },
        });
    }
}

function loadDepartment() {
    //call api den BE lay ds department
    $.ajax({
        type: "GET",
        url: baseUrlDepartment,
        dataType: "JSON",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", `Bearer ${loginInfor.token}`);
        },
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
        error: function (error) {},
    });
}

function loadPosition() {
    $.ajax({
        type: "GET",
        url: baseUrlPosition,
        dataType: "JSON",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", `Bearer ${loginInfor.token}`);
        },
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
        error: function (error) {},
    });
}

function validationField(errorId, inputId, rules) {
    // clear ô show lỗi
    $(`#${errorId}`).empty();
    // lấy ra gtri của cần validation
    var inputValue = $(`#${inputId}`).val();
    if (rules.required && inputValue.trim() == "") {
        $(`#${errorId}`).append(`${rules.name} không được để trống`);
        return false;
    }

    if (rules.length && inputValue.length > rules.length) {
        $(`#${errorId}`).append(
            `${rules.name} không dài quá ${rules.length} kí tự`,
        );
        return false;
    }

    if (rules.pattern && !rules.pattern.test(inputValue)) {
        $(`#${errorId}`).append(`${rules.name} không đúng định dạng`);
        return false;
    }
    return true;
}

function hideAndResetModal() {
    $("#inputAvatar").val("");
    $("#inputUsername").val("");
    $("#inputFullname").val("");
    $("#inputEmail").val("");
    $("#emailDangerId").empty();
    $("#fullNameDangerId").empty();
    $("#usernameDangerId").empty();
    $("#modal-id").modal("hide");
}

$("#modal-id").on("hidden.bs.modal", function () {
    hideAndResetModal();
});

function logout() {
    // tro ve trang dang nhap
    window.open("./login.html", "_self");
    // clear localStorage
    localStorage.removeItem("loginInfor");
}