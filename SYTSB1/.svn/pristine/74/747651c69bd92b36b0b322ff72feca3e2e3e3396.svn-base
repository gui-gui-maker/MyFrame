<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>大厅报检信息列表</title>
<style type="text/css">


</style>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="app/common/js/windowprint.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},		
			sp_fields:[
					{name:"hall_no",compare:"like",value:""},
					{name:"com_name",compare:"like",value:""},
					{name:"device_type",compare:"=",value:""},
					{name:"inspection_type",compare:"=",value:""},
					{name:"reg_op",compare:"=",value:""},
					{group: [{name:"reg_date",compare:">=",value:""},
						{label:"到",name:"reg_date",compare:"<",value:"",labelWidth:20} ]
					 }],
	            tbar:[
					{ text:'查看', id:'detail',icon:'detail', click: detail},
					"-",
					{ text:'新增', id:'add',icon:'add', click: add},
					"-",
					{ text:'修改', id:'modify',icon:'modify', click: modify},
					"-",
					{ text:'提交', id:'sub',icon:'check', click: sub},
					"-",
					//{ text:'打印回执单', id:'print',icon:'print', click: print},//回执单神马的暂时不打印
					//{ text:'打印申请受理书', id:'printAccept',icon:'print', click: printAccept},//回执单神马的暂时不打印
					//{ text:'撤回', id:'recall',icon:'nav-prev', click: recall},// 我们暂时不提供一切撤回的功能
					{ text:'作废', id:'del',icon:'delete', click: del},
					"-",
					{ text:'历史记录', id:'history',icon:'detail', click: history},
					"-",
	 				{ text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory}
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({sub:count>0,modify:count==1,recall:count>0,detail:count==1,del:count>0,print:count==1,printAccept:count==1,turnHistory:count==1});

	                },
		            rowAttrRender : function(rowData, rowid) {
		            	var flag = rowData.status;
		            	//var fontColor="#8B008B";
		            	var fontColor="#000000";
		            	if(flag=="2"){
	            			fontColor="blue";
	            		}
	            		return "style='color:"+fontColor+"'";
	            	}
	            }
	};
	
	
	function add(){
			top.$.dialog({
				width : 800, 
				height : 550, 
				lock : true, 
				title:"添加报检信息",
				content: 'url:app/insing/report_hall_detail.jsp?status=add',
				data : {"window" : window}
			});
		
	}
	
	function sub(){
	   var flag = Qm.getValueByName("status");
		if(flag=="2"){
			top.$.notice("该报检记录已经提交！");
			return;
		}
		$.ligerDialog.confirm("确定提交？",
				function(yes) {
			    if(yes){
			    	$.getJSON("reportHallAction/subDep.do?ids="+Qm.getValuesByName("id"), function(resp){
						
						if(resp.success){
						
							top.$.notice("提交成功！");
							submitAction();
						}else{
							$.ligerDialog.alert(resp.message, "提示");
						}
					});
			}
		        
		});
	}
	
	function modify(){
		var flag = Qm.getValueByName("status");
		if(flag=="2"){
			top.$.notice("已提交，不能修改！");
			return;
		}
		top.$.dialog({
			width : 800, 
			height : 550, 
			lock : true, 
			title:"修改报检信息",
			content: 'url:app/insing/report_hall_detail.jsp?status=modify&id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}
	function del(){
		
		
		  $.del("确定作废?",
		    		"reportHallAction/del.do",
		    		{"ids":Qm.getValuesByName("id").toString()});
    }
	function recall(){
		$.ligerDialog.confirm("确定撤回？",
				function(yes) {
			    if(yes){
				$.getJSON("reportHallAction/recall.do?ids="+Qm.getValuesByName("id"), function(resp){
					if(resp.success){
						top.$.notice("撤回成功！");
						Qm.refreshGrid();
					}else{
						$.ligerDialog.alert(resp.message, "提示");
					}
				});
			}
		        
		});
  }
	
	
	function detail(){
		
		top.$.dialog({
			width : 800, 
			height : 600, 
			lock : true, 
			title:"大厅报检信息",
			content: 'url:app/insing/report_hall_detail.jsp?status=detail&id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}

	function print(){
		var id = Qm.getValueByName("id");
		$.getJSON("reportHallAction/getPrintContent.do?id="+id, function(resp){
			if(resp.success){
				document.getElementById("printDiv").innerHTML=resp.printContent;
				printHtml("printDiv");
			}else{
				$.ligerDialog.error(resp.message);
			}
		})
	}
function printAccept(){
	var id = Qm.getValueByName("id");
	$.getJSON("reportHallAction/getPrintAcceptContent.do?id="+id, function(resp){
		if(resp.success){
			
			document.getElementById("printAcceptDiv").innerHTML=resp.printContent;
			
			printAcceptHtml("printAcceptDiv");
		}else{
			$.ligerDialog.error(resp.message);
		}
	})
}
	function history(){
		
		top.$.dialog({
			width : 800, 
			height : 600, 
			lock : true, 
			title:"历史记录",
			content: 'url:app/insing/report_hall_history.jsp',
			data : {"window" : window}
		}); 
	}

	function printHtml(printDiv){
		
		try{
			pagesetup_null();
			newwin=window.open("", "newwin", "height="+window.screen.height+",width="+window.screen.width+",toolbar=no,scrollbars=auto,menubar=no");
			newwin.document.body.innerHTML=document.getElementById(printDiv).innerHTML;
			
			newwin.window.print();
 			pagesetup_default();
 			newwin.window.close();
		}catch(e){
		}
	}
	
	function printAcceptHtml(printDiv){
		
		try{
			pagesetup_null();
			newwin=window.open("", "newwin", "height="+window.screen.height+",width="+window.screen.width+",toolbar=no,scrollbars=auto,menubar=no");
			newwin.document.body.innerHTML=document.getElementById("printAcceptDiv").innerHTML;
			//alert(newwin.document.body.innerHTML);
			newwin.window.print();
 			pagesetup_default();
 			newwin.window.close();
		}catch(e){
		}
	}
	
	// 流转过程
	function turnHistory(){	
		top.$.dialog({
   			width : 400, 
   			height : 700, 
   			lock : true, 
   			title: "流程卡",
   			content: 'url:reportHallAction/getFlowStep.do?id='+Qm.getValueByName("id"),
   			data : {"window" : window}
   		});
	}
	
	function submitAction(o) {
		Qm.refreshGrid();
	}
	
</script>
</head>
<body >
<!-- <div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="blue">“蓝色”</font>代表报检信息已经提交。
				
			</div>
		</div>
	</div> -->
	
	
	<qm:qm pageid="tzsb_hall_list" script="false" singleSelect="false">
		<qm:param name="flow_status" value="(1,3)" compare="in" />
	</qm:qm>
	<div id="printDiv" style="display:none;"></div>
	<div id="printAcceptDiv" style="display:none;"></div>
</body>
</html>
