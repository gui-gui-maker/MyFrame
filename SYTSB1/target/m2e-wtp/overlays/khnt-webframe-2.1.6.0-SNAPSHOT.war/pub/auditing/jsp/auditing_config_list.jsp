<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>审计配置管理</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
			var qmUserConfig = {
				sp_fields: [
					{name:"module_class_name",compare:"like"},
					{name:"method_short_name",compare:"like"},
					{name:"locked_status",compare:"="}
				],
				<sec:authorize ifNotGranted="super_administrate">
				<tbar:toolBar type="tbar" code="sys_auditing_config">
				</tbar:toolBar>,
				</sec:authorize>
			<sec:authorize access="hasRole('super_administrate')">
			tbar:[
		            { text:'详情', id:'detail',icon:'detail',click:detail},
		            "-",
	                { text:'新增', id:'add',icon:'add', click:newConfig},
		            "-",
	                { text:'修改', id:'modify',icon:'settings', click:modifyConfig},
		            "-",
	                { text:'删除', id:'del',icon:'del', click:deleteCofnig},
	                "-",
	                { text:'启用', id:'allow',icon:'accept', click:enableConfig},
		            "-",
	                { text:'禁用', id:'forbid',icon:'forbid', click:disenableConfig}
	            ],
	           </sec:authorize>
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();
	                	var ls = Qm.getValueByName("locked_status");
	                    Qm.setTbar({
	                    	detail: count==1,
	                    	del: count>0,
	                    	modify: count==1,
	                    	allow: count==1 && ls=='否',
	                    	forbid: count==1 && ls=='是'
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
					width:600,
					height:260,
					lock:true,
					title:"审计配置详情",
					content: 'url:pub/auditing/jsp/auditing_config_detail.jsp?status=detail&id=' + id,
					data:{window:window},
					cancel:true
				});
			}
        	
			//新增数据
        	function newConfig(){
				top.$.dialog({
					width:700,
					height:480,
					lock:true,
					title:"新增审计配置",
					content: 'url:pub/auditing/jsp/auditing_config_detail.jsp?status=add',
					data:{Qm:Qm}
				});
			}
			//修改数据
			function modifyConfig(){
				top.$.dialog({
					width:700,
					height:480,
					lock:true,
					title:"修改配置编辑",
					content: 'url:pub/auditing/jsp/auditing_config_detail.jsp?status=modify&id=' + Qm.getValueByName("id"),
					data:{Qm:Qm}
				});
			}
			//删除数据
			function deleteCofnig(){
			    $.del(kFrameConfig.DEL_MSG,
			    		"khnt/auditing/config/delete.do",
			    		{"ids":Qm.getValuesByName("id").toString()});
		    }
			//配置启用
			function enableConfig(){
				enable(true);
			}
			//配置禁用
			function disenableConfig(){
				enable(false);
			}
			function enable(config){
				$.getJSON("khnt/auditing/config/enable.do",{enable:config,id:Qm.getValueByName("id")},function(response){
					if(response.success){
						top.$.notice("设置成功！");
						var rowData = Qm.getQmgrid().getSelectedRow();
						Qm.getQmgrid().updateCell("locked_status",config?"启用":"禁用",rowData);
					}
					else
						$.ligerDialog.error('设置失败！');
				});
			}
        </script>
	</head>
	<body>
		<qm:qm pageid="auditing_config" script="false" singleSelect="false"></qm:qm>
	</body>
</html>