<%@ page import="com.khnt.security.util.SecurityUtil" %>
<%@ page import="com.khnt.security.CurrentSessionUser" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    CurrentSessionUser user = SecurityUtil.getSecurityUser();
    String userid = user.getId();
%>

<!DOCTYPE html>
<html lang="en">
<%@include file="/k/kui-base.jsp"%>
<head>
    <meta charset="UTF-8">
    <title>画图工具界面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="css/public.css">
    <link rel="stylesheet" href="css/index.css">
    <script src="js/jquery.min.js"></script>
   
</head>
<body>
<!-- 填充颜色 -->
<input type="hidden" id="fillColor"  value="white" />
<!-- 线条颜色 -->
<input type="hidden" id="strokeColor" value="black"  />
<!-- 描边线宽-->
<input type="hidden" id="strokeWidth" value="1"  />
<!-- 线条类型-->
<input type="hidden" id="lineDashType" value=""  />
<!-- 文字大小-->
<input type="hidden" id="fontSize" value="14"  />
<!-- 字体-->
<input type="hidden" id="fontType" value="April"  />


<div id="web">
    <div id="header">
        <div class="open">
            打开
        </div>
        <div class="toolbar">
            <ul>
                <li class="shu"><a href="javascript:newPage()" title="新建"><img src="images/toolbar1_03.png"></a></li>
                <li><a href="javascript:save()" title="保存"><img src="images/toolbar2_03.png"></a></li>
                <li class="shu"><a href="javascript:"><img src="images/toolbar3_03.png"></a></li>
                <li><a href="javascript:"><img src="images/toolbar4_03.png"></a></li>
                <li class="shu"><a href="javascript:"><img src="images/toolbar5_03.png"></a></li>
                <li><a href="javascript:"><img src="images/toolbar6_03.png"></a></li>
                <li class="shu"><a href="javascript:"><img src="images/toolbar7_03.png"></a></li>
<!--                 <li><a href="javascript:""><img src="images/toolbar8_03.png"></a></li> -->
<!--                 <li><a href="javascript:"><img src="images/toolbar9_03.png"></a></li> -->
<!--                 <li class="shu"><a href="javascript:"><img src="images/toolbar10_03.png"></a></li> -->
                <li><a href="javascript:leftAlign();" title="左对齐"><img src="images/toolbar11_03.png"></a></li>
                <li><a href="javascript:horizontallyAlign();" title="左右居中"><img src="images/toolbar12_03.png"></a></li>
                <li class="shu"><a href="javascript:rightAlign();"  title="右对齐"><img src="images/toolbar13_03.png"></a></li>
                <li><a href="javascript:topAlign()" title="上对齐"><img src="images/toolbar14_03.png"></a></li>
                <li><a href="javascript:verticallyAlign()" title="上下居中"><img src="images/toolbar15_03.png"></a></li>
                <li class="shu"><a href="javascript:bottomAlign()" title="下对齐"><img src="images/toolbar16_03.png"></a></li>
                <li><a href="javascript:horizontallyAvg();" title="横向分布"><img src="images/toolbar17_03.png"></a></li>
                <li class="shu"><a href="javascript:verticallyAvg();" title="纵向分布"><img src="images/toolbar18_03.png"></a></li>
                <li><a href="javascript:moveUp()" title="上移一层"><img src="images/toolbar19_03.png"></a></li>
                <li><a href="javascript:moveDown()" title="下移一层"><img src="images/toolbar20_03.png"></a></li>
                <li><a href="javascript:moveTop()" title="置顶"><img src="images/toolbar19_03.png"></a></li>
                <li class="shu"><a href="javascript:moveBottom()" title="置底"><img src="images/toolbar20_03.png"></a></li>
                <li class="liu">
                    <a href="javascript:"><img src="images/toolbar21_03.png"></a>
                    <ul class="mune">
                        <li class="xi" ><div></div></li>
                        <li class="zong" ><div></div></li>
                        <li class="cu" ><div></div></li>
                        <li class="dacu" ><div></div></li>
                    </ul>
                </li>
                <li class="liu"><a href="javascript:"><img src="images/toolbar22_03.png"></a></li>
                <li class="shu liu"><a href="javascript:"><img src="images/toolbar23_03.png"></a></li>
