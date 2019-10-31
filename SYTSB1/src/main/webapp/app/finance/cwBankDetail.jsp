<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.khnt.security.CurrentSessionUser"%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    CurrentSessionUser user=(CurrentSessionUser)request.getSession().getAttribute("currentSessionUser");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
    
    
    function SunAmount() {
  	  

    	   
        var money1 = document.getElementById("money1").value;//转入金额
        var money2 = document.getElementById("usedMoney").value;//已收金额
       
        
        
        var sumamount = document.getElementById("restMoney");
        var sumAmount = parseFloat(money1 == "" ? 0 : money1) - parseFloat(money2 == "" ? 0 : money2);
        sumamount.value = sumAmount == 0 ? "" : sumAmount.toFixed(2); 
        if(sumAmount==0||sumAmount==""||sumAmount==null){
        	
        	$("#restMoney").val("0");
        
        }
      
      
        
    }
    
    
    var columns=[];
    var backColumns=[];
    var pageStatus="${param.pageStatus}";
    var payDetailGrid;
    var payBackDetailGrid;
   
        $(function () {
        $("#backlog").hide();
        $("#usedMoney").click(function(){
                
                $("#usedMoney").rules("add", {max:Number($("#money1").val())});
                var form = $("#form1").validate();
                form.showErrors();
            });
        $("#restMoney").click(function(){
                
                $("#restMoney").rules("add", {max:Number($("#money1").val())});
                var form = $("#form1").validate();
                form.showErrors();
            });
        defineColumns();
        initGrid();
        
//          如果不设置额外参数，不用调用此方法，初始化时会自动调用
            $("#form1").initForm({
                success : function(responseText) {//处理成功
                    if (responseText.success) {
                        top.$.notice("保存成功！",3);
                        api.data.window.Qm.refreshGrid();
                        api.close();
                    } else {
                    	console.log(responseText);
                        $.ligerDialog.error(responseText.msg);
                    }
                },
                getSuccess:function(responseText){
                $.getJSON("cw/bank/queryPayDetails.do?id=${param.id}","",function(data){
                    if(data.success){
                        //列表添加收费数据
                        payDetailGrid.loadData({Rows:data.Rows});
                        //如果有退款，则显示退款明细
                        if(data.BackRows.length>0){
                            $("#backlog").show();
                            defineBackColumns();
                            initBackGrid();
                            payBackDetailGrid.loadData({Rows:data.BackRows});
                        }
                    }else{
                        $.ligerDialog.error("获取收费明细失败！");
                    }
                });
                }
            });
            

        });
function initGrid() {
        payDetailGrid = $("#payDetail").ligerGrid({
        columns: columns,
        enabledEdit: pageStatus!="detail",
        clickToEdit: true,
        rownumbers: true,
        width:"99%",
        frozenRownumbers: false,
        usePager: false,
        page: 1,
        pageSize: 20,
        data: {Rows: []}
    });
}
function initBackGrid() {
    payBackDetailGrid = $("#backDetail").ligerGrid({
        columns: backColumns,
        enabledEdit: pageStatus!="detail",
        clickToEdit: true,
        rownumbers: true,
        width:"99%",
        frozenRownumbers: false,
        usePager: false,
        page: 1,
        pageSize: 20,
        data: {Rows: []}
    });
    }
    function defineColumns(){
        //var columns=[];
        /*if(pageStatus!="detail"){
            columns.push({ display: "<a class='l-a iconfont l-icon-add' href='javascript:void(0);' onclick='javascript:addDevice()' title='增加'><span>增加</span></a>", isSort: false, width: '30',height:'5%', render: function (rowdata, index, value ) {
                var h = "";
                if (!rowdata._editing) {
                    h += "<a class='l-a l-icon-del' href='javascript:delDevice(deviceGrid,"+index+")' title='删除'><span>删除</span></a> ";
                }
                
                return h;
            }
            });
            
        }*/
        
        columns.push({display: 'id', name: 'id', hide:true},
                     //{display: '操作类型',width: '20%',name: 'doType',type: 'text',required:false},
                     {display: '收费时间',width: '20%',name: 'payTime',type: 'text',required:false},
                     {display: '收费人姓名',width: '12%',name: 'payName',type:'text',required:false},
                     {display: '金额',width: '12%',name: 'doContent',type:'text',required:false}
        );
    }
    function defineBackColumns(){
        backColumns.push({display: 'id', name: 'id', hide:true},
                     {display: '操作时间',width: '15%',name: 'operatorTime',type: 'text',required:false},
                     {display: '操作人',width: '15%',name: 'operatorName',type:'text',required:false},
                     {display: '退款金额',width: '15%',name: 'fefundMoney',type:'text',required:false},
                     {display: '单位名称',width: '20%',name:'unitName',type:'text',required:false},
                     {display: '退款原因',width: '25%',name: 'fefundReason',type:'text',required:false}
        );
    }
    </script>
</head>
<body>
   <form id="form1" action="cw/bank/saveBank.do?pageStatus=${param.pageStatus}" method="post" getAction="cw/bank/detail.do?id=${param.id}" >
        <input type="hidden" name="id">
        <input type="hidden" name="remrk">
        <fieldset class="l-fieldset">
            <legend class="l-legend">
                <div>转账信息</div>
            </legend>
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
		
       <tr> 
        <td class="l-t-td-left">交易时间：</td>
        <td class="l-t-td-right"> 
            <input name="jyTime" validate="{required:true}" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd hh:mm:ss'}" />
        </td>
        <td class="l-t-td-left">转入金额：</td>
        <td class="l-t-td-right"> 
            <input name="money"  validate="{required:true}" onkeyup="SunAmount();" id="money1" type="text" ltype='text'/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">对方户名：</td>
        <td colspan="3" class="l-t-td-right"> 
            <input validate="{required:true}" name="accountName" type="text" ltype='text'/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">转账人：</td>
        <td class="l-t-td-right"> 
            <input validate="{required:true,maxlength:30}" name="transferPerson" type="text" ltype='text'/>
        </td>
        <td class="l-t-td-left">转账人电话：</td>
        <td class="l-t-td-right"> 
            <input validate="{required:true,maxlength:30}" name="transferPersonTel" type="text" ltype='text'/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">转账人地址：</td>
        <td class="l-t-td-right" colspan="3"> 
            <input validate="{required:true,maxlength:255}" name="transferAddress" type="text" ltype='text'/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">已收金额：</td>
        <td class="l-t-td-right"> 
            <input name="usedMoney" validate="{required:true}" onkeyup="SunAmount();" id="usedMoney" type="text" ltype='spinner' ligerui="{type:'float',explain:'小于转入金额'}" value="0"/>
        <td class="l-t-td-left">剩余金额：</td>
        <td class="l-t-td-right"> 
            <input name="restMoney"   id="restMoney" type="text" ltype='spinner' ligerui="{type:'float',explain:'小于转入金额'}"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">备注：</td>
        <td class="l-t-td-right" colspan="3"> 
            <textarea name="remrk" rows="2" class="l-textarea" validate="{maxlength:2000}" ligerui="{explain:'字数不能超过2000个字'}"/></textarea>
        </td>
       </tr>
      </table>
      </fieldset>
        <fieldset class="l-fieldset">
            <legend class="l-legend">
                <div>收费记录</div>
            </legend>
            <div style="height:100%;" id="payDetail"></div>
            </fieldset>
         <fieldset class="l-fieldset" id='backlog'>
            <legend class="l-legend">
                <div>退款记录</div>
            </legend>
            <div style="height:100%;" id="backDetail"></div>
            </fieldset>
    </form>
</body>
</html>
