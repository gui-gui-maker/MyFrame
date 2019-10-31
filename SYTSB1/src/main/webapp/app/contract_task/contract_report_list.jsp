<%@page import="java.util.Map"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
%>
	<head>
		<title>合同检验任务单信息列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
	var bar =[	
		{ text:'详情', id:'detail',icon:'detail', click: detail}
 	]
 	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
		sp_fields:[
			{name:"sn", id:"sn", compare:"like"},
			{name:"com_name", id:"com_name", compare:"like"},
			{name:"org_name",compare:"="}
	    ],
		tbar:bar,
	   	listeners: {
	    	selectionChange : function(rowData,rowIndex){
	       		var count=Qm.getSelectedCount();//行选择个数
	         	Qm.setTbar({modify:count==1,detail:count==1,turnHistory:count==1,del:count>0});
	     	},
			rowDblClick : function(rowData, rowIndex) {
				Qm.getQmgrid().selectRange("id", [rowData.id]);
				detail();
			},
			rowAttrRender : function(rowData, rowid) {
	            var fontColor="black";
	            if(rowData.ssje!=rowData.js_money){
	            	fontColor="orange";
	            }
	            
	            var myDate = new Date();
	            if(rowData.cqsj !=null  && rowData.cqsj!=""){
		            var cqsj=new Date(rowData.cqsj);
		            if(cqsj < myDate){
		            	console.log("超期啦"+rowData.cqsj);
		            	fontColor="red";
		            }
	            }
	           	
	            
// 	            var planDate = new Date($.trim($('#planDate').datetimebox('getValue')));
// 	            var applyDate =new Date($.trim($('#applyDate').datetimebox('getValue')));

// 	            if (applyDate > planDate ) {
// 	            $.messager.alert("提示消息", "预计完成时间不能早于提请日期！", 'warning');
// 	            return false;
// 	            }
	            
	            return "style='color:"+fontColor+"'";
			}
		}
	};
	
	// 详情
	function detail(id){
		if($.type(id)!="string"){
			id = Qm.getValueByName("id").toString();
		}		
		winOpen({
			width : 900, 
			height : 550,
			lock : true,
			title : "任务单详情",
			content : 'url:app/contract_task/contract_task_detail_bs.jsp?status=detail&id='+ id,
			data : {
				"window" : window
			}
		}).max();
	}
	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
	</head>
	<body>
	<div class="item-tm" id="titleElement">
    <div class="l-page-title">
        <div class="l-page-title-note">提示：
            <font color="orange">“橙色”</font>代表结算金额与实收金额不一致，
            <font color="red">“红色”</font>代表已经超期。
        </div>
    </div>
</div>
	
	
		<%
		String dep=user.getDepartment().getId();
		Boolean b=!dep.equals("100045") ;
		%>
		<qm:qm pageid="contract_report" singleSelect="false"><!-- script="false"  -->
		<c:if test="<%=b %>">
			<qm:param name="org_id" value="<%=user.getDepartment().getId() %>" compare="=" logic="or"  />
			<qm:param name="cjry_id" value="<%=user.getDepartment().getId() %>" compare="like" logic="or"  />
		</c:if>
		</qm:qm>
<!-- 		<script type="text/javascript"> -->
<%--  			Qm.config.columnsInfo.org_id.binddata=<u:dict sql="select t.id code, t.ORG_NAME text from SYS_ORG t where t.parent_id='100000' and t.property='dep' and t.status='used' order by orders "></u:dict>; --%>
<!-- 		</script> -->
	</body>
</html>
