<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.khnt.security.CurrentSessionUser"%>
<%@ page import="java.util.Map"%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>公招员工录用</title>
    <%@ include file="/k/kui-base-list.jsp"%>
     <%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
     <style type="text/css">
    .l-icon-next{background:url('k/kui/skins/icons/next.gif') no-repeat center;}
    .l-icon-check{background:url('k/kui/skins/icons/check.gif') no-repeat center;}
    .l-icon-rss-default{background:url('k/kui/skins/icons/rss-default.gif') no-repeat center;}
    .l-icon-user-move{background:url('k/kui/skins/icons/user-move.gif') no-repeat center;}
	</style>
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
                {name:"zj_no",compare:"like",value:""}
            ],

            tbar:[
                  { text:'体检', id:'test',icon:'add', click:test},
                  "-",
                  { text:'复检', id:'recheck',icon:'next', click:recheck},
                  "-",
                  { text:'公示', id:'publicity',icon:'rss-default', click:publicity},
                  "-",
                  { text:'录取', id:'enroll',icon:'user-move', click:result}
//                  ,
//                  "-",
//                  { text:'查看', id:'detail',icon:'detail', click:detail}
              ],
            ////            //提供以下4个事件
            listeners: {
                rowClick :function(rowData,rowIndex){
                },
                rowDblClick :function(rowData,rowIndex){
                   // detail(rowData);
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
            var sign= Qm.getValueByName("sign");
            if(count==0){
                Qm.setTbar({test:false,recheck:false,publicity:false,enroll:false,detail:false});
            }else if(count==1){
                if(sign=="18"){
                    //等待体检
                    Qm.setTbar({test:true,recheck:false,publicity:false,enroll:false,detail:true});
                }else if(sign=="11" || sign=="19"){
                    //体检通过
                    Qm.setTbar({test:false,recheck:false,publicity:true,enroll:false,detail:true});
                }else if(sign=="15"){
                    //公示通过
                    Qm.setTbar({test:false,recheck:false,publicity:false,enroll:true,detail:true});
                }
                else if(sign=="12"){
                    //体检未通过
                    Qm.setTbar({test:false,recheck:true,publicity:false,enroll:false,detail:true});
                }else{
                    Qm.setTbar({test:false,recheck:false,publicity:false,enroll:false,detail:false});
                }

            }else{
                Qm.setTbar({test:false,publicity:false,enroll:false,detail:false});
            }
        }

        //体检结果录入
        function test(){
            var selectedId=Qm.getValueByName("id");
            var status = "edit";
            var width=600;
            var height=400;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"录入体检结果",
                content: 'url:app/zp/jltd/assessShowDetail.do?status='+status+'&id='+selectedId+'&flag=1',
                data: {"window": window}
            });
        }

        //复检结果
        function recheck(){
            var selectedId=Qm.getValueByName("id");
            var status = "edit";
            var width=600;
            var height=400;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"录入复检结果",
                content: 'url:app/zp/jltd/assessShowDetail.do?status='+status+'&id='+selectedId+'&flag=2',
                data: {"window": window}
            });
        }
        //公示结果
        function publicity(){
            var selectedId=Qm.getValueByName("id");
            var status = "edit";
            var width=600;
            var height=400;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"录入公示结果",
                content: 'url:app/zp/jltd/assessShowDetail.do?status='+status+'&id='+selectedId+'&flag=4',
                data: {"window": window}
            });
        }


        function result(item){
            var selectedId=Qm.getValueByName("id");
            var status = "edit";
            var width=600;
            var height=400;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"选择进入部门",
                content: 'url:app/zp/jltd/assessShowDetail.do?status='+status+'&id='+selectedId+'&flag=5',
                data: {"window": window}
            });
        }

        function detail(item){
            var selectedId=Qm.getValueByName("id");
            var status = "detail";
            if(selectedId==null||selectedId==""){
                top.$.ligerDialog.alert('请选择一条需要审核的数据！', "提示");
                return;
            }

            var width=600;
            var height=400;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"查看详细信息",
                content: 'url:app/zp/jltd/assessShowDetail.do?status='+status+'&id='+selectedId+'',
                data: {"window": window}
            });
        }

        
        function submitAction(o) {
            Qm.refreshGrid();
        }

    </script>
</head>
<body>
<qm:qm pageid="hr_zp_018" script="false" singleSelect="true" >

</qm:qm>
<script type="text/javascript">
Qm.config.columnsInfo.fk_dep_id.binddata=<u:dict sql="select t.id ,t.parent_id ,t.id,t.org_name from SYS_ORG t order by t.orders"></u:dict>;
</script>
</body>
</html>