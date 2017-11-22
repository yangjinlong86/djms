$(document).ready(function () {

    //新增下级机构按钮点击事件
    $("#addChildOrganBtn").bind("click", function () {
        addChildOrganization();
    });

    //保存机构信息按钮点击事件
    $("#saveOrganizationBtn").bind("click", function () {
        saveOrganization();
    });
});

/**
 * 获取机构信息并填充到表单
 */
function getOrganizationInfo(orgId) {
    $.ajax({
        type: "post",
        async: false,
        url: "/findOrganization/" + orgId,
        success: function (res) {
            var currentOrganization = JSON.parse(res);
            $("#OrganizationForm").autofill(currentOrganization);
            var treeObj = $.fn.zTree.getZTreeObj("organizationTree");
            if (currentOrganization.pId != "0") {
                var node = treeObj.getNodeByParam("id", currentOrganization.pId, null);
                $("#parentOrganization").val(node.name);
            } else {
                $("#parentOrganization").val(currentOrganization.name);
            }
        }
    });
}

// 新增机构
function addChildOrganization() {
    var newOransPId = $("#id").val();
    if (newOransPId == "") {
        alert("请选择要增加下级机构的节点");
        return;
    }
    $("#pId").val(newOransPId);
    $("#id").val("");
    $("#parentOrganization").val($("#name").val());
    $("#name").val("");
    $("#type").val("");
    $("#comment").val("");
    var params = $("#OrganizationForm").serializeObject();
}

// 保存机构信息
function saveOrganization() {
    var params = $("#OrganizationForm").serializeObject();
    $.ajax({
        type: "post",
        async: false,
        url: "/saveOrganization/",
        data: params,
        success: function (res) {
            if (res == "true") {
                window.location.href = "/organization";
            }
        }
    });
}


