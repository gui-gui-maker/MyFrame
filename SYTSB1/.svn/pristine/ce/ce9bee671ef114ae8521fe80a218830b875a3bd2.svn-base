<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    CurrentSessionUser user = SecurityUtil.getSecurityUser();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>招聘报批</title>
    <%@ include file="/k/kui-base-list.jsp"%>
    <script type="text/javascript">
        var qmUserConfig = {
            //自定义查询面板样式参数
            sp_defaults:{columnWidth:0.3,labelAlign:'right',labelWidth:60},// 可以自己定义
            //自定义查询面板查询字段
            sp_fields:[
                {name:"gw_name",compare:"like",value:""},
                {name:"use_m",compare:"=",value:""},
                {name:"sign",compare:"=",value:""}
            ],

            tbar:[
				{ text:'详情', id:'detail',icon:'detail', click:detail},
				"-",
                { text:'新增', id:'add',icon:'add', click:add },
                "-",
                { text:'修改', id:'edit',icon:'edit', click:edit },
                "-",
                { text:'删除', id:'del',icon:'delete', click:del},
                "-",
                { text:'不通过原因', id:'checkno',icon:'comment', click:checkno},
                "-",
                { text:'提交', id:'submit',icon:'submit', click:submit}

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
                        return "style='color:blue'";
                    }else if(rowData.sign=="审核打回"||rowData.sign=="未提交"){
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
                Qm.setTbar({add:true,edit:false,del:false,detail:false,submit:false,checkno:false});
            }else if(count==1){

                Qm.setTbar({add:true,edit:true,del:true,detail:true,submit:true,checkno:false});
                if(Qm.getValueByName("sign")=="已提交"){
                    Qm.setTbar({edit:false,del:false,submit:false});
                }
                if(Qm.getValueByName("sign")=="审核打回"){
                    Qm.setTbar({checkno:true});
                }

            }else{

                Qm.setTbar({add:true,edit:false,del:true,detail:false,submit:true,checkno:false});
                var sign=Qm.getValuesByName("sign").toString().split(",");
                var str;
                for(str in sign){
                    //alert(str);
                    if(sign[str]=="已提交"){
                        Qm.setTbar({del:false,submit:false});
                    }
                }
            }
        }

        function edit(item){
            var selectedId = new Array();
            var selectedJhrq = null;
            var status = "edit";
            if(item.id == "edit"){//点击修改按钮调用的本方法
                selectedId = Qm.getValuesByName("id");
            }else{//双击数据调用本方法
                selectedId[0] = item.id;
                status = "detail";
            }
            if(selectedId.length > 1){
                top.$.ligerDialog.alert('不支持批量修改，请只选择一条数据！', "提示");
                return;
            }else if(selectedId.length < 1){
                top.$.ligerDialog.alert('请先选择要修改的数据！', "提示");
                return;
            }
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
            if(selectedId.length > 1){
                top.$.ligerDialog.alert('不支持批量修改，请只选择一条数据！', "提示");
                return;
            }else if(selectedId.length < 1){
                top.$.ligerDialog.alert('请先选择要修改的数据！', "提示");
                return;
            }
            var width=800;
            var height=500;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"查看招聘需求",
                content: 'url:app/zpmanage/zpxq/xqbp_detail.jsp?status='+status+'&id='+selectedId,
                data: {"window": window}
            });
        }
        //查看不通过原因
        function checkno(item){
            var selectedId = Qm.getValuesByName("id");
            var status = "detail";
            var width=800;
            var height=500;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"查看审核不通过原因",
                content: 'url:app/zpmanage/zpxq/xq_byy_list.jsp?status='+status+'&id='+selectedId+"&ywId=4",
                data: {"window": window}
            });
        }

        function add(item){
            var width=800;
            var height=500;
            var windows=top.$.dialog({
                width:width,
                height:height,
                lock:true,
                title:"新增需求报批",
                content: 'url:app/zpmanage/zpxq/xqbp_detail.jsp?status=add',
                data: {"window": window}
            });
        }
        function del(){
            var selectedId = Qm.getValuesByName("id");
            if(selectedId.length<1){
                top.$.ligerDialog.alert('请先选择要删除的事项！', "提示");
                return;
            }
            var tishi = "你确定要删除【"+Qm.getValueByName("dep_name")+"-"+Qm.getValueByName("gw_name")+"】这条需求吗？\n删除后不能恢复！";
            if(selectedId.length>1){
                tishi = "你确定要删除这 "+selectedId.length+" 条数据吗？\n删除后不能恢复！";
            }
            $.del(tishi,"app/zp/xqxx/delete.do?",{"ids":selectedId.toString()});
        }

        function submit(item){
            var id = Qm.getValuesByName("id");
            if(!id){
                $.ligerDialog.alert("请先选择要提交的数据！");
                return;
            }
            $.ligerDialog.confirm("确认要提交选择数据，提交将等待审核不能修改！",function(val){
                if(val){

                    $.post('app/zp/xqxx/submit.do',{id:id.toString()},function(data){
                    	if(data.success){
                    		 $.ligerDialog.alert(data);
                             Qm.refreshGrid();
                    	}
                    },'html');
                }
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
					<font color="red">“红色”</font>代表状态为未提交、审核打回,可编辑。
					<font color="blue">“蓝色”</font>代表状态为已提交,不可编辑。
					<font color="black">“黑色”</font>代表状态审核通过,不可编辑。
					双击可查看数据详情。
				</div>
			</div>
		</div>
<qm:qm pageid="hr_zp_001" script="false" singleSelect="false" >
    <sec:authorize access="!hasRole('verify_zp_admin')">
    <qm:param name="fk_dep_id" value="<%=user.getDepartment().getId()%>" compare="=" />
    </sec:authorize>
</qm:qm>

</body>
</html>