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
