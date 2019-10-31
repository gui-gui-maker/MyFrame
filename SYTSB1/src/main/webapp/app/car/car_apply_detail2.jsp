<%@page import="java.util.Date"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.khnt.rbac.impl.bean.Org"%>
<%@page import="com.khnt.rbac.impl.bean.Employee"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>用车申请</title>
<%@ include file="/k/kui-base-form.jsp"%>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<style>
	<%
		String status = request.getParameter("status");
		if("detail".equals(status)){
			%>
    		html,body{font-size: 13px;}
			<%
		}
	%>
	#wrap {
		display: flex;
		font-size: 13px;
	}
	.l-t-td-left{ width:10%;}
</style>
<%
	CurrentSessionUser cur_user = SecurityUtil.getSecurityUser();
	User user = (User)cur_user.getSysUser();
	Employee emp = (Employee)user.getEmployee();
	
	String orgId = cur_user.getDepartment().getId();
	String orgName = cur_user.getDepartment().getOrgName();
	String empId = emp.getId();
	String uId = user.getId();

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = dateFormat.format(new Date());
%>
<script type="text/javascript">
	var pageStatus = "${param.status}"; 

	$(function() {
	    $("#formObj").initForm({
			success: function (response) {//处理成功
				$("body").unmask();
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});    
	            	api.data.window.refreshGrid();
		            api.close();
	    		} else {
	           		$.ligerDialog.alert('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (resp){
				$("#formObj").setValues(resp.data[0]);

				$("#applySn").html(resp.data[0].apply_sn);
				$("#applyDate").html(resp.data[0].apply_date.substring(0,10));
				
				if("detail" == "${param.status}"){
					$("#use_user_name").html(resp.data[0].use_user_name+"/"+resp.data[0].use_user_phone);
				}else if("modify" == "${param.status}"){
					$("#use_user_name").val(resp.data[0].use_user_name+"/"+resp.data[0].use_user_phone);
				}else{
					$("#use_user_name").val("<%=user.getName() %>/<%=emp.getMobileTel() %>");
				}
			}, toolbar: [
				{ text:'保存', id:'save',icon:'save', click:saveInfo},
				{
					text: "关闭", id:'close', icon: "cancel", click: function(){
						api.close();
					}
				}
			], toolbarPosition: "bottom"
		});			    
		
	});
	function selectUnit(){
		top.$.dialog({
			width : 290,
			height : 420,
			lock : true,
			title : "单位/部门选择",
			content : 'url:app/car/choose_unit_list.jsp',
			data : {
				"window" : window
			}
		});
	}
	function callUnit(id,text){
		$("#use_dep_name").val(text);
		$("#use_dep_id").val(id);
	}
	function selectUser(){
		var use_dep_id = $("#use_dep_id").val();
		var use_dep_name = $("#use_dep_name").val();
		if(use_dep_id == "" || use_dep_name == "" ){
			$.ligerDialog.alert("请先选择用车部门！");
			return;
		}
		top.$.dialog({
			width : 200,
			height : 420,
			lock : true,
			title : "选择联系人",
			content : 'url:app/car/choose_user_list.jsp?org_id='+use_dep_id,
			data : {
				"window" : window
			}
		});
	}
	
	function callUser(id, name){ 
		$('#use_user_id').val(id);
		$('#use_user_name').val(name);
		$.post("car/apply/queryMobileTel.do", {
			user_id : id
		}, function(resp) {
  			if(resp.success){
  				$('#use_user_name').val(name+"/"+resp.data);
  			}
		}, "json");
	}	
	
	
	//比较日前大小  
	function compareDate(checkStartDate, checkEndDate) {
		var arys1 = new Array();
		var arys2 = new Array();
		if (checkStartDate != null && checkEndDate != null) {
			arys1 = checkStartDate.split('-');
			var sdate = new Date(arys1[0], parseInt(arys1[1] - 1), arys1[2]);
			arys2 = checkEndDate.split('-');
			var edate = new Date(arys2[0], parseInt(arys2[1] - 1), arys2[2]);
			if (sdate > edate) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	function saveInfo(){
		//验证表单数据
		if ($("#formObj").validate().form()) {
			var start_date = $("#use_start_date").val();
    		var end_date = $("#use_end_date").val();
    		if(compareDate(start_date, end_date)){
    			if(compareDate('<%=nowTime%>',start_date)){
    				$.ligerDialog.confirm('亲，确定保存吗？保存后会自动提交至部门负责人哦！', function (yes){
    					if(yes){
    						$("body").mask("正在保存数据，请稍后！");
    						$("#save").attr("disabled","disabled");
    						//表单提交
			    			$("#formObj").submit();
    					}
    				});
    			}else{
    				$.ligerDialog.alert("亲，“申请用车起”不能早于今天！");   
	    			return;
    			}
    		}else{
    			$.ligerDialog.alert("亲，“用车止”不能早于“申请用车起”！");   
    			return;
    		}       
		}
	}
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="car/apply/saveBasic.do?status=${param.status}"
		getAction="car/apply/getDetail.do?id=${param.id}">
		<input type="hidden" name="id" id="basic_id" value="${param.id}"/>
		<input type="hidden" name="apply_sn" id="apply_sn" />
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>基本信息</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">编号：</td>
					<td class="l-t-td-right">
						<c:choose>
							<c:when test="${param.status eq 'add'}">
								<c:out value="保存后由系统自动生成"></c:out>
							</c:when>
							<c:otherwise>
								<div id="applySn"></div>
							</c:otherwise>
						</c:choose>
					</td>
					<td class="l-t-td-left">填报日期：</td>
					<td class="l-t-td-right">
						<c:choose>
							<c:when test="${param.status eq 'add'}">
								<c:out value="<%=nowTime%>"></c:out>
							</c:when>
							<c:otherwise>
								<div id="applyDate"></div>
							</c:otherwise>
						</c:choose>
					</td>
					<td class="l-t-td-left">用车部门：</td>
					<%-- <td class="l-t-td-right">
						<input name="use_dep_name" type="text" readonly="readonly" id="use_dep_name" title="点击此处选择用车部门"
							ltype='text' validate="{required:true}" ligerui="{value:'<%=orgName %>',iconItems:[{icon:'add',click:function(){selectUnitOrUser(0,0,'use_dep_id','use_dep_name')}}]}"onclick="selectUnitOrUser(0,0,'use_dep_id','use_dep_name')" />
						<input type="hidden" name="use_dep_id" id="use_dep_id" value="<%=orgId %>"/>
					</td> --%>
					<td class="l-t-td-right">
						<input name="use_dep_name" type="text" readonly="readonly" id="use_dep_name" title="点击此处选择用车部门"
							ltype='text' validate="{required:true}" ligerui="{value:'<%=orgName %>',iconItems:[{icon:'add',click:function(){selectUnit()}}]}"onclick="selectUnit()" />
						<input type="hidden" name="use_dep_id" id="use_dep_id" value="<%=orgId %>"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">申请用车起：</td>
					<td class="l-t-td-right" ><input
						id="use_start_date" name="use_start_date" type="text" ltype="date"
						validate="{required:true}"
						ligerui="{initValue:'',format:'yyyy-MM-dd hh:mm'}" />
					</td>
					<td class="l-t-td-left">用车止：</td>
					<td class="l-t-td-right">
						<input id="use_end_date" name="use_end_date" type="text" ltype="date"
						validate="{required:true}" ligerui="{initValue:'',format:'yyyy-MM-dd hh:mm'}" /><!-- width: '160' -->
					</td>
					<td class="l-t-td-left">联系人：</td>
					<td class="l-t-td-right">
						<input type="hidden" name="use_user_id" id="use_user_id" value="<%=user.getId() %>"/>
						<input type="text" name="use_user_name" id="use_user_name"
						ltype="text" validate="{required:true}"
						ligerui="{value:'<%=user.getName() %>/<%=emp.getMobileTel() %>',iconItems:[{icon:'add',click:function(){selectUser()}}]}" onclick="selectUser()"
						/> 
					</td>
				</tr>
				<tr>
				
					<td class="l-t-td-left">用车任务：</td>
					<td class="l-t-td-right"><input type="text" name="apply_reason"
						id="apply_reason" ltype="text" validate="{required:true}" />
					</td>
					<c:choose>
						<c:when test="${param.status eq 'detail'}">
							<td class="l-t-td-left">用车天数：</td>
							<td class="l-t-td-right">
								<input type="text" name="use_days" id="use_days" ltype="text" />
							</td>
							<td class="l-t-td-left">行驶路线：</td>
							<td class="l-t-td-right">
								<input type="text" name="drive_route" id="drive_route" ltype="text" validate="{required:true,maxlength:2000}" />
							</td>
						</c:when>
						<c:otherwise>
							<td class="l-t-td-left">行驶路线：</td>
							<td class="l-t-td-right" colspan="3">
								<input type="text" name="drive_route"
								id="drive_route" ltype="text" validate="{required:true,maxlength:2000}" />
							</td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td class="l-t-td-left">乘车人数：</td>
					<td class="l-t-td-right" >
						<c:choose>
						<c:when test="${param.status eq 'detail'}">
							<input type="text" name="passengers_count" id="passengers_count" ltype="text" />
						</c:when>
						<c:otherwise>
							<input name="passengers_count" id="passengers_count" type="text" ltype="spinner" validate="{required:true,digits:true,maxlength:32}" value="1"/>
						</c:otherwise>
					</c:choose>
						
					</td>
					<td class="l-t-td-left">是否需要司机：</td>
					<td class="l-t-td-right">
						<input type="radio" name="need_driver"  id="need_driver" ltype="radioGroup" validate="{required:true}"
						ligerui="{value:'0',data: [ { text:'是', id:'1' }, { text:'否', id:'0' } ] }"/> 
					</td>
					<td class="l-t-td-left">&nbsp;&nbsp;</td>
					<td class="l-t-td-right">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right" colspan="5">
						<textarea name="apply_remark" id="apply_remark" rows="3" cols="25" class="l-textarea" validate="{required:false,maxlength:2000}"></textarea>
					</td>
				</tr>
			</table>
		</fieldset>
		<c:if test="${param.status eq 'detail'}">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>用车部门负责人审核</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">审核结果：</td>
					<td class="l-t-td-right"><input type="radio"
						name="dep_deal_result" id="dep_deal_result" ltype="radioGroup"
						validate="{required:false}"
						ligerui="{value:'1',data: [ { text:'同意', id:'1' }, { text:'不同意', id:'2' } ] }" />
					</td>
					<td class="l-t-td-left">审核人：</td>
					<td class="l-t-td-right" width="100">
						<input type="text" id="dep_deal_name" name="dep_deal_name" ltype="text" />
					</td>
					<td class="l-t-td-left">审核时间：</td>
					<td class="l-t-td-right">
						<input name="dep_deal_date" id="dep_deal_date" type="text" ltype="date" ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
					</td>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right" width="215">
						<input type="text" id="dep_deal_remark" name="dep_deal_remark" ltype="text" />
					</td>
				</tr>	
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>分管院领导审核</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">审核结果：</td>
					<td class="l-t-td-right"><input type="radio"
						name="leader_deal_result" id="leader_deal_result" ltype="radioGroup"
						validate="{required:false}"
						ligerui="{value:'1',data: [ { text:'同意', id:'1' }, { text:'不同意', id:'2' } ] }" />
					</td>
					<td class="l-t-td-left">审核人：</td>
					<td class="l-t-td-right" width="100">
						<input type="text" id="leader_deal_name" name="leader_deal_name" ltype="text" />
					</td>
					<td class="l-t-td-left">审核时间：</td>
					<td class="l-t-td-right">
						<input name="leader_deal_date" id="leader_deal_date" type="text" ltype="date" ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
					</td>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right" width="215">
						<input type="text" id="leader_deal_remark" name="leader_deal_remark" ltype="text" />
					</td>
				</tr>	
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>车队负责人审核</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">审核结果：</td>
					<td class="l-t-td-right"><input type="radio"
						name="fleet_deal_result" id="fleet_deal_result" ltype="radioGroup"
						validate="{required:false}"
						ligerui="{value:'1',data: [ { text:'同意', id:'1' }, { text:'不同意', id:'2' } ] }" />
					</td>
					<td class="l-t-td-left" >审核人：</td>
					<td class="l-t-td-right" width="100">
						<input type="text" id="fleet_deal_name" name="fleet_deal_name" ltype="text" />
					</td>
					<td class="l-t-td-left">审核时间：</td>
					<td class="l-t-td-right">
						<input name="fleet_deal_date" id="fleet_deal_date" type="text" ltype="date" ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
					</td>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right" width="215">
						<input type="text" id="fleet_deal_remark" name="fleet_deal_remark" ltype="text" />
					</td>
				</tr>	
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>车队派车</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					
					<td class="l-t-td-left">车牌号：</td>
					<td class="l-t-td-right">
						<input type="text" id="plate_number" name="plate_number" ltype="text" />
					</td>
					<td class="l-t-td-left">驾驶员：</td>
					<td class="l-t-td-right">
						<input type="text" id="driver_user_name" name="driver_user_name" ltype="text" />
					</td>
					<td class="l-t-td-left">公里起数：</td>
					<td class="l-t-td-right">
						<input type="text" id="start_km" name="start_km" ltype="text" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">出车时间：</td>
					<td class="l-t-td-right">
						<input name="out_date" id="out_date" type="text" ltype="date" ligerui="{initValue:'',format:'yyyy-MM-dd hh:mm'}" />
					</td>
					<td class="l-t-td-left">派车人：</td>
					<td class="l-t-td-right">
						<input type="text" id="send_deal_name" name="send_deal_name" ltype="text" />
					</td>
				</tr>	
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>办公室负责人审核</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">审核结果：</td>
					<td class="l-t-td-right"><input type="radio"
						name="office_deal_result" id="office_deal_result" ltype="radioGroup"
						validate="{required:false}"
						ligerui="{value:'1',data: [ { text:'同意', id:'1' }, { text:'不同意', id:'2' } ] }" />
					</td>
					<td class="l-t-td-left">审核人：</td>
					<td class="l-t-td-right" width="100">
						<input type="text" id="office_deal_name" name="office_deal_name" ltype="text" />
					</td>
					<td class="l-t-td-left">审核时间：</td>
					<td class="l-t-td-right">
						<input name="office_deal_date" id="office_deal_date" type="text" ltype="date" ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
					</td>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right" width="215">
						<input type="text" id="office_deal_remark" name="office_deal_remark" ltype="text" />
					</td>
				</tr>	
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>车队收车</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">	
				<tr>
					<td class="l-t-td-left">返回时间：</td>
					<td class="l-t-td-right">
						<input name="back_date" id="back_date" type="text" ltype="date" ligerui="{initValue:'',format:'yyyy-MM-dd hh:mm'}" />
					</td>
					<td class="l-t-td-left">收车人：</td>
					<td class="l-t-td-right">
						<input type="text" id="receive_deal_name" name="receive_deal_name" ltype="text" />
					</td>
					<td class="l-t-td-left">公里止数：</td>
					<td class="l-t-td-right">
						<input type="text" id="end_km" name="end_km" ltype="text" />
					</td>
				</tr>
			</table>
		</fieldset>
		</c:if>
	</form>
</body>
</html>
