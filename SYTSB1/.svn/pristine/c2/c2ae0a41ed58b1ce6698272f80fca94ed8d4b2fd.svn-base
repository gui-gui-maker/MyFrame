<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>报告送审</title>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<%@ include file="/k/kui-base-form.jsp"%>
<%
	String check_flow = request.getParameter("check_flow");	// 报告审批流程（0：二级审核 1：一级审核）
%>
<script type="text/javascript">
	$(function() {
		$("#form1").initForm({ //参数设置示例
			toolbar : [ {
				text : '保存',
				//id : 'save',
				icon : 'save',
				click : save
			}, {
				text : '关闭',
				//id : 'close',
				icon : 'cancel',
				click : close
			} ],
			getSuccess : function(res) {
			
			}
		});
	})
	
	function close(){	
		api.close();
	}
	
	function save(){	
		var sub_op_id = "";
		var sub_op_name = "";
		if("0"=="<%=check_flow %>"){
			sub_op_id =$('#next_sub_op').ligerGetComboBoxManager().getValue();
			sub_op_name =$('#next_op_name').val();				
			if(sub_op_id==''||sub_op_id==null){
				top.$.notice("请选择审核人");
				return;
			}
		}
		url = "inspection/zzjd/subCheck.do?infoId=${param.infoId}&flow_num=${param.flow_num}&acc_id=${param.acc_id}&check_flow=${param.check_flow}&sub_op_id="+sub_op_id+"&sub_op_name="+encodeURIComponent(sub_op_name);
		//验证表单数据
		if($('#form1').validate().form()){
			$("body").mask("系统正在玩命提交数据中，请耐心等待！");
			$.ajax({
				url: url,
			  	type: "POST",
			 	datatype: "json",
				success: function (data, stats) {
			    	$("body").unmask();
			    	if (data["success"]) {
			    		if("0"=="<%=check_flow %>"){
			    			if(api.data.window.Qm){
				                api.data.window.refreshGrid();
				            }
				            top.$.dialog.notice({content:'提交成功'});
				            api.data.window.refreshGrid();
				            api.close();
			    		}else{
									if(api.data.window.Qm){
						                api.data.window.refreshGrid();
						            }
									api.data.window.refreshGrid();
									api.close();
									top.$.dialog({
						    			width : 800,
						    			height :600,
						    			lock : true,
						    			title : "签发分配结果",
						    			content : 'url:app/inspection/report_auto_issue_list.jsp?info_ids=${param.infoId}',
						    			data : {
						    				"window" : window
						    			}
						    		});
			    		}
					}else{
			        	$.ligerDialog.error('提示：' + responseText.data);
			      	}
				},
			    error: function (data,stats) {
			    	$("body").unmask();
			     	$.ligerDialog.error('提示：' + data.msg);
			  	}
			});
		}
	}
	
	function setValue(valuex,name){
		if(valuex==""){
			return;
		}
		if(name=='next_op_name'){
			$('#next_op_name').val(valuex)
		}
	}		
</script>
</head>
<body>
	<form id="form1" >		
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>报告送审</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>	
					<c:choose>
						<c:when test="${param.check_flow eq '0'}">
							<td class="l-t-td-left">审核人：</td>
							<td class="l-t-td-right"  colspan="2">
								<input  name ="next_op_name" id="next_op_name" type="hidden"/>	
								<input type="text"  name="next_sub_op" id="next_sub_op"  ltype="select"  validate="{required:false}" onchange="setValue(this.value,'next_op_name')"
									ligerui="{tree:{checkbox:false,data:<u:dict sql="select t.id,t.pid,t.code, t.text from (select o.id as id, o.id as code, o.ORG_NAME as text, o.PARENT_ID as pid from sys_org o union select t1.id as id,t1.id as code, t1.NAME as text, t1.ORG_ID as pid from employee e,sys_user t1 where e.id=t1.employee_id and t1.status='1' and t1.id in (select s.user_id from sys_user_role s ,Sys_Role r where r.id=s.role_id and r.name='报告审核')) t start with t.id in ('100020','100021','100022','100023','100024','100026','100034','100035','100033','100036','100037','100062','100066','100063','100065','100067','100045') connect by t.pid = prior t.id"/>}}"/>
							</td>
						</c:when>
						<c:otherwise>
							<td class="l-t-td-left" >下一步操作人：</td>
							<td class="l-t-td-right" colspan="2">&nbsp;&nbsp;由系统自动分配，提交后可进入“签发分配记录”页面中查看分配结果。&nbsp;&nbsp;</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
