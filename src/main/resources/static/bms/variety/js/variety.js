var page = {};

var qp = {};
//页面加载时完成
$(document).ready(function(){
	//初始化查询条件
	qb = getQueryBean();
	//加载列表数据
	loadData(qb);
	
	bingPage();
	
	bingQuery()
	//绑定自动填充
	autocomplete("auto");
	
	// 表单验证
    $('#saveForm').bootstrapValidator({
        message: '输入不为空',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: '请填名称'
                    }
                }
            },
            brand: {
                validators: {
                    notEmpty: {
                        message: '请选择品牌'
                    }
                }
            }
        }
    });
	//新增
	$("#addBtn").on("click",function(){
		$('#saveForm')[0].reset();
		$("#modal").modal('show');
	});
	//删除
	$("#delBtn").on("click",function(){
		var checkdata = getCheckedIds("checkboxIds");
		var msg = validCheckId(1,checkdata.count);
		if(msg != ''){
			$("#alertMsg").html(msg);
            $("#alert").removeClass("hidden").addClass("alert-warning").show();
			return;
		}
		$("#delModal").modal('show');
	});
	
	$("#delbBtnConfirm").on("click",function(){
		var checkdata = getCheckedIds("checkboxIds");
		 $.ajax({
		        type:"post",
		        url: "variety/deleteByIds",
		        dataType:"json",
		        data:{'ids':checkdata.checkIds},
		        success:function(data){
		        	if(data.status){
		        		loadData(qb);
		        	}
		        }
		 	});
	});
	$("#btnCloseAlert").click(function () {
        $("#alertMsg").html("");
        $("#alert").removeClass("alert-success")
            .removeClass("alert-warning")
            .removeClass("alert-danger")
            .hide();
    });
	//编辑客户
	$("#editBtn").on("click",function(){
		$('#saveForm')[0].reset();
		var checkdata = getCheckedIds("checkboxIds");
		var msg = validCheckId(2,checkdata.count);
		if(msg != ''){
			$("#alertMsg").html(msg);
            $("#alert").removeClass("hidden").addClass("alert-warning").show();
			return;
		}
		var dataId = checkdata.checkIds;
		var data = getDataById(dataId);
		$("#saveForm").autofill(data);
		$("#endDate").val(data.endValidityStr);
		//$("#region_auto").val(data.regionStr);
		$("#modal").modal('show');
		
	});
	//保存或修改
	$("#btnSave").on("click",function(){
		 $('#saveForm').data("bootstrapValidator").validate();
	        if(!$('#saveForm').data("bootstrapValidator").isValid()) {
	            return false;
	        }
		var data = $("#saveForm").serializeObject();
		 $.ajax({
		        type:"post",
		        url: "variety/saveOrUpdate",
		        dataType:"json",
		        data:data,
		        success:function(data){
		        	if(data.status){
		        		$("#alertMsg").html(data.msg);
		                $("#alert").removeClass("hidden").addClass("alert-success").show();
		        		loadData(qb);
		        		$("#modal").modal('hide');
		        	}
		        }
		 	});
	})
});

function loadSelect(dx){
	$("." + dx).on("keyup",function(e){
		var v = $(e).val();
		alert(v);
	});
}
$.fn.serializeObject = function() {  
    var o = {};  
    var a = this.serializeArray();  
    $.each(a, function() {  
        if (o[this.name]) {  
            if (!o[this.name].push) {  
                o[this.name] = [ o[this.name] ];  
            }  
            o[this.name].push(this.value || '');  
        } else {  
            o[this.name] = this.value || '';  
        }  
    });  
    return o;  
} 

function autocomplete(className){
	$("."+className).each(function(index,element){
		var codeType = $(element).attr("code");
		
		var name = $(element).attr("name");
		var hide_id = name.split("_")[0];
		$(element).autocomplete({
		    // 静态的数据源
		    source:'dict/getValues/'+codeType+'/10',
		    dataType: 'json',
		    select: function(event, ui){
		        // 这里的this指向当前输入框的DOM元素
		        // event参数是事件对象
		        // ui对象只有一个item属性，对应数据源中被选中的对象
		        $(this).value = ui.item.label;
		        $("#"+hide_id).val(ui.item.code);
		        // 必须阻止事件的默认行为，否则autocomplete默认会把ui.item.value设为输入框的value值
		        event.preventDefault();     
		    }
		});
	});
}

