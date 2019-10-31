<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.khnt.security.CurrentSessionUser"%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>考场信息列表</title>
    <%@ include file="/k/kui-base-list.jsp"%>
    <script type="text/javascript">
        var qmUserConfig = {
            //自定义查询面板样式参数
            sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:60},// 可以自己定义
            //自定义查询面板查询字段
            sp_fields:[
                {name:"type",compare:"=",value:""},
                {name:"num",compare:"like",value:""},
                {name:"sign",compare:"=",value:""}
            ],

            tbar:[
				{ text:'详情', id:'detail',icon:'detail', click:detail},
				"-",
                { text:'添加', id:'add',icon:'add', click:add},
                "-",
                { text:'修改', id:'edit',icon:'edit', click:edit}
                <sec:authorize access="hasRole('examroom_delete')">
                ,
                "-",
                { text:'删除', id:'del',icon:'delete', click:del}
                </sec:authorize>
            ],
            ////            //提供以下4个事件
            listeners: {
                rowClick :function(rowData,rowIndex){
                },
                rowDblClick :function(rowData,rowIndex){
                    //detail(rowData);
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
                Qm.setTbar({add:true,edit:false,detail:false,del:false});
            }else if(count==1){
                Qm.setTbar({add:true,edit:true,detail:true,del:true});

            }else{

                Qm.setTbar({add:true,edit:false,detail:false,del:false});
            }
        }


        function add(){
            var status = "add";
            var width=600;
            var height=300;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"添加考场信息",
                content: 'url:app/zpmanage/system/examroom_detail.jsp?status='+status+'',
                data: {"window": window}
            });
        }

        function edit(){
            var selectedId=Qm.getValueByName("id");
            var status = "edit";
            var width=600;
            var height=300;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"修改考场信息",
                content: 'url:app/zpmanage/system/examroom_detail.jsp?status='+status+'&id='+selectedId+'',
                data: {"window": window}
            });
        }

        function detail(){
            var selectedId=Qm.getValueByName("id");
            var status = "detail";
            var width=600;
            var height=300;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"查看考场信息",
                content: 'url:app/zpmanage/system/examroom_detail.jsp?status='+status+'&id='+selectedId+'',
                data: {"window": window}
            });
        }
        function del(){
            var selectedId = Qm.getValueByName("id");
            var notice = "你确定要删除？\n删除后不能恢复！";
            if(selectedId==""){
                top.$.ligerDialog.alert('请先选择要删除的事项！', "提示");
                return;
            }
            $.del(notice,"app/zp/examroom/delete.do?",{"ids":selectedId.toString()});
        }


        function submitAction(o) {
            Qm.refreshGrid();
        }

    </script>
</head>
<body>
<qm:qm pageid="hr_zp_008" script="false" singleSelect="true" >

</qm:qm>

</body>
</html>