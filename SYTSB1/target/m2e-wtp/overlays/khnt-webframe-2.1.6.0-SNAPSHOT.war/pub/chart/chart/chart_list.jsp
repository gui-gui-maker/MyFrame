<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="q" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>通用查询</title>
    <%@include file="/k/kui-base-list.jsp"%>
	<script type="text/javascript">
			var selectdTypeId="${param.typeId }";
			var qmUserConfig = {
				 sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:80},
				 sp_fields:[
				       {label:"图表编号",name:"chart_num",compare:"like",value:""},
				       {label:"图表名称",name:"chart_name",compare:"like",value:""}
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
                    { text:'导出 ', id:'exp',icon:'export', click:exportChart },
                    "-",
                    { text:'导入 ', id:'imp',icon:'basket-put', click:importChart }
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
	                    	exp:count>0
	                    });
	                },
            		rowDblClick :function(rowData,rowIndex){
            			detail(rowData.id);
            		},
                    rowHeight:110
                }
	        };
			
			function detail(id){
				var selectdTypeId = Qm.getValueByName("fk_chart_type");
				if($.type(id)!="string")
					id = Qm.getValueByName("id").toString();
				top.$.dialog({
					width:'80%',
					height:'80%',
					lock:true,
					title:"图表详情",
					content: 'url:pub/chart/chart/chart_detail.jsp?status=detail&id=' + id+'&typeId='+selectdTypeId,
					data:{window:window},
					cancel:true
				}).max();
			}
        	
			//新增数据
        	function add(){
				top.$.dialog({
					width:'80%',
					height:'80%',
					lock:true,
					title:"新增图表",
					content: 'url:pub/chart/chart/chart_detail.jsp?status=add&typeId='+selectdTypeId,
					data:{window:window}
				}).max();
			}
			//修改数据
			function modifyConfig(){
				var selectdTypeId = Qm.getValueByName("fk_chart_type");
				top.$.dialog({
					width:'80%',
					height:'80%',
					lock:true,
					title:"修改图表",
					content: 'url:pub/chart/chart/chart_detail.jsp?status=modify&id=' + Qm.getValueByName("id")+'&typeId='+selectdTypeId,
					data:{window:window}
				}).max();
			}
			//删除数据
			function deleteCofnig(){
			    $.del("确定删除?",
			    		"chart/chartinfo/delete.do",
			    		{"ids":Qm.getValuesByName("id").toString()});
		    }
			//复制
    		function copy(){
    			var id = Qm.getValueByName("id");
                if(!id){
                    $.ligerDialog.alert("请先选择要复制的数据！");
                    return;
                }
                var selectdTypeId = Qm.getValueByName("fk_chart_type");
    			top.$.dialog({
    				title:"复制",
    				width : 300,
    				height: 200,
    				lock : true,
    				data : {window:window},
    				content : "url:pub/chart/chart/chart_copy.jsp?status=modify&id=" + Qm.getValuesByName("id")+'&typeId='+selectdTypeId,
    			}).max();
    		}
    		function modifyApp(id){
                var status = "modify";
                var windows=top.$.dialog({
    				width:'80%',
    				height:'80%',
                    lock:true,
                    title:"修改图表",
                    content: 'url:pub/chart/chart/chart_detail.jsp?status='+status+'&id='+id,
                    data: {"window": window}
                }).max();
            }
    		
    		function loadGridData(typeId,code){
    			selectdTypeId = typeId
    			Qm.setCondition({name:"code",compare:"llike",value:code});
    			Qm.searchData();
    		}

	        function renderRow(row){
	             return "<table class='detail'>"+
	                     "  <tr>"+
	                     "<td rowspan='5'><img height='100' width='140' src='"+row["icon"]+"'></td>"+
	                     "<td align='left'><label>类型：</label>"+row["name"]+"</td>"+
	                     "<td align='right' rowspan='5' style='width:350px;white-space:normal'><a onclick='javascript:review(\""+row.chart_num+"\")' style='cursor:pointer;color:red'>预览</a></td>"+
	                     "  </tr>"+
	                     "  <tr>"+
	                     "<td align='left'><label>编号：</label>"+row["chart_num"]+"</td>"+
	                     "  </tr>"+
	                     "  <tr>"+
	                     "<td align='left'><label>名称：</label>"+row["chart_name"]+"</td>"+
	                     "  </tr>"+
	                     "  <tr>"+
	                     "<td  align='left'><label>修改时间：</label>"+row["update_date"]+"</td>"+
	                     "  </tr>"+
	                     "  <tr>"+
	                     "<td align='left'><label>备注：</label>"+row["chart_remark"]+"</td>"+
	                     "  </tr>"+
	                     "  </table>";
	        }
	        
	        function review(obj){
	        	var windows=top.$.dialog({
	 				width:'80%',
	 				height:'80%',
                 	lock:true,
                 	title:"预览",
                 	content: 'url:pub/chart/tag_detail.jsp?chartNum='+obj,
                 	data: {"window": window}
	             });
	        }
	        
			function exportChart(){
	      		$("body").mask("正在导出请稍后...");
	      		$("#down").attr('src',"chart/chartinfo/export.do?ids="+Qm.getValuesByName("id").toString())
	      		$("body").unmask();
		    }
			function importChart(){
				top.$.dialog({
	 				width:'40%',
	 				height:'30%',
                 	lock:true,
                 	title:"导入",
                 	content: 'url:pub/chart/chart/import.jsp',
                 	data: {"window": window}
	             });
		    }
        </script>
    <style type="text/css">
        .detail td{
            padding-left: 10px;
        }
        .detail label{
            color:blue;
            /*font-weight: bold;*/
        }
    </style>
</head>
<body>
	<q:qm pageid="chart_info" singleSelect="false">
	</q:qm>
	<iframe style="display:none" id="down"></iframe>
</body>
</html>