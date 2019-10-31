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
//搜索文件
function searchData() {
	var Request = new Object();
    Request = GetRequest();
    var siteId=document.getElementById("siteId").value;
    var SearchValue=document.getElementsByName("search")[0].value;
    var searchType=Request['searchType'];
    if(siteId==""){
    	siteId = Request['siteId'];
    }
    if (siteId != "" && searchType== "file") {
        $.getJSON("/infomanage/article/getSearchData.do", {"siteId":siteId, "searchValue":SearchValue}, function (data) {
            var data = data.data;
            var inHtml = '<div id="searchResultDiv"><ul>';
            for (var i = 0; i < data.length; i++) {
		       inHtml+='<li><div class="num">'+(i+1)+'</div><a href="'+data[i].url+'" target="_blank" title="' + data[i].title + '">'+data[i].title+'</a><div class="pgtm">['+ showDate(data[i].eidtDate) + ']</div></li>'
            }
            inHtml += '</ul></div>';
            $("#searchResultDiv").remove();  
            $("#search_result").append(inHtml);
        });
    }
}

//首页上的搜索
function searchDataByIndex(url){
	var siteId=document.getElementById("siteId").value;
	var searchRadio=document.getElementsByName("search");
	var searchValue="";
	$.each(searchRadio,function(i){
		var radio=searchRadio[i];
		 if(radio.checked==true){
			 searchValue=radio.value;
		 }
	});
	url+='?siteId='+siteId+'&searchType='+searchValue;
	window.location.href=url;
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
    if(date!=null&&date!=""){
    	var dateStr=date.split(" ")[0];
    }
    return dateStr;
}

