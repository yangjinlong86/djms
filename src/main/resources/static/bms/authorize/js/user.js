$(document).ready(function(){

    // 加载用户数据列表
    queryUser(initQueryBean());
    initRoleList();
    // 绑定第一页按钮事件
    $("#firstPage").bind("click",function(){
        var qb = initQueryBean();
        if(qb.pageNum != 1){
            qb.pageNum = 1;
            queryUser(qb);
            $("#pageNum").val(1);
        }
    });

    // 绑定上一页按钮事件
    $("#prevPage").bind("click",function(){
        var qb = initQueryBean();
        var currentPageNum = Number(qb.pageNum);
        if(currentPageNum == 1){
            this.disabled;
            return;
        }
        qb.pageNum = currentPageNum - 1;
        queryUser(qb);
        $("#pageNum").val(qb.pageNum);
    });

    // 绑定下一页按钮事件
    $("#nextPage").bind("click",function(){
        var qb = initQueryBean();
        var currentPageNum = Number(qb.pageNum);
        if(currentPageNum == 10){
            this.disabled;
            return;
        }
        qb.pageNum = currentPageNum + 1;
        queryUser(qb);
        $("#pageNum").val(qb.pageNum);
    });

    // 绑定最后一页按钮事件
    $("#lastPage").bind("click",function(){
        var qb = initQueryBean();
        var totalPage = 10;
        if(qb.pageNum != totalPage){
            // TODO 获取总页数
            qb.pageNum = totalPage;
            queryUser(qb);
            $("#pageNum").val(totalPage);
        }

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
    $("#btnSelect").click(function(){
        var queryBean = initQueryBean();
        queryUser(queryBean);
    });

    // 表单验证
    $('#btnSaveUser').click(function() {
        var roleList = $("input[name='role_checkbox']:checked");
        var roleValues = "";
        var checkBoxValue = "";
        roleList.each(function(){
            roleValues += $(this).val()+",";
        })
        roleValues = roleValues.substring(0,roleValues.length-1);
        $.ajax({
            type: "post",
            url: "saveUser",
            data: {
                id: $('#id').val(),
                name: $('#name').val(),
                realName: $('#realName').val(),
                corpId: $("#corpId").val(),
                deptId: $("#deptId").val(),
                roleValues:roleValues
            }
        });

    });

    // 绑定重置表单事件
    $('#btnResetQueryUserForm').bind("click",function() {
            $('#queryUserForm')[0].reset();
        }
    );

    // 绑定重置表单事件
    $('#resetBtn').bind("click",function() {
            $('#saveUserForm').data('bootstrapValidator').resetForm(true);
        }
    );

    // 绑定编辑用户点击事件
    $("#editUserBtn").bind("click", function(){
        editCheckedUser();
    });
});

// 定义一个数组,用来存放用户信息
var userArray;
function queryUser(queryBean){
    userArray = new Array();
    // 清空table下的内容
    $("#user-list").html("");
    // 显示loading.gif
    $("#loadingDiv").show();

    $.ajax({
        type:"post",
        url: "userList",
        dataType:"json",
        data:queryBean,
        success:function(data){
            var users = data.list;
            for (var i = 0; i < users.length; i++) {
                userArray.push(users[i]);
                $("#user-list").append(
                    '<tr>' +
                    '<td style="text-align: center"><input name="checkbox_user" id="checkbox_' + i + '" type="checkbox" value="' + users[i].id + '"></td>' +
                    '<td style="text-align: center">' + users[i].name + '</td>' +      // 用户名
                    '<td style="text-align: center">' + users[i].realName + '</td>' +   // 昵称
                    '<td>' + users[i].corpId + '</td>' +                 // 单位
                    '<td>' + users[i].deptId + '</td>' +                 // 部门
                    '</tr>'
                );
            }
            // 隐藏loading.gif
            $("#loadingDiv").hide();
        }
    });
}

function editCheckedUser(){
    var checkedUserIds = getCheckedUserIds();
    if(checkedUserIds.count == 0){
        alert("请选择一条数据!");
        return;
    }
    if(checkedUserIds.count > 1){
        alert("只能选择一条数据进行编辑!");
        return;
    }
    var user = getUserFromArray(checkedUserIds.values);

    $("#userModalLabel").html("修改用户");
    $("#userModal").modal('show');
    // 自动填充表单
    $("#saveUserForm").autofill(user);
    $.ajax({
        type:"post",
        url:"findRoleListByUserId/"+user.id,
        success:function(roleList){
            if(roleList == "" || roleList.length == 0){
                return;
            }
            for (var i = 0; i < roleList.length; i++) {
                $("#roleId").val(roleList[i].id);
            }
        }
    });
}

// 获取列表中已经勾选的用户ID
// 返回值:单条数据返回ID字符串，多条数据返回用逗号隔开的ID字符串
function getCheckedUserIds(){
    var checkedList = $("input[name='checkbox_user']");
    var checkedUserIds = {
        values:"",
        count:0
    };
    for(var i=0; i<checkedList.length; i++){
        if(checkedList[i].checked){
            if(i != checkedList.length-1){
                checkedUserIds.values += checkedList[i].value+",";
                checkedUserIds.count++;
            }
            checkedUserIds.values += checkedList[i].value;
            checkedUserIds.count++;
        }
    }
    return checkedUserIds;
}

function getUserFromArray(userId){
    var user;
    for(var i = 0; i<userArray.length; i++){
        if(userId == userArray[i].id){
            user = userArray[i];
            return user;
        }
    }
}

// 删除用户
function deleteUser(index){
    var user = userArray[index];
    $.ajax({
        type:"post",
        url:"deleteUser/" +user.id,
        async: false,
        success:function(status){
            if (status == "true") {
                queryUser(initQueryBean());
            } else {
                alert("delete failed");
            }
        }
    });
}

// 隐藏模态框
$(function () { $('#userModal').modal('hide')});
// hidden.bs.modal	当模态框完全对用户隐藏时触发。
$(function () { $('#userModal').on('hide.bs.modal', function () {
        // 重置表单
        $("#resetBtn").click();
    });
});

// queryBean对象,用于与后台交互,包含分页信息,组织机构信息,以及查询用的其他条件
function queryBean(pageNum, limitNum, corpId, deptId, name){
    this.pageNum = pageNum;
    this.limitNum = limitNum;
    this.corpId = corpId;
    this.deptId = deptId;
    this.name = name;
}

// 初始化queryBean,实时获取页面当前各个查询条件,需要重新调用此方法
function initQueryBean(){
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
function initRoleList(){
    $("#roles_checkboxs").html("");
    $.ajax({
        type:"post",
        url:"findAllRoles",
        success:function(roles){
            if(roles != "" && roles.length > 0){
                for(var i=0; i<roles.length; i++){
                    $("#roles_checkboxs").append(
                        '<label>'+
                        '<input type="checkbox" name="role_checkbox" value="'+roles[i].id+'">'+ roles[i].roleDesc +
                        '</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
                    );
                }
            }
        }
    });
}