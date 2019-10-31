<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="/k/kui-base-list.jsp" %>
	<title></title>
	<script test="text/javascript">
	$(function () {//jQuery页面载入事件
		pageTitle({to:"#title",text:"页面标题",note:"页面介绍",icon:"k/kui/images/icons/32/places.png",show:true});
	});
	</script>

	<SCRIPT type="text/javascript">
		<!--
		var setting = {
			edit: {
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false,
				drag:{
					isCopy:true,
					isMove:true,
					prev:true,
					inner:true,
					next:true
				}
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeDrag: beforeDrag,
				beforeDrop: beforeDrop
			}
		};

		var zNodes =[
			{ id:1, pId:0, name:"随意拖拽 1", open:true},
			{ id:11, pId:1, name:"随意拖拽 1-1"},
			{ id:12, pId:1, name:"随意拖拽 1-2", open:true},
			{ id:121, pId:12, name:"随意拖拽 1-2-1"},
			{ id:122, pId:12, name:"随意拖拽 1-2-2"},
			{ id:123, pId:12, name:"随意拖拽 1-2-3"},
			{ id:13, pId:1, name:"禁止拖拽 1-3", open:true, drag:false},
			{ id:131, pId:13, name:"禁止拖拽 1-3-1", drag:false},
			{ id:132, pId:13, name:"禁止拖拽 1-3-2", drag:false},
			{ id:133, pId:13, name:"随意拖拽 1-3-3"},
			{ id:2, pId:0, name:"随意拖拽 2", open:true},
			{ id:21, pId:2, name:"随意拖拽 2-1"},
			{ id:22, pId:2, name:"禁止拖拽到我身上 2-2", open:true, drop:false},
			{ id:221, pId:22, name:"随意拖拽 2-2-1"},
			{ id:222, pId:22, name:"随意拖拽 2-2-2"},
			{ id:223, pId:22, name:"随意拖拽 2-2-3"},
			{ id:23, pId:2, name:"随意拖拽 2-3"}
		];

		function beforeDrag(treeId, treeNodes) {
			for (var i=0,l=treeNodes.length; i<l; i++) {
				if (treeNodes[i].drag === false) {
					return false;
				}
			}
			return true;
		}
		function beforeDrop(treeId, treeNodes, targetNode, moveType) {
			return targetNode ? targetNode.drop !== false : true;
		}
		
		function setCheck() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			isCopy = true;//$("#copy").attr("checked"),
			isMove = true;//$("#move").attr("checked"),
			prev = true;//$("#prev").attr("checked"),
			inner = true;//$("#inner").attr("checked"),
			next = true;//$("#next").attr("checked");
			zTree.setting.edit.drag.isCopy = isCopy;
			zTree.setting.edit.drag.isMove = isMove;
			//showCode(1, ['setting.edit.drag.isCopy = ' + isCopy, 'setting.edit.drag.isMove = ' + isMove]);

			zTree.setting.edit.drag.prev = prev;
			zTree.setting.edit.drag.inner = inner;
			zTree.setting.edit.drag.next = next;
			//showCode(2, ['setting.edit.drag.prev = ' + prev, 'setting.edit.drag.inner = ' + inner, 'setting.edit.drag.next = ' + next]);
		}
		function showCode(id, str) {
			var code = $("#code" + id);
			code.empty();
			for (var i=0, l=str.length; i<l; i++) {
				code.append("<li>"+str[i]+"</li>");
			}
		}
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			//setCheck();
			$("#copy").bind("change", setCheck);
			$("#move").bind("change", setCheck);
			$("#prev").bind("change", setCheck);
			$("#inner").bind("change", setCheck);
			$("#next").bind("change", setCheck);
		});
		//-->
	</SCRIPT>
	
</head>
<body>


<div class="">
	<div class="left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
</div>

</body>
</html>