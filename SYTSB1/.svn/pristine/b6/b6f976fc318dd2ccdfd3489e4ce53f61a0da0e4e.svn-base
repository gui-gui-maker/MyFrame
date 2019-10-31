<%@ page import="com.khnt.utils.StringUtil" %>
<%@ page import="com.khnt.core.exception.KhntException" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
    String goodsIds = request.getParameter("goodsIds");
    String result = "(";
    if (StringUtil.isNotEmpty(goodsIds)) {
        String[] goodsId = goodsIds.split(",");
        for (int i = 0; i < goodsId.length; i++) {
            result += i == 0 ? ("'" + goodsId[i] + "'") : (",'" + goodsId[i] + "'");
        }
        result += ")";
        System.out.println(result);
    } else {
        result += "'')";
    }
    String sql = "select a.wpmc,a.je,a.se,t.* from TJY2_CH_GOODS a inner join (select goods.*,out.CKDBH,OUT.id as outid from TJY2_CH_GOODSANDORDER goods inner join TJY2_CH_OUT out on GOODS.FK_ORDER_ID = out.ID) t on a.id = t.fk_goods_id";
    sql += " where fk_goods_id in " + result;

%>
<head>
    <title>存货去向列表</title>
    <%@include file="/k/kui-base-list.jsp" %>
    <script type="text/javascript">
        var qmUserConfig = {
            tbar: [
                {text: '出库单详情', id: 'detail', icon: 'detail', click: detail}
            ],
            listeners: {
                rowClick: function (rowData, rowIndex) {

                },
                rowDblClick: function (rowData, rowIndex) {
                    detail();
                },
                selectionChange: function (rowData, rowIndex) {
                    var count = Qm.getSelectedCount();
                    Qm.setTbar({
                        detail: count == 1,
                    });
                }
            }
        }


        function detail() {
            var id = Qm.getValueByName("outid");
            winOpen({
                lock: true,
                title: "出库单",
                content: 'url:app\\fwxm\\outstorage\\ch_ck_detail.jsp?status=detail&id=' + id,
                data: {
                    "window": window,
                }
            }).max();
        }

    </script>

</head>
<body>
<div class="lb_gys_list" id="titleElement">
    <qm:qm pageid="ch_goods_ck_list" sql="<%=sql%>">

    </qm:qm>
</div>
</body>
</html>