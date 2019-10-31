/**
 * Created by IntelliJ IDEA.
 * User: LYC
 * Date: 12-5-4
 * Time: 下午12:37
 */


function ReVoteSave() {

    if ($("#name").val() == "") {
        alert("姓名必须填写！");
        return false;
    }
    else if ($("#password").val() == "") {
        alert("请联系成都市民政局获取提交密码！")
    }
    else if ($("select[name='identity'][option=true]").val() == "") {
        alert("请选择您的身份！")
    }
    else {
        var yfxz_itemIds = document.getElementsByName('yfxz_itemIds');
        var ywnl_itemIds = document.getElementsByName('ywnl_itemIds');
        var fwtd_itemIds = document.getElementsByName('fwtd_itemIds');
        var gzxl_itemIds = document.getElementsByName('gzxl_itemIds');
        var gzzf_itemIds = document.getElementsByName('gzzf_itemIds');
        var zwgk_itemIds = document.getElementsByName('zwgk_itemIds');
        var ljzl_itemIds = document.getElementsByName('ljzl_itemIds');
        var postJson = "0";
        postJson = setValue(yfxz_itemIds, postJson);
        postJson = setValue(ywnl_itemIds, postJson);
        postJson = setValue(fwtd_itemIds, postJson);
        postJson = setValue(gzxl_itemIds, postJson);
        postJson = setValue(gzzf_itemIds, postJson);
        postJson = setValue(zwgk_itemIds, postJson);
        postJson = setValue(ljzl_itemIds, postJson);
        if (postJson.split(",").length != 8) {
            alert("对不起，每一组评价请至少选择一个评价项目！");
            return false;
        }
        postJson = postJson.replace("0,", "").split(",");
        $.post("/infomanage/questionnaire/QuestionnaireSave.do", {
            "fkClasstypeId":$("#fkClasstypeId").val(), "yfxz":postJson[0], "ywnl":postJson[1], "fwtd":postJson[2], "gzxl":postJson[3], "gzzf":postJson[4], "zwgk":postJson[5], "ljzl":postJson[6], "wthbz":$("#wthbz").val(), "yjhjy":$("#yjhjy").val(), "name":$("#name").val(), "identity":$("select[name='identity'][option=true]").val(), "identity":$("#identity").val(), "duties":$("#duties").val(), "telph":$("#telph").val(), "unit":$("#unit").val(), "password":$("#password").val()
        }, function (data) {
            if (data.success) {
                if ("ok" == data.msg) {
                    alert("提交成功，谢谢参与", "success");
                    formObj.reset();
                }
                else {
                    alert(data.msg, "success");
                    //formObj.reset();
                }
            } else {
                alert(data.msg, "error");
            }
        }, "json");
    }

}
function setValue(itemIds, postJson) {
    for (var i = 0; i < itemIds.length; i++) {
        if (itemIds[i].checked) {
            postJson += "," + itemIds[i].getAttribute("value");
        }
    }
    return postJson;
}
//投票验证
function check_votes(group, allowCount) {
    var voteItems = document.getElementsByName(group);
    var count = 0;
    //alert(voteItems.length)
    for (var i = 0; i < voteItems.length; i++) {
        if (voteItems[i].checked && voteItems[i].getAttribute("group") == group) {
            count++;
        }
    }
    if (count == allowCount && allowCount >= 1) {
        for (var i = 0; i < voteItems.length; i++) {
            if (!voteItems[i].checked && voteItems[i].getAttribute("group") == group) {
                voteItems[i].disabled = true;
            }
        }
        return true;
    }
    else {
        for (var i = 0; i < voteItems.length; i++) {
            if (voteItems[i].getAttribute("group") == group)
                voteItems[i].disabled = false;
        }
    }
    if (count == 0) {
        //alert("对不起，请至少选择一个投票项！");
        return false;
    }
    return true;
}
function pswCheck() {

    var psw = prompt("请输入密码", '');
    if (psw!=null && psw!="")
    {
        $.post("/infomanage/questionnaire/pswCheck.do", { "password":psw}, function (data) {
            if (data.success) {
                if ("ok" == data.msg) {
                    window.location.href = "/cdmzj/fdjl/wsdc/mzgzdcwj";
                }
                else {
                    alert(data.msg, "success");

                }
            } else {
                alert(data.msg, "error");
            }
        }, "json");
    }
    else
    {
        alert("请联系成都市民政局获取密码！")
    }
}


