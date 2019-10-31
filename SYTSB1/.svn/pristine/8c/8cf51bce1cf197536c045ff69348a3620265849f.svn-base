<%@page import="com.khnt.rbac.impl.bean.Org"%>
<%@page import="com.khnt.rbac.impl.bean.Employee"%>
<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head pageStatus="${param.pageStatus}">
<title></title>
 <%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
<script type="text/javascript" src="app/finance/js/jquery.autocomplete.js"></script>
<link rel="Stylesheet" href="app/finance/css/jquery.autocomplete.css" />
<link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
<%
	CurrentSessionUser cur_user = SecurityUtil.getSecurityUser();
	User user = (User)cur_user.getSysUser();
	Employee emp = (Employee)user.getEmployee();
	
	Org org = (Org) cur_user.getDepartment();	// 获取当前岗位所在部门信息
	// 不存在岗位信息时，获取当前登录用户所在部门信息
	if(org == null){
		org = (Org) user.getOrg();
	}
	
	String empId = emp.getId();
	String uId = user.getId();
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = dateFormat.format(new Date());
	
	String status = request.getParameter("status");
%>	 
<script type="text/javascript">
    var pageStatus = "<%=status %>"; 
    
	$(function () {    
	    if(pageStatus=="add"){
	        var orgId = <%=org.getId() %>;
	        //如果是新增，申请部门id设为当前登录者所在部门的id
	        $("#use_dep_id").val(orgId);
	
	    }
    	$("#form1").initForm({
    		 showToolbar: true,
             toolbarPosition: "bottom",
             toolbar: tbar,
    		 success: function (response) {
				if(response.success){
					top.$.notice("保存成功！",3);
					api.data.window.Qm.refreshGrid();
					api.close();
				}else{
					$.ligerDialog.error("操作失败！<br/>" + response.msg);
				}
			},
			getSuccess: function(resp){
				
            }
    	});
    	
    	
    	$("body").append('<object style="height:1px;" id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0><embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed></object>');

    	$('#use_dep_name').autocomplete("employee/basic/searchOrg.do", {
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
            $("#use_dep_id").val(row.orgId);

        });
    	
});

	function chooseOrg(){
		var dw =	$("#unit-txt").val();
		var parent_id="100000";		 
	    top.$.dialog({
	        width: 800,
	        height: 450,
	        lock: true,
	        parent: api,
	        title: "选择部门",
	        content: 'url:app/common/org_choose_new.jsp?par_id='+parent_id,
	        cancel: true,
	        ok: function(){
	            var p = this.iframe.contentWindow.getSelectedPerson();
	            if(!p){
	                top.$.notice("请选择部门！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
	                return false;
	            }
	            $("#use_dep_id").val(p.id);
	            $("#use_dep_name").val(p.name);
	            if("<%=org.getId() %>"!=p.id){
	               	top.$.notice("<span style='color:red;'>所选部门与当前登陆人员部门不符，请留意！</span>", 7, "k/kui/images/icons/dialog/32X32/hits.png");
	            }
	        }
	    });
	}   
    
    
    function print(){
       var LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));
                    var strBodyStyle = "<style>" + $("#pstyle").html() + "</style>";
                    LODOP.PRINT_INIT("打印申请单");
                    LODOP.ADD_PRINT_HTM('120px', '100px', "770px", "520px", strBodyStyle + $("#form1").html());
                    LODOP.SET_PRINT_PAGESIZE (2, 0, 0,"B5(ISO)");
                    LODOP.PRINT();
    }
      function save(){
    	  var amoney = $("#money").val();
    	  if(amoney ==null || amoney =="" ){
    		 	 $.ligerDialog.warn("金额不能为空，请重新填写！");
	               return;   
    	  }else{
    		  var obj=$("#form1").validate().form();
      	    if(obj){
      	    	  if($("#dmName").val() !="" && $("#dmName").val() != undefined){
      	    		  if($("#dmId").val() == "" || $("#dmId").val() == undefined){
       	        		 $.ligerDialog.warn("姓名id为空,请重新选择!");
       	        		 return;
       	        	 }
       	         } 
      	         if($("#use_dep_name").val() != "" && $("#use_dep_name").val() != undefined){
      	           if($("#departmentId").val() == "" || $("#departmentId").val() == undefined){
      	               $.ligerDialog.warn("部门id为空，请重新选择部门！");
      	               return;
      	           }
      	         }
      	       var formData = $("#form1").getValues();
               $("body").mask("正在保存......");
               $.ajax({
                  url: "cwRefundmentAction/savetk.do",
                  type: "POST",
                  datatype: "json",
                  contentType: "application/json; charset=utf-8",
                  data: $.ligerui.toJSON(formData),
                  success: function (data, stats) {
                      $("body").unmask();
                      if (data["success"]) {
                          top.$.dialog.notice({content:'保存成功！'});
                          api.data.window.Qm.refreshGrid();
                          if(isCheck!="1"){
      					 	 api.close();
                          }
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
    	}
     
      
   function close(){
	   	if(api.data.window)
	   		api.data.window.refreshGrid();
	   	api.close();
   }

	
	function selectUser(){
		var org_id = $("#use_dep_id").val();
		var org_name = $("#use_dep_name").val();
		if(org_id == "" || org_name == "" ){
			$.ligerDialog.alert("请先选择用车部门！");
			return;
		}
		top.$.dialog({
			width : 200,
			height : 420,
			lock : true,
			title : "选择联系人",
			content : 'url:app/car/choose_user_list.jsp?org_id='+org_id,
			data : {
				"window" : window
			}
		});
	}
    </script>
	<style>
	div .input {
		border-bottom: 0px;
	}
	
	.l-detail-table1 td, .l-detail-table1, .l-table td1, .l-table1 {
		border-collapse: collapse;
		border: 1px solid #d2e0f1;
	}
	
	h2 {
		font-family: 宋体;
		font-size: 6mm;
		text-align: center;
		margin: 10px 0 0 0;
	}
	</style>
	<style type="text/css" media="print" id="pstyle">
	* {
		font-family: "宋体";
		font-size: 15px;
		letter-spacing: normal;
	}
	
	table {
		margin: 0 auto;
	}
	
	table td {
		height: 36px;
	}
	
	.l-detail-table td, .l-detail-table {
		border-collapse: collapse;
		border: 1px solid black;
	}
	
	.l-detail-table {
		padding: 5px;
		border: 0px solid #CFE3F8;
		border-top: 0px;
		border-left: 0px;
		word-break: break-all;
		table-layout: fixed;
	}
	
	.check {
		width: 770px;
	}
	
	.l-t-td-left {
		text-align: center;
	}
	
	.l-t-td-right {
		padding-left: 5px;
	}
	
	.fybx2 {
		height: 40px;
		line-height: 20px;
		overflow: hidden;
	}
	
	h2 {
		font-family: 宋体;
		font-size: 7mm;
		text-align: center;
		margin: 10px 0 0 0;
	}
	
	#wrap {
		display: flex;
		margin-left: 20px;
		font-size: 18px;
	}
	</style>
</head>
<body>
	<form id="form1" action="car/apply/saveBasic.do" >
		    <input type="hidden" id="id" name="id" value="" >
   		    <input type="hidden" name="data_status" id="data_status" >
   		    <h2>四川省特检院公务用车申请单 </h2>
   		   <table class="check">
            	 <tr>
                    <td width="80px">填报日期：</td>
                    <td class="l-t-td-right" width="160px">
                    	<input id="apply_date" name="apply_date" type="text" ltype="text" validate="{required:true}"  value="<%=nowTime%>" readonly="readonly"/>
                    </td>
                    <td width="100px" height="50px">申请用车时间：</td>
                     <td class="l-t-td-right" width="320px">
                     	<div id="wrap">
	                     	<input id="use_start_date" name="use_start_date" type="text" ltype="date" validate="{required:true}" ligerui="{initValue:'',format:'yyyy-MM-dd',labelWidth:100}" />到<input id="use_end_date" name="use_end_date" type="text" ltype="date" validate="{required:true}" ligerui="{initValue:'',format:'yyyy-MM-dd',labelWidth:100}" />
        				</div>
        			</td>
                    <td width="60px" align="center">编号:</td>
                    <td style="width: 150px;" class="l-t-td-right"><input id="apply_sn" name="apply_sn" ltype='text' type="text" readonly="readonly" value="保存后由系统自动生成"/></td>
                </tr>
            </table>
            
    <table  id="gwycsq" class="l-detail-table has-head-image" width="720px">
        <tr>
            <th style="border:0px;width:40px"></th>
            <th style="border:0px;width:40px"></th>
            <th style="border:0px;width:80px"></th>
            <th style="border:0px;width:40px"></th>
            <th style="border:0px;width:40px"></th>
            <th style="border:0px;width:40px"></th>
		</tr>
        <tr>
        	<td class="l-t-td-left" width="160px">用车部门</td>
        	<td class="l-t-td-right" height="50px">
        		<input type="hidden" id="use_dep_id" name="use_dep_id" value="<%=org.getId() %>" />
				<input name="use_dep_name" id="use_dep_name" type="text" ltype="text" validate="{maxlength:20,required:true}" onclick="chooseOrg()" ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}"  value="<%=org.getOrgName() %>" /> <!--  ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}"--> 
        	</td>
        	<td class="l-t-td-left" >联系人</td>
        	<td class="l-t-td-right" >
        		<input type="hidden" name="use_emp_id" id="use_emp_id" />
						<input type="text"
							name="use_emp_name" id="use_emp_name" ltype="text"
								validate="{required:true}" ligerui="{value:'',iconItems:[{icon:'add',click:function(){selectUser()}}]}" onclick="selectUser()"/><!-- onclick="selectUser()" -->
			</td>
        	<td class="l-t-td-left" >联系电话</td>
        	<td class="l-t-td-right" >
        		<input id="use_emp_phone" name="use_emp_phone" type="text" ltype="text" validate="{maxlength:11,required:true}" value="<%=emp.getMobileTel() %>" />
        	</td>
        </tr>
        <tr>
        	<td class="l-t-td-left" rowspan="2">用车具体任务</td>
        	<td class="l-t-td-right" height="50px" rowspan="2">
        		<input name="apply_reason" type="text" ltype="text" validate="{required:true,maxlength:250}" />
        	</td>
        	<td class="l-t-td-left" >出车时间</td>
        	<td class="l-t-td-right" >
        		<input id="out_date" name="out_date" type="text" ltype="date" validate="{required:true}" ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
        	</td>
        	<td class="l-t-td-left" rowspan="2">用车部门负责人签字</td>
        	<td class="l-t-td-right" height="50px" rowspan="2"></td>
        </tr>
        <tr>
        	<td class="l-t-td-left" >返回时间</td>
        	<td class="l-t-td-right" >
        		<input id="back_date" name="back_date" type="text" ltype="date" validate="{required:true}" ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
        	</td>
        </tr>
        <tr>
            <td class="l-t-td-left" colspan="2">行驶路线</td>
            <td colspan="14" class="l-t-td-right" height="50px">
            	<input id="drive_route" name="drive_route" type="text" ltype="text" validate="{required:true,maxlength:250}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left" >分管院领导意见</td>
        	<td class="l-t-td-right" height="50px" colspan="3"></td>
        	<td class="l-t-td-left" >办公室负责人意见</td>
        	<td class="l-t-td-right" height="50px"></td>
        </tr>
        <tr>
        	<td class="l-t-td-left" rowspan="2">车队负责人签字</td>
        	<td class="l-t-td-right" height="50px" rowspan="2"></td>
        	<td class="l-t-td-left" >车牌号</td>
        	<td class="l-t-td-right" ></td>
        	<td class="l-t-td-left" >公里起数</td>
        	<td class="l-t-td-right" ></td>
        </tr>
        <tr>
        	<td class="l-t-td-left" >驾驶员</td>
        	<td class="l-t-td-right" ></td>
        	<td class="l-t-td-left" >公里止数</td>
        	<td class="l-t-td-right" ></td>
        </tr>
        <tr>
            <td class="l-t-td-left" colspan="6">备注：1、检验部门检验用车需提前2天提出用车申请；</td>
        </tr>
        <tr>
            <td class="l-t-td-left" colspan="6">2、用车3天以上由分管院领导签字确认；</td>
        </tr>
        <tr>
            <td class="l-t-td-left" colspan="6">3、用车3天以内由办公室负责人签字确认；</td>
        </tr>
    </table>
	</form>
</body>
</html>