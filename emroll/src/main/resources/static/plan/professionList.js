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

function addPlan() {
	var row = $('#dg').datagrid('getSelections');
	if(row.length==1){
		$('#dlg').dialog('open').dialog('setTitle', '拆分专业');
		$('#fm').form('clear');
		$('#fm').form('load', row[0]);
		//计划id
		$("#id").textbox("setValue","");
		//省编专业代号
		$("#sbzydh").textbox("setValue","");
		//专业备注
		$("#zybz").textbox("setValue","");
		//招生计划数
		$("#zsjhs").textbox("setValue","");
		url = '../enroll/saveOrUpdate';
	}else{
		$.messager.alert('提示',"请选择一行数据！",'info');
	}
}

function editPlan() {
	var row = $('#dg').datagrid('getSelections');
	if (row.length==1) {
		$('#dlg').dialog('open').dialog('setTitle', '修改计划');
		$('#fm').form('load', row[0]);
		// 专业名称
		$("#profession-name").html(row[0].zszymc);
		
		url = '../enroll/saveOrUpdate';
	}else{
		$.messager.alert('提示',"请选择一行数据！",'info');
	}
}

function savePlan() {
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
		url : '../enroll/findByCondition',
		columns : [ [ {
			field : 'ck',
			checkbox : true
		},{
			field : 'id',
			width : '200',
			title : '计划ID',
			hidden: true
		}, {
			field : 'yxmc',
			width : '200',
			title : '院校名称'
		},{
			field : 'sbzydh',
			width : '50',
			title : '代码'
		}, {
			field : 'zszymc',
			width : '200',
			title : '专业'
		}, {
			field : 'pcdm',
			width : '50',
			title : '批次'
		}, {
			field : 'kldm',
			width : '50',
			title : '科类'
		}, {
			field : 'ccdm',
			width : '50',
			title : '层次'
		}, {
			field : 'jhxzdm',
			width : '50',
			title : '计划性质'
		}, {
			field : 'jhlbdm',
			width : '50',
			title : '计划类别'
		}, {
			field : 'zylbdm',
			width : '50',
			title : '专业类别'
		}, {
			field : 'zklxdm',
			width : '50',
			title : '报考类型'
		}, {
			field : 'zsjhs',
			width : '50',
			title : '计划'
		} ] ],
		rowStyler: function(index,row){
			alert(row.last_update_time);
			alert(typeof row.last_update_time);
			/*if (row.last_update_time && new Date() - row.last_update_time < 80){
				return 'background-color:#6293BB;color:#fff;'; // return inline style
				// the function can return predefined css class and inline style
				// return {class:'r1', style:{'color:#fff'}};	
			}*/
		},
		toolbar : "#toolbar",
		pagination : true,
		rownumbers : true,
		fitColumns : true,
		//singleSelect : true,
		selectOnCheck:true,
		checkOnSelect : true,
		onLoadSuccess : function(){
			var rows = $("#dg").datagrid("getRows");
			if($("#s-yxdh").textbox("getValue")!=""){
				$("#college-name").html(rows[0].yxmc);
			}
		}
	});
	// 加载combobox
	// 批次
	$('#pcdm').combobox({
		valueField : 'id',
		textField : 'text',
		data : pcdm
	});
	// 科类
	$('#kldm').combobox({
		valueField : 'id',
		textField : 'text',
		data : kldm
	});
	// 层次
	$('#ccdm').combobox({
		valueField : 'id',
		textField : 'text',
		data : ccdm
	});
	// 计划性质
	$('#jhxzdm').combobox({
		valueField : 'id',
		textField : 'text',
		data : jhxzdm
	});
	// 计划类别
	$('#jhlbdm').combobox({
		valueField : 'id',
		textField : 'text',
		data : jhlbdm
	});
	// 专业类别
	$('#zylbdm').combobox({
		valueField : 'id',
		textField : 'text',
		data : zylbdm
	});
	// 招考类别
	$('#zklxdm').combobox({
		valueField : 'id',
		textField : 'text',
		data : zklxdm
	});
	// 招考类别
	$('#xzdm').combobox({
		valueField : 'id',
		textField : 'text',
		data : xzdm
	});
	// 院校代码查询
	$('#s-yxdh').textbox({
		inputEvents : $.extend(
				{},
				$.fn.textbox.defaults.inputEvents,
				{
					keyup : function(event) {
						if (event.keyCode == 13) {
							$.post(
									"../enroll/listCatalog",
									{"yxdh" : this.value},
									function(res) {
										if (res.success) {
											$("#catalog").empty();
											var data = res.data;
											$.each(data,function(i) {
												var code = data[i][0].match(/[0]*$/);
												var space = ('0000' == code ? 0 : '000' == code ? 1 : '00' == code ? 2 : '0' == code ? 3 : 4);
													
												$("#catalog").append("<div class='fitem catalog'><span class='catalog-left'>"+ data[i][0]+ "</span><span class='catalog-right' style='padding-left:"+(space*30)+"px;'>"+data[i][1]+ "</span></div>");
											});
											$("div.catalog").click(
												function() {
													var dyml = $(this).find("span.catalog-left").html();
													$('#dg').datagrid("load",{
														"yxdh" : $('#s-yxdh').textbox('getValue'),
														"dyml" : dyml
													});
											});
										} else {
											$.messager.show({ // show error message
												title : 'Error',
												msg : res.msg
											});
										}
							});
							$('#dg').datagrid("load", {
								"yxdh" : this.value
							});
						}
					}
			})
	});
});