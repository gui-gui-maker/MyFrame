<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@ page import="java.util.*" %> 
<%@page import="java.text.SimpleDateFormat"%>
<%
CurrentSessionUser user = SecurityUtil.getSecurityUser();
User uu = (User)user.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy");
String nowTime=""; 
nowTime = dateFormat.format(new Date());
%>
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <link type="text/css" rel="stylesheet" href="app/qualitymanage/css/form_detail.css" />
	<script type="text/javascript" src="pub/bpm/js/util.js"></script>
    <script type="text/javascript">
    var tbar="";
	var pattern = /^([0-9.]+)$/;
	var ab=pageStatus="${param.pageStatus}";
 	$(function () {
 			if(ab=="add"){
 				$("#zongjs").hide();
 				//document.getElementById(zongjs).style.display="none";
 			}else{
 				
 			}
        tbar=[{ text: '保存', id: 'up', icon: 'save', click: directChange},
            { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
            
        if ("${param.pageStatus}"=="detail")
        tbar=[{ text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
    	$("#form1").initForm({
            showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
    });
 	
        function directChange(){ 
        	var obj=$("#form1").validate().form();
   		 if(obj){
   		$("#form1").submit();
//    			 var formData = $("#form1").getValues();
//    	         $("body").mask("正在保存......");
//    	        $.ajax({
//    	            url: "Tjy2GjjAction/RL/saveBasic.do",
//    	            type: "POST",
//    	            datatype: "json",
//    	            contentType: "application/json; charset=utf-8",
//    	            data: $.ligerui.toJSON(formData),
//    	            success: function (data, stats) {
//    	                $("body").unmask();
//    	                if (data["success"]) {
//    	                	top.$.notice(data.msg,3);	
//    	                    //top.$.dialog.notice({content:'保存成功！'});
//    	                    api.data.window.Qm.refreshGrid();//刷新
//    	                    api.close();
//    	                }else{
//    	                    $.ligerDialog.error('提示：' + data.msg);
//    	                    api.data.window.Qm.refreshGrid();//刷新
//    	                }
//    	            },
//    	            error: function (data,stats) {
//    	                $("body").unmask();
//    	                $.ligerDialog.error('提示：' + '保存错误!!');
//    	            }
//    	        });
    		 }else{
   			 return;
   		}} 
        function choosePerson(){
            top.$.dialog({
                width: 800,
                height: 450,
                lock: true,
                parent: api,
                title: "选择人员",
                content: 'url:app/common/person_choose.jsp',
                cancel: true,
                ok: function(){
                    var p = this.iframe.contentWindow.getSelectedPerson();
                    if(!p){
                        top.$.notice("请选择人员！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                        return false;
                    }
                    $("#nameId").val(p.id);
                    $("#name").val(p.name);
                    $("#org").val(p.org_name);
                    $("#orgId").val(p.org_id);
                    $("#jySj").val(p.jySj);
                    $("#xl").ligerGetComboBoxManager().setValue( getEducation(p.initial_education,p.initial_degree));
                    
                }
            });
        }
        
        function getEducation(name,degree){
        	if(name==""||name==null){
        		return "";
        	}
        	if(name.indexOf("博士")!=-1){
        		return "BS";
        	}else if(name.indexOf("硕士")!=-1||name.indexOf("研究生")!=-1){
        		return "SS";
        	}else if(name.indexOf("本科")!=-1||degree!=""){
        		if(degree!=""&&degree!=null){
        			var value = degree.replace(/学士/g,";学士;");
            		var n = value.split("学士").length;
            		if(n>2){
            			return "BKS";
            		}else{
            			return "BK";
            		}
        		}else{
        			return "BK";
        		}
        	}else{
    			return "DZ";
    		}
        }
        
        function chooseOrg(){
            top.$.dialog({
                width: 800,
                height: 450,
                lock: true,
                parent: api,
                title: "选择部门",
                content: 'url:app/common/org_choose.jsp',
                cancel: true,
                ok: function(){
                    var p = this.iframe.contentWindow.getSelectedPerson();
                    if(!p){
                        top.$.notice("请选择部门！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                        return false;
                    }
                    $("#orgId").val(p.id);
                    $("#org").val(p.name);
                }
            });
        }
        function valueChange(val,text){
        	var a=$("#xl").ligerGetComboBoxManager().getValue();
			if(a=="DZ"){
				$("#syqmyjyJs").val("3200");
			}else if(a=="BK"){
				$("#syqmyjyJs").val("3600");
			}else if(a=="SS" || a=="BKS"){
				$("#syqmyjyJs").val("3800");
			}else{
				$("#syqmyjyJs").val("4000");
			}
        }
        	function onlyNonNegative(obj) {
         var inputChar = event.keyCode;
         //alert(event.keyCode);
         //1.判断是否有多于一个小数点
         if(inputChar==190 ) {//输入的是否为.
     	    var index1 = obj.value.indexOf(".") + 1;//取第一次出现.的后一个位置
     	    var index2 = obj.value.indexOf(".",index1);
     	    while(index2!=-1) {
     		      //alert("有多个.");
     		     obj.value = obj.value.substring(0,index2);
     		     index2 = obj.value.indexOf(".",index1);
     	    }
         }
         //2.如果输入的不是.或者不是数字，替换 g:全局替换
        	obj.value = obj.value.replace(/[^(\d|.)]/g,"");
         var jyhgzNx=document.getElementById("jyhgzNx").value;
        	}
      
        function getDate1(){//进院
        	var jySj = $("#jySj").val();
        	var ws=jySj.charAt(jySj.length - 1);//月
        	var yue=jySj.substr(5, 6);//yue
        	var nian=jySj.substr(0, 4);//年
        	var bn ="<%=nowTime%>";//今年
    		var bb=parseInt(bn)-parseInt(ws);
   			var bc=parseInt(bn)-parseInt(nian)-1;
   			if(parseInt(nian)+2==bn && yue=="01"){
   				//alert(11111111111);
   				$("#jyhgzNx").val(parseInt(bc)+1);
   			}else if(parseInt(nian)+1==bn && yue=="01"){
   				//alert(222222);
   				$("#jyhgzNx").val(parseInt(bc)+1);
   			}else if(bc=="-1"){
   				//alert(3333333333);
   				$("#jyhgzNx").val("0");
   			}else {
				if (pattern.test(bc)) {
					if(yue=="01"){
						$("#jyhgzNx").val(parseInt(bc)+1);
					}else{
						$("#jyhgzNx").val(bc);
					}
				}else{
					$("#jyhgzNx").val("0");
				}
   			}
   			var yb=$("#jyhgzNx").val();
   			$("#ybgzryrzNx").val(yb);
   			$("#zjdyjfJs").val(yb*60);
        }
        function getDate2(){//一般工作人员任职时间
        	var jySj = $("#ybgzryjy_sj").val();
        	var ws=jySj.charAt(jySj.length - 1);//月
        	var yue=jySj.substr(5, 6);//yue
        	var nian=jySj.substr(0, 4);//年
        	var bn ="<%=nowTime%>";//今年
    		var bb=parseInt(bn)-parseInt(ws);
   			var bc=parseInt(bn)-parseInt(nian)-1;
   			if(parseInt(nian)+2==bn && yue=="01"){
   				$("#ybgzryrzNx").val(parseInt(bc)+1);
   				$("#zjdyjfJs").val((parseInt(bc)+1)*60);
   				//$("#zjfJs").val((parseInt(bc)+1)*60);
   			}else if(parseInt(nian)+1==bn && yue=="01"){
   				$("#ybgzryrzNx").val(parseInt(bc)+1);
   				$("#zjdyjfJs").val((parseInt(bc)+1)*60);
   				//$("#zjfJs").val((parseInt(bc)+1)*60);
   			}else if(bc=="-1"){
   				$("#ybgzryrzNx").val("0");
   				$("#zjdyjfJs").val("0");
   			}else {
   				if (pattern.test(bc)) {
   					$("#ybgzryrzNx").val(bc);
   					$("#zjdyjfJs").val(bc*60);
				}else{
					$("#ybgzryrzNx").val("");
					$("#zjdyjfJs").val("");
				}
   			}
        }
        function setnian1(){
        	var jySj = $("#jySj").val();//进院ybgzryrzNx
        	var ybgzryrzNx = $("#ybgzryrzNx").val();//
        	$("#zrzlrzSjA").val();//主任助理任职时间
        	$("#zrzlrzNx").val();
        	$("#zjdyjfJb").val();
        	$("#fzrzSj").val();//副职任职时间
        	$("#fzrzNx").val();
        	$("#zjdyfJsC").val();
        	$("#zzrzSj").val();//正职任职时间
        	$("#zzrzNx").val();
        	$("#zjdyfJsD").val();
        	
        }
        function getDate3(){//主任助理任职时间
        	var jySj = $("#zrzlrzSjA").val();
        	var ws=jySj.charAt(jySj.length - 1);//月
        	var yue=jySj.substr(5, 6);//yue
        	var nian=jySj.substr(0, 4);//年
        	var bn ="<%=nowTime%>";//今年
    		var bb=parseInt(bn)-parseInt(ws);
   			var bc=parseInt(bn)-parseInt(nian)-1;
   			if(parseInt(nian)+2==bn && yue=="01"){
   				$("#zrzlrzNx").val(parseInt(bc)+1);
   				$("#zjdyjfJb").val((parseInt(bc)+1)*80);
   			}else if(parseInt(nian)+1==bn && yue=="01"){
   				$("#zrzlrzNx").val(parseInt(bc)+1);
   				$("#zjdyjfJb").val((parseInt(bc)+1)*80);
   			}else if(bc=="-1"){
   				$("#zrzlrzNx").val("0");
   				$("#zjdyjfJb").val("0");
   			}else {
   				if (pattern.test(bc)) {
   					if(yue=="01"){
	   					$("#zrzlrzNx").val(parseInt(bc)+1);
	   					$("#zjdyjfJb").val((parseInt(bc)+1)*80);
					}else{
						$("#zrzlrzNx").val(bc);
	   					$("#zjdyjfJb").val(bc*80);
					}
				}else{
					$("#zrzlrzNx").val("");
					$("#zjdyjfJb").val("");
				}
   			}
   			var yb=$("#jyhgzNx").val();//进院
   			var zhu=$("#zrzlrzNx").val();//主任
   			var fu=$("#fzrzNx").val();//副
   			var zheng=$("#zzrzNx").val();//正
   			if(pattern.test(fu)){
   			}else{
   				fu="0";
   			}
   			if(pattern.test(zheng)){
   			}else{
   				zheng="0";
   			}
   			var he=parseInt(yb)-parseInt(zhu)-parseInt(fu)-parseInt(zheng);
   			if(pattern.test(bc)){
   				if(he>=0){
		   			$("#ybgzryrzNx").val(parseInt(yb)-parseInt(zhu)-parseInt(fu)-parseInt(zheng));
		   			$("#zjdyjfJs").val((parseInt(yb)-parseInt(zhu)-parseInt(fu)-parseInt(zheng))*60);
   				}else{
   					$.ligerDialog.error('提示：任职年限超过了进院后工作年限请仔细核对！！！');
   				}
   			}else{
   				$("#ybgzryrzNx").val("");
	   			$("#zjdyjfJs").val("");
   			}
        }
        function getDate4(){//副职任职时间
        	var jySj = $("#fzrzSj").val();
        	var ws=jySj.charAt(jySj.length - 1);//月
        	var yue=jySj.substr(5, 6);//yue
        	var nian=jySj.substr(0, 4);//年
        	var bn ="<%=nowTime%>";//今年
    		var bb=parseInt(bn)-parseInt(ws);
   			var bc=parseInt(bn)-parseInt(nian)-1;
   			if(parseInt(nian)+2==bn && yue=="01"){
   				$("#fzrzNx").val(parseInt(bc)+1);
   				$("#zjdyfJsC").val((parseInt(bc)+1)*100);
   			}else if(parseInt(nian)+1==bn && yue=="01"){
   				$("#fzrzNx").val(parseInt(bc)+1);
   				$("#zjdyfJsC").val((parseInt(bc)+1)*100);
   			}else if(bc=="-1"){
   				$("#fzrzNx").val("0");
   				$("#zjdyfJsC").val("0");
   			}else {
   				if (pattern.test(bc)) {
   					if(yue=="01"){
	   					$("#fzrzNx").val(parseInt(bc)+1);
	   					$("#zjdyfJsC").val((parseInt(bc)+1)*100);
					}else{
						$("#fzrzNx").val(bc);
	   					$("#zjdyfJsC").val(bc*100);
					}
				}else{
					$("#fzrzNx").val("");
					$("#zjdyfJsC").val("");
				}
   			}
   			var yb=$("#jyhgzNx").val();//进院
   			var zhu=$("#zrzlrzNx").val();//主任
   			var fu=$("#fzrzNx").val();//副
   			var zheng=$("#zzrzNx").val();//正
   			var zrzlrzSjA=$("#zrzlrzSjA").val();//主任助理任职时间
   			if(pattern.test(zhu)){
   			}else{
   				zhu="0";
   			}
   			if(pattern.test(zheng)){
   			}else{
   				zheng="0";
   			}
   			var he=parseInt(yb)-parseInt(zhu)-parseInt(fu)-parseInt(zheng);
   			if(pattern.test(bc)){
   				//if(he>=0){
		   			$("#ybgzryrzNx").val(parseInt(yb)-parseInt(fu)-parseInt(zhu)-parseInt(zheng));
		   			$("#zjdyjfJs").val((parseInt(yb)-parseInt(fu)-parseInt(zhu)-parseInt(zheng))*60);
		   			if(zrzlrzSjA==""){
   						
   					}else{
   						var nian1=zrzlrzSjA.substr(0, 4);//年
   						var cha=parseInt(nian)-parseInt(nian1);
   						if(cha>=0){
   							$("#zrzlrzNx").val(cha);
        					$("#zjdyjfJb").val(cha*80);
        					$("#ybgzryrzNx").val(parseInt(yb)-parseInt(fu)-parseInt(cha)-parseInt(zheng));
		   					$("#zjdyjfJs").val((parseInt(yb)-parseInt(fu)-parseInt(cha)-parseInt(zheng))*60);
   						}
   					}
   				//}else{
   				//	$.ligerDialog.error('提示：任职年限超过了进院后工作年限请仔细核对！！！');
   				//}
   			}else{
   				$("#ybgzryrzNx").val("");
   	   			$("#zjdyjfJs").val("");
   			}
        }
        function getDate5(){//正职任职时间
        	var jySj = $("#zzrzSj").val();
        	var ws=jySj.charAt(jySj.length - 1);//月
        	var yue=jySj.substr(5, 6);//yue
        	var nian=jySj.substr(0, 4);//年
        	var bn ="<%=nowTime%>";//今年
    		var bb=parseInt(bn)-parseInt(ws);
   			var bc=parseInt(bn)-parseInt(nian)-1;
   			if(parseInt(nian)+2==bn && yue=="01"){
   				$("#zzrzNx").val(parseInt(bc)+1);
   				$("#zjdyfJsD").val((parseInt(bc)+1)*120);
   			}else if(parseInt(nian)+1==bn && yue=="01"){
   				$("#zzrzNx").val(parseInt(bc)+1);
   				$("#zjdyfJsD").val((parseInt(bc)+1)*120);
   			}else if(bc=="-1"){
   				$("#zzrzNx").val("0");
   				$("#zjdyfJsD").val("0");
   			}else {
   				if (pattern.test(bc)) {
   					if(yue=="01"){
	   					$("#zzrzNx").val(parseInt(bc)+1);
	   					$("#zjdyfJsD").val((parseInt(bc)+1)*120);
					}else{
						$("#zzrzNx").val(bc);
	   					$("#zjdyfJsD").val(bc*120);
					}
				}else{
					$("#zzrzNx").val("");
					$("#zjdyfJsD").val("");
				}
   			}
   			var yb=$("#jyhgzNx").val();//进院
   			var zhu=$("#zrzlrzNx").val();//主任
   			var fu=$("#fzrzNx").val();//副
   			var zheng=$("#zzrzNx").val();//正
   			var zrzlrzSjA=$("#zrzlrzSjA").val();//主任助理任职时间
   			var fzrzSj=$("#fzrzSj").val();//副职任职时间
   			if(pattern.test(fu)){
   			}else{
   				fu="0";
   			}
   			if(pattern.test(zhu)){
   			}else{
   				zhu="0";
   			}
   			var he=parseInt(yb)-parseInt(zhu)-parseInt(fu)-parseInt(zheng);
   			if(pattern.test(bc)){	
   				//if(he>=0){
		   			$("#ybgzryrzNx").val(parseInt(yb)-parseInt(zheng)-parseInt(zhu)-parseInt(fu));
		   			$("#zjdyjfJs").val((parseInt(yb)-parseInt(zheng)-parseInt(zhu)-parseInt(fu))*60);
		   			if(zrzlrzSjA=="" && fzrzSj!=""){//alert(1);
   							var nian12=fzrzSj.substr(0, 4);//主任助理任职年
   							var cha12=parseInt(nian)-parseInt(nian12);
   							if(cha12>=0){
   								$("#fzrzNx").val(cha12);
        						$("#zjdyfJsC").val(cha12*100);
   								//$("#zrzlrzNx").val(cha);
        						//$("#zjdyjfJb").val(cha*80);
        						$("#ybgzryrzNx").val(parseInt(yb)-parseInt(cha12)-parseInt(zhu)-parseInt(zheng));
		   						$("#zjdyjfJs").val((parseInt(yb)-parseInt(cha12)-parseInt(zhu)-parseInt(zheng))*60);
   							}
   					}else if(zrzlrzSjA!="" && fzrzSj==""){//alert(6);
   							var nian12=zrzlrzSjA.substr(0, 4);//主任助理任职年
   							var cha12=parseInt(nian)-parseInt(nian12);
   							if(cha12>=0){
   								$("#zrzlrzNx").val(cha12);
        						$("#zjdyjfJb").val(cha12*80);
        						$("#ybgzryrzNx").val(parseInt(yb)-parseInt(cha12)-parseInt(fu)-parseInt(zheng));
		   						$("#zjdyjfJs").val((parseInt(yb)-parseInt(cha12)-parseInt(fu)-parseInt(zheng))*60);
   							}
   					}else if(zrzlrzSjA=="" && fzrzSj==""){//alert(2);
   						$("#ybgzryrzNx").val(parseInt(yb)-parseInt(zheng)-parseInt(zhu)-parseInt(fu));
		   				$("#zjdyjfJs").val((parseInt(yb)-parseInt(zheng)-parseInt(zhu)-parseInt(fu))*60);
   					}else{//alert(3);
   						var nian1=zrzlrzSjA.substr(0, 4);//主任助理任职年
   						var nian13=fzrzSj.substr(0, 4);//副职
   						var cha=parseInt(nian13)-parseInt(nian1);
   						var chacha=parseInt(nian)-parseInt(nian13);//alert(nian13+"-"+chacha+"-"+cha);
   						if(pattern.test(cha)){
   						}else{
   							cha="0";
   						}
   						if(cha>=0){
   							if(cha=="0"){
   							}else{
   								$("#zrzlrzNx").val(cha);
        						$("#zjdyjfJb").val(cha*80);
   							}
        					if(chacha>=0){
        						$("#fzrzNx").val(chacha);
        						$("#zjdyfJsC").val(chacha*100);
        					}//alert(yb+"-"+cha+"-"+chacha+"-"+zheng);
        					$("#ybgzryrzNx").val(parseInt(yb)-parseInt(cha)-parseInt(chacha)-parseInt(zheng));
		   					$("#zjdyjfJs").val((parseInt(yb)-parseInt(cha)-parseInt(chacha)-parseInt(zheng))*60);
   						}
   					}
   				//}else{
   					//$.ligerDialog.error('提示：任职年限超过了进院后工作年限请仔细核对！！！');
   				//}
   			}else{
   				$("#ybgzryrzNx").val("");
   	   			$("#zjdyjfJs").val("");
   			}
        }
        function getDate6(){//工程师任职时间
        	var jySj = $("#gcsrzSj").val();
        	var ws=jySj.charAt(jySj.length - 1);//月
        	var yue=jySj.substr(5, 6);//yue
        	var nian=jySj.substr(0, 4);//年
        	var bn ="<%=nowTime%>";//今年
    		var bb=parseInt(bn)-parseInt(ws);
   			var bc=parseInt(bn)-parseInt(nian)-1;
   			
   			var yuanjySj = $("#jySj").val();
   			var nianyue=yuanjySj.substr(0, 4)+yuanjySj.substr(5, 6);//进院的时间
   			var gcsrzSj= $("#gcsrzSj").val();
   			var gnianyue=gcsrzSj.substr(0, 4)+gcsrzSj.substr(5, 6);
   			var jinyuannx= $("#jyhgzNx").val();//进院工作年限
   			
   			if(gnianyue<=nianyue){//工程师任职时间小于入院时间
   				if (pattern.test(bc)) {
					$("#gcsrzNx").val(jinyuannx);
   					$("#zjdyfJsE").val(jinyuannx*100);
				}else{
					$("#gcsrzNx").val("");
					$("#zjdyfJsE").val("");
				}
	  			$("#ybgzryrzNx_a").val("0");
	   			$("#zjdyjfJs_a").val("0");
   			}else{
   				if(parseInt(nian)+2==bn && yue=="01"){
   	   				$("#gcsrzNx").val(parseInt(bc)+1);
   	   				$("#zjdyfJsE").val((parseInt(bc)+1)*100);
   	   			}else if(parseInt(nian)+1==bn && yue=="01"){
   	   				$("#gcsrzNx").val(parseInt(bc)+1);
   	   				$("#zjdyfJsE").val((parseInt(bc)+1)*100);
   	   			}else if(bc=="-1"){
   	   				$("#gcsrzNx").val("0");
   	   				$("#zjdyfJsE").val("0");
   	   			}else {
   	   				if (pattern.test(bc)) {
   	   					if(yue=="01"){
   		   					$("#gcsrzNx").val(parseInt(bc)+1);
   		   					$("#zjdyfJsE").val((parseInt(bc)+1)*100);
   						}else{
   							$("#gcsrzNx").val(bc);
   		   					$("#zjdyfJsE").val(bc*100);
   						}
   					}else{
   						$("#gcsrzNx").val("");
   						$("#zjdyfJsE").val("");
   					}
   	   			}
   	   			var yb=$("#jyhgzNx").val();//进院
   	   			var g=$("#gcsrzNx").val();//工程师
   	   			var gg=$("#gjgcsrzNx").val();//高工程师
   	   			if(pattern.test(gg)){
   	   			}else{
   	   				gg="0";
   	   			}
   	   			var he=parseInt(yb)-parseInt(g)-parseInt(gg);
   	   			if(pattern.test(bc)){
   		   			$("#ybgzryrzNx_a").val(parseInt(yb)-parseInt(g)-parseInt(gg));
   		   			$("#zjdyjfJs_a").val((parseInt(yb)-parseInt(g)-parseInt(gg))*60);
   	   			}else{
   	   				$("#ybgzryrzNx_a").val("");
   		   			$("#zjdyjfJs_a").val("");
   	   			}
   			}
        }
        function getDate7(){//高级工程师任职时间
        	var jySj = $("#gjgcsrzSj").val();
        	var ws=jySj.charAt(jySj.length - 1);//月
        	var yue=jySj.substr(5, 6);//yue
        	var nian=jySj.substr(0, 4);//年
        	var bn ="<%=nowTime%>";//今年
    		var bb=parseInt(bn)-parseInt(ws);
   			var bc=parseInt(bn)-parseInt(nian)-1;
   			

   			var yuanjySj = $("#jySj").val();
   			var nianyue=yuanjySj.substr(0, 4)+yuanjySj.substr(5, 6);//进院的时间
   			var gcsrzSj= $("#gjgcsrzSj").val();
   			var gnianyue=gcsrzSj.substr(0, 4)+gcsrzSj.substr(5, 6);
   			var jinyuannx= $("#jyhgzNx").val();//进院工作年限
   			
   			if(gnianyue<=nianyue){
   				if (pattern.test(bc)) {
					$("#gjgcsrzNx").val(jinyuannx);
   					$("#zjdyfJsF").val(jinyuannx*120);
				}else{
					$("#gjgcsrzNx").val("");
					$("#zjdyfJsF").val("");
				}
	  			$("#ybgzryrzNx_a").val("0");
	   			$("#zjdyjfJs_a").val("0");
   			}else{
	   			if(parseInt(nian)+2==bn && yue=="01"){
	   				$("#gjgcsrzNx").val(parseInt(bc)+1);
	   				$("#zjdyfJsF").val((parseInt(bc)+1)*120);
	   			}else if(parseInt(nian)+1==bn && yue=="01"){
	   				$("#gjgcsrzNx").val(parseInt(bc)+1);
	   				$("#zjdyfJsF").val((parseInt(bc)+1)*120);
	   			}else if(bc=="-1"){
	   				$("#gjgcsrzNx").val("0");
	   				$("#zjdyfJsF").val("0");
	   			}else {
	   				if (pattern.test(bc)) {
	   					if(yue=="01"){
		   					$("#gjgcsrzNx").val(parseInt(bc)+1);
		   					$("#zjdyfJsF").val((parseInt(bc)+1)*120);
						}else{
							$("#gjgcsrzNx").val(bc);
		   					$("#zjdyfJsF").val(bc*120);
						}
					}else{
						$("#gjgcsrzNx").val("");
						$("#zjdyfJsF").val("");
					}
	   			}
	   			var yb=$("#jyhgzNx").val();//进院
	   			var g=$("#gcsrzNx").val();//工程师
	   			var gg=$("#gjgcsrzNx").val();//高工程师
	   			var gcsrzSj=$("#gcsrzSj").val();//工程师
	   			if(pattern.test(g)){
	   			}else{
	   				g="0";
	   			}
	   			var he=parseInt(yb)-parseInt(g)-parseInt(gg);
	   			if(pattern.test(bc)){
	   				//if(he>=0){
			   			$("#ybgzryrzNx_a").val(parseInt(yb)-parseInt(gg)-parseInt(g));
			   			$("#zjdyjfJs_a").val((parseInt(yb)-parseInt(gg)-parseInt(g))*60);
			   			if(gcsrzSj==""){
   						
   						}else{
   							var nian1=gcsrzSj.substr(0, 4);//年
   							var cha=parseInt(nian)-parseInt(nian1);
   							if(nian1<nian){
   								nian1=yuanjySj.substr(0, 4);//年
   	   							cha=parseInt(nian)-parseInt(nian1);
   							}//alert(nian+"-"+nian1);
   							if(cha>=0){//alert(yb+"-"+gg+"-"+cha);
   								$("#gcsrzNx").val(cha);
   		   						$("#zjdyfJsE").val(cha*100);
        						$("#ybgzryrzNx_a").val(parseInt(yb)-parseInt(gg)-parseInt(cha));
			   					$("#zjdyjfJs_a").val((parseInt(yb)-parseInt(gg)-parseInt(cha))*60);
   							}
   						}
	   				//}else{
	   				//	$.ligerDialog.error('提示：任职年限超过了进院后工作年限请仔细核对！！！');
	   				//}
	   			}else{
	   				$("#ybgzryrzNx_a").val("");
		   			$("#zjdyjfJs_a").val("");
	   			}
   			}
        }
        function getDate8(){//检验师任职时间
        	var jySj = $("#jysrz_Sj").val();
        	var ws=jySj.charAt(jySj.length - 1);//月
        	var yue=jySj.substr(5, 6);//yue
        	var nian=jySj.substr(0, 4);//年
        	var bn ="<%=nowTime%>";//今年
    		var bb=parseInt(bn)-parseInt(ws);
   			var bc=parseInt(bn)-parseInt(nian)-1;
   			
   			var yuanjySj = $("#jySj").val();
   			var nianyue=yuanjySj.substr(0, 4)+yuanjySj.substr(5, 6);//进院的时间
   			var gcsrzSj= $("#jysrz_Sj").val();
   			var gnianyue=gcsrzSj.substr(0, 4)+gcsrzSj.substr(5, 6);
   			var jinyuannx= $("#jyhgzNx").val();//进院工作年限
   			//alert(gnianyue+"@@"+nianyue);
   			if(gnianyue<=nianyue){
   				if (pattern.test(bc)) {
					$("#jysrzNx").val(jinyuannx);
   					$("#zjdyfJsG").val(jinyuannx*100);
				}else{
					$("#jysrzNx").val("");
					$("#zjdyfJsG").val("");
				}
	  			$("#ybgzryrzNx_b").val("0");
	   			$("#zjdyjfJs_b").val("0");
   			}else{
	   			if(parseInt(nian)+2==bn && yue=="01"){
	   				$("#jysrzNx").val(parseInt(bc)+1);
	   				$("#zjdyfJsG").val((parseInt(bc)+1)*100);
	   			}else if(parseInt(nian)+1==bn && yue=="01"){
	   				$("#jysrzNx").val(parseInt(bc)+1);
	   				$("#zjdyfJsG").val((parseInt(bc)+1)*100);
	   			}else if(bc=="-1"){
	   				$("#jysrzNx").val("0");
	   				$("#zjdyfJsG").val("0");
	   			}else {
	   				if (pattern.test(bc)) {
	   					if(yue=="01"){
		   					$("#jysrzNx").val(parseInt(bc)+1);
		   					$("#zjdyfJsG").val((parseInt(bc)+1)*100);
						}else{
							$("#jysrzNx").val(bc);
		   					$("#zjdyfJsG").val(bc*100);
						}
					}else{
						$("#jysrzNx").val("");
						$("#zjdyfJsG").val("");
					}
	   			}
	   			var yb=$("#jyhgzNx").val();//进院
	   			var jy=$("#jysrzNx").val();//检验师
	   			if(pattern.test(bc)){
		   			$("#ybgzryrzNx_b").val(parseInt(yb)-parseInt(jy));
		   			$("#zjdyjfJs_b").val((parseInt(yb)-parseInt(jy))*60);
	   			}else{
	   				$("#ybgzryrzNx_b").val("");
		   			$("#zjdyjfJs_b").val("");
	   			}
   			}
        }
    </script>
</head>
<body>
	<form id="form1" action="Tjy2GjjAction/RL/saveBasic.do" getAction="Tjy2GjjAction/RL/detail.do?id=${param.id}">
		<input type="hidden" id="id" name="id"/>
		<input type="hidden" id="cjr" name="cjr"/>
		<input type="hidden" id="cjSj" name="cjSj"/>
		<input type="hidden" id="nameId" name="nameId"/>
		<input type="hidden" id="orgId" name="orgId"/>
		<input type="hidden" id="brqr" name="brqr"/>
		<input name="data_status" type="hidden" value="1"/>
		
		<h1 id="sg2" align="center" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">住房公积金确认表 </h1></br>
		
		<table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table" id="gjj">
			<tr>
				<th style="border:0px;width:40px"></th>
	        	<th style="border:0px;width:45px"></th>
	        	<th style="border:0px;width:50px"></th>
	        	<th style="border:0px;width:45px"></th>
	        	<th style="border:0px;width:50px"></th>
            	<th style="border:0px;width:45px"></th>
            	
            	<th style="border:0px;width:40px"></th>
	        	<th style="border:0px;width:45px"></th>
	        	
			</tr>
			<tr>
				<td class="l-t-td-left">姓名</td>
       			<td class="l-t-td-right" colspan="2"><input type="text" ltype="text" name="name" id="name" validate="{required:true}" onclick="choosePerson();" ligerui="{iconItems:[{icon:'user',click:choosePerson}]}"/></td>
	         	<td class="l-t-td-left">学历</td>
	         	<td class="l-t-td-right" colspan="">
	         		<input type="text" ltype="select" name="xl" id="xl" ligerui="{data:<u:dict code="TJY2_XL" />}"  validate="required:true" onchange="valueChange()"/>
	         	
	         <%-- 	<u:combo validate="required:true" name="xl" code="TJY2_XL" attribute="onSelected:valueChange"/> --%>
	         	
	         	</td>
<!-- 	 colspan="2"         	<input id="xl" name="xl" type="text" ltype="text" validate="{required:true}"/> -->
	         	<td class="l-t-td-left">试用期满月<br />缴费基数</td>
	         	<td class="l-t-td-right" colspan="2"><input id="syqmyjyJs" name="syqmyjyJs" type="text" ltype="text" ligerui="{readonly:true}" title="不可输入！！"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">部门</td>
	         	<td class="l-t-td-right" colspan="2"><input id="org" name="org" type="text" ltype="text" validate="{required:true}" readonly="readonly"  onclick="chooseOrg();" ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}" title="不可输入！！"/></td>
	         	<td class="l-t-td-left">进院时间</td>
        		<td class="l-t-td-right" colspan=""><input name="jySj" id="jySj" type="text" ltype='date' ligerui="{initValue:'',format:'yyyy-MM'}" onblur="getDate1();"/></td><!--onblur onclick -->
	         	<td class="l-t-td-left">进院后工作年限</td>
	         	<td class="l-t-td-right" colspan="2"><input id="jyhgzNx" name="jyhgzNx" type="text" ltype="text" validate="{required:true}"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">一般工作人员<br/>任职年限（年）</td>
				<td class="l-t-td-left">增加的月缴费<br/>基数</td>
				<td class="l-t-td-left">主任助理<br/>任职时间</td>
        		<td class="l-t-td-right"><input name="zrzlrzSjA" id="zrzlrzSjA" type="text" ltype='date' ligerui="{format:'yyyy-MM'}" onblur="getDate3();" /></td>
				<td class="l-t-td-left">副职任职时间</td>
        		<td class="l-t-td-right"><input name="fzrzSj" id="fzrzSj" type="text" ltype='date' ligerui="{format:'yyyy-MM'}" onblur="getDate4();" /></td>
				<td class="l-t-td-left">正职任职时间</td>
        		<td class="l-t-td-right"><input name="zzrzSj" id="zzrzSj" type="text" ltype='date' ligerui="{format:'yyyy-MM'}" onblur="getDate5();" /></td>
			</tr>
			<tr>
				<td class="l-t-td-right" rowspan="2"><input id="ybgzryrzNx" name="ybgzryrzNx" type="text" ltype="text"/></td>
				<td class="l-t-td-right" rowspan="2"><input id="zjdyjfJs" name="zjdyjfJs" type="text" ltype="text"  /></td>
				<td class="l-t-td-left">主任助理<br/>任职年限（年）</td>
	         	<td class="l-t-td-right"><input id="zrzlrzNx" name="zrzlrzNx" type="text" ltype="text"/></td>
				<td class="l-t-td-left">副职任职年限<br/>（年）</td>
	         	<td class="l-t-td-right"><input id="fzrzNx" name="fzrzNx" type="text" ltype="text"/></td>
				<td class="l-t-td-left">正职任职年限<br/>（年）</td>
	         	<td class="l-t-td-right"><input id="zzrzNx" name="zzrzNx" type="text" ltype="text"/></td>
			</tr>
			<tr>
<!-- 				<td class="l-t-td-right"><input id="ybgzryrzNx" name="ybgzryrzNx" type="text" ltype="text"/></td> -->
<!-- 				<td class="l-t-td-right"><input id="zjdyjfJs" name="zjdyjfJs" type="text" ltype="text"  /></td> -->
				<td class="l-t-td-left">增加的月缴费<br/>基数</td>
	         	<td class="l-t-td-right"><input id="zjdyjfJb" name="zjdyjfJb" type="text" ltype="text"/></td>
				<td class="l-t-td-left">增加的月缴费<br/>基数</td>
	         	<td class="l-t-td-right"><input id="zjdyfJsC" name="zjdyfJsC" type="text" ltype="text"/></td>
				<td class="l-t-td-left">增加的月缴费<br/>基数</td>
	         	<td class="l-t-td-right"><input id="zjdyfJsD" name="zjdyfJsD" type="text" ltype="text"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left" colspan="">一般工作人员<br/>任职年限（年）</td>
				<td class="l-t-td-left" colspan="">增加的月缴费基数</td>
				<td class="l-t-td-left" colspan="2">工程师任职时间</td>
        		<td class="l-t-td-right" colspan=""><input name="gcsrzSj" id="gcsrzSj" type="text" ltype='date' ligerui="{format:'yyyy-MM'}" onblur="getDate6();" /></td>
        		<td class="l-t-td-left" colspan="2">高级工程师任职时间</td>
        		<td class="l-t-td-right" colspan=""><input name="gjgcsrzSj" id="gjgcsrzSj" type="text" ltype='date' ligerui="{format:'yyyy-MM'}" onblur="getDate7();" /></td>
			</tr>
			<tr>
				<td class="l-t-td-right" colspan="" rowspan="2"><input id="ybgzryrzNx_a" name="ybgzryrzNx_a" type="text" ltype="text"/></td>
				<td class="l-t-td-right" colspan="" rowspan="2"><input id="zjdyjfJs_a" name="zjdyjfJs_a" type="text" ltype="text"  /></td>
				<td class="l-t-td-left" colspan="2">工程师任职年限（年）</td>
	         	<td class="l-t-td-right" colspan=""><input id="gcsrzNx" name="gcsrzNx" type="text" ltype="text"/></td>
				<td class="l-t-td-left" colspan="2">高级工程师任职年限（年）</td>
	         	<td class="l-t-td-right" colspan=""><input id="gjgcsrzNx" name="gjgcsrzNx" type="text" ltype="text"/></td>
			</tr>
			<tr>
<!-- 				<td class="l-t-td-right" colspan="2"><input id="ybgzryrzNx" name="ybgzryrzNx" type="text" ltype="text"/></td> -->
<!-- 				<td class="l-t-td-right" colspan="2"><input id="zjdyjfJs" name="zjdyjfJs" type="text" ltype="text"  /></td> -->
				<td class="l-t-td-left" colspan="2">增加的月缴费基数</td>
	         	<td class="l-t-td-right" colspan=""><input id="zjdyfJsE" name="zjdyfJsE" type="text" ltype="text"/></td>
				<td class="l-t-td-left" colspan="2">增加的缴费基数</td>
	         	<td class="l-t-td-right" colspan=""><input id="zjdyfJsF" name="zjdyfJsF" type="text" ltype="text"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left" colspan="">一般工作人员<br/>任职年限（年）</td>
				<td class="l-t-td-left" colspan="">增加的月缴费<br/>基数</td>
				<td class="l-t-td-left" colspan="2">检验师任职时间</td>
        		<td class="l-t-td-right" colspan="4"><input name="jysrz_Sj" id="jysrz_Sj" type="text" ltype='date' ligerui="{format:'yyyy-MM'}" onblur="getDate8();" /></td>
			</tr>
			<tr>
				<td class="l-t-td-right" colspan="" rowspan="2"><input id="ybgzryrzNx_b" name="ybgzryrzNx_b" type="text" ltype="text"/></td>
				<td class="l-t-td-right" colspan="" rowspan="2"><input id="zjdyjfJs_b" name="zjdyjfJs_b" type="text" ltype="text"  /></td>
				<td class="l-t-td-left" colspan="2">检验师任职年限（年）</td>
	         	<td class="l-t-td-right" colspan="4"><input id="jysrzNx" name="jysrzNx" type="text" ltype="text"/></td>
	         	
			</tr>
			<tr>
				<td class="l-t-td-left" colspan="2">增加的缴费基数</td>
        		<td class="l-t-td-right" colspan="4"><input name="zjdyfJsG" id="zjdyfJsG" type="text" ltype="text"/></td>
				
			</tr>
			
			<tr id="zongjs">
				<td class="l-t-td-left">总缴费基数</td>
        		<td class="l-t-td-right"><input name="zjfJs" id="zjfJs" type="text" ltype="text"  ligerui="{readonly:true}" title="不可输入！！"/></td>
	         	<td class="l-t-td-left">调整后月缴费金额</td>
	         	<td class="l-t-td-right" colspan="2"><input id="tzhyjfJe" name="tzhyjfJe" type="text" ltype="text" ligerui="{readonly:true}" title="不可输入！！"/></td>
	         	<td class="l-t-td-left">个人缴费金额</td>
        		<td class="l-t-td-right" colspan="2"><input name="mqjfJe" id="mqjfJe" type="text" ltype="text" ligerui="{readonly:true}" title="不可输入！！"/></td>
			</tr>
		</table>
		
	</form>
</body>
</html>