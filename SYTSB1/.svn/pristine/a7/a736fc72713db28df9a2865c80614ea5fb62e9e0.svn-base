<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title></title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/tzsb/inspection/flow/directive/check/doc_order_fee2.js"></script>

<%

	CurrentSessionUser user = SecurityUtil.getSecurityUser();

	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String now = df.format(new Date());

%>

<script type="text/javascript">
var count = 0;
	$(function() {
		$("#form1").initForm({ //参数设置示例
			success : function(res) {
				if(res.success){
					var id = res.data.id;
					api.data.window.Qm.refreshGrid();
					api.close();
					
					api.data.window.randomExport(id);
				}
			},
			getSuccess : function(res) {

			}
		});

	})

	function close(url) {
		api.close();
	}


	function changeExpertType(val,text){
		$("#expert_type_name").val(text);
		count = val.split(",").length;
	}
	
	//判断机选专家数量
	function changeNum(num){
		if(count==0){
			$.ligerDialog.error("请选择专家类型！");
			$("#expert_num").val("");
			return;
		} else if((num-0)<count){
			$.ligerDialog.error("你填写的专家数量少于选择的专家类型数量，请重新填写！");
			$("#expert_num").val("");
			return;
		}
	}
	
	function changeNocome(val,text){
		$("#nocome_op_name").val(text);
	}
</script>
</head>
<body>
	<form id="form1" action="expertRecordAction/save.do" getAction="expertRecordAction/detail.do?id=${param.id}">
			<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table">
				<input type="hidden" name="status" value="0"/>
				<input type="hidden" name="create_date" value="<%=now %>"/>
				<input type="hidden" name="candidate_num" id="candidate_num"/>
				<tr> 
					<td class="l-t-td-left">项目名称:</td>
					<td class="l-t-td-right" colspan="3">
					<input validate="required:true" ltype="text"
						type="text" name="item_name" id="item_name"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">时间:</td>
					<td class="l-t-td-right">
					<input validate="required:true" ltype="date"
						type="text" name="meeting_date" id="meeting_date" ligerui="{format:'yyyy-MM-dd'}"/>
					</td>
					<td class="l-t-td-left">机选操作:</td>
					<td class="l-t-td-right">
					<input type="hidden" name="jx_option_id" id="jx_option_id" value="<%=user.getId() %>"  />
					<input type="text" name="jx_option" id="jx_option" value="<%=user.getName() %>"
						ltype="text" validate="{required:true}"  />
					</td>
					
				</tr>
				<tr>
					<td class="l-t-td-left">地点:</td>
					<td class="l-t-td-right" colspan="3">
					<input type="text" name="meeting_addr" id="meeting_addr" 
						ltype="text" validate="{required:true}" 
						/>
					</td>
					
					
				</tr>
				<tr>
					<td class="l-t-td-left">未参加专家:</td>
					<td class="l-t-td-right" colspan="3">
					<input type="hidden" name="nocome_op_name" id="nocome_op_name"   />
					<input type="text" name="nocome_op" id="nocome_op" 
						ltype="select"
						ligerui="{initValue:'',
						readonly:true,
						onSelected:changeNocome,
						tree:{checkbox:true,data:<u:dict sql="select a.id, a.pid, a.code, a.text
												  from (
												select t.id,t.id code, t.emp_name text, t.person_type pid
												  from TJY2_RL_EMPLOYEE_BASE t
												union all
												select v.value id,v.value code, v.name text, '0' pid
												  from PUB_CODE_TABLE t, Pub_Code_Table_Values v
												 where t.id = v.code_table_id
												   and t.code = 'PERSON_TYPE')a
												   start with a.pid ='0' 
												   connect by a.pid = prior a.id" />}}" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">专家类型:</td>
					<td class="l-t-td-right">
					<input type="hidden" name="expert_type_name" id="expert_type_name"/>
					<input validate="required:true"
						type="text" name="expert_type" id="expert_type"
						ltype="select"
						ligerui="{initValue:'',
						onSelected:changeExpertType,
						isMultiSelect:true,
						readonly:true,data: <u:dict code='PERSON_TYPE' />}" />
					</td>
					<td class="l-t-td-left">机选专家数量:</td>
					<td class="l-t-td-right">
					<input type="text" name="expert_num" id="expert_num" onchange="changeNum(this.value)"
						ltype="text" validate="{max:30,number:true,required:true}" 
						/>
					</td>
				</tr>
				
			</table>
	</form>
</body>
</html>
