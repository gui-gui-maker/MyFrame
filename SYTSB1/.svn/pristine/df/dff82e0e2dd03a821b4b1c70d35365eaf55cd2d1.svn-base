<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
    p{
        text-align: center;
        border:1px solid #bcbfcd;
    }
    table{border-collapse:collapse;}
    td{
        border:1px solid black;
    }
</style>
</head>
<script src="/rtbox/app/test/js/jquery.js"></script>
<!-- <link rel="stylesheet" href="/rtbox/app/test/css/jquery-ui.min.css"> 
<script src="/rtbox/app/test/js/jquery-ui.min.js"></script>-->
<script>
    $(function() {
       /*  $( "td" ).resizable({
            start: function (event, ui) {


   
        $( "td" ).draggable({
            start: function (event, ui) {

                console.log("移动开始"+JSON.stringify(ui.position));

            },
            stop: function (event, ui) {

                console.log("移动结束"+JSON.stringify(ui.position));

            }
        });
        $( "p" ).draggable(); */
    });
    function tzsf(){
        $( "td" ).draggable({addClasses:false,});
        $( "td" ).resizable({addClasses:false,});
        $( "p" ).draggable({ addClasses:false});
    }
    function yc(){
        $( "td" ).draggable( "destroy" );
        $( "td" ).resizable( "destroy" );
    }
    function test(obj){
        tzsf();
        //console.log(obj);
     //   var oldText=obj.innerHTML;

      //  obj.innerHTML  = 'pink';

    }
    function getFormContent(){
        yc();
        alert($("#layout2").html());
        var path = "/rtbox/app/test/index1.html";
        var content = $("#layout2").html();
        $.post("/com/rt/page/saveIndexChange.do",{'path':path,'content':content},function(res){
            if(res.success){
                alert("保存成功");
                tzsf();
            }else{
                alert("保存失败");

            }
</script>
<body>
<div id="layout2" style="width: 99.8%">
    <input type="hidden" id="inputFocus" name="inputFocus"/>
    <input type="hidden" id="thisPage" name="thisPage" value="?page=1">
    <div position="center" class="wrapper" style="overflow: auto;width: 100%" align="center">
        <input type="hidden" id="fk_report_id" name="fk_report_id" value="">
        <input type="hidden" id="fk_inspection_info_id" name="fk_inspection_info_id" value="">
      <!--  <button style="background-color: green" align="center"><input type="button" id="test" onclick="test()" value="启   用">  </button>-->
        <!--    <button style="background-color: green" align="center"><input type="button" id="end" onclick="yc()" value="结   束"> </button>-->
          <button style="background-color: green" align="center"><input type="button" id="save" onclick="getFormContent()" value="测试保存"> </button>
          <br/>     <!-- userBodyTop goes here -->
        <div class="document"><p class="dtitle" style="line-height: 125%;"><span>眉山市特种设备监督检验所</span></p>
            <p class="dtitle"  style="line-height: 125%;"><span><span>叉车定期（首次）</span></span><span>检验原始记录</span></p>
            <table id="docx4j_tbl_0">
                <colgroup>
                    <col style="width: 70%;"/>
                    <col style="width: 30%;"/>
                </colgroup>
                <tbody>
                <tr>
                    <td><p style="text-align: right;"><span>记录编号</span></p></td>
                    <td><p><input id="base__report_sn" name="base__report_sn" type="text" ltype="text" value=""
                                  ligerui="{width:179}" readonly="readonly"/></p></td>
                </tr>
                </tbody>
            </table>
            <table id="docx4j_tbl_1">
                <colgroup>
                    <col style="width: 10.01%;"/>
                    <col style="width: 11.27%;"/>
                    <col style="width: 8.8%;"/>
                    <col style="width: 2.92%;"/>
                    <col style="width: 10.05%;"/>
                    <col style="width: 5.75%;"/>
                    <col style="width: 5.23%;"/>
                    <col style="width: 1.46%;"/>
                    <col style="width: 2.04%;"/>
                    <col style="width: 3.71%;"/>
                    <col style="width: 3.36%;"/>
                    <col style="width: 4.58%;"/>
                    <col style="width: 1.76%;"/>
                    <col style="width: 10.89%;"/>
                    <col style="width: 18.16%;"/>
                </colgroup>
                <tbody>
                <tr style="height: 4mm;page-break-inside: avoid;">
                    <td colspan="2"><p><span ondblclick="test(this)"  onclick="yc()" id="tid">使用单位名称</span></p></td>
                    <td colspan="13"><p><input id="base__com_name" name="base__com_name" type="text" ltype="text"
                                               value="" ligerui="{width:507}"/></p></td>
                </tr>
                <tr style="height: 4mm;page-break-inside: avoid;">
                    <td colspan="2"><p><span>使用单位地址</span></p></td>
                    <td colspan="13"><p><input id="base__com_address" name="base__com_address" type="text" ltype="text"
                                               value="" ligerui="{width:507}"/></p></td>
                </tr>
                <tr style="height: 4mm;page-break-inside: avoid;">
                    <td colspan="2"><p><span>使用单位联系人</span></p></td>
                    <td colspan="5"><p><input id="base__company_contact" name="base__company_contact" type="text"
                                              ltype="text" value="" ligerui="{width:205}"/></p></td>
                    <td colspan="5"><p><span>联系电话</span></p></td>
                    <td colspan="3"><p><input id="base__check_tel" name="base__check_tel" type="text" ltype="text"
                                              value="" ligerui="{width:192}"/></p></td>
                </tr>
                <tr style="height: 4mm;page-break-inside: avoid;">
                    <td colspan="2"><p><span>车辆名称</span></p></td>
                    <td colspan="5"><p><input id="base__p_clmc" name="base__p_clmc" type="text" ltype="text" value=""
                                              ligerui="{width:205}"/></p></td>
                    <td colspan="5"><p><span>规格型号</span></p></td>
                    <td colspan="3"><p><input id="base__q_ggxh" name="base__q_ggxh" type="text" ltype="text" value=""
                                              ligerui="{width:192}"/></p></td>
                </tr>
                <tr style="height: 4mm;page-break-inside: avoid;">
                    <td colspan="2"><p><span>制造单位</span></p></td>
                    <td colspan="5"><p><input id="base__make_units_name" name="base__make_units_name" type="text"
                                              ltype="text" value="" ligerui="{width:205}"/></p></td>
                    <td colspan="5"><p><span>产品编号</span></p></td>
                    <td colspan="3"><p><input id="TBL00002" name="TBL00002" type="text" ltype="text" value=""
                                              ligerui="{width:192}"/></p></td>
                </tr>
                <tr style="height: 4mm;page-break-inside: avoid;">
                    <td colspan="2"><p><span>使用登记证号</span></p></td>
                    <td colspan="5"><p><input id="base__registration_num" name="base__registration_num" type="text"
                                              ltype="text" value="" ligerui="{width:205}"/></p></td>
                    <td colspan="5"><p><span>注册代码</span></p></td>
                    <td colspan="3"><p><input id="base__device_registration_code" name="base__device_registration_code" type="text" ltype="text" value=""
                                              ligerui="{width:192}"/></p></td>
                </tr>
                <tr style="height: 4mm;page-break-inside: avoid;">
                    <td colspan="2"><p><span>车辆牌号</span></p></td>
                    <td colspan="5"><p><input id="base__q_clph" name="base__q_clph" type="text" ltype="text" value=""
                                              ligerui="{width:205}"/></p></td>
                    <td colspan="5"><p><span>燃料种类</span></p></td>
                    <td colspan="3"><p><input id="base__fuel_type" name="base__fuel_type" type="text" ltype="text"
                                              value="" ligerui="{width:192}"/></p></td>
                </tr>
                <tr style="height: 4mm;page-break-inside: avoid;">
                    <td colspan="2"><p><span>空车重量</span></p></td>
                    <td colspan="3"><p><input id="base__cp_by1" name="base__cp_by1" type="text" ltype="text" value=""
                                              ligerui="{width:133}"/></p></td>
                    <td colspan="2"><p><span><span>（kg）</span></span></p></td>
                    <td colspan="5"><p><span>门架形式</span></p></td>
                    <td colspan="3"><p><input id="TBL00005" name="TBL00005" type="text" ltype="text" value=""
                                              ligerui="{width:192}"/></p></td>
                </tr>
                <tr style="height: 4mm;page-break-inside: avoid;">
                    <td colspan="2"><p><span>额定载重</span></p></td>
                    <td colspan="3"><p><input id="base__p_edzzl" name="base__p_edzzl" type="text" ltype="text" value=""
                                              ligerui="{width:133}"/></p></td>
                    <td colspan="2"><p><span><span>（kg）</span></span></p></td>
                    <td colspan="2" rowspan="2"><p><span>防爆</span></p></td>
                    <td colspan="3"><p><span>等级</span></p></td>
                    <td colspan="3"><p><input id="base__cp_by6" name="base__cp_by6" type="text" ltype="text" value=""
                                              ligerui="{width:192}"/></p></td>
                </tr>
                <tr style="height: 4mm;page-break-inside: avoid;">
                    <td colspan="2"><p><span>最高时速</span></p></td>
                    <td colspan="3"><p><input id="TBL00008" name="TBL00008" type="text" ltype="text" value=""
                                              ligerui="{width:133}"/></p></td>
                    <td colspan="2"><p><span><span>（Km/h）</span></span></p></td>
                    <td colspan="3"><p><span>使用场所</span></p></td>
                    <td colspan="3"><p><input id="base__use_place_type" name="base__use_place_type" type="text"
                                              ltype="text" value="" ligerui="{width:192}"/></p></td>
                </tr>
                <tr style="height: 4mm;page-break-inside: avoid;">
                    <td colspan="2"><p><span><span>检验依据</span></span></p></td>
                    <td colspan="13"><p>
                        <span><span>场(厂)内专用机动车辆安全技术监察规程（</span><span>TSG N0001</span><span>—</span><span>201</span><span>7）</span></span>
                    </p></td>
                </tr>
                <tr style="height: 9mm;">
                    <td colspan="10"><p><span>现场检验条件要求</span></p></td>
                    <td colspan="5"><p><span>条件确认</span></p></td>
                </tr>
                <tr style="height: 7mm;">
                    <td colspan="10"><p style="text-align: left;"><span><span>露天检验应在无雨情况下进行；</span></span></p></td>
                    <td colspan="5"><p><input id="base__sfltjx" name="base__sfltjx" type="text" ltype="select"
                                              value="" ligerui="{isMultiSelect:true,width:245}"/></p></td>
                </tr>
                <tr style="height: 7mm;">
                    <td colspan="10"><p style="text-align: left;"><span><span>检验现场环境符合相关标准中对检验场地的要求；</span></span></p></td>
                    <td colspan="5"><p><input id="base__jiycdsffh" name="base__jiycdsffh" type="text" ltype="select"
                                              value="" ligerui="{isMultiSelect:true,width:245}"/></p></td>
                </tr>
                <tr style="height: 7mm;">
                    <td colspan="10"><p style="text-align: left;"><span><span>检验现场应清洁，并应放置警示牌。</span></span></p></td>
                    <td colspan="5"><p><input id="base__qjjsp" name="base__qjjsp" type="text" ltype="select"
                                              value="" ligerui="{isMultiSelect:true,width:245}"/></p></td>
                </tr>
                <tr style="height: 11mm;">
                    <td colspan="2"><p><span>使用环境</span></p></td>
                    <td colspan="13"><p>
                        <input id="TBL00097" name="TBL00097" type="text" ltype="select" value="" class="checkboxDiv"
                             ligerui="{data:[{'id':'普通','text':'普通'},{'id':'爆炸','text':'爆炸'},{'id':'其他','text':'其他'}]}"></input>
                        </p>
                    </td>
                </tr>
                <tr style="height: 0.23in;">
                    <td colspan="2" rowspan="2"><p><span>主要检验</span></p>
                        <p><span>仪器设备</span></p></td>
                    <td colspan="4"><p><span><span>检测工具箱（</span></span><span>MTJD-024-</span></p></td>
                    <td colspan="7"><p><input id="base__jyyqsbxh" name="base__jyyqsbxh" type="text" ltype="text"
                                              value="" ligerui="{width:135}"/></p></td>
                    <td colspan="2"><p><span>）</span></p></td>
                </tr>
                <tr style="height: 0.62in;">
                    <td colspan="13"><p>
                        <div id="TBL00098" name="TBL00098" type="text" ltype="checkBox" value="" class="checkboxDiv"
                             ligerui="{data:[{'id':'其它检测仪器：','text':'其它检测仪器：'},{'id':'制动性能测试仪（MTJD-020）','text':'制动性能测试仪（MTJD-020）'},{'id':'踏板力计（MTJD-018）','text':'踏板力计（MTJD-018）'},{'id':'转向参数测试仪（MTJD-019）','text':'转向参数测试仪（MTJD-019）'},{'id':'测滑仪（MTJD-022）','text':'测滑仪（MTJD-022）'},{'id':'称重仪（MTJD-023）','text':'称重仪（MTJD-023）'},{'id':'转向轮定位测试仪（MTJD-021）','text':'转向轮定位测试仪（MTJD-021）'},{'id':'烟度计（MTJD-026）','text':'烟度计（MTJD-026）'}]}"></div>
                        </p>
                    </td>
                </tr>
                <tr style="height: 12mm;">
                    <td colspan="2"><p><span>检验结论</span></p></td>
                    <td colspan="13"><p><input  id="base__inspection_conclusion"
                                                name="base__inspection_conclusion" type="text" ltype="select"
                                                value="" class="checkboxDiv"
                                                ligerui="{data:[{'id':'合格','text':'合格'},{'id':'不合格','text':'不合格'},{'id':'复检合格','text':'复检合格'},{'id':'复检不合格','text':'复检不合格'}],initValue:'合格'}"/></p></td>
                </tr>
                <tr style="height: 15mm;">
                    <td colspan="2"><p><span>备注</span></p></td>
                    <td colspan="13"><p><textarea id="base__remarks" name="base__remarks" type="text" ltype="text" value=""
                                                  ligerui="{width:507}"></textarea></p></td>
                </tr>
                <tr style="height: 3mm;">
                    <td colspan="3"><p><span><span>下次检验时间：</span></span></p></td>
                    <td colspan="12"><p><input  id="base__inspect_next_date" name="base__inspect_next_date" type="text"
                                                ltype="date" value="" ligerui="{format:'yyyy-MM',isTextBoxMode:true,width:153}" required/></p></td>
                </tr>
                <tr style="height: 12mm;">
                    <td><p><span><span>检验：</span></span></p></td>
                    <td colspan="3"><p><input id="base__inspect_op" name="base__inspect_op" type="text" ltype="select"
                                              value="" ligerui="{isMultiSelect:true,width:141}"/></p></td>
                    <td colspan="4"><p><input id="base__inspect_date" name="base__inspect_date" type="text" ltype="date" value=""
                                              ligerui="{width:137}"/></p></td>
                    <td colspan="3"><p><span><span>校核：</span></span></p></td>
                    <td colspan="3"><p><input id="base__confirm_op" name="base__confirm_op" type="text" ltype="text" value=""
                                              ligerui="{width:103}"/></p></td>
                    <td><p><input id="base__confirm_time" name="base__confirm_time" type="text" ltype="date" value=""
                                  ligerui="{width:109}"/></p></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
