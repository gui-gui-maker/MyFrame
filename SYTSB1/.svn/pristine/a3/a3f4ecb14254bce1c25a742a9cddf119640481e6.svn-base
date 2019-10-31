<%--
  Created by IntelliJ IDEA.
  User: qin
  Date: 12-8-2
  Time: 下午1:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.status}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        var status='${param.status}';
        var xqid='${param.id}';
        var sign='${param.sign}';
        $(function () {
        	if(status!='detail'){
            	//加载部门信息
                $("#dep_id").ligerComboBox({
               	valueField:'id',
   	            treeLeafOnly: true,
   	            tree: { 
   	            	url: 'rbac/org/orgTree.do?orgid=<sec:authentication property="principal.unit.id" />',
   	            	checkbox:false,
   	            	onClick:function(data){
   	            		getDepName(data)
   	            	}
   	            }
                })
            }
            //加载机构信息
//                    如果不设置额外参数，不用调用此方法，初始化时会自动调用
            $("#formXq").initForm({
                toolbar:[
                    { text:'确定', click:function(){savefb();}, icon:'save'},
                    { text:'关闭', click:function(){api.close();}, icon:'delete'}
                ],
                toolbarPosition:'bottom',
                success : function(responseText) {//处理成功
                    if (responseText.success) {
                        top.$.dialog.notice({content:'保存成功'});
                        api.close();
                        api.data.window.submitAction();
                        $('#xq_id').val(responseText.data.id);
                        //$(window.parent.document).find('#teacher_id').val(responseText.data.id);
                    } else {

                            $.ligerDialog.error('保存失败' + responseText);
                    }
                },
                getSuccess:function(responseText){
                    //如果是公开招聘，自动读取设置的报名结束时间为招聘的有效期
                    if ($('#useM').val()=='01') {
                    	$.getJSON('app/zp/system/detail.do',{id:'sys001'},function(data){
                    		if(data.success){
                    			var date = new Date(Date.parse(data.data.regEndTime.replace(/-/g, "/")))
                    			$('#yxTime').val(date.format("yyyy-MM-dd hh:mm"));
                    		}
                    	});
						
					}


                }
            });

        });
      //格式化日期
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

        //发布信息保存
        function savefb(){
            //alert(1);
            if(sign=='1'){
                //发布
                $('#status').val('1');
                $('#formXq').submit();
            }else{
                //取消发布
                $('#status').val('2');
                $('#formXq').submit();
            }
        }

    </script>
