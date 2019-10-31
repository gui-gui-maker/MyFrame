<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus = "${param.pageStatus}">
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

    String sql=" select id,GYSMC FROM TJY2_CH_SUPPLIER WHERE STATUS=1 and CREATE_ORG_ID='"+uu.getOrg().getId()+"'";
	%>
<script type="text/javascript">
	var pageStatus = "${param.pageStatus}";
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
 	         		{text: "保存", icon: "save", click: save},
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
	                	var gridDataArr=new Array();
	                	var goodsAndOrder=response.data.goodsAndOrder;
	                	for(var i=0; i<goodsAndOrder.length;i++){
	                		var rowData=new Object();
	                		rowData.id=goodsAndOrder[i].goods.id;
	                		rowData.wpmc=goodsAndOrder[i].goods.wpmc;
	                		rowData.rkdh=goodsAndOrder[i].goods.warehousing_num;
	                		rowData.wplx=goodsAndOrder[i].goods.wplx;//data.data[i].lx_id;
	                		rowData.ggjxh=goodsAndOrder[i].goods.ggjxh;
	                		rowData.dw=goodsAndOrder[i].goods.dw;
	                		rowData.sl=goodsAndOrder[i].sl;
	                		rowData.je=goodsAndOrder[i].goods.je;
	                		rowData.se=goodsAndOrder[i].goods.se;
	                		rowData.bzs=goodsAndOrder[i].bz;
	        				gridDataArr.push(rowData);
	                	}
	        		    if(gridDataArr!=null){
	        			   grid.loadData({Rows:gridDataArr});
	        		    }
	        		    $("body").unmask();
			},
			showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
		
		init();
	});

	

	function save(){
		if(rows.length!=0){//删除
			$.ajax({
                url: "com/tjy2/goodsReturn/deleteReturnByGoodsId.do",
                type: "POST",
                async: false, 
                data:{"goodsId":rows.toString(),"orderId":'${param.id}'},
                success : function(data, stats) {
                }
			})
		}
		 if ($("#form1").validate().form()) {
			 $("body").mask("正在保存数据，请稍后！");
			 var formData = $("#form1").getValues();
			formData.goods=grid.getData();
	        var instruction=$.ligerui.toJSON(formData);
	        $.ajax({
                url: "com/tjy2/goodsReturn/saveGoodsReturn.do",
                type: "POST",
                async: false, 
                data:{"goodsReturn":instruction},
                success : function(data, stats) {
					$("body").unmask();
					if (data["success"]) {
						top.$.dialog.notice({
							content : '保存成功'
						});
						api.data.window.Qm.refreshGrid();
						api.close();
					} else {
						$.ligerDialog.error('提示：' + data.message);
					}
				},
                error : function(data) {
                    $("body").unmask();
                    $.ligerDialog.error('保存数据失败！');
                }
            });
		 }
	}
	
	
	
    function init(){
    	var typeName=<u:dict sql="SELECT ID,LX_NAME FROM TJY2_CH_GOODS_TYPE WHERE STATE='1'"/>
     	   column =[
					{display: "",
						isSort: false, 
						width: '30',
						render : function(item, index) {
							return "<a class='l-a l-icon-del' href='javascript:delDevice(grid," + index + ")'><span>删除</span></a>";
						}
					},
					{ display: '物资表ID', name: 'id',type: 'text',hide:true},
 		 			{ display: '产品名称', name: 'wpmc',align: 'center', width: 78,totalSummary:{ render: function (e){return "合计：" }}},
 		 			{display: '入库单号',width: '130',name: 'rkdh',type:'text'},
 		 			{display: '类型',width: '80',name: 'wplx',type:'text'},
 		 			{ display: '规格及型号', name: 'ggjxh',align: 'center', width: 180},
 		 			{ display: '单位', name: 'dw',align: 'center', width: 80},
 		 			{ display: '退货数量', name: 'sl',align: 'center', width: 80,editor: { type: 'int',minValue:'1' },totalSummary:{ render: function (e){return e.sum }}},
 		 			{ display: '备注', name: 'bzs',align: 'center', width: 180,editor: { type: 'text' }}
 		 			
 				]
     	   
     
 		
    		grid = $("#checkGrid").ligerGrid({
             columns:column, 
             data:{Rows:[]},
             rownumbers:true,
             frozenRownumbers: false,
             usePager: false,
             enabledEdit: true,
		     clickToEdit: true,
		     rownumbers: true, 
	         enabledEdit: '${param.pageStatus}'=='detail' ?false:true,
// 		     onAfterEdit:f_onBeforeEdit,
             height:'90%'
 	  	});
     	  
    }
//     function f_onBeforeEdit(e){
// 		 var column_name = e.column.name;
//    	   if (column_name == "thsl"){
//    		    if(e.record.sl<e.record.thsl){
//    		    	alert("退货数量不能大于库存数量哟！");
   		    	
//    		    }
//           }
//     }
    
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
				 bh.push(n.rkdh);
			 }
			 return n.id!=rowData.id;
		});
		 
		//更新合计
		grid.loadData({Rows:lb});
		var rkdbh=$("#rkdbh").val().split(",");
		 //取两个数组的交集！！！！ IE8不支持
