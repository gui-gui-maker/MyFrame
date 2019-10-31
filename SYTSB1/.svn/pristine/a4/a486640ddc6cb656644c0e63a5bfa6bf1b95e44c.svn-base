<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-list.jsp" %>
<title></title>
<script test="text/javascript">
$(function () {//jQuery页面载入事件
	$.getJSON('pub/syspersonmenu/getUserResources.do',function(data){
		for(var i in data){
			//一级菜单
			var ul="";
			if(data[i].children)
			{
				//二级菜单
				var li="";
				for(var j in data[i].children)
				{
					var divdetail="";
					if(data[i].children[j].children)
					{
						//三级菜单
						for(var k in data[i].children[j].children){
							var tt="";
							if(!data[i].children[j].children[k].children){
								var show ="";
								if(data[i].children[j].children[k].ispermenu){
									show =  "<P class='name'>"+data[i].children[j].children[k].text+"</P>"+
									   "<a class='btn'>已添加</a>"
								}else{
									show =  "<P class='name'>"+data[i].children[j].children[k].text+"</P>"+
									   "<a style='cursor:pointer' class='btn' resid='"+data[i].children[j].children[k].id+"' onclick='addmenu(this)' >添加</a>"
								}
								var image = data[i].children[j].children[k].image32;
								if(!image){
									image = "k/kui/images/icons/32/resources.png";
								}
								
								divdetail+="<div class='menu-setting-user-a'>"+
								   "<div class='lf'><b class='js-component-icon gApps-productIco nui-ico ' >"+
								   "<img src='"+image+"'></b><p class='gB'>"+data[i].children[j].children[k].text+"</p></div>"+
								   "<div class='menu-setting-user-a-hover'>"+
								   show+
								   "</div></div>";
							}else{
								//四级菜单以及上面的菜单递归添加到divdetail里面
							    cd = add4(tt,data[i].children[j].children[k]);
								divdetail+=cd;
							}
						}
						li+="<li class='No1'>"+
							"<b class='NO-icon'><img src='k/kui/skins/default/images/basic/No1_icon.png' width='10' height='18' /></b>"+
							"<table width='100%' border='0' cellpadding='0' cellspacing='0'>"+
							"<tr>"+"<td class='names' valign='middle'><span>"+data[i].children[j].text+"</span></td>"+
							"<td class='menu-setting-user-cts'>"+
							divdetail+"</td></tr></table></li>"
					}else
					{
						var show ="";
						if(data[i].children[j].ispermenu){
							show =  "<P class='name'>"+data[i].children[j].text+"</P>"+
							   "<a class='btn'>已添加</a>"
						}else{
							show =  "<P class='name'>"+data[i].children[j].text+"</P>"+
							   "<a class='btn' style='cursor:pointer' resid='"+data[i].children[j].id+"' onclick='addmenu(this)'>添加</a>"
						}
						//没有三层菜单
						divdetail+="<div class='menu-setting-user-a'>"+
						   "<div class='lf'><b class='js-component-icon gApps-productIco nui-ico ' >"+
						   "<img src='"+data[i].children[j].image32+"'></b><p class='gB'>"+data[i].children[j].text+"</p></div>"+
						   "<div class='menu-setting-user-a-hover'>"+
						   show+
						   "</div></div>";
						li+="<li class='No1'>"+
						"<b class='NO-icon'><img src='k/kui/skins/default/images/basic/No1_icon.png' width='10' height='18' /></b>"+
						"<table width='100%' border='0' cellpadding='0' cellspacing='0'>"+
						"<tr>"+"<td class='names' valign='middle'><span></span></td>"+
						"<td class='menu-setting-user-cts'>"+
						divdetail+"</td></tr></table></li>"
					}
				}
				ul="<ul>"+li+"</ul>";
			}
			$("#slider1").append("<div class='s-menu-settings-user'>"
					 +"<h2 class='h2-click' ><em></em><span>"+data[i].text+"</span><a class='s-menu-settings-user-down'  href='#'></a></h2>"
					 +"<div class='s-menu-setting-user-ct'>"
					 +ul
					 +"</div>"
				     +"</div>"
	    	);
		}
		
		$('.menu-setting-user-a-hover').hide();
		$(".s-menu-setting-user-ct").show();
		$('.h2-click').toggle(function(){
			$(this).next("div.s-menu-setting-user-ct").hide();
			$(this).children("a").removeClass('menu-settings-user-down');
			$(this).children("a").addClass('menu-settings-user-up');
		},function(){
			$(this).next("div.s-menu-setting-user-ct").show();
			$(this).children("a").removeClass('menu-settings-user-up');
			$(this).children("a").addClass('menu-settings-user-down');
			
		});
		$('.menu-setting-user-a').hover(function(){
				$(this).find('.menu-setting-user-a-hover').stop().animate({top:"0px"});
				$(this).find('.menu-setting-user-a-hover').show();
			},function(){
				$(this).find('.menu-setting-user-a-hover').stop().animate({top:"105px"});
				$(this).find('.menu-setting-user-a-hover').hide();
		})
	})
	//pageTitle({to:"#title",text:"快捷菜单设置",note:"",icon:"k/kui/images/icons/32/s-menu.png",show:true});
});

