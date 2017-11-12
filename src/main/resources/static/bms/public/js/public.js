// 获取列表中选中行数据的ID
// 参数:checkbox的name属性值
// 返回值:单条数据返回ID字符串，多条数据返回用逗号隔开的ID字符串
function getCheckedIds(checkbox_name){
    var checkedBoxs = $('input[name='+ checkbox_name +']:checked');
    var checkedIds = {
        values:"",
        count:0
    };
    checkedBoxs.each(function(){
        checkedIds.values += $(this).val()+",";
        checkedIds.count ++;
    })
    checkedIds.values = checkedIds.values.substring(0,checkedIds.values.length-1);
    return checkedIds;
}
/**
 * 序列化表单数据为json
 * 用法 $("#saveCustForm").serializeObject(); 返回json
 */
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