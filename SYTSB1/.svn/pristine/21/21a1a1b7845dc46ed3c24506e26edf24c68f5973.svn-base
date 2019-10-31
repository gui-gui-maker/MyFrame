function openPdfViewer(fileId,fname){
    if($.browser.msie && $.browser.version < 9.0){
        location.href = $("base").attr("href")+"fileupload/download.do?id=" + fileId;
    }else{
        top.$.dialog({
            width: $(top).width(),
            height: $(top).height(),
            title: fname||"PDF Viewer",
            lock: false,
            reSize: false,
            content: "url:pub/pdfjs/viewer.jsp?fid=" + fileId
        });
    }
}