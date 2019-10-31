<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统用户</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
		sp_fields:[
	    	{name:"account",compare:"like"},
			{name:"name",compare:"like"},
			{name:"status",compare:"like",value:"1",xtype:"radioGroup"}
	    ],
		<sec:authorize ifNotGranted="super_administrate">
			<tbar:toolBar type="tbar" code="userManage"/>,
		</sec:authorize>
		<sec:authorize access="hasRole('super_administrate')">
			tbar:[
             { text:'详情', id:'detail',icon:'detail', click: detailUser},
             "-",
             { text:'新增', id:'add',icon:'add', click: addUser},
             "-",
             { text:'修改', id:'modify',icon:'modify', click: modifyUser},
             "-",
             { text:'删除', id:'del',icon:'delete', click: delUser},
             "-",
             { text:'启用', id:'enable',icon:'userUnlock', click: function(){setEnabled(1)}},
             "-",
             { text:'禁用', id:'disenable',icon:'userLock', click: function(){setEnabled(0)}},
             "-",
             { text:'调整', id:'config',icon:'userMove', click: config},
             "-",
             { text:'授权', id:'setting',icon:'setting', click: authorized},"-",
             { text:'临时授权', id:'tempAuth',icon:'setting', click: tempAuthorized},"-",
             { text:'已授权限', id:'setting1',icon:'setting', click: authorizedPer},"-",
             { text:'已授资源', id:'setting2',icon:'setting', click: authorizedRes},"-",
             { text:'密码重置', id:'resetPwd',icon:'settings', click: resetPwd}
        	],
		</sec:authorize>
		
        listeners: {
			selectionChange :function(rowData,rowIndex){
     			var count = Qm.getSelectedCount();//行选择个数
               	Qm.setTbar({
               		modify:count==1,
               		detail:count==1,
               		del:count>0,
               		config:count==1,
               		enable:count==1,
               		disenable:count==1,
               		setting:count==1,
               		setting1:count==1,
               		setting2:count==1,
               		settings:count==1,
               		tempAuth:count==1
               	});
			},
			rowDblClick : function(rowData, rowIndex) {
				Qm.getQmgrid().selectRange("id", [rowData.id]);
				detailUser();
			},
			rowAttrRender:function(item,rowid){
				if(item.status=="否"){
					return 'style="color:#e6640d;"';
				}
			},
			rowDraggable:true,
			onRowDragDrop:function(data){
				var sortIds = "";
				var data = Qm.getQmgrid().getData();
				for(var i in data){
					sortIds += data[i].id+",";
				}
				$.post("rbac/user/changeSort.do",{sortIds:sortIds},function(res){
				},"JSON");
			}
		}
	};

	function config(){
		var actionNode =  Qm.getValueByName("id");
		selectUnitOrUser('000', 0, null, null, function(data){
			if(data.code){
				if(data.code == Qm.getValueByName("org_id")){
					top.$.ligerDialog.alert("该用户已经在你选择的机构下，无需调整！");
					return;
				}
				$.getJSON("rbac/user/changeOrg.do",{userId:actionNode,orgId:data.code},function(resp){
					if(resp.success){
						Qm.refreshGrid();
						top.$.notice("调整成功！",2);
					}
				});
			}
		});
	}
	
	//新增用户
	function addUser() {
		var org = parent.orgTreeManager.getSelected();
		if(org==null){
			parent.$.ligerDialog.warn("请选择新增用户的单位!");
			return;
		}
		var url = "pub/rbac/user_simple_detail.jsp";
	
		if(kui["SYS_POSITION_SET"]){
			url = "pub/rbac/user_simple_employee_detail.jsp"
		}
		top.$.dialog({
			width : 550, 
			height : 280, 
			lock : true, 
			title : "新增用户", 
			parent: api,
			data: {
				window: window
			},
			content : 'url:'+url+'?status=add&orgid=' + org.data.id
		});
	}
	
	function submitAction(o) {
		Qm.refreshGrid();
	}

	//修改用户
	function modifyUser(item) {
		var url = "pub/rbac/user_simple_detail.jsp";
		if(kui["SYS_POSITION_SET"]){
			url = "pub/rbac/user_simple_employee_detail.jsp"
		}
		top.$.dialog({
			width : 550, 
			height : 280, 
			lock : true, 
			title : "修改用户", 
			parent: api,
			data: {
				window: window
			},
			content : 'url:'+url+'?status=modify&id=' + Qm.getValueByName("id")
		});
	}
	
	//查看用户
	function detailUser() {
		var url = "pub/rbac/user_simple_detail.jsp";
		if(kui["SYS_POSITION_SET"]){
			url = "pub/rbac/user_simple_employee_detail.jsp"
		}
		top.$.dialog({
			width : 550, 
			height : 280, 
			lock : true, 
			title : "查看用户", 
			parent: api,
			data: {
				window: window
			},
			cancel : true,
			content : 'url:'+url+'?status=detail&id=' + Qm.getValueByName("id")
		});
	}
	
	//删除用户
	function delUser(item) {
		$.del(kFrameConfig.DEL_MSG, "rbac/user/delete.do", {
			"ids" : Qm.getValuesByName("id").toString()
		});
	}
	
	function loadGridData(orgId){
		Qm.config.defaultSearchCondition[0].value=orgId;
		Qm.searchData();
	}
	
	function setEnabled(status){
		$.getJSON("rbac/user/setEnabled.do",{'id':Qm.getValueByName("id"),'status':status},function(resp){
			if(resp.success){
				top.$.notice("操作成功！");
				Qm.refreshGrid();
			}
			else
				$.ligerDialog.error("操作失败：<br/>" + resp.msg);
		});
	}
	
	//给用户授权
	function authorized(){
		top.$.dialog({
			width: 1100,
			height: 650,
			lock: true,
			title: "设置用户【" + Qm.getValueByName("name") + "】的角色",
			parent: api,
			content: 'url:rbac/user/initAuthorizedRole.do?userId=' + Qm.getValueByName("id"),
			cancel: true,
			ok: function(w){
				var iframe=this.iframe.contentWindow;
				iframe.authorizedRole();
				return false;
			}
		});
	}
	
	// 给用户配置临时权限
	function tempAuthorized(){
		top.$.dialog({
			width: 900,
			height: 600,
			lock: true,
			title: "设置用户【" + Qm.getValueByName("name") + "】的临时角色",
			parent: api,
			content: 'url:pub/rbac/user_authorized_temp_role.jsp?userId=' + Qm.getValueByName("id"),
			cancel: true,
			ok: function(w){
				var iframe = this.iframe.contentWindow;
				iframe.authorizedRole();
				return false;
			}
		});
	}
	
	//已授予权限
	function authorizedPer(){
		top.$.dialog({
			width: 1100,
			height: 650,
			lock:true,
			title:"查看用户已经授予权限",
			parent: api,
			data: {
				window: window
			},
			content: 'url:pub/rbac/user_permissions.jsp?userId=' + Qm.getValueByName("id")
		});
	}
	
	function authorizedRes(){
		top.$.dialog({
			width:200,
			height:500,
			lock:true,
			title:"查看用户已经授予资源",
			parent: api,
			data: {
				window: window
			},
			content: 'url:pub/rbac/user_resources.jsp?userId=' + Qm.getValueByName("id")
		});
	}
	
	//重设密码
	function resetPwd(){
		$.getJSON("rbac/user/initPassword.do",{'id':Qm.getValueByName("id")},function(resp){
			if(resp.success){
				top.$.notice("操作成功！<br/>该用户密码已恢复为初始密码！",4);
			}
			else
				$.ligerDialog.error("操作失败：<br/>" + resp.msg);
		});
	}
</script>
</head>
<body>
	<qm:qm pageid="sys_user" script="false" singleSelect="true">
		<qm:param name="level_code" compare="llike" value="${param.levelCode}" />
		<qm:param name="account" compare="!=" value="administrator" />
	</qm:qm>
</body>
</html>
