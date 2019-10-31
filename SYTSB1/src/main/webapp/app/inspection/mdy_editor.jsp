<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
var pageStatus="${param.pageStatus}";
	$(function () {
	    $("#formObj").initForm({    //参数设置示例
	       toolbar:[
	            { text:'保存', id:'save',icon:'save', click:saveClose },
	          
	            { text:'关闭', id:'close',icon:'cancel', click:close }
	        ],
	        getSuccess:function(res){
	      
	        }
	    });
	});
	
	function saveClose(){
    	var formData = $("#formObj").getValues();
		var data = {};
		data = $.ligerui.toJSON(formData);
		
		var enter_op_id = $("input[name='enter_op_id']").ligerGetComboBoxManager().getValue();
		var enter_op_name = $('#enter_op_name').val();
		if(enter_op_id == ''){
			$("#save").removeAttr("disabled");
			$.ligerDialog.alert("请先选择编制人！");
			return;
		}else{
			var op_users = enter_op_name.split(",");
			if(op_users.length!=1){
				$("#save").removeAttr("disabled");
				$.ligerDialog.alert("只能增加一个编制人！");
				return;
			}else{
				$.ajax({
		            url: "inspectionInfo/basic/addEditor.do?info_id=${param.info_id}&enter_op_id="+enter_op_id+"&enter_op_name="+encodeURIComponent(enter_op_name),
		            data: data,	//JSON.stringify(json)把json转化成字符串
		            cache:false,    
		            type: "POST",
		            datatype: "json",
		            contentType: "application/json; charset=utf-8",
		            success: function (data) {
		                if (data["success"]) {
		                	top.$.notice("修改成功！");
		               		api.close();
		                	api.data.window.submitAction();
		                } else {
		                	$.ligerDialog.error(data.msg);
		                }
		            },
		            error: function (data) {
		                $.ligerDialog.error(data.msg);
		            }
		        });
			}
		}
    }
	
	function close(){
  		api.close();
    }
	
	function setValues(valuex){
		if(valuex==""){
			$('#enter_op_name').val(valuex);   
		}else{
			//var text= $("input[name='enter_op_id']").ligerGetComboBoxManager().getValue();
    		$('#enter_op_name').val(valuex);   
		}
	}
</script>
</head>
<body>

<form name="formObj" id="formObj" method="post" >
	<fieldset class="l-fieldset">
		<legend class="l-legend">
			<div>增加编制人</div>
		</legend>
	<table width="100%">		
		<tr>
			<td class="l-t-td-left">编制人：</td>
			<td class="l-t-td-right">
				<input type="text" name="enter_op_id" id="enter_op_id"
						ltype="select" onchange="setValues(this.value)"
						validate="{required:false}"
						ligerui="{readonly:true, tree:{checkbox:false,data: 
					<u:dict sql="select t.id, t.pid, t.code, t.text from (select o.id as id,o.id as code, o.org_code  as tcode, o.ORG_NAME as text,o.PARENT_ID as pid from sys_org o union select e.id as id, e.id as code, e.code as tcode, e.NAME as text, e.ORG_ID as pid from employee e where e.ORG_ID != '100049') t where t.id!='100049' start with t.id in ('100029','100045','100034', '100035','100033', '100065', '100036','100037', '100066','100067') connect by t.pid = prior t.id ORDER BY T.TCODE"/>}
				}" />
				<input type="hidden"  name="enter_op_name" id="enter_op_name"/>
			</td>
		</tr>
	</table>
	</fieldset>
</form>
</body>
</html>
