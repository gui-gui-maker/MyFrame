<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head >

<title>待处理业务</title>
<%@include file="/k/kui-base-list.jsp"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
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
              { text:'处理', id:'deal',icon:'detail', click: deal },
               { text:'查看审批进度', id:'detail',icon:'detail', click:detail}
               /*{ text:'流转过程', id:'flow_note',icon:'follow', click:getFlow}*/
        ],
        listeners : {
            rowDblClick : function(rowData, rowIndex) {
                detail(rowData);
            },
            selectionChange : function(rowData, rowIndex) {
                var count = Qm.getSelectedCount();//行选择数
                Qm.setTbar({
                    detail : count == 1,
                    progress: count==1,
                    deal:count==1
                });
            },
            rowAttrRender : function(item, rowid) {
                if (item.status == '逾期')
                    return "style='color:red'";
            },
           afterQmReady: function() {
                Qm.setCondition([
              //      {name: "handler_id", compare: "=", value: "<sec:authentication property='principal.id'/>"}
                ]);
                Qm.searchData();
            }
        }
    };
    function deal() {
	    var config={
	        width :800,
	        height : 630,
	        id:Qm.getValueByName("id")
	    }
	    // 调用流程的方法
	    openWorktask(config);
     }
     function detail(){
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
	 <qm:qm pageid="TJY2_LEAVE_PEND_LIST" singleSelect="true">
	 <qm:param name="handler_id" compare="=" value="<%=uId%>"/>
	 </qm:qm>
		
</body>
</html>