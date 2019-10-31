<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户常用意见管理</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
    var qmUserConfig = {
        tbar:[
            { text:'增加', icon:'add', id:'add', click: add},"-",
            { text:'修改', icon:'modify', id:'modify', click: modify},"-",
            { text:'删除', icon:'del', id:'del', click: del},"-",
            { text:'设为默认', icon:'setting', id:'sdefault', click: setDefault},"-",
            { text:'取消默认', icon:'setting', id:'cdefault', click: setDefault}
        ],
        listeners: {
            selectionChange: function(rowData,rowIndex){
                var count = Qm.getSelectedCount();
                var def = Qm.getValueByName("defaults");
                Qm.setTbar({
                    modify : count == 1,
                    detail : count == 1,
                    del : count >= 1,
                    sdefault : count == 1 && def == '0',
                    cdefault : count == 1 && def == '1'
                });
            }
        }
    };

    function add() {
        top.$.dialog({
            width : 600,
            height : 255,
            lock : true,
            parent : api,
            title : "添加常用意见",
            content : 'url:pub/bpm/common_opinion_detail.jsp?status=add',
            data : {
                callback : function() {
                    Qm.refreshGrid();
                }
            }
        });
    }

    function modify() {
        top.$.dialog({
            width : 600,
            height : 255,
            lock : true,
            parent : api,
            title : "修改常用意见",
            content : 'url:pub/bpm/common_opinion_detail.jsp?status=edit&id='
                    + Qm.getValueByName("id"),
            data : {
                callback : function() {
                    Qm.refreshGrid();
                }
            }
        });
    }

    function setDefault(item) {
        $.post("bpm/common_opinion/set_default.do", {
            id : Qm.getValueByName("id"),
            dval : item.id == "sdefault" ? "1" : "0"
        }, function(resp) {
            if (resp.success) {
                top.$.notice("设置成功！");
                Qm.refreshGrid();
            } else {
                $.ligerDialog.error("设置失败，请稍后重试！");
            }
        }, "json");
    }

    function del() {
        $.del("确定要删除？", "bpm/common_opinion/delete.do", {
            "ids" : Qm.getValuesByName("id").toString()
        });
    }

    function getAllData() {
        var datas = Qm.getQmgrid().getData();
        var ocs = [];
        for ( var idx in datas) {
            ocs.push({
                text : datas[idx].content,
                id : datas[idx].id,
                def: datas[idx].defaults
            });
        }
        return ocs;
    }
</script>
</head>
<body>
    <qm:qm pageid="bpm_common_opinion" usePager="false" defaultOrder="[{field:\"sort_order\",direction:\"asc\"}]" />
</body>
</html>