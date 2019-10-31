<%@page import="java.util.Date"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <%@include file="/k/kui-base-list.jsp" %>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
  <%@ include file="/k/kui-base-form.jsp"%>
    
<%
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User uu = (User)curUser.getSysUser();
    String typeSql="SELECT ID,LX_NAME FROM TJY2_CH_GOODS_TYPE WHERE STATE='1' and CREATE_ORG_ID='"+uu.getOrg().getId()+"'";
   // String 
%>

    <script type="text/javascript">
        var qmUserConfig = {
            sp_fields: [
                { name: "gysmc", compare: "like"},
                { name: "warehousing_num", compare: "like"},
                { name: "fk_goodstype_id", compare: "like"},
                { name: "wpmc", compare: "like"},
                { name: "ggjxh", compare: "like"},
                {group:[
        				{name:"rk_time", id:"rk_time", compare:">=", value:""},
        				{label:"到", name:"rk_time", id:"rk_time1", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
        			]}
                
                ],
            tbar: [
                {text: '详情', id: 'detail', icon: 'detail', click: detail},
//                  {text : '新增',id : 'add',icon : 'add',click : add},"-",
                {text : '修改',id : 'modify',icon : 'modify',click : modify}
//                 { text:'删除', id:'del',icon:'delete', click:del}
//                 {text: '存货去向', id: 'follow', icon: 'follow', click: follow}, "-",
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
                        modify: count == 1 && rowData.bx=='未报销',
                        del: count > 0,
                        follow: count == 1,
                    });
                    
                },
                rowAttrRender: function (rowData, rowIndex) {
                	if(rowData.bx=='已报销'){
                		return 'style="color:green;"';
                	}
                	 
                }
            }
        }
        
        function follow() {
            var ids = Qm.getValueByName("id").toString();
            winOpen({
                width: 1000,
                height: 800,
                lock: true,
                title: "存货去向",
                content: 'url:app/supplies/stock_follow_list.jsp?goodsIds='+ids,
                data: {
                    "window": window,
                    goodsIds: ids,
                }
            });
        }
        function del() {
            $.del("确定删除?", "com/tjy2/goodsType/deleteByIds.do", {
                "ids": Qm.getValuesByName("id").toString()
            })
        }

        function detail() {
            top.$.dialog({
                width: 600,
                height: 300,
                lock: true,
                parent: api,
                data: {
                    window: window
                },
                title: "详情",
                content: 'url:app/supplies/stock_detail.jsp?pageStatus=detail&id='
                + Qm.getValueByName("id")
            });
        }

        function add() {
            top.$.dialog({
                width: 600,
                height: 300,
                lock: true,
                parent: api,
                data: {
                    window: window
                },
                title: "新增物品",
                content: 'url:app/supplies/stock_detail.jsp?pageStatus=add'
            });

        }

        function modify() {
        	var cksl=Qm.getValueByName("cksl")
            top.$.dialog({
                width: 600,
                height: 300,
                lock: true,
                parent: api,
                data: {
                    window: window
                },
                title: "修改物品类型",
                content: 'url:app/supplies/stock_detail.jsp?pageStatus=modify&id='
                + Qm.getValueByName("id") + "&status=2&cksl="+cksl
            });
        }
    </script>
</head>
<body>

<div class="item-tm" id="titleElement">
    <div class="l-page-title">
        <div class="l-page-title-note">提示：列表数据项
            <font color="black">“黑色”</font>代表未报销，
            <font color="green">"绿色"</font>代表已报销。
        </div>
    </div>
</div>
<div class="lb_gys_list" id="titleElement">
    <qm:qm pageid="ch_goods_list">
    </qm:qm>
    <!-- 修改qm查询值 -->
    <script type="text/javascript">
    	Qm.config.columnsInfo.fk_goodstype_id.binddata=<u:dict sql="<%=typeSql%>"></u:dict>;
	</script>
</div>
</body>
</html>