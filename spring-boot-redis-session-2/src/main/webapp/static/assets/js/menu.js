var menus = null;
	$(function(){
		$.post("/sr/findResource",{},function(res){
			if(res.success){
				addMenu($("#mainMenu"),res.data);
				menus = res.data;
			}else{
				
			}
		});
	});
	
	function addMenu($ul,item){
		$.each(item,function(i){
			if(item[i].children&&item[i].children.length>0){
				var li = $("<li id='"+item[i].id+"'></li>");
				li.appendTo($ul[0]);
				li.append("<a href='javascript:void(0);' class='dropdown-toggle'><i class='menu-icon fa fa-list'></i>"
				+"<span class='menu-text'>"+ item[i].name +"</span><b class='arrow fa fa-angle-down'></b></a><b class='arrow'></b>");
				
				var subUl = $("<ul class='submenu'></ul>");
				subUl.appendTo(li[0]);
				
				//递归
				var submenu = item[i].children;
				addMenu(subUl,submenu);
			}else{
				$ul.append("<li id='"+item[i].id+"'><a href='"+item[i].url+"'><i class='menu-icon fa fa-tachometer'></i><span class='menu-text'>"+ item[i].name +"</span></a><b class='arrow'></b></li>")
			}
		});
	}
	
