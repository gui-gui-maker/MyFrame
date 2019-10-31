<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head >
<base href="<%=basePath %>" />
<link rel="stylesheet" type="text/css" href="app/fwxm/dining/css3checkbox/style.css" />
<link rel="stylesheet" type="text/css" href="app/fwxm/dining/jquery-easyui-1.5.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="app/fwxm/dining/jquery-easyui-1.5.1/themes/icon.css">
<link rel="stylesheet" type="text/css" href="app/fwxm/dining/jquery-easyui-1.5.1/demo/demo.css">
<!--[if lte IE 8]>
<link href="ie8.css" rel="stylesheet" type="text/css"/>
<![endif]-->
<style type="text/css" >
	body{
		margin:0;
		padding:0;
	}
	#container{
		width:80%;
		margin:0 auto;
	}
	#container .header{
		border:1px solid #95B8E7;
	}
	#container .header .head{
		height:40px;
		line-height:40px;
	    font-size: 18px;
    	font-weight: bold;
	}
	#container .header .tip{
		height:15px;
		text-align:right;
		line-height:15px;
	}
	#container #p{
		height:160px;
	}
	#container #p h2{
		height:15px;
		line-height:15px;
	}
	#container #p div.filter{
		float:left;
		width:33%;
	}


	#container .foods{
		height:1000px;
		border:1px solid #95B8E7;
		border-button:none;
		padding:10px 10px;
		
	}
	#container .foods .food{
		width:200px;
		height:240px;
		border:1px solid red;
		float:left;
		margin:10px 10px;
		
	}
	#container .foods div.food div.img{
		width:200px;
		height:200px;
		background-color:yellowgreen;
	}
	#container .foods div.food div.img img{
		width:196px;
		height:196px;
		margin:2px;
	}
	#container .foods div.food div.chk{
		width:200px;
		height:40px;
	}
	#container .foods div.food div.chk .chk1{
		float:left;
		width:150px;
		height:30px;
		margin:10px 0 0 10px;
	}
	#container .foods div.food div.chk .chk2{
		 float:right;
		 width:30px;
		 height:30px;
		 margin:10px 10px 0 0;
	}
	

</style>
<script src="app/fwxm/dining/css3checkbox/modernizr.js"></script>
<!--[if IE]>
<script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
<![endif]-->
<script type="text/javascript" src="app/fwxm/dining/jquery-easyui-1.5.1/jquery.min.js"></script>
<script type="text/javascript" src="app/fwxm/dining/jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#ff').form({
		    url:"dining/foodOrder/saveOrder.do",
		    onSubmit: function(){
		    	var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.progress('close');	// hide progress bar while the form is invalid
				}
				var formData = $("#ff").form();
				console.log(formData);
				isValid = false;
		    	var objs = $(this).find("input[type='checkbox']").get();
		    	
		    	for(var j=0;j<objs.length;j++){
		    		//alert(objs[j]);
		    		//alert(objs[j].checked);
		    		if(objs[j].checked){
		    			isValid = true;
		    			break;
		    		}
		    	}
		    	if(!isValid){
		    		$.messager.progress('close');
		    		$.messager.show({
		                title:'提示',
		                msg:'请至少选择一个样菜品...',
		                timeout:1500,
		                showType:'fade', 
		                style:{
		                    right:'',
		                    bottom:''
		                }
		          	});  
		    	} 
				return isValid;
		    },
		    success:function(data){
		        $.messager.progress('close');
		        var jsonObject = $.parseJSON(data);
		      	//console.log(jsonObject);
		       if(jsonObject.success){
		    	   $("#ostatus").textbox("setValue","已定餐");
		    	   $.messager.show({
		                title:'提示',
		                msg:'预定成功',
		                timeout:1000,
		                showType:'fade', 
		                style:{
		                    right:'',
		                    bottom:''
		                }
		          	});  
		       }else{
		    	   $.messager.alert('提示',jsonObject["msg"],'info'); 
		       }
		    }
		});
		//初始化combo
		 $('#meal_name').combo({
             required:true,
             editable:false,
             label:'餐点:',
             labelPosition:'before'
         });
         $('#sp').appendTo($('#meal_name').combo('panel'));
         $('#sp input').click(function(){
             var v = $(this).val();
             var s = $(this).next('span').text();
             $('#meal_name').combo('setValue', v).combo('setText', s).combo('hidePanel');
         });
         //初始化textbox
         $('#ostatus').textbox({
             editable:false,
             readonly:true
         });

		//初始化值
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
		init(date_string,meal);
	});

	
