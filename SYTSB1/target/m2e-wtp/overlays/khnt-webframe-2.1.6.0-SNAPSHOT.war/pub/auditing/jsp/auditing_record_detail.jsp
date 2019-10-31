<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="detail">
<title>审计记录详情</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
    $(function() {
        $("#formObj").initForm({
            getSuccess : function(response) {
                if (response.success){
                    $("#entity_info").height($(window).height() - $(".l-detail-table").height() - 20);
                    try {
                        var infoObj = $.parseJSON(response.data.entityInfo);
                        showUlLogs(infoObj,$("#entity_info"));
                        $("span.hideable").click(function(){
                            var ul = $(this).parent().children("ul");
                            if($(this).hasClass("expand"))
                                ul.slideUp(200);
                            else
                                ul.slideDown(200);
                            $(this).toggleClass("expand");
                            $(this).toggleClass("close");
                        });
                    } catch (e) {
                        $("#entity_info").text(response.data.entityInfo);
                    }
                }
            }
        });
    });
    
    function showUlLogs(obj,parent){
        var log = $("<ul></ul>");
        if(obj==null || typeof obj == 'string'){
            log.append("<li><span class='content'>" + (obj||"[空]") + "</span></li>");
        }
        else{
            $.each(obj,function(k,v){
                var row = $("<li><span class='expand hideable'></span><span class='label'>" + k + "：</span></li>");
                showUlLogs(v,row);
                log.append(row);
            });
        }
        parent.append(log);
    }

    function showGridLogs(infoObj) {
        $("#entity_info").ligerGrid({
            columns : [ {
                id : "text",
                name : "text",
                align : "left",
                width : $("#entity_info").width() - 30
            } ],
            tree : {
                columnId : "text"
            },
            height : $(window).height() - $(".l-detail-table").height() - 40,
            data : {
                Rows : parseObjectInfo(infoObj)
            },
            usePager : false,
            headerRowHeight : 0
        });
    }

    function parseObjectInfo(obj) {
        if (obj == null)
            return null;
        var objInfo = [];
        $.each(obj, function(k, v) {
            var row = {
                text : k + "："
            };
            if (v != null){
                if(typeof v == 'string')
                    row.text = row.text + v;
                else
                    row["children"] = parseObjectInfo(v);
            }
            objInfo.push(row);
        });
        return objInfo;
    }
</script>
<style type="text/css">
#entity_info {overflow:auto;padding:10px;}
#entity_info>ul ul {padding:0;margin-left: 2em;}
#entity_info .hideable{display:inline-block;height:14px;width:14px;cursor:pointer;}
#entity_info .expand{background: url("k/kui/skins/default/shell/images/menu-1/arrows.png") no-repeat -17px 0px}
#entity_info .close{background: url("k/kui/skins/default/shell/images/menu-1/arrows.png") no-repeat 0px 0px}
#entity_info .content{color:blue}
#entity_info .label{color:gray}
</style>
</head>
<body>
    <form getAction="khnt/auditing/record/detail.do?id=${param.id}" id="formObj">
        <table class="l-detail-table">
            <tr>
                <td class="l-t-td-left" style="width:70px">事件名称：</td>
                <td class="l-t-td-right"><input name="eventName" type="text" ltype='text'
                    validate="{required:true}"></td>
                <td class="l-t-td-left" style="width:70px">操作时间：</td>
                <td class="l-t-td-right"><input name="operateTime" type="text" ltype='date'
                    ligerui="{format:'yyyy-MM-dd hh:mm:ss'}" /></td>
            </tr>
            <tr>
                <td class="l-t-td-left">操作人：</td>
                <td class="l-t-td-right"><input name="operator" type="text" ltype='text' /></td>
                <td class="l-t-td-left">所属部门：</td>
                <td class="l-t-td-right"><input name="department" type="text" ltype='text' /></td>
            </tr>
            <tr>
                <td class="l-t-td-left">登陆账号：</td>
                <td class="l-t-td-right"><input name="loginUser" type="text" ltype='text' /></td>
                <td class="l-t-td-left">IP地址：</td>
                <td class="l-t-td-right"><input name="ipAddress" type="text" ltype='text' /></td>
            </tr>
        </table>
        <div id="entity_info"></div>
    </form>
</body>
</html>