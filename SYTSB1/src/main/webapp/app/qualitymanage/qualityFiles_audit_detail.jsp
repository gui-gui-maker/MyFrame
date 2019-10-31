		<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %> 
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser user = SecurityUtil.getSecurityUser();
User uu = (User)user.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
%>
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
	<link type="text/css" rel="stylesheet" href="app/qualitymanage/css/form_detail.css" />
	<script type="text/javascript" src="pub/bpm/js/util.js"></script>
    <script type="text/javascript">

 	$(function () {
 		var step="${param.step }";
 		
    	if(step=="audit"){
    		tbar=[{ text: '审核', id: 'submit', icon: 'submit', click: sh},
                  { text: '关闭', id: 'close', icon:'cancel', click:function(){api.data.window.Qm.refreshGrid();api.close();}}];
    	}else if(step=="sign"){
    		tbar=[{ text: '审批', id: 'submit', icon: 'submit', click: sp},
                  { text: '关闭', id: 'close', icon:'cancel', click:function(){api.data.window.Qm.refreshGrid();api.close();}}];
    	}else{
        	tbar=[{ text: '关闭', id: 'close', icon:'cancel', click:function(){api.data.window.Qm.refreshGrid();api.close();}}];             
        }
    	 if ("${param.pageStatus}"=="detail")
    	        tbar=[{ text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
    	$("#form1").initForm({
            showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
    });
 	
 	function sh() {
 		var ids = "${param.id}";
		top.$.dialog({
			width: 360,
			height: 250,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "审核",
			content: 'url:app/qualitymanage/qualityFiles_choose_opinion.jsp?pageStatus=add&step=audit&ids='+ids,
			colse:function(){
				api.data.window.Qm.refreshGrid();
				api.colse();
			}
		});
	}
 	function sp() {
 		var ids = "${param.id}";
		top.$.dialog({
			width: 360,
			height: 250,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "审批",
			content: 'url:app/qualitymanage/qualityFiles_choose_opinion.jsp?pageStatus=add&step=sign&ids='+ids,
			colse:function(){
				api.data.window.Qm.refreshGrid();
				api.colse();
			}
		});
	}
    function submitSh(){
    	
    	
    }
	function directChange(){ 
	   	var obj=$("#form1").validate().form();
		if(obj){
			 $("#form1").submit();
		}else{
			 return;
		}
	} 
	function changeAudit(val,text){
		$("#auditManName").val(text);		
	}
    </script>
</head>
<style>
.l-t-td-right1 {
    padding: 0;
    margin: 0;
}

.l-t-td-right1 .l-text {
    background-image: none;
}

.l-t-td-right1 .l-text-wrapper {
    width: 100%;
    height: 124px;
}

.l-textarea .l-text-wrapper {
    width: 100%;
    height: 100%;
}

.l-textarea-onerow {
    height: 30px;
}

.l-textarea-onerow div {
    padding: 0;
}

.l-t-td-right1 .l-text {
    border: none;
}

.l-t-td-right1 .l-text.change, .l-t-td-right1 .l-radio-group-wrapper.change
    {
    background: url("../images/x-input.png") repeat-x;
}

.l-t-td-right1 .l-text input {
    height: 124px;
    padding-top: 0;
    line-height: 24px;
}

.l-t-td-right1 .l-radio-group-wrapper {
    height: 124px;
    padding-left: 5px;
}

.l-t-td-right1 textarea {
    height: 100%;
}

.l-textarea-onerow textarea {
    height: 12px;
    padding: 6px 5px;
    width: 98%
}

.l-t-td-right1 label {
    height: 124px;
    line-height: 24px;
    display: inline-block;
}

.l-t-td-right1 div.input, .l-td-right div.input {
    border: none;
    padding-left: 5px;
}

.l-t-td-right1 .input-warp div {
    height: 100%;
    line-height: 28px;
}
</style>
<body >
<form id="form1" action="qualityFilesUpdateAction/applyEdit.do" getAction="qualityFilesUpdateAction/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id">
     <input type="hidden" id="fkQfilesOldId" name="fkQfilesOldId" value="${param.file_id }"> 
    <input type="hidden" id="updateType" name="updateType" value="1"> 
    <input type="hidden" id="handle_status" name="handle_status" value="1">   
    <h1 id="xgsq2" align="center" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">文&nbsp;件&nbsp;修&nbsp;改&nbsp;申&nbsp;请&nbsp;表 </h1></br>
    <table id="xgsq1" class="check">
			 <tr>
                    <td width="650px">&nbsp;</td>
                    <td >编号</td>
                    <td class="l-t-td-right"><input ltype='text' name="identifier" type="text"/></td>
            </tr>
	</table>
    <table id="xgsq" border="1" cellpadding="3" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">文件名称</td>
         	<td class="l-t-td-right" ><input  validate="{required:true}" ligerUi="{readonly:true}" ltype="text"  name="fileNameOld" id="fileNameOld" type="text" /></td>
         	
         	<td class="l-t-td-left">文件编号</td>
         	<td class="l-t-td-right"><input ligerUi="{readonly:true}" id="fileNumOld" name="fileNumOld" type="text" ltype="text" validate="{required:true}" /></td>
        </tr>
        <tr>
            <td class="l-t-td-left">需修改的内容</td>
            <td colspan="3" class="l-t-td-right1"><textarea rows="6" name="needsUpdate" ltype="text" validate="{required:true,maxlength:2000}"></textarea></td>
        </tr>
        <tr>
            <td class="l-t-td-left">修改理由</td>
            <td colspan="3" class="l-t-td-right1"><textarea rows="6" name="updateReasons" ltype="text" validate="{required:true,maxlength:2000}"></textarea></td>
        </tr>
          <tr>
            <td class="l-t-td-left">编制人</td>
            <td class="l-t-td-right">
            	<input name="registrantName" type="text" ltype="text" ligerUi="{disabled:true}"/>
            	<input name="registrant" type="hidden"   ligerUi="{disabled:true}"/>
            	</td>
         	<td class="l-t-td-left">日期</td>
        	<td class="l-t-td-right"><input name="registrantTime" type="text" ltype='date' ligerui="{disabled:true,format:'yyyy-MM-dd'}"/></td>
    	 </tr>
    	 <c:if test="${param.step=='sign' }">
    	  <tr>
            <td class="l-t-td-left">编制人</td>
            <td class="l-t-td-right">
            	<input name="auditManName" type="text" ltype="text" ligerUi="{disabled:true}"/>
            	<input name="auditManId" type="hidden"  ligerUi="{disabled:true}"/>
            	</td>
         	<td class="l-t-td-left">日期</td>
        	<td class="l-t-td-right"><input name="auditTime" type="text" ltype='date' ligerui="{disabled:true,format:'yyyy-MM-dd'}"  /></td>
    	 </tr>
    	 </c:if>
    </table>	
</form>
</body>
</html>
