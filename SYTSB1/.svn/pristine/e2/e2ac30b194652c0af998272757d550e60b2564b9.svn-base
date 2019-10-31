<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>节假日管理</title>
        <%@include file="/k/kui-base-list.jsp"%>
        <script type="text/javascript">
        var qmUserConfig = {
			<tbar:toolBar type="tbar" code="pub_holiday" />,
            listeners: {
                rowDblClick :function(rowData,rowIndex){detail(rowData.id);},
                selectionChange :function(rowData,rowIndex){
                     var count = Qm.getSelectedCount();
                     Qm.setTbar({modify:count==1,detail:count==1,del:count>=1});
                }
            }
        };
        
        function _save_callback(){
        	Qm.refreshGrid();
        }
        
        function add(){
            top.$.dialog({
                width: 500,
                height: 300,
                lock:true,
                data: {callback:_save_callback},
                title:"新增节假日",
                content: 'url:pub/calendar/holiday_detail.jsp?status=add'
            });
        }
       
        function edit(){
            top.$.dialog({
                width: 500,
                height: 300,
                lock:true,
                data: {callback:_save_callback},
                title:"修改节假日",
                content: 'url:pub/calendar/holiday_detail.jsp?status=modify&id='+Qm.getValueByName("id")
            });
        } 
        
        function detail(id){
            if(Qm.getSelectedCount() == 1)
                id = Qm.getValueByName("id");
            top.$.dialog({
                width: 500,
                height: 300,
                lock: true,
                cancel: true,
                title: "查看节假日信息",
                content: 'url:pub/calendar/holiday_detail.jsp?status=detail&id=' + id
            });
        }
        
        function del(){
            $.del(kFrameConfig.DEL_MSG,"pub/calendar/holiday/delete.do",{"ids":Qm.getValuesByName("id").toString()});
        }
    </script>
    </head>
    <body>
        <qm:qm pageid="pub_holiday" singleSelect="false" />
    </body>
</html>
