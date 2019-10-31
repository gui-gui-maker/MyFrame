<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="/k/kui-base-list.jsp"%>
    <title>证书/报告类型</title>
 <script type="text/javascript">
   var qmUserConfig = {
		   sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
   		sp_fields:[{name:"report_name",compare: "like"}],
   		tbar: [{text: '删除', id: 'del', icon: 'delete', click: del}],
   		listeners: {
   			selectionChange: function(rowData, rowIndex) {
   				var count = Qm.getSelectedCount();
   				Qm.setTbar({
   					del: count>0
   				});
   			}
   		}
   	};
 
   function del() {
	   	$.del(kFrameConfig.DEL_MSG, "certificateReportAction/deleteCertReports.do", {
			"ids": Qm.getValuesByName("rid").toString()
		});
	}
  /*  function getSelectedReport(){
       if(Qm.getSelectedCount()!=1){
           return null;
       }else{
           return {
               id: Qm.getValueByName("id"),
               report_name: Qm.getValueByName("report_name"),
               report_type: Qm.getValueByName("report_type"),
               reference_device: Qm.getValueByName("reference_device"),
               report_range: Qm.getValueByName("report_range")
               
           };
       }
   } */
   </script> 

</head>
<body>
	<qm:qm pageid="certificate_reports">
		<qm:param name="id" value="${param.id}" compare="=" />
	</qm:qm>
</body>
</html>