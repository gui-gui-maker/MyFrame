<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
	<title>四川省特检院就餐系统</title>
	<link rel="stylesheet" type="text/css" href="app/fwxm/dining/jquery-easyui-1.5.1/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="app/fwxm/dining/jquery-easyui-1.5.1/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="app/fwxm/dining/jquery-easyui-1.5.1/demo/demo.css">
	<script type="text/javascript" src="app/fwxm/dining/jquery-easyui-1.5.1/jquery.min.js"></script>
	<script type="text/javascript" src="app/fwxm/dining/jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="app/fwxm/dining/jquery-easyui-1.5.1/easy.tools.js"></script>
<!-- 	<script type="text/javascript" src="app/fwxm/dining/dining-js/look.js"></script> -->
	
	<style type = "text/css">
	    body{ background:url(app/fwxm/dining/jquery-easyui-1.5.1/themes/icons/cy_bg.jpg) no-repeat ;  background-size: cover; }
		#container{
			margin:0,auto;
		}
		
		#cc {
			 -moz-box-shadow:0 4px 15px rgba(0, 0, 0, .35);
			 -webkit-box-shadow:0 4px 15px rgba(0, 0, 0, .35);
			 box-shadow:0 4px 15px rgba(0, 0, 0, .35); 
		}
		.north-left{
			width:220px;
			height:40px;
			line-height:40px;
			margin-left:5px;
			float:left;
		}
		.north-right{
			width:150px;
			padding:10px 20px 0 0;
			float:right
		}
		.Center-Container {  
		  position: relative;  
		}  
  
		.Absolute-Center {  
		  width: 50%;  
		  height: 50%;  
		  overflow: auto;  
		  margin: auto;  
		  position: absolute;  
		  top: 0; left: 0; bottom: 0; right: 0;  
		}  
		
		a.easyui-linkbutton{
			text-decoration:none;
		}
		
		
	.datagrid-row,.datagrid-header-row {  
	  height: 40px;  
	}
	.north-left ,north-right{
		font-size:14px;
		font-weight:bold;
	}
	.datagrid-header-row td div{  
	 	font-size:16px;
	 	font-weight:bold;
	}
	.datagrid-row td div {
		font-size:13px;
	}
	
	</style>
</head>
<body>
<div id="container" style="width:900px;margin:0 auto;padding:0;">
	<h1 style="text-align:center;color:#fff">四川省特检院就餐系统</h1>
	<div id="cc" class="easyui-layout" style="width:900px;height:680px;">
		<div data-options="region:'north',split:true" style="height:60px">
			<div class="north-left">
				刷卡: <span id="card"><input type="text" id="cardNo" name="cardNo" value=""></span>
		    </div>
			<div class="north-left">
				领餐卡号: <span id="card_no"></span>
		    </div>
		    <div class="north-left">
				备餐编号:<span id="number"></span>
		    </div>
		     <div class="north-right">
				<a href="javascript:void(0)" onclick="initGridData();" class="easyui-linkbutton"  style="margin:0 5px 0 51px;width:60px;height:30px;" data-options="iconCls:'icon-reload'">刷新</a>
			</div>
		</div>
		<div data-options="region:'center'" style="height:500;padding:20px;">
			<table id="data" class="easyui-datagrid" 
					title="订单列表" style="width:100%;height:100%;"
					data-options="nowrap:false">
			</table>
		</div>
		<div data-options="region:'south',split:true" style="height:100px;">
			<span id="message" style="word-break:break-all;"></span>
		</div>
	</div>
	
