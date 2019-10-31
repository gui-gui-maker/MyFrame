<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
var changeFlag = false;
$(function () {
	
$("#form1").initForm({
	
     toolbarPosition :"bottom",
    success : function(responseText) {//处理成功
    	
        if (responseText.success) {    	
            top.$.dialog.notice({content:'保存成功'});
            api.data.window.submitAction();
            
            $('#fk_reports_id').val(responseText.data.id);
            $('#report_id').val(responseText.data.id);
            //$(window.parent.document).find('#teacher_id').val(responseText.data.id);
        } else {

                $.ligerDialog.error('保存失败' + responseText);
        }
    },
    getSuccess:function(responseText){
        //alert(res.data.id);
        //alert("111");
    	changeFlag = true;
		setMenuData();


    }
});

});
    //
	function choose(val,text) {
		if (val == '1') {
			var xx = document.getElementById("xzhou");
			xx.disabled = false;
			var yy= document.getElementById("yzhou");
			yy.disabled = false;
			$('#xzhou').show();
			$('#yzhou').show();
	
		}else if(val == "2"){
			$('#xzhou').hide();
			$('#yzhou').hide();
		}
	}
    
	//得到焦点时触发事件
	function OnFocusFun(element, elementvalue) {
		if (element.value == elementvalue) {
			element.value = "";
			element.style.color = "#000";
		}
	}
	//离开输入框时触发事件
	function OnBlurFun(element, elementvalue) {
		if (element.value == "" || element.value.replace(/\s/g, "") == "") {
			element.value = elementvalue;
			element.style.color = "#999";
		}
	}
    
	function selectRtboxReport(type){
	    if(type==null||type==undefined){
	        type=""
	    }
	    var url = 'url:rtbox/config/rtPage_select.jsp?type='+type;
	    top.$.dialog({
	        parent: api,
	        width : 800,
	        height : 550,
	        lock : true,
	        title:"选择js报告模板",
	        content: url,
	        data : {"parentWindow" : window}
	    });
	}
	function callRtBack(id,code,type){
	    if(type=="1"){

	        $('#recordModelId').val(id);
	        $('#recordModelCode').val(code);
	    }else{
	        $('#rtboxId').val(id);
	        $('#rtboxCode').val(code);
	    }
	    setMenuData();
	}
	function setMenuData(){
		if(!changeFlag){
			return;
		}
		reportCode = $("#rtboxCode").val();
		recordCode = $("#recordModelCode").val();
		$.post("baseReportsMenuConfigAction/getRecordModelDir.do",{"rtCode":reportCode},function(resp){
			if(resp.success){
				$("#reportMenuCode").ligerGetComboBoxManager().setData(resp.menus);
			}
		})
		$.post("baseReportsMenuConfigAction/getRecordModelDir.do",{"rtCode":recordCode},function(resp){
			if(resp.success){
				$("#recordMenuCode").ligerGetComboBoxManager().setData(resp.menus);
			}
		})
	}
	var rtboxCodeC = "";
	function changeReportType(val){
	    reportType = val;
	    if(val=="2"){
	        $(".rtbox").show();
	        rtboxCodeC = $('#rtboxCode').attr("class");
	        //添加js模板必填验证
	        $('#rtboxCode').attr("class","requiredstar "+$('#rtboxCode').attr("class"));
	        $('#rtboxCode').attr("required","required");

	        //取消其他模板必填验证
	        $('#reportFile').attr("class",rtboxCodeC)
	        $('#reportFile').removeAttr("required");

	    }else{
	        $('#rtboxId').val("");
	        $('#rtboxCode').val("");
	        //取消js模板必填验证
	        $('#rtboxCode').removeAttr("required");
	        $('#rtboxCode').attr("class",rtboxCodeC)
	        $(".rtbox").hide();

	        //添加其他模板必填验证
	        $('#reportFile').attr("class","requiredstar "+$('#rtboxCode').attr("class"));
	        $('#reportFile').attr("required","required");

	    }
	}
