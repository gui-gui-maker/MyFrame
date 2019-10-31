<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>详情页面</title>
<%@include file="/k/kui-base-form.jsp" %>

 <script type="text/javascript" src="app/fwxm/scientific/instruction/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
var pageStatus="${param.status}";
	$(function () {
		$("#formObj").initForm({    //参数设置示例
			toolbar : [ {
   	        	text : '保存',
   	        	id : 'save',
   		        icon : 'save',
   		        click : save
   	          },{
     	        	text : '提交',
       	        	id : 'submit',
       		        icon : 'save',
       		        click : submit
       	          }, {
   	           	text : '关闭',
   		        id : 'close',
   		        icon : 'cancel',
   		        click : close
   	          } ],
			success: function (response) {
				if(response.success){
					top.$.notice("保存成功！",3);
					api.data.window.Qm.refreshGrid();
					api.close();
				}
				else{
					$.ligerDialog.error("操作失败！<br/>" + response.msg);
				}
			},
			getSuccess: function(resp){
				 if(resp.data){
					 var readers = [];
					 if(resp.data.projectCyManId!=null){
						 var id=resp.data.projectCyManId.split(",");
						 var name=resp.data.projectCyMan.split(",");
	                     for(var i=0;i<id.length;i++){
	                         readers.push({
	                             types : "reader",
	                             name: name[i],
	                             id: id[i]
	                         });
	                     } 
	                     addReader(readers,false);
					 }
                     
                 }
			}
		});
	});
	

	function submit(){
		if($('#formObj').validate().form()){
			var tjUserId="<sec:authentication property='principal.id' htmlEscape='false' />";
			var tjUserName="<sec:authentication property='principal.name' htmlEscape='false' />";
			if(${param.tj!=0}){//审批、审核
				var showOrHide="hide";
				 if(${param.tj==3}){
					 showOrHide="show";
				 }
				 top.$.dialog({
						width : 400,
						height : 200,
						lock: true,
						parent: null,
						data: {window: window,showOrHide:showOrHide},
						title: "审核结论",
						content: 'url:app/fwxm/scientific/instruction/choose_opinion.jsp',
						cancel: true,
						ok : function() {
							  var data = this.iframe.contentWindow.getSelectResult();
							 doSumbit(data.opinion,data.remark,data.tjlx);
						}
				 });
			}else{
	     	   doSumbit(null,null);
			}
			
		}
	}

    function doSumbit(opinion,remark,tjlx){
    	var formData = $("#formObj").getValues();
    	console.log(formData);
        formData["tjType"]="2";
        if(${param.tj!=0}){//审核环节
            formData["audit_opinion"]=remark;
            formData["opinion"]=opinion;
            formData["tjlx"]=tjlx;
        }
       var instruction=$.ligerui.toJSON(formData);
    $("body").mask("提交中...");
       $.ajax({
           url: "com/tjy2/instructionRw/saveBasic.do",
           type: "POST",
           data:{"instruction":instruction},
           success : function(data, stats) {
				$("body").unmask();
				if (data["success"]) {
					if("${param.tj!=1}"){
						top.$.dialog.notice({
							content : '提交成功'
						});
						api.data.window.Qm.refreshGrid();
						api.close();
					}
				} else {
					$.ligerDialog.error('提示：' + data.message);
				}
			},
           error : function(data) {
               $("body").unmask();
               $.ligerDialog.error('保存数据失败！');
           }
           });
       
    }
	function save(){
		if($('#formObj').validate().form()){
    	var formData = $("#formObj").getValues();
    	
		$.ajax({
	           url: "com/tjy2/instructionRw/saveBasic.do",
	           type: "POST",
	           data:{"instruction":$.ligerui.toJSON(formData)},
	           success : function(data, stats) {
					$("body").unmask();
					if (data["success"]) {
							top.$.dialog.notice({
								content : '提交成功'
							});
							api.data.window.Qm.refreshGrid();
							api.close();
					} else {
						$.ligerDialog.error('提示：' + data.message);
					}
				},
	           error : function(data) {
	               $("body").unmask();
	               $.ligerDialog.error('保存数据失败！');
	           }
	           });
		}
	}

	function close(){	
	 api.close();
	}		
	function selectType(){
		top.$.dialog({
			parent: api,
			width : 700, 
			height : 500, 
			lock : true, 
			title:"选择项目",
			content: 'url:app/fwxm/scientific/instruction/instruction_type_list.jsp',
			data : {"window" : window}
		});
	}
	function selectTypeBack(id,type,types,requirements){
		$("#projectName").val(type);
		$("#tjy2InstructionInfoId").val(id);
		$("#requirements").val(requirements);
		$("#type").val(types);
	}
	   function selectReaders(){
	        
           var readers = $("#form1").data("readers"); 
           selectUnitOrUser1("5",1,"","",function(datas){
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
      
      var CCnames='';//参与人姓名
      var CCids='';//参与人id
       function addReader(newReaders,isNew){
       var names = '';
       var ids = '';
       var repids='';
       var repNames='';
       //alert(names);
        for(var i in newReaders){
               if(CCids.indexOf(newReaders[i].id)==-1){
                   names = names+newReaders[i].name+",";
                   ids = ids+newReaders[i].id+",";
                   var mtext='<span class="label label-read" id="' + (isNew?newReaders[i].id:newReaders[i].id) + '"><span class="text">' + newReaders[i].name;
                   if(pageStatus!="detail"){
                      mtext = mtext+'</span><span class="del btn btn-lm" onclick="deleteReader(\'' + (isNew?newReaders[i].id:newReaders[i].id) + '\',' + isNew + ')"><img src="k/kui/images/icons/16/delete1.png">&nbsp;</span></span>';
                   }
                   if(pageStatus=="detail"){
                      mtext = mtext+','
                      }
                   $("#reader_td").prepend(mtext);
               } else if (newReaders[i].name){
                   repNames = repNames+newReaders[i].name+",";
               }
          }
          //alert(names);
          if(repNames != ""){
                   $.ligerDialog.warn("人员："+"<span style='color:red;'>"+repNames.substring(0,repNames.length-1)+"</span>"+" 已存在！");
          }
          CCnames = CCnames+names;
          CCids = CCids+ids;
          /*if(CCnames!=""){
             CCnames = CCnames.substring(0,CCnames.length-1);
          }
          if(CCids!=""){
             CCids = CCids.substring(0,CCids.length-1);
          }*/
          $("#projectCyMan").val(CCnames);
          $("#projectCyManId").val(CCids);
          //alert($("#clCcr").val());
          //alert($("#clCcid").val());
       
       
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
           $("#projectCyMan").val(CCnames);
          $("#projectCyManId").val(CCids);
       }
       function selectUser() {
	        selectUnitOrUser1(1, 0, "projectHeadId", "projectHead");
	    }
       function selectUser1() {
	        selectUnitOrUser1(1, 0, "reviewId", "reviewMan");
	    }
       function selectUser2() {
	        selectUnitOrUser1(1, 0, "auditId", "auditMan");
	    }
       function selectUser3() {
	        selectUnitOrUser1(1, 0, "sign_id", "sign_man");
	    }
       function selectUser4() {
	        selectUnitOrUser1(1, 0, "kj_id", "kj_man");
	    }
       function selectUser5() {
	        selectUnitOrUser1(1, 0, "fzr_id", "fzr_man");
	    }
       function selectUser6() {
	        selectUnitOrUser1(1, 0, "zr_id", "zr_man");
	    }
</script>
<style type="">
.l-t-td-center{text-align: center;
	width: 200px;
}
.l-t-td-input{
padding:5px 15px 5px 0px;border:1px solid ;border-bottom: 1px;
}
</style>
</head>
<body>
<form id="formObj"  getAction="com/tjy2/instructionRw/detail.do?id=${param.id}">
   <input type="hidden" name="id"/>
    <input type="hidden" name="status" value="0"/>
   <table  class="l-detail-table" width="80%">
   		<tr>
   			<td colspan="2" class="l-t-td-left" style="text-align: center;font-size: 20px;"><b>四川省特种设备检验研究院科研技术委员会<br />作业指导书制（修订）任务书</b></td>
   		</tr>
   </table>
    <table cellpadding="3"  border="1" cellspacing="0" cellpadding="0" Align="center" width="80%">
    <tr>
    		<td class="l-t-td-center">编 &thinsp;&thinsp;&thinsp;&thinsp;&thinsp;&thinsp;&thinsp;&thinsp;&thinsp;&thinsp;&thinsp;号：</td>
            <td colspan="2" style="padding:5px 15px 5px 0px;border:0px solid #CFE3F8;border-top: 0px;">
<!--             	<input name="rwNumber" id="rwNumber"  type="text" ltype="text" ligerui="{readonly:true}" /> -->
            	
            	<c:if test="${param.rw_number!='' }">
            	<input name="rwNumber" id="rwNumber"  type="text" ltype="text" ligerui="{readonly:true}" />
            	</c:if>
            	<c:if test="${param.rw_number=='' }">
            		<input name="number" id="number" type="text" ltype="select" validate="{required:true}" ligerui="{
					initValue:'',
					readonly:true,
					data:[{'id':'1','text':'ZDSJ'},{'id':'2','text':'ZDSC'}]
					}"/>
            	</c:if>
            	
            </td>
        </tr>
        <tr>
            <td class="l-t-td-center">项 目 名 称：</td>
            <td class="l-t-td-input">
              <input name="tjy2InstructionInfoId" id="tjy2InstructionInfoId"  type="hidden"/>
            	<input name="projectName" id="projectName" type="text" ltype="text" validate="{required:true,maxlength:100}" 
            	onclick="selectType()"
				ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectType()}}]}"/>
            </td>
        </tr>
        <tr>
             <td class="l-t-td-center">项目负责人：</td>
            <td class="l-t-td-input" >
            <input name="projectHeadId" id="projectHeadId" type="hidden"></input>
            	<input name="projectHead" id="projectHead" type="text" ltype="text" validate="{required:true,maxlength:32}" 
            	onClick="selectUser()" readonly="readonly" />
            </td>
            
        </tr>
        <tr>
          <td class="l-t-td-center">项目参与人员：</td>
            <td class="l-t-td-input"  id="reader_td">
            <input name="projectCyManId" id="projectCyManId" type="hidden"></input>
            <input name="projectCyMan"  id="projectCyMan" type="hidden"  />
             <c:if test="${param.status!='detail' }">
           <span class="l-button label" title="添加参与人员">
                                    <span  class="l-a l-icon-add"  onclick="selectReaders();">&nbsp;</span>
                                    </c:if>
            </td>
        </tr>
         <tr>
         <td class="l-t-td-center">标准审查人员：</td>
            <td class="l-t-td-input">
             <input name="reviewId" id="reviewId" type="hidden"></input>
            	<input name="reviewMan" id="reviewMan" type="text" ltype="text" onClick="selectUser1()" readonly="readonly"  validate="{required:true,maxlength:32}" />
            </td>
        </tr> 
        <tr>
        	<td class="l-t-td-center">审 核 人 员：</td>
            <td class="l-t-td-input">
            <input name="auditId" id="auditId" type="hidden"></input>
            	<input name="auditMan" id="auditMan" type="text" ltype="text" onClick="selectUser2()" readonly="readonly" validate="{required:true,maxlength:32}" />
            </td>
        </tr>
         <tr>
         <td class="l-t-td-center">批 准 人 员：</td>
            <td class="l-t-td-input">
            <input name="sign_id" id="sign_id" type="hidden"></input>
            	<input name="sign_man" id="sign_man" type="text" ltype="text" onClick="selectUser3()" readonly="readonly" validate="{required:true,maxlength:32}" />
            </td>
        </tr> 
        <tr>
            <td class="l-t-td-center">制定或修订：</td>
            <td class="l-t-td-input" >
            <u:combo name="type"  code="instruction_type1" validate="required:false" />
            </td>
        </tr>
        <tr>
          <td class="l-t-td-center">制 定 内 容：</td>
            <td class="l-t-td-input">
            <textarea name="developContent" rows="2" cols="25" class="l-textarea" validate="{maxlength:4000}"></textarea>
            	<!-- <input name="developContent" id="developContent" type="text" ltype="text" validate="{required:true,maxlength:1000}" /> -->
            </td>
        </tr>
        <tr>
            <td class="l-t-td-center">要&thinsp;&thinsp;&thinsp;&thinsp;&thinsp;&thinsp;&thinsp;&thinsp;&thinsp;&thinsp;&thinsp;&thinsp;求：</td>
            <td class="l-t-td-input" >
            
            <textarea name="requirements" rows="2" cols="25" class="l-textarea" validate="{maxlength:4000}"></textarea>
            	<!-- <input name="requirements" id="requirements" type="text" ltype="text" validate="{required:true,maxlength:1000}" /> -->
            </td>
           
        </tr>
         <tr>
            <td class="l-t-td-center">协调专家支持或购<br/>买服务：</td>
            <td class="l-t-td-input">
            	<input name="commitmentsType" id="commitmentsType" type="text" ltype="text" validate="{required:false,maxlength:32}" />
            </td>
            
        </tr>
        <tr>
        	<td class="l-t-td-center">项目经费预算：</td>
            <td class="l-t-td-input">
            	<input name="budgetMoney" id="budgetMoney" type="text" ltype="text" validate="{required:false,maxlength:32}" />
            </td>
        </tr>
        
        <tr>
            <td class="l-t-td-center">项目开始日期：</td>
            <td class="l-t-td-input">
            	<input name="projectStartDate" id="projectStartDate" type="text" ltype="date" validate="{required:false}" />
            </td>
            
        </tr>
        <tr>
        	<td class="l-t-td-center">项目结束日期：</td>
            <td class="l-t-td-input">
            	<input name="projectEndDate" id="projectEndDate" type="text" ltype="date" validate="{required:false}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-center">项目验收日期：</td>
            <td class="l-t-td-input">
            	<input name="projectAcceptanceDate" id="projectAcceptanceDate" type="text" ltype="date" validate="{required:false}" />
            </td>
        </tr>
       <!--  <tr>
            <td class="l-t-td-center">项目填报开始日期：</td>
            <td class="l-t-td-input">
            	<input name="limit_start_date" id="limit_start_date" type="text" ltype="date" validate="{required:true}" />
            </td>
        </tr>
        <tr>
        	<td class="l-t-td-center">项目填报结束日期：</td>
            <td class="l-t-td-input">
            	<input name="limit_end_date" id="limit_end_date" type="text" ltype="date" validate="{required:true}" />
            </td>
        </tr> -->
         <tr>
         <td class="l-t-td-center">科技委办公室：</td>
            <td class="l-t-td-input">
             <input name="kj_id" id="kj_id" type="hidden"></input>
            	<input name="kj_man" id="kj_man" type="text" ltype="text" onClick="selectUser4()" readonly="readonly"  validate="{required:true,maxlength:32}" />
            </td>
         
        </tr> 
        <tr>
        	<td class="l-t-td-center">常务副主任委员：</td>
            <td class="l-t-td-input">
            <input name="fzr_id" id="fzr_id" type="hidden"></input>
            	<input name="fzr_man" id="fzr_man" type="text" ltype="text" onClick="selectUser5()" readonly="readonly" validate="{required:true,maxlength:32}" />
            </td>
        </tr>
         <tr>
         <td class="l-t-td-center">主任委员批准：</td>
            <td class="l-t-td-input">
            <input name="zr_id" id="zr_id" type="hidden"></input>
            	<input name="zr_man" id="zr_man" type="text" ltype="text" onClick="selectUser6()" readonly="readonly" validate="{required:true,maxlength:32}" />
            </td>
        </tr> 
    </table>
</form>
</body>
</html>
