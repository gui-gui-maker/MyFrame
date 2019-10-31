<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus = "${param.pageStatus}">
<title>固定资产信息</title>
<%@ include file="/k/kui-base-form.jsp"%>
<!-- 生成条形码JS导入 -->
<script type="text/javascript">
	var pageStatus = "${param.pageStatus}";
	var tbar="";
	$(function() {
		var big_class = api.data.bigClasss.split(",");
		var fk_tsjc_device_document_id =  api.data.device_ids.split(",");
		var report_type =  api.data.report_types.split(",");
		var id = api.data.id.split(",");
		
		printTagsEBylodop(big_class,fk_tsjc_device_document_id,report_type,id);
 	})
	// lodop打印二维码标签
	function printTagsEBylodop(bigClassifys,ids,report_ids,info_ids){	
		/* var bigClassifys = Qm.getValuesByName("big_class");
		var ids = Qm.getValuesByName("fk_tsjc_device_document_id");
		var report_ids = Qm.getValuesByName("report_type");
		var info_ids = Qm.getValuesByName("id"); */
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
		api.close();
	}
</script>
</head>
<body>
	
</div>

</body>
</html>
