<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %> 
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser user = SecurityUtil.getSecurityUser();
User uu = (User)user.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
%>
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <!--获取当前登录人  -->
    <%CurrentSessionUser useres = SecurityUtil.getSecurityUser();
String username=useres.getName();
String userid= useres.getId();
String  eid = e.getId();
String use=useres.getName();
    %>
    <% 
SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
String nowTime=""; 
nowTime = dateFormat.format(new Date());
%>

<link rel="Stylesheet" href="app/finance/css/jquery.autocomplete.css" />
<link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
<script type="text/javascript" src="k/kui/frame/ligerTree.js"></script>
<script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
<script type="text/javascript" src="app/finance/js/jquery.autocomplete.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="app/qualitymanage/js/yuanshi.js"></script>      
<style>
div .input {
	width: 98%;
	padding: 0px 0px 0px 0px;
	line-height: 20px;
	border-bottom: 0px #9db9e2 solid;
}
.l-text { position:relative;
	border:0px solid #a5b1e2;padding:2px 2px;
}
</style>
	<script type="text/javascript">
		var pageStatus="${param.pageStatus}";
	 	var tbar="";
	 	var isChecked="${param.isChecked}";
		var serviceId = "${requestScope.serviceId}";//提交数据的id
		var activityId = "${requestScope.activityId}";//流程id
		var processId = "${requestScope.processId}";//退回id

		var areaFlag;//改变状态
	     <bpm:ifPer function="TJY2_ZL_ZSSQ_SQSH" activityId = "${requestScope.activityId}">areaFlag="1";</bpm:ifPer>
	  	 <bpm:ifPer function="TJY2_ZL_ZSSQ_FWSH" activityId = "${requestScope.activityId}">areaFlag="2";</bpm:ifPer>
	  	 <bpm:ifPer function="TJY2_ZL_ZSSQ_FWJSR" activityId = "${requestScope.activityId}">areaFlag="3";</bpm:ifPer>
	  	 <bpm:ifPer function="TJY2_ZL_ZSSQ_FWBJSRJD" activityId = "${requestScope.activityId}">areaFlag="3";</bpm:ifPer>
	  	
	 	$(function () {
	 		createReportPrintRecordGrid();
	 		if(isChecked!="" && typeof(isChecked)!="undefined"){
	 			$.ajax({
	 				url: "qualitymanage/QualityZssqAction/getDetail.do?id=${requestScope.serviceId}",
	 	            type: "POST",
	 	            success: function (resp) {
	 	            	if(resp.success){
							reportPrintGrid.loadData({
								Rows : resp.qualityZssqSubs,
								rowAttrRender :function(rowData, rowid) {}
							});
						}
	 	            }
	 			});
	 			 $("#jysq").transform("detail",function(){});
	        	 $("#jysq2").transform("detail",function(){});
	        	 $("#jysq").setValues("qualitymanage/QualityZssqAction/detail.do?id=${requestScope.serviceId}");
	        	 $("#jysq2").setValues("qualitymanage/QualityZssqAction/detail.do?id=${requestScope.serviceId}");
	        	 $("#jysq1").transform("detail",function(){});
	   	   		 $("#jysq4").transform("detail",function(){});
	   	 	  	 $("#jysq1").setValues("qualitymanage/QualityZssqAction/detail.do?id=${requestScope.serviceId}");
	   	 		 $("#jysq4").setValues("qualitymanage/QualityZssqAction/detail.do?id=${requestScope.serviceId}");
	   		   	 //$("#jysq3").setValues("QualityZssqSubAction/getQualityZssqSubs.do?id=${requestScope.serviceId}");
	   	   		 $("#tb1").setValues("qualitymanage/QualityZssqAction/detail.do?id=${requestScope.serviceId}"); 
		   	    
	   	    	 if(areaFlag==1){
	   	    		document.getElementById("applyOpinion").removeAttribute("readOnly");
	   	    	 }else if(areaFlag==2){
	   	    	 $("#tb1").setValues("qualitymanage/QualityZssqAction/detail.do?id=${requestScope.serviceId}"); 
	   	    		document.getElementById("serviceOpinion").removeAttribute("readOnly");
	   	    	 }else if(areaFlag==3){
	   	    	 $("#tb1").setValues("qualitymanage/QualityZssqAction/detail.do?id=${requestScope.serviceId}"); 
	   	    		document.getElementById("recipientOpinion").removeAttribute("readOnly");
	   	    		
	   	    	 }
	   	    	//document.getElementById("reportNumber").setAttribute("readOnly",'true');
	   	    	$("#reportNumber").removeAttr("onclick");
	   	    
	   	    	tbar=[ 
	   	    	    { text: '不通过', id: 'nosubmit', icon: 'forbid', click: nosubmitSh}, 
	   	    	 { text: '通过', id: 'submit', icon: 'accept', click: submitSh}, 
	                   { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
	        } else {
	            tbar=[{ text:'选择复制', id:'copy',icon:'copy', click:copyInfo},
	                  { text: '保存', id: 'up', icon: 'save', click: save},
	                  { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
	        
	    	 if ("${param.pageStatus}"=="detail"){
	    	        tbar=[{ text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];}}
	         $("#form1").initForm({
	            showToolbar: true,
	            toolbarPosition: "bottom",
	            toolbar: tbar,
	            getSuccess:function(resp){
		        	if(resp.success){
						reportPrintGrid.loadData({
							Rows : resp.qualityZssqSubs
						});
						$("#form1").setValues(resp.data);
					}
		        }
	    	});
	    });
	 	
	function submitSh(){
		var serviceId = "${requestScope.serviceId}";//提交数据的id
		var activityId = "${requestScope.activityId}";//流程id
		var formData = $("#form1").getValues();
		/* alert(serviceId+"=="+activityId); */
		$.ligerDialog.confirm('是否要通过？', function (yes){
		if(!yes){return false;}
        $("body").mask("提交中...");
        getServiceFlowConfig("TJY2_ZL_ZSSQ1","",function(result,data){
               if(result){
                    top.$.ajax({
                        url: "qualitymanage/QualityZssqAction/sgcjjybgsh.do?id="+serviceId+
                       		 "&typeCode=TJY2_ZL_ZSSQ1&status="+"&activityId="+activityId+"&areaFlag="+areaFlag
                       		 ,
                        type: "POST",
                        dataType:'json',
                        contentType: "application/json; charset=utf-8",
                        async: false,
                        data: $.ligerui.toJSON(formData),
                        success:function (data) {
                            if (data) {
                               $("body").unmask();
                               $.ligerDialog.alert("审核成功！！！");
                               api.data.window.Qm.refreshGrid();//刷新
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
	function nosubmitSh(){
		var formData = $("#form1").getValues();
		var obj=$("#form1").validate().form();
		$.ligerDialog.confirm('是否要不通过审核？', function (yes){
		if(!yes){return false;}
		$("body").mask("正在处理，请稍后！");
		getServiceFlowConfig("TJY2_ZL_ZSSQ1","",function(result,data){
	    	if(result){
	        	top.$.ajax({
	            	url: "qualitymanage/QualityZssqAction/sgcjjybgth.do?id="+serviceId+
	                	"&typeCode=TJY2_ZL_ZSSQ1&status="+"&activityId="+activityId+"&areaFlag="+areaFlag+"&processId="+processId,
	            	type: "POST",
	             	dataType:'json',
	            	contentType: "application/json; charset=utf-8",
	            	async: false,
	           		data: $.ligerui.toJSON(formData),
	             	success:function (data) {
	                	if (data) {
	                    	$.ligerDialog.alert("操作成功！！！");
	                    	api.data.window.Qm.refreshGrid();
	                    	api.close();
	                    	$("body").unmask();
	                      	if(obj){
	                             	/*  if(areaFlag=="1"){
	 		                            	$("#auditStep").val("部门负责人审核");
	                          		 }else if(areaFlag=="2"){
	 		                            	$("#auditStep").val("业务服务部审核");
	 	                             }
	                             	 $("#auditResult").val("未通过已退回");
	                        			 $("#form1").submit(); */
	                    	}else{
	                        	return;
	                        	}
	                             
	                           }
	                       },
	             	error:function () {
	                	$.ligerDialog.error("出错了！请重试！");
	                	$("body").unmask();
	                	}
	                   });
	        	}else{
	             	$.ligerDialog.error("出错了！请重试！");
	            	$("body").unmask();
	        	}
	    	});
		});
	}
 	function copyInfo(){
 		top.$.dialog({
			parent: api,
			width : 860, 
			height : 500, 
			lock : true, 
			title:"选择报告编号",
			content: 'url:app/qualitymanage/file_xuanze.jsp',
			data : {"parentWindow" : window}
		});
	}
	//保存
    function save(){
    	var obj=$("#form1").validate().form();
		if(obj){
			var formData = $("#form1").getValues();
			var data = {};
			data = formData;
			data["qualityZssqSubs"] = reportPrintGrid.getData();
			if(data["qualityZssqSubs"]==""||data["qualityZssqSubs"]=="undefined"){
				$.ligerDialog.error('提示：报告信息不能为空！');
				return
			}
			$("body").mask("正在提交，请稍后！");
	        $.ajax({
	        	url: "qualitymanage/QualityZssqAction/saveZssq.do",
	        	type: "POST",
	        	datatype: "json",
	        	contentType: "application/json; charset=utf-8",
	        	data: $.ligerui.toJSON(data),
	        	success: function (data, stats) {
	            	$("body").unmask();
	             	if(data.success){
		             	setTimeout("close()","200");
		            	top.$.dialog.notice({content:'操作成功！'});
	             	}else{
	               	  $.ligerDialog.error('提示： 操作失败！' );
	            	}
	             }
	         });
		 }else{
			return;
		}
	}
	function close(){
		api.data.window.Qm.refreshGrid();
		api.close();
	}
	function chooseOrg(){
	    top.$.dialog({
	    	width: 800,
	        height: 450,
	        lock: true,
	        parent: api,
	        title: "选择部门",
	        content: 'url:app/qualitymanage/yuanshi_org.jsp?par_id=',
	        cancel: true,
	        ok: function(){
	            var p = this.iframe.contentWindow.getSelectedPerson();
	            if(!p){
	                top.$.notice("请选择部门！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
	                return false;
	            }
	            $("#departmentId").val(p.id);
	            $("#department").val(p.name);
	        }
	    });
	}
	function choosePerson(){
        top.$.dialog({
            width: 800,
            height: 450,
            lock: true,
            parent: api,
            title: "选择人员",
            content: 'url:app/qualitymanage/yuanshi_choose.jsp',
            cancel: true,
            ok: function(){
                var p = this.iframe.contentWindow.getSelectedPerson();
                if(!p){
                    top.$.notice("请选择人员！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                    return false;
                }
                $("#peopleConcernedId").val(p.id);
                $("#applyName").val(p.name);
                $("#departmentId").val(p.org_id);
                $("#department").val(p.org_name);
            }
        });
    }
	function choosefile(){
	   	top.$.dialog({
			parent: api,
			width : 860, 
			height : 500, 
			lock : false, 
			title:"选择报告编号",
			content: 'url:app/qualitymanage/file_xuanze.jsp',
			data : {"parentWindow" : window}
		});
	
	 }
	function callBackReport(ADVANCE_TIME,id, report_sn,check_op_name,org_name,COM_NAME,check_unit_id,device_registration_code){
		var devRow = reportPrintGrid.rows;
		var isexist=false;
		var resportIsExist="";
		var ids  = id.split(",");
		var ADVANCE_TIMES  = ADVANCE_TIME.split(",");
		var temp = report_sn.split(",");
		var COM_NAMES  = COM_NAME.split(",");
		var check_op_names  = check_op_name.split(",");
		var check_unit_ids = check_unit_id.split(",");
		var org_names = org_name.split(",");
    	var device_registration_codes  = device_registration_code.split(",");
    	for (var i = 0; i < ids.length; i++) {
    		for(var j in devRow){
    			if(ids[i] == devRow[j].reportFk){
       	   			isexist = true;
       	   			resportIsExist = resportIsExist+temp[i]+",";
       	   			break;
       	   		}else{
       	   			isexist = false;
       	   		}
       	   	}
	   	   	 if(!isexist){
				var tt = {  
						reportFk : ids[i], 
						advanceTime: ADVANCE_TIMES[i], 
						reportSn:temp[i],
						reportCount : "1",
						comName : COM_NAMES[i], 
						checkOpName: check_op_names[i], 
						checkUnitId:check_unit_ids[i],
						orgName : org_names[i],
						deviceRegistrationCode : device_registration_codes[i]
				};
				reportPrintGrid.addRows(tt);
			}
   	 	}
    	if(resportIsExist != ""){
        	$.ligerDialog.warn("所选报告："+"<span style='color:red;'>"+resportIsExist.substring(0,resportIsExist.length-1)+"</span>"+" 已存在！");
      	}
	}	
	</script>
</head>
<body>
<form id="form1" action="qualitymanage/QualityZssqAction/saveZssq.do" getAction="qualitymanage/QualityZssqAction/getDetail.do?id=${param.id}">
	<input type="hidden" id="id" name="id"/>
	<input type="hidden" id="reportNumberId" name="reportNumberId"/>
	<input type="hidden" value="<%=eid %>"  name="peopleConcernedId" id="peopleConcernedId" input/>
	<input type="hidden" id="useUnitId" name="useUnitId"/>
	<input type="hidden" value="<%=useres.getDepartment().getId()%>"  id="departmentId" name="departmentId"/>
    <input type="hidden" id="status" name="status"/>
    <input type="hidden" id="createName" name="createName"/>
    <input type="hidden" id="createId" name="createId"/>
    <input type="hidden" id="createTime" name="createTime"/>
    <h1 id="jysq2" align="center" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">无&nbsp;原&nbsp;始&nbsp;资&nbsp;料&nbsp;打&nbsp;印&nbsp;检&nbsp;验&nbsp;报&nbsp;告&nbsp;/&nbsp;证&nbsp;书&nbsp;申&nbsp;请&nbsp;表</h1></br>
    <table id="jysq1"  >
    
           		 <tr>	
                    <td width="160px"></td>
                    <td width="160px" class="l-t-td-right">
                    </td>
                    <td width="380px">&nbsp;</td>
                    <td width="150px" align="center">文件编号:</td>
                    <td  style="width:270px;"   class="l-t-td-right"><input readonly="readonly" id="fileNumber" name="fileNumber"  ltype='text' readOnly="true"  type="text"/></td>
                    <td width="250px">&nbsp;</td>
                    <td width="150px" align="center"></td>
                    <td width="200" class="l-t-td-right"><input   id="implementationDate" name="implementationDate" ligerui="{format:'yyyy-MM-dd'}" readonly="readonly" value="<%=nowTime %>" ltype="date"  type="hidden"/></td>
                    <td ></td>
                </tr>
	</table>
    <table id="tb1"  border="1" cellpadding="3" class="l-detail-table">
     <tr  id="jysq">
            <td class="l-t-td-left">申请部门</td>
         	<td class="l-t-td-right" ><input readonly="readonly"  id="department" name="department" readonly="readonly" value="<%=useres.getDepartment().getOrgName() %>" validate="{required:true}"  ltype="text"  name="department" id="department"  type="text" /><!-- ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}" -->
         	</td>
         	<td class="l-t-td-left">申请人</td>
         	<td class="l-t-td-right"><input readonly="readonly" id="applyName" name="applyName" value="<%=use %>" ltype='text'  type="text"  /><!-- ligerui="{iconItems:[{icon:'user',click:choosePerson}]}" -->
         	</td>
        </tr>
         <tr id="jysq4">
            <td class="l-t-td-left">申请日期</td>
         	<td class="l-t-td-right" ><input ligerui="{disabled:true}"  id="applyTime" ligerui="{format:'yyyy-MM-dd'}" readonly="readonly" name="applyTime" readonly="readonly" value="<%=nowTime %>"  ltype="date"  type="text"  />
         	</td>
         	<td class="l-t-td-left">拟归还原始<br>资料日期</td>
         	<td class="l-t-td-right"><input id="returnTime" name="returnTime" title="（三个工作日内）" ligerui="{format:'yyyy-MM-dd'}"  type="text"  ltype="date"  /></td>
        </tr>
        <tr id="jysq3">
        	<td colspan="4">
        		<div id="reportPrintRecords"></div>
        	</td>
        </tr>
        <!-- <tr id="jysq3">
            <td class="l-t-td-left">使用单位名称</td>
         	<td class="l-t-td-right" ><input id="useUnit" name="useUnit"  validate="{required:true,maxlength:2000}" ltype="text" type="text"  />
  			 </td>
         	
         	<td class="l-t-td-left">数  量<br>共/台</td>
         	<td class="l-t-td-right"  ><input id="total"  name="total"  type="text" ltype="number" validate="{required:true,maxlength:100}" /></td>
        </tr>
        <tr>
            <td class="l-t-td-left">报告编号</td>
            <td  colspan="3" ><textarea  title="提示：报告编号可以手动输入，分割符号要用英文逗号！！！"  id="reportNumber" name="reportNumber"  rows="6" class="l-textarea" ltype="text" validate="{maxlength:2000}" ligerui="{iconItems:[{icon:'add',click:function(){choosefile()}}]}"></textarea>
            </td>
        </tr> -->
        
        <tr id="jysq5" >
            <td rowspan="2" class="l-t-td-left">申请部门<br>负责人意见</td>
            <td colspan="3" ><textarea type="text" validate="{maxlength:2000}" readonly="readonly" rows="6" id="applyOpinion" name="applyOpinion"  ltype="text" ></textarea></td>
        </tr>
        <tr>
        <td colspan="3"  class="l-t-td-left" style="padding-top: 0px;padding-left: 0px;padding-right: 0px;padding-bottom: 0px;">
        	<table id="tb-bmfzr"  border="0" cellpadding="0" class="l-detail-table">
        		<tr>
        			<td class="l-t-td-left">申请部门负责人</td>
        			<td class="l-t-td-left"><input ligerui="{disabled:true}" readonly="readonly" id="bmfzr"   name="bmfzr"  type="text" ltype="text"  /></td>
        			<td class="l-t-td-left">日期</td>
        			<td class="l-t-td-left"><input ligerui="{disabled:true}" ligerui="{initValue:'',format:'YYYY-MM-dd'}"  readonly="readonly" id="applyOpinionTime" name="applyOpinionTime"   type="text" ltype="date"  /></td>
        		</tr>
        	</table>
        </td>
        </tr>
        <!-- <tr  >
        <td colspan="1"  class="l-t-td-left">日期</td>
         	<td colspan="2" class="l-t-td-right"><input ligerui="{disabled:true}" ligerui="{initValue:'',format:'YYYY-MM-dd'}"  readonly="readonly" id="applyOpinionTime" name="applyOpinionTime"   type="text" ltype="date"  /></td>
        </tr> -->
        <tr>
            <td rowspan="2" class="l-t-td-left">服务部<br>负责人意见</td>
            <td colspan="3" ><textarea  readonly="readonly" validate="{maxlength:2000}"  id="serviceOpinion" name="serviceOpinion" rows="6" ltype="text" ></textarea></td>
        </tr>
        <tr>
        <td colspan="3"  class="l-t-td-left" style="padding-top: 0px;padding-left: 0px;padding-right: 0px;padding-bottom: 0px;">
        	<table id="tb-fwbfzr"  border="0" cellpadding="0" class="l-detail-table">
        		<tr>
        			<td class="l-t-td-left">服务部负责人</td>
        			<td class="l-t-td-left"><input ligerui="{disabled:true}" readonly="readonly" id="fwbfzr"   name="fwbfzr"  type="text" ltype="text"  /></td>
        			<td class="l-t-td-left">日期</td>
        			<td class="l-t-td-left"><input ligerui="{disabled:true}" ligerui="{initValue:'',format:'YYYY-MM-dd'}" readonly="readonly" id="serviceOpinionTime" name="serviceOpinionTime"   type="text" ltype="date"  /></td>
        		</tr>
        	</table>
        </td>
        </tr>
        <!-- <tr>
        <td colspan="1"  class="l-t-td-left">日期</td>
         	<td colspan="2" class="l-t-td-right"><input ligerui="{disabled:true}" ligerui="{initValue:'',format:'YYYY-MM-dd'}" readonly="readonly" id="serviceOpinionTime" name="serviceOpinionTime"   type="text" ltype="date"  /></td>
        </tr> -->
         <tr>
            <td rowspan="2" class="l-t-td-left">服务部<br>接收人</td>
            <td colspan="3" ><textarea  readonly="readonly" validate="{maxlength:2000}" rows="6" readonly="readonly" id="recipientOpinion" name="recipientOpinion" ltype="text" ></textarea></td>
        </tr>
        <tr>
        <td colspan="3"  class="l-t-td-left" style="padding-top: 0px;padding-left: 0px;padding-right: 0px;padding-bottom: 0px;">
        	<table id="tb-fwbjbr"  border="0" cellpadding="0" class="l-detail-table">
        		<tr>
        			<td class="l-t-td-left">服务部接收人</td>
        			<td class="l-t-td-left"><input ligerui="{disabled:true}" readonly="readonly" id="fwbjbr"   name="fwbjbr"  type="text" ltype="text"  /></td>
        			<td class="l-t-td-left">日期</td>
        			<td class="l-t-td-left"><input ligerui="{disabled:true}" ligerui="{initValue:'',format:'YYYY-MM-dd'}" readonly="readonly" id="recipientOpinionTime"   name="recipientOpinionTime"  type="text" ltype="date"  /></td>
        		</tr>
        	</table>
        </td>
        </tr>
        <!-- <tr>
        <td colspan="1"  class="l-t-td-left">日期</td>
         	<td  colspan="2"  class="l-t-td-right"><input ligerui="{disabled:true}" ligerui="{initValue:'',format:'YYYY-MM-dd'}" readonly="readonly" id="recipientOpinionTime"   name="recipientOpinionTime"  type="text" ltype="date"  /></td>
        </tr> -->
    </table>
</form>
</body>
</html>
