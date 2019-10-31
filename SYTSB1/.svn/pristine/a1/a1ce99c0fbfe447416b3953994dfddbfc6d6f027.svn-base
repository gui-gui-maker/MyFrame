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
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User user = (User)curUser.getSysUser();
	Employee emp = (Employee)user.getEmployee();
	String emp_id = emp.getId();
%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields : [ 
			{name : "report_sn", compare : "like", value : "" },
			{name : "report_com_name", compare : "like", value : "" },
			{name : "advance_fees", id : "advance_fees", compare:"like"},
			{name : "change_money", compare : "like", value : "" },
			{group:[
				{name:"create_date", id:"create_date", compare:">="},
				{label:"到", name:"create_date", id:"create_date1", compare:"<=", labelAlign:"center", labelWidth:20}
			]},
			{name:"data_status", id:"data_status", compare:"="}
		],
		tbar : [
			{ text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory}
			<sec:authorize ifAnyGranted="CHECK_CHANGE_MONEY,sys_administrate">
				, '-', {text : '审核', id : 'check', click : check, icon : 'modify'}
			</sec:authorize>
			<sec:authorize ifAnyGranted="CHECK_CHANGE_MONEY,sys_administrate,CHANGE_REPORT_MONEY">
			, '-', {text : '删除', id : 'del', click : del, icon : 'del'}
		</sec:authorize>
		],
		listeners : {
			rowClick : function(rowData, rowIndex) {
			},
			rowDblClick : function(rowData, rowIndex) {
				turnHistory();
			},
			selectionChange : function(rowData, rowIndex) {
				selectionChange()
			},
			rowAttrRender : function(rowData, rowid) {
	            var fontColor="black";
	            // 0：未审核
	            if (rowData.status == '0'){
	            	fontColor="black";
	            }
	         	// 1：审核通过
	            if (rowData.status == '1'){
	            	fontColor="green";
	            }
	            // 2：审核不通过
	            if (rowData.status == '2'){
	            	fontColor="red";
	            }
	         	// 99：已作废
	            if (rowData.status == '99'){
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
				turnHistory : false,
				check : false,
				del : false
			});
		} else if (count == 1) {
			Qm.setTbar({
				turnHistory : true,
				check : true,
				del : true
			});
		} else {
			Qm.setTbar({
				turnHistory : false,
				check : true,
				del : false
			});
		}
	}
	
	function check(){
		var ids = Qm.getValuesByName("id").toString();
		var data_status = Qm.getValuesByName("data_status").toString();
		var d_status = data_status.split(",");
		var to_check = true;
		for(var i=0;i<d_status.length;i++){
			if("未审核" != d_status[i]){
				to_check = false;
				$.ligerDialog.alert("您选择的记录中包含已审核或已作废的记录，不能执行审核操作，请重新选择！");
			}
		}
		
		if(to_check){
			top.$.dialog({
				width: 600,
				height: 300,
				lock:true,
				title:"审核",
				content: 'url:app/payment/change_money_check.jsp?status=modify',
				data : {
					"window" : window,
					"ids":ids
				}
			});
		}
	}
	
	//删除
	function del() {
		var data_status = Qm.getValueByName("data_status").toString();
		if("未审核" == data_status){
			var create_emp_id = Qm.getValueByName("create_emp_id").toString();
			if(create_emp_id == "<%=emp_id %>"){
				$.ligerDialog.confirm(kui.DEL_MSG, function(yes) {
					if (yes) {
						$.ajax({
							url : "inspectionChangeMoney/del.do?ids=" + Qm.getValuesByName("id").toString(),
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
			}else{
				top.$.notice("亲，您无权删除该记录，请联系数据录入人员删除！");
			}
		}else if("已作废" == data_status){
			top.$.notice("亲，所选记录已删除！");
		}else{
			top.$.notice("亲，所选记录已审核，不能删除哦！");
		}
	}

	// 流转过程
	function turnHistory(){	
		top.$.dialog({
	   		width : 400, 
	   		height : 700, 
	   		lock : true, 
	   		title: "流程卡",
	   		content: 'url:inspectionChangeMoney/getFlowStep.do?id='+Qm.getValueByName("id").toString(),
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
				<font color="black">“黑色”</font>代表待审核；
				<font color="green">“绿色”</font>代表审核通过；
				<font color="red">“红色”</font>代表审核未通过；
				<font color="blue">“蓝色”</font>代表已作废。
			</div>
		</div>
	</div>
	<qm:qm pageid="change_money_list" script="false">
	</qm:qm>	
</body>
</html>
