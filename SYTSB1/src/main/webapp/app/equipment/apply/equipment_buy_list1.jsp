<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>待出库设备</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript" src="app/common/js/render.js"></script>
		<script type="text/javascript">
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
            sp_fields:[
            	{name:"eq_name", compare:"like"},
				{name:"eq_no", compare:"like"},
				{name:"eq_model", compare:"like"},
            	{name:"eq_manufacturer", compare:"like"},
            	{name:"eq_accurate", compare:"like"}
            ],
            listeners: {
                selectionChange : function(rowData,rowIndex){
                },
        		rowDblClick :function(rowData,rowIndex){
        			detail(rowData.id);
        		}
            }
        };
        function submitAction() {
    		Qm.refreshGrid();
    	}
    	function close(){	
    		 api.close();
    	}
    	function f_select(){ 
    		 var rows = Qm.getQmgrid().getSelectedRows();
             return rows; 
        }
        // 刷新Grid
        function refreshGrid() {
            Qm.refreshGrid();
        }
    </script>
	</head>
	<body>	
		<qm:qm pageid="eq2_list06" singleSelect="false">
		</qm:qm>
	</body>
</html>
