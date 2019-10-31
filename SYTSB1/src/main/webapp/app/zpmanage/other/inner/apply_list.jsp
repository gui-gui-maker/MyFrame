<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head >
        <title>我的申请</title>
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
                        {name:"name",compare:"like",value:""},      
                        {name:"certificates_num",compare:"like",value:""}
                    ],
                //定义按钮，可以直接是extjs的按钮
                tbar:[
					{ text:'详情', id:'detail',icon:'detail', click:detail },
					{line:true},
					{ text:'岗位分配', id:'apply',icon:'add', click:add },
					{line:true},
					{ text:'岗位分配调整', id:'edit',icon:'edit', click:edit },
					{line:true},
                    { text:'审核进度', id:'showFlow',icon:'compute-2', click:showFlow },
                    {
                    	line:true
                    },
                    { text:'打印预览', id:'print',icon:'print', click:print }
                    
                ],
                ////            //提供以下4个事件
                listeners: {
                    rowAttrRender : function(rowData, rowid) {
                    	var status = rowData.status;
                        if(status=='0'){//已签署意见，未提交
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
                            Qm.setTbar({showFlow:false,detail:false,print:false,edit:false});
                        }else if(count==1){
                            Qm.setTbar({showFlow:true,detail:true,print:true,edit:true});
                        }else{
                            Qm.setTbar({showFlow:false,detail:false,print:false,edit:false});
                        }
                    }
                }
            };
            function print()
            {
            	var selectedId = Qm.getValuesByName("id");
        		if (selectedId.length < 1) {
        			$.ligerDialog.alert('请先选择要打印的业务！', "提示");
        			return;
        		}
        		if (selectedId.length > 1) 
        		{
        				$.ligerDialog.alert('请选择一条业务数据打印！', "提示");
        				return;
        		} 
        		var way = Qm.getValuesByName("way");
        		var url = 'app/zp/inner/print.do?id='+selectedId+"&way="+way;
        		var windows = top.$.dialog({
       				width : 900,
       				height : 600,
       				lock : true,
       				title : "查看",
       				data : {
       					"window" : window
       				},
       				content : 'url:'+url
       			}).max();
            }
            function detail()
            {
            	var selectedId = Qm.getValuesByName("id");
            	var status = Qm.getValuesByName("status");
        		if (selectedId.length < 1) {
        			$.ligerDialog.alert('请先选择要查看的事项！', "提示");
        			return;
        		}
        		if (selectedId.length > 1) 
        		{
        				$.ligerDialog.alert('请选择一条数据查看！', "提示");
        				return;
        		} 
            	var url = 'app/zpmanage/other/inner/apply_detail.jsp'+'?status=detail&id='+selectedId+"&personDetail=1"+"&datastatus="+status;
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
            	var selectedId = Qm.getValuesByName("id");
            	var status = Qm.getValuesByName("status");
        		if (selectedId.length < 1) {
        			$.ligerDialog.alert('请先选择要查看的事项！', "提示");
        			return;
        		}
        		if (selectedId.length > 1) 
        		{
        				$.ligerDialog.alert('请选择一条数据查看！', "提示");
        				return;
        		} 
        		if(status == '0')
        		{
        			$.ligerDialog.alert('信息还未提交！', "提示");
    				return;
        		}
        		else
        		{
        			$.post("app/zp/inner/getprocess.do?businessid="+selectedId,function(data)
        		         	{
        						
        						if(data!="")
        						{
        							var processid = data;
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
        			                    content: 'url:pub/bpm/FlowTrask_index.jsp?id='+processid
        			                });
        						}
        						else
        						{
        							$.ligerDialog.alert('获取数据错误！', "提示");
        						}
        		            }
        		            ,"html");
        		}
            }
            function add()
            {
            	var width = 900;
       			var height = 650;
       			var windows = top.$.dialog({
       				width : width,
       				height : height,
       				lock : true,
       				title : "岗位分配",
       				data : {
       					"window" : window
       				},
       				content : 'url:app/zpmanage/other/inner/apply_detail.jsp?status=add'
       			});
            }
            function edit()
            {
            	var selectedId = Qm.getValuesByName("id");
            	var status = Qm.getValuesByName("status");
        		if (selectedId.length < 1) {
        			$.ligerDialog.alert('请先选择要查看的事项！', "提示");
        			return;
        		}
        		if (selectedId.length > 1) 
        		{
        				$.ligerDialog.alert('请选择一条数据查看！', "提示");
        				return;
        		} 
        		if(status != '0')
        		{
        			$.ligerDialog.alert('只能修改未提交的数据！', "提示");
    				return;
        		}
            	var url = 'app/zpmanage/other/inner/apply_detail.jsp'+'?status=modify&id='+selectedId+"&personDetail=1";
            	var width = 900;
       			var height = 650;
       			var windows = top.$.dialog({
       				width : width,
       				height : height,
       				lock : true,
       				title : "岗位分配调整",
       				data : {
       					"window" : window
       				},
       				content : 'url:'+url
       			});
            }
            function submitAction(o) {
                Qm.refreshGrid();
            }
        </script>
    </head>
    <body>
        <qm:qm pageid="zpinner" script="false" singleSelect="true" >
        	
        </qm:qm>
    </body>
</html>
