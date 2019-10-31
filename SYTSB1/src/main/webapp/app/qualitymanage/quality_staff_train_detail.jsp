<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html;charset=UTF-8"%>

<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	String nowTime=""; 
	Date date = new Date();
	nowTime = dateFormat.format(date);%>
<% CurrentSessionUser user = SecurityUtil.getSecurityUser(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<head pageStatus="${param.pageStatus}">
<title></title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
<script type="text/javascript" src="app/finance/js/jquery.autocomplete.js"></script>
<link rel="Stylesheet" href="app/finance/css/jquery.autocomplete.css" />
<link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
<script type="text/javascript">
var tbar="";
var isChecked="${param.isChecked}";
var serviceId = "${requestScope.serviceId}";//提交数据的id
var activityId = "${requestScope.activityId}";//流程id
var processId = "${requestScope.processId}";//退回id
var areaFlag;//改变状态
	 <bpm:ifPer function="TJY2_ZL_ZGPX_BM"   activityId = "${requestScope.activityId}">areaFlag="1";</bpm:ifPer>
	 <bpm:ifPer function="TJY2_ZL_ZGPX_KYJS" activityId = "${requestScope.activityId}">areaFlag="2";</bpm:ifPer>
	 <bpm:ifPer function="TJY2_ZL_ZGPX_BGY"  activityId = "${requestScope.activityId}">areaFlag="3";</bpm:ifPer>
	 <bpm:ifPer function="TJY2_ZL_ZGPX_PXB"  activityId = "${requestScope.activityId}">areaFlag="4";</bpm:ifPer>
	 <bpm:ifPer function="TJY2_ZL_ZGPX_YZ"   activityId = "${requestScope.activityId}">areaFlag="5";</bpm:ifPer>
	$(function(){
		if(isChecked!="" && typeof(isChecked)!="undefined"){
        	$("#ctmb").transform("detail",function(){});
        	$("#ctmbCheck").transform("modify",function(){});
        	$("#ctmb,#ctmbCheck,#ctmb1" ).setValues("quality/staff/train/detail.do?id=${requestScope.serviceId}",function(res){
        		
        		var a=res.data.userDepIdeaTime;
            	var userDepIdeaTime=a.substring(a.indexOf("/")+1,a.lastIndexOf(" "));
            	$("#userDepIdeaTime").html(userDepIdeaTime);
            	$("#userDepIdeaTime").val(userDepIdeaTime);
            	
            	var b=res.data.skillManageIdeaTime;
            	var skillManageIdeaTime=b.substring(b.indexOf("/")+1,b.lastIndexOf(" "));
            	$("#skillManageIdeaTime").html(skillManageIdeaTime);
            	$("#skillManageIdeaTime").val(skillManageIdeaTime);
            	
            	var c=res.data.branchedPassageIdeaTime;
            	var branchedPassageIdeaTime=c.substring(c.indexOf("/")+1,c.lastIndexOf(" "));
            	$("#branchedPassageIdeaTime").html(branchedPassageIdeaTime);
            	$("#branchedPassageIdeaTime").val(branchedPassageIdeaTime);
            	
            	var d=res.data.trainFgIdeaTime;
            	var trainFgIdeaTime=d.substring(d.indexOf("/")+1,d.lastIndexOf(" "));
            	$("#trainFgIdeaTime").html(trainFgIdeaTime);
            	$("#trainFgIdeaTime").val(trainFgIdeaTime);
            	
            	var e=res.data.deanExamineIdeaTime;
            	var deanExamineIdeaTime=e.substring(e.indexOf("/")+1,e.lastIndexOf(" "));
            	$("#deanExamineIdeaTime").html(deanExamineIdeaTime);
            	$("#deanExamineIdeaTime").val(deanExamineIdeaTime);
        		
        	});
        	
   	    	if(areaFlag==1){
   	    		$("#ctmbyj").transform("modify",function(){});
	    		document.getElementById("userDepIdea").removeAttribute("readOnly");
	    	 }else if(areaFlag==2){
	    		 //$("#ctmbyj1").transform("modify",function(){});
	    		 $("#ctmb").setValues("qualitymanage/QualityZssqAction/detail.do?id=${requestScope.serviceId}"); 
	    		 document.getElementById("skillManageDepIdea").removeAttribute("readOnly");
	    	 }else if(areaFlag==3){
	    	     //alert(123);
	    		 //$("#ctmbyj2").transform("modify",function(){});
	    	 	 $("#ctmb").setValues("qualitymanage/QualityZssqAction/detail.do?id=${requestScope.serviceId}"); 
	    		document.getElementById("branchedPassageLeaderIdea").removeAttribute("readOnly");
	    	 }else if(areaFlag==4){
	    		//$("#ctmbyj3").transform("modify",function(){});
	    	    $("#ctmb").setValues("qualitymanage/QualityZssqAction/detail.do?id=${requestScope.serviceId}"); 
	    		document.getElementById("trainFgLeaderIdea").removeAttribute("readOnly");
	    	 }else if(areaFlag==5){
	    	   $("#ctmb").setValues("qualitymanage/QualityZssqAction/detail.do?id=${requestScope.serviceId}"); 
	    		document.getElementById("deanExamineIdea").removeAttribute("readOnly");
	    	 }
   	    	tbar=[{ text: '通过', id: 'submit', icon: 'accept', click: submitSh},
   	    	      { text: '不通过', id: 'nosubmit', icon: 'forbid', click: nosubmitSh},
                  { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
        } else {
            tbar=[{ text: '保存', id: 'up', icon: 'save', click: save},
                  { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
            
        }
    	 if ("${param.pageStatus}"=="detail")
    	        tbar=[//{ text: '打印', id: 'print', icon:'print', click:print },
    	              { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
    	$("#form1").initForm({
            showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar,
            getSuccess : function(res) { //
            	var a=res.data.userDepIdeaTime;
            	var userDepIdeaTime=a.substring(a.indexOf("/")+1,a.lastIndexOf(" "));
            	$("#userDepIdeaTime").html(userDepIdeaTime);
            	$("#userDepIdeaTime").val(userDepIdeaTime);
            	
            	var b=res.data.skillManageIdeaTime;
            	var skillManageIdeaTime=b.substring(b.indexOf("/")+1,b.lastIndexOf(" "));
            	$("#skillManageIdeaTime").html(skillManageIdeaTime);
            	$("#skillManageIdeaTime").val(skillManageIdeaTime);
            	
            	var c=res.data.branchedPassageIdeaTime;
            	var branchedPassageIdeaTime=c.substring(c.indexOf("/")+1,c.lastIndexOf(" "));
            	$("#branchedPassageIdeaTime").html(branchedPassageIdeaTime);
            	$("#branchedPassageIdeaTime").val(branchedPassageIdeaTime);
            	
            	var d=res.data.trainFgIdeaTime;
            	var trainFgIdeaTime=d.substring(d.indexOf("/")+1,d.lastIndexOf(" "));
            	$("#trainFgIdeaTime").html(trainFgIdeaTime);
            	$("#trainFgIdeaTime").val(trainFgIdeaTime);
            	
            	var e=res.data.deanExamineIdeaTime;
            	var deanExamineIdeaTime=e.substring(e.indexOf("/")+1,e.lastIndexOf(" "));
            	$("#deanExamineIdeaTime").html(deanExamineIdeaTime);
            	$("#deanExamineIdeaTime").val(deanExamineIdeaTime);
            }
    	});
		
    	$("body").append('<object style="height:1px;" id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0><embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed></object>');
    	
		$("#userName").autocomplete("employee/basic/searchEmployee.do", {
			 max: 12,    //列表里的条目数
	         minChars: 1,    //自动完成激活之前填入的最小字符
	         width: 200,     //提示的宽度，溢出隐藏
	         scrollHeight: 300,   //提示的高度，溢出显示滚动条
	         scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
	         matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
	         autoFill: false,    //自动填充
	         formatItem: function(row, i, max) {
	             return row.name + '   ' + row.mobileTel;
	         },
	         formatMatch: function(row, i, max) {
	             return row.name + row.mobileTel;
	         },
	         formatResult: function(row) {
	             return row.name;
	         }
	     }).result(function(event, row, formatted) {
	    	 $("#userId").val(row.id);
	     });
		
		$("#userDep").autocomplete("employee/basic/searchOrg.do", {
	        max: 12,    //列表里的条目数
	        minChars: 1,    //自动完成激活之前填入的最小字符
	        width: 200,     //提示的宽度，溢出隐藏
	        scrollHeight: 300,   //提示的高度，溢出显示滚动条
	        scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
	        matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
	        autoFill: false,    //自动填充
	        formatItem: function(row, i, max) {
	        //alert(row);
	            return row.orgName;
	        },
	        formatMatch: function(row, i, max) {
	            return row.orgName;
	        },
	        formatResult: function(row) {
	            return row.orgName;
	        }
	    }).result(function(event, row, formatted) {
	      	 $("#userDepId").val(row.orgId);
	    });	
	});

	function choosePerson(){
	    top.$.dialog({
	        width: 800,
	        height: 450,
	        lock: true,
	        parent: api,
	        title: "选择人员",
	        content: 'url:app/common/person_choose.jsp',
	        cancel: true,
	        ok: function(){
	            var p = this.iframe.contentWindow.getSelectedPerson();
	            if(!p){
	                top.$.notice("请选择人员！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
	                return false;
	            }
	            $("#userId").val(p.id);
	            $("#userName").val(p.name);
	            $("#userDep").val(p.org_name);
	            $("#userDepId").val(p.org_id);
	        }
	    });
	}

	
	function chooseOrg(){
	    top.$.dialog({
	        width: 800,
	        height: 450,
	        lock: true,
	        parent: api,
	        title: "选择部门",
	        content: 'url:app/common/org_choose.jsp',
	        cancel: true,
	        ok: function(){
	            var p = this.iframe.contentWindow.getSelectedPerson();
	            if(!p){
	                top.$.notice("请选择部门！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
	                return false;
	            }
	            $("#userDepId").val(p.id);
	            $("#userDep").val(p.name);
	            
	        }
	   	 });
	}   
	
	function submitSh(){
	     	var obj=$("#form1").validate().form();
	     	var formData = $("#form1").getValues();
	         $.ligerDialog.confirm("是否提交审核？", function (yes){
	         if(!yes){return false;}
	          $("body").mask("提交中...");
	          getServiceFlowConfig("TJY2_ZL_ZGPX","",function(result,data){
	                 if(result){
	                     top.$.ajax({
	                          url: "quality/staff/train/submitsh.do?id="+serviceId+
	                         		 "&typeCode=TJY2_ZL_ZGPX&status="+"&activityId="+activityId+"&areaFlag="+areaFlag,
	                          type: "POST",
	                          dataType:'json',
	                          contentType: "application/json; charset=utf-8",
	                          data:$.ligerui.toJSON(formData),
	                          async: false,
	                          success:function (data) {
	                        	  api.close();
	                        	  api.data.window.Qm.refreshGrid();
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
     	$.ligerDialog.confirm("是否要不通过审核？", function (yes){
            if(!yes){return false;}
     	 $("body").mask("正在处理，请稍后！");
     	 
     	 getServiceFlowConfig("TJY2_ZL_ZGPX","",function(result,data){
              if(result){
                   top.$.ajax({
                       url: "quality/staff/train/nosubmitSh.do?id="+serviceId+
                      		 "&typeCode=TJY2_ZL_ZGPX&status="+"&activityId="+activityId+
                      		 "&areaFlag="+areaFlag+"&processId="+processId,
                       type: "GET",
                       dataType:'json',
                       async: false,
                       success:function (data) {
                           if (data) {
                              $("body").unmask();
                        	  top.$.notice("操作成功！！！",3);	
                              api.data.window.Qm.refreshGrid();//刷新
                              api.close();
                           }
                       },
                       error:function () {
                    	   $.ligerDialog.error("出错了！请重试！!",3);	
                           $("body").unmask();
                       }
                   });
              }else{
            	  $.ligerDialog.error("出错了！请重试！!",3);	
               $("body").unmask();
              }
           });
      });
     }
	
	
	function save(){
		var userAge = $("#userAge").val();
		var reg = /^\d*$/;
		if(!reg.test(userAge)){
			$.ligerDialog.error("亲,只能输入,数字哟");
			return;
		}
	 	var obj=$("#form1").validate().form();
	if(obj){
		var formData = $("#form1").getValues();
	       $("body").mask("正在保存数据，请稍候……");
	       $.ajax({
	          url: "quality/staff/train/saveTrain.do",
	          type: "POST",
	          datatype: "json",
	          contentType: "application/json; charset=utf-8",
	          data:$.ligerui.toJSON(formData),
	          success: function (data, stats) {
	              $("body").unmask();
	              if (data["success"]) {
	            	  $("#id").val(data);
	                  top.$.dialog.notice({content:"保存成功！"});
	                  api.data.window.Qm.refreshGrid();
					  api.close();
	              }else{
	                  $.ligerDialog.error("提示：" + data.msg);
	                  api.data.window.Qm.refreshGrid();
	              }
	          },
	          error: function (data,stats) {
	              $("body").unmask();
	              $.ligerDialog.error("提示：" + data.msg);
	          }
	      });
		}
	}
	
	function idea(){
	    if(isChecked!="" && typeof(isChecked)!="undefined"){
        	$("#form1").transform("detail",function(){});
   	    	$("#form1").setValues("quality/staff/train/detail.do?id=${requestScope.serviceId}");
   	    	top.$.dialog({
				width: 900,
				height: 700,
				lock: true,
				parent: api,
				data: {window: window},
				title: "新增",
				content: 'url:app/qualitymanage/quality_staff_train_idea.jsp?pageStatus=add&areaFlag='+areaFlag
   	    	});
	    }
	}
	
	function print(){
	       var LODOP = getLodop(document.getElementById("LODOP_OB"), document.getElementById("LODOP_EM"));
	                    var strBodyStyle = "<style>" + $("#pstyle").html() + "</style>";
	                    LODOP.PRINT_INIT("打印领款单");
	                    LODOP.ADD_PRINT_HTM("120px", "100px", "770px", "520px", strBodyStyle + $("#form1").html());
	                    LODOP.SET_PRINT_PAGESIZE (1, 0, 0,"A4");
	                    //sLODOP.PRINT();
	                   LODOP.PREVIEW();
	    }
	
</script>


</head>
    <style>
h2{font-family:宋体;font-size:6mm; text-align:center;margin:10px 0 0 0;}
    </style>
   <style type="text/css" media="print" id="pstyle">
* {
    font-family:"宋体";
    font-size:15px;
    letter-spacing:normal;
    
}
table{ margin:0 auto;}
table td{ height:40px;}
.l-detail-table td, .l-detail-table {
    border-collapse: collapse;
    border: 1px solid black;
}
.l-detail-table {
    padding:5px;
    border:0px solid #CFE3F8;
    border-top:0px;
    border-left:0px;
    word-break:break-all;
    table-layout:fixed;
}
.check {
    width:720px;
}
.l-t-td-left{ text-align:center;}
.l-t-td-right{ padding-left:5px;}
.fybx2{   height:40px; line-height:20px; overflow: hidden;}

h2{font-family:宋体;font-size:6mm; text-align:center;margin:10px 0 0 0;}

</style>
<style>
.l-t-td-right1 {
    padding: 0;
    margin: 0;
}

.l-t-td-right1 .l-text {
    background-image: none;
}

.l-t-td-right1 .l-text-wrapper {
    width: 100%;
    height: 90px;
}

.l-textarea .l-text-wrapper {
    width: 100%;
    height: 100%;
}

.l-textarea-onerow {
    height: 30px;
}

.l-textarea-onerow div {
    padding: 0;
}

.l-t-td-right1 .l-text {
    border: none;
}

.l-t-td-right1 .l-text.change, .l-t-td-right1 .l-radio-group-wrapper.change
    {
    background: url("../images/x-input.png") repeat-x;
}

.l-t-td-right1 .l-text input {
    height: 90px;
    padding-top: 0;
    line-height: 24px;
}

.l-t-td-right1 .l-radio-group-wrapper {
    height: 90px;
    padding-left: 5px;
}

.l-t-td-right1 textarea {
    height: 100%;
}

.l-textarea-onerow textarea {
    height: 12px;
    padding: 6px 5px;
    width: 98%
}

.l-t-td-right1 label {
    height: 90px;
    line-height: 24px;
    display: inline-block;
}

.l-t-td-right1 div.input, .l-td-right div.input {
    border: none;
    padding-left: 5px;
}

.l-t-td-right1 .input-warp div {
    height: 100%;
    line-height: 28px;
}
</style>
<body>
	<form id="form1" action="quality/staff/train/saveTrain.do" getAction="quality/staff/train/detail.do?id=${param.id}">
	<input type="hidden" id="id" name="id" />
	<input id="userId" name="userId" type="hidden"/>
	<input id="userDepId" name="userDepId" type="hidden" />
	<input id="trainUnitId" name="trainUnitId" type="hidden" />
	<input id="applyId" name="applyId" type="hidden" value="<%=user.getId()%>"/>
	<input id="userDepLeaderId" name="userDepLeaderId" type="hidden" />
	<input id="status" name="status" type="hidden"/>
	<input id="registrantId" name="registrantId" type="hidden"/>
	<input id="registrantName" name="registrantName" type="hidden"/>
	<input id="registrantTime" name="registrantTime" type="hidden"/>
	<input id="applyDepId" name="applyDepId" type="hidden" value="<%=user.getDepartment().getId()%>"/>
	
	
	<h1 class="l-label" align="center" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">四川省特种设备检验研究院职工培训审批表</h1><div style="height:2px">&nbsp;</div>
	 <table id="ctmb1" class="check">
			 <tr>
                <td width="650px">&nbsp;</td>
                <td width="50px" align="center">编号：</td>
                <td style="width: 200px;" class="l-t-td-right"><input type="text" ltype="text" readOnly="true" id="trainChartNumber" name="trainChartNumber" /></td>
       			<td width="40px">&nbsp;</td>
                <td width="40px" align="center"></td>
                <td width="60" class="l-t-td-right"></td>
            </tr>
	</table>
	<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table" id="ctmb">
		<tr>
					<td class="l-t-td-left">姓名</td>
					<td class="l-t-td-right" colspan="2"><input id="userName" name="userName" tyep="text" ltype="text" onclick="choosePerson();"/></td>
					<td class="l-t-td-left">性别</td>
					<td class="l-t-td-right" colspan="2"><input type="radio" name="userGender" id="userGender" ltype="radioGroup"
							ligerui="{value:'0',data: [{ text:'男 ', id:'0' }, { text:'女', id:'1' }]}" /></td>
							
					<td class="l-t-td-left">年龄</td>
					<td class="l-t-td-right" colspan="2"><input id="userAge" name="userAge" tyep="text" ltype="text" validate="{required:false,maxlength:2}" /></td>
		</tr>
		<tr>
				<td class="l-t-td-left">所 在 部 门</td>
				<td class="l-t-td-right" colspan="3"><input id="userDep" name="userDep" tyep="text" ltype="text" readonly="readonly"/></td>
				<td class="l-t-td-left" colspan="2">职称（职务）</td>
				<td class="l-t-td-right" colspan="3"><input id="userDuty" name="userDuty" tyep="text" ltype="text"/></td>
		</tr>
		<tr>
				<td class="l-t-td-left">培训主办单位</td>
				<td class="l-t-td-right" colspan="8"><input id="trainUnit" name="trainUnit" tyep="text" ltype="text" validate="{required:true}" /></td>
		</tr>
		<tr>
                <td class="l-t-td-left">培训文件文号</td>
                <td class="l-t-td-right" colspan="8"><input id="tratnFileNum" name="tratnFileNum" tyep="text" ltype="text" validate="{required:true}"/></td>
        </tr>
		<tr>
				<td class="l-t-td-left">培 训 时 间</td>
				<td class="l-t-td-right" colspan="3"><input id="tratnTimeStart" name="tratnTimeStart" type="text" ltype="date" validate="{required:false}" 
	        					ligerui="{initValue:'',format:'yyyy-MM-dd'}" readonly="readonly" value="<%=nowTime%>" />
	        	</td>
				<td class="l-t-td-left" colspan="2">至</td>
				<td class="l-t-td-right" colspan="3"><input id="tratnTimeEnd" name="tratnTimeEnd" type="text" ltype="date" validate="{required:false}" 
	        					ligerui="{initValue:'',format:'yyyy-MM-dd'}" readonly="readonly" value="<%=nowTime%>" />
	        	</td>
		</tr>
		<tr>
				<td class="l-t-td-left">培 训 地 点</td>
				<td class="l-t-td-right" colspan="8"><input id="tratnSite" name="tratnSite" tyep="text" ltype="text" validate="{required:true}"/></td>
		</tr>
		<tr>
                <td class="l-t-td-left">培 训 类 别</td>
                <td class="l-t-td-right" colspan="8"><input type="radio" name="tratnType" id="tratnType" ltype="radioGroup"
                        ligerui="{value:'01',data: [ { text:'业务学习培训 ', id:'01' }, { text:'取（换）检验资格证培训', id:'02' },
                        { text:'其它培训 ', id:'03' }]}"/>  
                </td>
        </tr>
		<tr>
					<td class="l-t-td-left">预计培训费用（列</br>出明细）及出行</br>方式</td>
					<td class="l-t-td-right1" colspan="8"> 
	      		  		<textarea rows="6" class="l-textarea" name="tratnCostDetail" type="text" ltype="text"  validate="{required:true,maxlength:2000}"></textarea>
	        		</td>
		</tr>
		<tr>
					<td rowspan="2" style="height:120px" class="l-t-td-left">申请理由</td>
		        	<td colspan="8" style="height:94px" class="l-t-td-right1"><textarea rows="6" class="l-textarea" name="applyReason" id="applyReason" type="text" ltype="text"   validate="{required:true,maxlength:2000}"></textarea> </td>
		</tr>
		<tr>
					<td class="l-t-td-left">申请人</td>
		        	<td class="l-t-td-right" colspan="3"><input id="applyName" name="applyName" tyep="text" ltype="text" validate="{required:true}" value="<%=user.getName()%>"/></td>
		        	<td colspan="1" class="l-t-td-left">申请时间</td>
		        	<td class="l-t-td-right" colspan="3"><input id="applyTime" name="applyTime" tyep="text" ltype="date" validate="{required:false}" 
	        					ligerui="{initValue:'',format:'yyyy-MM-dd'}" readonly="readonly" value="<%=nowTime%>"/></td>
					<input id="applyDep" name="applyDep" type="hidden" value="<%=user.getDepartment().getOrgName() %>"/>
		</tr>
	</table>
	<table cellpadding="3" cellspacing="0" width=""
                class="l-detail-table" id="ctmbCheck"  style="border-top:0px;">
		<tr>
					<td rowspan="2" style="height:120px" class="l-t-td-left">所在部门意见</td>
		        	<td colspan="8" style="height:94px" class="l-t-td-right1"><textarea rows="6" class="l-textarea" name="userDepIdea" id="userDepIdea" type="text" ltype="text" readonly="readonly" validate="{required:false,maxlength:2000}" ></textarea> </td>
		</tr>
		<tr>
					<td class="l-t-td-left">负责人</td>
		        	<td class="l-t-td-right" colspan="3"><input id="userDepLeader" name="userDepLeader" tyep="text" ltype="text" readonly="readonly"/></td>
		        	<td colspan="1" class="l-t-td-left">意见时间</td>
		        	<td class="l-t-td-right" colspan="3"><input id="userDepIdeaTime" name="userDepIdeaTime" tyep="text" ltype="text"validate="{required:false}" readonly="readonly" /></td>
		</tr>
		<tr>
					<td rowspan="2" style="height:120px" class="l-t-td-left">科研技术管理部门</br>意见（业务学习培</br>训和检验资格培训）</td>
		        	<td colspan="8" style="height:94px" class="l-t-td-right1"><textarea rows="6" class="l-textarea" name="skillManageDepIdea" id="skillManageDepIdea" type="text" ltype="text" readonly="readonly" validate="{required:false,maxlength:2000}"></textarea> </td>
		</tr>
		<tr>
					<td class="l-t-td-left">负责人</td>
		        	<td class="l-t-td-right" colspan="3"><input id="skillManageLeader" name="skillManageLeader" tyep="text" ltype="text" readonly="readonly" /></td>
		        	<td colspan="1" class="l-t-td-left">意见时间</td>
		        	<td class="l-t-td-right" colspan="3"><input id="skillManageIdeaTime" name="skillManageIdeaTime" tyep="text" ltype="text"validate="{required:false}" readonly="readonly"/></td>
		</tr>
		<tr>
						<td rowspan="2" style="height:120px" class="l-t-td-left">所在部门分管院领</br>导意见</td>
			        	<td colspan="8" style="height:94px" class="l-t-td-right1"><textarea rows="6" class="l-textarea" name="branchedPassageLeaderIdea" id="branchedPassageLeaderIdea" type="text" ltype="text" validate="{required:false,maxlength:2000}" readonly="readonly"></textarea> </td>
		</tr>
		<tr>
			        	<td colspan="2" class="l-t-td-left">意见时间</td>
			        	<td colspan="6" class="l-t-td-right"><input id="branchedPassageIdeaTime" name="branchedPassageIdeaTime" tyep="text" ltype="text"validate="{required:false}" readonly="readonly"/></td>
		</tr>
		<tr>
						<td rowspan="2" style="height:120px" class="l-t-td-left">培训工作分管院领</br>导意见</td>
			        	<td colspan="8" style="height:94px" class="l-t-td-right1"><textarea rows="6" class="l-textarea" name="trainFgLeaderIdea" id="trainFgLeaderIdea" type="text" ltype="text" readonly="readonly" validate="{required:false,maxlength:2000}"></textarea> </td>
		</tr>
		<tr>
			        	<td colspan="2" class="l-t-td-left">意见时间</td>
			        	<td colspan="6" class="l-t-td-right"><input id="trainFgIdeaTime" name="trainFgIdeaTime" tyep="text" ltype="text"validate="{required:false}" readonly="readonly"/></td>
		</tr>
		<tr>
					<td rowspan="2" style="height:120px" class="l-t-td-left">院长审批意见</td>
					<td colspan="8" style="height:94px" class="l-t-td-right1"><textarea rows="6" class="l-textarea" name="deanExamineIdea" id="deanExamineIdea" type="text" ltype="text" readonly="readonly" validate="{required:false,maxlength:2000}"></textarea></td>
		</tr>
		<tr>
					<td colspan="2" class="l-t-td-left">意见时间</td>
					<td colspan="6" class="l-t-td-right"><input id="deanExamineIdeaTime" name="deanExamineIdeaTime" tyep="text" ltype="text" validate="{required:false}" readonly="readonly"/></td>
		</tr>		
	</table>
	<div>
			<span style="color:red"></br>注：本表一式二份（可在签字完成后复印），科研技术管理部（或办公室）和院财务室各一份。</span>
	</div>
	
	</form>
</body>
</html>