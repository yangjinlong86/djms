$(document).ready(function () {
    $("#btnCloseRoleAlert").click(function () {
        $("#alertMsg").html("");
        $("#roleAlert").removeClass("alert-success")
            .removeClass("alert-warning")
            .removeClass("alert-danger")
            .hide();
    });

    // 加载用户数据列表
    queryRole(initQueryBean());
    // 绑定第一页按钮事件
    $("#firstPage").bind("click", function () {
        var qb = initQueryBean();
        if (qb.pageNum != 1) {
            qb.pageNum = 1;
            queryRole(qb);
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
        queryRole(qb);
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
        queryRole(qb);
        $("#pageNum").val(qb.pageNum);
    });

    // 绑定最后一页按钮事件
    $("#lastPage").bind("click", function () {
        var qb = initQueryBean();
        var pages = $("#pages").html();
        if (qb.pageNum != pages) {
            qb.pageNum = pages;
            queryRole(qb);
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
            queryRole(initQueryBean());
        }
    });

    // 每页条数改变后,默认查第一页的数据
    $("#pageSize").bind("change", function () {
        $("#pageNum").val(1);
        queryRole(initQueryBean());
    });

    // 表单验证
    $('#saveRoleForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            roleName: {
                validators: {
                    notEmpty: {
                        message: '请填写角色名称'
                    }
                }
            }
        }
    });

    // 为查询按钮绑定提交表单事件
    $("#btnSelect").click(function () {
        var queryBean = initQueryBean();
        queryRole(queryBean);
    });

    $("#btnSelect").bind("keypress", function (event) {
        if (event.keyCode == "13") {
            var queryBean = initQueryBean();
            queryRole(queryBean);
        }
    });

    // 保存角色信息
    $('#btnSaveRole').click(function () {
        // 编辑的时候需要手动调用一下validate()方法,否则校验不通过!
        $('#saveRoleForm').data("bootstrapValidator").validate();
        if (!$('#saveRoleForm').data("bootstrapValidator").isValid()) {
            return false;
        }
        $.ajax({
            type: "post",
            url: "saveRole",
            data: $("#saveRoleForm").serializeObject(),
            success: function (res) {
                if ("exist" == res) {
                    $("#alertMsg").html("角色名称已经存在!");
                    $("#roleAlert").removeClass("hidden").addClass("alert-warning").show();
                    return;
                } else if ("true" == res) {
                    $("#alertMsg").html("保存成功!");
                    $("#roleAlert").removeClass("hidden").addClass("alert-success").show();
                    $("#roleModal").modal("hide");
                    var queryBean = initQueryBean();
                    queryRole(queryBean);
                } else if ("false" == res) {
                    $("#alertMsg").html("保存失败!");
                    $("#roleAlert").removeClass("hidden").addClass("alert-danger").show();
                    return;
                }
            }
        });

    });

    // 绑定重置表单事件
    $('#btnResetQueryRoleForm').bind("click", function () {
            $('#queryRoleForm')[0].reset();
        }
    );

    // 绑定重置表单事件
    $('#resetBtn').bind("click", function () {
            $('#saveRoleForm').data('bootstrapValidator').resetForm(true);
        }
    );

    // 绑定编辑用户点击事件
    $("#editRoleBtn").bind("click", function () {
        editCheckedRole();
    });

    // 绑定删除用户事件
    $("#delRoleBtn").bind("click", function () {
        deleteCheckedRoles();
    });
});

// 定义一个数组,用来存放用户信息
var roleArray;
function queryRole(queryBean) {
    roleArray = new Array();
    // 清空table下的内容
    $("#role-list").html("");
    // 显示loading.gif
    $("#loadingDiv").show();

    $.ajax({
        type: "post",
        url: "selectRoles",
        dataType: "json",
        data: queryBean,
        success: function (data) {
            $("#pages").html(data.pages);
            $("#totalNum").html(data.total);

            var roles = data.list;
            for (var i = 0; i < roles.length; i++) {
                roleArray.push(roles[i]);
                $("#role-list").append(
                    '<tr>' +
                    '<td style="text-align: center"><input name="checkbox_role" id="checkbox_' + i + '" type="checkbox" value="' + roles[i].id + '"></td>' +
                    '<td style="text-align: center">' + roles[i].roleName + '</td>' + // 角色名称
                    '<td style="text-align: center">' + roles[i].roleDesc + '</td>' + // 角色描述
                    '</tr>'
                );
            }
            // 隐藏loading.gif
            $("#loadingDiv").hide();
        }
    });
}

function editCheckedRole() {
    // 默认隐藏alert提示框
    $("#btnCloseRoleAlert").click();
    // 获取选中的用户ID
    var checkedRoleIds = getCheckedIds("checkbox_role");
    if (checkedRoleIds.count == 0) {
        $("#alertMsg").html("请选择一条数进行编辑!");
        $("#roleAlert").removeClass("hidden").addClass("alert-warning").show();
        return;
    }
    if (checkedRoleIds.count > 1) {
        $("#alertMsg").html("编辑时只能选择一条数据!");
        $("#roleAlert").removeClass("hidden").addClass("alert-warning").show();
        return;
    }
    var role = getRoleFromArray(checkedRoleIds.values);

    $("#roleModalLabel").html("修改角色");
    $("#roleModal").modal('show');
    // 自动填充表单
    $("#saveRoleForm").autofill(role);

    $.ajax({
        type: "post",
        url: "findResourcesByRoleId/" + role.id,
        success: function (resources) {
            if (resources == "" || resources.length == 0) {
                return;
            }
            // 资源用树形结构展示

        }
    });
}

function getRoleFromArray(roleId) {
    var role;
    for (var i = 0; i < roleArray.length; i++) {
        if (roleId == roleArray[i].id) {
            role = roleArray[i];
            return role;
        }
    }
}

// 删除角色
function deleteCheckedRoles() {
    // 默认隐藏alert提示框
    $("#btnCloseRoleAlert").click();
    // 获取选中的用户ID
    var checkedRoleIds = getCheckedIds("checkbox_role");
    if (checkedRoleIds.count == 0) {
        $("#alertMsg").html("请选择一条数进行操作!");
        $("#roleAlert").removeClass("hidden").addClass("alert-warning").show();
        return;
    }
    $.ajax({
        type: "post",
        url: "deleteRole/" + checkedRoleIds.values,
        async: false,
        success: function (status) {
            if (status == "true") {
                $("#alertMsg").html("删除成功");
                $("#roleAlert").removeClass("hidden").addClass("alert-success").show();
                queryRole(initQueryBean());
            } else {
                $("#alertMsg").html("删除失败");
                $("#roleAlert").removeClass("hidden").addClass("alert-danger").show();
            }
        }
    });


}

// 隐藏模态框
$(function () {
    $('#roleModal').modal('hide')
});
// hidden.bs.modal	当模态框完全对用户隐藏时触发。
$(function () {
    $('#roleModal').on('hide.bs.modal', function () {
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
    var role_querybean = new queryBean(
        $("#pageNum").val(),
        $("#pageSize").val(),
        $("#queryBean_corpId").val(),
        $("#queryBean_deptId").val(),
        $("#queryBean_name").val()
    );
    return role_querybean;
}
