<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="q" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>通用查询</title>
    <%@include file="/k/kui-base-list.jsp"%>
	<script type="text/javascript">
			var qmUserConfig = {
				 sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:80},
				 sp_fields:[
				       {label:"图表ID",name:"chartid",compare:"like",value:""},
				  	   {group:[{label:"更新日期从",name:"modtime",compare:">=",value:"",width:"100"},{label:"到",name:"modtime",compare:"<=",value:"",labelAlign:"center",labelWidth:20}]}
				], 
	            tbar:[
		            { text:'详情', id:'detail',icon:'detail',click:detail},
		            "-",
	                { text:'新增', id:'add',icon:'add', click:add},
		            "-",
	                { text:'修改', id:'modify',icon:'modify', click:modifyConfig},
		            "-",
	                { text:'删除', id:'del',icon:'del', click:deleteCofnig},
	                "-",
                    { text:'复制 ', id:'copy',icon:'copy', click:copy },
                    "-",
                    { text:'标签测试 ', id:'tag',icon:'detail', click:tag }
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({
	                    	detail: count==1,
	                    	del: count>0,
	                    	modify: count==1,
	                    	allow: count==1,
	                    	copy: count==1,
	                    	tag:count==1
	                    });
	                },
            		rowDblClick :function(rowData,rowIndex){
            			detail(rowData.id);
            		}
	            }
	        };
			
			function detail(id){
				if($.type(id)!="string")
					id = Qm.getValueByName("id").toString();
				top.$.dialog({
					width:'80%',
					height:'80%',
					lock:true,
					title:"图表详情",
					content: 'url:pub/chart/chart_detail.jsp?status=detail&id=' + id,
					data:{window:window},
					cancel:true
				});
			}
        	
			//新增数据
        	function add(){
				top.$.dialog({
					width:'80%',
					height:'80%',
					lock:true,
					title:"新增图表",
					content: 'url:pub/chart/chart_detail.jsp?status=add',
					data:{window:window}
				});
			}
			//修改数据
			function modifyConfig(){
				top.$.dialog({
					width:'80%',
					height:'80%',
					lock:true,
					title:"修改图表",
					content: 'url:pub/chart/chart_detail.jsp?status=modify&id=' + Qm.getValueByName("id"),
					data:{window:window}
				});
			}
			//删除数据
			function deleteCofnig(){
			    $.del("确定删除?",
			    		"chart/chart/remove.do",
			    		{"ids":Qm.getValuesByName("id").toString()});
		    }
			//复制
    		function copy(){
    			var id = Qm.getValueByName("id");
                if(!id){
                    $.ligerDialog.alert("请先选择要复制的数据！");
                    return;
                }
    			top.$.dialog({
    				title:"复制",
    				width : 300,
    				height: 200,
    				lock : true,
    				data : {window:window},
    				content : "url:app/chart/chart_copy.jsp?id=" + Qm.getValuesByName("id").toString()
    			});
    		}
    		function modifyApp(id){
                var status = "modify";
                var windows=top.$.dialog({
    				width:'80%',
    				height:'80%',
                    lock:true,
                    title:"修改图表",
                    content: 'url:pub/chart/chart_detail.jsp?status='+status+'&id='+id,
                    data: {"window": window}
                });
            }
    		function tag(){
    			var id = Qm.getValueByName("chartid");
    			 var windows=top.$.dialog({
     				width:'80%',
     				height:'80%',
                     lock:true,
                     title:"标签显示",
                     content: 'url:pub/chart/tag_detail.jsp?status='+status+'&chartId='+id,
                     data: {"window": window}
                 });
    		}
    		
        </script>
</head>
<body>
<q:qm pageid="pub_chart" singleSelect="false">
</q:qm>
</body>
</html>