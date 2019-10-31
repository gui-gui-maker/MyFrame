<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser user = SecurityUtil.getSecurityUser();
User uu = (User)user.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields:[ {name:"status",compare: "="},
						{name:'invoice_type',compare:"like"},
						{name:'money_type',compare:'like'},
						{name:'lead_name',compare:'like'},
						{name:'invoice_name',compare:'like'},
						{group:[
							  {name:'invoice_code',compare:'>='},
							  {label:'到',name:'invoice_code', compare:'<=',labelAlign:"center",labelWidth:20}
							]},
						{group:[
							{name:'lead_date',compare:'>='},
							{label:'到',name:'lead_date', compare:'<=',labelAlign:"center",labelWidth:20}
						]},
						{group:[
							{name:'invoice_date',compare:'>='},
							{label:'到',name:'invoice_date', compare:'<=',labelAlign:"center",labelWidth:20}
						]}
					],
			
					listeners:{
						rowClick:function(rowData,rowIndex){
						},
						rowDblClick:function(rowDate,rowIndex){
							Qm.getQmgrid().selectRange("id", [rowData.id]);
							detail();
						},
						selectionChange:function(rowDate,rowIndex){
							var count = Qm.getSelectedCount();
							Qm.setTbar({
								detail:count==1,
							});
							var up_status = Qm.getValueByName("status");
             				
             				var status={};
             				if(count==0){
             					status={detail:false, lead:false, cancel:false,leadLog:false};
             				}else if(count==1){
             					if("未使用"==up_status ){
             						status={detail:true, lead:true, cancel:true,leadLog:false};
           						}else if("已领用"==up_status){
           							status={detail:true, lead:false, cancel:true,leadLog:true};
           						} else {
           							status={detail:true, lead:false, cancel:false,leadLog:false};
           						}
             					
             				}
             				Qm.setTbar(status);
             			}			
					}
					
	};
	
	function detail(){
		id = Qm.getValueByName("id").toString();	
		top.$.dialog({
			width : 700,
			height : 200,
			lock : true,
			title : "业务详情",
			content : 'url:app/finance/cwInvoice_lead_datail1.jsp?pageStatus=detail&id='+ id,
			data : {
				"window" : window
			}
		});
	}
	
	function leadLog(){
	 	var id = Qm.getValueByName("pk_lead_id").toString();
		top.$.dialog({
			width : 700,
			height : 300,
			lock : true,
			title : "领用记录",
			content : 'url:app/finance/cwInvoice_lead_datail.jsp?pageStatus=detail&id='+ id,
			data : {
				"window" : window
			}
		});
	}
	
	
	
	function lead(){
		var invoice_codes = Qm.getValuesByName("invoice_code").toString();
		var ids = Qm.getValuesByName("id").toString();
		
		top.$.dialog({
			width : 700, 
			height : 300, 
			lock : true, 
			title : "发票领取", 
			data : {"window" : window},
			content : 'url:app/finance/cwInvoice_lead_datail.jsp?&pageStatus=add&invoice_codes='+invoice_codes+'&count='+Qm.getSelectedCount()+"&ids="+ids
			
		});
		
	}
	
	
	
	
	function cancel(){
		$.del("确定作废当前选中的数据吗?","cwInvoiceLead/lead/cancel.do",{"ids":Qm.getValuesByName("id").toString()});

	}
	
</script>
</head>
<body>
	<qm:qm pageid="TJY2_CW_FPGL" script="false" singleSelect="false">
		<qm:param name="lead_id" value="<%=userId%>" compare="=" />
		<qm:param name="pk_lead_id" value="${param.id}" compare="=" />
	</qm:qm>
</body>
</html>