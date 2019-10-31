<%@page import="com.khnt.pub.fileupload.service.AttachmentManager"%>
<%@page import="com.khnt.base.Factory"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head pageStatus="${param.pageStatus}">
        <%@include file="/k/kui-base-form.jsp" %>
        <script src="pub/fileupload/js/attachment.js" type="text/javascript"></script>
        <script type="text/javascript">
            var sysBaseRoot = "${pageContext.request.contextPath}/";
            $(function() {
                $("#layout1").ligerLayout({border: false, space: 0});
                loadPageImg();
            });

            function loadPageImg() {
                $.ajax({
                    url: 'fileupload/attachmentList.do',
                    dataType: 'text', async: false,
                    data: {"businessId": '${param.fileId}'},
                    success: function(data) {
                        var fileList = eval("(" + data + ")").data;
                        showImg(fileList);
                        $(".f-list1-select-div-img img").each(function() {
                            if ($(this).attr("src").toString().indexOf("loading.gif") > 0) {
                                $(this).attr("src", $(this).attr("srctemp"));
                                //2012-6-4 下午6:27 lybide
                                $(this).on("load", function() {
                                    if ($(this).width() > 80) {
                                        $(this).width(80)
                                    }
                                })
                            }

                        })
                    }
                });
            }

            //文件类型图标对照表
            var fileTypeRep = {
                "doc": "doc.gif",
                "docx": "doc.gif",
                "ppt": "ppt.gif",
                "pptx": "doc.gif",
                "xls": "xls.gif",
                "xlsx": "xls.gif",
                "vsd": "vsd.gif",
                "pdf": "pdf.gif",
                "ai": "avi.gif",
                "avi": "avi.gif",
                "rmvb": "avi.gif",
                "3gp": "avi.gif",
                "mkv": "avi.gif",
                "mp4": "avi.gif",
                "css": "css.gif",
                "dll": "dll.gif",
                "exe": "exe.gif",
                "swf": "swf.gif",
                "fla": "swf.gif",
                "flv": "swf.gif",
                "htm": "html.gif",
                "html": "html.gif",
                "js": "js.gif",
                "mp3": "mp3.gif",
                "rar": "rar.gif",
                "zip": "rar.gif",
                "xml": "xml.gif",
                "txt": "txt.gif",
                "jpg": "jpg.png",
                "png": "png.png",
                "gif": "gif.png",
                "bmp": "bmp.png"
            };

            function showImg(fileList) {
                var attachmentFolder = "<%=Factory.getSysPara().getProperty("web_photo_address")%>/";
                $.each(fileList, function(i, data) {
                    var fileSrc = "app/webmanage/icons/loading.gif";
                    var fileSrcTemp = data.relativePath;
                    var fileName = data.name;
                    var fileId = data.id;
                    var imgWH = [80, 80];
                    var fileExt = fileName.substring(fileName.indexOf(".") + 1, fileName.length);
                    if (fileTypeRep[fileExt])
                        fileSrc = "app/webmanage/icons/" + fileTypeRep[fileExt];
                    else
                        fileSrc = "app/webmanage/icons/file.png";

                    $("<div class='f-list1-select-div' id='" + fileId + "' onmouseover='showDelBtn(this)' onmouseout='hideDelBtn(this)'>" +
                            "<div class='f-list1-select-div-img'><img srctemp='" + fileSrcTemp + "' src='" + fileSrc + "' alt='" + fileName + "' border='0' style='width:32px;height:32px;'/></div>" +
                            "<div class='f-list1-select-div-div'>" + fileName +
                            "</div><div class='f-list1-select-div-delbt'><input type='button' value='删除' class='btn_del' onclick='deleteImg(this,\"" + fileId + "\")' /></div>" +
                            "</div>").click(function() {
                        //赋值
                        var obj = $(this);
                        $(".f-list1-select-div-down").attr("class", "f-list1-select-div");
                        if (obj.hasClass("f-list1-select-div")) {
                            obj.attr("class", "f-list1-select-div-down");
                        }
                        else {
                            obj.attr("class", "f-list1-select-div");
                        }
                        parent.$('#fileUrl').val(attachmentFolder + data.relativePath);
                        parent.returnValue = attachmentFolder + data.relativePath;
                        if (fileExt == 'jpg' || fileExt == 'png' || fileExt == 'gif' || fileExt == 'bmp')
                            parent.$("#preview").html("<img src='" + attachmentFolder + data.relativePath + "' /><p>" + fileName + "</p>");
                        else
                            parent.$("#preview").html("<div class='nopreview'>无法预览</div><p>" + fileName + "</p>");
                    }).appendTo($("body"));
                })
            }
            //鼠标移动到图片框上显示删除按钮
            function showDelBtn(ctr) {
                $(ctr).addClass("f-list1-select-div-hover");
                $(ctr).find(".f-list1-select-div-div").hide();
                $(ctr).find(".f-list1-select-div-delbt").show();
            }
            //鼠标从图片框上移出隐藏删除按钮
            function hideDelBtn(ctr) {
                $(ctr).removeClass("f-list1-select-div-hover");
                $(ctr).find(".f-list1-select-div-div").show();
                $(ctr).find(".f-list1-select-div-delbt").hide();
            }
            //删除图片
            function deleteImg(btn, id) {
                try {
                    if (confirm("确定删除文件？")) {
                        deleteFile(id, function(response) {
                            if ($(btn).parent().parent().hasClass("f-list1-select-div-down"))
                                try {
                                    window.parent.fileUrl.value = "";
                                } catch (err) {
                                }
                            $(btn).parent().parent().remove();
                        });
                    }
                    event.stopPropagation();
                }
                catch (error) {
                }
            }
        </script>
        <style type="text/css">
            .f-list1-select-div {z-index:100;float:left;margin:3px;padding:10px;width:60px;height:60px;position:relative;display:inline;text-align:center;cursor:pointer;border:1px solid #FFFFFF;}
            .f-list1-select-div-down {float:left;margin:3px;padding:10px;width:60px;height:60px;position:relative;text-align:center;cursor:pointer;border:1px solid #94C0F8;background:url("app/webmanage/icons/select-div-down.gif") right top no-repeat #E3EFFD;}
            .f-list1-select-div-hover {float:left;margin:3px;padding:10px;width:60px;height:60px;position:relative;text-align:center;cursor:pointer;background:url("app/webmanage/icons/select-div-down.gif") right top no-repeat #E3EFFD;}
            .f-list1-select-div-img {border:0px solid #FF0000;width:100%;height:40px;overflow:hidden;}
            .f-list1-select-div-div {width:100%;height:14px;text-align:center;overflow:hidden;text-overflow:ellipsis;white-space: nowrap;position:absolute;left:0px;bottom:5px;}
            .f-list1-select-div-delbt{width:100%;text-align:center;height:22px;left:0;position:absolute;bottom:0;display:none;z-index:101;}
            .btn_del {background:url(../../../k/app/skins/Aqua/images/kui/btnbj1.png) repeat-x; border:#1c6a9e solid 1px; color:#fff; font-weight:bold;width:40px;height:20px;}
            /*.f-list2-select-div {width:100%;height:100%;border:1px solid #FFFFFF;}
                            .f-list2-select-div-down {width:100%;height:100%;border:1px solid #94C0F8;background:url("images/select-div-down.gif") right no-repeat #E3EFFD;}

                            .f-list3-select-div {width:100%;height:100%;border:1px solid #FFFFFF;}
                            .f-list3-select-div-down {width:100%;height:100%;border:1px solid #94C0F8;background:url("images/select-div-down.gif") right bottom no-repeat #E3EFFD;}*/
        </style>
    </head>
    <body style="padding:0px; overflow:auto;position:relative;">
        <%--<div id="showImgTab" style="height: 390px; overflow:auto;"></div>--%>
    </body>
</html>