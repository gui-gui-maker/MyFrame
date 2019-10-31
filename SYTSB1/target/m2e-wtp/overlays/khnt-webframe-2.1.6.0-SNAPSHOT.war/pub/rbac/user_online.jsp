<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>在线人员</title>
    <%@include file="/k/kui-base-list.jsp" %>
    <script type="text/javascript">
    	var usgrid;
        $(function () {
            $.getJSON("security/web/onlineUser.do",function (datas) {
                usgrid = $("#maingrid").ligerGrid({
                    title:'在线人数： '+ datas.length + ' 人',
                    showTitle:true,
                    rowHeight:25,
                    frozen :false,
                    height:'99.8%',
                    columns:[
                        {display:'id', name:'id', hide:true,isAllowHide:false},
                        {display:'sessionId', name:'sessionId', hide:true,isAllowHide:false},
                        {display:'名 称', name:'name', minWidth:100,isAllowHide:false},
                        {display:'部 门', name:'depart', minWidth:180},
                        {display:'IP', name:'ip', minWidth:100}
                        <sec:authorize access="hasRole('sys_administrate')">
                        ,{display:'操作', name:"opr", width:40,isAllowHide:false,render: function (rowdata, rowindex, value) {
    			            var h = "<a class='l-a l-icon-del' href='javascript:killAccount();' title='将此帐号踢出'><span>踢出</span></a>";
    			            return h;
    		            }}
                        </sec:authorize>
                    ],
                    data:{Rows:datas},
                    usePager:false,
                    rownumbers:true
                });
            });
            $("#pageloading").hide();
        });
        
        function killAccount(){
    		var rd = usgrid.getSelectedRow();
    		if(rd && rd.id){
    			$.post("security/web/killSessionUser.do",{userId:rd.id,sessionId:rd.sessionId},function(resp){
    				if(resp.success){
    					usgrid.deleteSelectedRow();
    					top.notice("成功踢出该用户！");
    				}
    				else
    					$.ligerDialog.error("<br/>" + (resp.msg?resp.msg:""));
    			},"json");
    		}
        }
    </script>
</head>
<body><div id="maingrid"></div>
</body>
</html>