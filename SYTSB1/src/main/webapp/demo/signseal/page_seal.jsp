<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="edit">
<title>页面签章示例</title>
<%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript" src="pub/seal/js/seal.js"></script>
<script type="text/javascript">
        $(function () {
			$("#form1").initForm({
				toolbar: [
					{ text: '加盖印章', id: 'addSeal', icon: 'seal', click: addSeal},
					{ text: '签名', id: 'addSign', icon: 'seal', click: addSign},
					{ text: '显示印章', id: 'show', icon: 'show', click: function () {
						showSingleSeal(sealId, sign.data[0], 80, -90, 0);
					} },
					{ text: '关闭', id: 'close', icon: 'cancel', click: close }
				]
			});
		});

		function addSeal() {
			//初始化印章参数
			configSetting(4, null, '', '', function (data) {
				//处理添加印章成功后回调函数
				var seal = $("#seal").val();
				if (seal.length > 0) {
					seal += "," + data.id;
				} else {
					seal += data.id;
				}
				$("#seal").val(seal);
				$("#addSeal").ligerGetTextBoxManager().setDisabled();
				//alert($("#seal").val());
			}, function (data) {
				//处理删除成功后，回调函数
				var seal = $("#seal").val();
				seal = seal.replace(data, "");
				var array = seal.split(",");
				seal = "";
				for (var i = 0; i < array.length; i++) {
					if (array[i] != "") {
						seal += "," + array[i];
					}
				}
				if (seal.length > 0) {
					seal = seal.substr(1);
				}
				$("#seal").val(seal);

			});
			//添加印章
			InsertSeal("sealPositon", true, 150, 150, 10, -80);

		}
		
		function addSign() {
			//初始化印章参数
			configSetting(4, null, '', '', function (data) {
				//处理添加印章成功后回调函数
				var seal = $("#seal").val();
				if (seal.length > 0) {
					seal += "," + data.id;
				} else {
					seal += data.id;
				}
				$("#seal").val(seal);
				$("#addSign").ligerGetTextBoxManager().setDisabled();
				//alert($("#seal").val());
			}, function (data) {
				//处理删除成功后，回调函数
				var seal = $("#seal").val();
				seal = seal.replace(data, "");
				var array = seal.split(",");
				seal = "";
				for (var i = 0; i < array.length; i++) {
					if (array[i] != "") {
						seal += "," + array[i];
					}
				}
				if (seal.length > 0) {
					seal = seal.substr(1);
				}
				$("#seal").val(seal);

			});
			//添加印章
			InsertSeal("sealPositon2", false, 150, 150, 10, -80);

		}
    </script>
<style type="text/css">
.l-panel-header {
	background:url("");
	border-bottom:1px solid #99BBE8;
	color:#15428B;
	font-size:12px;
	font-weight:bold;
	height:24px;
	position:relative;
}

