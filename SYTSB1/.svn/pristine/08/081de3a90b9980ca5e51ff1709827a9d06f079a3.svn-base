function selectOrgUser(options) {
	var cfg = $.extend({
		code : "",
		name : "",
		isAsyn : "1",
		checkbox: false,
		callback: function(){}
	},options);

	selectUnitOrUser(cfg.type, cfg.checkbox, cfg.code, cfg.name, cfg.callback,cfg.isAsyn);
}

/**
 * 选取角色人员
 * 
 * @param roles 角色ID
 * @param range 角色范围，机构ID
 * @param checkbox 是否多选
 * @param code 设值ID字段id
 * @param name 设值name字段id
 * @param callback 回调方法
 */
function chooseRoleUser(roles,range,checkbox,code,name,callback){
	var roleRange = range?range:"";
	var uroles = roles.replace(/,+$/,"").replace(/^,+/,"").replace(/,+/,"','");
	top.$.dialog({
		width : 1000,
		height : 500,
		lock : true,
		parent : api,
		id : "selectRoleUser",
		title : "选择角色人员",
		content : "url:pub/selectUser/select_role_user.jsp?roles=" + uroles + "&range=" + roleRange+"&checkbox="+checkbox,
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
function selectUnitOrUser(type, isCheckBox, code, name, callback, isAsyn) {
	if(!window["__chooseSystemResource"]){
		$("head").append('<script type="text/javascript" src="pub/common/common.js"></script>');
	}
	var url,title,width = 1000;
	if (type == "0" || type == "00" || type=="000") {
		if(type == "0"){
			title = "单位/部门选择";
			url = "url:pub/selectUser/org_select.jsp?type=" + type + "&_r="+Math.random();
			try {
				if(isAsyn=="1"){
					url = "url:pub/selectUser/org_select_asyn.jsp?type=" + type + "&_r="+Math.random();
				}
			} catch (e) {
			}
			width = 350;
		}else{
			var range = type=="00"?"all":"sa";
			__chooseSystemResource("unit",{
			    range: range,
			    multiple: isCheckBox==1,
			    sync: isAsyn,
			    idField: code,
			    nameField: name,
			    callback: callback
			});
			return;
		}
	}
	else if (type == "1" || type == "10" || type=="11") {
		if(type == "1"){
			title = "人员选择";
			url = "url:pub/selectUser/org_user_select.jsp?type=" + type;
			width = 1200;
		}else{
			__chooseSystemResource("user",{
			    range: type,
			    multiple: isCheckBox==1,
			    idField: code,
			    nameField: name,
			    callback: callback
			});
			return;
		}
	}
	else if (type == "2") {
		title = "系统角色选择";
		url = "url:pub/selectUser/role_select.jsp?_r="+Math.random();
		width = 600;
	}
	else if (type == "3" || type == "33" || type == "30") {
		title = "单位/部门和人员选择";
		url = "url:pub/selectUser/org_user_select.jsp?chooseOrg=1&type=" + type + "&_r="+Math.random();
	}else if(type=="4" || type == "44"){
		title = "岗位选择";
		url = "url:pub/selectUser/org_position_select.jsp?type="+type+"&_r="+Math.random();
	}else if(type=="444"){
		title = "岗位选择";
		url = "url:pub/selectUser/org_position_select.jsp?position=1&type="+type+"&_r="+Math.random();
	}else if(type=="5" || type == "55"){
		title = "人员选择";
		url = "url:pub/selectUser/org_position_select_cw.jsp?type="+type+"&_r="+Math.random();
	}
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