<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%CurrentSessionUser useres = SecurityUtil.getSecurityUser();
String users=useres.getName();
String userid= useres.getId();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
      <link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
      
    <script type="">
$(document).ready(function() { 
var yzm=<%=request.getParameter("yzm")%> 

<%
String ss="";

if(request.getSession().getAttribute("edcrfv")!=null){
	ss=request.getSession().getAttribute("edcrfv").toString();}%>
var ss="";
<sec:authorize access="hasRole('TJY2_CW_GZ')">
		var ss=<%=ss%>+"";
    	</sec:authorize>

if(ss!="9999"){
	var pathName=window.document.location.pathname;  
    			var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1); 
 return window.location.href=projectName+"/finance/messageCheck_detail.jsp"; 
}

}); 
</script>
    <script type="text/javascript">
    
  
    
    function SunAmount() {
    	 // 绩效工资 合计计算
        var money1 = document.getElementById("jxgzJcjxMy").value;
        var money2 = document.getElementById("jxgzJcjxBf").value;
        var money3 = document.getElementById("jxgzJdjx").value;
        var money4 = document.getElementById("jxgzBtZcbt").value;
        var money5 = document.getElementById("jxgzBtQt").value;
        var sumamount = document.getElementById("jxgzXj");
        var sumAmount = parseFloat(money1 == "" ? 0 : money1) + parseFloat(money2 == "" ? 0 : money2) + parseFloat(money3 == "" ? 0 : money3)
        + parseFloat(money4 == "" ? 0 : money4)+ parseFloat(money5 == "" ? 0 : money5);
        sumamount.value = sumAmount == 0 ? "" : sumAmount.toFixed(2);
  
        //基本工资计算
        var money11 = document.getElementById("jbgzGwgz").value;
        var money22 = document.getElementById("jbgzXjgz").value;
        var money33 = document.getElementById("jbgzBlbt").value;
        var money44 = document.getElementById("jbgzLt").value;
        var money55 = document.getElementById("jbgzBfx").value;
        var money56 = document.getElementById("jbgzDz").value;
        var sumamount2 = document.getElementById("jbgzXj");
        var sumAmount2 = parseFloat(money11 == "" ? 0 : money11) + parseFloat(money22 == "" ? 0 : money22) + parseFloat(money33 == "" ? 0 : money33)
         + parseFloat(money44 == "" ? 0 : money44) + parseFloat(money55 == "" ? 0 : money55)+ parseFloat(money56 == "" ? 0 : money56);
        sumamount2.value = sumAmount2 == 0 ? "" : sumAmount2.toFixed(2);
        //扣除项计算
        var money13 = document.getElementById("kkxZynjMy").value;
        var money23 = document.getElementById("kkxZynjBk").value;
        var money333 = document.getElementById("kkxYlbxMy").value;
        var money43 = document.getElementById("kkxYlbxBf").value;
        var money53 = document.getElementById("kkxOldbxMy").value;
        var money63 = document.getElementById("kkxOldbxBf").value;
        var money73 = document.getElementById("kkxSyMy").value;
        var money83 = document.getElementById("kkxSyBf").value;
        var money93 = document.getElementById("kkxGjjMy").value;
        var money123 = document.getElementById("kkxGjjBf").value;
        var money103 = document.getElementById("kkxGhjf").value;
        var money113 = document.getElementById("kkxSds").value;
        var sumamount3 = document.getElementById("kkxXj");
        var sumAmount3 = parseFloat(money13 == "" ? 0 : money13) + parseFloat(money23 == "" ? 0 : money23) + parseFloat(money333 == "" ? 0 : money333)
         + parseFloat(money43 == "" ? 0 : money43) + parseFloat(money53 == "" ? 0 : money53)+ parseFloat(money63 == "" ? 0 : money63) + parseFloat(money73 == "" ? 0 : money73) + parseFloat(money83 == "" ? 0 : money83)
         + parseFloat(money93 == "" ? 0 : money93)+ parseFloat(money103 == "" ? 0 : money103)+ parseFloat(money113 == "" ? 0 : money113)+ parseFloat(money123 == "" ? 0 : money123);
        sumamount3.value = sumAmount3 == 0 ? "" : sumAmount3.toFixed(2);
        
        
         	// 合计计算
            var moneyq = sumamount.value
            var moneyw = sumamount3.value
            var moneye = sumamount2.value
            var sumamoun = document.getElementById("fsalary");
            var sumAmoun = parseFloat(moneyq == "" ? 0 : moneyq) - parseFloat(moneyw == "" ? 0 : moneyw) + parseFloat(moneye == "" ? 0 : moneye);
            sumamoun.value = sumAmoun == 0 ? "" : sumAmoun.toFixed(2);
          
        
}
   
    
        $(function () {
        	if ("${param.pageStatus}"=="detail")
        var	 tbar=[
		             { text: '关闭', id: 'close', icon:'cancel', click:function(){api.data.window.Qm.refreshGrid();api.close();}}];//参数设置示例
			$("#form1").initForm({ 
				  showToolbar: true,
			         toolbarPosition: "bottom",
			         toolbar: tbar,
				success: function (response) {
					if(response.success){
						top.$.notice("保存成功！",3);
						api.data.window.Qm.refreshGrid();
						api.close();
					}
					else{
						$.ligerDialog.error("操作失败！<br/>" + response.msg);
					}
				},
				getSuccess : function(res) {
				    var jbhj = res.data.jbgzGwgz+res.data.jbgzXjgz+res.data.jbgzBlbt+res.data.jbgzDz+res.data.jbgzLt+res.data.jbgzBfx;
					if(jbhj != res.data.jbgzXj){
						
						$("#jbgzXj").css("background-color","#FF7777");
						  
					}
					 var bfhj1 = res.data.jxgzJcjxMy+res.data.jxgzJcjxBf+res.data.jxgzJdjx
					 +res.data.jxgzBtZcbt+res.data.jxgzBtQt;
					 bfhj = bfhj1 == 0 ? "" : bfhj1.toFixed(9);
						if(bfhj != res.data.jxgzXj){
							$("#jxgzXj").css("background-color","#FF7777");
						}
						 var kchj = res.data.kkxZynjMy+res.data.kkxZynjBk+res.data.kkxYlbxMy+res.data.kkxYlbxBf+res.data.kkxOldbxMy+res.data.kkxOldbxBf+res.data.kkxSyMy+res.data.kkxSyBf+res.data.kkxGjjMy
						  +res.data.kkxGjjBf+res.data.kkxGhjf+res.data.kkxSds;
							if(kchj != res.data.kkxXj){
								
								$("#kkxXj").css("background-color","#FF7777");
							}
							 var hj = res.data.jbgzXj+res.data.jxgzXj-res.data.kkxXj;
								if(hj != res.data.fsalary){
									$("#fsalary").css("background-color","#FF7777");
								}
				},
			});
		});      
        
        function choosePerson(){
            top.$.dialog({
                width: 800,
                height: 450,
                lock: true,
                parent: api,
                title: "选择人员",
                content: 'url:app/common/person_choose.jsp',
                cancel: true,
                ok: function(){
                    var p = this.iframe.contentWindow.getSelectedPerson();
                    if(!p){
                        top.$.notice("请选择人员！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                        return false;
                    }
                //    $("#responsiblePersonid").val(p.id);
                    $("#name").val(p.name);
                    $("#fkEmployeeBaseId").val(p.id);
                    $("#telePhone").val(p.mobile_tel);
                 //   $("#department").val(p.org_name);
                  //  $("#departmentId").val(p.org_id);
					
                }
            });
        }
    </script>
    <script type="text/javascript">
    
</script>
<style>
div .input{width:97%;}
</style>
</head>
<body>
<form id="form1" action="finance/salaryAction/save.do?ids=${param.ids}" getAction="finance/salaryAction/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id"></input>
    <input type="hidden" id="fkEmployeeBaseId" name="fkEmployeeBaseId"></input>
    <input type="hidden" id="telePhone" name="telePhone"></input>
     <input type="hidden"  value="${param.imid}" type="text" id="importId" name="importId"></input>
    
    <h1 onclick="setDetailMony();" class="l-label" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">工资信息 </h1><div style="height:15px">&nbsp;</div>
        <table class="l-detail-table">
			<tr>
				<td class="l-t-td-left">姓　　名</td>
				<td class="l-t-td-right"><input value="<%=users %>" readonly="readonly" ligerui="{iconItems:[{icon:'user',click:choosePerson}]}" id="name" ltype="text" type="text" name="name"></input></td>
				<td class="l-t-td-left">应发合计</td>
				<td class="l-t-td-right" ><input  ltype="text" type="text" id="fsalary" name="fsalary"></input></td>
			</tr>
		</table>
		<div style="height:10px"></div>
		<table class="l-detail-table has-head-image">
			<tr><td colspan="7" style="background-color: #DEFEFE" class="l-t-td-left">基本工资</td></tr>
			<tr>
				<td   class="l-t-td-left" style="background-color: #DEFEFE">岗位工资</td>
				<td style="background-color: #DEFEFE" class="l-t-td-left">薪级工资</td>
				<td style="background-color: #DEFEFE" class="l-t-td-left">保留补贴</td>
				<td style="background-color: #DEFEFE" class="l-t-td-left">独子</td>
				<td style="background-color: #DEFEFE" class="l-t-td-left">粮贴</td>
				<td style="background-color: #DEFEFE" class="l-t-td-left">补发项</td>
				<td style="background-color: #DEFEFE;width:150px;">小计</td>
			</tr>
			<tr>
			<td class="l-t-td-right"><input onkeyup="SunAmount();"  ltype="text" type="text" id="jbgzGwgz" name="jbgzGwgz"></input></td>
			<td class="l-t-td-right"><input onkeyup="SunAmount();"  ltype="text" type="text" id="jbgzXjgz" name="jbgzXjgz"></input></td>
			<td class="l-t-td-right"><input onkeyup="SunAmount();"  ltype="text" type="text" id="jbgzBlbt" name="jbgzBlbt"></input></td>
			<td class="l-t-td-right"><input onkeyup="SunAmount();"  ltype="text" type="text" id="jbgzDz" name="jbgzDz"></input></td>
			<td class="l-t-td-right"><input onkeyup="SunAmount();"  ltype="text" type="text" id="jbgzLt" name="jbgzLt"></input></td>
			<td class="l-t-td-right"><input onkeyup="SunAmount();"  ltype="text" type="text" id="jbgzBfx" name="jbgzBfx"></input></td>
			<td class="l-t-td-right"><input onkeyup="SunAmount();"  ltype="text" type="text" id="jbgzXj" name="jbgzXj"></input></td>	
			</tr>
		</table>
		<div style="height:10px"></div>
		<table class="l-detail-table has-head-image">
		<tr><td colspan="6" style="background-color: #DEFEFE" class="l-t-td-left">绩效工资及其他</td></tr>
			<tr style="height:31px">
				<td colspan="2" style="background-color: #DEFEFE;;" class="l-t-td-left">基础绩效</td>
				<td rowspan="2" style="background-color: #DEFEFE;" class="l-t-td-left">季度<br>绩效</br></td>
				<td colspan="2"  style="background-color: #DEFEFE;" class="l-t-td-left">补贴</td>
				<td rowspan="2" style="background-color: #DEFEFE;width:150px;">小计</td>
			</tr>
			<tr style="height:31px">
				<td  style="background-color: #DEFEFE;;" class="l-t-td-left">每月</td>
				<td  style="background-color: #DEFEFE;" class="l-t-td-left">补发</td>
				<td  style="background-color: #DEFEFE;" class="l-t-td-left">驻场补助</td>
				<td  style="background-color: #DEFEFE;" class="l-t-td-left">其他</td>
			</tr>
			<tr style="height:31px">
				<td class="l-t-td-right" ><input onkeyup="SunAmount();" ltype="text" type="text" id="jxgzJcjxMy" name="jxgzJcjxMy"></input></td>
				<td class="l-t-td-right" ><input onkeyup="SunAmount();" ltype="text" type="text" id="jxgzJcjxBf" name="jxgzJcjxBf"></input></td>
				<td class="l-t-td-right" ><input onkeyup="SunAmount();" ltype="text" type="text" id="jxgzJdjx" name="jxgzJdjx"></input></td>
				<td class="l-t-td-right" ><input onkeyup="SunAmount();" ltype="text" type="text" id="jxgzBtZcbt" name="jxgzBtZcbt"></input></td>
				<td class="l-t-td-right" ><input onkeyup="SunAmount();"  ltype="text" type="text" id="jxgzBtQt" name="jxgzBtQt"></input></td>
				<td class="l-t-td-right" ><input onkeyup="SunAmount();"  ltype="text" type="text" id="jxgzXj" name="jxgzXj"></input></td>
			</tr>
			
		</table>
		<div style="height:10px"></div>
		<table class="l-detail-table has-head-image">
		<tr><td colspan="13" style="background-color: #DEFEFE" class="l-t-td-left">扣款项</td></tr>
			<tr>
				<td colspan="2" style="background-color: #DEFEFE" class="l-t-td-left">职业年金</td>
				<td colspan="2" style="background-color: #DEFEFE" class="l-t-td-left">医疗保险</td>
				<td colspan="2" style="background-color: #DEFEFE" class="l-t-td-left">养老保险</td>
				<td colspan="2" style="background-color: #DEFEFE" class="l-t-td-left">失业</td>
				<td colspan="2" style="background-color: #DEFEFE" class="l-t-td-left">公积金</td>
				<td rowspan="2" style="background-color: #DEFEFE" class="l-t-td-left">工会经费</td>
				<td rowspan="2" style="background-color: #DEFEFE" class="l-t-td-left">所得税</td>
				<td rowspan="2" style="background-color: #DEFEFE;width: 150px" class="l-t-td-left">小计</td>
			</tr>
			<tr>
				<td style="background-color: #DEFEFE" class="l-t-td-left" >每月</td>
				<td style="background-color: #DEFEFE" class="l-t-td-left">补扣</td>
				<td style="background-color: #DEFEFE" class="l-t-td-left">每月</td>
				<td style="background-color: #DEFEFE" class="l-t-td-left">补扣</td>
				<td style="background-color: #DEFEFE" class="l-t-td-left">每月</td>
				<td style="background-color: #DEFEFE" class="l-t-td-left">补扣</td>
				<td style="background-color: #DEFEFE" class="l-t-td-left">每月</td>
				<td style="background-color: #DEFEFE" class="l-t-td-left">补扣</td>
				<td style="background-color: #DEFEFE" class="l-t-td-left">每月</td>
				<td style="background-color: #DEFEFE;width: 150px" class="l-t-td-left">补扣</td>
			</tr>
			<tr>
			<td class="l-t-td-right"><input onkeyup="SunAmount();"  ltype="text" type="text" id="kkxZynjMy" name="kkxZynjMy"></input></td>
			<td class="l-t-td-right"><input onkeyup="SunAmount();"  ltype="text" type="text" id="kkxZynjBk" name="kkxZynjBk"></input></td>	
			<td class="l-t-td-right"><input onkeyup="SunAmount();"  ltype="text" type="text" id="kkxYlbxMy" name="kkxYlbxMy"></input></td>
			<td class="l-t-td-right"><input onkeyup="SunAmount();"  ltype="text" type="text" id="kkxYlbxBf" name="kkxYlbxBf"></input></td>
			<td class="l-t-td-right"><input onkeyup="SunAmount();"  ltype="text" type="text" id="kkxOldbxMy" name="kkxOldbxMy"></input></td>
			<td class="l-t-td-right"><input onkeyup="SunAmount();"  ltype="text" type="text" id="kkxOldbxBf" name="kkxOldbxBf"></input></td>
			<td class="l-t-td-right"><input onkeyup="SunAmount();"  ltype="text" type="text" id="kkxSyMy" name="kkxSyMy"></input></td>
			<td class="l-t-td-right"><input onkeyup="SunAmount();"  ltype="text" type="text" id="kkxSyBf" name="kkxSyBf"></input></td>
			<td class="l-t-td-right"><input onkeyup="SunAmount();"  ltype="text" type="text" id="kkxGjjMy" name="kkxGjjMy"></input></td>
			<td class="l-t-td-right"><input onkeyup="SunAmount();"  ltype="text" type="text" id="kkxGjjBf" name="kkxGjjBf"></input></td>
			<td class="l-t-td-right"><input onkeyup="SunAmount();"  ltype="text" type="text" id="kkxGhjf" name="kkxGhjf"></input></td>
			<td class="l-t-td-right"><input onkeyup="SunAmount();"  ltype="text" type="text" id="kkxSds" name="kkxSds"></input></td>
			<td class="l-t-td-right"><input onkeyup="SunAmount();"  ltype="text" type="text" id="kkxXj" name="kkxXj"></input></td>
			</tr>
		</table>
		<br>
		 
    
</form>
</body>
</html>
