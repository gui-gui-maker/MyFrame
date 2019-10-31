<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser user = SecurityUtil.getSecurityUser();
User uu = (User)user.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<%
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	String org_code = curUser.getDepartment().getOrgCode();	// 部门编号
	String org_id = curUser.getDepartment().getId();
	String type = request.getParameter("type");
%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
		sp_fields : [ 
			{name:"sn", compare:"like"},
			<%
			if(!org_code.startsWith("jd") && !org_code.startsWith("cy")){
				%>
				{name:"error_dep_id", compare:"="},	
				{name:"error_user_name", compare:"like"},	
				<%
			}	
			if(StringUtil.isEmpty(type)){
				%>
				<%
			}
				%>
			{name:"report_sn", compare:"like"},	
			{name:"new_report_sn", compare:"like"},
			{name:"data_status", compare:"like"}
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
			if(org_code.startsWith("jd") || org_code.startsWith("cy") || org_code.startsWith("xxzx")){
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
					if("02".equals(type)){
						%>
						, '-', {
							text : '确认',
							id : 'app_dep_finish',
							click : app_dep_finish,
							icon : 'modify'
						}
						<%
					}
				}
			}
			if(org_code.startsWith("fuwu") || org_code.startsWith("ziliang")){
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
			}
			if(org_code.startsWith("fuwu") && "01".equals(type)){
				%>
				, '-', {
					text : '借报告',
					id : 'borrow',
					click : borrow,
					icon : 'role'
				}
				, '-', {
					text : '确认',
					id : 'confirm',
					click : confirm,
					icon : 'modify'
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
	            // 外借报告 显示蓝色
	            if (rowData.is_borrow_report == 1){
	            	fontColor="blue";
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
				confirm : false,
				app_dep_finish : false,
				showReport : false,
				borrow : false
			});
		} else if (count == 1) {
			Qm.setTbar({
				modify : true,
				del : true,
				detail : true,
				turnHistory : true,
				confirm : true,
				app_dep_finish : true,
				showReport : true,
				borrow : true
			
			});
		} else {
			Qm.setTbar({
				modify : false,
				del : true,
				detail : false,
				turnHistory : false,
				confirm : false,
				app_dep_finish : false,
				showReport : false,
				borrow : false
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
			content : 'url:app/report/report_error_record_detail.jsp?status=add&type=&confirm_flag=1&org_id=<%=org_id%>'
		});
	}

	//修改
	function modify() {
		// 业务服务部未确认以前，检验员可以修改纠正流转表
		var error_dep_id = Qm.getValueByName("error_dep_id").toString();
		var data_status = Qm.getValueByName("data_status").toString();
        if(data_status.indexOf("检验员") == -1){
        	$.ligerDialog.warn("该不符合纠正正在流转中或已纠正完成，不能修改，请核查！");
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
			content : 'url:app/report/report_error_record_detail.jsp?status=modify&type=&org_id='+error_dep_id+'&confirm_flag='+ Qm.getValueByName("confirm_flag")+'&id='+ Qm.getValueByName("id")
		});
	}

	//查看
	function detail() {
		var error_dep_id = Qm.getValueByName("error_dep_id").toString();
		top.$.dialog({
			width : 1000,
			height : 680,
			lock : true,
			title : "详情",
			data : {
				"window" : window
			},//把当前页面窗口传入下一个窗口，以便调用。
			content : 'url:app/report/report_error_record_detail.jsp?status=detail&org_id='+error_dep_id+'&confirm_flag='+ Qm.getValueByName("confirm_flag")+'&id='+ Qm.getValueByName("id")
		});
	}

	// 删除（流转中或已完成的纠正流转表，质量部才拥有删除权限）
	// 业务服务部未确认以前，检验员可以删除纠正流转表
	function del() {
		var dep_code = "<%=org_code%>";
		// 部门编号（机电部门：jd，承压部门：cy）
		if(dep_code.indexOf("jd")!=-1 || dep_code.indexOf("cy")!=-1){
			var data_status = Qm.getValueByName("data_status").toString();
	        if(data_status.indexOf("检验员") == -1){
	        	$.ligerDialog.warn("该不符合纠正正在流转中或已纠正完成，如需删除，请联系质量部负责人！");
	        	return;
	        }
		}
		$.ligerDialog.confirm(kui.DEL_MSG, function(yes) {
			if (yes) {
				$.ajax({
					url : "report/error/record/del.do?ids=" + Qm.getValuesByName("id").toString(),
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
	
	// 业务服务部确认不符合纠正措施
	function confirm(){
		var error_dep_id = Qm.getValueByName("error_dep_id").toString();
		top.$.dialog({
			width: 1000,
			height: 620,
			lock:true,
			title:"确认纠正",
			data: {window:window},
			content: 'url:app/report/report_error_record_detail.jsp?status=modify&type=01&org_id='+error_dep_id+'&confirm_flag='+ Qm.getValueByName("confirm_flag")+'&id='+Qm.getValueByName("id")
		});
	}
	
	 // 责任部门负责人确认纠正完成情况
	function app_dep_finish(){
		var error_dep_id = Qm.getValueByName("error_dep_id").toString();
  		var data_status = Qm.getValueByName("data_status").toString();
  		if(data_status.indexOf("业务服务部已确认")!=-1){
			top.$.dialog({
				width: 1000,
				height: 620,
				lock:true,
				title:"确认纠正完成情况",
				data: {window:window},
				content: 'url:app/report/report_error_record_detail.jsp?status=modify&type=02&org_id='+error_dep_id+'&confirm_flag='+ Qm.getValueByName("confirm_flag")+'&id='+Qm.getValueByName("id")
			});
		}else{
	    	$.ligerDialog.warn("请先等待业务服务部确认后，再确认纠正完成情况！");
	   		return;
		}
	}
	
	// 流转过程
	function turnHistory(){	
		top.$.dialog({
	   		width : 400, 
	   		height : 700, 
	   		lock : true, 
	   		title: "流程卡",
	   		content: 'url:report/error/record/getFlowStep.do?id='+Qm.getValueByName("id"),
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
			content: 'url:app/report/report_error_record_info_list.jsp?id='+id
		});
	}
	
	// 借报告
	function borrow(){
		var org_id = Qm.getValuesByName("error_dep_id").toString();
		top.$.dialog({
			width : 600,
			height : 200,
			lock : true,
			title : "借报告",
			content : 'url:app/report/report_error_record_borrow.jsp?status=modify&org_id='+org_id+'&ids='
						+ Qm.getValuesByName("id").toString(),
			data : {
				"window" : window
			}
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
				<font color="blue">“蓝色”</font>代表是外借报告。
			</div>
		</div>
	</div>
	<qm:qm pageid="report_error_record" script="false">
		<%
			if(org_code.startsWith("jd") || org_code.startsWith("cy")){
				if(StringUtil.isEmpty(type)){
					%>
					<qm:param name="deal_user_id" value="<%=userId%>" compare="=" />
					<%
				}else{
					%>
					<qm:param name="error_dep_id" value="<%=org_id%>" compare="=" />
					<%
						if(!"-1".equals(type)){
							%>
							<qm:param name="data_status" value="<%=type%>" compare="=" />
							<%
						}
				}
			}else{
				if(StringUtil.isNotEmpty(type)){
					if(!"-1".equals(type)){
					%>
					<qm:param name="data_status" value="<%=type%>" compare="=" />
					<%
					}
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
