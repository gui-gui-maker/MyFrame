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
			sp_defaults:{columnWidth:0.3,labelWidth:80},
			 sp_fields:[
			              {label:"标题",name:"title",compare:"like",value:""}
			           ],
   			tbar : [  {
   				text : '详情',
   				id : 'detail',
   				icon : 'detail',
   				click : appItem
   			}, "-",{
   				text : '新增',
   				id : 'add',
   				icon : 'add',
   				click : addAppItem
   			}, "-", {
   				text : '修改',
   				id : 'modify',
   				icon : 'modify',
   				click : appItem
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
   					//appItem(rowData);
   				},
   				selectionChange : function(rowData, rowIndex) {
   					selectionChange();
   				}
   			}
   		};
   		//行选择改变事件
   		function selectionChange() {
   			var count = Qm.getSelectedCount();//行选择个数
   				if (count == 0) {
   	   				Qm.setTbar({
   	   					add : true,
   	   					modify : false,
   	   					del : false,
   	   					detail :false
   	   				});
   	   			} else if (count == 1) {
   	   				Qm.setTbar({
   	   					add : true,
   	   					modify : true,
   	   					del : true,
   	   					detail :true
   	   				});
   	   			} else {
   	   				Qm.setTbar({
   	   					add : true,
   	   					modify : false,
   	   					del : true,
   	   					detail :false
   	   				});
   	   			}
   		}

   		function appItem(item) {
   		
   			var selectedId = Qm.getValuesByName("id");
   			var width = 600;
   			var height = 400;
   			var windows = top.$.dialog({
   				width : width,
   				height : height,
   				lock : true,
   				title : item.text,
   				data : {
   					"window" : window
   				},
   				content : 'url:demo/fileupload/fileupload_detail.jsp?status=' + item.id
   						+ '&id=' + selectedId
   			});
   		}
   		function addAppItem(item) {
   			var width = 600;
   			var height = 400;
   			var windows = top.$.dialog({
   				width : width,
   				height : height,
   				lock : true,
   				title : item.text,
   				data : {
   					"window" : window
   				},
   				content : 'url:demo/fileupload/fileupload_detail.jsp?status=item.id'
   			});
   		}

   		function deleteAppItems() {
   			$.del(kFrameConfig.DEL_MSG, "demo/upload/delete.do", {
   				"ids": Qm.getValuesByName("id").toString()
   			});
   		}
		
   		function submitAction(o) {
			Qm.refreshGrid();
		}
</script>
</head>
<body>
	<qm:qm pageid="test_upload_demo" script="false" singleSelect="false">
	</qm:qm>

</body>
</html>