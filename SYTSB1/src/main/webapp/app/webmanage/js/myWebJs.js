/**
 * Created by IntelliJ IDEA.
 * User: LYC
 * Date: 12-3-13
 * Time: 下午3:14
 * To change this template use File | Settings | File Templates.
 */
function addUploadButton(editor,siteId) {
    CKEDITOR.on('dialogDefinition', function (ev) {
        var dialogName = ev.data.name;
        var dialogDefinition = ev.data.definition;
        //删除不要的tab标签页
        dialogDefinition.removeContents('Link');
        //由于filebrowserUploadUrl的使用,删除链接dialog中出现的upload标签页
        //dialogDefinition.removeContents('upload');

        if (dialogName == 'image') {
            var infoTab = dialogDefinition.getContents('info');
            infoTab.add({
                type:'button',
                id:'upload_image',
                align:'center',
                label:'浏览服务器',
                style:'display:inline-block;margin-top:10px;',
                onClick:function (evt) {
                    var thisDialog = this.getDialog();
                    var txtUrlObj = thisDialog.getContentElement('info', 'txtUrl');
                    var txtUrlId = txtUrlObj.getInputElement().$.id;
                    addUploadImage(txtUrlId,siteId);
                }
            }, 'browse'); //place front of the browser button
            infoTab.remove( 'browse' );//移除原来的浏览服务器按钮
        }
        else if (dialogName == 'flash') {
            var infoTab = dialogDefinition.getContents('info');
            infoTab.add({
                type:'button',
                id:'upload_image',
                align:'center',
                label:'浏览服务器',
                style:'display:inline-block;margin-top:10px;',
                onClick:function (evt) {
                    var thisDialog = this.getDialog();
                    var txtUrlObj = thisDialog.getContentElement('info', 'src');
                    var txtUrlId = txtUrlObj.getInputElement().$.id;
                    addUploadImage(txtUrlId,siteId);
                }
            }, 'browse'); //place front of the browser button
            infoTab.remove( 'browse' );//移除原来的浏览服务器按钮
        }
        else if (dialogName == 'link') {
            var infoTab = dialogDefinition.getContents('info');
            infoTab.add({
                type:'button',
                id:'upload_image',
                align:'center',
                label:'浏览服务器',
                style:'display:inline-block;margin-top:10px;',
                onClick:function (evt) {
                    var thisDialog = this.getDialog();
                    var txtUrlObj = thisDialog.getContentElement('info', 'url');
                    var txtUrlId = txtUrlObj.getInputElement().$.id;
                    addUploadImage(txtUrlId,siteId);
                }
            }, 'browse'); //place front of the browser button
            infoTab.remove( 'browse' );//移除原来的浏览服务器按钮
        }
        else if (dialogName == 'flvPlayer') {
            var infoTab = dialogDefinition.getContents('info');
            infoTab.add({
                type:'button',
                id:'upload_image',
                align:'center',
                label:'浏览服务器',
                style:'display:inline-block;margin-top:10px;',
                onClick:function (evt) {
                    var thisDialog = this.getDialog();
                    var txtUrlObj = thisDialog.getContentElement('info', 'src');
                    var txtUrlId = txtUrlObj.getInputElement().$.id;
                    addUploadImage(txtUrlId,siteId);
                }
            }, 'browse'); //place front of the browser button
            infoTab.remove( 'browse' );//移除原来的浏览服务器按钮
        }
    });
}

function addUploadImage(theURLElementId,siteId) {
    var uploadUrl = "/app/webmanage/FileManager/FileView.jsp?siteID="+siteId;
    //这是我自己的处理文件/图片上传的页面URL
    var urlObj = document.getElementById(theURLElementId)
    W.$.dialog({
        width:800,
        height:450,
        title:'资源管理器',
        content:'url:app/webmanage/FileManager/FileView.jsp?siteID='+siteId,
        ok:function (w) {
            var iframe = this.iframe.contentWindow;
            if (iframe.returnValue != "") {
                urlObj.value = "/"+iframe.returnValue;
                urlObj.fireEvent("onchange"); //触发url文本框的onchange事件，以便预览图片
            }
            else {
                urlObj.value = "/"+iframe.returnValue;
                urlObj.fireEvent("onchange"); //触发url文本框的onchange事件，以便预览图片
            }
            return true;
        }, cancel:function (w) {//取消按钮函数
            //alert("列表页面的全局对象："+w.xx)
        }
    });
}



