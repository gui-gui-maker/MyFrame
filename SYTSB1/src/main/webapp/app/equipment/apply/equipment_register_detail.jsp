<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>

    <script type="text/javascript">
    
    var tbar="";
    $(function () {
    	
//      如果不设置额外参数，不用调用此方法，初始化时会自动调用
    	 var tbar=[{ text: '保存', id: 'up', icon: 'save', click: save},
    	            { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
    	$("#form1").initForm({
            showToolbar: false,
            toolbarPosition: "bottom",
            toolbar: tbar,
    	});
    });
    
function selEquipment(){	
    		top.$.dialog({
    			parent: api,
    			width : 800, 
    			height : 550, 
    			lock : true, 
    			title:"选择设备信息",
    			content: 'url:app/equipment/base/equipment_basic_choose_list.jsp',
    			data : {"parentWindow" : window}
    		});
    	}

     
    	function callBack(data){
    		$('#equipmentId').val(data.id);	// 设备ID
    		$('#equipmentName').val(data.eq_name);	//设备名称
    		/* $('#equipmentNumbe').val(data.eq_no);	//内部编号
    		$('#eqType').val(data.eq_model);	//设备型号
    		$('#eqNumbe').val(data.eq_no);	//出厂编号
    		
    		$('#period').val(data.eq_check_cycle);	//周期
    		$('#nextTime').val(data.eq_next_check_date);	//下次检定日期 */

    		
    		  
    	}
    	//选择人
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
                    $("#borrowerId").val(p.id);
                    $("#borrower").val(p.name);
                    $("#departmentId").val(p.org_id);
                    $("#departmentName").val(p.org_name);
                }
            });
        }
    	
    
    	
    	function save(){
/*     		
    		 alert($("#equipmentId").val());
 */    	     var obj=$("#form1").validate().form();
    	     if(obj){
    	         if($("#equipmentName").val() != "" && $("#equipmentName").val() != undefined){
    	           if($("#equipmentId").val() == "" || $("#equipmentId").val() == undefined){
    	               $.ligerDialog.warn("设备id为空，请重新选择设备！");
    	               return;
    	           }else if($("#borrowerId").val() == "" || $("#borrowerId").val() == undefined ){
    	        	   $.ligerDialog.warn("借用人id为空，请重新选择借用人！");
    	        	   return;
    	           }
    	         }
    	         $("#form1").submit();
    	     }else{
    	         return;
    	    }
    	}
    	
    	 
    </script>
</head>
<body>
    <form id="form1" action="equipmentUseRegisterAction/save.do" getAction="equipmentUseRegisterAction/detail.do?id=${param.id}">
    	<input id="id" name="id" type="hidden"/>
        <input id="equipmentId" name="equipId" type="hidden"/><!--设备id  -->
        <input id="borrowerId" name="borrowerId" type="hidden"/><!-- 借用人id -->
        <input id="departmentId" name="departmentId" type="hidden"/><!-- 部门id -->
        <input id="departmentName" name="departmentName" type="hidden"/><!-- 部门 -->
       <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
       <tr> 
        <td class="l-t-td-left"> 设备名称:</td>
        <td class="l-t-td-right"> 
         <input name="equipmentName"   id="equipmentName" type="text" ltype='text' validate="{required:true,maxlength:100}" validate="{required:true,maxlength:32}" onclick="selEquipment()" ligerui="{value:'',iconItems:[{icon:'add',click:function(){selEquipment()}}]}"/>
        </td>
         <td class="l-t-td-left"> 借用人:</td>
        <td class="l-t-td-right"> 
        <input  ltype='text' id="borrower" name="borrowerName" type="text" id="Reviewers" validate="{required:true,maxlength:32}" ligerui="{iconItems:[{icon:'user',click:choosePerson}]}"/></td>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 借用时间:</td>
        <td class="l-t-td-right"> 
         <input validate="{required:true,maxlength:32}" id="borrowerTime" name="borrowerTime"type="text" ltype="date" validate="{required:false}"ligerui="{initValue:'',format:'yyyy-MM-dd'}"  onchange="setValues(this.value,'advance_time')"/>
        </td>
           <td class="l-t-td-left"> 归还时间:</td>
        <td class="l-t-td-right"> 
         <input validate="{required:true,maxlength:32}" id="returnTime" name="returnTime"type="text" ltype="date" validate="{required:false}"ligerui="{initValue:'',format:'yyyy-MM-dd'}"  onchange="setValues(this.value,'advance_time')"/>
        </td>
       </tr>
         <tr> 
          <td class="l-t-td-left"> 使用地点:</td>
        <td class="l-t-td-right"> 
        <input name="useSite" type="text" ltype='text' validate="{required:true,maxlength:2000}"/>
        </td>
        <td class="l-t-td-left"> 状态:</td>
        <td class="l-t-td-right"> 
        <u:combo   name="status" validate="required:true" attribute="initValue:'01'"  code="TJY2_SBDJZT" ></u:combo>
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left"> 备注:</td>
        <td colspan="3" class="l-t-td-right"> 
        <textarea  rows="4" name="remark" type="text" ltype='text' validate="{required:true,maxlength:2000}"></textarea>
        </td>
      </tr>
      </table>
    </form> 




</div>

</body>
</html>
