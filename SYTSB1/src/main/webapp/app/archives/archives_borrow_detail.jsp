<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	User uu = (User) user.getSysUser();
	com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee) uu.getEmployee();
	e.getId();
	String userId = e.getId();
	String uId = SecurityUtil.getSecurityUser().getId();
%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<%@page import="com.khnt.security.util.SecurityUtil"%>

<title></title>
<%@include file="/k/kui-base-form.jsp"%>
<link type="text/css" rel="stylesheet"
	href="app/qualitymanage/css/form_detail.css" />
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
<script type="text/javascript" src="app/archives/js/borrow.js"></script>
<script type="text/javascript">
    var tbar="";
    var isChecked="${param.isChecked}";
    var serviceId = "${requestScope.serviceId}";//提交数据的id
	var activityId = "${requestScope.activityId}";//流程id
	var processId = "${requestScope.processId}";//退回id
	var pageStatus="${param.pageStatus}";
	var isSg="${param.isSg}";
	var areaFlag;//改变状态
     <bpm:ifPer function="TJY2_CW_JK_cwsh" activityId = "${requestScope.activityId}">areaFlag="1";</bpm:ifPer>
  	 <bpm:ifPer function="TJY2_CW_JK_bmld" activityId = "${requestScope.activityId}">areaFlag="2";</bpm:ifPer>
  	 <bpm:ifPer function="TJY2_CW_JK_fgyld" activityId = "${requestScope.activityId}">areaFlag="3";</bpm:ifPer>
  	 <bpm:ifPer function="TJY2_CW_JK_dwfzr" activityId = "${requestScope.activityId}">areaFlag="4";</bpm:ifPer>
  	 
    $(function () {
    	createReportBorrowRecordGrid();
    	if(isChecked!="" && typeof(isChecked)!="undefined"){
    		$.ajax({
 				url: "archives/borrow/getDetail.do?id=${requestScope.serviceId}",
 	            type: "POST",
 	            success: function (resp) {
 	            	if(resp.success){
						reportBorrowGrid.loadData({
							Rows : resp.archivesBorrowSubs,
							rowAttrRender :function(rowData, rowid) {}
						});
					}
 	            }
 			});
        	$("#jysq").transform("detail",function(){});
   	    	$("#jysq").setValues("archives/borrow/detail.do?id=${requestScope.serviceId}");
   	    	$("#jysq1").setValues("archives/borrow/detail.do?id=${requestScope.serviceId}");
   	    	tbar=[{ text: '审核', id: 'submit', icon: 'submit', click: sh},
                   { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
        } else {
        	if("${param.isSg}"=="0"){
        		tbar=[{ text:'选择复制', id:'copy',icon:'copy', click:choosefile},
                	{ text: '保存', id: 'up', icon: 'save', click: directChange},
                    { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
        	}else if("${param.isSg}"=="1"){
        		tbar=[{ text: '保存', id: 'up', icon: 'save', click: directChange},
                    { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
        	}
        }
    	 if ("${param.pageStatus}"=="detail")
    	        tbar=[{ text: '打印报告编号', id: 'printReportSn', icon:'print', click:printReportSn},
    	        	{ text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
    	$("#form1").initForm({
            showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar,
            getSuccess:function(resp){
	        	if(resp.success){
					reportBorrowGrid.loadData({
						Rows : resp.archivesBorrowSubs
					});
					$("#form1").setValues(resp.data);
				}
	        }
    	});
    	$("body").append('<object style="height:1px;" id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0><embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed></object>');
    	$('#proposer').autocomplete("employee/basic/searchEmployee.do", {
            max: 12,    //列表里的条目数
            minChars: 1,    //自动完成激活之前填入的最小字符
            width: 200,     //提示的宽度，溢出隐藏
            scrollHeight: 300,   //提示的高度，溢出显示滚动条
            scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
            matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
            autoFill: false,    //自动填充
            formatItem: function(row, i, max) {
                return row.name + '   ' + row.mobileTel;
            },
            formatMatch: function(row, i, max) {
                return row.name + row.mobileTel;
            },
            formatResult: function(row) {
                return row.name;
            }
        }).result(function(event, row, formatted) {
//            alert(row.mobileTel);
			$("#proposerId").val(row.id);
        }); 
    	$('#applyUnit').autocomplete("employee/basic/searchOrg.do", {
            max: 12,    //列表里的条目数
            minChars: 1,    //自动完成激活之前填入的最小字符
            width: 200,     //提示的宽度，溢出隐藏
            scrollHeight: 300,   //提示的高度，溢出显示滚动条
            scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
            matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
            autoFill: false,    //自动填充
            formatItem: function(row, i, max) {
            //alert(row);
                return row.orgName;
            },
            formatMatch: function(row, i, max) {
                return row.orgName;
            },
            formatResult: function(row) {
                return row.orgName;
            }
        }).result(function(event, row, formatted) {
//            alert(row.orgId);
				$("#applyUnitId").val(row.orgId);
        });
    });
    function sh() {
		top.$.dialog({
			width: 600,
			height: 350,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "审核",
			content: 'url:app/archives/archives_borrow_yijian.jsp?pageStatus=add&serviceId='+serviceId+'&activityId='+activityId+'&processId='+processId
		});
	}
    function directChange(){ 
    	var obj=$("#form1").validate().form();
    	var a=$("#reportNumber").val();
    	if(obj){
        	if($("#applyUnit").val() != "" && $("#applyUnit").val() != undefined){
 	           if($("#applyUnitId").val() == "" || $("#applyUnitId").val() == undefined){
 	               $.ligerDialog.warn("部门id为空，请重新选择部门！");
 	               return;
 	           }
 	         }
 		 if($("#proposer").val() != "" && $("#proposer").val() != undefined){
 	           if($("#proposerId").val() == "" || $("#proposerId").val() == undefined){
 	               $.ligerDialog.warn("人员id为空，请重新选择人员！");
 	               return;
 	           }
 	         }
 		var formData = $("#form1").getValues();
		var data = {};
		data = formData;
		data["archivesBorrowSubs"] = reportBorrowGrid.getData();
		if(data["archivesBorrowSubs"]==""||data["archivesBorrowSubs"]=="undefined"){
			$.ligerDialog.error('提示：报告信息不能为空！');
			return
		}
        $("body").mask("正在保存......");
       $.ajax({
           url: "archives/borrow/save.do?isSg="+isSg,
           type: "POST",
           datatype: "json",
           contentType: "application/json; charset=utf-8",
           data: $.ligerui.toJSON(data),
           success: function (data, stats) {
               $("body").unmask();//alert(99);
               if (data["success"]) {
               top.$.notice(data.msg,3);	
                   //top.$.dialog.notice({content:'保存成功！'});
                   //api.data.window.Qm.refreshGrid();//刷新
                   //api.close();
              	if("${param.pageStatus}"=="modify"){
                 	 api.data.window.Qm.refreshGrid();
                     api.close();
                 }else{
             	 	 api.data.window.api.data.window.Qm.refreshGrid();
                     api.data.window.api.close();
                     api.close();
                 }
               }else{
                   $.ligerDialog.error('提示：' + data.msg);
                   //api.data.window.Qm.refreshGrid();//刷新
                   if("${param.pageStatus}"=="modify"){
                   	 api.data.window.Qm.refreshGrid();
                   }else{
               	 	 api.data.window.api.data.window.Qm.refreshGrid();
                   }
               }
           },
           error: function (data,stats) {
               $("body").unmask();
               $.ligerDialog.error('提示：' + data.msg);
           }
       });
        	//$("#form1").submit();
            //Qm.refreshGrid();//刷新
 		 }else{
                 return;
 		 }
//         var d1=$("#applyTime").val();
//         var d2=$("#restoreTime").val();
//         top.$.ajax({
//             url: "archives/borrow/bgbh.do?reportNumber="+a,
//             type: "GET",
//             dataType:'json',
//             success:function (data) {
//                if(data.success){
//          		 if(obj){
//                 	if($("#applyUnit").val() != "" && $("#applyUnit").val() != undefined){
//          	           if($("#applyUnitId").val() == "" || $("#applyUnitId").val() == undefined){
//          	               $.ligerDialog.warn("部门id为空，请重新选择部门！");
//          	               return;
//          	           }
//          	         }
//          		 if($("#proposer").val() != "" && $("#proposer").val() != undefined){
//          	           if($("#proposerId").val() == "" || $("#proposerId").val() == undefined){
//          	               $.ligerDialog.warn("人员id为空，请重新选择人员！");
//          	               return;
//          	           }
//          	         }
//                 	$("#form1").submit();
//                     Qm.refreshGrid();//刷新
//          		 }else{
//    	                  return;
//          		 }
//                 }else{
//                     //$.ligerDialog.alert(data.msg);
//                 	$.ligerDialog.error(data.msg);  
//                 }
//              },
//              error:function () {
//             	 $.ligerDialog.error("保存失败！");
//              }
// 		});
	 }
    function chooseOrg(){
        top.$.dialog({
            width: 800,
            height: 450,
            lock: true,
            parent: api,
            title: "选择部门",
            content: 'url:app/common/org_choose.jsp',
            cancel: true,
            ok: function(){
                var p = this.iframe.contentWindow.getSelectedPerson();
                if(!p){
                    top.$.notice("请选择部门！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                    return false;
                }
                $("#applyUnitId").val(p.id);
                $("#applyUnit").val(p.name);
            }
        });
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
                $("#proposerId").val(p.id);
                $("#proposer").val(p.name);
            }
        });
    }
    function choosefile(){
    	top.$.dialog({
			parent: api,
			width : 860, 
			height : 500, 
			lock : true, 
			title:"选择报告编号",
			content: 'url:app/archives/archives_file_xuanze.jsp',
			data : {"parentWindow" : window}
		});

    }
    function callBackReport(ADVANCE_TIME,id, report_sn,check_op_name,org_name,COM_NAME,check_unit_id,device_registration_code){
		var devRow = reportBorrowGrid.rows;
		var isexist=false;
		var resportIsExist="";
		var ids  = id.split(",");
		var ADVANCE_TIMES  = ADVANCE_TIME.split(",");
		var temp = report_sn.split(",");
		var COM_NAMES  = COM_NAME.split(",");
		var check_op_names  = check_op_name.split(",");
		var check_unit_ids = check_unit_id.split(",");
		var org_names = org_name.split(",");
    	var device_registration_codes  = device_registration_code.split(",");
    	for (var i = 0; i < ids.length; i++) {
    		for(var j in devRow){
    			if(ids[i] == devRow[j].fkReportId){
       	   			isexist = true;
       	   			resportIsExist = resportIsExist+temp[i]+",";
       	   			break;
       	   		}else{
       	   			isexist = false;
       	   		}
       	   	}
	   	   	 if(!isexist){
				var tt = {  
						fkReportId : ids[i], 
						advanceTime: ADVANCE_TIMES[i], 
						reportSn:temp[i],
						reportCount : "1",
						comName : COM_NAMES[i], 
						checkOpName: check_op_names[i], 
						checkUnitId:check_unit_ids[i],
						orgName : org_names[i],
						deviceRegistrationCode : device_registration_codes[i]
				};
				reportBorrowGrid.addRows(tt);
			}
   	 	}
    	if(resportIsExist != ""){
        	$.ligerDialog.warn("所选报告："+"<span style='color:red;'>"+resportIsExist.substring(0,resportIsExist.length-1)+"</span>"+" 已存在！");
      	}
	}
    /* function callBackReport(id, report_sn){
    	var a=0;
    	var temp = report_sn.split(",");
    	for (var i = 0; i < temp.length; i++) {
    		a++;
    	}
    	var b=$("#shares").val();
    	if(b==""){
    		$("#shares").val(a);
    	}else{
    		var bb=parseInt(a)+parseInt(b);
	    	$("#shares").val(bb);		
    	}
    	var c=$("#reportNumber").val();
    	var cd=$("#reportNumberId").val();
    	if(c==""){
    		$("#reportNumber").val(report_sn);	
    		$("#reportNumberId").val(id);
    	}else{
    		$("#reportNumber").val(c+","+report_sn);
    		$("#reportNumberId").val(cd+","+id);
    	}
    	//$("#reportNumber").val(report_sn);		
    	//$("#reportNumberId").val(id);
    	//alert($("#reportNumber").val());
	} */	
   function close(){
   	if(api.data.window.submitAction)
   		api.data.window.submitAction();
   		api.close();
   }
    //检验档案报告书编号打印
    function printReportSn(){
    	$.ajax({
			url: "archives/borrow/detail.do?id=${param.id}",
            type: "POST",
            success: function (resp) {
            	if(resp.success){
            		var identifier = resp.data.identifier;//编号
            		var reportNumber = resp.data.reportNumber;//报告编号
            		var reportNumberArr = reportNumber.split(",");
            		reportNumberArr=reportNumberArr.sort();
            		var result = [];
            		for(var i=0,len=reportNumberArr.length;i<len;i+=4){
            		   result.push(reportNumberArr.slice(i,i+4));
            		}
            		
                	var LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));
                	LODOP.PRINT_INIT("检验档案报告书编号打印");
            		LODOP.SET_PRINT_PAGESIZE(1,0,0,"A4");
            		LODOP.ADD_PRINT_TEXT(50,0,"100%",39,"检验档案（"+identifier+"）报告书编号");
            		LODOP.SET_PRINT_STYLEA(0,"FontSize",18);
            		LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
            		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
            		
            		for(var i = 0; i < result.length; i++){
            			if(i+1!=result.length){
            				LODOP.ADD_PRINT_TEXT(120+40*i,45,"100%",30,result[i]+",");
                    		LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
                    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
                    		LODOP.SET_PRINT_STYLEA(0,"LineSpacing",5);
            			}else{
            				LODOP.ADD_PRINT_TEXT(120+40*i,45,"100%",30,result[i]+"。");
                    		LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
                    		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
                    		LODOP.SET_PRINT_STYLEA(0,"LineSpacing",5);
            			}
            		}
            		//LODOP.PRINT_DESIGN();
                    //LODOP.PREVIEW();
                    LODOP.PRINT();
				}else{
					
				}
            }
		});
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
<body>


	<form id="form1" action="archives/borrow/save.do"
		getAction="archives/borrow/getDetail.do?id=${param.id}">
		<input type="hidden" id="id" name="id" /> <input type="hidden"
			id="status" name="status" /> <input type="hidden"
			id="reportNumberId" name="reportNumberId" /> <input type="hidden"
			id="proposerId" name="proposerId" value="<%=userId%>" /> <input
			type="hidden" id="applyUnitId" name="applyUnitId"
			value="<%=user.getDepartment().getId()%>" /> <input type="hidden"
			id="fkMsg" name="fkMsg" /> <input type="hidden" id="registrant"
			name="registrant" /> <input type="hidden" id="registrantTime"
			name="registrantTime" /> <input type="hidden" id="jfbgrId"
			name="jfbgrId" /> <input type="hidden" id="fhbgrId" name="fhbgrId" />
		<input type="hidden" id="reportReceiptor" name="reportReceiptor" /> <input
			type="hidden" id="reportReceiptorId" name="reportReceiptorId" /> <input
			type="hidden" id="reportReceiptDate" name="reportReceiptDate" /> <input
			type="hidden" id="isSg" name="isSg" value="${param.isSg}" />
		<h1 id="jysq2" align="center"
			style="padding: 5mm 0 2mm 0; font-family: 微软雅黑; font-size: 6mm;">检验档案（借阅、查阅、复制）审批表
		</h1>
		</br>
		<table id="jysq1" class="check">
			<tr>
				<td width="30px">&nbsp;</td>
				<td width="20" align="center">共：</td>
				<td width="30" class="l-t-td-right" align="center"><input
					ltype='text' readOnly="true" id="shares" name=shares type="text" /></td>
				<td width="20px" align="center">份</td>
				<td width="520px">&nbsp;</td>
				<td>编号：</td>
				<td width="200px" class="l-t-td-right"><input ltype='text'
					readOnly="true" name="identifier" type="text" /></td>
			</tr>
		</table>
		<table id="jysq" border="1" cellpadding="3" class="l-detail-table">
			<tr>
				<th style="border: 0px; width: 100px"></th>
				<th style="border: 0px; width: 100px"></th>
				<th style="border: 0px; width: 100px"></th>
				<th style="border: 0px; width: 100px"></th>
				<th style="border: 0px; width: 100px"></th>
				<th style="border: 0px; width: 100px"></th>
			</tr>
			<tr>
				<td class="l-t-td-left">部门</td>
				<td class="l-t-td-right"><input
					value="<%=user.getDepartment().getOrgName()%>"
					validate="{maxlength:50}" ltype="text" name="applyUnit"
					id="applyUnit" type="text" ligerUi="{disabled:true}" /></td>
				<!--        readonly="readonly" 	 ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}" -->
				<td class="l-t-td-left">申请类型</td>
				<td class="l-t-td-right"><input type="radio" name="applyType"
					id="applyType" ltype="radioGroup"
					ligerui="{value:'1',data: [ { text:'借阅', id:'1' }, { text:'查阅', id:'2' },
					{ text:'复制', id:'3' } ] }" />
				</td>
				<td class="l-t-td-left">天数</td>
				<td class="l-t-td-right"><input type="radio" id="fatalism"
					name="fatalism" ltype="radioGroup"
					ligerui="{value:'5',data:[{text:'5天',id:'5'},{text:'15天',id:'15'},{text:'30天',id:'30'}]}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">申请人</td>
				<td class="l-t-td-right"><input name="proposer" id="proposer"
					type="text" ltype="text" ligerUi="{disabled:true}"
					value="<sec:authentication property="principal.name"/>"
					ligerui="{iconItems:[{icon:'user',click:choosePerson}]}" /></td>
				<td class="l-t-td-left">申请日期</td>
				<%
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String nowTime = "";
					nowTime = dateFormat.format(new Date());
				%>
				<td class="l-t-td-right"><input id="applyTime" name="applyTime"
					type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}"
					value="<%=nowTime%>" validate="{required:true}" /></td>
				<td class="l-t-td-left">返还时间</td>
				<td class="l-t-td-right"><input readonly="readonly"
					id="restoreTime" name="restoreTime" validate="{required:}"
					ligerUi="{disabled:true,format:'yyyy-MM-dd'}" type="text"
					ltype="date" /></td>
			</tr>
			<tr id="jysq3">
				<td colspan="6">
					<div id="reportBorrowRecords"></div>
				</td>
			</tr>
			<%-- <tr>	
            <td class="l-t-td-left">报告号</td>
            <td class="l-t-td-right1" colspan="5">
<!--                 <input type="hidden" id="reportNumber" name="reportNumber"/> -->
	             <c:if test="${param.pageStatus=='modify' or param.pageStatus=='add'}"><span class="l-button label" title="">
<!-- 	               <span  class="l-a l-icon-add"  onclick="choosefile();">&nbsp;</span> -->
	              </c:if>
            	<textarea id="reportNumber" name="reportNumber" rows="5" cols="25" class="l-textarea" validate="{required:true}" readonly="readonly"
            	ligerui="{iconItems:[{icon:'add',click:function(){choosefile()}}]}" title="提示：报告编号可以手动输入，分割符号要用英文逗号！！！"></textarea>
<!--             	onclick="choosefile();" -->
            </td>
        </tr> --%>
			<tr>
				<td class="l-t-td-left">申请理由</td>
				<td class="l-t-td-right1" colspan="5"><textarea
						name="applyReason" id="applyReason" rows="5" cols="25"
						class="l-textarea" validate="{maxlength:1000,required:true}"></textarea></td>
			</tr>

			<tr>
				<td class="l-t-td-left">部门负责人意见</td>
				<td class="l-t-td-right1" colspan="5"><textarea
						readonly="readonly" name="bmfzryj" id="bmfzryj" rows="5" cols="25"
						class="l-textarea" validate="{maxlength:2000}"></textarea></td>
			</tr>
			<tr>
				<td class="l-t-td-left">部门负责人</td>
				<td class="l-t-td-right" colspan="2"><input readonly="readonly"
					name="bmfzr" id="bmfzr" type="text" ltype="text"
					ligerUi="{disabled:true}" /></td>
				<td class="l-t-td-left">审核时间</td>
				<td class="l-t-td-right" colspan="2"><input readonly="readonly"
					name="bmfzrTime" id="bmfzrTime"
					ligerUi="{disabled:true,format:'yyyy-MM-dd'}" type="text"
					ltype='date' /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">业务服务部意见</td>
				<td class="l-t-td-right1" colspan="5"><textarea
						readonly="readonly" name="yffwbyj" id="yffwbyj" rows="5" cols="25"
						class="l-textarea" validate="{maxlength:2000}"></textarea></td>
			</tr>
			<tr>
				<td class="l-t-td-left">业务服务部负责人</td>
				<td class="l-t-td-right" colspan="2"><input readonly="readonly"
					name="yffwb" id="yffwb" type="text" ltype="text"
					ligerUi="{disabled:true}" /></td>
				<td class="l-t-td-left">审核时间</td>
				<td class="l-t-td-right" colspan="2"><input readonly="readonly"
					name="yffwbTime" id="yffwbTime"
					ligerUi="{disabled:true,format:'yyyy-MM-dd'}" type="text"
					ltype='date' /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">业务服务部经办人/<br />交付报告时间
				</td>
				<td class="l-t-td-right"><input readonly="readonly"
					name="jfbgr" id="jfbgr" type="text" ltype='text'
					ligerUi="{disabled:true}" /></td>
				<td class="l-t-td-right"><input readonly="readonly"
					name="jfbgsj" id="jfbgsj"
					ligerUi="{disabled:true,format:'yyyy-MM-dd'}" type="text"
					ltype='date' /></td>
				<td class="l-t-td-left">业务服务部经办人/<br />返还报告时间
				</td>
				<td class="l-t-td-right"><input readonly="readonly"
					name="fhbgr" id="fhbgr" type="text" ltype='text'
					ligerUi="{disabled:true}" /></td>
				<td class="l-t-td-right"><input readonly="readonly"
					name="fhbgsj" id="fhbgsj"
					ligerUi="{disabled:true,format:'yyyy-MM-dd'}" type="text"
					ltype='date' /></td>
			</tr>
		</table>


	</form>
</body>
</html>
