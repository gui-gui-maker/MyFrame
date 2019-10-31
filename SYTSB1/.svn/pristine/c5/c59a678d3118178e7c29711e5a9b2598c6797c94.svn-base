/**
* jQuery ChipCombobox 1.0
* 
* Author Mr.Dawn [QQ602468816] 
* 
*/
(function ($)
{
    var a=0;
    
    $.fn.ChipCombobox = function (options)
    {
    	var combobox=$(this);
    	var content=$("<div id='ChipCombobox'/>");
        var defaults = {
         id:'',
         icon:'k/kui/images/icons/16/application_view_tile.png',
         top:300,
         left:500,
         width:300,
         height:'auto',
         display:'none',
   		 background: 'yellow',
   		 fieldValue:'',
   		 fieldText:'',
   		 position:'absolute',
   		 textBobox:true,
   		 mult:true,
   		 setText:function(text){
   		 $("#select-temp-text-value").val(text);
   		 },
   		 show:function(text){
   		  $("#select-temp-text").css({display:'block'});//隐藏临时文本框
   		  combobox.show("normal");
   		  return opts;
   		 },
   		 onSelected:function(item){
   		 
   		 },
   		 data:null
 		 };   
 		 var opts = $.extend(defaults, options);
 		 $(this).addClass("l-box-select");
 		 $(this).addClass("l-box-select-absolute");
 		 $(this).html(' <div class="l-box-select-inner" style="overflow: auto;"> <table cellpadding="0" cellspacing="0" border="0" class="l-box-select-table l-table-checkbox"> </table> </div><div class="l-btn-nw-drop" id="select_data"></div>');
      
        var tbody='';
        var data=opts.data;
	    for(var i in data){
	    var tr='';
	    if(opts.mult){
	      tr='<tr value="'+data[i].id+'" >'+
            '<td style="width:18px;" index="'+i+'" value="'+data[i].id+'" text="'+data[i].text+'">'+
            '<div class="l-checkbox-wrapper">'+
            ' <a class="l-checkbox"></a>'+
            '<input type="checkbox" class="l-hidden" />'+
            '</div></td>'+
            '<td index="'+i+'" value="'+data[i].id+'" align="left">'+data[i].text+'</td>'+
            '</tr>';
	    }else{
	      tr='<tr value="'+data[i].id+'" text="'+data[i].text+'" class="choose-item">'+
	 	'<td style="width:18px;" index="'+i+'" value="'+data[i].id+'" text="'+data[i].text+'">'+
            ' <a  style="height:16px;width:16px;background-image:url('+opts.icon+');background-repeat:no-repeat;">&nbsp;&nbsp;&nbsp;&nbsp;</a>'+
            '</td>'+
            '<td index="'+i+'" value="'+data[i].id+'" align="left">'+data[i].text+'</td>'+
            '</tr>';
	    }
            tbody=tbody+tr;
	      }
         $(this).find("table").html(tbody);
         /************选择*************/
         combobox.find(".l-checkbox").click(function(){
	 
	 if($(this).hasClass("l-checkbox-checked")){
	    $(this).css({backgroundPositionX:-13,backgroundPositionY:0});
	    this.className='l-checkbox';
	  }else{
	   //var value=$(this).parent().parent().attr('value');
	   //var text=$(this).parent().parent().attr('text');
	    this.className='l-checkbox l-checkbox-checked';
	    $(this).css({backgroundPositionX:-13,backgroundPositionY:-13});
	   
	  }
	  //选择后及时给文本框赋值
	    var texts=[];
	    var items=[];
	    var ids=[];
		combobox.find(".l-checkbox-checked").each(function(i,item){
			var value=$(item).parent().parent().attr('value');
	        var text=$(item).parent().parent().attr('text');
	        var m={'id':value,'text':text};
	        texts.push(text);
	        ids.push(value);
	        items.push(m);
		  });
		
		  opts.onSelected(items,ids,texts);
	    $("#select-temp-text-value").val(texts);
	 
	});
	
	/***********设置鼠标滑入样式**************/
	 combobox.find(".l-checkbox").mouseover(function(){
	  if($(this).hasClass("l-checkbox-checked")){
	    $(this).css({backgroundPositionX:-13,backgroundPositionY:-13});
	  }else{
	    $(this).css({backgroundPositionX:-13,backgroundPositionY:0});
	  }
	});
	/************设置鼠标滑出样式*************/
	 combobox.find(".l-checkbox").mouseout(function(){
	 if($(this).hasClass("l-checkbox-checked")){
	    $(this).css({backgroundPositionX:0,backgroundPositionY:-13});
	  }else{
	   $(this).css({backgroundPositionX:0,backgroundPositionY:0});
	  }
	 
	});
	/********************************/
	var textbox='<div class="l-grid-editor"  id="select-temp-text">'+
   '<div class="l-text-wrapper"><div class="l-text" style="height: 21px; padding: 0px;">'+
    '<input type="text" class="l-text-editing l-text-field"  style="height: 19px;"  id="select-temp-text-value"/>'+
     '<div class="l-text-l"></div>'+
     '<div class="l-text-r"></div>'+
     ' </div> </div> </div>';
   $("#select-temp-text").remove();
	if(opts.textBobox){
		$(textbox).insertAfter($(this));
		var h = $("#select-temp-text").css("height");
		$("#select-temp-text").css({position:opts.position,width: opts.width,left: opts.left,top: opts.top,display: opts.display, height: opts.height});
		$(this).css({position:opts.position,width: opts.width,left: opts.left,top: opts.top+parseInt(h),display: opts.display, height: opts.height});
		
	}
	
	/***********单选选择事件************/
	combobox.find(".choose-item").click(function(){
	 	 var value=$(this).attr('value');
	     var text=$(this).attr('text');
	     combobox.hide("normal");
	     //给临时文本框内赋值
	     $("#select-temp-text").css({display:'none'});
	     $("#select-temp-text-value").val(text);
	     
	     var item={'id':value,'text':text};
	     opts.onSelected(item);
	 });
	 
	 /**********************控制选择面板的消失*************************/
	$("body").click(function(){
	
	var ev = ev || window.event;
    var sx = ev.clientX + document.body.scrollLeft - document.body.clientLeft;  
    var ey = ev.clientY + document.body.scrollTop - document.body.clientTop;
    var height=parseInt(combobox.css("height"));
    var widht=parseInt(combobox.css("width"));
    var top = combobox.offset().top;
	var left=combobox.offset().left;
    
	if((sx>=(left))&&(sx<=(widht+left))&&(ey>=top)&&(ey<=(height+top))){
	
	}else{
		combobox.hide("normal");
		$("#select-temp-text").css({display:'none'});//隐藏临时文本框
	}
	    var data=[];
	    var texts=[];
	    var values=[];
	    
		combobox.find(".l-checkbox-checked").each(function(i,item){
			var value=$(item).parent().parent().attr('value');
	        var text=$(item).parent().parent().attr('text');
	        data.push({'text':text,'value':value});
	        texts.push(text);
	        values.push(value);
		  });
		
	     $("#select-temp-text-value").val(texts);
		 
	});
	
	
	return opts;
    };

})(jQuery);