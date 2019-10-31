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
					{ text:'查看管理评价信息', id:'evaluate',icon:'follow', click: evaluate}
					
					
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({detail:count==1,evaluate:count==1});
	                }
	            },
				rowAttrRender : function(rowData, rowid) {
					var audit_step = rowData.audit_step;
					var fontColor="black";
			     	// 数据状态（2：已录入报告）
					if (audit_step == '1'){
			       		fontColor="orange";
			  		}else if (audit_step == '2'){
			       		fontColor="blue";
			  		}else if (audit_step == '3'){
			       		fontColor="green";
			  		}
			  			
			        return "style='color:"+fontColor+"'";
				}
	};
	
	var org_id = null;

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
		
		
	}
	
	function submitAction(o) {
		Qm.refreshGrid();
	}

	function evaluate(){
		top.$.dialog({
			width : 1000, 
			height : 800, 
			lock : true, 
			title:"查看管理评价信息",
			content: 'url:app/fwxm/contract/contract_evaluate_manager.jsp?id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}
</script>
</head>
<body>
<div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="black">“黑色”</font>代表在起草状态的合同，
				<font color="orange">“橙色”</font>代表审批中的合同。
				<font color="blue">“蓝色”</font>代表审批完成的合同。
				<font color="green">“绿色”</font>代表已经归档的合同。
			</div>
		</div>
	</div>
	<qm:qm pageid="contract_list" script="false" singleSelect="false">
	</qm:qm>
</body>
</html>