function add4(obj,data){
	if(data!=null&&data.children)
	{
		for(var i in data.children)
		{
			obj+=add4(obj,data.children[i])
		}
	}
	else{
		if(data!=null){
			var show ="";
			if(data.ispermenu){
				show =  "<P class='name'>"+data.text+"</P>"+
				   "<a class='btn'>已添加</a>"
			}else{
				show =  "<P class='name'>"+data.text+"</P>"+
				   "<a class='btn' style='cursor:pointer' resid='"+data.id+"' onclick='addmenu(this)'>添加</a>"
			}
			var image = data.image32;
			if(!image){
				image = "k/kui/images/icons/32/resources.png";
			}
			obj="<div class='menu-setting-user-a'>"+
			   "<div class='lf'><b class='js-component-icon gApps-productIco nui-ico ' >"+
			   "<img src='"+image+"'></b><p class='gB'>"+data.text+"</p></div>"+
			   "<div class='menu-setting-user-a-hover'>"+
			   show+
			   "</div></div>";
		}
	}
	return obj
}

function addmenu(obj){
	
	$.getJSON("pub/syspersonmenu/addmenu.do",{resourceId:$(obj).attr('resid')},function(res){
		if(res.success){
			//变换样式
			$(obj).parent().find('.btn').text("已添加");
			$(obj).parent().find('.btn').removeAttr('style').removeAttr('onclick')
			//$(obj).parent().append("<a class='open-menu-icon' style='cursor:pointer' title='删除' resid='"+$(obj).attr('resid')+"' onclick='delmenu(this)'><img src='k/kui/skins/default/images/basic/menu_open_icon.png' width='16' height='16' /></a>");
			var data={};
			data["resId"]=$(obj).attr('resid');
			data["resName"]=$(obj).parent().find('.name').text();
			data["resImage"]= $(obj).parent().parent().find('img').attr('src');
			data["resUrl"]=res.data.resource.url
			api.data.window.addmenu(data)
		}
	})
}

function delmenu(obj){
	$.ligerDialog.confirm("确定删除?",function(yes){
		if(yes){
			$.getJSON("pub/syspersonmenu/deletemenu.do",{resourceId:$(obj).attr('resid')},function(res){
				if(res.success){
					//变换样式
					$(obj).parent().find('.btn').text('添加');
					$(obj).parent().find('.btn').attr('style','cursor:pointer').attr('onclick','addmenu(this)');
					$(obj).remove();
					api.data.window.delmenu($(obj).attr('resid'))
				}
			})
		}
	})
}
</script>
</head>
<body>

<div class="item-tm">
	<div id="title" style=""></div>
</div>
	
<div class="scroll-tm">
	<div class="s-menu_settings" id="slider1"> 
	</div>
</div>
</body>
</html>