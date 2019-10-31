<%@page import="java.util.Map"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="java.util.HashMap"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>科研项目管理-任务书填写</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">

var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},		
		sp_fields:[
				     {name: "project_name", compare: "like"},
				     {name: "project_department", compare: "like"},
				     {name: "project_head", compare: "like"}
		] , 
		 tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		},  "-",
		  {text: '新增', id: 'add', icon: 'add', click: add } 
		  , "-", {
				text: '修改', id: 'modify', icon: 'modify', click: modify
			} , "-", {
			text: '提交', id: 'sub', icon: 'sub', click: sub
		}] ,
		
        listeners: {
	        rowClick: function(rowData, rowIndex) {
	        	
	        },
	        rowDblClick: function(rowData, rowIndex) {
		      //detail();
	        },	
	        selectionChange: function(rowData, rowIndex) {
	        	    var count = Qm.getSelectedCount();
		            Qm.setTbar({
		          	detail: count==1,
		          	modify:count==1&&flag&&flag3,
		          	sub:count>0&&flag4&&flag3,
		       }); 
	        },   
	        rowAttrRender : function(rowData, rowid) {
	        	 var fontColor="black";
	        	 if (rowData.status=='1'){
	        		 fontColor="blue";
	        	 }
	        	 if (rowData.status=='2'&&rowData.audit_status=='0'){
	        		 fontColor="blue";
	        	 }
	        	 if (rowData.status=='2'&&rowData.audit_status=='1'){
	        		 fontColor="green";
	        	 }
	        	 if(rowData.status=='2'&&rowData.audit_status==''){
	        		 fontColor="green";
	        	 }
	        	 if((rowData.status=='0'||rowData.status=='')&&rowData.project_department=='1'){
	        		 fontColor="red";
	        	 }
	        	 return "style='color:"+fontColor+"'";
	 				}

}
}
		function sub (){}
		function modify (){
	
}
function add(){
	
}

function detail(){
//      top.$.dialog({
//          width: 1000,
//          height: 500,
//          lock: true,
//          parent: api,
//          data: {
//        	 window: window
//          },
//          title: "详情",
//          content: 'url:app/fwxm/scientific/scientific_detail.jsp?pageStatus=detail&id='+Qm.getValueByName("id")
//       });
     
}

</script>

</head>
<body>
 <div class="item-tm" id="titleElement">
		<div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="black">“黑色”</font>代表任务书未下达，
                <font color="blue">“蓝色”</font>代表任务书已下达，
                <font color="green">“绿色”</font>代表任务书完。
			</div>
		</div>
	</div>
       <qm:qm pageid="KYTaskBook" singleSelect="false"></qm:qm>
</body>
</html>