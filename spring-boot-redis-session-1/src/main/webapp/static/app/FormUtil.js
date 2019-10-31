//将表单序列化成对象
$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};
//判断字符串是否为空
function isEmpty(str){
	if(null==str||''==str||'null'==str){
		return true;
	}
	return false;
}
//渲染select 框
function initSelectTag($element,data,callback) {
	$element.empty();
	$element.append("<option value=''></option>");
	for ( var i in data) {
		$element.append(
				"<option value='" + data[i]['key'] + "'>" + data[i]['key']
						+ "-" + data[i]['value'] + "</option>");
	}
	if(callback){
		callback(data);
	}
}
//渲染select 
function setSelectOptions($element,data,filter,callback) {
	var _data = [];
	for(var i in data){
		if(data[i]['version']==filter['version']){
			_data.push(data[i]);
		}
	}
	$element.empty();
	$element.append("<option value=''></option>");
	for ( var j in _data) {
		$element.append(
				"<option value='" + data[j]['key'] + "'>" + data[j]['key']
						+ "-" + data[j]['value'] + "</option>");
	}
	if(callback){
		callback(_data);
	}
}