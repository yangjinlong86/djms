var page = {};

var qp = {};
//页面加载时完成
$(document).ready(function(){
	dateInit();
	//初始化查询条件
	qb = getQueryBean();
	//加载列表数据
	loadData(qb);
	
	bingPage();
	
	bingQuery()
	//绑定自动填充
	autocomplete("auto");
	//新增客户
	$("#addCustBtn").on("click",function(){
		$('#saveCustForm')[0].reset();
		$("#custModal").modal('show');
	});
	//删除客户
	$("#delCustBtn").on("click",function(){
		var checkdata = getCheckedIds("checkboxIds");
		var msg = validCheckId(1,checkdata.count);
		if(msg != ''){
			alert(msg);
			return;
		}
		 $.ajax({
		        type:"post",
		        url: "delete",
		        dataType:"json",
		        data:{'ids':checkdata.checkIds},
		        success:function(data){
		        	if(data.status){
		        		loadData(qb);
		        	}
		        }
		 	});
	});
	
	//编辑客户
	$("#editCustBtn").on("click",function(){
		$('#saveCustForm')[0].reset();
		var checkdata = getCheckedIds("checkboxIds");
		var msg = validCheckId(2,checkdata.count);
		if(msg != ''){
			alert(msg);
			return;
		}
		var dataId = checkdata.checkIds;
		var data = getDataById(dataId);
		$("#saveCustForm").autofill(data);
		$("#birth").val(data.birth);
		$("#region_auto").val(data.regionStr);
		$("#custModal").modal('show');
		
	});
	//保存或修改
	$("#btnSaveCust").on("click",function(){
		var data = $("#saveCustForm").serializeObject();
		 $.ajax({
		        type:"post",
		        url: "saveOrUpdateCust",
		        dataType:"json",
		        data:data,
		        success:function(data){
		        	if(data.status){
		        		loadData(qb);
		        		$("#custModal").modal('hide');
		        	}
		        }
		 	});
	})
});
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

function dateInit(){
	  
	$('.form_date').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 1,
		format: "yyyy-MM-dd",
	    linkField: "birth",
	    linkFormat: "yyyy-MM-dd"
    });
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
	        url: "custList",
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
			//电话
			dataHtml += '<td style="text-align: center">';
			dataHtml += data.telephone;
			dataHtml += '</td>';
			
			//生日
			dataHtml += '<td style="text-align: center">';
			dataHtml += data.birth;
			dataHtml += '</td>';
			
			//
			dataHtml += '<td style="text-align: center">';
			dataHtml += data.name;
			dataHtml += '</td>';
			
			//家庭住址
			dataHtml += '<td style="text-align: center">';
			dataHtml += data.addr;
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

function auth(){
	//权限 控制按钮是否显示
	//所有有 auth 样式的元素隐藏
	$(".auth").css("display","none");
	//得到用的权限点
	var user_auth = ['bms.sys.user.add'];
	//遍历所有有  auth 样式的 元素
	$(".auth").each(function(index,element){
		var auth = $(this).attr("auth");
		for ( var i in user_auth) {
			if(auth == user_auth[i]){
				$(this).css("display","block");
				break;
			}
		}
	});
}