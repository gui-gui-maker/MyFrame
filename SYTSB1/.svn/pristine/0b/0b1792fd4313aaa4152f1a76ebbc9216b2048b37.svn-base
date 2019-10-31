<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Domain参数管理</title>
<script language="javascript">
var dataGrid;
$(function() {
	$("#formObj").initForm({
		toolbar:[{id:'save',text:"保存",icon:'save',click:function(){
			var data = dataGrid.getData();
			$.post("lchart/conf/saveDomainParam.do",{'dmid':"${param.domainId}",'data':$.ligerui.toJSON(data)},function(res){
				if(res.success){
					top.$.notice("保存成功！",3);
					api.data.window.getDomainParams();
					api.close();
				}else{
					$.ligerDialog.error('提示：数据保存失败！');
				}
			});
			}
		},{id:'close',icon:'cancel',text:'关闭',click:function(){api.close()}}]
	});
});

function getParams()
{
	$.post("lchart/conf/getParams.do",{'dmid':"${param.domainId}"},function(res){
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
		{ display: "<a class='l-a l-icon-add' href='javascript:void(0);' onclick='addNewRow()' title='增加'><span>增加</span></a>", isSort: false, width: '10%', render: function (rowdata, rowindex, value) {
				var h = "";
				if (!rowdata._editing) {
					h += "<a class='l-a l-icon-del' href='javascript:deleteRow()' title='删除'><span>删除</span></a> ";
				}
				return h;
			}
		},
		{ display: 'ID', name: 'ID', hide:true},
		{ display: 'FK_DM_ID', name: 'FK_DM_ID', hide:true},
		{ display: '参数名称', width: '20%', name: 'MC', type: 'text',required:true,align : 'left',editor: { type: 'text'},maxlength:32},
		{ display: '默认值', width: '60%', name: 'MRZ', type: 'text',required:true,align : 'left',editor: { type: 'textarea',height:100},maxlength:2000}
		
	];
	
	dataGrid = $("#dataset").ligerGrid({
		columns: columns,
		enabledEdit: true,
		clickToEdit: true,
		rownumbers: true,                         //是否显示行序号
		frozenRownumbers: false,
		usePager: false,
		height:$(window).height()-100,
		widht:'100%',
		data: {Rows: []}
	});
}

function addNewRow(name) {
	dataGrid.addEditRow({ID:'',FK_DM_ID:"${param.domainId}"}); //添加一行
}

function deleteRow(name) {
	var data = dataGrid.getSelectedRow();
	if (data.ID) {
		$.ligerDialog.confirm(kui.DEL_MSG, function (yes) {
			if (yes) {
				dataGrid.deleteSelectedRow();
			}
		});
	} else {
		dataGrid.deleteSelectedRow();
	}
}

function onLoad()
{
	createGrid();
	getParams();
}
</script>
</head>

<body onload="onLoad()">
	<form id="formObj" action="" getAction="">
	<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>Domain参数设计</div>
			</legend>
			<div id="dataset"></div>
		</fieldset>
    </form>
</body>
</html>