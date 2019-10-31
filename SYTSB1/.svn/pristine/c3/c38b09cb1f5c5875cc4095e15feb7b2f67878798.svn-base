<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车辆保养提醒列表</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.25,labelAlign:'right',labelSeparator:'',labelWidth:100},//可以自己定义 layout:column,float,
		sp_fields : [
				{ name : "car_num", compare : "like" },
				{ name : "wb_com_name", compare : "like"},
				{ name : "item_name", compare : "like"},
				{ group : [
					{ name : "apply_date", compare : ">=", value : "" },
					{ label : "到", name : "apply_date", compare : "<=",value:"",labelAlign:"center",labelWidth:20}
				]
				}],
		listeners : {
			rowClick : function(rowData, rowIndex) {
			},
			rowDblClick : function(rowData, rowIndex) {
			},
			selectionChange : function(rowData, rowIndex) {
			},
			rowAttrRender : function(rowData, rowid) {
				var fontColor="black";
	            return "style='color:"+fontColor+"'";
			}
		}
	};
</script>
</head>

<body>
	<qm:qm pageid="TJY2_CAR_WB_COMPARE" script="false" singleSelect="true">
	</qm:qm>
</body>
</html>