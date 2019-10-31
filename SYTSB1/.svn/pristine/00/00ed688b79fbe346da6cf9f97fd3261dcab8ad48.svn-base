<%@page import="com.khnt.rbac.impl.bean.Org"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
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
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User user = (User)curUser.getSysUser();
	Org org = (Org)user.getOrg();
	String userId = user.getId();
	String user_account = user.getAccount();
	String org_type = org.getProperty();
	String org_code = org.getOrgCode();

%>
<script type="text/javascript">

	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields:[
						{name:"device_name",compare:"like",value:""},
						{name:"device_registration_code",compare:"like",value:""},
						<sec:authorize access="hasRole('DEVICE_WARNINGS_LIST')">
						{name:"area_id", compare : "llike",treeLeafOnly:false},
						</sec:authorize>
						{name:"com_name",compare:"like",value:""},
						{name:"factory_code",compare:"like",value:""},
						{group:[
							{name:"waring1", id:"warn_days1", compare:">="},
							{label:"到", name:"waring1", id:"warn_days2", compare:"<=", labelAlign:"center", labelWidth:20}
						]},
						{name:"deal_status",compare:"=",value:""},
						{name:"device_status",compare:"=",value:""},
						{group:[
							{name:"inspect_next_date",  compare:">="},
							{label:"到", name:"inspect_next_date", compare:"<=", labelAlign:"center", labelWidth:20}
						]}
		            ],
	            tbar:[
					{ text:'查看', id:'detail',icon:'detail', click: detail},
					"-",
					{ text:'30天内到期', id:'query1',icon:'detail', click: query1},
					"-",
					{ text:'10天内到期', id:'query2',icon:'detail', click: query2},
					"-",
					{ text:'已过期', id:'query3',icon:'detail', click: query3},
					//"-",
					//{ text:'打印检验通知书', id:'print',icon:'print', click: print},
					//"-",
					//{ text:'修改检验信息', id:'changeInfo',icon:'edit', click: changeInfo},
					"-",
					{ text:'添加处理记录', id:'addRecord',icon:'edit', click: addRecord},
					"-",
					{ text:'已报检', id:'addRecord100',icon:'edit', click: addRecord1},
					"-",
					{ text:'已检验合格，待出报告', id:'addRecord101',icon:'edit', click: addRecord2},
					"-",
					{ text:'处理情况', id:'situation',icon:'copy', click: dealdetail}
					<%
						if("region".equals(org_type)){
							%>
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
								}, '-', {
									text : '修改设备信息',
									id : 'modifyDevice',
									click : modifyDevice,
									icon : 'modify'
								}
							<%
						}
					%>
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
	
	var device_id = null;
	
