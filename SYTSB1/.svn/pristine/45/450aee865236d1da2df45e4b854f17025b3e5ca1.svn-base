window.can_choose_pay_type = [];
window.getCanChoose = function (canchoose, inverse, code) {
    if ( code == null || typeof(code) == 'undefined' ) {
        return "";
    }
    if ( window.can_choose_pay_type.length == 0 ) {
        $.ajax({
            url: "pub/codetablevalue/getCodetableValuesByCode.do?code=" + code,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            type: "post",
            async: false,
            success: function (data) {
                if ( data.Rows ) {
                    var zftype = data.Rows;
                    for (var i in zftype) {
                        if ( inverse == true ? !$.inArray(zftype[i]["code"], canchoose) < 0 : $.inArray(zftype[i]["code"], canchoose) < 0 ) {
                            continue;
                        } else {
                            window.can_choose_pay_type.push({id: zftype[i]["code"], text: zftype[i]["name"]});
                        }
                    }
                } else {
                    $.ligerDialog.error("获取可选择支付类型失败,请刷新后再试");
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.ligerDialog.error("获取可选择支付类型失败,请刷新后再试");
            }
        });
        return window.can_choose_pay_type;
    } else {
        return window.can_choose_pay_type;
    }
}

