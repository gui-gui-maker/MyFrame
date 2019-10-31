/**
 * 人员和部门的选择：type:0.只选部门、1.只选人员、2.可选部门和人；isCheckBox：0.单选；1.多选;code:部门或人员Id；name：部门或人员名称
 */
function selectUnitOrUser(type, isCheckBox, id, name, tel, callback) {
	var title = "人员选择";
	var selId=$("#"+id).val();
	var selTel=$("#"+tel).val();
	var selName=encodeURIComponent($("#"+name).val());
	var url = "url:app/oa/select/user_select.jsp?id="+selId+"&tel="+selTel+"&name="+selName+"&";
	var width = 800	;
	if (type == "0") {
		title = "单位/部门选择";
		url = "url:app/oa/select/org_select.jsp?id="+selId+"&tel="+selTel+"&name="+selName+"&";
		width = 590;
	}
	else if (type == "2") {
		title = "单位/部门和人员选择";
		url = "url:app/oa/select/org_user_select.jsp?id="+selId+"&tel="+selTel+"&name="+selName+"&";
		width = 900;
	}else if(type == "3") {
		title = "局领导选择";
		url = "url:app/oa/select/jld_select.jsp?id="+selId+"&tel="+selTel+"&name="+selName+"&";
		width = 590;
	}
	url += "checkbox=" + isCheckBox + "&fieldName=" + name + "&fieldId=" + tel;	
	var win=top.$.dialog({
		width : width,
		height : 500,
		lock : false,
		parent : api,
		id : "win98",
		title : title,
		content : url,
		cancel: true,
		ok : function() {
			var datas = this.iframe.contentWindow.getSelectResult();
			if(datas){
				if(datas.tel){
					$("#" + tel).val(datas.tel);
				}
				if(datas.name){
					$("#" + name).val(datas.name);
				}
				if(datas.id){
					$("#"+id).val(datas.id);
				}
				var result = new Object();
				result[id] = datas.id;
				result[tel] = datas.tel;
				result[name] = datas.name;
				if(callback) callback(result);
				return true;
			}
			else{
				return false;
			}
		}
	});
}

//选择指定单位下的人员
function selectUserByOrg(orgId,isCheckBox, id, name, tel, callback) {
	var title = "人员选择";
	var width = 700	;
	var url="url:app/oa/select/user_select.jsp?checkbox="+isCheckBox+"&fieldName="+name+"&fieldId="+tel;	
	top.$.dialog({
		width : width,
		height : 500,
		lock : false,
		parent : api,
		id : "win98",
		title : title,
		content : url,
		cancel: true,
		ok : function() {
			var datas = this.iframe.contentWindow.getSelectResult();
			if(datas){
				if(datas.tel){
					$("#" + tel).val(datas.tel);
				}
				if(datas.name){
					$("#" + name).val(datas.name);
				}
				if(datas.id){
					$("#"+id).val(datas.id);
				}
				var result = new Object();
				result[id] = datas.id;
				result[tel] = datas.tel;
				result[name] = datas.name;
				if(callback) callback(result);
				return true;
			}
			else{
				return false;
			}
		}
	});
}

//选择指定单位下的人员
function selectUserByNoOrg(orgId,isCheckBox, id, name, tel, callback) {
	var title = "人员选择";
	var width = 700	;
	var url="url:app/oa/select/userorg_select.jsp?checkbox="+isCheckBox+"&fieldName="+name+"&fieldId="+tel;	
	top.$.dialog({
		width : width,
		height : 500,
		lock : false,
		parent : api,
		id : "win98",
		title : title,
		content : url,
		cancel: true,
		ok : function() {
			var datas = this.iframe.contentWindow.getSelectResult();
			if(datas){
				if(datas.tel){
					$("#" + tel).val(datas.tel);
				}
				if(datas.name){
					$("#" + name).val(datas.name);
				}
				if(datas.id){
					$("#"+id).val(datas.id);
				}
				var result = new Object();
				result[id] = datas.id;
				result[tel] = datas.tel;
				result[name] = datas.name;
				if(callback) callback(result);
				return true;
			}
			else{
				return false;
			}
		}
	});
	
}

