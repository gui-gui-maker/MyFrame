<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>业务信息查询</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<%

%>
		<script type="text/javascript">
		

        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.25,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
            sp_fields:[ ],
            tbar:[
		        { text:'打印', id:'print',icon:'print',click:printTagsEBylodop},
		        "-",
		        { text:'关闭', id:'close',icon:'modify',click:close}
            ],
            listeners: {
            	selectionChange :function(rowData,rowIndex){
            		var count=Qm.getSelectedCount();//行选择个数
                    Qm.setTbar({print:count>0});
    			},
    			afterQmReady : function() {
    				Qm.setCondition([ 
    				                  {name : "id",compare : "in ",value : "("+api.data.id+")" , dataType :"user"}
    								]);
    				Qm.searchData();
    			}
            }
        };
        
		// lodop打印二维码标签
		function printTagsEBylodop(){	
			var bigClassifys = Qm.getValuesByName("big_class");
			var ids = Qm.getValuesByName("fk_tsjc_device_document_id");
			var report_ids = Qm.getValuesByName("report_type");
			var info_ids = Qm.getValuesByName("id");
			
			for(var i = 0 ; i < ids.length; i++ ){
				if(bigClassifys[i]=="3"){
					top.$.dialog({
						width : 1000,
						height : 800,
						lock : true,
						title : "特种设备使用标志",
						content : "url:app/device/usesign/device_elevator_usesign.jsp?device_id="+ids[i]+'&report_id='+report_ids[i]+'&inspection_info_id='+info_ids[i],
						data : {
							"window" : window
						}
					});
				}else if(bigClassifys[i]=="6" || bigClassifys[i]=="9" ){
					top.$.dialog({
						width : 1000,
						height : 800,
						lock : true,
						title : "特种设备使用标志",
						content : "url:app/device/usesign/device_yl_usesign.jsp?device_id="+ids[i]+'&report_id='+report_ids[i]+'&inspection_info_id='+info_ids[i],
						data : {
							"window" : window
						}
					});
				}else{
					top.$.dialog({
						width : 1000,
						height : 800,
						lock : true,
						title : "特种设备使用标志",
						content : "url:app/device/usesign/device_other_usesign.jsp?device_id="+ids[i]+'&report_id='+report_ids[i]+'&inspection_info_id='+info_ids[i],
						data : {
							"window" : window
						}
					});
				}
			}
		}
	
        
        // 刷新Grid
        function submitAction() {
            Qm.refreshGrid();
        }
        
        function closewindow(){
			api.close();
		}
    </script>
	</head>
	<body>	
		
		<qm:qm pageid="report_sign_report" singleSelect="false" seachOnload="false">
		</qm:qm>
		<script type="text/javascript">
</script>
	</body>
</html>