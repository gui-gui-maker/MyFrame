<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="/k/kui-base.jsp" %>
    <title></title>

    <script type="text/javascript" src="pub/worktask/js/worktask.js"></script>
    <script type="text/javascript" src="pub/bpm/js/util.js"></script>

    <!--载入欢迎页资源包-->
    <script type="text/javascript" src="k/kui/frame/welcome-desktop.js"></script>
    <script type="text/javascript" src="k/kui/frame/jquery.mousewheel.js"></script>
    <script type="text/javascript">
        loadCoreLibrary({css: "shell/welcome-desktop.css"});
    </script>
    <!--在这里可以引入单独的css-->
    <!--<link rel="stylesheet" href="app/kui/skins/default/shell/welcome-desktop.css" type="text/css" media="all" id="cssMain"/>-->
    <script type="text/javascript">
        function openAffairs(text, url) {
            var width = 700;
            var height = 500;
            //起草公文
            if (text == "起草公文") {
                addDocDraft();
                return;
            }
            if (text == "发布通知公告") {
                width = 900;
                height = 558;
            }
            if (text == "发起协同任务") {
                width = 700;
                height = 370;
            }
            if (text == "新建任务") {
                width = 700;
                height = 370;
            }
            if (text == "请示起草") {
                width = 700;
                height = 380;
            }
            if (text == "报送信息") {
                width = 900;
                height = 621;
            }
            if (text == "个人通讯录") {
                width = 1106;
                height = 472;
            }
            if (text == "公共通讯录") {
                width = 1106;
                height = 872;
            }
            top.$.dialog({
                width: width,
                height: height,
                lock: true,
                title: text,
                content: "url:" + url
            });
        }


        //打开工作任务列表
        function openWorktaskList(workType, title) {
            alert("演示");
            return;
            top.$.dialog({
                width: $(top).width() * 0.8,
                height: $(top).height() * 0.8,
                lock: true,
                title: "待处理任务—" + title,
                data: {
                    "window": window
                },
                content: 'url:pub/worktask/worktask_list.jsp?status=0,2&workType=' + workType
            }).max();
        }


        function openDialog(title, url) {
            top.$.dialog({
                width: $(top).width() * 0.8,
                height: $(top).height() * 0.8,
                lock: true,
                title: title,
                data: {
                    "window": window
                },
                content: 'url:' + url
            });
        }


        function doListC(keys) {
            $("#dot-list li").removeClass("selected")
            $("#dot-list li:eq(" + keys + ")").addClass("selected");
        }

    </script>
