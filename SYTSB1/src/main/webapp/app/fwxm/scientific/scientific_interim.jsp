<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>

    <script type="text/javascript">
    var id="";
    $(function() {
    	
    	 var tbar=[{
				text : '保存',
				icon : 'save',
				click : save
			}, {
				text : '取消',
				icon : 'cancel',
				click : function() {
					api.close();
				}
			},{
				text : '导出',
				icon : 'print',
				click : input
			}];
    	 $("#form1").initForm({
    			showToolbar: true,
    			toolbar :tbar,
    			toolbarPosition : "bottom",
    			success : function(response) {//处理成功
    				if (response.success) {
    					top.$.notice("保存成功！");
    					//保存基本信息（主表）后，id未自动赋值，故此处手动赋值
    					
    					api.data.window.Qm.refreshGrid();
    					api.close();
    				} else {
    					$.ligerDialog.error('保存失败！<br/>' + response.msg);
    				}
    			},
    			//取得图片
    			getSuccess : function(res) {
    				if($("#id").val()==""){
    					$("#fkScientificId").val("${param.id}");
    					$.ajax({
    	     				url : "tjy2ScientificResearchAction/detailBase.do?id=${param.id}",
    	     				type : "POST",
    	     				success : function(data, stats) {
    	     					$("body").unmask();
    	     					if (data["success"]) {
    	     						$("#projectName").val(data.data.projectName);
    	     						$("#projectNo").val(data.data.projectNo);
    	     						$("#projectHead").val(data.data.projectHead);
    	     						var startDate=data.data.startDate;
    	     						var  endDate=data.data.endDate;
    	     						$("#projectStartEnd").val((startDate.substr(0,10)).replace(/-/g,"年")+"-"+(endDate.substr(0,10)).replace(/-/g,"年"));
    	     						$("#approvalMoney").val(data.data.p900001);
    	     					} else {
    	     						$.ligerDialog.error('提示：' + data);
    	     					}
    	     				},
    	     				error : function(data, stats) {
    	     					$("body").unmask();
    	     					$.ligerDialog.error('提示：' + JSON.stringify(data));
    	     				}
    	     			});
    				
    				
    				}else{
    					id=res.data.id;
    				}
    				}
    		});
    	
    	
    });
    function save(){
    	$("#form1").submit();
    }
    function input(){
     	window.location.href ="/tjy2ScientificInterimAction/input.do?id="+id;
    }
    </script>
