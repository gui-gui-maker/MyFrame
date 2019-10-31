<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
		<%@ include file="/k/kui-base-form.jsp" %>
		<script type="text/javascript" src="pub/office/js/editor.js"></script>
		<script type="text/javascript" src="pub/seal/js/seal.js"></script>
        <title>行政区划机构</title>

		<script type="text/javascript">
			$(function () {
				$("#layout1").ligerLayout({
					topHeight: 40,
					sapce: 5,
					allowLeftCollapse: false,
					allowRightCollapse: false
				});

				createNtkoEditor("container");//初始化千航office控件。
				initSeal("container");//初始化印章参数。用户提示安装控件


				loadRemoteDoc();//加载word文档
			});
			//远程加载文档
			function loadRemoteDoc() {
				try {
					var wordObj = document.getElementById("TANGER_OCX_OBJ");
					wordObj.OpenFromURL("${pageContext.request.contextPath}/demo/signseal/demo_seal.doc");
					wordObj.FileClose = false;
					wordObj.FileSave = false;
					wordObj.ActiveDocument.Saved = true;
				} catch (err) {
					alert("打开文件错误，请检查！");
				}
			}
			//一般盖章
			function signOne() {
				var wordObj = document.getElementById("TANGER_OCX_OBJ");
				insertWordSeal("办案单位意见", wordObj.ActiveDocument, "", false, null);
			}
			//选择盖章
			function signSelect() {
				var wordObj = document.getElementById("TANGER_OCX_OBJ");
				insertWordSeal("治安部门意见", wordObj.ActiveDocument, "", true, "CDKH");
			}
			function deleteSeal() {
				var wordObj = document.getElementById("TANGER_OCX_OBJ");
				delWordSeal(wordObj.ActiveDocument);
			}
			function handSign() {
				var wordObj = document.getElementById("TANGER_OCX_OBJ");
				handSeal(wordObj.ActiveDocument);
			}
		</script>
    </head>
	<body>
		<div id="layout1">
			<div position="top" title="百成印章集成测试-千航" class="overflow-auto">
				<div class="toolbar" style="">
					<input type="button" class="l-button l-button-submit" style="margin:5px 0px 5px 10px" name="handSign" value="一般盖章" onclick="signOne()"/>
					<input type="button" class="l-button l-button-submit" style="margin:5px 0px 5px 10px" name="handSign" value="选择盖章" onclick="signSelect()"/>
					<input type="button" class="l-button l-button-submit" style="margin:5px 0px 5px 10px" name="handSign" value="删除印章" onclick="deleteSeal()"/>
					<input type="button" class="l-button l-button-submit" style="margin:5px 0px 5px 10px" name="handSign" value="签名" onclick="handSign()"/>
				
				</div>
			</div>
			<div position="center">
				<div id="container" style="height:100%">

				</div>
			</div>
		</div>
    </body>
</html>
