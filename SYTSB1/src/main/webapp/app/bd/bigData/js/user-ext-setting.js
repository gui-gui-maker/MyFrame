/**
 * Created by lybide on 2016/12/1.
 */
$(function(){
	var welcomeBigDesktopShow=kui["welcomeBigDesktopShow"] || "1";//kui["user"]["welcomeBigDesktopShow"]=="" ? "" : kui["user"]["welcomeBigDesktopShow"];
	if (kui["user"]["welcomeBigDesktopShow"]) { //2016年11月29日 17:48:45 星期二 lybide 全局变量
		welcomeBigDesktopShow=kui["user"]["welcomeBigDesktopShow"];
	}
	//if (welcomeBigDesktopShow) {
		$("body").append('<div class="dfasdfwef" style="position:absolute;left:10px;bottom:3px;z-index:110;font-size:12px;color:#FFFFFF;"><input type="checkbox" value="1" name="welcomeBigDesktopShow" id="dd-user-welcomeBigDesktopShow9988" style="float:left;height:16px;width:16px;"><label for="dd-user-welcomeBigDesktopShow9988" style="">下次不再显示</label></div>');
		//2016年10月26日 16:21:09 星期三 lybide
		if (welcomeBigDesktopShow=="1") {
			$("#dd-user-welcomeBigDesktopShow9988").attr('checked','true');
		}
		$("#dd-user-welcomeBigDesktopShow9988").click(function(){
			var _this=$(this);
			var sss=$("#dd-user-welcomeBigDesktopShow9988:checked").val();
			if (sss) {
				sss = "1";
			} else {
				sss = "0";
			}
			//console.log(sss);
			//return;
			$.post("pub/sysPersonalize/savePersonalize.do", {extConfig:"{\"welcomeBigDesktopShow\":\""+sss+"\"}"}, function () {

			});
		});
	//}
	$("#m-goto-system-a").click(function(){
		parent.$("#m-big-data-panel-close-a").click();
	});
});