</div>
	<script type="text/javascript">
		$(function(){
			$('#cc').layout();
			setHeight();
			//加载table数据
			initGridData();
			
			$('#cardNo').focus();
			$('#cardNo').bind('input propertychange', function(event) {
				var value = $(this).val();
				if(value.length>=10){
					$('#card_no').html(value);
					swipe(value);
					$('#cardNo').val("").focus();
				}
			});  
		});
		function setHeight(){
			var c = $('#cc');
			var p = c.layout('panel','center');	// get the center panel
			var oldHeight = p.panel('panel').outerHeight();
			if(p.find('div.order')[0]==null){
				p.panel('resize', {height:'400px'});
			}else{
				p.panel('resize', {height:'auto'});
			}
			var newHeight = p.panel('panel').outerHeight();
			c.layout('resize',{
				height: (c.height() + newHeight - oldHeight)
			});
		}
		//初始化数据
		function initGridData(){
			$.getJSON( "dining/foodOrder/getGridData.do", function(res) {
				if(res.success){
					var data = res.data;
					load(data);
				}else{
					$.messager.show({
		                title:'提示',
		                msg:data.data,
		                timeout:1500,
		                showType:'fade', 
		                style:{
		                    right:'',
		                    bottom:''
		                }
		            });
				}
			});
		}
		//加载数据
		function load(data){
			var grid = [];
			var row = {};
			$.each(data,function(i,item){
				row = {};
				row.card = item.card_no;
				row.user = item.order_man;
				row.foods = item.fpm_names;
				row.count = item.count;
				row.number = item.prepareNumber;
				row.priority=item.grade;
				row.state = item.ostatus;
				row.id =item.id;
				grid.push(row);
			});
			$('#data').datagrid({  
			    border:false,
			    iconCls: 'icon-save',
			    fitColumns:true,  
			    singleSelect: true,   
			    columns:[[
			        {field:'card',title:'卡号',width:120},  
			        {field:'user',title:'姓名',width:80,align:'center'},  
			        {field:'foods',title:'菜单',width:200,align:'center'}, 
			        {field:'count',title:'份数',width:40,align:'center'}, 
			        {field:'number',title:'编号',width:40,align:'center'},
			        {field:'priority',title:'优先级',hidden:true,align:'center'},
			        {field:'state',title:'状态',width:60,align:'center', 
			        	formatter:function(value,row){
			        		var str = '';
			            	if(value=='0'){
			            		str = '未备餐';  
			            	}else if(value=='1'){
			            		str = '已备餐';  
			            	}else if(value=='2'){
			            		str='已领餐'
			            	}else{
			            		str = value;
			            	}
			                return str;  
			            }  
			        },  
			        {field:'id',title:'操作',width:150,align:'center',  
			            formatter:function(value,row){
			            	if(row.state==0){
				                return '<a href="javascript:void(0);" class="easyui-linkbutton l-btn l-btn-small" onclick="save(\''+value+'\');"><span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">备餐</span><span class="l-btn-icon icon-cart-ok">&nbsp;</span></span></a>';
				                	//<a href="javascript:void(0);" class="easyui-linkbutton" onclick="lock(\''+value+'\');">备餐</a> 
			            	}else if(row.state==1){
			            		return '<a href="javascript:void(0);" class="easyui-linkbutton l-btn l-btn-small" onclick="get(\''+value+'\');"><span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">领餐</span><span class="l-btn-icon icon-ok">&nbsp;</span></span></a>';  
			            	}else{
			            		return '';
			            	}
			            }  
			        }  
			    ]],
			    data: grid,
			    rowStyler: function(index,row){
                    if (row.priority == 20||row.priority == 15){
                        return 'background-color:red;color:#fff;font-weight:bold;font-size:30px;';
                    }else if(row.priority == 30){
                    	return 'background-color:yellowgreen;color:#fff;font-size:14px;';
                    }else if(row.priority == 40){
                    	return 'background-color:blue;color:#fff;font-size:12px;';
                    }else if(row.priority == 10){
                    	return 'background-color:pink;color:black;font-weight:bold;font-size:12px;';
                    }
                }
			});
			$('#cardNo').focus();
		}
		//备餐完毕
		function save(id){
			$.ajax({
				  type: 'POST',
				  url: "dining/foodOrder/prepareCompleted.do", 
				  data: {"id":id},
				  success: saveSuccess,
				  dataType: "json"
				});
		}
		function saveSuccess(data){
			if(data.success){
				var order = data.order;
				$('#cc').layout('panel','south').find('span#message').html(order.order_man+"的美餐，准备完毕...");
				var rows = data.data;
				load(rows);
			}else{
				$.messager.show({
	                title:'提示',
	                msg:data.data,
	                timeout:1500,
	                showType:'fade', 
	                style:{
	                    right:'',
	                    bottom:''
	                }
	            });
			}
			$('#cardNo').focus();
		}
		//根据卡号获取订单,刷卡
		function swipe(cardNo){
		 	$.ajax({
				  type: 'POST',
				  url: "dining/foodOrder/getCurrentOrderByCard.do", 
				  data: {"cardNo":cardNo},
				  success: function(data){
					  if(data.success){
						  var order = data.order;
						  var rows = data.data;
						//显示备餐编号
						if(order){
						 	$('#cc').layout('panel','north').find('span#number').html(order.prepareNumber);
						}else{
							 $.messager.show({
					                title:'提示',
					                msg:'未查询到订单...',
					                timeout:1500,
					                showType:'fade',
					                style:{
					                    right:'',
					                    bottom:''
					                }
					            });
						}
						//刷新数据
						load(rows);
					  }else{
						  $.messager.show({
				                title:'提示',
				                msg:data.data,
				                timeout:1500,
				                showType:'fade', 
				                style:{
				                    right:'',
				                    bottom:''
				                }
				            });
					  }
				  },
				  dataType: "json"
			});
		}
		//领餐
		function get(id){
			$.ajax({
				  type: 'POST',
				  url: "dining/foodOrder/setOrderStatusOver.do", 
				  data: {"id":id},
				  success: function(data){
					  if(data.success){
						  var rows = data.data;
						  load(rows);
						  $('#cc').layout('panel','north').find('span#number').html('');
						  $('#cc').layout('panel','north').find('span#card_no').html('');
						  $.each(rows,function(i,row){
							  if(id = row.id){
								  $('#cc').layout('panel','south').find('span#message').html(row.order_man+":领走了她的美食...");
							  }
						  });
					  }else{
						  $.messager.show({
				                title:'提示',
				                msg:data.data,
				                timeout:1500,
				                showType:'fade',
				                style:{
				                    right:'',
				                    bottom:''
				                }
				            });
					  }
				  },
				  dataType: "json"
			});
			$('#cardNo').focus();
		}
		
	</script>
</body>
</html>