<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/common/common.js"></script>
<script type="text/javascript">
	$(function() {
		if(!api.data)return;
		console.log(api.data);
	    $.each(api.data,function(){
	    	var activityId = this.id;
	    	var _isInnerFlow = this['innerFlow']=='1' && this["aid"]=="${param.caid}";
	    	var _isChoosePerson = this["chooseRolePerson"]=="1";
	    	var trhtml = "<li onclick='selectInput(this)' data-type='" + (_isInnerFlow||_isChoosePerson?"yes":"no")
	    	+ "' data-val='" + this.id + "'><div class='check'></div><h4>" + this.name + "</h4><p id='" + activityId + "'>";
			var types = this.signature=="1"||this.signature=="2"?"checkbox":"radio";
	    	if(_isInnerFlow){
	    		// 如果可以选择当前环节，即为环节内流转，直接选人
    			trhtml += "<span class='person-chbox person-choose' onclick='chooseMorePerson(this,\"radio\")'>选择人员</span>";
	    	}
	    	else if(_isChoosePerson){
		    	//需要选择角色人员
    			$.each(this.nextPerson,function(){
    				trhtml += "<label class='person-chbox' for='" + activityId + "_" + this.id
    					+ "'><input type='" + types + "' name='roleUser' id='" + activityId 
    					+ "_" + this.id + "' value='" + this.id + "' /><span>" + this.name + "<span></label>";
    			});
    			trhtml += "<span class='person-chbox person-more' onclick='chooseMorePerson(this,\"" + types + "\")'>其他人员</span>";
    		}else if(this.nextPersonNames){
    			trhtml += this.nextPersonNames;
    		}
			trhtml += "</p></li>";
			var li = $(trhtml).appendTo("#nodes");
			li.find("div.check").height(li.height());
	    });
	    if($("#nodes").height() < $("body").height())
	    	$("#nodes").css("margin-top",($("body").height()/2-$("#nodes").height()/2) + "px")
	});

	function selectInput(obj){
		$("#nodes .li-select").removeClass("li-select");
		$(obj).addClass("li-select");//.find(":radio").attr("checked",true);
	}

	function getSelected(){
		var rc = $("#nodes .li-select");
		if(rc.size()==0){
			$.ligerDialog.warn("请选择下一步！");
			return null;
		}else {
			var returnVal = {activityId: rc.attr("data-val")};
			if(rc.attr("data-type")=="yes"){
				returnVal.nextPerson = [];
				var personChs = rc.find(".person-chbox :radio:checked,.person-chbox :checkbox:checked");
				if(personChs.size()==0){
					$.ligerDialog.warn("请选择下一步人员！");
					return null;
				}
				personChs.each(function(){
					returnVal.nextPerson.push({id:$(this).val(),name:$(this).next().text()});
				});
			}
			return returnVal;
		}
	}
	
	function chooseMorePerson(obj,types){
		chooseSystemUser({
			multiple: false,
			callback: function(result){
				$(obj).siblings(".person-plus").remove();
				var pid = $(obj).parent().attr("id");
				for(var i in result.data){
					if($("#" + pid + "_" + result.data[i].id).length>0){
						$("#" + pid + "_" + result.data[i].id).attr("checked",true);
						continue;
					}
					$(obj).before("<label class='person-chbox person-plus' for='" + pid + "_" + result.data[i].id
	    				+ "'><input type='" + types + "' name='roleUser' value='" + result.data[i].id + "' id='" + pid
	    				+ "_" + result.data[i].id + "' checked='checked' /><span>" + result.data[i].name + "<span></label>");
				}
			}
		});
	}
</script>
<style type="text/css">
*{box-sizing:border-box;}
html,body{overflow-y: auto;height:100%;padding:0;margin:0;}
.node-sel-item {margin:0;border:none;border-top:1px solid #dddddd;width:100%;border-collapse:collapse;}
.node-sel-item li{border-bottom:1px solid #dddddd;padding-left:1em;min-height:40px;padding-bottom:5px;}
.node-sel-item li:hover {background-color:#F3F3F3;}
.node-sel-item li h4{line-height:40px;font-size:4mm;}
.node-sel-item li p{line-height:1.8em;font-size:3.5mm;padding-right:50px;}
.node-sel-item li div.check{display:inline-block;width:40px;height:100%;float:right;margin-right:5px;}
.node-sel-item li.li-select div.check{background:url("pub/bpm/image/confirm.png") no-repeat center center;}
.node-sel-item li .person-chbox{display:inline-block;padding:0 5px;background-color:#b8e4fc;border-radius:3px;margin:1px 2px;}
.node-sel-item li .person-more,.node-sel-item li .person-choose{padding-left:20px;background:#f0ad4e url("pub/bpm/image/plus.png") no-repeat 2px center;cursor:default;color:white}
.node-sel-item li .person-choose{background-color:#b8e4fc;color:black;}
.node-sel-item li .person-chbox input{float:left;margin-top:1.5mm;}
.node-sel-item li .person-more:hover{background-color:#ea8e13;}
.node-sel-item li .person-choose:hover{background-color:#66c6f9;}
</style>
</head>
<body>
   <ul class="node-sel-item" id="nodes"></ul>
</body>
</html>