// 		 var intersection = rkdbh.filter(function(v){ return bh.indexOf(v) > -1 });
		 
		 //取交集
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
		function callUser(id, name){ 
			$('#lxrmc_id').val(id);
			$('#lxrmc').val(name);
		}	
		 function queryRk(){
			 var rkdbh=$("#rkdbh").val();
				$.ajax({
	                url: "com/tjy2/goodsReturn/getRkGoods.do",
	                type: "POST",
	                data:{"rkId":rkdbh,'oldBH':oldBH},
	                success : function(data, stats) {
	                	var gridDataArr=new Array();
	                	var bean=grid.getData();
						
	                	var oldGoods ={};
	                	$.each(grid.getData(), function (i, item) {
	                		oldGoods[item.id]=item;
	                	  }); 
	                	var newGoods={}
	                	$.each(data.data, function (i, item) {
	                		newGoods[item.id]=item;
	                	  }); 

	                	//以newGoods为准，id一样的取oldGoods里面的数据
	                	var newresult = [];
	                	for(var id in newGoods){
	                		var bdata = newGoods[id];
	                		var adata = oldGoods[id];
	                		if(adata){
	                			mergeData(bdata, adata);
	                		}
	                		newresult.push(bdata);
	                	}
	                	

	                	//newGoods里面多的数据要加在新的里面，newGoods和oldGoods里面少的数据，新的里面也要没有
	                	var bresult2 = [];
	                	for(var id in newGoods){
	                		var bdata = newGoods[id];
	                		var adata = oldGoods[id];
	                		if(null == oldGoods){
	                			bresult2.push(bdata);
	                		}
	                	}

// 	                	console.log(newresult);
// 	                	console.log(bresult2);
	                	
	                	//合并对象属性
	                	function mergeData(to, from){
	                	  if(from === undefined){
	                		return;
	                	  }

	                	  for(var p in from){
	                		to[p] = from[p];
	                	  }
	                	}
	                	
	                	for(var i=0; i<newresult.length;i++){
	                				var rowData=new Object();
	                				rowData.id=newresult[i].id;
	    	                		rowData.create_time=newresult[i].create_time;
	    	                		rowData.create_user_id=newresult[i].create_user_id;
	    	                		rowData.create_user_name=newresult[i].create_user_name;
	    	                		rowData.create_org_name=newresult[i].create_org_name;
	    	                		rowData.create_org_id=newresult[i].create_org_id;
	    	                		
	    	                		rowData.wpmc=newresult[i].wpmc;
	    	                		rowData.rkdh=newresult[i].warehousing_num;
	    	                		rowData.wplx=newresult[i].wplx;//data.data[i].lx_id;
	    	                		rowData.ggjxh=newresult[i].ggjxh;
	    	                		rowData.dw=newresult[i].dw;
	    	                		rowData.sl=newresult[i].sl;
	    	                		rowData.bzs=newresult[i].bz;
	    							
	    	        				gridDataArr.push(rowData);
	                		
	                	}

	        		    if(gridDataArr!=null){
	        			   grid.loadData({Rows:gridDataArr});
	        		    }
	        		    $("body").unmask();
	                	
	                	
	                	}
	                });
		 }
		 
		 var oldBH='';
		 function selectRkDh(){
			 var rkdbh=$("#rkdbh").val();
			 oldBH=rkdbh;
				winOpen({
					width :$(top).width()-700,
					height : 420,
					lock : true,
					title : "选择入库单号",
					content : 'url:app/supplies/out/return_rk_dh.jsp',
					data : {
						"window" : window,'rkbh':rkdbh
					}
				});
		 }
		 function returnRkBh(bh){
			 $("#rkdbh").val(bh);
			 
			 queryRk()
		 }

		 function getGysId(val,text){
			 $("#thgys").val(text);
		 }
</script>
</head>
<body  >
    <form id="form1"  getAction="com/tjy2/goodsReturn/detail.do?id=${param.id}">
     <input type="hidden" name="id"/>
     <input type="hidden" name="create_user_id"/>
     <input type="hidden" name="create_user_name"/>
     <input type="hidden" name="create_org_name"/>
     <input type="hidden" name="create_org_id"/>
     <input type="hidden" name="create_time"/>
     <input type="hidden" name="thbh"/>
     
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
       		<input name="rkdbh" value="" id="rkdbh"  type="text" ltype='text' validate="{required:true}" onclick="selectRkDh()"/></td>
       		
       </tr>
       <tr>
       		<td class="l-t-td-left">退货供应商：</td>
       		<td class="l-t-td-right">
       		<input name="thgys" id="thgys" type="hidden" />
       		<input name="thgys_id" id="thgys_id" validate="{required:true}" ltype='select' 
       		ligerui="{onSelected:getGysId,
       		data:<u:dict code='' sql='<%=sql%>'/>}"/></td>
       		<td class="l-t-td-left">退货时间：</td>
       		<td class="l-t-td-right"><input name="thtime" id="thtime" value=""  ltype='date' validate="{required:true}"  ligerui="{format:'yyyy-MM-dd'}" /></td>
       		
       		
       </tr>
       <tr>
       		<td class="l-t-td-left">接收人：</td>
       		<td class="l-t-td-right"><input name="jsr" id="jsr" value="" type="text" ltype='text' validate="{maxlength:200}" /></td>
       		<td class="l-t-td-left">接收电话：</td>
       		<td class="l-t-td-right"><input name="tel" id="tel" value="" type="text" ltype='text' validate="{maxlength:200}" /></td>
       		
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
