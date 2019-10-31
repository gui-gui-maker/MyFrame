<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="com.khnt.utils.DateUtil"%>
<%@page import="com.khnt.utils.StringUtil"%>

<%
	String type = request.getParameter("type");	// 操作类别（04：报告审核 05：报告签发）
	String device_type = request.getParameter("device_type");	// 设备类别（用来判断是否默认审核日期为编制日期加3天、签发日期为审核日期加1天）
	String enter_time = request.getParameter("enter_time");	// 编制日期
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<title></title>
	<!-- 每个页面引入，页面编码、引入js，页面标题 -->
	<%@include file="/k/kui-base-form.jsp"%>
	<head>
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
				
			<%
				if("04".equals(type)){	// 报告审核，默认审核日期
					String check_date = "";
					if("3".equals(device_type)){
						Calendar calendar = Calendar.getInstance();
						if(StringUtil.isNotEmpty(enter_time)){
							calendar.setTime(DateUtil.convertStringToDate("yyyy-MM-dd",enter_time));	// 编制日期
						}else{
							calendar.setTime(new Date());	// 当前日期
						}
						calendar.add(Calendar.DATE, 3);	//（2014-10-08要求，审核日期=编制日期+3天）
						check_date = DateUtil.getDate(calendar.getTime());
					}else{
						check_date = DateUtil.getDateTime("yyyy-MM-dd", new Date());	// 起重机类机电设备审核日期默认当前日期
					}
					%>
					$('#op_time').val('<%=check_date%>');
					<%
				}
			%>
		})
		
		function close(){	
			api.close();
			api.data.window.showBB();
		}
			
		function save(){
			var  Type= ${param.type};
			var is_pass =$('#revise_conclusion').ligerGetRadioGroupManager().getValue();
			var op_time = $('#op_time').val();
			op_time = op_time.replace(/-/g,"/");
			var op_date = new Date(op_time);
					
			if(is_pass=="1"){
				if(${param.type}=="04"){
					var enter_date = new Date('<%=enter_time%>'.replace(/-/g,"/"));
					if(op_date<enter_date){
						alert("亲，审核日期不能早于编制日期哦，请重新选择审核日期！");
						return;
					}
				}
			}
			if(is_pass=="2"){
				if($('#revise_remark').val()==null||$('#revise_remark').val()==undefined||$('#revise_remark').val()==""){
					top.$.dialog.notice({content:'请填写退回原因！'});
					return;
				}
			}
			
			url = "report/batch/batchCheckPage.do?type=${param.type}&isBatch=${param.isBatch}&flow_num=${param.flow_num}&acc_id=${param.acc_id}&infoId=${param.ids}";					
			//验证表单数据
			if($('#form1').validate().form()){
				var formData = $("#form1").getValues();
				var data = {};
				data = formData;
			 	$("body").mask("正在保存数据，请稍后！");
			 	$.ajax({
			   		url: url,
			       	type: "POST",
			    	datatype: "json",
			   		data: {dataStr : $.ligerui.toJSON(data)},
			  		success: function (resp, stats) {	
			         	$("body").unmask();
			      		if (resp["success"]) {
			            	if(resp["data"] == "1"){
			               		if(api.data.window.Qm){
				                	api.data.window.Qm.refreshGrid();
				                }
				                top.$.dialog.notice({content:'保存成功'});
				           		api.data.window.api.data.window.Qm.refreshGrid();
				             	api.data.window.api.close();
				            	api.close();			                	
			                }else{
			                	top.$.dialog.notice({content:'保存成功'});
				                api.data.window.left._opid${param.opid}.innerHTML="<img src='k/kui/images/icons/16/check.png' border='0' >";
				                close();
			              	}
			         	}else{
			                $.ligerDialog.error('提示：' + resp.msg);
			        	}
			  		},
			      	error: function (resp,stats) {
			        	$("body").unmask();
			       		$.ligerDialog.error('提示：' + resp.msg);
			    	}
			 	});
			}
		}
	</script>
	</head>
	<body>		
	<form id="form1"  >
		<table border="1" cellpadding="0" cellspacing="0" width="" class="l-detail-table">
			<tr>
				<td class="l-t-td-left">结论：</td>
				<td class="l-t-td-right" >
					<input type="radio" name="revise_conclusion"  id="revise_conclusion" ltype="radioGroup"
						validate="{required:false}"
							ligerui="{value:'1',data: [ { text:'通过', id:'1' }, { text:'不通过', id:'2' } ] }"/>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">检验日期：</td>
				<td class="l-t-td-right" ><c:out value="${param.advance_time}"></c:out> </td>
			</tr>
			<tr>
				<td class="l-t-td-left">编制日期：</td>
				<td class="l-t-td-right" ><c:out value="${param.enter_time}"></c:out> </td>
			</tr>
			<tr>
				<c:choose>
					<c:when test='${param.type=="04"}'>
						<td class="l-t-td-left">审核日期：</td>
						<input type="hidden" name="title_and_data_conclusion" value="报告审核"/>
					</c:when>
					<c:when test='${param.type=="05"}'>
						<td class="l-t-td-left">签发日期：</td>
						<input type="hidden" name="title_and_data_conclusion" value="报告签发"/>
					</c:when>
				</c:choose>
				<td class="l-t-td-right" >
					<input name="op_time"
						type="text" ltype="date" validate="{required:false}"
							ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="op_time" />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">备注：</td>
				<td class="l-t-td-right"  colspan="2"><textarea name="revise_remark" id="revise_remark" rows="3" cols="" ext_type="string" ext_maxLength="200" ext_name="备注" isNull="Y" class="area_text" onfocus="this.innerHTML='';">请在此处填写报告退回原因！</textarea></td>
			</tr>
		</table>
	</form>
	</body>
</html>
