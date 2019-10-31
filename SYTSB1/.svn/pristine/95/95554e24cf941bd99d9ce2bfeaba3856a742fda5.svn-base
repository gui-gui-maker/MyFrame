<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.khnt.security.CurrentSessionUser"%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    CurrentSessionUser user=(CurrentSessionUser)request.getSession().getAttribute("currentSessionUser");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
    var emails = [
                  { name: "Peter Pan", to: "peter@pan.de" },
                  { name: "Molly", to: "molly@yahoo.com" },
                  { name: "Forneria Marconi", to: "live@japan.jp" },
                  { name: "Master <em>Sync</em>", to: "205bw@samsung.com" },
                  { name: "Dr. <strong>Tech</strong> de Log", to: "g15@logitech.com" },
                  { name: "Don Corleone", to: "don@vegas.com" },
                  { name: "Mc Chick", to: "info@donalds.org" },
                  { name: "Donnie Darko", to: "dd@timeshift.info" },
                  { name: "Quake The Net", to: "webmaster@quakenet.org" },
                  { name: "Dr. Write", to: "write@writable.com" },
                  { name: "GG Bond", to: "Bond@qq.com" },
                  { name: "Zhuzhu Xia", to: "zhuzhu@qq.com" }
              ];
    
        $(function () {
//          如果不设置额外参数，不用调用此方法，初始化时会自动调用
            $("#form").initForm({
                success : function(responseText) {//处理成功
                    if (responseText.success) {
                        top.$.dialog.notice({content:'保存成功'});
                        api.data.window.Qm.refreshGrid();
                        $("#id").val(responseText.data.id);
                    } else {
                        $.ligerDialog.error('保存失败' + responseText);
                    }
                },
                getSuccess:function(responseText){
                }
            });
            
        	$("body").append('<object style="height:1px;" id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0><embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed></object>');
			 $('#peopleConcerned').autocomplete("employee/basic/searchEmployee.do", {
                   max: 12,    //列表里的条目数
                   minChars: 1,    //自动完成激活之前填入的最小字符
                   width: 200,     //提示的宽度，溢出隐藏
                   scrollHeight: 300,   //提示的高度，溢出显示滚动条
                   scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
                   matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
                   autoFill: false,    //自动填充
                   formatItem: function(row, i, max) {
                       return row.name + '   ' + row.mobileTel;
                   },
                   formatMatch: function(row, i, max) {
                       return row.name + row.mobileTel;
                   },
                   formatResult: function(row) {
                       return row.name;
                   }
               }).result(function(event, row, formatted) {
                   alert(row.mobileTel);
               });
               
                $('#department').autocomplete("employee/basic/searchOrg.do", {
                   max: 12,    //列表里的条目数
                   minChars: 1,    //自动完成激活之前填入的最小字符
                   width: 200,     //提示的宽度，溢出隐藏
                   scrollHeight: 300,   //提示的高度，溢出显示滚动条
                   scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
                   matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
                   autoFill: false,    //自动填充
                   formatItem: function(row, i, max) {
                   //alert(row);
                       return row.orgName;
                   },
                   formatMatch: function(row, i, max) {
                       return row.orgName;
                   },
                   formatResult: function(row) {
                       return row.orgName;
                   }
               }).result(function(event, row, formatted) {
                   alert(row.orgId);
               });

        });

    </script>
