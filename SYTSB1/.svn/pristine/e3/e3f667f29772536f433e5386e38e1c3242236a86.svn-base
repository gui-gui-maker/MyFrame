<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>车辆选择</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript" src="app/common/js/render.js"></script>
		<script type="text/javascript" src="app/oa/select/org_select.js"></script>
		<script type="text/javascript">
		var arrayObj;
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.45,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
            sp_fields:[
            	{name:"car_num", compare:"like"},
            	{name:"car_brand", compare:"like"},
            	{name : "manager_room_code", compare : "=",xtype:"combo",onBeforeOpen:showDepartList				}
            ],
            listeners: {
                selectionChange : function(rowData,rowIndex){
                },
        		rowDblClick :function(rowData,rowIndex){
        		},
        		rowAttrRender : function(rowData, rowid) {
    	        }
            }
        };
      	//显示部门选择列表
    	function showDepartList(){
    		var unitId = "<sec:authentication property="principal.unit.id" />";
    		var unitName = "<sec:authentication property="principal.unit.orgName" />";
    		$(this).data('unitId',unitId);
    		$(this).data('unitName',unitName);
    		showOrgList.call(this);
    	}
	    function f_select(){ 
	   		var rows = Qm.getQmgrid().getSelectedRows();
         	return rows; 
        }
    </script>
	</head>
	<body>
		<qm:qm  pageid="oa_selcar" singleSelect="false"></qm:qm>
	</body>
</html>
