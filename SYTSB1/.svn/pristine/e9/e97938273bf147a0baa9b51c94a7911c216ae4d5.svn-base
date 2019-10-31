<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-list.jsp"%>
<title>私人通讯录</title>
<script type="text/javascript">
	var orgTreeManager;
	var addressListId;
	var  firstId;
	$(function() {
		//按钮工具栏
		toolbarManager = $("#toptoolbar").ligerToolBar(
		{
			items: [ 
			{
				text: '新增类别',id: 'add',click: add,icon: 'add'
			}, "-", {
				text: '修改类别',id: 'modify',click: modify,icon: "modify"
			}, "-", {
				text: '删除类别',id: 'del',click: del,icon: "delete"
			},"-",{
				text: '详情', id: 'detailValue', icon: 'detail', click: detailValue
			}, "-", {
				text: '新增', id: 'addValue', icon: 'add', click: addValue,disabled:true
			}, "-", {
				text: '修改', id: 'editValue', icon: 'modify', click: editValue
			}, "-", {
				text: '删除', id: 'delValue', icon: 'delete', click: delValue
			}
			
			//,"-", {
			//		text: '设置', id: 'updateValue', icon: 'settings', click: updateValue
			//}
			]
		} 
		);
		$("#layout1").ligerLayout({
			leftWidth : 180,
			topHeight: 30,
			sapce : 5,
			allowLeftCollapse : true,
			allowRightCollapse : false,
			allowTopResize: false
		});
		var userId = "<sec:authentication property="principal.id" />";
		var unitId = "<sec:authentication property="principal.unit.id" />";
		var unitName = "<sec:authentication property="principal.unit.orgName" />";
		var levelCode = "<sec:authentication property="principal.unit.levelCode" />";
		//alert(unitId);
		orgTreeManager = $("#tree1").ligerTree({
			checkbox : false,
			selectCancel : false,
            iconFieldName : "level",
            url:'rbac/pubAddressType/addressListTreePrivate.do',
            iconParse : function(data){
            	//alert(JSON.stringify(data)) ;  
            	if(data["level"]==1)
               		return "k/kui/images/icons/16/home.png";
				else if (data["level"] == 2)
					return "k/kui/images/icons/16/org.png";
				else if (data["level"] == 3)
                	return "k/kui/images/icons/16/group.png";
				else
                	return "k/kui/images/icons/16/folders_explorer.png";
            },
            onSuccess:function(res){
            	if(res!=""){
            		firstId=res[0].id;	
            		$("#rightFrame").attr("src","pub/addressBook/private_address_org.jsp?my_type="+firstId+"&userId="+userId);
            	}else{
            		$("#rightFrame").attr("src","pub/addressBook/private_address_org.jsp");
            	}
            } ,
			onSelect : function(node) {
				if(node){
					if(node.data.id.length==32){
						toolbarManager.setEnabled({addValue:true});
					}else{
						toolbarManager.setEnabled({addValue:false});
					}
				}
				changeToolbarStatus();
				//alert(JSON.stringify(node.data));
				var win = $("#rightFrame").get(0).contentWindow.window;
				win.s_org_id = node.data.id;
				addressListId=node.data.id;
				//alert(node.data.id);
				if(win.loadGridData) win.loadGridData(node.data.id);
			},onAfterAppend:function(data){
				//alert(data);
			}
		});
		changeToolbarStatus();
		//$("#rightFrame").attr("src","pub/addressBook/private_address_org.jsp?my_type="+firstId+"&userId="+userId);
	});
	
	function changeToolbarStatus(){
		var actionNode = orgTreeManager.getSelected();
		if(actionNode==null){
			toolbarManager.setEnabled({modify:false,del:false,add:true,addValue:false,detail:false});
		}else{
			if(actionNode.data.id.length==32){
				toolbarManager.setEnabled({modify:true,del:true,add:true,detail:true,addValue:true });
			}else{
				toolbarManager.setEnabled({modify:true,del:true,add:true,detail:true,addValue:false });
			}
		}
	} 
	//详情
	 function detail(){
		if(addressListId.length<32){
				$.ligerDialog.warn('只能查看通讯列表详情 ');
			}else{
				top.$.dialog({
					width: 400,
					height: 200,
					lock: true,
					parent: api,
					data: {
						window: window
					},
					title: "详情",
					content: 'url:pub/addressBook/private_addresslist_detail.jsp?id=' + addressListId + '&pageStatus=detail'
				}); 
			}
		}
	//添加
		function add(){
			top.$.dialog({
				width: 400,
				height: 200,
				lock: true,
				parent: api,
				data: {
					window: window
				},
				title: "新增",
				content: 'url:pub/addressBook/private_addresslist_detail.jsp?pageStatus=add'
			}); 
		}
		
		function modify(){
			if(addressListId.length<32){
				$.ligerDialog.warn('只能修改通讯列表信息');
			}else{
				top.$.dialog({
					width: 400,
					height: 200,
					lock: true,
					parent: api,
					data: {
						window: window
					},
					title: "修改",
					content: 'url:pub/addressBook/private_addresslist_detail.jsp?id=' + addressListId + '&pageStatus=modify'
				}); 		
			}
		}
		
		function del(){
			if(addressListId.length<32){
				$.ligerDialog.warn('组织机构，不能删除');
			}else{
				var actionNode = orgTreeManager.getSelected();
				if (actionNode == null) {
					$.ligerDialog.warn('请选择您要删除的数据!', "提示")
					return false;
				}else {
					if (orgTreeManager.hasChildren(actionNode.data)) {
						$.ligerDialog.warn('该节点下含有子节点，请先删除子节点!', "提示")
						return false;
					}
					$.ligerDialog.confirm(kFrameConfig.DEL_MSG, function(yes) {
						if (yes) {
							$.post("rbac/pubAddressType/delete.do", {
								"ids": actionNode.data.id
							}, function(response) {
								if (response.success) {
									if(response.data=="false"){
										$.ligerDialog.warn('该类别下面还有联系人,不能删除！');
									}else{
										orgTreeManager.remove(actionNode.data);
										changeToolbarStatus();
									}
								} else {
									$.ligerDialog.error('删除失败！');
								}
							
							});
						}
					});
				}
			}
		}
		
		//码表新增更新回刷 
		function refreshTree(data) {
		/* 	var newNodes = [ {
				text: data.text,
				id: data.id,
				ischecked:false,
			} ];
			alert(data.treeId);
			orgTreeManager.append(data.treeId,newNodes); */
			orgTreeManager.clear();
			orgTreeManager.loadData("","rbac/pubAddressType/addressListTreePrivate.do","");
		}
		 function detailValue(){
		    	var win = $("#rightFrame").get(0).contentWindow.window;
		    	if(win.detailValue){ win.detailValue();}
		    }
		    function addValue(){
		    	var win = $("#rightFrame").get(0).contentWindow.window;
		    	if(win.addValue){ win.addValue(addressListId);}
		    }
		    function delValue(){
		    	var win = $("#rightFrame").get(0).contentWindow.window;
		    	if(win.delValue){ win.delValue();}
		    }
		    function editValue(){
		    	var win = $("#rightFrame").get(0).contentWindow.window;
		    	if(win.editValue){ win.editValue();}
		    }
		    function updateValue(){
		    	var win = $("#rightFrame").get(0).contentWindow.window;
		    	if(win.updateValue){ win.updateValue();}
		    }
		    function falsh(count){
				 if(count==0){
					toolbarManager.setEnabled({detailValue:false,editValue:false,delValue:false,updateValue:false});
				}else if(count==1){
					toolbarManager.setEnabled({detailValue: true,editValue: true,delValue: true,updateValue: true});
				}else{
					toolbarManager.setEnabled({detailValue: false,editValue: false,delValue: true,updateValue: false});
				} 
		    } 
</script>
<style type="text/css">
    .l-tree .l-tree-icon-none img {
        height: 16px;
        margin: 3px;
        width: 16px;
    }
</style>
</head>
<body class="p5">

	<div id="layout1">
	<div position="top" id="toptoolbar"></div>
		<div position="left" title="类别" class="overflow-auto">
			<ul id="tree1"></ul>
		</div>
		<div position="center">
			<iframe id="rightFrame" frameborder="0" name="userFrame" width="100%" height="100%" scrolling="no" src="" /></iframe>
		</div>
	</div>
</body>
</html>
