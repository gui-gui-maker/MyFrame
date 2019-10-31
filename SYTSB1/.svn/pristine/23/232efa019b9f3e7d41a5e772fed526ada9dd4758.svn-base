<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        $(function () {
			$("#form1").initForm({    //参数设置示例
				success: function (response) {
					if(response.success){
						top.$.notice("保存成功！",3);
						api.data.window.Qm.refreshGrid();
						api.close();
					}
					else{
						$.ligerDialog.error("操作失败！<br/>" + response.msg);
					}
				}
			});
		});
    </script>
     <script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
     <script type="text/javascript" src="k/kui/frame/ligerTree.js"></script>
     <script type="text/javascript">
     //單據等只能輸入正整數
     //通过2步做到输入的为非负数
     //1.去掉小数点
     //2.保证只能数字
     function NonNegative(obj) {
      var inputChar = event.keyCode;
      //alert(event.keyCode);
      
      //1.判断是否有多于一个小数点
      if(inputChar==190 ) {//输入的是否为.
       var index1 = obj.value.indexOf(".") + 1;//取第一次出现.的后一个位置
       var index2 = obj.value.indexOf(".",index1);
      
        
        
        obj.value = obj.value.substring(0,index2);
        index2 = obj.value.indexOf(".",index1);
      
      }
      //2.如果输入的不是.或者不是数字，替换 g:全局替换
      obj.value = obj.value.replace(/[^(\d|.)]/g,"");
     }
     
     
     </script>
   
</head>
<body>
<form id="form1" action="dining/diningWindow/save.do" method="post" getAction="dining/diningWindow/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id" value="${param.id}"/>
    <table cellpadding="3" class="l-detail-table has-head-image" style="margin:0px;padding:0px" width="400px">
    	<tr>
	       <td class="l-t-td-left">窗口号：</td>
	       <td class="l-t-td-right">
	       		<!-- <input name="windowNumber" type="text" ltype="text" validate="required:true"/> -->
	       		 <input  validate="{maxlength:2}" ltype='text' name="windowNumber" onkeydown="NonNegative(this)" type="text" id="windowNumber" title="只能输入数字" class="underlineinput" /></td>
		  </td>
	    </tr>
	    <tr>
	       <td class="l-t-td-left">用户：</td>
	       <td class="l-t-td-right">
	       		<!-- <input name="userid" type="text" ltype="text" validate="required:true"/> -->
	       		 <input name="employeeId" id="employeeId" type="hidden" />
                 <input name="employeeName" id="employeeName" type="hidden" />
                 <c:if test="${param.pageStatus=='modify' or param.pageStatus=='add'}">
	       		 	<span class="l-button label" title="添加用户">
                    <span  class="l-a l-icon-add"  onclick="selectReaders();">&nbsp;</span>
                </c:if>
	       		 <span  id="append"></span>
		  </td>
	    </tr>
	    <tr>
	       <td class="l-t-td-left">状态：</td>
	       <td class="l-t-td-right">
                 <input name="windowStatus" id="windowStatus" type="text" ltype="select" ligerUi="{initValue:'0',data:[{id:'0',text:'关闭'},{id:'1',text:'开启'}]}" validate="{required:true}"/>
                
		  </td>
	    </tr>
    </table>
</form>
</body>
<script>
 function selectReaders(){
            selectUnitOrUser("4",1,"","",function(data){
            	var ids = data.code;
            	var id = ids.split(",");
            	if(id.length>1){
            		alert("请只选择一人");
            		return;
            	}
            	$("#employeeId").val(data.code);
            	$("#employeeName").val(data.name);
            	$("#employeeName").parent().find('#append').html(data.name);
            });
        }
       
</script>
</html>