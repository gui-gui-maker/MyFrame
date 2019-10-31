<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>

<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>

<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields:[
	         {name:"proposer",compare: "like"},
	         {name:"document_id",compare: "like"},
	         {group: [
	  				{name: "print_time", compare: ">="},
	  				{label: "到", name: "print_time", compare: "<=", labelAlign: "center", labelWidth:20}
	  		 ]}
	    ],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}/*, "-", {
			text: '修改', id: 'edit', icon: 'modify', click: edit
		}*/],

		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail: count==1,
					edit: false,
					del: count>0
						
				});
			}
		}
	};
	function detail() {
		var id = Qm.getValueByName("application_id");
		top.$.dialog({
			width: 900,
			height: 450,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/archives/archives_print_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function del() {
		$.del(kFrameConfig.DEL_MSG, "quality/updateFile1/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
</script>
</head>
<body>

	<qm:qm pageid="TJY2_ARCHIVES_DYRZ" singleSelect="true"></qm:qm>
</body>
</html>