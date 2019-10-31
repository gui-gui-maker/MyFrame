<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>设备采购申请</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript">
var qmUserConfig = {
	sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
    sp_fields:[
	    {name:"project_name", compare:"like"},
	    {name:"rw_number", compare:"like"},
		{group:[
			{name:"project_start_date", compare:">=", value:""},
			{label:"到", name:"project_start_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
		]}
	],
	tbar:[
    	{ text:'详情', id:'detail',icon:'detail', click: detail},
        "-",
		{ text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory}
     ],
     listeners: {
     	selectionChange : function(rowData,rowIndex){
        	var count = Qm.getSelectedCount(); // 行选择个数
			Qm.setTbar({
				detail: count==1,
				modify: count==1&&rowData.status=='填报中',
				del: count>0,
				submit: count==1&&rowData.status=='填报中',
				sign:count==1,
				remark:count==1,
				back:count==1&&rowData.status=='审查中',
				turnHistory:count==1
			});
			
       },rowAttrRender : function(rowData, rowid) {
    	   var newTime = new Date();
    	   var project_start_date = new Date(rowData.project_start_date);
    	   var date3=project_start_date.getTime()-newTime.getTime();
    	   var days=Math.floor(date3/(24*3600*1000));
    	   
    	   
    	   var fontColor="black";
    	   if(rowData.status=='填报中'||rowData.status=='审查中'||rowData.status=='审核中'||rowData.status=='批准中'||rowData.status=='主任审批中'){
	       		fontColor="blue";
	       }else if(rowData.status=='已完成'){
	       		fontColor="green";
	       }
    	   
    	   if(days<=7 && rowData.fileid==""){
    		   fontColor="red";
    	   }
	       return "style='color:"+fontColor+"'";
		},rowDblClick :function(rowData,rowIndex){
			detail(rowData.id);
		}
     }
};
//流转过程
function turnHistory(){	
	top.$.dialog({
			width : 400, 
			height : 700, 
			lock : true, 
			title: "流程卡",
			content: 'url:com/tjy2/instructionRw/getFlowStep.do?ins_info_id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
}
	function detail(id){
		if($.type(id)!="string"){
			id = Qm.getValueByName("id").toString();
		}
		top.$.dialog({
			width: 1000,
			height: 730,
			lock:true,
			title:"详情",
			content: 'url:app/fwxm/scientific/instruction/instructionRw_tx_detail.jsp?status=detail&id=' + id,
			data:{window:window}
		});
	}
        
     //新增
     function add(){
	 	top.$.dialog({
			width: 1000,
			height: 730,
			lock:true,
			title:"采购申请",
			data: {window:window},
			content: 'url:app/fwxm/scientific/instruction/instructionRw_detail.jsp?status=add'
		});
     }
        
     //修改
     function modify(){
		top.$.dialog({
			width: 1000,
			height: 730,
			lock:true,
			title:"填写",
			data: {window:window},
			content: 'url:app/fwxm/scientific/instruction/instructionRw_tx_detail.jsp?status=modify&type=modify&id='+Qm.getValueByName("id")
		});
     }
     //删除
     function del(){
     	$.del("确定要删除？删除后无法恢复！","equipmentBuyAction/delete.do",{"ids":Qm.getValuesByName("id").toString()});
     } 
     //撤回
     function back(){
	   /* if(Qm.getValuesByName("status")="审查中"){ */
		  // $.del("确定要撤回？","com/tjy2/instructionRw/collback.do",{"id":Qm.getValuesByName("id").toString()});
	  /*  } */
	  var con=confirm("确定要撤回");
		  $.getJSON("com/tjy2/instructionRw/collback.do?id=" +Qm.getValuesByName("id"),
							function(resp) {
								if (resp.success) {
									$.ligerDialog.success("操作成功！");
									submitAction();
								} else {
									$.ligerDialog.error(resp.msg);
								}
							})
     } 
     //提交
     function submit(){
 		
 		 $.ligerDialog.confirm("是否提交？",
					function(yes) {
				    if(yes){
				    	$.ajax({
				            url: "com/tjy2/instructionRw/subAudit.do?id="+Qm.getValuesByName("id"),
				            type: "POST",
				           /*    dataType: "json", 
				            contentType: "application/json; charset=utf-8",  */
				            
				            success : function(data, stats) {
								$("body").unmask();
								top.$.notice("提交成功！");
									api.data.window.Qm.refreshGrid();
									api.close();
							},
				            error : function(data) {
				                $("body").unmask();
				                $.ligerDialog.error('保存数据失败！');
				            }
				        });
				    	
				}
			        
			});
 	}
     
     function print(){
 		var status=Qm.getValueByName("status");
 		/* if(status=="5"){
 			$.ligerDialog.alert("审批未通过，请重新选择！！！");
 			return;
 		} */
 		var id=Qm.getValueByName("id");
 		top.$.dialog({
  			width : 1000, 
  			height : 800, 
  			lock : true, 
  			title:"打印作业指导书任务书",
  			content: 'url:app/fwxm/scientific/instruction/instruction_doc.jsp?id='+id,
  			data :{"window" : window}
  		}).max();
 	}
     function remark(){
     	top.$.dialog({
		         width: 500,
		         height: 400 ,
		         lock: true,
		         parent: api,
		         data: {
		       	 window: window
		         },
		         title: "审核说明",
		         content: 'url:app/fwxm/scientific/instruction/instruction_remark.jsp?pageStatus=detail&id='+Qm.getValueByName("id")
	          });
     	
     }
</script>
</head>
<body>
	<qm:qm pageid="INSTRUCTION_RW_TJ" singleSelect="true">
	
	</qm:qm>
	
</body>
</html>