<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>节假日收车</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="app/oa/select/org_select.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:100},//可以自己定义 layout:column,float,
		sp_fields : [
				{
					name : "car_num", compare : "like"
				},{
					name : "driver", compare : "like"
				},{
					name : "manager_room_code", compare : "=",xtype:"combo",onBeforeOpen:showDepartList
				}, {
					name : "car_state", compare : "="
				},{
					name : "change_num", compare : "="
				},{
					group:[
					       {name:"car_state_date",compare:">=",value:""},
					       {label:"到",name:"car_state_date",compare:"<=",value:"",labelAlign:"center",labelWidth:20}
					       ],columnWidth:0.33
				}
		], 
		<tbar:toolBar type="tbar">
		</tbar:toolBar>,
		listeners : {
			rowClick : function(rowData, rowIndex) {
			}, rowDblClick : function(rowData, rowIndex) {
			}, selectionChange : function(rowData, rowIndex) {
				selectionChange();
			}, rowAttrRender : function(rowData, rowid) {
				if (rowData.change_num != "")
					rowData.car_num = rowData.car_num + "(" + rowData.change_num + ")";
			}
		}
	};

	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();
		if (count == 0) {
			Qm.setTbar({
				backCar : false
			});
		} else if (count == 1) {
			Qm.setTbar({
				backCar : true
			});
		} else {
			Qm.setTbar({
				backCar : true
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
	
	function backCar(item) {
		var width = 400;
		var height = 200;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "登记收车情况", data : {
				"window" : window
			},
			content : 'url:app/oa/car/Holidays_closing_detail.jsp?id=' + Qm.getValuesByName("id")
		});
	}
	function detail() {
		var selectedId = Qm.getValuesByName("id");
		var width = 700;
		var height =  400;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "查看", data : {
				"window" : window
			}, content : 'url:app/oa/car/apply_detail.jsp?status=detail' + '&id=' + selectedId
		});
	}
	
	function record(){
		var width = 800;
		var height = 550;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "未正常交车情况", data : {
				"window" : window
			},
			content : 'url:app/oa/car/unpack_list.jsp'
		});
	}
</script>
</head>
<%
	String unitId = SecurityUtil.getSecurityUser().getUnit().getId();
%>
<body>
	<qm:qm pageid="oa_c_back" script="false" singleSelect="true">
		<qm:param name="org_code" value="<%=unitId %>" compare="="/>
	</qm:qm>
	<script type="text/javascript">
		Qm.config.columnsInfo["car_state"]["binddata"]=[{text:'无故未交车',id:'无故未交车'},{text:'因公未交车',id:'因公未交车'},{text:'值班车',id:'值班车'},{text:'领导用车集中停放',id:'领导用车集中停放'}];
	</script>
</body>
</html>