<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>人员信息列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<style type="text/css">
   .man{
       background-image: url("pub/rbac/img/man.gif");
   }
   .woman{
       background-image: url("pub/rbac/img/woman.gif");
   }
   .photo{
       display: block;
       float: right;
       height: 16px;
       margin-left: 2px;
       margin-top: 2px;
       overflow: hidden;
       width: 16px;
   }
</style>
<script type="text/javascript" src="pub/rbac/js/photo.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
    var qmUserConfig = { 
        sp_fields : [
            {name : "name", compare : "like"},
            {name : "org_name", compare : "like"},
            {name : "gender", compare : "=", xtype: "radioGroup"}
        ],
        <sec:authorize ifNotGranted="super_administrate">
            <tbar:toolBar type="tbar" code="hrManage"/>,
        </sec:authorize>
        <sec:authorize access="hasRole('super_administrate')">
        tbar:[
               { text:'详情', id:'detail',icon:'detail', click: detailEmployee},
               "-",
               { text:'新增', id:'add',icon:'add', click: addEmployee},
               "-",
               { text:'修改', id:'modify',icon:'modify', click: modifyEmployee},"-",
               { text:'岗位调整 ', id:'gwtz',icon:'modify', click: gwtz},
               "-",
               { text:'删除', id:'del',icon:'delete', click: delEmployee}
          ],
        </sec:authorize>
        listeners: {
            rowDblClick: detailEmployee,
            selectionChange :function(rowData,rowIndex){
                var count=Qm.getSelectedCount();
                Qm.setTbar({detail:count==1,gwtz:count>=1, edit:count==1, del:count>=1,out:count==1,produce:count>=1});
            }
        }
    };
     
    function gwtz(){
        selectUnitOrUser(4, 1, null, null, function(data){
            $.ligerDialog.confirm("确认调整员工岗位信息？",function(yes){
                if(yes){
                    $("body").mask();
                    $.post("rbac/position/ajustPostion.do",{empIds:Qm.getValuesByName("id").toString(),positionIds:data.code,positionNames:data.name},function(res){
                        $("body").unmask("操作中...");
                        if(res.success){
                            top.$.notice('操作成功！');
                            Qm.refreshGrid();
                        }else{
                            $.ligerDialog.error(res.msg);
                        }
                    },"JSON")
                }
            })
        });
    }
     
    function addEmployee(){
        var org = parent.orgTreeManager.getSelected();
        top.$.dialog({
            width: $(top).width(),
            height:$(top).height(),
            lock:true,
            parent:api,
            data:{window:window} ,
            title:"新增",
            content: 'url:pub/rbac/employee_detail.jsp?pageStatus=add&orgid=' + org.data.id
        });
    }
    function modifyEmployee(){
        var id = Qm.getValueByName("id");
        var windows=top.$.dialog({
            width: $(top).width(),
               height: $(top).height(),
            lock:true,
            parent:api,
            data:{window:window} ,
            title:"修改",
            content: 'url:pub/rbac/employee_detail.jsp?id='+id+'&pageStatus=modify'
        });
    }
    function delEmployee(){
        $.del(kFrameConfig.DEL_MSG,"rbac/employee/delete.do",{"ids":Qm.getValuesByName("id").toString()});
    }
    function detailEmployee(){
        var id = Qm.getValueByName("id");
        var windows=top.$.dialog({
            width: $(top).width(),
               height: $(top).height(),
            lock:true,
            parent:api,
            data:{window:window} ,
            title:"详情",
            content: 'url:pub/rbac/employee_detail.jsp?id='+id+'&pageStatus=detail'
        });
    }
    
    function parsePic(thisValue,row){
              if(row["idno"]!=""){
                  return thisValue+" <span idn='"+row["idno"]+"' class='photo "+(row["gender"]=="男"?"man":"woman")+"'> </span>"
              }
              return thisValue;
          }
    
    function loadGridData(orgId){
        Qm.config.defaultSearchCondition[0].value=orgId;
        Qm.searchData();
    }
    function submitAction(o) {
        Qm.refreshGrid();
    }
</script>
</head>
<body>
    <qm:qm pageid="employee">
        <qm:param name="level_code" compare="llike" value="${param.levelCode}" />
        <qm:param name="polstatus" value="1" compare="!="/>
    </qm:qm>
</body>
</html>