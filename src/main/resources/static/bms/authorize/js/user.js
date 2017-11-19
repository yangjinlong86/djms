$(document).ready(function () {
    $("#btnCloseUserAlert").click(function () {
        $("#alertMsg").html("");
        $("#userAlert").removeClass("alert-success")
            .removeClass("alert-warning")
            .removeClass("alert-danger")
            .hide();
    });

    // 加载用户数据列表
    queryUser(initQueryBean());
    // 绑定第一页按钮事件
    $("#firstPage").bind("click", function () {
        var qb = initQueryBean();
        if (qb.pageNum != 1) {
            qb.pageNum = 1;
            queryUser(qb);
            $("#pageNum").val(1);
        }
    });

    // 绑定上一页按钮事件
    $("#prevPage").bind("click", function () {
        var qb = initQueryBean();
        var currentPageNum = Number(qb.pageNum);
        if (currentPageNum == 1) {
            this.disabled;
            return;
        }
        qb.pageNum = currentPageNum - 1;
        queryUser(qb);
        $("#pageNum").val(qb.pageNum);
    });

    // 绑定下一页按钮事件
    $("#nextPage").bind("click", function () {
        var qb = initQueryBean();
        var pages = $("#pages").html();
        var currentPageNum = Number(qb.pageNum);
        if (currentPageNum == pages) {
            this.disabled;
            return;
        }
        qb.pageNum = currentPageNum + 1;
        queryUser(qb);
        $("#pageNum").val(qb.pageNum);
    });

    // 绑定最后一页按钮事件
    $("#lastPage").bind("click", function () {
        var qb = initQueryBean();
        var pages = $("#pages").html();
        if (qb.pageNum != pages) {
            qb.pageNum = pages;
            queryUser(qb);
            $("#pageNum").val(pages);
        }

    });

    // 回车键后查询指定页码的数据
    $("#pageNum").bind('keypress', function (event) {
        if (event.keyCode == "13") {
            // 如果输入的值大于总页数,将其置为总页数
            var pages = $("#pages").html();
            if (Number($("#pageNum").val()) > Number(pages)) {
                $("#pageNum").val(pages);
            }
            queryUser(initQueryBean());
        }
    });

    // 每页条数改变后,默认查第一页的数据
    $("#pageSize").bind("change", function () {
        $("#pageNum").val(1);
        queryUser(initQueryBean());
    });

    // 表单验证
    $('#saveUserForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            corpId: {
                validators: {
                    notEmpty: {
                        message: '请选择单位'
                    }
                }
            },
            deptId: {
                validators: {
                    notEmpty: {
                        message: '请选择部门'
                    }
                }
            },
            name: {
                validators: {
                    notEmpty: {
                        message: '请填写用户名'
                    }
                }
            },
            realName: {
                validators: {
                    notEmpty: {
                        message: '请填写昵称'
                    }
                }
            }
        }
    });

    // 为查询按钮绑定提交表单事件
    $("#btnSelect").click(function () {
        var queryBean = initQueryBean();
        queryUser(queryBean);
    });

    // 查询按钮回车事件
    $("#btnSelect").bind("keypress", function (event) {
        if (event.keyCode == "13") {
            $("#btnSelect").click();
        }
    });

    // 保存用户信息
    $('#btnSaveUser').click(function () {
        // 编辑的时候需要手动调用一下validate()方法,否则校验不通过!
        $('#saveUserForm').data("bootstrapValidator").validate();
        if(!$('#saveUserForm').data("bootstrapValidator").isValid()) {
            return false;
        }
        var params = $("#saveUserForm").serializeObject();
        $.ajax({
            type: "post",
            url: "saveUser",
            data: params,
            success: function (res) {
                if ("exist" == res) {
                    $("#alertMsg").html("用户已经存在!");
                    $("#userAlert").removeClass("hidden").addClass("alert-warning").show();
                    return;
                } else if ("true" == res) {
                    $("#alertMsg").html("保存成功!");
                    $("#userAlert").removeClass("hidden").addClass("alert-success").show();
                    $("#userModal").modal('hide');
                    queryUser(initQueryBean());
                } else if ("false" == res) {
                    $("#alertMsg").html("保存失败!");
                    $("#userAlert").removeClass("hidden").addClass("alert-danger").show();
                    return;
                }
            }
        });

    });

    // 绑定保存用户角色关系按钮事件
    $("#btnSaveUserRole").bind("click", function(){
        saveUserRole();
    });

    // 绑定重置表单事件
    $('#btnResetQueryUserForm').bind("click", function () {
        $('#queryBean_corpId').val("");
        $('#queryBean_deptId').val("");
        $('#queryBean_name').val("");
        }
    );

    // 绑定重置表单事件
    $('#resetBtn').bind("click", function () {
            $('#saveUserForm').data('bootstrapValidator').resetForm(true);
        }
    );

    // 绑定编辑用户点击事件
    $("#editUserBtn").bind("click", function () {
        editCheckedUser();
    });

    // 设置用户角色按钮点击事件
    $("#userRoleBtn").bind("click", function(){
        setUserRole();
    });

    // 绑定删除用户事件
    $("#delUserBtn").bind("click", function () {
        deleteCheckedUsers();
    });
});

