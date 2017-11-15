$(document).ready(function () {
    $("#btnCloseResourceAlert").click(function () {
        $("#alertMsg").html("");
        $("#resourceAlert").removeClass("alert-success")
            .removeClass("alert-warning")
            .removeClass("alert-danger")
            .hide();
    });

    // 加载资源数据列表
    queryResource(initQueryBean());
    // 绑定第一页按钮事件
    $("#firstPage").bind("click", function () {
        var qb = initQueryBean();
        if (qb.pageNum != 1) {
            qb.pageNum = 1;
            queryResource(qb);
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
        queryResource(qb);
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
        queryResource(qb);
        $("#pageNum").val(qb.pageNum);
    });

    // 绑定最后一页按钮事件
    $("#lastPage").bind("click", function () {
        var qb = initQueryBean();
        var pages = $("#pages").html();
        if (qb.pageNum != pages) {
            qb.pageNum = pages;
            queryResource(qb);
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
            queryResource(initQueryBean());
        }
    });

    // 每页条数改变后,默认查第一页的数据
    $("#pageSize").bind("change", function () {
        $("#pageNum").val(1);
        queryResource(initQueryBean());
    });

    // 表单验证
    $('#saveResourceForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            resourceName: {
                validators: {
                    notEmpty: {
                        message: '请填写资源名称'
                    }
                }
            }
        }
    });

    // 为查询按钮绑定提交表单事件
    $("#btnSelect").click(function () {
        var queryBean = initQueryBean();
        queryResource(queryBean);
    });

    $("#btnSelect").bind("keypress", function (event) {
        if (event.keyCode == "13") {
            var queryBean = initQueryBean();
            queryResource(queryBean);
        }
    });

    // 保存资源信息
    $('#btnSaveResource').click(function () {
        if (!$('#saveResourceForm').data("bootstrapValidator").isValid()) {
            return false;
        }
        var resourceList = $("input[name='checkbox_resource']:checked");
        var resourceValues = "";
        resourceList.each(function () {
            resourceValues += $(this).val() + ",";
        })
        resourceValues = resourceValues.substring(0, resourceValues.length - 1);
        $.ajax({
            type: "post",
            url: "saveResource",
            data: {
                resourceName: $('#resourceName').val(),
                resourceDesc: $('#resourceDesc').val()
            },
            success: function (res) {
                if ("exist" == res) {
                    $("#alertMsg").html("资源名称已经存在");
                    $("#resourceAlert").removeClass("hidden").addClass("alert-warning").show();
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
    $('#btnResetQueryResourceForm').bind("click", function () {
            $('#queryResourceForm')[0].reset();
        }
    );

    // 绑定重置表单事件
    $('#resetBtn').bind("click", function () {
            $('#saveResourceForm').data('bootstrapValidator').resetForm(true);
        }
    );

    // 绑定编辑资源点击事件
    $("#editResourceBtn").bind("click", function () {
        editCheckedResource();
    });

    // 绑定删除资源事件
    $("#delResourceBtn").bind("click", function () {
        deleteCheckedResources();
    });
});

// 定义一个数组,用来存放资源信息
var resourceArray;
function queryResource(queryBean) {
    resourceArray = new Array();
    // 清空table下的内容
    $("#resource-list").html("");
    // 显示loading.gif
    $("#loadingDiv").show();

    $.ajax({
        type: "post",
        url: "selectResources",
        dataType: "json",
        data: queryBean,
        success: function (data) {
            $("#pages").html(data.pages);
            $("#totalNum").html(data.total);

            var resources = data.list;
            for (var i = 0; i < resources.length; i++) {
                resourceArray.push(resources[i]);
                $("#resource-list").append(
                    '<tr>' +
                    '<td style="text-align: center"><input name="checkbox_resource" id="checkbox_' + i + '" type="checkbox" value="' + resources[i].id + '"></td>' +
                    '<td style="text-align: center">' + resources[i].resourceName + '</td>' + // 资源名称
                    '<td style="text-align: center">' + resources[i].resourceDesc + '</td>' + // 资源描述
                    '</tr>'
                );
            }
            // 隐藏loading.gif
            $("#loadingDiv").hide();
        }
    });
}

function editCheckedResource() {
    // 默认隐藏alert提示框
    $("#btnCloseResourceAlert").click();
    // 获取选中的资源ID
    var checkedResourceIds = getCheckedIds("checkbox_resource");
    if (checkedResourceIds.count == 0) {
        $("#alertMsg").html("请选择一条数进行编辑!");
        $("#resourceAlert").removeClass("hidden").addClass("alert-warning").show();
        return;
    }
    if (checkedResourceIds.count > 1) {
        $("#alertMsg").html("编辑时只能选择一条数据!");
        $("#resourceAlert").removeClass("hidden").addClass("alert-warning").show();
        return;
    }
    var resource = getResourceFromArray(checkedResourceIds.values);

    $("#resourceModalLabel").html("修改资源");
    $("#resourceModal").modal('show');
    // 自动填充表单
    $("#saveResourceForm").autofill(resource);

    $.ajax({
        type: "post",
        url: "findResourcesByResourceId/" + resource.id,
        success: function (resources) {
            if (resources == "" || resources.length == 0) {
                return;
            }
            // 资源用树形结构展示

        }
    });
}

function getResourceFromArray(resourceId) {
    var resource;
    for (var i = 0; i < resourceArray.length; i++) {
        if (resourceId == resourceArray[i].id) {
            resource = resourceArray[i];
            return resource;
        }
    }
}

// 删除资源
function deleteCheckedResources() {
    // 默认隐藏alert提示框
    $("#btnCloseResourceAlert").click();
    // 获取选中的资源ID
    var checkedResourceIds = getCheckedIds("checkbox_resource");
    if (checkedResourceIds.count == 0) {
        $("#alertMsg").html("请选择一条数进行操作!");
        $("#resourceAlert").removeClass("hidden").addClass("alert-warning").show();
        return;
    }

    $.ajax({
        type: "post",
        url: "deleteResource/" + checkedResourceIds.values,
        async: false,
        success: function (status) {
            if (status == "true") {
                queryResource(initQueryBean());
            } else {
                alert("delete failed");
            }
        }
    });


}

// 隐藏模态框
$(function () {
    $('#resourceModal').modal('hide')
});
// hidden.bs.modal	当模态框完全对资源隐藏时触发。
$(function () {
    $('#resourceModal').on('hide.bs.modal', function () {
        // 重置表单
        $("#resetBtn").click();
    });
});

// queryBean对象,用于与后台交互,包含分页信息,组织机构信息,以及查询用的其他条件
function queryBean(pageNum, limitNum, name) {
    this.pageNum = pageNum;
    this.limitNum = limitNum;
    this.name = name;
}

// 初始化queryBean,实时获取页面当前各个查询条件,需要重新调用此方法
function initQueryBean() {
    var resource_querybean = new queryBean(
        $("#pageNum").val(),
        $("#pageSize").val(),
        $("#queryBean_name").val()
    );
    return resource_querybean;
}
