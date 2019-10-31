<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="edit">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        $(function () {
//                        如果不设置额外参数，不用调用此方法，初始化时会自动调用
            $("#formObj").initForm({    //参数设置示例
                toolbarPosition:"top",
                success : function(responseText) {//处理成功
                    if (responseText.success) {
                        top.$.notice("保存成功！");
                    } else {

                            $.ligerDialog.error('保存失败' + responseText);
                    }
                }
            });


        });


    </script>
</head>
<body>
<form id="formObj" action="app/zp/website/save.do" getAction="app/zp/website/detail.do?id=sys001">
    <input type="hidden" id="id" name="id">
    <input type="hidden" id="unitfdesc" name="unitfdesc">
    <input type="hidden" id="unitdesc" name="unitdesc">
    <fieldset class="l-fieldset">
        <legend class="l-legend">
            <div>浮动新闻设置</div>
        </legend>
	    <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
	    	<tr>
	            <td class="l-t-td-left"> 名称1：</td>
	            <td class="l-t-td-right"><input name="name" type="text" ltype='text' validate="{required:true,maxlength:32}"/>
	            </td>
	            <td class="l-t-td-left"> 链接1：</td>
	            <td class="l-t-td-right"><input name="url" type="text" ltype='text' validate="{required:true,maxlength:128}"/>
	            </td>
	            <td class="l-t-td-left"> 是否显示：</td>
	            <td class="l-t-td-right">
	            	 <input type="radio" id="display" name="display" ltype="radioGroup" validate="{required:true}"
                               ligerui="{value:'1', data: [ { text:'是', id:'1' }, { text:'否', id:'0' }]}"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="l-t-td-left"> 名称2：</td>
	            <td class="l-t-td-right"><input name="name1" type="text" ltype='text' validate="{required:true,maxlength:32}"/>
	            </td>
	            <td class="l-t-td-left"> 链接2：</td>
	            <td class="l-t-td-right"><input name="url1" type="text" ltype='text' validate="{required:true,maxlength:128}"/>
	            </td>
	            <td class="l-t-td-left"> 是否显示：</td>
	            <td class="l-t-td-right">
	            	 <input type="radio" id="display1" name="display1" ltype="radioGroup" validate="{required:true}"
                               ligerui="{value:'1', data: [ { text:'是', id:'1' }, { text:'否', id:'0' }]}"/>
	            </td>
	        </tr>
	    </table>
    </fieldset>
    <fieldset class="l-fieldset">
        <legend class="l-legend">
            <div>联系我们设置</div>
        </legend>
	    <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
	    	<tr>
	            <td class="l-t-td-left"> 联系电话：</td>
	            <td class="l-t-td-right"><input name="telnum" type="text" ltype='text' validate="{required:true,maxlength:256}"/>
	            </td>
	            <td class="l-t-td-left"> 技术支持：</td>
	            <td class="l-t-td-right"><input name="jszc" type="text" ltype='text' validate="{required:true,maxlength:256}"/>
	            </td>
	            <td class="l-t-td-left"> 网址：</td>
	             <td class="l-t-td-right"><input name="website" type="text" ltype='text' validate="{required:true,maxlength:256}"/>
	            </td>
	             <td class="l-t-td-left"> 邮箱：</td>
	             <td class="l-t-td-right"><input name="email" type="text" ltype='text' validate="{required:true,maxlength:256}"/>
	            </td>
	        </tr>
	    </table>
    </fieldset>
    <fieldset class="l-fieldset">
        <legend class="l-legend">
            <div>版权部分设置</div>
        </legend>
	    <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
		    	<tr>
		    		<td class="l-t-td-left"> 系统名称：</td>
		            <td class="l-t-td-right" colspan="5"><input name="sysname" type="text" ltype='text' validate="{required:true,maxlength:256}"/>
		            </td>
		        </tr>
	            <tr>
		            <td class="l-t-td-left"> 单位名称：</td>
		            <td class="l-t-td-right"><input name="unitname" type="text" ltype='text' validate="{required:true,maxlength:256}"/>
		            </td>
		            <td class="l-t-td-left"> 部门名称：</td>
		            <td class="l-t-td-right"><input name="depname" type="text" ltype='text' validate="{required:true,maxlength:256}"/>
		            </td>
		            <td class="l-t-td-left"> 版权：</td>
		            <td class="l-t-td-right"><input name="bquan" type="text" ltype='text' validate="{required:true,maxlength:256}"/>
		            </td>
	            </tr>
		        <tr>
		            <td class="l-t-td-left"> 地址：</td>
		            <td class="l-t-td-right"><input name="addtel" type="text" ltype='text' validate="{required:true,maxlength:256}"/>
		            </td>
		            <td class="l-t-td-left"> 单位链接：</td>
		            <td class="l-t-td-right"><input name="uniturl" type="text" ltype='text' validate="{required:true,maxlength:256}"/>
		            </td>
		            <td class="l-t-td-left"> 部门链接：</td>
		            <td class="l-t-td-right"><input name="depurl" type="text" ltype='text' validate="{required:true,maxlength:256}"/>
		            </td>
		        </tr>
	    </table>
    </fieldset>
</form>
</body>
</html>
