<%@page import="com.khnt.utils.StringUtil"%>
<%@page import="com.khnt.base.Factory"%>
<%@page import="java.math.RoundingMode"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.khnt.utils.Base64Utils"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="k/kui/frame/jquery.qrcode.min.js"></script>
<style type="text/css">
.qrcode{
    position: fixed;
    z-index: 9999;
    width: 400px;
    height: 300px;
    display: none;
    border: 2px solid #dddddd;
    text-align: center;
    background: #ffffff;
}
.qrcode .q-title{height: 30px;}
.qrcode .q-title .q-title-close{
    float: right;font-family: Consolas,Arial,Verdana;
    font-size:24px;cursor:pointer;display:inline-block;padding-right:10px;
}
.qrcode .qcc{margin-top: 20px;text-align: center;position: relative;}
.qrcode .qcc>table{margin-left:auto;margin-right:auto;}
.qrcode .qcc>span{display:inline-block;margin-top:100px;}
#qcc_tips{font-size:12px;padding-top:2em;display:none;}
.l-grid-row-cell-inner{display:inline-block;line-height:30px;}
</style>
<script type="text/javascript">
var manager;
var filesList=[
<%
String softPath = Factory.getSysPara().getProperty("pub.soft.path");
System.out.println(softPath);
File fileDir = null;
if(StringUtil.isNotEmpty(softPath))
	fileDir = new File(softPath);
if(fileDir != null && fileDir.exists() && fileDir.isDirectory()){
    File [] fileList = fileDir.listFiles();
    for(int i =0;i<fileList.length; i++){
        File f =  fileList[i];
        if(!f.isHidden()){
            FileInputStream fis = new FileInputStream(f);
            out.print(i==0?"":",");
            out.print("{name:'" + f.getName() + "',");
            String fsize;
            int size = fis.available()/1024;
            if(size>=1024){
                fsize = new BigDecimal(size).divide(new BigDecimal(1024)).setScale(2,RoundingMode.FLOOR).floatValue() + "MB";
            }else{
                fsize = size + "KB";
            }
            out.print("type:'y',");
            out.print("size:'" + fsize + "',");
            out.print("code:'" + Base64Utils.encode(f.getName().getBytes()) + "'}");
        }
    }
}
%>];
    $(function(){
        $('#qrcode').css("left",($(window).width()-400)/2 + "px");
        $('#qrcode').css("top",($(window).height()-300)/2 + "px");
        manager=$("#filelist").ligerGrid({
            columns:[{
                name: "name",
                align: "left",
                display: "点击下载",
                width: 550,
                render: function(row){
                    return "<a target='_blank' href='pub/tools/dowload_soft.jsp?fpath=" + row.code + "'>" + row.name + "</a>";
                }
            },{
                name: "name",
                display: "扫描下载",
                width: 100,
                render: function(row){
                    return "<input style='margin-top:4px;cursor:pointer' type='image' src='pub/tools/img/qrcode.png' onclick='downloadQRCode(\""+ row.code +"\",\""+ row.type +"\")' title='点击这里使用移动设备扫描下载' />";
                }
            },{
                name: "size",
                display: "大小",
                align: "right",
                width: 150
            },{
                name: "code",
                hide: true
            }],
            data: {Rows:filesList},
            usePager: false,
            width: "100%",
            height: "99.9%",
            rowHeight: 30,
            rownumbers: true,
            frozen: false
        });
    });
    
    function downloadQRCode(fname,type){
        $("#qcc").empty();
        var ie = $.browser.msie;
        var iever = $.browser.version;
        var dm = document.documentMode; //文档模式
        $("#qrcode").fadeIn("fast",function(){
            if(ie && (parseInt(iever)<=8 || parseInt(dm)<=8)){
                $('#qcc').html("<img class='qrcode-img' src='pub/tools/qrcode_img.jsp?fname=" + fname + "&type="+type+"' />");
            }else{
                try{
                    $('#qcc').qrcode({
                        width:175,
                        height:175,
                        text: 'http://${pageContext.request.serverName}:<%=request.isSecure()?Factory.getSysPara().getProperty("server.http.port","80"):request.getServerPort()%>${pageContext.request.contextPath}/pub/tools/dowload_soft.jsp?fpath=' + fname
                    });
                }catch(err){
                    $('#qcc').html("<img class='qrcode-img' src='pub/tools/qrcode_img.jsp?fname=" + fname + "&type=" + type + "' />");
                }
            }
            $("#qcc_tips").show();
            $("#qcctext").hide();
        });
    }
    function closeQrcode(){
        $("#qrcode").hide();
        $("#qcc_tips").hide();
    }
</script>
</head>
<body style="position:relative;" class="p5">
    <div id="filelist"></div>
    <div class="qrcode" id="qrcode">
        <div class="q-title">
            <span class="q-title-close" onclick="closeQrcode()">X</span>
        </div>
        <div id="qcctext" class="qcc"><span><img src="k/kui/images/icons/dialog/loading.gif" /></span></div>
        <div id="qcc" class="qcc"></div>
        <p id="qcc_tips">请在手机或者平板电脑上使用扫描工具扫描此二维码下载文件</p>
    </div>
    <iframe name="downloadFrame" style="display:none;" src=""></iframe>
</body>
</html>