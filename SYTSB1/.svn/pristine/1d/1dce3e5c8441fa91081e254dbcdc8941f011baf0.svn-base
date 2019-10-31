<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*" %> 
<%@page contentType="text/html;charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
CurrentSessionUser user = SecurityUtil.getSecurityUser();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title></title>
<script type="text/javascript" src="<%=basePath %>app/cloud_platform/upload/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
var businessId = '${param.id}';
var busUniqueName = '${param.bh}';
var uploadType = '';
var bacth = '';
var api = frameElement.api;
$(function() {
	//初始化扫描机参数
	SCANOCX.DisplayToolbar(0);
	//第一个参数设置图片本地保存的地址，第二个参数1设置上传类型为jpg
	SCANOCX.SetScanParam('E:\\test\\',1);
	//清除缓存
	SCANOCX.CleanCache();

});
//开始扫描（可以叠加）
function scan(){
	//扫描
	$("#scan").attr("disabled",true);
	SCANOCX.BeginScan();
	setTimeout(function(){
		$("#scan").attr("disabled",false);
	}, 2000);
}
//清楚缓存
function clean(){
	SCANOCX.CleanCache();
}

//上传
function upload(){
	uploadType = $("input[name='uploadType']:checked").val();
	if(uploadType == ""||uploadType==null){
		alert("请选择上传类别！");
		return;
	}
	//禁用上传按钮
	$("#scanUpload").attr("disabled",true);
	
	var date = new Date();
	batch = date.toString();
	var paramsObject = {
			"businessId" : businessId,
			"busUniqueName" : busUniqueName,
			"uploadType" : uploadType,
			"batch" : batch,
		};
	var params = JSON.stringify(paramsObject);
	SCANOCX.UploadFileToNet('<%=basePath%>fileupload/uploads.do',params);
	if(!SCANOCX.ScanFinish()){
		//更新档案盒
		$.post("<%=basePath%>uploadsAction/a/updateArchivesBox.do",{id:businessId},function(res){
			if(res.success){
				console.log("更新档案和成功");
			}else{
				console.log(res.msg);
			}
		});
		//请求核实上传是否完全成功
		$.ajax({  
	        url : "<%=basePath%>uploadsAction/a/scanUploadDetail.do",  
	        async : false, // 注意此处需要同步，因为返回完数据后，下面才能让结果的第一条selected  
	        type : "POST",
	        data:{
		        	"id":batch,
		        	"businessId" : businessId,
		        	"userId":"<%=user.getId()%>",
		        	"userName":"<%=user.getName()%>"
	        	},
	        success : function(res){
				if(res.success){
					if(res.data){
						var batch = res.data;
						var str = '共上传'+batch.total+'个文件，成功：'+batch.success+'个，失败：'+(batch.total-batch.success)+'个。';
						//清楚缓存
						SCANOCX.CleanCache();
						//刷新qm
						api.data.window.Qm.refreshGrid();
						//将选择重置，让用户重新选择
						$("input[name='uploadType']").attr("checked",false);
						//恢复上传按钮
						$("#scanUpload").attr("disabled",false);
						alert(str);
						//关闭窗口
						//api.close();
					}
				}else{
					$("#scanUpload").attr("disabled",false);
					alert(res.msg);
					//恢复上传按钮
				}
			}
	    });
	}
}
</script>
</head>

<body>
<div style="margin-left:200px;">
	<h3>上传类型：</h3>
	<form>
	<label>检验报告</label></label><input name="uploadType" type="radio" value="0">
	<label>原始记录</label></label><input name="uploadType" type="radio" value="1">
	<label>自检报告</label></label><input name="uploadType" type="radio" value="2">
	<label>其他</label></label><input name="uploadType" type="radio" value="3">
	</form>
	<br></br>
	<input id="scan"  type="button" value="扫描" onclick="scan();"/>
	<input id="scanUpload" type="button" value="上传" onclick="upload();" />
	<input type="button" value="清除缓存" onclick="clean();"/>
</div>
<div style="text-align:center;" >
<p>请在右侧空白框中右键单击选择扫描仪</p>
		<center>
			<object classid="clsid:BEB4D964-617E-4067-BF8D-4A49C046FC74" codeBase = "SCANOCX.cab#version=1,2013,1025,1839" id="SCANOCX" width="1000" height="520">
			</object>
		</center>
</div>
	
	
</body>
</html>