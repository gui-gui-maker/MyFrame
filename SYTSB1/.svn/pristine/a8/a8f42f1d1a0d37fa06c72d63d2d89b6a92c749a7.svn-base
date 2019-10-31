<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>加班申请审核</title>
<%@include file="/k/kui-base-list.jsp"%>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
%>
<link type="text/css" rel="stylesheet"
	href="app/qualitymanage/css/form_detail.css" />
<script type="text/javascript">
       var qmUserConfig = {
       	sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
       	sp_fields:[
					{name:"other_applicants", id:"other_applicants", compare:"like"},
					{name:"department", id:"department", compare:"like"},
					{name:"overtime_type", id:"overtime_type", compare:"like"}
		        ],
			        
           tbar:[
            { text:'打印加班补助通知单', id:'printW',icon:'print', click: printW}
           	
           ],
           listeners: {
               selectionChange : function(rowData,rowIndex){
               	selectionChange();
               },
       		rowDblClick :function(rowData,rowIndex){
       			detail(rowData.id);
       		},
			rowAttrRender : function(rowData, rowid) {
				var step = rowData.flowStep;
		       		var fontColor="black";
		       		/* if (step!="0"){
		       			fontColor="green"; */
	       		return "style='color:"+fontColor+"'";
			//} 
           }
       }
       }  
       // 行选择改变事件
	function selectionChange() {
   		var count = Qm.getSelectedCount(); // 行选择个数
     	if(count == 1){
            Qm.setTbar({detail: true,  modify: true, print: true ,del: true,planDoc:true,submit:true});            	
 		}else if(count > 1){
       		Qm.setTbar({detail: false, modify: false,del: true,planDoc:false,submit:false});
    	}else{
    		Qm.setTbar({detail: false,  modify: false,del: false,planDoc:false,submit:false});
    	}
	}
	

	function submitAction() {
		Qm.refreshGrid();
	}
       
	function closewindow(){
		api.close();
	}
	
	function printW(){
		top.$.dialog({
			width : 1000, 
			height : 800, 
			lock : true, 
			title:"打印加班补助通知单",
			content: 'url:app/humanresources/overtime_allowance/overtime_allowance_print.jsp?status=detail&id='
					+Qm.getValueByName("fk_plan_id")+"&opId="+Qm.getValueByName("other_applicants_id"),
			data : {"window" : window,startDate:api.data.startDate,endDate:api.data.endDate}
		});
	}
	
</script>
</head>
<body>	
	<!-- <div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="green">“绿色”</font>代表已提交审核的申请表。
			</div>
		</div>
	</div>  -->
	<qm:qm pageid="op_allowance" singleSelect="false" >
		<qm:param name="fk_plan_id" value="${param.id }" compare="=" />
	</qm:qm>
</body>
</html>
