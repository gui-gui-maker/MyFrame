<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/photograph/js/webcam.js"></script>
    <script type="text/javascript">
    $(function(){
    	$("#toolbar1").ligerButton({
			items:[
				{text:"拍照", id:"pic",disabled:false,click:function(){
					webcam.freeze();
				}},
				{text:"保存",id:"save",click:function(){
					do_upload();
				}},
				{text:"重拍",id:"repic",click:function(){
				     webcam.reset();
				}},
				{text:"设置",id:"set",click:function(){
				     webcam.configure();
				}},
				{text:"关闭",id:"close",click:function(){
				     webcam.set_stealth();//清除摄像头缓存
				     api.close();
				}}
			]
		});
    });
    </script>
</head>
<body>
	<div class="scroll-tm">
	<form >
	    <table>
			<script type="text/javascript">
			    webcam.set_api_url('pub/personImg/photograph.do?idn=' + (api.data.idn?api.data.idn:"")); /** 处理图片的URL地址 */
			    webcam.set_swf_url('${pageContext.request.contextPath}/pub/photograph/flash/webcam.swf'); /** 设备地址 */
			    webcam.set_quality(90); /** JPEG质量(1 - 100) 100最好 */
			    webcam.set_shutter_sound(true,'${pageContext.request.contextPath}/pub/photograph/flash/shutter.mp3'); /** 是否播放声音文件 */
			
				<!-- 第二步, 按照设置好的大小(300x370)把设备写入页面上去  -->
				document.write(webcam.get_html(300, 370));
				
		       	<!-- 代码处理服务器的相应 (参见set_api_url里边填写的URL的代码) -->
	            webcam.set_hook('onComplete', 'my_completion_handler');
	            function do_upload() {
	               /** 上传到服务器上 */
	               webcam.upload();
	            }
	            function my_completion_handler(msg) {
	               if(msg){
	                    //var pathfile=msg.replace(/\s+/gi,'');
	                    var json = $.parseJSON(msg);
				        //拍照成功之后把文件的名称传给父页面
				        if(json.success){
				        	if(api.data.callback)
				           		api.data.callback(json);
					        webcam.set_stealth();//清除摄像头缓存
				            api.close();
				        }
				        else{
				        	alert("上传图片失败，请稍后重试");
			                webcam.reset();
				        }
	               }else{
	                    alert("上传图片失败，请稍后重试");
	                    webcam.reset();
	               }
	            }
		    </script>
	    </table>
	</form>
	</div>
	<div class="toolbar-tm">
		<div class="toolbar-tm-bottom">
			<div id="toolbar1" align="center"></div>
		</div>
	</div>
</body>
</html>