</head>
<body>
<div class="navtab">
<div title="岗位需求信息" lselected="true" style="height: 400px">
    <form id="formXq" action="app/zp/xqxx/savefb.do" getAction="app/zp/xqxx/detail.do?id=${param.id}">
        <input type="hidden" name="id" id="xq_id">
        <input type="hidden" name="sign">
        <input type="hidden" name="status" id="status" value="">
        <input type="hidden" name="createdBy">
        <input type="hidden" name="createdTime">
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
        	<c:if test="${param.sign=='2'}">
                <tr>
                    <td class="l-t-td-left">取消发布原因：</td>
                    <td class="l-t-td-right" colspan="3">
                        <textarea name="yy" id="yy" title="取消发布原因" cols="70" rows="3" class="l-textarea"
                                  style="width:100%" validate="{required:true}"  ></textarea>
                    </td>

                </tr>
            </c:if>
            <c:if test="${param.sign=='1'}">
            <tr>
                <td class="l-t-td-left">岗位编码：</td>
                <td class="l-t-td-right" >
                    <input name="gwNum" type="text" ltype="text" validate="{required:true}" />
                </td>
                <td class="l-t-td-left">招聘有效期：</td>
                <td class="l-t-td-right" >
                    <input name="yxTime" id="yxTime" title="选择岗位有效期" type="text" ltype="date" ligerui="{format:'yyyy-MM-dd hh:mm'}"  validate="{required:true}"/>
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">排序：</td>
                <td class="l-t-td-right" colspan="3">
                	<input name="sort" type="text" ltype='spinner' validate="{required:true}" ligerui="{type:'int'}"/>
                </td>
            </tr>
            </c:if>
            <c:if test="${param.sign!='1'}">
                <tr>
                    <td class="l-t-td-left">岗位编码：</td>
                    <td class="l-t-td-right"  >
                        <input name="gwNum" type="text" ltype="text" ligerui="{disabled:true}"/>
                    </td>
                    <td class="l-t-td-left">招聘有效期：</td>
	                <td class="l-t-td-right" >
	                    <input name="yxTime" id="yxTime" title="选择岗位有效期" type="text" ltype="date" ligerui="{format:'yyyy-MM-dd hh:mm',disabled:true}" />
	                </td>

                </tr>
                <tr>
                <td class="l-t-td-left">排序：</td>
                <td class="l-t-td-right" colspan="3">
                	<c:if test="${param.sign!='2'}">
                		<input name="sort" type="text" ltype='spinner' validate="{required:true}" ligerui="{type:'int'}" />
                	</c:if>
                	<c:if test="${param.sign=='2'}">
                		<input name="sort" type="text" ltype='spinner'  ligerui="{type:'int',disabled:true}" />
                	</c:if>
                </td>
            </tr>
            </c:if>
            <tr>
                <td class="l-t-td-left">招聘部门：</td>
                <td class="l-t-td-right" colspan="3">
                    <c:if test="${param.status!='detail'}">
                    	<c:if test="${param.sign!='2'}">
                			<input name="fkDepId" id="dep_id" type="text" title="选择机构下面的部门" ltype='text' validate="{required:true}" ligerui="{disabled:true}"/>
                		</c:if>
                		<c:if test="${param.sign=='2'}">
                			<input name="fkDepId" id="dep_id" type="text" title="选择机构下面的部门" ltype='text' ligerui="{disabled:true}"/>
                		</c:if>
                        <input name="depName" type="hidden" />
                    </c:if>
                    <c:if test="${param.status=='detail'}">
                        <input name="depName" type="text" ltype="text" ligerui="{disabled:true}"/>
                    </c:if>
                </td>
            </tr>
            <tr>
            	 <td class="l-t-td-left">岗位名称：</td>
                <td class="l-t-td-right">
                    <input name="gwName" type="text" ltype="text" ligerui="{disabled:true}"/>
                </td>
                <td class="l-t-td-left">用人方式：</td>
                <td class="l-t-td-right">
                    <u:combo name="useM"  code="zpyrfs"  attribute="disabled:true"></u:combo>
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">学历要求：</td>
                <td class="l-t-td-right">
                    <u:combo name="academic" code="zpacademic"  attribute="disabled:true"></u:combo>
                </td>
                <td class="l-t-td-left">学位要求：</td>
                <td class="l-t-td-right">
                    <u:combo name="degree" code="zpxuewei"  attribute="disabled:true"></u:combo>
                </td>

            </tr>
            <tr>

                <td class="l-t-td-left">年龄要求：</td>
                <td class="l-t-td-right">
                    <input name="nlxz" title="输入要求的最大年龄" type="text" ltype='text'
                                                ligerui="{type:'int',disabled:true}"/>
                </td>
                <td class="l-t-td-left">拟招聘人数：</td>
                <td class="l-t-td-right">
                    <input name="num" title="输入补充的人数" type="text" ltype='text'
                                                 value="" ligerui="{type:'int',disabled:true}"/>
                </td>

            </tr>
            <tr>
                <td class="l-t-td-left">专业要求：</td>
                <td class="l-t-td-right" colspan="3">
                    <textarea name="yqzy" id="yqzy" title="填写详细的专业信息" cols="70" rows="2" class="l-textarea"
                              style="width:100%"  readonly="true" ligerui="{disabled:true}" ></textarea>
                </td>
            </tr>
             <tr>
                <td class="l-t-td-left">技术职称要求：</td>
                <td class="l-t-td-right" colspan="3">
                    <textarea name="jsyq" title="填写技术职称要求" cols="70" rows="3" class="l-textarea"
                                                               style="width:100%" readonly="true" ligerui="{disabled:true}" ></textarea>
                </td>
            </tr>
             <tr>
                <td class="l-t-td-left">其他要求：</td>
                <td class="l-t-td-right" colspan="3">
                    <textarea name="otheryq" id="otheryq" title="" cols="70" rows="2" class="l-textarea"
                              style="width:100%"  readonly="true" ligerui="{disabled:true}" ></textarea>
                </td>
            </tr>

            <tr>
                <td class="l-t-td-left">备注：</td>
                <td class="l-t-td-right" colspan="3">
                    <textarea name="beizhu" title="备注" cols="70" rows="3" class="l-textarea"
                                                               style="width:100%" readonly="true" ligerui="{disabled:true}" ></textarea>
                </td>
            </tr>
        </table>

    </form>

