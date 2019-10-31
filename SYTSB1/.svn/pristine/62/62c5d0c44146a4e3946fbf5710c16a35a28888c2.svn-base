<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript" src="app/webmanage/js/myWebJs.js"></script>
    
   	<SCRIPT type=text/javascript src="ueditor/ueditor.config.js"></SCRIPT>  
    <SCRIPT type=text/javascript src="ueditor/ueditor.all.min.js"></SCRIPT>
    
    <script type="text/javascript">
        $(function () {
        	
            $("#titleColor").click(function () {
                W.$.dialog({
                    width:360,
                    height:240,
                    title:'颜色选取',
                    lock:true,
                    content:'url:app/webmanage/infomanage/selcolor.html',
                    ok:function (w) {
                        var iframe = this.iframe.contentWindow;
                        $("#titleColor").val(iframe.returnValue);
                        return true;
                    }, cancel:function (w) {//取消按钮函数
                    }
                });
            });
            var editor = UE.getEditor("select");
            //var editor = new UE.ui.Editor();
            //editor.render("content");
            editor.ready(function () {
                //editor.setDisabled();
                //editor.setHide();//隐藏编辑器
                editor.addListener('beforeInsertImage', function (t, arg) { //侦听图片上传
                    var imagesUrl = "";
                    for (var i = 0; i < arg.length; i++) {
                        //实现多图上传. ,分割图片地址
                        if (arg[i].src === undefined) {
                            continue;
                        }
                        if (i == (arg.length - 1)) {
                            imagesUrl = imagesUrl + (arg[i].src);
                        }
                    }
                    if (imagesUrl != "") {
                        $("#imgUrl").val(imagesUrl);
                        $("#imgOff").val("y");
                    }
                    else {
                        $("#imgUrl").val(imagesUrl);
                        $("#imgOff").val("n");
                    }

                });

            });
            //----------------------------------------------------------------------------------
            $("#formObj").initForm({
                toolbar: [
                    {text: '保存', id: 'save', icon: 'save', click: function () {
                        if (editor.hasContents()) { //此处以非空为例
                            editor.sync(); //同步内容
                        }
                        $("#formObj").submit();
                    }
                    },
                    {text: '关闭', id: 'close', icon: 'close', click: function () {
                        api.close();
                    }}
                ],
                getSuccess: function (res) {
                    editor.ready(function () {
                        editor.setContent(res.data.content);
                    });
                }})
           <%-- $("#imgUrl").click(function () {
                W.$.dialog({
                    width:800,
                    height:450,
                    title:'资源管理器',
                    content:'url:app/webmanage/FileManager/FileView.jsp?siteID=${param.siteID}',
                    ok:function (w) {
                        var iframe = this.iframe.contentWindow;
                        if (iframe.returnValue != "") {
                            $("#imgUrl").val(iframe.returnValue);
                            $("#imgOff").val("y");
                        }
                        else {
                            $("#imgUrl").val(iframe.returnValue);
                            $("#imgOff").val("n");
                        }
                        return true;
                    }, cancel:function (w) {//取消按钮函数
                    }
                });
            });--%>
        });
    </script>
