<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title></title>
<%@ include file="/k/kui-base-form.jsp"%>
<link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" /> 
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript">
var tbar="";
var ab=pageStatus="${param.status}";
var isChecked="${param.isChecked}";
var serviceId = "${requestScope.serviceId}";//提交数据的id
var activityId = "${requestScope.activityId}";//流程id
var processId = "${requestScope.processId}";//过程
var status1="${requestScope.status}";
var areaFlag;//改变状态
	<bpm:ifPer function="TJY2_EQ_ACCEPTANCE_ACCEPTOR" activityId = "${requestScope.activityId}">areaFlag="6";</bpm:ifPer>//验收人员审核
	
	$(function () {
    	if(isChecked!="" && typeof(isChecked)!="undefined"){
    		$("#table1").attr("disabled",true);
    		$("#table2").attr("disabled",true);
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
     	//alert(serviceId+"=="+activityId);
     	var id=$("#id").val();
     	var formData=$("#formObj").getValues();
     	var maintain=$.ligerui.toJSON(formData)
     	/* alert(maintain) */
         $.ligerDialog.confirm('是否审核通过？', function (yes){
         if(!yes){return false;}
          $("body").mask("提交中...");
          getServiceFlowConfig("TJY2_EQUIPMENT_ACCEPTANCE","",function(result,data){
                 if(result){
                      top.$.ajax({
                          url: "equipMaintainAction/subPass.do?id="+serviceId+
                         		 "&typeCode=TJY2_EQUIPMENT_ACCEPTANCE&status=1"+"&activityId="+activityId+"&areaFlag="+areaFlag,
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
	    	 getServiceFlowConfig("TJY2_EQUIPMENT_ACCEPTANCE","",function(result,data){
	    		 if(result){
	                 top.$.ajax({
	                     url: "equipMaintainAction/shbtg.do?id="+serviceId+
	                    		 "&typeCode=TJY2_EQUIPMENT_ACCEPTANCE&status=1"+"&activityId="+activityId+"&areaFlag="+areaFlag+"&processId="+processId,
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

	<form id="formObj"  action="equipMaintainAction/save.do"  getAction="equipMaintainAction/detail.do?id=${param.id}">
	<h1 class="l-label" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">维&nbsp;修&nbsp;设&nbsp;备&nbsp;验&nbsp;收&nbsp;报&nbsp;告</h1><div style="height:2px">&nbsp;</div>
		<input type="hidden" name="id" id="applyid" value="${param.id}"/>
  		<input type="hidden" name="status" value="1"/>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
			<tr><td  colspan="5">
			     <table id="table1" cellpadding="3" cellspacing="0" class="l-detail-table">
			     <tr>
					<td class="l-t-td-left">编号</td>
					<td class="l-t-td-right" ><input name="numbers" id="numbers" type="text" ltype='text'  /></td>				
				</tr>
				<tr>
					<td class="l-t-td-left">设备名称</td>
					<td class="l-t-td-right">
					<input name="fkDeviceId" id="fkDeviceId" type="hidden"  />
					<input  validate="{required:true,maxlength:50}" ltype="text"  name="deviceName" id="deviceName" type="text" onclick="chooseDevice()"  ligerui="{iconItems:[{icon:'add',click:chooseDevice}]}"/></td>		
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
				</table>
			 </td></tr>
			 <tr><td colspan="5">
				  <table id="table2" cellpadding="3" cellspacing="0" class="l-detail-table">
				     <tr>
				        <td class="l-t-td-left" width="10px" rowspan="2">设<br/>备<br/>维<br/>修<br/>情<br/>况<br/>说<br/>明</td>
					    <td colspan="5">
						<textarea name="otherSkills" id="remark" rows="8" cols="50" class="l-textarea" ></textarea>
					     </td>
				     </tr>
				     <tr align="right">
				        <td class="l-t-td-left" colspan="2" align="right">经办人</td>
					    <td class="l-t-td-right">
						<input name="apply_submit_object_name" id="apply_submit_object_name" type="text" ltype='text'  />
					     </td>
				         <td class="l-t-td-left">日期</td>
					     <td class="l-t-td-right">
						<input name="apply_need_date" id="apply_need_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" />
					     </td>
				     </tr>
				  </table>
				</td></tr>
				
				<tr><td colspan="5">
				  <table id="table3" cellpadding="3" cellspacing="0" class="l-detail-table">
				     <tr>
				        <td class="l-t-td-left" width="10px" rowspan="2">验<br/>收<br/>记<br/>录</td>
					      <td colspan="5">
						<textarea name="otherSkills" id="remark" rows="8" cols="50" class="l-textarea" ></textarea>
					     </td>
				     </tr>
				  </table>
				</td></tr>
				<tr><td colspan="5">
				  <table id="table4" cellpadding="3" cellspacing="0" class="l-detail-table">
				     <tr>
				        <td class="l-t-td-left" width="10px" rowspan="2">通<br/>电<br/>试<br/>验</td>
					      <td colspan="5">
						<textarea name="otherSkills" id="remark" rows="8" cols="50" class="l-textarea" ></textarea>
					     </td>
				     </tr>
				  </table>
				</td></tr>
				<tr><td colspan="5">
				  <table id="table5" cellpadding="3" cellspacing="0" class="l-detail-table">
				     <tr>
				        <td class="l-t-td-left" width="10px" >验<br/>收<br/>结<br/>论</td>
					     <td colspan="5">
						<textarea name="otherSkills" id="remark" rows="8" cols="50" class="l-textarea" ></textarea>
					     </td>
				     </tr>
				     <tr align="right">
				        <td class="l-t-td-left" align="right">参加检验人员</td>
					    <td class="l-t-td-right" colspan="3">
						<input name="apply_submit_object_name" id="apply_submit_object_name" type="text" ltype='text' />
					     </td>
				         <td class="l-t-td-left">日期</td>
					     <td class="l-t-td-right">
						<input name="apply_need_date" id="apply_need_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" />
					     </td>
				     </tr>
				  </table>
				</td></tr>
			</table>
	</form>
		
</body>
</html>
