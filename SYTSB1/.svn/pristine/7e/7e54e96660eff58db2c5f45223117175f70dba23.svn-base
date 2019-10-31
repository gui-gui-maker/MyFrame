<%@page import="com.khnt.base.Factory"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head id="main_head">
        <%@include file="/k/kui-base-form.jsp"%>
        <title></title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/fileupload/js/plupload.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/fileupload/js/plupload.flash.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/fileupload/js/plupload.html5.js"></script>
        <script type="text/javascript">
            var fileUploader = new plupload.Uploader(
                    {
                        runtimes: 'flash',
                        browse_button: 'pickfiles',
                        container: "upload_container",
                        max_file_size: 10485760,
                        url: '${pageContext.request.contextPath}/fileupload/fileUp.do?saveDB=true&businessId=',
                        flash_swf_url: '${pageContext.request.contextPath}/pub/fileupload/js/plupload.flash.swf',
                        filters: [{
                                title: "bmp,gif,jpeg,jpg,png,swf,fla,flv,3gp,doc,docx,xls,xlsx,ppt,pptx,pdf,txt,zip,rar",
                                extensions: "bmp,gif,jpeg,jpg,png,swf,fla,flv,3gp,doc,docx,xls,xlsx,ppt,pptx,pdf,txt,zip,rar"
                            }]
                    });
            var treeManager;
            $(function() {
                api.size(900, 500);
                api.position($(top).width() / 2 - 450, $(top).height() / 2 - 300);
                $("#layout1").ligerLayout({
                    leftWidth: 200,
                    rightWidth: 250,
                    allowLeftCollapse: false,
                    allowRightCollapse: false,
                    bottomHeight: 50
                });
                treeManager = $("#tree1").ligerTree({
                    checkbox: false,
                    url: 'fileTree/getTree.do?siteID=${param.siteID}',
                    attribute: ["url"],
                    onSelect: function(node) {
                        $("#rightFrame").attr(
                                "src",
                                "app/webmanage/FileManager/FileListView.jsp?pageStatus=detail&fileId="
                                + node.data.id);
                    }
                });

                //上传前
                fileUploader
                        .bind(
                        'BeforeUpload',
                        function(up, files) {
                            if (treeManager.getSelected() != null) {
                                up.settings.url = "${pageContext.request.contextPath}/fileupload/fileUp.do?saveDB=true&businessId="
                                        + treeManager.getSelected().data.id;
                            } else {
                                alert("请选择类别！");
                                return false;
                            }
                        });
                //添加一个文件
                fileUploader.bind('FilesAdded', function(up, files) {
                    if (files.length > 1) {
                        alert("只能同时上传一个文件!");
                        for (var i in files) {
                            up.removeFile(files[i]);
                        }
                        return false;
                    }
                });

                //文件列表发生变化,控制文件数量只能为一个，选择一个文件时自动上传
                fileUploader.bind('QueueChanged', function(uploader) {
                    fileUploader.start();
                });

                //单个文件上传完成
                fileUploader.bind('FileUploaded', function(uploader, file, resObj) {
                    var json = $.parseJSON(resObj.response);
                    if (json.success) {//上传成功
                        json.data.relativePath = json.data.path;
                        document.getElementById("rightFrame").contentWindow.showImg([json.data]);
                        $('#fileUrl').val("<%=Factory.getSysPara().getProperty("web_photo_address")%>/"+ json.data.relativePath);
                    } else {//上传失败
                        alert(json.msg);
                        file.status = plupload.FAILED;
                    }
                });

                //发生错误时
                fileUploader.bind('Error',
                        function(uploader, error) {
                            alert(error.code);
                            if (error.code == plupload.FILE_SIZE_ERROR) {
                                alert("所选的文件太大，超过了限制("
                                        + uploader.settings.max_file_size + ")");
                            }
                        });

                //执行初始化
                fileUploader.init();
            });
        </script>
        <style type="text/css">
            #preview img {
                width: 240px;
            }

            #preview p {
                padding-top: 10px;
            }

            #preview .nopreview {
                height: 100px;
                padding-top: 70px;
            }
        </style>
    </head>
    <body>
        <div id="layout1">
            <div position="left" title="类别">
                <ul id="tree1"></ul>
            </div>
            <div position="center" title="资源">
                <iframe marginwidth="0" id="rightFrame" marginheight="0"
                        frameborder="0" valign=top src="" name="rtree" width=100% height=390
                        scrolling="yes" allowTransparency></iframe>
            </div>
            <div position="bottom">
                <table width="100%">
                    <tr>
                        <td id="up_td" style="padding: 5px 0; width: 80px;">
                            <div id="upload_container"><input type="button" id="pickfiles" value="上传文件" /></div>
                        </td>
                        <td style="padding: 5px 0; width: 120px; text-align: right;">您选择的文件：</td>
                        <td><input type="text"
                                   style="width: 100%; font-size: 14px; height: 20px;" id="fileUrl"
                                   readonly="true"></td>
                        <td style="padding: 5px 0; width: 130px;">（不选择视为清空）</td>
                    </tr>
                </table>
            </div>
<!--           <div position="right" title="预览">
                <table style="height: 100%; width: 100%;">
                    <tr>
                        <td style="text-align: center; vertical-align: middle;"
                            id="preview"></td>
                    </tr>
                </table>
            </div>
  -->             
        </div>
    </body>
</html>