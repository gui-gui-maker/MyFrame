<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.status}">
    <title>直接考核面试资格审核</title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        var status='${param.status}';
        var xqid='${param.id}';
        var sign='${param.sign}';
        $(function () {
            //加载机构信息

            $("#org_id").ligerComboBox({
                valueField:'id',
                treeLeafOnly:false,
                tree:{
                    url:'app/teacher/orgTree.do',
                    checkbox:false,
                    idFieldName:'id',
                    parentIDFieldName:'pid',
                    nodeWidth:150
                },
                onSelected:function(value,text){
                    //获取该机构下面的部门
                    $('input[name="orgName"]').val(text);
                    //alert($('input[name="orgName"]').val());
                    $.ajax({
                        type:'post',
                        dataType:'html',
                        url:'app/teacher/deptTree.do?unitId='+value,
                        success:function(msg)
                        {
                            var data = eval(msg);
                            $('input[name="depName"]').val("");
                            $('input[name="depId"]').val("");
                            $('input[name="gwId"]').val("");
                            $('input[name="gwName"]').val("");
                            $("#dep_id").ligerGetComboBoxManager().clearContent();
                            $("#dep_id").ligerGetComboBoxManager().setData(data);
                        }
                    })
                }
            });
            //加载部门信息
            $("#dep_id").ligerComboBox({
                data:null,
                isMultiSelect:false,
                valueField:'id',
                onSelected:function(val,text){
                    //获取部门下面的岗位信息
                    $('input[name="depName"]').val(text);
                    $.ajax({
                        type:'post',
                        dataType:'html',
                        url:'app/teacher/postion.do?depId='+val,
                        success:function(msg)
                        {
                            // alert(msg);

                            // alert(msg);
                            var data = eval(msg);
                            data.push({"id":"999999","text":"其他"});
                            //alert(data)
                            $('input[name="gwId"]').val("");
                            $('input[name="gwName"]').val("");
                            $("#gw_id").ligerGetComboBoxManager().clearContent();
                            $("#gw_id").ligerGetComboBoxManager().setData(data);
                        }
                    })
                }
            });
            //加载岗位信息
            $("#gw_id").ligerComboBox({
                data: null,
                isMultiSelect: false,
                valueField:'id',
                onSelected:function(val,text){
                    $('input[name="gwName"]').val(text);
                    if(val=="999999"){
                        //$('#qtgw').ligerGetTextBoxManager().setValue("");
                        $('#qtgw').ligerGetTextBoxManager().setDisabled();
                    }else{
                        $('#qtgw').ligerGetTextBoxManager().setValue("");
                        $('#qtgw').ligerGetTextBoxManager().setDisabled();
                    }
                }

            });

//                    如果不设置额外参数，不用调用此方法，初始化时会自动调用
            $("#formXq").initForm({
                toolbar:false,
                toolbarPosition:'bottom',               
                getSuccess:function(responseText){
                    if(status!="detail"){
                    $('#useM-txt').ligerGetTextBoxManager().setDisabled();
                    $('#academic-txt').ligerGetTextBoxManager().setDisabled();
                    $('#degree-txt').ligerGetTextBoxManager().setDisabled();
                    }
                }
            });
            
            $("#formJl").initForm({
                toolbar:null,
//                toolbarPosition:'bottom',
                getSuccess:function(responseText){
                    if(status!="detail"){
                    $('#nationality-txt').ligerGetTextBoxManager().setDisabled();
                    $('#academic-txt').ligerGetTextBoxManager().setDisabled();
                    $('#degree-txt').ligerGetTextBoxManager().setDisabled();
                    $('#political-txt').ligerGetTextBoxManager().setDisabled();
                    $('#xjAcademic-txt').ligerGetTextBoxManager().setDisabled();
                    $('#xjDegree-txt').ligerGetTextBoxManager().setDisabled();
                    $('#physicStatus-txt').ligerGetTextBoxManager().setDisabled();
                    $('#maritalStatus-txt').ligerGetTextBoxManager().setDisabled();
                    $('#sex').ligerGetTextBoxManager().setDisabled();
                    }
                    if(status=="detail"){
                        $.getJSON('app/zp/yjyy/getReason.do',{fkId:'${param.id}',fkYwId:'2'},function(data){
                                //给原因赋值
                                if(data.success){
                                    //alert(data.data.yjyy);
                                    $('#reason').html(data.data.yjyy);
                                }

                        });
                    }
                }
            });
            //加载考场信息
            $("#examRoomIdtext").ligerComboBox({
                data: ${examRoomStr},
                isMultiSelect: false,
                valueField:'id',
                onSelected:function(val,text){
                    $('#examRoomId').val(val);
                }

            });

            $("#formInterview").initForm({
                toolbar:[
                    { text:'保存', click:function(){saveInterview();}, icon:'save'},
                    { text:'关闭', click:function(){api.close();}, icon:'delete'}
                ],
                toolbarPosition:'bottom',
                success : function(responseText) {//处理成功
                    if (responseText.success) {
                        top.$.dialog.notice({content:'操作成功！'});
                        api.data.window.submitAction();
                        api.close();
                    } else {
                        $.ligerDialog.error(responseText.msg)
                    }
                }
                });

            //根据审核情况动态改变页面上的参数
            $('#sign').ligerRadioGroup().bind("change",function(data){
                if(data=="1"){
                    $('#assign').css("display","");
                    $('#notPass').css("display","none");
                    $('#bonusReason').val("/");
                    $('#jfje').val("");
                    $('#examRoomIdtext').val("");
                }else if(data=="0"){
                    $('#notPass').css("display","");
                    $('#assign').css("display","none");
                    $('#jfje').val("/");
                    $('#examRoomIdtext').val("/");
                    $('#bonusReason').val("");
                }
            });


        });
        //发布信息保存
        function saveInterview(obj){
        	//alert("1");
           $('#formInterview').submit();
//                    setTimeout(function(){
//                        top.$.dialog.notice({content:'操作成功！'});
//                        api.close();
//                        api.data.window.submitAction();
//                    },500);
        }
        function test1(data){
        	if(data=="1"){
                $('#assign').css("display","");
                $('#notPass').css("display","none");
                $('#bonusReason').val("/");
                $('#jfje').val("");
                $('#examRoomIdtext').val("");
            }else if(data=="0"){
                $('#notPass').css("display","");
                $('#assign').css("display","none");
                $('#jfje').val("/");
                $('#examRoomIdtext').val("/");
                $('#bonusReason').val("");
            }
		}
    </script>
