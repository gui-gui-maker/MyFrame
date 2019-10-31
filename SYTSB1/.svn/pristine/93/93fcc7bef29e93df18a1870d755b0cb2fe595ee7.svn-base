<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %> 
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser user = SecurityUtil.getSecurityUser();
User uu = (User)user.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
%>
	<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
				String nowTime=""; 
				nowTime = dateFormat.format(new Date());%>
  <title></title>
     <%@include file="/k/kui-base-form.jsp" %>
	 <script type="text/javascript" src="pub/bpm/js/util.js"></script>
	 <link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
	 <script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
     <script type="text/javascript">
    var tbar="";
    var serviceId = "${requestScope.serviceId}";//提交数据的id
	var activityId = "${requestScope.activityId}";//流程id
	var isChecked="${param.isChecked}";
    var businessPart="100020,100021,100022,100023,100024,100063,100034,100035,100037,100033,100065,100036,100049,100066,100067,100068"
    	 var pageStatus = "${param.pageStatus}";
			var repayment_time=$("#repayment_time").val(<%=nowTime%>);
		$(function () {
			 $("#fgld").hide();
			$("#xxx").hide();
			$("#hk").transform("detail",function(){});
   	    	$("#hk").setValues("money/borrow/detail.do?id=${param.id}");
    	//$("#areaFlag").val(areaFlag);
        if ("${param.pageStatus}"=="detail"){
        tbar=[<sec:authorize access="hasRole('TJY2_CW_CHECK')">
		        { text: '打印', id: 'print', icon: 'print', click: print},
		        </sec:authorize>
        	  { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}
        ];} 
        if ("${param.pageStatus}"=="detail" && "${param.a}"=="a"){
        	if("${param.hkstatus}"=="1"){
        		tbar=[{ text: '取消确认', id: 'submit', icon: 'submit', click: undirectChange},
              	  { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}
              ];
        	}else if("${param.hkstatus}"=="0"){
        		tbar=[{ text: '确认', id: 'submit', icon: 'submit', click: directChange},
              	  { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}
              ];
        	}
            }
        if ("${param.status}"=="aa" && "${param.pageStatus}"=="detail"){
        	tbar=[ { text: '审核', id: 'chu', icon: 'dispose', click: chu},
        	       <sec:authorize access="hasRole('TJY2_CW_CHECK')">
		            { text: '打印', id: 'print', icon: 'print', click: print},
		            </sec:authorize>
             	  { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];}
    	$("#form1").initForm({
            showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar,
            getSuccess : function(res) {
            	//如果申请部门id不为空，并且属于业务部门（机电、承压），则将管领导签字部分显示出来
                if(res.data.departmentId!="" && businessPart.indexOf(res.data.departmentId)>-1){
                    $("#fgld").show();
                }
            	 //$("#repayment_time").html(<%=nowTime%>);
                var zje=res.data.import_money;
                var money = zje.toString();
                var temp = money.split(".");
                if(temp[1]){
                }else{
                    var a = zje+".00";
                    $("#import_money").val(a);
                    $("#import_money").html(a);
                }
                if(temp[0].length>0){
                $("#yuan").html(temp[0].charAt(temp[0].length-1));}
                if(temp[0].length>1){
                $("#shi").html(temp[0].charAt(temp[0].length-2));}
                if(temp[0].length>2){
                $("#bai").html(temp[0].charAt(temp[0].length-3));}
                if(temp[0].length>3){
                $("#qian").html(temp[0].charAt(temp[0].length-4));}
                if(temp[0].length>4){
                $("#wan").html(temp[0].charAt(temp[0].length-5));}
                if(temp[0].length>5){
                $("#shiwan").html(temp[0].charAt(temp[0].length-6));}
                if(temp[1]){
	                if(temp[1].length>0){
	                $("#jiao").html(temp[1].charAt(temp[1].length-2));}
	                if(temp[1].length>1){
	                $("#fen").html(temp[1].charAt(temp[1].length-1));}
                }
//                 else{
//                     $("#jiao").html("0");
//                     $("#fen").html("0");
//                 }
            }
    	});
    	$("body").append('<object style="height:1px;" id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0><embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed></object>');
    });
	 function chu() {
			var id = "${param.id}";
			$.ligerDialog.confirm('是否要审批？', function (yes){
	          	 if(!yes){return false;}
	              top.$.ajax({
	                   url: "money/borrow/cwhkshtg.do?id="+id,
	                   type: "POST",
	                   dataType:'json',
	                   async: false,
	                   success:function (data) {
	                   	$("body").unmask();
	                       //api.close();
 	                       toDetail();
	                       top.$.dialog.notice({content:'操作成功！'});
	                           api.data.window.Qm.refreshGrid();
// 	                       api.data.window.reLoad();
	                       //setTimeout("close()","300");
	                   },
	                   error:function () {
	                   	 $("body").unmask();
	                       $.ligerDialog.error("审批失败！");
	                        $("#save").removeAttr("disabled");
	                   }
	               });
	          });
// 			var status = "aa";
// 			top.$.dialog({
// 				width: 600,
// 				height: 250,
// 				lock: true,
// 				parent: api,
// 				data: {
// 					window: window
// 				},
// 				title: "财务审核",
// 				content: 'url:app/finance/finance_bills_yijian.jsp?id='+id+'&status='+status+'&pageStatus=add'

// 			});
		}	
    function print(){
       var LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));
       var strBodyStyle = "<style>" + $("#pstyle").html() + "</style>";
       LODOP.PRINT_INIT("打印还款单");
       LODOP.ADD_PRINT_HTM('120px', '100px', "770px", "520px", strBodyStyle + $("#form1").html());
       LODOP.SET_PRINT_PAGESIZE (2, 0, 0,"B5(ISO)");
       LODOP.PRINT();
    }
    function directChange(){ 
//     	var repayment_man_id=$("#repayment_man_id").val();
//         var repayment_man=$("#repayment_man").val();
//         var repayment_time=$("#repayment_time").val();
        var id="${param.id}";

 			 $.ligerDialog.confirm('是否确认已还款？', function (yes){
 	        	if(!yes){return false;}
 	            top.$.ajax({
 	                 url: "money/borrow/huansubmit.do?id="+id+"&check=1",//+"&repayment_man_id="+repayment_man_id+
 	                		 //"&repayment_time="+repayment_time,
 	                 type: "POST",
 	                 dataType:'json',
 	                // data:"&repayment_man="+repayment_man,
 	                 async: false,
 	                 success:function (data) {
//  	                	 if("还款已提交"==up_status){
//  	                		 $.ligerDialog.warn("此条还款已提交！");
//  	                	 }else{
 		                	 if(data.success){
 		                		 top.$.notice('确认已还款成功！',3);
 		                		api.close();
 		                		api.data.window.refreshGrid();//刷新
 		                     }else{
 		                         $.ligerDialog.warn(data.msg);
 		                     }
// 	                	 }
 	                 },
 	                 error:function () {
 	                     $.ligerDialog.warn("确认已还款失败！");
 	                 }
 	             });
 	        });

// 			 $("#form1").submit();
// 		 }else{
// 			 return;
// 		}
	}
	function undirectChange(){ 
//     	var repayment_man_id=$("#repayment_man_id").val();
//         var repayment_man=$("#repayment_man").val();
//         var repayment_time=$("#repayment_time").val();
        var id="${param.id}";

 			 $.ligerDialog.confirm('是否取消已还款？', function (yes){
 	        	if(!yes){return false;}
 	            top.$.ajax({
 	                 url: "money/borrow/huansubmit.do?id="+id+"&check=0",//+"&repayment_man_id="+repayment_man_id+
 	                		 //"&repayment_time="+repayment_time,
 	                 type: "POST",
 	                 dataType:'json',
 	                // data:"&repayment_man="+repayment_man,
 	                 async: false,
 	                 success:function (data) {
//  	                	 if("还款已提交"==up_status){
//  	                		 $.ligerDialog.warn("此条还款已提交！");
//  	                	 }else{
 		                	 if(data.success){
 		                		 top.$.notice('取消已还款成功！',3);
 		                		api.close();
 		                		api.data.window.refreshGrid();//刷新
 		                     }else{
 		                         $.ligerDialog.warn(data.msg);
 		                     }
// 	                	 }
 	                 },
 	                 error:function () {
 	                     $.ligerDialog.warn("取消已还款失败！");
 	                 }
 	             });
 	        });

// 			 $("#form1").submit();
// 		 }else{
// 			 return;
// 		}
	}
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
                $("#repayment_man_id").val(p.id);
                $("#repayment_man").val(p.name);
            }
        });
    }
    function toDetail(){
    	$("#chu").hide();
        $("#up").hide();
        //form1   jksq
    	$("#form1").transform("detail",function(){});
	    $("#form1").setValues("money/borrow/detail.do?id=${param.id}");
    }
    </script>
    
   <style>
