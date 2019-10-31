<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>报告领取</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function () {
		$("#formObj").initForm({    //参数设置示例
			toolbar : [
				{
					text : '保存',
					icon : 'save',
					click : save
				},{
					text : '关闭',
					icon : 'cancel',
					click : close
				}   
			],
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
		var fkInspectionInfoId="${param.fkInspectionInfoId}";
		var cid=$("#cid").ligerComboBox().getValue();
		$.ajax({
			type:'POST',
			url:'payment/payInfo/queryAppSign.do?id='+fkInspectionInfoId+'&cid='+cid,
			data:{},
    		dataType:'JSON',
    		success:function(ress){
    			if(ress){
    				top.$.notice("推送成功！",3);
					api.data.window.Qm.refreshGrid();
					api.close();
    			}
    		}
		})
	}
	
	function close(){
		 api.close();
	}
</script>
</head>
<body >
	<form name="formObj" id="formObj" method="post" action="">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>设备选择</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">设备选择：</td>
					<td class="l-t-td-right" colspan="3">
						<input name="cid" id="cid"  ltype="select" validate="{required:true}" 
						ligerui="{value:'',data:<u:dict code="SIGN_DEVICE"/>}"/> 
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>