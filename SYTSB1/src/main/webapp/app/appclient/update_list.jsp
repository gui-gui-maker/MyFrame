<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://khnt.com/tags/ui" prefix="u"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="/k/kui-base-list.jsp" %>
<head>
<title>app升级列表信息</title>
  <script type="text/javascript">
	var qmUserConfig = {
		tbar: [
			{text: '详情',id: 'detail',icon: 'detail',click: detail}, "-", 
		    {text: '新增',id: 'add',icon: 'add',click: add}, "-", 
		    {text: '修改',id: 'modify',icon: 'modify',click: modify}, "-", 
		    {text: '删除',id: 'del',icon: 'del',click: del}
		],
		listeners: {
			rowClick: function (rowData, rowIndex) {},
			rowDblClick: function (rowData, rowIndex) {detail();},
			selectionChange: function (rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail: count == 1,
					modify: count == 1,
					del: count > 0
				});
			},
			rowAttrRender: function(rowData, rowid) {}
		}
	};
	//添加
	function add() {
		 top.$.dialog({
    		width: 800,
    		height: 650,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "新增",
			content: 'url:app/appclient/update_detail.jsp?pageStatus=add'
		}); 
	}
	//修改
	function modify() {
		var id = Qm.getValueByName("id");
		 top.$.dialog({
			width: 800,
			height: 650,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:app/appclient/update_detail.jsp?id=' + id + '&pageStatus=modify'
		}); 
	}
	//详细
	function detail() {
		var id = Qm.getValueByName("id");
		winOpen({
			width: 800,
			height: 650,
			lock : true,
			parent: api,
			data: {
				window: window
			},
			title: "城市详情",
			content: 'url:app/appclient/update_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	//删除城市
	function del() {
		 $.del(kFrameConfig.DEL_MSG, "appclient/update/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		}); 
	}
</script>
</head>
<body>
    <qm:qm pageid="sys_app_update" defaultOrder="[{field:\"cdate\",direction:\"desc\"}]"></qm:qm>
</body>
</html>
