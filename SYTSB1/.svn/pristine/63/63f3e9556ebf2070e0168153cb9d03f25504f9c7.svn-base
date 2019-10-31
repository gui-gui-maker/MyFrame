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
			tbar : [ {
   				text : '打印预览',
   				id : 'view',
   				icon : 'print',
   				click : ViewAppItem
   			}
   			],
            sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:60},// 可以自己定义
			 sp_fields:[
						  {name:"name",compare:"like",value:""},
			              {name:"certificates_num",compare:"like",value:""},
			              {name:"academic",compare:"=",value:""},
			              {name:"college",compare:"like",value:""}
			           ],
   			////            //提供以下4个事件
   			listeners : {
   				rowClick : function(rowData, rowIndex) {
   				},
   				rowDblClick : function(rowData, rowIndex) {
   					//modifyAppItem(rowData);
   				},
   				selectionChange : function(rowData, rowIndex) {
   					//selectionChange();
   				}
   			}
   		};
   		//行选择改变事件
   		function selectionChange() {
   			var count = Qm.getSelectedCount();//行选择个数
   				if (count == 0) {
   	   				Qm.setTbar({
   	   					view :false,
   	   					hbrc:false
   	   				});
   	   			} else if (count == 1) {
   	   				Qm.setTbar({
   	   					view :true,
	   					hbrc:true
   	   				});
   	   			} else {
   	   				Qm.setTbar({
	   	   				view :false,
	   					hbrc:true
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
   		function HBAppItem()
   		{
   			var selectedId = Qm.getValuesByName("id");
   			if (selectedId.length < 1) {
   				$.ligerDialog.alert('请先选择要进入人才库的简历！', "提示");
   				return;
   			}
   			$.ligerDialog.confirm('确认该简历进入人才库', function (yes) { 
   				$.post("app/zp/jlxx/toHbrc.do",{id:selectedId.toString()},function(data)
   			    		{
   							top.$.ligerDialog.alert(data);
   							submitAction();
   			    		}
   			    ,"html");
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
   				title : "编辑",
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
		function ViewAppItem()
   		{
   			var selectedId = Qm.getValuesByName("id");
   			var gwid = Qm.getValuesByName("gwid");
   			if (selectedId.length < 1) {
   				$.ligerDialog.alert('请先选择要预览打印的简历！', "提示");
   				return;
   			}
   			var url = document.getElementById("purl").value;
   			url = url +"?id="+selectedId+'&gwid='+gwid;
   			var width = 800;
   			var height = 600;
   			var windows = top.$.dialog({
   				width : width,
   				height : height,
   				lock : true,
   				title : "预览打印",
   				data : {
   					"window" : window
   				},
   				content : 'url:'+url
   			}).max();
   		}
   		
</script>
</head>
<body>
<input type="hidden" id="purl" value="<%=request.getSession().getServletContext().getContextPath()+"/app/zp/jlxx/print.do" %>"/>
	<qm:qm pageid="hr_zp_019" script="false" singleSelect="true">
	</qm:qm>
</body>
</html>