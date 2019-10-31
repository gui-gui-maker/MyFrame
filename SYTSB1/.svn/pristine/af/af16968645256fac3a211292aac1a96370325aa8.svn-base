<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/fwxm/contract/contract_custom_list.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
var pageStatus = "${param.status}";		
<%
String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

%>

var  flag = ${param.flag}

$(function() {
	$("#formObj").initForm({
		/*  toolbar:[
                   {text:"保存", icon:"save", click:function(){
                	   var contract_type = $("#contract_type").ligerGetRadioGroupManager().getValue();
                	   var fk_contract_id = $("#fk_contract_id").val();
                	   if(contract_type=="2"&&fk_contract_id==""){
                		   $.ligerDialog.warn('分包合同需要选择总合同!')
                		   return;
                	   }
                           $("#formObj").submit();
                   },
	            	{text:"取消", icon:"cancel", click:function(){
			        	api.close();
		    }}
               ],
         */
        toolbarPosition :"bottom",
		getSuccess : function(resp) {
		},
		success : function(resp) {//处理成功
			if (resp.success) {
				top.$.notice("保存成功！");
				api.data.window.submitAction();
				api.close();
			} else {

					$.ligerDialog.error('保存失败!')
			}
		}
	});

});


</script>
</head>
<body>
<form name="formObj" id="formObj" method="post" Action="contractCustomLevelAction/save.do"
			getAction="contractCustomLevelAction/detail.do?id=${param.id}">
<br/>
<table cellpadding="3" cellspacing="0" class="l-detail-table">
		<input id="id" name="id" type="hidden" />
		<input id="data_status" name="data_status" type="hidden" value="0"/>
		<tr> 
        	<td class="l-t-td-left"> 级别:</td>
        	<td class="l-t-td-right"> 
        		<input id="levels" name="levels" type="text" ltype='text' validate="{required:true,maxlength:100}"
        	</td>
		</tr>
		<tr> 
        	<td class="l-t-td-left">主要产品:</td>
        	<td class="l-t-td-right"> 
        		<input id="main_product" name="main_product" type="text" ltype='text' validate="{required:true,maxlength:100}"
        	</td>
		</tr>
		<tr> 
			<td class="l-t-td-left"> 备注:</td>
        	<td class="l-t-td-right" > 
        	<textarea rows="3" cols=""  id="remark" name="remark"  ltype='text'></textarea>
        	</td>
        </tr>
		</table>
</form>
</body>
</html>
