<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统用户</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults : {
			labelWidth : 60,
			columnWidth : 150,
			width : 150
		},
		sp_fields:[
		 {name:'name',compare:'like'},
	    ],
		tbar:[
         /*     { text:'差旅费详情', id:'detail',icon:'detail', click: detailUser}, */
              { text:'详情', id:'detail',icon:'detail', click: detailUser1}
        ],
        listeners: {
			selectionChange :function(rowData,rowIndex){
     			var count = Qm.getSelectedCount();//行选择个数
               	Qm.setTbar({modify:count==1,detail:count==1,del:count>0,config:count==1,enable:count==1,disenable:count==1,setting:count==1});
			},
			rowDblClick : function(rowData, rowIndex) {
				detailUser1();
				Qm.getQmgrid().selectRange("id", [rowData.id]);
			}
		}
	};

	function config(){
		var actionNode =  Qm.getValueByName("id");
		selectUnitOrUser("4", 0, null, null, function(data){
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
	
	
	var s_org_id = "${param.orgid}";
	//新增用户
	function addUser() {
		var oid = parent.orgTreeManager.getSelected().data.id;
		
		top.$.dialog({
			width : 500, 
			height : 220, 
			lock : true, 
			title : "新增用户", 
			data : {"window" : window},
			content : 'url:pub/rbac/user_simple_detail.jsp?status=add&orgid=' + oid
		});
	}
	
	function submitAction(o) {
		Qm.refreshGrid();
	}

	//修改用户
	function modifyUser(item) {
		top.$.dialog({
			width : 500, 
			height : 220, 
			lock : true, 
			title : "修改用户", 
			data : {"window" : window},
			content : 'url:pub/rbac/user_simple_detail.jsp?status=modify&id=' + Qm.getValueByName("id")
		});
	}
	
	//查看差旅费
	function detailUser() {
		top.$.dialog({
			width : 800, 
			height : 700, 
			lock : true, 
			title : "查看用户", 
			data : {"window" : window},
			cancel : true,
			content : 'url:app/finance/fcp_clfbxd_list.jsp?status=detail&id=' + Qm.getValueByName("employee_id")
		});
	}
	
	
	function detailUser1() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 600,
			height: 250,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/finance/fcp_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	
	
	//删除用户
	function delUser(item) {
		$.del("您确定要删除吗？", "rbac/user/delete.do", {
			"ids" : Qm.getValuesByName("id").toString()
		});
	}
	

	
	function loadGridData(orgId){
		s_org_id = orgId;
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
			width:900,
			height:400,
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
	
	//重设密码
	function resetPwd(){
		$.getJSON("rbac/user/initPassword.do",{'id':Qm.getValueByName("id")},function(resp){
			if(resp.success){
				top.$.notice("操作成功！<br/>该用户密码已恢复为初始密码[ " + resp.data +" ]！",4);
			}
			else
				$.ligerDialog.error("操作失败：<br/>" + resp.msg);
		});
	}
</script>
</head>
<body>
	<qm:qm pageid="TJY2_FCP" script="false" singleSelect="true">
		<qm:param name="level_code" compare="llike" value="${param.levelCode}" />
	</qm:qm>
</body>
</html>
