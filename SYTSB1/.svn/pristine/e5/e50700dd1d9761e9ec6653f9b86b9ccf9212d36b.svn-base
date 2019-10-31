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
    <title>需求审核</title>
    <%@ include file="/k/kui-base-list.jsp"%>
    <%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
    <script type="text/javascript">
        var qmUserConfig = {
            //自定义查询面板样式参数
            sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:60},// 可以自己定义
            //自定义查询面板查询字段
            sp_fields:[
				{name:"gw_name",compare:"like",value:""},
                {name:"fk_dep_id",compare:"=",value:""}, 
                {name:"use_m",compare:"=",value:""}
            ],

            tbar:[
				{ text:'详情', id:'detail',icon:'detail', click:detail},
				 "-",
	            { text:'修改', id:'edit',icon:'edit', click:edit },
				"-",
                { text:'审核', id:'check',icon:'check', click:check }

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
                    if(rowData.sign=="已提交")
                    {
                        return "style='color:red'";
                    }else{
                        return "";
                    }
                }
            }
        };
        //行选择改变事件
        function selectionChange(){
            var count=Qm.getSelectedCount();//行选择个数

            if(count==0){
                Qm.setTbar({check:false,edit:false,detail:false});
            }else if(count==1){

                Qm.setTbar({check:true,edit:false,detail:true});
                if(Qm.getValueByName("sign")=="审核通过"){
                    Qm.setTbar({check:false,edit:true,detail:true});
                }

            }else{
                Qm.setTbar({check:false,edit:false,detail:false});
            }
        }
        //审核
        function check(item){
            var selectedId = Qm.getValueByName("id");
            var status = "detail";
            var width=800;
            var height=300;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"招聘需求审核",
                content: 'url:app/zpmanage/zpxq/xqbp_detail.jsp?status='+status+'&id='+selectedId,
                data: {"window": window},
                cancelVal:'取消',
                cancel:true,
                button:[
                {
                	icon:'accept',
                    name:'通过',
                    callback:function(){
                        $.post('app/zp/xqxx/xqcheck.do',{id:selectedId,status:'1',yy:""},function(data){
                            Qm.refreshGrid();
                        },'html');

                        return true;
                    },
                    focus: true
                },
                {
                	icon:'return',
                    name:'不通过',
                    callback:function(){
                        $.ligerDialog.prompt('填写不通过原因',true, function (yes,value) {
                            if(yes)
                            $.post('app/zp/xqxx/xqcheck.do',{id:selectedId,status:'2',yy:value},function(data){
                                Qm.refreshGrid();
                            },'html');

                        });
                        return true;
                    },
                    focus: true
                }
                ]
            });
        }
        //查看信息
        function detail(item){
            var selectedId = new Array();
            var selectedJhrq = null;
            var status = "detail";
            if(item.id == "detail"){//点击修改按钮调用的本方法
                selectedId = Qm.getValuesByName("id");
            }else{//双击数据调用本方法
                selectedId[0] = item.id;
                status = "detail";
            }

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
			<font color="red">“红色”</font>代表状态为待审核、不可编辑。
			<font color="black">“黑色”</font>代表状态审核通过、可编辑。
			双击可查看数据详情。
		</div>
	</div>
</div>
<qm:qm pageid="hr_zp_002" script="false" singleSelect="true" >
</qm:qm>
<script type="text/javascript">
   Qm.config.columnsInfo.fk_dep_id.binddata=<u:dict sql="select t.id ,t.parent_id ,t.id,t.org_name from SYS_ORG t order by t.orders"></u:dict>;
</script>
</body>
</html>