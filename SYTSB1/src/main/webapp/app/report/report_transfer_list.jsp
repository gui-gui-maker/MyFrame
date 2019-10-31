<%@page import="java.util.Map"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>业务服务部前后台报告交接业务信息列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
	//var userId = "<sec:authentication property='principal.id' htmlEscape='false' />";
	var bar =[];
	//var condition=[];
 	bar =[
 		{ text:'录入', id:'add',icon:'add', click: add},"-", 	  
		{ text:'详情', id:'detail',icon:'detail', click: detail},"-",     
		{ text:'修改', id:'modify',icon:'modify', click: modify},"-",
		{ text:'删除', id:'del',icon:'delete', click: del}
 	]
 	//condition=[{name : "enter_op_id", compare : "=", value : userId}]
 	
 	var qmUserConfig = {
 		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	 			
		sp_fields:[
			{name:"org_id", id:"org_id", compare:"="},
			{name:"com_name", id:"com_name", compare:"like"}, 
			{name:"report_sn",compare:"like"},
			{name:"commit_user_name",compare:"like"},
			{name:"commit_date",compare:"="},
			{name:"receive_user_name",compare:"like"},
	    ],
		tbar:bar,
	   	listeners: {
	    	selectionChange : function(rowData,rowIndex){
	       		var count=Qm.getSelectedCount();//行选择个数
	         	Qm.setTbar({modify:count==1,detail:count==1,del:count>0});
	     	},
			rowDblClick : function(rowData, rowIndex) {
				Qm.getQmgrid().selectRange("id", [rowData.id]);
				detail();
			}/*,
	    	afterQmReady:function(){
	        	//Qm.setCondition(condition);
	      		Qm.searchData();
	   		}*/
		}
	};
	
	// 详情
	function detail(id){
		if($.type(id)!="string"){
			id = Qm.getValueByName("id").toString();
		}		
		top.$.dialog({
			width : 1000, 
			height : 600, 
			lock : true,
			title : "检报告交接业务详情",
			content : 'url:app/report/report_transfer_detail.jsp?status=detail&id='+ id,
			data : {
				"window" : window
			}
		});
	}
	
	function add(){	
		top.$.dialog({
			width : 1000, 
			height : 600, 
			lock : true, 
			title : "新增报告交接业务信息", 
			data : {"window" : window},
			content : 'url:app/report/report_transfer_detail.jsp?status=add'
		});
	}

	function modify(){
		top.$.dialog({
			width : 1000, 
			height : 600, 
			lock : true, 
			title:"修改报告交接业务信息",
			content: 'url:app/report/report_transfer_detail.jsp?status=modify&id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}
	
	function del(){
		var statusArr = Qm.getValuesByName("data_status");	// 数据状态
		for(var i=0;i<statusArr.length;i++){
			if(statusArr[i] == '已签收'){
				$.ligerDialog.error("亲，您所选的数据中，包含已签收的数据，不能删除哦，请重新选择！");
				return;
			}
		}
		$.del("亲，确定删除所选报告交接业务信息吗？删除后系统无法恢复数据哦！",
	    	"report/transfer/del.do",
	    		{"ids":Qm.getValuesByName("id").toString()},function(result){alert('删除成功！')});
	}
	
	function commit(){
		var statusArr = Qm.getValuesByName("data_status");	// 数据状态
		for(var i=0;i<statusArr.length;i++){
			if(statusArr[i] != '创建'){
				$.ligerDialog.error("亲，您所选的数据中，包含已提交的数据，不能重复提交哦，请重新选择！");
				return;
			}
		}
		if(confirm("亲，确认提交所选报告交接业务信息？提交后无法修改数据哦！")){
				$.getJSON("report/transfer/commit.do?ids="+Qm.getValuesByName("id").toString(), function(resp){
					if(resp.success){
						top.$.dialog.notice({
				             content: "提交成功！"
						});
						refreshGrid();
					}else{
						$.ligerDialog.error("提交失败，未找到系统相关业务流程，请联系系统管理员！");
					}
			})
		}
	}
	
	function receive(){
		var statusArr = Qm.getValuesByName("data_status");	// 数据状态
		for(var i=0;i<statusArr.length;i++){
			if(statusArr[i] != '已提交'){
				$.ligerDialog.error("亲，您所选的数据中，包含已接收的数据，不能重复接收哦，请重新选择！");
				return;
			}
		}
		if(confirm("亲，确认接收所选报告交接业务信息吗？接收后无法回退数据哦！")){
				$.getJSON("report/transfer/receive.do?ids="+Qm.getValuesByName("id").toString(), function(resp){
					if(resp.success){
						top.$.dialog.notice({
				             content: "操作成功！"
						});
						refreshGrid();
					}else{
						$.ligerDialog.error("操作失败，未找到系统相关业务流程，请联系系统管理员！");
					}
			})
		}
	}
	
	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
	</head>
	<body>
		<qm:qm pageid="report_transfer_list" singleSelect="false">
		<%
			CurrentSessionUser user = SecurityUtil.getSecurityUser();

			User uu = (User) user.getSysUser();
			String userId = uu.getId();

			Map<String, String> roles = user.getRoles();
			String role_name = "";
			for (String role : roles.values()) {
				//Role role = (Role)value;
				if (role_name.length() > 0) {
					role_name += ",";
				}
				role_name += role;
			}
			if (StringUtil.isNotEmpty(role_name) && !role_name.contains("系统管理员")) {
				%>
				<qm:param name="enter_op_id" value="<%=userId%>" compare="=" />
				<%
			}
		%>

	</qm:qm>
	<script type="text/javascript">
		Qm.config.columnsInfo.org_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ( ORG_CODE like 'cy%' and ORG_CODE != 'cy4_1' and ORG_CODE != 'cy8') order by orders "></u:dict>;
	</script>
	</body>
</html>
