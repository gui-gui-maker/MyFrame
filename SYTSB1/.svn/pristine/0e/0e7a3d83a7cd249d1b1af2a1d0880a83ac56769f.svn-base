<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>还款单填报</title>
<%@include file="/k/kui-base-list.jsp"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser usee = SecurityUtil.getSecurityUser();
User uu = (User)usee.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
%>
<script type="text/javascript">
var iscw="0";
<sec:authorize access="hasRole('TJY2_CW_CHECK')">
iscw = "1";
</sec:authorize>
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:85},
		sp_fields : [
			  {name:"repayment_status",compare:"like"},
			  {name:"borrower",compare:"like"},
			  {group: [
						{name: "borrow_money_date", compare: ">="},
						{label: "到", name: "borrow_money_date", compare: "<=", labelAlign: "center", labelWidth: 16}
					]},
			  
			{name:"department",compare:"like"},
			{name:"opening_bank",compare:"like"},
			{group: [
				{name: "repayment_time", compare: ">="},
				{label: "到", name: "repayment_time", compare: "<=", labelAlign: "center", labelWidth: 16}
			]}
		],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}
		, "-", {text:'提交', id:'submit',icon:'submit', click: submitData}
			<sec:authorize access="hasRole('TJY2_CW_CHECK')">
			,"-",{ text:'还款', id:'hk',icon:'submit', click: hk}
        	, "-", {text:'财务审核', id:'submitsh',icon:'dispose', click: chu1}
        	</sec:authorize>
        , "-", {
			text:'审批记录', id:'submityj',icon:'bluebook', click: chuyi}],
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail: count==1,
					submit: count==1
				});
			},
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
			
				var up_status = Qm.getValueByName("repayment_status");
				var sp_status = Qm.getValueByName("sp_status");
				var status={};
// 				if(iscw !='1'){
// 					if(up_status=="已还款"){
// 						Qm.setTbar({detail: count==1,hk:count<0});
// 					}else {
// 						Qm.setTbar({detail: count==1,hk:count>0});
//                 	}
// 				}else if (up_status=="已还款"){
// 					Qm.setTbar({detail: count==1,hk:count<0});
// 				}else {
// 					Qm.setTbar({detail: count==1,hk:count>0});
//                 }
				if(iscw !='1'){
					if(up_status=="未审批"){
						Qm.setTbar({detail: count==1,hk:count<0,submit:count<0,submitsh:count==1,submityj:count<0
						});
					}else if (up_status=="审批通过但未还款"){
						Qm.setTbar({detail: count==1,hk:count>0,submit:count<0,submitsh:count<0,submityj:count==1
						});
					}else if( up_status=="审批未通过"){
						Qm.setTbar({detail: count==1,hk:count<0,submit:count==1,submityj:count==1,submitsh:count<1
						});
					}else if( up_status=="已还款"){
						Qm.setTbar({detail: count==1,hk:count<0,submit:count<0,submityj:count==1,submitsh:count<1
						});
					}else if (up_status=="已作废"){
						Qm.setTbar({detail: count==1,hk:count<0,submit:false,submitsh:false,submityj:count==1
					    });
					}else {
	                    Qm.setTbar({detail: count==1,hk:count>0,submit:count==1,submitsh:count<0,submityj:count<0
	                    });
                	}
				}else if (up_status=="未审批"){
					Qm.setTbar({detail: count==1,hk:count<0,submit:false,submitsh:count==1,submityj:count==1
				    });
				} else if(up_status=="未提交"){
					Qm.setTbar({detail: count==1,hk:count<0,submit:true,submitsh:false,submityj:count==1
		            });
				}else if( up_status=="审批未通过"){
					Qm.setTbar({detail: count==1,hk:count<0,submit:count==1,submityj:count==1,submitsh:count<1
					});
				}else if( up_status=="已还款"){
					Qm.setTbar({detail: count==1,hk:count<0,submit:count<0,submityj:count==1,submitsh:count<1
					});
				}else if (up_status=="已作废"){
					Qm.setTbar({detail: count==1,hk:count<0,submit:false,submitsh:false,submityj:count==1
				    });
				}else {	
                    Qm.setTbar({detail: count==1,hk:count>0,submit:false,submitsh:false,submityj:count==1
                    });
                }
			},
			rowAttrRender : function(rowData, rowid) {
                var fontColor="black";
                if (rowData.repayment_status=='已还款'){
                    fontColor="green";
                }else if(rowData.repayment_status=='审批未通过') {
                    fontColor="red";
                }else if(rowData.repayment_status=='未审批') {
                    fontColor="orange";
                }else if(rowData.repayment_status=='审批通过但未还款') {
                    fontColor="blue";
                }else if(rowData.repayment_status=='已作废') {
                    fontColor="#8E8E8E";
                }
                return "style='color:"+fontColor+"'";
            }
			
		}
	};
	function chuyi(){
		var id = Qm.getValueByName("id");
		if(!id){
            top.$.notice('请先选择要查看的数据！',3);
            return;
        }
		top.$.dialog({
			width: 715,
			height: 350,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "审批记录",
			content: 'url:app/finance/cw_yijian.jsp?ids=' + id + '&pageStatus=detail'
		});
	}
	function chu1() {
		var id = Qm.getValueByName("id");
		var status = "aa";
		top.$.dialog({
			width: 900,
			height: 500,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "财务审核",
			content: 'url:app/finance/finance_bills_detail.jsp?id='+id+'&status='+status+'&pageStatus=detail'

		});
	}
	function submitData(){
        var id = Qm.getValueByName("id");
        $.ligerDialog.confirm('是否提交审核？', function (yes){
        	if(!yes){return false;}
            top.$.ajax({
                 url: "money/borrow/submit1.do?id="+id,
                 type: "GET",
                 dataType:'json',
                 async: false,
                 success:function (data) {
                    if(data.success){
                       top.$.notice('提交成功！！！',3);
                        Qm.refreshGrid();//刷新
                    }else{
                        $.ligerDialog.warn(data.msg);
                    }
                 },
                 error:function () {
                	 $.ligerDialog.error("出错了！请重试！!");
                 }
             });
        });
    }
	
	function hk() {
		var id = Qm.getValueByName("id");
		var up_status = Qm.getValueByName("repayment_status");
		var a="a";
		if("已还款"==up_status){
   		 $.ligerDialog.warn("此条还款已提交！");
   	 	}else{
		top.$.dialog({
			width: 900,
			height: 500,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "还款",
			content: 'url:app/finance/finance_bills_detail.jsp?id='+id+'&a='+a+'&pageStatus=detail'
		});}
	}
	function submit() {
		var id = Qm.getValueByName("id");
		var up_status = Qm.getValueByName("repayment_status");
        $.ligerDialog.confirm('是否提交还款？', function (yes){
        	if(!yes){return false;}
            top.$.ajax({
                 url: "money/borrow/huansubmit.do?id="+id,
                 type: "GET",
                 dataType:'json',
                 async: false,
                 success:function (data) {
                	 if("还款已提交"==up_status){
                		 $.ligerDialog.warn("此条还款已提交！");
                	 }else{
	                	 if(data.success){
	                		 top.$.notice('还款成功！',3);
	                         Qm.refreshGrid();//刷新
	                     }else{
	                         $.ligerDialog.warn(data.msg);
	                     }
                	 }
                 },
                 error:function () {
                     $.ligerDialog.warn("还款失败！");
                 }
             });
        });
	}
	
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 500,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/finance/finance_bills_detail.jsp?id=' + id + '&pageStatus=detail'
			//content: 'url:money/borrow/getBeanMap.do?id=' + id 
		});
	}
	function refreshGrid() {
	    Qm.refreshGrid();
	}
	function close(){
		api.close();
	}

