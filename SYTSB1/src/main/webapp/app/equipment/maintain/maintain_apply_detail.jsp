<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title></title>
<%@ include file="/k/kui-base-form.jsp"%>
<link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" /> 
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript">
var tbar="";
var ab=pageStatus="${param.status}";
//jQuery页面载入事件
var isChecked = "${param.isChecked}";
var serviceId = "${requestScope.serviceId}";//提交数据的id
var activityId = "${requestScope.activityId}";//流程id
var processId = "${requestScope.processId}";//过程
var areaFlag;//改变状态
	<bpm:ifPer function="TJY2_EQUIPMENT1_ZZSH" activityId = "${requestScope.activityId}">areaFlag="2";</bpm:ifPer>//站长（主任）审批
	<bpm:ifPer function="TJY2_EQUIPMENT1_ZRSH" activityId = "${requestScope.activityId}">areaFlag="3";</bpm:ifPer>//主任审批
	<bpm:ifPer function="TJY2_EQUIPMENT1_YLDSH" activityId = "${requestScope.activityId}">areaFlag="4";</bpm:ifPer>//院领导批准
	
	
	
	$(function () {
    	if(isChecked!="" && typeof(isChecked)!="undefined"){
    		$("#table1").attr("disabled",true);
    		$("#table2").attr("disabled",true);
    		if(areaFlag=="2"){
    			$("#table4").attr("disabled",true);
    			$("#table5").attr("disabled",true);
    		}else if(areaFlag=="3"){
    			$("#table3").attr("disabled",true);
    			$("#table5").attr("disabled",true);
    		}else if(areaFlag=="4"){
    			$("#table3").attr("disabled",true);
    			$("#table4").attr("disabled",true);
    		}
   	    	 $("#formObj").setValues("equipMaintainAction/detail.do?id=${requestScope.serviceId}"); 
   	    	 $("#formObj").attr("getAction","")
   	    	 
   	    	tbar=[{ text: '审核不通过', id: 'shbtg', icon: 'del', click: shbtg},
   	    	      { text: '审核通过', id: 'submit', icon: 'submit', click: sh},
                   { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
        } else {
        	$("#table3").attr("disabled",true);
			$("#table4").attr("disabled",true);
			$("#table5").attr("disabled",true);
            tbar=[{ text: '保存', id: 'up', icon: 'save', click: directChange},
                { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
            
        }
    	 if ("${param.status}"=="detail")
    	        tbar=[{ text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
    	$("#formObj").initForm({
            showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
    });
	//审核通过
	function sh(){
     	var id=$("#id").val();
     	var formData=$("#formObj").getValues();
     	var maintain=$.ligerui.toJSON(formData)
         $.ligerDialog.confirm('是否审核通过？', function (yes){
         if(!yes){return false;}
          $("body").mask("提交中...");
          getServiceFlowConfig("TJY2_EQUIPMENT_MAINTAIN1","",function(result,data){
                 if(result){
                      top.$.ajax({
                          url: "equipMaintainAction/subPass.do?id="+serviceId+
                         		 "&typeCode=TJY2_EQUIPMENT_MAINTAIN1&status=0"+"&activityId="+activityId+"&areaFlag="+areaFlag,
                          type: "POST",
                          dataType:'json',
                          data:{"maintain":maintain},
                          async: false,
                          success:function (data) {
                              if (data) {
       	                         	//$.ligerDialog.success("提交成功！");
	                            	// Qm.refreshGrid();
	                            	/* $.ligerDialog.success("审核成功！"); */
	                            	api.data.window.Qm.refreshGrid();
	 			                    api.close();
                             	 	
                              }
                          }
                      });
                 }else{
                  $.ligerDialog.alert("出错了！请重试！");
                  
                  $("body").unmask();
                 }
              });
         });
	}
	//审核不通过
	function shbtg(){
		 var id=$("#id").val();
       	 var formData=$("#formObj").getValues();
       	var maintain=$.ligerui.toJSON(formData)
    	 $.ligerDialog.confirm('是否要不通过？', function (yes){
	         if(!yes){return false;}
	    	 $("body").mask("正在处理，请稍后！");
	    	 getServiceFlowConfig("TJY2_EQUIPMENT_MAINTAIN1","",function(result,data){
	    		 if(result){
	                 top.$.ajax({
	                     url: "equipMaintainAction/shbtg.do?id="+serviceId+
	                    		 "&typeCode=TJY2_EQUIPMENT_MAINTAIN1&status=0"+"&activityId="+activityId+"&areaFlag="+areaFlag+"&processId="+processId,
	                     type: "POST",
	                     dataType:'json',
	                     data:{"maintain":maintain},
	                     async: false,
	                     success:function (data) {
	                         if (data) {
	                           	api.data.window.Qm.refreshGrid();
				                api.close();
	                         }
	                     }
	                 });
	            }else{
	              $.ligerDialog.alert("出错了！请重试！");
	              $("body").unmask();
	             }
	         });
     	});
   }
	  function directChange(){ 
      	var obj=$("#formObj").validate().form();
  	 if(obj){
  		 $("#formObj").submit();
  	 }else{
  		 return;
  	}}   
function chooseDevice(){
    top.$.dialog({
        width: 800,
        height: 450,
        lock: true,
        parent: api,
        title: "选择设备",
        content: 'url:app/equipment/maintain/device_choose.jsp',
        cancel: true,
        ok: function(){
            var p = this.iframe.contentWindow.getSelectedPerson();
            if(!p){
                top.$.notice("请选择设备！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                return false;
            }
            $("#fkDeviceId").val(p.id);
            $("#deviceName").val(p.name);
            $("#specifications").val(p.eq_model);
            $("#deviceNum").val(p.eq_no);
            $("#purchaseDate").val(p.eq_buy_date);
            $("#purchaseMoney").val(p.eq_value);
            $("#precision").val(p.eq_accurate);
        }
    });
}
</script>
</head>
<body>
		<form name="formObj" id="formObj"  action="equipMaintainAction/save.do"  getAction="equipMaintainAction/detail.do?id=${param.id}">
			  <h1 class="l-label" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">设&nbsp;备&nbsp;故&nbsp;障&nbsp;及&nbsp;维&nbsp;修&nbsp;申&nbsp;请&nbsp;表&nbsp;</h1><div style="height:2px">&nbsp;</div>
		      <input type="hidden" name="id" id="id" />
		      <input type="hidden" name="status" value="0"/>
  		
			<table  cellpadding="3" cellspacing="0" class="l-detail-table">
			<tr><td colspan="5">
			<table id="table1" cellpadding="3" cellspacing="0" class="l-detail-table">
			  <tr>
					<td class="l-t-td-left">编号</td>
					<td class="l-t-td-right" ><input name="numbers" id="numbers" type="text" ltype='text'  /></td>				
				</tr>
				<tr>
					<td class="l-t-td-left">设备名称</td>
					<td class="l-t-td-right">
					<input name="fkDeviceId" id="fkDeviceId" type="hidden"  />
					<input  validate="{required:true,maxlength:50}" ltype='text'  name="deviceName" id="deviceName" type="text" onclick="chooseDevice()"  ligerui="{iconItems:[{icon:'add',click:chooseDevice}]}"/></td>		
					<td class="l-t-td-left">设备精度</td>
					<td class="l-t-td-right"><input name="precision" id="precision" type="text" ltype='text'  /></td>				
				</tr>
				<tr>
					<td class="l-t-td-left">型号规格</td>
					<td class="l-t-td-right"><input name="specifications" id="specifications" type="text" ltype='text'  /></td>	
					<td class="l-t-td-left">设备编号</td>
					<td class="l-t-td-right"><input name="deviceNum" id="deviceNum" type="text" ltype='text'  /></td>	
				</tr>
				<tr>	
					<td class="l-t-td-left">购进日期</td>
					<td class="l-t-td-right">
					<input name="purchaseDate" id="purchaseDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" /></td>	
					<td class="l-t-td-left">设备原值</td>
					<td class="l-t-td-right">
					<input name="purchaseMoney" id="purchaseMoney" type="text" ltype='text'  />
					</td>	
				</tr>
				<tr>			
					<td class="l-t-td-left">预算维修金额</td>
					<td class="l-t-td-right">
						<input name="maintainMoney" id="maintainMoney" type="text" ltype='text'  />
					</td>
					<td class="l-t-td-left">处理结果</td>
					<td class="l-t-td-right">
						<input name="dispose" id="dispose" type="text" ltype='text' />
					</td>
				</tr>	
			</table>
			</td>
			</tr>
			<tr><td colspan="5">
				  <table id="table2" cellpadding="3" cellspacing="0" class="l-detail-table">
				     <tr>
				        <td class="l-t-td-left" width="10px" rowspan="2">设<br/>备<br/>故<br/>障<br/>分<br/>析<br/>说<br/>明</td>
					    <td colspan="5">
						<textarea name="maintenanceInstructions" id="maintenanceInstructions" rows="8" cols="50" class="l-textarea" ></textarea>
					     </td>
				     </tr>
				     <tr align="right">
				        <td class="l-t-td-left" colspan="2" align="right">设备管理员</td>
					    <td class="l-t-td-right">
						<input name="equipmentMan" id="equipmentMan" type="text" ltype='text'  />
					     </td>
				         <td class="l-t-td-left">日期</td>
					     <td class="l-t-td-right">
						<input name="equipmentDate" id="equipmentDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" />
					     </td>
				     </tr>
				  </table>
			</td></tr>
			<tr><td colspan="5">
				  <table id="table3" cellpadding="3" cellspacing="0" class="l-detail-table">
				     <tr>
				        <td class="l-t-td-left" width="10px" rowspan="2">检<br/>验<br/>室<br/>(试<br/>验<br/>站)<br/>意<br/>见<br/>设</td>
					      <td colspan="5">
						<textarea name="checkoutOpinion" id="checkoutOpinion" rows="8" cols="50" class="l-textarea" ></textarea>
					     </td>
				     </tr>
				     <tr align="right">
				        <td class="l-t-td-left" colspan="2" align="right">主任(站长)</td>
					    <td class="l-t-td-right">
						<input name="webmasterMan" id="webmasterMan" type="text" ltype='text'  />
					     </td>
				         <td class="l-t-td-left">日期</td>
					     <td class="l-t-td-right">
						<input name="webmasterDate" id="webmasterDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" />
					     </td>
				     </tr>
				  
				  
				  </table>
			</td></tr>
			<tr><td colspan="5">
				  <table id="table4" cellpadding="3" cellspacing="0" class="l-detail-table">
				     <tr>
				        <td class="l-t-td-left" width="10px" rowspan="2">保<br/>障<br/>部<br/>意<br/>见<br/>明</td>
					      <td colspan="5">
						<textarea name="securityOpinion" id="securityOpinion" rows="8" cols="50" class="l-textarea" ></textarea>
					     </td>
				     </tr>
				     <tr align="right">
				        <td class="l-t-td-left" colspan="2" align="right">主任</td>
					    <td class="l-t-td-right">
                           <input name="directorMan" id="directorMan" type="text" ltype='text'  />
					     </td>
				         <td class="l-t-td-left">日期</td>
					     <td class="l-t-td-right">
						<input name="directorDate" id="directorDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" />
					     </td>
				     </tr>
				  
				  
				  </table>
			</td></tr>
			<tr><td colspan="5">
				  <table id="table5" cellpadding="3" cellspacing="0" class="l-detail-table">
				     <tr>
				        <td class="l-t-td-left" width="10px" rowspan="2">分<br/>管<br/>院<br/>领<br/>导<br/>意<br/>见<br/>明</td>
					     <td colspan="5">
						<textarea name="leadershipOpinion" id="leadershipOpinion" rows="8" cols="50" class="l-textarea" ></textarea>
					     </td>
				     </tr>
				     <tr align="right">
				        <td class="l-t-td-left" colspan="2" align="right">院领导</td>
					    <td class="l-t-td-right">
						<input name="leadershipMan" id="leadershipMan" type="text" ltype='text' />
					     </td>
				         <td class="l-t-td-left">日期</td>
					     <td class="l-t-td-right">
						<input name="leadershipDate" id="leadershipDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" />
					     </td>
				     </tr>
				  
				  
				  </table>
			</td></tr>
			</table>
	</form>
</body>
</html>
