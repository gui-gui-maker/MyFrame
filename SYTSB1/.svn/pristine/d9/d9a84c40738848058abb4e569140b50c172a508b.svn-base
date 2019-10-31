<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript">
var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
		sp_fields : [
              {group: [{name: "jy_time", compare: ">="},
                       {label: "到", name: "jy_time", compare: "<=", labelAlign: "center", labelWidth:20}
                      ]},
                 {name: "money", compare: "="},
             	 {name:"account_name",compare:"like"},
              	 {name:"rest_money",compare:"like"},
              	 {name:"fefund_money",compare:"like"}
        ],
        listeners: {
            rowClick: function(rowData, rowIndex) {
            },
            rowDblClick: function(rowData, rowIndex) {
                detail();
            },  
            selectionChange: function(rowData, rowIndex) {
            },
            rowAttrRender: function(rowData, rowid) {
        		
            }
        }
    };
</script>
</head>
<body>
	<qm:qm pageid="TJY2_CW_FEFUND" script="false" singleSelect="true">
	
	</qm:qm>
</body>
</html>