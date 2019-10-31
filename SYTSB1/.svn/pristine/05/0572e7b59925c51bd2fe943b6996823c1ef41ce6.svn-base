<%@page import="com.khnt.rbac.impl.bean.Org"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>通用查询</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.25,labelAlign:'right',labelSeparator:'',labelWidth:100},//可以自己定义 layout:column,float, 
		sp_fields : [
				{
					name : "car_num", compare : "like"
				}, {
					name : "cost_type", compare : "="
				},{
					group:[
					        {name:"register_date",compare:">=",value:""},
					        {label:"到",name:"register_date",compare:"<=",value:"",labelAlign:"center",labelWidth:20}
					        ]
				}
		], 
		<tbar:toolBar type="tbar">
		</tbar:toolBar>,
		listeners : {
			rowClick : function(rowData, rowIndex) {
			}, rowDblClick : function(rowData, rowIndex) {
				modify(rowData);
			}, selectionChange : function(rowData, rowIndex) {
				selectionChange();
			}, rowAttrRender : function(rowData, rowid) {
				//alert(rowid)
				if (rowData.change_num != "")
					rowData.car_num = rowData.car_num + "(" + rowData.change_num + ")";

			}
		}
	};
	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		if (count == 0) {
			Qm.setTbar({
				add : true, modify : false, del : false, detail : false

			});
		} else if (count == 1) {
			Qm.setTbar({
				add : true, modify : true, del : true, detail : true
			});
		} else {
			Qm.setTbar({
				add : true, modify : false, del : true, detail : false
			});

		}
	}

	function modify(item) {
		var selectedId = new Array();
		var status = "modify";
		var title= "修改"
		if (item.id == "modify") {//点击修改按钮调用的本方法
			selectedId = Qm.getValuesByName("id");
		} else {//双击数据调用本方法
			selectedId[0] = item.id;
			status = "detail";
			title = "详情"
		}
		if (selectedId.length > 1) {
			$.ligerDialog.alert('不支持批量修改，请只选择一条数据！', "提示");
			return;
		} else if (selectedId.length < 1) {
			$.ligerDialog.alert('请先选择要修改的数据！', "提示");
			return;
		}
		var width = 700;
		var height = 263;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : title, data : {
				"window" : window
			}, content : 'url:app/oa/car/carCost_detail.jsp?status=' + status + '&id=' + selectedId
		});
	}
	function add(item) {
		var width = 700;
		var height = 263;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "新增", data : {
				"window" : window
			}, content : 'url:app/oa/car/carCost_detail.jsp?status=add'
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
					url:"oa/car/cost/delete.do?ids="+selectedId,
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

	function detail() {
		var selectedId = Qm.getValuesByName("id");
		var width = 700;
		var height = 263;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "详情", data : {
				"window" : window
			}, content : 'url:app/oa/car/carCost_detail.jsp?status=detail' + '&id=' + selectedId
		});
	}
</script>
</head>
	<%
		String unitId = SecurityUtil.getSecurityUser().getUnit().getId();
		Org org = (Org) SecurityUtil.getSecurityUser().getDepartment();
	%>
<body>
	<qm:qm pageid="oa_c_cost" script="false" singleSelect="false">
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