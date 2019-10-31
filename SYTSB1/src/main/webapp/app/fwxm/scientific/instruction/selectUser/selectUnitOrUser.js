function selectOrgUser(options) {
	var cfg = {
		type : options.type || 1,
		checkbox : options.checkbox || 0,
		code :options.code || "",
		name :options.name || "",
		callback: options.callback || function(){}
	};
	selectUnitOrUser1(cfg.type, cfg.checkbox, cfg.code, cfg.name, cfg.callback);
}
/**
 * 参数说明：
 * 参数3、4、5都是可选，但是【3、4】和【5】必须存在一个，否则页面获取不到选择结果。
 * 
 * 参数1：[0]选择本单位及以下机构]，[00]选择全部范围内机构，[000]选择本部门及以下；
         [1]选系统全范围人员、[10]选本单位内人员、[11]选择本部门内人员
         [2]选择角色；
         [3]选择本单位及以下机构和人，[33]选择全部范围内的机构和人，[30]只选择本部门及以下机构r；
         [4]岗位选择本单位， [44]选择本部门岗位
         [5]财务出差人选择， [55]财务出差人选择
 * 参数2：0.单选；1.多选;
 * 参数3：要填充的code字段控件id，可为空;
 * 参数4：要填充的name字段控件id，可为空；
 * 参数5：选择完成回调方法，可为空，回调参数为选择的结果：callback(selectResult)
 * isAsyn:单位部门选择是否采用异步方式
 */
function selectUnitOrUser1(type, isCheckBox, code, name, callback,isAsyn) {
	var title = "人员选择";
	var url = "url:app/fwxm/scientific/instruction/selectUser/org_user_select.jsp?type=" + type
	var width = 1000;
	url += "&checkbox=" + isCheckBox + "&fieldName=" + name + "&fieldId=" + code;	
	top.$.dialog({
		width : width,
		height : 500,
		lock : true,
		parent : api,
		id : "selectUnitOrUser",
		title : title,
		content : url,
		cancel: true,
		ok : function() {
			var datas = this.iframe.contentWindow.getSelectResult();
			if(datas){
				if(code)
					$("#" + code).val(datas.code);
				if(name)
					$("#" + name).val(datas.name)
				if(callback) 
					callback(datas);
				return true;
			}
			else return false;
		}
	});
}