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
//         "-",
//         { text:'增加', id:'add',icon:'add', click: add},
        "-",
        { text:'处理', id:'modify',icon:'modify', click:modify},
        "-",
        { text:'删除', id:'del',icon:'delete', click:del},
//         "-",
//         { text:'提交审核', id:'submit1',icon:'submit',click:submit1},
        "-",
        { text:'撤回', id:'back',icon:'back',click:back},
        "-",
        { text:'填写验收日期', id:'modify1',icon:'modify',click:modify1},
//         "-",
//         { text:'提交项目负责人', id:'submit',icon:'submit',click:submit},
        "-",
        { text:'打印任务书', id:'print',icon:'print',click:print},
        "-",
		{ text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory}
     ],
     listeners: {
     	selectionChange : function(rowData,rowIndex){
        	var count = Qm.getSelectedCount(); // 行选择个数/* &&rowData.status=="批准中" */
			Qm.setTbar({
				detail: count==1,
				modify: count==1&&(rowData.status=="未提交"||rowData.status=="批准中"),
				del: count>0,
				submit: count==1&&rowData.status=="任务书审核完成",
				submit1: count==1&&(rowData.status=="未提交"),
				print:count==1&&(rowData.status=="已完成"||rowData.status=="任务书审核完成"),
				modify1:count==1,
				back:count==1&&rowData.status!=="未提交",
				turnHistory:count==1
			});

			
       },rowAttrRender : function(rowData, rowid) {
    	   var fontColor="black";
	       if(rowData.status=='填报中'||rowData.status=='审查中'||rowData.status=='审核中'||rowData.status=='批准中'){
	       		fontColor="blue";
	       }else if(rowData.status=='科技委审核'||rowData.status=='副主任审核'||rowData.status=='主任审核'){
	       		fontColor="orange";
	       }else if(rowData.status=='已完成'){
	       		fontColor="green";
	       }
	       if(rowData.is_return=='1'){
	    	   fontColor="#EEE685";
	       }
	       return "style='color:"+fontColor+"'";
		},rowDblClick :function(rowData,rowIndex){
			detail(rowData.id);
		}
     }
};

// 流转过程
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
			height: 600,
			lock:true,
			title:"详情",
			content: 'url:app/fwxm/scientific/instruction/instructionRw_detail.jsp?status=detail&id=' + id,
			data:{window:window}
		});
	}
        
     //新增
     function add(){
    	 var type= Qm.getValueByName("statuss").toString();
	 	top.$.dialog({
			width: 1000,
			height: 600,
			lock:true,
			title:"新增",
			data: {window:window},
			content: 'url:app/fwxm/scientific/instruction/instructionRw_detail.jsp?status=add&type='+type
		});
     }
        
     //修改
     function modify(){
    	 var type= Qm.getValueByName("statuss").toString();
    	 var rw_number= Qm.getValueByName("rw_number").toString();
		top.$.dialog({
			width: 1000,
			height: 600,
			lock:true,
			title:"处理",
			data: {window:window},
			content: 'url:app/fwxm/scientific/instruction/instructionRw_detail.jsp?status=modify&id='+Qm.getValueByName("id")+"&type="+type+"&tj=0&rw_number="+rw_number
		});
     }
     //填写验收日期
     function modify1(){
    	 var type= Qm.getValueByName("statuss").toString();
		top.$.dialog({
			width: 1000,
			height: 600,
			lock:true,
			title:"填写验收时间",
			data: {window:window},
			content: 'url:app/fwxm/scientific/instruction/instructionRw_ys_detail.jsp?status=modify&id='+Qm.getValueByName("id")+"&type="+type
		});
     }
     //删除
     function del(){
     	$.del("确定要删除？删除后无法恢复！","com/tjy2/instructionRw/delete.do",{"ids":Qm.getValuesByName("id").toString()});
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
// 									submitAction();
				                    Qm.refreshGrid();
								} else {
									$.ligerDialog.error(resp.msg);
								}
							})
     } 
     //提交
     function submit(){
 		var id = Qm.getValueByName("id");
 		if(!id){
             $.ligerDialog.alert("请先选择要提交的数据！");
             return;
         }
         $.ligerDialog.confirm('是否提交项目负责人？', function (yes){
         if(!yes){return false; }
         $("body").mask("提交中...");
         top.$.ajax({
             url: "com/tjy2/instructionRw/subFZR.do?id="+id,
             type: "GET",
             dataType:'json',
             async: false,
             success:function (data) {
                 if (data) {
                	$("body").unmask();
               	 	top.$.notice('提交成功！！！');
                    Qm.refreshGrid();
                 }
             },
             error:function () {
            	 $("body").unmask();
            	 $.ligerDialog.error('出错了！请重试！!');
             }
         });
         });
 	}
     //提交审核
     function submit1(){
 		var id = Qm.getValueByName("id");
 		if(!id){
             $.ligerDialog.alert("请先选择要提交的数据！");
             return;
         }
         $.ligerDialog.confirm('是否提交审核？', function (yes){
         if(!yes){return false; }
         $("body").mask("提交中...");
         top.$.ajax({
             url: "com/tjy2/instructionRw/subAudit1.do?id="+id,
             type: "GET",
             dataType:'json',
             async: false,
             success:function (data) {
                 if (data) {
                	$("body").unmask();
               	 	top.$.notice('提交成功！！！');
                    Qm.refreshGrid();
                 }
             },
             error:function () {
            	 $("body").unmask();
            	 $.ligerDialog.error('出错了！请重试！!');
             }
         });
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
  			height : 600, 
  			lock : true, 
  			title:"打印作业指导书任务书",
  			content: 'url:app/fwxm/scientific/instruction/instruction_doc.jsp?id='+id,
  			data :{"window" : window}
  		}).max();
 	}
</script>
	<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                 <font color="black">“黑色”</font>代表未提交，
                <font color="blue">“蓝色”</font>代表作业指导书流程中，
                  <font color="orange">“橙色”</font>代表任务书流程中，
                <font color="#EEE685">“黄色”</font>代表退回修改
                <font color="green">“绿色”</font>代表已完成。
            </div>
        </div>
    </div>
</head>
<body>
	<qm:qm pageid="TJY2_INSTRUCTION_RW" singleSelect="true"></qm:qm>
	
</body>
</html>