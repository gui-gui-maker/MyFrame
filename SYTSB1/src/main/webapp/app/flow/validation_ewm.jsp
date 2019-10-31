<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="add">
<title>查询二维码</title>
<script type="text/javascript" src="k/config.js"></script>
<script type="text/javascript" src="k/kui/frame/core.js"></script>
<script type="text/javascript" src="k/kui/frame/main.js"></script>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
$(function(){
	$("#formObj2").initForm({
		success : function(response) {//处理成功
			
			
		}, 
		toolbarPosition : "bottom",
		toolbar : false
	});
});


function validSecondPwd(){
	$("body").mask("正在查询数据，请稍后！");
	$("#submit").attr("disabled","disabled");
	var device_up_code=$("#device_up_code").val();
	$.getJSON("reportValidationAction/getByCode.do?device_up_code="+device_up_code, function(resp){
		//top.$.notice("提交成功！");
	if(resp){
		$("body").unmask();
		$("#tj").remove();
		
	    if(resp.result=="1"){
	    	var address=(resp.address==null)?'':resp.address;
	    	var buildingName=(resp.buildingName==null)?'':resp.buildingName;
	    	var useNumber=(resp.useNumber==null)?'':resp.useNumber;
	    	var area=(resp.area==null)?'':resp.area;
	    	$("#con").append("<div id='tj' style='weight:300px;font-size:16px;'>二维码:"+resp.registNumber+"<br/>设备代码:"+resp.registCode+"<br/>地址:"+address+"<br/>楼盘:"+buildingName+"<br/>内部编号:"+useNumber+"<br/>所在区划:"+area+"</div>")
	    }else{
	    	$("#con").append("<div id='tj'>未查到相关设备信息，请核实二维码是否正确！</div>")
	    }
		
	}
})
}
</script>
<style type="text/css">
	td,input{font-size:14px;}
	.mdy a{text-decoration:underline;}
	.l-fieldset1 { padding:5px;border:1px solid #a2c8fb;margin:100px 300px 100px 300px;border-radius:5px 5px 5px 5px;}
	.l-legend1 { padding:0px 5px;border:1px solid #a2c8fb;background:#ecf8ff;border-radius:5px 5px 5px 5px; }
	.l-legend1 div { padding:0px; font-weight:bold; }
</style>
</head>
<body>
	<form name="formObj2" id="formObj2" method="post"
		action="reportValidationAction/getByCode.do" style="margin:2em">
		<input type="hidden" name="request_uri" value="/app/finance/clfbxd_list.jsp"/>
		<fieldset class="l-fieldset1">
			<legend class="l-legend1">
				<div>查询设备信息</div>
			</legend>
			<table id="tab1" border="0" cellpadding="3" cellspacing="0" width=""
				align="center">
				<tr>
					<td class="l-t-td-left">二维码：</td>
					<td class="l-t-td-right"><input name="device_up_code"
						id="device_up_code" type="text" ltype="text"
						validate="{required:true,maxlength:16}" /></td>
				</tr>
				<tr id="tr">
					<td class="l-t-td-left">&nbsp;</td>
					<td class="l-t-td-right" align="right"><a class="l-button-warp l-button"
						href="javascript:validSecondPwd();" style="margin-top:2px"><span
							class="l-button-main l-button-hasicon"><span
								class="l-button-text">查询</span><span
								class="l-button-icon l-icon-discuss"></span>
						</span>
					</a>
				</tr>
			</table>
			<div id="con" style='weight:300px'></div>
		</fieldset>
	</form>
</body>
</html>