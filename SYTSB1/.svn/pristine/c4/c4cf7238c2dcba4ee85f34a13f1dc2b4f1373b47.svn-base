<%@ page language="java" import="java.text.SimpleDateFormat" pageEncoding="utf-8" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>电梯检验费用计算</title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>
<link type="text/css" rel="stylesheet" href="app/qualitymanage/css/form_detail.css" />
<script type="text/javascript">		
	var pageStatus = "${param.status}";		
	
	$(function() {
		$("#form1").initForm({ //参数设置示例
			toolbar : [ /*{
				text : '保存',
				//id : 'save',
				icon : 'save',
				click : save
			},*/{
				text : '关闭',
				//id : 'close',
				icon : 'cancel',
				click : close
			}],
			toolbarPosition: "bottom",
			getSuccess : function(resp) {
				
			}
		});
	})
		
	function close(){	
		api.close();
	}
	
	String.prototype.trim = function() {
    	return this.replace(/(^\s*)|(\s*$)/g,'');
	}
	
	function dtCal(type){
		var result = 0;
		if("1" == type){
			var count1 = $("#count1").val();
			result = 550*count1;
			$("#result1").val(result);
		}else if("2" == type){
			var dtcs2 = $("#dtcs2").val();
			var count2 = $("#count2").val();
			result = (550+((dtcs2-5)*55))*count2;
			$("#result2").val(result);
		}else if("3" == type){
			var dtcs3 = $("#dtcs3").val();
			var count3 = $("#count3").val();
			result = ((550*1.3)+200)*count3;
			$("#result3").val(result);
		}else if("4" == type){
			var dtcs4 = $("#dtcs4").val();
			var count4 = $("#count4").val();
			result = ((550+((dtcs4-5)*55))*1.3+200)*count4;
			$("#result4").val(result);
		}else if("5" == type){
			var dtcs5 = $("#dtcs5").val();
			var count5 = $("#count5").val();
			result = ((550*1.3)+400)*count5;
			$("#result5").val(result);
		}else if("6" == type){
			var dtcs6 = $("#dtcs6").val();
			var count6 = $("#count6").val();
			result = ((550+((dtcs6-5)*55))*1.3+400)*count6;
			$("#result6").val(result);
		}else if("7" == type){
			var count7 = $("#count7").val();
			result = 400*count7;
			$("#result7").val(result);
		}else if("8" == type){
			var count8 = $("#count8").val();
			result = 550*1.5*count8;
			$("#result8").val(result);
		}else if("9" == type){
			var dtcs9 = $("#dtcs9").val();
			var count9 = $("#count9").val();
			result = ((550+((dtcs9-5)*55))*1.5)*count9;
			$("#result9").val(result);
		}else if("10" == type){
			var count10 = $("#count10").val();
			result = 600*count10;
			$("#result10").val(result);
		}
	}
	
	function ylCal(obj, type){
		var result = 0;
		var yljs = $("#yljs"+obj).html();
		var count = $("#count"+obj).val();
		if("0" == type){
			result = yljs*count;
		}else if("1" == type){
			var ts = $("#ts"+obj).val();
			result = yljs*ts*count;
		}
		$("#result"+obj).val(result);
	}
	
	function sdCal(obj, type){
		var result = 0;
		var sdjs = $("#sdjs"+obj).html();
		var count = $("#count"+obj).val();
		if("0" == type){
			result = sdjs*count;
		}else if("1" == type){
			var sdcd = $("#sdcd"+obj).val();
			var sdc = sdcd-800;
			if(sdc<100){
				result = sdjs*count;
			}else{
				result = ((Number((sdc/100)*sdjs*0.03))+Number(sdjs))*count;
			}
		}
		$("#result"+obj).val(result);
	}
	</script>
</head>
<body>
<%
 	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = dateFormat.format(new Date());
