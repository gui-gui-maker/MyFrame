<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u"%>
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

	<head pageStatus="${param.pageStatus}">
	<title></title>
	<%@ include file="/k/kui-base-form.jsp" %>
	<script type="text/javascript" src="app/finance/js/jquery.autocomplete.js"></script>
	<link rel="Stylesheet" href="app/finance/css/jquery.autocomplete.css" />
    <script type="text/javascript">
    var deviceGrid;
    var pageStatus="${param.pageStatus}";
    $("#invoice1").show();
    
    $(function(){
    	initGrid();
    	if(pageStatus == 'add' || pageStatus == 'modify'){
    		$("#invoice1").hide();
    	}
    	
    	$("#form1").initForm({
    		toolbar: [{text: "保存", icon: "save", click: save},
    				  {text: "关闭", icon: "cancel", click: function(){
    					api.close();
    				}
    			}
    		],toolbarPosition: "bottom",
    			getSuccess : function(res) {
    			//alert(res.data.cwInvoiceF[0].id);
							deviceGrid.loadData({
								Rows : res.data.cwInvoiceF
							});
    	}
      });
    	$('#buyName').autocomplete("employee/basic/searchEmployee.do", {
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
            $("#buyNameId").val(row.id);
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
	            $("#buyNameId").val(p.id);
	            $("#buyName").val(p.name);
	        }
	    });
	}
    function initGrid() {
		var code_zzzt = <u:dict code="TJY2_CW_FP_TYPE"/> //获取开票类型码表
		var status_zzt = <u:dict code="TJY2_CW_FP_STATUS"/> //获取发票状态码表
		var money_type = <u:dict code="PAY_TYPE" /> //获取交易类型码表
			deviceGrid = $("#invoice").ligerGrid({
			columns:[
            	{ display: 'id', name: 'id', hide:true},
				{ display: '发票状态', name: 'status',id:'status', width:'7%',   //显示信息
					editor:{type : 'select' , data : status_zzt },	
					render:function(rowdata, rowindex, value){
						for(var i in status_zzt){	//循环码表
							if(status_zzt[i]["id"] == rowdata["status"]){	
								return status_zzt[i]['text'];
							}
						}
						return rowdata["status"];
					}
				},
				{ display: '发票编号', name: 'invoiceCode', width:'10%'},
				{ display: '开票单位', name: 'invoiceUnit', width:'30%'},
				{ display: '开票金额', name: 'invoiceMoney',width:'15s%'},
				{ display: '交易类型', name: 'moneyType',   width:'10%',
					editor:{type : 'select',data : money_type},
					render:function(rowdata,rowindex,value){
						for(var i in money_type){
							if(money_type[i]["id"] == rowdata["moneyType"]){
								return money_type[i]["text"];
							}
						}
						return rowdata["moneyType"];
					}
				},
				{ display: '开票时间', name: 'invoiceDate', width:'10%'},
				{ display: '发票类型', name: 'invoiceType', width:'10%',
					editor:{type : 'select' , data : code_zzzt },
					render:function(rowdata, rowindex, value){
						for(var i in code_zzzt){
							if(code_zzzt[i]["id"] == rowdata["invoiceType"]){
								return code_zzzt[i]['text'];
							}
						}
						return rowdata["invoiceType"];
					}
				},
				{ display: '检验部门', name: 'checkoutPart',width:'10%'},
				{ display: '受检单位', name: 'checkoutUnit',width:'10%'}
				
			],
			height:$(window).height()-230,
			rownumbers: true,
 			usePager: false
		});
    }
    function save(){
    	  var radio= $("input[name='invoiceType']:checked").val();
   		if(radio == null || radio =="" ){
   			 $.ligerDialog.warn('请选择发票类型!');
   			 return;
   		}
    	
    	 var eb = document.getElementById("invoiceEnd").value;
         var sb = document.getElementById("invoiceStart").value;
         var sbtmp = 0, ebtmp = 0;
         if (sb.length >= 4) { 
         	sbtmp = sb.substring(sb.length - 5); 
         	sbtmp = Number(sbtmp); 
         }
         
         if (eb.length >= 4) { 
         	ebtmp = eb.substring(eb.length - 5); 
         	ebtmp = Number(ebtmp); 
         }
         if(ebtmp - sbtmp > 0  ){
        	 document.getElementById("invoiceNum").value = ebtmp - sbtmp + 1;
         }else{
        	 $.ligerDialog.warn("发票号码有误,请重新输入!");
        	 return;
         }
         	
         var t = document.getElementById("invoiceNum").value;
         if (t > 10000) { $.ligerDialog.error("单次登记发票数超过10000张！");return;}
         if (t != "") {
             dtim = t / 100;
             /* ftim(); */
         }
    	
       
         
	     var obj=$("#form1").validate().form();
	     if(obj){
	    	  if($("#registrant").val() !="" && $("#registrant").val() != undefined){
	    		  if($("#registrantId").val() == "" || $("#registrantId").val() == undefined){
	        		 $.ligerDialog.warn("姓名id为空,请重新选择!");
	        		 return;
	        	 }
	         }
	    	   $("#form1").submit();
	     }else{
	         return;
	    }
	}
	

        function billcon() {
        	
            var eb = document.getElementById("invoiceEnd").value;
            var sb = document.getElementById("invoiceStart").value;
            var sbtmp = 0, ebtmp = 0;
            if (sb.length >= 4) { 
            	sbtmp = sb.substring(sb.length - 5); 
            	sbtmp = Number(sbtmp); 
            }
            
            if (eb.length >= 4) { 
            	ebtmp = eb.substring(eb.length - 5); 
            	ebtmp = Number(ebtmp); 
            }
            
            	document.getElementById("invoiceNum").value = ebtmp - sbtmp + 1;
           
 		
        }
	
		
        var dtim;
        function desctim(){
            var t = document.getElementById("invoiceNum").value;
            if (t > 10000) { $.ligerDialog.error("单次登记发票数超过10000张！");return;}
            if (t != "") {
                dtim = t / 100;
                /* ftim(); */
            }
        } 
		
		 /*  function ftim() {
            if (dtim >= 0) {
                dtim--;
                document.getElementById("timdiv").innerHTML = dtim + "秒";
                setTimeout("ftim()", 1000);
            }
        } */
		
		

	</script>
	</head>
