<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>机构群组信息</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/common/common.js"></script>
<script type="text/javascript">
	$(function() {
		$("#gu_list").ligerGrid({
			data: {Rows:[]},
			height: 225,
			usePager: false,
			enabledSort: false,
			enabledEdit: true,
			columns: [{name:"memberId", hide: true},{
				display:"姓名", name:"memberName", width:150
			},{
				display:"是否负责人", name:"leader", width:100, editor:{type:"select",data:[{id:"1",text:"是"},{id:"0",text:"否"}],p:{emptyOption:false}},render:function(row){
					return row.leader?"是":"否";
				}
			},{
				display:"<a class='l-a l-icon-add' href='javascript:addPerson();'></a>", name:"opr", width:40, render: function(rowdata){
					return "<a class='l-a l-icon-del' href='javascript:$(\"#gu_list\").ligerGetGridManager().deleteSelectedRow();'></a>";
				}
			}]
		});
		
		$("#form1").initForm({
			success : function(resp) {
				if (resp.success) {
					top.$.dialog.notice({
						content : '保存成功!'
					});
					var status = "${param.status}";
					api.data.window.refresh(resp.data, status);
					api.close();
				} else {
					$.ligerDialog.error('保存失败\<br/>' + resp.msg);
				}
			},
			getSuccess : function(resp) {
				if (resp.data.users)
					$("#gu_list").ligerGetGridManager().loadData({Rows:resp.data.users});
					
			},
			toolbar: [{
				icon: "save",
				text: "保存",
				click: saveGroups
			},{
				icon: "cancel",
				text:"关闭",
				click: function(){
					api.close();
				}
			}]
		});
	});
	
	function saveGroups(){
		if(!$("#form1").validate().form()){
			reutrn;
		}
		var fvs = $("#form1").getValues();
		fvs.parent = {id:"${param.pid}"};
		var ups = $("#gu_list").ligerGetGridManager().getData();
		fvs.users = ups;
		$("body").mask("正在处理，请稍候...");
		$.ajax({
			url:"rbac/groups/save_group.do",
			data: $.ligerui.toJSON(fvs),
			dataType: "json",
			type: "post",
            contentType: "application/json;charset=utf-8",
			success: function(resp){
				if(resp.success){
					top.$.notice("操作成功！",3);
					if(api.data.callback) api.data.callback(resp.data);
					api.close();
				}else{
					$.ligerDialog.error("操作失败！<br/>请稍后重试或者联系技术支持人员。");
					$("body").unmask();
				}
			},
            error: function (data) {
                $.ligerDialog.error('操作失败！<br/>请稍后重试或者联系技术支持人员。');
        		$("body").unmask();
            }
		});
	}
	
	function addPerson(){
		chooseSystemUser({
			range: "sa",
			multiple: true,
			callback: function(result){
				$.each(result.data,function(){
					$("#gu_list").ligerGetGridManager().addRow({memberId:this.id,memberName:this.name,leader:false});
				});
			}
		});
	}
</script>
</head>
<body>
	<form name="form1" id="form1" method="post" action="rbac/groups/save.do"
		getAction="rbac/groups/detail.do?id=${param.id}">
		<input type="hidden" name="id" /> 
		<table class="l-detail-table">
			<tr>
				<td class="l-t-td-left" style="width:60px;">编号：</td>
				<td class="l-t-td-right"><input name="code" type="text" ltype='text' validate="{required:true,maxlength:32}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">名称：</td>
				<td class="l-t-td-right"><input name="name" type="text" ltype='text' validate="{required:true,maxlength:50}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">简介：</td>
				<td class="l-t-td-right"><textarea name="remark" rows="3" validate="{required:false,maxlength:500}"></textarea></td>
			</tr>
		</table>
		<fieldset class="l-fieldset">
			<legend class="l-legend"><div>群组成员</div></legend>
			<div id="gu_list"></div>	
		</fieldset>
	</form>
</body>
</html>