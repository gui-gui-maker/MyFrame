<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>设备申请</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>    
		<script type="text/javascript" src="pub/bpm/js/util.js"></script>
		<script type="text/javascript">
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
            sp_fields:[
            	{name:"device_name", compare:"like"},
            	{name:"device_num", compare:"like"},
            	{name:"specifications", compare:"like"}
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
                { text:'提交', id:'submit',icon:'submit', click:submit},
                "-",
                { text:'处理', id:'deal',icon:'dispose', click:deal},
                "-",
                { text:'查看审批进度', id:'progress',icon:'view-progress', click:progress}
            ],
            listeners: {
             	selectionChange : function(rowData,rowIndex){
                	var count = Qm.getSelectedCount(); // 行选择个数
        			Qm.setTbar({
        				detail: count==1,
        				modify: count==1,	
        				del: count>0,
        				submit: count==1,
        				deal: count==1,
        				progress: count==1
        			});
        			if(count>0){
        				//判断按钮可用情况
        				var status =Qm.getValuesByName("flow_status").toString();
        				var strs= new Array(); //定义一数组
        				strs=status.split(","); //字符分割
        				//已提交的情况
        				for (i=0;i<count;i++){
        					if(strs[i] == '已提交' || strs[i] == '审核中'){
        					Qm.setTbar({
        						modify:false,	
                				del:false,
                				submit: false
        					});
        				}else if(strs[i] == '审核通过'){
        					Qm.setTbar({
        						modify:false,	
                				del:false,
                				submit: false,
                				deal: false
        					});
        				}else if(strs[i] == '审核不通过'){
        					Qm.setTbar({
        						modify:false,
        						submit:false,
        						deal:false
        					});
        				}else{
        					Qm.setTbar({
        						deal:false,
        						progress:false
        					});
        				}
        			}
        			}
               },rowAttrRender : function(rowData, rowid) {
        	       var fontColor="black";
        	       if(rowData.flow_status=='已提交'){
        	       		fontColor="blue";
        	       }else if(rowData.flow_status=='审核中'){
        	       		fontColor="orange";
        	       }else if (rowData.flow_status=='审核通过'){
        	       		fontColor="green";
        	       }else if(rowData.flow_status=='审核不通过'){
        	       		fontColor="red";
        	       }
        	       return "style='color:"+fontColor+"'";
        		},rowDblClick :function(rowData,rowIndex){
        			detail(rowData.id);
        		}
             }
        };
        function submit(){
        	var id = Qm.getValueByName("id");
            if(!id){
                $.ligerDialog.alert("请先选择要提交审核的数据！");
                return;
            }
            $.ligerDialog.confirm('是否提交审核？', function (yes){
            if(!yes){ 
                        return false;
                     }
             $("body").mask("提交中...");
             getServiceFlowConfig("TJY2_EQUIPMENT_MAINTAIN1","",function(result,data){
                    if(result){
                         top.$.ajax({
                             url: "equipMaintainAction/subFolws.do?id="+id+"&flowId="+data.id+"&typeCode=TJY2_EQUIPMENT_MAINTAIN1&status=0",
                             type: "GET",
                             dataType:'json',
                             async: false,
                             success:function (data) {
                                 if (data) {
                                    $.ligerDialog.alert("提交成功！");
                                    Qm.refreshGrid();
                                    $("body").unmask();
                                 }
                             },
                             error:function () {
                                 $.ligerDialog.alert("出错了！请重试！");
                                 $("body").unmask();
                             }
                         });
                    }else{
                     $.ligerDialog.alert("出错了！请重试！");
                     $("body").unmask();
                    }
                    
                 });
            });
        	
        }
        
        function detail(id){
			if($.type(id)!="string"){
				id = Qm.getValueByName("id").toString();
			}
			top.$.dialog({
				width: 800,
				height: 500,
				lock:true,
				title:"查看详情",
				content: 'url:app/equipment/maintain/maintain_apply_detail.jsp?status=detail&id=' + id,
				data:{window:window}
			});
		}
        
        //新增
        function add(){
			top.$.dialog({
				width: 800,
				height: 500,
				lock:true,
				title:"新增",
				data: {window:window},
				content: 'url:app/equipment/maintain/maintain_apply_detail.jsp?status=add'
			});
        }
        
        //修改
        function modify(){
			top.$.dialog({
				width: 800,
				height: 500,
				 parent: api,
				lock:true,
				title:"修改",
				data: {window:window},
				content: 'url:app/equipment/maintain/maintain_apply_detail.jsp?status=modify&id='+Qm.getValueByName("id")
			});
        } 
        
        //删除
        function del(){
        	$.del("确定要删除？删除后无法恢复！","equipMaintainAction/delete.do",{"ids":Qm.getValuesByName("id").toString()});
        }  
        //处理
        function deal() {
        	var list;
        	var id;
        	var title;
        	var service_id = Qm.getValueByName("id");
        	$.ajax({
            	url: "equipMaintainAction/queryMainId.do?id="+service_id,
                type: "POST",
                success: function (resp) {
                	if(resp.success){
                		list = resp.list;
                		id=list[0].id;
            	        title=list[0].title;
            	        //alert(title);
                		var config={
                    	        width :800,
                    	        height : 630,
                    	        id:id,
                    	        title:title
                    	    }
                		// 调用流程的方法
                   	    openWorktask(config);
                	}else{
                		$.ligerDialog.error('没有流程数据！');
                	}
                },
                error: function (data,stats) {
                    $.ligerDialog.error('提示：' + data.msg);
                }
            });
         }
        //查看审批进度
        function progress(){
        	var service_id = Qm.getValueByName("id");
        	trackServiceProcess(service_id);
        }
        
        function parseStatus(thisValue,row){
        	/* var showText = "";
        	if("01" == thisValue){
        		showText = "申请"; 
        	}else if("02" == thisValue){
        		showText = "审核通过"; 
        	}else if("03" == thisValue){
        		showText = "审核不通过"; 
        	}else if("04" == thisValue){
        		showText = "已出库"; 
        	}else if("05" == thisValue){
        		if(row["apply_count"]==row["return_count"]){
        			showText = "已入库"; 
        		}else{
        			showText = "部分入库"; 
        		}
        	}else if("06" == thisValue){
        		showText = "已入库"; 
        	}else if("07" == thisValue){
        		showText = "已结束"; 
        	}
        	return showText; */
        }
        
        // 刷新Grid
        function refreshGrid() {
            Qm.refreshGrid();
        }
    </script>
    	<div class="item-tm" id="titleElement">
        <div class="l-page-title">
            <div class="l-page-title-note">提示：列表数据项
                <font color="black">“黑色”</font>代表未提交，
                <font color="blue">“蓝色”</font>代表已提交，
                <font color="orange">“橙色”</font>代表审核中，
                <font color="green">“绿色”</font>代表审核通过，
                <font color="red">"红色"</font>代表审核不通过。
            </div>
        </div>
    </div>
	</head>
	<body>
		<qm:qm pageid="TJY2_EQUIP_MAINTAIN" singleSelect="true">
		<qm:param name="status" compare="=" value="0"/>
		</qm:qm>
	</body>
</html>