</head>
<body>
<div class="navtab">
<div title="面试资格审核" lselected="true" style="height: 400px">
<form id="formInterview" action="app/zp/jltd/saveAssessInterviewReview.do"  >
    <input type="hidden" name="id" value="${zpJltd.id}"/>
    <input name="examRoomId" id="examRoomId" type="hidden" value=""/>
    <fieldset class="l-fieldset">
        <legend class="l-legend">
            <div>报考基本信息</div>
        </legend>
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
            <tr>
                <td class="l-t-td-left">姓名:</td>
                <td class="l-t-td-right">
                    ${zpJlxx.name}
                </td>
                <td class="l-t-td-left">身份证号码:</td>
                <td class="l-t-td-right">${zpJlxx.certificatesNum}
                </td>
            </tr>

            <tr>
                <td class="l-t-td-left">投递岗位:</td>
                <td class="l-t-td-right" colspan="3">
                    ${zpXqxx.gwNum}-${zpXqxx.gwName}-${zpXqxx.depName}-${zpXqxx.orgName}
                </td>

            </tr>


        </table>
    </fieldset>

    <fieldset class="l-fieldset">
        <legend class="l-legend">
            <div>审核情况</div>
        </legend>
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
            <tr>
                <td class="l-t-td-left">审核是否通过:</td>
                <td class="l-t-td-right" colspan="3">
                    <c:if test="${param.status!='detail'}">
                        <input type="radio" name="sign" id="sign"  ltype="radioGroup" validate="{required:true}"
                               ligerui="{onChange:function(data){test1(data)},value:'1', data: [ { text:'审核通过', id:'1' }, { text:'审核不通过', id:'0' } ]}"/>
                    </c:if>
                    <c:if test="${param.status=='detail'}">
                        <c:if test="${zpJltd.sign=='7'}">未审核

                        </c:if>
                        <c:if test="${zpJltd.sign=='8'}">审核通过</c:if>
                        <c:if test="${zpJltd.sign=='9'}">审核不通过</c:if>
                    </c:if>

                </td>

            </tr>


        </table>
    </fieldset>
    <fieldset id="notPass" class="l-fieldset" <c:if test="${zpJltd.sign!='9'}">style="display: none"</c:if>>
        <legend class="l-legend">
            <div>不通过原因</div>
        </legend>
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
            <tr>
                <td class="l-t-td-left">不通过原因:</td>
                <td class="l-t-td-right" colspan="3">
                    <c:if test="${param.status!='detail'}">
                        <textarea name="bonusReason" id="bonusReason" title="不通过原因" cols="70" rows="3" class="l-textarea"
                                  style="width:100%" validate="{required:true}"  >/</textarea>
                    </c:if>
                    <c:if test="${param.status=='detail'}">
                        ${bonusReason}
                    </c:if>

                </td>

            </tr>
        </table>
    </fieldset>

    <fieldset id="assign" class="l-fieldset" <c:if test="${zpJltd.sign=='7' and param.status=='detail' }">style="display: none"</c:if><c:if test="${zpJltd.sign=='9'}">style="display: none"</c:if>>
        <legend class="l-legend">
            <div>考场分配</div>
        </legend>
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
            <tr>
                <td class="l-t-td-left">考场分配:</td>
                <td class="l-t-td-right">
                    <c:if test="${param.status!='detail'}">
                        <input name="examRoomIdtext" id="examRoomIdtext" type="text" ltype="text" value=""  validate="{required:true}" />
                    </c:if>
                    <c:if test="${param.status=='detail'}">
                        ${examRoomIdtext}
                    </c:if>

                </td>
            </tr>
        </table>
    </fieldset>
