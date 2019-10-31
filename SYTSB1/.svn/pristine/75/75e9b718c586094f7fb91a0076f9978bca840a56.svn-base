<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus = "${param.status}">
<title>存货管理</title>
  <%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="/app/common/js/render.js"></script>
<%
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User uu = (User)curUser.getSysUser();
	com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
	String userId=e.getId();
	String userid = SecurityUtil.getSecurityUser().getId();
	String users=curUser.getName();
	
	
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date = sf.format(new Date());


    String sql=" select id,GYSMC FROM TJY2_CH_SUPPLIER WHERE STATUS=1 and CREATE_ORG_ID='"+uu.getOrg().getId()+"'";
	%>
<script type="text/javascript">
	var pageStatus = "${param.status}";
    var column=[];
	var grid;
	var tbar="";
	$(function() {
		$.ligerui.controls.Spinner.prototype. _getVerifyValue =function(value) {
            var g = this,
                p = this.options;
            var newvalue = null;
			if (p.type == 'int') {
                newvalue = parseInt(value);
                if(newvalue=='0'){
                    newvalue='1';
                }
            } 
            if (!g._isVerify(newvalue)) {
                return g.value;
            } else {
                return newvalue;
            }
        }
		
		if(pageStatus=="detail"){
			tbar=[{text: "关闭", icon: "cancel", click: function(){api.close();}}];
 		}else{
 			tbar=[
 	         		{text: "出库", icon: "save", click: out},
 					{text: "关闭", icon: "cancel", click: function(){api.close();}}];
 		}
		
		$("#form1").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	         		api.data.window.Qm.refreshGrid();
	            	api.close();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (response){
				$("#lqbm").val(response.data.create_org_name);
				$("#lqr").val(response.data.create_user_name);
	                	var gridDataArr=new Array();
	                	var order=response.data.goodsAndOrder;
	                	for(var i=0; i<order.length;i++){
	                		var rowData=new Object();
	                		rowData.id=order[i].goods.id;
	                		rowData.wpmc=order[i].goods.wpmc;
	                		rowData.warehousing_num=order[i].goods.warehousing_num;
	                		rowData.wplx=order[i].goods.wplx;//data.data[i].lx_id;
	                		rowData.ggjxh=order[i].goods.ggjxh;
	                		rowData.dw=order[i].goods.dw;
	                		rowData.sl=order[i].sl;//领取数量
	                		rowData.je=order[i].goods.je;
	                		rowData.se=order[i].goods.se;
	                		rowData.cssl=order[i].goods.cssl;
	                		rowData.bz=order[i].bz;
	                		rowData.kcsys=order[i].goods.sl;//库存剩余数量
	        				gridDataArr.push(rowData);
	                	}

	        		    if(gridDataArr!=null){
	        			   grid.loadData({Rows:gridDataArr});
	        		    }
			},
			showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
		
		
		
		
		init();
	});

	

	function out(){
            var formDatas = $("#form1").getValues();
            var goodsDatas = $("#checkGrid").ligerGetGridManager().getData();
            if ( goodsDatas.length <= 0 ) {
                $.ligerDialog.error("没有退货物资！");
                return;
            }
            $("body").mask("正在保存请稍后...");
            var goods = [];
            for (var i = 0; i < goodsDatas.length; i++) {
                goods.push({
                    goods: goodsDatas[i],
                    sl: goodsDatas[i]["sl"],
                    fk_goods_id: goodsDatas[i]["id"],
                    bz: goodsDatas[i]["bz"]
                })
            }
            console.log(formDatas.thtime);
            formDatas["ckyjtype"] = "TH";
            formDatas["goods"] = goods;
			formDatas["lqsj"]=formDatas.thtime;
			delete(formDatas['tel']);
			delete(formDatas['thtime']);
			delete(formDatas['thgys_id_val']);
			delete(formDatas['thgys_id']);
			delete(formDatas['thgys']);
			delete(formDatas['rkdbh']);
			delete(formDatas['jsr']);
            $.ajax({
                url: "chck/outstorage.do",
                data: JSON.stringify(formDatas),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                type: "post",
                async: true,
                success: function (data) {
                    $("body").unmask("");
                    if ( data.success ) {
                        api.data.window.Qm.refreshGrid();
                        api.close();
                    } else {
                        $.ligerDialog.error(data.msg);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    $("body").unmask("");
                    $.ligerDialog.error("网络出现问题，请联系管理员或刷新后再试");
                }
            });
            
	}
	
	
	
    function init(){
    	var typeName=<u:dict sql="SELECT ID,LX_NAME FROM TJY2_CH_GOODS_TYPE WHERE STATE='1'"/>
     	   column =[
					{ display: '物资表ID', name: 'id',type: 'text',hide:true},
					{ display: '初始数量', name: 'cssl',type: 'text',hide:true},
 		 			{ display: '产品名称', name: 'wpmc',align: 'center', width: 78,totalSummary:{ render: function (e){return "合计：" }}},
 		 			{display: '入库单号',width: '130',name: 'warehousing_num',type:'text'},
 		 			{display: '类型',width: '80',name: 'wplx',type:'text'},
 		 			{ display: '规格及型号', name: 'ggjxh',align: 'center', width: 180},
 		 			{ display: '单位', name: 'dw',align: 'center', width: 80},
 		 			{ display: '退货数量', name: 'sl',align: 'center', width: 80,editor: { type: 'int',minValue:'1'},min: 1,totalSummary:{ render: function (e){return e.sum }}},
 		 			{ display: '库存数量', name: 'kcsys',align: 'center', width: 80,totalSummary:{ render: function (e){return e.sum }}},
 		 			
 		 			{ display: '备注', name: 'bz',align: 'center', width: 180,editor: { type: 'text' }},
					{display: "",
						isSort: false, 
						width: '30',
						render : function(item, index) {
							return "<a class='l-a l-icon-del' href='javascript:delDevice(grid," + index + ")'><span>删除</span></a>";
						}
					}
 				]
     	   

           
 		
    		grid = $("#checkGrid").ligerGrid({
             columns:column, 
             data:{Rows:[]},
             rownumbers:true,
             frozenRownumbers: false,
             usePager: false,
             enabledEdit: '${param.status}'=='detail'?false:true,
             height:'90%'
 	  	});
     	  
    }
    
	var rows=[];
	 function delDevice(row,index){
		 row.deleteSelectedRow();
		 var rowData= grid.getSelectedRow();
		 if(pageStatus=="modify" ){
	  			//预删除数据
	  			rows.push(rowData.id);
	  	}
		 //判断物资信息列表是否还有‘入库单编号’
		 var lb=grid.getData();
		 var bh=new Array();
		 lb=$.grep(lb,function(n,i){//false删除；true保留
			 if(n.id!=rowData.id){
				 bh.push(n.warehousing_num);
			 }
			 return n.id!=rowData.id;
		});

		 var rkdbh=$("#rkdbh").val().split(",");
		 //取两个数组的交集！！！！ IE8不支持
// 		 var intersection = rkdbh.filter(function(v){ return bh.indexOf(v) > -1 });
		 grid.loadData({Rows:lb});
       var unix = new Array();
       for (var i = 0; i < rkdbh.length; i++) {
           if($.inArray(rkdbh[i] ,bh) != -1){
            unix.push(rkdbh[i]);
           }
       }
		 $("#rkdbh").val(unix);
	 }
		
		


		//四舍五入
		 function decimal(num,v){
		    	var vv = Math.pow(10,v);
		    	return Math.round(num*vv)/vv;
		 }
		
		 
		 function getGysId(val,text){
			 $("#thgys").val(text);
		 }
</script>
</head>
<body>
    <form id="form1"  method="post" getAction="com/tjy2/goodsReturn/detail.do?id=${param.id}">
     
            <input type="hidden" name="lqId" value="<%=curUser.getId()%>"/>
            <input type="hidden" name="lqOrgId" value="<%=curUser.getDepartment().getId()%>"/>
            <input type="hidden" name="lqUnitId" value="<%=curUser.getUnit().getId()%>"/>
            <input type="hidden" name="status"/>
            
            <input type="hidden" name="lqbm" id="lqbm"/>
     		<input type="hidden" name="lqr" id="lqr"/>
     		<input type="hidden" name="id" id="id"/>
    
     
     <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
       <tr>
       		<td class="l-t-td-left"></td>
       		<td class="l-t-td-right"></td>
       		<td class="l-t-td-left"></td>
       		<td class="l-t-td-right"></td>
       </tr>
       <tr>
       		<td class="l-t-td-left">入库单编号：</td>
       		<td class="l-t-td-right" colspan="3">
       		<input name="rkdbh" value="" id="rkdbh" 
       		type="text" ltype='text' validate="{required:true}"  ligerUi="{disabled:true}" /></td>
       		
       </tr>
       <tr>
       		<td class="l-t-td-left">退货供应商：</td>
       		<td class="l-t-td-right">
       		<input name="thgys" id="thgys" type="hidden" />
       		<input name="thgys_id" id="thgys_id" validate="{required:true}"  ltype='select' 
       		ligerui="{onSelected:getGysId,disabled:true,
       		data:<u:dict code='' sql='<%=sql%>'/>}"/></td>
       		<td class="l-t-td-left">退货时间：</td>
       		<td class="l-t-td-right"><input name="thtime" id="thtime" value=""  ltype='date' ligerui="{disabled:true,format:'yyyy-MM-dd'}" /></td>
       		
       </tr>
       <tr>
       		<td class="l-t-td-left">接收人：</td>
       		<td class="l-t-td-right"><input name="jsr" id="jsr"  value="" type="text" ltype='text' ligerUi="{disabled:true}"  validate="{maxlength:200}" /></td>
       		<td class="l-t-td-left">接收电话：</td>
       		<td class="l-t-td-right"><input name="tel" id="tel" value="" type="text" ltype='text' ligerUi="{disabled:true}" validate="{maxlength:200}" /></td>
       		
       </tr>
       
      </table>
      
	<fieldset class="l-fieldset" >
			<legend class="l-legend">
				<div>物资信息</div>
			</legend>
			<div  style="height:350px;"  id="checkGrid" ></div>
		</fieldset>	
    </form> 


</body>
</html>
