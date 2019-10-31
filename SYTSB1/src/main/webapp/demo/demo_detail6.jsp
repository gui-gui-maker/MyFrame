<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript" src="pub/rbac/js/area.js"></script>
 <script type="text/javascript">
$(function() {
    $("#bzcgsqbForm").initForm({
		success: function (response) {//处理成功
    		if (response.success) {
            	top.$.dialog.notice({
             		content: "保存成功！"
            	});
          		//保存标准采购申请（主表）后，id未自动赋值，故此处手动赋值
          		$("#bzcgsqb_id").attr("value",response.data.id);
         		api.data.window.refreshGrid();
            	//api.close();
    		} else {
           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
      		}
		}, getSuccess: function (response){

		}, toolbar: [
      		{
      			text: "保存", icon: "save", click: function(){
      				//表单验证
			    	if ($("#bzcgsqbForm").validate().form()) {
			    		if(confirm("确定保存？")){
			    			//表单提交
			    			$("#bzcgsqbForm").submit();
				    	}
			    	}
      			}
      		},
      		{
      			text: "提交", icon: "save", click: function(){
      				//表单验证
			    	if ($("#bzcgsqbForm").validate().form()) {
			    		if(confirm("确定提交？提交后数据不能修改！")){
			    			//表单提交
			    			bzcgsqbForm.action = "/members/bzfw/bzcg/saveBf_Bzcgsqb.do?status=detail&submit=true";
				    		$("#bzcgsqbForm").submit();
				  		}
			    	}
      			}
      		},
			{
				text: "关闭", icon: "cancel", click: function(){
					api.close();
				}
			}
		], toolbarPosition: "bottom"
	});

	$("#a100").click(function(){
		$.ligerDialog.open({
			title:'选择标准资料', name:'selBzzlWin', width:800, height:500,
			url:'/workplatform/members/bzfw/choose_bzzl.jsp',
			buttons:[
				{text:'确定', onclick: callbackBzzl},
				{text:'取消', onclick: function(item,dialog){dialog.close()}}
			]
		});
	});

});

	//获取选择标准返回数据
	function callbackBzzl(item,dialog){
		var obj = dialog.frame.getSelected();
		$("#standardid").val(obj.id);	//ID
		$("#a100").val(obj.bzbh);	//标准编号
		$("#a301").val(obj.bzmc);	//标准名称
		$("#standclass_txt").val(obj.bzfl_txt);	//标准分类（text）
		$("#standclass").val(obj.bzfl);	//标准分类（code）
		$("#a305").val(obj.ys);		//页数
		//标准资料暂未开始收费，所以金额暂时赋值为零
		$("#a306").val("0");	//标准资料金额
		dialog.close();
	}

	function doSave(){
    	//表单验证
    	if ($("#bzcgsqbForm").validate().form()) {
    		if(confirm("确定保存标准查新申请？")){
    			//表单提交
    			$("#bzcgsqbForm").submit();
	    	}
    	}
    }

	function doSubmit(){
  		//表单验证
    	if ($("#bzcgsqbForm").validate().form()) {
    		if(confirm("确定提交标准查新申请？提交后数据不能修改！")){
    			//表单提交
    			bzcgsqbForm.action = "/members/bzfw/bzcg/saveBf_Bzcgsqb.do?status=detail&submit=true";
	    		$("#bzcgsqbForm").submit();
	  		}
    	}
	}

	function closewindow(){
		api.close();
	}
