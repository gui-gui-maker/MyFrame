<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>数据集管理</title>
<script language="javascript">
var dataGrid;
var dataTypes = [
	{id:'sql',text:'sql'},
	{id:'class',text:'class'}
];
$(function() {
	$("#formObj").initForm({
		toolbar:[{id:'save',text:"保存",icon:'save',click:function(){
			var data = dataGrid.getData();
			$.post("lchart/conf/saveDataset.do",{'dmid':"${param.domainId}",'data':$.ligerui.toJSON(data)},function(res){
				if(res.success){
					top.$.notice("保存成功！",3);
					api.data.window.getDataset();
					api.close();
				}else{
					$.ligerDialog.error('提示：数据保存失败！');
				}
			});
			}
		},{id:'close',text:'关闭',icon:'cancel',click:function(){api.close()}}]
	});
});

function getDatasets()
{
	$.post("lchart/conf/getManageDatasets.do",{'dmid':"${param.domainId}"},function(res){
		if(res.success){
			dataGrid.loadData({
				Rows : res.data
			});
		}else{
			$.ligerDialog.error('提示：数据读取失败！');
		}
	});
}

function render(value,data){
	for (var i in data) {
		if (data[i]["id"] == value)
		{
			return data[i]['text'];
		}
	}
	return value;
}


function createGrid() {
	var columns=[
		{ display: "<a class='l-a l-icon-add' href='javascript:void(0);' onclick='addNewRow()' title='增加'><span>增加</span></a>", isSort: false, width: '4%', render: function (rowdata, rowindex, value) {
				var h = "";
				if (!rowdata._editing) {
					h += "<a class='l-a l-icon-del' href='javascript:deleteRow()' title='删除'><span>删除</span></a> ";
				}
				return h;
			}
		},
		{ display: 'ID', name: 'ID', hide:true},
		{ display: 'FK_DM_ID', name: 'FK_DM_ID', hide:true},
		{ display: '数据集名称', width: '8%', name: 'MC', type: 'text',required:true,align : 'left',editor: { type: 'text'},maxlength:32},
		{ display: '数据集类型', width: '6%', name: 'LX', type: 'text',required:true,align : 'center',editor: { type: 'select', data: dataTypes ,ext:{emptyOption:false}},
			render: function (item) {
				return render(item["LX"],dataTypes);
			}
		},
		{ display: 'SQL/Class',width: '50%', name: 'SQLSTR', required:true,align : 'left',editor: { type: 'textarea',height:'250'},maxlength:4000,render:function(data){
			if(data.SQLSTR==undefined){
				data.SQLSTR="";
			}
			return "<div title='"+data.SQLSTR+"'>"+data.SQLSTR+"</div>"
		}},
		{ display: '可用属性(SQL字段名,用逗号隔开)', width: '25%', name: 'KYSX', type: 'text',required:true,align : 'left',/*editor: { type: 'text'},*/maxlength:4000}
	];
	
	dataGrid = $("#dataset").ligerGrid({
		columns: columns,
		enabledEdit: true,
		clickToEdit: true,
		rownumbers: true,                         //是否显示行序号
		frozenRownumbers: false,
		usePager: false,
		height:$(window).height()-100,
		width:$(window).width()-5,
		data: {Rows: []}
	});
}

function addNewRow(name) {
	dataGrid.addEditRow({ID:'',FK_DM_ID:"${param.domainId}",LX:"sql"}); //添加一行
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
	getDatasets();
}
</script>
</head>

<body onload="onLoad()">
	<form id="formObj" action="" getAction="">
    <div id="dataset"></div>
    　<font color="#FF0000">注：若SQL中要使用全局参数，请使用 $P{参数名称} 格式。</font>
    </form>
</body>
</html>