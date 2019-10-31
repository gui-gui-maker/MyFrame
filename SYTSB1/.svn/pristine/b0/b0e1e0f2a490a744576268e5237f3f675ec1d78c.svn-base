<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="q" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>图表管理</title>
    <%@include file="/k/kui-base-list.jsp"%>
    <%@include file="/k/kui-base-chart.jsp"%>
	<script type="text/javascript">
			var selectdTypeId="${param.typeId}";
			var qmUserConfig = {
				 sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:80},
				 sp_fields:[
				       {label:"图表编号",name:"chartid",compare:"like",value:""}
				], 
	            tbar:[
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
                    rowHeight:100
                }
	        };
			
			//新增数据
        	function add(){
				top.$.dialog({
					width:'80%',
					height:'80%',
					lock:true,
					title:"图表设置",
					content: 'url:pub/chart/chart/lChart_detail.jsp?status=add&typeId='+selectdTypeId,
					data:{window:window}
				}).max();
			}
			//修改数据
			function modifyConfig(){
				top.$.dialog({
					width:'80%',
					height:'80%',
					lock:true,
					title:"修改图表",
					content: 'url:pub/chart/chart/lChart_detail.jsp?status=modify&id=' + Qm.getValueByName("id"),
					data:{window:window}
				}).max();
			}
			//删除数据
			function deleteCofnig(){
			    $.del("确定删除?",
			    		"lchart/conf/delChart.do",
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
    				height: 100,
    				lock : true,
    				data : {window:window},
    				content : "url:pub/chart/chart/lChart_copy.jsp?status=modify&id=" + Qm.getValuesByName("id"),
    			});
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
	                     "<td align='right' rowspan='5' style='width:100%;white-space:normal'><a onclick='javascript:review(\""+row.chartid+"\")' style='cursor:pointer;color:red'>预览</a></td>"+
	                     "  </tr>"+
	                     "  <tr>"+
	                     "<td align='left'><label>调用名称：</label>"+row["chartid"]+"</td>"+
	                     "  </tr>"+
	                     "  <tr>"+
	                     "<td  align='left'><label>修改时间：</label>"+row["lrsj"]+"</td>"+
	                     "  </tr>"+
	                     "  <tr>"+
	                     "<td align='left'><label>备注：</label>"+row["bz"]+"</td>"+
	                     "  </tr>"+
	                     "  </table>";
	        }
	        
	        function review(obj){
				var wObj = top.$.dialog({
					id: "chart_review",
					width: "100%",
					height: "100%",
					parent: api,
					title: "图表预览",
					max: true,
					min: false,
					//cancel: true,
					//ok: false,
					content: "url:pub/chart/lchart_win_byself.jsp?c="+obj+"&p={}"
				});
	        }
	        
			function exportChart(){
	      		$("body").mask("正在导出请稍后...");
	      		$("#down").attr('src',"lchart/conf/export.do?ids="+Qm.getValuesByName("id").toString())
	      		$("body").unmask();
		    }
			function importChart(){
				top.$.dialog({
	 				width:'40%',
	 				height:'30%',
                 	lock:true,
                 	title:"导入",
                 	content: 'url:pub/chart/chart/lChart_import.jsp',
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
	<q:qm pageid="lchart_info" singleSelect="false">
	</q:qm>
	<iframe style="display:none" id="down"></iframe>
</body>
</html>