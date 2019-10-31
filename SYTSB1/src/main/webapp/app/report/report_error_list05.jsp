<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser useres = SecurityUtil.getSecurityUser();
User uu = (User)useres.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
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
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields : [ 
			<%
				if(org_code.startsWith("ziliang") ){
					%>
					{name:"error_dep_id", compare:"="},	
					{name:"error_user_name", compare:"like"},	
					<%
				}
			%>
			{name:"report_sn", compare:"like"}	
		],
		tbar : [ {
			text : '详情',
			id : 'detail',
			click : detail,
			icon : 'detail'
		}
		, '-', {
			text : '查看纠错报告',
			id : 'showReport',
			click : showReport,
			icon : 'detail'
		}
		, '-', {
			text : '整改确认',
			id : 'qua_head_finish',
			click : qua_head_finish,
			icon : 'modify'
		}
		, '-', { 
			text:'流转过程', 
			id:'turnHistory',
			icon:'follow', 
			click: turnHistory
		}
		],
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
	            // 02：质量部审核未通过
	            if (rowData.status == '02'){
	            	fontColor="purple";
	            }
	            // 03：质量部审核通过，检验员待处理
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
		var status = Qm.getValueByName("status").toString();
		if (count == 0) {
			Qm.setTbar({
				detail : false,
				turnHistory : false,
				dep_head : false,
				qua_head_finish : false,
				showReport : false
			});
		} else if (count == 1) {
			if(status == "04" || status == "05"){
				Qm.setTbar({
					detail : true,
					turnHistory : true,
					qua_head_finish : true,
					showReport : true
				});	
			}else{
				Qm.setTbar({
					detail : true,
					turnHistory : true,
					qua_head_finish : false,
					showReport : true
				});	
			}
		} else {
			Qm.setTbar({
				detail : false,
				turnHistory : false,
				qua_head_finish : false,
				showReport : false
			});
		}
	}

	//查看
	function detail() {
		var error_dep_id = Qm.getValueByName("error_dep_id").toString();
		top.$.dialog({
			width : 1000,
			height : 700,
			lock : true,
			title : "详情",
			data : {
				"window" : window
			},//把当前页面窗口传入下一个窗口，以便调用。
			content : 'url:app/report/report_error_detail.jsp?status=detail&org_id=<%=org_id%>&id='+ Qm.getValueByName("id")
		});
	}

	// 质量部整改确认
	function qua_head_finish(){
		var error_dep_id = Qm.getValueByName("error_dep_id").toString();
		top.$.dialog({
			width: 1000,
			height: 700,
			lock:true,
			title:"确认不符合报告整改情况",
			data: {window:window},
			content: 'url:app/report/report_error_detail.jsp?status=modify&type=05&org_id='+error_dep_id+'&id='+Qm.getValueByName("id")
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
				<font color="red">“红色”</font>代表质量部审核通过，检验员待处理；
				<font color="blue">“蓝色”</font>代表检验员已纠正，部门负责人待确认；
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
				if(StringUtil.isNotEmpty(type)){
					if("03".equals(type)){
						%>
						<qm:param name="error_user_id" value="<%=userId%>" compare="like" />
						<%
					}
				}else{
					%>
					<qm:param name="error_dep_id" value="<%=org_id%>" compare="like" />
					<%
				}
			}else{
				if(StringUtil.isNotEmpty(type)){
					if("04".equals(type)){
						%>
						<qm:param name="data_status" value="04" compare="=" logic="or"/>
						<%
					}
					if("05".equals(type)){
						%>
						<qm:param name="data_status" value="05" compare="=" logic="or"/>
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
