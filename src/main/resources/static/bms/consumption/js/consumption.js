$(document).ready(function () {
    $("#addOrEditConsumption").hide();
    $("#consumptionTable").show();

    $("#addConsumptionBtn").click(function () {
        $("#addOrEditConsumption").show();
        $("#consumptionTable").hide();
    });

    $("#closeFormBtn").click(function () {
        $("#addOrEditConsumption").hide();
        $("#consumptionTable").show();
    });

    queryConsumer(initQueryBean());

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
        queryConsumer(qb);
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
});

// 定义一个数组,用来存放用户信息
var consumerArray;
function queryConsumer(queryBean) {
    consumerArray = new Array();
    // 清空table下的内容
    $("#consumer-list").html("");
    // 显示loading.gif
    $("#loadingDiv").show();
    $.ajax({
        type: "post",
        url: "consumption/consumeList",
        dataType: "json",
        data: queryBean,
        success: function (res) {
            if (res.status) {
                $("#pages").html(res.data.pages);
                $("#totalNum").html(res.data.total);
                var consumers = res.data.list;
                for (var i = 0; i < consumers.length; i++) {
                    consumerArray.push(consumers[i]);
                    $("#consumer-list").append(
                        '<tr>' +
                        '<td style="text-align: center"><input name="checkbox_consumer" id="checkbox_' + i + '" type="checkbox" value="' + consumers[i].id + '"></td>' +
                        '<td style="text-align: center">' + consumers[i].customerId + '</td>' + //
                        '<td style="text-align: center">' + consumers[i].corpId + '</td>' + //
                        '<td style="text-align: center">' + consumers[i].userId + '</td>' + //
                        '<td style="text-align: center">' + consumers[i].formulaId + '</td>' + //
                        '<td style="text-align: center"></td>' + //
                        '<td style="text-align: center">' + consumers[i].amount + '</td>' + //
                        '<td style="text-align: center"></td>' + //
                        '</tr>'
                    );
                }
                // 隐藏loading.gif
                $("#loadingDiv").hide();
            }

        }
    });
}

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