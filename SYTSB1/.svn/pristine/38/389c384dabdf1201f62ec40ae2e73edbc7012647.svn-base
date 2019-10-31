<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %> 
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!--获取当前登录人  -->
<%
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User uu = (User)curUser.getSysUser();
	com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
	String userId=e.getId();//员工ID
	String uId = SecurityUtil.getSecurityUser().getId();//用户ID
%>
<head pageStatus="${param.pageStatus}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
	<link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
	<script type="text/javascript" src="pub/bpm/js/util.js"></script>
	<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
    <script type="text/javascript">
    var pageStatus = "${param.pageStatus}";
    var tbar="";
    var isChecked="${param.isChecked}";
    var serviceId = "${requestScope.serviceId}";//提交数据的id
    var activityId = "${requestScope.activityId}";//流程id
    var processId = "${requestScope.processId}";//过程
    var areaFlag;//改变状态
    <bpm:ifPer function="INSPECTION_DIRECTOR_CHECK" activityId = "${requestScope.activityId}">areaFlag="2";</bpm:ifPer>//部门主任审核
    <bpm:ifPer function="INSPECTION_QUALITY_CHECK" activityId = "${requestScope.activityId}">areaFlag="3";</bpm:ifPer>//院质量部审核
    <bpm:ifPer function="INSPECTION_SCIENTIFIC_CHECK" activityId = "${requestScope.activityId}">areaFlag="4";</bpm:ifPer>//院科管部审核
    <bpm:ifPer function="INSPECTION_HRD_CHECK" activityId = "${requestScope.activityId}">areaFlag="5";</bpm:ifPer>//人事审核
    $(function() {
    	if(isChecked!="" && typeof(isChecked)!="undefined"){
    		$("#form1").setValues("inspection/declare/detail.do?id=${requestScope.serviceId}");
    		getBusinessAttachments("${requestScope.serviceId}",function(files){
    			addAttachFile(files);
            });
    		tbar=[{ text: '通过', id: 'shtg', icon: 'submit', click: shtg},
    			{ text: '不通过', id: 'shbtg', icon: 'del', click: shbtg},
                  { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
    		if(areaFlag==2){
   	    		document.getElementById("directorOpinion").removeAttribute("readOnly");
   	    	 }else if(areaFlag==3){ 
   	    		document.getElementById("qualityDirectorOpinion").removeAttribute("readOnly");
   	    	 }else if(areaFlag==4){ 
   	    		document.getElementById("scientificDirectorOpinion").removeAttribute("readOnly");
   	    	 }else if (areaFlag == 5) {
             	tbar = [{text: '退回', id: 'turnback', icon: 'back', click: turnback},
            		{text: '通过', id: 'shtg', icon: 'submit', click: shtg},
                	{text: '不通过', id: 'shbtg', icon: 'del', click: shbtg},
                    {
                        text: '关闭', id: 'close', icon: 'cancel', click: function () {
                            api.close();
                        }
                    }];
            }
    	}else if(pageStatus=="detail"){
	 		tbar=[{text: "关闭", icon: "cancel", click: function(){api.close();}}];
	 	}else{
	 		tbar=[{text: "保存", icon: "save", click: function(){
      				//表单验证
			    	if ($("#form1").validate().form()) {
			    		$("#form1").submit();
			    	}else{
			    		$.ligerDialog.error('提示：' + '请将信息填写完整后保存！');
			    	}
      			}},
				  {text: "关闭", icon: "cancel", click: function(){api.close();}}];
	 		document.getElementById("reward").removeAttribute("readOnly");
	 	}
    	$("#form1").initForm({
			success: function (response) {
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	            	api.data.window.Qm.refreshGrid();
	            	api.close();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (response){
				if(response.data.id!=null && response.data.id!="undefined" && response.data.id!=""){
            		getBusinessAttachments(response.data.id,function(files){
            			addAttachFile(files);
		            });
            	}
			},
			showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
    })
    //退回
    function turnback(){
    	$.ligerDialog.confirm('是否退回？', function (yes) {
            if (!yes) {
                return false;
            }
            $("body").mask("正在处理，请稍后！");
            $.getJSON("inspection/declare/turnback.do?activityId="+activityId+"&id="+serviceId,
                    function(resp){
                        if(resp){
                            top.$.notice("操作成功！",3);
                            api.data.window.Qm.refreshGrid();
                            api.close();
                        }
                    })
    	})
    }
    //审核通过
	function shtg(){
     	var formData=$("#form1").getValues();
     	var inspectionDeclare=$.ligerui.toJSON(formData)
        $.ligerDialog.confirm('是否审核通过？', function (yes){
	        if(!yes){return false;}
	        $("body").mask("提交中...");
	        getServiceFlowConfig("TJY2_INSPECTION_DECLARE","",function(result,data){
	        	if(result){
	            	top.$.ajax({
	                	url: "inspection/declare/shtg.do?id="+serviceId+
	                    	"&typeCode=TJY2_INSPECTION_DECLARE"+"&activityId="+activityId+"&areaFlag="+areaFlag,
	                    type: "POST",
	                    dataType:'json',
	          	 		data: {"inspectionDeclare":inspectionDeclare},
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
  	//审核不通过
	function shbtg(){
       	var formData=$("#form1").getValues();
       	var inspectionDeclare=$.ligerui.toJSON(formData)
    	 $.ligerDialog.confirm('是否要不通过？', function (yes){
	         if(!yes){return false;}
	    	 $("body").mask("正在处理，请稍后！");
	    	 getServiceFlowConfig("TJY2_INSPECTION_DECLARE","",function(result,data){
	    		 if(result){
	                 top.$.ajax({
	                     url: "inspection/declare/shbtg.do?id="+serviceId+
	                    		 "&typeCode=TJY2_INSPECTION_DECLARE"+"&activityId="+activityId+"&areaFlag="+areaFlag+"&processId="+processId,
	                     type: "POST",
	                     dataType:'json',
	                     data:{"inspectionDeclare":inspectionDeclare},
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
    </script>
    <style>
		.l-t-td-left{
			width: 10%;
		}
    </style>
</head>
<body>
	<form id="form1" action="inspection/declare/save.do?pageStatus=${param.pageStatus}" getAction="inspection/declare/detail.do?id=${param.id}">
    	<h1 style="padding:5mm 0 2mm 0;font-family:宋体;font-size:7mm;text-align:center;">检验部门个人岗位级别申报表</h1></br>
    	<input type="hidden" id="id" name="id"/>
        <input type="hidden" id="empId" name="empId" value="<%=userId %>"/>
        <input type="hidden" id="workDepartment" name="workDepartment" value="<%=e.getOrg().getId() %>"/>
        <input type="hidden" id="directorId" name="directorId"/>
        <input type="hidden" id="directorName" name="directorName"/>
        <input type="hidden" id="directorDate" name="directorDate"/>
        <input type="hidden" id="qualityDirectorId" name="qualityDirectorId"/>
        <input type="hidden" id="qualityDirectorName" name="qualityDirectorName"/>
        <input type="hidden" id="qualityDirectorDate" name="qualityDirectorDate"/>
        <input type="hidden" id="scientificDirectorId" name="scientificDirectorId"/>
        <input type="hidden" id="scientificDirectorName" name="scientificDirectorName"/>
        <input type="hidden" id="scientificDirectorDate" name="scientificDirectorDate"/>
        <input type="hidden" id="status" name="status" value="WTJ"/>
        <input type="hidden" id="createId" name="createId"/>
        <input type="hidden" id="createBy" name="createBy"/>
        <input type="hidden" id="createDate" name="createDate"/>
        <input type="hidden" id="lastModifyId" name="lastModifyId"/>
        <input type="hidden" id="lastModifyBy" name="lastModifyBy"/>
        <input type="hidden" id="lastModifyDate" name="lastModifyDate"/>
       
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
       		<tr> 
		        <td class="l-t-td-left"> <br />姓名<br /><br /></td>
		        <td class="l-t-td-right">
		        	<input id="empName" name="empName" type="text" ltype='text' value="<%=e.getName() %>"/> 
		        </td>
		        <td class="l-t-td-left"> <br />性别<br /><br /></td>
		        <td class="l-t-td-right">
		        	<input id="empSex" name="empSex" type="text" ltype='select'
		        	ligerui="{value:'<%=e.getGender() %>',data: [ { text:'女', id:'0' }, { text:'男', id:'1' }] }"/> 
		        </td>
		        <td class="l-t-td-left"> 出生<br />年月</td>
		        <td class="l-t-td-right"> 
		        	<input id="empBirthDate" name="empBirthDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}"/> 
		        </td>
		        <td class="l-t-td-left"> <br />学历<br /><br /></td>
		        <td class="l-t-td-right">
		        	 <input id="empEducation" name="empEducation" type="text" ltype='text'/> 
		        </td>
       		</tr>
       		<tr> 
		        <td class="l-t-td-left"> 进院<br />时间</td>
		        <td class="l-t-td-right">
		        	 <input id="intoWorkDate" name="intoWorkDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}"/> 
		        </td>
		        <td class="l-t-td-left"> 工作<br />岗位</td>
		        <td class="l-t-td-right">
		        	 <input id="position" name="position" type="text" ltype='text'/>
		        </td>
		        <td class="l-t-td-left"> 毕业<br />院校</td>
		        <td class="l-t-td-right" colspan="3">
		        	 <input id="graduateSchool" name="graduateSchool" type="text" ltype='text'/>
		        </td>
       		</tr>
       		<tr> 
       			<td class="l-t-td-left"> <br />职称<br /><br /></td>
		        <td class="l-t-td-right">
		        	<input id="empTitle" name="empTitle" type="text" ltype='text'/> 
		        </td>
       			<td class="l-t-td-left"> <br />工作<br />部门<br /><br /></td>
		        <td class="l-t-td-right">
		        	<input id="workDepartmentName" name="workDepartmentName" type="text" ltype='text' value="<%=e.getOrg().getOrgName() %>"/> 
		        </td>
		        <td class="l-t-td-left"> 申报<br />岗位<br />级别<br /></td>
		        <td class="l-t-td-right">
		        	<%--<input id="postLevel" name="postLevel" type="text" ltype='text'/>--%>
					<u:combo name="postLevel" code="TJY2_RL_POSITION_LEVEL" validate="true"/>
		        </td>
		        <td class="l-t-td-left"> 从事检<br />验岗位<br />时间<br /></td>
		        <td class="l-t-td-right">
		        	<input id="intoInspectionYears" name="intoInspectionYears" type="text" ltype='text'/> 
		        </td>
       		</tr>
       		<tr> 
       			<td class="l-t-td-left"> <br /><br />持证<br />情况<br /><br /><br /></td>
		        <td colspan="7" style="padding-top: 0px;padding-left: 0px;padding-right: 0px;padding-bottom: 0px;">
		        	<table border="0" cellspacing="0" width="" class="l-detail-table" style="height: 110px;">
		        		<tr>
		        			<td class="l-t-td-left" style="width: 27.7%;">持主检验员（检验师）:</td>
		        			<td class="l-t-td-right" style="width: 22.3%;"><input id="certificate1"   name="certificate1"  type="text" ltype="text"  /></td>
		        			<td class="l-t-td-left" style="width: 27.7%;">初次取证时间:</td>
		        			<td class="l-t-td-right" style="width: 22.3%;"><input id="certificate1Date" name="certificate1Date"   type="text" ltype="date"  /></td>
        				</tr>
		        		<tr>
		        			<td class="l-t-td-left">持其他检验员（检验师）:</td>
		        			<td class="l-t-td-right"><input id="certificate2"   name="certificate2"  type="text" ltype="text"  /></td>
		        			<td class="l-t-td-left">取证时间:</td>
		        			<td class="l-t-td-right"><input id="certificate2Date" name="certificate2Date"   type="text" ltype="date"  /></td>
		        		</tr>
		        		<tr>
		        			<td class="l-t-td-left">持其他检验员（检验师）:</td>
		        			<td class="l-t-td-right"><input id="certificate3"   name="certificate3"  type="text" ltype="text"  /></td>
		        			<td class="l-t-td-left">取证时间:</td>
		        			<td class="l-t-td-right"><input id="certificate3Date" name="certificate3Date"   type="text" ltype="date"  /></td>
		        		</tr>
		        	</table>
		        </td>
       		</tr>
       		<tr> 
		        <td class="l-t-td-left"> <br /><br /><br /><br />年<br />度<br />考<br />核<br />获<br />得<br />院<br />表<br />彰<br />情<br />况<br /><br /><br /><br /><br /></td>
		        <td colspan="7">
		        	<textarea id="reward" name="reward" rows="16" cols="100" class="l-textarea" readonly="readonly" ></textarea> 
		        </td>
		    </tr>
		    <tr>
		        <td class="l-t-td-left"> <br />部审<br />门核<br />主意<br />任见<br /><br /></td>
		        <td colspan="7">
		        	<textarea id="directorOpinion" name="directorOpinion" rows="6" cols="100" class="l-textarea" readonly="readonly" ></textarea>  
		        </td>
      		</tr>
       		<tr>
		        <td class="l-t-td-left"> <br />院审<br />质核<br />量意<br />部见<br /><br /></td>
		        <td colspan="7"> 
		        	<textarea id="qualityDirectorOpinion" name="qualityDirectorOpinion" rows="6" cols="100" class="l-textarea" readonly="readonly" ></textarea> 
		        </td>
       		</tr>
       		<tr>
		        <td class="l-t-td-left"> <br />院审<br />科核<br />管意<br />部见<br /><br /></td>
		        <td colspan="7"> 
		        	<textarea id="scientificDirectorOpinion" name="scientificDirectorOpinion" rows="6" cols="100" class="l-textarea" readonly="readonly" ></textarea> 
		        </td>
       		</tr>
			<tr height="80px">
				<td class="l-t-td-left">附件</td>
				<td class="l-t-td-right" colspan="7">
					<input type="hidden" name="uploadFiles" id="uploadFiles">
					<input name="documentName" type="hidden" id="documentName"/>
					<input name="documentDoc" type="hidden" id="documentDoc"/>
					<c:if test='${param.pageStatus!="detail" }'>
						<p id="procufilesDIV">
							<a class="l-button" id="procufilesBtn">
								<span class="l-button-main"><span class="l-button-text">选择文件</span></span>
							</a>
						</p>
					</c:if>
					<div class="l-upload-ok-list">
						<ul id="procufiles3"></ul>
					</div>
				</td>
			</tr>
      </table>
    </form> 
</body>
<script type="text/javascript">
    $(function () {
        var receiptUploadConfig = {
            fileSize: "50mb",	//文件大小限制
            businessId: "",	//业务ID
            buttonId: "procufilesBtn",		//上传按钮ID
            container: "procufilesDIV",	//上传控件容器ID
            title: "文件",	//文件选择框提示
            extName: "doc,docx",	//文件扩展名限制
            workItem: "",	//页面多点上传文件标识符号
            fileNum: 1,	//限制上传文件数目
            callback: function (file) {	//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
                addAttachFile(file);
            }
        };
        var receiptUploader = new KHFileUploader(receiptUploadConfig);
    });

    //添加合同附件
    function addAttachFile(files) {
    	var isEdit = true;
        if ("${param.pageStatus}" == "detail") {
            $("#procufilesBtn").hide();
            isEdit = false;
        }
        if (files) {
            $.each(files, function (i) {
            	createFileView(files[i].id,files[i].name,isEdit,"procufiles3",true,function(fid){});
            });
            getUploadFile();
        }
    }

    // 将上传的合同附件id写入隐藏框中
    function getUploadFile() {
        var attachId = "";
        var i = 0;
        $("#procufiles3 li").each(function () {
            attachId += (i == 0 ? "" : ",") + this.id;
            i = i + 1;
        });
        if (attachId != "") {
            attachId = attachId.substring(0, attachId.length);
        }
        $("#uploadFiles").val(attachId);
    }
</script>
</html>
