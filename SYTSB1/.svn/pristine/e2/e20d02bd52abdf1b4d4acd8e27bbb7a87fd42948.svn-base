<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%

CurrentSessionUser usee = SecurityUtil.getSecurityUser();
User uu = (User)usee.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>业务信息查询</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript" src="pub/bpm/js/util.js"></script>
		<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>
<script type="text/javascript" src="/app/fwxm/scientific/instruction/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="/app/common/lodop/LodopFuncs.js"></script>
<script type="text/javascript"  src="/app/discipline/js/active.js" ></script>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String userId = user.getId();
	String user_account = user.getSysUser().getAccount();
	String userType = ((com.khnt.rbac.impl.bean.Org)(user.getDepartment())).getProperty();

	String org_code = user.getDepartment().getOrgCode();
	String isDt = "";
%>
		<script type="text/javascript">
		var w = window.screen.availWidth;
		var h = window.screen.availHeight;
		var deviceType=<u:dict sql="select t.value id,t.name text from PUB_CODE_TABLE_VALUES t,pub_code_table t1 where t.code_table_id=t1.id and   t1.code = 'device_classify' "/>
		var areas = <u:dict sql="select id,regional_name text from V_AREA_CODE where ID in ('510104','510106','510109','510122','510189')"></u:dict>;
		var check_types = <u:dict code="BASE_CHECK_TYPE"></u:dict>;
		var check_deps = <u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ORG_CODE like 'cy%' order by orders "></u:dict>;
		var reports = <u:dict sql='select id,report_name from base_reports'></u:dict>;
		var payments = <u:dict code="PAYMENT_STATUS"></u:dict>;
		
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
            sp_fields:[
                {name:"sn",compare:"like",value:""},
                {name:"zd",compare:"like",value:""},
            	{name:"szbm", id:"szbm", compare:"like"},
            	{name:"jdlb", id:"jdlb", compare:"like"},
            	{name:"create_user_name", id:"create_user_name", compare:"="},
                {group:[{name:"create_time",compare:">=", id:"report_end_date_b", value:""},
                        {label:"到",name:"create_time",compare:"<=",id:"report_end_date_e", value:"",labelAlign:"center",labelWidth:20}]}
               
            ],
          tbar: [{ text:'详情', id:'detail',icon:'detail', click:detail },
                 '-', 
                 { text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory},
                 { text:'打印', id:'print',icon:'print', click:print }
                 ],
          listeners: {
                rowDblClick :function(rowData,rowIndex){
                    detail();
                    },
                selectionChange :function(rowData,rowIndex){
                    var count=Qm.getSelectedCount();
            		Qm.setTbar({
    					detail : count == 1,
    					print : count == 1,
    					turnHistory:count==1
    				});
                },
                rowAttrRender: function(rowData, rowid) {
                	 var fontColor="black";
                	return "style='color:"+fontColor+"'";
                }
            }
        };
		// 详情
		function detail(id){
		var id=Qm.getValueByName("id");
		var zd=Qm.getValueByName("zd");
		var url='url:/app/discipline/active_jr_detail.jsp?status=detail&type=0&id='+id;
		if(zd=='申请介入'){
			url='url:/app/discipline/apply_jr_detail.jsp?status=detail&type=0&id='+id;
		}
			top.$.dialog({
				width: 900,
				height: 500, 
				lock : true, 
				title:"详情",
				content: url,
				data : {"window" : window}
			});
		}
		// 流转过程
		function turnHistory(){
			top.$.dialog({
	   			width : 400, 
	   			height : 700, 
	   			lock : true, 
	   			title: "流程卡",
	   			content: 'url:com/zdsx/sqjr/getFlowStep.do?id='+Qm.getValueByName("id")+"&sn="+Qm.getValueByName("sn"),
	   			data : {"window" : window}
	   		});
		}
		
// 		function print(){
			
// 		}
		
    </script>
	</head>
	<body>
		<qm:qm pageid="zdjdsx_list" singleSelect="true">
			
		</qm:qm>
	</body>
</html>
