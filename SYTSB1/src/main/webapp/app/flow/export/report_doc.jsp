<%@page import="com.khnt.utils.StringUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<%
String realPath1 = request.getSession().getServletContext().getRealPath("upload");

System.out.print(">>>>>>>"+realPath1+"<<<<<<");
String[] id = realPath1.split("\\\\");
String realPath="";
for(int i=0;i<id.length;i++){
	if(StringUtil.isNotEmpty(realPath)){
	realPath = realPath +"/"+id[i];
	}else{
		realPath = id[0];
	}
}
%>
<!--  -->
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />

<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<%@include file="/k/kui-base-form.jsp"%>
<style type="text/css">
    .l-icon-exportDoc{background:url('k/kui/images/16/icons/word2.png') no-repeat center;}
    .l-icon-printPreview{background:url('k/kui/images/16/icons/search2.png') no-repeat center;}
    .l-icon-fullScreen{background:url('k/kui/images/16/icons/div-drag.gif') no-repeat center;}
</style>
<script src="app/pub/office/ntkoofficecontrol.js" type="text/javascript"></script>
<script src="app/pub/office/editor.js" type="text/javascript"></script>
<script type="text/javascript">
	var pdf_path = "";
	var basepath = "${pageContext.request.contextPath}/";
	var toolBar;//工具条
	var time = new Date( ); //获得当前时间
	var m = time.getMonth();
	var d = time.getDate();
	if(m<10){
		m = "0"+(m+1);
	}
	if(d<10){
		d = "0"+d;
	}
	var day = time.getFullYear()+m+d;//当前的日期
	$(function(){
		$(".layout").ligerLayout({
			bottomHeight:30,
			space : 0
		});
		
		//加载文档编辑器
		//createNtkoEditor("editor","<sec:authentication property="principal.name" htmlEscape="false" />");
	    
	   
	    
	});
	
	
	function initPage(){
		$("body").mask("正在处理，请稍后！");
		initToolBar();
		createNtkoEditor("editor_container");
		TANGER_OCX_OBJ.SetReadOnly(true);
		TANGER_OCX_OBJ.Menubar = false;
		TANGER_OCX_OBJ.Statusbar = false;
		TANGER_OCX_OBJ.Toolbars = false;
		 //加载pdf插件
		addPdfPlugin();
		 var url = "D:/TEMP/"+"${param.report_sn}"+".pdf";
		 //CO-TD201564126
		TANGER_OCX_OBJ.OpenLocalFile("D:/TEMP/${param.report_sn}.pdf");
		saveCurrentDocument();
		//TANGER_OCX_OBJ.OpenFromURL($("base").attr("href") + "/upload/20160125/CO-TD201567683/CO-TD201567683.pdf");
		//} 
	}
	
  	function initToolBar(){
    	var saveBtn;
    	var closeBtn;
    	var printBtn;
    	var printPreviewBtn;
    	var setLayoutBtn;
    	var fullScreenBtn;
    	var subBtn;
    	var backBtn;
    
		var itemArr=new Array();
		
	    toolBar=$("#toolbar").ligerButton({
			items:itemArr
		});
    }
	
  	function saveCurrentDocument() {
  		
  		var folder = api.data.day+"/"+"${param.report_sn}";
  		
		var response = TANGER_OCX_OBJ.SaveToURL("${pageContext.request.contextPath}/fileupload/fileUp.do?attType=0&id=&folder="+folder+"&fname=${param.report_sn}","docFile", "","cs.pdf");
		
		
		var data = $.parseJSON(response);
		if(data.success){
			$.post("inspectionInfo/basic/expPdfFlag.do",{"date":api.data.day,"id":api.data.id},function(res){
	       		   if(res.success){
	       			 
	       			var report_id = res.report_type;
					//alert(report_id+"--------------id---")
					$.ajax({
						url : "report/item/getItem.do",
						type : "POST",
						async:false,
						datatype : "json",
						data : {
							id : report_id
						},
						success : function(data, stats) {
							if (data["success"]) {
								
								
								for (var i = 0; i < data.datalist.length; i++) {
									var dataR = data.datalist[i];
									if(dataR.is_electronic_seal_man=='1'){
										 gz(api.data.pdfPath,api.data.report_sn,dataR.page_index,
												dataR.xzhou,dataR.yzhou,"1234",
						       					"D:/biceng/certs/JYZY.pfx","D:/biceng/seals/JYZY.xml"); 
									}
									//alert(dataR.page_index+"---"+dataR.is_electronic_seal_man+"---"+dataR.x+"----"+dataR.y)
								}
							}
						}
					});
					
					/*  //转swf
		       			$.post("inspectionInfo/basic/pdf2Swf.do?pdfPath="+folder+"/${param.report_sn}.pdf&swfPath="+folder+"&swfName=${param.report_sn}",{},function(res){
			        		   if(res.success){
			        			  // alert("盖章并上传成功！");  
			        			  
			        		   }
			        	   })  */
		        	   api.close();
	       		   }
	       	   })
			//top.$.notice("文件保存成功！");
			
		}
		else{
			alert("文件保存失败！");
		} 
		
	}
  	
  	

	function gz(pdfPath,report_sn,signPage,sealPstX,sealPstY,certPwd,certPath,sealXmlPath){
			//初始化、指定处理函数、发送请求的函数
			if (window.XMLHttpRequest) //Mozila
			{
				http_request = new XMLHttpRequest();
			} else if (window.ActiveXobject) //IE
			{
			   try
			   {
					 http_request = new ActiveXObject("Msxml2.XMLHTTP");
			   }
			   catch (e)
			   {
				   try{
						 http_request = new ActiveXObject("Microsoft.XMLHTTP");
					   }
					catch (e) { }
			   }
			}
			 
			if (!http_request)  // 异常，创建对象实例失败
			{
			   alert("不能创建XMLHttpRequest实例！！");
			   return false;
			}
		
			// 指定当服务器返回信息时客户端的处理方式
			http_request.onreadystatechange = processRequest;
			var url ="${pageContext.request.scheme}://${pageContext.request.serverName}:8081/gxBatchSealing/services/sign.jsp"; 
			//var url ="http://localhost:8081/gxBatchSealing/services/sign.jsp"; 
			//var url ="http://jx.scsei.org.cn:8081/gxBatchSealing/services/sign.jsp"; 
			var sendContent = "<root><certPwd>"+certPwd+"</certPwd>"+
								"<certPath>"+certPath+"</certPath>"+
								"<sealXmlPath>"+sealXmlPath+"</sealXmlPath>"+
								"<pdfPath>"+pdfPath+"</pdfPath>"+
								"<signPage>"+signPage+"</signPage>"+
								"<sealPstX>"+sealPstX+"</sealPstX>"+
								"<sealPstY>"+sealPstY+"</sealPstY>"+
								"</root>";
								pdf_path = pdfPath;
			http_request.open("POST",url,false);
			http_request.send(sendContent);
			
	}

    //******************************************************************
    function processRequest()
    {
		//alert("判断对象状态-----------"+http_request.readyState);
        if (http_request.readyState == 4) // 判断对象状态
        {
           if (http_request.status == 200)  // 请求结果已经成功返回
           {
        	   
        	
        	   
        	   /* alert(1);
        	   $.post("inspectionInfo/basic/expPdfFlag.do",{"date":day,"id":api.data.id},function(res){
        		   if(res.success){
        			   alert("盖章并上传成功！");  
        		   }
        	   }) */
        	   
             //alert(http_request.responseText);
			  //document.getElementById("getTxt").value=http_request.responseText;
           }
           else  //页面不正常
           {
              alert("你请求的页面不正常");
           }
        }
    }
	
</script>
</head>
<body onload="initPage();">
<div class="layout">
	<div position="center" id="editor_container" style="width:100%;height:100%"></div>
	<div position="bottom">
		<div class="div1" id="toolbar" style="padding:1px;text-align:right;"></div>
	</div>
</div>
<iframe id="export_doc_iframe" style="display:none;">
</iframe>
</body>
</html>