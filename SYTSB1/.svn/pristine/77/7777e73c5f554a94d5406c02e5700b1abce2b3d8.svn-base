	//热门搜索数据	
	var HISTORIES = [];
	//添加历史记录
     function addHistory(history){
    	HISTORIES=history;
    	var string_ = "";
		for (var i = 0; i < history.length; i++) {
			var string_f = history[i][0];
			var string_n = history[i][1];
			string_ += "{text: '" + string_f + "', weight: '" + string_n 
			+ "',html: {'class': 'span_list',onclick:'on_click(this,event)'}},";//onmouseover:'on_mouseover(this,event)',onmouseout:'on_mouseout()',
		}
		var string_list = string_;
		var word_list = eval("[" + string_list + "]");
		$("#my_favorite_latin_words").empty();
    	$("#my_favorite_latin_words").jQCloud(word_list);
     }
	

	function on_mouseover(e, ev) {
		var txt = $(e).html();
		ev = ev || event;
		$.each(HISTORIES, function(i, item) {
			if(txt == item[0]){
				var html = item[0]+"<br />曝光数"+item[1]+"<br />"+item[2];
				$("#my_favorite_latin_words").after("<div class='append_div' style='left:" + ev.clientX + "px; top:" + ev.clientY + "px; '>" + html + "</div>");
				return;
			}
			
		});
	}
	function on_click(_this, ev) {
		var txt = $(_this).html();
		ev = ev || event;
		$.each(HISTORIES, function(i, item) {
			if(txt == item[0]){
				//query(item[0],item[2]);
				queryByCompany(item[0]);
				return;
			}
		});
	}
	/*$(function() {
		$("#my_favorite_latin_words").jQCloud(word_list);
	});*/
	

	function on_mouseout() {
		$(".append_div").remove();
	}
