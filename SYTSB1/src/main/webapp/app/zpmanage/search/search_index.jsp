<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.khnt.security.CurrentSessionUser"%>
<%@ page import="java.util.Map"%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>公招面试成绩录入</title>
    <%@ include file="/k/kui-base-list.jsp"%>
    <%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
    <script type="text/javascript">
        var qmUserConfig = {
            //自定义查询面板样式参数
            sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:70},// 可以自己定义
            //自定义查询面板查询字段
            sp_fields:[
                {name:"gw_num",compare:"like",value:""},
                {name:"gw_name",compare:"like",value:""},
                {name:"fk_dep_id",compare:"=",value:""},
                {name:"name",compare:"like",value:""},      
                {name:"zj_no",compare:"like",value:""},
                {name:"sign",compare:"=",value:""},
                {name:"academic",compare:"=",value:""},
                {name:"degree",compare:"=",value:""}
            ],

            tbar:[
				{ text:'详情', id:'detail',icon:'detail', click:detail}

            ],
            ////            //提供以下4个事件
            listeners: {
                rowClick :function(rowData,rowIndex){
                },
                rowDblClick :function(rowData,rowIndex){
                    detail(rowData);
                },
                selectionChange :function(rowData,rowIndex){
                    selectionChange();
                },
                rowAttrRender : function(rowData, rowid)
                {
                    //已经提交为蓝色，提交后审核部通过的为红色
                    //alert(rowData.sign);
                    //if(rowData.sign=="已提交")
                    //{
                    //    return "style='color:blue'";
                    //}else if(rowData.sign=="审核打回"){
                    //    return "style='color:red'";
                    //}else{
                    //    return "";
                    //}
                }
            }
        };
        //行选择改变事件
        function selectionChange(){
            var count=Qm.getSelectedCount();//行选择个数

            if(count==0){
                Qm.setTbar({detail:false});
            }else if(count==1){
                Qm.setTbar({detail:true});
            }else{
            	Qm.setTbar({detail:false});
            }
        }
        
        function detail(item){
            var selectedId=Qm.getValueByName("id");
            var status = "detail";
            if(selectedId==null||selectedId==""){
                top.$.ligerDialog.alert('请选择一条需要审核的数据！', "提示");
                return;
            }

            var width=1024;
            var height=768;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"查看详情",
                content: 'url:app/zpmanage/search/search_detail.jsp?status='+status+'&id='+selectedId+'&xqId='+Qm.getValueByName("fk_hr_zp_xqxx_id")+'&jlId='+Qm.getValueByName("fk_hr_zp_jlxx_id")+'',
                data: {"window": window}
            });
        }

        
        function submitAction(o) {
            Qm.refreshGrid();
        }

    </script>
</head>
<body>
<qm:qm pageid="hr_zp_020" script="false" singleSelect="true" >

</qm:qm>
<script type="text/javascript">
Qm.config.columnsInfo.fk_dep_id.binddata=<u:dict sql="select t.id ,t.parent_id ,t.id,t.org_name from SYS_ORG t order by t.orders"></u:dict>;
</script>
</body>
</html>