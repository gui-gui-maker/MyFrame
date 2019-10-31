<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
    <script type="text/javascript" src="pub/rbac/js/area.js"></script>
    <script type="text/javascript">
        function showlist(){
            $(this).data('areacode','0');
            showAreaList.call(this);
        }
        $(function(){
            $("#form1").initForm({
                getSuccess:function(data){
                    setPic(data.data.photo);
                }
            });
            if($("head").attr("pageStatus")=="detail") {
                $("#onefileDIV").html("<font color='blue'>产品图片</font>");
            }else{
                var oneUploadConfig = {
                    fileSize: "3mb",//文件大小限制
                    businessId: "",//业务ID
                    saveDB: false,
                    buttonId: "onefileBtn",//上传按钮ID
                    container: "onefileDIV",//上传控件容器ID
                    attType: "",//文件存储类型；1:数据库，0:磁盘，默认为磁盘
                    title: "产品图片",//文件选择框提示
                    extName: "jpg,jpeg,gif,bmp,png",//文件扩展名限制
                    fileNum: 1,//限制上传文件选择数目 弹出选择框 可以选择文件数量限制
                    workItem: "one",//页面多点上传文件标识符号
                    callback: function (files) {//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
                        addOneFile(files);
                    }
                };
                new KHFileUploader(oneUploadConfig);
            }
        });
        function addOneFile(files){
            if(files){
                setPic(files[0].id);
            }
        }
        function setPic(id){
            var src="app/images/default.png";
            var bigSrc="app/images/default.png";
            if(id){
                src='fileupload/previewImage.do?id=' + id;
                bigSrc='fileupload/downloadByObjId.do?id=' + id;
                $("#photo").val(id);
            }
            $("#photoImg").attr({
                src: src,
                bigSrc: bigSrc
            });
        }
    </script>
</head>
<body>
<form id="form1" action="product/save.do" getAction="product/detail.do?id=${param.id}">
    <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
        <tr>
            <td class="l-t-td-left"> 标签编码:</td>
            <td class="l-t-td-right">
                <input name="id" type="hidden"/>
                <input name="tagId" type="hidden"/>
                <input name="sign" type="hidden"/>
                <input name="queryTimes" type="hidden"/>
                <input name="vendorCode" type="hidden"/>
                <input name="vendorName" type="hidden"/>
                <input name="validity" type="hidden"/>
                <input name="photo" id="photo" type="hidden"/>
                <input name="tagCode" type="text" ltype='text' validate="{required:true,maxlength:22,readonly:true}"/>
            </td>
            <td class="l-t-td-right" style="width:160px" align="center">
                <p id="onefileDIV">
                    <a class="l-button" id="onefileBtn"><span
                            class="l-button-main"><span class="l-button-text">产品图片</span>
						</span>
                    </a>
                </p>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">产品名称:</td>
            <td class="l-t-td-right">
                <input name="productName" type="text" ltype='text' validate="{required:true,maxlength:50}"/>
            </td>
            <td class="l-t-td-right" rowspan="7" align="right">
                <img id="photoImg" src="app/public/images/default.png" alt="" width="150"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 销售区域:</td>
            <td class="l-t-td-right">
                <input id="saleRegion" name="saleRegion" type="hidden">
                <input name="saleRegionName" type="text"
                       ltype='select' validate="{maxlength:100}"
                       ligerui="{textModel:true,initValue:'',initText:'',valueFieldID:'saleRegion',onBeforeOpen:showlist}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 行业分类:</td>
            <td class="l-t-td-right">
                <u:combo name="category" code="rfid_category" validate="required:true"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 生产日期:</td>
            <td class="l-t-td-right">
                <input name="proDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}"
                       validate="{required:true}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 生产地址:</td>
            <td class="l-t-td-right">
                <input id="proRegion" name="proRegion" type="hidden">
                <input name="proRegionName" type="text"
                       ltype='select' validate="{maxlength:100}"
                       ligerui="{textModel:true,initValue:'',initText:'',valueFieldID:'proRegion',onBeforeOpen:showlist}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 产品状态:</td>
            <td class="l-t-td-right">
                <u:combo name="state" code="rfid_status" validate="required:true"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 销售日期:</td>
            <td class="l-t-td-right">
                <input name="saleTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}"
                       validate="{required:false}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 备注:</td>
            <td class="l-t-td-right">
                <textarea rows="3" cols="" name="remarks"></textarea>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
