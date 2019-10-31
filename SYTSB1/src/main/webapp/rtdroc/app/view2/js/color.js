$(function(){
	var $picker=$("#stroke_color_picker");
	var colorArrays=[
		['255,255,255','229,229,229','207,207,207','184,184,184','161,161,161','138,138,138','115,115,115','92,92,92','69,69,69','50,50,50','23,23,23','0,0,0'],
		[
			'255,204,204','255,230,204','255,255,204','230,255,204','204,255,204','204,255,230','204,255,255','204,229,255','204,204,255','229,204,255','255,204,255','255,204,230',
			'255,153,153','255,204,153','255,255,153','204,255,153','153,255,153','153,255,204','153,255,255','153,204,255','153,153,255','204,153,255','255,153,255','255,153,204',
			'255,102,102','255,179,102','255,255,102','179,255,102','102,255,102','102,255,179','102,255,255','102,178,255','102,102,255','178,102,255','255,102,255','255,102,179',
			'255,51,51','255,153,51','255,255,51','153,255,51','51,255,51','51,255,153','51,255,255','51,153,255','51,51,255','153,51,255','255,51,255','255,51,153',
			'255,0,0','255,128,0','255,255,0','128,255,0','0,255,0','0,255,128','0,255,255','0,127,255','0,0,255','127,0,255','255,0,255','255,0,128',
			'204,0,0','204,102,0','204,204,0','102,204,0','0,204,0','0,204,102','0,204,204','0,102,204','0,0,204','102,0,204','204,0,204','204,0,102',
			'153,0,0','153,76,0','153,153,0','77,153,0','0,153,0','0,153,77','0,153,153','0,76,153','0,0,153','76,0,153','153,0,153','153,0,77',
			'102,0,0','102,51,0','102,102,0','51,102,0','0,102,0','0,102,51','0,102,102','0,51,102','0,0,102','51,0,102','102,0,102','102,0,51',
			'51,0,0','51,26,0','51,51,0','26,51,0','0,51,0','0,51,26','0,51,51','0,25,51','0,0,51','25,0,51','51,0,51','51,0,26'
		]
	];
	
	for(var idx in colorArrays ){
		var colorArray=colorArrays[idx];
		var $color_items=$("<div class='color_items' ></div>");
		for(var i=0;i<colorArray.length;i++){
			$color_items.append("<div style=\"background-color: rgb("+colorArray[i]+");\" col=\""+colorArray[i]+"\" ></div>");
		}
		$color_items.append("<div class=\"clear\" ></div>");
		
		$picker.append($color_items);
	}
//	$picker.append("<div class=\"color_hex\"> # <input type=\"text\" id=\"tempStrokeColor\"> </div>");
	$picker.append("<div class=\"clear\" ></div>");
	
	$("#stroke_color_picker .color_items").on("click","div",function(){
		var rgbTxt=$(this).attr("col");
//		var color=colorRGB2Hex(rgbTxt);
//		$("#tempStrokeColor").val(color);
		document.getElementById("strokeColor").value="rgb("+rgbTxt+")";
		strokeColor();
	})
	/*填充样式*/
	$picker=$("#fill_color_picker");
	for(var idx in colorArrays ){
		var colorArray=colorArrays[idx];
		var $color_items=$("<div class='color_items' ></div>");
		for(var i=0;i<colorArray.length;i++){
			$color_items.append("<div style=\"background-color: rgb("+colorArray[i]+");\" col=\""+colorArray[i]+"\" ></div>");
		}
		$color_items.append("<div class=\"clear\" ></div>");
		
		$picker.append($color_items);
	}
//	$picker.append("<div class=\"color_hex\"> # <input type=\"text\" id=\"tempFillColor\"> </div>");
//	$picker.append("<input id=\"fillColorDegree\" type=\"range\" min=\"0\" max=\"1\" value=\"1\" step=\"0.1\">");
	$picker.append("<div class=\"clear\" ></div>");
	
 
//	 $('#fillColorDegree').on('input propertychange',function(){
//		 var degree=$(this).val();
//		 document.getElementById("fillDegree").value=degree;
//		 fillColorDegree();
//     })
//	
	$("#fill_color_picker .color_items").on("click","div",function(){
		var rgbTxt=$(this).attr("col");
//		var degree=$("#fillColorDegree").val();
//		var color=colorRGB2Hex(rgbTxt);
//		$("#tempFillColor").val(color);
		document.getElementById("fillColor").value="rgb("+rgbTxt+")";
		fillColor();
	})
	 
	 
});
function colorRGB2Hex(color) {
    var rgb = color.split(',');
    var r = parseInt(rgb[0].split('(')[1]);
    var g = parseInt(rgb[1]);
    var b = parseInt(rgb[2].split(')')[0]);
    var hex = "#" + ((1 << 24) + (r << 16) + (g << 8) + b).toString(16).slice(1);
    return hex;
 }
