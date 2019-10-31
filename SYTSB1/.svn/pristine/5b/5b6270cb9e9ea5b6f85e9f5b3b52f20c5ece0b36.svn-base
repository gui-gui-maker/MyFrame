<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
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
<script type="text/javascript">
$(document).ready(function() { 
	var yzm=<%=request.getParameter("yzm")%> 
	if(yzm==null||yzm==""||yzm=="null"){
	 return window.location.href="archives_read2_list.jsp"; 
	  }
	});
var	step_name="审核通过";
<%String a="审核通过";%>
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields:[
	         {name:"proposer",compare: "like"},
	         {name:"apply_unit",compare: "="},
	         {group: [
	  				{name: "apply_time", compare: ">="},
	  				{label: "到", name: "apply_time", compare: "<=", labelAlign: "center", labelWidth:20}
	  		 ],columnWidth:0.4}
		],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '归还', id: 'add', icon: 'return', click: add
		}],
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				var status = Qm.getValueByName("status");

				Qm.setTbar({
					detail: count==1,
					add: count>0,
						
				});
				if(status=="审核通过"){
					Qm.setTbar({detail: count==1,add:count>0});
				}else{
					Qm.setTbar({detail: count==1,add:count<0});
				}
			}/*,afterQmReady : function() {
				//alert(<%=a%>);
				Qm.setCondition([ {name : "status",compare : "=",value :step_name}]);
				Qm.searchData();
			},*/
		}
	};
	function add() {
		var yzm=<%=request.getParameter("yzm")%> 
		var id = Qm.getValueByName("id");
		if(!id){
			$.ligerDialog.warn('请先选择要归还权限的申请表！！',3);
            return;
        }
		$.ligerDialog.confirm('是否要归还权限？', function (yes){
		    if(!yes){return false;}
			top.$.ajax({
			         url: "archives/yijina/czqxgh.do?yzm="+yzm+"&id="+id,
			         type: "GET",
			         dataType:'json',
			         async: false,
			         success:function (data) {
			        	 if(data.success){
			        		 api.close();
			        		top.$.notice('已归还权限！',5);  
			        		return api.data.window.location.href="archives_read2_list.jsp"; 
			        		}else{
			                 $.ligerDialog.warn('出错了！');
			        	 }
			         }
			});
		});
	}
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 450,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/archives/archives_borrow_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
</script>
</head>
<body>

	<qm:qm pageid="TJY2_ARCHIVES_JY2" singleSelect="true" script="false">
		  <qm:param name="proposer_id" compare="=" value="<%=userId%>"/>
	
<%-- 			<qm:param name="status" value="<%=a%>" compare="=" /> --%>
	</qm:qm>
</body>
</html>