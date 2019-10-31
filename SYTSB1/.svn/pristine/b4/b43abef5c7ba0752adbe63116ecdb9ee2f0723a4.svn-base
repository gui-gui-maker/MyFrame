<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
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
	        getSuccess:function(res){
	        }
	    });
	    
	    function save(){
	    //验证表单数据
			if($('#formObj').validate().form()){
				$("body").mask("正在保存数据，请稍后！");
		    	var formData = $("#formObj").getValues();
				var data = {};
				data = $.ligerui.toJSON(formData);
		        $.ajax({
		            url: "report/print/record/receive.do?ids=${param.ids}&report_print_id=${param.report_print_id}",
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
	});
	
	function selectUser(){
		top.$.dialog({
			width : 200,
			height : 420,
			lock : true,
			title : "选择报送人",
			content : 'url:app/maintenance/choose_user_list.jsp?org_id=${param.org_id}',
			data : {
				"window" : window
			}
		});
	}
	
	function callUser(id, name){ 
		$('#commit_user_id').val(id);
		$('#commit_user_name').val(name);
	}		
    
	function close(){
        api.close();
    }
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post">
		<input type="hidden" name="id" value="${param.ids}"/>
		<input type="hidden" name="fk_report_print_id" value="${param.report_print_id}"/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>报送信息</div>
			</legend>
			<table width="100%">
				<tr>
					<td class="l-t-td-left">报送人：</td>
					<td class="l-t-td-right">
						<input type="hidden" name="commit_user_id" id="commit_user_id" />
						<input type="text"
							name="commit_user_name" id="commit_user_name" ltype="text"
								validate="{required:true}" ligerui="{value:'',iconItems:[{icon:'add',click:function(){selectUser()}}]}" onclick="selectUser()"/>
					</td>				
				</tr>				
				<tr>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right" ><textarea
							name="remark" id="remark" rows="3" cols="25"
							class="l-textarea" validate="{required:false,maxlength:1000}"></textarea>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
