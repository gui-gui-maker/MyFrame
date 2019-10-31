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
    	<script type="text/javascript" src="pub/bpm/js/util.js"></script>
    <title></title>
    <script type="text/javascript">
    	$(function(){
    		$("#tbar").ligerToolBar({
    			items: [
    				{disabled:true,id:'detail',text: "详情", icon: "detail",click:detail}
    				//{id:'add',text: "新增", icon: "add",click:add},
    				/*{disabled:true,id:'modify',text: "修改", icon: "modify",click:modify},
    				{disabled:true,id:'del',text: "删除", icon: "delete",click:del},"-",
    				{disabled:true,id:'availableRoom',text: "使用记录", icon: "search",click:availableRoom} */
    				]
    		});
    		getData();
    	});
        function getData() {
    		$("#tbar").ligerToolBar().setEnabled({detail: false, modify: false, del: false, add: true,availableRoom:false});
    		var data = {page: 1, pagesize: 100, start: 0,defaultSearchCondition: "[]"};
            $.post("qm?_method=q&pageid=TJY2_EQUIPMENT_BOX",data,parseData,'json');
        	//alert(data);
        }
        var index = 0;
        function parseData(data) {
            $("#content div").animate({opacity: 'hide'}, "slow");
            $("#content").empty();
           // alert(data.rows.length);
            if (data.rows.length > 0) {
                index = data.rows.length;
                for (var j in data.rows) {
                	var src = "app/equipment/base/default.png";
                	 $("#content").append("<div class='tt' id='div" + j + "' style='display:none;cursor:pointer' onClick='getRoomInfo(this)'>" +
                             "<table>" +
                             "<tr><td rowspan='5'><input type='hidden' name='id' value='"+data.rows[j].id+"'>"+
	 	                            "<input type='hidden' name='boxNumber' value='"+data.rows[j].box_number+"'>"+
	 	                            "<input type='hidden' name='templateName' value='"+data.rows[j].template_name+"'>"+
	 	                            "<img src ='"+src+"' width='160' height='130'/></td>"+
 	                            "<td>&nbsp;&nbsp;设备箱编号："+data.rows[j].box_number+"</td></tr>"+
 	                            
                             "<tr><td>&nbsp;&nbsp;报告类型："+data.rows[j].template_name+"</td></tr>"+
                             "<tr><td>&nbsp;管&nbsp;理&nbsp;部&nbsp;门："+data.rows[j].box_dep_name+"</td></tr></table></div>");
                }
                animate(0);
            }else{
            	$("#content").html("暂无任何设备箱！");
            }
        }
        function animate(i) {
            if (i < index) {
                $("#div" + i).animate({opacity: 'show'}, "normal", function () {
                    animate(++i);
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
    			}, content : 'url:app/equipment/base/equipment_box_detail.jsp?status=' + status + '&id=' + selectedId
    		});
    	}
    	function add(item) {
    		var width = 700;
    		var height = 400;
    		var windows = top.$.dialog({
    			width : width, height : height, lock : true, title : "新增", data : {
    				"window" : window
    			}, content : 'url:app/equipment/base/equipment_box_detail.jsp?status=add'

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
    		var width = 900;
    		var height = 600;
    		var windows = top.$.dialog({
    			width : width, height : height, lock : true, title : "详情", data : {
    				"window" : window
    			}, content : 'url:app/equipment/base/equipment_box_detail.jsp?pageStatus=detail' + '&id=' + selectedId
    		});
    	}
    	
    
    	
    	//
    	function availableRoom(){
    		var roomId= $("#selectedId").val();
    		var url="url:app/equipment/base/equipment_box_detail.jsp?status=add&roomId="+roomId;
    		var width = 850;
    		var height = 480;
    		var windows = top.$.dialog({
    			width : width, height : height, lock : true, title : "设备箱使用情况", data : {"window" : window},
    			content : url
    		});
    	}
    	function getRoomInfo(obj){
    		var selectedId = $(obj).find("input[name='id']").val();
    		var boxNumber = $(obj).find("input[name='boxNumber']").val();
    		$('.tt').filter(function(){
    			$(this).css("background-color","#ededed");
    		})
    		$(obj).css("background-color","#bcc7e9");
    		//改变按钮状态
    		$("#tbar").ligerToolBar().setEnabled({detail: true, modify: true, del: true, add: true,availableRoom:true});
    		$("#selectedId").val(selectedId);
    		$("#boxNumber").val(boxNumber);
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
<input type="hidden" id="selectedId" name="selectedId"/>
<input type="hidden" id="boxNumber" name="boxNumber"/>
<div class="item-tm">
	<div class="div1" id="tbar" style=""></div>
</div>
<div id="content" class="scroll-tm">
</div>
</body>
</html>