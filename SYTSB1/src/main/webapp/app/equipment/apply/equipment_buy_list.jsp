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
	    {name:"apply_name", compare:"like"},
	    {name:"apply_unit_name", compare:"like"},
	    {name:"apply_status", compare:"="}
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
        { text:'合同签订', id:'sign',icon:'edit',click:sign},
        "-",
        { text:'采购确认', id:'buy',icon:'bookpen',click:buy},
        "-",
        { text:'验货确认', id:'inspect',icon:'check',click:inspect},
        "-",
        { text:'入库确认', id:'instockOk',icon:'check',click:instockOk}
     ],
     listeners: {
     	selectionChange : function(rowData,rowIndex){
        	var count = Qm.getSelectedCount(); // 行选择个数
			Qm.setTbar({
				detail: count==1,
				modify: count==1,
				del: count>0,
				submit: count==1,
				sign:count==1,
				buy:count==1,
				inspect:count==1,
				instockOk:count==1
			});
			if(count>0){
				//判断按钮可用情况
				var status =Qm.getValuesByName("apply_status").toString();
				var strs= new Array(); //定义一数组
				strs=status.split(","); //字符分割
				//按钮控制
				for (i=0;i<count;i++){
					if(strs[i] == '未提交'){
					Qm.setTbar({
						sign:false,
						buy:false,
						inspect:false,
						instockOk:false
					});
				}else if(strs[i] == '待审核'||strs[i] == '审核中' ||strs[i] == '已完成' ){
					Qm.setTbar({
						modify:false,
						del:false,
						submit:false,
						sign:false,
						buy:false,
						inspect:false,
						instockOk:false
					});
				}else if(strs[i] == '审核不通过'){
					Qm.setTbar({
						modify:false,
						submit:false,
						sign:false,
						buy:false,
						inspect:false,
						instockOk:false
					});
				}else if(strs[i] == '审核通过'){
					Qm.setTbar({
						modify:false,
						del:false,
						submit:false,
						buy:false,
						inspect:false,
						instockOk:false
					});
				}else if(strs[i] == '已签合同'){
					Qm.setTbar({
						modify:false,
						del:false,
						submit:false,
						sign:false,
						inspect:false,
						instockOk:false
					});
				}else if(strs[i] == '已采购'){
					Qm.setTbar({
						modify:false,
						del:false,
						submit:false,
						sign:false,
						buy:false,
						instockOk:false
					});
				}else if(strs[i] == '已验货'){
					Qm.setTbar({
						modify:false,
						del:false,
						submit:false,
						sign:false,
						buy:false,
						inspect:false
					});
				}
			}
			}
       },rowAttrRender : function(rowData, rowid) {
	       var fontColor="black";
	       if(rowData.apply_status=='待审核'){
	       		fontColor="blue";
	       }else if(rowData.apply_status=='审核中'){
	       		fontColor="orange";
	       }else if (rowData.apply_status=='审核通过'||rowData.apply_status=='已签合同'||rowData.apply_status=='已采购'||rowData.apply_status=='已验货'){
	       		fontColor="green";
	       }else if(rowData.apply_status=='审核不通过'){
	       		fontColor="red";
	       }else if(rowData.apply_status=='已完成'){
	         	fontColor="#6F00D2";
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
			width:720,
			height:530,
			lock:true,
			title:"详情",
			content: 'url:app/equipment/apply/equipment_buy_detail.jsp?status=detail&id=' + id,
			data:{window:window}
		});
	}
        
     //新增
     function add(){
	 	top.$.dialog({
			width: 720,
			height: 530,
			lock:true,
			title:"采购申请",
			data: {window:window},
			content: 'url:app/equipment/apply/equipment_buy_detail.jsp?status=add'
		});
     }
        
     //修改
     function modify(){
		top.$.dialog({
			width: 720,
			height: 530,
			lock:true,
			title:"修改",
			data: {window:window},
			content: 'url:app/equipment/apply/equipment_buy_detail.jsp?status=modify&type=modify&id='+Qm.getValueByName("id")
		});
     }
     //删除
     function del(){
     	$.del("确定要删除？删除后无法恢复！","equipmentBuyAction/delete.do",{"ids":Qm.getValuesByName("id").toString()});
     } 
     //提交
     function submit(){
 		var id = Qm.getValueByName("id");
 		if(!id){
             $.ligerDialog.alert("请先选择要提交审核的数据！");
             return;
         }
         $.ligerDialog.confirm('是否提交审核？', function (yes){
         if(!yes){return false; }
         $("body").mask("提交中...");
          //获取业务流程
          getServiceFlowConfig("TJY2_EQUIPMENT_BUY","",function(result,data){
         	 if(result){
                  top.$.ajax({
                      url: "equipmentBuyAction/subFolw.do?id="+id+"&flowId="+data.id+"&typeCode=TJY2_EQUIPMENT_BUY&status="+status,
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
             }else{
             	$.ligerDialog.error('出错了！请重试！'); 
              $("body").unmask();
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
	function buy(){
		/* top.$.dialog({
			width: 720,
			height: 530,
			lock:true,
			title:"采购确认",
			data: {window:window},
			content: 'url:app/equipment/apply/equipment_buy_detail.jsp?status=modify&type=buy&id='+Qm.getValueByName("id")
		}); */
		var id = Qm.getValueByName("id");
 		if(!id){
             $.ligerDialog.alert("请先选择已采购的数据！");
             return;
         }
         $.ligerDialog.confirm('确认已采购？', function (yes){
	         if(!yes){return false; }
	         $("body").mask("提交中...");
	          //获取业务流程
	          top.$.ajax({
	              url: "equipmentBuyAction/confirmed.do?id="+id,
	              type: "GET",
	              dataType:'json',
	              async: false,
	              success:function (data) {
	                  if (data) {
	                 	$("body").unmask();
	                	 	top.$.notice('操作成功！！！');
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
	function inspect(){
		/* top.$.dialog({
			width: 720,
			height: 530,
			lock:true,
			title:"验货",
			data: {window:window},
			content: 'url:app/equipment/apply/equipment_buy_detail.jsp?status=modify&type=inspect&id='+Qm.getValueByName("id")
		}); */
		var id = Qm.getValueByName("id");
 		if(!id){
             $.ligerDialog.alert("请先选择要已验货的数据！");
             return;
         }
         $.ligerDialog.confirm('确认已验货？', function (yes){
	         if(!yes){return false; }
	         $("body").mask("提交中...");
	          //获取业务流程
	          top.$.ajax({
	              url: "equipmentBuyAction/examine.do?id="+id,
	              type: "GET",
	              dataType:'json',
	              async: false,
	              success:function (data) {
	                  if (data) {
	                 	$("body").unmask();
	                	 	top.$.notice('操作成功！！！');
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
	function instockOk(){
		var id = Qm.getValueByName("id");
 		if(!id){
             $.ligerDialog.alert("请先选择要已验货的数据！");
             return;
         }
         $.ligerDialog.confirm('确认已入库？', function (yes){
	         if(!yes){return false; }
	         $("body").mask("提交中...");
	          //获取业务流程
	          top.$.ajax({
	              url: "equipmentBuyAction/instockOk.do?id="+id,
	              type: "GET",
	              dataType:'json',
	              async: false,
	              success:function (data) {
	                  if (data) {
	                 	$("body").unmask();
	                	 	top.$.notice('操作成功！！！');
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
</script>
	<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="black">“黑色”</font>代表未提交，
                <font color="blue">“蓝色”</font>代表待审核，
                <font color="orange">“橙色”</font>代表审核中，
                <font color="green">“绿色”</font>代表审核通过，
                <font color="red">"红色"</font>代表审核不通过。
                <font color="#6F00D2">“紫色”</font>代表已完成。
            </div>
        </div>
    </div>
</head>
<body>
	<qm:qm pageid="TJY2_EQ_BUY_LIST" singleSelect="true"></qm:qm>
</body>
</html>