<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        $(function () {
            var voteId = '${param.id}';
            var pageStatus = "${param.pageStatus}";
            if ("" != voteId && pageStatus != "add") {
                //$("form").setValues("infomanage/vote/detail.do?id=" + voteId);
                $.getJSON("infomanage/voteitem/detail.do?voteId=" + voteId, function (data) {
                    var data = data.data;
                    var inHtml = "";
                    for (var i = 0; i < data.length; i++) {
                        var tr = $("#itemsContainer tr:nth-child(2)")
                        if (i != 0) {
                            tr = $("#itemsContainer tr:nth-child(2)").clone();
                            $("input", tr).attr("ligeruiid", "");
                            //$("td:last-child", tr).append('<input class="del-button" type="button" value="删除" onclick="$(this.parentElement.parentElement).remove()"/>')
                        }
                        if (pageStatus == "detail") {
                            $("div[name=itemId]", tr).text(data[i].id);
                            $("div[name=fkVoteId]", tr).text(data[i].fkVoteId);
                            $("div[name=itemTitle]", tr).text(data[i].itemTitle);
                            $("div[name=itemVotecount]", tr).text(data[i].itemVotecount);
                            $("div[name=sort]", tr).text(data[i].sort);
                            $("#itemsContainer").append(tr);
                        }
                        else {
                            $("input[name=itemId]", tr).val(data[i].id);
                            $("input[name=fkVoteId]", tr).val(data[i].fkVoteId);
                            $("input[name=itemTitle]", tr).val(data[i].itemTitle);
                            $("input[name=itemVotecount]", tr).val(data[i].itemVotecount);
                            $("input[name=sort]", tr).val(data[i].sort);
                            $("#itemsContainer").append(tr);
                        }
                    }
                });
            }
        })
        function addLines() {
            var tr = $("#itemsContainer tr:nth-child(2)").clone();
            $("input", tr).attr("ligeruiid", "");
            $("td:last-child", tr).append('<input class="del-button" type="button" value="删除" onclick="$(this.parentElement.parentElement).remove()"/>')
            $("#itemsContainer").append(tr);
        }
    </script>
</head>
<body>
<form name="formObj" id="formObj" method="post" action="infomanage/vote/voteSave.do"
      getAction="infomanage/vote/detail.do?id=${param.id}">
    <input name="id" type="hidden"/>
    <input name='fkClasstypeId' type='hidden' value="${param.classId}">
    <table border="0" cellpadding="3" cellspacing="0" width="" height="" align="" class="l-detail-table">
        <tr>
            <td class="l-t-td-left"> 标题：</td>
            <td class="l-t-td-right" colspan="3"><input name="title" type="text" ltype='text'
                                                        validate="{required:false,maxlength:256}"/>
            </td>
        </tr>

        <tr>
            <td class="l-t-td-left"> 描述：</td>
            <td class="l-t-td-right" colspan="3"><textarea name="description" rows="3" class="l-textarea"
                                                           validate="{required:false,maxlength:512}"></textarea>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 投票开始时间：</td>
            <td class="l-t-td-right"><input type="text" id="startTime" name="startTime" ltype="date"
                                            ligerui="{labelWidth: 100, labelAlign: 'left',initValue:<%=new Date().getTime()%>}"
                                            validate="{required:true}"
                    />
            </td>
            <td class="l-t-td-left"> 投票结束时间：</td>
            <td class="l-t-td-right"><input type="text" id="endTime" name="endTime" ltype="date"
                                            ligerui="{labelWidth: 100, labelAlign: 'left',initValue:<%=((new Date().getTime()/1000)+60*60*24*30)*1000%>}"
                                            validate="{required:true}"
                    />
            </td>
        </tr>
        <tr style="display: none">
            <td class="l-t-td-left"> 限制IP：</td>
            <td class="l-t-td-right"><input type="radio" name="restrictIp" ltype="radioGroup"
                                            validate="{required:false}"
                                            ligerui="{value:'off', data: [ { text:'是', id:'on' }, { text:'否', id:'off' }]}"/>
            </td>
            <td class="l-t-td-left"> 限制COOKIE：</td>
            <td class="l-t-td-right"><input type="radio" name="restrictCookie" ltype="radioGroup"
                                            validate="{required:false}"
                                            ligerui="{value:'off', data: [ { text:'是', id:'on' }, { text:'否', id:'off' }]}"/>
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left"> 是否关闭：</td>
            <td class="l-t-td-right"><input type="radio" name="isOff" ltype="radioGroup"
                                            validate="{required:false}"
                                            ligerui="{value:'off', data: [ { text:'是', id:'on' }, { text:'否', id:'off' }]}"/>
            </td>
            <td class="l-t-td-left"> 最多能选几项：</td>
            <td class="l-t-td-right"><input name="multiSelect" type="text" ltype='spinner'
                                            validate="{required:true}" value="1" ligerui="{type:'int'}"/>

            </td>
        </tr>
    </table>
    <br>
    <table border="0" cellpadding="0" cellspacing="0" width="" height="" align="" class="l-detail-table"
           id="itemsContainer">
        <tr>
            <td class="l-t-td-left">
                <input type="button" value="新增选项" onclick="addLines();"/>
            </td>
        </tr>

        <tr class="row">
            <td class="l-t-td-left">标题:</td>
            <td class="l-t-td-right"><input type="hidden" name="itemId" value=""/>
                <input type="hidden" name="fkVoteId" value=""/>
                <input type="text" name="itemTitle" value="" ltype='text' validate="{required:false,maxlength:256}"/>
            </td>
            <td class="l-t-td-left">票数:</td>
            <td class="l-t-td-right"><input type="text" name="itemVotecount" value="0" ltype='text' validate="{required:true,maxlength:16,number:true}"/>
            <td class="l-t-td-left">排序:</td>
            <td class="l-t-td-right"><input type="text" name="sort" value="10" ltype='text' validate="{required:true,maxlength:32}"/>
            <td class="l-t-td-left"></td>
        </tr>
    </table>
</form>
</body>
</html>