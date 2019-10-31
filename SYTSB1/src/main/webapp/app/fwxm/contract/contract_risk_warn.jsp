<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>单位信息列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields:[
						{name:"contract_no",compare:"like",value:""},
						{name:"project_name",compare:"like",value:""},
						{name:"custom_com_name",compare:"like",value:""}
		            ],
	            tbar:[
	              
					{ text:'查看', id:'detail',icon:'detail', click: detail},
					"-",
					{ text:'归档', id:'file',icon:'archives', click: file},"-",
					{ text:'查看检验报告信息', id:'inspectionL',icon:'detail', click: inspectionL},
					{ text:'查看收费信息', id:'fee',icon:'standing-book', click: fee}
					
					
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({modify:count==1,detail:count==1,submitAudit:count>0,
	                    	del:count>0,printAudit:count==1,addFile:count==1,
	                    	checkAudit:count==1,file:count>0,printAuditV:count==1,
	                    	inspectionL:count==1,fee:count==1,paySure:count>0});
	                }
	            },
				rowAttrRender : function(rowData, rowid) {
					var audit_step = rowData.audit_step;
					var fontColor="red";
			     	/* // 数据状态（2：已录入报告）
					if (audit_step == '1'){
			       		fontColor="orange";
			  		}else if (audit_step == '2'){
			       		fontColor="blue";
			  		}else if (audit_step == '3'){
			       		fontColor="green";
			  		} */
			  			
			        return "style='color:"+fontColor+"'";
				}
	};
	
	var org_id = null;
	function add(){
		
	
			
			org_type=org_id;
			
			
			top.$.dialog({
				width : 800, 
				height : 700, 
				lock : true, 
				title:"新增",
				content: 'url:app/fwxm/contract/contract_detail.jsp?status=add',
				data : {"window" : window}
			});
		
	}
	
	function modify(){
		var id = Qm.getValueByName("id");
		var audit_step = Qm.getValuesByName("audit_step");
		if(audit_step!="0"){
			 $.ligerDialog.warn('已经提交的合同不能修改!');
 		   return;
		}
		top.$.dialog({
			width : 800, 
			height : 700, 
			lock : true, 
			title:"修改",
			content: 'url:app/fwxm/contract/contract_detail.jsp?status=modify&id='+Qm.getValueByName("id")+"&flag=2&device_type="+Qm.getValueByName("big_class"),
			data : {"window" : window}
		});
	}
	function del(){
		var audit_step = Qm.getValuesByName("audit_step");
		for (var i = 0; i < audit_step.length; i++) {
			if(audit_step[i]!='0'){
				 $.ligerDialog.warn('已经提交的合同不能删除!');
	      		   return;
			}
		}
		
		  $.del("确定删除?",
		    		"contractInfoAction/delete.do",
		    		{"ids":Qm.getValuesByName("id").toString()});
    }
	
	function detail(){
		
		top.$.dialog({
			width : 800, 
			height : 600, 
			lock : true, 
			title:"详情",
			content: 'url:app/fwxm/contract/contract_detail.jsp?status=detail&id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}
	
	
	
	function loadGridData(nodeId,unitId,url){
		
		/* org_id=${param.type};
		
		
			
				
				Qm.setCondition([{name:"enter_type",compare:"=",value:nodeId}]);
			
			  			
		
		Qm.searchData(); */
	}
	
	function submitAction(o) {
		Qm.refreshGrid();
	}

	function printAudit(){
		var contract_type =  Qm.getValueByName("contractType");
		top.$.dialog({
			width : 1000, 
			height : 800, 
			lock : true, 
			title:"打印合同审批表",
			content: 'url:app/fwxm/contract/doc_contract.jsp?status=detail&id='+Qm.getValueByName("id")+"&contract_type="+contract_type,
			data : {"window" : window}
		});
	}

	function printAuditV(){
		var contract_type =  Qm.getValueByName("contractType");
		top.$.dialog({
			width : 1000, 
			height : 800, 
			lock : true, 
			title:"打印合同审批表",
			content: 'url:app/fwxm/contract/doc_contract.jsp?status=detail&id='+Qm.getValueByName("id")
					+"&contract_type="+contract_type+"&type=set",
			data : {"window" : window}
		});
	}
	
	/* 确认审批完成  */
	function checkAudit(){
		var id = Qm.getValueByName("id");
		var audit_step = Qm.getValuesByName("audit_step");
		if(audit_step!="1"){
			 $.ligerDialog.warn('请选择审批中的合同!');
 		   return;
		}
		$.ligerDialog.confirm('确定确认审批完成?', function(yes) {
			if (yes) {
				$("body").mask("正在提交数据，请稍后！");
				$.post("contractInfoAction/submbitStep.do",{ids:id,step:"2"},function(res){
					$("body").unmask();
					if(res.success){
						top.$.notice("确认成功！");
						top.$.dialog({
							width : 800, 
							height : 400, 
							lock : true, 
							title:"添加合同附件",
							content: 'url:app/fwxm/contract/file_detail.jsp?status=modify&id='+Qm.getValueByName("id"),
							data : {"window" : window},
							close:function(){
								submitAction();
							}
						});
					}else{
						top.$.notice("确认失败！");
					}
				})
			}
		})
		
	}
	
	function addFile(){
		top.$.dialog({
			width : 800, 
			height : 400, 
			lock : true, 
			title:"添加合同附件",
			content: 'url:app/fwxm/contract/file_detail.jsp?status=modify&id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}
	
	/*  提交审批*/
	function submitAudit(){
		var ids = Qm.getValuesByName("id").toString();
		var audit_step = Qm.getValuesByName("audit_step");
		for (var i = 0; i < audit_step.length; i++) {
			if(audit_step[i]!="0"){
				 $.ligerDialog.warn('请选择没有开始审批的合同!');
      		   return;
			}
		}
		$.ligerDialog.confirm('确定提交审批中?', function(yes) {
			if (yes) {
				$("body").mask("正在提交数据，请稍后！");
				$.post("contractInfoAction/submbitStep.do",{ids:ids,step:"1"},function(res){
					$("body").unmask();
					if(res.success){
						top.$.notice("提交成功！");
						submitAction();
					}else{
						top.$.notice("提交失败！");
					}
				})
			}
		})
		
	}
	
	function file(){
		var ids = Qm.getValuesByName("id").toString();
		var audit_step = Qm.getValuesByName("audit_step");
		for (var i = 0; i < audit_step.length; i++) {
			if(audit_step[i]!="2"){
				 $.ligerDialog.warn('请选择审批完成的合同!');
      		   return;
			}
		}
		$.ligerDialog.confirm('确定归档?', function(yes) {
			if (yes) {
				$("body").mask("正在提交数据，请稍后！");
				$.post("contractInfoAction/submbitStep.do",{ids:ids,step:"3"},function(res){
					$("body").unmask();
					if(res.success){
						top.$.notice("提交成功！");
						submitAction();
					}else{
						top.$.notice("提交失败！");
					}
				})
			}
		})
	}
	
	//查看检验信息
	function inspectionL(){
		top.$.dialog({
			width : 1000, 
			height : 800, 
			lock : true, 
			title:"查看检验信息",
			content: 'url:app/fwxm/contract/contract_inspection_list.jsp?id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}
	
	function fee(){
		top.$.dialog({
			width : 1000, 
			height : 800, 
			lock : true, 
			title:"查看检验信息",
			content: 'url:app/fwxm/contract/contract_payment_list.jsp?id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}
	
	function paySure(){
		var ids = Qm.getValuesByName("id").toString();
		top.$.dialog({
			width : 400, 
			height : 200, 
			lock : true, 
			title:"收费对账",
			content: 'url:app/fwxm/contract/pay_sure_detail.jsp?ids='+ids,
			data : {"window" : window}
		});
	}
</script>
</head>
<body>
	<qm:qm pageid="contract_risk_warn" script="false" singleSelect="false">
	</qm:qm>
</body>
</html>
