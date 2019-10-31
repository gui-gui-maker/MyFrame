<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<%
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String nowTime = "";
    nowTime = dateFormat.format(new Date());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <%@include file="/k/kui-base-list.jsp" %>
    <script type="text/javascript">
        var qmUserConfig = {
            sp_defaults: {columnWidth: 0.3, labelAlign: 'right', labelSeparator: '', labelWidth: 120},
            sp_fields: [
                {name: "emp_name", compare: "like"},
                {name: "work_department", compare: "like"},
                {name: "emp_id_card", compare: "like"}
            ],
            tbar: [{
                text: '详情', id: 'detail', icon: 'detail', click: detail
            }],

            listeners: {
                rowClick: function (rowData, rowIndex) {

                },
                rowDblClick: function (rowData, rowIndex) {
                    detail();
                },
                selectionChange: function (rowData, rowIndex) {
                    var count = Qm.getSelectedCount();
                    Qm.setTbar({
                        detail: count === 1
                    });
                },
                rowAttrRender: function (rowData, rowid) {
                    var fontColor = "#8E8E8E";
                    if (rowData.ischeck === '已确认') {
                        fontColor = "black";
                    }
                    return "style='color:" + fontColor + "'";
                }
            }
        };

        function detail() {
            top.$.dialog({
                width: 900,
                height: 500,
                lock: true,
                parent: api,
                data: {
                    window: window
                },
                title: "详情",
                content: 'url:app/humanresources/registration_datail.jsp?pageStatus=detail&id=' + Qm.getValueByName("id")
            });
        }

        function getSelectedPerson() {
            if (Qm.getSelectedCount() !== 1) {
                return null;
            } else {
                return {
                    id: Qm.getValueByName("id"),
                    name: Qm.getValueByName("emp_name")
                };
            }
        }


    </script>

</head>
<body>
<div class="item-tm" id="titleElement">
    <div class="l-page-title">
        <div class="l-page-title-note">提示：列表数据项
            <font color="black">“黑色”</font>代表已确认，
            <font color="#8E8E8E">“灰色”</font>代表未确认。
        </div>
    </div>
</div>
<!-- <div class="item-tm"  id="titleElement">
 <div class="l-page-title">
      <div class="l-page-title-note">提示：列表数据项
          <font color="red">“红色”</font>代表员工生日,退休,合同到期,职称到期。
      </div>
  </div>
  </div> -->
<qm:qm pageid="EMPLOYEE_CHOOSE_LIST">
    <qm:param name="emp_status" value="(4,3)" compare="in"/>
</qm:qm>
</body>
</html>