<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>角色授权管理</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
        var qmUserConfig = {
            sp_fields:[
                {label:"角色名称",name:"name",compare:"like"}
            ],
            <sec:authorize ifNotGranted="super_administrate">
			<tbar:toolBar type="tbar" code="roleAuthorized"/>,
			</sec:authorize>
			<sec:authorize access="hasRole('super_administrate')">
			tbar:[
                { text:'权限配置', id:'pe_authorized',icon:'settings', click: pe_authorized},
                "-",
                { text:'授权给用户', id:'user_authorized',icon:'setting',click: user_authorized}
            ],
			</sec:authorize>
            listeners: {
                selectionChange: selectionChange
            }
        };
        //行选择改变
        function selectionChange(){
           var count=Qm.getSelectedCount();//行选择个数
           Qm.setTbar({pe_authorized: count==1,user_authorized:count==1});
        }
        //权限配置
        function pe_authorized(){
			top.$.dialog({
				width : 1000,
				height : 450,
				lock:true,
				title:"配置角色拥有的权限",
				content: 'url:rbac/role/initAuthorizedPermission.do?roleId='+Qm.getValueByName("id"),
				ok:function(w){
					var iframe=this.iframe.contentWindow;
                    iframe.authorizedRole();
					return false;
				}
			});
        }  
	
        //用户授权
        function user_authorized(){
			top.$.dialog({
				width:950,
				height:500,
				lock:true,
				title:"授权给用户",
				content: 'url:rbac/role/initAuthorizedUser.do?roleId='+Qm.getValueByName("id"),
				cancel: true,
				ok:function(w){
					var iframe = this.iframe.contentWindow;
                    iframe.authorizedUser();
					return false;
				}
			});
        }
    </script>
	</head>
	<body>
		<sec:authorize access="hasRole('sys_administrate')">
		<qm:qm pageid="sys_role" singleSelect="true" />
		</sec:authorize>
		<sec:authorize ifNotGranted="sys_administrate">
		<qm:qm pageid="sys_org_role" singleSelect="true" />
		</sec:authorize>
		<script type="text/javascript">
		Qm.config.columNum=true;
		</script>
	</body>
</html>
