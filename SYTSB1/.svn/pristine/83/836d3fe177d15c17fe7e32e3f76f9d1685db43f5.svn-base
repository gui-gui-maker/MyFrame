<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>任务管理</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<link type="text/css" rel="stylesheet" href="app/task/css/tl_component.css" />
<link type="text/css" rel="stylesheet" href="k/kui/skins/default/css/ligerui-basic.css" />
<script type="text/javascript" src="k/kui/frame/jquery.min.js"></script>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript">
	$(function(){
		$.ajaxSetup({
			cache: false
		});
		$(window).data("pdd","");
		$.getJSON("oa/task/log/holdersIcon.do",function(resp){
			if(resp.success) window["userIcons"] = resp.data;
			query(1);
			bindScrollEvent();
		});	
		$(".cbp_tmtimeline_menu li").click(function(){
			$(this).find(":radio").attr("checked","checked");
			$("#tasklogs").empty();
			query(1);
		});
		$(".next_page_btn").show();
	});
	var isLoading = false;
	function bindScrollEvent(){
		$(window).on("scroll",function(event){
			var bodyEl = $("body").get(0);
			var h = bodyEl.scrollHeight - bodyEl.scrollTop;
			if(h <= $(window).height() + 50){
				if(isLoading==false){
					isLoading = true;
					loadMore();
				}
			}
		});
	}
	
	function loadMore(){
		var pager = $(window).data("pager");
		if(pager.page == pager.totalPage){
			return;
		}
		query(pager.page + 1);
	}
	
	function query(pageNo){
		var c = getLogsTypes();
		$(".next_page_btn").html("<a>加载中...</a>");
		$.getJSON("oa/task/log/holdersLog.do?page="+pageNo,{
			page : pageNo,
			isSelf : c == "self",
			types : c
		},function(r){
			if(r.success){
				$(window).data("pager",{page:pageNo,total:r.data.totalCount,pageSize:50,totalPage:r.data.totalPageCount});
				if(r.data.totalCount==0){
					$(".next_page_btn").html("<div class='no-data-info'>您还没有参与过任何任务！您可以从这里<button class='new-task-btn' onclick='newTask()'>添加任务</button></div>");
					$(".cbp_tmtimeline_menu").hide();
					return;
				}else{
					$(".cbp_tmtimeline_menu").show();
				}
				for(var i in r.data.data){
					addLogItem(r.data.data[i],$(window).data("pdd"));
					$(window).data("pdd",r.data.data[i].operatorTime.substring(0,10));
				}
				$("#tasklogs li").show(500);
				if(pageNo >= r.data.totalPageCount){
					$(".next_page_btn").html("<div class='no-data-info'>加载完成，总共" + r.data.totalCount + "个任务日志！</div>");
				}
				else{
					$(".next_page_btn").html("<a href='javascript:loadMore()'>加载更多</a>");
				}
				isLoading = false;
			}else{
				alert("获取数据失败！");
			}
		});
	}

	var types_val_map = {
		"color_modify": "0,1,2,4,5,6,7,9",
		"color_feedback": "3,10",
		"color_discuss": "11"
	};
	function getLogsTypes(){
		var types = $(".cbp_tmtimeline_menu li :radio:checked").val();
		if(types == "all")
			return "";
		else if(types == "mine")
			return "self";
		else return types_val_map[types];
	}
	
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
	function addLogItem(log,pdate){
		var cdate = log.operatorTime.substring(0,10);
		var licon = window.userIcons[log.operatorId];
		if(!licon) licon = "k/kui/images/head/default.gif";
		var lhtml = '<li oper="' + log.operatorId + '" style="display:none" class="li_' + task_opr_types[log.types].colorClass + '"><div class="cbp_tmtime">' + (cdate==pdate?'<p class="cbp_date_blank">':'<p class="cbp_date">'+ cdate) + '</p><p class="cbp_time">' + log.operatorTime.substring(11,16) + 
		'</p><p class="cbp_oper">' + log.operator + '</p></div><div class="cbp_tmicon cbp_tmicon-phone" style="text-align:center;"><img src="' + licon + 
		'" /></div><div class="cbp_tmlabel ' + task_opr_types[log.types].colorClass + '"><h3>' + task_opr_types[log.types].name + 
		'——【<a href="javascript:viewTaskDetail(\'' + log.taskId + '\')">' + log.taskName + '</a>】</h3>';
		
		var isEmptyContent = true;
		if(log.content){
			isEmptyContent = false;
			if(log.types==1)
				lhtml += "<ol><li>" + (log.content!=null?log.content.replace(/^=->/,"").replace(/=->/gi,"</li><li>"):"") + "</li></ol>";
			else
				lhtml +=  "<p>" + log.content + "</p>";
		}
		if(log.attachments && log.attachments.length > 0){
			isEmptyContent = false;
			lhtml += "<p class='l-file-preview'>";
			$.each(log.attachments,function(){
				lhtml += "<a class='preview-item' href='javascript:viewAttachment(\"" + this.attId + "\",\"" + this.attName + "\")' title='" + this.attName + 
						"'><img src='" + getPreviewImageAddr(this.attId,this.attName,48) + "' width='48' height='48' /></a>";
			});
			lhtml += "</p>";
		}
		
		$("#tasklogs").append(lhtml + (isEmptyContent?"<p></p>":"") +'</div></li>');
	}
	
	function newTask(){
		top.$.dialog({
			width: 900,
			height: $(top).height()>550?550:$(top).height(),
			lock: true,
			title: "新增任务",
			content: 'url:app/task/edit_task.jsp?status=add&pid=',
			data: {Qm:{refreshGrid:function(){location.reload();}},ptitle:""}
		});
	}
	
	function viewTaskDetail(id){
		top.$.dialog({
			width: 1000,
			height: $(top).height()-100,
			lock: true,
			data:{Qm:{refreshGrid:function(){
			}}},
			title: "查看任务",
			content: 'url:app/task/task_detail.jsp?status=detail&id=' + id
		});
	}
