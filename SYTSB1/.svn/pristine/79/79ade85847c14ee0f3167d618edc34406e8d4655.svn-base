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
    <title>通用查询示例</title>
    <script type="text/javascript">
    var manager1=null,manager2=null,manager3=null;
    	var ids=null;
    	$(function(){
    		$("#tbar").ligerToolBar({
    			items: [
	    				{id:'detail', text: "详情", icon: "detail", click:detail},"-",
	    				{id:'add', text: "新增", icon: "add", click: add},"-", 
	    				{id:'edit', text: "修改", icon: "modify", click: editBox},"-", 
	    				{id:'del',text: "删除", icon: "delete", click: del}
	    				]
    		});
    		$("#btn1").css({"height":"20px","width":"50px","line-height":"18px"});
   	        $("#btn1").ligerButton({
   	            //icon:"count",
   	            img:"k/kui/images/16/icons/search2.gif",
   	            click: function (){
   	            	var searchParams = [];
   	            	var report_number = $("#report_number").ligerGetTextBoxManager().getValue();
   	            	var identifier = $("#identifier").ligerGetTextBoxManager().getValue();
   	            	var archives_box_id = $("#archives_box_id").ligerGetTextBoxManager().getValue();
   	            	if(report_number!=""){
   	            		//[{label:"",logic:"and",name:"enter_user_id",compare:"=",dataType:"string",value:"402884c4477c9bac01477fe0d188001b"}],
   	            		searchParams.push({label:"",logic:"and",name:"report_number",compare:"like",dataType:"string",value:report_number});
   	            	}
   	            	if(identifier!=""){
   	            		searchParams.push({label:"",logic:"and",name:"identifier",compare:"like",dataType:"string",value:identifier});
   	            	}
   	            	if(archives_box_id!=""){
   	            		searchParams.push({label:"",logic:"and",name:"archives_box_id",compare:"like",dataType:"string",value:archives_box_id});
   	            	}
   	            	
   	            	getData(searchParams);
   	            },
   	            text:"查询"
   	        });
   	     	$("#form0").ligerForm();
    		getData();
    	})
    	function sety() {
	    	var roomName4= $("#roomName4").val();
			str=roomName4.replace(/[,]/g,"','");
			ids="('"+str+"')";
		}
        function getData(searchParams) {
    		$("#tbar").ligerToolBar().setEnabled({detail: false, editBox: false, del: false, add: true,availableRoom:false});
            var data = {page: 1, pagesize: 100, start: 0,defaultSearchCondition: (searchParams?JSON.stringify(searchParams):"[]")};
            $.post("qm?_method=q&pageid=TJY2_ARCHIVES_BOX",data,parseData,'json')
        }
        var index = 0;
        function parseData(data) {
            $("#content div").animate({opacity: 'hide'}, "slow");
            $("#content").empty();
            if (data.rows.length > 0) {
                index = data.rows.length;
                for (var j in data.rows) {
                	var src = "app/archives/dangan.jpg";
                    $("#content").append("<div class='tt' id='div" + j + "' style='display:none;cursor:pointer' onClick='getRoomInfo(this)'>" +
                            "<table>" +
                            "<tr><td rowspan='5'><input type='hidden' name='roomid' value='"
	                            +data.rows[j].id+"'><input type='hidden' name='roomname' value='"
	                            +data.rows[j].identifier+"'><input type='hidden' name='roomname2' value='"
	                            +data.rows[j].identifier2+"'><input type='hidden' name='roomname3' value='"
	                            +data.rows[j].report_number+"'><input type='hidden' name='roomname4' value='"
	                            +data.rows[j].report_number_id+"'><img src ='"
	                            +src+"' width='150' height='130'/></td>"+
	                        "<td>&nbsp;&nbsp;档案盒&nbsp;编&nbsp;号："+data.rows[j].archives_box_id+"</td></tr>"+
	                        "<tr><td>&nbsp;&nbsp;开&nbsp;始&nbsp;编&nbsp;号："+data.rows[j].identifier+"</td></tr>"+
                            "<tr><td>&nbsp;&nbsp;结&nbsp;束&nbsp;编&nbsp;号："+data.rows[j].identifier2+"</td></tr>"+
                            //"<tr><td>&nbsp;&nbsp;管&nbsp;&nbsp;&nbsp;理&nbsp;&nbsp;&nbsp;人："+data.rows[j].manager_name+"</td></tr>"+
                           
                            "<tr><td>&nbsp;&nbsp;缺少的档案数量："+data.rows[j].reportnum+"</td></tr>"+
                            "</table></div>");
                    
                }
                animate(0);
            }else{
            	$("#content").html("暂无任何档案盒！")
            }
        }
        function animate(i) {
            if (i < index) {
                $("#div" + i).animate({opacity: 'show'}, "normal", function () {
                    animate(++i)
                });
            }
        }
    	function add(item) {
			top.$.dialog({
     			width : 700, 
     			height : 400, 
     			lock : true, 
     			title : "新增", 
     			data : {"window" : window}, 
    			content : 'url:app/archives/archives_box_detail.jsp?status=add'
     		}); 
    	}
    	function editBox() {
    		var selectedId= $("#selectedId").val();
    		top.$.dialog({
     			width : 700, 
     			height : 400, 
     			lock : true, 
     			title : "修改", 
     			data : {"window" : window}, 
    			content : 'url:app/archives/archives_box_detail.jsp?status=modify&id='+selectedId
     		}); 
    	}
    	function del() {
    		var selectedId= $("#selectedId").val();
    		$.ligerDialog.confirm(kui.DEL_MSG, function (yes) {
    			if(yes){
    				$.ajax({
    					url:"archives/box/del.do?id="+selectedId,
    					type:"post",
    					async:false,
    					success:function(data){
    						if(data.success){
    							top.$.notice(data.msg,3);
    							getData();
    						}else{
    							$.ligerDialog.error(data.msg);
    						}
    					}
    				});
    			}
    		});
    	}

    	function detail(item) {
    		var selectedId= $("#selectedId").val();
    		var roomName4= $("#roomName4").val();
    		str=roomName4.replace(/[,]/g,"','");
    		ids="('"+str+"')";
    		if (selectedId.length < 1) {
    			$.ligerDialog.alert('请先选择要查看的数据！', "提示");
    			return;
    		}
    		var width = 900;
    		var height = 400;
    		var windows = top.$.dialog({
    			width : width, height : height, lock : true, title : "详情", data : {
    				"window" : window
    			}, content : 'url:app/archives/archives_box_list2.jsp?status=detail' + '&id=' + selectedId
    			+ '&report_number_id=' + ids
    		});
    	}
    	
    	//使用记录
    	function availableRoom(){
    		var roomId= $("#selectedId").val();
    		var url="url:app/office/meeting/availableRoom_list.jsp?status=add&roomId="+roomId;
    		var width = 850;
    		var height = 480;
    		var windows = top.$.dialog({
    			width : width, height : height, lock : true, title : "会议室使用情况", data : {"window" : window},
    			content : url
    		});
    	}
    	function getRoomInfo(obj){
    		var selectedId = $(obj).find("input[name='roomid']").val();
    		var roomname = $(obj).find("input[name='roomname']").val();
    		var roomname2 = $(obj).find("input[name='roomname2']").val();
    		var roomname3 = $(obj).find("input[name='roomname3']").val();
    		var roomname4 = $(obj).find("input[name='roomname4']").val();
    		$('.tt').filter(function(){
    			$(this).css("background-color","#ededed");
    		})
    		$(obj).css("background-color","#bcc7e9");
    		//改变按钮状态
    		$("#tbar").ligerToolBar().setEnabled({detail: true, modify: true, del: true, add: true,availableRoom:true});
    		$("#selectedId").val(selectedId);
    		$("#roomName").val(roomname);
    		$("#roomName2").val(roomname2);
    		$("#roomName3").val(roomname3);
    		$("#roomName4").val(roomname4);
    	}
    </script>
    <style type="text/css">
        .tt {
            float: left;
            background-color: #ededed;
            margin: 5px;
            padding: 5px;
            width: 340px;
            height: 130px;
        }
    </style>
