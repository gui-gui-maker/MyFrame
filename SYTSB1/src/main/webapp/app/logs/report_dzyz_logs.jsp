<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>报告电子印章日志查询</title>
		<%@include file="/k/kui-base-list.jsp"%>
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
		var deviceType=<u:dict sql="select t.value id,t.name text from PUB_CODE_TABLE_VALUES t,pub_code_table t1 where t.code_table_id=t1.id and   t1.code = 'device_classify' "/>
		
		var reports = <u:dict sql='select id,report_name from base_reports'></u:dict>;

        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
            sp_fields:[
				{name:"big_class", id:"big_class", compare:"="},	      
            	{name:"report_sn", id:"report_sn", compare:"like"},				
            	{group:[
					{name:"op_time", id:"op_time", compare:">="},
					{label:"到", name:"op_time", id:"op_time2", compare:"<=", labelAlign:"center", labelWidth:20}
				]}
            ],
            tbar:[
                  { text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory}              
            ],
            listeners: {
                selectionChange : function(rowData,rowIndex){
                	selectionChange();
                },
        		rowDblClick :function(rowData,rowIndex){
    
        		},
        		rowClick:function(rowData){
        			//setConditionValues(rowData);
        		},
    			rowAttrRender : function(rowData, rowid) {
   				 	var fontColor="black";  		    
   		         	return "style='color:"+fontColor+";'";
   		            //return "style='color:"+fontColor+";font-weight: bold;'";
   				},
   	            pageSizeOptions:[10,20,30,50,100,200]
            }
        };
        
        // 行选择改变事件
		function selectionChange() {
	   		var count = Qm.getSelectedCount(); // 行选择个数
	     	if(count == 1){
	            Qm.setTbar({turnHistory: true});            	
	 		}else if(count > 1){
	       		Qm.setTbar({turnHistory: false});
	    	}else{
	    		Qm.setTbar({turnHistory: false});
	    	}
		}
		
		function setConditionValues(rowData){
			//$('#device_registration_code1').val(Qm.getValueByName("device_registration_code").toString());	// 设备注册代码
			$("#qm-search-p input").each(function(){
				var name = $(this).attr("id");
				if(!$.kh.isNull(name)){
					$(this).val(rowData[name]);
				}
			})
			//$("input[name='big_class-txt']").ligerComboBox().selectValue(render(rowData["big_class"],deviceType));	// 设备类别
			//$("input[name='area_id-txt']").ligerComboBox().selectValue(render(rowData["area_id"],areas));	// 设备所在地
			//$("input[name='check_type-txt']").ligerComboBox().selectValue(render(rowData["check_type"],check_types));	// 检验类别
			//$("input[name='check_unit_id-txt']").ligerComboBox().selectValue(render(rowData["check_unit_id"],check_deps));	// 检验部门
			
			//$("input[name='report_id-txt']").ligerComboBox().selectValue(render(rowData["report_id"],reports));	// 报告类型
			//$("input[name='fee_status-txt']").ligerComboBox().selectValue(render(rowData["fee_status"],payments));	// 收费状态
		}
		
		function empty(){
			$("#qm-search-p input").each(function(){
				$(this).val("");
			})
			//$("input[name='fee_status-txt']").ligerComboBox().selectValue('');	// 收费状态
			//$("input[name='big_class-txt']").ligerComboBox().selectValue('');	// 设备类别
			//$("input[name='check_type-txt']").ligerComboBox().selectValue('');	// 检验类别
			//$("input[name='check_unit_id-txt']").ligerComboBox().selectValue('');	// 检验部门
			//$("input[name='area_id-txt']").ligerComboBox().selectValue('');	// 设备所在地
			//$("input[name='data_status-txt']").ligerComboBox().selectValue('');	// 报告状态
			//$("input[name='is_mobile-txt']").ligerComboBox().selectValue('');	// 出具方式
			//$("input[name='error_id-txt']").ligerComboBox().selectValue('');	// 是否纠错报告
		}
		
		// 流转过程
		function turnHistory(){	
			top.$.dialog({
	   			width : 400, 
	   			height : 700, 
	   			lock : true, 
	   			title: "流程卡",
	   			content: 'url:department/basic/getFlowStep.do?ins_info_id='+Qm.getValueByName("inspection_info_id"),
	   			data : {"window" : window}
	   		});
		}
		
        // 刷新Grid
        function refreshGrid() {
            Qm.refreshGrid();
        }
    </script>
	</head>
	<body>	
		<div class="item-tm" id="titleElement">
		    <div class="l-page-title">
				<div class="l-page-title-note">
				</div>
			</div>
		</div>
		<qm:qm pageid="report_dzyz_logs" singleSelect="false">
		</qm:qm>
		<script type="text/javascript">
//    根据 sql或码表名称获得Json格式数据
/*Qm.config.columnsInfo.big_class.binddata=<u:dict sql="select substr(t.value,0,1) code, t.name text from pub_code_table_values t,pub_code_table t2 where t.code_table_id=t2.id and t2.code='device_classify' and t.value like '%000' order by t.sort"></u:dict>;*/
</script>
	</body>
</html>