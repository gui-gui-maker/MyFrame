<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>工作任务</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
	    sp_defaults: {labelWidth:60},
		sp_fields : [
    		{name : "title", compare : "like", columnWidth : 0.3}, 
            {name : "work_type", compare : "=", columnWidth : 0.2},
    		{name : "creater_name", compare : "like", columnWidth : 0.2},
    		{columnWidth : 0.3,group : [
    		    {name : "creater_time", compare : ">="}, 
    	        {name : "creater_time", compare : "<=",label : "到", labelAlign:"center", labelWidth:20}
    		]}
		],
		tbar : [ {
			text : '详情 ',id : 'detail',icon : 'detail',
			click : function() {
				detail({
					title : Qm.getValueByName("title"),
					id : Qm.getValueByName("id")
				});
			}
		}],
		listeners : {
			rowDblClick : function(rowData, rowIndex) {
				detail(rowData);
			},
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择数
				Qm.setTbar({
					detail : count == 1,
					del : count >= 1
				});
			},
			rowAttrRender : function(item, rowid) {
				if (item.status == '逾期')
					return "style='color:red'";
			}
		}
	};

	function submitAction() {
		Qm.refreshGrid();
	}

	function detail(rowData) {
		openWorktask({
			width : 1000,
			height : 600,
			id : rowData.id,
			title : rowData.title,
			close : function(){
				Qm.refreshGrid();
			},
			data : {
				"window" : window,
				"flowname" : rowData.flowname
			}
		});
	}
</script>
</head>
<body>
	<qm:qm pageid="worktask" singleSelect="true">
		<qm:attr name="status" value="${param.status}" />
	</qm:qm>
</body>
</html>
