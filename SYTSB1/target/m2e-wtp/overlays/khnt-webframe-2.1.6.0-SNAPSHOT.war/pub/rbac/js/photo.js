$(function () {
    var html = '<img id="_photo" src="pub/rbac/img/no_idno_pic.gif" width="120px" height="148px"/>';
    var pltsTitle = "照片";
    var tipLayer = $('<div id="pltsTipLayer" style="display:none;position:absolute;z-index:10001">' +
        '<table style="filter:alpha(opacity=90);border: 1px solid #cccccc" id="toolTipTalbe" cellspacing="1" cellpadding="0"><tr><td width="100%"><table bgcolor="#ffffff" cellspacing="0" cellpadding="0">' +
        '<tr id="tipPopTop"><td height="20" bgcolor="#1e90ff"><font color="#ffffff"><b><p id="topleft" align="left">↖' + pltsTitle + '</p><p id="topright" align="right" style="display:">' + pltsTitle + '↗</font></b></font></td></tr>' +
        '<tr><td style="padding-left:10px;padding-right:10px;padding-top: 8px;padding-bottom:6px;line-height:140%">' + html + '</td></tr>' +
        '<tr id="tipPopBot" style="display:none"><td height="20" bgcolor="#1e90ff"><font color="#ffffff"><b><p id="botleft" align="left">↙' + pltsTitle + '</p><p id="botright" align="right" style="display:none">' + pltsTitle + '↘</font></b></font></td></tr>' +
        '</table></td></tr></table>' +
        '</div>').appendTo("body");
    var tipPopTop = tipLayer.find("#tipPopTop");
    var tipPopBot = tipLayer.find("#tipPopBot");
    var topleft = tipLayer.find("#topleft");
    var botleft = tipLayer.find("#botleft");
    var topright = tipLayer.find("#topright");
    var botright = tipLayer.find("#botright");

    function setPhoto(idn) {
        if (idn != window.lastIdn) {
            $("#_photo").attr("src", "pub/personImg/download.do?idn=" + idn);
            window.lastIdn = idn;
        }
    }

    function reAdjust() {

        var img = $(this);
        setPhoto(img.attr("idn"));

        var pltsoffsetX = 12; // 弹出窗口位于鼠标左侧或者右侧的距离；3-12 合适
        var pltsoffsetY = 15; // 弹出窗口位于鼠标下方的距离；3-12 合适
        var position = img.offset();
        var left = position.left, top = position.top;
        var popHeight = tipLayer.height();
        var popWidth = tipLayer.width();
        var popTopAdjust = 0;
        var popLeftAdjust = 0;
        if (top + pltsoffsetY + popHeight > document.body.clientHeight) {
            popTopAdjust = -popHeight - pltsoffsetY * 1.5;
            tipPopTop.css("display", "none");
            tipPopBot.css("display", "");
        }
        else {
            popTopAdjust = 0;
            tipPopTop.css("display", "");
            tipPopBot.css("display", "none");
        }
        if (left + pltsoffsetX + popWidth > document.body.clientWidth) {
            popLeftAdjust = -popWidth - pltsoffsetX * 2;
            topleft.css("display", "none");
            botleft.css("display", "none");
            topright.css("display", "");
            botright.css("display", "");
        }
        else {
            popLeftAdjust = 0;
            topleft.css("display", "");
            botleft.css("display", "");
            topright.css("display", "none");
            botright.css("display", "none");
        }

        left = left + pltsoffsetX + document.body.scrollLeft + popLeftAdjust;
        top = top + pltsoffsetY + document.body.scrollTop + popTopAdjust;
        tipLayer.css({top: top, left: left,display:""});
    }

    $("span.photo").live({
        mouseenter: reAdjust,
        mouseleave: function () {
            tipLayer.hide();
        }
    });
});