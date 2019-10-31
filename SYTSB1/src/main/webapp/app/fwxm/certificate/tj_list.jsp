<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="java.util.Calendar"%>
<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <%@include file="/k/kui-base-list.jsp" %>
    <%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
    <script test="text/javascript">
    $(function () {
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
        
        var data = <u:dict sql="select t.id code,t.org_name text from SYS_ORG t order by t.orders"/>;
        var tt = new Array();
        tt.push({id:'all',text:'全部'});
        for(var i in data){
           tt.push(data[i]);
        }
        //$("#org_id").ligerComboBox().setData(tt);
       // $("#org_id").ligerGetComboBoxManager().setValue("all");
        init();       
    });
    
    
    function init(){
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
       // var org_id = $("#org_id").ligerGetComboBoxManager().findValueByText($("#org_id").val());
        var org_id=$("#org_id2").val();
        var userName = $("#userName").val();
        if(org_id==""){
            //$.ligerDialog.alert("请先选择部门！");
            //return;
        }
        $.post("tj/getCzData.do",{"startDate":startDate,"endDate":endDate,"org_id":org_id,"userName":userName}, function(resp){
            inputGrid = $("#countGrid").ligerGrid({
                columns: [
                     { display: '部门id', name: 'depId',align: 'center', width: 210,hide : true},
                         { display: '部门', name: 'depName',align: 'center', width: 250},
                         { display: '证书性质', name: 'zsxz',align: 'center', width: 210},
                         { display: '证书项目', name: 'zsxm',align: 'center', width: 120},
                         { display: '数量', name: 'number',align: 'center', width: 120,type: 'int'}
                ], 
                data:{Rows:eval(JSON.stringify(resp.data))},//json格式的字符串转为对象
                height:'100%',
                usePager:false,
                width:'100%',
                onSelectRow: function (rowdata, rowindex){
                	//$("#unitName").val(rowdata.unitId);
                	$("#unitName").ligerGetComboBoxManager().setValue(rowdata.unitId);
                	  $("#org_id2").val(rowdata.depId);
                	$("#org_id").val(rowdata.depName);
                },
                onDblClickRow:function (rowdata, rowindex){
                	  var startDate = $("#startDate").val();
                      var endDate = $("#endDate").val();
                    
                      //var org_id = $("#org_id").ligerGetComboBoxManager().findValueByText($("#org_id").val());
                      var org_id=rowdata.depId;
                      //var org_id=$("#org_id").val();
                      var unitName = $("#unitName").ligerGetComboBoxManager().findValueByText($("#unitName").val());
                	 top.$.dialog({
        		         width: 1000,
        		         height: 500,
        		         lock: true,
        		         parent: api,
        		         data: {
        		       	 window: window
        		         },
        		         title: "详情",
        		         content: 'url:app/finance/cw_bmfytj_list.jsp?pageStatus=detail&startDate='+startDate+"&endDate="+endDate+"&org_id="+org_id+"&unitName="+unitName
        	          });
                }
             },"json");
        });
    }
    function out()
    {
        /* var org_id = $("input[name='org_id']").ligerGetComboBoxManager().getValue(); */
        /* var org_id = $("#org_id").ligerGetComboBoxManager().findValueByText($("#org_id").val()); */
        /* var org_id=$("#org_id2").val();
        if(org_id==""){
            $.ligerDialog.alert("请先选择部门！");
            return;
        } */
        //$("#org_id1").val(org_id);
        $("body").mask("正在导出数据,请等待...");
        /* $("#form1").attr("action","sta/analyse/exportCountByUser.do"); */
        $("#form1").attr("action","tj/exportBmCount.do"); 
        $("#form1").submit();
        $("body").unmask();
    };
    function selectOrg(){
    	 top.$.dialog({
	         width: 350,
	         height: 400,
	         lock: true,
	         parent: api,
	         data: {
	       	 window: window
	         },
	         title: "部门",
	         content: 'url:app/finance/cw_bmfytj_org.jsp'
    	  });
    }
    function clickNodeId(unitId,unitName){
    	$("#org_id").val(unitName);
    	$("#org_id2").val(unitId);
    }
    </script>
</head>
<%-- <%
    String firstDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
    String curDate  = DateUtil.getDateTime("yyyy-MM-dd", new Date());
%> --%>
<body>
<form name="form1" id="form1" action="" getAction="" target="_blank">
<input type="hidden" name="org_id1" id="org_id1"/>
<input type="hidden" name="org_id2" id="org_id2"/>
<div class="item-tm">
    <div class="l-page-title2 has-icon has-note" style="height: 80px">
        <div class="l-page-title2-div"></div>
        <div class="l-page-title2-text"><h1>持证统计</h1></div>
        <div class="l-page-title2-note">以部门、证书项目为统计对象。</div>
        <div class="l-page-title2-icon"><img src="k/kui/images/icons/32/statistics.png" border="0"/></div>
        <div class="l-page-title-content" style="top:25px;height:80px;"> 
            <table border="0" cellpadding="0" cellspacing="0" width="">
                <tr>
                   <%--  <td style="text-align: right;width:50px;">单位：</td>
                    <td width="140px">
                        <u:combo attribute="initValue:''"   name="unitName" code="TJY2_UNIT" />
                       <input text="text" name="unitName" id ="unitName" 
							ltype="select" ligerui="{initValue:'',data:<u:dict code='TJY2_UNIT' />}" />
                    </td> --%>
                    <td width="60" style="text-align: right;">部门：</td>
                    <td width="110px">
                        <input type="text" name="org_id" id="org_id" ltype="text" onclick="selectOrg()" ligerui="{
                            readonly:true,
                            tree:{checkbox:false,data: }
                        }"/>
                    </td>
                    <td width="120" style="text-align: right;">注册开始时间从：</td>
                    <td width="110">
                            <input id="startDate" name="startDate" type="text" ltype="date" />
                    </td>
                    <td align="center">&nbsp;至&nbsp;</td>
                    <td width="110">
                        <input id="endDate" name="endDate" type="text" ltype="date" />
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