<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
var pageStatus="${param.pageStatus}";
var com_ids = "${param.com_ids}";
	<%
		String curDate = DateUtil.getCurrentDateTime();
	%>
	$(function () {
	    $("#formObj").initForm({    //参数设置示例
	       toolbar:[
	            { text:'保存', id:'save',icon:'save', click:save},
	            { text:'关闭', id:'close',icon:'cancel', click:close}
	        ],
	        getSuccess:function(resp){
	        	if (resp.expect_finish_date != null && resp.expect_finish_date != undefined){
					$("#expect_finish_date").val(resp.expect_finish_date);
				}
	        },
	        success: function (response) {//处理成功
				$("body").unmask();
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	            	//top.$.notice("保存成功！");
               		api.close();
                	api.data.window.refreshGrid();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}
	    });	 
	});
	
	function save(){
    	//验证表单数据
		if($('#formObj').validate().form()){
			
			//表单提交
			//$("#formObj").submit();
			var id = "${param.id}";
	    	var formData = $("#formObj").getValues();
			var data = {};
			data = $.ligerui.toJSON(formData);
			var prove_type =$('#prove_type').ligerGetRadioGroupManager().getValue();
			if("1" == prove_type){
				if($('#prove_remark').val()==null||$('#prove_remark').val()==undefined||$('#prove_remark').val()==""){
					$.ligerDialog.alert('论证结论为延期时，请填写论证备注！');
					return;
				}
			}else{
				var expect_finish_date = $('#expect_finish_date').val();
				if(expect_finish_date==null || expect_finish_date==undefined || expect_finish_date==""){
					$.ligerDialog.alert('论证结论为处理时，请选择预计完成日期！');
					return;
				}
			}
			
			var send_msg_type =$('#send_msg_type').ligerGetRadioGroupManager().getValue();			
			$("body").mask("正在保存数据，请稍后！");
	        $.ajax({
	            url: "maintenance/info/prove.do?id="+id+"&send_msg_type="+send_msg_type,
	            data: data,	//JSON.stringify(json)把json转化成字符串
	            cache:false,    
	            type: "POST",
	            datatype: "json",
	            contentType: "application/json; charset=utf-8",
	            success: function (data, stats) {
	            	$("body").unmask();
	                if (data["success"]) {
	                	top.$.notice("保存成功！");
	               		api.close();
	                	api.data.window.refreshGrid();
	                } else {
	                	$.ligerDialog.error(data.msg);
	                }
	            },
	            error: function (data) {
	            	$("body").unmask();
	                $.ligerDialog.error(data.msg);
	            }
	        });
	    }
    }
	
	function selUser12() {
		selectUnitOrUser(1, 1,"" , "", function(callbackData) {
			var userId = callbackData["code"];
			var nameArr = callbackData["name"];
			$("#prove_user_id").val(userId);
			$("#prove_user_name").val(nameArr);
		});
	}
	
	function close(){
    	api.close();
	}
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="maintenance/info/prove.do" getAction="maintenance/info/getTaskDetail.do?id=${param.id}">
		<input type="hidden" name="id" id="id" value="${param.id}" />
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>论证情况</div>
			</legend>
			<table width="100%">
				<tr>
					<td class="l-t-td-left">论证人员：</td>
					<td class="l-t-td-right" colspan="3">
						<input type="hidden" id="prove_user_id" name="prove_user_id"  />  
							<input type="text" id="prove_user_name" name="prove_user_name" validate="{required:true}" ltype="text"
							onclick="selUser12()" ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selUser12()}}]}" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">论证结论：</td>
					<td class="l-t-td-right">
						<input type="radio" name="prove_type" id="prove_type" ltype="radioGroup"
						validate="{required:true}" ligerui="{value:'0',data: [ { text:'处理', id:'0' }, { text:'延期', id:'1' }] }" />
					</td>	
					<td class="l-t-td-left">预计完成日期：</td>
					<td class="l-t-td-right">
						<input name="expect_finish_date" type="text" ltype="date" validate="{required:false}"
						ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="expect_finish_date" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">论证备注：</td>
					<td class="l-t-td-right" colspan="3"><textarea
							name="prove_remark" id="prove_remark" rows="3" cols="25"
							class="l-textarea" validate="{required:false,maxlength:1000}"></textarea>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">反馈方式：</td>
					<td class="l-t-td-right" colspan="3"><input type="radio"
						name="send_msg_type" id="send_msg_type" ltype="radioGroup"
						validate="{required:false}"
						ligerui="{value:'0',data: [{ text:'不反馈', id:'0' } ] }" />
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
