
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>

<head >
<%
	String work_department = request.getParameter("work_department");
	String status = request.getParameter("status");
	String sql="select t.*, to_char(sysdate, 'yyyy') - substr(emp_id_card, 7, 4) as age from  TJY2_RL_EMPLOYEE_BASE t where EMP_STATUS='4' and WORK_DEPARTMENT='"+work_department+"' ";
	if(status.equals("1")){
		sql+=" and EMP_SEX='1' ";
	}
	if(status.equals("2")){
		sql+=" and EMP_SEX='0' ";
	}
	if(status.equals("3")){
		sql+=" and EMP_MATING='1' ";
	}
	if(status.equals("4")){
		sql+=" and EMP_MATING='0' ";
	}
%>
<%@include file="/k/kui-base-list.jsp"%>


<script type="text/javascript">
var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	 
		sp_fields:[
                   {name: "emp_name", compare: "like"},
				     {name: "emp_sex", compare: "like"},
                   {name: "emp_status", compare: "="}
        ],
        tbar:[
             {text:'详细信息',id:'detail',icon:'detail',click:detail}
        ],
        listeners: {
            selectionChange :function(rowData,rowIndex){
            	var count = Qm.getSelectedCount();//行选择个数
                Qm.setTbar({status:count>0,detail:count>0});
            },
            rowAttrRender : function(rowData, rowid) {
				}
        }
    };
    function detail(){
    top.$.dialog({
        width: 900,
        height: 500,
        lock: true,
        parent: api,
        data: {
      	 window: window
        },
        title: "详情",
        content: 'url:app/humanresources/registration_datail.jsp?pageStatus=detail&id='+Qm.getValueByName("id")
     });
}
   
</script>

	</head>
	
	
	
<body>
	<qm:qm pageid="TJY2_RL_EMPLOYEE" script="false" singleSelect="false" sql="<%=sql %>" >
	</qm:qm>
</body>
</html>
