<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>设备预警列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="app/common/js/windowprint.js"></script>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String userId = user.getId();
	String user_account = user.getSysUser().getAccount();
	/* String org_type = user.getDepartment().getProperty(); */
	String org_code = user.getDepartment().getOrgCode();
%>
<script type="text/javascript">

	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},				
			sp_fields:[
						{name:"com_name",compare:"like",value:""},
						{name:"device_registration_code",compare:"like",value:""},
						{name:"device_name",compare:"like",value:""},
						{name:"factory_code",compare:"like",value:""},
						{group:[
							{name:"waring", id:"warn_days1", compare:">="},
							{label:"到", name:"waring", id:"warn_days2", compare:"<=", labelAlign:"center", labelWidth:20}
						]},
					
						{name:"device_status",compare:"=",value:""}
		            ],
	            tbar:[
					{ text:'查看', id:'detail',icon:'detail', click: detail},
					"-",
					{ text:'30天内到期', id:'query1',icon:'detail', click: query1},
					"-",
					{ text:'10天内到期', id:'query2',icon:'detail', click: query2},
					"-",
					{ text:'已过期', id:'query3',icon:'detail', click: query3}
					
								,'-', {
									text : '启用',
									id : 'enable',
									click : enable,
									icon : 'modify'
								}, '-', {
									text : '停用',
									id : 'disable',
									click : disable,
									icon : 'modify'
								}, '-', {
									text : '报废',
									id : 'useless',
									click : useless,
									icon : 'modify'
								}
							
					//"-",
					//{ text:'问题告知书', id:'book',icon:'org', click: book}
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({detail:count==1,changeInfo:count==1,situation:count==1,addRecord:count>0,addRecord100:count>0,addRecord101:count>0,enable:count>0,disable:count>0,useless:count>0,modifyDevice:count>0/*,print:count>0,addRecord:count>0,situation:count==1,book:count>0*/});
	                },
	                rowAttrRender : function(rowData, rowid) {
	           		    var blue = 30;
	           		 	var yell = 10;
	           			var red = 0;
	            		var fontColor="#8B008B";
	            		//到期30天之内10天之前 显示蓝色
	            		if (rowData.waring <=blue & rowData.waring >yell){
	            			fontColor="blue";
	            		}
	                    //到期10天之内 显示橙色
	            		else if(rowData.waring <=yell & rowData.waring >red ) {
	            			fontColor="orange";
	            		}
	            		//到期 显示红色
	            		else if(rowData.waring <=red) {
	            			fontColor="red";
	            		}
	            		return "style='color:"+fontColor+"'";
	            	}
	               
	
	            }
	};
	


	function detail(){
		
		top.$.dialog({
			width : 1000, 
			height : 600, 
			lock : true, 
			title:"特种设备信息",
			content: 'url:app/device/device_detail.jsp?status=detail&id='+Qm.getValueByName("id")+"&device_type="+Qm.getValueByName("big_class"),
			data : {"window" : window}
		});
	}
	
	
	

	
	function submitAction(o) {
		Qm.refreshGrid();
	}

	

	
	
	// 启用
	function enable() {
		var statusArr = Qm.getValuesByName("device_status").toString();
		if(statusArr.indexOf("使用") != -1){
			$.ligerDialog.alert("所选设备中，包含已启用的设备哦，请选择未启用的设备！");
			return;
		}
		$.ligerDialog.confirm("亲，确定要启用所选设备吗？", function(yes) {
			if (yes) {
				$.ajax({
					url : "device/basic/enable.do?ids=" + Qm.getValuesByName("id").toString(),
					type : "post",
					async : false,
					success : function(data) {
						if (data.success) {
							top.$.notice("启用成功！");
							Qm.refreshGrid();
						} else {
							top.$.notice("启用失败！" + data.msg);
						}
					}
				});
			}
		});
	}
	
	// 停用
	function disable() {
		var statusArr = Qm.getValuesByName("device_status").toString();
		if(statusArr.indexOf("停用") != -1){
			$.ligerDialog.alert("所选设备中，包含已停用的设备哦，请选择未停用的设备！");
			return;
		}
		$.ligerDialog.confirm("亲，确定要停用所选设备吗？停用设备请谨慎操作哦！", function(yes) {
			if (yes) {
				$.ajax({
					url : "device/basic/disable.do?ids=" + Qm.getValuesByName("id").toString(),
					type : "post",
					async : false,
					success : function(data) {
						if (data.success) {
							top.$.notice("停用成功！");
							Qm.refreshGrid();
						} else {
							top.$.notice("停用失败！" + data.msg);
						}
					}
				});
			}
		});
	}
	
	// 报废
	function useless() {
		var statusArr = Qm.getValuesByName("device_status").toString();
		if(statusArr.indexOf("报废") != -1){
			$.ligerDialog.alert("所选设备中，包含已报废的设备哦，请选择未报废的设备！");
			return;
		}
		$.ligerDialog.confirm("亲，确定要报废所选设备吗？报废设备请谨慎操作哦！", function(yes) {
			if (yes) {
				$.ajax({
					url : "device/basic/useless.do?ids=" + Qm.getValuesByName("id").toString(),
					type : "post",
					async : false,
					success : function(data) {
						if (data.success) {
							top.$.notice("报废成功！");
							Qm.refreshGrid();
						} else {
							top.$.notice("报废失败！" + data.msg);
						}
					}
				});
			}
		});
	}
	

	function query1(){
		$("#warn_days1").val("1");
		$("#warn_days2").val("30");
	}
	
	function query2(){
		$("#warn_days1").val("1");
		$("#warn_days2").val("10");
	}
	
	function query3(){
		$("#warn_days1").val("");
		$("#warn_days2").val("0");
	}
	
</script>
</head>
<body>

	<div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="blue">“蓝色”</font>代表设备还有30天到期。
				<font color="orange">“橙色”</font>代表设备还有10天到期。
				<font color="red">“红色”</font>代表设备已超过检验日期。
				
			</div>
		</div>
	</div>
	<qm:qm pageid="tanker_waring_info" script="false" singleSelect="false">
		
	</qm:qm>
	<div id="printDiv" style="display:none;"></div>
	<script type="text/javascript">
//    根据 sql或码表名称获得Json格式数据
<%--var aa=<u:dict code="community_type"></u:dict>;--%>
<%--Qm.config.columnsInfo.str3.binddata=<u:dict sql="select t.id,t.name from pub_code_table_values t where t.code_table_id='4028807036572ae1013657b7a4a0000b'"></u:dict>;--%>

Qm.config.columnsInfo.device_status.binddata = [
	{id: '2', text: '使用'},
	{id: '5', text: '停用'},
	{id: '6', text: '报废'}
];


</script>
</body>
</html>
