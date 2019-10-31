<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %> 
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
    <%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	String nowTime=""; 
	nowTime = dateFormat.format(new Date());%>
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
	 <script type="text/javascript" src="pub/bpm/js/util.js"></script>
	 <link type="text/css" rel="stylesheet" href="app/office/css/form_detail.css" />
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
	 <script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
     <script type="text/javascript">
    var tbar="";
	var js="${param.js}";
	var tbar="";
    $(function () {
    	var receiptUploadConfig = {
    			fileSize : "10mb",	//文件大小限制
    			businessId : "",	//业务ID
    			buttonId : "procufilesBtn",		//上传按钮ID
    			container : "procufilesDIV",	//上传控件容器ID
    			title : "图片",	//文件选择框提示
    			extName : "jpg,gif,jpeg,png,bmp,doc,docx,xls,xlsx,pdf",	//文件扩展名限制
    			workItem : "",	//页面多点上传文件标识符号
    			fileNum : 1,	//限制上传文件数目
    			callback : function(files){	//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
    				addAttachFile(files);
    			}
    	};
    	if(js=="a"){
    		
//     		document.getElementById("reclaimName").readOnly=false; 
//     		document.getElementById("reclaimTime").readOnly=false; 
    		document.getElementById("reclaimStatus").readOnly=false; 

    		tbar=[{ text: '接收', id: 'up', icon: 'save', click: saves},
    	            { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
		}else{
    		document.getElementById("fileName").readOnly=false; 
    		document.getElementById("extendMark").readOnly=false; 

			tbar=[{ text: '保存', id: 'up', icon: 'save', click: save},
	            { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
		}
    	var receiptUploader= new KHFileUploader(receiptUploadConfig);
    			$("#form1").initForm({
			
        	toolbarPosition : "bottom",
        	 toolbar: tbar,
        	success: function (response) {//处理成功
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	         		api.data.window.refreshGrid();
	            	api.close();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (response){
				if (response.attachs != null && response.attachs != undefined)
					if(js=="a"){//接收
						$("#useTime").ligerGetTextBoxManager().setDisabled();
						var a="<%=user.getName()%>";
			    		$("#reclaimName").val(a); 
			    		$("#reclaimNameId").val("<%=userId%>"); 
			    		$("#reclaimTime").val("<%=nowTime%>");
						showAttachFile1(response.attachs);
					}else{
						showAttachFile(response.attachs);
					}
				//alert(response.data.status);
			}
    	});
    });
    function saves(){
    	var id=$("#id").val();
    	var reclaimStatus=$("#reclaimStatus").val();
      top.$.ajax({
          url: "quality/ffhs/filejs.do?id="+id,
          type: "POST",
          dataType:'json',
          async: false,
          data:"&reclaimStatus="+reclaimStatus,
          success:function (data) {
              if (data) {
                 top.$.notice('数据接收成功！！！',3);
                 api.data.window.Qm.refreshGrid();
					api.close();
              }
          },
          error:function () {
         	 $.ligerDialog.error('出错了！请重试！！!',3);
              $("body").unmask();
          }
      });
    }
    function save(){ 
    	var uploadFiles=$("#uploadFiles").val();
    	var obj=$("#form1").validate().form();
    		var formData = $("#form1").getValues();
            $("body").mask("正在保存......");
           $.ajax({
               url: "quality/ffhs/save.do?uploadFiles="+uploadFiles,
               type: "POST",
               datatype: "json",
               contentType: "application/json; charset=utf-8",
               data: $.ligerui.toJSON(formData),
               success : function(data, stats) {
					if (data.success) {
						$("body").unmask();
						top.$.notice(data.msg,3);
						api.data.window.Qm.refreshGrid();
						api.close();
					} else {
					$("body").unmask();
						$.ligerDialog.error('保存失败！' );
					}
				},
				error : function(data) {
				$("body").unmask();
					$.ligerDialog.error('保存失败！');
				}
           });
    	}
  //添加附件
	function addAttachFile(files){
		if("${param.status}"=="detail"){
			$("#procufilesBtn").hide();
		}
		if(files){
			var attContainerId="procufiles";
			$.each(files,function(i){
				var file=files[i];
				$("#"+attContainerId).append("<li id='"+file.id+"'>"+
						"<div><a href='fileupload/downloadByFilePath.do?path="+file.path+"&fileName="+file.name+"'>"+file.name+"</a></div>"+
						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.path+"\",this,getUploadFile)'>&nbsp;</div>"+
						"</li>");
			});
			getUploadFile();
		}
	}
	 function showAttachFile1(files){
		$("#procufilesBtn").hide();
		var attContainerId="procufiles";
		 $.each(files,function(i){
				var file=files[i];
				 //显示附件
				$("#"+attContainerId).append("<li id='"+file.id+"'>"+
					"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
					"</li>");
			});
	 }
    function showAttachFile(files){
    	if("${param.pageStatus}"=="detail"){
			$("#procufilesBtn").hide();
		}
		if(files){
			//详情
			var attContainerId="procufiles";
			if("${param.pageStatus}"=="detail"){
				$.each(files,function(i){
					var file=files[i];
					 //显示附件
					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
						"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
						"</li>");
				});
			}
			//修改
			else if("${param.pageStatus}"=="modify"){
				$.each(files,function(i){
					var file=files[i];
					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
						"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.filePath+"\",this,getUploadFile)'>&nbsp;</div>"+
						"</li>");
				});
				getUploadFile();
			}
		}
    }
 // 将上传的所有文件id写入隐藏框中
	function getUploadFile(){
		var attachId="";
		var i=0;
		$("#procufiles li").each(function(){
			attachId+=(i==0?"":",")+this.id;
			i=i+1;
		});
		if(attachId!=""){
			attachId=attachId.substring(0,attachId.length);
		}
		$("#uploadFiles").val(attachId);
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
                $("#orgid").val(p.id);
                $("#orgName").val(p.name);
            }
        });
    }
    
   function close(){
   	if(api.data.window.submitAction)
   		api.data.window.submitAction();
   		api.close();
   }
	function choosePerson(){
		if(js=="a"){
		}else{
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
		            $("#useNameId").val(p.id);
		            $("#useName").val(p.name);
		            $("#useDepartment").val(p.org_name);
		            $("#useDepartmentId").val(p.org_id);
		        }
		    });
		}
	}
    </script>
   