<body>
	<form id="form1" action="cwInvoice/reg/saveFp.do" getAction="cwInvoice/reg/detail.do?id=${param.id}">
		    <input type="hidden" id="id" name="id" />
   		    <input type="hidden" name="status" />
   		    
   		  
   		    <h1 style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;text-align:center;">票&nbsp;据&nbsp;购&nbsp;买&nbsp;登&nbsp;记&nbsp;表</h1></br>
   		    <fieldset class="l-fieldset">
					<legend class="l-legend">
						<div>发票入库登记</div>
					</legend>
            <table  border="1" cellpadding="3" cellspacing="0"
					class="l-detail-table">
					<tr>
					<td class="l-t-td-left"> 发票类型</td>
					<td class="l-t-td-right" colspan="3">
			      	  <u:combo name="invoiceType" ltype="radioGroup" code="TJY2_CW_FP_TYPE"/>
			        </td>
					</tr>
                        <tr>
                            <td class="l-t-td-left"> 发票起始号</td>
                            <td class="l-t-td-right"><input name="invoiceStart" type="text" ltype="text" id="invoiceStart" validate="{required:true,maxlength:10}" /></td>
                            <td class="l-t-td-left"> 发票结束号</td>
                            <td class="l-t-td-right"><input name="invoiceEnd" type="text" ltype="text" id="invoiceEnd" validate="{required:true,maxlength:10}" onblur="billcon();desctim();" /></td>
                            <td class="l-t-td-left"> 发票数</td>
                            <td class="l-t-td-right"><input name="invoiceNum" type="text" ltype="text" id="invoiceNum" readOnly="true"  validate="{required:true}" /> </td>
                        </tr>
                        <tr>
                            <td class="l-t-td-left"> 购买人</td>
                           		 <input type="hidden" name="buyNameId" value="<%=userId %>"/>
                 				<td class="l-t-td-right"><input name="buyName" type="text" ltype="text" id="buyName"  validate="{required:true}" ligerui="{iconItems:[{icon:'user',click:choosePerson}]}" value="<sec:authentication property="principal.name"/>" /></td>
                            <td class="l-t-td-left"> 购入时间</td>
                            <%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
								String nowTime=""; 
								nowTime = dateFormat.format(new Date());%>
        					<td class="l-t-td-right">
        						<input name="buyDate" type="text" ltype="date" validate="{required:false}" 
        							ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="buyDate"  
        							onchange="setValues(this.value,'advance_time')" readonly="readonly" value="<%=nowTime%>" />
        					</td>
                            <td class="l-t-td-left">备注</td>
                            <td class="l-t-td-right"><input name="remark" type="text" ltype="text" id="remark" /></td>
                        </tr>
              </table>      
         </fieldset>  
         
          <fieldset class="l-fieldset" id="invoice1">
					<legend class="l-legend">
						<div>发票信息 </div>
					</legend>
					<div  id="invoice"></div>
		</fieldset>	
         
	</form>
</body>





</html>