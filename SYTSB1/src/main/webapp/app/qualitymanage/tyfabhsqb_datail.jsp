<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %> 
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
	<link type="text/css" rel="stylesheet" href="app/qualitymanage/css/form_detail.css" />
	<script type="text/javascript" src="pub/bpm/js/util.js"></script>
	<script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
	
    <script type="text/javascript">
    var tbar="";
	var ab=pageStatus="${param.pageStatus}";
	var id="${param.id}";
    var a1="${param.a}";
    var status="${param.status1}";
 	$(function () {
 		if(status=="CANC" || status=="CANCLLING"){
 			document.getElementById("tabReason").style.display="";//显示
 		}
    	if(a1=='1'){
//      		$("#hh").transform("modify",function(){});
//     		$("#aa").transform("detail",function(){});
//     		$("#bb").transform("detail",function(){});
//     		$("#cc").transform("detail",function(){});
//     		$("#dd").transform("detail",function(){});
//     		$("#ee").transform("detail",function(){});
//     		$("#ff").transform("detail",function(){});
//     		$("#gg").transform("detail",function(){});
//     		$("#aa").setValues("Tyfabh/a/detail.do?id=${param.id}");
//     		$("#bb").setValues("Tyfabh/a/detail.do?id=${param.id}");
//     		$("#cc").setValues("Tyfabh/a/detail.do?id=${param.id}");
//     		$("#dd").setValues("Tyfabh/a/detail.do?id=${param.id}");
//     		$("#ee").setValues("Tyfabh/a/detail.do?id=${param.id}");
//     		$("#ff").setValues("Tyfabh/a/detail.do?id=${param.id}");
//     		$("#gg").setValues("Tyfabh/a/detail.do?id=${param.id}");

		$("#fabh").transform("modify",function(){});
		$("#ty").transform("detail",function(){});
   		$("#ty").setValues("Tyfabh/a/detail.do?id=${param.id}");


   	    	tbar=[{ text: '通过', id: 'submit1', icon: 'accept', click: submitSh},
   	    		{ text: '不通过', id: 'submit2', icon: 'forbid', click: nosubmitSh},
                   { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
        }else if(a1=='2'){
        	$("#form1").transform("detail",function(){});
        	tbar=[{ text: '通过', id: 'submit3', icon: 'accept', click: submitSh1},
                     { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
        } else {
            tbar=[{ text: '保存', id: 'up', icon: 'save', click: directChange},
                { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
        }
    	 if ("${param.pageStatus}"=="detail"){
    	     tbar=[{ text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
    	 }
    	$("#form1").initForm({
            showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar,
            getSuccess : function(res) { //
            	var c=res.data.ywfwbjbrTime;
            	var ywfwbjbrTime=c.substring(c.indexOf("/")+1,c.lastIndexOf(" "));
            	$("#ywfwbjbrTime").html(ywfwbjbrTime);
            	$("#ywfwbjbrTime").val(ywfwbjbrTime);
            }
    	});

    });
 	function submitSh(){
 		var fabh=$("#as1").val();
 		var fabhId=$("#fabhId").val();
 		if(fabh==""){ $.ligerDialog.error("请先选择方案编号再通过审核！!");}else{
 		$.ligerDialog.confirm('您确定审核通过吗？', function (yes){
        	if(!yes){return false;}
        	$("body").mask("正在保存......");
			top.$.ajax({
	            url: "Tyfabh/a/submitsh.do?id="+id,
	            type: "POST",
	            dataType:'json',
	            data: "&fabhId="+fabhId+"&fabh="+fabh+"&a="+"2",
	            async: false,
	            success:function (data) {
	               if(data.success){
	            	   $("body").unmask();
	                  top.$.notice("审核通过！！",3);
	                  api.data.window.Qm.refreshGrid();//刷新
	                   api.close();
	               }
	            },
	            error:function () {
	            	$("body").unmask();
	           	 $.ligerDialog.error("出错了！请重试！!");
	            }
	        });
		});}
 	}
	function nosubmitSh(){
		var fabh=$("#as1").val();
 		var fabhId=$("#fabhId").val();
 		$.ligerDialog.confirm('您确定审核不通过吗？', function (yes){
        	if(!yes){return false;}
        	$("body").mask("正在保存......");
			top.$.ajax({
	            url: "Tyfabh/a/submitsh.do?id="+id,
	            type: "POST",
	            dataType:'json',
	            data: "&fabhId="+fabhId+"&fabh="+fabh+"&a="+"1",
	            async: false,
	            success:function (data) {
	               if(data.success){
	            	   $("body").unmask();
	                  top.$.notice("审核未通过！！",3);
	                  api.data.window.Qm.refreshGrid();//刷新
	                   api.close();
	               }
	            },
	            error:function () {
	            	$("body").unmask();
	           	 $.ligerDialog.error("出错了！请重试！!");
	            }
	        });
		});
 	}
	function submitSh1(){
 		$.ligerDialog.confirm('您确定作废通过吗？', function (yes){
        	if(!yes){return false;}
        	$("body").mask("正在保存......");
			top.$.ajax({
	            url: "Tyfabh/a/cancelsh.do?id="+id,
	            type: "POST",
	            dataType:'json',
	            data: "",
	            async: false,
	            success:function (data) {
	               if(data.success){
	            	   $("body").unmask();
	                  top.$.notice("作废通过！！",3);
	                  api.data.window.Qm.refreshGrid();//刷新
	                   api.close();
	               }
	            },
	            error:function () {
	            	$("body").unmask();
	           	 $.ligerDialog.error("出错了！请重试！!");
	            }
	        });
		});
 	}
    function directChange(saveone){ 
       	var obj=$("#form1").validate().form();
    	 if(obj){
    		 var formData = $("#form1").getValues();
             $("body").mask("正在保存......");
            $.ajax({
                url: "Tyfabh/a/save.do",
                type: "POST",
                datatype: "json",
                contentType: "application/json; charset=utf-8",
                data: $.ligerui.toJSON(formData),
                success: function (data, stats) {
                    $("body").unmask();
                    if (data["success"]) {
                    	top.$.notice(data.msg,3);	
                        //top.$.dialog.notice({content:'保存成功！'});
                    	 api.data.window.Qm.refreshGrid();
		                     api.close();
                    }else{
                        $.ligerDialog.error('提示：' + data.msg);
                        api.data.window.Qm.refreshGrid();
                    }
                },
                error: function (data,stats) {
                    $("body").unmask();
                    $.ligerDialog.error('提示：' + data.msg);
                }
            });
    	 }else{
    		 return;
    	}
    	 }
        function choosePerson(){
        	//var api=api.data;
        	top.$.dialog({
                width: 800,
                height: 450,
                lock: true,
                parent: api.data,
                title: "选择人员",
                content: 'url:app/common/person_choose.jsp',
                cancel: true,
                ok: function(){
                    var p = this.iframe.contentWindow.getSelectedPerson();
                    if(!p){
                        top.$.notice("请选择人员！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                        return false;
                    }
                    $("#applyNanId").val(p.id);
                    $("#applyName").val(p.name);
                    $("#department").val(p.org_name);
                    $("#departmentId").val(p.org_id);
                }
            });
        }
        function setfabh(){
        	var status=$("#status").val();
        	if(status=='JBR'){
	        	top.$.dialog({
						parent: api,
						width : 860, 
						height : 500, 
						lock : false, 
						title:"选择方案编号",
						content: 'url:app/qualitymanage/tyfabhsqb_xuanze.jsp',
						data : {"parentWindow" : window}
					});
        	}else{
        		$.ligerDialog.error("亲，你不可以选择哦！！！");
        	}
        }
        function callBackReport(id, fwbjhbg_num){
	    	 $("#fabh").html(fwbjhbg_num);
	    	 //$("#ty").setValues("Tyfabh/a/detail.do?id=${param.id}");
	    	 $("#as1").val(fwbjhbg_num);
	    	 $("#fabhId").val(id);
   	}	
    </script>
</head>
<style>
.l-t-td-right1 {
    padding: 0;
    margin: 0;
}

.l-t-td-right1 .l-text {
    background-image: none;
}

.l-t-td-right1 .l-text-wrapper {
    width: 100%;
    height: 90px;
}

.l-textarea .l-text-wrapper {
    width: 100%;
    height: 100%;
}

.l-textarea-onerow {
    height: 30px;
}

.l-textarea-onerow div {
    padding: 0;
}

.l-t-td-right1 .l-text {
    border: none;
}

.l-t-td-right1 .l-text.change, .l-t-td-right1 .l-radio-group-wrapper.change
    {
    background: url("../images/x-input.png") repeat-x;
}

.l-t-td-right1 .l-text input {
    height: 90px;
    padding-top: 0;
    line-height: 24px;
}

.l-t-td-right1 .l-radio-group-wrapper {
    height: 90px;
    padding-left: 5px;
}

.l-t-td-right1 textarea {
    height: 100%;
}

.l-textarea-onerow textarea {
    height: 12px;
    padding: 6px 5px;
    width: 98%
}

.l-t-td-right1 label {
    height: 90px;
    line-height: 24px;
    display: inline-block;
}

.l-t-td-right1 div.input, .l-td-right div.input {
    border: none;
    padding-left: 5px;
}

.l-t-td-right1 .input-warp div {
    height: 100%;
    line-height: 28px;
}
</style>
<body >
<form id="form1" action="Tyfabh/a/save.do" getAction="Tyfabh/a/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id"/>
    <input type="hidden" id="status" name="status"/>
    <input type="hidden" id="applyNameId" name="applyNameId"/>
    <input type="hidden" id="fabhId" name="fabhId"/>
    
        <input type="hidden" id="as1" name="as1"/>
    
    <h1 id="ty2" align="center" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">通用方案编号申请表 </h1></br>
    <table id="ty1" class="check">
			 <tr>
                    <td width="650px">&nbsp;</td>
                    <td width="50px" align="center">编号：</td>
                    <td style="width: 200px;" class="l-t-td-right"><input ltype='text' readOnly="true" name="identifier" type="text"/></td>
           			<td width="10px">&nbsp;</td>
                    <td width="10px" align="center"></td>
                    <td width="20" class="l-t-td-right"></td>
            </tr>
	</table>
    <table id="ty" border="1" cellpadding="3" class="l-detail-table" width="720px">
    	<tr>
	        <th style="border:0px;width:130px"></th>
	        <th style="border:0px;width:130px"></th>
	        <th style="border:0px;width:130px"></th>
	        <th style="border:0px;width:130px"></th>
            <th style="border:0px;width:130px"></th>
            <th style="border:0px;width:130px"></th>
        </tr>
        <div id="aa">
        <tr>
            <td class="l-t-td-left">方案名称</td>
         	<td class="l-t-td-right" colspan="5"><input ltype="text"  name="schemeName" id="schemeName" type="text"/></td>
        </tr>
        
        </div>
        <tr id="bb">
            <td class="l-t-td-left">设备种类（注）</td>
         	<td class="l-t-td-right" colspan="5"><u:combo name="equipmentVariety" code="device_type" validate="required:true"  attribute="isMultiSelect:false" /></td>
        </tr>
        <tr id="cc">
            <td class="l-t-td-left">设备类别或品种（注）</td>
            <td class="l-t-td-right" colspan="5"><u:combo name="equipmentCategoryPz" code="device_classify1" validate="required:true"  attribute="isMultiSelect:false" /></td>
        </tr>
        <tr id="dd">
            <td class="l-t-td-left">检验类别（注）</td>
            <td class="l-t-td-right" colspan="5"><u:combo name="equipmentType" code="TJY2_TYFA_JYLB" validate="required:true" attribute="isMultiSelect:false" /></td>
        </tr>
         <tr id="ee">
        	<td class="l-t-td-left">备注</td>
			<td class="l-t-td-right1" colspan="5">
			<textarea name="remarks" id="remarks" rows="7" cols="25" class="l-textarea"  validate="{maxlength:2000}"></textarea>
			</td>
		</tr>
		 <tr id="ff">
         	<td class="l-t-td-left">计划完成时间</td>
         	<td class="l-t-td-right" colspan="5"><input name="jhwctime" id="jhwctime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" value="<%=nowTime %>"/></td>
        </tr>
        <tr id="gg">
            <td class="l-t-td-left" colspan="">申请人</td>
            <td class="l-t-td-right" colspan="2"><input name="applyName" id="applyName" type="text" ltype="text"  value="<sec:authentication property="principal.name"/>" ligerui="{disabled:true}" /></td>
            <td class="l-t-td-left" colspan="">申请日期</td>
        	<td class="l-t-td-right" colspan="2"><input id="applyTime" name="applyTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" value="<%=nowTime%>" validate="{required:true}"/></td>
        </tr>
        <div id="hh">
        
        <tr >
            <td class="l-t-td-left">服务部计划的方案编号</td>
         	<td class="l-t-td-right" colspan="5" id="fabh">
         	<input readonly="readonly" name="fabh" id="fabh" type="text" ltype="text" onclick="setfabh();" ligerui="{iconItems:[{icon:'add',click:setfabh}]}"/></td>
        </tr>
        </div>
         <tr>
            <td class="l-t-td-left"><br></br>业务服务部经办人<br></br><br/></td>
            <td class="l-t-td-right" colspan="2"><input readonly="readonly" id="ywfwbjbr" name="ywfwbjbr" type="text" ltype="text" /></td>
         	<td class="l-t-td-left">日期</td>
        	<td class="l-t-td-right" colspan="2"><input readonly="readonly" id="ywfwbjbrTime" name="ywfwbjbrTime" type="text" ltype='text'/></td>
        </tr> 
    </table>
    <div> 
   		注 1、设备种类：根据2014版特种设备目录分为锅炉、压力容器、压力管道、电梯、起重机械等；<br />
		  &nbsp&nbsp&nbsp&nbsp2、设备类别或品种：根据2014版特种设备目录分为第一类压力容器、超高压容器、球形储罐、医用氧舱、汽车罐车、长输管道、桥式起重机等；<br />
          &nbsp&nbsp&nbsp&nbsp3、检验类别：包括制造监督检验，安装、改造和重大修理监督检验，进口监督检验，定期检验，委托检验等。<br />

    </div>
     <div id="tabReason" style="display: none;">
    <table class="l-detail-table"  style="color:red">
					<tr>
						<td class="l-t-td-left" colspan="2" style="width: 135px;">作废原因</td>
						<td class="l-t-td-right" colspan="5"><textarea  name="cancelReason" id="cancelReason" rows="5" cols="25" class="l-textarea"   validate="{minlength:1,maxlength:1000}"></textarea></td>
					</tr>
					</table>
					</div>
</form>
</body>
</html>
