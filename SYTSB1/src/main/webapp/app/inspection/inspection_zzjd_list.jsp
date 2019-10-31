<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>制造监督检验信息列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">

	
	var unitcode = "<sec:authentication property='principal.department.id' htmlEscape='false' />";
	var userId = "<sec:authentication property='principal.id' htmlEscape='false' />";
	var bar =[];
	var condition=[];
	// 报告录入
 	bar =[{ text:'录入', id:'add',icon:'add', click: add},"-", 	       
 	{ text:'修改', id:'modify',icon:'modify', click: modify},"-",
 	{ text:'删除', id:'del',icon:'delete', click: del},"-",
 	{ text:'提交', id:'commit',icon:'submit', click: commit}]
 	condition=[{name : "enter_op_id", compare : "=", value : userId}]
 	
 	var qmUserConfig = {
		sp_fields:[],
		tbar:bar,
	   	listeners: {
	    	selectionChange : function(rowData,rowIndex){
	       		var count=Qm.getSelectedCount();//行选择个数
	         	Qm.setTbar({modify:count==1,del:count>0,commit:count==1});
	     	},
	    	afterQmReady:function(){
	        	Qm.setCondition(condition);
	      		Qm.searchData();
	   		}
		}
	};

	function modify(){
		top.$.dialog({
			width : 1000, 
			height : 600, 
			lock : true, 
			title:"修改报告录入信息",
			content: 'url:app/inspection/inspection_zzjd_detail.jsp?status=modify&id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}
	
	function del(){
		$.del("确定删除此次录入的信息？亲，删除后系统无法恢复数据哦！",
	    	"inspection/zzjd/del.do",
	    		{"ids":Qm.getValuesByName("id").toString()},function(result){alert('删除成功！')});
	}
	
	function commit(){
		if(confirm("确认提交此次录入的信息？")){
			$("body").mask("正在提交数据，请稍后！");
			$("#commit").attr("disabled","disabled");
			$.getJSON("inspection/zzjd/commit.do?ids="+Qm.getValueByName("id"), function(resp){
				$("body").unmask();
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
	
	function add(){	
		top.$.dialog({
			width : 1000, 
			height : 600, 
			lock : true, 
			title : "报告录入", 
			data : {"window" : window},
			content : 'url:app/inspection/inspection_zzjd_detail.jsp?status=add'
		});
	}
	
	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
	</head>
	<body>
		<%String userId=SecurityUtil.getSecurityUser().getId();%>
		<qm:qm pageid="inspection_zzjd_list" script="false" singleSelect="false">
			<qm:param name="enter_op_id" value="<%=userId%>" compare="=" />
		</qm:qm>
	</body>
</html>
