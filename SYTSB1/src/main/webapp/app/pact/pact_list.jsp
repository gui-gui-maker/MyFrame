<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>设备信息列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">


	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields : [
			     		{
			     			name : "pact_num",
			     			compare : "like",
			     			value : ""
			     		},{
			     			name : "pact_name",
			     			compare : "like",
			     			value : ""
			     		}
			     		
			     		],
		
		tbar : [
				{
					text : '查看',
					id : 'detail',
					icon : 'detail',
					click : detail
				}
				
				, "-",
				{
					text : '新增',
					id : 'add',
					icon : 'add',
					click : add
				}, "-",

				{
					text : '修改',
					id : 'modify',
					icon : 'modify',
					click : modify
				},
				{
					text : '删除',
					id : 'del',
					icon : 'del',
					click : del
				}
				],
	
	
		listeners : {
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				Qm.setTbar({
					//add : count == 1,
					detail : count == 1,
					modify : count == 1,
					del : count >0
				});
			}
		}
	};
	
	function add() {
		top.$.dialog({
			width : 800,
			height : 600,
			lock : true,
			title : "添加合同",
			content : 'url:app/pact/pact_detail.jsp?status=add',
			data : {
				"window" : window
			}
		});
		
	}
	function detail() {
		top.$.dialog({
			width : 800,
			height : 600,
			lock : true,
			title : "合同详情",
			content : 'url:app/pact/pact_detail.jsp?status=detail&id='+Qm.getValueByName("id"),
			data : {
				"window" : window
			}
		});
	}

	function modify() {
		top.$.dialog({
			width : 800,
			height : 600,
			lock : true,
			title : "修改合同",
			content : 'url:app/pact/pact_detail.jsp?status=modify&id='+Qm.getValueByName("id"),
			data : {
				"window" : window
			}
		});
	}
	function del() {
		$.del("确定删除?", "pact/action/del.do", {
			"ids" : Qm.getValuesByName("id").toString()
		});
	}

	

	function submitAction() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="pactInfo" singleSelect="false">
	</qm:qm>
	
</body>
</html>