</head>
<body>
<form name="formObj" id="formObj" method="post" action="infomanage/article/save.do"
      getAction="infomanage/article/detail.do?id=${param.id}">
    <input name="id" type="hidden"/>
    <input name="fkClasstypeId" type="hidden" ltype='hidden' value="${param.classId}"/>
    <table border="1" cellpadding="3" cellspacing="0" width="" height="" align="" class="l-detail-table">
        <tr>
            <td class="l-t-td-left"> 标题：</td>
            <td class="l-t-td-right" colspan="3"><input name="title" type="text" ltype='text'
                                                        validate="{required:true,maxlength:255}"/></td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 标题颜色：</td>
            <td class="l-t-td-right">
                <table border="0" cellpadding="0" cellspacing="0" width="100%" align="left">
                    <tr>
                        <td class="l-t-td-right" width="30%">
                            <input name="titleColor" id="titleColor" type="text" ltype='text'
                                   validate="{required:false,maxlength:8}"/>

                        </td>
                        <td>&nbsp;</td>
                        <c:if test="${param.pageStatus!='detail'}">
                            <td>
                                <input id="titleStyleA" type="checkbox" name="titleStyleA"/>
                                <label for="titleStyleA">粗体</label>
                                <input id="titleStyleB" type="checkbox" name="titleStyleB"/>
                                <label for="titleStyleB">斜体</label>
                                <input id="titleStyleC" type="checkbox" name="titleStyleC"/>
                                <label for="titleStyleC">删除线</label>
                            </td>
                        </c:if>
                    </tr>
                </table>
            </td>
            <td class="l-t-td-left"> 副标题：</td>
            <td class="l-t-td-right"><input name="assTitle" type="text" ltype='text'
                                            validate="{required:false,maxlength:255}"/></td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 作者：</td>
            <td class="l-t-td-right"><input name="author" type="text" ltype='text'
                                            validate="{required:true,maxlength:32}"
                                            value="<sec:authentication property="principal.name" />"/>
            </td>
            <td class="l-t-td-left"> 来源：</td>
            <td class="l-t-td-right"><input name="source" type="text" ltype='text'
                                            validate="{required:false,maxlength:128}"/></td>
        </tr>
        <tr>
            <td class="l-t-td-left">文章小图片：</td>
            <td class="l-t-td-right" colspan="1">
                <input name="imgOff" type="hidden" ltype='hidden'/>
                <input name="imgUrl" id="imgUrl" type="text" ltype='text' validate="{required:false,maxlength:256}"
                       ligerui="{iconItems:[{icon:'photo',click:function(val,e,srcobj){UE.getEditor('select').getDialog('insertimage').open()}}]}"/>
            </td>
            <!-- <td class="l-t-td-right">
                <input name="imgOff" type="hidden" ltype='hidden'/>
                <input name="imgUrl" id="imgUrl" type="text" ltype='text' validate="{required:false,maxlength:256}"/>
            </td> -->
            <td class="l-t-td-left"> 时间：</td>
            <td class="l-t-td-right"><input type="text" id="eidtDate" name="eidtDate" ltype="date"
                                            ligerui="{labelWidth: 100, labelAlign: 'left',initValue:<%=new Date().getTime()%>}"
                                            validate="{required:true}"
                    />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 关键字：</td>
            <td class="l-t-td-right"><input name="nkey" type="text" ltype='text'
                                            validate="{required:false,maxlength:64}"/></td>
            <td class="l-t-td-left"> 编辑人：</td>
            <td class="l-t-td-right"><input name="editAuthor" type="text" ltype='text'
                                            value="<sec:authentication property="principal.name" />"
                                            validate="{required:false,maxlength:32}"/></td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 链接地址：</td>
            <td class="l-t-td-right"><input name="url" type="text" ltype='text' />
            </td>
            <%--<td class="l-t-td-left"> 排序：</td>
            <td class="l-t-td-right"><input name="sort" type="text" ltype='text'
                    />
            </td>--%>
        </tr>
        <tr>
            <td class="l-t-td-left"> 内容介绍：</td>
            <td class="l-t-td-right" colspan="3"><textarea name="contentIntro" rows="2" class="l-textarea"
                                                           ltype="textarea" validate="{maxlength:128}"></textarea>
            </td>
        </tr>
        <c:if test="${param.pageStatus!='detail'}">
        <tr>
            <td class="l-t-td-left"> 特殊属性：</td>
            <td class="l-t-td-right" colspan="3">
                <table border="0" cellpadding="0" cellspacing="0" width="100%" height="" align="center">
                    <tr>
                        <td width="150">
                                <%-- <input id="auditOff" type="checkbox" name="auditOff"/>
                               <label for="auditOff">发布</label>--%>
                            <input id="recommendOff" type="checkbox" name="recommendOff"/>
                            <label for="recommendOff">推荐</label>
                            <input id="isTopOff" type="checkbox" name="isTopOff"/>
                            <label for="isTopOff">头条</label>
                        </td>
                        <td width="80" align="right"> 置顶级别：</td>
                        <td><u:combo name="topLevel"
                                     code=""
                                     data="[{ text:'不置顶', id:'0' }
                                                        ,{ text:'置顶一', id:'5' }
                                                        ,{ text:'置顶二', id:'4' }
                                                        ,{ text:'置顶三', id:'3' }
                                                        ,{ text:'置顶四', id:'2' }
                                                        ,{ text:'置顶五', id:'1' }]"
                                     attribute="initValue:'0',selectBoxHeight:150,width:100"/></td>
                            <%--<td width="" align="right" >点击次数：</td>
                            <td width="" ><input name="hits" type="text" ltype='text' value="0"
                                                                        ligerui="{type:'int',width:100}"/>
                            </td>
                            <td width="" align="right" > 下载次数：</td>
                            <td ><input name="downHits" type="text" ltype='text' value="0"
                                                             ligerui="{type:'int',width:100}"/>
                            </td>--%>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        </c:if>
    </table>

    <TEXTAREA name="content" id="content"  style="height: 250px;width: 99%"></TEXTAREA>  
    <TEXTAREA name="select" id="select" style="display: none"></TEXTAREA>
 <SCRIPT id="editor" type="text/javascript">  
    //var editor = new UE.ui.Editor();  
    //editor.render("content");

    //1.2.4以后可以使用一下代码实例化编辑器 
    var editor1 = UE.getEditor("content");
    editor1.ready(function(){
		window.setTimeout(function(){
			var con = document.getElementById("content").value;
			//alert(con);
    		editor1.setContent(con);
    		//editor1.execCommand("fontSize","12px"); 
    		document.getElementById("topLevel-txt").focus();
		},100);
    });
    
    
    //editor1.enable();
</SCRIPT> 

   <!--   <div>
        <textarea name="content" id="content" rows="13"
                  class="l-textarea" ltype="textarea"></textarea>
    </div>-->
    
</form>
</body>
</html>