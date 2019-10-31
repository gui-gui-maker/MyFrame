<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<%@include file="/k/kui-base-list.jsp"%>
<title>图表管理</title>
<script type="text/javascript">
	var manager = null;
	var ztree;
	var zNodes;
	var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},callback: {
				onClick: ztreeClick,
				onNodeCreated:function(event, treeId, treeNode){
					var treeObj = $.fn.zTree.getZTreeObj(treeId);
					if(treeNode.image!="null" && treeNode.image!="" && treeNode.image!=undefined){
						//treeNode.icon = treeNode.image;
						//treeObj.updateNode(treeNode)
					}
					if(treeNode.id=='root'){
						treeObj.selectNode(treeNode);
						$("#rightFrame").attr("src","pub/chart/chart/lChart_list.jsp?typeId="+treeNode.id);
					}
				}
			}
	};
	$(function() {
		$("#layout1").ligerLayout({
			leftWidth : 250,
        	space : 5,
			allowLeftCollapse : false,
			allowRightCollapse : false
		});
		$("#ztree").css('height',$("#layout1").height()-$("#pic").height())
		$.getJSON("chart/type/checkSys.do",function(res){
			if(res.success){
				if(res.data){
					$.getJSON("chart/type/transformTree1.do?id=root",function(data){
						ztree = $.fn.zTree.init($("#tree1"), setting, data);
					})
				}else{
					$.ligerDialog.confirm("还未初始化图表相关数据表及参数，是否初始化？",function(yes){
						if(yes){
							$("body").mask("正在创建图表相关数据表及参数。。。");
							$.getJSON("chart/type/createSys.do",function(data){
								if(data.success){
									$("body").unmask();
									$.getJSON("chart/type/transformTree1.do?id=root",function(data){
										ztree = $.fn.zTree.init($("#tree1"), setting, data);
									})
								}
							})
						}
					})
				}
			}
		})
		//$("body").mask("正在加载数据。。。。")
	});
	function ztreeClick(event,treeId,treeNode){
		var win = $("#rightFrame").get(0).contentWindow.window;
		if(win.loadGridData) win.loadGridData(treeNode.id,treeNode.code);
		if(treeNode.image!="null" && treeNode.image!="" && treeNode.image!=undefined){
			$("#typepic").attr('src',treeNode.image)
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
		<div position="left" title="图表类型">
			<div id="ztree" style="overflow:auto;"><ul id="tree1" class="ztree"></ul></div>
			<div id="pic" align="center" style="height:150px;padding: 5px"><img id="typepic" ></img></div>
		</div>
		<div position="center">
			<iframe id="rightFrame" frameborder="0" src="" 
				name="rightFrame" width="100%" height="100%" scrolling="no"></iframe>
		</div>
	</div>
</body>
</html>
