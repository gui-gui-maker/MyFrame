<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
    <script type="text/javascript" src="k/kui/frame/ligerTree.js"></script>
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
				},
			 getSuccess:function(res){
				 console.log(res);
		        }
			});
		});
        function selectReaders(){
            selectUnitOrUser("4",1,"","",function(data){
            	var ids = data.code;
            	var id = ids.split(",");
            	if(id.length>1){
            		alert("请只选择一人");
            		return;
            	}
            	var name = data.name;
            	$("#userId").val(ids);
            	$("#cuser").val(name);
            	$("#cuser").parent().find('#append').html(data.name);
            });
        }
    </script>
</head>
<body>
<form id="form1" action="dining/foodCard/saveCard.do" method="post" getAction="dining/foodCard/detailCard.do?id=${param.id}">
    <input type="hidden" id="id" name="id" value="${param.id}"/>
    <input type="hidden" name="lastAction" />
	<input type="hidden" name="lastAdd" />
    <input type="hidden" name="lastDecrease" />
    <input type="hidden" name="lastOperator" />
    <input type="hidden" name="cardStatus" />
    <table cellpadding="3" class="l-detail-table">
    	<tr>
	       <td class="l-t-td-left">卡号：</td>
	       <td class="l-t-td-right">
	       	<input name="cardNo" type="text" ltype="text" validate="required:true"/>
		  </td>
	    </tr>
	    	<tr>
		       <td class="l-t-td-left">用户：</td>
		       <td class="l-t-td-right">
		       		<!-- <input name="userid" type="text" ltype="text" validate="required:true"/> -->
		       		 <input name="userId" id="userId" type="hidden" />
	                 <input name="cuser" id="cuser" type="hidden" />
	                 <c:if test="${param.pageStatus=='modify' or param.pageStatus=='add'}">
		       		 	<span class="l-button label" title="添加用户">
	                    <span  class="l-a l-icon-add"  onclick="selectReaders();">&nbsp;</span>
	                </c:if>
		       		 <span  id="append"></span>
			  </td>
		    </tr>
		    <c:if test="${param.pageStatus=='detail'}">
			    <tr>
			       <td class="l-t-td-left">电话：</td>
			       <td class="l-t-td-right">
			       <input name="tel" type="text" ltype="text" validate="required:true"/>
				  </td>
			    </tr>
				<tr>
					<td class="l-t-td-left">次数：</td>
				       <td class="l-t-td-right">
				       		<input name="count" type="text" ltype="text" />
					  </td>
			    </tr>
		    </c:if>
	      <c:if test="${param.pageStatus!='detail'}">
	     	 <input name="count" type="hidden" />
	     	 <input name="tel" type="hidden" />
	      </c:if>
    </table>
</form>
</body>
</html>
