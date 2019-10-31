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
                { text:'提交', id:'save',icon:'submit', click:save},
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
        				save: count==1,
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
        					if(strs[i] == '已提交'){
        					Qm.setTbar({
        						modify:false,	
                				del:false,
                				save: false
        					});
        				}else if(strs[i] == '审核通过'){
        					Qm.setTbar({
        						modify:false,	
                				del:false,
                				save: false,
                				deal:false
        					});
        				}else if(strs[i] == '审核不通过'){
        					Qm.setTbar({
        						modify:false,
        						save:false,
        						deal:false
        					});
        				}
        			}
        			}
               },rowAttrRender : function(rowData, rowid) {
        	       var fontColor="black";
        	       if(rowData.flow_status=='已提交'){
        	       		fontColor="blue";
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
        function save(){
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
             getServiceFlowConfig("TJY2_EQUIPMENT_ACCEPTANCE","",function(result,data){
                    if(result){
                         top.$.ajax({
                             url: "equipMaintainAction/subFolws.do?id="+id+"&flowId="+data.id+"&typeCode=TJY2_EQUIPMENT_ACCEPTANCE&status=1",
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
        // 行选择改变事件
		function selectionChange() {
	   		var count = Qm.getSelectedCount(); // 行选择个数
	   		Qm.setTbar({
    			modify: count==1, 
    			del: count>0, 
    			detail: count==1,
    			save: count==1
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
				content: 'url:app/equipment/maintain/maintain_acceptance_detail.jsp?status=detail&id=' + id,
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
				content: 'url:app/equipment/maintain/maintain_acceptance_detail.jsp?status=add'
			});
        }
        
        //修改
        function modify(){
			top.$.dialog({
				width: 800,
				height: 500,
				lock:true,
				title:"修改",
				data: {window:window},
				content: 'url:app/equipment/maintain/maintain_acceptance_detail.jsp?status=modify&id='+Qm.getValueByName("id")
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
                <font color="green">“绿色”</font>代表审核通过，
                <font color="red">"红色"</font>代表审核不通过。
            </div>
        </div>
    </div>
	</head>
	<body>
		<qm:qm pageid="TJY2_EQUIP_MAINTAIN1" singleSelect="true">
		<qm:param name="status" compare="=" value="1"/>
		</qm:qm>
	</body>
</html>
