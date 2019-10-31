function madeMaxPopUpBox(mainName) {
	if (document.getElementById(mainName) == undefined) {
		$("body")
				.append(
						"<div id='tccontent_"
								+ mainName
								+ "' class='largebox'>"
								+ "<div id='light_"
								+ mainName
								+ "' class='white_content'>"
								+ "<div class='wtctbg'></div>"
								+ "<div class='tankuang'>"
								+ "<div class='close'>"
								+ "<a id='t-close-btn_"
								+ mainName
								+ "' class='' href='javascript:void(0)' title='关闭'>x</a>"
								+ "</div><div id='"
								+ mainName
								+ "' class='m-center-wrap'  ></div></div> "
								+ "</div>"
								+ "<div id='fade_"
								+ mainName
								+ "' class='black_overlay' style='display:none;'></div>"
								+ "</div>");

		$("#fade_" + mainName + ",#t-close-btn_" + mainName).click(
				function() {
					$(
							"#light_" + mainName + ",#tccontent_" + mainName
									+ ",#fade_" + mainName).hide();
				});
	} else {
		if (document.getElementById("t-close-btn_" + mainName) == undefined) {
			console.log("页面存在重复的id参数：" + mainName);
		}
	}
}

function openMaxPopUpBox(mainName) {
	$(".largebox .white_content").addClass("md-show2");
	$("#light_" + mainName + ",#tccontent_" + mainName + ",#fade_" + mainName)
			.show();
}