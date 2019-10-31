<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.rbac.bean.Org"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<%
	String type=request.getParameter("type");
%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
        sp_fields:[
        	{name:"identifier",compare:"like"},
        	{name:"com_name",compare:"like"},
        	{name:"type",compare:"like"},
			{name:"inspector",compare:"like"},
        	{name:"check_status",compare:"like"},
        	{name:"print_status",compare:"like"}
        ],
		tbar : [ {
			text : '详情',id : 'detail',click : detail,icon : 'detail'}
			<%
			if(!"seal".equals(type)){
			%>
			,'-', 
			{text : '新增',id : 'add',click : add,icon : 'add'}, 
			'-', 
			{text : '修改',id : 'modify',click : modify,icon : 'modify'}, 
			'-', 
			{text : '删除',id : 'del',click : del,icon : 'del'}, 
			'-', 
			{text : '提交',id : 'submit',click : submit,icon : 'submit'}
			<sec:authorize access="hasRole('jdjy_file_check')">
				,'-', 
				{text : '审核',id : 'check',click : check,icon : 'check'}
	    	</sec:authorize>
			,'-', 
			{text : '打印',id : 'print',click : print,icon : 'print'} 
				<%
				}
			%>
			<%
			if("seal".equals(type)){
			%>
			<sec:authorize access="hasRole('jdjy_file_check')">
				,'-', 
				{text : '盖章',id : 'seal',click : seal,icon : 'seal'}
			</sec:authorize>
			<%
				}
			%>
			],
		listeners : {
			rowClick : function(rowData, rowIndex) {
			},
			rowDblClick : function(rowData, rowIndex) {
				detail();
			},
			selectionChange : function(rowData, rowIndex) {
				selectionChange()
			},
			rowAttrRender : function(rowData, rowid) {
				var fontColor="black";
				if(rowData.check_status=='待审核') {
                    fontColor="blue";
                }else if (rowData.check_status=='审核通过'){
                	fontColor="green";
                	if(rowData.seal_status=='Y'){
                		fontColor="orange";
                	}
                	if(rowData.print_status=='已打印'){
                		fontColor="#8B008B";
                	}
                }else if(rowData.check_status=='审核不通过') {
                    fontColor="red";
                }
                return "style='color:"+fontColor+"'"; 
			}
		}
	};

	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		var check_status = Qm.getValueByName("check_status");
		var check_statuss = Qm.getValuesByName("check_status");
		var print_status = Qm.getValueByName("print_status");
		var print_statuss = Qm.getValuesByName("print_status");
		var seal_status = Qm.getValueByName("seal_status");
		var toolbar={};
		var allowdel = true;
		var allowsubmit = true;
		var allowprint = true;
		for(var i=0;i<check_statuss.length;i++){
	    	if(check_statuss[i]!="未提交"){
	    		allowdel = false;
	    		allowsubmit = false;
	    	}
        }
        for(var i=0;i<check_statuss.length;i++){
	    	if(check_statuss[i]!="审核通过" || print_statuss[i]!="未打印"){
	    		allowprint = false;
	    	}
        }
		if(count==0){
			toolbar={detail:false, modify:false, del:false,submit:false,check:false,print:false,seal:false};
		}else if(count==1){
			if("未提交"==check_status){
				toolbar={detail:true, modify:true, del:true,submit:true,check:false,print:false,seal:false};
			}else if("待审核"==check_status){
				toolbar={detail:true, modify:false, del:false,submit:false,check:true,print:false,seal:false};
			}else if("审核不通过"==check_status){
				toolbar={detail:true, modify:false, del:false,submit:false,check:false,print:false,seal:false};
			}else if("审核通过"==check_status){
				toolbar={detail:true, modify:false, del:false,submit:false,check:false,print:true,seal:false};
				if("Y"==seal_status){
					toolbar={detail:true, modify:false, del:false,submit:false,check:false,print:true,seal:false};
				}else if("N"==seal_status){
					toolbar={detail:true, modify:false, del:false,submit:false,check:false,print:false,seal:true};
				}
			}else{
				toolbar={detail:false, modify:false, del:false,submit:false,check:false,print:false,seal:false};
			}
		}else{
			toolbar={
					detail:false, 
					modify:false, 
					del:true&&allowdel,
					submit:true&&allowsubmit,
					check:false,
					print:false,
					seal:false
			};
		}
		Qm.setTbar(toolbar);
		}

	//新增
	function add() {
		top.$.dialog({
			width : 800,
			height : 580,
			lock : true,
			title : "新增",
			data : {"window" : window},
			content : 'url:app/qualitymanage/inspection_note_detail.jsp?pageStatus=add&type='+"<%=type%>"
		});
	}

	//修改
	function modify() {
		var id = Qm.getValueByName("id").toString();
		top.$.dialog({
			width : 800,
			height : 580,
			lock : true,
			title : "修改",
			data : {
				"window" : window
			},
			content : 'url:app/qualitymanage/inspection_note_detail.jsp?pageStatus=modify&id='+id+'&type='+"<%=type%>"
		});
	}

	//详情
	function detail() {
		top.$.dialog({
			width : 800,
			height : 580,
			lock : true,
			title : "详情",
			data : {"window" : window},//把当前页面窗口传入下一个窗口，以便调用。
			content : 'url:app/qualitymanage/inspection_note_detail.jsp?pageStatus=detail&id='+ Qm.getValueByName("id")+'&type='+"<%=type%>"
		});
	}
	//删除
	function del() {
		if("liaison"=="<%=type%>"){
			$.del("确定要删除？删除后无法恢复！","QualityLiaisonAction/delete.do",{"ids":Qm.getValuesByName("id").toString()});
		}else if("note"=="<%=type%>"){
			$.del("确定要删除？删除后无法恢复！","QualityNoteAction/delete.do",{"ids":Qm.getValuesByName("id").toString()});
		}
	}
	//提交
    function submit(){
		var url;
		var ids = Qm.getValuesByName("id");
        if(!ids){
       	 	top.$.notice('请先选择要提交审核的数据！',3);
            return;
        }
        if("liaison"=="<%=type%>"){
			url="QualityLiaisonAction/submit.do?ids="+ids;
		}else if("note"=="<%=type%>"){
			url="QualityNoteAction/submit.do?ids="+ids;
		}
        $.ligerDialog.confirm('是否提交审核？', function (yes){
        if(!yes){return false; }
         top.$.ajax({
             url: url,
             type: "GET",
             dataType:'json',
             async: false,
             success:function (data) {
                 if (data.success) {
                	$("body").unmask();
               	 	top.$.notice('提交成功！！！');
                    Qm.refreshGrid();
                 }
             },
             error:function () {
            	 $("body").unmask();
            	 $.ligerDialog.error('请出错了！请重试！!');
             }
         });
        });
	}
	
	// 审核
	function check() {
		if("liaison"=="<%=type%>"){
			top.$.dialog({
				width : $(top).width(),
				height :  $(top).height()-40,
				lock : true,
				title : "打印通知书",
				parent: api,
				content : 'url:app/qualitymanage/docEditor.jsp?pageStatus=detail&op_type=check&type='+"<%=type%>",	
				data : {
					"window" : window,
					"id":Qm.getValuesByName("id").toString(),
					"identifier":Qm.getValuesByName("identifier").toString(),
					"com_name":Qm.getValueByName("com_name").toString(),
					"item1":Qm.getValuesByName("item1").toString(),
					"item2":Qm.getValueByName("item2").toString(),
					"item3":Qm.getValuesByName("item3").toString(),
					"content":Qm.getValueByName("content").toString(),
					"inspector":Qm.getValueByName("inspector").toString(),
					"inspector_date":Qm.getValuesByName("inspector_date").toString(),
					"type":Qm.getValueByName("type").toString(),
				}
			}).max();
		}else if("note"=="<%=type%>"){
			top.$.dialog({
				width : $(top).width(),
				height :  $(top).height()-40,
				lock : true,
				title : "打印通知书",
				parent: api,
				content : 'url:app/qualitymanage/docEditor.jsp?pageStatus=detail&op_type=check&type='+"<%=type%>",	
				data : {
					"window" : window,
					"id":Qm.getValuesByName("id").toString(),
					"identifier":Qm.getValuesByName("identifier").toString(),
					"com_name":Qm.getValueByName("com_name").toString(),
					"item1":Qm.getValuesByName("item1").toString(),
					"item2":Qm.getValueByName("item2").toString(),
					"item3":Qm.getValuesByName("item3").toString(),
					"content":Qm.getValueByName("content").toString(),
					"inspector":Qm.getValueByName("inspector").toString(),
					"inspector_date":Qm.getValuesByName("inspector_date").toString(),
					"inspector_people":Qm.getValuesByName("inspector_people").toString(),
					"inspector_people_date":Qm.getValueByName("inspector_people_date").toString(),
					"type":Qm.getValueByName("type").toString(),
				}
			}).max();
		}
	}
	//盖章
	function seal(){
		top.$.dialog({
			width : $(top).width(),
			height :  $(top).height()-40,
			lock : true,
			title : "加盖印章",
			parent: api,
			content : 'url:app/qualitymanage/docEditor.jsp?pageStatus=detail&op_type=seal&type='+"<%=type%>",	
			data : {
				"window" : window,
				"id":Qm.getValuesByName("id").toString(),
				"identifier":Qm.getValuesByName("identifier").toString(),
				"com_name":Qm.getValueByName("com_name").toString(),
				"item1":Qm.getValuesByName("item1").toString(),
				"item2":Qm.getValueByName("item2").toString(),
				"item3":Qm.getValuesByName("item3").toString(),
				"content":Qm.getValueByName("content").toString(),
				"inspector":Qm.getValueByName("inspector").toString(),
				"inspector_date":Qm.getValuesByName("inspector_date").toString(),
				"inspector_people":Qm.getValuesByName("inspector_people").toString(),
				"inspector_people_date":Qm.getValueByName("inspector_people_date").toString(),
				"type":Qm.getValueByName("type").toString(),
			}
		}).max();
	}
	// 打印
	function print() {
		var data;
		if("liaison"=="<%=type%>"){
			data={
				"window" : window,
				"id":Qm.getValuesByName("id").toString(),
				"identifier":Qm.getValuesByName("identifier").toString(),
				"com_name":Qm.getValueByName("com_name").toString(),
				"item1":Qm.getValuesByName("item1").toString(),
				"item2":Qm.getValueByName("item2").toString(),
				"item3":Qm.getValuesByName("item3").toString(),
				"content":Qm.getValueByName("content").toString(),
				"inspector":Qm.getValueByName("inspector").toString(),
				"inspector_date":Qm.getValuesByName("inspector_date").toString(),
				"type":Qm.getValueByName("type").toString(),
			}
		}else if("note"=="<%=type%>"){
			data={
				"window" : window,
				"id":Qm.getValuesByName("id").toString(),
				"identifier":Qm.getValuesByName("identifier").toString(),
				"com_name":Qm.getValueByName("com_name").toString(),
				"item1":Qm.getValuesByName("item1").toString(),
				"item2":Qm.getValueByName("item2").toString(),
				"item3":Qm.getValuesByName("item3").toString(),
				"content":Qm.getValueByName("content").toString(),
				"inspector":Qm.getValueByName("inspector").toString(),
				"inspector_date":Qm.getValuesByName("inspector_date").toString(),
				"inspector_people":Qm.getValuesByName("inspector_people").toString(),
				"inspector_people_date":Qm.getValueByName("inspector_people_date").toString(),
				"type":Qm.getValueByName("type").toString(),
			}
		}
		top.$.dialog({
			width : $(top).width(),
			height :  $(top).height()-40,
			lock : true,
			title : "打印通知书",
			parent: api,
			content : 'url:app/qualitymanage/docEditor.jsp?pageStatus=detail&op_type=print&type='+'<%=type%>',	
			data : data
		}).max();
	}
	// 打印
	function preview() {
		var data;
		if("liaison"=="<%=type%>"){
			data={
				"window" : window,
				"id":Qm.getValuesByName("id").toString(),
				"identifier":Qm.getValuesByName("identifier").toString(),
				"com_name":Qm.getValueByName("com_name").toString(),
				"item1":Qm.getValuesByName("item1").toString(),
				"item2":Qm.getValueByName("item2").toString(),
				"item3":Qm.getValuesByName("item3").toString(),
				"content":Qm.getValueByName("content").toString(),
				"inspector":Qm.getValueByName("inspector").toString(),
				"inspector_date":Qm.getValuesByName("inspector_date").toString(),
				"type":Qm.getValueByName("type").toString(),
			}
		}else if("note"=="<%=type%>"){
			data={
				"window" : window,
				"id":Qm.getValuesByName("id").toString(),
				"identifier":Qm.getValuesByName("identifier").toString(),
				"com_name":Qm.getValueByName("com_name").toString(),
				"item1":Qm.getValuesByName("item1").toString(),
				"item2":Qm.getValueByName("item2").toString(),
				"item3":Qm.getValuesByName("item3").toString(),
				"content":Qm.getValueByName("content").toString(),
				"inspector":Qm.getValueByName("inspector").toString(),
				"inspector_date":Qm.getValuesByName("inspector_date").toString(),
				"inspector_people":Qm.getValuesByName("inspector_people").toString(),
				"inspector_people_date":Qm.getValueByName("inspector_people_date").toString(),
				"type":Qm.getValueByName("type").toString(),
			}
		}
		top.$.dialog({
			width : $(top).width(),
			height :  $(top).height()-40,
			lock : true,
			title : "打印通知书",
			parent: api,
			content : 'url:app/qualitymanage/docEditor.jsp?pageStatus=detail&op_type=print&type='+'<%=type%>',	
			data : data
		}).max();
	}

	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String userId = user.getId();
