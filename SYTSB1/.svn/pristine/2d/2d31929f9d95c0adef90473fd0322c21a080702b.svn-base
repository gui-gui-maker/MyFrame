<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>消息管理</title>
<%@include file="/k/kui-base-list.jsp"%>
<script test="text/javascript">
	var qmUserConfig = {
		sp_defaults : {
			labelWidth : 70
		},
		sp_fields : [
		 	{name:"name",compare:"like"}
		],
		<sec:authorize ifNotGranted="super_administrate">
			<tbar:toolBar type="tbar">
			</tbar:toolBar>,
		</sec:authorize>
		<sec:authorize access="hasRole('super_administrate')">
			tbar:[
             { text:'审核', id:'check',icon:'check', click: check},
             "-",
             { text:'绑定', id:'bind',icon:'lock', click: bind},
             "-",
             { text:'解除绑定', id:'unbind',icon:'userUnlock', click: unbind}
        	],
		</sec:authorize>
		listeners : {
			rowDblClick : function(rowData, rowIndex) {
				detail();
			},
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					check : count == 1,
					bind : count == 1,
					unbind : count == 1 
				});
				if(Qm.getValueByName("status")=="未审核"){
					Qm.setTbar({
						check : true,
						bind : false,
						unbind : false 
					});
				}
				if(Qm.getValueByName("status")=="已绑定"){
					Qm.setTbar({
						check : false,
						bind : false,
						unbind : true 
					});
				}
				if(Qm.getValueByName("status")=="未绑定"){
					Qm.setTbar({
						check : false,
						bind : true,
						unbind : false 
					});
				}
			}
		}
	};
	function check(){
		$.ligerDialog.confirm("确定审核通过？",function(yes){
			if(yes){
				$.getJSON("rbac/userDevice/setDeviceStatus.do",{id:Qm.getValueByName("id"),status:"0"},function(data){
					if(data.success){
						top.$.notice('审核成功！');
						Qm.refreshGrid();
					}else{
						top.$.notice('操作失败！');
					}
					
				})
			}
		})
	}
	function bind(){
		$.ligerDialog.confirm("确定绑定该设备 ？",function(yes){
			if(yes){
				$.getJSON("rbac/userDevice/setDeviceStatus.do",{id:Qm.getValueByName("id"),status:"1"},function(data){
					if(data.success){
						top.$.notice('绑定成功！');
						Qm.refreshGrid();
					}else{
						top.$.notice(data.msg);
					}
				})
			}
		})
	}
	function unbind(){
		$.ligerDialog.confirm("确定解除设备绑定？",function(yes){
			if(yes){
				$.getJSON("rbac/userDevice/setDeviceStatus.do",{id:Qm.getValueByName("id"),status:"2"},function(data){
					if(data.success){
						top.$.notice('解除成功！');
						Qm.refreshGrid();
					}else{
						top.$.notice('操作失败！');
					}
					
				})
			}
		})
	}
</script>
</head>
<body>
	<qm:qm pageid="sysUserDevice" singleSelect="true"></qm:qm>
</body>
</html>