</script>
<body>

	<div class="navtab">
		<div title="报告配置" lselected="true">
			<form id="form1" action="report/basic/save.do"
				getAction="report/basic/detail.do?id=${param.id}">
				<input type="hidden" name="report_version" />
				<input type="hidden" name="mdy_user_id" id="mdy_user_id"/>
				<input type="hidden" name="mdy_user_name" id="mdy_user_name"/>
				<input type="hidden" name="mdy_date" id="mdy_date"/>
				<input type="hidden" name="remark" id="remark"/>
				<table border="1" cellpadding="3" cellspacing="0" width=""
					class="l-detail-table">
					<tr>
						<input id="report_id" name="id" type="hidden" />
						<input id="report_code" name="report_code" type="hidden" />
			
						<td class="l-t-td-left">报告名称：</td>
						<td class="l-t-td-right"><input name="report_name"
							validate="{required:true,maxlength:128}" ltype='text' /></td>
						<td class="l-t-td-left">报告模版文件：</td>
						<td class="l-t-td-right"><input name="report_file"
							validate="{required:true,maxlength:40}" ltype='text' /></td>
					</tr>
					</tr>
						<tr>

						<td class="l-t-td-left">模板类型：</td>
						<td class="l-t-td-right" colspan="3"><input type="radio"
														name="reportType" ltype="radioGroup"
														validate="{required:false}"
														ligerui="{initValue:'0',
							onChange:changeReportType,
							data:[{text:'明宇报表',id:'0'},{text:'office报表',id:'1'},{text:'科鸿rtbox模板',id:'2'}]}" />
						</td>

					</tr>
					<tr>
						<td class="l-t-td-left rtbox" style="display: none;">科鸿rtbox模版文件：</td>
						<td class="l-t-td-right rtbox" style="display: none;" colspan="3">
							<input name="rtboxId" id="rtboxId" type="hidden" />
							<input type="text" id="rtboxCode" name="rtboxCode"   ltype="text"  onclick="selectRtboxReport()" onchange="setMenuData()"
								   ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectRtboxReport()}}]}"/>
						</td>
					</tr>

					<tr>
						<td class="l-t-td-left rtbox" style="display: none;">原始记录模版文件：</td>
						<td class="l-t-td-right rtbox" style="display: none;" colspan="3">
							<input name="recordModelId" id="recordModelId" type="hidden"/>
							<input type="text" id="recordModelCode" name="recordModelCode" ltype="text"
								   onclick="selectRtboxReport('1')" onchange="setMenuData()"
								   ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectRtboxReport('1')}}]}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">报告打印份数：</td>
						<td class="l-t-td-right"><input name="printcopies" value=1
							ligerui="{type:'int'}" ltype='spinner'
							validate="{required:true,maxlength:128}" ltype='text' /></td>
						<td class="l-t-td-left">是否带限速器： </td>
						<td class="l-t-td-right"><input type="radio"
							name="is_xsq" ltype="radioGroup" validate="{required:true}"
							ligerui="{ initValue:'0',data: [ { text:'是', id:'1' }, { text:'否', id:'0' } ] }" />
						</td>
						<!-- 
						<td class="l-t-td-left">报告超期天数：</td>
						<td class="l-t-td-right"><input name="und_date"
							validate="{required:false,maxlength:128}" ltype='text' /></td>
						
						<td class="l-t-td-left">下次检验<br>间隔时间：</td>
						<td class="l-t-td-right"><input name="interval"
							ligerui="{type:'int'}" ltype='spinner'
							validate="{required:false,maxlength:128}" ltype='text' /></td>
						 -->
					</tr>
					<tr>
						<td class="l-t-td-left">原始记录名称：</td>
						<td class="l-t-td-right"><input name="ysjl_name"
							validate="{required:false,maxlength:40}" ltype='text' /></td>
						<td class="l-t-td-left">原始记录模版文件：</td>
						<td class="l-t-td-right"><input name="ysjl_file"
							validate="{required:false,maxlength:40}" ltype='text' /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">原始记录页码：</td>
						<td class="l-t-td-right"><input name="ysjl_pages"
							validate="{required:false,maxlength:128}" ltype='text' /></td>						
						<td class="l-t-td-left">原始记录打印份数：</td>
						<td class="l-t-td-right"><input name="print_ysjl_copies" value='1'
							ligerui="{type:'int'}" ltype='spinner'
							validate="{required:true,maxlength:128}" ltype='text' /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">报表路径：</td>
						<td class="l-t-td-right"><input name="rootpath"
							value="app/flow/report/temple"
							validate="{required:false,maxlength:128}" ltype='text' /></td>
						<td class="l-t-td-left">报告书编号格式：</td>
						<td class="l-t-td-right"><input name="report_sn"
							value="CO,T,C,R,XXX" validate="{required:false,maxlength:128}"
							ltype='text' /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">数据集名称：</td>
						<td class="l-t-td-right"><input name="mrdataset" value="BGDS"
							validate="{required:false,maxlength:128}" ltype='text' /></td>
						<td class="l-t-td-left">标签数据集名称：</td>
						<td class="l-t-td-right"><input name="mrdataset_tags"
							value="BGDS" validate="{required:false,maxlength:128}"
							ltype='text' /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">标签模板文件：</td>
						<td class="l-t-td-right"><input name="report_file_tags"
							validate="{required:false,maxlength:128}" ltype='text' /></td>
						
						<td class="l-t-td-left">信息表模板名称：</td>
						<td class="l-t-td-right"><input name="info_tmp_name"
							ltype='text' validate="{required:false,maxlength:40}" /></td>
					</tr>
					
					<tr>
						<td class="l-t-td-left">是否支持批量录入<br/>（excel导入）： </td>
						<td class="l-t-td-right"><input type="radio"
							name="enter_type" ltype="radioGroup" validate="{required:true}"
							ligerui="{ initValue:'0',data: [ { text:'是', id:'1' }, { text:'否', id:'0' } ] }" />
						</td>
						<td class="l-t-td-left">是否支持上传附件：</td>
						<td class="l-t-td-right"><input type="radio" name="is_upload"
							ltype="radioGroup" validate="{required:true}"
							ligerui="{ initValue:'0',data: [ { text:'是', id:'1' }, { text:'否', id:'0' } ] }" />
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">是否打印合格证：</td>
						<td class="l-t-td-right"><input type="radio"
							name="is_print_tags" ltype="radioGroup"
							validate="{required:true}"
							ligerui="{ initValue:2,data: [ { text:'是', id:'1' }, { text:'否', id:'2' } ] }" />
						</td>
						<td class="l-t-td-left">审批流程：</td>
						<td class="l-t-td-right"><input type="radio"
							name="check_flow" ltype="radioGroup" validate="{required:true}"
							ligerui="{ initValue:'0',data: [ { text:'一级审核', id:'1' }, { text:'二级审核', id:'0' } ] }" />
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">报告状态：</td>
						<td class="l-t-td-right"><input type="radio" name="flag"
							ltype="radioGroup" validate="{required:true}"
							ligerui="{ initValue:'1',data: [ { text:'启用', id:'1' }, { text:'停用', id:'0' } ] }" />
						</td>
						<td class="l-t-td-left">是否电子签章：</td>
						<td class="l-t-td-right"><input type="radio" name="is_electronic_seal_man"
							ltype="radioGroup" validate="{required:true}"
							ligerui="{ initValue:'0',data: [ { text:'是', id:'1' }, { text:'否', id:'0' } ] }" />
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">是否可修改<br/>编制人： </td>
						<td class="l-t-td-right"><input type="radio"
							name="is_mdy_editor" ltype="radioGroup" validate="{required:true}"
							ligerui="{ initValue:'0',data: [ { text:'是', id:'1' }, { text:'否', id:'0' } ] }" />
						</td>
						<td class="l-t-td-left">是否支持双面打印： </td>
						<td class="l-t-td-right"><input type="radio"
							name="is_print_double" ltype="radioGroup" validate="{required:true}"
							ligerui="{ initValue:'0',data: [ { text:'是', id:'1' }, { text:'否', id:'0' } ] }" />
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">是否签发指定类型： </td>
						<td class="l-t-td-right"><input type="radio"
							name="is_issue" ltype="radioGroup" validate="{required:true}"
							ligerui="{ initValue:'0',data: [ { text:'否', id:'0' }, { text:'是', id:'1' }] }" />
						</td>
						<td class="l-t-td-left">签发是否<br/>发送提醒： </td>
						<td class="l-t-td-right"><input type="radio"
							name="issue_msg_type" ltype="radioGroup" validate="{required:true}"
							ligerui="{ initValue:'0',data: [ { text:'微信', id:'1' }, { text:'短信', id:'2' }, { text:'微信和短信', id:'3' }, { text:'不发送', id:'0' } ] }" />
						</td>
					</tr>
				</table>

			</form>
		</div>
		<div id="test" title="报告关联证书">
			<form id="form2" action="report/cert/save.do"
				getAction="report/cert/getCert.do?id=${param.id}">

				<table border="1" cellpadding="3" cellspacing="0" width=""
					class="l-detail-table">
					<input type="hidden" name="id"> <input type="hidden"
						name="fk_reports_id" id="fk_reports_id" value="${param.id}">
							<tr>

								<td class="l-t-td-left">证书类型：</td>
								<td class="l-t-td-right"><u:combo name="cert_code"
										code="BASE_LETTER" /></td>
							</tr>
				</table>
			</form>
			<script type="text/javascript">
        $("#form2").initFormList({
//                opType:"auto",//数据保存模式，发现actionParam的关联信息为空时会自动调用opTypeFun，默认为auto
//                opTypeFun:save,//自动保存调用方法，opType为atuo时才会用到,默认为save
//                action:"",//保存数据或其它操作的action
			actionParam: {"fk_reports_id": $("#report_id")}, //保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把from1下的id为id的值带上去,可以是一个对象或选择器
			delAction: "report/cert/delete.do", //删除数据的action
			root:'datalist',
			//delActionParam: {name: "name"}, //默认为选择行的id
			//success: function (data) {
				//alert("success=" + $.ligerui.toJSON(data))
			//},
			//getSuccess: function (data) {
				//alert("getSuccess=" + $.ligerui.toJSON(data))
			//},
			//delSuccess: function (data) {
				//alert("delSuccess=" + $.ligerui.toJSON(data))
			//},
			//onBeforeSave: function (g) {
			//	alert(g.data("defaultValues"));
			//	return true;
			//},
  	onSelectRow:function (rowdata, rowindex) {
                $("#form2").setValues(rowdata);
            },
           
         
//                getAction:"",//取数据的action
//                getActionParam:{},//取数据时附带的参数，一般只在需要动态取特定值时用到
			columns: [
				//此部分配置同grid
				{ display: '主键', name: 'id', width: 50, hide: true},
			
				{ display: '证书名称', name: 'cert_code', width: '100%'}
			
			]
		});
    </script>
		</div>
		<div id="test" title="报告项目配置">

			<form id="form3" action="report/item/save.do"
				getAction="report/item/getItem.do?id=${param.id}">

				<table border="1" cellpadding="3" cellspacing="0" width=""
					class="l-detail-table">
					<input type="hidden" name="id"> <input type="hidden"
						name="fk_reports_id" value="${param.id}">


							<tr>

								<td class="l-t-td-left">项目名称：</td>
								<td class="l-t-td-right"><input name="itme_name"
									validate="{required:false,maxlength:128}" ltype='text' /></td>

								<td class="l-t-td-left">对应报表页索引：</td>
								<td class="l-t-td-right"><input name="page_index"
									validate="{required:false,maxlength:128}" ltype='text' /></td>
							</tr>

							<tr>

								<td class="l-t-td-left">是否单独检验员：</td>
								<td class="l-t-td-right"><input type="radio"
									name="is_inspect_man" ltype="radioGroup"
									validate="{required:true}"
									ligerui="{ initValue:2,data: [ { text:'是', id:'1' }, { text:'否', id:'2' } ] }" />
								</td>

								<td class="l-t-td-left">是否单独审核人：</td>
								<td class="l-t-td-right"><input type="radio"
									name="is_audit_man" ltype="radioGroup"
									validate="{required:true}"
									ligerui="{ initValue:2,data: [ { text:'是', id:'1' }, { text:'否', id:'2' } ] }" />
								</td>
							</tr>
							<tr>
								<td class="l-t-td-left">是否单独评片人：</td>
								<td class="l-t-td-right"><input type="radio"
									name="is_eval_pic_man" ltype="radioGroup"
									validate="{required:true}"
									ligerui="{ initValue:2,data: [ { text:'是', id:'1' }, { text:'否', id:'2' } ] }" />
								</td>
								<td class="l-t-td-left">是否为必选项：</td>
								<td class="l-t-td-right"><input type="radio" name="is_must"
									ltype="radioGroup" validate="{required:true}"
									ligerui="{ initValue:2,data: [ { text:'是', id:'1' }, { text:'否', id:'2' } ] }" />
								</td>
							</tr>
							<tr>
								<td class="l-t-td-left">是否电子签章：</td>
								<td class="l-t-td-right"><input type="radio" id="xz"
									name="is_electronic_seal_man" ltype="radioGroup"
									validate="{required:true}"
									ligerui="{ initValue:2,onChange:choose,data: [ { text:'是', id:'1' }, { text:'否', id:'2' } ] }" />
								</td>
								<td class="l-t-td-left">签章位置X：</td>
								<td class="l-t-td-right">
									<div id="x">
										<input name="xzhou" type="text" id="xzhou" disabled="true"
											ltype="spinner" validate="{required:true}"
											onfocus="OnFocusFun(this,'请输入0—500内的像素')"
											onblur="OnBlurFun(this,'请输入0—500内的像素')"
											ligerui="{type:'int',isNegative:false,minValue:0,maxValue:500}" />
									</div>
								</td>
							</tr>
							<tr>
								<td class="l-t-td-left">证书类型：</td>
								<td class="l-t-td-right"><u:combo name="cert_code"
										code="BASE_LETTER" /></td>
								<td class="l-t-td-left">签章位置Y：</td>
								<td class="l-t-td-right">
									<div id="y">
										<input name="yzhou" type="text" id="yzhou" disabled="true"
											ltype="spinner" validate="{required:true}"
											onfocus="OnFocusFun(this,'请输入0—400内的像素')"
											onblur="OnBlurFun(this,'请输入0—400内的像素')"
											ligerui="{type:'int',isNegative:false ,minValue:0,maxValue:400}" />
									</div>
								</td>
							</tr>
				</table>
			</form>
			<script type="text/javascript">
        $("#form3").initFormList({
//                opType:"auto",//数据保存模式，发现actionParam的关联信息为空时会自动调用opTypeFun，默认为auto
//                opTypeFun:save,//自动保存调用方法，opType为atuo时才会用到,默认为save
//                action:"",//保存数据或其它操作的action
			actionParam: {"fk_reports_id": $("#report_id")}, //保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把from1下的id为id的值带上去,可以是一个对象或选择器
			delAction: "report/item/delete.do", //删除数据的action
			root:'datalist',
			//delActionParam: {name: "name"}, //默认为选择行的id
			success: function (data) {
				/* alert("success=" + $.ligerui.toJSON(data)) */
				if(data.success){
					$.post("report/basic/createDefaultTemplete.do",{"fk_reports_id":data.data.fk_reports_id},function(resp){
						
					})
				}
			},
			//getSuccess: function (data) {
				//alert("getSuccess=" + $.ligerui.toJSON(data))
			//},
			delSuccess: function (data) {
				//alert("delSuccess=" + $.ligerui.toJSON(data))
				if(data.success){
					$.post("report/basic/createDefaultTemplete.do",{"fk_reports_id":"${param.id}"},function(resp){
						
					})
				}
			},
			//onBeforeSave: function (g) {
			//	alert(g.data("defaultValues"));
			//	return true;
			//},
  			onSelectRow:function (rowdata, rowindex) {
                $("#form3").setValues(rowdata);
            },
//                getAction:"",//取数据的action
//                getActionParam:{},//取数据时附带的参数，一般只在需要动态取特定值时用到
			columns: [
				//此部分配置同grid
				{ display: '主键', name: 'id', width: 50, hide: true},
				{ display: '项目名称', name: 'itme_name', width: '20%'},
				{ display: '对应报表页索引', name: 'page_index', width: '15%'},
				{ display: '是否单独检验员', name: 'is_inspect_man', width: '15%'},
				{ display: '是否单独审核人', name: 'is_audit_man', width: '15%'},
				{ display: '是否单独评片人', name: 'is_eval_pic_man', width: '15%'},
				{ display: '是否为必选项', name: 'is_must', width: '10%'},
				{ display: '证书类型', name: 'cert_code', width: '30%'}
			]
		});
    </script>
		</div>
	</div>
</body>
</html>
