<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>报告列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
			var qmUserConfig = {
					sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:75},	// 默认值，可自定义
		 	        sp_fields:[
		            {name:"report_sn",compare:"like"},
		            {name:"check_op_name",compare:"like"},
		            {name:"org_name",compare:"like"},
		            {name:"COM_NAME",compare:"like"},
		            {name:"device_registration_code",compare:"like"},
		            {group: [
		  				{name: "ADVANCE_TIME", compare: ">="},
		  				{label: "到", name: "ADVANCE_TIME", compare: "<=", labelAlign: "center", labelWidth:20}
		 	  		 ]}
		        ],
	            tbar:[
			        { text:'选择', id:'select',icon:'check',click:getSelectedPersons},
			        "-",
			        { text:'关闭', id:'close',icon:'modify',click:close}
	            ],
	            listeners: {
	            	onAfterShowData : function() {
	    				initGridSelectRange();
	    			},onCheckRow : function(checked,rowdata, rowindex, rowDomElement){
	    				parent.addOrRemovePermision(checked,rowdata);
	    			},
	    			onBeforeCheckAllRow : function(){
	    				return false;
	    			}/*,
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({});
	                }*/
	            }
	        };
			function initGridSelectRange(){
				var idRange = parent.getResourcePermissionArr();
				Qm.getQmgrid().selectRange("id", idRange);
			}
			function getSelectedPersons(){	
				api.data.parentWindow.callBackReport(
				Qm.getValuesByName("ADVANCE_TIME").toString(), 
				Qm.getValuesByName("id").toString(),
				Qm.getValuesByName("report_sn").toString(),
				Qm.getValuesByName("check_op_name").toString(), 
				Qm.getValuesByName("org_name").toString(),
				Qm.getValuesByName("COM_NAME").toString(),
				Qm.getValuesByName("check_unit_id").toString(),
				Qm.getValuesByName("device_registration_code").toString());
				api.close();
			}
//          function getSelectedPerson(){
//         	return {
// 	           	 id: Qm.getValueByName("id").toString(),
// 	           	 report_sn: Qm.getValueByName("report_sn").toString(),};
//         }
			function close(){
				api.close();
			}
			
			//列表刷新
			function refreshGrid() {
			    Qm.refreshGrid();
			}
        </script>
	</head>
	<body>
		<qm:qm pageid="TJY2_report57ZL"  pagesize="100">
		</qm:qm>
	</body>
</html>