function changeInfo(){
		
		top.$.dialog({
			width : 600, 
			height : 250, 
			lock : true, 
			title:"修改检验信息",
			content: 'url:app/device/edit_check.jsp?status=modify&id='+Qm.getValueByName("id"),
			data : {"window" : window}
		});
	}

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
	
	function addRecord(){
		top.$.dialog({
			width : 600, 
			height : 350, 
			lock : true, 
			title:"添加处理记录",
			content: 'url:app/device/device_waring_add.jsp?status=add&ids='+Qm.getValuesByName("id").toString(),
			data : {"window" : window}
		});
	}
	
	function addRecord1(){
		$.ligerDialog.confirm('确定批量添加“已报检”的处理记录？', function (yes) { 
			if(yes){
				$.getJSON('device/deal/saveDealRecords.do?ids='+Qm.getValuesByName("id").toString()+'&deal_status=100',function(data){
					if(data){
						top.$.notice("添加成功！");
						submitAction();
					}else{
						$.ligerDialog.alert(data.msg,"提示");
					}
				});
			}
		});
	}
	
	function addRecord2(){
		$.ligerDialog.confirm('确定批量添加“已检验合格，待出报告”的处理记录？', function (yes) { 
			if(yes){
				$.getJSON('device/deal/saveDealRecords.do?ids='+Qm.getValuesByName("id").toString()+'&deal_status=101',function(data){
					if(data){
						top.$.notice("添加成功！");
						submitAction();
					}else{
						$.ligerDialog.alert(data.msg,"提示");
					}
				});
			}
		});
	}
	
	function dealdetail(){
		top.$.dialog({
			width : 700, 
			height : 550, 
			lock : true, 
			title:"处理情况",
			content: 'url:app/device/device_waring_detail.jsp?id='+Qm.getValuesByName("id"),
			data : {"window" : window}
		});
	}
	function book(){
		url = "<%=basePath%>device/deal/checkisBooked.do";
		$.post(url,{id:Qm.getValuesByName("id")},function(msg){
			if(msg==0){
				var w=window.screen.availWidth;
				var h=window.screen.availHeight;
				var url = "/cfdinfo?CONTROLLER=com.khnt.command.BlankSecuredCmd&NEXT_PAGE=TSJY_NOTIFIC&deviceId="+GetIndexValue("id");
				var fileValue = window.showModalDialog(url,[],"dialogwidth:"+w+";dialogheight:"+h+";help=no;status=no;center=yes;edge=sunken;resizable=yes");

			}else{
				if(confirm("所选设备近期已向分局发送过告知书,是否继续？")){
					var w=window.screen.availWidth;
					var h=window.screen.availHeight;
					var url = "/cfdinfo?CONTROLLER=com.khnt.command.BlankSecuredCmd&NEXT_PAGE=TSJY_NOTIFIC&deviceId="+GetIndexValue("id");
					var fileValue = window.showModalDialog(url,[],"dialogwidth:"+w+";dialogheight:"+h+";help=no;status=no;center=yes;edge=sunken;resizable=yes");
					
				}else{

				}
			}
		});
	}
	function loadGridData(nodeId,unitId,url){
		
		
		
		device_id=nodeId;
		
		if(nodeId!=null) {
			
			if(nodeId.substring(1,4)=='000'){
				
				Qm.setCondition([{name:"device_sort_code",compare:"llike",value:nodeId.substring(0,1)}]);
			}else{
				
				Qm.setCondition([{name:"device_sort_code",compare:"=",value:nodeId}]);
			}
			  			
		}else{
			Qm.setCondition([]);
		}
		Qm.searchData();
	}
	
	function submitAction(o) {
		Qm.refreshGrid();
	}

	function print(){
		var id = Qm.getValuesByName("id");
		var comArr = Qm.getValuesByName("com_name");
		var first_com = comArr[0];
		for(var i=1;i<comArr.length;i++){
			if(comArr[i] != first_com){
				$.ligerDialog.alert("不同单位的设备不能同时打印检验通知书！");
				return;
			}
		}
		var deviceTypes = Qm.getValuesByName("device_sort_code");
		var first_deviceType = deviceTypes[0];
		for(var i=1;i<deviceTypes.length;i++){
			if(deviceTypes[i] != first_deviceType){
				$.ligerDialog.alert("不同的设备类型不能同时打印检验通知书！");
				return;
			}
		}
		
		$.getJSON("device/basic/getPrintContent.do?id="+id, function(resp){
			if(resp.success){
				document.getElementById("printDiv").innerHTML=resp.printContent;
				printHtml("printDiv");
			}else{
				$.ligerDialog.error(resp.message);
			}
		})
	}

	function printHtml(printDiv){
		try{
			pagesetup_null();
			newwin=window.open("", "newwin", "height="+window.screen.height+",width="+window.screen.width+",toolbar=no,scrollbars=auto,menubar=no");
			newwin.document.body.innerHTML=document.getElementById(printDiv).innerHTML;
			newwin.window.print();
 			pagesetup_default();
 			newwin.window.close();
		}catch(e){
		}
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
	
	// 修改设备信息（成都市区县局）
	function modifyDevice(){
		var device_type = device_type = Qm.getValueByName("big_class").substring(0, 1);
		var device_area_code = Qm.getValueByName("device_area_code");
		top.$.dialog({
			width : 600,
			height : 280,
			lock : true,
			title : "修改设备信息",
			content : 'url:app/device/device_detail2.jsp?status=modify&id='
					+ Qm.getValuesByName("id").toString() + '&device_type=' + device_type + '&device_area_code=' + device_area_code,
			data : {
				"window" : window
			}
		});
	}

	function query1(){
		$("#warn_days1").val("-30");
		$("#warn_days2").val("-1");
	}
	
	function query2(){
		$("#warn_days1").val("-10");
		$("#warn_days2").val("-1");
	}
	
	function query3(){
		$("#warn_days1").val("0");
		$("#warn_days2").val("");
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
	<qm:qm pageid="device_waring_info" script="false" singleSelect="false">
		<%
			//if("region".equals(userType)){	// 市行政区划（区县）
				if("shuangliu".equals(user_account)){
					%>
					<qm:param name="device_area_code" value="510122" compare="=" />
					<%	
				}else if("jinjiang".equals(user_account)){
					%>
					<qm:param name="device_area_code" value="510104" compare="=" />
					<%	
				}else if("gaoxin".equals(user_account)){
					%>
					<qm:param name="device_area_code" value="510109" compare="=" />
					<%	
				}else if("jinniu".equals(user_account)){
					%>
					<qm:param name="device_area_code" value="510106" compare="=" />
					<%	
				}
				if("AREA_TFXQ".equals(org_code)){
					%>
					<qm:param name="device_area_code" value="510189" compare="=" />
					<%	
				}else if("jd1".equals(org_code)){
					%>
					<qm:param name="device_area_code" value="510109" compare="=" />
					<%	
				}else if("jd2".equals(org_code)){
					%>
					<qm:param name="device_area_code" value="510106" compare="=" />
					<%	
				}else if("jd3".equals(org_code)){
					%>
					<qm:param name="device_area_code" value="510122" compare="=" />
					<%	
				}else if("jd4".equals(org_code)){
					%>
					<qm:param name="device_area_code" value="510104" compare="=" />
					<%	
				}else if("jd5".equals(org_code)){
					%>
					<qm:param name="device_area_code" value="510189" compare="=" />
					<%	
				}
				
			//}
		%>
	</qm:qm>
	<div id="printDiv" style="display:none;"></div>
	<script type="text/javascript">
//    根据 sql或码表名称获得Json格式数据
<%--var aa=<u:dict code="community_type"></u:dict>;--%>
<%--Qm.config.columnsInfo.str3.binddata=<u:dict sql="select t.id,t.name from pub_code_table_values t where t.code_table_id='4028807036572ae1013657b7a4a0000b'"></u:dict>;--%>
Qm.config.columnsInfo.area_id.binddata=<u:dict sql="select id,parent_id pid,case when substr(regional_code, length(regional_code) - 1, length(regional_code)) = '00' then substr(regional_code, 0, length(regional_code) - 2) else regional_code end code, regional_name text from V_AREA_CODE"></u:dict>;
Qm.config.columnsInfo.area_id2.binddata=<u:dict sql="select id,parent_id pid,regional_code code, regional_name text from V_AREA_CODE where ID in ('510122','510189')"></u:dict>;
Qm.config.columnsInfo.device_status.binddata = [
	{id: '2', text: '使用'},
	{id: '5', text: '停用'},
	{id: '6', text: '报废'}
];
Qm.config.columnsInfo.deal_status.binddata = [
{id: '100', text: '已报检'},
                                         		{id: '101', text: '已检验合格，待出报告'},
                                         		{id: '107', text: '设备已停用'},
                                         		{id: '103', text: '现场核实该设备不存在或其使用单位不存在'},
                                         		{id: '7', text: '其他检验机构检验'},
                                         		{id: '104', text: '已检验，不合格'},
                                         		{id: '102', text: '使用单位无故拖延或拒绝检验'},
                                         		{id: '106', text: '出现重大问题，应停止使用'},
                                         		{id: '108', text: '现场不具备检验条件(未使用登记)'}

                                         ];

</script>
</body>
</html>