</head>
<body class="dst-allbg">
<div class="newwrap" id="newwrap">
    <div id="page-switcher-start" class="page-switcher">
        <div></div>
        <a href="javascript:void(0);" class="btn btnPre" id="banner_index_pre"></a></div>
    <div class="btn_arlt" sId="2">
        <div class="wd-content">
            <div class="wl-tab-y" id="wlTabY1" style="width:100%;">

                <h3>业务分布</h3>

                <div class="tab-item">
                    <div id="charts333" style="width:100%;height:400px;"></div>

                </div>
                <h3>统计合集</h3>

                <div class="tab-item">
                    <div id="charts111" style="width:100%;height:400px;"></div>

                </div>
                <h3>各地统计</h3>

                <div class="tab-item">
                    <div id="charts222" style="width:100%;height:400px;"></div>

                </div>


                <h3>柱状图</h3>

                <div class="tab-item">
                    <div id="charts000" style="width:100%;height:400px;"></div>
                </div>
            </div>
            <script type="text/javascript">
                $(function () {//jQuery页面载入事件
                    init_2_fun();
                });
                //回调函数
                function init_2_fun() {
                    if ($("#wlTabY1").data("init")) {
                        return;
                    }
                    $("#wlTabY1").pageTab({
                        type: "left",
                        tabInit: function (tabObj, tabId) {
                            if (tabId == 0) {
                                echartsY3Init();
                            }
                            if (tabId == 1) {
                                echartsY1Init();
                            }
                            if (tabId == 2) {
                                echartsY2Init();
                            }
                            if (tabId == 3) {
                                echartsY0Init();
                            }
                        },
                        tabEvent: function (tabObj, tabId) {

                        }
                    });
                }

                function echartsY0Init() {
                    var myChart = echarts.init(document.getElementById("charts000"));
                    var option = {
                        tooltip: {
                            trigger: 'axis'
                        },
                        toolbox: {
                            show: true,
                            feature: {
                                mark: {
                                    show: true
                                },
                                dataView: {
                                    show: true,
                                    readOnly: false
                                },
                                magicType: {
                                    show: true,
                                    type: ['line', 'bar']
                                },
                                restore: {
                                    show: true
                                },
                                saveAsImage: {
                                    show: true
                                }
                            }
                        },
                        calculable: true,
                        legend: {
                            data: ['蒸发量', '降水量', '平均温度']
                        },
                        xAxis: [{
                            type: 'category',
                            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
                        }],
                        yAxis: [{
                            type: 'value',
                            name: '水量',
                            axisLabel: {
                                formatter: '{value} ml'
                            },
                            splitArea: {
                                show: true
                            }
                        }, {
                            type: 'value',
                            name: '温度',
                            axisLabel: {
                                formatter: '{value} °C'
                            },
                            splitLine: {
                                show: false
                            }
                        }],
                        series: [

                            {
                                name: '蒸发量',
                                type: 'bar',
                                data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
                            }, {
                                name: '降水量',
                                type: 'bar',
                                data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
                            }, {
                                name: '平均温度',
                                type: 'line',
                                yAxisIndex: 1,
                                data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
                            }]
                    };

                    myChart.setOption(option);
                }
                function echartsY1Init() {
                    var myChart = echarts.init(document.getElementById("charts111"));
                    var option = {
                        tooltip: {
                            trigger: 'axis'
                        },
                        toolbox: {
                            show: true,
                            y: 'bottom',
                            feature: {
                                mark: {
                                    show: true
                                },
                                dataView: {
                                    show: true,
                                    readOnly: false
                                },
                                magicType: {
                                    show: true,
                                    type: ['line', 'bar', 'stack', 'tiled']
                                },
                                restore: {
                                    show: true
                                },
                                saveAsImage: {
                                    show: true
                                }
                            }
                        },
                        calculable: true,
                        legend: {
                            data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎', '百度', '谷歌', '必应', '其他']
                        },
                        xAxis: [{
                            type: 'category',
                            splitLine: {
                                show: false
                            },
                            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
                        }],
                        yAxis: [{
                            type: 'value',
                            position: 'right',
                            splitArea: {
                                show: true
                            }
                        }],
                        series: [{
                            name: '直接访问',
                            type: 'bar',
                            data: [320, 332, 301, 334, 390, 330, 320]
                        }, {
                            name: '邮件营销',
                            type: 'bar',
                            tooltip: {
                                trigger: 'item'
                            },
                            stack: '广告',
                            data: [120, 132, 101, 134, 90, 230, 210]
                        }, {
                            name: '联盟广告',
                            type: 'bar',
                            tooltip: {
                                trigger: 'item'
                            },
                            stack: '广告',
                            data: [220, 182, 191, 234, 290, 330, 310]
                        }, {
                            name: '视频广告',
                            type: 'bar',
                            tooltip: {
                                trigger: 'item'
                            },
                            stack: '广告',
                            data: [150, 232, 201, 154, 190, 330, 410]
                        }, {
                            name: '搜索引擎',
                            type: 'line',
                            data: [862, 1018, 964, 1026, 1679, 1600, 1570]
                        },

                            {
                                name: '搜索引擎细分',
                                type: 'pie',
                                tooltip: {
                                    trigger: 'item',
                                    formatter: '{a} <br/>{b} : {c} ({d}%)'
                                },
                                center: [160, 130],
                                radius: [0, 50],
                                itemStyle: {
                                    normal: {
                                        labelLine: {
                                            length: 20
                                        }
                                    }
                                },
                                data: [{
                                    value: 1048,
                                    name: '百度'
                                }, {
                                    value: 251,
                                    name: '谷歌'
                                }, {
                                    value: 147,
                                    name: '必应'
                                }, {
                                    value: 102,
                                    name: '其他'
                                }]
                            }]
                    };

                    myChart.setOption(option);
                }
                function echartsY2Init() {
                    var myChart = echarts.init(document.getElementById("charts222"));
                    var option = {
                        tooltip: {
                            trigger: 'item'
                        },
                        legend: {
                            x: 'right',
                            data: ['北京', '上海', '广东']
                        },
                        dataRange: {
                            orient: 'horizontal',
                            min: 0,
                            max: 2500,
                            text: ['高', '低'],           // 文本，默认为数值文本
                            splitNumber: 0
                        },
                        toolbox: {
                            show: true,
                            orient: 'vertical',
                            x: 'right',
                            y: 'center',
                            feature: {
                                mark: {show: true},
                                dataView: {show: true, readOnly: false}
                            }
                        },
                        series: [
                            {
                                name: 'iphone销量',
                                type: 'map',
                                mapType: 'china',
                                mapLocation: {
                                    x: 'left'
                                },
                                selectedMode: 'multiple',
                                itemStyle: {
                                    normal: {label: {show: true}},
                                    emphasis: {label: {show: true}}
                                },
                                data: [
                                    {name: '北京', value: 1234, selected: true},
                                    {name: '天津', value: Math.round(Math.random() * 1000)},
                                    {name: '上海', value: 3456, selected: true},
                                    {name: '重庆', value: Math.round(Math.random() * 1000)},
                                    {name: '河北', value: Math.round(Math.random() * 1000)},
                                    {name: '河南', value: Math.round(Math.random() * 1000)},
                                    {name: '云南', value: Math.round(Math.random() * 1000)},
                                    {name: '辽宁', value: Math.round(Math.random() * 1000)},
                                    {name: '黑龙江', value: Math.round(Math.random() * 1000)},
                                    {name: '湖南', value: Math.round(Math.random() * 1000)},
                                    {name: '安徽', value: Math.round(Math.random() * 1000)},
                                    {name: '山东', value: Math.round(Math.random() * 1000)},
                                    {name: '新疆', value: Math.round(Math.random() * 1000)},
                                    {name: '江苏', value: Math.round(Math.random() * 1000)},
                                    {name: '浙江', value: Math.round(Math.random() * 1000)},
                                    {name: '江西', value: Math.round(Math.random() * 1000)},
                                    {name: '湖北', value: Math.round(Math.random() * 1000)},
                                    {name: '广西', value: Math.round(Math.random() * 1000)},
                                    {name: '甘肃', value: Math.round(Math.random() * 1000)},
                                    {name: '山西', value: Math.round(Math.random() * 1000)},
                                    {name: '内蒙古', value: Math.round(Math.random() * 1000)},
                                    {name: '陕西', value: Math.round(Math.random() * 1000)},
                                    {name: '吉林', value: Math.round(Math.random() * 1000)},
                                    {name: '福建', value: Math.round(Math.random() * 1000)},
                                    {name: '贵州', value: Math.round(Math.random() * 1000)},
                                    {name: '广东', value: 2345, selected: true},
                                    {name: '青海', value: Math.round(Math.random() * 1000)},
                                    {name: '西藏', value: Math.round(Math.random() * 1000)},
                                    {name: '四川', value: Math.round(Math.random() * 1000)},
                                    {name: '宁夏', value: Math.round(Math.random() * 1000)},
                                    {name: '海南', value: Math.round(Math.random() * 1000)},
                                    {name: '台湾', value: Math.round(Math.random() * 1000)},
                                    {name: '香港', value: Math.round(Math.random() * 1000)},
                                    {name: '澳门', value: Math.round(Math.random() * 1000)}
                                ]
                            },
                            {
                                name: '各省销量',
                                type: 'pie',
                                tooltip: {
                                    trigger: 'item',
                                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                                },
                                //center: [document.getElementById('charts222').offsetWidth - 150, "50%"],
                                center: [700, "50%"],
                                radius: [50, 120],
                                data: [
                                    {name: '北京', value: 1234},
                                    {name: '上海', value: 3456},
                                    {name: '广东', value: 2345}
                                ]
                            }
                        ],
                        animation: false
                    };
                    var ecConfig = {
                        "CHART_TYPE_LINE": "line",
                        "CHART_TYPE_BAR": "bar",
                        "CHART_TYPE_SCATTER": "scatter",
                        "CHART_TYPE_PIE": "pie",
                        "CHART_TYPE_RADAR": "radar",
                        "CHART_TYPE_MAP": "map",
                        "CHART_TYPE_K": "k",
                        "CHART_TYPE_ISLAND": "island",
                        "CHART_TYPE_FORCE": "force",
                        "CHART_TYPE_CHORD": "chord",
                        "COMPONENT_TYPE_TITLE": "title",
                        "COMPONENT_TYPE_LEGEND": "legend",
                        "COMPONENT_TYPE_DATARANGE": "dataRange",
                        "COMPONENT_TYPE_DATAVIEW": "dataView",
                        "COMPONENT_TYPE_DATAZOOM": "dataZoom",
                        "COMPONENT_TYPE_TOOLBOX": "toolbox",
                        "COMPONENT_TYPE_TOOLTIP": "tooltip",
                        "COMPONENT_TYPE_GRID": "grid",
                        "COMPONENT_TYPE_AXIS": "axis",
                        "COMPONENT_TYPE_POLAR": "polar",
                        "COMPONENT_TYPE_X_AXIS": "xAxis",
                        "COMPONENT_TYPE_Y_AXIS": "yAxis",
                        "COMPONENT_TYPE_AXIS_CATEGORY": "categoryAxis",
                        "COMPONENT_TYPE_AXIS_VALUE": "valueAxis",
                        "color": ["#ff7f50", "#87cefa", "#da70d6", "#32cd32", "#6495ed", "#ff69b4", "#ba55d3", "#cd5c5c", "#ffa500", "#40e0d0", "#1e90ff", "#ff6347", "#7b68ee", "#00fa9a", "#ffd700", "#6699FF", "#ff6666", "#3cb371", "#b8860b", "#30e0e0"],
                        "title": {
                            "text": "",
                            "subtext": "",
                            "x": "left",
                            "y": "top",
                            "backgroundColor": "rgba(0,0,0,0)",
                            "borderColor": "#ccc",
                            "borderWidth": 0,
                            "padding": 5,
                            "itemGap": 10,
                            "textStyle": {"fontSize": 18, "fontWeight": "bolder", "color": "#333"},
                            "subtextStyle": {"color": "#aaa"}
                        },
                        "legend": {
                            "orient": "horizontal",
                            "x": "center",
                            "y": "top",
                            "backgroundColor": "rgba(0,0,0,0)",
                            "borderColor": "#ccc",
                            "borderWidth": 0,
                            "padding": 5,
                            "itemGap": 10,
                            "itemWidth": 20,
                            "itemHeight": 14,
                            "textStyle": {"color": "#333"},
                            "selectedMode": true
                        },
                        "dataRange": {
                            "orient": "vertical",
                            "x": "left",
                            "y": "bottom",
                            "backgroundColor": "rgba(0,0,0,0)",
                            "borderColor": "#ccc",
                            "borderWidth": 0,
                            "padding": 5,
                            "itemGap": 10,
                            "itemWidth": 20,
                            "itemHeight": 14,
                            "precision": 0,
                            "splitNumber": 5,
                            "calculable": false,
                            "realtime": true,
                            "color": ["#006edd", "#e0ffff"],
                            "textStyle": {"color": "#333"}
                        },
                        "toolbox": {
                            "show": false,
                            "orient": "horizontal",
                            "x": "right",
                            "y": "top",
                            "color": ["#1e90ff", "#22bb22", "#4b0082", "#d2691e"],
                            "backgroundColor": "rgba(0,0,0,0)",
                            "borderColor": "#ccc",
                            "borderWidth": 0,
                            "padding": 5,
                            "itemGap": 10,
                            "itemSize": 16,
                            "showTitle": true,
                            "feature": {
                                "mark": {
                                    "show": false,
                                    "title": {"mark": "辅助线开关", "markUndo": "删除辅助线", "markClear": "清空辅助线"},
                                    "lineStyle": {"width": 1, "color": "#1e90ff", "type": "dashed"}
                                },
                                "dataZoom": {"show": false, "title": {"dataZoom": "区域缩放", "dataZoomReset": "区域缩放后退"}},
                                "dataView": {
                                    "show": false,
                                    "title": "数据视图",
                                    "readOnly": false,
                                    "lang": ["Data View", "close", "refresh"]
                                },
                                "magicType": {
                                    "show": false,
                                    "title": {"line": "折线图切换", "bar": "柱形图切换", "stack": "堆叠", "tiled": "平铺"},
                                    "type": []
                                },
                                "restore": {"show": false, "title": "还原"},
                                "saveAsImage": {"show": false, "title": "保存为图片", "type": "png", "lang": ["点击保存"]}
                            }
                        },
                        "tooltip": {
                            "show": true,
                            "showContent": true,
                            "trigger": "item",
                            "islandFormatter": "{a} <br/>{b} : {c}",
                            "showDelay": 20,
                            "hideDelay": 100,
                            "transitionDuration": 0.4,
                            "backgroundColor": "rgba(0,0,0,0.7)",
                            "borderColor": "#333",
                            "borderRadius": 4,
                            "borderWidth": 0,
                            "padding": 5,
                            "axisPointer": {
                                "type": "line",
                                "lineStyle": {"color": "#48b", "width": 2, "type": "solid"},
                                "areaStyle": {"size": "auto", "color": "rgba(150,150,150,0.3)"}
                            },
                            "textStyle": {"color": "#fff"}
                        },
                        "dataZoom": {
                            "show": false,
                            "orient": "horizontal",
                            "backgroundColor": "rgba(0,0,0,0)",
                            "dataBackgroundColor": "#eee",
                            "fillerColor": "rgba(144,197,237,0.2)",
                            "handleColor": "rgba(70,130,180,0.8)",
                            "realtime": true
                        },
                        "grid": {
                            "x": 80,
                            "y": 60,
                            "x2": 80,
                            "y2": 60,
                            "backgroundColor": "rgba(0,0,0,0)",
                            "borderWidth": 1,
                            "borderColor": "#ccc"
                        },
                        "categoryAxis": {
                            "position": "bottom",
                            "name": "",
                            "nameLocation": "end",
                            "nameTextStyle": {},
                            "boundaryGap": true,
                            "axisLine": {"show": true, "lineStyle": {"color": "#48b", "width": 2, "type": "solid"}},
                            "axisTick": {
                                "show": true,
                                "interval": "auto",
                                "inside": false,
                                "length": 5,
                                "lineStyle": {"color": "#333", "width": 1}
                            },
                            "axisLabel": {
                                "show": true,
                                "interval": "auto",
                                "rotate": 0,
                                "margin": 8,
                                "textStyle": {"color": "#333"}
                            },
                            "splitLine": {"show": true, "lineStyle": {"color": ["#ccc"], "width": 1, "type": "solid"}},
                            "splitArea": {
                                "show": false,
                                "areaStyle": {"color": ["rgba(250,250,250,0.3)", "rgba(200,200,200,0.3)"]}
                            }
                        },
                        "valueAxis": {
                            "position": "left",
                            "name": "",
                            "nameLocation": "end",
                            "nameTextStyle": {},
                            "boundaryGap": [0, 0],
                            "precision": 0,
                            "power": 100,
                            "splitNumber": 5,
                            "axisLine": {"show": true, "lineStyle": {"color": "#48b", "width": 2, "type": "solid"}},
                            "axisTick": {
                                "show": false,
                                "inside": false,
                                "length": 5,
                                "lineStyle": {"color": "#333", "width": 1}
                            },
                            "axisLabel": {"show": true, "rotate": 0, "margin": 8, "textStyle": {"color": "#333"}},
                            "splitLine": {"show": true, "lineStyle": {"color": ["#ccc"], "width": 1, "type": "solid"}},
                            "splitArea": {
                                "show": false,
                                "areaStyle": {"color": ["rgba(250,250,250,0.3)", "rgba(200,200,200,0.3)"]}
                            }
                        },
                        "polar": {
                            "center": ["50%", "50%"],
                            "radius": "75%",
                            "startAngle": 90,
                            "splitNumber": 5,
                            "name": {"show": true, "textStyle": {"color": "#333"}},
                            "axisLine": {"show": true, "lineStyle": {"color": "#ccc", "width": 1, "type": "solid"}},
                            "axisLabel": {"show": false, "textStyle": {"color": "#333"}},
                            "splitArea": {
                                "show": true,
                                "areaStyle": {"color": ["rgba(250,250,250,0.3)", "rgba(200,200,200,0.3)"]}
                            },
                            "splitLine": {"show": true, "lineStyle": {"width": 1, "color": "#ccc"}}
                        },
                        "bar": {
                            "xAxisIndex": 0,
                            "yAxisIndex": 0,
                            "barMinHeight": 0,
                            "barGap": "30%",
                            "barCategoryGap": "20%",
                            "itemStyle": {
                                "normal": {
                                    "borderColor": "#fff",
                                    "borderRadius": 0,
                                    "borderWidth": 0,
                                    "label": {"show": false}
                                },
                                "emphasis": {
                                    "borderColor": "#fff",
                                    "borderRadius": 0,
                                    "borderWidth": 0,
                                    "label": {"show": false}
                                }
                            }
                        },
                        "line": {
                            "xAxisIndex": 0,
                            "yAxisIndex": 0,
                            "itemStyle": {
                                "normal": {
                                    "label": {"show": false},
                                    "lineStyle": {
                                        "width": 2,
                                        "type": "solid",
                                        "shadowColor": "rgba(0,0,0,0)",
                                        "shadowBlur": 0,
                                        "shadowOffsetX": 0,
                                        "shadowOffsetY": 0
                                    }
                                }, "emphasis": {"label": {"show": false}}
                            },
                            "symbolSize": 2,
                            "showAllSymbol": false
                        },
                        "k": {
                            "xAxisIndex": 0,
                            "yAxisIndex": 0,
                            "itemStyle": {
                                "normal": {
                                    "color": "#fff",
                                    "color0": "#00aa11",
                                    "lineStyle": {"width": 1, "color": "#ff3200", "color0": "#00aa11"}
                                }, "emphasis": {}
                            }
                        },
                        "scatter": {
                            "xAxisIndex": 0,
                            "yAxisIndex": 0,
                            "symbolSize": 4,
                            "large": false,
                            "largeThreshold": 2000,
                            "itemStyle": {"normal": {"label": {"show": false}}, "emphasis": {"label": {"show": false}}}
                        },
                        "radar": {
                            "polarIndex": 0,
                            "itemStyle": {
                                "normal": {
                                    "label": {"show": false},
                                    "lineStyle": {"width": 2, "type": "solid"}
                                }, "emphasis": {"label": {"show": false}}
                            },
                            "symbolSize": 2
                        },
                        "pie": {
                            "center": ["50%", "50%"],
                            "radius": [0, "75%"],
                            "clockWise": true,
                            "startAngle": 90,
                            "minAngle": 0,
                            "selectedOffset": 10,
                            "itemStyle": {
                                "normal": {
                                    "borderColor": "#fff",
                                    "borderWidth": 1,
                                    "label": {"show": true, "position": "outer"},
                                    "labelLine": {
                                        "show": true,
                                        "length": 20,
                                        "lineStyle": {"width": 1, "type": "solid"}
                                    }
                                },
                                "emphasis": {
                                    "borderColor": "rgba(0,0,0,0)",
                                    "borderWidth": 1,
                                    "label": {"show": false},
                                    "labelLine": {
                                        "show": false,
                                        "length": 20,
                                        "lineStyle": {"width": 1, "type": "solid"}
                                    }
                                }
                            }
                        },
                        "map": {
                            "mapType": "china",
                            "mapLocation": {"x": "center", "y": "center"},
                            "mapValuePrecision": 0,
                            "showLegendSymbol": true,
                            "hoverable": true,
                            "itemStyle": {
                                "normal": {
                                    "borderColor": "#fff",
                                    "borderWidth": 1,
                                    "areaStyle": {"color": "#ccc"},
                                    "label": {"show": false, "textStyle": {"color": "rgb(139,69,19)"}}
                                },
                                "emphasis": {
                                    "borderColor": "rgba(0,0,0,0)",
                                    "borderWidth": 1,
                                    "areaStyle": {"color": "rgba(255,215,0,0.8)"},
                                    "label": {"show": false, "textStyle": {"color": "rgb(100,0,0)"}}
                                }
                            }
                        },
                        "force": {
                            "minRadius": 10,
                            "maxRadius": 20,
                            "density": 1,
                            "attractiveness": 1,
                            "initSize": 300,
                            "centripetal": 1,
                            "coolDown": 0.99,
                            "categories": [],
                            "itemStyle": {
                                "normal": {
                                    "label": {"show": false},
                                    "nodeStyle": {"brushType": "both", "color": "#f08c2e", "strokeColor": "#5182ab"},
                                    "linkStyle": {"strokeColor": "#5182ab"}
                                }, "emphasis": {"label": {"show": false}, "nodeStyle": {}, "linkStyle": {}}
                            }
                        },
                        "chord": {
                            "radius": ["65%", "75%"],
                            "center": ["50%", "50%"],
                            "padding": 2,
                            "sort": "none",
                            "sortSub": "none",
                            "startAngle": 90,
                            "clockWise": false,
                            "showScale": false,
                            "showScaleText": false,
                            "itemStyle": {
                                "normal": {
                                    "label": {"show": true},
                                    "lineStyle": {"width": 0, "color": "#000"},
                                    "chordStyle": {"lineStyle": {"width": 1, "color": "#666"}}
                                },
                                "emphasis": {
                                    "lineStyle": {"width": 0, "color": "#000"},
                                    "chordStyle": {"lineStyle": {"width": 2, "color": "#333"}}
                                }
                            },
                            "matrix": []
                        },
                        "island": {"r": 15, "calculateStep": 0.1},
                        "markPoint": {
                            "symbol": "pin",
                            "symbolSize": 10,
                            "effect": {"show": false, "period": 15, "scaleSize": 2},
                            "itemStyle": {
                                "normal": {"borderWidth": 2, "label": {"show": true, "position": "inside"}},
                                "emphasis": {"label": {"show": true}}
                            }
                        },
                        "markLine": {
                            "symbol": ["circle", "arrow"],
                            "symbolSize": [2, 4],
                            "effect": {"show": false, "period": 15, "scaleSize": 2},
                            "itemStyle": {
                                "normal": {
                                    "borderWidth": 1.5,
                                    "label": {"show": true, "position": "end"},
                                    "lineStyle": {
                                        "type": "dashed",
                                        "shadowColor": "rgba(0,0,0,0)",
                                        "shadowBlur": 0,
                                        "shadowOffsetX": 0,
                                        "shadowOffsetY": 0
                                    }
                                }, "emphasis": {"label": {"show": false}, "lineStyle": {}}
                            }
                        },
                        "textStyle": {
                            "decoration": "none",
                            "fontFamily": "Arial, Verdana, sans-serif",
                            "fontFamily2": "微软雅黑",
                            "fontSize": 12,
                            "fontStyle": "normal",
                            "fontWeight": "normal"
                        },
                        "EVENT": {
                            "REFRESH": "refresh",
                            "RESTORE": "restore",
                            "RESIZE": "resize",
                            "CLICK": "click",
                            "HOVER": "hover",
                            "DATA_CHANGED": "dataChanged",
                            "DATA_ZOOM": "dataZoom",
                            "DATA_RANGE": "dataRange",
                            "LEGEND_SELECTED": "legendSelected",
                            "MAP_SELECTED": "mapSelected",
                            "PIE_SELECTED": "pieSelected",
                            "MAGIC_TYPE_CHANGED": "magicTypeChanged",
                            "DATA_VIEW_CHANGED": "dataViewChanged",
                            "MAP_ROAM": "mapRoam",
                            "TOOLTIP_HOVER": "tooltipHover",
                            "TOOLTIP_IN_GRID": "tooltipInGrid",
                            "TOOLTIP_OUT_GRID": "tooltipOutGrid"
                        },
                        "DRAG_ENABLE_TIME": 150,
                        "symbolList": ["circle", "rectangle", "triangle", "diamond", "emptyCircle", "emptyRectangle", "emptyTriangle", "emptyDiamond"],
                        "loadingText": "Loading...",
                        "calculable": false,
                        "calculableColor": "rgba(255,165,0,0.6)",
                        "calculableHolderColor": "#ccc",
                        "nameConnector": " & ",
                        "valueConnector": " : ",
                        "animation": true,
                        "addDataAnimation": true,
                        "animationThreshold": 2500,
                        "animationDuration": 2000,
                        "animationEasing": "ExponentialOut"
                    };
                    myChart.on(ecConfig.EVENT.MAP_SELECTED, function (param) {
                        var selected = param.selected;
                        var mapSeries = option.series[0];
                        var data = [];
                        var legendData = [];
                        var name;
                        for (var p = 0, len = mapSeries.data.length; p < len; p++) {
                            name = mapSeries.data[p].name;
                            mapSeries.data[p].selected = selected[name];
                            if (selected[name]) {
                                data.push({
                                    name: name,
                                    value: mapSeries.data[p].value
                                });
                                legendData.push(name);
                            }
                        }
                        option.legend.data = legendData;
                        option.series[1].data = data;
                        myChart.setOption(option, true);
                    })


                    myChart.setOption(option);
                }
                function echartsY3Init() {
                    var myChart = echarts.init(document.getElementById("charts333"));
                    var option = {
                        // backgroundColor: '#1b1b1b',
                        color: ['gold', 'aqua', 'lime'],
                        title: {
                            text: '销售区域分布',
                            subtext: '数据纯属虚构',
                            x: 'center',
                            textStyle: {
                                color: 'blue'
                            }
                        },
                        tooltip: {
                            trigger: 'item',
                            formatter: function (v) {
                                return v[1].replace(':', ' > ');
                            }
                        },
                        legend: {
                            orient: 'vertical',
                            x: 'left',
                            data: ['茅台', '五粮液', '泸州老窖'],
                            selectedMode: 'single',
                            selected: {
                                '五粮液': false,
                                '泸州老窖': false
                            },
                            textStyle: {
                                color: 'blue'
                            }
                        },
                        toolbox: {
                            show: true,
                            orient: 'vertical',
                            x: 'right',
                            y: 'center',
                            feature: {
                                mark: {show: true},
                                dataView: {show: true, readOnly: false},
                                restore: {show: true},
                                saveAsImage: {show: true}
                            }
                        },
                        dataRange: {
                            min: 0,
                            max: 100,
                            calculable: true,
                            color: ['red', 'orange', 'yellow', 'lightgreen'],
                            textStyle: {
                                color: '#fff'
                            }
                        },
                        series: [
                            {
                                name: '全国',
                                type: 'map',
                                roam: true,
                                hoverable: false,
                                mapType: 'china',
                                itemStyle: {
                                    normal: {
                                        borderColor: 'rgba(100,149,237,1)',
                                        borderWidth: 0.5,
                                        areaStyle: {
                                            color: '#1b1b1b'
                                        }
                                    }
                                },
                                data: [],
                                markLine: {
                                    smooth: true,
                                    symbol: ['none', 'circle'],
                                    symbolSize: 1,
                                    itemStyle: {
                                        normal: {
                                            color: '#fff',
                                            borderWidth: 1,
                                            borderColor: 'rgba(30,144,255,0.5)'
                                        }
                                    },
                                    data: [
                                        [{name: '北京'}, {name: '包头'}],
                                        [{name: '北京'}, {name: '北海'}],
                                        [{name: '北京'}, {name: '广州'}],
                                        [{name: '北京'}, {name: '郑州'}],
                                        [{name: '北京'}, {name: '长春'}],
                                        [{name: '北京'}, {name: '长治'}],
                                        [{name: '北京'}, {name: '重庆'}],
                                        [{name: '北京'}, {name: '长沙'}],
                                        [{name: '北京'}, {name: '成都'}],
                                        [{name: '北京'}, {name: '常州'}],
                                        [{name: '北京'}, {name: '丹东'}],
                                        [{name: '北京'}, {name: '大连'}],
                                        [{name: '北京'}, {name: '东营'}],
                                        [{name: '北京'}, {name: '延安'}],
                                        [{name: '北京'}, {name: '福州'}],
                                        [{name: '北京'}, {name: '海口'}],
                                        [{name: '北京'}, {name: '呼和浩特'}],
                                        [{name: '北京'}, {name: '合肥'}],
                                        [{name: '北京'}, {name: '杭州'}],
                                        [{name: '北京'}, {name: '哈尔滨'}],
                                        [{name: '北京'}, {name: '舟山'}],
                                        [{name: '北京'}, {name: '银川'}],
                                        [{name: '北京'}, {name: '衢州'}],
                                        [{name: '北京'}, {name: '南昌'}],
                                        [{name: '北京'}, {name: '昆明'}],
                                        [{name: '北京'}, {name: '贵阳'}],
                                        [{name: '北京'}, {name: '兰州'}],
                                        [{name: '北京'}, {name: '拉萨'}],
                                        [{name: '北京'}, {name: '连云港'}],
                                        [{name: '北京'}, {name: '临沂'}],
                                        [{name: '北京'}, {name: '柳州'}],
                                        [{name: '北京'}, {name: '宁波'}],
                                        [{name: '北京'}, {name: '南京'}],
                                        [{name: '北京'}, {name: '南宁'}],
                                        [{name: '北京'}, {name: '南通'}],
                                        [{name: '北京'}, {name: '上海'}],
                                        [{name: '北京'}, {name: '沈阳'}],
                                        [{name: '北京'}, {name: '西安'}],
                                        [{name: '北京'}, {name: '汕头'}],
                                        [{name: '北京'}, {name: '深圳'}],
                                        [{name: '北京'}, {name: '青岛'}],
                                        [{name: '北京'}, {name: '济南'}],
                                        [{name: '北京'}, {name: '太原'}],
                                        [{name: '北京'}, {name: '乌鲁木齐'}],
                                        [{name: '北京'}, {name: '潍坊'}],
                                        [{name: '北京'}, {name: '威海'}],
                                        [{name: '北京'}, {name: '温州'}],
                                        [{name: '北京'}, {name: '武汉'}],
                                        [{name: '北京'}, {name: '无锡'}],
                                        [{name: '北京'}, {name: '厦门'}],
                                        [{name: '北京'}, {name: '西宁'}],
                                        [{name: '北京'}, {name: '徐州'}],
                                        [{name: '北京'}, {name: '烟台'}],
                                        [{name: '北京'}, {name: '盐城'}],
                                        [{name: '北京'}, {name: '珠海'}],
                                        [{name: '上海'}, {name: '包头'}],
                                        [{name: '上海'}, {name: '北海'}],
                                        [{name: '上海'}, {name: '广州'}],
                                        [{name: '上海'}, {name: '郑州'}],
                                        [{name: '上海'}, {name: '长春'}],
                                        [{name: '上海'}, {name: '重庆'}],
                                        [{name: '上海'}, {name: '长沙'}],
                                        [{name: '上海'}, {name: '成都'}],
                                        [{name: '上海'}, {name: '丹东'}],
                                        [{name: '上海'}, {name: '大连'}],
                                        [{name: '上海'}, {name: '福州'}],
                                        [{name: '上海'}, {name: '海口'}],
                                        [{name: '上海'}, {name: '呼和浩特'}],
                                        [{name: '上海'}, {name: '合肥'}],
                                        [{name: '上海'}, {name: '哈尔滨'}],
                                        [{name: '上海'}, {name: '舟山'}],
                                        [{name: '上海'}, {name: '银川'}],
                                        [{name: '上海'}, {name: '南昌'}],
                                        [{name: '上海'}, {name: '昆明'}],
                                        [{name: '上海'}, {name: '贵阳'}],
                                        [{name: '上海'}, {name: '兰州'}],
                                        [{name: '上海'}, {name: '拉萨'}],
                                        [{name: '上海'}, {name: '连云港'}],
                                        [{name: '上海'}, {name: '临沂'}],
                                        [{name: '上海'}, {name: '柳州'}],
                                        [{name: '上海'}, {name: '宁波'}],
                                        [{name: '上海'}, {name: '南宁'}],
                                        [{name: '上海'}, {name: '北京'}],
                                        [{name: '上海'}, {name: '沈阳'}],
                                        [{name: '上海'}, {name: '秦皇岛'}],
                                        [{name: '上海'}, {name: '西安'}],
                                        [{name: '上海'}, {name: '石家庄'}],
                                        [{name: '上海'}, {name: '汕头'}],
                                        [{name: '上海'}, {name: '深圳'}],
                                        [{name: '上海'}, {name: '青岛'}],
                                        [{name: '上海'}, {name: '济南'}],
                                        [{name: '上海'}, {name: '天津'}],
                                        [{name: '上海'}, {name: '太原'}],
                                        [{name: '上海'}, {name: '乌鲁木齐'}],
                                        [{name: '上海'}, {name: '潍坊'}],
                                        [{name: '上海'}, {name: '威海'}],
                                        [{name: '上海'}, {name: '温州'}],
                                        [{name: '上海'}, {name: '武汉'}],
                                        [{name: '上海'}, {name: '厦门'}],
                                        [{name: '上海'}, {name: '西宁'}],
                                        [{name: '上海'}, {name: '徐州'}],
                                        [{name: '上海'}, {name: '烟台'}],
                                        [{name: '上海'}, {name: '珠海'}],
                                        [{name: '广州'}, {name: '北海'}],
                                        [{name: '广州'}, {name: '郑州'}],
                                        [{name: '广州'}, {name: '长春'}],
                                        [{name: '广州'}, {name: '重庆'}],
                                        [{name: '广州'}, {name: '长沙'}],
                                        [{name: '广州'}, {name: '成都'}],
                                        [{name: '广州'}, {name: '常州'}],
                                        [{name: '广州'}, {name: '大连'}],
                                        [{name: '广州'}, {name: '福州'}],
                                        [{name: '广州'}, {name: '海口'}],
                                        [{name: '广州'}, {name: '呼和浩特'}],
                                        [{name: '广州'}, {name: '合肥'}],
                                        [{name: '广州'}, {name: '杭州'}],
                                        [{name: '广州'}, {name: '哈尔滨'}],
                                        [{name: '广州'}, {name: '舟山'}],
                                        [{name: '广州'}, {name: '银川'}],
                                        [{name: '广州'}, {name: '南昌'}],
                                        [{name: '广州'}, {name: '昆明'}],
                                        [{name: '广州'}, {name: '贵阳'}],
                                        [{name: '广州'}, {name: '兰州'}],
                                        [{name: '广州'}, {name: '拉萨'}],
                                        [{name: '广州'}, {name: '连云港'}],
                                        [{name: '广州'}, {name: '临沂'}],
                                        [{name: '广州'}, {name: '柳州'}],
                                        [{name: '广州'}, {name: '宁波'}],
                                        [{name: '广州'}, {name: '南京'}],
                                        [{name: '广州'}, {name: '南宁'}],
                                        [{name: '广州'}, {name: '南通'}],
                                        [{name: '广州'}, {name: '北京'}],
                                        [{name: '广州'}, {name: '上海'}],
                                        [{name: '广州'}, {name: '沈阳'}],
                                        [{name: '广州'}, {name: '西安'}],
                                        [{name: '广州'}, {name: '石家庄'}],
                                        [{name: '广州'}, {name: '汕头'}],
                                        [{name: '广州'}, {name: '青岛'}],
                                        [{name: '广州'}, {name: '济南'}],
                                        [{name: '广州'}, {name: '天津'}],
                                        [{name: '广州'}, {name: '太原'}],
                                        [{name: '广州'}, {name: '乌鲁木齐'}],
                                        [{name: '广州'}, {name: '温州'}],
                                        [{name: '广州'}, {name: '武汉'}],
                                        [{name: '广州'}, {name: '无锡'}],
                                        [{name: '广州'}, {name: '厦门'}],
                                        [{name: '广州'}, {name: '西宁'}],
                                        [{name: '广州'}, {name: '徐州'}],
                                        [{name: '广州'}, {name: '烟台'}],
                                        [{name: '广州'}, {name: '盐城'}]
                                    ],
                                },
                                geoCoord: {
                                    '上海': [121.4648, 31.2891],
                                    '东莞': [113.8953, 22.901],
                                    '东营': [118.7073, 37.5513],
                                    '中山': [113.4229, 22.478],
                                    '临汾': [111.4783, 36.1615],
                                    '临沂': [118.3118, 35.2936],
                                    '丹东': [124.541, 40.4242],
                                    '丽水': [119.5642, 28.1854],
                                    '乌鲁木齐': [87.9236, 43.5883],
                                    '佛山': [112.8955, 23.1097],
                                    '保定': [115.0488, 39.0948],
                                    '兰州': [103.5901, 36.3043],
                                    '包头': [110.3467, 41.4899],
                                    '北京': [116.4551, 40.2539],
                                    '北海': [109.314, 21.6211],
                                    '南京': [118.8062, 31.9208],
                                    '南宁': [108.479, 23.1152],
                                    '南昌': [116.0046, 28.6633],
                                    '南通': [121.1023, 32.1625],
                                    '厦门': [118.1689, 24.6478],
                                    '台州': [121.1353, 28.6688],
                                    '合肥': [117.29, 32.0581],
                                    '呼和浩特': [111.4124, 40.4901],
                                    '咸阳': [108.4131, 34.8706],
                                    '哈尔滨': [127.9688, 45.368],
                                    '唐山': [118.4766, 39.6826],
                                    '嘉兴': [120.9155, 30.6354],
                                    '大同': [113.7854, 39.8035],
                                    '大连': [122.2229, 39.4409],
                                    '天津': [117.4219, 39.4189],
                                    '太原': [112.3352, 37.9413],
                                    '威海': [121.9482, 37.1393],
                                    '宁波': [121.5967, 29.6466],
                                    '宝鸡': [107.1826, 34.3433],
                                    '宿迁': [118.5535, 33.7775],
                                    '常州': [119.4543, 31.5582],
                                    '广州': [113.5107, 23.2196],
                                    '廊坊': [116.521, 39.0509],
                                    '延安': [109.1052, 36.4252],
                                    '张家口': [115.1477, 40.8527],
                                    '徐州': [117.5208, 34.3268],
                                    '德州': [116.6858, 37.2107],
                                    '惠州': [114.6204, 23.1647],
                                    '成都': [103.9526, 30.7617],
                                    '扬州': [119.4653, 32.8162],
                                    '承德': [117.5757, 41.4075],
                                    '拉萨': [91.1865, 30.1465],
                                    '无锡': [120.3442, 31.5527],
                                    '日照': [119.2786, 35.5023],
                                    '昆明': [102.9199, 25.4663],
                                    '杭州': [119.5313, 29.8773],
                                    '枣庄': [117.323, 34.8926],
                                    '柳州': [109.3799, 24.9774],
                                    '株洲': [113.5327, 27.0319],
                                    '武汉': [114.3896, 30.6628],
                                    '汕头': [117.1692, 23.3405],
                                    '江门': [112.6318, 22.1484],
                                    '沈阳': [123.1238, 42.1216],
                                    '沧州': [116.8286, 38.2104],
                                    '河源': [114.917, 23.9722],
                                    '泉州': [118.3228, 25.1147],
                                    '泰安': [117.0264, 36.0516],
                                    '泰州': [120.0586, 32.5525],
                                    '济南': [117.1582, 36.8701],
                                    '济宁': [116.8286, 35.3375],
                                    '海口': [110.3893, 19.8516],
                                    '淄博': [118.0371, 36.6064],
                                    '淮安': [118.927, 33.4039],
                                    '深圳': [114.5435, 22.5439],
                                    '清远': [112.9175, 24.3292],
                                    '温州': [120.498, 27.8119],
                                    '渭南': [109.7864, 35.0299],
                                    '湖州': [119.8608, 30.7782],
                                    '湘潭': [112.5439, 27.7075],
                                    '滨州': [117.8174, 37.4963],
                                    '潍坊': [119.0918, 36.524],
                                    '烟台': [120.7397, 37.5128],
                                    '玉溪': [101.9312, 23.8898],
                                    '珠海': [113.7305, 22.1155],
                                    '盐城': [120.2234, 33.5577],
                                    '盘锦': [121.9482, 41.0449],
                                    '石家庄': [114.4995, 38.1006],
                                    '福州': [119.4543, 25.9222],
                                    '秦皇岛': [119.2126, 40.0232],
                                    '绍兴': [120.564, 29.7565],
                                    '聊城': [115.9167, 36.4032],
                                    '肇庆': [112.1265, 23.5822],
                                    '舟山': [122.2559, 30.2234],
                                    '苏州': [120.6519, 31.3989],
                                    '莱芜': [117.6526, 36.2714],
                                    '菏泽': [115.6201, 35.2057],
                                    '营口': [122.4316, 40.4297],
                                    '葫芦岛': [120.1575, 40.578],
                                    '衡水': [115.8838, 37.7161],
                                    '衢州': [118.6853, 28.8666],
                                    '西宁': [101.4038, 36.8207],
                                    '西安': [109.1162, 34.2004],
                                    '贵阳': [106.6992, 26.7682],
                                    '连云港': [119.1248, 34.552],
                                    '邢台': [114.8071, 37.2821],
                                    '邯郸': [114.4775, 36.535],
                                    '郑州': [113.4668, 34.6234],
                                    '鄂尔多斯': [108.9734, 39.2487],
                                    '重庆': [107.7539, 30.1904],
                                    '金华': [120.0037, 29.1028],
                                    '铜川': [109.0393, 35.1947],
                                    '银川': [106.3586, 38.1775],
                                    '镇江': [119.4763, 31.9702],
                                    '长春': [125.8154, 44.2584],
                                    '长沙': [113.0823, 28.2568],
                                    '长治': [112.8625, 36.4746],
                                    '阳泉': [113.4778, 38.0951],
                                    '青岛': [120.4651, 36.3373],
                                    '韶关': [113.7964, 24.7028]
                                }
                            },
                            {
                                name: '茅台',
                                type: 'map',
                                mapType: 'china',
                                data: [],
                                markLine: {
                                    smooth: true,
                                    effect: {
                                        show: true,
                                        size: 3,
                                        shadowColor: 'yellow'
                                    },
                                    itemStyle: {
                                        normal: {
                                            borderWidth: 1
                                        }
                                    },
                                    data: [
                                        [{name: '北京'}, {name: '上海', value: 95}],
                                        [{name: '北京'}, {name: '广州', value: 90}],
                                        [{name: '北京'}, {name: '大连', value: 80}],
                                        [{name: '北京'}, {name: '南宁', value: 70}],
                                        [{name: '北京'}, {name: '南昌', value: 60}],
                                        [{name: '北京'}, {name: '拉萨', value: 50}],
                                        [{name: '北京'}, {name: '长春', value: 40}],
                                        [{name: '北京'}, {name: '包头', value: 30}],
                                        [{name: '北京'}, {name: '重庆', value: 20}],
                                        [{name: '北京'}, {name: '常州', value: 10}]
                                    ]
                                },
                                markPoint: {
                                    symbol: 'emptyCircle',
                                    symbolSize: function (v) {
                                        return 10 + v / 10
                                    },
                                    effect: {
                                        show: true,
                                        shadowBlur: 0
                                    },
                                    itemStyle: {
                                        normal: {
                                            label: {show: false}
                                        }
                                    },
                                    data: [
                                        {name: '上海', value: 95},
                                        {name: '广州', value: 90},
                                        {name: '大连', value: 80},
                                        {name: '南宁', value: 70},
                                        {name: '南昌', value: 60},
                                        {name: '拉萨', value: 50},
                                        {name: '长春', value: 40},
                                        {name: '包头', value: 30},
                                        {name: '重庆', value: 20},
                                        {name: '常州', value: 10}
                                    ]
                                }
                            },
                            {
                                name: '五粮液',
                                type: 'map',
                                mapType: 'china',
                                data: [],
                                markLine: {
                                    smooth: true,
                                    effect: {
                                        show: true,
                                        size: 3,
                                        shadowColor: 'aqua'
                                    },
                                    itemStyle: {
                                        normal: {
                                            borderWidth: 1
                                        }
                                    },
                                    data: [
                                        [{name: '上海'}, {name: '包头', value: 95}],
                                        [{name: '上海'}, {name: '昆明', value: 90}],
                                        [{name: '上海'}, {name: '广州', value: 80}],
                                        [{name: '上海'}, {name: '郑州', value: 70}],
                                        [{name: '上海'}, {name: '长春', value: 60}],
                                        [{name: '上海'}, {name: '重庆', value: 50}],
                                        [{name: '上海'}, {name: '长沙', value: 40}],
                                        [{name: '上海'}, {name: '北京', value: 30}],
                                        [{name: '上海'}, {name: '丹东', value: 20}],
                                        [{name: '上海'}, {name: '大连', value: 10}]
                                    ]
                                },
                                markPoint: {
                                    symbol: 'emptyCircle',
                                    symbolSize: function (v) {
                                        return 10 + v / 10
                                    },
                                    effect: {
                                        show: true,
                                        shadowBlur: 0
                                    },
                                    itemStyle: {
                                        normal: {
                                            label: {show: false}
                                        }
                                    },
                                    data: [
                                        {name: '包头', value: 95},
                                        {name: '昆明', value: 90},
                                        {name: '广州', value: 80},
                                        {name: '郑州', value: 70},
                                        {name: '长春', value: 60},
                                        {name: '重庆', value: 50},
                                        {name: '长沙', value: 40},
                                        {name: '北京', value: 30},
                                        {name: '丹东', value: 20},
                                        {name: '大连', value: 10}
                                    ]
                                }
                            },
                            {
                                name: '泸州老窖',
                                type: 'map',
                                mapType: 'china',
                                data: [],
                                markLine: {
                                    smooth: true,
                                    effect: {
                                        show: true,
                                        size: 3,
                                        shadowColor: 'lime'
                                    },
                                    itemStyle: {
                                        normal: {
                                            borderWidth: 1
                                        }
                                    },
                                    data: [
                                        [{name: '广州'}, {name: '福州', value: 95}],
                                        [{name: '广州'}, {name: '太原', value: 90}],
                                        [{name: '广州'}, {name: '长春', value: 80}],
                                        [{name: '广州'}, {name: '重庆', value: 70}],
                                        [{name: '广州'}, {name: '西安', value: 60}],
                                        [{name: '广州'}, {name: '成都', value: 50}],
                                        [{name: '广州'}, {name: '常州', value: 40}],
                                        [{name: '广州'}, {name: '北京', value: 30}],
                                        [{name: '广州'}, {name: '北海', value: 20}],
                                        [{name: '广州'}, {name: '海口', value: 10}]
                                    ]
                                },
                                markPoint: {
                                    symbol: 'emptyCircle',
                                    symbolSize: function (v) {
                                        return 10 + v / 10
                                    },
                                    effect: {
                                        show: true,
                                        shadowBlur: 0
                                    },
                                    itemStyle: {
                                        normal: {
                                            label: {show: false}
                                        }
                                    },
                                    data: [
                                        {name: '福州', value: 95},
                                        {name: '太原', value: 90},
                                        {name: '长春', value: 80},
                                        {name: '重庆', value: 70},
                                        {name: '西安', value: 60},
                                        {name: '成都', value: 50},
                                        {name: '常州', value: 40},
                                        {name: '北京', value: 30},
                                        {name: '北海', value: 20},
                                        {name: '海口', value: 10}
                                    ]
                                }
                            }
                        ]
                    };


                    myChart.setOption(option);
                }
            </script>
        </div>
    </div>
    <div id="page-switcher-end" class="page-switcher">
        <div></div>
        <a href="javascript:void(0);" class="btn btnNext" id="banner_index_next"></a>
    </div>
</div>

</body>
</html>

<!--引入统计图-->
<script type="text/javascript" src="/k/kui/frame/echarts/echarts-plain-map.js"></script>