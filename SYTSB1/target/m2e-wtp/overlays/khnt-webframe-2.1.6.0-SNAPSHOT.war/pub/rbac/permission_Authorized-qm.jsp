<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>权限授权管理</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
        var qmUserConfig = {
       		sp_defaults : {
       			labelWidth : 60,
       			width : 130
       		}, 
        	sp_fields:[
	            {label:"权限代码",name:"p_code",compare:"like",value:""},
	            {label:"权限名称",name:"p_name",compare:"like",value:""}
         	],
			tbar:[
                { text:'资源配置', id:'re_authorized',icon:'settings', click: re_authorized},
                "-",
                { text:'授权给角色', id:'role_authorized',icon:'setting',click: role_authorized}
            ],
            listeners: {
                selectionChange :function(rowData,rowIndex){
                	var count = Qm.getSelectedCount();//行选择个数
                	Qm.setTbar({re_authorized:count==1,role_authorized:count==1});
				}
            }
        };
        
        //资源授权
        function re_authorized(){
			top.$.dialog({
				width: 450,
				height: $(top).height(),
				lock:true,
				title:"菜单资源配置",
				content: 'url:pub/rbac/permission_Authorized_resource.jsp?perId='+Qm.getValueByName("id"),
				ok:function(w){
					var iframe=this.iframe.contentWindow;
					iframe.authorizedResource();
					return false;
				},
				cancel:true
			});
        }  
		       
        //所属角色授权
        function role_authorized(){
			top.$.dialog({
				width: 1000,
				height: 450,
				lock:true,
				title:"所属角色配置",
				content: 'url:rbac/permission/initAuthorizedRole.do?perId='+Qm.getValueByName("id"),
				ok:function(w){
					var iframe=this.iframe.contentWindow;
                    iframe.authorizedRole();
					return false;
				},
				cancel:true
			});
        }
    </script>
	</head>
	<body>
		<qm:qm pageid="sys_01" script="false" singleSelect="true"></qm:qm>
	</body>
</html>
