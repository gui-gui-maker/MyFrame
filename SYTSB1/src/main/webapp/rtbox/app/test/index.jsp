<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String code = request.getParameter("code");
String pageName = request.getParameter("pageName");
String pagePath = "/rtbox/app/templates/MSTS_ZWDTDJBG/index3.jsp";//"/rtbox/app/templates/"+code+"/"+pageName;

%>
<style type="text/css">
		body, table, tr, th, td, input { margin: 0; padding: 0; }
		.dtitle { text-align: center; font-weight: bold; font-size:24px; font-family: 'simsun'; }
		#layout2 {width: 90%; font-family: 'simsun';background-color:#fff; }
		#layout2 table { position: relative; font-size: 14px; table-layout: fixed; vertical-align: top; border-collapse: collapse!important; width: 90%; }
		#layout2 table tr{min-height:6mm;height:auto; }
		#layout2 table.two { border: 1px solid #000; cellpadding:1; cellspacing:1; }
		#layout2 table.two td { border: 1px solid #000; }
		#layout2 table.l-checkboxlist-table { border: 0px solid #fff; cellpadding:1; cellspacing:1; }
		#layout2 table.l-checkboxlist-table td { border: 0px solid #fff; }
		/* .cont_center{width:1075px ;} */
		#layout2 table.two td.noborder1 {border-right:0px solid #fff!important;}
		#layout2 table.two td.noborder2 {border-left:0px solid #fff!important;}
		#layout2 table p { margin: 0;width: 90%;}
		#layout2 table input {width:90%;padding: 0px;text-align: center;padding: 0px;    }
		 #layout2 table input.iptw2 {width:72%;}
		#layout2 table textarea { width: 90%;border: 1px solid #fff;}
		#layout2 table.l-checkboxlist-table input { width:20px}
		#layout2 table p.bianma {text-align:right;}
		.l-text{height:100%;}
		.l-text-field{position:static!important;width:100%}
		.l-text-wrapper{width:95%}
		#docx4j_tbl_4 tr td:nth-child(1){  text-align: left;}
		#docx4j_tbl_4 tr td:nth-child(1) p{  text-align: left;}
		#docx4j_tbl_4 tr td:nth-child(1) p span{  text-align: left;}
		#docx4j_tbl_4 tr td:nth-child(1) span{  text-align: left;}
</style>
</head>
<%@include file="/rtbox/public/base.jsp"%>
<script src="/rtbox/public/jQuery/jquery-1.10.2.js"></script>
<!-- <link rel="stylesheet" href="/rtbox/app/test/css/jquery-ui.min.css"> 
<script src="/rtbox/app/test/js/jquery-ui.min.js"></script>-->
<script>
    $(function() {
       /*  $( "td" ).resizable({
            start: function (event, ui) {

                console.log("缩放开始"+JSON.stringify(ui.size));

            },
            stop: function (event, ui) {

                console.log("缩放结束"+JSON.stringify(ui.size));

            }
        });
        $( "td" ).draggable({
            start: function (event, ui) {

                console.log("移动开始"+JSON.stringify(ui.position));

            },
            stop: function (event, ui) {

                console.log("移动结束"+JSON.stringify(ui.position));

            }
        });
        $( "p" ).draggable(); */
        $("span").attr("contenteditable","true");
    });
    
    function getFormContent(){
    	alert($("#layout2").html());
    	var path = "<%=pagePath%>";
    	var content = $("#layout2").html();
    	$.post("com/rt/page/saveIndexChange.do",{'path':path,'content':content},function(res){
    		if(res.success){
    			alert("保存成功");
    		}else{
    			alert("保存失败");
    			
    		}
    	})
    }
</script>
<body>
<div style="background-color: green" align="center"><input type="button" id="save" onclick="getFormContent()" value="测试保存"></div>
<div id="layout" style="width: 99.8%">
		<input type="hidden" id="inputFocus" name="inputFocus" />
		 <input type="hidden" id="code_ext" name="code_ext"  value="${param.code_ext}"/> 
		<div position="center" style="overflow-y:auto;width: 100%" align="center">
			<form id="formObj" action="reportItemValueAction/saveMap.do"
						getAction="reportItemValueAction/detailMap.do" >
				
					<jsp:include page="<%=pagePath %>"></jsp:include>
				
				
			</form>
		</div>
	</div>
</body>
</html>
