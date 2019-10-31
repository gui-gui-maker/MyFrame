<%@page import="com.khnt.rbac.impl.bean.Employee"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<%
	String data_status = request.getParameter("data_status");
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User user = (User) curUser.getSysUser();
	Employee emp = (Employee) user.getEmployee();
%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields : [ 
			{name : "func_name", compare : "=", value : "" },
			{name : "pro_desc", compare : "like", value : "" },
			{name : "org_id", id : "org_id", compare:"=",isMultiSelect:true},
			{name : "advance_user_name", compare : "like", value : "" },
			{group:[
				{name:"advance_date", id:"advance_date", compare:">="},
				{label:"到", name:"advance_date", id:"advance_date1", compare:"<=", labelAlign:"center", labelWidth:20}
			]},
			{name:"data_status", id:"data_status", compare:"="}
		],
		tbar : [
			{
				text : '详情',
				id : 'detail',
				click : detail,
				icon : 'detail'
			}/*, '-', {
				text : '确认',
				id : 'confirm',
				click : confirm,
				icon : 'modify'
			}*/
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
				if (rowData.status == '5'){
		    		fontColor="blue";
		    	}
		   		if (rowData.data_status == '99'){
	          		fontColor="red";
	            }
				return "style='color:"+fontColor+";'";
		 		//return "style='color:"+fontColor+";font-weight: bold;'";
			},
	   		pageSizeOptions:[10,20,30,50,100,200]
		}
	};

	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		if (count == 0) {
			Qm.setTbar({
				detail : false,		
				confirm : false,
				turnHistory : false,
				del : false
			});
		} else if (count == 1) {
			Qm.setTbar({
				detail : true,		
				confirm : true,
				turnHistory : true,
				del : true
			});
		} else {
			Qm.setTbar({
				detail : false,			
				confirm : true,
				turnHistory : false,
				del : true
			});
		}
	}

	//查看
	function detail() {
		top.$.dialog({
			width : 1000,
			height : 600,
			lock : true,
			title : "详情",
			data : {
				"window" : window
			},//把当前页面窗口传入下一个窗口，以便调用。
			content : 'url:app/maintenance/maintenance_info_detail.jsp?status=detail&id='+ Qm.getValueByName("id")
		});
	}
	
	// 确认
	function confirm() {
		// 报告人ID
		var advance_user_id = Qm.getValueByName("advance_user_id").toString();
		if(advance_user_id != "<%=emp.getId() %>"){
			$.ligerDialog.error("亲，只能确认自己报告的，请重新选择！");
			return;
		}else{
			$.ligerDialog.confirm("亲，确定要确认所选业务功能吗？确认后无法撤回哦！", function(yes) {
				if (yes) {
					$.ajax({
						url : "maintenance/info/confirms.do?ids=" + Qm.getValuesByName("id").toString(),
						type : "post",
						async : false,
						success : function(data) {
							if (data.success) {
								top.$.notice("确认成功！");
								Qm.refreshGrid();
							} else {
								top.$.notice("确认失败！" + data.msg);
							}
						}
					});
				}
			});
		}
	}
	
	//删除
	function del() {
		$.ligerDialog.confirm(kui.DEL_MSG, function(yes) {
			if (yes) {
				$.ajax({
					url : "maintenance/info/del.do?ids=" + Qm.getValuesByName("id").toString(),
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

	// 流转过程
	function turnHistory(){	
		top.$.dialog({
	   		width : 400, 
	   		height : 700, 
	   		lock : true, 
	   		title: "流程卡",
	   		content: 'url:maintenance/info/getFlowStep.do?id='+Qm.getValueByName("id"),
	   		data : {"window" : window}
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
				<font color="black">“黑色”</font>代表待确认，
				<font color="blue">“蓝色”</font>代表已确认。
			</div>
		</div>
	</div>
	<qm:qm pageid="maintenance_list2" script="false" >
		<!-- 数据状态（数据字典MAINTENANCE_DATA_STATUS，0：未受理 1：已受理 2：已论证 3：已完成 4：已发布  5：已确认 99：已删除） -->
		<qm:param name="data_status" compare="in" value="('3','4','5')" dataType="user"/>
	</qm:qm>
	<script type="text/javascript">
		// 根据 sql或码表名称获得Json格式数据
		Qm.config.columnsInfo.func_name.binddata=<u:dict code="MAINTENANCE_FUNCTION"></u:dict>;
		Qm.config.columnsInfo.org_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where property='dep' and status='used' order by orders "></u:dict>;
	</script>
</body>
</html>