</script>
</head>
<body>
	<div id="container">
		<form name="ff" id="ff" method="post" >
			<div class="header">
				<div class="head">你好，欢迎你订餐，当前餐点：<span id="s1"></span><span id="s2"></span></div>
				<div class="tip">小提示：订单备餐前你可以修改菜单。</div>
			</div>
	        <div id="p" class="easyui-panel" title="信息栏" style="width:100%;height:200px;padding:10px;" data-options="collapsible:true">
	            <div class="filter f1">
					<h2>订餐情况</h2>
				    <p>订餐信息，含份数、状态信息，菜单详情请看下方图片列表。</p>
				   <!--  <div style="margin:5px 0;"></div> -->
				    <div class="easyui-panel" style="width:100%;max-width:330px;padding:5px 60px;height:95px;">
					    <div style="margin-bottom:3px">
				            <input class="easyui-textbox" label="份数:" name="count" id="count" value="1" labelPosition="before" style="width:100%;" data-options="required:true,validType:['number','length[1,3]']">
				        </div>
				        <div style="margin-bottom:3px">
				          <input class="easyui-textbox" label="状态:" name="ostatus" id="ostatus" value="" labelPosition="before" style="width:100%;">
				        </div>
			        </div>
				</div>
				<div class="filter f2" >
					<h2>餐点</h2>
				    <p>你还可以在次选择你想要预定的餐点。</p>
				   <!--  <div style="margin:5px 0;"></div>  -->
				    <div class="easyui-panel" style="width:100%;max-width:330px;padding:5px 60px;height:95px;">
				        <div style="margin-bottom:3px">
				            <input id="use_time" name="use_time" class="easyui-datebox" label="日期:" labelPosition="before" style="width:100%;" data-options="formatter:myformatter,parser:myparser" data-options="required:true">
				        </div>
				         <div style="margin-bottom:3px">
				            <input id="meal_name" name="meal_name" style="width:100%;">
				        </div>
				    </div>
				</div>
				<div class="filter f3" >
					<h2>操作</h2>
				    <p>点击查看订单信息，保存可保存或修改订单。</p>
				    <div class="easyui-panel" style="width:100%;max-width:360px;padding:5px 60px;height:95px;">
				        <div style="margin-bottom:3px">
				            <a id="search" href="javascript:void(0);" class="easyui-linkbutton" onclick="chakan();" data-options="iconCls:'icon-search'">查看</a>
				        </div>
				         <div style="margin-bottom:3px">
				           <a id="save" href="javascript:void(0);" class="easyui-linkbutton" onclick="save();" data-options="iconCls:'icon-save'">保存</a>
				        </div>
				    </div>
				</div>
	        </div>
			<div id="foods" class="foods">
				
			</div> 
		</form>
	</div>
	<div id="sp">
    	<div style="line-height:22px;background:#fafafa;padding:5px;">餐点</div>
 			<div style="padding:10px">
                <input type="radio" name="lang" value="0"><span>早餐</span><br/>
	            <input type="radio" name="lang" value="1"><span>午餐</span><br/>
	            <input type="radio" name="lang" value="2"><span>晚餐</span><br/>
             </div>
		</div>
    </div>
