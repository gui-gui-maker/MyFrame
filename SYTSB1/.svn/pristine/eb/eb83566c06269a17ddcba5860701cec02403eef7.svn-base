/**
 * Created by IntelliJ IDEA.
 * User: LYC
 * Date: 12-5-23
 * Time: 上午11:21
 * To change this template use File | Settings | File Templates.
 */


function getSearchData(url) {
    var siteId = document.getElementsByName('siteId')[0].value;
    var SearchValue = document.getElementsByName('SearchValue')[0].value;
    if ($.trim(SearchValue) == "") {
        alert("请输入查询关键字！");
        return false;
    }
    var Strurl = url + "?siteId=" + siteId + "&SearchValue=" + SearchValue;
    window.location.href = Strurl;
}
function reSearchDate() {
    var Request = new Object();
    Request = GetRequest();
    var siteId = Request['siteId'];
    var SearchValue = Request['SearchValue'];
    //document.getElementsByName('siteId')[0].value=SearchValue;
    //$("SearchValue").attr("value",SearchValue);
    if (siteId != "" && SearchValue != "") {
        $.getJSON("/infomanage/article/getSearchData.do", {"siteId":siteId, "searchValue":SearchValue}, function (data) {
            var data = data.data;
            var inHtml = "<ul>";
            for (var i = 0; i < data.length; i++) {
                inHtml += '<li><a href="' + data[i].url + '">' + data[i].title + '</a>' + showDate(data[i].eidtDate) + '</li>'
            }
            inHtml += '</ul>';
            $("#search_result").append(inHtml);
        });
    }
}
function cdmzjSearchDate() {
    var Request = new Object();
    Request = GetRequest();
    var siteId = Request['siteId'];
    var SearchValue = Request['SearchValue'];
    //document.getElementsByName('siteId')[0].value=SearchValue;
    //$("SearchValue").attr("value",SearchValue);
    if (siteId != "" && SearchValue != "") {
        $.getJSON("/infomanage/article/getSearchData.do", {"siteId":siteId, "searchValue":SearchValue}, function (data) {
            var data = data.data;
            var inHtml = "<ul>";
            for (var i = 0; i < data.length; i++) {
                inHtml += '<li><div class="p-list1-date"><span>'+ showDate(data[i].eidtDate) + '</span></div>' +
                    '<div class="p-list1-div"><a href="' + data[i].url + '" target="_blank" title="' + data[i].title + '"><span>' + data[i].title + '</span></a></div>' +
                    '   </li>'
            }
            inHtml += '</ul>';
            $("#search_result").append(inHtml);
        });
    }
}
function GetRequest() {
    var url = location.search;
    // 获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}
function showDate(date) {
    var date = new Date(date);
    var now = "";
    now = date.getFullYear() + "-";
    now = now + (date.getMonth() + 1) + "-";
    //取月的时候取的是当前月-1如果想取当前月+1就可以了
    now = now + date.getDate();
    return now;
}

