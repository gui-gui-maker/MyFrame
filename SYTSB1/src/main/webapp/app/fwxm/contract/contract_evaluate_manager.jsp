<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>单位信息列表</title>
<%

CurrentSessionUser user = SecurityUtil.getSecurityUser();

String user_id = user.getId();
%>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields:[
						{name:"evaluate_op",compare:"like",value:""},
						{name:"evaluate_date",compare:"like",value:""}
		            ],
	            tbar:[
	              
					{ text:'查看', id:'detail',icon:'detail', click: detail},
					{ text:'新增', id:'add',icon:'add', click: add},
					{ text:'修改', id:'modify',icon:'modify', click: modify},
					{ text:'删除', id:'del',icon:'del', click: del}
					
					
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({detail:count==1,modify:count==1,del:count>0});
	                }
	            },
				rowAttrRender : function(rowData, rowid) {
					var audit_step = rowData.audit_step;
					var fontColor="black";/* 
			     	// 数据状态（2：已录入报告）
					if (audit_step == '1'){
			       		fontColor="orange";
			  		}else if (audit_step == '2'){
			       		fontColor="blue";
			  		}else if (audit_step == '3'){
			       		fontColor="green";
			  		}
			  			 */
			        return "style='color:"+fontColor+"'";
				}
	};
	
	var org_id = null;

	function detail(){
		
		top.$.dialog({
			width : 600, 
			height : 500, 
			lock : true, 
			title:"详情",
			content: 'url:app/fwxm/contract/contract_evaluate_detail.jsp?status=detail&id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}
	
	
	
	function loadGridData(nodeId,unitId,url){
		
		
	}
	
	function submitAction(o) {
		Qm.refreshGrid();
	}

	function modify(){
		var evaluate_op_id = Qm.getValueByName("evaluate_op_id");
		if(evaluate_op_id!="<%=user_id%>"){
			 $.ligerDialog.warn('不能修改别人的评价!');
    		   return;
		}
		
		top.$.dialog({
			width : 600, 
			height : 500, 
			lock : true, 
			title:"查看管理评价信息",
			content: 'url:app/fwxm/contract/contract_evaluate_detail.jsp?status=modify&id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}
	
	function del(){
		var evaluate_op_id = Qm.getValuesByName("evaluate_op_id");
		for (var i = 0; i < evaluate_op_id.length; i++) {
			if(evaluate_op_id[i]!="<%=user_id%>"){
				 $.ligerDialog.warn('不能删除别人的评价!');
	      		   return;
			}
		}
		
		  $.del("确定删除?",
		    		"contractEvaluateAction/delete.do",
		    		{"ids":Qm.getValuesByName("id").toString()});
    }
	

	function add(){
		
		top.$.dialog({
			width : 600, 
			height : 500, 
			lock : true, 
			title:"查看管理评价信息",
			content: 'url:app/fwxm/contract/contract_evaluate_detail.jsp?status=add&contract_id=${param.id}',
			data : {"window" : window}
		});
	}
</script>
</head>
<body>
<div class="item-tm" id="titleElement">
	<qm:qm pageid="contract_evaluate" script="false" singleSelect="false">
		<qm:param name="contract_id" value="${param.id }" compare="=" />
	</qm:qm>
</body>
</html>
