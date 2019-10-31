<%@page import="com.khnt.utils.StringUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="java.util.Map"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User user = (User) curUser.getSysUser();
%>
<head>
<title>车辆维保记录</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults : {columnWidth : 0.33, labelAlign : 'right', labelSeparator : '', labelWidth : 60 },//可以自己定义 layout:column,float,
		sp_fields : [
				{ name : "car_num", compare : "like" },
				{ name : "wb_com_name", compare : "like" },
				{ group : [
					{ name : "apply_date", compare : ">=", value : "" },
					{ label : "到", name : "apply_date", compare : "<=",value:"",labelAlign:"center",labelWidth:20}
				],columnWidth:0.33
				}],
		tbar : [ { text : '详情', id : 'detail', click : detail, icon : 'detail' }, '-', 
				 { text : '新增', id : 'add', click : add, icon : 'add' }, '-', 
				 { text : '修改', id : 'modify', click : modify, icon : 'modify' }, '-', 
				 { text : '删除', id : 'del', click : del, icon : 'del' } ],
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
				var fontColor="black";
	            return "style='color:"+fontColor+"'";
			}
		}
	};

	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		if (count == 1) {
			Qm.setTbar({ modify : true, del : true, detail : true,});
		} else {
			Qm.setTbar({ modify : false, del : false, detail : false});
		}
	}
	//新增
	function add() {
		var width = 1000;
		var height = 800;
		top.$.dialog({
			width : width,
			height : height,
			lock : true,
			title : "新增",
			data : {
				"window" : window
			},
			content : 'url:app/car/car_wb/pc/carWb_record_detail.jsp?pageStatus=add'
		});
	}
	//详情
	function detail() {
		var selectedId = Qm.getValueByName("id");
		var width = 1000;
		var height = 800;
		var windows = top.$.dialog({
			width : width,
			height : height,
			lock : true,
			title : "详情",
			data : {
				"window" : window
			},
			content : 'url:app/car/car_wb/pc/carWb_record_detail.jsp?pageStatus=detail&id='+ selectedId
		});
	}
	//修改
	function modify() {
		var selectedId = Qm.getValueByName("id");
		var width = 1000;
		var height = 800;
		var windows = top.$.dialog({
			width : width,
			height : height,
			lock : true,
			title : "修改",
			data : {
				"window" : window
			},
			content : 'url:app/car/car_wb/pc/carWb_record_detail.jsp?pageStatus=modify&id=' + selectedId
		});
	}
	//删除
	function del() {
		var selectedId = Qm.getValueByName("id");
		$.ligerDialog.confirm(kui.DEL_MSG, function(yes) {
			if (yes) {
				$.ajax({
					url : "carWbRecordAction/deleteInfo.do?id=" + selectedId,
					type : "post",
					async : false,
					success : function(data) {
						if (data.success) {
							top.$.notice("删除成功！");
							Qm.refreshGrid();
						}
					}
				});
			}
		});
	}
	function refreshGrid() {
		Qm.refreshGrid();
	}
	function loadGridData(treeNodeId,treeNode,parentNode) {
    	if(treeNode){
	    	if(treeNode.id =="all"){
	    		Qm.setCondition([]);
	    	}else if((treeNode.id =="1" || treeNode.id =="2" || treeNode.id =="3" || treeNode.id =="4")){
				Qm.setCondition([ {
					name : "type",
					compare : "=",
					value : treeNode.id
				} ]);
			}else{
				Qm.setCondition([ {
					name : "fk_car_id",
					compare : "=",
					value : treeNode.id
				} ]);
			}
    	}else{
    		Qm.setCondition([]);
    	}
    	Qm.searchData();
	}
</script>
</head>

<body>
	<qm:qm pageid="TJY2_CAR_WB_RECORD" script="false" singleSelect="true">
		<qm:param name="data_status" value="99" compare="!=" />
	</qm:qm>
</body>
</html>