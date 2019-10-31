<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title></title>
    <%@include file="/k/kui-base-list.jsp" %>
    <%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
    <script src="app/statistics/js/echarts.js"></script>
    <script test="text/javascript">
        var allData;
        $(function () {
            $('#btn1').ligerButton({
                icon: "count",
                click: function () {
                    init();
                }, text: "统计"
            });

            $('#btn2').ligerButton({
                icon: "table",
                click: function () {
                    var button = $('#btn2');
                    var sp1 = button.find("span.l-button-text");
                    var sp2 = button.find("span.l-button-icon");
                    if (sp1.html() === "表格") {
                    	sp2.attr("class","l-button-icon iconfont l-icon-piechart");
                        sp1.html("图表");
                        $("#main").css("display", "none");
                        $("#container").css("display", "block");
                    } else {
                    	sp2.attr("class","l-button-icon iconfont l-icon-table");
                        sp1.html("表格");
                        $("#main").css("display", "block");
                        draw(allData);
                        $("#container").css("display", "none");
                    }
                    init();
                },
                text: "表格"
            });

            $("#form1").ligerForm();
            init();
        });

        function init() {
            var column = [
                {display: '性别', name: 'name', align: 'center', width: 300},
                {display: '人数', name: 'num', align: 'center', width: 300},
                {display: '占比', name: 'per', align: 'center', width: 300}];
            var b;
            var conditionObj = $('#condition');
            var condition = conditionObj.ligerGetComboBoxManager().findValueByText(conditionObj.val());
            if (condition === "2") {
                b = '性别';
            } else if (condition === "3") {
                b = '职称';
            } else if (condition === "4") {
                b = '职务';
            } else if (condition === "5") {
                b = '编制情况';
            } else if (condition === "6") {
                b = '中干情况';
            } else if (condition === "7") {
                b = '年龄层次';
            } else if (condition === "8") {
                b = '学历情况';
            } else if (condition === "9") {
                b = '人员性质';
            } else {
                $.ligerDialog.alert("请选择统计条件！");
            }
            column[0].display = b;
            $.post("employeeBaseAction/edumajorCount.do", {"condition": condition}, function (resp) {
                allData = resp.data;
                draw(allData, condition);
                inputGrid = $("#countGrid").ligerGrid({
                    columns: column,
                    data: {Rows: eval(JSON.stringify(resp.data))},//json格式的字符串转为对象
                    height: '100%',
                    usePager: false,
                    width: '100%',
                    onSelectRow: function (rowdata, rowindex) {
                    }
                }, "json");
            });
        }
    </script>
</head>
<body>
<form name="form1" id="form1" action="">
    <div class="item-tm">
        <div class="l-page-title2 has-icon has-note" style="height: 80px">
            <div class="l-page-title2-div"></div>
            <div class="l-page-title2-text"><h1>人力资源统计</h1></div>
            <div class="l-page-title2-note">以员工编制情况、中干情况、年龄层次、学历情况、人员性质、性别、职称、职务为统计对象。</div>
            <div class="l-page-title2-icon"><img src="k/kui/images/icons/32/statistics.png" border="0"/></div>
            <div class="l-page-title-content" style="top:15px;height:80px;">
                <table border="0" cellpadding="0" cellspacing="0" width="">
                    <tr>
                        <td width="80" style="text-align: right">统计条件：</td>
                        <td width="110">
                            <input type="text" name="condition" id="condition" ltype="select"
                                   ligerui="{value:'2',data: [{ text:'性别', id:'2' }, { text:'职称', id:'3' },
                                   { text:'职务', id:'4' },{ text:'编制情况', id:'5' },{ text:'中干情况', id:'6' },
                                   { text:'年龄层次', id:'7' },{ text:'学历情况', id:'8' },{ text:'人员性质', id:'9' }]}"/>
                        </td>
                        <td width="" style="text-align: right;float: left;padding-left: 5px;">
                            <div id="btn1"></div>
                            <div id="btn2"></div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</form>
<div id="container" position="center" style="display:none">
    <div id="countGrid"></div>
</div>
<div id="main" style="width:100%;height:400px;"></div>
</body>
<script type="text/javascript">

    function draw(data, condition) {
        var title;
        switch (condition) {
            case '2':
                title = '性别';
                break;
            case '3':
                title = '职称';
                break;
            case '4':
                title = '职务';
                break;
            case '5':
                title = '编制情况';
                break;
            case '6':
                title = '中干情况';
                break;
            case '7':
                title = '年龄层次';
                break;
            case '8':
                title = '学历情况';
                break;
            case '9':
                title = '人员性质';
                break;
            default:
                title = 'love';
                break;
        }
        var xData = [];//x轴
        var pieData = [];
        $.each(data, function (index) {
            xData.push(data[index].name);
            var dd = {};
            dd.value = data[index].num;
            dd.name = data[index].name;
            pieData.push(dd);
        });
        var myChart = echarts.init(document.getElementById('main'));
        option = {
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: 'left',
                data: xData
            },
            series: [
                {
                    name: title,
                    type: 'pie',
                    radius: ['50%', '70%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data: pieData,
                    color: [

                        '#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B',
                        '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
                        '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0',
                        '#0000FF', '#3D59AB', '#1E90FF', '#0B1746', '#03A89E',
                        '#191970', '#33A1C9', '#8A2BE2', '#DA70D6', '#F0E68C',
                        '#BC8F8F', '#C76114', '#734A12', '#5E2612', '#2E8B57',
                        '#00FF7F', '#40E0D0', '#6A5ACD', '#4682B4', '#1E90FF',
                        '#483D8B', '#CD5555', '#FF1493', '#8B8989', '#CD6889',
                        '#CDB5CD', "#c23531", "#2f4554", "#61a0a8", "#d48265",
                        "#91c7ae", "#749f83", "#ca8622", "#bda29a", "#6e7074",
                        "#546570", "#c4ccd3", "#4BABDE", "#FFDE76", "#E43C59",
                        "#37A2DA"
                    ]
                }
            ]
        };
        myChart.setOption(option);
    }
</script>
</html>