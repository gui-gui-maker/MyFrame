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
		}
		  ,  "-",
		 {
				text: '修改', id: 'modify', icon: 'modify', click: modify
			} 
		, "-", {
			text: '删除', id: 'del', icon: 'del', click: del
		}],
		
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
		          	alteration: count==1,
		          	cancellation : count>0,
		          	modify:count==1,
		          	del:count>0,
		          	ok:count>0
		       }); 
	        },   
	        rowAttrRender : function(rowData, rowid) {
	        	
	 				}

}
}
		
		
		
		function ok(){
     	top.$.dialog({
	         width: 900,
	         height: 500,
	         lock: true,
	         parent: api,
	         data: {
	       	 window: window
	         },
	         title: "新增",
	         content: 'url:app/fwxm/scientific/scientific_detail_wc.jsp?pageStatus=add&id='+Qm.getValueByName("id")
         });
        
         }
		function del(){
			      $.del("确定删除?", "tjy2ScientificResearchAction/delete.do", {
			       	"ids" : Qm.getValuesByName("id").toString()
			      })
		}
		
		function detail(){
	         top.$.dialog({
		         width: 900,
		         height: 500,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "详情",
		         content: 'url:app/fwxm/scientific/scientific_detail.jsp?pageStatus=detail&id='+Qm.getValueByName("id")+"&status=2"
	          });
	         
        }
		function add(){
			top.$.dialog({
		         width: 800,
		         height: 400,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "新增",
		         content: 'url:app/fwxm/scientific/scientific_detail.jsp?pageStatus=modify&type=rw&id='+Qm.getValueByName("id")
	          });
		}
        function modify(){
        	top.$.dialog({
		         width: 900,
		         height: 500 ,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "修改",
		         content: 'url:app/fwxm/scientific/scientific_detail_wc.jsp?pageStatus=modify&id='+Qm.getValueByName("id")+"&status=2"
	          });
        	
        }
       
       
</script>

</head>
<body>
       <qm:qm pageid="scientific_list_jl">
       </qm:qm>
</body>
</html>