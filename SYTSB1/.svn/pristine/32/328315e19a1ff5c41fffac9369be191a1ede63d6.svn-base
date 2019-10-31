<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm"%>
<head pageStatus="${param.status}">
<title></title>
<%
DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
String now = df.format(new Date());

%>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">

$(function() {
	$("#basePeop").initForm({
		 toolbar:[
                   {text:"保存", icon:"save", click:function(){
                	  /*  var contract_type = $("#contract_type").ligerGetRadioGroupManager().getValue();
                	   var fk_contract_id = $("#fk_contract_id").val();
                	   if(contract_type=="2"&&fk_contract_id==""){
                		   $.ligerDialog.warn('分包合同需要选择总合同!')
                		   return;
                	   } */
                           $("#basePeop").submit();
                       }
                   },
	            	{text:"取消", icon:"cancel", click:function(){
			        	api.close();
		    }}
               ],
        
        toolbarPosition :"bottom",
		getSuccess : function(resp) {
		},
		success : function(resp) {//处理成功
			if (resp.success) {
				top.$.notice("归档成功！");
				api.data.window.submitAction();
				api.close();
			} else {

					$.ligerDialog.error('归档失败!')
			}
		}
	});
	
});

	
</script>
</head>
<body>
	<form name="basePeop" id="basePeop" method="post"
		action="contractInfoAction/submbitStep.do?ids=${param.ids}&step=3" >
		<input type="hidden" name="ids" value="${param.ids }"/>
		<br/>
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
			<tr>
				<td class="l-t-td-left">归档日期：</td>
				<td class="l-t-td-right" >
					<input id="op_date" name="op_date" type="text" ltype='date' value="<%=now %>"
					 validate="{required:true}" ligerui="{format:'yyyy-MM-dd'}"/>
				</td>
				</td>
			</tr> 
		</table>
	</form>
</body>
</html>