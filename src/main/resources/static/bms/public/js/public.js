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