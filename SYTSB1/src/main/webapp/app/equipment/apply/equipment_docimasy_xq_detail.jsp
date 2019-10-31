
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
    <script type="text/javascript">
        $(function () {
//          如果不设置额外参数，不用调用此方法，初始化时会自动调用
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
                getSuccess:function(res){
                	var readers = [];
                    var names = [];
                    var ids = [];//alert(res.data.dutyName);
                    if(res.data.checkOp){
                         names = res.data.checkOp.split(",");
                         ids = res.data.checkOpId.split(",");
                         for(var i=0;i<names.length;i++){
                            readers.push({
                                types : "reader",
                                name: names[i],
                                id: ids[i]
                            });
                        } 
                        addReader(readers,false);
                    }
                    getPicture();
                }
            });

        });
function selectReaders(){
            
            var readers = $("#form").data("readers"); 
            selectUnitOrUser("4",1,"","",function(datas){
                if(!datas.code)return;
                var codeArr = datas.code.split(",");
                var nameArr = datas.name.split(",");
                var readers = [];
                var existReaders = $("#form").data("readers")||[];
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
    	var CCnames='';//送检负责人
        var CCids='';//送检负责人id
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
            $("#checkOp").val(CCnames.substring(0, CCnames.length-1));
            $("#checkOpId").val(CCids);
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
             $("#checkOp").val(CCnames);
             $("#checkOpId").val(CCids);
         }
      // 显示附件文件
      function showAttachFile(files){
      	if("${param.pageStatus}"=="detail"){
  			$("#procufilesBtn").hide();
  		}
  		if(files){
  			//详情
  			var attContainerId="procufiles";
  			if("${param.pageStatus}"=="detail"){
  				$.each(files,function(i){
  					var file=files[i];
  					/* $("#"+attContainerId).append("<li id='"+file.id+"'>"+
  							"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
  							"</li>"); */
  					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
  							"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
  							"</li>");
  				});
  			}
  		}
      }
    function getPicture(){
		$.ajax({
	    	url: "eq/docimasyFkAction/getPicture.do?id=${param.id}",
	        type: "POST",
	        datatype: "json",
	        contentType: "application/json; charset=utf-8",
	        success: function (resp) {
	        	if (resp.attachs != null && resp.attachs != undefined)
					showAttachFile(resp.attachs);
	        }
    	});
    }
    </script>
</head>
<body>
<div class="navtab">
<div title="基本信息" lselected="true">
    <form id="form" action="eq/docimasyFkAction/save.do" method="get" getAction="eq/docimasyFkAction/detail.do?id=${param.id}" >
        <input type="hidden" id="id" name="id"/>
        <input type="hidden" id="checkOpId" name="checkOpId"/>
         <input type="hidden" value="9dman" name="ids" id="ids"/>
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
      <tr> 
        <td class="l-t-td-left"> 设备名称</td>
        <td class="l-t-td-right"> 
        <input name="equipmentId" id="equipmentId" type="hidden" />
        <input name="equipmentName"   id="equipmentName" type="text" ltype='text' validate="{required:true,maxlength:100}" validate="{required:true,maxlength:32}" onclick="selEquipment()" ligerui="{value:'',iconItems:[{icon:'add',click:function(){selEquipment()}}]}"/>
        </td>
         <td class="l-t-td-left"> 设备编号</td>
        <td class="l-t-td-right"> 
        <input id="equipmentNumbe"  ligerUi="{disabled:true}" name="equipmentNumbe" type="text" ltype='text' />
        </td>
       </tr>
         <tr> 
        <td class="l-t-td-left"> 设备型号</td>
        <td class="l-t-td-right">  
        <input name="eqType"  ligerUi="{disabled:true}" id="eqType" type="text" ltype='text'   />
        </td>
         <td class="l-t-td-left"> 出厂编号</td>
        <td class="l-t-td-right"> 
        <input id="eqNumbe"  ligerUi="{disabled:true}" name="eqNumbe" type="text" ltype='text' />
        </td>
       </tr>
       <tr> 
       <td class="l-t-td-left"> 检定周期（月）</td>
	        <td class="l-t-td-right"> 
	        <input ligerUi="{disabled:true}"  name="period" type="text" ltype='text' />
        </td>
           <td class="l-t-td-left"> 计划日期</td>
	        <td class="l-t-td-right"> 
	        <input ligerUi="{disabled:true}"  id="nextTime" name="nextTime" type="text" ltype="date" ligerui="{initValue:'',format:'yyyy-MM-dd'}"  onchange="setValues(this.value,'advance_time')" />
	        </td>
       </tr>
       <tr> 
	         <td class="l-t-td-left"> 送检时间</td>
	        <td class="l-t-td-right"> 
	        	          <input validate="{required:true,maxlength:32}" name="equipmentTime"type="text" ltype="date" validate="{required:false}"ligerui="{initValue:'',format:'yyyy-MM-dd'}"  onchange="setValues(this.value,'advance_time')"/>
	        </td>
	         <td class="l-t-td-left"> 检定单位</td>
        <td class="l-t-td-right"> 
        <input id="unit"  name="unit" validate="required:true" type="text" ltype='text' />
        </td>
       </tr>
       <tr> 
	        <td class="l-t-td-left">送检责任人</td>
			<td class="l-t-td-right" id="reader_td" colspan="3" >
			<input type="hidden" name="checkOp" id="checkOp"  validate="{required:true}"/>
				 <c:if test="${param.pageStatus=='modify' or param.pageStatus=='add'}"><span class="l-button label" title="送检责任人">
	                          <span  class="l-a l-icon-add"  onclick="selectReaders();">&nbsp;</span>
	                         </c:if>
			
			</td>
       </tr>
         <tr> 
            <td class="l-t-td-left"> 实际检定日期</td>
	        <td class="l-t-td-right"> 
	          <input validate="{required:true,maxlength:32}" name="practicalTime"type="text" ltype="date" validate="{required:false}"ligerui="{initValue:'',format:'yyyy-MM-dd'}"  onchange="setValues(this.value,'advance_time')"/>
	        </td>
	        <td class="l-t-td-left"> 检定状态 </td>
	        <td class="l-t-td-right"> 
	         <u:combo  validate="{required:true,maxlength:32}"  name="status" code="TJY2_DOCIMASY_STATUS" />
	        </td>
       </tr>
      <!--  <tr> 
	       </td>
	          <td class="l-t-td-left"> 检定结果</td>
	        <td class="l-t-td-right"> 
	        <input name="result" type="text" ltype='text' validate="{required:true,maxlength:2000}"/>
	        </td>
	         <td class="l-t-td-left"> 实际检定日期</td>
        <td class="l-t-td-right"> 
        <input name="practicalTime" type="text" ltype='date' ligerui="{format:''}" validate="{required:true}}"/>
        </td>
	         
       </tr> -->
       <tr> 
       
        <td class="l-t-td-left"> 检定结果</td>
        <td colspan="3" class="l-t-td-right"> 
        <textarea name="result" rows="4" type="text" ltype='text' ></textarea>
        </td>
       </tr>
       <tr>
		<td class="l-t-td-left">检定证书</td>
		<td colspan="3" class="l-t-td-right">
			<input name="uploadFiles" type="hidden" id="uploadFiles" name="uploadFiles" validate="{maxlength:1000}" />
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

</div>
<div  title="反馈信息">
    <form id="form1" action="eq/docimasyFksAction/save.do" method="get" getAction="eq/docimasyFksAction/fdetail1.do?id=${param.id}" >
        <input type="hidden" id="id" name="id"></input>
          <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
       <tr> 
        <td class="l-t-td-left"> 检定状态</td>
        
       <%--  <td class="l-t-td-right"><u:combo name="box_status" code="TJY2_EQ_BOXSTATUS" attribute="initValue:'02'"/></td>	 --%>
         <td class="l-t-td-right"> 
         <u:combo   name="status" code="TJY2_DOCIMASY_STATUS" />
        </td>
        <td class="l-t-td-left"> 检定日期</td>
        <td class="l-t-td-right"> 
        <input name="practicalTime" type="text" ltype='date' ligerui="{format:''}" validate="{required:true}}"/>
        </td>
       </tr>
      <tr>
        <td class="l-t-td-left"> 检定结果</td>
        <td colspan="3" class="l-t-td-right"> 
        <textarea class="l-textarea" rows="5" name="result"  validate="{maxlength:200,required:true}"></textarea>
        </td>
      </tr>
      <tr>
        <td class="l-t-td-left"> 备注</td>
        <td colspan="3" class="l-t-td-right"> 
        <textarea class="l-textarea" rows="5" name="remark"  validate="{maxlength:500}"></textarea>
        </td>
      </tr>
      </table>
    </form>
    <script type="text/javascript">
	        $("#form1").initFormList({
	        	/* var docimasy_status=<u:dict code="TJY2_DOCIMASY_STATUS" /> */
				// opType:"auto",//数据保存模式，发现actionParam的关联信息为空时会自动调用opTypeFun，默认为auto
				// opTypeFun:save,//自动保存调用方法，opType为atuo时才会用到,默认为save
				// action:"",//保存数据或其它操作的action
	            actionParam:{"docimasyFk.id":$("#form>[name=id]")}, //保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把from1下的name为id的值带上去
	            delAction:"eq/docimasyFksAction/delete.do",//删除数据的action
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
				
	            columns:[
	                //此部分配置同grid
	                { display:'主键', name:'id', width:50, hide:true},
	                { display:'检定状态', name:'status', width:110,
	                	render:function(rowData){
	                    	if(rowData.status=="jxz"){
	                        	return "进行中";
	                    	}else if(rowData.status=="ywc"){
	                        	return "已完成";
	                    	}else if(rowData.status=="wxf"){
	                        	return "未下发";
	                    	}else if(rowData.status=="wwc"){
	                        	return "未完成";
	                    	}else if(rowData.status=="wks"){
	                        	return "为开始";
	                    	}
	                	}},
	                { display:'实际检定日期', name:'practicalTime', width:170},
	                { display:'检定结果', name:'result', width:90},
	                { display:'备注', name:'remark', width:170}
	            ]
	        });
    	</script>
</div>
</div>
</body>
</html>











