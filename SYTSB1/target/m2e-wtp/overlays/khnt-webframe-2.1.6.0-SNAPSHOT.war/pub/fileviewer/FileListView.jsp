<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        var fileList
        //jQuery页面载入事件
        $(function () {
            $("#layout1").ligerLayout({border:false, space:0});
            var url = encodeURIComponent("${param.url}");
            url = url.replace(/%2F/g,'/').replace(/%3F/g,'?').replace(/%3D/g,'=');
            $.getJSON(url,function(data){
            	if(data.success){
            		$("#basepath").val(data.basepath);
            		fileList = data.data;
            		showImg(fileList);
					$(".f-list1-select-div-img img").each(function(){
						if ($(this).attr("src").toString().indexOf("loading.gif")>0) {
							$(this).attr("src",$(this).attr("srctemp"));//return;
							$(this).on("load",function(){
								if ($(this).width()>80) {
									$(this).width(80)
								}
							})
						}

					})
            	}
            })
        });
        function showImg(fileList) {
            $.each(fileList, function (i, data) {
            	//alert(data.filePath)
            	//alert(data.isDirectory)
            	//alert(data.uppath)
                var fileSrc = "pub/fileviewer/icons/loading.gif";
                var fileSrcTemp = "${pageContext.request.contextPath}/"+data.relatePath;
                var fileName = data.relatePath;
                var imageName = fileName.substring(fileName.lastIndexOf("/")+1);
				var imgWH=[80,80];
				if(data.isDirectory){
					fileSrc = "k/kui/images/file-type/48/folder.png"; imgWH=[80,80]
				}else{
					var fileExt = fileName.substring(fileName.indexOf(".") + 1, fileName.length).toLowerCase();
					if (fileExt == "doc" || fileExt == "docx") { fileSrc = "k/kui/images/file-type/48/doc.png"; imgWH=[48,48]}//word图标
					else if (fileExt == "ppt" || fileExt == "pptx") { fileSrc = "k/kui/images/file-type/48/ppt.png"; imgWH=[48,48]}//ppt
					else if (fileExt == "xls" || fileExt == "xlsx") { fileSrc = "k/kui/images/file-type/48/xls.png"; imgWH=[48,48]}//xls
					else if (fileExt == "vsd" || fileExt == "vsdx") { fileSrc = "k/kui/images/file-type/48/vsd.png"; imgWH=[48,48]}//vsd
					else if (fileExt == "pdf") { fileSrc = "k/kui/images/file-type/48/pdf.png"; imgWH=[48,48]}//pdf图标
					else if (fileExt == "ai" || fileExt == "avi" || fileExt == "rmvb" || fileExt == "3gp") { fileSrc = "k/kui/images/file-type/48/avi.png"; imgWH=[48,48]}//媒体图标
					else if (fileExt == "css") { fileSrc = "k/kui/images/file-type/48/css.png"; imgWH=[48,48]}//css图标
					else if (fileExt == "dll") { fileSrc = "k/kui/images/file-type/48/dll.png"; imgWH=[48,48]}//dll
					else if (fileExt == "exe") { fileSrc = "k/kui/images/file-type/48/exe.png"; imgWH=[48,48]}//exe
					else if (fileExt == "swf" || fileExt == "fla"||fileExt == "flv") { fileSrc = "k/kui/images/file-type/48/swf.png"; imgWH=[48,48]}//swf
					else if (fileExt == "htm" || fileExt == "html") { fileSrc = "k/kui/images/file-type/48/html.png"; imgWH=[48,48]}//html
					else if (fileExt == "js") { fileSrc = "k/kui/images/file-type/48/js.png"; imgWH=[48,48]}//js
					else if (fileExt == "mp3") { fileSrc = "k/kui/images/file-type/48/mp3.png"; imgWH=[48,48]}//mp3
					else if (fileExt == "rar" || fileExt == "zip") { fileSrc = "k/kui/images/file-type/48/rar.png"; imgWH=[48,48]}//rar
					else if (fileExt == "js") { fileSrc = "k/kui/images/file-type/48/js.png"; imgWH=[48,48]}//js
					else if (fileExt == "xml") { fileSrc = "k/kui/images/file-type/48/xml.png"; imgWH=[48,48]}//xml
					else if (fileExt == "txt") { fileSrc = "k/kui/images/file-type/48/txt.png"; imgWH=[48,48]}//txt
					else if (fileExt =="png" || fileExt =="gif" ||fileExt =="jpg" ||fileExt =="jpeg"){}
					else {fileSrc = "k/kui/images/file-type/48/default.icon.png"; imgWH=[48,48]}
				}
                $("<div class='f-list1-select-div'>" +
					"<div class='f-list1-select-div-img'>"+
					"<img srctemp='"+fileSrcTemp+"' src='" + fileSrc + "' border='0' dir='"+data.isDirectory+"' updir='"+data.uprealtePath+"'  file = '"+data.relatePath+"' style=''  />"+
					"</div>" +
					"<div class='f-list1-select-div-div'>" + imageName + "</div>" +
				"</div>").click(
				function () {
					//选中样式
					//$("#div_ID").hover
					//赋值
					var obj=$(this);
					$(".f-list1-select-div-down1").attr("class","f-list1-select-div");
					$(".f-list1-select-div-down").attr("class","f-list1-select-div");
					
					if(obj.find("img").attr("dir")=="false"){
						if (obj.attr("class")=="f-list1-select-div") {
							obj.attr("class","f-list1-select-div-down");
						}
						else {
							obj.attr("class","f-list1-select-div");
						}
						parent.$('#path').val(fileName);
						parent.returnValue = fileName;
					}else{
						if (obj.attr("class")=="f-list1-select-div") {
							obj.attr("class","f-list1-select-div-down1");
						}
						else {
							obj.attr("class","f-list1-select-div");
						}
						parent.$('#path').val("");
						parent.returnValue = "";
					}
				}).dblclick(function(){
					var obj=$(this);
					if(obj.find("img").attr("dir")=="true"){
						//parent.$("#uppath").val(obj.find("img").attr("updir"))
						parent.$("#toolbar2").ligerToolBar().setEnabled({up:true});
						fileName.substring(fileName.lastIndexOf("/")+1);
						var urlobj = "${param.url}";
						if(urlobj.indexOf("?")!=-1){
							urlobj = urlobj.substring(0,urlobj.indexOf("?"));
						}
						var url = urlobj+"?relatePath="+obj.find("img").attr("file");
						parent.$("#rightFrame").attr("src", "pub/fileviewer/FileListView.jsp?url="+url);
					}
				}).appendTo($("body"));
            })
        }
    </script>
	<style type="text/css">
		.f-list1-select-div {float:left;margin:5px;padding:10px;width:80px;height:80px;position:relative;display:inline;text-align:center;cursor:pointer;border:1px solid #FFFFFF;}
		.f-list1-select-div-down {float:left;margin:5px;padding:10px;width:80px;height:80px;position:relative;text-align:center;cursor:pointer;border:1px solid #94C0F8;background:url("pub/fileviewer/icons/select-div-down.gif") right top no-repeat #E3EFFD;}
		.f-list1-select-div-down1 {float:left;margin:5px;padding:10px;width:80px;height:80px;position:relative;text-align:center;cursor:pointer;border:1px solid #94C0F8;background:right top no-repeat #E3EFFD;}
		.f-list1-select-div-img {border:0px solid #FF0000;width:100%;height:70px;overflow:hidden;}
		.f-list1-select-div-div {width:100%;/*height:14px;*/text-align:center;overflow:hidden;text-overflow:ellipsis;white-space: nowrap;position:absolute;left:0px;bottom:5px;}

		/*.f-list2-select-div {width:100%;height:100%;border:1px solid #FFFFFF;}
				.f-list2-select-div-down {width:100%;height:100%;border:1px solid #94C0F8;background:url("images/select-div-down.gif") right no-repeat #E3EFFD;}
				.f-list3-select-div {width:100%;height:100%;border:1px solid #FFFFFF;}
				.f-list3-select-div-down {width:100%;height:100%;border:1px solid #94C0F8;background:url("images/select-div-down.gif") right bottom no-repeat #E3EFFD;}*/
	</style>
</head>
<body style="padding:0px; overflow:auto;position:relative;">
<%--<div id="showImgTab" style="height: 390px; overflow:auto;"></div>--%>
<input type="hidden" id="basepath"/>
</body>
</html>