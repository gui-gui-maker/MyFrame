<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>交通厅数据</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},				
			sp_fields : [
			     		{
			     			name : "clpzh",
			     			compare : "like",
			     			value : ""
			     		},
			     		{
			     			name : "cx",
			     			compare : "like",
			     			value : ""
			     		}
			     		
			     		],
		tbar : [
		{
			text : '查看',
			id : 'detail',
			icon : 'detail',
			click : detail
		}
		
	
		],
	
	
		
		
		listeners : {
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				Qm.setTbar({
					
					detail : count == 1,
					
				});
			}
		}
	};



	
	
	

	function detail() {
		device_type = Qm.getValueByName("big_class").substring(0, 1);
		
		
		if("9" == device_type){
				top.$.dialog({
						width : 1000,
						height : 600,
						lock : true,
						title : "客运索道详情",
						content : 'url:app/device/device_detail_cableway.jsp?status=detail&device_type='
								+ device_type + '&id='+Qm.getValueByName("id"),
						data : {
							"window" : window
						}
					});
		}else{
			top.$.dialog({
				width : 1000,
				height : 600,
				lock : true,
				title : "特种设备信息",
				content : 'url:app/device/device_detail.jsp?status=detail&id='
						+ Qm.getValueByName("id") + "&device_type="
						+ Qm.getValueByName("big_class"),
				data : {
					"window" : window
				}
			});
		}
	}
	
	
	

	function submitAction() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="car_info_tanker" singleSelect="false">
	</qm:qm>
	
</body>
</html>
