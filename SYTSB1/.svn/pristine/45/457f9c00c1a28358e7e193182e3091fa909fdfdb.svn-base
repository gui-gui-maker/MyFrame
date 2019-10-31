<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>

<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    CurrentSessionUser user=(CurrentSessionUser)request.getSession().getAttribute("currentSessionUser");

%>
<%CurrentSessionUser useres = SecurityUtil.getSecurityUser();
String users=useres.getName();
String userid= useres.getId();%>
<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	String nowTime=""; 
	nowTime = dateFormat.format(new Date());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title></title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="app/office/js/jquery.autocomplete.js"></script>
<link rel="Stylesheet" href="app/office/css/jquery.autocomplete.css" />
<script type="text/javascript">
var pageStatus = "${param.pageStatus}";
	$(function () {
//      如果不设置额外参数，不用调用此方法，初始化时会自动调用
        $("#form1").initForm({
        	toolbar: [{text: "保存", icon: "save", click: save},
    				  {text: "关闭", icon: "cancel", click: function(){
    					  api.data.window.Qm.refreshGrid();
    					  api.close();
    				}
    			}
    		],toolbarPosition: "bottom",
            success : function(responseText) {//处理成功
                if (responseText.success) {
                    top.$.dialog.notice({content:'保存成功'});
                    api.data.window.Qm.refreshGrid();
                    $("#id").val(responseText.data.id);
                    $("#ids").val(responseText.data.id);
                    $("#phId").val(responseText.data.id);
                } else {
                    $.ligerDialog.error('保存失败' + responseText);
                }
            },
            getSuccess:function(res){
            	var readers = [];
                var names = [];
                var ids = [];//alert(res.data.mainDutyName);
                if(res.data.mainDutyName){
                     names = res.data.mainDutyName.split(",");
                     ids = res.data.mainDutyId.split(",");
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
            afterParse:function(formObj){//form表单完成渲染后，回调函数
			}
        });
	
	$('#mainLeadName,#mainDutyName').autocomplete("employee/basic/searchEmployee.do", {
		 max: 12,    //列表里的条目数
         minChars: 1,    //自动完成激活之前填入的最小字符
         width: 200,     //提示的宽度，溢出隐藏
         scrollHeight: 300,   //提示的高度，溢出显示滚动条
         scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
         matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
         autoFill: false,    //自动填充
         formatItem: function(row, i, max) {
             return row.name + '   ' + row.mobileTel;
         },
         formatMatch: function(row, i, max) {
             return row.name + row.mobileTel;
         },
         formatResult: function(row) {
             return row.name;
         }
     }).result(function(event, row, formatted) {
     	 $("#mainLeadId").val(row.id);
     	 $("#mainDutyId").val(row.id);
     });
	
});

	
	function choosePerson(){
	    top.$.dialog({
	        width: 800,
	        height: 450,
	        lock: true,
	        parent: api,
	        title: "选择人员",
	        content: 'url:app/common/person_choose.jsp',
	        cancel: true,
	        ok: function(){
	            var p = this.iframe.contentWindow.getSelectedPerson();
	            if(!p){
	                top.$.notice("请选择人员！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
	                return false;
	            }
	            $("#mainLeadId").val(p.id);
	            $("#mainLeadName").val(p.name);
	            $("#mainDep").val(p.org_name);
	            $("#mainDepId").val(p.org_id);
	        }
	    });
	}


	function chooseOrg(){
	    top.$.dialog({
	        width: 800,
	        height: 450,
	        lock: true,
	        parent: api,
	        title: "选择部门",
	        content: 'url:app/common/org_choose.jsp',
	        cancel: true,
	        ok: function(){
	            var p = this.iframe.contentWindow.getSelectedPerson();
	            if(!p){
	                top.$.notice("请选择部门！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
	                return false;
	            }
	            $("#mainDepId").val(p.id);
	            $("#mainDep").val(p.name);
	        }
	    });
	}   
	
	function choosePerson1(){
	    top.$.dialog({
	        width: 800,
	        height: 450,
	        lock: true,
	        parent: api,
	        title: "选择人员",
	        content: 'url:app/common/person_choose.jsp',
	        cancel: true,
	        ok: function(){
	            var p = this.iframe.contentWindow.getSelectedPerson();
	            if(!p){
	                top.$.notice("请选择人员！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
	                return false;
	            }
	            $("#mainDutyId").val(p.id);
	            $("#mainDutyName").val(p.name);
	        }
	    });
	}


	function chooseOrg1(){
	    top.$.dialog({
	        width: 800,
	        height: 450,
	        lock: true,
	        parent: api,
	        title: "选择部门",
	        content: 'url:app/common/org_choose.jsp',
	        cancel: true,
	        ok: function(){
	            var p = this.iframe.contentWindow.getSelectedPerson();
	            if(!p){
	                top.$.notice("请选择部门！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
	                return false;
	            }
	            $("#phDepId").val(p.id);
	            $("#phDepName").val(p.name);
	        }
	    });
	}   
	

	function save(){
		 var obj=$("#form1").validate().form();
		 if(obj){
			 if($("#mainLeadName").val() !="" && $("#mainLeadName").val() != undefined){
	  		  if($("#mainLeadId").val() == "" || $("#mainLeadId").val() == undefined){
	       		 $.ligerDialog.warn("牵头人姓名id为空,请重新选择!");
	       		 return;
	       	 }
	        }
	       if($("#mainDep").val() != "" && $("#mainDep").val() != undefined){
	       	if($("#mainDepId").val() == "" || $("#mainDepId").val() == undefined){
	            $.ligerDialog.warn("部门id为空，请重新选择部门！");
	             return;
	         }
	       }
	       if($("#mainDutyName").val() !="" && $("#mainDutyName").val() != undefined){
		  		  if($("#mainDutyId").val() == "" || $("#mainDutyId").val() == undefined){
		       		 $.ligerDialog.warn("主责任人姓名id为空,请重新选择!");
		       		 return;
		       	 }
		        }
		     if($("#finishLimit").val() =="" || $("#finishLimit").val() ==undefined){
		    	 $.ligerDialog.warn("完成时限不能为空,请选择!");
	       		 return;
		     }
	       var formData = $("#form1").getValues();
           $("body").mask("正在保存......");
           $.ajax({
              url: "weighty/Task/saveWei.do",
              type: "POST",
              datatype: "json",
              contentType: "application/json; charset=utf-8",
              data: $.ligerui.toJSON(formData),
              success: function (data, stats) {
                  $("body").unmask();
                  if (data.success) {
                      top.$.dialog.notice({content:'保存成功！'});
                      $("#id").val(data.data.id);
                      $("#ids").val(data.data.id);
                      $("#phId").val(data.data.id);
                      api.data.window.Qm.refreshGrid();
                  }else{
                      $.ligerDialog.error('提示：' + data.msg);
                      api.data.window.Qm.refreshGrid();
                  }
              },
              error: function (data,stats) {
                  $("body").unmask();
                  $.ligerDialog.error('提示：' + data.msg);
              }
          });
		 }else{
			 return;
		 }
	}
	
	function selectReaders(){
        
        var readers = $("#form1").data("readers"); 
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
    var CCnames='';//负责人姓名
    var CCids='';//负责人id
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
                 if(pageStatus!="detail"){
                    mtext = mtext+'</span><span class="del btn btn-lm" onclick="deleteReader(\'' + (isNew?newReaders[i].id:newReaders[i].id) + '\',' + isNew + ')"><img src="k/kui/images/icons/16/delete1.png">&nbsp;</span></span>';
                 }
                 if(pageStatus=="detail"){
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
        $("#mainDutyName").val(CCnames.substring(0, CCnames.length-1));
        $("#mainDutyId").val(CCids);
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
         $("#mainDutyName").val(CCnames);
         $("#mainDutyId").val(CCids);
     }
	  function chooseOrg(){
          top.$.dialog({
              width: 800,
              height: 450,
              lock: true,
              parent: api,
              title: "选择部门",
              content: 'url:app/common/org_choose.jsp',
              cancel: true,
              ok: function(){
                  var p = this.iframe.contentWindow.getSelectedPerson();
                  if(!p){
                      top.$.notice("请选择部门！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                      return false;
                  }
                  $("#mainDepId").val(p.id);
                  $("#mainDep").val(p.name);
              }
          });
      }

</script>

</head>

<body>
<div class="navtab">
<div  title="重大任务信息" lselected="true">
	<form id="form1" action="weighty/Task/saveWei.do" getAction="weighty/Task/detail.do?id=${param.id}">
		<input type="hidden" id="id" name="id"/>
		<input type="hidden" id="mainLeadId" name="mainLeadId"/>
		<input type="hidden" id="mainDutyId" name="mainDutyId"/>
		<input type="hidden" id="mainDepId" name="mainDepId"/>
	<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					日期
				</div>
			</legend>
		<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table" id="zlrw">
				
					<tr>
						<td class="l-t-td-left">完成时限</td>
						<td class="l-t-td-right"> <u:combo name="finishLimit"  code="TJY2_PART_TIME"  ></u:combo></td>
						<td class="l-t-td-left">开始日期</td>
						<td class="l-t-td-right"><input name="startTime" type="text" ltype="date" validate="{required:false}" 
        					ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="startTime"  
        					 readonly="readonly" value="<%=nowTime%>" /></td>
        				<td class="l-t-td-left">完成日期</td>
						<td class="l-t-td-right"><input name="finishTime" type="text" ltype="date" validate="{required:true}" 
        					ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="finishTime"  
        					 readonly="readonly"  /></td>
					</tr>
					</table>
				</fieldset>
					
					<fieldset class="l-fieldset">
					<legend class="l-legend">
						<div>
							重大任务
						</div>
					</legend>
		<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table" id="Weighty">
					
					<tr>
						<td class="l-t-td-left">牵头部门</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="mainDep" id="mainDep" readonly="readonly" validate="{required:true}"  onclick="click:chooseOrg()" ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}" /></td>
						<td class="l-t-td-left">牵头领导</td>
						<td class="l-t-td-right"><input name="mainLeadName" type="text" ltype="text"  id="mainLeadName" readonly="readonly" validate="{required:true,maxlength:50}"  onclick="choosePerson()" ligerui="{iconItems:[{icon:'user',click:choosePerson}]}"/></td>
					</tr>
					<tr>
						<td class="l-t-td-left">责任人</td>
						<td class="l-t-td-right" id="reader_td">
						<input type="hidden" name="mainDutyName" id="mainDutyName"  validate="{required:true}"/>
							 <c:if test="${param.pageStatus=='modify' or param.pageStatus=='add'}"><span class="l-button label" title="责任人">
				                          <span  class="l-a l-icon-add"  onclick="selectReaders();">&nbsp;</span>
				                         </c:if>
						
						</td>
						<!-- <td class="l-t-td-left">主责任人</td>
						<td class="l-t-td-right"><input name="mainDutyName" type="text" ltype="text"  id="mainDutyName"  readonly="readonly" validate="{required:true,maxlength:50}" onclick="choosePerson1()" ligerui="{iconItems:[{icon:'user',click:choosePerson1}]}"/></td> -->
					</tr>
					<tr>
						<td class="l-t-td-left">工作内容</td>
						<td class="l-t-td-right" colspan="3"><textarea name="taskContent" id="taskContent" rows="5" cols="25" class="l-textarea" validate="{maxlength:2000}"></textarea></td>
					</tr>
					</table>
				</fieldset>
				</form>
				</div>
<div title="配合部门" tabId="certTab">
	<form id="certForm" name="certForm" method="post" action="weighty/Dep/saveWei.do"   getAction="weighty/Dep/getdetail.do?id=${param.id}">
  	<input type="hidden" name="id" >
  	<input type="hidden" name="pkDepId">
  	<%-- <input type="hidden" value="${param.id}" id="ids" name="ids" /> --%> 
  	<%-- <input type="hidden" name="weightyTask.id" id="weightTaskId" value="${param.id}"/> --%>
  	<input type="hidden" name="phDepId" id="phDepId"/>
	<table cellpadding="3" cellspacing="0" class="l-detail-table"  >
		<tr>
	       <td class="l-t-td-left">配合部门</td>
	       <td class="l-t-td-right" ><input name="phDepName" id="phDepName" type="text" ltype="text" readonly="readonly"  onclick="click:chooseOrg1()" ligerui="{iconItems:[{icon:'org',click:chooseOrg1}]}"/></td>	
	    </tr>
	</table> 
      
      <script type="text/javascript">
	        $("#certForm").initFormList({
				// opType:"auto",//数据保存模式，发现actionParam的关联信息为空时会自动调用opTypeFun，默认为auto
				// opTypeFun:save,//自动保存调用方法，opType为atuo时才会用到,默认为save
				// action:"",//保存数据或其它操作的action
	            actionParam:{"weightyTask.id" : $("#id")}, //保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把from1下的name为id的值带上去
	            delAction:'weighty/Dep/getDelete.do',//删除数据的action
//				delActionParam:{ids:'id'},//默认为选择行的id
				 // getAction:"",//取数据的action
				 // getActionParam:{},//取数据时附带的参数，一般只在需要动态取特定值时用到
	             onSelectRow:function (rowdata, rowindex) {
	            	//ids = rowdata.id;
	                $("#certForm").setValues(rowdata);
	            },
	            success: function (data, stats) {
	                if (data.success) {
                		  top.$.notice("保存成功！");
	                } 
	            },
	            columns:[
	                 {display:'配合部门主键', name:'id', width:'1%', hide:true},
	                 {display:'重大任务主键', name:'pkDepId', width:'1%', hide:true},
	                 {display:'配合部门ID',name:'phDepId',width:'1%',hide:true},
	                 {display:'配合部门', name:'phDepName',width:'15%'}
	            	]
	        });
    	</script>
	</form>
	</div>
	
	
	
	</div>
</body>
</html>