// 定义一个数组,用来存放用户信息
var userArray;
function queryUser(queryBean) {
    userArray = new Array();
    // 清空table下的内容
    $("#user-list").html("");
    // 显示loading.gif
    $("#loadingDiv").show();

    $.ajax({
        type: "post",
        url: "userList",
        dataType: "json",
        data: queryBean,
        success: function (data) {
            $("#pages").html(data.pages);
            $("#totalNum").html(data.total);

            var users = data.list;
            for (var i = 0; i < users.length; i++) {
                userArray.push(users[i]);
                $("#user-list").append(
                    '<tr>' +
                    '<td style="text-align: center"><input name="checkbox_user" id="checkbox_' + i + '" type="checkbox" value="' + users[i].id + '"></td>' +
                    '<td style="text-align: center">' + users[i].name + '</td>' + // 用户名
                    '<td style="text-align: center">' + users[i].realName + '</td>' + // 昵称
                    '<td>' + users[i].corpId + '</td>' + // 单位
                    '<td>' + users[i].deptId + '</td>' + // 部门
                    '</tr>'
                );
            }
            // 隐藏loading.gif
            $("#loadingDiv").hide();
        }
    });
}

// 编辑选中用户
function editCheckedUser() {
    // 默认隐藏alert提示框
    $("#btnCloseUserAlert").click();
    // 获取选中的用户ID
    var checkedUserIds = getCheckedIds("checkbox_user");
    if (checkedUserIds.count == 0) {
        $("#alertMsg").html("请选择一条数进行编辑!");
        $("#userAlert").removeClass("hidden").addClass("alert-warning").show();
        return;
    }
    if (checkedUserIds.count > 1) {
        $("#alertMsg").html("编辑时只能选择一条数据!");
        $("#userAlert").removeClass("hidden").addClass("alert-warning").show();
        return;
    }
    var user = getUserFromArray(checkedUserIds.values);

    $("#userModalLabel").html("修改用户");
    $("#userModal").modal('show');
    // 自动填充表单
    $("#saveUserForm").autofill(user);
}

function getUserFromArray(userId) {
    var user;
    for (var i = 0; i < userArray.length; i++) {
        if (userId == userArray[i].id) {
            user = userArray[i];
            return user;
        }
    }
}

// 删除用户
function deleteCheckedUsers() {
    // 默认隐藏alert提示框
    $("#btnCloseUserAlert").click();
    // 获取选中的用户ID
    var checkedUserIds = getCheckedIds("checkbox_user");
    if (checkedUserIds.count == 0) {
        $("#alertMsg").html("请选择一条数进行操作!");
        $("#userAlert").removeClass("hidden").addClass("alert-warning").show();
        return;
    }
    $("#delModal").modal('show');
    $("#checkedUserIds_input").val(checkedUserIds.values);
}

function deleteByIds(){
    var ids = $("#checkedUserIds_input").val();
    $.ajax({
        type: "post",
        url: "deleteUser/" + ids,
        async: false,
        success: function (status) {
            if (status == "true") {
                $("#alertMsg").html("删除成功!");
                $("#userAlert").removeClass("hidden").addClass("alert-success").show();
                $("#delModal").modal('hide');
                queryUser(initQueryBean());
            } else {
                alert("delete failed");
            }
        }
    });
}