<!--                 <li><a href="javascript:"><img src="images/toolbar24_03.png"></a></li> -->
<!--                 <li><a href="javascript:"><img src="images/toolbar25_03.png"></a></li> -->
				 <li><a href="javascript:group();">组合</a></li>
				 <li><a href="javascript:cancelGroup();">取消组合</a></li>
            </ul>
        </div>
        <div class="out">
            <a href="#"><img src="images/out1_03.png"></a>
            <a class="shez" href="#">页面设置</a>
            <a href="#"><img src="images/out2_03.png"></a>
        </div>
        <!--页面设置弹出框    -->
        <div class="tanchu">
            <label>宽度</label>
            <input type="text" placeholder="请输入需要设置的宽度"><br>
            <label>高度</label>
            <input type="text" placeholder="请输入需要设置的高度">
        </div>
    </div>
    <div id="content">
        <div class="dianjileft">
            <img src="images/dleft_03.png">
        </div>
        <div class="dianjiright">
            <img src="images/dright_07.png">
        </div>
        <div class="left">
            <div class="search">
                <div class="searchleft">
                    <select>
                        <option>全部</option>
                        <option>种类1</option>
                        <option>种类2</option>
                        <option>种类3</option>
                    </select>
                </div>
                <div class="searchright">
                    <input type="text" placeholder="请输入组件名称">
                </div>
            </div>
            <div class="classification">
                <div class="yj">
                    <img src="images/zhankai_03.png">
                    基础原件
                </div>
                <div class="yjnr">
                    <ul>
                        <li id="handButton" class="active" onclick="hand();"><img src="images/gj_03.png"><p>选择工具</p></li>
                        <li onclick="drawRect();"><img src="images/gj_05.png"><p>矩形</p></li>
                        <li onclick="drawCircle()"><img src="images/gj_08.png"><p>圆形</p></li>
                        <li onclick="drawEllipse()"><img src="images/gj_08.png"><p>椭圆形</p></li>
<!--                         <li onclick="alert('建设中...')"><img src="images/gj_13.png"><p>圆角矩形</p></li> -->
                        <li onclick="drawPen()"><img src="images/gj_18.png"><p>铅笔工具</p></li>
                        <li onclick="drawLine()"><img src="images/gj_15.png"><p>直线工具</p></li>
                        <li onclick="alert('建设中...')"><img src="images/gj_27.png"><p>箭头工具</p></li>
                        <li onclick="drawTriangle()"><img src="images/gj_24.png"><p>三角形</p></li>
                        <li onclick="alert('建设中...')"><img src="images/gj_25.png"><p>五角星</p></li>
                        <li onclick="alert('建设中...')"><img src="images/gj_31.png"><p>多边形工具</p></li>
                        <li onclick="writeText()"><img src="images/gj_32.png"><p>文本标签</p></li>
                        <li><img src="images/gj_35.png"><p>吸色工具</p></li>
                        <li><img src="images/gj_40.png"><p>颜色选择</p></li>
                        
                        
                        <div class="cl"></div>
                    </ul>
                </div>
            </div>
            <div class="classification1">
                <div class="yj1">
                    <img src="images/zhankai_03.png">
                   	自定义
                </div>
                <div class="yjnr1">
                    <ul id="person">
<!--                         <li class="active"><img src="images/gj_03.png"><p>选择工具</p></li> -->
<!--                         <li><img src="images/gj_05.png"><p>矩形1</p></li> -->
<!--                         <li><img src="images/gj_08.png"><p>椭圆形</p></li> -->
<!--                         <li><img src="images/gj_13.png"><p>圆角矩形</p></li> -->
<!--                         <li><img src="images/gj_15.png"><p>铅笔工具</p></li> -->
<!--                         <li><img src="images/gj_18.png"><p>直线工具</p></li> -->
<!--                         <li><img src="images/gj_24.png"><p>箭头工具</p></li> -->
<!--                         <li><img src="images/gj_25.png"><p>三角形</p></li> -->
<!--                         <li><img src="images/gj_27.png"><p>五角星</p></li> -->
<!--                         <li><img src="images/gj_31.png"><p>多边形工具</p></li> -->
<!--                         <li><img src="images/gj_32.png"><p>文本标签</p></li> -->
<!--                         <li><img src="images/gj_35.png"><p>吸色工具</p></li> -->
<!--                         <li><img src="images/gj_40.png"><p>颜色选择</p></li> -->
                        <div class="cl"></div>
                    </ul>
                </div>
            </div>