</head>
<body>
<form id="form1" action="quality/ffhs/save.do" method="post" getAction="quality/ffhs/detail1.do?id=${param.id}">
    <input type="hidden" id="id" name="id"/>
    <input type="hidden" id="status" name="status"/>
    <input type="hidden" id="registrant" name="registrant" />
    <input type="hidden" id="registrantId" name="registrantId" />
    <input type="hidden" id="registrantDate" name="registrantDate" />
    <input type="hidden" id="useDepartmentId" name="useDepartmentId" />
    <input type="hidden" id="useNameId" name="useNameId" />
    <input type="hidden" id="reclaimNameId" name="reclaimNameId" />
    

    <h1 id="dy2" align="center" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">文件发放回收登记表</h1></br>
     <table id="sg1" class="check">
			 <tr>
                    <td width="650px">&nbsp;</td>
                    <td width="50px" align="center">编号：</td>
                    <td style="width: 200px;" class="l-t-td-right"><input ltype='text' readOnly="true" name="identifier" type="text"/></td>
           			<td width="10px">&nbsp;</td>
                    <td width="10px" align="center"></td>
                    <td width="20" class="l-t-td-right"></td>
            </tr>
	</table>
    <table id="dy" border="1" cellpadding="3" class="l-detail-table">
        <tr>
             <td class="l-t-td-left">文件名称</td>
        	<td class="l-t-td-right" colspan="5" ><input  readonly="readonly" validate="{required:true,maxlength:200}" ltype="text"  name="fileName" id="fileName" type="text" /></td>
		</tr>
        <tr>
         	<td class="l-t-td-left" >发放号</td>
        	<td class="l-t-td-right" colspan="2" ><input  readonly="readonly" validate="{required:true,maxlength:200}" ltype="text"  name="extendMark" id="extendMark" type="text" /></td>
              <td class="l-t-td-left" >领用部门</td>
            <td class="l-t-td-right" colspan="2" ><input  validate="{required:true}" ltype="text" readonly="readonly" name="useDepartment" id="useDepartment" type="text"/></td>
<!-- 		 onclick="chooseOrg()" ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}" -->
        </tr>
        
        <tr>
			<td class="l-t-td-left" >领用者签名</td>
			<td class="l-t-td-right" colspan="2"><input readonly="readonly" onclick="choosePerson();" name="useName" id="useName" type="text" ltype='text'  validate="{required:true}" /></td>
            <td class="l-t-td-left">领用时间</td>
			<td class="l-t-td-right"  colspan="2"> <input value="<%=nowTime %>" readonly="readonly" name="useTime" id="useTime"  type="text" ltype="date"  /></td>
        
        </tr>
        <tr>
				<td class="l-t-td-left">附件：</td>
				<td class="l-t-td-right" colspan="5">
						<input name="uploadFiles" type="hidden" id="uploadFiles" />
						<p id="procufilesDIV">
							<a class="l-button" id="procufilesBtn">
								<span class="l-button-main"><span class="l-button-text">选择文件</span></span>
							</a>
						</p>
				    	<div class="l-upload-ok-list">
							<ul id="procufiles"></ul>
						</div>
			</td>	
		</tr>
		<tr>
			<td class="l-t-td-left" >回收人</td>
			<td class="l-t-td-right" colspan="2"><input readonly="readonly" name="reclaimName" id="reclaimName" type="text" ltype='text' /></td>
            <td class="l-t-td-left">回收时间</td>
			<td class="l-t-td-right"  colspan="2"> <input readonly="readonly" name="reclaimTime" id="reclaimTime" type="text" ltype="date" ligerui="{disabled:true}" /></td>
        
        </tr>
        <tr>
             <td class="l-t-td-left">回收状况</td>
        	<td class="l-t-td-right" colspan="5" ><input readonly="readonly" ltype="text"  name="reclaimStatus" id="reclaimStatus" type="text" /></td>
		</tr>
    </table>
   
   
</form>
</body>
</html>
