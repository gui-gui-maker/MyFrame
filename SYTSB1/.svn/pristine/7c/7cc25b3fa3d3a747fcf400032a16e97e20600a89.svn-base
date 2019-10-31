<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u"%>
<%
	String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);   
	String basePath2 =  request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);   

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>内江压力容器定期检验原始记录</title>
<%@include file="/rtbox/public/base.jsp" %>
<meta name="keywords" content="报表工具" />
<meta name="description" content="报表工具">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Theme CSS -->
<link rel="stylesheet" type="text/css" href="rtbox/app/templates/default/assets/skin/default_skin/css/theme.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="rtbox/app/templates/default/assets/js/html5shiv.js"></script>
  <script src="rtbox/app/templates/default/assets/js/respond.min.js"></script>
<![endif]-->
<script type="text/javascript"> var relColumn='fk_report_id=${param.fk_report_id}&fk_inspection_info_id=${param.fk_inspection_info_id}';</script>

<script type="text/javascript">
var markOptions = <u:dict code="problem_nature_type"></u:dict>;
var fk_report_id="${param.fk_report_id}";
var input="${param.input}";
var info_id="${param.id}";
var pageStatus="${param.pageStatus}";
var rt_code="${param.code}";
var status = "${param.status}";
var check_op ="${param.check_op}";
var basePath="<%=basePath%>";
var basePath2 = "<%=basePath2%>"
function saveRight(){
	try {
		rightFrame.submitForm();
	} catch (e) {
		// TODO: handle exception
	}
}
function fontSizes(){
	var objS = document.getElementById("fontSizes");
	var index = objS.selectedIndex;
	var grade = objS.options[index].value;
	rightFrame.fontSizes(grade);
}
function markColor(){
	rightFrame.markColor();
}
function fontFamilys(){
	var objS = document.getElementById("fontFamilys");
	var index = objS.selectedIndex
	var grade = objS.options[index].value;
	rightFrame.fontFamilys(grade);
}
function sup(){
	rightFrame.sup();
}
function sub(){
	rightFrame.sub();
}
function fontBold(){
	rightFrame.fontBold();
}
function fontItalic(){
	rightFrame.fontItalic();
}
function copyToRow(){
	var obj = rightFrame.bindInputEl;
	rightFrame.copyToRow(obj);
}
function sequence(){
	var obj = rightFrame.bindInputEl;
	var data = {};
	$.ligerDialog.open({ 
		title: '填写序号规则', 
		width: 350,
		height: 200, 
		url: "rtbox/app/templates/default/fill_sequence.jsp", 
		buttons: [
            {
                text: '确定', onclick: function (item, dialog) {
                    var data = dialog.frame.getData();
                    var reg = /^[0-9]*$/;
                    
                    if(reg.test(data.initValue)){
                    	rightFrame.sequence(obj,data.initValue,data.prefix,data.suffix);
                    	dialog.hide();
                    }else{
                    	top.$.notice("初始序号必须为正整数！");
                    	return; 
                    }
                    
                }
            },
            {
                text: '取消', onclick: function (item, dialog) {
                    dialog.hide();
                }
            }
        ]
    });
	
}

function drawMarker(){
	var obj = rightFrame.focus_element;
	rightFrame.label(obj);
}

