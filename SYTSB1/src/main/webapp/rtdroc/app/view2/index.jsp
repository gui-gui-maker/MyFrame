<%@ page import="com.khnt.security.util.SecurityUtil" %>
<%@ page import="com.khnt.security.CurrentSessionUser" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
//     CurrentSessionUser user = SecurityUtil.getSecurityUser();
//     String userid = user.getId();
%>

<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="/k/kui-base.jsp"%>
<meta charset="UTF-8">
<title>画图工具界面</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="rtdroc/app/view2/css/public.css">
<link rel="stylesheet" href="rtdroc/app/view2/css/index.css">

<script src="rtdroc/app/view2/js/jquery.min.js"></script>
<script src="rtdroc/app/view2/js/jquery.minicolors.js"></script>
<link rel="stylesheet" href="rtdroc/app/view2/css/jquery.minicolors.css" />
<link rel="stylesheet" href="rtdroc/app/view2/css/dtree.css" type="text/css" />

<link rel="stylesheet" href="rtdroc/app/view2/css/color.css">
<script src="rtdroc/app/view2/js/color.js"></script>


<style type="text/css">
.aui-about-dow2 { opacity: 0; visibility: hidden; position: absolute; left: -50px; top: 110%; min-width: 189px; padding: 8px 10px; background: #fff; border-radius: 2px; z-index: 99; box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3); -webkit-transition: all 0.25s ease-in-out; -moz-transition: all 0.25s ease-in-out; -o-transition: all 0.25s ease-in-out; }
.drop-down2:hover .aui-about-dow2 { top: 100%;  opacity: 1; visibility: visible; -webkit-transition: all 0.25s ease-in-out; -moz-transition: all 0.25s ease-in-out; -o-transition: all 0.25s ease-in-out; filter: alpha(opacity=100);  }

</style>

<script>
    $(document).ready(function() {

        $('.setcolor').each(function() {
            $(this).minicolors({
                control: $(this).attr('data-control') || 'hue',
                defaultValue: $(this).attr('data-defaultValue') || '',
                inline: $(this).attr('data-inline') === 'true',
                letterCase: $(this).attr('data-letterCase') || 'lowercase',
                opacity: $(this).attr('data-opacity'),
                position: $(this).attr('data-position') || 'bottom left',
                change: function(hex, opacity) {
                    if (!hex)
                        return;
                    if (opacity)
                        hex += ', ' + opacity;
                    try {
                        console.log(hex);
                    } catch (e) {
                    }
                },
                theme: 'bootstrap'
            });

        });
    });
    
    $(function(){
		//加载组件
		loadComponents();
		
		//初始化页面
		var imageId="${param.imageId}";
		if(imageId&&imageId!="null"){					
			var drawData=getDrawDataByImgAttId(imageId);
			if(drawData){
				importPage(drawData);
				refreshComponents();
			}else{
				//以图片形式填充
				var oldImg=new Image();
				var path=kFrameConfig.base + "fileupload/download.do?id=" + imageId;
				oldImg.src=path;		
				oldImg.onload=function(){
// 					var rtImage=new RtImage(rtLayer);
					var rtImage1=new RtImage(rtLayer,new Point(0,0),true);
					rtImage1.draw(oldImg.src);
			    } 
			}
		}else{
			//初始化页面
			var fileId="${param.fileId}";
			if(fileId&&fileId!="null"){					
				var drawData=getDrawDataByAttId(fileId);
				if(drawData){
					importPage(drawData);
					refreshComponents();
				} 
			}
		}
		
		
	});
    
    function refreshAttribute(){
    	
    }
</script>
<script>
   
</script>

</head>
<body>

<!-- 传递的初始化图片id -->
<input type="hidden" id="imageId"  value="${param.imageId }" />

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


<!-- 文字靠左 靠右  靠中间-->
<input type="hidden" id="textAlign" value="start"  />

<!-- 开始编号-->
<input type="hidden" id="ruleStart" value="1"  />
<!-- 编号前缀-->
<input type="hidden" id="rulePrefix" value="F"  />
<!-- 编号形状类型-->
<input type="hidden" id="ruleShape" value="三角形"  />
<!-- 图片专用隐藏canvas 去掉网格-->
<div style="width:0px;height:0px;overflow:hidden;">
  <canvas id="imgCanvas"   ></canvas>
</div>
<div id="web">
	<div id="header">
		<div class="open"> 打开 </div>
		<div class="toolbar">
			<ul>
<!-- 				<li><a href="javascript:newPage()" title="新建"><img src="rtdroc/app/view2/images/toolbar1_03.png"></a></li> -->
				<li><a href="javascript:save()" title="保存"><img src="rtdroc/app/view2/images/toolbar2_03.png"></a></li>
<!-- 				<li class="shu"><a href="javascript:" title="另存为"><img src="rtdroc/app/view2/images/toolbar3_03.png"></a></li> -->
				<li><a href="javascript:undo();" title="撤销"><img src="rtdroc/app/view2/images/toolbar4_03.png"></a></li>
				<!--  class="shu disabled" -->
				<li class="shu"><a href="javascript:redo();" title="重做"><img src="rtdroc/app/view2/images/toolbar5_03.png"></a></li>
<!-- 				<li><a href="javascript:"><img src="rtdroc/app/view2/images/toolbar6_03.png"></a></li> -->
<!-- 				<li class="shu"><a href="javascript:"><img src="rtdroc/app/view2/images/toolbar7_03.png"></a></li> -->
<!-- 				<li><a ><img src="rtdroc/app/view2/images/toolbar8_03.png"></a></li> -->
<!-- 				<li><a ><img src="rtdroc/app/view2/images/toolbar9_03.png"></a></li> -->
<!-- 				<li class="shu"><a ><img src="rtdroc/app/view2/images/toolbar10_03.png"></a></li> -->
				<li><a href="javascript:leftAlign();" title="左对齐"><img src="rtdroc/app/view2/images/toolbar11_03.png"></a></li>
				<li><a href="javascript:horizontallyAlign();" title="左右居中"><img src="rtdroc/app/view2/images/toolbar12_03.png"></a></li>
				<li class="shu"><a href="javascript:rightAlign();"  title="右对齐"><img src="rtdroc/app/view2/images/toolbar13_03.png"></a></li>
				<li><a href="javascript:topAlign()" title="上对齐"><img src="rtdroc/app/view2/images/toolbar14_03.png"></a></li>
				<li><a href="javascript:verticallyAlign()" title="上下居中"><img src="rtdroc/app/view2/images/toolbar15_03.png"></a></li>
				<li class="shu"><a href="javascript:bottomAlign()" title="下对齐"><img src="rtdroc/app/view2/images/toolbar16_03.png"></a></li>
				<li><a href="javascript:horizontallyAvg();" title="横向分布"><img src="rtdroc/app/view2/images/toolbar17_03.png"></a></li>
				<li class="shu"><a href="javascript:verticallyAvg();" title="纵向分布"><img src="rtdroc/app/view2/images/toolbar18_03.png"></a></li>
				<li><a href="javascript:moveUp()" title="上移一层"><img src="rtdroc/app/view2/images/toolbar19_03.png"></a></li>
				<li><a href="javascript:moveDown()" title="下移一层"><img src="rtdroc/app/view2/images/toolbar20_03.png"></a></li>
				<li><a  href="javascript:moveTop()" title="置顶"><img src="rtdroc/app/view2/images/toolbar19_04.png"></a></li>
				<li class="shu"><a href="javascript:moveBottom()" title="置底"><img src="rtdroc/app/view2/images/toolbar20_04.png"></a></li>
				<li><a href="javascript:group();" title="组合"><img src="rtdroc/app/view2/images/toolbar26_03.png"></a></li>
				<li class="shu"><a href="javascript:cancelGroup();" title="取消组合"><img src="rtdroc/app/view2/images/toolbar26_04.png"></a></li>
				<li class="liu drop-down"><a href="javascript:void(0)"><img src="rtdroc/app/view2/images/toolbar21_03.png"></a>
				        <!-- 填充颜色  -->
				        <div class="aui-about-dow">
							<div id="fill_color_picker" class="color_picker"></div>
						</div>
				</li>
				
				<li class="liu drop-down"><a href="javascript:void(0)"><img src="rtdroc/app/view2/images/toolbar21_03.png"></a>
				         <!-- 线条颜色  -->
				        <div class="aui-about-dow">
							<div id="stroke_color_picker" class="color_picker"></div>
						</div>
				</li>

				
				
				<li class="liu drop-down"> <a href="javascript:void(0)"><img src="rtdroc/app/view2/images/toolbar22_03.png"></a>
					<ul class="aui-about-dow">
                        <li>
                            <a href="javascript:setStrokeWidth(1);">1px</a>
                        </li>
                        <li>
                            <a href="javascript:setStrokeWidth(2);">2px</a>
                        </li>
                        <li>
                            <a href="javascript:setStrokeWidth(3);">3px</a>
                        </li>
                        <li>
                            <a href="javascript:setStrokeWidth(4);">4px</a>
                        </li>
                        <li>
                            <a href="javascript:setStrokeWidth(5);">5px</a>
                        </li>
                        <li>
                            <a href="javascript:setStrokeWidth(6);">6px</a>
                        </li>
                        <li>
                            <a href="javascript:setStrokeWidth(7);">7px</a>
                        </li>
                        <li>
                            <a href="javascript:setStrokeWidth(8);">8px</a>
                        </li>
                    </ul>
				</li>
				
				<!-- shu liu disable -->
				<li class="liu drop-down"><a href="javascript:"><img src="rtdroc/app/view2/images/toolbar23_03.png"></a>
					<ul class="aui-about-dow">
	                        <li class="xi">
	                            <a href="javascript:setLineDashType();"><div></div></a>
	                        </li>
	                        <li>
	                            <a href="javascript:setLineDashType('2,1');">........................</a>
	                        </li>
	                        <li>
	                            <a href="javascript:setLineDashType('15,3');">— — — — — —</a>
	                        </li>
	                        <li>
	                            <a href="javascript:setLineDashType('25,8,4,8');">— - — - — - — -</a>
	                        </li>
	                         <li>
	                            <a href="javascript:setLineDashType('58,8,4,8');">—— - —— - —— -</a>
	                        </li>
	                    </ul>
				</li>
				<li><a href="javascript:locked()"><img src="rtdroc/app/view2/images/toolbar24_03.png"></a></li>
				<li><a href="javascript:unlocked()"><img src="rtdroc/app/view2/images/toolbar25_03.png"></a></li>
				<li><a href="javascript:copy()"><div style="color: white;font-size:14px;">复制</div></a></li>
				<li><a href="javascript:paste()"><div style="color: white;font-size:14px;">粘贴</div></a></li>
				<li><a href="javascript:del()"><div style="color: white;font-size:14px;">删除</div></a></li>
			</ul>
		</div>
<!-- 		<div class="out"> <a class="shez" href="#">页面设置</a> <a href="#" class="exit"><img src="rtdroc/app/view2/images/out2_03.png"></a> </div> -->
		<!--页面设置弹出框    -->
		<div class="tanchu">
			<label>宽度</label>
			<input type="text" placeholder="请输入需要设置的宽度">
			<br>
			<label>高度</label>
			<input type="text" placeholder="请输入需要设置的高度">
		</div>
	</div>
	<div id="content">
		<div class="dianjileft"> <img src="rtdroc/app/view2/images/dleft_03.png"> </div>
		<div class="dianjiright"> <img src="rtdroc/app/view2/images/dright_07.png"> </div>
		<div class="left">
		<!-- 
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
		 -->
			<div class="classification1" id="c1">
				<div class="yj1"> <img src="rtdroc/app/view2/images/zhankai_03.png"> 基础原件 </div>
				<div class="yjnr1">
					<ul>
						 <li id="handButton" class="active" onclick="hand();"><img src="rtdroc/app/view/images/gj_03.png"><p>选择工具</p></li>
                        <li onclick="drawRect();"><img src="rtdroc/app/view/images/gj_05.png"><p>矩形</p></li>
                        <li onclick="drawCircle()"><img src="rtdroc/app/view/images/gj_08.png"><p>圆形</p></li>
                        <li onclick="drawEllipse()"><img src="rtdroc/app/view/images/gj_08.png"><p>椭圆形</p></li>
<!--                         <li onclick="alert('建设中...')"><img src="rtdroc/app/view/images/gj_13.png"><p>圆角矩形</p></li> -->
                        <li onclick="drawPen()"><img src="rtdroc/app/view/images/gj_18.png"><p>铅笔工具</p></li>
                        <li onclick="drawLine()"><img src="rtdroc/app/view/images/gj_15.png"><p>直线工具</p></li>
                        <li onclick="drawArrow()"><img src="rtdroc/app/view/images/gj_27.png"><p>单向箭头</p></li>
                        <li onclick="drawArrowDouble()"><img src="rtdroc/app/view/images/gj_27.png"><p>双向箭头</p></li>
                        <li onclick="drawTriangle()"><img src="rtdroc/app/view/images/gj_24.png"><p>三角形</p></li>
<!--                         <li onclick="alert('建设中...')"><img src="rtdroc/app/view/images/gj_25.png"><p>五角星</p></li> -->
<!--                         <li onclick="alert('建设中...')"><img src="rtdroc/app/view/images/gj_31.png"><p>多边形工具</p></li> -->
 						<li onclick="writeText()"><img src="rtdroc/app/view/images/gj_35.png"><p>文本标签</p></li>
                        
                        <li onclick="alert('建设中...');"><img src="rtdroc/app/view/images/gj_32.png"><p>吸色工具</p></li>
                       
<!-- 						<li> -->
<!-- 							<div class="setcc"> -->
<!-- 								<img src="rtdroc/app/view2/images/scarrow.png"> -->
<!-- 								<input type="hidden" id="hidden-input" class="setcolor" value="#000"> -->
<!-- 							</div> -->
<!-- 								<p>颜色选择</p> -->
<!-- 						</li> -->
						<div class="cl"></div>
					</ul>
				</div>
			</div>
			<div class="classification2">
                <div class="yj2">
                    <img src="rtdroc/app/view/images/zhankai_03.png">
                                                                     规则组件
                </div>
                <div class="yjnr2">
                    <ul>
                            <li onclick="hitRule('x')">X<p>叉叉</p></li>
                        	<li onclick="hitRule('三角形')"><img src="rtdroc/app/view/images/gj_24.png"><p>三角形</p></li>
                        <div class="cl"></div>
                    </ul>
                </div>
            </div>
			 
			<div class="classification3"  id="c3">
				<div class="yj3"> <img src="rtdroc/app/view2/images/zhankai_03.png"> 自定义组件 </div>
				<div class="yjnr3">
					<ul id="custom_components">
						
						<div class="cl"></div>
					</ul>
				</div>
			</div>
			
		</div>
		
		<!-- 中间内容开始 -->
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
        <script src="rtdroc/app/view2/js/RtJs.js"></script>
        
        <script >
        var rtDrawer = new RtDrawer(canvas);
		var rtLayer = new RtLayer(rtDrawer);		
		var rtDrawingLayer = new RtDrawingLayer(rtDrawer);
		</script >
		<!-- 中间内容结束 -->
		
		<div class="right" id="hideright">
		<!--
			<div class="attribute">
				<p>基础属性</p>
				<ul>
					<li>
						<span>ID:</span>
						<input type="text" value="444411" style="width: 164px" />
					</li>
					
					<li>
						<span>name:</span>
						<input type="text" value="圆" />
						<span>type:</span>
						<input type="text" value="圆" disabled="disabled"/>
					</li>
					
					<li>
						<span >x:</span>
						<input type="text">
						<span>y:</span>
						<input type="text">
					</li>
					<li>
						<span>w:</span>
						<input type="text">
						<span>h:</span>
						<input type="text">
					</li>
					<li>
						<span>rotate:</span>
						<input type="text">
						<span>scale:</span>
						<input type="text">
					</li>
				</ul>
			</div>
			-->
			
			<div class="assembly">
				<div class="ss">
					<p>组件列表</p>
<!-- 					<input type="text" placeholder="请输入组件名称"> -->
				</div>
				<div id="treeView">
				<!--  -->
				<!--
					<ul class="" >
						<li>
							<div class="treeNode  treeNode-cur" style="padding-left: 0px" data-file-id="0"> <i class="icon icon-control icon-minus"></i> <i class="icon icon-file"></i> <span class="title">组件列表</span> </div>
							<ul  class="" >
								<li>
									<div class="treeNode" style="padding-left: 20px" data-file-id="1"> <i class="icon icon-control icon-add"></i> <i class="icon icon-file"></i> <span class="title">矩形</span> </div>
									<ul class="none">
										<li>
											<div class="treeNode treeNode-empty " style="padding-left: 40px" data-file-id="11"> <i class="icon icon-control icon-add"></i> <i class="icon icon-file"></i> <span class="title">矩形1</span> </div>
											<ul class="none">
											</ul>
										</li>
										<li>
											<div class="treeNode treeNode-empty " style="padding-left: 40px" data-file-id="12"> <i class="icon icon-control icon-add"></i> <i class="icon icon-file"></i> <span class="title">矩形2</span> </div>
											<ul class="none">
											</ul>
										</li>
										<li>
											<div class="treeNode treeNode-empty " style="padding-left: 40px" data-file-id="13"> <i class="icon icon-control icon-add"></i> <i class="icon icon-file"></i> <span class="title">矩形(070ffa5864ca)</span> </div>
											<ul class="none">
											</ul>
										</li>
									</ul>
								</li>
								<li>
									<div class="treeNode treeNode-empty " style="padding-left: 20px" data-file-id="2"> <i class="icon icon-control icon-add"></i> <i class="icon icon-file"></i> <span class="title">椭圆形</span> </div>
									<ul class="none">
									</ul>
								</li>
								<li>
									<div class="treeNode treeNode-empty " style="padding-left: 20px" data-file-id="3"> <i class="icon icon-control icon-add"></i> <i class="icon icon-file"></i> <span class="title">三角形</span> </div>
									<ul class="none">
									</ul>
								</li>
								<li>
									<div class="treeNode  " style="padding-left: 20px" data-file-id="4"> <i class="icon icon-control icon-add"></i> <i class="icon icon-file"></i> <span class="title">多边形</span> </div>
									<ul class="none">
										<li>
											<div class="treeNode treeNode-empty " style="padding-left: 40px" data-file-id="41"> <i class="icon icon-control icon-add"></i> <i class="icon icon-file"></i> <span class="title">多边形33333333</span> </div>
											<ul class="none">
											</ul>
										</li>
										<li>
											<div class="treeNode treeNode-empty " style="padding-left: 40px" data-file-id="42"> <i class="icon icon-control icon-add"></i> <i class="icon icon-file"></i> <span class="title">多边形333</span> </div>
											<ul class="none">
											</ul>
										</li>
										<li>
											<div class="treeNode treeNode-empty " style="padding-left: 40px" data-file-id="43"> <i class="icon icon-control icon-add"></i> <i class="icon icon-file"></i> <span class="title">多边形</span> </div>
											<ul class="none">
											</ul>
										</li>
									</ul>
								</li>
							</ul>
						</li>
					</ul>
					-->
					<!--  -->
					
				</div>
				<script src="rtdroc/app/view2/js/data.js"></script> 
				<script src="rtdroc/app/view2/js/tools.js"></script> 
				<script src="rtdroc/app/view2/js/handledata.js"></script> 
				<script src="rtdroc/app/view2/js/index2.js"></script>
				<!--
				<div class="assemblylist">
					<div class="assemblyname">Page1</div>
					<div class="listT">
						<ul>
							<li id="assembly1" class="activeTi"><a href="javascript:">图形框01(矩形)</a><img src="rtdroc/app/view2/images/lvyan_03.png"></li>
							<li id="assembly2"><a href="javascript:">横截面02(椭圆形)</a><img src="rtdroc/app/view2/images/lvyan_03.png"></li>
							<li id="assembly3"><a href="javascript:">顶部开关01(三角形)</a><img src="rtdroc/app/view2/images/lvyan_03.png"></li>
							<li id="assembly4"><a href="javascript:">图形框01(矩形)</a><img src="rtdroc/app/view2/images/lvyan_03.png"></li>
							<li id="assembly5"><a href="javascript:">横截面02(椭圆形)</a><img src="rtdroc/app/view2/images/lvyan_03.png"></li>
							<li id="assembly3"><a href="javascript:">顶部开关01(三角形)</a><img src="rtdroc/app/view2/images/lvyan_03.png"></li>
							<li id="assembly1"><a href="javascript:">图形框01(矩形)</a><img src="rtdroc/app/view2/images/lvyan_03.png"></li>
							<li id="assembly2"><a href="javascript:">横截面02(椭圆形)</a><img src="rtdroc/app/view2/images/lvyan_03.png"></li>
							<li id="assembly3"><a href="javascript:">顶部开关01(三角形)</a><img src="rtdroc/app/view2/images/lvyan_03.png"></li>
							<li id="assembly1"><a href="javascript:">图形框01(矩形)</a><img src="rtdroc/app/view2/images/lvyan_03.png"></li>
							<li id="assembly2"><a href="javascript:">横截面02(椭圆形)</a><img src="rtdroc/app/view2/images/lvyan_03.png"></li>											
						</ul>
					</div>
				</div>
				 -->
			</div>
		</div>
	</div>
</div>

<style>
       .floatingControls {
         position: absolute;     
         z-index: 9999;
         display: none; 
      }
</style>
<div id="textControls" class="floatingControls">
<!-- input id="textControl" name="textControl" type="text" style="width:100%;"/> -->
<textarea  id="textControl" name="textControl"  style="width:100%;height:200px;min-height: 26px;"></textarea>
</div>
<script>
	var textarea=document.getElementById('textControl');
	makeExpandingArea(textarea); //autoTextarea.js
</script>

 <script >
       
		//刷新组件
		EventHub.on("RtComponentsList.refresh", function () { 
			console.log("RtComponentsList.refresh");
			//$RtComponentsList.html("");	
			
			refreshComponents();
			 
			var selectedPrimitives =rtDrawingLayer.selectedPrimitives;
			if(selectedPrimitives&&selectedPrimitives.length>0){
				selectComponent(selectedPrimitives);
			}
		});
		
		//组件选中
		EventHub.on("RtComponentsList.select", function () { 
			console.log("RtComponentsList.select");
			var selectedPrimitives =rtDrawingLayer.selectedPrimitives;
			console.log("RtComponentsList.select:"+selectedPrimitives.length);
			if(selectedPrimitives&&selectedPrimitives.length>0){
				if(selectedPrimitives>1){
					//修改基础属性
				}
				selectComponent(selectedPrimitives);
			}else{
				selectComponent(null);
			}
			
		});
		//组件未选中
		EventHub.on("RtComponentsList.selectNone", function () { 
			console.log("RtComponentsList.selectNone");
			selectComponent(null);
		});
		
		//组件选中
		EventHub.on("ViewComponentsList.select", function (id) { 
			selectPrimitive(id);
			//修改基础属性
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
</body>
<script src="rtdroc/app/view2/js/index.js"></script>
<script src="rtdroc/app/view2/js/view.js"></script>
</html>