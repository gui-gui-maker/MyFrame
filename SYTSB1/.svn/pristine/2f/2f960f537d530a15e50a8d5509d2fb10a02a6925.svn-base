<%@page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>app版本升级</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/appclient/js/fileupload.js"></script>
<script type="text/javascript">
	var pageStatus = "${param.pageStatus}";//页面状态
	$(function() {
		//初始化表单数据
		$("#form1").initForm({
			toolbar : [ {
				text : '保存',
				id : 'save',
				icon : 'save',
				click : saveData
			}, {
				text : '关闭',
				id : 'close',
				icon : 'cancel',
				click : function() {
					api.close();
				}
			} ],
			success : function(res) {
				if (res.success) {
					top.$.notice("操作成功！");
					api.data.window.Qm.refreshGrid();
					api.close();
				} else {
					$.ligerDialog.error("操作异常！");
				}
			},
			afterParse: function(){
				$("#receiptfiles").khUpload({ 
			        fileSize : "100mb",
			        businessId: "${param.id}",
			        fileNum: 1,
			        useThirdDevice: false,
			        extName : "wgt,apk,ipa"
			    }); 
			}
		});
	});

	//保存申请信息
	function saveData() {
		if ($("#form1").validate().form()) {
			var version = $("#version").val();
			var updPkgs = $("#receiptfiles").khUpload().uploadedFiles;
			var pattern = /\d+\.\d+\.\d+/;
			if (!pattern.test(version)) {
				$.ligerDialog.error("版本号填写异常！,请按规则填写版本号。如：1.0.1");
				$("#version").focus();
				return;
			}
			if (updPkgs.length==0) {
				//$.ligerDialog.error("请上传升级包！");
				//return;
			}else{
				$("#carpicId").val(updPkgs[0].id);
			}
			$("#form1").submit();
		}
	}
</script>
</head>
<body>
    <form id="form1" action="appclient/update/save_update_data.do" getAction="appclient/update/detail.do?id=${param.id}">
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
            <input type="hidden" name="id" />
            <input name="carpicId" id="carpicId" type="hidden" />
            <tr>
            	<td class="l-t-td-left">类型：</td>
	            <td class="l-t-td-right">
	            	<input type="radio" ltype="radioGroup" name='type' validate="{required:true}" ligerui="{data:[{id:'IOS',text:'IOS'},{id:'ANDROID',text:'ANDROID'}]}"/>
	            </td>
	            <td class="l-t-td-left">发布方式：</td>
	            <td class="l-t-td-right" >
	            	<input type="radio" ltype="radioGroup"  name='pubType' validate="{required:true}" ligerui="{data:[{id:'1',text:'wgt升级包'},{id:'2',text:'整包方式'},{id:'3',text:'android签名更改APK升级'}]}"/>
	            </td>
            </tr>
            <tr>
	            <td class="l-t-td-left">应用版本号：</td>
	            <td class="l-t-td-right" ><input name="appVersion" type="text" id="version" ltype='text'
	                validate="{required:true,maxlength:32}" /></td>
	            <td class="l-t-td-left">资源版本号：</td>
	            <td class="l-t-td-right" ><input name="version" type="text" id="version" ltype='text'
	                validate="{required:true,maxlength:32}" /></td>
            </tr>
            <tr>
            	<td class="l-t-td-left">包名（bundleId）：</td>
	            <td class="l-t-td-right" colspan="3">
	            	<input name="packageName" type="text"  ltype='text'
	                validate="{required:true,maxlength:32}" />
	            </td>
            </tr>
            <tr>
            	<td class="l-t-td-left">强制更新：</td>
	            <td class="l-t-td-right" colspan="3">
	            	<u:combo name="needUpdate" ltype="radioGroup" code="sys_sf" attribute="initValue:'0'" validate="required:true"></u:combo>
	            </td>
            </tr>
            <tr>
                <td class="l-t-td-left">升级描述：</td>
                <td class="l-t-td-right" colspan="3"><textarea rows="4" name="description" type="text" ltype="text"
                        validate="{required:true,maxlength:255}"></textarea></td>
            </tr>
            <tr>
                <td class="l-t-td-left">升级包：</td>
                <td class="l-t-td-right" id="receiptfiles"></td>
            </tr>
            <tr>
            	<td class="l-t-td-left">升级地址：</td>
	            <td class="l-t-td-right" colspan="3">
	            	<input name="url" type="text"  ltype='text'
	                validate="{maxlength:4000}" ligerui="{explain:'企业应用：plist的地址(https开头),应用商店:应用商店app地址'}" />
	            </td>
            </tr>
        </table>
    </form>
</body>
</html>
