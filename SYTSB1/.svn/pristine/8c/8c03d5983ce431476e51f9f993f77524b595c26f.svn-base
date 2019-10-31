<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
	<title>四川省特检院就餐系统</title>
	<%@include file="/k/kui-base-form.jsp" %>
	<link rel="stylesheet" type="text/css" href="app/fwxm/dining/jquery-easyui-1.5.1/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="app/fwxm/dining/jquery-easyui-1.5.1/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="app/fwxm/dining/jquery-easyui-1.5.1/demo/demo.css">
	<script type="text/javascript" src="app/fwxm/dining/jquery-easyui-1.5.1/jquery.min.js"></script>
	<script type="text/javascript" src="app/fwxm/dining/jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="app/fwxm/dining/jquery-easyui-1.5.1/easy.tools.js"></script>
	
	<style type = "text/css">
	/*菜单css*/
		    body{ background:url(app/fwxm/dining/jquery-easyui-1.5.1/themes/icons/cy_bg.jpg) no-repeat ; background-size: cover;  }
		/*布局css*/
		#cc {
			width:900px;
			height:600px;
			-moz-box-shadow:0 4px 15px rgba(0, 0, 0, .35);
		    -webkit-box-shadow:0 4px 15px rgba(0, 0, 0, .35);
		    box-shadow:0 4px 15px rgba(0, 0, 0, .35); 
		}
		#north {
			height:50px;
		}
		#north div.msg-box{
			height:40px;
			width:350px;
			line-height:40px;
			margin-left:5px;
			float:left;
		}
		#north div.btn-box{
			width:150px;
			padding:10px 20px 0 0;
			float:right
		}
		.msg-box input.cardNo{ width:200px; }
		
		#center {
			height:520px;
		}
		
		
		
.food{
		width:160px;
		height:200px;
		border:1px solid red;
		float:left;
		margin:10px 10px;
		
	}
	
.food div.img{
		width:160px;
		height:160px;
		background-color:yellowgreen;
	}
.food div.img img{
		height:156px;
		width:156px;
		margin:2px;
	}
.food div.chk{
		width:160px;
		height:40px;
	}
.food div.chk .chk1{
		float:left;
		width:160px;
		height:30px;
		margin:10px 0 0 10px;
	}
	
	#north div.msg-box,#north div.btn-box,.l-btn-icon-left .l-btn-text{
		font-size:14px;
		font-weight:bold;
	}

	</style>
</head>
<body>
<div id="container" style="width:900px;margin:0 auto;padding:0;">
	<h1 style="text-align:center;color:#fff;">四川省特检院就餐系统</h1>
	<div id="cc" class="easyui-layout" >
		<div id="north" data-options="region:'north',split:true">
			<div class="msg-box">刷卡: 
				<span><input type="text" id="cardNo" name="cardNo" /></span>
				<span id="card"></span>
			</div>
		    <div class="msg-box">领餐窗口:<span id="wdw"></span></div>
		    <div class="btn-box">
				<a href="javascript:void(0)" onclick="clr();" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">清除</a>
			</div>
		</div>
		<div id="center" data-options="region:'center',split:true" style="padding:20px;" >
			<div id="p" class="easyui-panel" title="订单信息" style="width:100%;height:100%;padding:10px;">
		        <p style="font-size:14px">欢迎你，<span id="name"></span>。当前是：<span id="date"></span>日,<span id="meal"></span>时间。你的菜单如下，共<span id="count"></span>份，<span id="window"></span ></p>
		        <div id="foods">
		           
		        </div>
		    </div>
		</div>
	</div>
</div>
	<script type="text/javascript">
		$(function(){
			$('#cc').layout();
			$('#cardNo').focus();
			$('#cardNo').bind('input propertychange', function(event) {
				if($(this).val().length>=10){
					$('#card').html($(this).val());
					swipe($(this).val());
					$('#cardNo').val("").focus();
				}
			});
			
			var date = new Date();
			var time = date.getHours();
			var meal = 0;
			if(time<10){
				meal = 0;
			}else if(time>=10&&time<15){
				meal = 1;
			}else{
				meal = 2;
			}
			var date_string = date.Format("yyyy-MM-dd");
			$("#date").html(date_string);
			$("#meal").html(meal==0?'早餐':meal==1?'午餐':'晚餐');
			$('#p').panel('close');
		});	
		function clr(){
			  $('#cc').layout('panel','north').find('span#wdw').html('');
			  $('#cc').layout('panel','north').find('span#card').html('');
			  $('#p').panel('close');
			  $('#cardNo').val("").focus();
		}
		 function swipe(cardNo){
			  if(cardNo){
				$.ajax({
					  type: 'POST',
					  url: "dining/foodOrder/getNotGetOrderByCard.do", 
					  data: {"cardNo":cardNo},
					  success: function(data){
						  console.log(data);
						  if(data.success){
							  var order = data.order;
							  if(order){
								  setContent(data);
							  }else{
								   $.messager.show({
						                title:'提示',
						                msg:'对不起，未查询到你的订单...',
						                timeout:1500,
						                showType:'fade', 
						                style:{
						                    right:'',
						                    bottom:''
						                }
						            });  
							  }
							 
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
		} 
		function setContent(data){
			var order = data.order;
			var foods = data.foods;
			$("#name").html(order.order_man);
			
			$("#count").html(order.count);
			var win = order.wdw;
			$("#wdw").html(win);
			var mes = (win==0?"请稍等，稍后会有微信信息通知你的领餐窗口！":"请到"+win+"号窗口领取你的美食。");
			$("#window").html(mes);
			$('#foods .food').remove();
			$.each(foods,function(i,f){
				var path = f.pic_path;
				if(path){
					path = path.replace(/\\/g,"/");
				}else{
					path='';
				}
				$('#foods').append('<div class="food">'+
										'<div class="img">'+
											'<img src="upload/'+path+'" alt="美食" />'+
										'</div>'+
										'<div class="chk">'+
											'<div class="chk1" >'+
												'<label>'+f.food+'</label>'+
											'</div>'+
										'</div>'+
									'</div>');
			});
			$('#p').panel('open');
			setTimeout("$('#p').panel('close');",10000000);
		}
		
		
		Date.prototype.Format = function (fmt) { //author: meizz 
            var o = {
                "M+": this.getMonth() + 1, //月份 
                "d+": this.getDate(), //日 
                "h+": this.getHours(), //小时 
                "m+": this.getMinutes(), //分 
                "s+": this.getSeconds(), //秒 
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
                "S": this.getMilliseconds() //毫秒 
            };
            if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return fmt;
        }
	</script>
</body>
</html>