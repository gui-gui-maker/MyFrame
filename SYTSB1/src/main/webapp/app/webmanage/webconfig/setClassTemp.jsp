<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
       /* $(function () {
            $("#form1").initForm({

            });
        });*/
        /*从数据库得到栏目分类的数据*/
        var TreeClassData;
        var ListTempData;
        var ContentTempData;
        $.ajax({
            url:'infomanage/setclasstemp/ClassViewTree.do',
            dataType:'text',
            data:{"siteId":'${param.siteId}', "sytleId":'${param.styleId}'},
            success:function (data) {
                data = eval("(" + data + ")");
                getListTempData();
                getContentTempData();
                f_initGrid(data)
            }});
        /*从数据库得到列表模板及自定义模板的数据*/

        function getListTempData() {
            $.ajax({
                url:'infomanage/setclasstemp/ListTemp.do',
                dataType:'text', async:false,
                data:{"siteId":'${param.siteId}', "sytleId":'${param.styleId}'},
                success:function (data) {
                    ListTempData = eval("(" + data + ")").Rows;
                    // alert(ListTempData.length)
                }});
        }
        /*从数据库得到内容模板及自定义模板的数据*/
        function getContentTempData() {
            $.ajax({
                url:'infomanage/setclasstemp/ContentTemp.do',
                dataType:'text', async:false,
                data:{"siteId":'${param.siteId}', "sytleId":'${param.styleId}'},
                success:function (data) {
                    ContentTempData = eval("(" + data + ")").Rows;
                }});
        }
        var manager, g;
        function f_initGrid(TreeClassData) {
            g = manager = $("#maingrid").ligerGrid({
                toolbar:{items:[
                    {text:"保存", icon:"save", click:function (){
                        manager.endEdit();//提交修改
                        var Postdata = manager.getData();//得到修改的数据
                        if (Postdata != "") {
                            $.post("infomanage/setclasstemp/saveStyleClassTemp.do", {"josnData":$.ligerui.toJSON(Postdata)}, function (data) {
                                if (data.success) {
                                    api.close()
                                } else {
                                    parent.$.ligerDialog.alert(data.msg, "error");
                                    api.close();
                                }
                            });
                        }
                    }}
                ]},
                columns:[
                    { display:'ID', name:'Id', hide:true, width:'1%'},
                    { display:'风格ID', name:'fkStyleId', hide:true, width:'1%'},
                    { display:'栏目ID', name:'fkClasstypeId', hide:true, width:'1%'},
                    { display:'栏目', name:'className', width:'35%', align:'left'},
                    { display:'列表模板', width:300, name:'fkListTempId',
                        editor:{ type:'select', data:ListTempData, valueColumnName:'fkListTempId', displayColumnName:'listTempName',width:'300px'},
                        render:function (item) {
                            for (var i = 0; i < ListTempData.length; i++) {
                                //alert(ListTempData[i]['listTempId']+"------"+item.listTemp)
                                if (ListTempData[i]['fkListTempId'] == item.fkListTempId)
                                    return ListTempData[i]['listTempName']
                            }
                            return item.listTempName;
                        }
                    },
                    { display:'内容模板', width:'30%', name:'fkDetailTempId',
                        editor:{ type:'select', data:ContentTempData, valueColumnName:'fkDetailTempId', displayColumnName:'contentTempName'},
                        render:function (item) {
                            for (var i = 0; i < ContentTempData.length; i++) {
                                //alert(ListTempData[i]['listTempId']+"------"+item.listTemp)
                                if (ContentTempData[i]['fkDetailTempId'] == item.fkDetailTempId)
                                    return ContentTempData[i]['contentTempName']
                            }
                            return item.contentTempName;
                        }
                    }
                ],
                onSelectRow:function (rowdata, rowindex) {
                    $("#txtrowindex").val(rowindex);
                    var row = manager.getSelectedRowObj();
                    //if (!manager.hasChildren(row))
                    beginEdit();
                },
                enabledEdit:true, clickToEdit:false,
                data:TreeClassData, usePager:false, tree:{ columnName:'className' },
                width:'auto', height:'100%'
            });
        }
        function beginEdit() {
            var row = manager.getSelectedRow();
            if (!row) {
                alert('请选择行');
                return;
            }
            manager.beginEdit(row);
        }
    </script>
</head>
<body>
<div id="maingrid" ></div>
</body>
</html>