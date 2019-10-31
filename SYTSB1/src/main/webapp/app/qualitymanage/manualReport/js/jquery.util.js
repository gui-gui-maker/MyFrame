//序列化表单
(function ($) {
    $.fn.serializeFormJSON = function () {
 
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
})(jQuery);

//初始化下拉框函数
function initSelect(selectionObj,options){
	var ops = '<option value="">请选择</option>';
	//获取部门
	for(var i=0;i<options.length;i++){
		ops += "<option value="+options[i]["id"]+">"+options[i]["text"]+"</option>"
	}
	$(selectionObj).html(ops);
}
//初始化表单信息
function initFormData(item){
	var hides = $("#formObj input[type='hidden']").get();
	for(var i in hides){
		$(hides[i]).val(item[hides[i].name]);
	}
	var texts = $("#formObj input[type='text']").get();
	for(var j in texts){
		//特殊处理时间框
		if(texts[j].name=="applyTime"){
			$(texts[j]).val(item[texts[j].name].substring(0,10));
		}else{
			$(texts[j]).val(item[texts[j].name]);
		}
		
	}
	var selects = $("#formObj select").get();
	for(var k in selects){
		if(item[selects[k].name]){
			var vals = item[selects[k].name].split(",");
			for(var n in vals){
				 $(selects[k]).find("option[value='"+vals[n]+"']").attr("selected", true); 
			}
		}
	}
}

//显示流程信息
function showDataFlow(item){
	if(item.applyName!=null){
		$("#splc").append(
				'<li class="ad_mind">'
					+'<div class="add_fir">'
						+'<img src="images/user.png" />'
							+'<span class="add_distance">'+item.applyName+'</span>'
							+'<span class="add_distance">发起申请</span>'
					+'</div>'
					+'<div class="add_sen">'+item.applyTime.substring(0,16).replace(/\-/g,'.')+'</div>'
					+'<div class="add_third">'
						+'<span class="add_third2"></span>'
					+'</div>'
				+'</li>');
	}
	if(item.departmentMan!=null){
		$("#splc").append(
				'<li>'
					+'<div class="add_fir">'
						+'<img src="images/user.png" />'
							+'<span class="add_distance">'+item.departmentMan+'</span>'
							+'<span class="add_distance">部门审核</span>'
					+'</div>'
					+'<div class="add_sen">'+item.departmentManDate.substring(0,16).replace(/\-/g,'.')+'</div>'
					+'<div class="add_third">'
						+'<span class="add_third2">'+(item.status=="NO_PASS"?"不同意":"同意")+'</span>'
					+'</div>'
				+'</li>');
	}
	if(item.zlshman!=null){
		$("#splc").append(
				'<li>'
					+'<div class="add_fir">'
						+'<img src="images/user.png" />'
						+'<span class="add_distance">'+item.zlshman+'</span>'
						+'<span class="add_distance">质量监管部审核</span>'
					+'</div>'
					+'<div class="add_sen">'+item.zlshtime.substring(0,16).replace(/\-/g,'.')+'</div>'
						+'<div class="add_third">'
						+'<span class="add_third2">'+(item.status=="NO_PASS"?"不同意":"同意")+'</span>'
					+'</div>'
				+'</li>');
	}
	if(item.jyrjfzr!=null){
		$("#splc").append(
				'<li>'
					+'<div class="add_fir">'
						+'<img src="images/user.png" />'
						+'<span class="add_distance">'+item.jyrjfzr+'</span>'
						+'<span class="add_distance">检验软件负责人确认</span>'
					+'</div>'
					+'<div class="add_sen">'+item.jyrjfzrDate.substring(0,16).replace(/\-/g,'.')+'</div>'
						+'<div class="add_third">'
						+'<span class="add_third2">'+(item.status=="NO_PASS"?"不同意":"同意")+'</span>'
					+'</div>'
				+'</li>');
	}
	if(item.ywfwbjbr!=null){
		$("#splc").append(
				'<li>'
					+'<div class="add_fir">'
						+'<img src="images/user.png" />'
						+'<span class="add_distance">'+item.ywfwbjbr+'</span>'
						+'<span class="add_distance">业务服务部确认</span>'
					+'</div>'
					+'<div class="add_sen">'+item.ywfwbjbrDate.substring(0,16).replace(/\-/g,'.')+'</div>'
						+'<div class="add_third">'
						+'<span class="add_third2">'+(item.status=="NO_PASS"?"不同意":"同意")+'</span>'
					+'</div>'
				+'</li>');
	}
}


//比较日期大小  
function compareDate(checkStartDate, checkEndDate) {
	var arys1 = new Array();
	var arys2 = new Array();
	if (checkStartDate != null && checkEndDate != null) {
		arys1 = checkStartDate.split('-');
		var sdate = new Date(arys1[0], parseInt(arys1[1] - 1), arys1[2]);
		arys2 = checkEndDate.split('-');
		var edate = new Date(arys2[0], parseInt(arys2[1] - 1), arys2[2]);
		if (sdate > edate) {
			return false;
		} else {
			return true;
		}
	}
}