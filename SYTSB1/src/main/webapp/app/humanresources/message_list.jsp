<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd"); 
	String nowTime=""; 
	nowTime = dateFormat.format(new Date());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript">
var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义 
		sp_fields:[
				     {name: "send_manner", compare: "like"},
				     {name: "send_date", compare: "like"},
				     {name: "send_man", compare: "like"}
		] , 
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		},
		{
			text: '新增', id: 'add', icon: 'add', click: add
		},
		{
			text: '修改', id: 'modify', icon: 'modify', click: modify
		},
		{
			text: '删除', id: 'del', icon: 'del', click: del
		}
		],
		
        listeners: {
	        rowClick: function(rowData, rowIndex) {
	        	
	        },
	        rowDblClick: function(rowData, rowIndex) {
		      detail();
	        },	
	        selectionChange: function(rowData, rowIndex) {
		         var count = Qm.getSelectedCount();
		         Qm.setTbar({
		          	detail: count==1,
		          	modify:count==1,
		          	del:count>0
			
		       });
	        },
	        rowAttrRender : function(rowData, rowid) {
				}
}
}
		
		function modify(){
	 top.$.dialog({
         width: 500,
         height: 300,
         lock: true,
         parent: api,
         data: {
       	 window: window
         },
         title: "修改",
         content: 'url:app/humanresources/message_detail.jsp?pageStatus=modify&id='+Qm.getValueByName("id")
      });
	
       }
		function add(){
			top.$.dialog({
		         width: 500,
		         height: 300,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "新增",
		         content: 'url:app/humanresources/message_detail.jsp?pageStatus=add&id='+Qm.getValueByName("id")
		      });
		}
		function del(){
			
	      		  $.del("确定删除?",
	      		    		"rlMessageAction/delete.do",
	      		    		{"ids":Qm.getValuesByName("id").toString()});
	  
		}
		function detail(){
	         top.$.dialog({
		         width: 500,
		         height: 300,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "详情",
		         content: 'url:app/humanresources/message_detail.jsp?pageStatus=detail&id='+Qm.getValueByName("id")
	          });
        }
		$(function(){
	        importData();
	    });
		function importData() {
		  //创建上传实例
		  khFileUploader = new KHFileUploader({
		      fileSize: "10mb",//文件大小限制
		      buttonId: "importData",//上传按钮ID
		      container: "filecontainer3",//上传控件容器ID
		      title: "固定资产",//文件选择框提示
		      saveDB : false,
		      extName: "xls,xlsx",//文件扩展名限制
		      fileNum: 1,//限制上传文件数目
		      callback : function(files){
		                  //文件无误，执行保存
		                  saveData(files);
		      }
		  });
		}

	  //上传完成，开始保存汇编数据
	  function saveData(files){
		  $("body").mask("正在上传...");
		  $.post("employeeBaseAction/saveTask.do",{files:$.ligerui.toJSON(files)},function (data) {
		          $("body").unmask();
		          if (data.success) {
	                    $.ligerDialog.success("成功导入&nbsp;"+"<span style='color:green;'>"+data.total+"</span>"+"&nbsp;条固定资产!");
		              Qm.refreshGrid();
		          } else {
		        	  $.ligerDialog.error("保存失败！<br/>");
		          }
		     },"json");
	  }
</script>

</head>
<body>

<p id="filecontainer3" style="margin:5px">
		<a class="l-button-warp l-button" id="importData">
			<span class="l-button-main l-button-hasicon">
				<span class="l-button-text">导入年假信息</span>
				<span class="l-button-icon iconfont l-icon-excel-import"></span>
			</span>
		</a>
	</p>
       <qm:qm pageid="TJY2_RL_MESSAGE">
       </qm:qm>
</body>
</html>