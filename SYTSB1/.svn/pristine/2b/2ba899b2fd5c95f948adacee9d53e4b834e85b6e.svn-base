<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<title>已处理业务</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>
<%
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User uu = (User)curUser.getSysUser();
	com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
	String userId=e.getId();
	String uId = SecurityUtil.getSecurityUser().getId();
	%>
<script type="text/javascript">
     var qmUserConfig = {
        tbar:[ 
               { text:'查看审批完成进度', id:'look',icon:'detail', click: look}
        ],
        listeners : {
            rowDblClick : function(rowData, rowIndex) {
                detail(rowData);
            },
            selectionChange : function(rowData, rowIndex) {
                var count = Qm.getSelectedCount();//行选择数
                Qm.setTbar({
                	look : count == 1
                });
            },
            rowAttrRender : function(item, rowid) {
            },
           afterQmReady: function() {
                Qm.setCondition([
                ]);
                Qm.searchData();
            }
        }
    };
     function look(){
                var process_id=Qm.getValueByName("process_id");
                var activityId=Qm.getValueByName("activity_id");
                trackProcess(process_id);
            }
     function submitAction() {
         Qm.refreshGrid();
     } 
     </script>
</head>
<body>
      <qm:qm pageid="TJY2_LEAVE_DONE_LIST" singleSelect="true">
      <qm:param name="handler_id" compare="=" value="<%=uId%>"/>
      </qm:qm>
</body>
</html>