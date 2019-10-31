<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%=basePath %>" />
<link href="app/expert/css/common.css" rel="stylesheet" type="text/css">
<script src="app/expert/js/jquery.min.1.9.1.js"></script>
<script type="text/javascript" src="app/expert/js/scroll.js"></script>
<title>四川省特种设备检验系统专家库</title>
</head>
<body>
<div class="s_n_bg"></div>
<div class="n_zj_contain1">
    <div class="m_cont">
        <div class="zj_m_tit">四川省特种设备检验系统专家库</div>
        <div id="selected_list" class="zj_list">
       <!--   <img src="images/xx.png">
    
        
        <h2>杨秉辉 男，1938年8月生。</h2>
       <p> 1962年毕业于原上海医科大学医学系，现为复旦大学医学院（原上海医科大学）肝癌研究所教授、博士生导师，曾任复旦大学医学院（原上海医科大学）附属中山医院院长及复旦大学医学院（原上海医科大学）中山临床医学院院长，兼任中华医学会全科学会副主任委员、中华医学会肝病学会科学委员、上海医学会科普学会主任委员及上海医学会肿瘤学会副主任委员等职。主要研究肝癌的早期发现，早期诊断及肝癌的内科治疗等。1985年曾因 “ 小肝癌的诊断和治疗 ” 获国家科学技术进步一等奖。主编《原发性肝癌的研究和进展》、《内科治疗矛盾》等专著、并担任《实用内科学》第十版副主编、第十二版编委，参与编写专著近20部，发表学术论文百余篇。</p>

         </div> -->
       <!--   <div class="xx_tit2">
	         <span class="name_t">姓名：张三</span>
	         <span class="neib">计量专家</span> 
         </div> -->
    </div>
 </div>
  <div class="zj_bot">  
<!--  <div class="btns"><a href="javascript:void(0)" onclick="toAttlist();" class="btn btn-big btn-gray" id="xyb">返回</a></div>  -->
</div>

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
$(document).ready(function(){
    $('.list_lh li:even').addClass('lieven');
})
$(function(){
    $("div.list_lh").myScroll({
        speed:40, //数值越大，速度越慢
        rowHeight:40 //li的高度
    });
    $.post("expertRecordAction/randomExport.do",{id:'${param.id}'},function(res){
    	if(res.success){
    		var data = res.result;
    		$("#selected_list").find("div.xx_tit2").remove();
    		for(var i =0;i<data.length;i++){
    			$("#selected_list").append("<div class=\"xx_tit2\"><span class=\"name_t\">姓名："+data[i].name+"</span><span class=\"neib\">"+data[i].expert_type+"</span></div>");
    		}
    	}else{
    		alert(res.msg);
    	}
    });
});
</script>

</body>
</html>
