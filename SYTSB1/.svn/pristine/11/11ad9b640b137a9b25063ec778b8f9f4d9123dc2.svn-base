<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="q" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="com.khnt.security.CurrentSessionUser" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
CurrentSessionUser sessionUser=(CurrentSessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
String areaCode=sessionUser.getDepartment().getAreaCode();
%>
<head>
    <title>办理时限设置列表页面</title>
    <%@include file="/k/kui-base-list.jsp"%>
    <script type="text/javascript" src="pub/rbac/js/area.js"></script>
    <script test="text/javascript">
        var qmUserConfig = {
//            //自定义查询面板样式参数
//            title:"重命名",
            sp_defaults:{columnWidth:0.5,labelAlign:'right',labelSeparator:'',labelWidth:80},// 可以自己定义
//            自定义查询面板查询字段
            sp_fields:[
                {name:"handler_name",compare:"like",value:""},
                {name:"work_type",compare:"=",value:""},
                {name:"mobile_tel",compare:"like",value:""},
                {name:"area_code",compare:"llike",value:"",xtype:"combo",onBeforeOpen:showlist}
            ],
//            //定义按钮，可以直接是extjs的按钮
            tbar:[{}],
            listeners: {
                
               	rowAttrRender:function(item, rowid) {
                    if(item.status=="已超期")
                        return "style='color: red'";
                    else
                        return "";
                 }
            }
        };
        function showlist(){
        	$(this).data('areacode','<%=areaCode%>');
        	showAreaList.call(this);
        }
        

    </script>
</head>
<body>
<q:qm pageid="pub_work_admin" singleSelect="true" >
	<qm:param name="area_code" compare="llike" value="<%=areaCode%>"/>
</q:qm>

</body>
</html>