</head>
<body>
<div class="navtab">
<div title="下发任务" lselected="true">
    <form id="form" action="office/ywhbsgzAction/save.do" getAction="office/ywhbsgzAction/detail.do?id=${param.id}">
        <input type="hidden" name="id" id="id">
         <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
		  		          <tr> 
       
        <td class="l-t-td-left"> 任务开始时间</td>
        <td class="l-t-td-right"> 
        <input  ligerui="{initValue:'',format:'yyyy-MM-dd'}" name="startTime" type="text" ltype='date' ligerui="{format:''}" validate="{required:true}}"/>
         <td class="l-t-td-left"> 任务结束时间</td>
        <td class="l-t-td-right"> 
        <input ligerui="{initValue:'',format:'yyyy-MM-dd'}" name="endTim" type="text" ltype='date' ligerui="{format:''}" validate="{required:true}}"/>
        </td>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 部门</td>
        <td class="l-t-td-right"> 
        <input name="department" id="department" type="text" ltype='text' validate="{required:true}"/>
        </td>
         <td class="l-t-td-left"> 责任人</td>
        <td class="l-t-td-right"> 
        <input  name="responsiblePerson" type="text" ltype='text' validate="{required:true}"/>
        </td>
       </tr>
       <tr > 
       <td class="l-t-td-left"> 任务内容</td>
        <td colspan="3" class="l-t-td-right"> 
        <textarea Style="height: 70px" name="missionContent" type="text" ltype='text' validate="{required:true}"></textarea>
        </td>
        </tr>
       <tr>
       <td class="l-t-td-left"> 完成情况</td>
        <td colspan="3" class="l-t-td-right"> 
        <textarea Style="height: 70px"  name="performance" type="text" ltype='text' validate="{required:true}"></textarea>
        </td>
       </tr>
        <tr>
       <td  class="l-t-td-left"> 未完成原因</td>
        <td colspan="3" class="l-t-td-right"> 
        <textarea Style="height: 70px" name="unfinishedReason" type="text" ltype='text' ></textarea>
        </td>
       </tr>
      </table>

    </form>

</div>
<div  title="反馈">
   <form id="form1" action="office/ywhbsgz_fkAction/save.do" method="get" getAction="office/ywhbsgz_fkAction/fdetail1.do?id=${param.id}" >
        <input type="hidden" name="id">
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
		
       <tr> 
        <td class="l-t-td-left"> 完成进度</td>
        <td colspan="3" class="l-t-td-right"> 
           <u:combo name="status1" ltype="radioGroup" code="TJY2_BG_RWFK"/>
        </td>
        <td class="l-t-td-left"> </td>
        <td class="l-t-td-right"> 
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left">已完成情况</td>
        <td colspan="3" class="l-t-td-right"> 
        <textarea name="feedbackRemark"  type="text" ltype='text' validate="{required:true,maxlength:2000}"></textarea>
        </td>
       </tr>
        <tr> 
        <td class="l-t-td-left"> 未完成原因</td>
        <td colspan="3" class="l-t-td-right"> 
        <textarea name="notCompleteReason" type="text" ltype='text' validate="{required:true,maxlength:2000}"></textarea>
        </td>
       </tr>
      </table>
    </form>
    <script type="text/javascript">
	        $("#form1").initFormList({
				// opType:"auto",//数据保存模式，发现actionParam的关联信息为空时会自动调用opTypeFun，默认为auto
				// opTypeFun:save,//自动保存调用方法，opType为atuo时才会用到,默认为save
				// action:"",//保存数据或其它操作的action
	            actionParam:{"ywhbsgz.id":$("#form>[name=id]")}, //保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把from1下的name为id的值带上去
				 // delActionParam:{"id":$},//默认为选择行的id
				 // getAction:"",//取数据的action
				 // getActionParam:{},//取数据时附带的参数，一般只在需要动态取特定值时用到
	            onSelectRow:function (rowdata, rowindex) {
	                $("#form1").setValues(rowdata);
	            },
				// toolbar:[
				//  { text:'保存', click:function(){$("#formJc").submit();}, icon:'save'},
				//  { text:'删除', click:function(){$("#formJc").submit();}, icon:'save'}
				//  ],
	            columns:[
	                //此部分配置同grid
	                { display:'主键', name:'id', hide:true},
	                { display:'完成进度', name:'status1', width:'20%'},
	                { display:'已完成情况', name:'feedbackRemark', width:'37%'},
	                { display:'未完成原因', name:'notCompleteReason', width:'37%'}
	            ]
	        });
    	</script>
    	</div>
    	</div>
    	

</body>
</html>