</head>
<body>
	<input type="hidden" id="selectedId"/>
	<input type="hidden" id="roomName"/>
	<input type="hidden" id="roomName2"/>
	<input type="hidden" id="roomName3"/>
	<input type="hidden" id="roomName4"/>
	<div class="item-tm">
			<div class="searchDiv" id="searchDiv" style="height: 40px; line-height: 40px; postion: relative">
				<form id="form0" action="">
					<table border="0" cellpadding="0" cellspacing="0" width="">
		                <tr>
		                    <td style="text-align: right;width:120px;">档案编号</td>
		                    <td width="200px">
		                       <input id="report_number" name="report_number" type="text" ltype="text" value=""/>
		                    </td>
		                    <td style="text-align: right;width:120px;">档案盒开始编号</td>
		                    <td width="200px">
		                       <input id="identifier" name="identifier" type="text" ltype="text" value=""/>
		                    </td>
		                    <td style="text-align: right;width:120px;">档案盒编号</td>
		                    <td width="200px">
		                       <input id="archives_box_id" name="archives_box_id" type="text" ltype="text" value=""/>
		                    </td>
		                    <td align="right" width="200px">
		                        <div id="btn1"></div>
		                    </td>
		                </tr>
		            </table>
				</form>
			</div>
			<div class="div1" id="tbar" style="border-top:1px solid #ccc;"></div>
	</div>
	<div id="content" class="scroll-tm"></div>
</body>
</html>