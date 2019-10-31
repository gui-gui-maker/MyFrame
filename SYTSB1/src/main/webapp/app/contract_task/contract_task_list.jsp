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
// 	var bar =[
//  		{ text:'新增', id:'add',icon:'add', click: add},"-", 	  
// 		{ text:'修改', id:'modify',icon:'modify', click: modify},"-",		
// 		{ text:'详情', id:'detail',icon:'detail', click: detail},"-",   
// 		{ text:'操作日志', id:'turnHistory',icon:'follow', click: turnHistory},"-",   
// 		{ text:'删除', id:'del',icon:'delete', click: del}
//  	]
 	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
		sp_fields:[
			{name:"sn", id:"sn", compare:"like"},
			{name:"com_name", id:"com_name", compare:"like"},
			{name:"org_id",compare:"="}
	    ], <tbar:toolBar type="tbar" code="contract_task_list"> </tbar:toolBar>,
// 		tbar:bar,
	   	listeners: {
	    	selectionChange : function(rowData,rowIndex){
	       		var count=Qm.getSelectedCount();//行选择个数
	         	Qm.setTbar({edit:count==1,detail:count==1,turnHistory:count==1,del:count>0});
	     	},
			rowDblClick : function(rowData, rowIndex) {
				Qm.getQmgrid().selectRange("id", [rowData.id]);
				detail();
			},
	    	/*afterQmReady:function(){
	      		Qm.searchData();
	   		},*/
			rowAttrRender : function(rowData, rowid) {
	            var fontColor="black";
	            // 0：已创建
	            if (rowData.data_status == '0'){
	            	fontColor="black";
	            }
	         	// 1：已修改
	            if (rowData.data_status == '1'){
	            	fontColor="blue";
	            }
	         	// 9：已作废
	            if (rowData.data_status == '99'){
	            	fontColor="red";
	            }
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
			content : 'url:app/contract_task/contract_task_detail.jsp?status=detail&id='+ id,
			data : {
				"window" : window
			}
		}).max();
	}
	
	function add(){	
		var unitcode = 	<sec:authentication property='principal.department.id' htmlEscape='false' />
		winOpen({
			width : 900, 
			height : 550, 
			lock : true, 
			title : "新增任务单", 
			data : {"window" : window},
			content : 'url:app/contract_task/contract_task_detail.jsp?status=add&org_id='+unitcode
		}).max();
	}

	function modify(){
		top.$.dialog({
			width : 900, 
			height : 550,
			lock : true, 
			title:"修改任务单",
			content: 'url:app/contract_task/contract_task_detail.jsp?status=modify&id='+Qm.getValueByName("id"),
			data : {"window" : window}
		}).max();
	}
	
	
	function del(){
		$.ligerDialog.confirm(kui.DEL_MSG, function(yes) {
			if (yes) {
				$.ajax({
					url : "contractTaskInfo/del.do?ids=" + Qm.getValuesByName("id").toString(),
					type : "post",
					async : false,
					success : function(data) {
						if (data.success) {
							top.$.notice("删除成功！");
							refreshGrid();
						} else {
							top.$.notice("删除失败！" + data.msg);
						}
					}
				});
			}
		});
	}
	
	// 操作日志
	function turnHistory(){	
		top.$.dialog({
	   		width : 400, 
	   		height : 700, 
	   		lock : true, 
	   		title: "流程卡",
	   		content: 'url:contractTaskInfo/getFlowStep.do?id='+Qm.getValueByName("id").toString(),
	   		data : {"window" : window}
	   	});
	}
	
	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
	</head>
	<body>
		<div class="item-tm" id="titleElement">
		    <div class="l-page-title">
				<div class="l-page-title-note">提示：列表数据项
					<font color="black">“黑色”</font>代表任务单已创建；
					<font color="blue">“蓝色”</font>代表任务单已修改；
					<font color="red">“红色”</font>代表任务单已作废。
				</div>
			</div>
		</div>
		<%
		String dep=user.getDepartment().getId();
		Boolean b=!dep.equals("100045") ;
		%>
		
		<qm:qm pageid="contract_task_list" singleSelect="false"><!-- script="false"  -->
		<%-- <c:if test="<%=b %>">
		<qm:param name="org_id" value="<%=user.getDepartment().getId() %>" compare="=" logic="or"  />
		<qm:param name="cjry_id" value="<%=user.getDepartment().getId() %>" compare="like" logic="or"  />
		</c:if>--%>
		</qm:qm> 
		<script type="text/javascript">
			Qm.config.columnsInfo.org_id.binddata=<u:dict sql="select t.id code, t.ORG_NAME text from SYS_ORG t where t.parent_id='100000' and t.property='dep' and t.status='used' order by orders "></u:dict>;
		</script>
	</body>
</html>