<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<%@ taglib uri="http://bpm.khnt.com" prefix="fb"%>
   <script src="/k/kui/frame/ligerGrid.js" type="text/javascript"></script>
   <script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
	<!-- 每个页面引入，页面编码、引入js，页面标题 -->
	<script type="text/javascript">
	
	$(function(){
		$('#nextUserName').focus(function(){
			selectUnitOrUser('1','0','spemploye','spuserName',function(data){
				$('#nextUserName').val(data.name);
				$('#nextUserId').val(data.code);
			})
		})
		$('#formObj').initForm({
			<c:if test="${param.status=='detail'}">
				<c:if test="${param.personDetail!='1'}">
					showToolbar:true,//只在status=detail的时候此参数有用
				</c:if>
				toolbar:[
					  	{
					  		text: "提交",
					    	icon: "right",
					    	click: function(){
					    		var opinion = isSigned();
					    		if(opinion)
					    		{
					    			<c:if test="${param.personDetail!='1'}">
						    			<fb:ifPer activityId="${param.activityId}" function="nextPeron">
							    			if($('#nextUserId').val()==''||$('#nextUserId').val()==undefined)
								    		{
								    			//请先签署意见
								    			$.ligerDialog.alert("请选择下一步处理人", "提示",function(){
								    				nextPerson();
								    			});
								    			return;
								    		}
						    			</fb:ifPer>
						    			$.ligerDialog.confirm("确定要提交吗？", "选择", function(res){
											if(res){
												//流程提交
												subFlow(opinion.opinion,$('#nextUserId').val());
											}
										});
					    			</c:if>
					    		}else
					    		{
					    			//请先签署意见
					    			$.ligerDialog.alert("请先签署意见！", "提示",function(){
										signOpinion();
									});
					    		}
					    	}
					  	},
					  	{line:true},
					    {
					  		text: "签署意见",
					    	icon: "modify",
					    	click: signOpinion
					    },
					    <c:if test="${param.personDetail!='1'}">
					  	<fb:ifPer activityId="${param.activityId}" function="nextPeron">	
						  	{line:true},
						  	{
						  		text: "下一步处理人",
						    	icon: "modify",
						    	click: nextPerson
						  	},
					  	</fb:ifPer>
					 	</c:if>
					    {line:true},
					    {
					    	text: "流程监控",
					    	icon: "compute-2",
					    	click: function(){
					    		var width=top.$(window).width();
				                var height=top.$(window).height();
				                width=top.$("body").width()*0.8;
				                height=top.$("body").height()*0.9;
				                var windows=top.$.dialog({
				                    width:width,
				                    height:height,
				                    lock:true,
				                    parent:api,
				                    title:"流程监控",
				                    data:{"window":window},
				                    content: 'url:pub/bpm/FlowTrask_index.jsp?id='+"${param.processId}"
				                });
					    	}
					    }       
					],
					toolbarPosition:'top',
			</c:if>
			<c:if test="${param.status!='detail'}">
			toolbar:[
					  	{
					  		text: "保存",
					    	icon: "save",
					    	click: function(){
					    		$('input[name="istj"]').val("0");//保存
					    		$('#formObj').submit();
					    	}
					  	},
					    {
					  		text: "报批",
					    	icon: "submit",
					    	click: function()
					    	{
					    		$.ligerDialog.confirm("确认提交报批？", function (yes) 
							    { 
					    			if(yes)
					    			{
					    				$('input[name="istj"]').val("1");
					    				var val=$('input:radio[name="way"]:checked').val();
					    				var flowId='zpinner000';
					    				if(val=='1')
					    				{
					    					flowId='zpinner001';
					    				}
					    				var url = "app/zp/inner/save.do?flowId="+flowId;
					    				$('#formObj').attr('action',url);
					    				$('#formObj').submit();
					    			}
							    })
					    	}
					    },
					    {
					    	text: "关闭",
					    	icon: "cancel",
					    	click: function(){
					    		api.close();
					    	}
					    }       
					],
			</c:if>
			success : function(responseText) {//处理成功
				if (responseText.success) {
					if($('input[name="istj"]').val()=='0')
					{
						top.$.dialog.notice({content:'保存成功！'});
						$('input[name="id"]').val(responseText.data.id);
						api.data.window.Qm.refreshGrid();
					}
					if($('input[name="istj"]').val()=='1')
					{
						var nextUser = responseText.nextUser;
	                    var userNames = "";
	                    var dialogContent = "";
	                    for(var i = 0;i<nextUser.length;i++){
	                        var temp = nextUser[i];
	                        if(temp.userId == "end"){//流程结束
	                            dialogContent ="审核结束";
	                            break;
	                        }
	                        userNames += "【"+temp.userName+"】,";
	                    }
	                    if(userNames){
	                        userNames = userNames.substring(0,userNames.length-1);
	                        dialogContent = "已提交给"+userNames+"审核！"
	                    }
	                    top.$.dialog.notice({content:dialogContent,lock:false});
	                    api.data.window.Qm.refreshGrid();
						api.close()
					}
				} else {
					$.ligerDialog.error(responseText.msg)
				}
			},
		 getSuccess:function(responseText){
			 $('#gwname').val(responseText.data.gwname);
			 $('#ypname').val(responseText.data.name);
		 }
		});
		//-------------------------选择岗位需求
		$('#gwname').ligerComboBox({
            onBeforeOpen:f_selectContact
        });
		function f_selectContact()
		{
			$.ligerDialog.open(
					{ title: '选择岗位需求', name:'winselector',width: 800, height: 400, 
					url: 'app/zpmanage/other/inner/xq_list.jsp',
					buttons:[
					 {text: '确定', onclick: f_selectContactOK },
	                 { text: '取消', onclick: f_selectContactCancel}
	                ]                    
				})
				return false;
		}
		function f_selectContactOK(item,dialog)
		{
			var info = new Array() ;
			info = dialog.frame.getSelected();
			$('input[name="fkXqId"]').val(info[0]);
			$('input[name="gwname"]').val(info[1])
			$('#gwname').val(info[1]);
			dialog.close();
		}
		function f_selectContactCancel(item,dialog)
		{
			dialog.close();
		}
		//-------------------------选择简历需求
		$('#ypname').ligerComboBox({
            onBeforeOpen:f_selectContact1
        });
		function f_selectContact1()
		{
			$.ligerDialog.open(
					{ title: '选择简历信息', name:'winselector',width: 800, height: 400, 
					url: 'app/zpmanage/other/inner/jl_list.jsp',
					buttons:[
					 {text: '确定', onclick: f_selectContactOK1 },
	                 { text: '取消', onclick: f_selectContactCancel}
	                ]                    
				})
				return false;
		}
		function f_selectContactOK1(item,dialog)
		{
			var info = new Array() ;
			info = dialog.frame.getSelected();
			$('input[name="fkJlId"]').val(info[0]);
			$('input[name="name"]').val(info[1])
			$('#ypname').val(info[1])
			dialog.close();
		}
	})
	
	function isSigned(){//检查是否已签署意见
		var opinionObj = null;
		$.ajax({
			type: "POST",
			url: "app/rsyd/opinion/getOpinion.do",
			data: "activityId=${param.activityId}&businessId=${param.id}",
			async: false,
			success: function(responseContext){
				if(responseContext.success){
					opinionObj = responseContext.data;
				}
			}
		});
		return opinionObj;
	}
	function signOpinion()//签署意见
	{
		var pageStatus = "add";
		if(isSigned()){
			pageStatus = "modify";
		}
		var activityName = escape("${param.activityName}").replace(/%/g,"\\");
		var url = 'url:app/rsyd/opinion.jsp?activityId=${param.activityId}&businessId=${param.id}&pageStatus='+pageStatus+'&activityName='+activityName;
		var width=400;
		var height=300;
		top.$.dialog({
			width:width,
			height:height,
			lock:true,
			parent:api,
			title:"签署意见",
			data:{"window":window},
			content: url
		});
	}
	//流程提交
    function subFlow()
	{
        var result = arguments[0];//审批结果:0不通过，1通过
        var nextUserId = arguments[1];//下一步处理人
        var params = {activityId : "${param.activityId}",operationId : "${param.id}",opinionResult : result,nextUserId:nextUserId};
        $.post("app/zp/inner/flowSubmit.do",params,function(data){
            if(data.success){
            	try{
                    var nextUser = data.nextUser;
                    var userNames = "";
                    var dialogContent = "";
                    for(var i = 0;i<nextUser.length;i++){
                        var temp = nextUser[i];
                        if(temp.userId == "end"){//流程结束
                            dialogContent ="审核结束";
                            break;
                        }
                        userNames += "【"+temp.userName+"】,";
                    }
                    if(userNames){
                        userNames = userNames.substring(0,userNames.length-1);
                        dialogContent = "已提交给"+userNames+"审核！"
                    }
                    top.$.dialog.notice({content:dialogContent,lock:false});
                    if(api.data.window.Qm){
                        api.data.window.Qm.refreshGrid();
                    }
                    api.close();
                }catch(e){
                    alert(e);
                }
            }else{
                $.ligerDialog.success('流程提交失败');
            }
        });
    }
    function nextPerson()
	{
		selectUnitOrUser('1','0','spemploye','spuserName',function(data){
			//$('#nextUserName').val(data.name);
			$('#nextUserId').val(data.code);
		})
	}
	Date.prototype.format = function(format)
	{
		var o =
		{
		"M+" : this.getMonth()+1, //month
		"d+" : this.getDate(),  //day
		"h+" : this.getHours(),  //hour
		"m+" : this.getMinutes(), //minute
		"s+" : this.getSeconds(), //second
		"q+" : Math.floor((this.getMonth()+3)/3),  //quarter
		"S" : this.getMilliseconds() //millisecond
		}
		if(/(y+)/.test(format))
		format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
		for(var k in o)
		if(new RegExp("("+ k +")").test(format))
		format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
		return format;
	}
