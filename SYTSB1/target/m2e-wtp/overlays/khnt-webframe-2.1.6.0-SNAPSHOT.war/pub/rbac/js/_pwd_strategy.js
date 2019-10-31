$(function () {
	$("#formObj").initForm({
		toolbar:null,
		afterParse: initPage
	});
	
	$("#saveRule").ligerButton({
		text: "保存规则",
		icon: "save",
		click: function(){
			var pwdRuleUse = $("#pwdRule").ligerGetCheckBoxManager().getValue();
			var params = {};
			if(pwdRuleUse=="on"){
				params.pwdRuleUse = "1";
				var length = $("#length").ligerGetSpinnerManager().getValue();
        		var letter = $("#letter").ligerGetCheckBoxManager().getValue();
        		var numbers = $("#numbers").ligerGetCheckBoxManager().getValue();
        		var other = $("#other").ligerGetCheckBoxManager().getValue();
        		if(length == "" && letter == "" && numbers == "" && other == ""){
        			$.ligerDialog.alert("密码规则至少需要包含一项！");
        			return;
        		}
        			
        		if(length !="" && length > 0 && length <= 16)
    				params.length = length;
        		if(letter=="on")
        			params.letter = "1";
        		if(numbers=="on")
        			params.number = "1";
        		if(other=="on")
        			params.other = "1";
				
			}else{
				params.pwdRuleUse = "0";
			}
			$("body").mask("正在保存设置...");
			$.post("security/web/pwd/setRule.do",params,function(r){
				$("body").unmask();
				if(r.success)
					top.$.notice("设置成功！",3);
				else
					$.ligerDialog.error("设置失败！<br/>" + (r.msg||""));
			},"json");
		}
	});
	$("#saveExpiry").ligerButton({
		text: "保存设置",
		icon: "save",
		click: function(){
			var expiryUse = $("#pwdExpiry").ligerGetCheckBoxManager().getValue();
			var immv = $("#isMustModify").ligerGetCheckBoxManager().getValue();
			var params;
			if(expiryUse=="on"){
				var maxDay = $("#maxDays").ligerGetSpinnerManager().getValue();
				if(maxDay==""){
					$.ligerDialog.alert("密码有效期不能空！");
					return;
				}
				params = {
					isUse: "1",
					maxUseDay: maxDay,
					isMustModify: immv=="on"?"1":"0"
				};
			}else{
				params = {isUse: "0",maxUseDay:0};
			}
			$("body").mask("正在保存设置...");
			$.post("security/web/pwd/setExpiry.do",params,function(r){
				$("body").unmask();
				if(r.success)
					top.$.notice("设置成功！",3);
				else
					$.ligerDialog.error("设置失败！<br/>" + (r.msg||""));
			},"json");
		}
	});
	$("#saveInitPwd").ligerButton({
		text: "保存设置",
		icon: "save",
		click: function(){
			var initPwd = $("#initPwd").ligerGetSpinnerManager().getValue();
			var mmpd = $("#mmpd").ligerGetCheckBoxManager().getValue();
			
			if(initPwd==""){
				$.ligerDialog.alert("初始密码不能空！");
				return;
			}
			
			$("body").mask("正在保存设置...");
			$.post("security/web/pwd/setInitPwd.do",{dpwd:initPwd,m:mmpd=="on"?"1":"0"},function(r){
				$("body").unmask();
				if(r.success)
					top.$.notice("设置成功！",3);
				else
					$.ligerDialog.error("设置失败！<br/>" + (r.msg||""));
			},"json");
		}
	});
});

function initPage(){
	//初始密码
	$("#initPwd").val(initPwd);
	$("#mmpd").ligerGetCheckBoxManager().setValue(mmdInitPwd=="1"?"on":"");
	
	//密码规则
	if(pwdRuleUse=="0" || pwdRule=="null" || $.kh.isNull(pwdRule)){
		$("#pwdRule").ligerGetCheckBoxManager().setValue("");
		$("#length").ligerGetSpinnerManager().setDisabled();
		$("#letter").ligerGetCheckBoxManager().setDisabled();
		$("#numbers").ligerGetCheckBoxManager().setDisabled();
		$("#other").ligerGetCheckBoxManager().setDisabled();
	}else{
		$("#pwdRule").ligerGetCheckBoxManager().setValue("on");
		if(pwdRule["length"])
			$("#length").ligerGetSpinnerManager().setValue(pwdRule["length"]);
		if("1" == pwdRule["letter"])
			$("#letter").ligerGetCheckBoxManager().setValue("on");
		if("1" == pwdRule["number"])
			$("#numbers").ligerGetCheckBoxManager().setValue("on");
		if("1" == pwdRule["other"])
			$("#other").ligerGetCheckBoxManager().setValue("on");
	}
	
	//密码过期策略
	if(pwdExpiry=="0" || pwdExpiry=="null"||$.kh.isNull(pwdExpiry)){
		$("#pwdExpiry").ligerGetCheckBoxManager().setValue("");
		$("#maxDays").ligerGetSpinnerManager().setDisabled();
	}else{
		$("#pwdExpiry").ligerGetCheckBoxManager().setValue("on");
		$("#maxDays").ligerGetSpinnerManager().setValue(maxExpiryDay);
		$("#isMustModify").ligerGetCheckBoxManager().setValue(mustModifyPwd=="1"?"on":"");
	}
}

function setExpiryUsed(obj){
	if(obj.checked){
		$("#maxDays").ligerGetSpinnerManager().setEnabled();
	}else{
		$("#maxDays").ligerGetSpinnerManager().setDisabled();
	}
}

function setRulesUsed(obj){
	if(!obj.checked){
		$("#length").ligerGetSpinnerManager().setDisabled();
		$("#letter").ligerGetCheckBoxManager().setDisabled();
		$("#numbers").ligerGetCheckBoxManager().setDisabled();
		$("#other").ligerGetCheckBoxManager().setDisabled();
	}else{
		$("#length").ligerGetSpinnerManager().setEnabled();
		$("#letter").ligerGetCheckBoxManager().setEnabled();
		$("#numbers").ligerGetCheckBoxManager().setEnabled();
		$("#other").ligerGetCheckBoxManager().setEnabled();
	}
}