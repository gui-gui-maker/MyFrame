<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>报告作废</title>
<%@ include file="/k/kui-base-form.jsp"%>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<script type="text/javascript" src="app/common/js/idCard.js"></script>
<script type="text/javascript" src="app/common/js/windowprint.js"></script>
<%
	String status = request.getParameter("status");
	String type = request.getParameter("type");	// 处理类型（1：责任部门负责人审核作废申请 3：质保工审核 4：责任部门负责人纠正完成情况 5：业务服务部确认 6：质量部审核）
%>
<script type="text/javascript">
	$(function() {
	    $("#formObj").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
		    		<%
		      			if(StringUtil.isEmpty(type) || "4".equals(type) || "5".equals(type)){
		      				%>
		      				top.$.dialog.notice({
			             		content: "保存成功！"
			            	});
		      				<%
		      			}else{
		      				if("1".equals(type) || "3".equals(type)){	// 1：责任部门负责人审核 3：质保工程师审核
	      						%>
	      						top.$.dialog.notice({
				             		content: "审核成功！"
				            	});
		      					<%
		      				}
		      			}
		      		%>
	            	api.data.window.refreshGrid();
	            	api.close();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (resp){
				if("detail" != "${param.status}"){
					manager = $("#error_from").ligerRadio({ disabled: true });
					if("1" == resp.data.error_from){
						manager.setValue("1");
						$("#error_from2").val("");	
					}else if("2" == resp.data.error_from){
						manager.setValue("2");
						$("#error_from2").val("");	
					}else if("3" == resp.data.error_from){
						manager.setValue("3");
						$("#error_from2").val("");	
					}else{
						manager.setValue("4");
						$("#error_from2").val(resp.data.error_from);					
					}
				}else{
					if(resp.data.error_from.length>1){
						$("#error_fromTr").attr('style','display:block');
						document.getElementById("error_from").innerHTML="外部输入";
						$("#error_from2").val(resp.data.error_from);				
					}else{
						$("#error_fromTr").attr('style','display:none');
						$("#error_from2").val("");	
					}
				}
			}, toolbar: [
	      		
	      			<%
	      				if(StringUtil.isEmpty(type)){
	      					%>
	      					{
		      					text: "保存", icon: "save", click: function(){
		      						if ($("#formObj").validate().form()) {
										if(confirm("确定保存？")){
									   		$("#formObj").submit();
										}		    				    		
									}
			      				}
			      			}
	      					<%
	      				}else{
	      					if("1".equals(type)){	// 1：责任部门负责人审核作废申请
	      						%>
	      						{
			      					text: "审核通过", icon: "check", click: function(){
								    	if(confirm("确认审核通过？提交后不可修改！")){
								    		formObj.action="report/cancel/dep_head_check.do?check=0";
								    		$("#formObj").submit();
									    }		   				    		
								    	
					      			}
				      			},{
					      			text: "审核不通过", icon: "del", click: function(){
								    	if(confirm("确认审核不通过？提交后不可修改！")){
								    		formObj.action="report/cancel/dep_head_check.do?check=1";
								    		$("#formObj").submit();
									    }		    				    									    
					      			}
				      			}
		      					<%
	      					}else if("3".equals(type)){	// 3：质保工程师审核作废申请
	      						%>
	      						{
			      					text: "审核通过", icon: "check", click: function(){
								    	if(confirm("确认审核通过？提交后不可修改！")){
								    		formObj.action="report/cancel/qua_dep_check.do?check=0";
								    		$("#formObj").submit();
									    }		   				    		
								    	
					      			}
				      			},{
					      			text: "审核不通过", icon: "del", click: function(){
								    	if(confirm("确认审核不通过？提交后不可修改！")){
								    		formObj.action="report/cancel/qua_dep_check.do?check=1";
								    		$("#formObj").submit();
									    }		    				    									    
					      			}
				      			}
		      					<%
	      					}else if("4".equals(type)){	// 4：责任人纠正
	      						%>
	      						{
			      					text: "保存", icon: "save", click: function(){
			      						var new_report_sn = $("#new_report_sn").val();
		      							if(new_report_sn.length==0){
		      								$.ligerDialog.alert("请填写新报告书编号！");
		      								return;
		      							}
		      							if ($("#formObj").validate().form()) {
											if(confirm("确认保存？提交后不可修改！")){
								    			formObj.action="report/cancel/app_deal.do";
								    			$("#formObj").submit();
									    	}		    				    		
										}
					      			}
				      			}
		      					<%
	      					}else if("5".equals(type)){	// 5：责任部门负责人确认纠正完成情况
	      						%>
	      						{
			      					text: "保存", icon: "save", click: function(){
			      						var dep_head_remark = $("#dep_head_remark").val();
		      							if(dep_head_remark.length==0){
		      								$.ligerDialog.alert("请填写完成情况！");
		      								return;
		      							}
		      							if ($("#formObj").validate().form()) {
											if(confirm("确认保存？提交后不可修改！")){
								    			formObj.action="report/cancel/app_dep_finish.do";
								    			$("#formObj").submit();
									    	}		    				    		
										}
					      			}
				      			}
		      					<%
	      					}
	      				}
	      			%>
	      			,
					{
						text: "关闭", icon: "cancel", click: function(){
							api.close();
						}
					}
			], toolbarPosition: "bottom"
		});		
		
	});
	
	function closewindow(){
		api.close();
	}
	
	function changeFlag(flag){	
		if(flag=="4"){
			$("#error_fromTr").attr('style','display:block');
		}else{
			$("#error_fromTr").attr('style','display:none');
		}	
	}
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="report/cancel/save.do?status=${param.status}"
		getAction="report/cancel/detail.do?id=${param.id}">
		<input id="id" name="id" type="hidden" />
		<input id="sn" name="sn" type="hidden" />
		<input id="apply_user_id" name="apply_user_id" type="hidden" />
		<input id="apply_user_name" name="apply_user_name" type="hidden" />
		<input id="apply_dep_id" name="apply_dep_id" type="hidden" />
		<input id="apply_dep_name" name="apply_dep_name" type="hidden" />
		<input id="apply_date" name="apply_date" type="hidden" />
		<!-- 
		<input id="apply_dep_head_id" name="apply_dep_head_id" type="hidden" />
		<input id="apply_dep_head_name" name="apply_dep_head_name" type="hidden" />
		<input id="apply_head_check_date" name="apply_head_check_date" type="hidden" />
		 -->
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>报告纠正基本情况</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>									
					<td class="l-t-td-left">申请纠正原因：</td>
					<td class="l-t-td-right">
						<input type="radio" name="apply_reason"  id="apply_reason" ltype="radioGroup"
							ligerui="{value:'1',data: [ { text:'不符合', id:'1' }, { text:'潜在不符合', id:'2' } ] }"/>
					</td>								
				</tr>
				<tr>
					<td class="l-t-td-left">不符合来源：</td>
					<td class="l-t-td-right" colspan="3">
						<input type="radio" name="error_from"  id="error_from" ltype="radioGroup"
							ligerui="{onChange:changeFlag,value:'1',data: [ { text:'质量抽查', id:'1' }, { text:'部门自查', id:'2' }, { text:'内部审核', id:'3' }, { text:'外部输入', id:'4' } ] }"/>					
					</td>
				</tr>
				<tr id="error_fromTr" style="display:none;">
					<td class="l-t-td-left">&nbsp;</td>
					<td class="l-t-td-right" colspan="3">
						<input name="error_from" id="error_from2" type="text" ltype='text' validate="{maxlength:20}" value="CTJC-019-B08-"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">不符合类型：</td>
					<td class="l-t-td-right" colspan="3">
						<input type="radio" name="error_type"  id="error_type" ltype="radioGroup"
							ligerui="{value:'3',data: [{ text:'一般不符合', id:'3' }, { text:'严重不符合', id:'4' } ] }"/>	<!-- { text:'体系性不符合', id:'1' }, { text:'效果性不符合', id:'2' },  -->
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">事实陈述：</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="error_desc" id="error_desc" rows="3" cols="25" class="l-textarea" validate="{required:true,maxlength:1000}"></textarea>
					</td>		
				</tr>
				<tr>
					<td class="l-t-td-left">报告书编号：</td>
					<td class="l-t-td-right">
						<input name="report_sn" id="report_sn" type="text" ltype='text' validate="{required:true,maxlength:20}"/>
					</td>	
				</tr>
				<tr>								
					<td class="l-t-td-left">报告状态：</td>
					<td class="l-t-td-right" colspan="3">
						<input type="radio" name="report_status"  id="report_status" ltype="radioGroup"
							ligerui="{value:'1',data: [ { text:'报告封存未存档', id:'1' }, { text:'报告已封存', id:'2' } ] }"/>	<!-- , { text:'报告已发出', id:'3' } -->
					</td>								
				</tr>
				<tr>
					<td class="l-t-td-left">原因及对检验质量<br/>影响分析：</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="error_reason" id="error_reason" rows="3" cols="25" class="l-textarea" validate="{maxlength:1000}"></textarea>
					</td>		
				</tr>
				<tr>
					<td class="l-t-td-left">纠正措施计划：</td>
					<td class="l-t-td-right" colspan="3">
						<input type="checkbox" name="deal_plan"  id="deal_plan" ltype="checkboxGroup"
							ligerui="{data: [ { text:'原报告号作废', id:'1' }, { text:'重新出报告', id:'2' }, { text:'原报告盖作废章，原始记录存入新报告', id:'3' }, 
							{ text:'发出的报告追回并销毁', id:'4' } ] }"/>		
						<!-- 
						<input type="radio" name="deal_plan"  id="deal_plan" ltype="radioGroup"
							ligerui="{value:'1',data: [ { text:'原报告号作废', id:'1' }, { text:'重新出报告', id:'2' }, { text:'原报告盖作废章，原始记录存入新报告', id:'3' }, 
							{ text:'发出的报告追回并销毁', id:'4' } ] }"/>
						 -->
					</td>		
				</tr>
				<tr>
					<td class="l-t-td-left">预防措施计划：</td>
					<td class="l-t-td-right" colspan="3">
						<input type="radio" name="pre_plan"  id="pre_plan" ltype="radioGroup"
							ligerui="{value:'1',data: [ { text:'组织相关人员学习，杜绝类似情况', id:'1' }] }"/>
					</td>		
				</tr>
				<tr>
					<td class="l-t-td-left">计划完成时间：</td>
					<td class="l-t-td-right">
						<input name="plan_end_date" id="plan_end_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" validate="{required:true}" />
					</td>	
					<td class="l-t-td-left"></td>
					<td class="l-t-td-right"></td>
				</tr>
				<c:if test="${'detail' eq param.status}">
				<tr>
					<td class="l-t-td-left">责任人：</td>
					<td class="l-t-td-right">
						<input name="apply_user_name" id="apply_user_name" type="text" ltype='text'/>
					</td>
					<td class="l-t-td-left">申请纠正日期：</td>
					<td class="l-t-td-right">
						<input name="apply_date" id="apply_date" type="text" ltype='date'/>
					</td>
				</tr>
				</c:if>
				<%
					if("4".equals(type) && "modify".equals(status)){
						%>
						<tr>
							<td class="l-t-td-left">新报告书编号：</td>
							<td class="l-t-td-right" >
								<input name="new_report_sn" id="new_report_sn" type="text" ltype='text' validate="{required:true,maxlength:20}"/>
							</td>	
						</tr>
						<tr>
							<td class="l-t-td-left">备注：</td>
							<td class="l-t-td-right" >
								<input name="deal_remark" id="deal_remark" type="text" ltype='text' validate="{maxlength:1000}"/>
							</td>	
						</tr>
						<%
					}
					if("5".equals(type) && "modify".equals(status)){
						%>
						<tr>
							<td class="l-t-td-left">纠正和预防措施<br/>完成情况：</td>
							<td class="l-t-td-right" colspan="3">
								<textarea name="dep_head_remark" id="dep_head_remark" rows="3" cols="25" class="l-textarea" validate="{maxlength:1000}"></textarea>
							</td>	
						</tr>
						<%
					}
				%>
			</table>
		</fieldset>
		<c:if test="${'detail' eq param.status}">
					<fieldset class="l-fieldset">
						<legend class="l-legend">
							<div>报告纠正审核情况：</div>
						</legend>
						<table cellpadding="3" cellspacing="0" class="l-detail-table">
							<tr>
								<td class="l-t-td-left">责任部门负责人：</td>
								<td class="l-t-td-right">
									<input name="apply_dep_head_name" id="apply_dep_head_name" type="text" ltype='text'/>
								</td>
								<td class="l-t-td-left">审核结果：</td>
								<td class="l-t-td-right">
									<input type="radio" name="apply_head_check_result"  id="apply_head_check_result" ltype="radioGroup"
										ligerui="{value:'0',data: [ { text:'审核通过', id:'0' }, { text:'审核未通过', id:'1' } ] }"/>
								</td>
							</tr>
							<tr>
								<td class="l-t-td-left">审核日期：</td>
								<td class="l-t-td-right">
									<input name="apply_head_check_date" id="apply_head_check_date" type="text" ltype='date'/>
								</td>
							</tr>
							<tr>
								<td class="l-t-td-left">质保工程师：</td>
								<td class="l-t-td-right">
									<input name="qua_dep_check_name" id="qua_dep_check_name" type="text" ltype='text'/>
								</td>
								<td class="l-t-td-left">审核结果：</td>
								<td class="l-t-td-right">
									<input type="radio" name="qua_dep_check_result"  id="qua_dep_check_result" ltype="radioGroup"
										ligerui="{value:'0',data: [ { text:'审核通过', id:'0' }, { text:'审核未通过', id:'1' } ] }"/>
								</td>
							</tr>
							<tr>
								<td class="l-t-td-left">审核日期：</td>
								<td class="l-t-td-right">
									<input name="qua_dep_check_date" id="qua_dep_check_date" type="text" ltype='date'/>
								</td>
							</tr>
						</table>
					</fieldset>
					<fieldset class="l-fieldset">
						<legend class="l-legend">
							<div>责任人纠正</div>
						</legend>
						<table cellpadding="3" cellspacing="0" class="l-detail-table">
							<tr>
								<td class="l-t-td-left">新报告书编号：</td>
								<td class="l-t-td-right">
									<input name="new_report_sn" id="new_report_sn" type="text" ltype='text' validate="{required:true,maxlength:20}"/>
								</td>	
							</tr>
							<tr>
								<td class="l-t-td-left">备注：</td>
								<td class="l-t-td-right">
									<input name="deal_remark" id="deal_remark" type="text" ltype='text' validate="{maxlength:1000}"/>
								</td>	
							</tr>
							<tr>
								<td class="l-t-td-left">检验员：</td>
								<td class="l-t-td-right">
									<input name="deal_user_name" id="deal_user_name" type="text" ltype='text'/>
								</td>
								<td class="l-t-td-left">纠正日期：</td>
								<td class="l-t-td-right">
									<input name="deal_date" id="deal_date" type="text" ltype='date'/>
								</td>
							</tr>							
						</table>
					</fieldset>
					<fieldset class="l-fieldset">
						<legend class="l-legend">
							<div>业务服务部确认</div>
						</legend>
						<table cellpadding="3" cellspacing="0" class="l-detail-table">
							<tr>
								<td class="l-t-td-left">确认纠正措施：</td>
								<td class="l-t-td-right">
									<input type="radio" name="confirm_result"  id="confirm_result" ltype="radioGroup"
										ligerui="{value:'1',data: [ { text:'原报告号作废', id:'1' }, { text:'原报告盖作废章', id:'2' }, { text:'追回报告销毁', id:'3' } ] }"/>
								</td>	
							</tr>
							<tr>
								<td class="l-t-td-left">经办人：</td>
								<td class="l-t-td-right">
									<input name="confirm_user_name" id="confirm_user_name" type="text" ltype='text'/>
								</td>
								<td class="l-t-td-left">确认日期：</td>
								<td class="l-t-td-right">
									<input name="confirm_date" id="confirm_date" type="text" ltype='date'/>
								</td>
							</tr>
						</table>
					</fieldset>
					<fieldset class="l-fieldset">
						<legend class="l-legend">
							<div>纠正和预防措施完成情况</div>
						</legend>
						<table cellpadding="3" cellspacing="0" class="l-detail-table">
							<tr>
								<td class="l-t-td-left">完成情况：</td>
								<td class="l-t-td-right">
									<input name="dep_head_remark" id="dep_head_remark" type="text" ltype='text' validate="{maxlength:1000}"/>
								</td>	
							</tr>
							<tr>
								<td class="l-t-td-left">责任部门负责人：</td>
								<td class="l-t-td-right">
									<input name="dep_head_name" id="dep_head_name" type="text" ltype='text'/>
								</td>
								<td class="l-t-td-left">纠正措施完成日期：</td>
								<td class="l-t-td-right">
									<input name="dep_head_check_date" id="dep_head_check_date" type="text" ltype='date'/>
								</td>
							</tr>
						</table>
					</fieldset>
					<fieldset class="l-fieldset">
						<legend class="l-legend">
							<div>纠正和预防措施跟踪及有效性验证/纠正结果</div>
						</legend>
						<table cellpadding="3" cellspacing="0" class="l-detail-table">
							<tr>
								<td class="l-t-td-left">纠正结果：</td>
								<td class="l-t-td-right" colspan="3">
									<textarea name="qua_end_result" id="qua_end_result" rows="3" cols="25" class="l-textarea" ></textarea>				
								</td>	
							</tr>
							<tr>
								<td class="l-t-td-left">质量监督管理部<br/>负责人：</td>
								<td class="l-t-td-right">
									<input name="qua_head_name" id="qua_head_name" type="text" ltype='text'/>
								</td>
								<td class="l-t-td-left">处理日期：</td>
								<td class="l-t-td-right">
									<input name="qua_head_check_date" id="qua_head_check_date" type="text" ltype='date'/>
								</td>
							</tr>
						</table>
					</fieldset>
		</c:if>
	</form>
</body>
</html>