$(function() {
	if(document.getElementById('tsColor')!=undefined){
		document.getElementById('tsColor').onchange = function(){
			rightFrame.tsColor(this.value);
		};
	}
	
	//详情时影藏按钮工具栏
	if(status=="detail"){
		$("#treeH").css("padding-top","0px");
	}
	var width = $(window).width();
	if(width>1024){
		$("#rightFrame").attr("width",width-300)
	}
	
});
</script>
<script type="text/javascript" src="rtbox/app/recordTemplates/js/tpl01.js"></script>
<!-- <script type="text/javascript" src="rtbox/app/templates/default/tpl02.js"></script>  -->
<script type="text/javascript" src="/k/kui/frame/main.js"></script> 
<style type="text/css">
	
	.test11 td{ padding:10px 0; }
	.input11{ height: 26px; line-height: 26px; border:1px solid #ddd;   }
	.testl{ text-align: right; padding-right: 10px;  }

	/*2017-09-06 09:32:23*/
	
	.bzhu { position: relative; width: 200px;   }
	.flag { /*border: 1px dashed #e22118;*/ position: relative; padding-left:5px;  }
	.bzhu span{ position: absolute; left: -1px; top:-1px;border:4px solid transparent; border-top:4px solid #e22118; border-left:4px solid #e22118;  }
	.bzhu_box{ 
		border: 1px solid #e22118; 
		position: absolute; 
		top:26px; 
		display: none;
		width: 250px; 
		min-height: 100px; 
		padding: 5px;
		z-index:100;
		-webkit-box-shadow: 2px 3px 0px 0px rgba(0,0,0,.15);
		box-shadow:2px 3px 0px 0px rgba(0,0,0,.15);
		background-color:#fff;
	}
	.bzhu:hover .bzhu_box,
	.bzhu:focus .bzhu_box,
	.bzhu:active .bzhu_box,
	.bzhu.active .bzhu_box{
		display: block;
	}




</style>
</head>

<body class="dashboard-page">

<!-- Start: Theme Preview Pane --> 

<!-- End: Theme Preview Pane --> 

<!-- Start: Main -->
<div id="main"> 
	<!-- Start: Header -->
	<div class="navbar navbar-fixed-top navbar-shadow " style="min-height:20px;height: 20px;">
        <ul class="nav navbar-nav navbar-left" style="min-height:20px;">
				<li>
							<div>
								
							</div>
						</li>
						<li>
							<div>
								<a href="javascript:void(0);" onclick="markColor();"> <span
									title="上色">上一页</span>
								</a>
							</div>
						</li>
						<li>
							
						</li>
						<li>
							<div>
								<a href="JavaScript:drawMarker();"> <span
									title="标注">下一页</span>
								</a>
							</div>
						</li>
				</ul>
	</div>
	<!-- End: Sidebar Left --> 
	
	<!-- Start: Content-Wrapper -->
	<div id="content_wrapper" align="center" style="margin-left: 0px;margin-top: 100px;margin-right:10px;" align="cengter">
		<div  class="cont_center" style="width: 90%">
<!-- 		<iframe id="rightFrame" frameborder="0" name="rightFrame" width="100%" height="100%" scrolling="no" src="test.html" /></iframe> -->
		<iframe marginwidth="0" id="rightFrame" name="rightFrame" marginheight="0" frameborder="0" valign=top 
		src="rtbox/app/recordTemplates/show_right.jsp?code=NJTS_YLRQDQJYJL&pageName=index1.jsp" name="rtree"
	                width=99% height=900 scrolling="no" allowTransparency>
	     </iframe>
		</div>


	</div>
	<!-- End: Content-Wrapper --> 
	
</div>
<!-- End: Main --> 

<!-- BEGIN: PAGE SCRIPTS --> 

<!-- jQuery --> 
<!-- <script src="vendor/jquery/jquery-1.11.1.min.js"></script>  -->
<!-- <script src="vendor/jquery/jquery_ui/jquery-ui.min.js"></script>  -->

<!-- Theme Javascript --> 
<script src="rtbox/app/templates/default/assets/js/demo/demo.js"></script> 
<script src="rtbox/app/templates/default/assets/js/main.js"></script> 

<!-- END: PAGE SCRIPTS -->

</body>
<script type="text/javascript">

	function sf(){
		try{
			var fsEl = window.rightFrame.focus_element;
			share.data("focus_element",fsEl);
			top.$.dialog({
	    		width:500,
	    		height:420,
	    		lock:false,
	    		title:"特殊字符",
	    		content:"url:rtbox/app/templates/default/font/fonts.jsp",
	    		data : {
					"window" : window
				}
	    	});
		}catch(err){
			console.log(err.description);
		}
		
	}
</script> 
</html>
