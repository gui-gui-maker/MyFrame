<%@page import="com.khnt.rbac.impl.bean.Employee"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head pageStatus="${param.status}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <%
    	Employee employee = (Employee)session.getAttribute("employee");
    	System.out.println(employee.getId());
    	request.setAttribute("employee", employee);
    %>
	<head>
		<meta charset="utf-8">
		<title>Hello MUI</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<!--标准mui.css-->
		<link rel="stylesheet" href="app/weixin/dining/css/mui.min.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="app/weixin/dining/css/app.css"/>
		<link rel="stylesheet" type="text/css" href="app/weixin/dining/css/iconfont.css"/>
		<link rel="stylesheet" type="text/css" href="app/weixin/dining/css/icons-extra.css"/>
		<link rel="stylesheet" type="text/css" href="app/weixin/dining/css/iconfont2.css"/>
<!-- 		<link rel="stylesheet" href="app/weixin/dining/css/validationEngine.jquery.css" type="text/css"/>
		<script src="app/weixin/dining/js/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
		<script src="app/weixin/dining/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script> -->
		<style>
			/* 	预加载 */
			.flex-container {
				display: -webkit-box;
				display: -webkit-flex;
				display: flex;
				-webkit-flex-flow: row wrap;
				justify-content: space-between;
				text-align: center;
				
			}
			.mui-content-padded.yujiazai {
				padding: 10px;
			}
			.mui-content-padded.yujiazai a {
				margin: 3px;
				width: 50px;
				height: 50px;
				display: inline-block;
				text-align: center;
				background-color: #fff;
				border: 0px solid #ddd;
				border-radius: 25px;
				background-clip: padding-box;
				margin:0 auto;
			}
			.mui-content-padded.yujiazai a .mui-icon {
				margin-top: 12px;
			}
			.mui-spinner,
			.mui-spinner-white {
				margin-top: 12px
			}
			.active .mui-spinner-indicator {
				background: #007AFF;
			}
			.mui-content a {
				color: #8F8F94;
			}
			.mui-content a.active {
				color: #007aff;
			}
			
			/*表单选择*/
			input[type=radio],button, .mui-btn {
				margin-top: 10px;
				margin-left: 10px;
			} 
			.chart {
				height: 200px;
				margin: 0px;
				padding: 0px;
			}
			
			#barChart.chart{
				width:100%;
			}
			#pieChart.chart{
				width:240%;
				padding:5px;
			}
			.title{
				margin: 20px 15px 10px;
				color: #6d6d72;
				font-size: 15px;
			}
			
			.oa-contact-cell.mui-table .mui-table-cell {
				padding: 11px 0;
				vertical-align: middle;
			}
			
			.oa-contact-cell {
				position: relative;
				margin: -11px 0;
			}
	
			.oa-contact-avatar {
				width: 75px;
			}
			.oa-contact-avatar img {
				border-radius: 50%;
			}
			.oa-contact-content {
				width: 100%;
			}
			.oa-contact-name {
				margin-right: 20px;
			}
			.oa-contact-name, oa-contact-position {
				float: left;
			}
	 	/*选择时间段*/
		.mui-input-row.mui-radio{
			position:relative;
		}
		.mui-input-row.mui-radio div{
			float:left;
			height:40px;
			text-indent:15px;
			line-height:40px;
		}
		.mui-input-row.mui-radio div span{
			text-indent:0;
		}
		.mui-radio input[type=radio]:before {
			display:inline-block;
			position:absolute;
			top:-9px;
		}
	/* 	.mui-input-row.mui-checkbox{
			height:60px;
			position:relative;
		} */
		.mui-input-row.mui-checkbox ul{
			margin:0 15px;
			padding:0;
			width:60px;
			height:60px;
			float:left;
		}
		.mui-input-row.mui-checkbox ul li{
			margin:0;
			padding:0;
		}
		.mui-input-row.mui-checkbox ul li.preview{
			height:60px;
		}
		.mui-input-row.mui-checkbox ul li.preview img{
			margin:6px 6px;
		}
		.oa-contact-content.mui-table-cell .mui-clearfix,.oa-contact-content.mui-table-cell .oa-contact-email.mui-h6{
			margin-right:70px;
		}
		/* .mui-checkbox input[type=checkbox]:before {
			display:inline-block;
			position:absolute;
			top:2px;
		}  */
		.mui-card{
			margin-bottom:60px;
		}
		.order-sign-before{
			position:absolute;
			top:0;
			left:0;
			width:150px;
			height:150px;
			border-radius:50%;
			overflow: auto;
			margin:auto;
			bottom:0;
			right:0;
		}
		.order-loading{
			position:absolute;
			opacity:0;
			filter: alpha(opacity=0);
			top:0;
			left:0;
			width:150px;
			height:150px;
			border-radius:50%;
			overflow: auto;
			margin:auto;
			bottom:0;
			right:0;
		}
		.order-loading span{
			display:block;
			width:150px;
			height:150px;
		}
		.order-signed{
			font-size:12px;
			position:absolute;
			top:0;
			left:0;
			width:300px;
			height:400px;
			border:1px solid yellowgreen;
			border-radius:10px;
			overflow: auto;
			margin:auto;
			bottom:0;
			right:0;
			text-align:center;
			line-height:400px;
		}
		.order-signed div{
			height:30px;
			line-height:30px;
			text-indent:10px;
		}
		.order-signed div.order_title{
			background-color:yellowgreen;
			border-top-left-radius:10px;
			border-top-right-radius:10px;
		}
		.order-signed div.order_menu{
			text-align:center;
		}
		</style>
		<script src="app/weixin/dining/js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="app/js/jQuery.md5.js"></script>
		<script type="text/javascript">
		/* //自动登录
		 	var username = '${username}';
			var password = '${password}';
			
			if(username!=''){
				$.post($("base").attr("href")+"j_spring_security_check",{j_username:username,j_password:$.md5("password")},function(data){
					
				});
			}else{
				$.post($("base").attr("href")+"j_spring_security_check",{j_username:"dengww",j_password:"11111"},function(data){
					//alert("admin");
				}); 
			} */
			 
		</script>
	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">员工食堂</h1>
		</header>
		<nav class="mui-bar mui-bar-tab">
			<a class="mui-tab-item" href="#tabbar-with-chat">
				<span class="mui-icon iconfont mui-icon-dining-shipu"></span>
				<span class="mui-tab-label">食谱</span>
			</a>
			
			<a class="mui-tab-item" href="#tabbar-with-contact">
				<span class="mui-icon iconfont mui-icon-dining-dingdan"></span>
				<span class="mui-tab-label">订单</span>
			</a>
			<a class="mui-tab-item" href="#tabbar-with-comming">
				<span class="mui-icon iconfont icon-qiandao"></span>
				<span class="mui-tab-label">签到</span>
			</a>
			<a class="mui-tab-item" href="#tabbar-with-map">
				<span class="mui-icon iconfont mui-icon-dining-zhuangtai"></span>
				<span class="mui-tab-label">状态</span>
			</a>
		</nav>
		<div class="mui-content mui-scroll" style="height:100%;">
		<!-- 第一页 -->
			<div id="tabbar-with-chat" class="mui-control-content mui-active">
				<div class="mui-content-padded yujiazai">
					<div class="flex-container">
						<a>
							<span class="mui-spinner"></span>
						</a>
					</div>
				</div>
				
			</div>
			<!-- 第二页 -->
			<div id="tabbar-with-contact" class="mui-control-content">
				<div class="mui-card">
					<ul class="mui-table-view mui-table-view-chevron" style="margin-bottom:10px;">
						<li class="mui-table-view-cell" ><h4>我的订单</h4>
						</li>
					</ul>
					<ul id="meal" class="mui-table-view mui-table-view-chevron">
						
					</ul>
				</div>
			<div class="mui-content-padded">
				<ul class="mui-pagination mui-pagination-lg">
					
				</ul>
			</div>
			</div>
			<!-- 第二页 -->
			<div id="tabbar-with-comming" class="mui-control-content" style="position:relative;height:100%;">
					
			</div>
			<div id="tabbar-with-map" class="mui-control-content">
				<div class="mui-content-padded ">
					<h5>食堂状态柱图</h5>
					<div class="chart" id="barChart"></div>
					<h5>食堂状态饼图</h5>
					<div class="chart" id="pieChart"></div>
				</div>
			</div>
		</div>
	</body>
	<script src="app/weixin/dining/js/mui.min.js"></script>
	<script src="app/weixin/dining/libs/echarts-all.js"></script>
	
	<script type="text/javascript" src="app/fwxm/dining/js-chart/highcharts.js"></script>
	<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
		<script script="text/javascript">
		var mealtype = $.parseJSON("<u:dict code="food_meal_type"/>");
		var foodtype = $.parseJSON("<u:dict code="fCategory"/>");
		var quantum = $.parseJSON("<u:dict code="food_quantum"/>");
		//查询是否有订单
		 mui.post('dining/foodOrder/initSignedPage.do',{'employeeId':'${employee.id}'},function(data){
			 console.log(data);
			if(data["success"]){
				 if(data.data){
					 var order = data.data;
					 if(order.isSigned=='no'){
						 $('#tabbar-with-comming').append('<input type="hidden" name="id" value="'+order.id+'"/>');
						 //签到ui
						 $('#tabbar-with-comming').append('<button id="sign_comming" type="button" data-loading-icon="mui-spinner mui-spinner-custom" class="mui-btn mui-btn-primary order-sign-before">到</button>');
					 }else{
						 var str = '<div class="order_title" >订单号:'+order.id+'</div>';
							 str +='<div>菜单:</div>';
						 var menus = order.fpm_names.split(',');
							$.each(menus,function(ind,menu){
								str+='<div class="order_menu">'+menu+'</div>';
							});
							str+='<div>份数:'+order.count+'</div>';
							str+='<div>窗口:'+order.wdw+'</div>';
							str+='<div>编号:'+order.prepareNumber+'</div>';
						 //显示订单数据
						 $('#tabbar-with-comming').append('<div class="order-signed">'+str+'</div>');
					 }
				 }else{
					// 没有订单
					 $('#tabbar-with-comming').append('<div class="order-signed">抱歉，没有找到您的订单，请前去订餐！</div>');
				 }
			 }else{
				 $('#tabbar-with-comming').append('<div class="order-signed">'+data.data+'</div>');
			 } 
		 },'json');
		 var mask = mui.createMask();//遮罩层
		 mui('body').on('tap', '#sign_comming', function() {
			 var id = $("input[name='id']").val();
			 if(id&&id!=''){
			 	mui.ajax('dining/foodOrder/weixinSignComming.do',{
				    data:{
				    	'id':id
				    },
				    dataType: 'json', //服务器返回json格式数据
				        type: 'post', //HTTP请求类型
				        timeout: 10000, //超时时间设置为10秒；
				    beforeSend: function() {
				        //plus.nativeUI.showWaiting(title, options);
				        mask.show();//显示遮罩层
				    },
				    complete: function() {
				        //plus.nativeUI.closeWaiting();
				        mask.close();//关闭遮罩层
				    },
				    success: function(data) {
				        //服务器返回响应，根据响应结果，分析是否登录成功； 
				    	$('#tabbar-with-comming').empty();
						if(data["success"]){
							 if(data.data){
								 var order = data.data;
								 var str = '<div class="order_title" >订单号:'+order.id+'</div>';
									 str +='<div>菜单:</div>';
								 var menus = order.fpm_names.split(',');
									$.each(menus,function(ind,menu){
										str+='<div class="order_menu">'+menu+'</div>';
									});
									str+='<div>份数:'+order.count+'</div>';
									str+='<div>窗口:'+order.wdw+'</div>';
									str+='<div>编号:'+order.prepareNumber+'</div>';
								 //显示订单数据
								 $('#tabbar-with-comming').append('<div class="order-signed">'+str+'</div>');
							 }
						 }else{
							 $('#tabbar-with-comming').append('<div class="order-signed">'+data.data+'</div>');
						 } 
				    },
				    error: function(xhr, type, errorThrown) {
				        mui.alert('服务器连接超时，请稍后再试');
				    }
				});
			 }
		 });
		  function signInit(){
			 mui.post('dining/foodOrder/initSignedPage.do',{'employeeId':'${employee.id}'},function(data){
				if(data["success"]){
					$('#tabbar-with-comming').empty();
					 if(data.data){
						 var order = data.data;
						 if(order.isSigned=='no'){
							 $('#tabbar-with-comming').append('<input type="hidden" name="id" value="'+order.id+'"/>');
							 //签到ui
							 $('#tabbar-with-comming').append('<button id="sign_comming" type="button" data-loading-icon="mui-spinner mui-spinner-custom" class="mui-btn mui-btn-primary order-sign-before">到</button>');
						 }else{
							 var str = '<div class="order_title" >订单号:'+order.id+'</div>';
								 str +='<div>菜单:</div>';
							 var menus = order.fpm_names.split(',');
								$.each(menus,function(ind,menu){
									str+='<div class="order_menu">'+menu+'</div>';
								});
								str+='<div>份数:'+order.count+'</div>';
								str+='<div>窗口:'+order.wdw+'</div>';
								str+='<div>编号:'+order.prepareNumber+'</div>';
							 //显示订单数据
							 $('#tabbar-with-comming').append('<div class="order-signed">'+str+'</div>');
						 }
					 }else{
						// 没有订单
						 $('#tabbar-with-comming').append('<div class="order-signed">抱歉，没有找到您的订单，请前去订餐！</div>');
					 }
				 }else{
					 $('#tabbar-with-comming').append('<div class="order-signed">'+data.data+'</div>');
				 } 
			 },'json');
		 } 
		 //菜单查询
		 mui.post('dining/pubo/dailyMenu.do',{},function(data){
			 $('#tabbar-with-chat').empty();
			 var list = data.data;
			 if(list.length==0){
				 $('#tabbar-with-chat').append('未发布菜单！');
				 return;
			 }
			 for(var i=0;i<list.length;i++){
					var meal = list[i].meal_name;
					var mealdate = list[i].use_time;
					var fpoid = list[i].id;
					var mealname = '';
					for(var n=0;n<mealtype.length;n++){
						if(mealtype[n].id==meal){
							mealname = mealtype[n].text;
						break;
						};
					}
					//var meal_quantum_code = meal==0?0:meal==1?1:2;
					/* var meal_quantums = '';
					$.each(quantum,function(index){
						if(quantum[index].id==meal_quantum_code){
							meal_quantums = quantum[index].children;
						}
					}); */
					
					mealdate = mealdate.substr(0,mealdate.indexOf(" "));
					$('#tabbar-with-chat').append('<h5 class="mui-content-padded">'+mealdate+'>'+mealname+'</h5><div class="mui-card"><form id="fm'+i+'" class="mui-input-group" action="dining/foodOrder/saveOrder.do" method="post"><input type="hidden" name="fpo_id" value="'+fpoid+'"/></form></div>');
					//foods是FoodExt对象集合,{[{id:'',name:'',attrs:Array[]},{id:'',name:'',attrs:Array[]}]}
					var foods = list[i].fs;
					for(var j = 0; j < foods.length; j++){
						var type = '';
						for(var m=0;m<foodtype.length;m++){
							if(foodtype[m].id==foods[j].ftype){
								type = foodtype[m].text;
							break;
							};
						}
						var files = foods[j].atts;
						if(files.length==0){
							$('#fm'+i).append('<div class="mui-input-row mui-checkbox">'
												+'<label>'+foods[j].name+'</label>' 
														/* +'<ul id="menuFiles'+i+j+'"><li style="width:60px;height:60px;background: #ccc url(app/weixin/dining/images/cc-default.jpg) no-repeat left -20px top 0px;"></li></ul>'
														+'<div class="oa-contact-content mui-table-cell">'
															+'<div class="mui-clearfix">'
																+'<h4 class="oa-contact-name">'+foods[j].name+'</h4>'
																 +'<span class="oa-contact-position mui-h6">'+type+'</span>' 
															+'</div>'
															 +'<p class="oa-contact-email mui-h6">'+foods[j].fdesc+'</p>' 
														+'</div>' */
														+'<input name="fpm_ids" value="'+foods[j].fpmId+'" type="checkbox">'
														/* +'<input name="fpm_ids" value="'+foods[j].fpmId+'" type="checkbox" style="line-height:80px;">' */
													+'</div>');
						}else{
							
							$('#fm'+i).append('<div class="mui-input-row mui-checkbox">'
									+'<label>'+foods[j].name+'</label>' 
									/* +'<ul id="menuFiles'+i+j+'"></ul>'
									+'<div class="oa-contact-content mui-table-cell">'
										+'<div class="mui-clearfix">'
											+'<h4 class="oa-contact-name">'+foods[j].name+'</h4>'
											 +'<span class="oa-contact-position mui-h6">'+type+'</span>' 
										+'</div>'
										+'<p class="oa-contact-email mui-h6">'+foods[j].fdesc+'</p>' 
									+'</div>' */
									+'<input name="fpm_ids" value="'+foods[j].fpmId+'" type="checkbox">'
/* 									+'<input name="fpm_ids" class="validate[required,funcCall[checkHELLO]] value="'+foods[j].fpmId+'" type="checkbox" style="line-height:80px;">' */
								+'</div>');
							var container = 'menuFiles'+i+j;
							$.each(files,function(k){
								if(files[k]!=undefined){
									createFileView(files[k].id,files[k].fileName,false,container,true,function(fid){});
								}
							});
						}
					}
					$.each(quantum,function(index){
						$('#fm'+i).append('<div class="mui-input-row mui-radio">'
													+'<div id="quantum'+quantum[index].id+'"><span class="mui-badge mui-badge-primary">'+quantum[index].text+'</span></div>'
													+'<input name="quantum" type="radio" value="'+quantum[index].id+'">'
												+'</div>'
							);
					});
					$('#fm'+i).append('<div class="mui-input-row"><label>份数</label><input name="count" style="width:60%;margin:0;" type="text" class="mui-input-clear" placeholder="输入份数"></div>');
					$('#fm'+i).append('<div class="mui-input-row"><label>期望菜品</label><input name="remark" style="width:60%;margin:0;" type="text" class="mui-input-clear" placeholder="菜品名称"></div>');
					$('#fm'+i).append('<div class="mui-button-row" style="height:60px;">'
												+'<button id="order_a_order" type="button" class="mui-btn mui-btn-primary" onclick="return false;">下订</button>&nbsp;&nbsp;'
												+'<button id="order_cancel" type="button" class="mui-btn mui-btn-danger" onclick="return false;">取消</button>'
											+'</div>');
				}
		 },'json');
		
		 //<span class="mui-badge mui-badge-danger">14</span>
		 //var quantumSelectors=$('input[type=radio]');
		 mui('body').on('tap', 'input[type=radio]', function() {
			 //获取本餐的id
				 var el = window.event.srcElement || window.event.target;
				 var form = el.parentNode.parentNode;
				 var fpo = $(form).find('input[name="fpo_id"]').val();
			 //ajax加载订单订购情况
			 mui.post('dining/pubm/getQmOs.do',{id:fpo},function(data){
				 var dataArray = data.data;
				 $.each(dataArray,function(index,obj){
					 var pc = obj.qm;
					 var div = $(form).find('div#quantum'+pc);
					 var span = div.find('span')[0];
					 div.empty();
					 div.append(span);
					 div.append('<span class="mui-badge mui-badge-danger">'+obj.count+'</span>');
				 });
			 },'json');
			 
		 });
		 //
		 mui('body').on('tap', 'button.mui-btn#order_cancel', function() {
			 var el = window.event.srcElement || window.event.target;
			 var form = el.parentNode.parentNode;
			 var checkboxs = $(form).find("input[type='checkbox']");
			 checkboxs.attr('checked',false);
			
		 }); 
		  mui('body').on('tap', 'button#order_a_order', function() {
				var el = window.event.srcElement || window.event.target;
				var classList = el.classList;
				//表单验证
				if(!$(el.parentNode.parentNode).find('input[name="fpm_ids"]:checked').val()){
					 mui.alert("请至少选择一个菜品");
					 return false;
				 };
				if(!$(el.parentNode.parentNode).find('input[name="quantum"]:checked').val()){
					 mui.alert("请选择就餐批次");
					 return false;
				 };
				 if(!$(el.parentNode.parentNode).find('input[name="count"]').val()||$(el.parentNode.parentNode).find('input[name="count"]').val().trim()==''){
					 mui.alert("请输入份数");
					 return false;
				 }else{
					 var regu = /^[1-9]\d{0,1}$/;//0-99
			         if (!regu.test($(el.parentNode.parentNode).find('input[name="count"]').val())) {
			        	 mui.alert("请输入1-99整数");
				         return false;
			         } 
				 };
				order(el);
				classList.add('mui-disabled');
				setTimeout(function(){disabled(classList);},15000);
				
		  });
		  function disabled(obj){
				if(obj.contains('mui-disabled')){
					obj.remove('mui-disabled');
				}
		  }

		 //订餐
		 function order(el){
			 
				var btnArray = ['否', '是'];
				mui.confirm('你确认选好了菜单并下单吗？', '你好！', btnArray, function(e) {
					if (e.index == 1) {
						
						var form = el.parentNode.parentNode;
						//form.submit(); 
						var ajaxURL= $(form).attr('action');       
			            //alert($(form).serialize());
		                $.ajax({
		                    type: "POST",
		                    dataType: "json",
		                    url: ajaxURL,
		                    data: $(form).serialize(),
		                    success: function (result) {
		                    	if(result.data){
				                    alert('订单号：'+result.data.id+',保存成功');
				                   $(form).find("input[type='checkbox']").attr('checked',false);
				                   $(form).find("input[type='radio']").attr('checked',false);
				                   $(form).find("input[type='text']").val('');
			                        mui.post('dining/foodOrder/weixinOrders.do',{pageSize:5,page:1},function(data){
			                        	
										refresh(data);
									},'json');
			                        signInit();
		                    	}else{
		                    		alert("error:"+result.msg);
		                    	}
		                    },
		                    error: function(data) {
		                        alert("error:"+data.responseText);
		                     }
		                });
					}
				});
		 }
		//分页
		//总记录数
		var pageSize = 5;
		var dataCount = 0;
		var pageNum = 0;
		mui.post('dining/foodOrder/weixinOrdersCount.do',{},function(data){
			dataCount = data.data;
			pageNum = dataCount/pageSize;
			$('.mui-pagination').append('<li class="mui-previous mui-disabled"><a href="#">&laquo;</a></li>');
			for(var i=0;i<pageNum;i++){
				if(i==0){
					$('.mui-pagination').append('<li class="mui-active"><a href="#">'+(i+1)+'</a></li>');
				}else{
					$('.mui-pagination').append('<li><a href="#">'+(i+1)+'</a></li>');
				}
			};
			$('.mui-pagination').append('<li class="mui-next"><a href="#">&raquo;</a></li>');
			
		},'json');
		//分页动作
		(function($) {
			$('.mui-pagination').on('tap', 'a', function() {
				var li = this.parentNode;
				var classList = li.classList;
				if (!classList.contains('mui-active') && !classList.contains('mui-disabled')) {
					var active = li.parentNode.querySelector('.mui-active');
					if (classList.contains('mui-previous')) {//previous
						if (active) {
							var previous = active.previousElementSibling;
							console.log('previous', previous);
							if (previous && !previous.classList.contains('mui-previous')) {
								$.trigger(previous.querySelector('a'), 'tap');
								//刷新页面
								//var page = previous.children[0].innerHTML;
								/* mui.post('dining/foodOrder/weixinOrders.do',{pageSize:pageSize,page:page},function(data){
								
									refresh(data);
								},'json'); */
							} else {
								classList.add('mui-disabled');
							}
						}
					} else if (classList.contains('mui-next')) {//next
						if (active) {
							var next = active.nextElementSibling;
							if (next && !next.classList.contains('mui-next')) {
								$.trigger(next.querySelector('a'), 'tap');
								//刷新页面
								//var page = next.children[0].innerHTML;
							/* 	mui.post('dining/foodOrder/weixinOrders.do',{pageSize:pageSize,page:page},function(data){
									
									refresh(data);
								},'json'); */
							} else {
								classList.add('mui-disabled');
							}
						}
					} else {//page
						var page = li.children[0].innerHTML;
						mui.post('dining/foodOrder/weixinOrders.do',{pageSize:pageSize,page:page},function(data){
							
							refresh(data);
						},'json');
						active.classList.remove('mui-active');
						classList.add('mui-active');
						var page = parseInt(this.innerText);
						var previousPageElement = li.parentNode.querySelector('.mui-previous');
						var nextPageElement = li.parentNode.querySelector('.mui-next');
						previousPageElement.classList.remove('mui-disabled');
						nextPageElement.classList.remove('mui-disabled');
						if (page <= 1) {
							previousPageElement.classList.add('mui-disabled');
						} else if (page >= pageNum) {
							nextPageElement.classList.add('mui-disabled');
						}
					}
				}
				
			});
		})(mui);
		//刷新页面数据
		function refresh(data){
			$('#meal').empty();
			var list = data.data;
			for(var i=0;i<list.length;i++){
				var meal = list[i].name;
				var mealdate = list[i].date;
				var count = list[i].count;
				var wdw = list[i].wdw;
				var  prepareNumber = list[i].preparenumber;
				var mealname = '';
				for(var n=0;n<mealtype.length;n++){
					if(mealtype[n].id==meal){
						mealname = mealtype[n].text;
					break;
					};
				}
				mealdate = mealdate.substr(0,mealdate.indexOf(" "));
				$('#meal').append('<li class="mui-table-view-cell mui-collapse"><a class="mui-navigate-right" href="#">'+mealdate+'>'+mealname+':'+count+'份,窗口号：'+wdw+',序号：'+prepareNumber+'</a><ul id="order'+i+'" class="mui-table-view mui-table-view-chevron"></ul></li>');
				//foods是list对象,{[{file:Array[]},{info:Object}],[],[]}
				var foods = list[i].food;
				for(var j = 0; j < foods.length; j++){
					var info = foods[j].info;
					var type = '';
					for(var m=0;m<foodtype.length;m++){
						if(foodtype[m].id==info.ftype){
							type = foodtype[m].text;
						break;
						};
					}
					$('#order'+i).append('<li class="mui-table-view-cell"><div class="mui-slider-cell"><div class="oa-contact-cell mui-table"><div class="oa-contact-avatar mui-table-cell"><ul id="receiptfiles'+i+j+'"></ul></div><div class="oa-contact-content mui-table-cell"><div class="mui-clearfix"><h4 class="oa-contact-name">'+info.name+'</h4><span class="oa-contact-position mui-h6">'+type+'</span></div><p class="oa-contact-email mui-h6">'+info.fdesc+'</p></div></div></div></li>');
					var file = foods[j].file;
					if(file!=undefined){
						var container = 'receiptfiles'+i+j;
						createFileView(file.id,file.fileName,false,container,true,function(fid){});
					}
				}
			}
		}
		
		//初始化我的订单
		mui.post('dining/foodOrder/weixinOrders.do',{pageSize:pageSize,page:1},function(data){refresh(data);},'json');
		//食堂订单统计
		var el = '';
		var chartData = [];
		mui.post('dining/foodOrder/typeOrder.do',{},function(data){
				if(data.success){
					var datas = data.data;
					var meal = data.dataMeal;
					var usedate = data.dataDate;
					var seriesName = (meal==0?'早餐':meal==1?'午餐':'晚餐')+' '+ usedate.substr(0,10);
					hchart(datas,seriesName);
					for(var i=0;i<datas.length;i++){
						if(datas[i][0]==0){
							el = {};
							el.name = '预定';
							el.value = datas[i][1];
							chartData.push(el);
						}else if(datas[i][0]==1){
							el = {};
							el.name = '备餐';
							el.value = datas[i][1];
							chartData.push(el);
						}else if(datas[i][0]==2){
							el = {};
							el.name = '就餐';
							el.value = datas[i][1];
							chartData.push(el);
						}else if(datas[i][0]==3){
							el = {};
							el.name = '离开';
							el.value = datas[i][1];
							chartData.push(el);
						}else if(datas[i][0]==-1){
							el = {};
							el.name = '取消';
							el.value = datas[i][1];
							chartData.push(el);
						}
					} 
					var getOption = function(chartType) {
						var chartOption = {
							calculable: false,
							series: [{
								name: '食堂就餐状态分布',
								type: 'pie',
								radius: '65%',
								center: ['50%', '50%'],
								data: chartData}]
						};
						return chartOption;
					};
					
					var pieChart = echarts.init(byId('pieChart'));
					pieChart.setOption(getOption('pie'));
				}else{
					$('#barChart').append('<h3>当前食堂未开放！</3>');
					$('#pieChart').append('<h3>当前食堂未开放！</3>');
				}
			},'json'
		);
		var byId = function(id) {
			return document.getElementById(id);
		};
		function hchart(data,seriesName) { 
			var chartCate = [];
			var chartData = [];
		for(var i=0;i<data.length;i++){
			if(data[i][0]==0){
				chartCate.push('预定');
				chartData.push(data[i][1]);
			}else if(data[i][0]==1){
				chartCate.push('备餐');
				chartData.push(data[i][1]);
			}else if(data[i][0]==2){
				chartCate.push('就餐');
				chartData.push(data[i][1]);
			}else if(data[i][0]==3){
				chartCate.push('离开');
				chartData.push(data[i][1]);
			}else if(data[i][0]==-1){
				chartCate.push('取消');
				chartData.push(data[i][1]);
			}
		}
		   var chart = {
		      type: 'bar'
		   };
		   var title = {
		      text: '当前食堂状态'   
		   };
		   var subtitle = {
		      text: 'Source: Wikipedia.org'  
		   };
		   var xAxis = {
		      categories: chartCate,
		      title: {
		         text: null
		      }
		   };
		   var yAxis = {
		      min: 0,
		      title: {
		         text: 'Population (单)',
		         align: 'high'
		      },
		      labels: {
		         overflow: 'justify'
		      }
		   };
		   var tooltip = {
		      valueSuffix: ' 单'
		   };
		   var plotOptions = {
		      bar: {
		         dataLabels: {
		            enabled: true
		         }
		      }
		   };
		   var legend = {
		      layout: 'vertical',
		      align: 'right',
		      verticalAlign: 'top',
		      x: -40,
		      y: 100,
		      floating: true,
		      borderWidth: 1,
		      backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
		      shadow: true
		   };
		   var credits = {
		      enabled: false
		   };
		   
		   var series= [{
		            name: seriesName,
		            data: chartData     
			    }
		   ];     
		      
		   var json = {};   
		   json.chart = chart; 
		   json.title = title;   
		   json.subtitle = subtitle; 
		   json.tooltip = tooltip;
		   json.xAxis = xAxis;
		   json.yAxis = yAxis;  
		   json.series = series;
		   json.plotOptions = plotOptions;
		   json.legend = legend;
		   json.credits = credits;
		   $('#barChart').highcharts(json);
		  
		}
		
		</script>
</html>