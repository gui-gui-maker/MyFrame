<%@page import="com.khnt.utils.StringUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>加班申请</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
       var qmUserConfig = {
       	sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
           sp_fields:[
           	{name:"applicants", compare:"like"}
           	],
           listeners: {
               selectionChange : function(rowData,rowIndex){
    
               },
       		rowDblClick :function(rowData,rowIndex){
       			
       		},
       		rowAttrRender : function(rowData, rowid) {
                  var fontColor="black";
                  if(rowData.status=='99'){
                	  fontColor = "red";
                  }
                  return "style='color:"+fontColor+"'"; 
              }
           }
       };
       
      


   
	
	function f_select()
    { 
		var rows = Qm.getQmgrid().getSelectedRows();
        return rows; 
    }
</script>
</head>
<body>
<div class="item-tm" id="titleElement">
		<div class="l-page-title">
			
		</div>
	</div>	
	<qm:qm pageid="sub_allowance_select" singleSelect="true" >

	</qm:qm>

</body>
</html>
