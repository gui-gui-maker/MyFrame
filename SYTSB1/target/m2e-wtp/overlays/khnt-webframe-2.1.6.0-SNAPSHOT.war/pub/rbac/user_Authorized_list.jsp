<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户角色授权</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{
			labelWidth: 60
		},
		sp_fields:[
			{name:"name",compare:"like"},
			{name:"org_name",compare:"like"},
			{name:"status",compare:"="}
	    ],
	    <sec:authorize ifNotGranted="super_administrate">
		<tbar:toolBar type="tbar" code="userAuthorizedrole"/>,
		</sec:authorize>
		<sec:authorize access="hasRole('super_administrate')">
		tbar:[
            { text:'设置用户角色', id:'edit',icon:'setting', click: configUserRole}
        ],
		</sec:authorize>
        listeners: {
        	selectionChange :function(rowData,rowIndex){
    			var count = Qm.getSelectedCount();
    			Qm.setTbar({edit : count==1});
        	}
        }
	};
	
	function configUserRole(){
		top.$.dialog({
			width:1100,
			height:600,
			lock:true,
			title:"设置用户【" + Qm.getValueByName("name") + "】拥有的角色",
			content: 'url:rbac/user/initAuthorizedRole.do?userId=' + Qm.getValueByName("id"),
			ok:function(w){
				var iframe=this.iframe.contentWindow;
				iframe.authorizedRole();
				return false;
			},
			cancel:true
		});
	}
	
	function loadUserList(orgId){
		Qm.config.defaultSearchCondition[0].value=orgId;
		Qm.searchData();
	}
</script>
</head>
<body style="p0">
	<qm:qm pageid="user_1" script="false" singleSelect="true">
		<qm:param name="org_id" compare="llike" value="${param.orgid}" />
	</qm:qm>
</body>
</html>