</script>
</head>
<body>
<div class="navtab">
<div title="标准采购" tabId="bzcgsqbTab" style="height: 400px">
<form id="bzcgsqbForm" name="bzcgsqbForm" method="post" action="members/bzfw/bzcg/saveBf_Bzcgsqb.do?status=detail"
      getAction="demo/demo_detail6_1.json">
	<input type="hidden" name="id" id="bzcgsqb_id" value="402880c04255f760014255f892200001"/>
	<input type="hidden" name="_lxr" value="asdf"/>
	<input type="hidden" name="_lxdh" value=""/>
	<input type="hidden" name="bz" />
	<table cellpadding="3" cellspacing="0" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">机构代码：</td>
            <td class="l-t-td-right" ><input name="jgdm" id="jgdm" type="text" ltype='text' value="12341234X"
            	validate="{required:true,maxlength:1000}" ligerui="{disabled:true}"/></td>
            <td class="l-t-td-left">机构名称：</td>
            <td class="l-t-td-right" ><input name="dwmc" type="text" ltype='text' value="重庆智尊商业管理有限公司成都分公司" validate="{required:true,maxlength:1000}"
            	ligerui="{disabled:true}" /></td>
        </tr>
        <tr>
 			<td class="l-t-td-left">申请日期：</td>
            <td class="l-t-td-right" >



     				<input type="text" ltype='date'  name="pub_Ywjd.sqrq" id="pub_Ywjd.sqrq" validate="{required:true}"
     				ligerui="{format:'yyyy-MM-dd',disabled:true}"/>


			</td>
        </tr>
    </table>
   </form>
   </div>

<div title="明细" tabId="bzcgmxTab">
<form id="bzcgmxForm" name="bzcgmxForm" method="post" action="members/bzfw/bzcg/saveBf_Bzcgmx.do?status=detail">
  	<input type="hidden" name="id" id="bzcgmx_id"/>
  	<input type="hidden" name="bf_Bzcgsqb.id" value="402880c04255f760014255f892200001"/>
  	<input type="hidden" id="standardid" name="standard.standardid"/>
  	<input type="hidden" id="standclass" name="standard.standclass"/>
	<table cellpadding="3" cellspacing="0" class="l-detail-table"  >
		<tr>
	       <td class="l-t-td-left">标准编号：</td>
	       <td class="l-t-td-right" ><input name="standard.a100" id="a100" type="text"
	                                    ltype='text' validate="{required:true,maxlength:100}"/></td>

	       <td class="l-t-td-left">标准名称：</td>
	       <td class="l-t-td-right" ><input name="standard.a301" id="a301" type="text" ltype='text' validate="{required:true,maxlength:1000}" ligerui="{readonly:true}" /></td>
	    </tr>
	    <tr>
	       <td class="l-t-td-left">标准分类：</td>
	       <td class="l-t-td-right" ><input name="standclass_txt" id="standclass_txt" type="text" ltype='text' validate="{required:true,maxlength:800}"
										ligerui="{readonly:true}" /></td>

	       <td class="l-t-td-left">页数：</td>
	       <td class="l-t-td-right" ><input name="standard.a305" id="a305" type="text" ltype='text' validate="{required:true,maxlength:1000}" ligerui="{readonly:true}" /></td>
	    </tr>
	    <tr>
	       <td class="l-t-td-left">金额：</td>
	       <td class="l-t-td-right" ><input name="standard.a306" id="a306" type="text" ltype='text' validate="{required:true,maxlength:1000}" ligerui="{readonly:true}" /></td>
	    </tr>
	</table>
  	<script type="text/javascript">
    $("#bzcgmxForm").initFormList({
    	root:'datalist',
        getAction:"demo/demo_detail6_2.json",
        //getActionParam:{},	//取数据时附带的参数，一般只在需要动态取特定值时用到
        actionParam:{"bf_Bzcgsqb.id" : $("#bzcgsqbForm>[name='id']")},	//保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把Form1下的id为id的值带上去,可以是一个对象或选择器
        delAction:'members/bzfw/bzcg/deleteBzcgmx.do',	//删除数据的action
        delActionParam:{ids:"id"},	//默认为选择行的id
        columns:[
            //此部分配置同grid
            { display:'标准采购明细主键', name:'id', width:'1%', hide:true},
			{ display:'标准采购申请表主键', name:'bf_Bzcgsqb.id', width:'1%', hide:true},
			{ display:'标准资料主键', name:'standard.standardid', width:'1%', hide:true},
            { display:'标准分类（编号）', name:'standard.standclass', width:'2%', hide:true},
            { display:'标准编号', name:'standard.a100', width:'15%'},
            { display:'标准名称', name:'standard.a301', width:'35%'},
            { display:'标准分类', name:'standclass_txt', width:'25%'},
            { display:'页数', name:'standard.a305', width:'10%'},
            { display:'金额', name:'standard.a306', width:'10%'}
        ]
    });
	</script>
</form>
  </div>
</div>
</body>
</html>