/**
 * Created by IntelliJ IDEA.
 * User: LYC
 * Date: 12-5-23
 * Time: 下午7:43
 * To change this template use File | Settings | File Templates.
 */
function getRoadName(url) {
    var road_name = document.getElementsByName('road_name')[0].value;
    var RadioGroup1 = document.getElementsByName('RadioGroup1');
    var checkRadio ="like";
    for (var i = 0; i < RadioGroup1.length; i++) {
        if (RadioGroup1[i].checked) {
            checkRadio = RadioGroup1[i].value;
        }
    }
    var searchValue = document.getElementsByName('searchValue')[0].value;
    if ($.trim(searchValue) == "") {
        alert("请输入查询关键字！");
        return false;
    }
    var Strurl = url + "?road_name=" + road_name + "&RadioGroup1=" + checkRadio + "&searchValue=" + searchValue;
    window.location.href = Strurl;
}
function reRoadName() {

    var Request = new Object();
    Request = GetRequest();
    var road_name = Request['road_name'];
    var RadioGroup1 = Request['RadioGroup1'];
    var searchValue = Request['searchValue'];
    if (searchValue != ""&&searchValue!=undefined) {
        $.getJSON("/area/roadname/getRoadName.do", {"roadName":road_name, "RadioGroup":RadioGroup1, "searchValue":searchValue}, function (data) {
            var data = data.data;
            var inHtml = "<table width='100%' cellspacing='1'>" +
                "<tr class='Road_Search_Result_title'> <td>标准名称</td> " +
                "<td>罗马字母拼写</td> " +
                //"<td>地名含义</td> " +
                "<td>起点</td> " +
                "<td>起点</td> " +
                "<td>长度</td> " +
                "<td>路面宽度</td> " +
                //"<td>路面质地</td> " +
                //"<td>道路等级</td> " +
                //"<td>门牌号码范围</td> " +
                "<td>途经行政区</td> " +
                "</tr>";
            for (var i = 0; i < data.length; i++) {

                inHtml += "<tr>";
                inHtml += "<td>" + reNull(data[i].name) + "</td>";
                inHtml += "<td>" + reNull(data[i].romanChar) + "</td>";
                //inHtml += "<td>" + reNull(data[i].reson) + "</td>";
                inHtml += "<td>" + reNull(data[i].startPlace) + "</td>";
                inHtml += "<td>" + reNull(data[i].endPlace) + "</td>";
                inHtml += "<td>" + reNull(data[i].roadLength) + "</td>";
                inHtml += "<td>" + reNull(data[i].width) + "</td>";
                //inHtml += "<td>" + reNull(data[i].texture) + "</td>";
                //inHtml += "<td>" + reNull(data[i].roadLevel) + "</td>";
                //inHtml += "<td>" + reNull(data[i].houseNumber) + "</td>";
                inHtml += "<td>" + reNull(data[i].wayArea) + "</td>";
                inHtml += "</tr>";
            }
            inHtml += '</table>';
            //prompt("",inHtml);
            //alert(inHtml);
            $("#Road_Search_Result").append(inHtml);
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
/*
* 去空
* */
function reNull(str)
{

    if (!str && typeof(str)!="undefined" && str!=0)
        return "";
    else
        return str;

}