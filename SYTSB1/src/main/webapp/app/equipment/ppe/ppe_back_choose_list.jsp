<%@page import="com.lsts.report.bean.SysOrg"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>已借出固定资产列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript" src="app/common/js/render.js"></script>
		<script type="text/javascript">
		<%String loan_fk = request.getParameter("loan_fk");%>
		var arrayObj;
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
            sp_fields:[
				{name:"self_no", compare:"like"},
            	{name:"asset_name", compare:"like"},
            	{name:"spaci_model", compare:"like"},
            	{name:"sn", compare:"like"},
            	{name:"department_name", compare:"like"},
            	{name:"loaner_name", compare:"like"},
				{group:[
						{name:"loan_date", compare:">=", value:""},
						{label:"到", name:"loan_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
					]},
				{group:[
						{name:"back_date", compare:">=", value:""},
						{label:"到", name:"back_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
					]},
				{name:"status", compare:"="}
            ],
            tbar:[
            	{ text:'详情', id:'detail',icon:'detail', click: detail}
            ],
            listeners: {
                selectionChange : function(rowData,rowIndex){
                	selectionChange();
                },
        		rowDblClick :function(rowData,rowIndex){
        			detail(rowData.ppe_fk);
        		},
        		rowAttrRender : function(rowData, rowid) {

    	        }
            }
        };
        
        // 行选择改变事件
		function selectionChange() {
	   		var count = Qm.getSelectedCount(); //行选择个数
			Qm.setTbar({
				detail: count==1
			});
		}
        
		function detail(){
			var ppe_fk = Qm.getValueByName("ppe_fk").toString();
			top.$.dialog({
				width:700,
				height:470,
				lock:true,
				title:"详情",
				content: 'url:app/equipment/ppe/ppe_basic_detail.jsp?pageStatus=detail&id='+ppe_fk+'&fromList=back',
				data:{window:window}
			});
		}
        // 刷新Grid
        function refreshGrid() {
            Qm.refreshGrid();
        }
	    function f_select(){ 
	   		var rows = Qm.getQmgrid().getSelectedRows();
         	return rows; 
        }
    </script>
	</head>
	<body>
	<%StringBuffer sql = new StringBuffer();
		String sql1="";
		if(request.getParameter("backtype").equals("giveBack")){
			sql1="select t.*,t1.department_name,t1.loaner_name,t1.loan_date  from TJY2_PPE_LOAN_SUB t,TJY2_PPE_LOAN t1 "+
					"where t.loan_fk=t1.id and t.status='1' and t.loan_fk ='"+ loan_fk +"'order by t.self_no";
			System.out.println(sql1.toString());
		}else if(request.getParameter("backtype").equals("backDetail")){
			sql1="select t.*,t1.department_name,t1.loaner_name,t1.loan_date  from TJY2_PPE_LOAN_SUB t,TJY2_PPE_LOAN t1 "+
					"where t.loan_fk=t1.id order by t1.loan_date desc nulls last,t.self_no";
		}
	%>
		<qm:qm  pageid="TJY2_PPE_BACK_CHOOSE" singleSelect="false" sql="<%=sql1.toString() %>">
		</qm:qm>
	</body>
</html>
