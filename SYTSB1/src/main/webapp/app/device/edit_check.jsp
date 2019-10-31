<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>



<head>
<title>修改检验信息</title>




<script language="javascript">

$(function () {
    $("#formObj").initForm({    //参数设置示例
       toolbar:[
            { text:'保存', id:'save',icon:'save', click:saveClose },
          
            { text:'关闭', id:'close',icon:'cancel', click:close }
        ],
        getSuccess:function(res){
      
            //setData(res.data);
            
        }
    });
    function saveClose(){
    	
    	var report_sn=$('#report_sn').val();

    	if(report_sn=="CO-"){
    		$.ligerDialog.alert("请输入报告书编号！", "提示");
    	  return;
    	}
    	
    	
    	var deviceId = "${param.id}";
    	
    	var formData = $("#formObj").getValues();
		var data = {};
		data = $.ligerui.toJSON(formData);
        $.ajax({
            url: "device/basic/changeInfo.do?id="+deviceId,
            data: data,	//JSON.stringify(json)把json转化成字符串
            cache:false,    
            type: "POST",
            datatype: "json",
            contentType: "application/json; charset=utf-8",
            success: function (data, stats) {
            
                if (data["success"]) {
                	top.$.notice("修改成功！");
                	api.data.window.submitAction();
               		api.close();
                	
                } else {
                	$.ligerDialog.error(data.msg);
                }
            },
            error: function (data) {
                $.ligerDialog.error(data.msg);
            }
        });
    }
    
   function close(){
        api.close();
    }
});


function changeDate() {
	
	var inspectDate=$('#advance_time').val();
	
	var c = inspectDate.replace("-","/").replace("-","/");
	
	var d = new Date(c);
	
	d.setDate(d.getDate()-1);
	d.setMonth(d.getMonth()-1);
	d.setFullYear(d.getFullYear()+1);
	var str  = DateAdd("m",1,d).toLocaleDateString();
	
	
	var temp =str.split(" ")[0].replace("年","-").replace("月","-").replace("日","");
	

	
	$('#last_check_time').val(temp);
	
	
	}
function DateAdd(strInterval,NumDay,dtDate)
{
	var dtTmp=new Date(dtDate);
	if (isNaN(dtTmp)) {
	dtTmp=new Date();
	}
	switch (strInterval) {
	case "s":return new Date(Date.parse(dtTmp)+(1000*NumDay));
	case "n":return new Date(Date.parse(dtTmp)+(60000*NumDay));
	case "h":return new Date(Date.parse(dtTmp)+(3600000*NumDay));
	case "d":return new Date(Date.parse(dtTmp)+(86400000*NumDay));
	case "w":return new Date(Date.parse(dtTmp)+((86400000*7)*NumDay));
	case "m":return new Date(dtTmp.getFullYear(),(dtTmp.getMonth())+NumDay,dtTmp.getDate(),dtTmp.getHours(),dtTmp.getMinutes(),dtTmp.getSeconds());
	case "y":return new Date((dtTmp.getFullYear()+NumDay),dtTmp.getMonth(),dtTmp.getDate(),dtTmp.getHours(),dtTmp.getMinutes(),dtTmp.getSeconds());
}
}



//-->
</script>
<html>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >



		<form name="formObj" id="formObj" method="post" ">
			<table border="0" cellpadding="0" cellspacing="0" align="center" class="table_detail">
				<tr >
					
					<td class="l-t-td-left">检验报告编号：</td>
					<td class="l-t-td-right">
					<input name="report_sn"
					validate="{required:true,maxlength:32}" value="CO-" ltype='text' id="report_sn" /></td>
					
					<td class="l-t-td-left">设备检验结论：</td>
					<td class="l-t-td-right">
					<input name="inspection_conclusion"
					validate="{required:true,maxlength:64}"  value="" ltype='text' id="inspection_conclusion" /></td>
						
					</td>
				</tr>

				<tr >
					<td class="l-t-td-left">检验日期：</td>
					<td class="l-t-td-right">
					<input name="advance_time" id="advance_time"
					type="text" ltype="date" validate="{required:true}"
					ligerui="{initValue:'',format:'yyyy-MM-dd'}"  onchange="changeDate();"/></td>
					
					<td class="l-t-td-left">下次检验日期：</td>
					<td class="l-t-td-right">
					<input name="last_check_time" id="last_check_time"
					type="text" ltype="date" validate="{required:true}"
					ligerui="{initValue:'',format:'yyyy-MM-dd'}" /></td>
					
				</tr>
				
			</table>
		</form>

		

</body>

</html>


