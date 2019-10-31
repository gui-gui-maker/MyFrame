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
	         {name:"report_number",compare: "like"},
	         {name:"identifier",compare: "like"},
	         {name:"archives_box_id",compare: "like"}
		],
		tbar: [ 
		        {text: '详情', id: 'detail', icon: 'detail', click: detail}, "-", 
		        {text: "新增文件盒", id:'add', icon: "add", click: add},"-",
				{text: "修改文件盒", id:'editBox', icon: "modify", click: editBox},"-",
				{text: '删除', id: 'del', icon: 'delete', click: del
			}],
	   
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
					add: true,
					editBox: count==1,
					del: count>0
				});
			}
		}
	};
	function add(item) {
		top.$.dialog({
 			width : 700, 
 			height : 400, 
 			lock : true, 
 			title : "新增", 
 			data : {"window" : window}, 
			content : 'url:app/archives/archives_box_detail.jsp?status=add'
 		}); 
	}

	function editBox(item) {
		top.$.dialog({
 			width : 700, 
 			height : 400, 
 			lock : true, 
 			title : "修改", 
 			data : {"window" : window}, 
			content : 'url:app/archives/archives_box_detail.jsp?status=modify&id='+Qm.getValueByName("id")
 		}); 
	}
	function detail() {
		var selectedId = Qm.getValueByName("id");
		var ids = Qm.getValueByName("report_number_id");
		if (selectedId.length < 1) {
			$.ligerDialog.alert('请先选择要查看的数据！', "提示");
			return;
		}
		str=ids.replace(/[,]/g,"','");
		idss="('"+str+"')";
		var width = 900;
		var height = 400;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "详情", data : {
				"window" : window
			}, content : 'url:app/archives/archives_box_list2.jsp?status=detail' + '&id=' + selectedId
			+ '&report_number_id=' + idss
		});
	}
	function del() {
// 		$.del(kFrameConfig.DEL_MSG, "archives/borrow/delete.do", {
// 			"ids": Qm.getValuesByName("id").toString()
// 		});
		var selectedId = Qm.getValueByName("id");
		$.ligerDialog.confirm(kui.DEL_MSG, function (yes) {
			if(yes){
				$.ajax({
					url:"archives/box/del.do?id="+selectedId,
					type:"post",
					async:false,
					success:function(data){
						if(data.success){
							top.$.notice(data.msg,3);
							Qm.refreshGrid();

						}else{
							$.ligerDialog.error(data.msg);
						}
					}
				});
			}
		});
	}
</script>
</head>
<body>

	<qm:qm pageid="TJY2_ARCHIVES_BOX2" singleSelect="true"></qm:qm>
</body>
</html>