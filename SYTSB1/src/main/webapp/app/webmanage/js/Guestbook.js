/**
 * Created by IntelliJ IDEA.
 * User: LYC
 * Date: 12-5-4
 * Time: 下午1:19
 * To change this template use File | Settings | File Templates.
 */
function GuestbookSave(){
    if($("#userTitle").val()!=""&&$("#userContent").val()!=""){
        $.post("/infomanage/guestbook/GuestbookSave.do", {
            "fkClasstypeId":$("#fkClasstypeId").val()
            ,"userTitle":$("#userTitle").val()
            ,"userNickname":$("#userNickname").val()
            ,"userName":$("#userName").val()
            ,"userSex":$("input[name='userSex'][checked=true]").val()
            ,"userTel":$("#userTel").val()
            ,"userEmail":$("#userEmail").val()
            ,"userQq":$("#userQq").val()
            ,"userContent":$("#userContent").val()
        }, function (data) {
            if (data.success) {
                alert(data.msg, "success");
                formObj.reset();
            } else {
                alert(data.msg, "error");
            }
        },"json");
    }else
    {
        alert("请完整填写留言信息")
    }
}