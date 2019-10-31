<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-list.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Domain管理</title>
<script language="javascript">
var dataGrid;
function getDomains()
{
	$.post("lchart/conf/getDomains.do",{},function(res){
		if(res.success){
			dataGrid.loadData({
				Rows : res.data
			});
		}else{
			$.ligerDialog.error('提示：数据读取失败！');
		}
	});
}
function createGrid() {
	var columns=[
		{ display: 'ID', name: 'ID', hide:true},
		{ display: 'Domain', width: '80%', name: 'MC', type: 'text',required:true,align : 'left',editor: { type: 'text'},maxlength:32},
		{ display: "操作", isSort: false, width: '10%', render: function (rowdata, rowindex, value) {
				var h = "";
				if (!rowdata._editing) {
					h += "<a class='l-a l-icon-del' href='javascript:deleteRow()' title='删除'><span>删除</span></a> ";
				}
				return h;
			}
		}
	];
	dataGrid = $("#dataset").ligerGrid({
		columns: columns,
		enabledEdit: true,
		clickToEdit: false,
		rownumbers: true,                         //是否显示行序号
		frozenRownumbers: false,
		usePager: false,
		width:'100%',
		height:'100%',
		data: {Rows: []}
	});
}
function deleteRow() {
	var data = dataGrid.getSelectedRow();
	if (data.ID) {
		$.ligerDialog.confirm(kui.DEL_MSG, function (yes) {
			if (yes) {
				$.post("lchart/conf/delDomain.do",{'dmid':data.ID},function(res){
					if(res.success){
						dataGrid.deleteSelectedRow();
					}else{
						$.ligerDialog.error('提示：此Domain关联了全局参数、数据集或Chart，不能删除！');
					}
				});
			}
		});
	}
}
function onLoad()
{
	createGrid();
	getDomains();
}
</script>
</head>
<body onload="onLoad()">
    <div id="dataset"></div>
</body>
</html>