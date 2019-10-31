<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="detail">
<title>系统菜单资源管理</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/rbac/js/draggable.js"></script>
<script type="text/javascript">
	var menuTreeManager = null;
	var ztree;
	var zNodes;
	var setting = {
			data: {
				key: {
					name: "text",
					url:""
				}
			},callback: {
				onClick: ztreeClick,
				onNodeCreated:function(event, treeId, treeNode){
					var treeObj = $.fn.zTree.getZTreeObj(treeId);
					if(treeNode.image!="null" && treeNode.image!="" && treeNode.image!=undefined){
						treeNode.icon = treeNode.image;
						treeObj.updateNode(treeNode)
					}
					if(treeNode.id=='root'){
						treeObj.selectNode(treeNode);
					}
				},
				beforeDrag: beforeDrag,
				beforeDrop: beforeDrop,
				beforeDragOpen: beforeDragOpen,
				onDrag: onDrag,
				onDrop: onDrop
			},
			edit:{
				drag: {
					autoExpandTrigger: true,
					prev: dropPrev,
					inner: dropInner,
					next: dropNext,
					isCopy:true,
					isMove:true
				},
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false
			}
	};
	$(function() {

		$.getJSON("rbac/resource/transformTree.do?id=root",function(data){
			var strData = JSON.stringify(data);
			strData = strData.replace(/isexpand/g, "open");
			zNodes = eval(strData)
			ztree = $.fn.zTree.init($("#tree1"), setting, zNodes);
		})
		//功能按钮栏
		$("#toptoolbar").ligerToolBar(
			<sec:authorize ifNotGranted="super_administrate">
				<tbar:toolBar type="ligerToolBar" code="conmenu">
				</tbar:toolBar>
			</sec:authorize>
			<sec:authorize access="hasRole('super_administrate')">
				 {
			items : [ 
			    { text : '增加系统资源', click : add, icon : 'add'},
			    { text : '增加普通资源', click : addP, icon : 'link-add'},
			    { text : '增加按钮资源', click : addBtn, icon : 'info-add'},
				{ text : '增加CRUD资源', click : addPBtn, icon : 'plus'},
			    "-",
			    { text : '修改', click : modify,icon : "modify"},
			    "-",
				{ text : '删除', click : remove, icon : "del"},
				"-",
				{ text : '复制', click : copy, icon : "copy"},
				"-",
				{ text : '权限设置', click : authorized, icon : "setting"},
				"-",
				{ text : '更新缓存', click : refreshCache, icon : "refurbish"},
				"-",
				{ text : '生成权限代码树', click : createPer, icon : "setting"},
				"-",
				{ text : '生成手机资源zip包', click : generate, icon : "generate"}
			]
			}  
		    </sec:authorize>
		);
		//页面布局
		$("#layout").ligerLayout({
			leftWidth : 300,
			topHeight : 30,
			space : 5,
			allowTopResize : false,
			allowLeftCollapse : false,
			allowRightCollapse : false
		});
	});

	function createPer(){
		var actionNode = ztree.getSelectedNodes();
		var pid = (actionNode == null ? "00" : actionNode[0].id);
		$("body").mask("权限代码树生成中!");
		$.getJSON("rbac/resource/createPermissions.do",{resourceId:pid},function(res){
			$("body").unmask();
			if(res.success){
				top.$.notice("权限代码生成成功！",3);
			}else{
				top.$.notice("权限代码生成失败！",3);
			}
		})
	}
	
	function refresh(data, status) {
		var node = ztree.getSelectedNodes();
		if (status == "add") {
			var newNode=[];
			newNode.push({
				text:data.name,
				id:data.id,
				type:data.type,
				code:data.code,
				icon:data.image
			})
			if (node){
				ztree.addNodes(node[0], newNode,false);
			}
			else
			{
				ztree.addNodes(null, newNode,false);
			}
		} else {
			if (node){
				node[0].text = data.name;
				if (node.length>0) {
					ztree.updateNode(node[0]);
				}
			}
		}
		ztree.selectNode(ztree.getNodeByParam("id", data.id, null));
		showFormData(ztree.getNodeByParam("id", data.id, null))
	}
	function add(){
		var actionNode = ztree.getSelectedNodes();
		var pid = (actionNode == null ? "00" : actionNode[0].id);
		top.$.dialog({
			width:700,
			height:510,
			lock:true,
			title:"新增资源",
			content: "url:pub/rbac/resource_detail.jsp?status=add&pid=" + pid,
			data:{window:window}
		});
	}
	function addP(){
		var actionNode = ztree.getSelectedNodes();
		var pid = (actionNode == null ? "00" : actionNode[0].id);
		top.$.dialog({
			width:700,
			height:300,
			lock:true,
			title:"新增普通资源",
			content: "url:pub/rbac/resource_p_detail.jsp?status=add&pid=" + pid,
			data:{window:window}
		});
	}
	function addPBtn(){
		var actionNode = ztree.getSelectedNodes();
		if(actionNode == null||actionNode[0].type =='2' ){
			$.ligerDialog.warn("只能在菜单资源下添加按钮！");
			return;
		}
		var pid = (actionNode == null ? "00" : actionNode[0].id);
		$.getJSON('rbac/resource/saveBtnp.do',{pid:pid},function(data){
			if(data.success){
				for(var i=0;i<data.data.length;i++){
					var newNode=[];
					newNode.push({
						text:data.data[i].name,
						id:data.data[i].id,
						type:data.data[i].type,
						code:data.data[i].code,
						icon:data.data[i].image
					})
					if (actionNode){
						ztree.addNodes(actionNode[0], newNode,false);
					}
					else
					{
						ztree.addNodes(null, newNode,false);
					}
				}
				top.$.notice('成功批量增加按钮资源！', 2);
			}
		})
	}
	function addBtn(){
		var actionNode = ztree.getSelectedNodes();
		if(actionNode == null||actionNode[0].type =='2' ){
			$.ligerDialog.warn("只能在菜单资源下添加按钮！");
			return;
		}
		var pid = (actionNode == null ? "00" : actionNode[0].id);
		top.$.dialog({
			width:700,
			height:380,
			lock:true,
			title:"新增按钮资源",
			content: "url:pub/rbac/resource_btn_detail.jsp?status=add&pid=" + pid,
			data:{window:window}
		});
	}
	
	function authorized(){
		var actionNode = ztree.getSelectedNodes();
		if(!actionNode){
			$.ligerDialog.alert("请选择一个资源！");
			return;
		}
		top.$.dialog({
			width: 920,
			height: 450,
			lock: true,
			title: "新增资源",
			content: "url:pub/rbac/resource_Authorized_permission.jsp?rid=" + actionNode[0].id
		});
	}
	
	function modify(){
		var actionNode = ztree.getSelectedNodes();
		if (actionNode == null) {
			$.ligerDialog.error('请选择需要修改的资源!')
			return false;
		}
		var url = "pub/rbac/resource_detail.jsp";
		if(actionNode[0].type=='2'){
			url = "pub/rbac/resource_btn_detail.jsp"
		}
		if(actionNode[0].type=='1'){
			url = "pub/rbac/resource_p_detail.jsp"
		}
		top.$.dialog({
			width:700,
			height:510,
			lock:true,
			title:"修改资源",
			content: "url:"+url+"?status=modify&pid=" + actionNode[0].id,
			data:{window:window}
		});
	}

	function remove() {
		var actionNode = ztree.getSelectedNodes();
		if (actionNode == null) {
			$.ligerDialog.error('请选择您要删除的资源!', "提示")
			return false;
		} else {
			if (actionNode[0].id=="root") {
				$.ligerDialog.error("不能删除根节点!");
				return false;
			}
			//var tips = "你确定要删除资源【" + actionNode[0].text + "】吗？\n将删除资源及其子资源，删除后不能恢复！";
			$.ligerDialog.confirm(kFrameConfig.DEL_MSG, function(yes) {
				if (yes) {
					$.post("rbac/resource/delete.do", {ids:actionNode[0].id}, function(res) {
						if (res.success) {
							ztree.selectNode(ztree.getNodeByParam("id", 'root', null));
							if (actionNode && actionNode.length>0) {
								ztree.removeChildNodes(actionNode[0]);
								ztree.removeNode(actionNode[0]);
							}
							showFormData(ztree.getNodeByParam("id", 'root', null));
						} else {
							$.ligerDialog.alert('删除失败！', "error");
						}
					});
				}
			});
		}
	}
	function copy(){
		var copyNode = ztree.getSelectedNodes();
		if (copyNode == null) {
			$.ligerDialog.error('请选择需要复制的资源!')
			return false;
		}
		var url = "pub/rbac/resource_detail.jsp";
		if(copyNode[0].type=='2'){
			url = "pub/rbac/resource_btn_detail.jsp"
		}
		if(copyNode[0].type=='1'){
			url = "pub/rbac/resource_p_detail.jsp"
		}
		top.$.dialog({
			width:700,
			height:450,
			lock:true,
			title:"复制资源",
			content: "url:"+url+"?status=modify&copy=1&pid=" + copyNode[0].id,
			data:{window:window}
		});
	}
	
	function copyRes(data){
		var copyNode = ztree.getSelectedNodes();
		var targetNode = copyNode[0].getParentNode();
		var newNode=[];
		newNode.push({
			text:data.name,
			id:data.id,
			type:data.type,
			code:data.code,
			icon:data.image
		})
		if (copyNode){
			ztree.addNodes(targetNode, newNode,false);
		}
	}
	
	function ztreeClick(event,treeId,treeNode){
		showFormData(treeNode);
	}
	function showFormData(treeNode){
		if(treeNode){
			$.getJSON("rbac/resource/detail.do",{id:treeNode.id},function(resp){
				if(resp.success){
					$("#formObj").setValues(resp.data);
					if(resp.data.type=='2'){
						$("#btn").show();
						$("#resource").hide();
						$("#pres").hide()
					}else if(resp.data.type=='1'){
						$("#pres").show();
						$("#btn").hide();
						$("#resource").hide();
					}else{
						$("#pres").hide();
						$("#btn").hide();
						$("#resource").show();
					}
				}
			});
		}
	}
	
	function refreshCache(){
		$.ligerDialog.confirm("确定要刷新缓存吗?",function(result){
			$.getJSON("rbac/resource/refreshCache.do",function(resp){
				if(resp)
					window.location.reload();
				else
	         		top.$.dialog.tips('缓存刷新失败！',4,"k/kui/images/icons/dialog/32X32/hits.png",null,0);
			});
		});
	}
	function generate(){
		$.ligerDialog.confirm("生成手机资源zip包?",function(result){
			if(result){
					$.getJSON("rbac/resource/createMobileZip.do",function(resp){
					if(resp.success){
						top.$.dialog.tips("成功生成手机资源zip包!");
					}
					else{
						top.$.dialog.tips("生成手机资源zip包失败!");
					}
				});
			}
		});
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
	<div id="layout">
		<div position="top" id="toptoolbar"></div>
		<div position="left" title="资源树" class="overflow-auto">
			<ul id="tree1" class="ztree"></ul>
		</div>
		<div position="center" style="overflow:auto">
			<form name="formObj" id="formObj" method="post"
			getAction="rbac/resource/detail.do?id=root">
				<table cellpadding="3" cellspacing="0" class="l-detail-table" id="resource">
					<tr>
						<td class="l-t-td-left" style="width:120px;">资源编号：</td>
						<td class="l-t-td-right"><input name="code" type="text" ltype='text' validate="{required:true,maxlength:32}" /></td>
						<td class="l-t-td-left" style="width:100px;">资源名称：</td>
						<td class="l-t-td-right"><input name="name" type="text" ltype='text' validate="{required:true,maxlength:64}" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">资源类型：</td>
						<td class="l-t-td-right"><u:combo name="type" code="sys_res_type" validate="required:true" /></td>
						<td class="l-t-td-left">菜单宽度：</td>
						<td class="l-t-td-right"><input name="width" type="text" ltype='text' validate="{maxlength:32}" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">是否引用资源：</td>
						<td class="l-t-td-right">
							<input name="isRef" type="radio" ltype='radioGroup'  ligerui="{initValue:'0',data:[{id:'1',text:'是'},{id:'0',text:'否'}]}"/>
						</td>
							<td class="l-t-td-left">资源路径：</td>
						<td class="l-t-td-right"><input name="url" type="text" ltype='text' validate="{maxlength:1024}" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">菜单图片16*16：</td>
						<td class="l-t-td-right" colspan="3">
							<input name="image" id="image" type="text" ltype='text' validate="{maxlength:128}"  
								ligerui="{iconItems:[{icon:'picture',click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">菜单图片32*32：</td>
						<td class="l-t-td-right" colspan="3">
							<input name="image32" id="image32" type="text" ltype='text' validate="{maxlength:128}"  
								ligerui="{iconItems:[{icon:'picture',click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">菜单图片48*48：</td>
						<td class="l-t-td-right" colspan="3">
							<input name="image48" id="image48" type="text" ltype='text' validate="{maxlength:128}"  
								ligerui="{iconItems:[{icon:'picture',click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">菜单图片64*64：</td>
						<td class="l-t-td-right" colspan="3">
							<input name="image64" id="image64" type="text" ltype='text' validate="{maxlength:128}"  
								ligerui="{iconItems:[{icon:'picture',click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">菜单图片128*128：</td>
						<td class="l-t-td-right" colspan="3">
							<input name="image128" id="image128" type="text" ltype='text' validate="{maxlength:128}"  
								ligerui="{iconItems:[{icon:'picture',click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">菜单图片256*256：</td>
						<td class="l-t-td-right" colspan="3">
							<input name="image256" id="image256" type="text" ltype='text' validate="{maxlength:128}"  
								ligerui="{iconItems:[{icon:'picture',click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">图片css类：</td>
						<td class="l-t-td-right"><input name="iconCss" type="text" ltype='text' validate="{maxlength:256}" /></td>
						<td class="l-t-td-left">图片显示区域：</td>
						<td class="l-t-td-right">
							<input type="checkbox" name="display" ltype="checkboxGroup" validate="{required:true}"
								ligerui="{initValue:'1,2',data:[{text:'菜单',id:'1'},{text:'桌面',id:'2'}]}" />
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">桌面弹窗宽高：</td>
						<td class="l-t-td-right"><input name="winwh" type="text" ltype='text' validate="{maxlength:256}" title="填写格式400,500表示宽400高"/></td>
						<td class="l-t-td-left">是否文本：</td>
						<td class="l-t-td-right">
							<input name="istext" type="radio" ltype="radioGroup" ligerui="{initValue:'false',data:[{id:'true',text:'是'},{id:'false',text:'否'}]}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">桌面弹窗URL：</td>
						<td class="l-t-td-right" colspan="3"><input name="desktopUrl" type="text" ltype='text' validate="{maxlength:1024}" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">备注：</td>
						<td class="l-t-td-right"colspan="3"><textarea name="remark" ltype='l-textarea' rows="2" validate="{maxlength:1024}"></textarea></td>
					</tr>
				</table>
				<table cellpadding="3" cellspacing="0" class="l-detail-table" id="btn" style="display:none">
					<tr>
						<td class="l-t-td-left" style="width:100px;">资源名称：</td>
						<td class="l-t-td-right">
							<input name="name" type="text" ltype='text' validate="{required:true,maxlength:64}" />
						</td>
						<td class="l-t-td-left">资源类型：</td>
						<td class="l-t-td-right">
							<input type="text" name="type" ltype="select" ligerui="{initValue:'2',data:[{id:'2',text:'按钮资源'}]}" validate="{required:true}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left" style="width:120px;">资源编号：</td>
						<td class="l-t-td-right"><input name="code" type="text" ltype='text' validate="{maxlength:32}" /></td>
						<td class="l-t-td-left">资源图标：</td>
						<td class="l-t-td-right">
							<input name="iconCss" id="iconCss" type="text" ltype='text' validate="{maxlength:128}" />
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">资源路径：</td>
						<td class="l-t-td-right" colspan="3">
							<input name="url" type="text" ltype='text' validate="{maxlength:1024}" />
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">资源事件：</td>
						<td class="l-t-td-right" colspan="3">
							<textarea rows="5" name="click" ltype="l-textarea" validate="{maxlength:4000}"></textarea>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">显示分割线：</td>
						<td class="l-t-td-right">
							<input name="istext" type="radio" ltype="radioGroup" ligerui="{initValue:'false',data:[{id:'true',text:'是'},{id:'false',text:'否'}]}"/>
						</td>
						<td class="l-t-td-left">排序：</td>
						<td class="l-t-td-right"><input name="sort" type="text" ltype='text' validate="{maxlength:32}" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">备注：</td>
						<td class="l-t-td-right"colspan="3"><textarea name="remark" ltype='l-textarea' rows="2" validate="{maxlength:1024}"></textarea></td>
					</tr>
				</table>
				<table cellpadding="3" cellspacing="0" class="l-detail-table"  id="pres" style="display:none">
					<tr>
						<td class="l-t-td-left" style="width:100px;">资源名称：</td>
						<td class="l-t-td-right">
							<input name="name" type="text" ltype='text' validate="{required:true,maxlength:64}" />
						</td>
						<td class="l-t-td-left">资源类型：</td>
						<td class="l-t-td-right">
							<input type="text" name="type" ltype="select" ligerui="{initValue:'1',data:[{id:'1',text:'普通资源'}]}" validate="{required:true}"/>
							<input name="image" id="image" type="hidden" value='k/kui/images/icons/16/link.png'/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">资源路径：</td>
						<td class="l-t-td-right" colspan="3">
							<input name="url" type="text" ltype='text' validate="{maxlength:1024}" />
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">菜单图片16*16：</td>
						<td class="l-t-td-right" colspan="3">
							<input name="image" id="image" type="text" ltype='text' validate="{maxlength:128}"  
								ligerui="{iconItems:[{icon:'picture',click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">菜单图片32*32：</td>
						<td class="l-t-td-right" colspan="3">
							<input name="image32" id="image32" type="text" ltype='text' validate="{maxlength:128}"  
								ligerui="{iconItems:[{icon:'picture',click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">菜单图片48*48：</td>
						<td class="l-t-td-right" colspan="3">
							<input name="image48" id="image48" type="text" ltype='text' validate="{maxlength:128}"  
								ligerui="{iconItems:[{icon:'picture',click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">菜单图片64*64：</td>
						<td class="l-t-td-right" colspan="3">
							<input name="image64" id="image64" type="text" ltype='text' validate="{maxlength:128}"  
								ligerui="{iconItems:[{icon:'picture',click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">菜单图片128*128：</td>
						<td class="l-t-td-right" colspan="3">
							<input name="image128" id="image128" type="text" ltype='text' validate="{maxlength:128}"  
								ligerui="{iconItems:[{icon:'picture',click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">菜单图片256*256：</td>
						<td class="l-t-td-right" colspan="3">
							<input name="image256" id="image256" type="text" ltype='text' validate="{maxlength:128}"  
								ligerui="{iconItems:[{icon:'picture',click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left" style="width:120px;">资源编号：</td>
						<td class="l-t-td-right"><input name="code" type="text" ltype='text' validate="{maxlength:32}" /></td>
						<td class="l-t-td-left">排序：</td>
						<td class="l-t-td-right"><input name="sort" type="text" ltype='text' validate="{maxlength:32}" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">备注：</td>
						<td class="l-t-td-right" colspan="3"><textarea name="remark" ltype='l-textarea' rows="5" validate="{maxlength:1024}"></textarea>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
