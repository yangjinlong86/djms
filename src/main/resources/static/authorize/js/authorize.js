$(document).ready(function(){
    // 加载用户数据列表
    queryUser($("#pageNum").val(), $("#pageSize").val());

    // 绑定第一页按钮事件
    $("#firstPage").bind("click",function(){
        if($("#pageNum").val() != 1){
            queryUser(1, $("#pageSize").val());
            $("#pageNum").val(1);
        }
    });

    // 绑定上一页按钮事件
    $("#prevPage").bind("click",function(){
        var currentPageNum = Number($("#pageNum").val());
        if(currentPageNum == 1){
            this.disabled;
            return;
        }
        queryUser(currentPageNum - 1, $("#pageSize").val());
        $("#pageNum").val(currentPageNum - 1);
    });

    // 绑定下一页按钮事件
    $("#nextPage").bind("click",function(){
        var currentPageNum = Number($("#pageNum").val());
        if(currentPageNum == 10){
            this.disabled;
            return;
        }
        queryUser(currentPageNum + 1, $("#pageSize").val());
        $("#pageNum").val(currentPageNum + 1);
    });

    // 绑定最后一页按钮事件
    $("#lastPage").bind("click",function(){
        var totalPage = 10;
        if($("#pageNum").val() != totalPage){
            // TODO 获取总页数
            queryUser(totalPage, $("#pageSize").val());
            $("#pageNum").val(totalPage);
        }

    });

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
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    different: {
                        field: 'name',
                        message: '密码不能与用户名相同'
                    }
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: '请再次填写确认密码'
                    },
                    identical: {
                        field: 'password',
                        message: '两次密码输入不一致'
                    },
                    different: {
                        field: 'name',
                        message: '密码不能与用户名相同'
                    }
                }
            },
            roleId:{
                validators:{
                    notEmpty: {
                        message: '请为用户分配角色'
                    }
                }
            }

        }
    });

    // 表单验证
    $('#btnSaveUser').click(function() {
        $('#saveUserForm').bootstrapValidator('validate');
        //验证通过提交表单
        if($('#saveUserForm').data("bootstrapValidator").isValid()){
            document.getElementById("saveUserForm").submit();
        }
    });

    // 绑定重置表单时间
    $('#resetBtn').bind("click",function() {
            $('#saveUserForm').data('bootstrapValidator').resetForm(true);
        }
    );
});

// 定义一个数组,用来存放用户信息
var userArray = new Array();
function queryUser(pageNum, pageSize){
    $("#user-list").html("");
    // 显示loading.gif
    $("#loadingDiv").show();
    $.post("userList/" + pageNum + "/" + pageSize,
        function(data){
            var users = data.list;
            for(var i=0; i < users.length; i++){
                userArray.push(users[i]);
                $("#user-list").append(
                    '<tr>'+
                    '<td style="text-align: center">'+ users[i].name+'</td>' +      // 用户名
                    '<td style="text-align: center">'+ users[i].realName+'</td>'+   // 昵称
                    '<td>'+ users[i].corpId+'</td>'+                 // 单位
                    '<td>'+ users[i].deptId+'</td>'+                 // 部门
                    '<td></td>'+//部门
                    '<td style="text-align: center">'+
                        // 操作：编辑
                    '<a href="javascript:void(0)" class="btn btn-primary btn-outline btn-sm" data-toggle="modal" onclick="editUser('+i+')"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp; '+
                        // 操作：删除
                    '<a href="javascript:void(0)" class="btn btn-danger btn-outline btn-sm" onclick="deleteUser('+i+')"><i class="fa fa-times"></i></a>'+
                    '</td>'+
                    '</tr>'
                );
            }
            // 隐藏loading.gif
            $("#loadingDiv").hide();
        }
    );
}

// 修改用户信息
// 根据下标访问userArray数据,获取用户信息
function editUser(index){
    var user = userArray[index];
    $("#userModalLabel").html("修改用户");
    $("#userModal").modal('show');
    $("#saveUserForm").autofill(user);
}

// 删除用户
function deleteUser(index){
    var user = userArray[index];
    $.ajax({
        type:"get",
        url:"deleteUser/" +user.id,
        async: false,
        success:function(status){
            if (status == "true") {
                queryUser($("#pageNum").val(), $("#pageSize").val());
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