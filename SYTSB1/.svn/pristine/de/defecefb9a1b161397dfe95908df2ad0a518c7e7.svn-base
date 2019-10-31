<%@page import="com.khnt.rbac.impl.bean.Org"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.utils.DateUtil"%>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>通用查询</title>
		<% String type = request.getParameter("type");%>
		<%@ include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript" src="app/oa/select/org_select.js"></script>
		<script type="text/javascript">
			var qmUserConfig = {
				sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:100},//可以自己定义 layout:column,float,
				sp_fields : [
					{
						name : "car_num",
						compare : "like"
					}, {
						label : "${param.type=='2'?'保养':'维修'}金额大于",
						name : "repair_money",
						compare : ">="
					},{group:[
						       {label:"${param.type=='2'?'保养':'维修'}日期",name:"repair_time",compare:">=",value:""},
						       {label:"到",name:"repair_time",compare:"<=",value:"",labelAlign:"center",labelWidth:20}
						       ],columnWidth:0.33
					}, {
						label : "${param.type=='2'?'保养':'维修'}厂家",
						name : "repair_factory",
						compare : "like"
					}
					<% if ("2".equals(type)) {%>
					, {
						group:[
						       {name:"next_maintenance",compare:">=",value:""},
						       {label:"到",name:"next_maintenance",compare:"<=",value:"",labelAlign:"center",labelWidth:20}
						       ],columnWidth:0.33
					}
					<%}else{%>
					, {
						name : "driver",
						compare : "like"
					}
					<%}%>
					, {name : "repair_room_code",compare : "like",xtype:"combo",onBeforeOpen:showDepartList}
				],
				<tbar:toolBar type="tbar" code="carRepair">
				</tbar:toolBar>,
				////            //提供以下4个事件
				listeners : {
					rowClick : function(rowData, rowIndex) {
					},
					rowDblClick : function(rowData, rowIndex) {
						modify(rowData);
					},
					selectionChange : function(rowData, rowIndex) {
						selectionChange();
					},
					rowAttrRender : function(rowData, rowid) {
						//alert(rowid)
						if(rowData.change_num!="")rowData.car_num=rowData.car_num+"("+rowData.change_num+")";
			<% if ("2".equals(type)) {%>
				
							var stl="";
							var dstr="<%=DateUtil.getDateTime("yyyy-MM-dd", new Date())%>";
			
							var nowDate=new Date(Date.parse(dstr.replace(/-/g,"/")));
							var rowDate=formatDate(rowData.next_maintenance);
				
							var head7=formatDate("<%=DateUtil.getDate(DateUtil.adjustDate(new Date(), 7))%>");//提前一周预警;
							var head30=formatDate("<%=DateUtil.getDate(DateUtil.adjustDate(new Date(), 30))%>");//提前一个月预警
			
							if(rowDate<=nowDate){
								stl = "style='color:red'";
							}
							else if(rowDate<=head7&&rowDate>nowDate){
								stl = "style='color:#8B008B'";
							}else if(rowDate<=head30&&rowDate>head7){
								stl = "style='color:#006400'";
							}else{
								stl = "style='color:blue'";
							}
							return stl;
			<%}%>
				
						}
					}
				};
	
				function formatDate(strDate){
					if(strDate==null||strDate=="")return ;
					var strDateArr=strDate.split(" ");
					var dates=strDateArr[0].split("-");

					var newDates=dates[1]+"/"+dates[2]+"/"+dates[0];
		  
					var sysdates=new Date(newDates);
		  
					return sysdates;
				}
				function addDates (sysDates,interval, value) {
		
					var d = sysDates.clone();
					if (!interval || value === 0) return d;

					switch(interval.toLowerCase()) {
						case Date.MILLI:
							d.setMilliseconds(sysDates.getMilliseconds() + value);
							break;
						case Date.SECOND:
							d.setSeconds(sysDates.getSeconds() + value);
							break;
						case Date.MINUTE:
							d.setMinutes(sysDates.getMinutes() + value);
							break;
						case Date.HOUR:
							d.setHours(sysDates.getHours() + value);
							break;
						case Date.DAY:
							d.setDate(sysDates.getDate() + value);
							break;
						case Date.MONTH:
							var day = sysDates.getDate();
							if (day > 28) {
								day = Math.min(day, sysDates.getFirstDateOfMonth().add('mo', value).getLastDateOfMonth().getDate());
							}
							d.setDate(day);
							d.setMonth(sysDates.getMonth() + value);
							break;
						case Date.YEAR:
							d.setFullYear(sysDates.getFullYear() + value);
							break;
					}
					return d;
				}
				//行选择改变事件
				function selectionChange() {
					var count = Qm.getSelectedCount();//行选择个数
					if (count == 0) {
						Qm.setTbar({
							add : true,
							modify : false,
							del : false,
							detail : false
				
						});
					} else if (count == 1) {
						Qm.setTbar({
							add : true,
							modify : true,
							del : true,
							detail : true
						});
					} else {
						Qm.setTbar({
							add : true,
							modify : false,
							del : true,
							detail : false
						});
			
					}
				}
				
				//显示部门选择列表
				function showDepartList(){
					var unitId = "<sec:authentication property="principal.unit.id" />";
					var unitName = "<sec:authentication property="principal.unit.orgName" htmlEscape="false"/>";
					$(this).data('unitId',unitId);
					$(this).data('unitName',unitName);
					showOrgList.call(this);
				}

				function modify(item) {
					var selectedId = new Array();
					var status = "modify";
					var title = "修改";
					if (item.id == "modify") {//点击修改按钮调用的本方法
						selectedId = Qm.getValuesByName("id");
					} else {//双击数据调用本方法
						selectedId[0] = item.id;
						status = "detail";
						title = "详情";
					}
					if (selectedId.length > 1) {
						$.ligerDialog.alert('不支持批量修改，请只选择一条数据！', "提示");
						return;
					} else if (selectedId.length < 1) {
						$.ligerDialog.alert('请先选择要修改的数据！', "提示");
						return;
					}
					var width = 700;
					var height = 340;
					var windows = top.$.dialog({
						width : width,
						height : height,
						lock : true,
						title : title,
						data : {
							"window" : window
						},
						content : 'url:app/oa/car/repair_detail.jsp?type=<%=type%>&status=' + status
							+ '&id=' + selectedId
					});
				}
				function add(item) {
					var width = 700;
					var height = 340;
					var windows = top.$.dialog({
						width : width,
						height : height,
						lock : true,
						title : "新增",
						data : {
							"window" : window
						},
						content : 'url:app/oa/car/repair_detail.jsp?type=<%=type%>&status=add'
			
					});
				}

				function del() {
					var selectedId = Qm.getValuesByName("id");
					if (selectedId.length < 1) {
						$.ligerDialog.alert('请先选择要删除的事项！', "提示");
						return;
					}
					$.ligerDialog.confirm(kui.DEL_MSG, function (yes) {
						if(yes){
							$.ajax({
								url:"oa/car/repair/delete.do?ids="+selectedId,
								type:"post",
								async:false,
								success:function(data){
									if(data.success){
										top.$.notice("删除成功！");
										Qm.refreshGrid();
									}
								}
							});
						}
					});
				}

				function detail() {
					var selectedId = Qm.getValuesByName("id");
					var width = 700;
					var height = 340;
					var windows = top.$.dialog({
						width : width,
						height : height,
						lock : true,
						title : "详情",
						data : {
							"window" : window
						},
						content : 'url:app/oa/car/repair_detail.jsp?type=<%=type%>&status=detail'
							+ '&id=' + selectedId,
						cancel:true
					});
				}
	

		</script>
	</head>
	<%
		String unitId = SecurityUtil.getSecurityUser().getUnit().getId();
		Org org = (Org)SecurityUtil.getSecurityUser().getDepartment();
	%>
	<body>
	     
   
	
		<% if ("1".equals(type)) {%>
		<qm:qm pageid="oa_c_r_m" script="false" singleSelect="false">
			<qm:param name="type" value="1" compare="=" />
			<qm:param name="org_code" value="<%=unitId %>" compare="=" />
			<sec:authorize access="hasRole('oa_car_info')">
				<qm:param name="level_code" value="" compare="like" />
			</sec:authorize>
			<sec:authorize ifNotGranted="oa_car_info">
				<qm:param name="level_code" value="<%=org.getLevelCode() %>" compare="like" />
			</sec:authorize>
			</qm:qm>

		<%} else {%>
		<!-- 提示文字 -->
		 <div class="item-tm" id="titleElement">
		    <div class="l-page-title">
				<div class="l-page-title-note">提示：列表数据项
				    <font color="blue">“蓝色”</font>代表处于保养期内的车辆。
					<font color="red">“红色”</font>代表已过保养期的车辆。
					<font color="#8B008B">“紫色”</font>代表7天内将过保养期的车辆。
					<font color="#006400">“绿色”</font>代表7天后30天之内将过保养期的车辆。
				</div>
			</div>
		</div>
	    <!-- 提示文字 -->
		<qm:qm pageid="oa_car_m" script="false" singleSelect="false">
			<qm:param name="type" value="2" compare="=" />
			<qm:param name="org_code" value="<%=unitId %>" compare="=" />
			<sec:authorize access="hasRole('oa_car_info')">
				<qm:param name="level_code" value="" compare="like" />
			</sec:authorize>
			<sec:authorize ifNotGranted="oa_car_info">
				<qm:param name="level_code" value="<%=org.getLevelCode() %>" compare="like"/>
			</sec:authorize>
		</qm:qm>
		<%}%>

	</body>
</html>