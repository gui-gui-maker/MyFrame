<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head >
        <title>行政审批待处理</title>
        <%@ include file="/k/kui-base-list.jsp"%>
        <script src="app/rsyd/Item.js" type="text/javascript"></script>
        <script type="text/javascript">
            var qmUserConfig = {
                //自定义查询面板样式参数
                //自定义查询面板样式参数
                    title:"查询",
                    sp_defaults:{columnWidth:0.3,labelAlign:'top',labelSeparator:'',labelWidth:70},// 可以自己定义
                    //自定义查询面板查询字段
                    sp_fields:[
                        {name:"gw_num",compare:"like",value:""},
                        {name:"gw_name",compare:"like",value:""},
                        {name:"yp_name",compare:"like",value:""}
                    ],
                //定义按钮，可以直接是extjs的按钮
                tbar:[
                    { text:'处理', id:'running',icon:'book-5', click:running },
					"-",
                    { text:'流程监控', id:'showFlow',icon:'compute-2', click:showFlow }
                ],
                ////            //提供以下4个事件
                listeners: {
                    rowAttrRender : function(rowData, rowid) {
                    	var opinionId = rowData.opinion_id;
                        if(opinionId){//已签署意见，未提交
							return "style='color: red'";
                        }
                    },
                    rowClick :function(rowData,rowIndex){
                    },
                    rowDblClick :function(rowData,rowIndex){
                    },
                    selectionChange :function(rowData,rowIndex){
                        var count=Qm.getSelectedCount();//行选择个数
                        if(count==0){
                            Qm.setTbar({running:false,showFlow:false});
                        }else if(count==1){
                            Qm.setTbar({running:true,showFlow:true});
                        }else{
                            Qm.setTbar({running:false,showFlow:false});
                        }
                    }
                }
            };
            function running(){
                var operationId = Qm.getValueByName("operationid");
                
                var activityId = Qm.getValueByName("activityid");
                if(!operationId){
                    $.ligerDialog.alert("请先选择要处理的申请事项");
                    return;
                }
                var activityName = escape(Qm.getValueByName("activityname")).replace(/%/g,"\\");
                var processId = Qm.getValueByName("processid");
                var url = 'app/zpmanage/other/inner/apply_detail.jsp'+'?status=detail&id='+operationId+"&processId="+processId+"&activityId="+activityId+"&activityName="+activityName;
                var width=950;
                var height=650;
				var dialogConfig = {
                    width:width,
                    height:height,
                    lock:true,
                    title:"培训进修申请审批",
                    data:{"window":window},
                    content: 'url:'+url
                };
				if(api){
					dialogConfig.parent = api;
				}
                top.$.dialog(dialogConfig);
            }
            function showFlow(){
                var width=top.$(window).width();
                var height=top.$(window).height();
                width=top.$("body").width()*0.8;
                height=top.$("body").height()*0.9;
                var windows=top.$.dialog({
                    width:width,
                    height:height,
                    lock:true,
                    title:"流程监控",
                    data:{"window":window},
                    content: 'url:pub/bpm/FlowTrask_index.jsp?id='+Qm.getValueByName("processid")
                });
            }
            function submitAction(o) {
                Qm.refreshGrid();
            }
        </script>
    </head>
    <body>
        <qm:qm pageid="zpwait" script="false" singleSelect="true" >
            <qm:param name="userId" compare="=" value="${currentSessionUser.id}" />
        </qm:qm>
    </body>
</html>
