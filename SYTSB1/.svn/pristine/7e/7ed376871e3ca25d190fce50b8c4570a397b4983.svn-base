<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>报告列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
		var a="${param.abc}";
			var qmUserConfig = {
	            tbar:[
			        { text:'选择', id:'select',icon:'check',click:getSelectedPersons},
			        "-",
			        { text:'新增', id:'xz',icon:'add',click:add},
			        "-",
			        { text:'关闭', id:'close',icon:'close',click:close}
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({});
	                }
	            }
	        };
        	
			function getSelectedPersons(){
			api.data.parentWindow.callBackReport(
					Qm.getValuesByName("id").toString(), 
					Qm.getValuesByName("fwbjhbg_num").toString());
				api.close();
			}
			function add(){
				top.$.dialog({
					width: 400,
					height: 250,
					lock: true,
					parent: api,
					data: {
						window: window
					},
					title: "新增",
					content: 'url:app/qualitymanage/tyfabhsqb_xuanze_datail.jsp?pageStatus=add'
				});
			}
			function close(){
				api.close();
			}
			
			//列表刷新
			function refreshGrid() {
			    Qm.refreshGrid();
			}
        </script>
	</head>
	<body>
		<qm:qm pageid="TJY2_ZL_TYFABH_XZ"  pagesize="30">
		</qm:qm>
	</body>
</html>