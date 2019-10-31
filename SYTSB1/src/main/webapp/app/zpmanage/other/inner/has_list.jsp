<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head >
        <title>已处理业务</title>
        <%@ include file="/k/kui-base-list.jsp"%>
        <script src="app/rsyd/Item.js" type="text/javascript"></script>
        <script type="text/javascript">
            var qmUserConfig = {
                
                    //自定义查询面板样式参数
                    title:"查询",
                    sp_defaults:{columnWidth:0.3,labelAlign:'top',labelSeparator:'',labelWidth:70},// 可以自己定义
                    //自定义查询面板查询字段
                    sp_fields:[
                        {name:"gw_num",compare:"like",value:""},
                        {name:"gw_name",compare:"like",value:""},
                        {name:"yp_name",compare:"like",value:""}
                    ],
                tbar:[
					{ text:'详细信息', id:'detail',icon:'detail', click:detail },
					{line:true},
                    { text:'流程监控', id:'showFlow',icon:'compute-2', click:showFlow }
                ],
                ////            //提供以下4个事件
                listeners: {
                    rowAttrRender : function(rowData, rowid) {
							
                    },
                    rowClick :function(rowData,rowIndex){
                    },
                    rowDblClick :function(rowData,rowIndex){
                    },
                    selectionChange :function(rowData,rowIndex){
                        var count=Qm.getSelectedCount();//行选择个数
                        if(count==0){
                            Qm.setTbar({showFlow:false,detail:false});
                        }else if(count==1){
                            Qm.setTbar({showFlow:true,detail:true});
                        }else{
                            Qm.setTbar({showFlow:false,detail:false});
                        }
                    }
                }
            };
            function detail()
            {
            	var selectedId = Qm.getValuesByName("id");
        		if (selectedId.length < 1) {
        			$.ligerDialog.alert('请先选择要查看的事项！', "提示");
        			return;
        		}
        		if (selectedId.length > 1) 
        		{
        				$.ligerDialog.alert('请选择一条数据查看！', "提示");
        				return;
        		} 
            	var processid = Qm.getValueByName("processid");
            	
            	var url = 'app/zpmanage/other/inner/apply_detail.jsp'+'?status=detail&id='+selectedId+"&personDetail=1";
            	var width = 900;
       			var height = 650;
       			var windows = top.$.dialog({
       				width : width,
       				height : height,
       				lock : true,
       				title : "查看",
       				data : {
       					"window" : window
       				},
       				content : 'url:'+url
       			});
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
        <qm:qm pageid="zphas" script="false" singleSelect="true" >
        	<qm:param name="userid" value="${currentSessionUser.id}" compare="=" />
        </qm:qm>
    </body>
</html>
