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
			
			// 签收
			function receives(){
				var statusArr = Qm.getValuesByName("data_status");	// 数据状态
				for(var i=0;i<statusArr.length;i++){
					if(statusArr[i] == '已签收'){
						$.ligerDialog.error("亲，您所选的数据中，包含已签收的数据，不能重复签收哦，请重新选择！");
						return;
					}
				}
					if(confirm("亲，确认签收所选数据吗？签收后无法退回哦！")){
						$.getJSON("report/transfer/receive.do?ids="+ Qm.getValuesByName("id").toString() + '&report_transfer_id=${param.report_transfer_id}', function(resp){
							if(resp.success){
								if(resp.info_id!=""){
									if(resp.bhg_report_sn!=""){
										top.$.dialog.notice({
								             content: "本次包含不合格的报告（报告编号："+resp.bhg_report_sn+"），不合格的报告不打印合格证！"
										});
									}/*else{
										top.$.dialog.notice({
								             content: "操作成功！正在跳转进行合格证打印..."
										});
									}*/
									
									$.ligerDialog.confirm("亲，确定要打印合格证吗？", function(yes) {
										if (yes) {
											var w = 400 ;
											var h = 200
											top.$.dialog({ 
												width : w, 
												height :h, 
												lock : true, 
												title:"打印标签",
												content: 'url:app/query/report_sign_pirnt.jsp',
												data : {
													"pwindow" : window,
													"id":resp.info_id,
													"device_ids":resp.device_ids,
													"report_types":resp.report_types,
													"bigClasss":resp.bigClasss
												}
											});
										}else{
											api.data.window.refreshGrid();
							            	api.close();
										}
									});
									
									/* 
					            	var w=window.screen.availWidth;
									var h=(window.screen.availHeight);		
									top.$.dialog({ 
										width : w, 
										height :h, 
										lock : true, 
										title:"打印标签",
										content: 'url:app/query/report_print_index_tags.jsp?flag=yes&type=2',
										data : {
											"pwindow" : window,
											"id":resp.info_id
										} 
									}).max();*/
								}else{
									top.$.dialog.notice({
							             content: "操作成功！"
									});
								}
								//api.data.window.refreshGrid();
				            	//api.close();
							}else{
								$.ligerDialog.error("操作失败，未找到系统相关业务流程，请联系系统管理员！");
							}
					})
				}
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
		<qm:qm pageid="receive_rep_transfer">
			<qm:param name="fk_report_transfer_id" value="${param.report_transfer_id}" compare="=" />
		</qm:qm>
		<script type="text/javascript">
		Qm.config.columnsInfo.org_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ( ORG_CODE like 'cy%' and ORG_CODE != 'cy4_1' and ORG_CODE != 'cy8') order by orders "></u:dict>;
		</script>
	</body>
</html>