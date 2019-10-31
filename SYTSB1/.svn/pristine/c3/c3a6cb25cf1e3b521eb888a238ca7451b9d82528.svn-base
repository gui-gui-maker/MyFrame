<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
    <script type="text/javascript">
    var pageStatus = "${param.pageStatus}";
        $(function () {
        	if ("${param.pageStatus}"=="add" || "${param.pageStatus}"=="modify" ){
        		 $("#wwcyy").hide();//隐藏
        		  $("#wcqk").hide();//隐藏 
        		  document.getElementById("missionContent").style.height="200px";
        	}
			//如果不设置额外参数，不用调用此方法，初始化时会自动调用
        	var tbar=[{ text: '保存', id: 'up', icon: 'save', click: save},
        	            { text: '关闭', id: 'close', icon:'cancel', click:function(){
        	            	api.data.window.Qm.refreshGrid();
        	            	api.close();}}];
            if ("${param.pageStatus}"=="detail")
            var tbar=[{ text: '打印', id: 'print', icon: 'print', click: print}];
        	$("#form").initForm({
                showToolbar: true,
                toolbarPosition: "bottom",
                toolbar: tbar,
                getSuccess: function(res){ 
                    var readers = [];
                    var names = [];
                    var ids = [];//alert(res.data.dutyName);
                    if(res.data.responsiblePerson){
                         names = res.data.responsiblePerson.split(",");
                         ids = res.data.responsiblePersonid.split(",");
                         for(var i=0;i<names.length;i++){
                            readers.push({
                                types : "reader",
                                name: names[i],
                                id: ids[i]
                            });
                        } 
                        addReader(readers,false);
                    }
                }
        	});
         $('#responsiblePerson').autocomplete("employee/basic/searchEmployee.do", {
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
         	// alert($("#mainDutyId").val());
         	 document.getElementById("responsiblePersonid").value = $("#mainDutyId").val();
         });
            
        	$("body").append('<object style="height:1px;" id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0><embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed></object>');
			 $('#peopleConcerned').autocomplete("employee/basic/searchEmployee.do", {
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
              //     alert(row.mobileTel);
               });
               
                $('#department').autocomplete("employee/basic/searchOrg.do", {
                   max: 12,    //列表里的条目数
                   minChars: 1,    //自动完成激活之前填入的最小字符
                   width: 200,     //提示的宽度，溢出隐藏
                   scrollHeight: 300,   //提示的高度，溢出显示滚动条
                   scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
                   matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
                   autoFill: false,    //自动填充
                   formatItem: function(row, i, max) {
                   //alert(row);
                       return row.orgName;
                   },
                   formatMatch: function(row, i, max) {
                       return row.orgName;
                   },
                   formatResult: function(row) {
                       return row.orgName;
                   }
               }).result(function(event, row, formatted) {
 //                  alert(row.orgId);
  document.getElementById("departmentId").value = row.orgId
            	   
               });

        });
        
        
        
        function save(){
        	/* alert($("#departmentId").val());
        	alert($("#responsiblePersonid").val()); */
             var obj=$("#form").validate().form();
             if(obj){
            	 //if($("#department").val() != "" && $("#department").val() != undefined){
                    if($("#departmentId").val() == "" || $("#departmentId").val() == undefined){ 
                       $.ligerDialog.warn("部门id为空，请重新选择部门！");
                       return;
                    } else if($("#responsiblePersonid").val() == "" || $("#responsiblePersonid").val() == undefined ){
                	   $.ligerDialog.warn("责任人id为空，请重新选择责任人！");
                	   return;
                   }
                // }
                 $("#form").submit();
             }else{
                 return;
            }
        }
        function close(){
        	if(api.data.window.submitAction)
        		api.data.window.submitAction();
        		api.close();
        }
        
        

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
                            $("#responsiblePersonid").val(p.id);
                            $("#responsiblePerson").val(p.name);
                            $("#department").val(p.org_name);
                            $("#departmentId").val(p.org_id);
							
                        }
                    });
                }
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
            $("#responsiblePerson").val(CCnames.substring(0, CCnames.length-1));
            $("#responsiblePersonid").val(CCids);
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
             $("#responsiblePerson").val(CCnames);
             $("#responsiblePersonid").val(CCids);
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
                            $("#departmentId").val(p.id);
                            $("#department").val(p.name);
                        }
                    });
                }

    </script>
    <style type="">
    #missionContent{height: 70px}
    </style>
</head>
<body>


    <form id="form" action="office/ywhbsgzAction/save.do" getAction="office/ywhbsgzAction/detail.do?id=${param.id}">
     <input type="hidden" id="departmentId" name="departmentId"/>
     <input type="hidden" id="responsiblePersonid" name="responsiblePersonid"/>
        <input type="hidden" name="id" id="id"/>
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
		  		          <tr> 
        <td class="l-t-td-left"> 任务开始时间</td>
        <td class="l-t-td-right"> 
        <input  ligerui="{initValue:'',format:'yyyy-MM-dd'}" name="startTime" type="text" ltype='date' ligerui="{format:''}"  validate="{required:true,maxlength:2000}}"/>
         <td class="l-t-td-left"> 任务结束时间</td>
        <td class="l-t-td-right"> 
        <input ligerui="{initValue:'',format:'yyyy-MM-dd'}" name="endTim" type="text" ltype='date' ligerui="{format:''}" validate="{required:true,maxlength:2000}}"/>
        </td>
        </td>
       </tr>
       <tr> 
       
        <td class="l-t-td-left"> 责任部门</td>
        <td class="l-t-td-right"> 
       <input  validate="{maxlength:50,required:true}" readonly="readonly" ltype="text"  name="department" id="department"  type="text" onclick="chooseOrg();" ligerui="{iconItems:[{icon:'org',click:chooseOrg}]}"/>
        </td>
        <td class="l-t-td-left">责任人</td>
		<td class="l-t-td-right" id="reader_td">
		<input type="hidden" name="responsiblePerson" id="responsiblePerson"  validate="{required:true}"/>
			 <c:if test="${param.pageStatus=='modify' or param.pageStatus=='add'}"><span class="l-button label" title="责任人">
                          <span  class="l-a l-icon-add"  onclick="selectReaders();">&nbsp;</span>
                         </c:if>
		
		</td>
        <%-- <td class="l-t-td-left"> 责任人</td>
        <td class="l-t-td-right"> 
         <input  ltype='text' readonly="readonly" value="<%=users %>" id="responsiblePerson" name="responsiblePerson" type="text" id="Reviewers"  onclick="choosePerson();" ligerui="{iconItems:[{icon:'user',click:choosePerson}]}"/>
        </td> --%>
       </tr>
       <tr > 
       <td class="l-t-td-left"> 任务内容</td>
        <td colspan="3" class="l-t-td-right"> 
        <textarea  id="missionContent" name="missionContent" type="text" ltype='text' validate="{required:true,maxlength:2000}"></textarea>
        </td>
        </tr>
          <tr id = "wcqk">
       <td class="l-t-td-left"> 完成情况</td>
        <td colspan="3"  class="l-t-td-right"> 
        <textarea Style="height: 70px" validate="{maxlength:2000}"  name="performance" type="text" ltype='text' ></textarea>
        </td>
       </tr>
        <tr id = "wwcyy">
       <td  class="l-t-td-left"> 未完成原因</td>
        <td colspan="3" class="l-t-td-right"> 
        <textarea Style="height: 70px" validate="{maxlength:2000}" name="unfinishedReason" type="text" ltype='text' ></textarea>
        </td>
       </tr>
      </table>
    </form>
</body>
</html>
