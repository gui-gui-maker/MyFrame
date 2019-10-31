<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>数据表管理</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript" src="pub/rbac/js/area.js"></script>
		<script type="text/javascript">
			var qmUserConfig = {
			  sp_fields:${seachInfo},
	            tbar:[
		            { text:'详情', id:'detail',icon:'detail',click:detail},
		            "-",
	                { text:'新增', id:'add',icon:'add', click:add},
	                { text:'修改', id:'modify',icon:'settings', click:modify},
	                { text:'删除', id:'del',icon:'del', click:del}
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({
	                    	detail: count==1,
	                    	del: count>0,
	                    	modify: count==1,
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
					width:800,
					height:460,
					lock:true,
					title:"查看数据表",
					content: 'url:pub/form/data/initFormInfo.do?pageStatus=detail&formId=${param.formId}&id=' + Qm.getValueByName("id"),
					data:{window:window},
					cancel:true
				});
			}
     
			//新增数据
        	function add(){
				top.$.dialog({
				width:800,
					height:460,
					lock:true,
					title:"新增数据表",
					content: 'url:pub/form/data/initFormInfo.do?pageStatus=add&formId=${param.formId}',
					data:{window:window}
				});
			}
			//修改数据
			function modify(){
				top.$.dialog({
			    	width:800,
					height:460,
					lock:true,
					title:"修改数据表",
					content: 'url:pub/form/data/initFormInfo.do?pageStatus=modify&formId=${param.formId}&id=' + Qm.getValueByName("id"),
					data:{window:window}
				});
			}
			//删除数据
			function del(){
			    $.del("确定删除数据表?",
			    		"pub/form/data/delete.do",
			    		{"ids":Qm.getValuesByName("id").toString(),"formId":"${param.formId}"});
		    }
	
   	
	function submitAction(o) {
		Qm.refreshGrid();
	}
        </script>
	</head>
	<body>
		<qm:qm pageid="${param.formId}" script="false" singleSelect="false"></qm:qm>
	</body>
</html>