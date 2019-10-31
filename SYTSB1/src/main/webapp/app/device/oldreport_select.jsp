<%@page import="com.khnt.utils.StringUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>旧模板</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
       var qmUserConfig = {
       	sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
           sp_fields:[
           	{name:"report_name", compare:"like"}
           	],
           listeners: {
               selectionChange : function(rowData,rowIndex){
            	   var flag = Qm.getValuesByName("flag");
            	   if(flag=="1"){
            		   $.ligerDialog.error('已申请过此模版，请直接起草报告并选择使用', function ()
            				   {
            			   Qm.refreshGrid();
            				   });
            		   /* $.ligerDialog.warning("11111", function ()
            				   {
            			   Qm.refreshGrid();
            				   }); */
            	   }

               },
       		rowDblClick :function(rowData,rowIndex){
       			
       		},
       		rowAttrRender : function(rowData, rowid) {
                  var fontColor="black";
                  if (rowData.flag == '0'){
  	            	fontColor="black";
  	            }
  	            if (rowData.flag == '1'){
  	            	fontColor="gray";
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
			<div class="l-page-title-note">提示：状态
               <font color=gray>“灰色”</font>代表该模板已经处于启用状态
			</div>
		</div>
	</div>	
	<qm:qm pageid="report_flagchanges" script="false" singleSelect="false"   >
	</qm:qm>

</body>
</html>