.l-text-suffix {
	padding:0 0 0 5px !important;
	vertical-align:middle;
	width:36px;
}
</style>
</head>
<body>
	<div class="scroll-tm">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>家庭基本资料</div>
			</legend>
			<table id="homeInfo" border="1" cellpadding="3" cellspacing="0"
				   width="" class="l-detail-table" pageStatus="detail">
				<tr>
					<td class="l-t-td-left"></td>
					<td class="l-t-td-right" align="right" colspan="5"><a
						href="javascript:openDetail()">[家庭详细资料]</a></td>
				</tr>
				<tr>
					<td class="l-t-td-left">户主姓名：</td>
					<td class="l-t-td-right"><input name="name" type="text"
													ltype='text' validate="{required:true,maxlength:50}"/></td>
					<td class="l-t-td-left">户主身份证号：</td>
					<td class="l-t-td-right"><input name="idn" type="text"
													ltype='text' validate="{required:true,idno:true,maxlength:18}"/>
					</td>
					<td class="l-t-td-left">联系电话：</td>
					<td class="l-t-td-right"><input name="tel" type="text"
													ltype='text' validate="{required:true,maxlength:11}"/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">户籍所在地 ：</td>
					<td class="l-t-td-right"><input name="natives" type="text"
													ltype='text' ligerui="{suffix:'派出所'}"
													validate="{required:true,maxlength:200}"/></td>

					<td class="l-t-td-left">居住地址：</td>
					<td class="l-t-td-right" colspan="3"><input name="addr"
																type="text" ltype='text' validate="{required:true,maxlength:200}"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">救助类别：</td>
					<td class="l-t-td-right"><u:combo name="helpTypes"
													  code="db_002" validate="required:true"
													  attribute="split:',',isShowCheckBox: true,isMultiSelect: true"/>
					</td>
					<td class="l-t-td-left">致贫原因：</td>
					<td class="l-t-td-right">
						<!--	<input name="poorReason" id="poorReason" type="text" ltype="text" />-->
						<u:combo name="poorReason" code="db_001" tree="true"
								 validate="required:true" attribute="treeLeafOnly:true"
								 treeAttribute="checkbox:true"/>
					</td>
					<td class="l-t-td-left">申请时间：</td>
					<td class="l-t-td-right"><input name="applyTime" type="text"
													ltype='date' validate="{required:true}"
													ligerui="{format:'yyyy-MM-dd'}"/></td>

				</tr>
				<tr>
					<td class="l-t-td-left">申请原因 ：</td>
					<td class="l-t-td-right" colspan="3"><textarea
						name="applyReason" cols="70" rows="3" class="l-textarea"
						validate="{required:true}"></textarea></td>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right" colspan="1"><textarea name="remark"
																   cols="70" rows="3" class="l-textarea"></textarea></td>
				</tr>
			</table>
		</fieldset>
		<fieldset id="sug1" class="l-fieldset">
			<legend class="l-legend">
				<div>村社区审核意见</div>
			</legend>
			<div style="height: 170px; border: 0px;">
				<table border="1" cellpadding="3" cellspacing="0"
					   pageStatus="detail" width="" class="l-detail-table">
					<tr>
						<td class="l-t-td-left">是否同意：</td>
						<td class="l-t-td-right"><u:combo name="isPass"
														  ltype="radioGroup" code="ba_sf" validate="required:true"/></td>
						<td id="sealPositon1" rowspan="2"
							style="width: 300px; height: 170px; border: 0px; text-align: center; vertical-align: middle;">
							印&nbsp;&nbsp;&nbsp;章</td>
					</tr>
					<tr>
						<td class="l-t-td-left">意见：</td>
						<td class="l-t-td-right"><textarea name="sug" cols="70"
														   rows="12" class="l-textarea" validate="{required:true}"></textarea>
						</td>

					</tr>
				</table>
			</div>
		</fieldset>
		<fieldset id="sug2" class="l-fieldset">
			<legend class="l-legend">
				<div>乡镇审核意见</div>
			</legend>
			<table border="1" cellpadding="3" cellspacing="0" pageStatus="detail"
				   width="" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">是否同意：</td>
					<td class="l-t-td-right"><u:combo name="isPass"
													  ltype="radioGroup" code="ba_sf" validate="required:true"/></td>
					<td id="sealPositon2" rowspan="2"
						style="width: 300px; height: 170px; border: 0px; text-align: center; vertical-align: middle;">
						印&nbsp;&nbsp;&nbsp;章</td>
				</tr>
				<tr>
					<td class="l-t-td-left">意见：</td>
					<td class="l-t-td-right"><textarea name="sug" cols="70"
													   rows="12" class="l-textarea" validate="{required:true}"></textarea>
					</td>

				</tr>
			</table>
		</fieldset>
		<form id="form1">
			<input type="hidden" id="id" name="id"/> <input type="hidden"
															id="homeId" name="homeId" value=""/> <input
			type="hidden" name="areaCode" value=""/> <input
			type="hidden" id="seal" name="seal" value=""/>
			<fieldset class="l-fieldset">
				<legend class="l-legend">
					<div>审核意见</div>
				</legend>
				<table border="1" cellpadding="3" cellspacing="0" width=""
					   class="l-detail-table">

					<tr>
						<td class="l-t-td-left">是否同意：</td>
						<td class="l-t-td-right" id="sealPositon"
							style="width: 300px; height: 170px;"><u:combo name="isPass"
																		  ltype="radioGroup" code="ba_sf" validate="required:true"
																		  attribute="onChange:setSealButton"/></td>
						<td class="l-t-td-left">意见：</td>
						<td class="l-t-td-right" style="width: 60%"><textarea
							name="sug" cols="70" rows="12" class="l-textarea"
							validate="{required:true}"></textarea></td>
					</tr>
					<!-- <tr>
				<td height="0" width="0" colspan="4" id="sealPositon" style="POSITION: relative;border: 0px;"></td>
			</tr> -->
				</table>
			</fieldset>
		</form>
	</div>
	<div class="toolbar-tm">
		<div class="toolbar-tm-bottom"></div>
	</div>

</body>
</html>
