<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>科研项目申请书审核</title>
<%@ include file="/k/kui-base-form.jsp"%>
<%
	String apply_type = request.getParameter("apply_type");
%>
<SCRIPT type=text/javascript src="app/common/ueditor/ueditor.config.js"></SCRIPT>  
<SCRIPT type=text/javascript src="app/common/ueditor/ueditor.all.min.js"></SCRIPT>
<script type="text/javascript" src="app/common/js/idCard.js"></script>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	$(function() {
	    $("#formObj").initForm({toolbar: null});
	});
	function openFile(id){
		window.location.href = $("base").attr("href")+"fileupload2/downloadCompress.do?id="+id+"&proportion=0";
		
// 	 	top.$.dialog({
// 			width : '90%',
// 			height : '95%',
// 			lock : true,
// 			title : "预览",
// 			content : 'url:app/cloud_platform/owner/doc_view.jsp?status=add',
// 			data : {
// 				"window" : window,id:id
// 			}
// 		}); 
	}

</script>
</head>
<body>
<base
	href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />

	<form name="formObj" id="formObj" method="post" action="com/tjy2/scientificProvince/updateConfirm.do?id=${param.id}">
		<input type="hidden" name="id" id="id"/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>报销制度</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
			<tr>
					<td class="l-t-td-right">
					<a href="javascript:void(0);"  onclick="openFile('402883a26584248e0165888b56cd3327')"  style=" height: 21px; border: 1px #26bbdb solid;
			line-height: 21px; padding: 0 11px; background: #e4e4e4; border-radius: 3px; display: inline-block; text-decoration: none;outline: none; font-size: 15px;" >报销要求一览表</a>
			</td>			
				</tr>
				<tr>
					<td class="l-t-td-right">
					<a href="javascript:void(0);"  onclick="openFile('402883a2656a48ca01656a5d8e8e05ef')"  style=" height: 21px; border: 1px #26bbdb solid;
			line-height: 21px; padding: 0 11px; background: #e4e4e4; border-radius: 3px; display: inline-block; text-decoration: none;outline: none; font-size: 15px;" >差旅费管理办法</a>
			</td>			
				</tr>
				<tr>
					<td class="l-t-td-right">
					<a href="javascript:void(0);"  onclick="openFile('402883a05b0e3035015b0e41bb750aa0')"  style=" height: 21px; border: 1px #26bbdb solid;
			line-height: 21px; padding: 0 11px; background: #e4e4e4; border-radius: 3px; display: inline-block; text-decoration: none;outline: none; font-size: 15px;" >节约相关制度解答</a>
			</td>			
				</tr>
				<tr>
					<td class="l-t-td-right">
					<a href="javascript:void(0);"  onclick="openFile('402883a25c78c384015c7c2fa4b407ff')"  style=" height: 21px; border: 1px #26bbdb solid;
			line-height: 21px; padding: 0 11px; background: #e4e4e4; border-radius: 3px; display: inline-block; text-decoration: none;outline: none; font-size: 15px;" >存货管理暂行办法</a>
			</td>			
				</tr><tr>
					<td class="l-t-td-right">
					<a href="javascript:void(0);"  onclick="openFile('402883a05b0e3035015b0e41bc5f0aa4')"  style=" height: 21px; border: 1px #26bbdb solid;
			line-height: 21px; padding: 0 11px; background: #e4e4e4; border-radius: 3px; display: inline-block; text-decoration: none;outline: none; font-size: 15px;" >差旅费住宿费标准明细表</a>
			</td>			
				</tr>
			</table>
		</fieldset>
	</form>
</div>
</body>
</html>