</script>
</head>
<body>
<%-- 	<qm:qm pageid="TJY2_CW_HK" script="false" singleSelect="true"></qm:qm> --%>
<%-- 		<qm:param name="handle_id" value="<%=userId %>" compare="=" /> --%>
<div class="item-tm" id="titleElement">
	<div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="black">“黑色”</font>代表单据未提交，
                <font color="orange">“橙色”</font>代表未审批，
                <font color="red">“红色”</font>代表审批未通过，
                <font color="green">“绿色”</font>代表已还款，
                <font color="blue">“蓝色”</font>代表审批通过但未还款，
                <font color="#8E8E8E">“灰色”</font>代表单据作废。
			</div>
		</div></div>	
		
		<%StringBuffer sql = new StringBuffer(); %>
    <%String sql1 = ""; 
    String CSTG = "CSTG";%>
    <%CurrentSessionUser useres = SecurityUtil.getSecurityUser();
    String departmentId= useres.getDepartment().getId();%>
    <sec:authorize access="!hasRole('TJY2_CW_CHECK')">
		<%sql1="select * from TJY2_CW_BORROW_MONEY t where t.STATUS='"+ CSTG +"' and t.repayment_man_id='"+ userId +"' or registrantid='"+ uId +"' order by t.identifier desc Nulls last "; %>
		<%System.out.print("=========可怜的我只能看到自己的！！"+userId); %>    
	</sec:authorize>
	
	<sec:authorize access="hasRole('TJY2_BMFZR')">
   		<%sql1="select * from (select * from TJY2_CW_BORROW_MONEY t where t.STATUS='"+ CSTG +"' and t.department_id ='"+ departmentId +"' and t.department_id !='"+ 100025 +"' union all select * from TJY2_CW_BORROW_MONEY t where t.repayment_man_id='"+ userId +"' or registrantid='"+ uId +"' and t.department_id !='"+ departmentId +"' ) order by identifier desc Nulls last "; %>
   		<%System.out.print("=========我才是部长！！"+departmentId); %>
    </sec:authorize>

   	<sec:authorize access="hasRole('TJY2_CW_CHECK')">
   		<%sql1="select * from TJY2_CW_BORROW_MONEY t where t.STATUS='"+ CSTG +"' order by t.identifier desc Nulls last "; %>
		<%System.out.print("=========我是财务部长！！"); %>       
	</sec:authorize>
			
	<qm:qm pageid="TJY2_CW_HK" script="false" singleSelect="true" sql="<%=sql1.toString() %>">
<%-- 	<sec:authorize access="!hasRole('TJY2_CW_CHECK')"> --%>
<%-- 		<qm:param name="registrantid" value="<%=uId%>" compare="=" /> --%>
<%-- 		<qm:param name="repayment_man_id" value="<%=userId%>" compare="=" logic="or"/> --%>
<%-- 	</sec:authorize> --%>
	</qm:qm>
</body>
</html>