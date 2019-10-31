
//加载后的图片控件前缀，使用过程中会根据触发的控件改变值
var receiptfiles = "receiptfiles";
//储存图片相关信息，包括字段名，宽，高，附件id
var pictures = [];
/**
 * 引入此js后，对所有class为uploadPhoto的控件进行初始化，使其能够完成上传图片，调整图片的功能
 * 此js建立在jQuery和公司框架的基础上
 * 
 * 使用此功能需要引入js（head最后引入）
 * <script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
 * <script type="text/javascript" src="rtbox/app/templates/default/tpl_photo.js"></script> 
 * 
 *在对应位置添加标记控件 <div name="pictureo1" class="uploadPhoto" style="width: 500px;height: 500px;"></div>
 * 
 * 解释权归pingZhou2017/03/09
 */
$(function() {
	
	//页面head引入框架的上传附加js  pub/fileupload/js/plupload.js
	/*var script = document.createElement('script');
	script.type = 'text/javascript';
	script.language = 'javascript';
	script.src = "pub/fileupload/js/fileupload.js";
	document.getElementsByTagName("head")[0].appendChild(script);
	
	*/
	//alert(unescape('%3Cscript type="text/javascript" src="pub/fileupload/js/plupload.js"%3E%3C/script%3E'))
	//document.write(unescape('%3Cscript type="text/javascript" src="'+$("base").attr("href")+'pub/fileupload/js/plupload.js"%3E%3C/script%3E'));
	//$("body").append("")
	
	//循环处理图片标记类
	var n = 1;
	$(".uploadPhoto").each(
			function() {
				//实例 控件处应写的内容
				/*
				 * 
				 * <div name="pictureo1" class="uploadPhoto" style="width: 500px;height: 500px;"></div>
				 * 
				 * */
				//取出字段名，方便保存
				var name = $(this).attr("id")
				var widthD = $(this).css("width");
				var heightD = $(this).css("height");
				
				
				
				
				var width = $(this).css("width").substring(0,widthD.length-2)-50;
				var height = $(this).css("height").substring(0,heightD.length-2)-50;
				
				//存控件参数
				var picture ={};
				picture["name"]=name;
				picture["width"]=width;
				picture["height"]=height;
				picture["fileid"]="";
				pictures[n-1] = picture;
				
				//alert("加载控件的参数为-----name："+name+"---width:"+width+"--height:"+height)
				
				$(this).append('<table cellpadding="3" cellspacing="0">'
						+'<tr style="display:none;">'
						+'<td class="l-t-td-left">浏览：</td>'
						+'<td  class="l-t-td-right" colspan="3" id="receiptfiles'+n+'DIV">'
						+'<a class="l-button3"  id="receiptfiles'+n+'Btn"  onmouseover="changeUploadBtn(this)">'
						+'<img src="rtbox/app/templates/default/img/bn.png" id="receiptfiles'+n+'O"/></a>'
						+'<div class="l-upload-ok-list"><ul id="receiptfiles'+n+'"></ul></div>'
						+'</td>'
						+'</tr> '
					    +'<tr>'
					    +'<td class="l-t-td-left"></td>'
						+'<td  class="l-t-td-right" colspan="3">'
						+'	<input name="'+name+'" id="receiptfiles'+n+'AttchNames" type="hidden" class="receiptfiles'+n+'"/>'
						//+'<a id="cs" href="https://daohang.qq.com/?fr=hmpage">1234532345</a>'
						+'<a href="javascript:changePicture(\'receiptfiles'+n+'\');" id="receiptfiles'+n+'A">'
						+'<img src="" title="点击选择图片" alt="点击选择图片" id="receiptfiles'+n+'P" style="width: '+width+'px;height: '+height+'px;"/>'
						+'</a>'
						+'</td>'
						+'</tr>'+'</table>');
				new KHFileUploader({
		    			fileSize : "1mb",//文件大小限制
		    			folder:"supervise/order",
		    			businessId : "",//业务ID
		    			buttonId : "receiptfiles"+n+"Btn",//上传按钮ID
		    			container : "receiptfiles"+n+"DIV",//上传控件容器ID
		    			title : "请选择附件",//文件选择框提示
		    			extName : "png,jpg,gif,bmp",//文件扩展名限制
		    			saveDB : true,//是否往数据库写信息
		    			attType : "",//文件存储类型；1:数据库，0:磁盘，默认为磁盘
		    			fileNum : 1,//限制上传文件数目
		    			callback : function(files){//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
		    				
		    				addAttachFile(files);
		    			}
		    		});
				n++;
			}) 
	
}
)
//<span id="receiptfiles'+n+'O">+</span>
function deleteFileUp(){
		$("#"+receiptfiles+"Btn").show();
		//$("#procufilesBtn").show();
		$("#"+receiptfiles+"P").attr("src","");
		getUploadFile();
	}

	//将上传的所有文件id写入隐藏框中
	function getUploadFile(){
		var attachId="";
		var i=0;
		$("#"+receiptfiles+" li").each(function(){
			attachId+=(i==0?"":",")+this.id;
			i=i+1;
		});
		if(i>=1){
			$("#"+receiptfiles+"Btn").hide();
		}
		$("#"+receiptfiles+"AttchNames").val(attachId);
	}

/**
 * 图片上传成功后处理及显示
 * @param files
 */
function addAttachFile(files){
		if("${param.status}"=="detail"){
			$("#receiptfilesBtn").hide();
		}
		
		if(files){
			$.each(files,function(i){
				var data=files[i];
				var c = receiptfiles.substring(receiptfiles.length-1,receiptfiles.length);
				var fileid = pictures[c-1]["fileid"];
				//alert(fileid)
				if(fileid!=""){
					//删除之前上传的附件
					$.getJSON('fileupload/deleteAtt.do?id='+fileid);
				}
				$("#"+receiptfiles+"P").attr("src","fileupload/downloadByObjId.do?id="+data.id);
				
				/*createFileView(data.id,data.name,$("head").attr("pageStatus")=="detail"?false:true,receiptfiles,true,function(fid){
					deleteFileUp();
				})*/
				$("#"+receiptfiles+"AttchNames").val(data.id);
				//alert($("#"+receiptfiles+"AttchNames").val())
				pictures[c-1]["fileid"] = data.id;
				//getUploadFile();
			})
		}
	}


/**
 * 当一页页面有多处需要上传图片时，要切换控件
 * @param up
 */
function changeUploadBtn(up){
		var id = $(up).attr("id");
		receiptfiles = id.substring(0,id.length-3);
	}	

function changePicture(up){
	receiptfiles = up;
	//alert("#"+receiptfiles+'Btn')
	$("#"+receiptfiles+'O').click();
	//$("#cs").click();
	
}