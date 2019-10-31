/**
 * Created by IntelliJ IDEA.
 * User: LYC
 * Date: 12-5-4
 * Time: 下午12:37
 */
function ReVoteSave(url,classTypeId) {
    var voteItems = document.getElementsByName('itemIds');
    var count = 0;
    var postJson = "0";
    for (var i = 0; i < voteItems.length; i++) {
        if (voteItems[i].checked) {
            count++;
            postJson += "," + voteItems[i].getAttribute("id");
        }
    }
    if (count == 0) {
        alert("对不起，请至少选择一个投票项！");
        return false;
    }
    $.post("/infomanage/vote/ReVote.do", {"voteItemId":postJson}, function (data) {
        if (data.success) {
            alert(data.msg, "success");
            //window.location.href = url;
            getVoteResult(classTypeId);
        } else {
            alert(data.msg, "error");
        }
    }, "json");

}
//投票验证
function check_votes(group, allowCount) {
    var voteItems = document.getElementsByName('itemIds');
    var count = 0;
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


function getVoteResult(classTypeId) {
    $.getJSON("/infomanage/vote/getVoteResult.do?classId=" + classTypeId, function (data) {
        var data = data.data;
        var inHtml = "";
        for (var i = 0; i < data.length; i++) {
            var childdata = data[i].cheild;
            var total = 0;
            for (var k = 0; k < childdata.length; k++) {
                total += childdata[k].itemVotecount;
            }
            inHtml += '<div id="search_msg" class="search_msg">投票结果：(总票数：<span>' + total + '</span>) ';
            for (var k = 0; k < childdata.length; k++) {
                var intemCount = childdata[k].itemVotecount;
                var intemCount_total = Math.round(intemCount / total * 100);
                inHtml += '<div><span class="tt">' + (k + 1) + '、' + childdata[k].itemTitle + ' - (' + intemCount + '票)</span>';
                inHtml += '<span class="pic"><img src="/app/webmanage/icons/vote_bar.gif" width="' + intemCount_total + '%" height="10px" border="0"/></span><span>' + intemCount_total + '%</span></div>';
            }
            inHtml+="</div>";
        }
        var voteResultDiv = $('#vote_result').find('div'); 
        $("#search_msg").remove();  
        $("#vote_result").append(inHtml);
        $("#vote_result").show();
    });
}

