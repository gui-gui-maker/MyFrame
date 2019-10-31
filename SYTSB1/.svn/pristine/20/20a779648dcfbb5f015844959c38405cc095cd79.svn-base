<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>报告列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
			var qmUserConfig = {
				sp_defaults:{columnWidth:0.5,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
	         	sp_fields:[
					{name:"report_name",compare:"like",value:""}
	            ],
	            tbar:[
			        { text:'选择', id:'select',icon:'check',click:selectValue},
			        "-",
			        { text:'关闭', id:'close',icon:'modify',click:close}
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({select:count==1});
	                }
	            }
	        };
        	
			function selectValue(){	
				api.data.parentWindow.callBackReport(Qm.getValueByName("id").toString(), Qm.getValueByName("report_name").toString());
				api.close();
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
		<qm:qm pageid="reports_list" script="false">
		</qm:qm>
	</body>
</html>