</form>
</div>
<div title="简历信息"  style="height: 400px">
    <form id="formJl" action="app/zp/jlxx/save.do" getAction="app/zp/jlxx/detail.do?id=${param.jlId}">
        <fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>投递人简历信息</div>
			</legend>
	        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
	            <tr>
	            	<td class="l-t-td-left">姓名：</td>
                    <td class="l-t-td-right">
                        <input name="name" type="text" ltype="text" validate="{required:true,maxLength:32}" ligerui="{disabled:true}" />
                    </td>
                    <td class="l-t-td-left">性别：</td>
                    <td class="l-t-td-right">
                        <input type="radio" name="sex" id="sex" ltype="radioGroup" 
                               ligerui="{value:'', data: [ { text:'男', id:'1' }, { text:'女', id:'2' } ],disabled:true}"/>
                    </td>
	            </tr>
                <tr>
                    <td class="l-t-td-left">身份证号码：</td>
                    <td class="l-t-td-right">
                        <input name="certificatesNum" id="certificatesNum" type="text" ltype="text" ligerui="{disabled:true}"/>
                    </td>
                    <td rowspan="5" class="l-t-td-left">头像：</td>
                    <td rowspan="5" class="l-t-td-right">
                        <input id="photoAdd" name="photoAdd" type="hidden" value=""/>
                        <img id="photos" src="/web/images/psb.png" width="126" height="160" alt="" />
                    </td>
                </tr>
                <tr>
                    <td class="l-t-td-left">出生日期：</td>
                    <td class="l-t-td-right">
                        <input name="birthday" id="birthday" type="text" ltype="text" ligerui="{disabled:true}"/>
                    </td>
                </tr>
                <tr>
                    <td class="l-t-td-left">手机号码：</td>
                    <td class="l-t-td-right">
                        <input name="telNum" type="text" ltype="text" ligerui="{disabled:true}"/>
                    </td>

                </tr>
                <tr>
                    <td class="l-t-td-left">民族：</td>
                    <td class="l-t-td-right">
                        <u:combo name="nationality" code="mz" validate="required:true"></u:combo>
                    </td>

                </tr>
                <tr>
                    <td class="l-t-td-left">政治面貌：</td>
                    <td class="l-t-td-right">
                        <u:combo name="political" code="zzmm" validate="required:true"></u:combo>
                    </td>

                </tr>
                <tr>
                    <td class="l-t-td-left">现居住地址：</td>
                    <td class="l-t-td-right" colspan="3">
                        <input name="nowaddress" type="text" ltype="text" ligerui="{disabled:true}"/>
                    </td>

                </tr>
                <tr>
                    <td class="l-t-td-left">最高学历：</td>
                    <td class="l-t-td-right">
                        <u:combo name="academic" code="academic" validate="required:true"></u:combo>
                    </td>
                    <td class="l-t-td-left">最高学位：</td>
                    <td class="l-t-td-right">
                        <u:combo name="degree" code="xuewei" validate="required:true"></u:combo>
                    </td>
                </tr>
                <tr>
                    <td class="l-t-td-left">毕业院校：</td>
                    <td class="l-t-td-right">
                        <input name="college" type="text" ltype="text" ligerui="{disabled:true}"/>
                    </td>
                    <td class="l-t-td-left">所学专业：</td>
                    <td class="l-t-td-right">
                        <input name="major" type="text" ltype="text" ligerui="{disabled:true}"/>
                    </td>
                </tr>
                <tr>
                    <td class="l-t-td-left">毕业时间：</td>
                    <td class="l-t-td-right">
                        <input name="educatedTime" type="text" ltype="date" ligerui="{disabled:true}"/>
                    </td>
                    <td class="l-t-td-left">下一级学历<br/>/学历：</td>
                    <td class="l-t-td-right">
                        <u:combo name="xjAcademic" code="academic" validate="required:true"></u:combo>
                    </td>
                </tr>
                <tr>
                    <td class="l-t-td-left">下一级学历<br/>/学位：</td>
                    <td class="l-t-td-right">
                        <u:combo name="xjDegree" code="xuewei" validate="required:true"></u:combo>
                    </td>
                    <td class="l-t-td-left">下一级学历<br/>/所学专业：</td>
                    <td class="l-t-td-right">
                        <input name="xjMajor" type="text" ltype="text" ligerui="{disabled:true}"/>
                    </td>
                </tr>
                <tr>
                    <td class="l-t-td-left">下一级学历<br/>/毕业院校：</td>
                    <td class="l-t-td-right">
                        <input name="xjCollege" type="text" ltype="text" ligerui="{disabled:true}"/>
                    </td>
                    <td class="l-t-td-left">下一级学历<br/>/毕业时间：</td>
                    <td class="l-t-td-right">
                        <input name="xjEducatedTime" type="text" ltype="date" ligerui="{disabled:true}"/>
                    </td>
                </tr>
                <tr>
                    <td class="l-t-td-left">现工作单位：</td>
                    <td class="l-t-td-right">
                        <input name="gwNum" type="text" ltype="text" ligerui="{disabled:true}"/>
                    </td>
                    <td class="l-t-td-left">参加工作时间：</td>
                    <td class="l-t-td-right">
                        <input name="cjTime" type="text" ltype="date" ligerui="{disabled:true}"/>
                    </td>
                </tr>
                <tr>
                    <td class="l-t-td-left">职称及所评专业：</td>
                    <td class="l-t-td-right">
                        <input name="professional" type="text" ltype="text" ligerui="{disabled:true}"/>
                    </td>
                    <td class="l-t-td-left">职务：</td>
                    <td class="l-t-td-right">
                        <input name="physicStatus" type="text" ltype="text" ligerui="{disabled:true}"/>
                    </td>
                </tr>
                <tr>
                    <td class="l-t-td-left">健康状况：</td>
                    <td class="l-t-td-right">
                        <u:combo name="physicStatus" code="health" validate="required:true"></u:combo>
                    </td>
                    <td class="l-t-td-left">婚姻状况：</td>
                    <td class="l-t-td-right">
                        <u:combo name="maritalStatus" code="hunyin" validate="required:true"></u:combo>
                    </td>
                </tr>
                <tr>
                    <td class="l-t-td-left">家庭通讯地址：</td>
                    <td class="l-t-td-right" colspan="3">
                        <input name="phoneadd" type="text" ltype="text" ligerui="{disabled:true}"/>
                    </td>

                </tr>
                <tr>
                    <td class="l-t-td-left">联系电话：</td>
                    <td class="l-t-td-right">
                        <input name="phone" type="text" ltype="text" ligerui="{disabled:true}"/>
                    </td>
                    <td class="l-t-td-left">邮箱地址：</td>
                    <td class="l-t-td-right">
                        <input name="email" type="text" ltype="text" ligerui="{disabled:true}"/>
                    </td>
                </tr>
	            <tr>
	                <td class="l-t-td-left">申请加分原因：</td>
	                <td class="l-t-td-right" colspan="3">
	                    <textarea name="addyy" title="申请加分原因" cols="70" rows="3" class="l-textarea"
	                                                               style="width:100%" readonly="true" ligerui="{disabled:true}" ></textarea>
	                </td>
	            </tr>
                <tr>
                    <td class="l-t-td-left">个人简历：</td>
                    <td class="l-t-td-right" colspan="3">
                        <textarea name="grjl" title="个人简历" cols="70" rows="3" class="l-textarea"
                                  style="width:100%" readonly="true" ligerui="{disabled:true}" ></textarea>
                    </td>
                </tr>
                <tr>
                    <td class="l-t-td-left">所受奖惩情况：</td>
                    <td class="l-t-td-right" colspan="3">
                        <textarea name="ssjc" title="所受奖惩情况" cols="70" rows="3" class="l-textarea"
                                  style="width:100%" readonly="true" ligerui="{disabled:true}" ></textarea>
                    </td>
                </tr>
                <tr>
                    <td class="l-t-td-left">专业证书，专长：</td>
                    <td class="l-t-td-right" colspan="3">
                        <textarea name="zyzs" title="专业证书，专长" cols="70" rows="3" class="l-textarea"
                                  style="width:100%" readonly="true" ligerui="{disabled:true}" ></textarea>
                    </td>
                </tr>
                <tr>
                    <td class="l-t-td-left">考生诚信承诺：</td>
                    <td class="l-t-td-right" colspan="3">
                        <textarea name="kscn" title="考生诚信承诺" cols="70" rows="3" class="l-textarea"
                                  style="width:100%" readonly="true" ligerui="{disabled:true}" ></textarea>
                    </td>
                </tr>
	            
	        </table>
		</fieldset>

    </form>

