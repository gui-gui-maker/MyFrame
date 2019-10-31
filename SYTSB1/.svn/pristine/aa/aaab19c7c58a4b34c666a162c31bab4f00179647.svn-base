<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String userId = user.getId();
	String userType = ((com.khnt.rbac.impl.bean.Org)(user.getDepartment())).getProperty();
%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields : [{
			name : "device_type",
			compare : "=",
			value : ""
		},{
			name : "product_name",
			compare : "like",
			value : ""
		},{
			name : "status",
			compare : "=",
			value : ""
		}],
		tbar : [{
			text : '详情',
			id : 'detail',
			click : detail,
			icon : 'detail'
		}
		<%
			if("unit".equals(userType)){
				%>
				, '-', {
					text : '新增',
					id : 'add',
					click : add,
					icon : 'add'
				}
				<%
			}
		%>
		, '-', {
			text : '修改',
			id : 'modify',
			click : modify,
			icon : 'modify'
		}
		<%
			if("unit".equals(userType)){
				%>
				, '-', {
					text : '删除',
					id : 'del',
					click : del,
					icon : 'del'
				}, '-', {
					text : '提交',
					id : 'submit',
					click : submit,
					icon : 'submit'
				}
				<%
			}else if("dep".equals(userType)){
				%>
				, '-', {
					text : '生成报告',
					id : 'generateReport',
					click : generateReport,
					icon : 'submit'
				}
				<%
			}
		%>
		],
		listeners : {
			rowClick : function(rowData, rowIndex) {
			},
			rowDblClick : function(rowData, rowIndex) {
				detail();
			},
			selectionChange : function(rowData, rowIndex) {
				<%
					if("unit".equals(userType)){
						%>
						selectionChange1();
						<%
					}else if("dep".equals(userType)){
						%>
						selectionChange2();
						<%
					}
				%>
			},
			rowAttrRender : function(rowData, rowid) {

			}
		}
	};

	//行选择改变事件
	function selectionChange1() {
		var count = Qm.getSelectedCount();//行选择个数
		if (count == 0) {
			Qm.setTbar({
				modify : false,
				del : false,
				detail : false,
				submit: false
			});
		} else if (count == 1) {
			var status = Qm.getValueByName("status");
			if (status == "未提交") {
				Qm.setTbar({
					modify : true,
					del : true,
					detail : true,
					submit: true
				});
			} else {
				Qm.setTbar({
					modify : false,
					del : false,
					detail : true,
					submit: false
				});
			}
		} else {
			var n = 0;
			var m = 0;
			var records = Qm.getQmgrid().getSelecteds();
			var l = records.length;
			for ( var i = 0; i < l; i++) {
				var status = records[i]["status"];
				if (status == "未提交") {
					n++;
				} else if (status == "已提交") {
					m++;
				}
			}

			if ( n > 0  && m > 0) {
				Qm.setTbar({
					modify : false,
					del : false,
					detail : false,
					submit: false
				});
			} else if (n > 0 && m < 1) {
				Qm.setTbar({
					modify : false,
					del : true,
					detail : false,
					submit: false
				});
			} else if (n < 1 && m > 0) {
				Qm.setTbar({
					modify : false,
					del : false,
					detail : false,
					submit: false
				});
			}
		}
	}
	
	function selectionChange2() {
		var count = Qm.getSelectedCount();//行选择个数
		if (count == 0) {
			Qm.setTbar({
				modify : false,
				detail : false,
				generateReport: false
			});
		} else if (count == 1) {
				Qm.setTbar({
					modify : true,
					detail : true,
					generateReport: true
				});
			
		} else {
			Qm.setTbar({
				modify : false,
				detail : false,
				generateReport: false
			});
		}
	}

	//新增
	function add() {
		top.$.dialog({
					width : 800,
					height : 420,
					lock : true,
					title : "新增",
					data : {
						"window" : window
					},
					content : 'url:app/sinspection/sinspection_detail.jsp?status=add'
				});
	}

	//修改
	function modify() {
		
		var temp = Qm.getValueByName("flag").toString();

		
		if(temp=="2"){
			
			$.ligerDialog.alert("所选数据已生成报告，请重新选择！");
			return;
		}
		
		
		var record = Qm.getQmgrid().getSelected();
		if (record.check_result == "通过") {
			$.ligerDialog.success('数据已审核通过，不能修改！');
			return;
		}
		top.$.dialog({
					width : 800,
					height : 420,
					lock : true,
					title : "修改",
					data : {
						"window" : window
					},
					content : 'url:app/sinspection/sinspection_detail.jsp?status=modify&id='+ record.id
				});
	}

	//查看
	function detail() {
		var selectedId = Qm.getValuesByName("id");
		if (selectedId.length < 1) {
			$.ligerDialog.alert('请先选择要查看的记录！', "提示");
			return;
		}
		top.$.dialog({
					width : 800,
					height : 420,
					lock : true,
					title : "详情",
					data : {
						"window" : window
					},//把当前页面窗口传入下一个窗口，以便调用。
					content : 'url:app/sinspection/sinspection_detail.jsp?status=detail&id='+ Qm.getValueByName("id")
				});
	}

	//删除
	function del() {
		var selectedId = Qm.getValuesByName("id");
		if (selectedId.length < 1) {
			$.ligerDialog.alert('请先选择要删除的记录！', "提示");
			return;
		}
		$.ligerDialog.confirm(kui.DEL_MSG, function(yes) {
			if (yes) {
				$.ajax({
					url : "supervision/inspection/deleteSupervisionInspection.do?ids=" + selectedId,
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
	
	// 提交
	function submit() {
		var selectedId = Qm.getValuesByName("id");
		if (selectedId.length < 1) {
			$.ligerDialog.alert('请先选择要提交的记录！', "提示");
			return;
		}
		top.$.dialog({
			width : 500, 
			height : 200, 
			lock : true, 
			title:"产品监检提交",
			parent:api,
			content: 'url:app/sinspection/sinspection_submit.jsp?id='+selectedId,
			data : {"window" : window}
		});
	}
	
	// 生成报告
	function generateReport() {
		var temp = Qm.getValueByName("flag").toString();
		if(temp=="2"){
			$.ligerDialog.alert("所选数据已生成报告，请重新选择！");
			return;
		}
		var unitId="<sec:authentication property='principal.department.id'/>";
		getServiceFlowConfig("pro_flow",unitId,function(result,flowData){
			if(result){
				top.$.dialog({
					width : 600, 
					height : 200, 
					lock : true, 
					title:"选择证书",
					content: 'url:app/inspection/reportChange.jsp?status=modify&id='+Qm.getValuesByName("id")+'&flowId='+flowData.id,
					data : {"window" : window}
				});
			}else{
				$.ligerDialog.alert('没有找到相应流程！', "提示");
			}
		});	
	}

	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="sinspection_list" script="false">
	<%
		if("unit".equals(userType)){	// 独立法人单位
			%>
			<qm:param name="enter_user_id" value="<%=userId%>" compare="=" />
			
			<%
		}else if("dep".equals(userType)){	// 内部职能部门
			%>
			<qm:param name="send_user_id" value="<%=userId%>" compare="=" />
			<qm:param name="status" value="0" compare="<>" /><!-- 0：未提交，1：已提交，2：已生成报告 -->
			<%
		}
	%>
	</qm:qm>
</body>
</html>
