<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head pageStatus="${status}">
		<title></title>						
		<style type="text/css">
		.table-up-filelist {border:1px solid #96c2f1;border-right:0px;border-bottom:1px;border-collapse:collapse;}
		.table-up-filelist td {padding:5px;border:1px solid #96c2f1;border-top:0px;border-left:1px;}
		.table-up-filelist-tr td {font-weight:bold}
		.table-up-filelist-td {border:1px solid #96c2f1;background:#eff7ff;text-align:center;}
		.table-up-filelist-e-td1 {width:350px;}
		.table-up-filelist-e-td2 {text-align:center;width:150px;}
		.table-up-filelist-e-td3 {text-align:center;width:150px;}
		</style>
		<!-- 每个页面引入，页面编码、引入js，页面标题 -->
		<%@include file="/k/kui-base-form.jsp"%>
		<script type="text/javascript">
		var pageStatus = "${status}";
		var id = "${param.id}";
		var report_id = "${param.report_id}";
		var device_id = "${param.device_id}";

		$(function() {
				$("#form1").initForm({ //参数设置示例
					toolbar : [ {
						text : '确定',
						//id : 'save',
						icon : 'save',
						click : save
					},{
						text : '关闭',
						//id : 'close',
						icon : 'cancel',
						click : close
					}],
					getSuccess : function(res) {
					
					},
					afterParse:function(){
						setValue =	function(obj,name,tdid,pageIndex,id_name){							
							$("#"+tdid).find("input[name='"+name+"']").val(obj.value);
						}	
					}
				});
			})
		
			function close(){	
				 api.close();
			}
			
			function save(){
				//验证表单数据
				if($('#form1').validate().form())
				{
					var formData = $("#form1").getValues();
			        var data = {};
			        data = formData;
			        //var a1=$("input[name=report_item]:checked").val();$.ligerDialog.alert(a1);return;
			        var cObj=document.getElementsByName("record_item");
			    	//var cPep=document.getElementsByName("page_inspection_op");
			    	
			    	var xsq=0
			    	var cArr=[];
			    	var carr_1=[];
			    	for (var i = 0; i < cObj.length; i++) {
			    		var inputObj=cObj[i];
			    		//var CpeObj = cPep[i];
			    		if (inputObj.type.toLowerCase()=="text") {
			    			cArr[cArr.length]=inputObj.value;
			    		}
			    		
			    		if (inputObj.checked) {
							//alert($(inputObj).parent().parent().parent().text().trim().indexOf("限速器"));
				    		if($(inputObj).parent().parent().parent().text().indexOf("限速器")!=-1){
				    			xsq=xsq+1;
				    		}
			    			cArr[cArr.length]=inputObj.value;
			    		}
			    	}
			    	
			    	var report_item = carr_1.join(",");
			    	var xsqsb = cArr.join(",");
			    	url = "report/item/record/saveGcItem.do?id="+id+"&report_id="+report_id+"&record_item="+xsqsb;
					
			        $("body").mask("正在保存数据，请稍后！");
			        $.ajax({
			            url: url,
			            type: "POST",
			            dataType: "json",
			           	data: {dataStr : $.ligerui.toJSON(data)},
			            success: function (resp) {
			            	$("body").unmask();
			                if (resp["success"]) {
			                	if(api.data.window.Qm){
			                		api.data.window.Qm.refreshGrid();
			                	}
			                    top.$.dialog.notice({content:'保存成功'});
			                    top.$.dialog({
			            			width : 800, 
			            			height : 500, 
			            			lock : true, 
			            			title:"原始记录",
			            			content: 'url:report/item/record/modifyGcRecord.do?isSub=no&report_id='+report_id+'&id='+id+'&device_id='+device_id,
			            			data : {"window" : window}
			            		}).max();
			                }else{
			                	$.ligerDialog.error('提示：' + resp.msg);
			                }
			            },
			            error: function (resp) {
			            	$("body").unmask();
			                $.ligerDialog.error('提示：' + resp.msg);
			            }
			        });
				}
			}
	var setValue = function(){}	
	</script>
	</head>
	<body>
	<form id="form1"   >	
		<table  width="100%" height="24" class="table-up-filelist">
			<tr class="table-up-filelist-tr">
				<td width="350" class="table-up-filelist-td">原始记录目录</td>
			</tr>
		</table>
		<table  width="100%" class="table-up-filelist">
			<input type="hidden" name="report_id" value="${param.report_id}">
			<input type="hidden" name="id" >
			<c:forEach items="${recordItems}" var="item" varStatus="vs"> 
				<tr id="trp-${vs.index}" class="table-up-filelist-e-tr"> 
					<c:choose>
						<c:when test="${item.is_must eq 'yes'}">	
							<td class="table-up-filelist-e-td1">
							&nbsp;√&nbsp;<div style="display:none;"><input type="checkbox" name="record_item" value="${item.page_index}"  checked /></div>&nbsp;${item.index_text}
							</td>
						</c:when>
						<c:when test="${item.is_must eq 'no'}">
							<td class="table-up-filelist-e-td1">
								<input style="border:0;border-bottom:1 solid black;" type="checkbox" name="record_item" value="${item.page_index}"  ${item.is_disCheck} />&nbsp;${item.index_text}
							</td>
						</c:when>
					</c:choose>					
				</tr>
			</c:forEach>	
		</table>
	</form>
	</body>
</html>