h2{font-family:宋体;font-size:6mm; text-align:center;margin:10px 0 0 0;}
    </style>
   <style type="text/css" media="print" id="pstyle">
* {
    font-family:"宋体";
    font-size:15px;
    letter-spacing:normal;
    
}
table{ margin:0 auto;}
table td{ height:40px;}
.l-detail-table td, .l-detail-table {
    border-collapse: collapse;
    border: 1px solid black;
}
.l-detail-table {
    padding:5px;
    border:0px solid #CFE3F8;
    border-top:0px;
    border-left:0px;
    word-break:break-all;
    table-layout:fixed;
}
.check {
    width:720px;
}
.l-t-td-left{ text-align:center;}
.l-t-td-right{ padding-left:5px;}
.fybx2{   height:40px; line-height:20px; overflow: hidden;}

h2{font-family:宋体;font-size:6mm; text-align:center;margin:10px 0 0 0;}

</style>
</head>
<body >
<form id="form1" action="money/borrow/huansubmit.do" getAction="money/borrow/detail.do?id=${param.id}">
   
    <input type="hidden" id="id" name="id"/>
    <input type="hidden" id="registrant" name="registrant" value=""/>
    <input type="hidden" id="registrantId" name="registrantId" value=""/>
    <input type="hidden" name="status" value=""/>
    <input type="hidden" id="departmentId" name="departmentId" value=""/>
    <input type="hidden" id="repayment_man_id" name="repayment_man_id" value="<%=userId%>"/>
