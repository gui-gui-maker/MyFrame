<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
 <link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
    <script type="text/javascript">
    function selectUser() {
        selectUnitOrUser(1, 1, "userId", "userName");
    }
    </script>
</head>
<body>
    <form id="form1" action="tjy2CertificateTrainAction/saveTrain.do" getAction="tjy2CertificateTrainAction/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id" />
    <input type="hidden" id="userId" name="userId" />
    <input type="hidden" id="createDate" name="createDate" value=""/>
    <input type="hidden" id="createMan" name="createMan" />
       <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
       <tr>
        <td class="l-t-td-left">培训内容:</td>
        <td class="l-t-td-right" colspan="3"> <input id="trainRemark" id="trainRemark" name="trainRemark" type="text" ltype="text" ></input>
        </td>
       </tr>
		<tr> 
        <td class="l-t-td-left">人员:</td>
        <td class="l-t-td-right" colspan="3"> <input id="userName" name="userName" type="text" ltype="text" validate="{required:true}" onClick="selectUser()"
                        readonly="readonly"></input>
        </td>
       </tr>
        <tr> 
        <td class="l-t-td-left"> 培训类别 :</td>
        <td class="l-t-td-right" colspan="3"> <input type="radio" name="tratnType" id="tratnType" ltype="radioGroup"
                        ligerui="{value:'01',data: [ { text:'业务学习培训 ', id:'01' }, { text:'取（换）检验资格证培训', id:'02' },
                        { text:'其它培训 ', id:'03' },{ text:'会议、交流', id:'04' }]}"/> 
        </td>
       </tr>
 
       <tr> 
        <td class="l-t-td-left"> 培训主办单位:</td>
        <td class="l-t-td-right" colspan="3"><input type="text" ltype="text" id="trainUnit" name="trainUnit" validate="{required:true,maxlength:36}" ></input>
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left"> 培训地址:</td>
        <td class="l-t-td-right" colspan="3"> <input type="text" ltype="text" id="tratnSite" name="tratnSite" validate="{required:true,maxlength:36}" ></input>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 培训文件文号:</td>
        <td class="l-t-td-right"  colspan="3"> <input type="text" ltype="text" id="tratnFileNum" name="tratnFileNum" validate="{required:true,maxlength:36}" ></input>
        </td>
        </tr>
        <tr>
        <td class="l-t-td-left"> 培训开始时间:</td>
        <td class="l-t-td-right"> <input type="text" ltype="date" id="tratnTimeStart" name="tratnTimeStart" validate="{required:true,maxlength:36}" ligerui="{format:'yyyy-MM-dd'}" ></input>
        </td> 
        <td class="l-t-td-left"> 培训结束时间:</td>
        <td class="l-t-td-right"> <input type="text" ltype="date" id="tratnTimeEnd" name="tratnTimeEnd" validate="{required:true,maxlength:36}" ligerui="{format:'yyyy-MM-dd'}" ></input>
        </td>
       
       </tr>
      <tr>
          <td class="l-t-td-left"> 学时:</td>
        <td class="l-t-td-right"> <input type="text" ltype="text" id="period" name="period" validate="{required:false,maxlength:36}" ></input>
        </td> 
        <td class="l-t-td-left"> 培训费:</td>
        <td class="l-t-td-right"> <input type="text" ltype="text" id="trainCost" name="trainCost" validate="{required:false,maxlength:36}" ></input>
        </td>
      </tr>
      <tr>
          <td class="l-t-td-left"> 其他费用:</td>
        <td class="l-t-td-right"> <input type="text" ltype="text" id="otherCost" name="otherCost" validate="{required:false,maxlength:36}" ></input>
        </td> 
      <td class="l-t-td-left"> 编号:</td>
        <td class="l-t-td-right"> <input type="text" ltype="text" id="trainNum" name="trainNum" validate="{required:false,maxlength:36}" ></input>
        </td>
      </tr>
      </table>
    </form> 




</div>

</body>
</html>
