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
    	$(function(){
    		$("#tbar").ligerToolBar({
    			items: [
    				"-",
    				{disabled:true,id:'detail',text: "详情", icon: "detail",click:detail},"-",
    				{id:'add',text: "新增", icon: "add",click:add},"-",
    				{disabled:true,id:'modify',text: "修改", icon: "modify",click:modify},"-",
    				{disabled:true,id:'del',text: "删除", icon: "delete",click:del}
    				]
    		});
    		getData();
    	})
        function getData() {
    		$("#tbar").ligerToolBar().setEnabled({detail: false, modify: false, del: false, add: true});
            var data = {page: 1, pagesize: 100, start: 0,defaultSearchCondition: "[{name:'org_code',compare:'=',value:'<%=orgId%>'}]"};
            $.post("qm?_method=q&pageid=oa_carinfo",data,parseData,'json')
        }
        var index = 0;
        function parseData(data) {
            $("#content div").animate({opacity: 'hide'}, "slow");
            $("#content").empty();
            if (data.rows.length > 0) {
                index = data.rows.length;
                for (var j in data.rows) {
                	var src = "app/oa/car/default.png";
                	if(data.rows[j].carpic_id!=''&&data.rows[j].carpic_id!=null&&data.rows[j].carpic_id!=undefined){
                		src = "fileupload/downloadByObjId.do?id="+data.rows[j].carpic_id
                	}
                    $("#content").append("<div class='tt' id='div" + j + "' style='display:none;cursor:pointer' onClick='getCarInfo(this)'>" +
                            "<table>" +
                            "<tr><td rowspan='5'><input type='hidden' value='"+data.rows[j].id+"'><img src ='"+src+"' width='160' height='130'/></td><td>&nbsp;&nbsp;车&nbsp;牌&nbsp;&nbsp;号："+data.rows[j].car_num+"</td></tr>"+
                            "<tr><td>&nbsp;&nbsp;车辆品牌："+data.rows[j].car_brand+"</td></tr>"+
                            "<tr><td>&nbsp;&nbsp;车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型："+data.rows[j].car_type+"</td></tr>"+
                            "<tr><td>&nbsp;&nbsp;购车日期："+data.rows[j].buy_date+"</td></tr>"+
                            "<tr><td>&nbsp;&nbsp;所属部门："+data.rows[j].manager_room_name+"</td></tr>"+
                            "</table></div>");
                    
                }
                animate(0);
            }else{
            	$("#content").html("暂无任何车辆信息！")
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
        	var selectedId = $("#selectedId").val();
    		if (selectedId==''||selectedId==undefined||selectedId==null) {
    			$.ligerDialog.alert('请先选择要修改的数据！', "提示");
    			return;
    		}
    		var width = 868;
    		var height = 635;
    		var windows = top.$.dialog({
    			width : width, height : height, lock : true, title : "编辑", data : {
    				"window" : window
    			}, content : 'url:app/oa/car/carInfo_detail.jsp?status=modify&id=' + selectedId
    		});
    	}
    	function add(item) {
    		var width = 868;
    		var height = 635;
    		var windows = top.$.dialog({
    			width : width, height : height, lock : true, title : "新增", data : {
    				"window" : window
    			}, content : 'url:app/oa/car/carInfo_detail.jsp?status=add'

    		});
    	}

    	function del() {
    		var selectedId = $("#selectedId").val();
    		if (selectedId.length < 1) {
    			$.ligerDialog.alert('请先选择要删除的事项！', "提示");
    			return;
    		}
    		$.ligerDialog.confirm(kui.DEL_MSG, function (yes) {
    			if(yes){
    				$.ajax({
    					url:"oa/car/info/delete.do?ids="+selectedId,
    					type:"post",
    					async:false,
    					success:function(data){
    						if(data.success){
    							top.$.notice("删除成功！");
    							getData();
    						}
    					}
    				});
    			}
    		});
    	}

    	function detail(item) {
    		var selectedId = $("#selectedId").val();
    		var width = 700;
    		var height = 511;
    		var windows = top.$.dialog({
    			width : width, height : height, lock : true, title : "详情", data : {
    				"window" : window
    			}, content : 'url:app/oa/car/carInfo_detail.jsp?status=detail' + '&id=' + selectedId
    		});
    	}
    	function getCarInfo(obj){
    		var selectedId = $(obj).find("input[type='hidden']").val();
    		$('.tt').filter(function(){
    			$(this).css("background-color","#ededed");
    		})
    		$(obj).css("background-color","#bcc7e9");
    		//改变按钮状态
    		$("#tbar").ligerToolBar().setEnabled({detail: true, modify: true, del: true, add: true});
    		$("#selectedId").val(selectedId);
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
<div class="item-tm">
	<div class="div1" id="tbar" style=""></div>
</div>
<div id="content" class="scroll-tm">
</div>
</body>
</html>