<%--     <input id="repayment_time" name="repayment_time" type="hidden" value="<%=nowTime%>" /> --%>
    <h2>还&nbsp;款&nbsp;单 </h2></br>
    <table class="check">
             <tr>
                    <td width="160px"></td>
                    <td width="160px" class="l-t-td-right">
                    </td>
                    <td width="380px">&nbsp;</td>
                    <td width="80px" align="center">编号:</td>
                    <td  style="width: 130px;" class="l-t-td-right"><input ltype='text' name="identifier" type="text" id="identifier" readonly="readonly"/></td>
                    <td width="280px">&nbsp;</td>
                    <td width="80px" align="center"></td>
                    <td width="60" class="l-t-td-right"></td>
                    <td width="20"></td>
                     </td>
                    
                   
                </tr>
            </table>
    <table id="hk" border="1" cellpadding="3" class="l-detail-table has-head-image">
         <tr>
        <th style="border:0px;width:45px"></th>
        <th style="border:0px;width:45px"></th>
        <th style="border:0px;width:45px"></th>
        <th style="border:0px;width:45px"></th>
            <th style="border:0px;width:45px"></th>
            <th style="border:0px;width:45px"></th>
            <th style="border:0px;width:45px"></th>
            <th style="border:0px;width:45px"></th>
            <th style="border:0px;width:45px"></th>
            <th style="border:0px;width:45px"></th>
            <th style="border:0px;width:45px"></th>
            <th style="border:0px;width:65px"></th>
            <th style="border:0px;width:20px"></th>
            <th style="border:0px;width:20px"></th>
            <th style="border:0px;width:20px"></th>
            <th style="border:0px;width:20px"></th>
            <th style="border:0px;width:20px"></th>
            <th style="border:0px;width:20px"></th>
            <th style="border:0px;width:20px"></th>
            <th style="border:0px;width:20px"></th>
            
            </tr>
        <tr>
        	<td class="l-t-td-left" colspan="2">单位</td>
        	<td colspan="6" class="l-t-td-right">
        		<u:combo name="unit" code="TJY2_UNIT" />
        	</td>
        	<td class="l-t-td-left" colspan="2">部门</td>
        	<td class="l-t-td-right" colspan="10"><input  validate="{maxlength:20}" ltype="text"  name="department" id="department" type="text"/></td>

        </tr>
        <tr>
        	<td class="l-t-td-left" colspan="2">日期</td>
        	<td colspan="6" class="l-t-td-right"><input name="borrowMoneyDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" value="<%=nowTime%>" /></td>
        	<td class="l-t-td-left" colspan="2">借款人姓名</td>
        	<td colspan="10" class="l-t-td-right">
				<input name="borrower" type="text" ltype="text" validate="{required:true,maxlength:50}" />
			</td>
        </tr>
        <tr>
        	<td colspan="12" align="center" class="l-t-td-left">付款方式</td>
        	<td align="center" class="l-t-td-left" colspan="8">金额</td>
        </tr>
        <tr>
        	<td class="l-t-td-left" colspan="2">现金</td>
        	<td colspan="10" class="l-t-td-left">转账、电汇</td>
							<td align="center">拾万</td>
							<td align="center">万</td>
							<td align="center">仟</td>
							<td align="center">佰</td>
							<td align="center">拾</td>
							<td align="center">元</td>
							<td align="center">角</td>
							<td align="center">分</td>
						</tr>
        <tr> 
        	<td rowspan="2" class="l-t-td-right" colspan="2"><input id="xianjin" name="cash" type="text" ltype='text' onkeyup="sumAmount();"/></td>
        	<td class="l-t-td-left" colspan="3">收款单位</td>
        	<td class="l-t-td-left" colspan="3">开户行</td>
        	<td class="l-t-td-left" colspan="3">帐号</td>
        	<td class="l-t-td-left" colspan="1">金额</td>
        	<td class="l-t-td-right"><input id="shiwan" type="text" ltype="text" onkeyup="getMonyNum('wan');" validate="{maxlength:1}" /></td>
                            <td class="l-t-td-right"><input id="wan" type="text" ltype="text" onkeyup="getMonyNum('qian');" validate="{maxlength:1}" /></td>
                            <td class="l-t-td-right"><input id="qian" type="text" ltype="text" onkeyup="getMonyNum('bai');" validate="{maxlength:1}" /></td>
                            <td class="l-t-td-right"><input id="bai" type="text" ltype="text" onkeyup="getMonyNum('shi');" validate="{maxlength:1}" /></td>
                            <td class="l-t-td-right"><input id="shi" type="text" ltype="text" onkeyup="getMonyNum('yuan');" validate="{maxlength:1}" /></td>
                            <td class="l-t-td-right"><input id="yuan" type="text" ltype="text" onkeyup="getMonyNum('jiao');" validate="{maxlength:1}" /></td>
                            <td class="l-t-td-right"><input id="jiao" type="text" ltype="text" onkeyup="getMonyNum('fen');" validate="{maxlength:1}" /></td>
                            <td class="l-t-td-right"><input id="fen" ltype="text" onkeyup="getMonyNum('fen');" validate="{maxlength:1}" name="fen" type="text" /></td>
        </tr>
         <tr>
        	<td class="l-t-td-right" colspan="3"><input name="payee" type="text" ltype="text"  /></td>
        	<td class="l-t-td-right" colspan="3"><input name="openingBank" type="text" ltype="text"  /></td>
        	<td class="l-t-td-right" colspan="3"><input name="accounts" ltype="text" type="text" /></td>
        	<td class="l-t-td-right" colspan="1"><input name="money" id="money" ltype="text" type="text" onkeyup="sumAmount();"/></td>
        	<td class="l-t-td-right" colspan="8">
				<input name="import_money" id="import_money" type="text" ltype="text" title="总金额" onkeyup="sumAmount();"/>        	
			</td>
        </tr>
        <tr>
        	<td class="l-t-td-left" colspan="2">人民币大写</td>
        	<td colspan="6" class="l-t-td-right"><input name="rmb" id="rmb" type="text" ltype="text" validate="{maxlength:50}" /></td>
			<td  class="l-t-td-left" colspan="2">备注</td>
			<td  colspan="10" class="l-t-td-right"><input name="remark" type="text" ltype="text" validate="{maxlength:50}" /></td>

        </tr>
        <tr>
            <td class="l-t-td-left" style="border-top:1px;width:90px" colspan="2">还款人</td>
            <td colspan="6" class="l-t-td-right" style="border-top:1px;width:270px" ><input name="borrower" id="borrower" type="text" ltype="text" 
                validate="{required:true}"  ligerUi="{disabled:true}"/></td>
            <td style=" border-top:1px;width:90px" class="l-t-td-left" colspan="2">还款时间</td>
            <td colspan="10"  style="border-top:1px;width:375px" class="l-t-td-right">
            <input id="repayment_time" name="repayment_time" type="text" ltype='text' /></td>
</tr>
    </table>
	<table class="check">
		<tr>
			<td>单位负责人：</td>
			<td class="l-td-opinion">
                <p class="signer-date">
                    <span class="l-content signer"></span>
                </p>
			 </td>
			<td>分管院领导：</td>
            <td class="l-td-opinion">
                <p class="signer-date">
                    <span class="l-content signer"></span>
                </p>
             </td>
			<td><div id="fgld">分管领导：</div></td>
            <td class="l-td-opinion">
                <p class="signer-date">
                    <span class="l-content signer"></span>
                </p>
             </td>
			<td>财务审核：</td>
            <td class="l-td-opinion">
                <p class="signer-date">
                    <span class="l-content signer"></span>
                </p>
             </td>
<!-- 			<td>还款人：</td> -->
<!-- 			<td class="l-td-opinion"> -->
<!--                 <p class="signer-date"> -->
<!--                     <span class="l-content signer"></span> -->
<!--                 </p> -->
<!--              </td> -->
		</tr>
	</table>
	
</form>
</body>
</html>
