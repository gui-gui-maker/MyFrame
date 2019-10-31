
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="">
$(document).ready(function() { 


<%
String ss="";

if(request.getSession().getAttribute("edcrfvg")!=null){
	ss=request.getSession().getAttribute("edcrfvg").toString();}%>
var ss=<%=ss%>+"";
if(ss!="8888"){
	var pathName=window.document.location.pathname;  
    			var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1); 
 return window.location.href=projectName+"/finance/messageChecky_detail.jsp"; 
}

}); 
</script>
<script type="text/javascript">
    var khFileUploader;
	var qmUserConfig = {
		sp_fields : [
		],
		tbar:[
		{text: '详情', id: 'detail', icon: 'detail', click: detail}
            ],
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
					edit: count==1,
					del: count>0
				});
			}
		}
	};
	

var ids = Qm.getValueByName("id");
// 上传完成，开始保存汇编数据

		
		
		
function add() {
	top.$.dialog({
		width: 900,
		height: 600,
		lock: true,
		parent: api,
		data: {
			window: window
		},
		title: "新增",
		content: 'url:app/finance/importSalaryy_detail.jsp?pageStatus=add'
	});
}
	function edit() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 600,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:app/finance/salaryy_detail.jsp?id=' + id + '&pageStatus=modify'
		});
	}
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 600,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/finance/salaryy_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function del() {
		$.del(kFrameConfig.DEL_MSG, "finance/importSalaryAction/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
	
</script>

</head>

<body>
	<%
		
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User uu = (User)curUser.getSysUser();
		com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
		String userId=e.getId();
		String uId = SecurityUtil.getSecurityUser().getId();
		String depId = e.getOrg().getOrgCode();
	%>
	<qm:qm pageid="TJY2_GEGZCX">
		<sec:authorize access="!hasRole('sys_administrate')">
		<qm:param name="emp_id" value="<%=userId %>" compare="=" />
		</sec:authorize>
	</qm:qm>
	
</body>
</html>





