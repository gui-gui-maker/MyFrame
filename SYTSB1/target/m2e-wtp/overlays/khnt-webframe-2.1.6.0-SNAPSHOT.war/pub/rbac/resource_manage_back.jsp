<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="detail">
<title>系统菜单资源管理</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	var menuTreeManager = null;
	function refresh(data, status) {
		if (status == "add") {
			var node = menuTreeManager.getSelected();
			var nodes = [];
			nodes.push({
				text : data.name,
				id : data.id
			});
			if (node)
				menuTreeManager.append(node.target, nodes);
			else
				menuTreeManager.append(null, nodes);
		} else {
			var node = menuTreeManager.getSelected();
			if (node){
				menuTreeManager.update(node.target, {
					text : data.name,
					id : data.id
				});
			}
		}
		menuTreeManager.selectNode(data.id);
	}


	$(function() {
		//页面布局
		$("#layout").ligerLayout({
			leftWidth : 300,
			topHeight : 30,
			space : 5,
			allowTopResize : false,
			allowLeftCollapse : false,
			allowRightCollapse : false
		});

		//菜单树
		menuTreeManager = $("#tree1").ligerTree({
			checkbox : false,
			selectCancel:false,
			nodeDraggable:true,
			url : 'rbac/resource/transformTree.do?id=root',
			attribute : [ "url" ],
			iconFieldName: "image",
			iconParse: function(data){
				if(data.id=='root'){
					return "k/kui/images/icons/16/home.png";
				}
				if(data.image!=null&&data.image!='null'){
					return data.image;
				}else{
					if(!data.children){
						return "k/kui/skins/default/images/tree/tree-leaf.gif"
					}else{
						return "k/kui/skins/default/images/tree/folder.gif"
					}
				}
            },
			onAfterAppend : function() {
				menuTreeManager.selectNode("root"); //初始化
			},
			onSelect : function(node) {
				showFormData(node.data.id);
			}
		});

		//功能按钮栏
		$("#toptoolbar").ligerToolBar({
			items : [ 
			    { text : '增加', click : add, icon : 'add'},
			    "-",
			    { text : '修改', click : modify,icon : "modify"},
			    "-",
				{ text : '删除', click : remove, icon : "del"},
				"-",
				{ text : '更新缓存', click : refreshCache, icon : "refurbish"}
			]
		});
		
	});

	function add(){
		var actionNode = menuTreeManager.getSelected();
		var pid = (actionNode == null ? "00" : actionNode.data.id);
		top.$.dialog({
			width:700,
			height:450,
			lock:true,
			title:"修改配置编辑",
			content: "url:pub/rbac/resource_detail.jsp?status=add&pid=" + pid,
			data:{window:window}
		});
	}

	function modify(){
		var actionNode = menuTreeManager.getSelected();
		if (actionNode == null) {
			$.ligerDialog.error('请选择需要修改的机构!')
			return false;
		}
		top.$.dialog({
			width:700,
			height:450,
			lock:true,
			title:"修改配置编辑",
			content: "url:pub/rbac/resource_detail.jsp?status=modify&pid=" + actionNode.data.id,
			data:{window:window}
		});
	}

	function remove() {
		var actionNode = menuTreeManager.getSelected();
		if (actionNode == null) {
			$.ligerDialog.error('请选择您要删除的菜单!', "提示")
			return false;
		} else {
			if (menuTreeManager.hasChildren(actionNode.data)) {
				$.ligerDialog.error('该节点下含有子节点，请先删除子节点!', "提示")
				return false;
			}
			if (actionNode.data.id=="root") {
				$.ligerDialog.error("不能删除根节点!");
				return false;
			}
			var tips = "你确定要删除菜单【" + actionNode.data.text + "】吗？\n删除后不能恢复！";
			$.ligerDialog.confirm(tips, function(yes) {
				if (yes) {
					$.post("rbac/resource/delete.do", {ids:actionNode.data.id}, function(res) {
						if (res.success) {
							menuTreeManager.selectNode("root");
							menuTreeManager.remove(actionNode.target);
						} else {
							$.ligerDialog.alert('删除失败！', "error");
						}
					});
				}
			});
		}
	}
	function showFormData(id){
		if(id){
			$.getJSON("rbac/resource/detail.do",{id:id},function(resp){
				if(resp.success){
					$("#formObj").setValues(resp.data);
					$("#img16").attr('src',resp.data.image);
					$("#img32").attr('src',resp.data.image32);
					$("#img48").attr('src',resp.data.image48);
					$("#img64").attr('src',resp.data.image64);
					$("#img128").attr('src',resp.data.image128);
					$("#img256").attr('src',resp.data.image256);
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
			<ul id="tree1"></ul>
		</div>
		<div position="center" style="overflow:auto">
			<form name="formObj" id="formObj" method="post"
			getAction="rbac/resource/detail.do?id=root">
				<table cellpadding="3" cellspacing="0" class="l-detail-table">
					<tr>
						<td class="l-t-td-left" style="width:120px;">资源编号：</td>
						<td class="l-t-td-right"><input name="code" type="text" ltype='text' validate="{required:true,maxlength:32}" /></td>
						<td class="l-t-td-left" style="width:120px;">资源名称：</td>
						<td class="l-t-td-right"><input name="name" type="text" ltype='text' validate="{required:true,maxlength:64}" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">资源类型：</td>
						<td class="l-t-td-right"><u:combo name="type" code="sys_res_type" validate="required:true" /></td>
						<td class="l-t-td-left">菜单宽度：</td>
						<td class="l-t-td-right"><input name="width" type="text" ltype='text' validate="{maxlength:32}" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">资源路径：</td>
						<td class="l-t-td-right" colspan="3"><input name="url" type="text" ltype='text' validate="{maxlength:1024}" /></td>
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
						<td class="l-t-td-left">排序：</td>
						<td class="l-t-td-right" colspan="3"><input name="sort" type="text" ltype='text' validate="{maxlength:32}" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">备注：</td>
						<td class="l-t-td-right"colspan="3"><textarea name="remark" ltype='l-textarea' rows="2" validate="{maxlength:1024}"></textarea></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