</script>
<style type="text/css">
body,p,div,span{
	font-size: 12px;
	font-family: "微软雅黑","宋体";
}
.cbp_tmtimeline_menu{
	top:0;
	padding: 10px;
	margin:0;
	width:100%;
	border-bottom: 1px solid #E7E7E7;
	background-color:#F8F8F8;
	display: none;
}
.next_page_btn{
	height: 30px;
	text-align: center;
	padding: 10px;
	display: none;
	margin-bottom: 2em;
}
.next_page_btn a, .no-data-info {
	text-decoration: none;
	display: inline-block;
	padding: 10px;
	width: 350px;
	color: #fff;
	background-color: #449d44;
	border-color: #398439;
	border-radius: 4px;
}
.no-data-info{width:500px;display:block;margin-left: auto;margin-right: auto;}
.next_page_btn a:HOVER {
	background-color: #398439;
}
.new-task-btn{
	color: #fff;
	background-color: #c9302c;
	border: 1px solid #ac2925;
	border-radius: 4px;
	padding: 3px 5px;
}
.color_mine{background-color: #99CC99}
.cbp_tmtimeline li a{text-decoration:none;}
</style>
</head>
<body style="overflow:auto;padding:0;margin:0">
<ul class="cbp_tmtimeline_menu">
	<li class="color_alllogs"><input type="radio" name="viewType" value="all" checked="checked" /><span>所有</span></li>
	<li class="color_mine"><input type="radio" name="viewType" value="mine" /><span>我的</span></li>
	<li class="color_modify"><input type="radio" name="viewType" value="color_modify" /><span>编辑</span></li>
	<li class="color_feedback"><input type="radio" name="viewType" value="color_feedback" /><span>反馈</span></li>
	<li class="color_discuss"><input type="radio" name="viewType" value="color_discuss" /><span>评论</span></li>
</ul>
<ul class="cbp_tmtimeline" id="tasklogs" style="margin-top:5em;"></ul>
<div class="next_page_btn">
	<a href="javascript:loadMore()">加载中...</a>
</div>
</body>
</html>