</div>
<div id="test" title="教材信息" >
    <form id="formJc" action="app/zp/jcxx/save.do" method="get" getAction="app/zp/jcxx/detailJc.do?id=${param.id}" >
        <input type="hidden" name="id">
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
            <tr>
                <td class="l-t-td-left">教材名称：</td>
                <td class="l-t-td-right"><input name="name" title="输入教材名称" type="text" ltype='text'
                                                validate="{required:true}" ligerui="{disabled:true}">
                </td>
                <td class="l-t-td-left">主编：</td>
                <td class="l-t-td-right"><input type="text" name="zb" title="输入主编信息" ltype="text" validate="{required:true}" ligerui="{disabled:true}"/>
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">出版社：</td>
                <td class="l-t-td-right"><input name="cbs" title="出版社" type="text" ltype='text'
                                                validate="{required:true}" ligerui="{disabled:true}" /></td>
                <td class="l-t-td-left">出版时间：</td>
                <td class="l-t-td-right"><input name="cbtime" type="text" title="输入出版时间" ltype='text'
                                                validate="{required:true}" ligerui="{disabled:true}" /></td>

            </tr>
            <tr>
                <td class="l-t-td-left">备注：</td>
                <td class="l-t-td-right" colspan="3"><textarea name="beizhu" title="备注" cols="70" rows="3" class="l-textarea"
                                                               style="width:100%" ligerui="{disabled:true}" readonly="true" ></textarea></td>
            </tr>
        </table>
    </form>
    <script type="text/javascript">
        $("#formJc").initFormList({
//                opType:"auto",//数据保存模式，发现actionParam的关联信息为空时会自动调用opTypeFun，默认为auto
//                opTypeFun:save,//自动保存调用方法，opType为atuo时才会用到,默认为save
//                action:"",//保存数据或其它操作的action
            actionParam:{"fkHrZpXqxxId.id":$("#formXq>[name=id]")}, //保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把from1下的name为id的值带上去
                delAction:"app/zp/jcxx/delete.do",//删除数据的action
//                delActionParam:{"id":$},//默认为选择行的id
//                getAction:"",//取数据的action
//                getActionParam:{},//取数据时附带的参数，一般只在需要动态取特定值时用到
            onSelectRow:function (rowdata, rowindex) {
                $("#formJc").setValues(rowdata);
            },
            toolbar:null,
            columns:[
                //此部分配置同grid
                { display:'主键', name:'id', width:50, hide:true},
                { display:'教材名称', name:'name', width:200},
                { display:'主编', width:150, name:'zb'},
                { display:'出版社', name:'cbs', width:200},
                { display:'出版时间', name:'cbtime', width:100},
                { display:'备注', name:'beizhu', width:300}
            ]
        });
    </script>
</div>

        <div id="rz" title="操作日志">
            <form id="formRz" action="" method="get" getAction="app/zp/rz/detailRz.do?fkId=${param.id}" >
                <input type="hidden" name="id">
                <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
                    <tr>
                        <td class="l-t-td-left">业务名称：</td>
                        <td class="l-t-td-right"><input name="ywName" title="业务名称" type="text" ltype='text'
                                                        validate="{required:true}" ligerui="{disabled:true}">
                        </td>
                        <td class="l-t-td-left">操作人：</td>
                        <td class="l-t-td-right"><input type="text" name="name" title="操作人" ltype="text" validate="{required:true}" ligerui="{disabled:true}"/>
                        </td>
                        <td class="l-t-td-left">操作时间：</td>
                        <td class="l-t-td-right"><input name="time" title="操作时间" type="text" ltype='date'
                                                        validate="{required:true}" ligerui="{format:'yyyy-MM-dd hh:mm',disabled:true}" /></td>
                    </tr>

                </table>
            </form>
            <script type="text/javascript">
                $("#formRz").initFormList({
//                opType:"auto",//数据保存模式，发现actionParam的关联信息为空时会自动调用opTypeFun，默认为auto
//                opTypeFun:save,//自动保存调用方法，opType为atuo时才会用到,默认为save
//                action:"",//保存数据或其它操作的action
                    actionParam:{"fkId":$("#formXq>[name=id]")}, //保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把from1下的name为id的值带上去
//                    delAction:"app/zp/jcxx/delete.do",//删除数据的action
//                delActionParam:{"id":$},//默认为选择行的id
//                getAction:"",//取数据的action
//                getActionParam:{},//取数据时附带的参数，一般只在需要动态取特定值时用到
                    onSelectRow:function (rowdata, rowindex) {
                        $("#formRz").setValues(rowdata);
                    },
            toolbar:null,
                    columns:[
                        //此部分配置同grid
                        { display:'主键', name:'id', width:50, hide:true},
                        { display:'业务名称', name:'ywName', width:400},
                        { display:'操作人', width:150, name:'name'},
                        { display:'操作时间', type:"date",format:'yyyy-MM-dd hh:mm', name:'time', width:150}
                    ]
                });
            </script>
        </div>
</div>

</body>
</html>
