<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus = "${param.pageStatus}">
<title>技术相关信息</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var pageStatus = "${param.pageStatus}";
	var tbar="";
	$(function() {
		if(pageStatus=="detail"){
	 		tbar=[{text: "关闭", icon: "cancel", click: function(){api.close();}}];
	 	}else{
	 		tbar=[{text: "保存", icon: "save", click: function(){
		      				//表单验证
					    	if ($("#form1").validate().form()) {
					    		$("#form1").submit();
					    	}else{
					    		$.ligerDialog.error('提示：' + '请将信息填写完整后保存！');
					    	}
		      			}},
				  {text: "关闭", icon: "cancel", click: function(){api.close();}}];
	 	}
		
		$("#form1").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	         		api.data.window.refreshGrid();
	            	api.close();
	    		} else {
	           		$.ligerDialog.error(response.msg);
	      		}
			}, getSuccess: function (response){
				var techExchange = response.data;
				var readers = [];
                var names = [];
                var ids = [];
                if(techExchange.personnel){
                     names = techExchange.personnel.split(",");
                     ids = techExchange.personnelId.split(",");
                     for(var i=0;i<names.length;i++){
                        readers.push({
                            types : "reader",
                            name: names[i],
                            id: ids[i]
                        });
                    } 
                    addReader(readers,false);
				}
			},
			showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
	})
	function selectReaders(){
	selectUnitOrUser("4",1,"","",function(datas){
		if(!datas.code)return;
		var codeArr = datas.code.split(",");
		var nameArr = datas.name.split(",");
		var readers = [];
		var existReaders = $("#form1").data("readers")||[];
		for(var i in codeArr){
			var exist = false;
			for(var j in existReaders){
				if(existReaders[j].stakeholderId == codeArr[i] && existReaders[j].valid == true)
					exist=true;
			}
			if(exist) continue;
			readers.push({
				types : "reader",
				name: nameArr[i],
				id: codeArr[i]
			});
		}
		addReader(readers,true);
	});
}
var CCnames='';//接收人姓名
var CCids='';//接收人id
function addReader(newReaders,isNew){
	var names = '';
	var ids = '';
	var repids='';
	var repNames='';
	//alert(newReaders);
	for(var i in newReaders){
         if(CCids.indexOf(newReaders[i].id)==-1){
             names = names+newReaders[i].name+","; 
             ids = ids+newReaders[i].id+",";
             var mtext='<span class="label label-read" id="' + (isNew?newReaders[i].id:newReaders[i].id) + '"><span class="text">' + newReaders[i].name;
             if("${param.pageStatus}"!="detail"){
                mtext = mtext+'</span><span class="del btn btn-lm" onclick="deleteReader(\'' + (isNew?newReaders[i].id:newReaders[i].id) + '\',' + isNew + ')"><img src="k/kui/images/icons/16/delete1.png">&nbsp;</span></span>';
             }
             if("${param.pageStatus}"=="detail"){
                mtext = mtext+',';
              }
             $("#reader_td").prepend(mtext);
         } else if (newReaders[i].name){
             repNames = repNames+newReaders[i].name+",";
         }
    }
    if(repNames != ""){
             $.ligerDialog.warn("人员："+"<span style='color:red;'>"+repNames.substring(0,repNames.length-1)+"</span>"+" 已存在！");
    }
    CCnames = CCnames+names;
    CCids = CCids+ids;
    $("#personnel").val(CCnames.substring(0, CCnames.length-1));
    $("#personnelId").val(CCids);
 }
 function deleteReader(id,isNew){
     $("#"+id).remove();
     var ids = CCids.split(",");
     var names = CCnames.split(",");
     for(var i in ids){
        if(id==ids[i]){
            ids[i]="";
            names[i]="";
        }
     }
     var cid="";
     var cname="";
     for(var i in ids){
        if(ids[i]!=""){
            cid = cid+ids[i]+',';
            cname = cname+names[i]+',';
        }
     }
     CCnames = cname;
     CCids = cid;
     $("#personnel").val(CCnames);
     $("#personnelId").val(CCids);
 }
</script>
</head>
<body>
    <form id="form1" action="TechExchangeAction/save.do?status=${param.pageStatus}" getAction="TechExchangeAction/detail.do?id=${param.id}">
     <input type="hidden" name="id" id="id"/>
     <input type="hidden" name="personnelId" id="personnelId"/>
     <input type="hidden" name="createDate" id="createDate"/>
     <input type="hidden" name="createId" id="createId"/>
     <input type="hidden" name="createBy" id="createBy"/>
     <input type="hidden" name="lastModifyDate" id="lastModifyDate"/>
     <input type="hidden" name="lastModifyId" id="lastModifyId"/>
     <input type="hidden" name="lastModifyBy" id="lastModifyBy"/>
     <input type="hidden" name="type" id="type" value="jsjl"/>
       <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
	  <tr> 
        <td class="l-t-td-left"> 交流题目</td>
        <td class="l-t-td-right"> 
        <input name="actTitle" id="actTitle" type="text" ltype='text'/>
        </td>
      </tr>
      <tr>
        <td class="l-t-td-left"> 交流内容</td>
        <td class="l-t-td-right"> 
        <textarea name="actContent" id="actContent" rows="5" cols="25" class="l-textarea"></textarea>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 交流人员</td>
        <td class="l-t-td-right" id="reader_td"> 
        <input name="personnel" id="personnel" type="hidden"/>
        <c:if test="${param.pageStatus=='modify' or param.pageStatus=='add'}"><span class="l-button label" title="添交流人员">
	      		<span  class="l-a l-icon-add"  onclick="selectReaders();">&nbsp;</span>
	       	 </span></c:if>
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left"> 交流成果</td>
        <td class="l-t-td-right"> 
        <textarea name="actResult" id="actResult" rows="5" cols="25" class="l-textarea"></textarea>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 交流时间</td>
        <td class="l-t-td-right"> 
        <input name="actDate" id="actDate" type="text" ltype='date'/>
        </td>
       <tr>
		<td class="l-t-td-left">备注</td>
		<td class="l-t-td-right">
			<textarea name="remark" rows="2" cols="25" class="l-textarea" validate="{maxlength:500}"></textarea>
		</td>						
	  </tr>
      </table>
    </form> 
</body>
</html>
