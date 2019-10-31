
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.khnt.security.CurrentSessionUser"%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    CurrentSessionUser user=(CurrentSessionUser)request.getSession().getAttribute("currentSessionUser");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
    <script type="text/javascript">
        $(function () {
        	var nowDate = new Date().format("yyyy-MM-dd");
        	if("${param.jdDate}"!=""&&"${param.jdDate}"!=undefined){
        		$("#practicalTime").val("${param.jdDate}");
        	}else{
        		$("#practicalTime").val(nowDate);
        	}
			//如果不设置额外参数，不用调用此方法，初始化时会自动调用
            $("#form").initForm({
                success : function(responseText) {//处理成功
                    if (responseText.success) {
                        top.$.dialog.notice({content:'保存成功'});
                        api.data.window.Qm.refreshGrid();
                        $("#id").val(responseText.data.id);
                    } else {
                        $.ligerDialog.error('保存失败' + responseText);
                    }
                },
                getSuccess:function(responseText){
                }
            });

            var receiptUploadConfig = {
        			fileSize : "40mb",	//文件大小限制
        			businessId : "",	//业务ID
        			buttonId : "procufilesBtn",		//上传按钮ID
        			container : "procufilesDIV",	//上传控件容器ID
        			attType : "",//文件存储类型；1:数据库，0:磁盘，默认为磁盘
        			title : "图片选择",	//文件选择框提示
        			extName : "jpg,jpeg,gif,png,bmp,pdf",	//文件扩展名限制
        			workItem : "five",	//页面多点上传文件标识符号
        			fileNum : 5,	//限制上传文件数目
        			callback : function(files){	//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
        				addAttachFile(files);
        			}
        	};
    		var receiptUploader= new KHFileUploader(receiptUploadConfig);
        });

      //添加附件
    	function addAttachFile(files){
    		if("${param.status}"=="detail"){
    			$("#procufilesBtn").hide();
    		}
    		if(files){
    			var attContainerId="procufiles";
    			$.each(files,function(i){
    				var file=files[i];
    				/* $("#"+attContainerId).append("<li id='"+file.id+"'>"+
    						"<div><a href='fileupload/downloadByFilePath.do?path="+file.path+"&fileName="+file.name+"'><image style='width:60px;height:60px' src='upload/"+file.path+"'></a></div>"+
    						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.path+"\",this,getUploadFile)'>&nbsp;</div>"+
    						"</li>"); */
    				$("#"+attContainerId).append("<li id='"+file.id+"'>"+
    						"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.name+"'>"+file.name+"</a></div>"+
    						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\"\",this,getUploadFile)'>&nbsp;</div>"+
    						"</li>");
    			});
    			getUploadFile();
    		}
    	}
    	  
    	// 将上传的所有文件id写入隐藏框中
    	function getUploadFile(){
    		var attachId="";
    		var i=0;
    		$("#procufiles li").each(function(){
    			attachId+=(i==0?"":",")+this.id;
    			i=i+1;
    		});
    		if(attachId!=""){
    			attachId=attachId.substring(0,attachId.length);
    		}
    		$("#uploadFiles").val(attachId);
    	}
    </script>
</head>
<body>
    <form id="form1" action="eq/docimasyFksAction/save.do?type=${param.type}" method="get" getAction="eq/docimasyFksAction/fdetail1.do?id=${param.id}" >
        <input type="hidden" id="id" name="id"/>
         <input type="hidden" id="idf" value="${param.id}" name="idf"/>
         <input type="hidden" id="equipmentId" name="equipmentId" value="${param.ide}"/>
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
       <tr> 
       <td class="l-t-td-left"> 检定日期</td>
        <td class="l-t-td-right"> 
        <input validate="required:true" id="practicalTime" name="practicalTime" type="text" ltype="date" ligerui="{format:'yyyy-MM-dd'}"/>
        </td>
        <td class="l-t-td-left"> 检定状态</td>
        <td  class="l-t-td-right"> 
        <u:combo name="status" code="TJY2_DOCIMASY_STATUSF" attribute="initValue:'ywc'" validate="required:true" />
        </td>
        
       </tr>
      <tr>
        <td class="l-t-td-left"> 检定结果</td>
        <td colspan="3" class="l-t-td-right">
        <input type="radio" name="result" id="result" ltype="radioGroup" validate="{required:true}"
							ligerui="{value:'合格',data: [ { text:'合格', id:'合格' }, { text:'不合格', id:'不合格' }] }" />
						</td>
        <!-- <td colspan="3" class="l-t-td-right"> 
        <textarea class="l-textarea" rows="3" name="result"  validate="{maxlength:200,required:true}"></textarea>
        </td> -->
      </tr>
      <tr>
        <td class="l-t-td-left"> 备注</td>
        <td colspan="3" class="l-t-td-right"> 
        <textarea class="l-textarea" rows="3" name="remark"  validate="{maxlength:200}"></textarea>
        </td>
      </tr>
      <tr>
		<td class="l-t-td-left">检定证书</td>
		<td colspan="3" class="l-t-td-right">
			<input type="hidden" name="uploadFiles" id="uploadFiles" name="uploadFiles" validate="{maxlength:2000}" />
			<p id="procufilesDIV">
				<a class="l-button" id="procufilesBtn">
					<span class="l-button-main"><span class="l-button-text">选择文件</span></span>
				</a>
			</p>
	    	<div class="l-upload-ok-list">
				<ul id="procufiles"></ul>
			</div>
		</td>	
	</tr>
      </table>
    </form>
    <script type="text/javascript">
	        $("#form1").initForm({
				// opType:"auto",//数据保存模式，发现actionParam的关联信息为空时会自动调用opTypeFun，默认为auto
				// opTypeFun:save,//自动保存调用方法，opType为atuo时才会用到,默认为save
				// action:"",//保存数据或其它操作的action
	            actionParam:{"docimasyFk.id":$("#form1>#idf")}, //保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把from1下的name为id的值带上去
	//            delAction:"eq/docimasyFksAction/delete.do",//删除数据的action
				 // delActionParam:{"id":$},//默认为选择行的id
				 // getAction:"",//取数据的action
				 // getActionParam:{},//取数据时附带的参数，一般只在需要动态取特定值时用到
	            onSelectRow:function (rowdata, rowindex) {
	                $("#form1").setValues(rowdata);
	            },
				// toolbar:[
				//  { text:'保存', click:function(){$("#formJc").submit();}, icon:'save'},
				//  { text:'删除', click:function(){$("#formJc").submit();}, icon:'save'}
				//  ],
	           
	        });
    	</script>
</body>
</html>