%>
<body>
<div class="item-tm" id="titleElement">
		 <div class="l-page-title">
			<div class="l-page-title-note">提示：列表数据项
				<font color="black">“黑色”</font>代表未提交，
                <font color="blue">“蓝色”</font>代表待审核，
                <font color="green">“绿色”</font>代表审核通过，
                <font color="red">“红色”</font>代表审核未通过,
                <%
					if("note".equals(type)){
				%>
					<font color="orange">“橙色”</font>代表已盖章,
				<%} %>
                <font color="#8B008B">“紫色”</font>代表已打印。
			</div>
		</div> 
	</div> 
	<%
		if("liaison".equals(type)){
	%>
	<qm:qm pageid="TJY2_QUALITY_LIAISON" script="false">
		<qm:param name="create_id" value="<%=userId%>" compare="=" />
		<sec:authorize access="hasRole('jdjy_file_check')">
			<qm:param name="check_status" value="DSH" compare="=" logic="or"/>
			<qm:param name="check_status" value="PASS" compare="=" logic="or"/>
			<qm:param name="check_status" value="NO_PASS" compare="=" logic="or"/>
		</sec:authorize>
	</qm:qm>
	<%
		}else if("note".equals(type)){
	%>
	<qm:qm pageid="TJY2_QUALITY_NOTE" script="false">
		<qm:param name="create_id" value="<%=userId%>" compare="=" />
		<sec:authorize access="hasRole('jdjy_file_check')">
			<qm:param name="check_status" value="DSH" compare="=" logic="or"/>
			<qm:param name="check_status" value="PASS" compare="=" logic="or"/>
			<qm:param name="check_status" value="NO_PASS" compare="=" logic="or"/>
		</sec:authorize>
	</qm:qm>
	<%	
		}else if("seal".equals(type)){
	%>
	<qm:qm pageid="TJY2_QUALITY_NOTE" script="false">
		<qm:param name="check_status" value="PASS" compare="=" />
		<qm:param name="seal_status" value="N" compare="=" logic="and"/>
	</qm:qm>
	<%	
		}
	%>
</body>
</html>
