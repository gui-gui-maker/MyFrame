		<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %> 
<%@page import="java.text.SimpleDateFormat"%>
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
	<link type="text/css" rel="stylesheet" href="app/qualitymanage/css/form_detail.css" />
	<script type="text/javascript" src="pub/bpm/js/util.js"></script>
    <script type="text/javascript">
    var tbar="";
	var ab=pageStatus="${param.pageStatus}";
    var isChecked="${param.isChecked}";
    var serviceId = "${requestScope.serviceId}";//提交数据的id
	var activityId = "${requestScope.activityId}";//流程id
	var processId = "${requestScope.processId}";//退回id

	var status1="${requestScope.status}";
	var areaFlag;//改变状态
 	<bpm:ifPer function="TJY2_SCIENTIFIC_XGSQ_SH" activityId = "${requestScope.activityId}">areaFlag="1";</bpm:ifPer>//申请单审核
 	<bpm:ifPer function="TJY2_SCIENTIFIC_XGSQ_PZ" activityId = "${requestScope.activityId}">areaFlag="4";</bpm:ifPer>//批准

 	
 	$(function () {
    	if(isChecked!="" && typeof(isChecked)!="undefined"){
        	$("#xgsq").transform("detail",function(){});
   	    	$("#xgsq").setValues("tjy2ScientificFilesUpdateAction/detail.do?id=${requestScope.serviceId}");
   	    	$("#xgsq1").setValues("tjy2ScientificFilesUpdateAction/detail.do?id=${requestScope.serviceId}");
   	    	tbar=[{ text: '审核', id: 'submit', icon: 'submit', click: sh},
                   { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
        } else {
            tbar=[{ text: '保存', id: 'up', icon: 'save', click: directChange},
                { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
            
        }
    	 if ("${param.pageStatus}"=="detail")
    	        tbar=[{ text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
    	$("#form1").initForm({
            showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
    });
 	
 	function sh() {
		top.$.dialog({
			width: 600,
			height: 350,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "审核",		
			content: 'url:app/fwxm/scientific/bzgl/update_apply_yijian.jsp?pageStatus=add&serviceId='
					+serviceId+'&activityId='+activityId+'&processId='+processId
		});
	}
//     window.onload=function(){
//     	if(ab=="add" || ab=="detail"){
//     		var cdd1= document.getElementById('cdd1');
//     		var cdd= document.getElementById('cdd');
//     		cdd1.style.display ="none";
//     		cdd.style.display ="none";
//     		var cdd2= document.getElementById('cdd2');
//     		cdd2.style.display ="none";
//     	}	
//     }
    
    
    function submitSh(){
    	var serviceId = "${requestScope.serviceId}";//提交数据的id
    	var activityId = "${requestScope.activityId}";//流程id
    	/*alert(serviceId+"=="+activityId);*/
    	
        $.ligerDialog.confirm('是否提交审核？', function (yes){
        if(!yes){return false;}
         $("body").mask("提交中...");
         getServiceFlowConfig("TJY2_SCIENTIFIC_XGSQ","",function(result,data){
                if(result){
                     top.$.ajax({
                         url: "tjy2ScientificFilesUpdateAction/zltj.do?id="+serviceId+
                        		 "&typeCode=TJY2_SCIENTIFIC_XGSQ&status="+"&activityId="+activityId+"&areaFlag="+areaFlag,
                         type: "GET",
                         dataType:'json',
                         async: false,
                         success:function (data) {
                             if (data) {
                                $.ligerDialog.alert("审核成功！！！");
                                //api.clsoe();
                                api.data.window.Qm.refreshGrid();//刷新
                                $("body").unmask();
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
        	var obj=$("#form1").validate().form();
//         	top.$.ajax({
//                 url: "quality/updateFile1/save1.do",
//                 type: "POST",
//                 dataType:'json',
//                 async: false,
//                 success:function (data) {
//                 	 if(data.success){
//                 		 top.$.notice('修改成功！',3);												
//                      }else{
//                     	 $.ligerDialog.error(data.msg,3);	
//                      }
//                 },
//                 error:function () {
//                 	$.ligerDialog.error('出错了！请重试！!',3);											
//                 }
//             });
    	 if(obj){
    		 $("#form1").submit();
    	 }else{
    		 return;
    	}}   
        function selectorg(){
        	zlfile("",0,"","",function(datas){
        		if(!datas.code)return;
        		var codeArr = datas.code.split(",");
        		var nameArr = datas.name.split(",");
        		var readers = [];
        		var existReaders = $("#formObj").data("readers")||[];
        		for(var i in codeArr){
        			var exist = false;
        			for(var j in existReaders){
        				if(existReaders[j].stakeholderId == codeArr[i] && existReaders[j].valid == true)
        					exist=true;
        			}
        			if(exist) continue;
        			readers.push({
        				types : "reader",
        				name: nameArr[i],
        				id: codeArr[i]
        			});
        		}
        		addReader(readers,true);
        	});
        }
        
        function addReader(newReaders,isNew){
        	var readers = $("#formObj").data("readers");
        	$.each(newReaders,function(){
        		$("#reader_td").prepend('<span class="label label-read" id="' + (isNew?this.id:this.id) + '"><span class="text">' + this.name + 
        			'</span><span class="del btn btn-lm" onclick="deleteReader(\'' + (isNew?this.id:this.id) + '\',' + isNew + ')">&nbsp;</span></span>');
        	});
        	if(readers)
        		readers = readers.concat(newReaders);
        	else
        		readers = newReaders;
        	$("#formObj").data("readers",readers);
        }

        function deleteReader(id,isNew){
        	var readers = $("#formObj").data("readers");
        	var newReaders = [];
        	$.each(readers,function(){
        		if(!isNew){
        			if(this.id == id)
        				this.valid = false;
        			newReaders.push(this);
        		}
        	});
        	$("#formObj").data("readers",newReaders);
        	$("#"+id).remove();
        }
        
        function zlfile(type, isCheckBox, code, name, callback) {
        	var title = "文件选择";
        	var url = "url:app/qualitymanage/zlfile_select.jsp?checkbox="+ 
        			isCheckBox+ "&fieldName=" + name + "&fieldId=" + code;
        	var width = 760	;
        	top.$.dialog({
        		width : width,
        		height : 500,
        		lock : true,
        		parent : api,
        		id : "zlfile",
        		title : title,
        		content : url,
        		cancel: true,
        		ok : function() {
        			var datas = this.iframe.contentWindow.getSelectResult();
        			if(datas){
        					$("#fileId").val(datas.code);
        					alert($("#fileId").val(datas.code));
        					$("#fileName").val(datas.name);
        				if(callback) 
        					callback(datas);
        				return true;
        			}
        			else return false;
        		}
        	});
        }
        function choosefile(){
            top.$.dialog({
                width: 650,
                height: 450,
                lock: true,
                parent: api,
                title: "选择文件",
                content: 'url:app/fwxm/scientific/bzgl/file_list.jsp',
                cancel: true,
                ok: function(){
                    var p = this.iframe.contentWindow.getSelectedPerson();
                    if(!p){
                        top.$.notice("请选择文件！", 3, "k/kui/images/icons/dialog/face-sad.png");
                        return false;
                    }
                    //alert(p.id);
                    $("#fileId").val(p.fileid);
                    $("#fileName").val(p.name);
                    $("#qualityXybzFileId").val(p.id);
                }
            });
        }
    </script>
</head>
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
    height: 124px;
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
    height: 124px;
    padding-top: 0;
    line-height: 24px;
}

.l-t-td-right1 .l-radio-group-wrapper {
    height: 124px;
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
    height: 124px;
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
<body >
<form id="form1" action="tjy2ScientificFilesUpdateAction/save1.do" getAction="tjy2ScientificFilesUpdateAction/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id">
    <input type="hidden" id="status" name="status">
    <input type="hidden" id="qualityXybzFileId" name="qualityXybzFileId"/>
    
    <h1 id="xgsq2" align="center" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">文&nbsp;件&nbsp;修&nbsp;改&nbsp;申&nbsp;请&nbsp;表 </h1></br>
    <table id="xgsq1" class="check">
			 <tr>
                    <td width="650px">&nbsp;</td>
                    <td >编号</td>
                    <td class="l-t-td-right"><input ltype='text' readOnly="true" name="identifier" type="text"/></td>
            </tr>
	</table>
    <table id="xgsq" border="1" cellpadding="3" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">文件名称</td>
<!--             <td class="l-t-td-right"><input name="fileName" type="text" ltype="text"onclick="selectorg()" -->
<!--disabled 		ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg()}}]}"/></td> -->
         	<td class="l-t-td-right" ><input  validate="{required:true}" ltype="text"  name="fileName" id="fileName" 
         	type="text" ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:choosefile}]}" onclick="choosefile();" title="点击选择文件"/></td>
         	
         	<td class="l-t-td-left">文件编号</td>
         	<td class="l-t-td-right"><input ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:choosefile}]}" onclick="choosefile();" title="点击选择文件" ligerUi="{readonly:true}" id="fileId" name="fileId" type="text" ltype="text" validate="{required:true}" /></td>
        </tr>
<!--         <tr> -->
<!--             <td class="l-t-td-left">需修改的内容</td>style="height:100px" -->
<!--             <td colspan="3" class="l-t-td-right1"><textarea rows="6" class="l-textarea" name="needsUpdate" ltype="text" validate="{required:true,maxlength:2000}"></textarea> -->
<!--             </td> -->
<!--         </tr> -->
        <tr>
            <td class="l-t-td-left">需修改的内容</td>
            <td colspan="3" class="l-t-td-right1"><textarea rows="6" name="updateOcntent" ltype="text" validate="{required:true,maxlength:2000}"></textarea></td>
        </tr>
        <tr>
            <td class="l-t-td-left">修改理由</td>
            <td colspan="3" class="l-t-td-right1"><textarea rows="6" name="updateReasons" ltype="text" validate="{required:true,maxlength:2000}"></textarea></td>
        </tr>
<!--         <tr> -->
<!--             <td class="l-t-td-left">执行日期</td> -->
            <%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
				String nowTime=""; 
				nowTime = dateFormat.format(new Date());%>
<%--         	<td colspan="3" class="l-t-td-right"><input name="materialDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" value="<%=nowTime%>" /></td> --%>
<!--         </tr> -->
          <tr>
            <td class="l-t-td-left">编制人</td>
            <td class="l-t-td-right"><input name="current_man" type="text" ltype="text" value="<sec:authentication property="principal.name"/>"  ligerUi="{disabled:true}"/></td>
         	<td class="l-t-td-left">日期</td>
        	<td class="l-t-td-right"><input name="current_mandate" type="text" ltype='date' ligerui="{disabled:true,format:'yyyy-MM-dd'}"  value="<%=nowTime%>" /></td>
    	 </tr>
         <tr>
            <td class="l-t-td-left">审核人</td>
            <td class="l-t-td-right"><input name="sh_man" type="text" ltype="text" /></td>
         	<td class="l-t-td-left">日期</td>
        	<td class="l-t-td-right"><input name="sh_mandate" type="text" ltype='date' ligerui="{disabled:true,format:'yyyy-MM-dd'}" /></td>
        </tr>
         <tr>
            <td class="l-t-td-left">批准人</td>
            <td class="l-t-td-right"><input name="pz_man" type="text" ltype="text" /></td>
         	<td class="l-t-td-left">日期</td>
        	<td class="l-t-td-right"><input name="pz_mandate" type="text" ltype='date' ligerui="{disabled:true,format:'yyyy-MM-dd'}" /></td>
        </tr> 
    </table>
    
    <!-- 
    <h1 id="cdd2" align="center" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">方案/报告/技术/质量管理文件审批传递单 </h1></br>
    <table id="cdd1" class="check">
			 <tr>
                    <td width="650px">&nbsp;</td>
                    <td align="center">编号:</td>
                    <td class="l-t-td-right"><input ltype='text' readOnly="true" name="identifierC" type="text"/></td>
            </tr>
	</table>
	<table id="cdd" border="1" cellpadding="3" class="l-detail-table">
		<tr>
            <td class="l-t-td-left">项目名称：</td>
            <td colspan="3" class="l-t-td-right"><input id="projectsName" name="projectsName" type="text" ltype="text" /></td>
         	<td class="l-t-td-left">项目编号：</td>
         	<td class="l-t-td-right"><input name="projectNumber" type="text" ltype="number" validate="{required:true,maxlength:32}" /></td>
        </tr>
		<tr>
            <td class="l-t-td-left">负责部门：</td>
            <td class="l-t-td-right"><input name="department" type="text" ltype="text" /></td>
            <td class="l-t-td-left">项目负责人：</td>
            <td class="l-t-td-right"><input name="projectLeader" type="text" ltype="text" /></td>
         	<td class="l-t-td-left">文件编号：</td>
         	<td class="l-t-td-right"><input name="fileIdCdd" type="text" ltype="number" validate="{required:true,maxlength:32}" /></td>
        </tr>
        <tr>
        	<td class="l-t-td-left">文件性质：</td>
			<td class="l-t-td-right" colspan="5">
				<input type="radio" name="fileProperties"  id="fileProperties" ltype="radioGroup"
				ligerui="{data: [ { text:'方案', id:'1' }, { text:'报告', id:'2' }, { text:'技术文件', id:'3' }, 
				{ text:'质量管理文件', id:'4' }, { text:'其它', id:'5' } ] }"/>		
			</td>	
		</tr>
		<tr>								
			<td class="l-t-td-left">检验案例：</td>
			<td class="l-t-td-right" colspan="5">
				<input type="radio" name="testCase"  id="testCase" ltype="radioGroup"
				ligerui="{data: [ { text:', id:'1' }, { text:'无', id:'2' } ] }"/>
			</td>								
		</tr>
	</table>-->
	
</form>
</body>
</html>
