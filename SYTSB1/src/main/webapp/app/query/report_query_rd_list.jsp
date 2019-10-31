<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>报告信息查询</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript" src="pub/bpm/js/util.js"></script>
		<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>
		<script language="javascript" src="app/flow/report/report.js"></script>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String userId = user.getId();
	String user_account = user.getSysUser().getAccount();
	String userType = ((com.khnt.rbac.impl.bean.Org)(user.getDepartment())).getProperty();
	String org_code = ((com.khnt.rbac.impl.bean.Org)(user.getDepartment())).getOrgCode();
	String isDt = "";
%>
		<script type="text/javascript">
		var w = window.screen.availWidth;
		var h = window.screen.availHeight;
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.25,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
            sp_fields:[
				{name:"report_sn2", id:"report_sn2", compare:"like"},       
            	{name:"device_registration_code", id:"device_registration_code", compare:"like"},
            	{name:"report_com_name", id:"report_com_name", compare:"like"},	
            	{name:"report_item_name", id:"report_item_name", compare:"like"},
            	{name:"check_unit", id:"check_unit", compare:"like"},	
            	{name:"check_type", id:"check_type", compare:"like"},
            	{name:"inspection_conclusion", id:"inspection_conclusion", compare:"like"},
            	{name:"enter_user_name", id:"enter_user_name", compare:"like"},         	
            	{group:[
					{name:"last_inspection_date", id:"last_inspection_date", compare:">="},
					{label:"到", name:"last_inspection_date", id:"last_inspection_date1", compare:"<=", labelAlign:"center", labelWidth:20}
				]},
				{group:[
					{name:"enter_date", id:"enter_date", compare:">="},
					{label:"到", name:"enter_date", id:"enter_date1", compare:"<=", labelAlign:"center", labelWidth:20}
				]},
            	{group:[
					{name:"print_time", id:"print_time", compare:">="},
					{label:"到", name:"print_time", id:"print_time1", compare:"<=", labelAlign:"center", labelWidth:20}
				]}
            ],
            tbar:[
 				{text : '清空', id : 'empty', icon : 'modify', click : empty}                  
            ],
            listeners: {
                selectionChange : function(rowData,rowIndex){
                	selectionChange();
                },
        		rowDblClick :function(rowData,rowIndex){
        			detail(rowData.inspection_info_id);
        		},
        		rowClick:function(rowData){
        			setConditionValues(rowData);
        		},
    			rowAttrRender : function(rowData, rowid) {
 
   				},
   	            pageSizeOptions:[10,20,30,50,100,200]
            }
        };
        
        // 行选择改变事件
		function selectionChange() {
	   		var count = Qm.getSelectedCount(); // 行选择个数

		}
		
		function setConditionValues(rowData){
			//$('#device_registration_code1').val(Qm.getValueByName("device_registration_code").toString());	// 设备注册代码
			$("#qm-search-p input").each(function(){
				var name = $(this).attr("id");
				if(!$.kh.isNull(name)){
					$(this).val(rowData[name]);
				}
			})
			$("input[name='check_type-txt']").ligerComboBox().selectValue(render(rowData["check_type"],check_types));	// 检验类别
			$("input[name='check_type-txt']").ligerComboBox().selectValue(render(rowData["check_type"],check_types));	// 检验类别
		}
		
		function empty(){
			$("#qm-search-p input").each(function(){
				$(this).val("");
			})
		}
	
        
        // 刷新Grid
        function submitAction() {
            Qm.refreshGrid();
        }
        
        function closewindow(){
			api.close();
		}
    </script>
	</head>
	<body>	
		<qm:qm pageid="report_query_rd_list" singleSelect="false">
		</qm:qm>
		<script type="text/javascript">
		Qm.config.columnsInfo.check_type.binddata = [
			{id: '定期检验', text: '定期检验'}, 
			{id: '年度检验', text: '年度检验'},
			{id: '全面检验', text: '全面检验'},
			{id: '监督检验', text: '监督检验'},
			{id: '验收检验', text: '验收检验'},
			{id: '安装检验', text: '安装检验'},
			{id: '修理检验', text: '修理检验'},
			{id: '改造', text: '改造'},
			{id: '内部检验', text: '内部检验'},                                     		
			{id: '外部检验', text: '外部检验'},                                     		
			{id: '内部和外部检验', text: '内部和外部检验'},                                    		
			{id: '水压试验', text: '水压试验'},                                     		
			{id: '产品监检', text: '产品监检'},                                    		
			{id: '进口检验', text: '进口检验'},                                    		
			{id: '限速器检验', text: '限速器检验'}                              		
		];
		Qm.config.columnsInfo.check_unit.binddata = [
			{id: '四川省特种设备检验研究院', text: '四川省特种设备检验研究院'}, 
			{id: '四川省锅炉压力容器检验研究所', text: '四川省锅炉压力容器检验研究所'},
		 	{id: '四川省特种设备检验所', text: '四川省特种设备检验所'},
		    {id: '四川省特种设备研究院', text: '四川省特种设备研究院'}                              		
		];
		</script>
	</body>
</html>