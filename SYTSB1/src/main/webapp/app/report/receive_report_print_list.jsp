<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>报告信息查询列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
			var qmUserConfig = {
					sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
					sp_fields:[
					{name:"org_id", id:"check_unit_id", compare:"="},
	         		{name:"report_sn", id:"report_sn", compare:"like"},
	         		{name:"com_name", compare:"like"}
	            ],
	            tbar:[
			        { text:'签收', id:'select',icon:'check',click:receives},
			        "-",
			        { text:'关闭', id:'close',icon:'modify',click:close}
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({select:count>0});
	                }
	            }
	        };
			
			function receiveSelectValue(){	
				var statusArr = Qm.getValuesByName("data_status");	// 数据状态
				for(var i=0;i<statusArr.length;i++){
					if(statusArr[i] == '已签收'){
						$.ligerDialog.error("亲，您所选的数据中，包含已签收的数据，不能重复签收哦，请重新选择！");
						return;
					}
				}
				if(confirm("亲，确认签收所选中的报送打印信息吗？签收后无法回退数据哦！")){
						$.getJSON("report/print/record/receives.do?ids="+Qm.getValuesByName("id").toString()+"&report_print_id=${param.report_print_id}", function(resp){
							if(resp.success){
								top.$.dialog.notice({
						             content: "操作成功！"
								});
								api.data.window.refreshGrid();
								api.close();
							}else{
								$.ligerDialog.error("操作失败，请联系系统管理员！");
							}
					})
				}
			}
			
			// 签收
			function receives(){
				var statusArr = Qm.getValuesByName("data_status");	// 数据状态
				for(var i=0;i<statusArr.length;i++){
					if(statusArr[i] == '已签收'){
						$.ligerDialog.error("亲，您所选的数据中，包含已签收的数据，不能重复签收哦，请重新选择！");
						return;
					}
				}
				//if(confirm("亲，确认签收所选中的报送打印信息吗？签收后无法回退数据哦！")){
					top.$.dialog({
						width : 600,
						height : 300,
						lock : true,
						title : "签收",
						content : 'url:app/report/receive_report_print.jsp?status=modify&ids='
									+ Qm.getValuesByName("id").toString() + '&org_id=' + Qm.getValueByName("org_id").toString() + '&report_print_id=${param.report_print_id}',
						data : {
							"window" : window
						}
					});	
				//}
			}
			
			function close(){
				api.close();
				api.data.window.refreshGrid();
			}
			
			function refreshGrid() {
				Qm.refreshGrid();
			}
        </script>
	</head>
	<body>
		<qm:qm pageid="receive_report_print">
			<qm:param name="fk_report_print_id" value="${param.report_print_id}" compare="=" />
		</qm:qm>
		<script type="text/javascript">
		Qm.config.columnsInfo.org_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ( ORG_CODE like 'cy%' and ORG_CODE != 'cy4_1' and ORG_CODE != 'cy8') order by orders "></u:dict>;
		</script>
	</body>
</html>