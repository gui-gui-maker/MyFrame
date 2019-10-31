<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%
    String id = request.getParameter("id");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>通用查询</title>
<%@ include file="/k/kui-base-list.jsp"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/worktask/js/worktask.js"></script>
<%CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
User uu = (User)curUser.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
	String uId = SecurityUtil.getSecurityUser().getId();
	%>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
			sp_fields : [
				{name : "SQ_DEPARTMENT", compare : "like"},
				{name : "name", compare : "like"},
				{name:"start_time", compare:">=", value:""},
				{name:"end_time", compare:"<=", value:""},
		        {name : "code", compare : "like"},
				{name : "status", compare : "like"}
		        ],
		tbar:[{
                text: '详情', id: 'detail', icon: 'detail', click: detail
            }, "-", {
                text: '新增', id: 'add', icon: 'add', click: add 
            }, "-", {
                text: '修改', id: 'edit', icon: 'modify', click:modify
            }, "-", {
                text: '删除', id: 'del', icon: 'delete', click:del
            }, "-", {
                text: '提交', id: 'submit', icon: 'submit', click:submit
            }, "-", {
    			text: '撤销', id: 'ret', icon: 'delete', click: ret
    		}
    		<sec:authorize access="hasRole('TJY2_OFFICE_BGSZR')">
        		,"-",{ text:'处理', id:'deal',icon:'dispose', click: deal }
        	</sec:authorize> 
        	
        	,"-",{ text:'流转过程', id:'turnHistory',icon:'follow', click: turnHistory}
    		,"-", {text:'发送通知', id: 'sendNotice', icon: 'outbox', click:sendNotice }
    		, "-", {text:'记录会议 ', id: 'notes', icon: 'take-notes', click:notes}
            ],
		////            //提供以下4个事件
		listeners : {
			rowClick : function(rowData, rowIndex) {
			}, rowDblClick : function(rowData, rowIndex) {
				detail(rowData);
			}, selectionChange : function(rowData, rowIndex) {
				selectionChange();
			}, rowAttrRender : function(rowData, rowid) {
				var fontColor="black";
				//闲置状态
		if(rowData.status == "审核不通过" || rowData.status == "已撤销"){
					fontColor="red";
				}
				if (rowData.status == "未提交" ){
					fontColor="black";
				}
				if (rowData.status == "待审核" || rowData.status == "审核中"){
					fontColor="blue";
				}
				if (rowData.status == "审核通过" && rowData.MEETING_STATUS == "未发送通知"){
					fontColor="#8B008B";
				}if (rowData.status == "审核通过" && rowData.MEETING_STATUS == "已发开会通知"){
					fontColor="green";
				}if (rowData.status == "审核通过" && rowData.MEETING_STATUS == "会议已结束"){
					fontColor="#8E8E8E";
				}
				return "style='color:"+fontColor+"'";
			}, afterQmReady: function() {
				var orgCode = "<sec:authentication property='principal.unit.id' htmlEscape='false' />";
				Qm.setCondition([{name: "unit_id", compare: "llike", value: orgCode}]);
				Qm.searchData();
			}
		}
	};
	//撤销
	function ret(){
		var id = Qm.getValueByName("id");
		var dj_peopleid = Qm.getValueByName("DJ_PEOPPLEID");
		if(dj_peopleid!="<%=userId%>"){
            $.ligerDialog.alert("只能撤销自己的申请！");
            return;
        }
		top.$.dialog({
			width: 600,
			height: 200,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "撤销",
			content: 'url:app/office/meeting/office_meeting_apply_ret.jsp?id=' + id
		});
		
	}
	//提交
	function submit(){
		var id = Qm.getValueByName("id");
		var dj_peopleid = Qm.getValueByName("DJ_PEOPPLEID");
        if(!id){
            $.ligerDialog.alert("请先选择要提交审核的数据！");
            return;
        }
        if(dj_peopleid!="<%=userId%>"){
            $.ligerDialog.alert("只能提交自己的申请！");
            return;
        }
        $.ligerDialog.confirm('是否提交审核？', function (yes){
        	
        if(!yes){return false; }
        
         $("body").mask("提交中...");
         getServiceFlowConfig("TJY2_OFFICE_MEETREQ_FLOW","",function(result,data){
        	 if(result){
                 top.$.ajax({
                     url: "oa/meetingReq/info/subFolws.do?id="+id+"&flowId="+data.id+"&typeCode=TJY2_OFFICE_MEETREQ_FLOW&status="+status,
                     type: "GET",
                     dataType:'json',
                     async: false,
                     success:function (data) {
                         if (data) {
                        	$("body").unmask();
                        	//tel();
                            $.ligerDialog.alert("提交成功！！！");
                            Qm.refreshGrid();
                         }
                     },
                     error:function () {
                    	 $("body").unmask();
                         $.ligerDialog.alert("出错了！请重试！!");
                     }
                 });
            }else{
             $.ligerDialog.alert("出错了！请重试！");
             $("body").unmask();
            }
         });
        });
	}
	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		var up_status = Qm.getValueByName("status");//会议室申请状态
		var meeting_status = Qm.getValueByName("MEETING_STATUS");//会议状态
		var status={};
		if(count==0){
			status={detail:false, edit:false, del:false,submit:false,ret:false,sendNotice:false,notes:false,deal:false,turnHistory:false};
		}else if(count==1){
		    if("会议已结束"==meeting_status){
		    	status={detail:true, edit:false, del:false,submit:false,ret:false,sendNotice:false,notes:false,deal:false,turnHistory:false};
		    }else if("未提交"==up_status){
				status={detail:true, edit:true, del:true,submit:true,ret:false,sendNotice:false,notes:false,deal:false,turnHistory:false};
			}else  if("审核中"==up_status || "待审核"==up_status){
				status={detail:true, edit:false, del:false,submit:false,ret:true,sendNotice:false,notes:false,deal:true,turnHistory:true};
			}else if("审核不通过"==up_status){
				status={detail:true, edit:false, del:true,submit:false,ret:false,sendNotice:false,notes:false,deal:false,turnHistory:true};
			}else if("审核通过"==up_status){
				if("未发送通知"==meeting_status){
					status={detail:true, edit:false, del:false,submit:false,ret:true,sendNotice:true,notes:false,deal:false,turnHistory:true};
				}else if("已发开会通知"==meeting_status){
					status={detail:true, edit:false, del:false,submit:false,ret:false,sendNotice:true,notes:true,deal:false,turnHistory:true};
				}
			}else if("已撤销"==up_status){
				status={detail:true, edit:false, del:true,submit:false,ret:false,sendNotice:false,notes:false,deal:false,turnHistory:true};
			}
		 }else{
			 status={detail:false, edit:false, del:false,submit:false,ret:false,sendNotice:false,notes:false,deal:false,turnHistory:true};
		}
		Qm.setTbar(status);
	
	}  
	function modify(item) {
	  selectedId = Qm.getValuesByName("id");
	  if (selectedId.length < 1) {
			$.ligerDialog.alert('请先选择要修改的事项！', "提示");
			return;
		}
	  var dj_peopleid = Qm.getValueByName("DJ_PEOPPLEID");
		if(dj_peopleid!="<%=userId%>"){
          $.ligerDialog.alert("只能修改自己的申请！");
          return;
      }
		var width = 800;
		var height = 1000;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "修改", data : {
				"window" : window
			}, content : 'url:app/office/meeting/meetingReq_detail.jsp?status=modify&id=' + selectedId
		});
	}
	function add(item) {
		var width = 800;
		var height = 1000;
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "新增", data : {
				"window" : window
			}, content : 'url:app/office/meeting/meetingReq_detail.jsp?status=add'

		});
	}
 
	function notes(){
		var reqId=Qm.getValuesByName("id");
		var reqName=Qm.getValuesByName("name");
		var width = 700;
		var height = 462;
		var url="";
	    var notes_id=Qm.getValuesByName("notes_id");
	    var dj_peopleid = Qm.getValueByName("DJ_PEOPPLEID");
		if(dj_peopleid!="<%=userId%>"){
          $.ligerDialog.alert("只能填写自己的会议记录！");
          return;
      }
		if(notes_id!="" && notes_id!=null){
			url="url:app/office/meeting/meetingNotes_detail.jsp?status=modify&id="+notes_id;
		}
		else{
			url="url:app/office/meeting/meetingNotes_detail.jsp?status=add&reqId="+reqId+"&reqName="+reqName;
		}
		var windows = top.$.dialog({
			width : width, height : height, lock : true, title : "新增会议记录", data : {
				"window" : window
			}, content : url
		});
	}
	
	function del() {
		var selectedId = Qm.getValuesByName("id");
		var dj_peopleid = Qm.getValueByName("DJ_PEOPPLEID");
		if (selectedId.length < 1) {
			$.ligerDialog.alert('请先选择要删除的事项！', "提示");
			return;
		}
		if(dj_peopleid!="<%=userId%>"){
	          $.ligerDialog.alert("只能删除自己的申请！");
	          return;
	      }
		$.ligerDialog.confirm(kui.DEL_MSG, function (yes) {
			if(yes){
				$.ajax({
					url:"oa/meetingReq/info/deletes.do?ids="+selectedId,
					type:"post",
					async:false,
					success:function(data){
						if(data.success){
							top.$.notice("删除成功！");
							Qm.refreshGrid();
						}
					}
				});
			}
		});
	}

	function detail(item) {
		var selectedId = item.id;
		if (selectedId == "detail") {
			selectedId = Qm.getValuesByName("id");
			 notes_id=Qm.getValuesByName("notes_id");
		} else {
			selectedId = item.id;
			 notes_id=Qm.getValuesByName("notes_id");
		}
	   
		var width = 800;
		var height = 1000;
		var windows = top.$.dialog({
			width : width, 
			height : height, 
			lock : true, 
			title : "详情", 
			data : {
				"window" : window
			},
			 content : 'url:app/office/meeting/meetingReq_detail.jsp?status=detail' + '&id=' + selectedId+'&notes_id='+notes_id+'&a='+"a"
		});
	}
	
	function sendNotice(){
		var width = 300;
		var height = 100;
		var selectId=Qm.getValuesByName("id");
		var dj_peopleid = Qm.getValueByName("DJ_PEOPPLEID");
		if(dj_peopleid!="<%=userId%>"){
          $.ligerDialog.alert("只能发送自己申请的通知！");
          return;
      	}else if (selectId.length < 1) {
    			$.ligerDialog.alert('请先选择要发送通知的事项！', "提示");
    			return;
    	}else{
    		var windows = top.$.dialog({
    			width : width,
    		    height : height,
    		     lock : true, 
    		     parent : api,
    		     title : "发送通知",
    		     data : {
    				"window" : window
    			}, content : 'url:app/office/meeting/checkOut_audit.jsp?status=add&reqId='+selectId
    		});
    	}
	}
	
	function selectReq() {
		var title = "会议选择";
		var url = 'url:app/office/meeting/checkOut_audit.jsp?status=add&reqId='+selectId
		var width = 700;
		top.$.dialog({
			width : 300,
			height : 100,
			lock : true,
			parent : api,
			id : "",
			title : title,
			content : url,
			cancel : false,
			ok : function() {
				var datas = this.iframe.contentWindow.getSelectResult();
				if (datas) {
					var reqId=datas.reqid;
					//取参会人员及单位信息及会议基本信息
					getMeetingReqInfo(reqId);
					return true;
				} else {
					return false;
				}
			}
		});
	}
	//处理
    function deal() {
    	var list;
    	var id;
    	var title;
    	var service_id = Qm.getValueByName("id");
    	$.ajax({
        	url: "equipMaintainAction/queryMainId.do?id="+service_id,
            type: "POST",
            success: function (resp) {
            	if(resp.success){
            		list = resp.list;
            		id=list[0].id;
        	        title=list[0].title;
        	        //alert(title);
            		var config={
                	        width :800,
                	        height : 630,
                	        id:id,
                	        title:title
                	    }
            		// 调用流程的方法
               	    openWorktask(config);
            	}else{
            		$.ligerDialog.error('没有流程数据！');
            	}
            },
            error: function (data,stats) {
                $.ligerDialog.error('提示：' + data.msg);
            }
        });
     }
    function progress(){
    	var process_id;
    	var service_id = Qm.getValueByName("id");
    	trackServiceProcess(service_id);
    }
 	// 流转过程
	function turnHistory(){	
		top.$.dialog({
   			width : 400, 
   			height : 700, 
   			lock : true, 
   			title: "流程卡",
   			content: 'url:oa/meetingReq/info/getFlowStep.do?meeting_req_id='+Qm.getValueByName("id"),
   			data : {"window" : window}
   		});
	}
</script>
</head>
<body>
<!-- 提示文字 -->
    <div class="item-tm" id="titleElement">
	    <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="black">“黑色”</font>代表未提交，
				<font color="blue">“蓝色”</font>代表待审核或审核中，
				<font color="#8B008B">“紫色”</font>代表审核通过未发送通知，
				<font color="green">“绿色”</font>代表审核通过且已发送通知，
				<font color="red">“红色”</font>代表审核不通过或已撤销，
				<font color="#8E8E8E">“灰色”</font>代表会议已结束。
			</div>
		</div>
	</div>
	<qm:qm pageid="TJY2_OFFICE_MEET_REQ" seachOnload="false" script="false" singleSelect="true">
	</qm:qm>
</body>
</html>