//页面加载时完成
$(document).ready(function(){
	
});
/**
 * post 异步提交
 * @param action url
 * @param data 提交数据 
 * @param callBack 回调函数
 * @returns
 */
function post(action,data,callBack){
	$.post( action,
			data,
			function(data,status){
			if(data.status){
				(callback && typeof(callback) === "function") && callback();
			}
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
/**
 * 序列化 form 表单 数据
 * @param formId 表单ID
 * @returns
 */
function getFormValues(formId){
	return $("#"+formId).serializeArray();
}
/**
 * 组装查询参数
 * @param className 类名
 * @param type 
 * @returns
 */
function getQueryBean(className,type){
	//初始化查询参数
	var qb = {};
	//选择所有的   qb 样式的元素 的 input输入框
	$("." + className).(":" + type)each(function(index,element){
		var name = $(this).attr("name");
		var val  = $(this).val();
		qb[name] = val;
	});
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