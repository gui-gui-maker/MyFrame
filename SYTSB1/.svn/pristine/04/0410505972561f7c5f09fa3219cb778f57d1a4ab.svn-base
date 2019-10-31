<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<% CurrentSessionUser user = SecurityUtil.getSecurityUser(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%
CurrentSessionUser usee = SecurityUtil.getSecurityUser();
User uu = (User)usee.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();

%>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
					tbar:[{
							text: '详情', id: 'detail', icon: 'detail', click:detail
						}, '-' ,{
							text: '删除', id: 'del' , icon:'delete', click:del 
						}, '-' ,{
							text: '退票', id: 'refund' ,icon:'return', click:refund
						}
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
								del:count>0,
								refund:count>0
							});
						}
					}
	};
	
	function detail(){
		 var id = Qm.getValueByName("id");
	        top.$.dialog({
	            width: 700,
	            height: 250,
	            lock: true,
	            parent: api,
	            data: {
	                window: window
	            },
	            title: "详情",
	          content : 'url:app/finance/cwInvoice_lead_datail.jsp?pageStatus=detail&id='+ id
			//content : 'url:app/finance/cwInvoice_manage_list1.jsp?pageStatus=detail&id='+ id
		});
	}
	
	function del() {
		$.del(kFrameConfig.DEL_MSG, "cwInvoiceLead/lead/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
	
	function refund(){
		var id = Qm.getValueByName("id");
		if(!id){
            top.$.notice('请先选择要退 的数据！',3);
            return;
        }
        $.ligerDialog.confirm('是否要退票？', function (yes){
            if(!yes){return false;}
            top.$.ajax({
                         url: "cwInvoiceLead/lead/refund.do?id="+id,
                         type: "GET",
                         dataType:'json',
                         async: false,
                         success:function (data) {
                            if(data.success){
                                $.ligerDialog.success("退票成功！");
                                Qm.refreshGrid();//刷新
                            }else{
                                $.ligerDialog.warn(data.msg);
                            }
                         },
                         error:function () {
                             $.ligerDialog.warn("退票失败！");
                         }
                     });
        });
	}
	
</script>
</head>
<body>
	<qm:qm pageid="TJY2_CW_PGLY" script="false" singleSelect="false">
		<qm:param name="lead_id" value="<%=userId%>" compare="=" />
	</qm:qm>
</body>
</html>