<!--             <div class="classification2"> -->
<!--                 <div class="yj2"> -->
<!--                     <img src="images/zhankai_03.png"> -->
<!--                     电梯原件 -->
<!--                 </div> -->
<!--                 <div class="yjnr2"> -->
<!--                     <ul> -->
<!--                         <li class="active"><img src="images/gj_03.png"><p>选择工具</p></li> -->
<!--                         <li><img src="images/gj_05.png"><p>矩形1</p></li> -->
<!--                         <li><img src="images/gj_08.png"><p>椭圆形</p></li> -->
<!--                         <li><img src="images/gj_13.png"><p>圆角矩形</p></li> -->
<!--                         <li><img src="images/gj_15.png"><p>铅笔工具</p></li> -->
<!--                         <li><img src="images/gj_18.png"><p>直线工具</p></li> -->
<!--                         <li><img src="images/gj_24.png"><p>箭头工具</p></li> -->
<!--                         <li><img src="images/gj_25.png"><p>三角形</p></li> -->
<!--                         <li><img src="images/gj_27.png"><p>五角星</p></li> -->
<!--                         <li><img src="images/gj_31.png"><p>多边形工具</p></li> -->
<!--                         <li><img src="images/gj_32.png"><p>文本标签</p></li> -->
<!--                         <li><img src="images/gj_35.png"><p>吸色工具</p></li> -->
<!--                         <li><img src="images/gj_40.png"><p>颜色选择</p></li> -->
<!--                         <div class="cl"></div> -->
<!--                     </ul> -->
<!--                 </div> -->
<!--             </div> -->
        </div>
        <div class="center">
            <canvas id="canvas" >
				您的浏览器不支持
			</canvas>		
        </div>
        <script >
	      //定义画布 =============================
			var canvas = document.getElementById("canvas");
	        var height=$(".center").height();
	        var width=$(".center").width();
	        canvas.height=height;
	        canvas.width=width;
	
			var cxt = canvas.getContext('2d'); 
        </script>
        <script src="js/RtJs.js"></script>
        <script >
        var rtDrawer = new RtDrawer(canvas);
		var rtLayer = new RtLayer(rtDrawer);		
		var rtDrawingLayer = new RtDrawingLayer(rtDrawer);
		
		
		</script >
        <div class="right" id="hideright">
      <!--        <div class="attribute">
                <p>基础属性</p>
                <span>宽:</span><input type="text"><span>高:</span><input type="text">
                <span class="xzhou">x:</span><input type="text"><span class="yzhou">y:</span><input type="text">
            </div> -->
            <div class="assembly">
                <div class="ss">
                    <p>组件列表</p>
                    <input type="text" placeholder="请输入组件名称">
                </div>
                <div class="assemblylist">
                    <div class="assemblyname">组件列表</div>
                    <div class="listT">
                        <ul id="components_list">
  <!--                         <li id="assembly1" class="activeTi"><a href="javascript:">图形框01(矩形)</a><img src="images/lvyan_03.png"></li> -->
<!--                             <li id="assembly2"><a href="javascript:">横截面02(椭圆形)</a><img src="images/lvyan_03.png"></li> -->
<!--                             <li id="assembly3"><a href="javascript:">顶部开关01(三角形)</a><img src="images/lvyan_03.png"></li> -->
                        </ul>
                    </div>
                </div>
            </div>
        </div>
         <script >
       
		var	$componentsList=$("#components_list");

		
		//刷新组件
		EventHub.on("componentsList.refresh", function () { 
			console.log("componentsList.refresh");
			$componentsList.html("");	
			var rtLayers=rtDrawer.layers;
			//debugger;
			//图层			
			for(var i=rtLayers.length-1;i>=0;i--){
				//元素层
				var rtLayer=rtLayers[i];
				if(!rtLayer.visiable){
					continue;
				}
				var rtPrimitives=rtLayer.primitives;				
				for(var j=rtPrimitives.length-1;j>=0;j--){
					var rtPrimitive=rtPrimitives[j];
					if(!rtPrimitive.visiable||rtPrimitive.del){
						continue;
					}
					var $li =$( " <li id=\"component_"+rtPrimitive.id+"\" ><a href=\"javascript:selectPrimitive('"+rtPrimitive.id+"');refreshClass('"+rtPrimitive.id+"');\">"+rtPrimitive.data.cname+"("+rtPrimitive.id+")</a></li> ");
					$componentsList.append($li);			
					if(rtPrimitive.type=="RtGroup"){
						var primitives=rtPrimitive.data.primitives;
						for(var z=primitives.length-1;z>=0;z--){
							var primitive=rtPrimitive.data.primitives[z];
							var $lis =$( " <li id=\"component_"+primitive.id+"\" ><a href=\"javascript:selectPrimitive('"+primitive.id+"');refreshClass('"+primitive.id+"');\">——"+primitive.data.cname+"("+primitive.id+")</a></li> ");
							$componentsList.append($lis);	
						}
							
					}
				}				
			}
		});
		function refreshClass(id){
			$('#components_list').find('li').each(function(){
				var cid="component_"+id;
				if($(this).attr("id")==cid){
					$(this).addClass("activeTi");					
				}else{
					$(this).removeClass("activeTi");
				}
			});
		}
		
		function getSelectResult(){
			return save();
    	}
	     </script >
    </div>
</div>
</body>
<script src="js/index.js"></script>
</html>