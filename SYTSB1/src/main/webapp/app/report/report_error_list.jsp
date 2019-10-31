<%@page import="com.khnt.rbac.impl.bean.Employee"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<%
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User uu = (User)curUser.getSysUser();
	Employee e = (Employee)uu.getEmployee();
	String emp_id = e.getId();
	String uId = SecurityUtil.getSecurityUser().getId();
	String org_code = curUser.getDepartment().getOrgCode();	// 部门编号
	String org_id = curUser.getDepartment().getId();
	String type = request.getParameter("type");
%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields : [ 
			<%
			if(org_code.startsWith("ziliang") ){
				%>
				{name:"error_dep_id", compare:"="},	
				{name:"error_user_name", compare:"like"},	
				<%
			}else{
				if(org_code.startsWith("jd") || org_code.startsWith("cy") || org_code.startsWith("xxzx")){
					%>
					{name:"error_user_name", compare:"like"},	
					<%
				}
			}	
			if(StringUtil.isEmpty(type)){
				%>
				<%
			}
				%>
			{name:"sn", compare:"like"},
			{name:"report_sn", compare:"like"}	
		],
		tbar : [ {
			text : '详情',
			id : 'detail',
			click : detail,
			icon : 'detail'
		}
		, '-', {
			text : '查看报告',
			id : 'showReport',
			click : showReport,
			icon : 'detail'
		}
		<%
			if(org_code.startsWith("ziliang") || org_code.startsWith("xxzx")){
				if(StringUtil.isEmpty(type)){
					%>
					, '-', {
						text : '新增',
						id : 'add',
						click : add,
						icon : 'add'
					}, '-', {
						text : '修改',
						id : 'modify',
						click : modify,
						icon : 'modify'
					}
					<%
				}else{
					if("01".equals(type)){
						%>
						, '-', {
							text : '审核',
							id : 'qua_head_check',
							click : qua_head_check,
							icon : 'modify'
						}
						<%
					}
				}
				if(StringUtil.isEmpty(type)){
					%>
					, '-', {
						text : '删除',
						id : 'del',
						click : del,
						icon : 'del'
					}
					<%
				}
				%>
				, '-', {
					text : '发短信',
					id : 'sendMoMsg',
					click : sendMoMsg,
					icon : 'submit'
				}, '-', {
					text : '发微信',
					id : 'sendWxMsg',
					click : sendWxMsg,
					icon : 'submit'
				}
				<%
			}			
		%>
		, '-', { text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory}],
		listeners : {
			rowClick : function(rowData, rowIndex) {
			},
			rowDblClick : function(rowData, rowIndex) {
				detail();
			},
			selectionChange : function(rowData, rowIndex) {
				selectionChange()
			},
			rowAttrRender : function(rowData, rowid) {
	            var fontColor="black";
	            // 01：质量部已记录，待审核
	            if (rowData.status == '01'){
	            	fontColor="black";
	            }
	            // 03：质量部已审核并已发送至责任人，检验员待处理
	            if (rowData.status == '03'){
	            	fontColor="red";
	            }
	            // 04：检验员已纠正，部门负责人待确认
	            if (rowData.status == '04'){
	            	fontColor="blue";
	            }
	            // 05：部门负责人已确认，质量部待确认
	            if (rowData.status == '05'){
	            	fontColor="orange";
	            } 
	            // 06：质量部已确认整改完成
	            if (rowData.status == '06'){
	            	fontColor="green";
	            }
	            return "style='color:"+fontColor+"'";
			}
		}
	};

	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		if (count == 0) {
			Qm.setTbar({
				modify : false,
				del : false,
				detail : false,
				turnHistory : false,
				dep_head : false,
				qua_head_finish : false,
				qua_head_check : false,
				showReport : false,
				sendMoMsg : false,
				sendWxMsg : false,
				error_user_deal : false
			});
		} else if (count == 1) {
			Qm.setTbar({
				modify : true,
				del : true,
				detail : true,
				turnHistory : true,
				dep_head : true,
				qua_head_finish : true,
				qua_head_check : true,
				showReport : true,
				sendMoMsg : true,
				sendWxMsg : true,
				error_user_deal:true

			});
		} else {
			Qm.setTbar({
				modify : false,
				del : true,
				detail : false,
				turnHistory : false,
				dep_head : false,
				qua_head_finish : false,
				qua_head_check : true,
				showReport : false,
				sendMoMsg : true,
				sendWxMsg : true,
				error_user_deal : false
			});
		}
	}

	//新增
	function add() {
		top.$.dialog({
			width : 1000,
			height : 600,
			lock : true,
			title : "新增",
			data : {
				"window" : window
			},
			content : 'url:app/report/report_error_detail.jsp?status=add&type='
		});
	}

	//修改
	function modify() {
		// 质量部未审核以前，记录人可以修改不符合报告，审核后，审核人可以修改
		var error_dep_id = Qm.getValueByName("error_dep_id").toString();
		var data_status = Qm.getValueByName("data_status").toString();
		var check_user_id = Qm.getValueByName("check_user_id").toString();
        if(data_status.indexOf("已记录") == -1 && check_user_id!="<%=curUser.getId() %>"){
        	$.ligerDialog.warn("该不符合报告正在流转中或已整改完成，不能修改，请核查！如需修改，请联系审核人员进行修改！");
        	return;
        }

		top.$.dialog({
			width : 1000,
			height : 600,
			lock : true,
			title : "修改",
			data : {
				"window" : window
			},
			content : 'url:app/report/report_error_detail.jsp?status=modify&type=&id='+ Qm.getValueByName("id")
		});
	}

	//查看
	function detail() {
		//var error_dep_id = Qm.getValueByName("error_dep_id").toString();
		top.$.dialog({
			width : 1000,
			height : 680,
			lock : true,
			title : "详情",
			data : {
				"window" : window
			},//把当前页面窗口传入下一个窗口，以便调用。
			content : 'url:app/report/report_error_detail.jsp?status=detail&id='+ Qm.getValueByName("id")
		});
	}

	// 删除（流转中或已完成的不符合报告，质量部才拥有删除权限）
	function del() {
		$.ligerDialog.confirm(kui.DEL_MSG, function(yes) {
			if (yes) {
				$.ajax({
					url : "report/error/del.do?ids=" + Qm.getValuesByName("id").toString(),
					type : "post",
					async : false,
					success : function(data) {
						if (data.success) {
							top.$.notice("删除成功！");
							Qm.refreshGrid();
						} else {
							top.$.notice("删除失败！" + data.msg);
						}
					}
				});
			}
		});
	}

	// 质量部审核
	function qua_head_check(){
		var id = Qm.getValuesByName("id").toString();
		top.$.dialog({
			width: 600,
			height: 300,
			lock:true,
			title:"审核",
			content: 'url:app/report/report_error_qua_check.jsp?status=modify&type=01',
			data : {
				"window" : window,
				"ids":Qm.getValuesByName("id").toString()
			}
		});
	}
	
	// 发短信
	function sendMoMsg() {
		$.ligerDialog.confirm("亲，确定要发送短信给责任人吗？", function(yes) {
			if (yes) {
				$.ajax({
					url : "report/error/sendMoMsg.do?ids=" + Qm.getValuesByName("id").toString(),
					type : "post",
					async : false,
					success : function(resp) {
						if (resp.success) {
							top.$.notice("发送成功！");
							Qm.refreshGrid();
						} else {
							$.ligerDialog.alert(resp.msg);
						}
					}
				});
			}
		});
	}

	// 发微信
	function sendWxMsg() {
		$.ligerDialog.confirm("亲，确定要发送微信给责任人吗？", function(yes) {
			if (yes) {
				$.ajax({
					url : "report/error/sendWxMsg.do?ids=" + Qm.getValuesByName("id").toString(),
					type : "post",
					async : false,
					success : function(resp) {
						if (resp.success) {
							top.$.notice("发送成功！");
							Qm.refreshGrid();
						} else {
							$.ligerDialog.alert(resp.msg);
						}
					}
				});
			}
		});
	}
	
	// 发微信和短信
	function sendMsgs(id, send_msg_type) {
		$.ajax({
			url : "report/error/sendMsgs.do?ids=" + id + "&send_msg_type="+send_msg_type,
			type : "post",
			async : false,
			success : function(resp) {
				if (resp.success) {
					top.$.notice("保存成功！");
					Qm.refreshGrid();
				} else {
					$.ligerDialog.alert(resp.msg);
					Qm.refreshGrid();
				}
			}
		});
	}
	
	// 流转过程
	function turnHistory(){	
		top.$.dialog({
	   		width : 400, 
	   		height : 700, 
	   		lock : true, 
	   		title: "流程卡",
	   		content: 'url:report/error/getFlowStep.do?id='+Qm.getValueByName("id"),
	   		data : {"window" : window}
	   	});
	}
	
	// 查看报告
	function showReport(){
		var id = Qm.getValueByName("id").toString();
		top.$.dialog({
			width: 1000,
			height: 620,
			lock:true,
			title:"查看报告",
			data: {window:window},
			content: 'url:app/report/report_error_info_list.jsp?id='+id
		});
	}


	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="black">“黑色”</font>代表质量部待审核；
				<font color="purple">“紫色”</font>代表质量部审核未通过，流程结束；
				<font color="red">“红色”</font>代表检验员待处理；
				<font color="blue">“蓝色”</font>代表检验员已纠正，部门负责人待确认，
				<!-- 2016-09-02孟凡昊提出修改，去掉责任部门负责人确认流程 -->
				<!-- 2017-08-01谢方提出修改，增加责任部门负责人确认流程 -->
				<font color="orange">“桔色”</font>代表部门负责人已确认，质量部待确认；
				<font color="green">“绿色”</font>代表质量部已确认整改完成。
			</div>
		</div>
	</div>
	<qm:qm pageid="report_error" script="false">
		<%
			if(org_code.startsWith("jd") || org_code.startsWith("cy")){
				if(StringUtil.isEmpty(type)){
					%>
					<qm:param name="receive_user_id" value="<%=emp_id%>" compare="=" />
					<%
				}else{
					%>
					<qm:param name="error_dep_id" value="<%=org_id%>" compare="like" />
					<%
						if(!"-1".equals(type)){
							%>
							<qm:param name="data_status" value="<%=type%>" compare="=" />
							<%
						}
				}
			}else{
				if(StringUtil.isNotEmpty(type)){
					%>
					<qm:param name="data_status" value="<%=type%>" compare="=" />
					<%
				}
			}
		%>
		
	</qm:qm>
	<script type="text/javascript">
	// 根据 sql或码表名称获得Json格式数据
	Qm.config.columnsInfo.error_dep_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ORG_CODE like 'cy%' order by orders "></u:dict>;
	</script>
</body>
</html>