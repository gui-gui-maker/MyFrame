<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<title>已处理业务</title>
	<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>    
<script type="text/javascript">
     var qmUserConfig = {
        tbar:[ 
               { text:'查看审批完成进度', id:'deal',icon:'detail', click: look}
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
    

     function look(){
                var process_id=Qm.getValueByName("process_id");
                var activityId=Qm.getValueByName("activity_id");
                trackProcess(process_id);
            }

     
     function deal() {
    	    
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
    }
     function submitAction() {
         Qm.refreshGrid();
     } 
     </script>
</head>
<body>
      <qm:qm pageid="TJY2_BUY_DONE_LIST" singleSelect="true"></qm:qm>
</body>
</html>