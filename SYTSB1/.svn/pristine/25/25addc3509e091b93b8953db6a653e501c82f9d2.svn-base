<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>通用查询</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
             sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:60},// 可以自己定义
			 sp_fields:[
			              {name:"title",compare:"like",value:""}
			           ],
   			tbar : [ {
   				text : '详情',
   				id : 'view',
   				icon : 'detail',
   				click : detialAppItems
   			}, "-", {
   				text : '增加',
   				id : 'add',
   				icon : 'add',
   				click : addAppItem
   			}, "-", {
   				text : '修改',
   				id : 'modify',
   				icon : 'modify',
   				click : modifyAppItem
   			}, "-", {
   				text : '删除',
   				id : 'del',
   				icon : 'delete',
   				click : deleteAppItems
   			}
   			],
   			////            //提供以下4个事件
   			listeners : {
   				rowClick : function(rowData, rowIndex) {
   				},
   				rowDblClick : function(rowData, rowIndex) {
   					modifyAppItem(rowData);
   				},
   				selectionChange : function(rowData, rowIndex) {
   					selectionChange();
   				}
   			}
   		};
function check(item)
{
	var selectedId = Qm.getValuesByName("id");
	if (selectedId.length < 1) {
		$.ligerDialog.alert('请先选择要审核的事项！', "提示");
		return;
	}
	var checkstatus;
	if(item.id=="checkpass")
	{
		checkstatus = 1;
	}
	else
	{
		checkstatus = 2;	
	}
	$.post("app/teacherCer/check.do",{ids:selectedId.toString(),checkstatus:checkstatus.toString()},function(data)
    		{
				submitAction();
    			top.$.ligerDialog.alert(data);
    		}
    ,"html");	
}
   		//行选择改变事件
   		function selectionChange() {
   			var count = Qm.getSelectedCount();//行选择个数
   				if (count == 0) {
   	   				Qm.setTbar({
   	   					add : true,
   	   					modify : false,
   	   					del : false,
   	   					view :false
   	   				});
   	   			} else if (count == 1) {
   	   				Qm.setTbar({
   	   					add : true,
   	   					modify : true,
   	   					del : true,
   	   					view :true
   	   				});
   	   			} else {
   	   				Qm.setTbar({
   	   					add : true,
   	   					modify : false,
   	   					del : true,
   	   					view :false
   	   				});
   	   			}
   		}

   		function modifyAppItem(item) {
   		
   			var selectedId = new Array();
   			var status = "modify";
   			if (item.id == "modify") {//点击修改按钮调用的本方法
   				selectedId = Qm.getValuesByName("id");
   			} else {//双击数据调用本方法
   				selectedId[0] = item.id;
   				status = "detail";
   			}
   			if (selectedId.length > 1) {
   				$.ligerDialog.alert('不支持批量修改，请只选择一条数据！', "提示");
   				return;
   			} else if (selectedId.length < 1) {
   				$.ligerDialog.alert('请先选择要修改的数据！', "提示");
   				return;
   			}
   			var width = 800;
   			var height = 600;
   			var windows = top.$.dialog({
   				width : width,
   				height : height,
   				lock : true,
   				title : "编辑",
   				data : {
   					"window" : window
   				},
   				content : 'url:app/zpmanage/notice/notice_detail.jsp?status=' + status
   						+ '&id=' + selectedId
   			});
   		}
   		function addAppItem(item) {
   			var width = 800;
   			var height = 600;
   			var windows = top.$.dialog({
   				width : width,
   				height : height,
   				lock : true,
   				title : "新增",
   				data : {
   					"window" : window
   				},
   				content : 'url:app/zpmanage/notice/notice_detail.jsp?status=add'
   			});
   		}

   		function deleteAppItems() {
   			var selectedId = Qm.getValuesByName("id");
   			if (selectedId.length < 1) {
   				$.ligerDialog.alert('请先选择要删除的事项！', "提示");
   				return;
   			}
   			var tishi = "你确定要删除吗？"+"\n删除后不能恢复！";
   			if (selectedId.length > 1) {
   				tishi = "你确定要删除这 " + selectedId.length + " 条数据吗？\n删除后不能恢复！";
   			}
   			$.del(tishi, "app/zp/notice/delete.do", {
   				"ids" : selectedId.toString()
   			});
   		}
   		function detialAppItems()
   		{
   			var selectedId = Qm.getValuesByName("id");
   			if (selectedId.length < 1) {
   				$.ligerDialog.alert('请先选择要查看的事项！', "提示");
   				return;
   			}
   			var width = 800;
   			var height = 600;
   			var windows = top.$.dialog({
   				width : width,
   				height : height,
   				lock : true,
   				title : "查看详细信息",
   				data : {
   					"window" : window
   				},
   				content : 'url:app/zpmanage/notice/notice_detail.jsp?status=detail'
   						+ '&id=' + selectedId
   			});
   		}
		function submitAction(o) {
			Qm.refreshGrid();
		}
   		
</script>
</head>
<body>
	<qm:qm pageid="zpnotice" script="false" singleSelect="false">
	</qm:qm>

</body>
</html>