<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>详情页面</title>
<%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript">
	var g;
	$(function () {
		initGrid();
		$("#formObj").initForm({    //参数设置示例
			toolbar : [ {
				text : '保存',
				id : 'save',
				icon : 'save',
				click : save
			}, 
			{
				text : '关闭',
				id : 'close',
				icon : 'cancel',
				click : function close(){	
						 	api.close();
						}
			}],
			success: function (response) {
				if(response.success){
					top.$.notice("保存成功！",3);
					api.data.window.Qm.refreshGrid();
					api.close();
				}
				else{
					$.ligerDialog.error("操作失败！<br/>" + response.msg);
				}
			}
		});
	});
	function save(){
		
		//验证表单数据
		if($('#formObj').validate().form()){
			
			var formData = $("#formObj").getValues();
	        if(!validateGrid(g)){
	        	return false;
			}
	        if(g.getData().length==0){
	        	top.$.dialog.notice({content:'至少添加一条二维码！'});
	        	return false;
	        }
	        formData["codes"] = g.getData();
	        var  jsonString = $.ligerui.toJSON(formData);
	        $("body").mask("正在保存数据，请稍后！");
	        $.ajax({
	            url: "lib/qrcode/save.do",
	            type: "POST",
	            datatype: "json",
	            contentType: "application/json; charset=utf-8",
	           	data: $.ligerui.toJSON(formData),
	            success: function (data, stats) {
	            	$("body").unmask();
	                if (data["success"]) {
	                	if(api.data.window.Qm){
	                		api.data.window.Qm.refreshGrid();
	                	}
	                    top.$.dialog.notice({content:'保存成功'});
	                    api.close();
	                }else{
	                	$.ligerDialog.error('提示：' + data.msg);
	                	$("#save").removeAttr("disabled");
	                }
	            },
	            error: function (data,stats) {
	            	$("body").unmask();
	                $.ligerDialog.error('提示：' + data.msg);
	                $("#save").removeAttr("disabled");
	            }
	        });
		}
	}
	//初始化表格
	function initGrid() {
		g = $("#grid").ligerGrid({
			columns : [
			           {display: "<a class='l-a iconfont l-icon-add' href='javascript:void(0);' onclick='javascript:addCode()' title='增加'><span>增加</span></a>", 
						isSort: false, 
						minWidth:30,
						width: '5%',
						height:'5%', 
						render: function (rowdata, index, value ) {
									var h = "";
									if (!rowdata._editing) {
										h += "<a class='l-a l-icon-del' href='javascript:delCode(g,"+index+")' title='删除'><span>删除</span></a> ";
									}
									return h;
								}
			           },
			           {display : '二维码', name : 'qrcode',width : '80%',required:true,editor: { type:'text'}}
			           ],
 			enabledEdit: true,
			rownumbers : true,
			height : "320",
			width : "98%",
			frozenRownumbers : false,
			usePager : false,
			data : {Rows : []},
			onSelectRow : function(rowdata, rowid, rowobj) {

			}
		});
	}
	function addCode() {
		g.addRow({qrcode:''});
	}
	function delCode(obj, index) {
		var data = obj.getSelectedRow();
		var dataId = data.id;
		$.ligerDialog.confirm("确定要移除吗？", function(yes) {
			if (yes) {
				obj.deleteRow(index);
			}
		});
	}
</script>
</head>
<body>
	<form id="formObj" action="lib/qrcode/save.do">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>选择二维码类别</div>
			</legend>
			<table cellpadding="3" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">二维码类别：</td>
					<td class="l-t-td-right" colspan="3"><input name="type"
						id="type" type="text" ltype="select" validate="{required:true}"
						ligerui="{initValue:'book',data:<u:dict code='lib_qrcode_type'/>}" /></td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="l-fieldset" style="height: 500px">
			<legend class="l-legend">二维码编辑</legend>
			<div id="grid" style="height: 400px;"></div>
		</fieldset>
	</form>
</body>
</html>
