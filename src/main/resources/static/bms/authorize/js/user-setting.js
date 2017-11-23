$(document).ready(function () {
    $("#btnCloseUserAlert").click(function () {
        $("#alertMsg").html("");
        $("#userAlert").removeClass("alert-success")
            .removeClass("alert-warning")
            .removeClass("alert-danger")
            .hide();
    });

    $('#userSettingForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            password: {
                validators: {
                    notEmpty: {
                        message: '请输入原密码'
                    }
                }
            },
            newPassword: {
                validators: {
                    notEmpty: {
                        message: '请输入新密码'
                    }
                }
            },
            confirmNewPassword: {
                validators: {
                    notEmpty: {
                        message: '请确认新密码'
                    },
                    identical: {
                        field: 'newPassword',
                        message: '两次密码输入不一致'
                    }
                }
            }
        }
    });

    $("#saveUserInfoBtn").bind("click", function () {
        saveUserInfo();
    });
});

function saveUserInfo() {
    $("#btnCloseUserAlert").click();
    $('#userSettingForm').data("bootstrapValidator").validate();
    if (!$('#userSettingForm').data("bootstrapValidator").isValid()) {
        return false;
    }
    var params = $("#userSettingForm").serializeObject();
    $.ajax({
        type: "post",
        url: "updatePassword",
        async: false,
        data: params,
        success: function (res) {
            if (res) {
                $("#alertMsg").html("密码修改成功!");
                $("#userAlert").removeClass("hidden").addClass("alert-success").show();
                $("#userModal").modal('hide');
            } else {
                $("#alertMsg").html("原密码输入不正确!");
                $("#userAlert").removeClass("hidden").addClass("alert-danger").show();
                $("#userModal").modal('hide');
            }
        }
    });
}