</script>
</head>
	<body>
		<form name="formObj" id="formObj" method="post"
			action="app/zp/inner/save.do" 
			getAction="app/zp/inner/detail.do?id=${param.id }"
			>
			<div class="navtab" id="tabDiv">
				<div title="岗位分配信息" lselected="true" style="height: 400px">
					<fieldset class="l-fieldset">
						<legend class="l-legend">
							<div>需求信息</div>
						</legend>
						<table border="0" cellpadding="3" cellspacing="0" width="" height=""
							align="" class="l-detail-table">
							 <tr>
							    <td class="l-t-td-left">岗位名称：</td>
							    <td class="l-t-td-right">
							    	<c:if test="${param.status!='detail' }">
							    	
							    		<input type="text" id="gwname" validate="{required:true}" />
								    	<input name="fkXqId" type="hidden"/>
								    	<input type="hidden"  name="gwname" />
							    	</c:if>
							    	<c:if test="${param.status=='detail' }">
								    	<input name="fkXqId" type="hidden"/>
								    	<input type="text"  name="gwname" ltype="text" />
							    	</c:if>
							    </td>
							  </tr>
							  <tr>
							  	<td class="l-t-td-left">审批类型：</td>
							    <td class="l-t-td-right">
							    	<c:if test="${param.status!='detail'}">
					                    <input type="radio" id="way" name="way" ltype="radioGroup" validate="{required:true}"
					                               ligerui="{value:'1', data: [ { text:'聘用人员申请审批', id:'1' }, { text:'名誉教授（客座教授、兼职教师）聘用申请审批', id:'0' }]}"/>
					                </c:if>
					                <c:if test="${param.status=='detail'}">
					                	<input type="radio" name="way" ltype="radioGroup" validate="{required:true}"
					                               ligerui="{data: [ { text:'聘用人员申请审批', id:'1' }, { text:'名誉教授（客座教授、兼职教师）聘用申请审批', id:'0' }]}"/>
					                </c:if>  
							    </td>
							  </tr>
						</table>
					</fieldset>
					<fieldset class="l-fieldset">
						<legend class="l-legend">
							<div>简历信息</div>
						</legend>
						<table border="0" cellpadding="3" cellspacing="0" width="" height=""
							align="" class="l-detail-table">
							<input type="hidden" name="id" value="${param.id}"/>
							<input type="hidden" name="istj"/>
							<tr>
								<td class="l-t-td-left">应聘者姓名：</td>
								<td class="l-t-td-right">
									<c:if test="${param.status!='detail' }">
							    		<input type="text"  id="ypname"  validate="{required:true}" />
								    	<input name="fkJlId" type="hidden"/>
								    	<input type="hidden"  name="name" />
							    	</c:if>
							    	<c:if test="${param.status=='detail' }">
								    	<input name="fkJlId" type="hidden"/>
								    	<input type="text"  name="name" ltype="text"/>
							    	</c:if>
								</td>
							</tr>
							<input type="hidden" id="nextUserId" name="nextUserId" ltype="text"/>
								<c:if test="${param.status!='detail' }">
									<tr>
										<td class="l-t-td-left">下一步处理人：</td>
										<td class="l-t-td-right">
											<input type="text" id="nextUserName" name="nextUserName" ltype="text" validate="{required:true}"/>
										</td>    
									</tr>
								</c:if>
						</table>
					</fieldset>
				</div>
				<c:if test="${param.status=='detail'&&param.datastatus!='0'}">
					
					<div id="yj" title="意见列表">
						<script type="text/javascript">
	                        $("#yj").html('<iframe id="opinionIframe" name="opinionIframe" src="app/rsyd/opinionList.jsp?operationId=${param.id}" frameborder="0" style="width:100%;height:'+($(window).height()-60)+'px" marginheight="0" marginwidth="0"></iframe>');
	                    </script>
					</div>
				</c:if>
			</div>
		</form>
	</body>
</html>
