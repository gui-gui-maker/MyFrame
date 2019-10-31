<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="java.util.Calendar"%>
<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <%
    String firstDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
    String lastDate  = DateUtil.getLastDateStringOfYear("yyyy-MM-dd");
	%>
    <%@include file="/k/kui-base-list.jsp" %>
    <%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
    <script test="text/javascript">
    $(function () {
        /* $("#btn1").css({"height":"20px","line-height":"18px"})
        $("#btn2").css({"height":"20px","line-height":"18px"}) */
        $("#btn1").ligerButton({
            icon:"count",
            click: function (){
                init();
            },text:"统计"
        });
        $("#btn2").ligerButton({
            icon:"excel-export",
            click: function (){
                out();
            },text:"导出"
        });
        $("#form1").ligerForm(); 
        var data = <u:dict sql="select t.id as code,t.org_name as text from SYS_ORG t where t.parent_id = '100000' and t.id not in ('100032', '100039', '100069', '100048', '100070', '100038', '100059', '100061', '100051') and t.status = 'used' order by t.orders"/>;
        var tt = new Array();
        tt.push({id:'all',text:'全部'});
        for(var i in data){
           tt.push(data[i]);
        }
        $("#org_id").ligerComboBox().setData(tt);
        $("#org_id").ligerGetComboBoxManager().setValue("all");
        init();       
    });
    
    function init(){
    	var column;
    	var org_id = $("#org_id").ligerGetComboBoxManager().findValueByText($("#org_id").val());
    	var userName = $("#userName").val();
    	var startDate = $("#startDate").val();
        var lastDate = $("#lastDate").val();
        column=[
            { display: '部门', name: 'org_name',align: 'center', width: '10%'},
            { display: '员工ID', name: 'peopleId',hide:true,isAllowHide: false},
            { display: '姓名', name: 'emp_name',align: 'center', width: '10%'},
			{ display: '进院时间', name: 'into_work_date',align: 'center', width: '10%'},
			{ display: '工龄（年）', name: 'work_age',align: 'center', width: '7%'},
			{ display: '应休假天数', name: 'total_days',align: 'center', width: '7%'},
			{ display: '已休年假', name: 'nj_days_used',align: 'center', width: '7%'},
			{ display: '休假时间', name: 'nj_days_used_date',align: 'center', width: '11%'},
			{ display: '剩余年假', name: 'nj_days_left',align: 'center', width: '7%'},
			{ display: '其他已休', name: 'other_days_used',align: 'center', width: '10%'},
			{ display: '休假时间', name: 'other_days_used_date',align: 'center', width: '11%'},
			{ display: '备注', name: 'remark',align: 'center', width: '10%'}];
         $.post("BgLeaveAction/getLeaveInfoCount.do",{"org_id":org_id,"userName":userName,"startDate":startDate,"lastDate":lastDate}, function(resp){
            inputGrid = $("#countGrid").ligerGrid({
                columns: column, 
                data:{Rows:eval(JSON.stringify(resp.data))},//json格式的字符串转为对象
                height:'100%',
                usePager:false,
                rownumbers: true, 
                width:'100%',
                onSelectRow: function (rowdata, rowindex){
                },
                onDblClickRow : function (rowdata, rowindex, rowobj){
                	top.$.dialog({
             	       width: 1100,
             	       height: 800,
             	       lock: true,
             	       parent: api,
             	       data: {
             	     	 window: window
             	       },
             	       title: "请休假列表",
             	       content: 'url:app/humanresources/leave/leave_analysis_list.jsp?people_id='+rowdata.peopleId+"&startDate="+startDate+"&lastDate="+lastDate
             	    });
                }
             },"json");
        }); 
    }
    function out(){
    	var org_id = $("#org_id").ligerGetComboBoxManager().findValueByText($("#org_id").val());
        if(org_id==""){
            $.ligerDialog.alert("请先选择部门！");
            return;
        }else{
        	$("#org_id1").val(org_id);
        }
        $("body").mask("正在导出数据,请等待...");
        $("#form1").attr("action","BgLeaveAction/getLeaveInfoCountExport.do"); 
        $("#form1").submit();
        $("body").unmask();
    };
    </script>
</head>
<body>
<form name="form1" id="form1" action="" getAction="">
<input type="hidden" name="org_id1" id="org_id1"/>
<div class="item-tm">
    <div class="l-page-title2 has-icon has-note" style="height: 80px">
        <div class="l-page-title2-div"></div>
        <div class="l-page-title2-text"><h1>请休假信息统计</h1></div>
        <div class="l-page-title2-note">以员工请休假信息为统计对象。</div>
        <div class="l-page-title2-icon"><img src="k/kui/images/icons/32/statistics.png" border="0"/></div>
        <div class="l-page-title-content" style="top:25px;height:80px;"> 
            <table border="0" cellpadding="0" cellspacing="0" width="">
                <tr>
                    <td width="60" style="text-align: right;">部门：</td>
                    <td width="110px">
                        <input type="text" name="org_id" id="org_id" ltype="select" ligerui="{
                            readonly:true,
                            tree:{checkbox:false,data: }
                        }"/>
                    </td>
                    <td width="60" style="text-align: right;">姓名：</td>
                    <td width="110px">
                        <input type="text" id="userName" name="userName" ltype="text"/>
                    </td>
                    <td width="70" style="text-align: right;">年份从：</td>
                    <td width="110">
                        <input id="startDate" name="startDate" type="text" ltype="date" value="<%=firstDate %>"/>
                    </td>
                    <td align="center">&nbsp;到&nbsp;</td>
                    <td width="110">
                        <input id="lastDate" name="lastDate" type="text" ltype="date" value="<%=lastDate %>"/>
                    </td>
                    <td width="" style="text-align: right;float: left;padding-left: 5px;">
                        <div id="btn1"></div><div id="btn2"></div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</form>
<div position="center">
    <div id="countGrid"></div>   
</div>
</body>
</html>