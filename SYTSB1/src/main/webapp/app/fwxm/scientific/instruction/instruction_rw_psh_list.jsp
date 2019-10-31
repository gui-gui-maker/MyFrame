<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
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
        { text:'审核', id:'submit',icon:'submit',click:submit},
        "-",
		{ text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory}
     ],
     listeners: {
     	selectionChange : function(rowData,rowIndex){
        	var count = Qm.getSelectedCount(); // 行选择个数
			Qm.setTbar({
				detail: count==1,
				submit: count==1,
				turnHistory:count==1
			});

			if(count==1){
				if(rowData.psh_id!="<sec:authentication property='principal.id' htmlEscape='false' />"){
	        		Qm.setTbar({
	        			submit:false
					});
        		}
			}
			
       },rowAttrRender : function(rowData, rowid) {
    	   var fontColor="black";
    	   console.log(rowData.is_return);
    	   if(rowData.is_return==1){
    		   fontColor="#EEE685";
    	   }
	       return "style='color:"+fontColor+"'";
		},rowDblClick :function(rowData,rowIndex){
			detail(rowData.id);
		}
     }
};

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
			title:"修改",
			data: {window:window},
			content: 'url:app/fwxm/scientific/instruction/instructionRw_tx_detail.jsp?status=modify&type=modify&id='+Qm.getValueByName("id")
		});
     }
     //删除
     function del(){
     	$.del("确定要删除？删除后无法恢复！","equipmentBuyAction/delete.do",{"ids":Qm.getValuesByName("id").toString()});
     } 
     //提交
     function submit(){
    	 top.$.dialog({
 			width: 1000,
 			height: 730,
 			lock:true,
 			title:"审查",
 			data: {window:window},
 			content: 'url:app/fwxm/scientific/instruction/instructionRw_tx_detail.jsp?status=modify&type=modify&id='+Qm.getValueByName("id")+"&tj=1"
 		});
//     	 top.$.dialog({
//   			width: 720,
//   			height: 530,
//   			lock:true,
//   			title:"批准",
//   			data: {window:window},
//   			content: 'url:app/fwxm/scientific/instruction/choose_opinion_rw.jsp?status=modify&type=sh&id='+Qm.getValueByName("id")
//   		});
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
		         content: 'url:app/fwxm/scientific/scientific_remark.jsp?pageStatus=detail&id='+Qm.getValueByName("id")
	          });
     	
     }
</script>
	<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
               <font color="black">“黑色”</font>代表未提交，
                <font color="#EEE685">“黄色”</font>代表退回修改。
            </div>
        </div>
    </div>
</head>
<body><%

	CurrentSessionUser cur_user = SecurityUtil.getSecurityUser();
	User user = (User)cur_user.getSysUser();
%>
	<qm:qm pageid="TJY2_INSTRUCTION_RW" singleSelect="true">
	<qm:param name="status" value="7" compare="=" />
	</qm:qm>
	
</body>
</html>