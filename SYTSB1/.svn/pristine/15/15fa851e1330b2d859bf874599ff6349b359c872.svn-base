<%@page import="com.khnt.rbac.impl.bean.Org"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>通用查询</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="app/oa/select/org_select.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	//可以自己定义 layout:column,float,
		sp_fields : [{
			name : "apply_room_code", compare : "=",xtype:"combo",onBeforeOpen:showDepartList
			},{
				name : "destination", compare : "like"
			},{
				name : "car_num", compare : "like"
			},
			{
				name : "state", compare : "like"
			},
			{
				group:[
			          {name:"start_time",compare:">=",value:""},
			          {label:"到",name:"start_time",compare:"<=",value:"",labelAlign:"center",labelWidth:20}
			          ]
			},{
				group:[
				       {name:"end_time",compare:">=",value:""},
				       {label:"到",name:"end_time",compare:"<=",value:"",labelAlign:"center",labelWidth:20}
				       ]
			}
		],
		tbar : [
				{
					text : '详情', id : 'detail', icon : 'detail', click : detail
				},"-",
				{
					text : '修改', id : 'modify', icon : 'modify', click : modify
				},"-",
				{
					text : '导出选中数据', id : 'downData', icon : 'detail', click : downData
				}
		], 
		listeners : {
			rowClick : function(rowData, rowIndex) {
			},
			rowDblClick : function(rowData, rowIndex) {
				detail();
			},
			selectionChange : function(rowData, rowIndex) {
				selectionChange();
			},
			rowAttrRender : function(rowData, rowid) {
				//alert(rowid)
				if(rowData.change_num!="")rowData.car_num=rowData.car_num+"("+rowData.change_num+")";
			}
		}
	};
	function downData(){
		var selectedId = Qm.getValuesByName("id").toString();
		var width = 700;
		var height = 400;
		var windows = top.$.dialog({
			width : width,
			height : height,
			lock : true,
			title : "导出数据",
			data : {
				"window" : window
			},
			content : 'url:oa/car/apply/exportHis.do'
				+ '?ids=' + selectedId
		});
	}
	
	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		var state = Qm.getValuesByName("status").toString();
		if (count == 0) {
			Qm.setTbar({
				modify : false,
				detail : false,
				downData:false
			});
		} 
		else if (count == 1) {
			if(state[0]== "1"){
				Qm.setTbar({
					modify : false,
					detail : true,
					downData:true
				});
			}else{
				Qm.setTbar({
					modify : true,
					detail : true,
					downData:true
				});
			}
		}
		else {
			var delStatus = true;	
			if(state.indexOf("1")!=-1){
				alert(state.indexOf("1")!=-1);
				delStatus=false;
			}
				Qm.setTbar({
					add : true,
					modify : false,
					del : delStatus,
					detail : false,
					downData:true
				});
		}
	}
	//显示部门选择列表
	function showDepartList(){
		var unitId = "<sec:authentication property="principal.unit.id" />";
		var unitName = "<sec:authentication property="principal.unit.orgName" htmlEscape="false"/>";
		$(this).data('unitId',unitId);
		$(this).data('unitName',unitName);
		showOrgList.call(this);
	}
	function detail(){
		var selectedId = Qm.getValuesByName("id");
		var width = 700;
		var height = 400;
		var windows = top.$.dialog({
			width : width,
			height : height,
			lock : true,
			title : "查看",
			data : {
				"window" : window
			},
			content : 'url:app/oa/car/historyCar_detail.jsp?status=detail'
				+ '&id=' + selectedId
		});
	}
	function modify(){
		var selectedId = Qm.getValuesByName("id");
		var startTime = Qm.getValuesByName("start_time");
		var width = 700;
		var height = 500;
		var windows = top.$.dialog({
			width : width,
			height : height,
			lock : true,
			title : "修改",
			data : {
				"window" : window
			},
			content : 'url:app/oa/car/historyCar_detail.jsp?status=modify'
			+ '&id=' + selectedId+"&startTime="+startTime
		});
	}
</script>
</head>
<%
	String unitId = SecurityUtil.getSecurityUser().getUnit().getId();
	Org org = (Org) SecurityUtil.getSecurityUser().getDepartment();
%>
<body>
	<qm:qm pageid="oa_c_his" script="false">
		<%-- <qm:param name="state" value="已归还" compare="like" /> --%>
		<qm:param name="org_code" value="<%=unitId %>" compare="=" />
		<sec:authorize access="hasRole('oa_car_info')">
			<qm:param name="level_code" value="" compare="like" />
		</sec:authorize>
		<sec:authorize ifNotGranted="oa_car_info">
			<qm:param name="level_code" value="<%=org.getLevelCode() %>" compare="like" />
		</sec:authorize>
	</qm:qm>
</body>
</html>