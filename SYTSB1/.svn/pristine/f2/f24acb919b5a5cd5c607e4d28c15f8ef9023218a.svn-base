<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<%@ include file="/k/kui-base-form.jsp"%>
<title></title>
</head>
<body>

<form id="costForm" name="costForm" method="post" >
  	<input type="hidden" name="id" />
  	<input type="hidden" name="fkTjy2ScientificId" id="cost_id"/>
	<table cellpadding="3" cellspacing="0" class="l-detail-table"  >
		<tr>
	       <td class="l-t-td-left">项目名称</td>
	       <td class="l-t-td-right" colspan="3"><input name="project_name" id="project_name" type="text" ltype='text' validate="{required:true,maxlength:36}" /></td>	
	    </tr>
	    <tr>
	       <td class="l-t-td-left">审核状态</td>
	       <td class="l-t-td-right" ><input name="process" id="process" type="text" ltype='text' validate="{required:true,maxlength:36}" /></td>	
	    </tr>
	    <tr>
	      	<td class="l-t-td-left">审核说明</td>
	       	<td class="l-t-td-right" colspan="3"><textarea name="remark" id="remark" rows="4" cols="25" class="l-textarea" validate="{maxlength:2000}"></textarea></td>
	    </tr>
	     <tr>
		      <td class="l-t-td-left">文件</td>
		       <td class="l-t-td-right" colspan="3" style="height: 80px;">
		       		<input name="scanFile" type="hidden" id="scanFile" />
							
							<span class="l-upload-ok-list">
								<ul id="onefile"></ul>
							</span></td>
		    </tr>
	</table> 
  	<script type="text/javascript">
    $("#costForm").initFormList({
    	root:'datalist',
        getAction:"tjy2ScientificRemarkAction/getList.do?id=${param.id}",
        //getActionParam:{},	//取数据时附带的参数，一般只在需要动态取特定值时用到
        delAction:'tjy2ScientificRemarkAction/delete.do',	//删除数据的action
        delActionParam:{ids:"id"},	//默认为选择行的id 
        columns:[
            //此部分配置同grid
            { display:'费用情况主键', name:'id', width:'1%', hide:true},
			{ display:'项目基本信息主键', name:'fk_scientific_id', width:'1%', hide:true},
            { display:'项目名称', name:'project_name', width:'25%'},
            { display:'审核状态', name:'process', width:'15%'},
            { display:'审核说明', name:'remark', width:'60%'}
          
          
        ],
        onSelectRow : function(rowdata, rowindex) {
			
			//alert(rowdata.scanFile);
			$.getJSON("tjy2ScientificRemarkAction/detailBaic.do", {id:rowdata.id},function(res){
				
				if(res.success){
					$("#project_name").html(res.data.project_name);
					$("#process").html(res.data.process);
					$("#remark").html(res.data.remark);
					 $("#onefile").empty();
					showSignPicture(res.attachs);
				}else{
					$.ligerDialog.alert(res.message, "提示");
				}
			});
		},getSuccess:function(res){
       		
       		//showSignPicture(res.scanFile);
     }
    });
    //显示签名文件
    function showSignPicture(files){
    	
    		$("#onefileBtn").hide();
    		 $.each(files,function(i){
					var file=files[i];
					 //显示附件
				
					$("#onefile").append("<li id='"+file.id+"'>"+
								"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
								"</li>");
				}); 
    	/* if(file){
    		var attContainerId="onefile";
    			$("#"+attContainerId).append("<li id='"+file.id+"'>"+
    					"<div><a href='fileupload/downloadByObjId.do?id="+file.id+"'>"+file.fileName+"</a></div>"+
    					"</li>");
    	} */
    }
	</script>
	</form>
</body>
</html>