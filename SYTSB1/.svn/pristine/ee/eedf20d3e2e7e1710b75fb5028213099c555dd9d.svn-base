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
	    {name:"deivce_name", compare:"like"},
	    {name:"department_name", compare:"like"},
	    {name:"head_man", compare:"="}
	],
	tbar:[
    	{ text:'详情', id:'detail',icon:'detail', click: detail},
        "-",
        { text:'增加', id:'add',icon:'add', click: add},
        "-",
        { text:'修改', id:'modify',icon:'modify', click:modify},
        "-",
        { text:'删除', id:'del',icon:'delete', click:del},
        "-",
        { text:'提交', id:'submit',icon:'submit',click:submit},
        "-",
        { text:'打印任务书', id:'print',icon:'print',click:print}
     ],
     listeners: {
     	selectionChange : function(rowData,rowIndex){
        	var count = Qm.getSelectedCount(); // 行选择个数
			Qm.setTbar({
				detail: count==1,
				modify: count==1&&rowData.status=="0",
				del: count>0&&rowData.status=="0",
				submit: count==1&&rowData.status=="0",
				print:count==1
			});
			
       },rowAttrRender : function(rowData, rowid) {
    	   var fontColor="black";
	       if(rowData.status=='1'){
	       		fontColor="blue";
	       }
	       return "style='color:"+fontColor+"'";
		},rowDblClick :function(rowData,rowIndex){
			detail(rowData.id);
		}
     }
};

	function detail(id){
		if($.type(id)!="string"){
			id = Qm.getValueByName("ids").toString();
		}
		top.$.dialog({
			width: 1000,
			height: 730,
			lock:true,
			title:"详情",
			content: 'url:app/fwxm/scientific/instrument/instrument_detail.jsp?status=detail&id=' + id,
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
			content: 'url:app/fwxm/scientific/instrument/instrument_detail.jsp?status=add'
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
			content: 'url:app/fwxm/scientific/instrument/instrument_detail.jsp?status=modify&type=modify&id='+Qm.getValueByName("ids")
		});
     }
     //删除
     function del(){
     	$.del("确定要删除？删除后无法恢复！","com/tjy2/instrumentDevice/deleteBase.do",{"id":Qm.getValuesByName("ids").toString()});
     } 
     //提交
     function submit(){
 		var id = Qm.getValueByName("ids");
 		if(!id){
             $.ligerDialog.alert("请先选择要提交的数据！");
             return;
         }
         $.ligerDialog.confirm('是否提交？', function (yes){
         if(!yes){return false; }
         $("body").mask("提交中...");
         top.$.ajax({
             url: "com/tjy2/instrumentDevice/subAudit.do?id="+id,
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
     function sign(){
     	top.$.dialog({
			width: 720,
			height: 530,
			lock:true,
			title:"合同签订",
			data: {window:window},
			content: 'url:app/equipment/apply/equipment_buy_detail.jsp?status=modify&type=sign&id='+Qm.getValueByName("id")
		});
     }
     function print(){
  		var status=Qm.getValueByName("status");
  		/* if(status=="5"){
  			$.ligerDialog.alert("审批未通过，请重新选择！！！");
  			return;
  		} */
  		var id=Qm.getValueByName("ids");
  		top.$.dialog({
   			width : 1000, 
   			height : 800, 
   			lock : true, 
   			title:"打印仪器设备需求表",
   			content: 'url:app/fwxm/scientific/instrument/instrument_doc.jsp?id='+id,
   			data :{"window" : window}
   		}).max();
  	}
</script>
	<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                 <font color="black">“黑色”</font>代表未提交，
                <font color="blue">“蓝色”</font>代表已提交。
            </div>
        </div>
    </div>
</head>
<body>
	<qm:qm pageid="TJY2_INSTRUMENT_DEV" singleSelect="true"></qm:qm>
</body>
</html>