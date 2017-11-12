// 获取列表中选中行数据的ID
// 参数:checkbox的name属性值
// 返回值:单条数据返回ID字符串，多条数据返回用逗号隔开的ID字符串
function getCheckedIds(checkbox_name){
    var checkboxs = $('input[name='+checkbox_name+']');
    var checkedUserIds = {
        values:"",
        count:0
    };
    for(var i=0; i<checkboxs.length; i++){
        if(checkboxs[i].checked){
            checkedUserIds.values += checkboxs[i].value + ",";
            checkedUserIds.count++;
        }
    }
    var values = checkedUserIds.values;
    checkedUserIds.values = values.substring(0,values.length-1);
    return checkedUserIds;
}