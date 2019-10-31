<%@page import="java.util.Date"%>
<%@page import="com.khnt.utils.DateUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
String infoId=request.getParameter("infoId");
String flow_num=request.getParameter("flow_num");
String acc_id=request.getParameter("acc_id");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="add">
<title>报告录入退回</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript">
	var filenumber;
	$(function() {
		$("#formObj").initForm({
			toolbar:[
	                	{text:'确定', id:'save',icon:'accept', click:submit },
	                	{text:'取消', id:'close',icon:'cancel', click:function(){api.close();} }
	                ],
			success : function(res) {
				if (res.success) {
					top.$.dialog.notice({
						content : "设置成功！",
						parent : api,
						lock : false
					});
					api.close();
				}
			}
		});
	});
	
	function submit(){
			/*var nextHandle=$('input[name="nextHandle"]').val();
		   nextHandle=nextHandle.replace(/flag==/g, "");
		   */
		   var nextHandle=$("#nextHandle-txt").ligerComboBox().getValue();
			if(nextHandle==null||nextHandle==""){
				$.dialog.alert("请选择处理环节！");
			}else{
				//api.data.pwindow.submit(nextHandle);
				//winClose("chooseWindow");
						$.getJSON('department/basic/reportBackINfo.do',{nextHandle:nextHandle,infoId:'<%=infoId%>',flow_num:'<%=flow_num%>',acc_id:'<%=acc_id%>'},function(data){
							if(data){
								top.$.notice("退回成功！");
								api.data.pwindow.Qm.refreshGrid();
								winClose("chooseWindow");
							}
						
						});
			}
	}
</script>
<body style="padding: 0; margin: 0">
<div class="scroll-tm">
	<form id="formObj" name="formObj" method="post"
		style="padding: 0; margin-top: 2em; width: 100%">
		<div style="margin-top:30px;text-align:center;">
		<input type="radio" name="nextHandle" id="nextHandle-txt" ltype="radioGroup" validate="{required:true}" value="退回到任务分配" ligerui="{value:'1',data:[{text:'退回到任务分配',id:'1'},{text:'退回到科室报检',id:'2'}] }"/>
		</div>
	</form>
</div>
<div class="toolbar-tm">
	<div class="toolbar-tm-bottom">
		<div id="toolbar1" height="30"></div>
	</div>
</div>
</body>
</html>