</body>
<script type="text/javascript">
function chakan(){
	//alert("kkkkk");
	var use_time = $("#use_time").datebox('getValue');
	if(!use_time){
		$.messager.alert('提示','请选择日期!','info');
		return;
	}
	var meal_name = $("#meal_name").combo('getValue');
	if(!meal_name){
		$.messager.alert('提示','请选择餐点!','info');
		return;
	}
	init(use_time,meal_name);
}
function save(){
	$.messager.progress();
	$('#ff').submit();
}
function init(date,meal){
	$("#s1").html(date);
	$("#s2").html(meal==0?'早餐':meal==1?'午餐':'晚餐');
	//设置餐点
	setDate($("#use_time"),date);
	setMeal(meal);
	//处理当餐点的数据
	$.post("dining/foodOrder/initOrder.do",{date:date,meal:meal},function(res){
		if(res["success"]){
			var foods = res["pubms"];
			if(foods){
				$("#foods .food").remove();
				$.each(foods,function(i,item){
					var path = item.pic_path;
					if(path){
						path = path.replace(/\\/g,"/");
					}else{
						path='';
					}
					$("#foods").append('<div class="food">'+
									'<div class="img">'+
										'<img src="fileupload/previewImage.do?path='+path+'" alt="美食" />'+
									'</div>'+
									'<div class="chk">'+
										'<div class="chk1" >'+
											'<label>'+item.food+'</label>'+
										'</div>'+
										'<div class="chk2" >'+
											'<input type="checkbox"name="fpm_ids"  id="'+item.id+'" class="chk_1" value="'+item.id+'"/>'+
											'<label for="'+item.id+'"></label>'+
										'</div>'+
									'</div>'+
								'</div>');
				});
				
				//var height =  Math.ceil(foods.length/3)*380;
				// $("#foods").css('height',height+'px');
				 /*$("#foods .food").bind('click',function(e){
					var obj = $(this).find("input[type='checkbox']")[0];
					var flag = $(obj).attr('checked');
					var id = $(obj).attr('id');
					//alert(typeof flag!='undefined');
					//alert(id);
					if(typeof flag!='undefined'){
						$("#"+id).attr('checked',false);
					}else{
						$("#"+id).attr('checked',true);
					}
				}) */
			}else{
				//设置状态
				$("#ostatus").textbox("setValue","未定餐");
				//设置份数
				$("#count").textbox("setValue","1");
				//移除菜单
				$("#foods .food").remove();
				$.messager.show({
	                title:'提示',
	                msg:"对不起，该餐点还未发布菜单...",
	                showType:'show'
	            });
				
			}
			//如果已订餐，赋值
			var order = res["order"];
			if(order){
				//设置份数
				$("#count").textbox("setValue",order.count);
				//设置状态
				$("#ostatus").textbox("setValue","已定餐");
				//设置菜单
				setChecked(foods,order);
			}else{
				//设置状态
				$("#ostatus").textbox("setValue","未定餐");
				//设置份数
				$("#count").textbox("setValue","1");
			}
			
		}else{
			//设置状态
			$("#ostatus").textbox("setValue","未定餐");
			//设置份数
			$("#count").textbox("setValue","1");
			 $.messager.show({
	                title:'错误！',
	                msg:res["msg"],
	                showType:'show'
	            });
		}
	},'json');
	
}

        //set datebox value
        function setDate(obj,dateString){
        	obj.datebox('setValue', dateString);	
        } 
        //set combo value
        function setMeal(val){
        	//val = val+'';
        	var objs = $('#sp input').get();
        	for(var k=0;k<objs.length;k++){
        		if($(objs[k]).val()==val){
        			var v = $(objs[k]).val();
                    var s = $(objs[k]).next('span').text();
                    $('#meal_name').combo('setValue', v).combo('setText', s);
        		}
        	}
        }
        
        //set checkbox value
        function setChecked(foods,order){
        	var ids = order.fpm_ids.split(",");
        	$.each(foods,function(i,item){
        		var flag = $.inArray(item.id,ids);
        		if(flag != -1){
        			//选中
        			$("#"+item.id).attr("checked",true);
        		}
        	});
        }
        
        //时间格式
        function myformatter(date){
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
        }
        function myparser(s){
            if (!s) return new Date();
            var ss = (s.split('-'));
            var y = parseInt(ss[0],10);
            var m = parseInt(ss[1],10);
            var d = parseInt(ss[2],10);
            if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
                return new Date(y,m-1,d);
            } else {
                return new Date();
            }
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
        $.extend($.fn.textbox.defaults.rules, {  
            number : {  
                validator : function(value, param) {  
                    return /^[0-9]*$/.test(value);  
                },  
                message : "请输入数字"  
            },  
            chinese : {  
                validator : function(value, param) {  
                    var reg = /^[\u4e00-\u9fa5]+$/i;  
                    return reg.test(value);  
                },  
                message : "请输入中文"  
            },  
            checkLength: {  
                validator: function(value, param){  
                    return param[0] >= get_length(value);  
                },  
                message: '请输入最大{0}位字符'  
            },  
            specialCharacter: {  
                validator: function(value, param){  
                    var reg = new RegExp("[`~!@#$^&*()=|{}':;'\\[\\]<>~！@#￥……&*（）——|{}【】‘；：”“'、？]");  
                    return !reg.test(value);  
                },  
                message: '不允许输入特殊字符'  
            }
        });
    </script>
</html>
