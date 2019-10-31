<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>四川省特种设备检验系统专家库</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%=basePath %>" />
<link href="app/expert/css/common.css" rel="stylesheet" type="text/css">

<script src="app/expert/js/jquery.min.1.9.1.js"></script>
<script type="text/javascript" src="app/expert/js/scroll.js"></script>
<style>
	.zj_bot .btns a.get{
		background-color:#ccc;
	}
</style>
</head>
<body>
<div class="s_n_bg"></div>
<div class="n_zj_contain">
    <div class="m_cont">
        <div class="zj_m_tit">四川省特种设备检验系统专家库</div>
        <div class="zj_list">
                <table width="100%" border="0" cellspacing="0" cellpadding="0" class="zj_table">
                            <tr>
                                <td>专家姓名</td>
                                <td>专家分组</td>
                            </tr>
                </table>

                <div class="list_lh">
                 <ul>
                     <!-- <li><p><a href="">张小萌</a></p><p><sapn>计量专家</sapn></p></li>
                     <li><p><a href="">李雷</a></p><p><sapn>特种专家</sapn></p></li>
                     <li><p><a href="">王二</a></p><p><sapn>质量专家</sapn></p></li>
                     <li><p><a href="">赵云</a></p><p><sapn>计量专家</sapn></p></li>
                     <li><p><a href="">张小萌</a></p><p><sapn>计量专家</sapn></p></li>
                     <li><p><a href="">李雷</a></p><p><sapn>特种专家</sapn></p></li>
                     <li><p><a href="">王二</a></p><p><sapn>质量专家</sapn></p></li>
                     <li><p><a href="">赵云</a></p><p><sapn>计量专家</sapn></p></li>
                     <li><p><a href="">张小萌</a></p><p><sapn>计量专家</sapn></p></li>
                     <li><p><a href="">李雷</a></p><p><sapn>特种专家</sapn></p></li>
                     <li><p><a href="">王二</a></p><p><sapn>质量专家</sapn></p></li>
                     <li><p><a href="">赵云</a></p><p><sapn>计量专家</sapn></p></li> -->
                 </ul>
                 </div>
        </div>
    </div>
 <div class="zj_bot">  <div class="btns"><a id="get" href="javascript:void(0)" onclick="SavetoDrafts();" class="btn btn-big btn-ok">随机抽取</a></div>  </div>
</div>

<script type="text/javascript">
var types = <u:dict code='PERSON_TYPE' />;
	function getText(sel,id){
		for(var i=0;i<sel.length;i++){
			if(id == sel[i]['id']){
				return sel[i]['text'];
			}
		}
	}
$(function(){
	$('.list_lh li:even').addClass('lieven');
    $("div.list_lh").myScroll({
        speed:40, //数值越大，速度越慢
        rowHeight:40 //li的高度
    });
    $.post("expertRecordAction/getExpByType.do",{id:'${param.id}'},function(res){
    	if(res.success){
	    	var list= res.data;
    		$("div.list_lh ul").find("li").remove();
	    	for(var i=0;i<list.length;i++){
	    		$("div.list_lh ul").append('<li><p><a href="void(0)">'+list[i][0]+'</a></p><p><sapn>'+getText(types,list[i][1])+'</sapn></p></li>');
	    	}
	    	$('.list_lh li:even').addClass('lieven');
    	}else{
    		alert(res.msg);
    	}
    });
});
function SavetoDrafts(){

	window.location.href=$("base").attr("href")+"app/expert/expert_choosed.jsp?id=${param.id}";
	 /* top.$.dialog({

	window.location.href=$("base").attr("href")+'app/expert/expert_choosed.jsp?id=${param.id}';
	/* if( $("#get").attr("class").indexOf("get")==-1){
		top.$.dialog({
 .r166323
			width : 1000,
			height : 900,
			lock : true,
			title : "机选结果",
			content : 'url:app/expert/expert_choosed.jsp?id=${param.id}',
			data : {
				"window" : window
			}
		}); 
	 	$("#get").addClass("get");*/
}



</script>

</body>
</html>
