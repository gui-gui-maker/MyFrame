<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="edit">
	<%@ include file="/k/kui-base-form.jsp" %>
	<title></title>
	<script type="text/javascript">
		$(function () {

			var form1 = $("#formObj2").initForm({
				toolbar: [
					{
						text: "还原默认值", id: "skinSetDe", click: function () {
						var setting = {
							"autoStart": "0",
							"autoLogin": "0",
							//"autoRememberPass": "0",
							"softAutoHide": "0",
							"softQQSound": "0",
							"msgboxAutoHide": "0",
							"softCloseStatus": "0"
						};
						form1.setValues(setting);
						setSetting(setting);
						closeWindow();
					}
					},
					{
						text: "保存", id: "save", icon: "save", click: function () {
						var setting = $("#formObj2").getValues();
						setSetting(setting);

						closeWindow();
					}
					},
					{
						text: "关闭", icon: "cancel", click: function () {
						//api.close();
						closeWindow();
					}
					}
				]
			});
			//form1.setValues({"autoStart":"0","autoLogin":"0","softAutoHide":"1","softQQSound":"1","msgboxAutoHide":"0","softCloseStatus":"1"});
			var setting = {};
			var autoStart=window.external.ReadIni("user", "autoStart", "confing.user.ini");
			setting["autoStart"]=autoStart;
			var autoLogin=window.external.ReadIni("user", "autoLogin", "confing.user.ini");
			setting["autoLogin"]=autoLogin;
			var softAutoHide=window.external.ReadIni("user", "softAutoHide", "confing.user.ini");
			setting["softAutoHide"]=softAutoHide;
			var softQQSound=window.external.ReadIni("user", "softQQSound", "confing.user.ini");
			setting["softQQSound"]=softQQSound;
			var msgboxAutoHide=window.external.ReadIni("user", "msgboxAutoHide", "confing.user.ini");
			setting["msgboxAutoHide"]=msgboxAutoHide;
			var softCloseStatus=window.external.ReadIni("user", "softCloseStatus", "confing.user.ini");
			setting["softCloseStatus"]=softCloseStatus;
			//alert(setting);
			form1.setValues(setting);
			//====================================================

			var tabId = $.kh.request("tabId") || 1;//alert(tabId);
			//tabId=5;

			var navtab = $("#tab1").ligerTab();
			navtab.selectTabItem("tabItem" + tabId);


			pageTitle({to: "#title", text: "个性设置", icon: "k/kui/images/icons/32/widgets.png", show: true});

			var sysMsgSwitch=kui["sysMsgSwitch"]!==false ? true : kui["sysMsgSwitch"];
			if (!sysMsgSwitch) {
				
			} else {
				$("#softQQSoundC1,#msgboxAutoHideC1").show();
			}


		});

		function closeWindow(arg) {
			var ret = window.external.ClosePopup("w_user_individuality_setting");
			if (!ret) {
				alert("WinKH_E7："+window.external.GetLastErrorMessage());
			}
		}
		function setSetting(data) {
			var setting=data,ret;
			for (var item in setting) {
				var value=setting[item];
				var ret = window.external.WriteIni("user", item, value, "confing.user.ini");
				if (!ret) {
					alert("请 以管理员身份运行 此程序。WinKH_E8："+window.external.GetLastErrorMessage());
				}
			};
			if (setting["autoStart"]=="1") {
				ret = window.external.SetAutoStartup(true);
			} else {
				ret = window.external.SetAutoStartup(false);
			}

			if (!ret) {
				var error = window.external.GetLastError();
				//alert(error);
			}
		}
	</script>

	<style type="text/css" media="screen" id="test">
		#skinSetDe {
			float: left;
		}

		.l-t-td-left {
			width: 140px;
		}
	</style>

</head>
<body>
<!-- 提示文字 -->
<div class="item-tm" id="title"></div>
<!-- 提示文字 -->
<form name="formObj2" id="formObj2" method="post">


	<div id="tab1" class="navtab">

		<div title="个性设置" tabid="tabItem1">
			<fieldset class="l-fieldset">
				<legend class="l-legend">
					<div>软件设置</div>
				</legend>
				<table cellpadding="3" cellspacing="0" class="l-detail-table">
					<tr>
						<td class="l-t-td-left">开机时自动启动：</td>
						<td class="l-t-td-right">
							<input type="radio" name="autoStart" ltype="radioGroup" validate="{required:false}" ligerui="{initValue:'1',data:[{text:'开启',id:'1'},{text:'关闭',id:'0'}]}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">启动自动登录：</td>
						<td class="l-t-td-right">
							<input type="radio" name="autoLogin" ltype="radioGroup" validate="{required:false}" ligerui="{initValue:'1',data:[{text:'开启',id:'1'},{text:'关闭',id:'0'}]}"/>
						</td>
					</tr>
					<%--<tr>--%>
						<%--<td class="l-t-td-left">登录记住密码：</td>--%>
						<%--<td class="l-t-td-right">--%>
							<%--<input type="radio" name="autoRememberPass" ltype="radioGroup" validate="{required:false}" ligerui="{initValue:'1',data:[{text:'开启',id:'1'},{text:'关闭',id:'0'}]}"/>--%>
						<%--</td>--%>
					<%--</tr>--%>
					<tr id="">
						<td class="l-t-td-left">自动登录后自动隐藏：</td>
						<td class="l-t-td-right">
							<input type="radio" name="softAutoHide" ltype="radioGroup" validate="{required:false}" ligerui="{initValue:'1',data:[{text:'开启',id:'1'},{text:'关闭',id:'0'}]}"/>
						</td>
					</tr>
					<tr id="softQQSoundC1" class="hide">
						<td class="l-t-td-left">有新消息来时声音提示：</td>
						<td class="l-t-td-right">
							<input type="radio" name="softQQSound" ltype="radioGroup" validate="{required:false}" ligerui="{initValue:'1',data:[{text:'开启',id:'1'},{text:'关闭',id:'0'}]}"/>
						</td>
					</tr>
					<tr id="msgboxAutoHideC1" class="hide">
						<td class="l-t-td-left">消息窗口自动隐藏：</td>
						<td class="l-t-td-right">
							<input type="radio" name="msgboxAutoHide" ltype="radioGroup" validate="{required:false}" ligerui="{initValue:'1',data:[{text:'开启',id:'1'},{text:'关闭',id:'0'}]}"/>
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset class="l-fieldset">
				<legend class="l-legend">
					<div>关闭主窗口</div>
				</legend>
				<table cellpadding="3" cellspacing="0" class="l-detail-table">
					<tr>
						<td class="l-t-td-left">隐藏到任务栏通知区域</td>
						<td class="l-t-td-right">
							<input type="radio" name="softCloseStatus" ltype="radioGroup" validate="{required:false}" ligerui="{initValue:'1',data:[{text:'隐藏到任务栏通知区域，不退出系统',id:'1'},{text:'退出系统',id:'0'}]}"/>
						</td>
					</tr>
				</table>
			</fieldset>
		</div>

	</div>

</form>

</body>
</html>