
loadCoreLibrary("sysDesktop");

function init_sysDesktop() {
	//winOpen({"content":"url:/app/main.jsp",lock:false});
	$("#sysDesktop").append('<div id="sysMain1" style="position:absolute;top:0px;left:0px;right:0px;bottom:0px;border: 1px solid #FF0000;"><iframe name="iframeObj" src="k/main.jsp" width="100%" height="100%" scrolling="auto" frameborder="0" border="0"></iframe></div>');
	//winOpen({"content":"url:/app/demo/demo_list.jsp",lock:false});
	$("#sysDesktop").append('<div id="dvDock" class="qo"><div hidefocus="true" tabindex="0" title="邮箱触点" id="dvDockStartOuter" class="mI"><div id="dvDockStart" class="ps"><span class="nm lq"></span><span class="nm kY"></span><span class="nm kZ"></span><span class="nm la"></span><span style="display:none" id="spnDockNew" class="qg"></span></div></div>');
	$("#dvDock").toggle(
		function () {
			$("#sysMain1").hide("right");
		},
		function () {
			$("#sysMain1").show("left");
		}
	);
	/*$("#dvDock").click(function(){

	 });*/
	//e();
	$("#sysMain1").show();

};