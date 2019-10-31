<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	var pageStatus="${param.pageStatus}";
	$(function () {
	    $("#formObj").initForm({    //参数设置示例
	       toolbar:[
	            { text:'保存', id:'save',icon:'save', click:save},
	            { text:'关闭', id:'close',icon:'cancel', click:close}
	        ],
	        getSuccess:function(res){
	        }
	    });
	    
	    $("#id").val(api.data.ids);
	    
	    function save(){
	   		// 验证表单数据
	   		var is_pass =$('#check_result').ligerGetRadioGroupManager().getValue();
	   		if(is_pass=="2"){	// 1：审核通过 2：审核不通过
	   			var check_remark = $('#check_remark').val();
	   			if(check_remark==null || check_remark==undefined || check_remark==""){
					//top.$.dialog.notice({content:'请填写审核不通过原因！'});
					$.ligerDialog.alert("亲，审核不通过时，请填写备注说明不通过原因！");
					return;
				}
	   		}
	   		var send_msg_type =$('#send_msg_type').ligerGetRadioGroupManager().getValue();
			if($('#formObj').validate().form()){
		    	//var id = "${param.id}";
		    	var formData = $("#formObj").getValues();
				var data = {};
				data = $.ligerui.toJSON(formData);
				$("body").mask("正在保存数据，请稍后！");
		        $.ajax({
		            //url: "report/error/qua_check.do?id="+id,
		            url: "report/error/qua_check.do?ids="+api.data.ids,
		            data: data,	//JSON.stringify(json)把json转化成字符串
		            cache:false,    
		            type: "POST",
		            datatype: "json",
		            contentType: "application/json; charset=utf-8",
		            success: function (resp) {
		            	$("body").unmask();
		                if (resp["success"]) {
		                	if("4" != send_msg_type){
		                		top.$.notice("保存成功！系统后台正在努力发送微信和短信...");
		                		api.data.window.sendMsgs(resp["data"], send_msg_type);
		                	}
		                	api.data.window.Qm.refreshGrid();
		               		api.close();
		                } else {
		                	$("body").unmask();
		                	$.ligerDialog.error(resp.msg);
		                }
		            },
		            error: function (resp) {
		            	$("body").unmask();
		                $.ligerDialog.error(resp.msg);
		            }
		        });
		    }
	    }
	});

	function close(){
		api.close();
	}
</script>
</head>
<body>
<form name="formObj" id="formObj" method="post" >
	<input type="hidden" name="id" id="id" value=""/>
	<fieldset class="l-fieldset">
		<legend class="l-legend">
			<div>不符合报告审核</div>
		</legend>
		<table width="100%">
				<tr>
					<td class="l-t-td-left">审核结果：</td>
					<td class="l-t-td-right"><input type="radio"
						name="check_result" id="check_result" ltype="radioGroup"
						validate="{required:false}"
						ligerui="{value:'1',data: [ { text:'审核通过', id:'1' }, { text:'审核不通过', id:'2' } ] }" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">发送信息：</td>
					<td class="l-t-td-right"><input type="radio"
						name="send_msg_type" id="send_msg_type" ltype="radioGroup"
						validate="{required:false}"
						ligerui="{value:'1',data: [ { text:'微信', id:'1' }, { text:'短信', id:'2' }, { text:'微信和短信', id:'3' }, { text:'不发送', id:'4' } ] }" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">说明：</td>
					<td class="l-t-td-right"><c:out
							value="审核通过，系统将自动发送提醒信息给责任人（用户也可手动点击再次发送）；"></c:out><br /> <c:out
							value="审核不通过，不符合报告流程结束。"></c:out></td>
				</tr>
				<tr>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right">
						<!-- 
					<textarea name="check_remark" id="check_remark" rows="2" ext_type="string" ext_maxLength="200" 
						ext_name="备注" isNull="Y" class="area_text" onfocus="this.innerHTML='';">请在此处填写审核不通过原因！</textarea>
					 --> <textarea name="check_remark" id="check_remark" rows="2"
							ext_type="string" ext_maxLength="200" ext_name="备注" isNull="Y"
							class="area_text"></textarea></td>
				</tr>
			</table>
	</fieldset>
</body>
</html>