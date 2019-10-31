$.fn.serializeObject = function()
{
   var o = {};
   var a = this.serializeArray();
   $.each(a, function() {
       if (o[this.name]) {
           if (!o[this.name].push) {
               o[this.name] = [o[this.name]];
           }
           o[this.name].push(this.value || '');
       } else {
           o[this.name] = this.value || '';
       }
   });
   return o;
};

var url;

function add() {
	$('#dlg').dialog('open').dialog('setTitle', '添加用戶');
	$('#fm').form('clear');
	url = '../userSave';
}

function edit() {
	var row = $('#dg').datagrid('getSelections');
	if (row.length==1) {
		$('#dlg').dialog('open').dialog('setTitle', '修改用戶信息');
		$('#fm').form('load', row[0]);
		url = '../userSave';
	}else{
		$.messager.alert('提示',"请选择一行数据！",'info');
	}
}

function save() {
	if ($("#fm").form('validate')) {
		var formdatas = $("#fm").serializeObject();
		$.messager.progress({ 
	        title: '提示', 
	        msg: '正在保存,请稍后...', 
	        text: '' 
	     });
		$.post(url,formdatas,function(res){
			$.messager.progress('close');
			if (!res.success) {
				$.messager.show({
					title : 'Error',
					msg : res.msg
				});
			} else {
				$('#dlg').dialog('close'); // close the dialog
				$('#dg').datagrid('reload'); // reload the user data
			}
			
		});
	}
}
function destroyPlan() {
	var row = $('#dg').datagrid('getSelections');
	if (row.length>0) {
		$.messager.confirm('确认','你确定要删除所选数据?', function(r) {
				if (r) {
					var ids = "";
					$.each(row,function(i){
						if(i=='0'){
							ids = row[i].id;
						}else{
							ids += ',' + row[i].id;
						}
					});
					$.post(
							'../enroll/deletes', 
							{
								"ids" : ids
							}, 
							function(result) {
								if (result.success) {
									$('#dg').datagrid('reload');
								} else {
									$.messager.show({
										title : '错误',
										msg : result.msg
									});
								}
							},
							'json'
					);
				}
		});
	}
}

$(function() {
	// 初始化表格
	$('#dg').datagrid({
		url : '../userList',
		columns : [ [ {
			field : 'ck',
			checkbox : true
		},{
			field : 'id',
			width : '200',
			title : 'userid',
			hidden: true
		}, {
			field : 'userName',
			width : '200',
			title : '用戶名'
		},{
			field : 'passWord',
			width : '50',
			title : '密碼'
		}, {
			field : 'email',
			width : '200',
			title : '郵箱'
		}, {
			field : 'nickName',
			width : '50',
			title : '昵稱'
		}, {
			field : 'regTime',
			width : '50',
			title : '注冊時間'
		} ] ],
		rowStyler: function(index,row){
	
		},
		toolbar : "#toolbar",
		pagination : true,
		rownumbers : true,
		fitColumns : true,
		//singleSelect : true,
		selectOnCheck:true,
		checkOnSelect : true,
		onLoadSuccess : function(){
			
		}
	});
	// 加载combobox
});