</head>
<body>
    <form id="form1" action="tjy2ScientificInterimAction/save.do" getAction="tjy2ScientificInterimAction/detailInterim.do?id=${param.id}">
       <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
       <input type="hidden" name="id" id="id"></input>
       <input type="hidden" name="fkScientificId"  id="fkScientificId"></input>
       <tr> 
        <td class="l-t-td-left"> 项目名称:</td>
        <td class="l-t-td-right"> 
        <input type="text" ltype='text' name="projectName" id="projectName" validate="{required:true}"/>
        </td>
        <td class="l-t-td-left"> 项目编号:</td>
        <td class="l-t-td-right"> 
         <input type="text" ltype='text' name="projectNo" id="projectNo" validate="{required:true}"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 项目来源:</td>
        <td class="l-t-td-right"> 
         <input type="text" ltype='text' name="projectSource" id="projectSource" validate="{required:true}"/>
        </td>
        <td class="l-t-td-left"> 项目负责人:</td>
        <td class="l-t-td-right"> 
         <input type="text" ltype='text' name="projectHead" id="projectHead" validate="{required:true}"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 项目参与人:</td>
        <td class="l-t-td-right">
         <input type="text" ltype='text' name="projectParticipationMan" id="projectParticipationMan" validate="{required:true}"/>
        </td>
        <td class="l-t-td-left"> 批准经费:</td>
        <td class="l-t-td-right">
         <input type="text" ltype='text' name="approvalMoney" id="approvalMoney" validate="{required:true}"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 项目计划起止日期:</td>
        <td class="l-t-td-right">
         <input type="text" ltype='text' name="projectStartEnd" id="projectStartEnd" validate="{required:true}"/>
        </td>
        <td class="l-t-td-left"> 中期检查时间:</td>
        <td class="l-t-td-right">
         <input type="text" ltype='date' name="projectMidDate" id="projectMidDate" validate="{required:true}"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 项目研究阶段:</td>
        <td class="l-t-td-right">
        <u:combo name="projectPhase" code="project_phase" validate="required:true"></u:combo>
        </td>
         </tr>
         <tr>
        <td class="l-t-td-left"> 本年度计划任务:</td>
        <td class="l-t-td-right" colspan="3"> <textarea  name="scheduledTask" id="scheduledTask" rows='5' cols='30' class='l-textarea' ltype='textarea'></textarea>
        </td>
      </tr>
       <tr> 
        <td class="l-t-td-left"> 进展情况:</td>
        <td class="l-t-td-right"> 
         <u:combo name="projectProgress" code="project_progress" validate="required:true"></u:combo>
        </td>
         </tr>
         <tr>
        <td class="l-t-td-left"> 进展情况说明:</td>
        <td class="l-t-td-right" colspan="3"> 
         <textarea  name="progressInstructions" id="progressInstructions" rows='5' cols='30' class='l-textarea' ltype='textarea'></textarea>
        </td>
      </tr>
       <tr> 
        <td class="l-t-td-left"> 已支出总金额:</td>
        <td class="l-t-td-right"> 
         <input type="text" ltype='text' name="totalCost" id="totalCost" />
        </td>
        <td class="l-t-td-left"> 设备费:</td>
        <td class="l-t-td-right"> 
         <input type="text" ltype='text' name="totalCost1" id="totalCost1" />
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 材料费:</td>
        <td class="l-t-td-right"> 
         <input type="text" ltype='text' name="totalCost2" id="totalCost2" />
        </td>
        <td class="l-t-td-left"> 测试化验加工费:</td>
        <td class="l-t-td-right"> 
         <input type="text" ltype='text' name="totalCost3" id="totalCost3" />
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 差旅费:</td>
        <td class="l-t-td-right"> 
         <input type="text" ltype='text' name="totalCost4" id="totalCost4" />
        </td>
        <td class="l-t-td-left"> 会议费:</td>
        <td class="l-t-td-right"> 
         <input type="text" ltype='text' name="totalCost5" id="totalCost5" />
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 出版/文献/信息传<br/>播/知识产权事务费:</td>
        <td class="l-t-td-right"> 
         <input type="text" ltype='text' name="totalCost6" id="totalCost6" />
        </td>
        <td class="l-t-td-left"> 劳务费:</td>
        <td class="l-t-td-right"> 
         <input type="text" ltype='text' name="totalCost7" id="totalCost7" />
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 专家咨询费:</td>
        <td class="l-t-td-right"> 
         <input type="text" ltype='text' name="totalCost8" id="totalCost8" />
        </td>
        <td class="l-t-td-left"> 其他费用:</td>
        <td class="l-t-td-right"> 
         <input type="text" ltype='text' name="totalCost9" id="totalCost9" />
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 新产品、新材料、<br/>新工艺、新装置:</td>
        <td class="l-t-td-right"> 
        <input type="text" ltype='text' name="results1" id="results1" ligerui="{suffixWidth:'30',suffix:'项'}"  />
        </td>
         </tr>
         <tr>
        <td class="l-t-td-left"> 发表论文:</td>
        <td class="l-t-td-right"> 
         <input type="text" ltype='text' name="results2" id="results2" ligerui="{suffixWidth:'30',suffix:'篇'}"  />
        </td>
         <td class="l-t-td-left"> 出版科技著作:</td>
        <td class="l-t-td-right"> 
         <input type="text" ltype='text' name="results3" id="results3" ligerui="{suffixWidth:'30',suffix:'部'}"  />
        </td>
       </tr>
       <tr> 
       
        <td class="l-t-td-left"> 申请专利:</td>
        <td class="l-t-td-right"> 
         <input type="text" ltype='text' name="results4" id="results4" ligerui="{suffixWidth:'30',suffix:'项'}"  />
        </td>
          <td class="l-t-td-left"> 获得专利授权:</td>
        <td class="l-t-td-right"> 
         <input type="text" ltype='text' name="results5" id="results5" ligerui="{suffixWidth:'30',suffix:'项'}"  />
        </td>
       </tr>
       <tr> 
      
        <td class="l-t-td-left"> 标准草案:</td>
        <td class="l-t-td-right"> 
         <input type="text" ltype='text' name="results6" id="results6" ligerui="{suffixWidth:'30',suffix:'项'}"  />
        </td>
         <td class="l-t-td-left"> 报批标准:</td>
        <td class="l-t-td-right"> 
         <input type="text" ltype='text' name="results7" id="results7" ligerui="{suffixWidth:'30',suffix:'项'}"  />
        </td>
       </tr>
      </table>
    </form> 




</div>

</body>
</html>
