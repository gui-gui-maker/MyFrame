<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>数据表管理</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
			var qmUserConfig = {
	            tbar:[
		            { text:'预览', id:'detail',icon:'detail',click:detail},
		            "-",
	                { text:'设计', id:'add',icon:'add', click:pageset}
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({
	                    	detail: count==1,
	                    	add: count==1,
	                    	allow: count==1,
	                    	forbid: count==1
	                    });
	                },
            		rowDblClick :function(rowData,rowIndex){
            			detail(rowData.id);
            		}
	            }
	        };
			
			function detail(id){
				if($.type(id)!="string")
				top.$.dialog({
					width:1200,
					height:700,
					lock:true,
					title:"预览",
					content: 'url:pub/form/data/initFormList.do?formId=' + Qm.getValueByName("table_name"),
					data:{window:window},
					cancel:true
				});
			}
        	
			//新增数据
        	function pageset(){
				top.$.dialog({
			width:1200,
					height:700,
					lock:true,
					title:"表单设计",
							content: 'url:pub/form/config/table_config_Form.jsp?pageStatus=modify&id=' + Qm.getValueByName("id"),
					data:{window:window}
				});
			}
	
	
        </script>
	</head>
	<body>
		<qm:qm pageid="sys_form" script="false" singleSelect="false"></qm:qm>
	</body>
</html>