// 隐藏模态框
$(function () {
    $('#userModal').modal('hide')
});
// hidden.bs.modal	当模态框完全对用户隐藏时触发。
$(function () {
    $('#userModal').on('hide.bs.modal', function () {
        // 重置表单
        $("#resetBtn").click();
    });
});

// queryBean对象,用于与后台交互,包含分页信息,组织机构信息,以及查询用的其他条件
function queryBean(pageNum, limitNum, corpId, deptId, name) {
    this.pageNum = pageNum;
    this.limitNum = limitNum;
    this.corpId = corpId;
    this.deptId = deptId;
    this.name = name;
}

// 初始化queryBean,实时获取页面当前各个查询条件,需要重新调用此方法
function initQueryBean() {
    var user_querybean = new queryBean(
        $("#pageNum").val(),
        $("#pageSize").val(),
        $("#queryBean_corpId").val(),
        $("#queryBean_deptId").val(),
        $("#queryBean_name").val()
    );
    return user_querybean;
}
function setUserRole(){
    // 默认隐藏alert提示框
    $("#btnCloseUserAlert").click();
    // 获取选中的用户ID
    var checkedUserIds = getCheckedIds("checkbox_user");
    if (checkedUserIds.count == 0) {
        $("#alertMsg").html("请选择一条数进行编辑!");
        $("#userAlert").removeClass("hidden").addClass("alert-warning").show();
        return;
    }
    if (checkedUserIds.count > 1) {
        $("#alertMsg").html("编辑时只能选择一条数据!");
        $("#userAlert").removeClass("hidden").addClass("alert-warning").show();
        return;
    }

    $("#roleModalLabel").html("设置用户角色");
    $("#userRoleModal").modal('show');
    // 自动填充表单
    $("#saveUserRoleForm").autofill(user);
    initRoleList();
    var user = getUserFromArray(checkedUserIds.values);
    getUserRoles(user.id);
}

function saveUserRole(){
    var params = {id: $('#user_id').val(), roleValues: getCheckedIds("checkbox_role").values}
    $.ajax({
        type: "post",
        url: "saveUserRole",
        async:false,
        data: params,
        success: function (res) {
            if ("true" == res) {
                $("#alertMsg").html("保存成功!");
                $("#userAlert").removeClass("hidden").addClass("alert-success").show();
                $("#userRoleModal").modal("hide");
                queryUser(initQueryBean());
            } else if ("false" == res) {
                $("#alertMsg").html("保存失败!");
                $("#userAlert").removeClass("hidden").addClass("alert-danger").show();
                return;
            }
        }
    });

}
function getUserRoles(id){
    $("#user_id").val(id);
    // 先将角色全部取消选中
    $("[name='checkbox_role']").removeAttr("checked");
    // 查询用户拥有的角色,选中对应值
    $.ajax({
        type: "post",
        url: "findRoleListByUserId/" + id,
        async:false,
        success: function (roles) {
            if (roles == "" || roles.length == 0) {
                return;
            }
            for (var i = 0; i < roles.length; i++) {
                var checkboxs = $("input[name='checkbox_role']");
                for (var j = 0; j < checkboxs.length; j++) {
                    if (checkboxs[j].value == roles[i].id) {
                        checkboxs.eq(j).attr("checked", 'true');
                    }
                }
            }
        }
    });
}

// 加载所有的角色
function initRoleList() {
    $("#roles_checkboxs").html("");
    $.ajax({
        type: "post",
        url: "selectRoles",
        async:false,
        success: function (pageInfo) {
            if (pageInfo != null && pageInfo.list.length > 0) {
                for (var i = 0; i < pageInfo.list.length; i++) {
                    $("#roles_checkboxs").append(
                        '<label>' +
                        '<input type="checkbox" name="checkbox_role" value="' + pageInfo.list[i].id + '">' + pageInfo.list[i].roleName +
                        '</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
                    );
                }
            }
        }
    });
}
