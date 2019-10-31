<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="/k/kui-base-form.jsp" %>
    <sec:authentication property="principal.position" var="position" />
    <script type="text/javascript">
        $(function () {
            var userId = '<sec:authentication property="principal.id" ></sec:authentication>';
            var posCode= "";
            <c:if test="${position!=null}">
            	posCode =  "${position.id}" ;      
            </c:if>
            $.getJSON("rbac/user/getUserPositionAll.do", {userId: userId}, function (pos) {
                var data = pos.data;
                for (var i in data) {
                    var tr = '<tr height="30"><td align="right">' + data[i].org.orgName + '</td>'
                            + '<td align="right">' + data[i].posName + '</td>'
                            + '<td align="right"><a href="javascript:changePos(\'' + data[i].id + '\')">切换</a></td>'
                            + '<td align="center"><a href="javascript:setDefPos(\'' + data[i].id + '\')">设为默认</a></td>'
                            + '</tr>';
                    $("#position").append(tr);
                }
                if ($.kh.isNull(data)) {
                    $("#position").append("<tr><td>无岗位信息</td></tr>");
                }
            });
            $("#position tr").live('mouseover mouseout', function(event) {
                if (event.type == 'mouseover') {
                    $(this).addClass("bg");
                } else {
                    $(this).removeClass("bg");
                }
            });
            pageTitle({
                to: "#title",
                text: "岗位设置",
                icon: "k/kui/images/icons/32/places.png"
            });
        });
        function setDefPos(id) {
            $.getJSON("rbac/userPosition/setPosition.do", {positionId: id}, function (res) {
                if (res.success) {
                    top.$.notice("默认岗位设置成功!", 4);
                    api.close();
                } else {
                    top.$.notice("默认岗位设置失败!", 4);
                }
            })
        }
        function changePos(id) {
            $.getJSON("rbac/position/positionLogin.do", {positionId: id}, function (res) {
                if (res.success) {
                    top.location = kFrameConfig["base"] + kFrameConfig["loginOkUrl"];
                    api.close();
                    return false;
                } else {
                    top.$.dialog.alert("切换岗位失败!");
                    return false;
                }
            })
        }
    </script>
    <style type="text/css">
        #position{
            font-size:14px;
            padding-top: 10px;
            border-collapse:separate;
        }
        .bg{
            background-color: #5e6c74;
            color:white;
        }
        .def{
            color: #0000ff;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="title-tm">
    <div id="title"></div>
</div>
<div class="scroll-tm">
    <table id="position" border="0" cellpadding="3" width="100%">
    </table>
</div>
</body>
</html>