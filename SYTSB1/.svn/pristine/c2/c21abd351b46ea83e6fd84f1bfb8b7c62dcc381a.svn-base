<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
    <script test="text/javascript">
    var empId ="${param.empId}";
    $(function () {
        $("#btn1").css({"height":"20px","line-height":"18px"})
        $("#btn1").ligerButton({
            icon:"count",
            click: function (){
                init();
            },text:"统计"
        });
        $("#form1").ligerForm();   
        init();       
    });
    
    function init(){
    	var column;
    	column=[
				{display:'主键', name:'id', hide:true},
				{display:'员工ID', name:'empId', hide:true},
                { display: '职务', name: 'worktitleName',align: 'center', width: '30%'},
                { display: '开始时间', name: 'startDate',align: 'center', width: '25%',type: 'date', format: 'yyyy-MM-dd'},
                { display: '结束时间', name: 'endDate',align: 'center', width: '25%',type: 'date', format: 'yyyy-MM-dd'},
    	 		{ display: '备注', name: 'status',align: 'center', width: '20%',type: 'text',
    	 			render:function(rowData){
                    	if(rowData.status=="0"){
                        	return "以往职务";
                    	}else if(rowData.status=="1"){
                        	return "当前职务";
                    	}
                	}}];
        $.post("employeeBaseAction/getWorkTitles.do",{"empId":empId}, function(resp){
            inputGrid = $("#countGrid").ligerGrid({
                columns: column, 
                data:{Rows:eval(JSON.stringify(resp.worktitleRecords))},//json格式的字符串转为对象
                height:'100%',
                usePager:false,
                rownumbers : true,
                width:'100%',
                onSelectRow: function (rowdata, rowindex){
                },
                onDblClickRow: function (rowdata, rowindex, rowDomElement) {
                	top.$.dialog({
       		         width: 500,
       		         height: 280,
       		         lock: true,
       		         parent: api,
       		         data: {
       		       	 window: window
       		         },
       		         title: "详情",
       		         content: 'url:app/humanresources/employee_worktitle_setting.jsp?pageStatus=detail&empId='+rowdata.empId+'&recordId='+rowdata.id
       	          });
                	}
             },"json");
        });
    }
    </script>
    <style>
    div{margin: 0.5px;}
    .l-button {padding:0 8px 2px 9px;}
    </style>
</head>
<body>

<div position="center">
    <div id="countGrid"></div>   
</div>
</body>
</html>