function loadData(qp){
	    // 显示loading.gif
	    $("#loadingDiv").show();
	    $.ajax({
	        type:"post",
	        url: "variety/varietyList",
	        dataType:"json",
	        data:qp,
	        success:function(data){
	        	listDataHtml(data);
	        }
	    });
}

function initQb(pageInfo){
	page = pageInfo;
}

function bingPage(){
	//第一页
	$("#firstPage").on("click",function(){
		qp = getQueryBean();
		qp['pageNum'] = page.firstPage;
		loadData(qp);
	});
	//最后一页
	$("#lastPage").on("click",function(){
		qp = getQueryBean();
		qp['pageNum'] = page.lastPage;
		loadData(qp);
	});
	//上一页
	$("#prevPage").on("click",function(){
		qp = getQueryBean();
		qp['pageNum'] = page.prePage;
		loadData(qp);
	});
	//下一页
	$("#nextPage").on("click",function(){
		qp = getQueryBean();
		qp['pageNum'] = page.nextPage;
		loadData(qp);
	});
	 $("#pageNum").keydown(function(e) {  
         if (e.keyCode == 13) {
        	 qp = getQueryBean();
        	 var num = $(this).val();
        	 if(isNaN(num) || num == ''){
        		 num = 0;
        	 }
        	 qp['pageNum'] = num; 
        	 loadData(qp);
         }
     });
}

function bingQuery(){
	$(".qb").keydown(function(e) {  
        if (e.keyCode == 13) {
         qp = getQueryBean();
       	 qp['pageNum'] = 1; 
       	 loadData(qp);
        }
    });
	$("#btnSelect").on("click",function(){
		qp = getQueryBean();
		qp['pageNum'] = 1; 
		loadData(qp);
	});
}
//设置分页栏信息
function setPageInfo(){
	//总条数
	$("#total").html(page.total);
	//总页数
	$("#pages").html("共：" + page.pages+"页");
	//当前页
	$("#pageNum").val(page.pageNum);
}

function listDataHtml(res){
	
	if(res.status){
		//清空旧数据
		//$("#data-list").html("");
		var dataHtml = "";
		var pageData = res.data;
		initQb(pageData);
		setPageInfo();
		var dataList = pageData.list;
		
		var data = {};
		for ( var index in dataList) {
			data = dataList[index];
			var dataJson = JSON.stringify(data);
			//主键 每一行数据都放在 tr 里面 方便获取
			dataHtml += '<tr id="'+data.id+'" data=\''+dataJson+'\'>';
			//复选框
			dataHtml += '<td style="text-align: center">';
			dataHtml += '<input name="checkboxIds" type="checkbox" value="'+ data.id + '"/>';
			dataHtml += '</td>';
			//名称
			dataHtml += '<td style="text-align: center">';
			dataHtml += data.name;
			dataHtml += '</td>';
			//类型
			dataHtml += '<td style="text-align: center">';
			dataHtml += data.brandStr;
			dataHtml += '</td>';
			
			dataHtml += '</tr>';
		}
		$("#data-list").html(dataHtml);
		$("#loadingDiv").hide();
	}
}

function getDataById(id){
	var dataStr = $("#" + id).attr("data");
	return JSON.parse(dataStr);
}
/**
 * @param delOrEdit 1删除或2编辑
 * @param name checkbox 的 name
 * 删除验证是否有选中的数据
 * 编辑验证选中一条数据
 * @returns
 */
function validCheckId(delOrEdit,count){
	var msg = "";
	if(delOrEdit == 1){//删除
		if(count==0){
			msg = "请选择一条数据操作";	
		}
	}else if(delOrEdit == 2){//编辑
		if(count==0){
			msg = "请选择一条数据操作";	
		}else if(count > 1){
			msg = "修改时只能选择一条数据";
		}
	}
	return msg;
}
function getCheckedIds(name){
	var checkData = {};
	var count = 0;
	var checkIds ="";
	var checkdList = $("input[name='"+name+"']");
	for(var i=0; i<checkdList.length; i++){
		 if(checkdList[i].checked){
			count ++;
			checkIds += checkdList[i].value + ";";
	       }
	}
	checkData['count']=count;
	if(count == 1){
		checkData['checkIds'] = checkIds.split(";")[0];
	}else{
		checkData['checkIds']=checkIds;
	}
	return checkData;
}

function getQueryBean(){
	var queryBean ={};
	$(".qb").each(function(index,element){
		var e = $(element).prop("tagName")
		if(e == 'INPUT'){
			var name = $(element).attr("name");
			var value = $(element).val();
			queryBean[name] = value
		}
	});
	return queryBean;
}
