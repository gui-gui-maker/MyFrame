/**
 * 参数说明： 
 * 参数1：选择范围 
 * 参数2：0.单选；1.多选; 
 * 参数3：要填充的code字段控件id，可为空; 
 * 参数4：要填充的name字段控件id，可为空； 
 * 参数5：操作类型 0:判断是否限号，1：车辆维保，00：无限制条件
 * 参数6：选择完成回调方法，可为空，回调参数为选择的结果：callback(selectResult)
 * isAsyn:选择是否采用异步方式
 */
function selectCar(type, isCheckBox, code, name, op_type, callback, isAsyn) {
	var title = "选择车辆";
	var url = "url:app/oa/car/car_type_manager.jsp?type=" + type
	var width = 1040;
	url += "&checkbox=" + isCheckBox + "&fieldName=" + name + "&fieldId="
			+ code + "&status=1" + "&op_type=" + op_type;
	top.$.dialog({
		width : width,
		height : 500,
		lock : true,
		parent : api,
		id : "selectUnitOrUser",
		title : title,
		content : url,
		cancel : true,
		ok : function() {
			var datas = this.iframe.contentWindow.getSelectResult();
			if (datas) {
				if (code) {
					$("#" + code).val(datas.code);
				}
				if (name) {
					$("#" + name).val(datas.name);
				}
				if (callback)
					callback(datas);
				return true;
			} else
				return false;
		}
	});
}