%>
<form id="form1" action="" getAction="">
    <fieldset class="l-fieldset">
    <legend class="l-legend">
    	<div>电梯定期检验费用计算</div>
	</legend>
    <table id="sg" border="1" cellpadding="3" class="l-detail-table" width="720px">
        <tr>
        	<td align="center" style="width:25px;">检验类别</td>
        	<td align="center" style="width:70px;">设备类别</td>
			<td align="center" style="width:30px;">限速器</td>
			<td align="center" style="width:20px;">基数</td>
			<td align="center" style="width:40px;">电梯层数</td>
			<td align="center" style="width:30px;">层数</td>
			<td align="center" style="width:25px;">数量</td>
			<td align="center" style="width:80px;">计算公式</td>
			<td align="center" style="width:45px;">计算结果</td>
			<td align="center" style="width:20px;">&nbsp;&nbsp;</td>
        </tr>
        <tr>
        	<td class="l-t-td-right" align="center" rowspan="7">定期检验</td>
        	<td class="l-t-td-right" rowspan="6">曳引与强制驱动电梯、液压驱动电梯、防爆电梯、消防员电梯</td>
        	<td class="l-t-td-right" align="center" rowspan="2">无</td>
        	<td class="l-t-td-right" align="center" rowspan="2">550元</td>
        	<td class="l-t-td-right" align="left" >5层以下（含5层）</td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count1" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="left">550*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result1" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:dtCal('1');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr>
        <tr>
        	<td class="l-t-td-right" align="left" >5层以上</td>
        	<td class="l-t-td-right" align="center" ><input name="dtcs" id="dtcs2" type="text" ltype="spinner"/></td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count2" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="left">(550+(层数-5)*55)*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result2" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:dtCal('2');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr>
        <tr>
        	<td class="l-t-td-right" align="center" rowspan="2">1个，200元</td>
        	<td class="l-t-td-right" align="center" rowspan="2">550元</td>
        	<td class="l-t-td-right" align="left" >5层以下（含5层）</td>
        	<td class="l-t-td-right" align="center">无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count3" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="left">(550*1.3+200)*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result3" type="text" ltype="text"/></td>  	
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:dtCal('3');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr>
        <tr>
       	 	<td class="l-t-td-right" align="left" >5层以上</td>
        	<td class="l-t-td-right"><input name="dtcs" id="dtcs4" type="text" ltype="spinner"/></td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count4" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="left">((550+(层数-5)*55)*1.3+200)*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result4" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:dtCal('4');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr>
        <tr>
        	<td class="l-t-td-right" align="center" rowspan="2">2个，400元</td>
        	<td class="l-t-td-right" align="center" rowspan="2">550元</td>
        	<td class="l-t-td-right" align="left" >5层以下（含5层）</td>
        	<td class="l-t-td-right" align="center">无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count5" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="left">(550*1.3+400)*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result5" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:dtCal('5');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td> 	
        </tr>
        <tr>
        	<td class="l-t-td-right" align="left" >5层以上</td>
        	<td class="l-t-td-right"><input name="dtcs" id="dtcs6" type="text" ltype="spinner"/></td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count6" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="left">((550+(层数-5)*55)*1.3+400)*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result6" type="text" ltype="text"/></td>   
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:dtCal('6');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>	
        </tr>
        <tr>
        	<td class="l-t-td-right" align="center">自动扶梯与自动人行道、杂物电梯</td>
        	<td class="l-t-td-right" align="center">无</td>
        	<td class="l-t-td-right" align="center">400元</td>
        	<td class="l-t-td-right" align="center">无</td>
        	<td class="l-t-td-right" align="center">无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count7" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="left" >400*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result7" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:dtCal('7');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr>    
    </table>
    </fieldset>
    <fieldset class="l-fieldset">
    <legend class="l-legend">
    	<div>电梯监督检验费用计算</div>
	</legend>
    <table id="sg" border="1" cellpadding="3" class="l-detail-table" width="720px">
    	<tr>
        	<td align="center" style="width:25px;">检验类别</td>
        	<td align="center" style="width:70px;">设备类别</td>
			<td align="center" style="width:30px;">限速器</td>
			<td align="center" style="width:20px;">基数</td>
			<td align="center" style="width:40px;">电梯层数</td>
			<td align="center" style="width:30px;">层数</td>
			<td align="center" style="width:25px;">数量</td>
			<td align="center" style="width:80px;">计算公式</td>
			<td align="center" style="width:45px;">计算结果</td>
			<td align="center" style="width:20px;">&nbsp;&nbsp;</td>
        </tr>
        <tr>
        	<td class="l-t-td-right" align="center" rowspan="3">监督检验</td>
        	<td class="l-t-td-right" rowspan="2">曳引与强制驱动电梯、液压驱动电梯、防爆电梯、消防员电梯</td>
        	<td class="l-t-td-right" align="center" rowspan="2">无</td>
        	<td class="l-t-td-right" align="center" rowspan="2">550元</td>
        	<td class="l-t-td-right" align="left" >5层以下（含5层）</td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count8" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="left">550*1.5*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result8" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:dtCal('8');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr>
        <tr>
        	<td class="l-t-td-right" align="left" >5层以上</td>
        	<td class="l-t-td-right" align="center" ><input name="dtcs" id="dtcs9" type="text" ltype="spinner"/></td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count9" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="left">(550+(层数-5)*55)*1.5*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result9" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:dtCal('9');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr>
        <tr>
        	<td class="l-t-td-right" align="center">自动扶梯与自动人行道、杂物电梯</td>
        	<td class="l-t-td-right" align="center">无</td>
        	<td class="l-t-td-right" align="center">600元</td>
        	<td class="l-t-td-right" align="center">无</td>
        	<td class="l-t-td-right" align="center">无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count10" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="left" >600*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result10" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:dtCal('10');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr>    
    </table>
    </fieldset>
    <div>
    <fieldset class="l-fieldset" style="width:47.5%;float:left;">
    <legend class="l-legend">
    	<div>游乐设施定期检验费用计算</div>
	</legend>
    <table id="sg" border="1" cellpadding="3" class="l-detail-table" >
    	<tr>
        	<td align="center">检验类别</td>
        	<td align="center">设备类别</td>
			<td align="center">类型</td>
			<td align="center">基数</td>
			<td align="center">台数</td>
			<td align="center">数量</td>
			<td align="center">计算公式</td>
			<td align="center">计算结果</td>
			<td align="center">&nbsp;&nbsp;</td>
        </tr>
        <tr>
        	<td class="l-t-td-right" align="center" rowspan="10">定期检验</td>
        	<td class="l-t-td-right" align="center"rowspan="3">小火车类</td>
        	<td class="l-t-td-right" align="center" >10人以内（含10人）</td>
        	<td class="l-t-td-right" align="center" ><div id="yljs11">140</div></td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count11" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center">140*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result11" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:ylCal('11','0');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr>
        <tr>
        	<td class="l-t-td-right" align="center" >11人至30人（含30人）</td>
        	<td class="l-t-td-right" align="center" ><div id="yljs12">160</div></td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count12" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center">160*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result12" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:ylCal('12','0');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr>
        <tr>
        	<td class="l-t-td-right" align="center">30人以上</td>
        	<td class="l-t-td-right" align="center"><div id="yljs13">180</div></td>
        	<td class="l-t-td-right" align="center">无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count13" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center" >180*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result13" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:ylCal('13','0');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr>   
        <tr>
        	<td class="l-t-td-right" align="center" >观览车类、滑行车类、水上游乐设施</td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><div id="yljs14">1200</div></td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count14" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center">1200*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result14" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:ylCal('14','0');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr> 
        <tr>
        	<td class="l-t-td-right" align="center" >陀螺类、架空游览车类</td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><div id="yljs15">700</div></td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count15" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center">700*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result15" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:ylCal('15','0');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr> 
        <tr>
        	<td class="l-t-td-right" align="center" >飞行塔类</td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><div id="yljs16">650</div></td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count16" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center">650*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result16" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:ylCal('16','0');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr> 
        <tr>
        	<td class="l-t-td-right" align="center" >自控飞机类</td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><div id="yljs17">510</div></td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count17" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center">510*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result17" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:ylCal('17','0');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr> 
        <tr>
        	<td class="l-t-td-right" align="center" >转马类</td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><div id="yljs18">500</div></td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count18" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center">500*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result18" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:ylCal('18','0');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr> 
        <tr>
        	<td class="l-t-td-right" align="center" >碰碰车类</td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><div id="yljs19">100</div></td>
        	<td class="l-t-td-right" align="center" ><input name="ts" id="ts19" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count19" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center">100*台*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result19" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:ylCal('19','1');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr> 
        <tr>
        	<td class="l-t-td-right" align="center" >赛车类</td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><div id="yljs20">150</div></td>
        	<td class="l-t-td-right" align="center" ><input name="ts" id="ts20" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count20" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center">150*台*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result20" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:ylCal('20','1');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr> 
    </table>
    </fieldset>
    <fieldset class="l-fieldset" style="width:47.5%;float:right;">
    <legend class="l-legend">
    	<div>游乐设施监督检验费用计算</div>
	</legend>
    <table id="sg" border="1" cellpadding="3" class="l-detail-table" >
    	<tr>
        	<td align="center">检验类别</td>
        	<td align="center">设备类别</td>
			<td align="center">类型</td>
			<td align="center">基数</td>
			<td align="center">台数</td>
			<td align="center">数量</td>
			<td align="center">计算公式</td>
			<td align="center">计算结果</td>
			<td align="center">&nbsp;&nbsp;</td>
        </tr>
        <tr>
        	<td class="l-t-td-right" align="center" rowspan="10">监督检验</td>
        	<td class="l-t-td-right" align="center"rowspan="3">小火车类</td>
        	<td class="l-t-td-right" align="center" >10人以内（含10人）</td>
        	<td class="l-t-td-right" align="center" ><div id="yljs112">168</div></td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count112" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center">168*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result112" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:ylCal('112','0');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr>
        <tr>
        	<td class="l-t-td-right" align="center" >11人至30人（含30人）</td>
        	<td class="l-t-td-right" align="center" ><div id="yljs122">192</div></td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count122" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center">192*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result122" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:ylCal('122','0');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr>
        <tr>
        	<td class="l-t-td-right" align="center">30人以上</td>
        	<td class="l-t-td-right" align="center"><div id="yljs132">216</div></td>
        	<td class="l-t-td-right" align="center">无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count132" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center" >216*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result132" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:ylCal('132','0');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr> 
        <tr>
        	<td class="l-t-td-right" align="center" >观览车类、滑行车类、水上游乐设施</td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><div id="yljs142">1440</div></td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count142" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center">1440*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result142" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:ylCal('142','0');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr>    
        <tr>
        	<td class="l-t-td-right" align="center" >陀螺类、架空游览车类</td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><div id="yljs152">840</div></td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count152" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center">840*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result152" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:ylCal('152','0');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr> 
        <tr>
        	<td class="l-t-td-right" align="center" >飞行塔类</td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><div id="yljs162">780</div></td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count162" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center">780*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result162" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:ylCal('162','0');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr> 
        <tr>
        	<td class="l-t-td-right" align="center" >自控飞机类</td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><div id="yljs172">612</div></td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count172" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center">612*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result172" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:ylCal('172','0');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr> 
        <tr>
        	<td class="l-t-td-right" align="center" >转马类</td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><div id="yljs182">600</div></td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count182" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center">600*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result182" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:ylCal('182','0');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr> 
        <tr>
        	<td class="l-t-td-right" align="center" >碰碰车类</td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><div id="yljs192">120</div></td>
        	<td class="l-t-td-right" align="center" ><input name="ts" id="ts192" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count192" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center">120*台*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result192" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:ylCal('192','1');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr> 
        <tr>
        	<td class="l-t-td-right" align="center" >赛车类</td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><div id="yljs202">180</div></td>
        	<td class="l-t-td-right" align="center" ><input name="ts" id="ts202" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count202" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="center">180*台*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result202" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:ylCal('202','1');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr> 
    </table>
    </fieldset>
    </div>
    <fieldset class="l-fieldset" >
    <legend class="l-legend">
    	<div>客运索道定期检验费用计算</div>
	</legend>
    <table id="sg" border="1" cellpadding="3" class="l-detail-table" >
    	<tr>
        	<td align="center" style="width:25px;">检验类别</td>
        	<td align="center" style="width:70px;">设备类别</td>
        	<td align="center" style="width:40px;">类型</td>
			<td align="center" style="width:25px;">基数</td>
			<td align="center" style="width:25px;">长度</td>
			<td align="center" style="width:25px;">数量</td>
			<td align="center" style="width:100px;">计算公式</td>
			<td align="center" style="width:25px;">计算结果</td>
			<td align="center" style="width:20px;">&nbsp;&nbsp;</td>
        </tr>
        <tr>
        	<td class="l-t-td-right" align="center" rowspan="4">定期检验</td>
        	<td class="l-t-td-right" align="center" rowspan="2">单线固定抱索器连续循环式</td>
        	<td class="l-t-td-right" align="center" >800米以下（含800米）</td>
        	<td class="l-t-td-right" align="center" ><div id="sdjs21">6100</div></td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count21" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="left">6100*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result21" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:sdCal('21','0');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr>
        <tr>
        	<td class="l-t-td-right" align="center" >800米以上</td>
        	<td class="l-t-td-right" align="center" ><div id="sdjs22">6100</div></td>
        	<td class="l-t-td-right" align="center" ><input name="sdcd" id="sdcd22" type="text" ltype="spinner" value='800'/></td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count22" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="left">1、(长度-800)<100：6100*数量<br/>2、(长度-800)>=100：(((长度-800)/100)*6100*0.03+6100)*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result22" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:sdCal('22','1');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr>
        <tr>
        	<td class="l-t-td-right" align="center" rowspan="2">单线固定抱索器脉动/间隙/往复式</td>
        	<td class="l-t-td-right" align="center" >800米以下（含800米）</td>
        	<td class="l-t-td-right" align="center" ><div id="sdjs23">6300</div></td>
        	<td class="l-t-td-right" align="center" >无</td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count23" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="left">6300*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result23" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:sdCal('23','0');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr>
        <tr>
        	<td class="l-t-td-right" align="center" >800米以上</td>
        	<td class="l-t-td-right" align="center" ><div id="sdjs24">6300</div></td>
        	<td class="l-t-td-right" align="center" ><input name="sdcd" id="sdcd24" type="text" ltype="spinner" value='800'/></td>
        	<td class="l-t-td-right" align="center" ><input name="count" id="count24" type="text" ltype="spinner" value='1'/></td>
        	<td class="l-t-td-right" align="left">1、(长度-800)<100：6300*数量<br/>2、(长度-800)>=100：(((长度-800)/100)*6300*0.03+6300)*数量</td>
        	<td class="l-t-td-right" align="center"><input name="result" id="result24" type="text" ltype="text"/></td>
        	<td class="l-t-td-right" align="center">
        		<a class="l-button-warp l-button"
						href="javascript:sdCal('24','1');" style="margin-top:2px"><span
								class="l-button-text">计算</span></a>
        	</td>
        </tr>
    </table>
    </fieldset>
</form>
</body>
</html>
