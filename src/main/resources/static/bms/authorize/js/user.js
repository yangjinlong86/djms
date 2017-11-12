$(document).ready(function () {

    // 加载用户数据列表
    queryUser(initQueryBean());
    initRoleList();
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
    $("#pageNum").bind('keypress',function(event){
        if(event.keyCode == "13") {
            // 如果输入的值大于总页数,将其置为总页数
            var pages = $("#pages").html();
            if($("#pageNum").val() >  pages){
                $("#pageNum").val(pages);
            }
            queryUser(initQueryBean());
        }
    });

    // 每页条数改变后,默认查第一页的数据
    $("#pageSize").bind("change",function(){
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

    // 保存用户信息
    $('#btnSaveUser').click(function () {
        var roleList = $("input[name='checkbox_role']:checked");
        var roleValues = "";
        roleList.each(function () {
            roleValues += $(this).val() + ",";
        })
        roleValues = roleValues.substring(0, roleValues.length - 1);
        $.ajax({
            type: "post",
            url: "saveUser",
            data: {
                id: $('#id').val(),
                name: $('#name').val(),
                realName: $('#realName').val(),
                corpId: $("#corpId").val(),
                deptId: $("#deptId").val(),
                roleValues: roleValues
            },
            success: function (res) {
                if ("exist" == res) {
                    alert("用户名已经存在");
                    return;
                } else if ("true" == res) {
                    alert("保存成功");
                    return;
                } else if ("false" == res) {
                    alert("保存失败");
                    return;
                }
            }
        });

    });

    // 绑定重置表单事件
    $('#btnResetQueryUserForm').bind("click", function () {
            $('#queryUserForm')[0].reset();
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

function editCheckedUser() {
    var checkedUserIds = getCheckedIds("checkbox_user");
    if (checkedUserIds.count == 0) {
        alert("请选择一条数据!");
        return;
    }
    if (checkedUserIds.count > 1) {
        alert("只能选择一条数据进行编辑!");
        return;
    }
    var user = getUserFromArray(checkedUserIds.values);

    $("#userModalLabel").html("修改用户");
    $("#userModal").modal('show');
    // 自动填充表单
    $("#saveUserForm").autofill(user);
    // 全部取消选中
    $("[name='checkbox_role']").removeAttr("checked");
    $.ajax({
        type: "post",
        url: "findRoleListByUserId/" + user.id,
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
function deleteUser(index) {
    var user = userArray[index];
    $.ajax({
        type: "post",
        url: "deleteUser/" + user.id,
        async: false,
        success: function (status) {
            if (status == "true") {
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

// 加载所有的角色
function initRoleList() {
    $("#roles_checkboxs").html("");
    $.ajax({
        type: "post",
        url: "findAllRoles",
        success: function (roles) {
            if (roles != "" && roles.length > 0) {
                for (var i = 0; i < roles.length; i++) {
                    $("#roles_checkboxs").append(
                        '<label>' +
                        '<input type="checkbox" name="checkbox_role" value="' + roles[i].id + '">' + roles[i].roleDesc +
                        '</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
                    );
                }
            }
        }
    });
}