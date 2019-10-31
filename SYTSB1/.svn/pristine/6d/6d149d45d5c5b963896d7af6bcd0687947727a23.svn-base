<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String orgId = user.getUnit().getId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
    <title>通用查询示例</title>
    <script type="text/javascript">
    	$(function(){
    		$("#tbar").ligerToolBar({
    			items: [
    				"-",
    				{disabled:true,id:'detail',text: "详情", icon: "detail",click:detail},"-",
    				{id:'add',text: "新增", icon: "add",click:add},
    				{disabled:true,id:'modify',text: "修改", icon: "modify",click:modify},
    				{disabled:true,id:'del',text: "删除", icon: "delete",click:del},"-",
    				{disabled:true,id:'useReq',text: "申请使用", icon: "accept",click:useReq},"-",
    				{disabled:true,id:'availableRoom',text: "使用记录", icon: "search",click:availableRoom}
    				]
    		});
    		getData();
    	})
        function getData() {
    		$("#tbar").ligerToolBar().setEnabled({detail: false, modify: false, del: false, add: true,useReq:false,availableRoom:false});
            var data = {page: 1, pagesize: 100, start: 0,defaultSearchCondition: "[{name:'creator_unit_id',compare:'=',value:'<%=orgId%>'}]"};
            $.post("qm?_method=q&pageid=TJY2_OFFICE_MROOM",data,parseData,'json')
        }
        var index = 0;
        function parseData(data) {
            $("#content div").animate({opacity: 'hide'}, "slow");
            $("#content").empty();
            if (data.rows.length > 0) {
                index = data.rows.length;
                for (var j in data.rows) {
                	var src = "app/office/meeting/default.png";
                	if(data.rows[j].roompic_id!=''&&data.rows[j].roompic_id!=null&&data.rows[j].roompic_id!="undefined"){
                		/* src = "fileupload/downloadByObjId.do?id="+data.rows[j].roompic_id; */
                		var roompicIds = data.rows[j].roompic_id.split(",");
                		src = "fileupload/previewImage.do?id="+roompicIds[0];
                	}
                    $("#content").append("<div class='tt' id='div" + j + "' style='display:none;cursor:pointer' onClick='getRoomInfo(this)'>" +
                            "<table>" +
                            "<tr><td rowspan='5'><input type='hidden' name='roomid' value='"+data.rows[j].id+
                            "'><input type='hidden' name='roomname' value='"+data.rows[j].code+
                            "'><input type='hidden' name='hyadress' value='"+data.rows[j].place+
                            "'><img src ='"+src+"' width='160' height='130'/></td><td>&nbsp;&nbsp;会议室名称："+data.rows[j].code+
                            "</td></tr>"+
                            "<tr><td>&nbsp;&nbsp;管&nbsp;&nbsp;理&nbsp;人："+data.rows[j].manager_name+"</td></tr>"+
                            "<tr><td>&nbsp;&nbsp;联系电话："+data.rows[j].manager_phone+"</td></tr>"+
                           
                            "<tr><td>&nbsp;&nbsp;所在地点："+data.rows[j].place+"</td></tr>"+
                            "</table></div>");
                    
                }
                animate(0);
            }else{
            	$("#content").html("暂无任何会议室！")
            }
        }
        function animate(i) {
            if (i < index) {
                $("#div" + i).animate({opacity: 'show'}, "normal", function () {
                    animate(++i)
                });
            }
        }
        function modify(item) {
    		var status = "modify";
    		var selectedId= $("#selectedId").val();
    		if (selectedId.length < 1) {
    			$.ligerDialog.alert('请先选择要修改的数据！', "提示");
    			return;
    		}
    		var width = 700;
    		var height = 400;
    		var windows = top.$.dialog({
    			width : width, height : height, lock : true, title : "修改", data : {
    				"window" : window
    			}, content : 'url:app/office/meeting/meetingRoom_detail.jsp?status=' + status + '&id=' + selectedId
    		});
    	}
    	function add(item) {
    		var width = 700;
    		var height = 400;
    		var windows = top.$.dialog({
    			width : width, height : height, lock : true, title : "新增", data : {
    				"window" : window
    			}, content : 'url:app/office/meeting/meetingRoom_detail.jsp?status=add'

    		});
    	}

    	function del() {
    		var selectedId= $("#selectedId").val();
    		if (selectedId.length < 1) {
    			$.ligerDialog.alert('请先选择要删除的事项！', "提示");
    			return;
    		}
    		$.ligerDialog.confirm(kui.DEL_MSG, function (yes) {
    			if(yes){
    				$.ajax({
    					url:"oa/meetingRoom/info/delMeetingRoom.do?ids="+selectedId,
    					type:"post",
    					async:false,
    					success:function(data){
    						if(data.success){
    							if(data.data.msg!=null){
    								$.ligerDialog.alert(data.data.msg, "提示");
    							}
    							else{
    								top.$.notice("删除成功！");
    							}
    							getData();
    						}
    					}
    				});
    			}
    		});
    	}

    	function detail(item) {
    		var selectedId= $("#selectedId").val();
    		if (selectedId.length < 1) {
    			$.ligerDialog.alert('请先选择要查看的数据！', "提示");
    			return;
    		}
    		var width = 700;
    		var height = 400;
    		var windows = top.$.dialog({
    			width : width, height : height, lock : true, title : "详情", data : {
    				"window" : window
    			}, content : 'url:app/office/meeting/meetingRoom_detail.jsp?status=detail' + '&id=' + selectedId
    		});
    	}
    	
    	function useReq(){
    		var roomId=$("#selectedId").val();
    		var roomName=$("#roomName").val();
    		var hyadress=$("#hyadress").val();
    		var url="url:app/office/meeting/meetingReq_detail.jsp?status=add&roomId="+roomId;
    		var width = 800;
    		var height = 1000;
    		var windows = top.$.dialog({
    			width : width, height : height, lock : true, title : "会议室使用申请", data : {"window" : window,"roomName":roomName,"hyadress":hyadress},
    			content : url
    		});
    	}
    	
    	//会议室使用记录
    	function availableRoom(){
    		var roomId=$("#selectedId").val();
    		var roomName=encodeURI(encodeURI($("#roomName").val()));
    		var hyadress=encodeURI(encodeURI($("#hyadress").val()));
    		var url="url:app/office/meeting/availableRoom_list.jsp?status=add&roomId="+roomId+"&roomName="+roomName+"&hyadress="+hyadress;
    		var width = 850;
    		var height = 480;
    		var windows = top.$.dialog({
    			width : width, height : height, lock : true, title : "会议室使用记录", data : {"window" : window},
    			content : url
    		});
    	}
    	function getRoomInfo(obj){
    		var selectedId = $(obj).find("input[name='roomid']").val();
    		var roomname = $(obj).find("input[name='roomname']").val();
    		var hyadress = $(obj).find("input[name='hyadress']").val();
    		$('.tt').filter(function(){
    			$(this).css("background-color","#ededed");
    		})
    		$(obj).css("background-color","#bcc7e9");
    		//改变按钮状态
    		$("#tbar").ligerToolBar().setEnabled({detail: true, modify: true, del: true, add: true,useReq:true,availableRoom:true});
    		$("#selectedId").val(selectedId);
    		$("#roomName").val(roomname);
    		$("#hyadress").val(hyadress);
    	}
    </script>
    <style type="text/css">
        .tt {
            float: left;
            background-color: #ededed;
            margin: 5px;
            padding: 5px;
            width: 350px;
            height: 130px;
        }
    </style>
</head>
<body>
<input type="hidden" id="selectedId"/>
<input type="hidden" id="roomName"/>
<input type="hidden" id="hyadress"/>
<div class="item-tm">
	<div class="div1" id="tbar" style=""></div>
</div>
<div id="content" class="scroll-tm">
</div>
</body>
</html>