</div>
<div title="投递岗位信息" style="height: 400px">
    <form id="formXq" action="app/zp/xqxx/savefb.do" getAction="app/zp/xqxx/detail.do?id=${param.xqId}">
        <input type="hidden" name="id" id="xq_id">
        
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
            <tr>
                <td class="l-t-td-left">机构：</td>
                <td class="l-t-td-right">
                    <c:if test="${param.status!='detail'}">
                        <input name="fkOrgId" id="org_id" type="text" ltype='text' title="选择机构信息" validate="{required:true}" ligerui="{disabled:true}"/>
                        <input name="orgName" type="hidden" />
                    </c:if>
                    <c:if test="${param.status=='detail'}">
                        <input name="orgName" type="text" ltype="text" ligerui="{disabled:true}" />
                    </c:if>
                </td>
                <td class="l-t-td-left">部门：</td>
                <td class="l-t-td-right">
                    <c:if test="${param.status!='detail'}">
                        <input name="fkDepId" id="dep_id" type="text" title="选择机构下面的部门" ltype='text' validate="{required:true}" ligerui="{disabled:true}"/>
                        <input name="depName" type="hidden" />
                    </c:if>
                    <c:if test="${param.status=='detail'}">
                        <input name="depName" type="text" ltype="text" ligerui="{disabled:true}"/>
                    </c:if>

                </td>
            </tr>
            
            <tr>
                <td class="l-t-td-left">岗位编码：</td>
                <td class="l-t-td-right" >
                    <input name="gwNum" type="text" ltype="text" ligerui="{disabled:true}"/>
                </td>
                <td class="l-t-td-left">招聘有效期：</td>
                <td class="l-t-td-right" >
                    <input name="yxTime" id="yxTime" title="选择岗位有效期" type="text" ltype="date" ligerui="{disabled:true}"  validate="{required:true}"/>
                </td>
            </tr>
            
            
            <tr>
                <td class="l-t-td-left">岗位：</td>
                <td class="l-t-td-right">
                    <c:if test="${param.status!='detail'}">
                        <input name="fkGwId" id="gw_id" type="text" title="选择岗位信息" ltype='text'
                               validate="{required:true}" ligerui="{disabled:true}"/>
                        <input name="gwName" type="hidden" />
                    </c:if>
                    <c:if test="${param.status=='detail'}">
                        <input name="gwName" type="text" ltype="text" ligerui="{disabled:true}"/>
                    </c:if>

                </td>
                <td class="l-t-td-left">用人方式：</td>
                <td class="l-t-td-right">
                    <u:combo name="useM"  code="zpyrfs" validate="required:true"></u:combo>
                </td>
            </tr>

            <tr>
                <td class="l-t-td-left">学历：</td>
                <td class="l-t-td-right">
                    <u:combo name="academic" code="academic" validate="required:true" attribute="disabled:true"></u:combo>
                </td>
                <td class="l-t-td-left">学位：</td>
                <td class="l-t-td-right">
                    <u:combo name="degree" code="xuewei" validate="required:true" attribute="disabled:true"></u:combo>
                </td>

            </tr>
            <tr>

                <td class="l-t-td-left">年龄限制：</td>
                <td class="l-t-td-right">
                    <input name="nlxz" title="输入要求的最大年龄" type="text" ltype='text'
                                                validate="{required:true}" ligerui="{type:'int',disabled:true}"/>
                </td>
                <td class="l-t-td-left">拟补充人数：</td>
                <td class="l-t-td-right">
                    <input name="num" title="输入补充的人数" type="text" ltype='text'
                                                validate="{required:true}" value="" ligerui="{type:'int',disabled:true}"/>
                </td>

            </tr>
            <tr>
                <td class="l-t-td-left">其他岗位：</td>
                <td class="l-t-td-right" colspan="3">
                    <input name="qtGw" title="在岗位选择其他时填写岗位名称" id="qtgw" type="text" ltype='text' ligerui="{disabled:true}" />
                </td>
            </tr>
            <tr>
                <td class="l-t-td-left">要求专业：</td>
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
    <form id="formJc" action="app/zp/jcxx/save.do" method="get" getAction="app/zp/jcxx/detailJc.do?id=${param.xqId}" >
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
<c:if test="${param.status=='detail'}">
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
</c:if>
</div>

</body>
</html>
