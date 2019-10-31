<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/k/kui-base-form.jsp" %>
<title>验证列表</title>

<style type="text/css">
#workaround {width:100%;overflow:;text-align:center;margin:0px 40px 0px 0px;}
</style>

<script type="text/javascript">

/* $(document).ready(function(){
	
	
	}); */
      $(function() {
    	  $("#formObj").initForm({ //参数设置示例
				toolbar : [{
					text : '上一步',
					id : 'close',
					icon : 'cancel',
					click : close
				} , 
				{
					text : '提交通过',
					id : 'save',
					icon : 'save',
					click : save
				} ],
				getSuccess : function(res) {
				
				}, afterParse: function(fm){
					 var sum=0;
		    		    var device_code="";
		    		    setTimeout("sub()",1000);
		    		   // sub();
				}
			});
//$("body").mask("系统正在努力为您验证二维码，请耐心等待！");
/*  for(var i=0;i<device_qr_codes.length;i++){
	if("${param.type}"=="2"){
		if(check_types[i]=="安改维监督检验"){
			$("#list").append("<div id="+device_qr_codes[i]+">监督检验，二维码："+device_qr_codes[i]+"</div>");
		 }else if(check_types[i]=="定期检验"){
			 $("#list").append("<div id="+device_qr_codes[i]+">定期检验，二维码："+device_qr_codes[i]+"</div>");
		 }
	}else if("${param.type}"=="3"){
		 $("#list").append("<div id="+device_qr_codes[i]+">定期检验，二维码："+device_qr_codes[i]+"</div>");
	}
	
	   
} 
 */
 
    	  var device_qr_code="${param.device_qr_code}";
    		var check_type=""+api.data.check_type;
    		var device_registration_code="${param.device_registration_code}";
    		var device_qr_codes=device_qr_code.split(",");
    		var check_types=check_type.split(",");
    		var device_registration_codes=device_registration_code.split(",");
    		
    		for(var i=0;i<device_qr_codes.length;i++){
    			if("${param.type}"=="2"){
    				if(check_types[i]=="安改维监督检验"){
    					$("#list").append("<div id="+device_qr_codes[i]+">监督检验，设备代码："+device_registration_codes[i]+"，二维码："+device_qr_codes[i]+"</div>");
    				 }else if(check_types[i]=="定期检验"){
    					 $("#list").append("<div id="+device_qr_codes[i]+">定期检验，设备代码："+device_registration_codes[i]+"，二维码："+device_qr_codes[i]+"</div>");
    				 }
    			}else if("${param.type}"=="3"){
    				 $("#list").append("<div id="+device_qr_codes[i]+">定期检验，设备代码："+device_registration_codes[i]+"，二维码："+device_qr_codes[i]+"</div>");
    			}
    			
    			   
    		}
   
    		
/*  $("body").unmask();
		//top.$.notice("验证成功！");
		subCheck(pis_input,pexamine_names,pcheck_flows,porg_ids,pactivity_id,pid);//提交审核
	 if (sum>0) {
		$.ligerDialog.alert("很抱歉，由于"+device_code+"共"+sum+"台金属二维码有误，未能通过成都市质监局的验证，请核实。可联系院信息中心辅助查询86607814。", "提示");
	}
	     Qm.refreshGrid(); */
});
      function save(){
    	  if("${param.type}"=="2"){
    		  if(pis_input==""){
    			  $.ligerDialog.alert("很抱歉,由于没有验证通过的报告，暂时不能提交审核！");
    		  }else{
    			  api.data.window.subCheck(pis_input,pexamine_names,pcheck_flows,porg_ids,pactivity_id,pid,nid,nactivity_id);//提交审核
    			  api.close();
    		  }
    		  
    	  }else if("${param.type}"=="3"){
    		  if(pcheck_status==""){
    			  $.ligerDialog.alert("很抱歉,由于没有验证通过的报告，暂时不能生成报告！");
    		  }else{
    			  api.data.window.startFlow(pcheck_status,pis_report_confirm,pcheck_type_code,pinfo_status,pinspection_id,pid);//生成报告并启动流程
    			  api.close();
    		  }
    		 
    	  }
    	  
      }
      function close(){
	        api.close();
	    }
      
	//标志通过
	function auditOk(device_qr_codes){
		var img = "<img alt=' ' src='k/qm/ligerui/skin/icons/ok.gif' border='0'>";
		$("#"+device_qr_codes).prepend(img);
	}
	//标志不通过通过
	function auditNo(device_qr_codes){
		var img = "<img alt=' ' src='k/qm/ligerui/skin/icons/candle.gif' border='0'>";
		$("#"+device_qr_codes).prepend(img);
	}
	function sub(){

		//alert(1)
		var device_qr_code="${param.device_qr_code}";
		var report_sn="${param.report_sn}";
		var id="${param.id}";
		var device_registration_code="${param.device_registration_code}";
		//提交审核参数
		var is_input ="";
		var examine_names ="";
		var check_flows ="";
		var org_ids = "";
		var activity_id="";
		var check_type=""
		var check_type_name=""
		//提交审核参数
		var is_inputs =[];
		var examine_namess =[];
		var check_flowss =[];
		var org_idss = [];
		var activity_ids=[];
		var check_type_names=[];
		//审核提交通过参数
		 pis_input =[];
		 pexamine_names =[];
		 pcheck_flows =[];
		 porg_ids = [];
		 pactivity_id=[];
		 pid=[];
		 nid=[];
		 nactivity_id=[];
		//生成报告参数
		 var check_status="";
		 var is_report_confirm="";
		 var check_type_code="";
		 var info_status="";
		 var inspection_id="";
		//生成报告参数
		 var check_statuss=[];
		 var is_report_confirms=[];
		 var check_type_codes=[];
		 var info_statuss=[];
		 var inspection_ids=[];
		  pcheck_status=[];
		  pis_report_confirm=[];
		  pcheck_type_code=[];
		  pinfo_status=[];
		  pinspection_id=[];
		if("${param.type}"=="2"){
			check_type=""+api.data.check_type;
			check_type_name=""+api.data.check_type_name;
			is_input="${param.is_input}";
			examine_names="${param.examine_names}";
			check_flows="${param.check_flows}";
			org_ids="${param.org_ids}";
			activity_id="${param.activity_id}";
			is_inputs=is_input.split(",");
			examine_namess=examine_names.split(",");
			check_flowss=check_flows.split(",");
			org_idss=org_ids.split(",");
			activity_ids=activity_id.split(",");
			check_types=check_type.split(",");
			check_type_names=check_type_name.split(",");

		} else if("${param.type}"=="3"){
			check_status="${param.check_status}";
		    is_report_confirm="${param.is_report_confirm}";
			check_type_code="${param.check_type_code}";
			info_status="${param.info_status}";
			inspection_id="${param.inspection_id}";
			check_statuss=check_status.split(",");
			is_report_confirms=is_report_confirm.split(",");
			check_type_codes=check_type_code.split(",");
			info_statuss=info_status.split(",");
			inspection_ids=inspection_id.split(",");
		}
		//为div加载列表
		var device_qr_codes=device_qr_code.split(",");
		var report_sns=report_sn.split(",");
		var ids=id.split(",");
		var device_registration_codes=device_registration_code.split(",");
	    if("${param.type}"=="2"){
	    	for(var i=0;i<device_qr_codes.length;i++){ 
	    		 var url="";
	    		 if(check_type_names[i]=="安装"){
	    			 url ="reportValidationAction/validation.do?deviceQrCode="+device_qr_codes[i]+"&report_sn="+report_sns[i]+"&checkType=2"+"&inspectionInfo="+ids[i];
	    		 }else if(check_types[i]=="定期检验"||check_type_names[i]=="改造"||check_type_names[i]=="重大修理"||check_type_names[i]=="修理"){
	    			 url ="reportValidationAction/validation.do?deviceQrCode="+device_qr_codes[i]+"&deviceRegistrationCode="+device_registration_codes[i]+"&checkType=3"+"&inspectionInfo="+ids[i];
	    		 }
	    		
	    		  $.ajax({
	    			url : url,
	    			type : "post",
	    			async : false,
	    			success : function(data) {
	    				if(data.success){
	    					 auditOk(device_qr_codes[i])
	    				     pis_input.push(is_inputs[i]);
	    		 			 pexamine_names.push(examine_namess[i]);
	    		 			 pcheck_flows .push(check_flowss[i]);
	    		 			 porg_ids.push(org_idss[i]);
	    		 			 pactivity_id.push(activity_ids[i]);
	    		 			 pid.push(ids[i]);
	    		 			
	    				 }else{
	    					 nid.push(ids[i]);
	    					 nactivity_id.push(activity_ids[i]);
	    					 auditNo(device_qr_codes[i])
	    					/* if(sum==0){
	    						device_code=device_qr_codes[i];
	    					}else{
	    						device_code=device_code+"、"+device_qr_codes[i];
	    					}
	    					 sum++; */
	    				}
	    			}
	    		});  
	    		
	    	}
	    }else if("${param.type}"=="3"){
	    	for(var i=0;i<device_qr_codes.length;i++){ 
	   		 var url="reportValidationAction/validation.do?deviceQrCode="+device_qr_codes[i]+"&deviceRegistrationCode="+device_registration_codes[i]+"&checkType=3"+"&inspectionInfo="+ids[i];
	   		  $.ajax({
	   			url : url,
	   			type : "post",
	   			async : false,
	   			success : function(data) {
	   				if(data.success){
	   					auditOk(device_qr_codes[i])
	   		 			pcheck_status.push(check_statuss[i]);
	   		 		    pis_report_confirm.push(is_report_confirms[i]);
	   		 		    pcheck_type_code.push(check_type_codes[i]);
	   		 		    pinfo_status.push(info_statuss[i]);
	   		 		    pinspection_id.push(inspection_ids[i]);
	   		 		    pid.push(ids[i]);
	   		 			
	   				 }else{
	   					 auditNo(device_qr_codes[i])
	   					/* if(sum==0){
	   						device_code=device_qr_codes[i];
	   					}else{
	   						device_code=device_code+"、"+device_qr_codes[i];
	   					}
	   					 sum++; */
	   				}
	   			}
	   		});  
	   		
	   	}
	    	
	    } 
	}
	
</script>
</head>
<body >
<div id="workaround"  style="overflow:auto; width:100%; height:100%" >
<form name="formObj" id="formObj" method="post" action="">
	 <div id="list">
	 </div>
	 </form>
</body>
</html>