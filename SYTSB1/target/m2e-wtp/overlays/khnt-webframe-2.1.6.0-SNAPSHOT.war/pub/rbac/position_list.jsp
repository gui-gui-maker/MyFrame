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
			{name:"position_code",compare:"like"},
			{name:"position_name",compare:"like"},
			{name:"position_type",compare:"="}
	    ],
		<sec:authorize ifNotGranted="super_administrate,sys_administrate">
		<tbar:toolBar type="tbar" code="positionManage"/>,
		</sec:authorize>
		<sec:authorize ifAnyGranted="super_administrate,sys_administrate">
			tbar:[
             { text:'详情', id:'detail',icon:'detail', click: detailPosition},
             "-",
             { text:'新增', id:'add',icon:'add', click: addPosition},
             "-",
             { text:'修改', id:'modify',icon:'modify', click: modifyPosition},
             "-",
             { text:'删除', id:'del',icon:'delete', click: delPostion},
             "-",
             { text:'授权', id:'setting',icon:'setting', click: authorized},
             "-",
             { text:'批量授权', id:'settingAll',icon:'setting', click: authorizedAll}
        	],
		</sec:authorize>
        listeners: {
			selectionChange :function(rowData,rowIndex){
     			var count = Qm.getSelectedCount();//行选择个数
               	Qm.setTbar({modify:count==1,detail:count==1,del:count>0,setting:count==1,settingAll:count>0});
			},
			rowDblClick : function(rowData, rowIndex) {
				Qm.getQmgrid().selectRange("id", [rowData.id]);
				detailPosition();
			}
		}
	};

	function config(){
		var actionNode =  Qm.getValueByName("id");
		selectUnitOrUser(0, 0, null, null, function(data){
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
	function addPosition() {
		var org = parent.orgTreeManager.getSelected();
		if(org==null){
			parent.$.ligerDialog.warn("请选择新增岗位的单位!");
			return;
		}
		
		top.$.dialog({
			width : 600, 
			height : 300, 
			lock : true, 
			title : "新增岗位", 
			data : {"window" : window},
			content : 'url:pub/rbac/position_detail.jsp?status=add&orgid=' + org.data.id
		});
	}
	
	function submitAction(o) {
		Qm.refreshGrid();
	}

	//修改用户
	function modifyPosition(item) {
		top.$.dialog({
			width : 600, 
			height : 300, 
			lock : true, 
			title : "修改岗位", 
			data : {"window" : window},
			content : 'url:pub/rbac/position_detail.jsp?status=modify&id=' + Qm.getValueByName("id")
		});
	}
	
	//查看用户
	function detailPosition() {
		top.$.dialog({
			width : 500, 
			height : 280, 
			lock : true, 
			title : "查看岗位", 
			data : {"window" : window},
			cancel : true,
			content : 'url:pub/rbac/position_detail.jsp?status=detail&id=' + Qm.getValueByName("id")
		});
	}
	
	//删除用户
	function delPostion(item) {
		$.del(kFrameConfig.DEL_MSG, "rbac/position/delete.do", {
			"ids" : Qm.getValuesByName("id").toString()
		});
	}
	
	function loadGridData(orgId){
		Qm.config.defaultSearchCondition[0].value=orgId;
		Qm.searchData();
	}
	
	//给用户授权
	function authorized(){
		top.$.dialog({
			width:1100,
			height:600,
			lock:true,
			title:"设置岗位【" + Qm.getValueByName("position_name") + "】拥有的权限",
			content: 'url:rbac/position/initAuthorizedPermission.do?positionId='+Qm.getValueByName("id"),
			ok:function(w){
				var iframe=this.iframe.contentWindow;
				iframe.authorizedRole();
				return false;
			},
			cancel:true
		});
	}
	//给用户授权
	function authorizedAll(){
		top.$.dialog({
			width:1100,
			height:600,
			lock:true,
			title:"批量设置岗位拥有的权限",
			data:{positionIds:Qm.getValuesByName("id").toString()},
			content: 'url:pub/rbac/position_Authorized_permission.jsp?&batch=true',
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
	<qm:qm pageid="sys_position" script="false" singleSelect="false">
		<qm:param name="level_code" compare="llike" value="${param.levelCode}" />
	</qm:qm>
</body>
</html>
