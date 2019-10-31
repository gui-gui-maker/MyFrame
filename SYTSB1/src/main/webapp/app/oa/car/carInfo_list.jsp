<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String orgId = user.getUnit().getId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>通用查询</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="app/oa/select/org_select.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:100},//可以自己定义 layout:column,float,
			sp_fields : [
				{
					name : "car_brand", compare : "like"
				},{
					name : "state", compare : "="
				},{
					name : "manager_room_code", compare : "=",xtype:"combo",onBeforeOpen:showDepartList
				},{
					name : "load_number", compare : ">="
				},{
					name : "car_num", compare : "like"
				},{group:[
				           {name:"buy_date",compare:">=",value:""},
				           {label:"到",name:"buy_date",compare:"<=",value:"",labelAlign:"center",labelWidth:20}
				           ],columnWidth:0.33
				}
		],
		<tbar:toolBar type="tbar">
		</tbar:toolBar>,
		/**
		tbar : [
				{
					text : '详情', id : 'detail', icon : 'detail', click : detail
				}
				<sec:authorize access="hasRole('oa_car_carinfo')">
				, "-", {
					text : '新增', id : 'add', icon : 'add', click : add
				}, "-", {
					text : '修改', id : 'modify', icon : 'modify', click : modify
				}, "-", {
					text : '删除', id : 'del', icon : 'delete', click : del
				}
				</sec:authorize>
		],
		*/
		listeners : {
			rowClick : function(rowData, rowIndex) {
			}, rowDblClick : function(rowData, rowIndex) {
				detail(rowData);
			}, selectionChange : function(rowData, rowIndex) {
				selectionChange();
			}, rowAttrRender : function(rowData, rowid) {
				//alert(rowid)
			}
		}
	};

	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		var repairType = Qm.getValuesByName("repairType");
		var CAR_STATE = Qm.getValuesByName("CAR_STATE");
		var delparam = true;
		for(i in repairType){
			if(repairType[i]=="维保中" || repairType[i]=="维保申请中" || CAR_STATE[i]=="用车中" || CAR_STATE[i]=="派车中"){
				delparam = false;
			}
		}
		if (count == 0) {
			Qm.setTbar({
				add : true, modify : false, del : false, detail : false
			});
		} else if (count == 1) {
			Qm.setTbar({
				add : true, modify : true, del : delparam, detail : true
			});
		} else {
			Qm.setTbar({
				add : true, modify : false, del : delparam, detail : false
			});
		}
	}
	//显示部门选择列表
	function showDepartList(){
		var unitId = "<sec:authentication property="principal.unit.id" />";
		var unitName = "<sec:authentication property="principal.unit.orgName" />";
		$(this).data('unitId',unitId);
		$(this).data('unitName',unitName);
		showOrgList.call(this);
	}
	function modify(item) {
		var selectedId = new Array();
		var status = "modify";
		if (item.id == "modify") {//点击修改按钮调用的本方法
			selectedId = Qm.getValuesByName("id");
		} else {//双击数据调用本方法
			selectedId[0] = item.id;
			status = "detail";
		}
		if (selectedId.length > 1) {
			$.ligerDialog.alert('不支持批量修改，请只选择一条数据！', "提示");
			return;
		} else if (selectedId.length < 1) {
			$.ligerDialog.alert('请先选择要修改的数据！', "提示");
			return;
		}
		var width = 868;
		var height = 635;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "编辑", data : {
				"window" : window
			}, content : 'url:app/oa/car/carInfo_detail.jsp?status=' + status + '&id=' + selectedId
		});
	}
	function add(item) {
		var width = 868;
		var height = 635;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "新增", data : {
				"window" : window
			}, content : 'url:app/oa/car/carInfo_detail.jsp?status=add'

		});
	}

	function del() {
		var selectedId = Qm.getValuesByName("id");
		if (selectedId.length < 1) {
			$.ligerDialog.alert('请先选择要删除的事项！', "提示");
			return;
		}
		$.ligerDialog.confirm(kui.DEL_MSG, function (yes) {
			if(yes){
				$.ajax({
					url:"oa/car/info/delete.do?ids="+selectedId,
					type:"post",
					async:false,
					success:function(data){
						if(data.success){
							top.$.notice("删除成功！");
							Qm.refreshGrid();
						}
					}
				});
			}
		});
	}

	function detail(item) {
		var selectedId = item.id;
		if (selectedId == "detail") {
			selectedId = Qm.getValuesByName("id");
		} else {
			selectedId = item.id;
		}
		var width = 700;
		var height = 511;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "详情", data : {
				"window" : window
			}, content : 'url:app/oa/car/carInfo_detail.jsp?status=detail' + '&id=' + selectedId
		});
	}
</script>
</head>
<body>
	<!-- 每个单位要过滤本单位车辆  -->
	<qm:qm pageid="oa_carinfo" script="false" singleSelect="false">
	    <qm:param name="org_code" value="<%=orgId%>" compare="=" />
	</qm:qm>
</body>
</html>