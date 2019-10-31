<%--
  Created by IntelliJ IDEA.
  User: qin
  Date: 12-8-2
  Time: 上午11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.khnt.security.CurrentSessionUser"%>
<%@ page import="java.util.Map"%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>需求发布</title>
    <%@ include file="/k/kui-base-list.jsp"%>
    <%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
    <script type="text/javascript">
        var qmUserConfig = {
            //自定义查询面板样式参数
            sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:80},// 可以自己定义
            //自定义查询面板查询字段
            sp_fields:[
				{name:"gw_num",compare:"like",value:""},
				{name:"gw_name",compare:"like",value:""},
                {name:"use_m",compare:"=",value:""},
                {name:"sign",compare:"=",value:""},
                {name:"fk_dep_id",compare:"=",value:""}
            ],
            tbar:[
				{ text:'详情', id:'detail',icon:'detail', click:detail},
				"-",
				{ text:'修改', id:'edit',icon:'edit', click:edit},
				"-",
                { text:'发布', id:'fb',icon:'accept', click:fb },
                "-",
                { text:'取消发布', id:'cancel',icon:'cancel', click:cancel },
//                "-",
//                { text:'批量发布', id:'allfb',icon:'accept', click:allfb },
//                "-",
//                { text:'批量取消', id:'allcancel',icon:'accept', click:allcancel },
                "-",
                { text:'取消原因', id:'celyy',icon:'comment', click:celyy }

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
                    if(rowData.sign=="取消发布"||rowData.sign=="审核通过"){
                        return "style='color:red'";
                    }
                }
            }
        };
        //行选择改变事件
        function selectionChange(){
            var count=Qm.getSelectedCount();//行选择个数

            if(count==0){
                Qm.setTbar({fb:false,cancel:false,allfb:false,allcancel:false,detail:false,celyy:false,edit:false});
            }else if(count==1){

                Qm.setTbar({fb:true,cancel:false,allfb:false,allcancel:false,detail:true,celyy:false,edit:false});
                if(Qm.getValueByName("sign")=="已发布"){
                	Qm.setTbar({fb:false,cancel:true,allfb:false,allcancel:false,detail:true,celyy:false,edit:false});
                }else if(Qm.getValueByName("sign")=="取消发布"){
                	Qm.setTbar({fb:true,cancel:false,allfb:false,allcancel:false,detail:true,celyy:true,edit:true});
                }else if(Qm.getValueByName("sign")=="取消招聘"){
                	Qm.setTbar({fb:false,cancel:false,allfb:false,allcancel:false,detail:true,celyy:true,edit:false});
                }else if(Qm.getValueByName("sign")=="已结束"){
                	Qm.setTbar({fb:false,cancel:false,allfb:false,allcancel:false,detail:true,celyy:false,edit:false});
                }
                else if(Qm.getValueByName("sign")=="已开考"){
                	Qm.setTbar({fb:false,cancel:false,allfb:false,allcancel:false,detail:true,celyy:false,edit:false});
                }

            }else{
                Qm.setTbar({fb:false,cancel:false,allfb:true,allcancel:true,detail:false,celyy:false,edit:false});
            }
        }
        //取消发布
        function cancel(item){
            var selectedId = Qm.getValueByName("id");
            var status = "edit";
            var width=800;
            var height=500;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"招聘需求取消发布",
                content: 'url:app/zpmanage/zpxq/xqfb_detail.jsp?status='+status+'&id='+selectedId+'&sign=2',
                data: {"window": window}
            });
        }
        //信息批量发布
        function allfb(item){
            //
        }

        //批量0取消
        function allcancel(item){
            $.ligerDialog.confirm("确认要批量处理选择数据吗？！",function(val){
                if(val){

                    $.post('app/zp/xqxx/fball.do',{ids:id,status:'1'},function(data){
                        //submitAction();
                        top.$.dialog.notice({content:data});
                        Qm.refreshGrid();
                    },'html');
                }
            });
        }
        //取消原因查看
        function celyy(item){

                var selectedId = Qm.getValuesByName("id");
                var status = "detail";
                var width=800;
                var height=500;
                var windows=top.$.dialog({
                    width:width,
                    height:height,
                    lock:true,
                    title:"取消发布原因",
                    content: 'url:app/zpmanage/zpxq/xq_byy_list.jsp?status='+status+'&id='+selectedId+"&ywId=6",
                    data: {"window": window}
                });
        }

        //信息发布
        function fb(item){
            var selectedId = Qm.getValueByName("id");
            var status = "edit";
            var width=800;
            var height=500;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"招聘需求发布",
                content: 'url:app/zpmanage/zpxq/xqfb_detail.jsp?status='+status+'&id='+selectedId+'&sign=1',
                data: {"window": window}
            });
        }
        //查看信息
        function detail(item){
            var selectedId = Qm.getValueByName("id");
            var selectedJhrq = null;
            var status = "detail";

            var width=800;
            var height=500;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"查看招聘需求",
                content: 'url:app/zpmanage/zpxq/xqbp_detail.jsp?status='+status+'&id='+selectedId+'&sign=1',
                data: {"window": window}
            });
        }
        function edit(){
            var selectedId = Qm.getValueByName("id");
            var status = "edit";
            var width=800;
            var height=500;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"修改需求报批",
                content: 'url:app/zpmanage/zpxq/xqbp_detail.jsp?status='+status+'&id='+selectedId,
                data: {"window": window}
            });
        }
        function submitAction(o) {
            Qm.refreshGrid();
        }

    </script>
</head>
<body>
<div class="item-tm" id="titleElement">
    <div class="l-page-title">
		<div class="l-page-title-note">提示：列表数据项
			<font color="red">“红色”</font>代表状态为未发布或取消发布。
			<font color="black">“黑色”</font>代表状态已发布。
			双击可查看数据详情。
		</div>
	</div>
</div>
<qm:qm pageid="hr_zp_004" script="false" singleSelect="true" >
</qm:qm>
<script type="text/javascript">
   Qm.config.columnsInfo.fk_dep_id.binddata=<u:dict sql="select t.id ,t.parent_id ,t.id,t.org_name from SYS_ORG t order by t.orders"></u:dict>;
</script>
</body>
</html>