<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title></title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>
<%@ taglib uri="http://khnt.com/tags/chart" prefix="chart"%>
<script src="pub/chart/js/chart_show.js"></script>
<script type="text/javascript">
	var dataGrid;
	var navtab;
	var pageStatus = "${param.status}";
	var dataTypes = [
	                 {id:'sql',text:'sql'},
	                 {id:'class',text:'自定义'}
					];
	$(function() {
		createGrid();
		$("#form1").initForm({
			toolbar:[{id:'save',text:"保存",icon:'save',click:function(){
				if($('#form1').validate().form())
				{
					var formData = $("#form1").getValues();
			        var data = {};
			        data = formData;
			        if(!validateGrid(dataGrid))
					{
						return false;
					}
			        data["chartDatasets"] = dataGrid.getData();
			        $("body").mask("正在保存数据，请稍后！");
			        $.ajax({
			            url: "chart/chartinfo/saveinfo.do",
			            type: "POST",
			            datatype: "json",
			            contentType: "application/json; charset=utf-8",
			           	data: $.ligerui.toJSON(data),
			            success: function (data, stats) {
			            	$("body").unmask();
			                if (data["success"]) {
			                	$("#id").val(data.data.id);
			                	dataGrid.loadData({
									Rows : data.data["chartDatasets"]
								});
			                	if(api.data.window.Qm)
			                	{
			                		api.data.window.Qm.refreshGrid();
			                	}
			                    top.$.dialog.notice({content:'保存成功'});
			                }else
			                {
			                	$.ligerDialog.error('提示：' + data.msg);
			                }
			            },
			            error: function (data,stats) {
			            	$("body").unmask();
			                $.ligerDialog.error('提示：' + data.msg);
			            }
			        });
			}}
			}],
			getSuccess : function(res) {
				if(res.success){
					$("#form1").setValues({chartTypeId:res.data.chartType.id})
					dataGrid.loadData({
						Rows : res.data["chartDatasets"]
					});
				}
			}
		});
		$.getJSON("chart/bparam/getParams.do",{typeId:"${param.typeId}"},function(res){
			if(res.success){
				var tabdiv = "<div class='navtab' id='childtab'>"
				for(var i in res.data){
					if(res.data[i].chartType.type!='ECHARTS'){
						var tab = "<div title='"+res.data[i].name+"'>"
						var table="";
						if(res.data[i].dataParam=='0'){
							table = "<table border='1' cellpadding='3' cellspacing='0' class='l-detail-table'>"
							for(var j in res.data[i].chartParams){
								var param = res.data[i].chartParams[j];
								var input="";
								if(param.colType=='string'||param.colType=='json'){
									input = "<input name='"+param.code+"' paramtype='"+param.paramType+"' paramColType='"+param.colType+"' ltype='text' title='"+param.remark+"' validate='{maxlength:"+param.colLength+"}' ligerui='{explain:\""+param.remark+"\"}'>";
								}
								if(param.colType=='boolean'){
									input = "<input id='"+param.code+"-txt' paramtype='"+param.paramType+"' paramColType='"+param.colType+"' ltype='select' title='"+param.remark+"' ligerui='{explain:\""+param.remark+"\",valueFieldID:\""+param.code+"\",initValue:\""+param.colDefvalue+"\",data:[{id:\"0\",text:\"否\"},{id:\"1\",text:\"是\"}]}'>"
								}
								if(param.colType == 'number'){
									input = "<input name='"+param.code+"' paramtype='"+param.paramType+"' paramColType='"+param.colType+"' ltype='spinner' title='"+param.remark+"' ligerui='{explain:\""+param.remark+"\",initValue:\""+param.colDefvalue+"\",type:\"int\",minValue:\""+param.colSmall+"\",maxValue:\""+param.colBig+"\"}' validate='{maxlength:"+param.colLength+"}'>";
								}
								if(param.colType=='color'){
									input = "<input name='"+param.code+"' paramtype='"+param.paramType+"' paramColType='"+param.colType+"' ltype='text' title='"+param.remark+"' validate='{maxlength:"+param.colLength+"}' ligerui='{explain:\""+param.remark+"\",iconItems:[{icon:\"edit\",click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}'>";
								}
								if(param.colType=='select'){
									bindData = param.bindData.replace(/'/g,"\"")
									input = "<input id='"+param.code+"-txt' paramtype='"+param.paramType+"' paramColType='"+param.colType+"' ltype='select'  title='"+param.remark+"' validate='{maxlength:"+param.colLength+"}' ligerui='{explain:\""+param.remark+"\",valueFieldID:\""+param.code+"\",data:"+bindData+"}'>"
								}
								var tr = "<tr><td class='l-t-td-left' style='width:150px' >"+param.name+"</td><td class='l-t-td-right'>"
								+input
								+"</td></tr>"
								table = table +tr;
							}
							table = table +"</table>";
						}else{
							table = "<table border='1' cellpadding='3' cellspacing='0' class='l-detail-table'>";
							var input ="<textarea readonly='readonly' paramtype='dataArray' paramColType='dataArray' bparamId='"+res.data[i].id+"' title='"+res.data[i].name+"' name='"+res.data[i].code+"' cols='70' rows='10' class='l-textarea' ligerui='{explain:\""+res.data[i].remark+"\",disabled:false,iconItems:[{icon:\"edit\",click:function(val,e,srcObj){getMParamData(val,e,srcObj)}}]}'></textarea>";	
							var tr = "<tr><td class='l-t-td-left' style='width:150px' >"+res.data[i].name+"</td><td class='l-t-td-right'>"
							+input
							+"</td></tr>"
							table = table +tr;
							table = table +"</table>";
						}
						tab = tab +table +"</div>";
						tabdiv = tabdiv + tab;
					}else{
						////////echarts相关
						var tab = "<div title='"+res.data[i].name+"'>"
						var table="";
						if(res.data[i].dataParam=='0'){
							table = "<table border='1' cellpadding='3' cellspacing='0' class='l-detail-table'>"
							for(var j in res.data[i].chartParams){
								var param = res.data[i].chartParams[j];
								var input="";
								if(param.colType=='string'||param.colType=='json'){
									input = "<input name='"+res.data[i].code+"_"+param.code+"' paramtype='"+param.paramType+"' paramColType='"+param.colType+"' ltype='text' title='"+param.remark+"' validate='{maxlength:"+param.colLength+"}' ligerui='{explain:\""+param.remark+"\"}'>";
								}
								if(param.colType=='boolean'){
									input = "<input id='"+res.data[i].code+"_"+param.code+"-txt' paramtype='"+param.paramType+"' paramColType='"+param.colType+"' ltype='select' title='"+param.remark+"' ligerui='{explain:\""+param.remark+"\",valueFieldID:\""+res.data[i].code+"_"+param.code+"\",initValue:\""+param.colDefvalue+"\",data:[{id:\"0\",text:\"否\"},{id:\"1\",text:\"是\"}]}'>"
								}
								if(param.colType == 'number'){
									input = "<input name='"+res.data[i].code+"_"+param.code+"' paramtype='"+param.paramType+"' paramColType='"+param.colType+"' ltype='spinner' title='"+param.remark+"' ligerui='{explain:\""+param.remark+"\",initValue:\""+param.colDefvalue+"\",type:\"int\",minValue:\""+param.colSmall+"\",maxValue:\""+param.colBig+"\"}' validate='{maxlength:"+param.colLength+"}'>";
								}
								if(param.colType=='color'){
									input = "<input name='"+res.data[i].code+"_"+param.code+"' paramtype='"+param.paramType+"' paramColType='"+param.colType+"' ltype='text' title='"+param.remark+"' validate='{maxlength:"+param.colLength+"}' ligerui='{explain:\""+param.remark+"\",iconItems:[{icon:\"edit\",click:function(val,e,srcObj){showFiles(val,e,srcObj)}}]}'>";
								}
								if(param.colType=='select'){
									bindData = param.bindData.replace(/'/g,"\"")
									input = "<input id='"+res.data[i].code+"_"+param.code+"-txt' paramtype='"+param.paramType+"' paramColType='"+param.colType+"' ltype='select'  title='"+param.remark+"' validate='{maxlength:"+param.colLength+"}' ligerui='{explain:\""+param.remark+"\",valueFieldID:\""+res.data[i].code+"_"+param.code+"\",data:"+bindData+"}'>"
								}
								var tr = "<tr><td class='l-t-td-left' style='width:150px' >"+param.name+"</td><td class='l-t-td-right'>"
								+input
								+"</td></tr>"
								table = table +tr;
							}
							table = table +"</table>";
						}else{
							table = "<table border='1' cellpadding='3' cellspacing='0' class='l-detail-table'>";
							var input ="<textarea readonly='readonly' paramtype='dataArray' paramColType='dataArray' bparamId='"+res.data[i].id+"' title='"+res.data[i].name+"' name='"+res.data[i].code+"' cols='70' rows='10' class='l-textarea' ligerui='{explain:\""+res.data[i].remark+"\",disabled:false,iconItems:[{icon:\"edit\",click:function(val,e,srcObj){getMParamData(val,e,srcObj)}}]}'></textarea>";	
							var tr = "<tr><td class='l-t-td-left' style='width:150px' >"+res.data[i].name+"</td><td class='l-t-td-right'>"
							+input
							+"</td></tr>"
							table = table +tr;
							table = table +"</table>";
						}
						tab = tab +table +"</div>";
						tabdiv = tabdiv + tab;
					}
				}
				tabdiv = tabdiv +"</div>"
				$(tabdiv).appendTo("#form2");
				$("#childtab").ligerTab().setTabButton();
				//初始化表单2
				$("#form2").initForm({
					toolbar:[{id:'save',text:"保存",icon:'save',click:function(){
							if($("#id").val()==undefined||$("#id").val()==null||$("#id").val()==''){
								$.ligerDialog.warn("请先保存图表信息");
								navtab.selectTabItem("tabitem1")
								return;
							}
							var data={};
							var dataParam = {};
							var dataColType = {};
							$("#form2 :input").not(":submit, :reset, :image,:button, [disabled],[groupChild=true]").each(function() {
								var ele = $(this);
								if(ele.attr("paramtype")==''||ele.attr("paramtype")==undefined||ele.attr("paramtype")==null){
									dataParam[ele.attr("name")]=$("#"+ele.attr("name")+"-txt").attr("paramtype");
									dataColType[ele.attr("name")]=$("#"+ele.attr("name")+"-txt").attr("paramColType");
								}else{
									if(ele.attr("name")!=''&&ele.attr("name")!=undefined&&ele.attr("name")!=null){
										dataParam[ele.attr("name")]=ele.attr("paramtype");
										dataColType[ele.attr("name")]=ele.attr("paramColType");
									}
								}
							})
							data["paramValue"]=$("#form2").getValues();
							data["paramType"]=dataParam;
							data["paramColType"]=dataColType;
							data["chartInfoId"]=$("#id").val();
							$("body").mask("正在保存数据，请稍后！");
							$.post("chart/paramresult/saveparam.do",{paramData:$.ligerui.toJSON(data).toString()},function(res){
								$("body").unmask();
								if(res.success){
									top.$.dialog.notice({content:'保存成功'});
								}else{
									$.ligerDialog.error('提示：' + data.msg);
								}
							},"json")
						}
					}],
					success:function(){
						
					},getSuccess:function(res){
						if(res.success){
							//alert(eval(res.data.paramValue))
							//$("#form2").setValues(eval(res.data.paramValue))
							//alert(res.data.paramValue)
							//eval("var tt="+res.data.paramValue)
							if(res.data){
								$("#form2").setValues($.parseJSON(res.data))
							}
						}
					}
				});
				
			}
		})
		
		
		navtab = $(".navtab").ligerTab({
			onAfterSelectTabItem : function(tabid) {
				if(tabid == 'tabitem3'){
					var a='';
					if(pageStatus=='detail'){
						a=$.trim($("#chartNum").text());
					}else{
						a=$.trim($("#chartNum").val());
					}
					if(a!=''&&a!=null&&a!=undefined){
						RenderChart(a,"","","tbyl","ylChartId"+Math.random(100));
					}
				}
			}
		});
	});
	function createGrid() {
	    var columns=[
	        { display: 'id', name: 'id', hide:true},
	        { display: '数据类型', width: '20%', name: 'dataType', type: 'text',required:true,align : 'center',editor: { type: 'select', data: dataTypes ,ext:{emptyOption:false}},
	            render: function (item) {
	                return render(item["dataType"],dataTypes);
	            }
	        },
	        { display: '数据', name: 'dataSet', width: '40%', editor: { type: 'textarea',height:'80'},required:true,maxlength:4000,align : 'left',render:function(data){
	        	if(data.dataSet==undefined){
	        		data.dataSet="";
	        	}
				return "<div title='"+data.dataSet+"'>"+data.dataSet+"</div>"
			}},
	        { display: '排序', name: 'sort', width: '10%',required:true, editor: { type: 'text'},maxlength:20,align : 'center'}
	    ];
	    if(pageStatus!="detail"){
	        columns.push({ display: "<a class='l-a l-icon-add' href='javascript:void(0);' onclick='addNewRow()' title='增加'><span>增加</span></a>", isSort: false, width: '4%', render: function (rowdata, rowindex, value) {
	            var h = "";
	            if (!rowdata._editing) {
	                h += "<a class='l-a l-icon-del' href='javascript:deleteRow()' title='删除'><span>删除</span></a> ";
	            }
	            return h;
	        }
	        });
	    }
	    dataGrid = $("#dataset").ligerGrid({
	    	columns: columns,
	        enabledEdit: pageStatus!="detail",
	        clickToEdit: true,
	        rownumbers: true,                         //是否显示行序号
	        frozenRownumbers: false,
	        usePager: false,
	        data: {Rows: [
	        ]}
	    });
	}
	function render(value,data){
	    for (var i in data) {
	    	if (data[i]["id"] == value)
	        {
	        	return data[i]['text'];
	        }
	    }
	    return value;
   }
	function addNewRow(name) {
		dataGrid.addEditRow({}); //添加一行
	}
	function deleteRow(name) {
	    var data = dataGrid.getSelectedRow();
	    if (data.id) {
	        $.ligerDialog.confirm(kui.DEL_MSG, function (yes) {
	            if (yes) {
	                $.getJSON("chart/dataset/delete.do", {ids: data.id}, function (json) {
	                    if (json.success) {
	                    	dataGrid.deleteSelectedRow();
	                    }
	                });
	            }
	        });
	    } else {
	    	dataGrid.deleteSelectedRow();
	    }
	}
	var _this;
	function getMParamData(val,e,srcObj){
		_this = e;
		var bparamid = $(srcObj).parent().find('textarea').attr("bparamId");
		top.$.dialog({
			width:'800px',
			height:'500px',
			lock:true,
			parent:api,
			title:"属性设置",
			content: 'url:pub/chart/chart/mparam_detail.jsp?status='+pageStatus+"&bparamId="+bparamid,
			data:{window:window,data:val}
		}).max();
	}
	function showFiles(val,e,srcObj){
		_this = e;
		top.$.dialog({
			width:'340px',
			height:'190px',
			lock:true,
			parent:api,
			title:"颜色选择",
			content: 'url:pub/chart/chart/color_detail.jsp',
			data:{window:window}
		});
	}
	function setMparamValue(retvalue){
		try {
			_this.setValue(retvalue);
		} catch (e) {
			$("input[name='"+_this.id+"']").val(retvalue);
			_this.src=retvalue;
		}
	}
	function setColorValue(retvalue){
		try {
			_this.setValue(retvalue);
		} catch (e) {
			$("input[name='"+_this.id+"']").val(retvalue);
			_this.src=retvalue;
		}
	}
	
	function zh(obj){
		//钻取测试
		alert(obj)
		alert('我在钻取...');
	}
</script>
</head>
<body>
	<div class="navtab">
		<div title="<span style='color:red'>图表</span>" lselected="true">
			<form id="form1" action="chart/chartinfo/saveinfo.do"
				getAction="chart/chartinfo/detail.do?id=${param.id}" name="form1">
				<fieldset class="l-fieldset">
					<legend class="l-legend"> 图表信息 </legend>
					<input type="hidden" name="id" id="id" /> <input type="hidden"
						name="chartTypeId" value="${param.typeId}" />
					<table border="1" cellpadding="3" cellspacing="0"
						class="l-detail-table">
						<tr>
							<td class="l-t-td-left">图表编号：</td>
							<td class="l-t-td-right"><input id="chartNum"
								name="chartNum" type="text" ltype='text'
								validate="{required:true,maxlength:32}" />
							</td>
							<td class="l-t-td-left">图表名称：</td>
							<td class="l-t-td-right"><input id="chartName"
								name="chartName" type="text" ltype='text'
								validate="{required:true,maxlength:128}" />
							</td>
						</tr>
						<tr>
							<td class="l-t-td-left">图表描述：</td>
							<td class="l-t-td-right" colspan="3"><textarea rows="5"
									cols="20" name="chartRemark" ltype="textarea"
									validate="{maxlength:512}"></textarea>
									</td>
						</tr>
					</table>
				</fieldset>
				<fieldset class="l-fieldset">
					<legend class="l-legend">
						<div>数据集</div>
					</legend>
					<div id="dataset"></div>
				</fieldset>
			</form>
		</div>

		<div title="图表参数">
			<form id="form2" action="chart/chartinfo/saveParam.do"
				getAction="chart/paramresult/getParamResultValue.do?chartinfoId=${param.id}" name="form2">
			</form>
		</div>
		<div title="图表预览">
			<div id="tbyl"
				style="margin:10px; width:98%; height:96%; border:hidden;">
				<font color="#999999">正在加载图形数据,请稍候...</font>
			</div>
		</div>
	</div>
</body>
</html>
