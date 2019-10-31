<%@page import="com.khnt.rbac.impl.bean.Org"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>通用查询</title>
<%@ include file="/k/kui-base-list.jsp"%>
<%
	String type = request.getParameter("type");
	String state=request.getParameter("state");
	String unitId = SecurityUtil.getSecurityUser().getUnit().getId();
	Org org = (Org)SecurityUtil.getSecurityUser().getDepartment();
%>
<script type="text/javascript">
    var state="<%=state%>";
	$(function(){
		<%if ("apply".equals(type)) {%>
			$("#tbar").ligerToolBar({
				items: [
					"-",
					{id:'detail',text: "查看使用情况", icon: "detail",click:detail}
					]
			});
		<%}%>
		getData();
	})
	function detail() {
		var title = "查看车辆使用情况";
		var windows = top.$.dialog({
			width : 750, height : 500, lock : true, title : title, parent : api, cancel : true,
			data : {
				"window" : window
			}, content : 'url:app/oa/car/seeCarUsed_list.jsp?id=' + Qm.getValuesByName("id")
		});
	}
	function getData() {
		var levelcode = "";
		<sec:authorize access="hasRole('oa_car_info')">
			levelcode = "[{name:'level_code',compare:'like',value:''}]";
		</sec:authorize>
		<sec:authorize ifNotGranted="oa_car_info">
			levelcode = "[{name:'level_code',compare:'like',value:'<%=org.getLevelCode()%>',logic:'and'}]";
			<%
				if(StringUtil.isNotEmpty(state)){
					%>
					levelcode = "[{name:'level_code',compare:'like',value:'<%=org.getLevelCode()%>',logic:'and'},{name:'state',compare:'=',value:'"+state+"'',logic:'and'}]";
					<%
				}
			%>
		</sec:authorize>
        //var data = {page: 1, pagesize: 100, start: 0,defaultSearchCondition: "[{name:'org_code',compare:'=',value:'<%=unitId%>'}]",searchPara:levelcode};
        var data = {page: 1, pagesize: 100, start: 0,defaultSearchCondition: "",searchPara:''};
        $.post("qm?_method=q&pageid=oa_selcar",data,parseData,'json')
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
            		/* src = "fileupload/downloadByObjId.do?id="+data.rows[j].carpic_id */
            		src = "fileupload/previewImage.do?id="+data.rows[j].carpic_id
            	}
                $("#content").append("<div class='tt' id='div" + j + "' style='display:none;cursor:pointer' onClick='getCarInfo(this)'>" +
                        "<table>" +
                        "<tr><td><input type='hidden' name='carid' value='"+data.rows[j].id+"'>"
                        +"<input type='hidden' name='carnum' value='"+data.rows[j].car_num+"'>"
                        +"<input type='hidden' name='driver' value='"+data.rows[j].driver+"'>"
                        +"<input type='hidden' name='carbrand' value='"+data.rows[j].car_brand+"'>"
                        +"<input type='hidden' name='engineNo' value='"+data.rows[j].engine_no+"'>"
                        +"<img src ='"+src+"' width='140' height='120'/></td></tr>"+
                        "<tr><td>&nbsp;&nbsp;车&nbsp;牌&nbsp;&nbsp;号："+data.rows[j].car_num+"</td></tr>"+
                        "<tr><td>&nbsp;&nbsp;车辆品牌："+data.rows[j].car_brand+"</td></tr>"+
                        "</table></div>");
                
            }
            animate(0);
        }else{
        	$("#content").html("暂无任何车辆信息！")
        }
    }
    function animate(i) {
        if (i < index) {
            $("#div" + i).animate({opacity: 'show'}, 0, function () {
                animate(++i)
            });
        }
    }
    function getCarInfo(obj){
		var carid = $(obj).find("input[name='carid']").val();
		var carnum = $(obj).find("input[name='carnum']").val();
		var driver = $(obj).find("input[name='driver']").val();
		var carbrand = $(obj).find("input[name='carbrand']").val();
		var engineNo = $(obj).find("input[name='engineNo']").val();
		$('.tt').filter(function(){
			$(this).css("background-color","#ededed");
		})
		$(obj).css("background-color","#bcc7e9");
		//改变按钮状态
		//$("#tbar").ligerToolBar().setEnabled({detail: true, modify: true, del: true, add: true});
		$("#carid").val(carid);
		$("#carnum").val(carnum);
		$("#driver").val(driver);
		$("#carbrand").val(carbrand);
		$("#engineNo").val(engineNo);
	}
    function detail() {
		var title = "查看车辆使用情况";
		if($("#carid").val()==''||$("#carid").val()==null||$("#carid").val()==undefined){
			$.ligerDialog.warn("请选择车辆");
			return;
		}
		var windows = top.$.dialog({
			width : 750, height : 500, lock : true, title : title, parent : api, cancel : true,
			data : {
				"window" : window
			}, content : 'url:app/oa/car/seeCarUsed_list.jsp?id=' + $("#carid").val()
		});
	}
  //选择结果
	function getSelectResult() {
		var result = {
			carid : "", carnum : "", driver : "", carbrand : "", engineNo : ""
		};
		result.carid = $("#carid").val();
		result.carnum  = $("#carnum").val();
		result.driver = $("#driver").val();
		result.carbrand = $("#carbrand").val();
		result.engineNo = $("#engineNo").val();
		return result;
	}
</script>
 <style type="text/css">
    .tt {
        float: left;
        background-color: #ededed;
        margin: 5px;
        padding: 5px;
        width: 140px;
        height: 150px;
    }
</style>
</head>
<body>
<input type="hidden" id="carid"/>
<input type="hidden" id="carnum"/>
<input type="hidden" id="driver"/>
<input type="hidden" id="carbrand"/>
<input type="hidden" id="engineNo"/>
	<div class="item-tm">
		<div class="div1" id="tbar" style=""></div>
	</div>
	<div id="content" class="scroll-tm">
	</div>
</body>
</html>