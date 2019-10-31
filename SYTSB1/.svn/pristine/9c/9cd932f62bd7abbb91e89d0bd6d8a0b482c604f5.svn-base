<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head >
<% CurrentSessionUser user=SecurityUtil.getSecurityUser(); %>

<title>待处理业务</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>    
<script type="text/javascript">
     var qmUserConfig = {
        tbar:[ 
              { text:'处理', id:'detail',icon:'detail', click: detail },
               { text:'查看审批进度', id:'deal',icon:'detail', click:look}
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
/*      //流转过程
    function  getFlow(){
    	var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 600,
			height: 350,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "流程卡",
			content: 'url:app/finance/finance_flow.jsp?id=' + id + '&pageStatus=detail'
		});
     } */
    
    function detail() {
                var config={
                    width :900,
                    height : 630,
                    id:Qm.getValueByName("id")
                }
                // 调用流程的方法
                openWorktask(config);
            }
     function look(){
                var process_id=Qm.getValueByName("process_id");
                var activityId=Qm.getValueByName("activity_id");
                trackProcess(process_id);
            }

     
    /*  function deal() {
    	    
    	 var id = Qm.getValueByName("id");
         var title =Qm.getValueByName("title");
        openWorktask({
            width : $(top).width(),
            height : $(top).height(),
            id : id,
            title :title,
            close : function(){
                Qm.refreshGrid();
            },
            data : {
                "window" : window,
          
            }
        });
    } */
     function submitAction() {
         Qm.refreshGrid();
     } 
   </script>
</head>
<body>
	<body>
	 <qm:qm pageid="TJY2_EQUIP_DEAL" singleSelect="true">
	 	<qm:param name="handler_id" compare="=" value="<%=user.getId()%>"/>
	 </qm:qm>

</body>
</html>