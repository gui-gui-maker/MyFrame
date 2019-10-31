<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>角色管理</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
        var qmUserConfig = {
            sp_fields:[
				{name:"name", compare:"like", columnWidth:0.33},
				{name:"remark", compare:"like", columnWidth:0.33},
				{name:"isdefault", compare:"=", columnWidth:0.33}
            ],
        	<sec:authorize ifNotGranted="super_administrate">
			<tbar:toolBar type="tbar" code="role_list" />,
			</sec:authorize>
			<sec:authorize access="hasRole('super_administrate')">
			tbar:[
                { text:'增加 ', id:'add',icon:'add', click: add},
                "-",
                { text:'修改 ', id:'modify',icon:'modify', click:modify},
                "-",
                { text:'删除 ', id:'del',icon:'delete', click:del },
                "-",
                { text:'权限配置', id:'pe_authorized',icon:'settings', click: pe_authorized},
                "-",
                { text:'授权给用户', id:'user_authorized',icon:'setting',click: user_authorized},
                "-",
                { text:'设置为默认角色', id:'set_default',icon:'setting',click: set_default},
                "-",
                { text:'取消默认角色', id:'remove_default',icon:'setting',click: removeDefault}
            ],
			</sec:authorize>
            listeners: {
                selectionChange :function(rowData,rowIndex){
                	var count = Qm.getSelectedCount();
                	var vid = Qm.getValueByName("vid");
                    Qm.setTbar({
                    	modify:count==1,
                    	del:count>0,
                    	pe_authorized: count==1,
                    	user_authorized: count==1,
                    	set_default: count==1 && $.kh.isNull(vid),
                    	remove_default: count==1 && !$.kh.isNull(vid)});
                }
            }
        };
       
        //权限配置
        function pe_authorized(){
			top.$.dialog({
				width : 1000,
				height : 800,
				lock:true,
				title:"配置角色拥有的权限",
				content: 'url:rbac/role/initAuthorizedPermission.do?roleId='+Qm.getValueByName("id"),
				ok:function(w){
					var iframe=this.iframe.contentWindow;
                    iframe.authorizedRole();
					return false;
				},
				cancel: true//取消按钮函数
			});
        }
	
        //用户授权
        function user_authorized(){
			top.$.dialog({
				width:1050,
				height:500,
				lock:true,
				title:"授权给用户",
				content: 'url:rbac/role/initAuthorizedUser.do?roleId='+Qm.getValueByName("id"),
				ok:function(w){
					var iframe=this.iframe.contentWindow;
                    iframe.authorizedUser();
					return false;
				},
				cancel:function(w){//取消按钮函数
				}
			});
        }
        
        //新增角色
        function add(){
			top.$.dialog({
				width: 600,
				height: 300,
				lock:true,
				title:"新增角色",
				data: {window:window},
				content: 'url:pub/rbac/role_detail.jsp?status=add'
			});
        }
        
        //列表刷新
		function submitAction(o) {
		    Qm.refreshGrid();
		}
       
        //修改角色
        function modify(){
			top.$.dialog({
				width: 600,
				height: 300,
				lock:true,
				title:"修改角色",
				data: {window:window},
				content: 'url:pub/rbac/role_detail.jsp?status=modify&id='+Qm.getValueByName("id")
			});
        } 
        //删除任务节点功能
        function del(){
            $.del(kFrameConfig.DEL_MSG,"rbac/role/delete.do",{"ids":Qm.getValuesByName("id").toString()});
        }

      //设置角色为默认
        function set_default(){
            var rid = Qm.getValueByName("id");
            var rname = Qm.getValueByName("name");
            if(rid){
                $.post("sys/defaultUserRole/save.do",{code:rid,name:rname},function(resp){
                    if(resp.success){
                        top.$.notice("操作成功！");
                        Qm.refreshGrid();
                    }else{
                        $.ligerDialog.error("操作失败！<br/>" + resp.msg);
                    }
                },"json");
            }
        }
      
        //取消默认角色
        function removeDefault(){
            $.del("确定要取消所选择的默认权限?","sys/defaultUserRole/remove.do",{"ids":Qm.getValuesByName("vid").toString()});
        }
    </script>
	</head>
	<body>
		<sec:authorize ifAnyGranted="super_administrate,sys_administrate">
		<qm:qm pageid="sys_role" singleSelect="true" />
		</sec:authorize>
		<sec:authorize ifNotGranted="super_administrate,sys_administrate">
		<qm:qm pageid="sys_org_role" singleSelect="true" />
		</sec:authorize>
		<script type="text/javascript">
		Qm.config.columNum=true;
		</script>
	</body>
</html>
