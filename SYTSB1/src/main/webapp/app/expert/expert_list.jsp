<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>设备信息列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_fields : [{
			name : "expert_type_name",
			compare : "like"
		},
		{group:[
				{name:"create_date", id:"create_date", compare:">="},
				{label:"到", name:"create_date", id:"create_date1", compare:"<=", labelAlign:"center", labelWidth:20}
			]}],
		tbar : [
		{
			text : '查看',
			id : 'detail',
			icon : 'detail',
			click : detail
		},
		"-",
		{
			text : '机选专家',
			id : 'add',
			icon : 'add',
			click : add
		}
		 , "-",{
			text : '打印会议纪要表',
			id : 'printW',
			icon : 'print',
			click : printW
		} ],
		listeners : {
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				Qm.setTbar({
					modify : count == 1,
					detail : count == 1,
					del : count > 0,
					printW:count==1
				});
			}
		}
	};
 
	function add() {

		top.$.dialog({
			width : 1000,
			height : 600,
			lock : true,
			title : "机选专家",
			content : 'url:app/expert/expert_type_choose.jsp?status=add',
			data : {
				"window" : window
			}
		});
		
	}

	
	function randomExport(id){
		top.$.dialog({
			width : 1000,
			height : 900,
			lock : true,
			title : "机选专家",
			content : 'url:app/expert/expert_choosed_list.jsp?status=add&id='+id,
			data : {
				"window" : window
			},close:function(){
				submitAction();
			}
		});
	}
	
	function detail(){
		top.$.dialog({
			width : 1000,
			height : 600,
			lock : true,
			title : "机选专家详情",
			content : 'url:app/expert/expert_type_choose.jsp?status=detail&id='+
						Qm.getValueByName("id"),
			data : {
				"window" : window
			}
		});
	}
	
	function printW(){
		
		var emp_name = Qm.getValueByName("emp_name");
		top.$.dialog({
			width : 1000,
			height : 900,
			lock : true,
			title : "打印会议纪要表",
			content : 'url:app/expert/expert_doc.jsp?status=detail&id='+
						Qm.getValueByName("id"),
			data : {
				"window" : window,"emp_name":emp_name
			}
		});
	}
	
	function del() {
		$.del("确定删除?", "device/basic/del.do", {
			"ids" : Qm.getValuesByName("id").toString()
		});
	}
	


	function loadGridData(nodeId, unitId, url) {

		
		
		
/* 
		if (nodeId != null) {

			if (nodeId.substring(1, 4) == '000') {

				Qm.setCondition([ {
					name : "device_sort",
					compare : "like",
					value : nodeId.substring(0, 1)+"%"
				} ]);
			}else if(nodeId.substring(2, 4) == '00'){
				Qm.setCondition([ {
					name : "device_sort",
					compare : "=",
					value : nodeId
				} ]);
				
			} else {

				Qm.setCondition([ {
					name : "device_sort_code",
					compare : "=",
					value : nodeId
				} ]);
			}

		} else {
			Qm.setCondition([]);
		}
		Qm.searchData(); */
	}
	


	function submitAction() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="tjy2_expert_list" singleSelect="false">
	</qm:qm>
</body>
</html>
