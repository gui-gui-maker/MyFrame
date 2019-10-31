<%@ page contentType="text/html;charset=UTF-8"%>
<ul class="cbp_tmtimeline_menu">
	<li class="color_alllogs" ><input type="radio" name="viewType"value="all" checked="checked" /><span>所有</span></li>
	<li class="color_modify"><input type="radio" name="viewType" value="color_modify" /><span>编辑</span></li>
	<li class="color_feedback"><input type="radio" name="viewType" value="color_feedback" /><span>反馈</span></li>
	<li class="color_discuss"><input type="radio" name="viewType" value="color_discuss" /><span>评论</span></li>
</ul>
<div>
<ul class="cbp_tmtimeline" id="tasklogs"></ul>
</div>
<script type="text/javascript">
var task_opr_types = {
	0:{name:"创建任务",colorClass:'color_modify'},
	1:{name:"修改任务",colorClass:'color_modify'},
	2:{name:"分解任务",colorClass:'color_modify'},
	3:{name:"进度反馈",colorClass:'color_feedback'},
	4:{name:"变更执行人",colorClass:'color_modify'},
	5:{name:"变更参阅人",colorClass:'color_modify'},
	6:{name:"变更考核人 ",colorClass:'color_modify'},
	7:{name:"终止任务",colorClass:'color_modify'},
	8:{name:"接受任务",colorClass:'color_feedback'},
	9:{name:"转派任务",colorClass:'color_modify'},
	10:{name:"完成任务",colorClass:'color_feedback'},
	11:{name:"任务评论",colorClass:'color_discuss'}
}
function addLogItem(log,pdate,icon){
	var cdate = log.operatorTime.substring(0,10);
	var lhtml = '<li style="display:none"  class="li_' + task_opr_types[log.types].colorClass + '"><div class="cbp_tmtime">' + (cdate==pdate?'<p class="cbp_date_blank">&nbsp;':'<p class="cbp_date">'+cdate) + '</p><p class="cbp_time">' + log.operatorTime.substring(11,16) + 
	'</p><p class="cbp_oper">' + log.operator + '</p></div><div class="cbp_tmicon cbp_tmicon-phone" style="text-align:center;"><img src="' + 
	icon + '" /></div><div class="cbp_tmlabel ' + task_opr_types[log.types].colorClass + '"><h3>' + task_opr_types[log.types].name + 
	'</h3><p class="content">';
	
	if(!$.kh.isNull(log.content)){
		if(log.types==1)
			lhtml += "<ol><li>" + (log.content!=null?log.content.replace(/^=->/,"").replace(/=->/gi,"</li><li>"):"") + "</li></ol></p>"
		else
			lhtml += log.content + "</p>"
	}
	if(log.attachments && log.attachments.length > 0){
		lhtml += "<p class='l-file-preview'>";
		$.each(log.attachments,function(){
			lhtml += "<a href='javascript:viewAttachment(\"" + this.attId + "\",\"" + this.attName + "\")' class='preview-item' title='" + this.attName + 
			"' onclick='viewAttachment(\"" + this.attId + "\",\"" + this.attName + "\")'><img width='48' height='48' src='" + getPreviewImageAddr(this.attId,this.attName,48) + 
			"' /></a>"
		});
		lhtml += "</p>";
	}
	$("#tasklogs").append(lhtml + '</div></li>');
}
</script>