<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="detail">
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        $.ajax({
            url:'infomanage/questionnaire/detail.do',
            //dataType:'text',
            data:{"id":'${param.id}'},
            success:function (data) {
                var tr = $("#itemsContainer tr:nth-child(2)");
                $("#yfxz_itemIds" + data.data.yfxz).text("√");
                $("#ywnl_itemIds" + data.data.ywnl).text("√");
                $("#fwtd_itemIds" + data.data.fwtd).text("√");
                $("#gzxl_itemIds" + data.data.gzxl).text("√");
                $("#gzzf_itemIds" + data.data.gzzf).text("√");
                $("#zwgk_itemIds" + data.data.zwgk).text("√");
                $("#ljzl_itemIds" + data.data.ljzl).text("√");
                $("#userName").text(data.data.name);
                $("#unit").text(data.data.unit);
                $("#yjhjy").html(data.data.yjhjy.replace(/\n/gi,"<br>"));
                $("#wthbz").html(data.data.wthbz.replace(/\n/gi,"<br>"));
                if(data.data.identity=="zxwy")
                {
                    $("#identity").text("政协委员");
                }
                else
                {
                    $("#identity").text("人大代表");
                }

                $("#duties").text(data.data.duties);
                $("#telph").text(data.data.telph);
                var sDate= new Date(data.data.sumitDate);

                $("#sumitDate").text($.kh.getFormatDate(sDate,"yyyy-MM-dd hh:mm:ss"));

            }});
    </script>
</head>
<body>
<form name="formObj" id="formObj" method="post"
      action="" getAction="">
    <input name="id" type="hidden"/>
    <input name='fkClasstypeId' type='hidden'>
    <table border="0" cellpadding="3" cellspacing="0" width="" height="" align="" class="l-detail-table">
        <tr>
            <td align='center'>
                <table width="690" border="0" cellspacing="0" cellpadding="0" class="votes">
                    <tr>
                        <td>
                            <table border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <th colspan="4" scope="col" align='center'><h2 class="tt" align='center'>
                                        加强和改进民政工作调查问卷</h2></th>
                                </tr>
                                <tr>
                                    <td colspan="4" align="left"><p>&nbsp;&nbsp;&nbsp;&nbsp;为凝聚各方智慧，加强和改进民政工作，更好地发挥民政工作在社会建设中的骨干作用，推进全市民政事业科学发展，我们希望借助您对民政事业的了解，帮助我们从不同侧面分析民政工作存在的不足与差距。对您提出的宝贵意见和建议，我们将会认真对待，切实整改。</p>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4" align="right" style="line-height:180%;">请在你认为的栏内划上“<font
                                            face="Verdana">√</font>”
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4">
                                        <table width="100%"
                                               style="border-collapse:collapse; line-height:220%;" border="1"
                                               cellspacing="0" cellpadding="0" align="center">
                                            <tr>
                                                <td width="16%" rowspan="8" align="center"><strong>总<br/>
                                                    体<br/>
                                                    评<br/>
                                                    价</strong></td>
                                                <td width="20%" align="center"><strong>评价项目</strong></td>
                                                <td width="16%" align="center"><strong>优秀</strong></td>
                                                <td width="16%" align="center"><strong>满意</strong></td>
                                                <td width="16%" align="center"><strong>一般</strong></td>
                                                <td width="16%" align="center"><strong>较差</strong></td>
                                            </tr>
                                            <tr id="yfxz_itemIds">
                                                <td align="center"><strong>依法行政</strong></td>
                                                <td align="center" id="yfxz_itemIds1"></td>
                                                <td align="center" id="yfxz_itemIds2"></td>
                                                <td align="center" id="yfxz_itemIds3"></td>
                                                <td align="center" id="yfxz_itemIds4"></td>
                                            </tr>
                                            <tr>
                                                <td align="center"><strong>业务能力</strong></td>
                                                <td align="center" id="ywnl_itemIds1"></td>
                                                <td align="center" id="ywnl_itemIds2"></td>
                                                <td align="center" id="ywnl_itemIds3"></td>
                                                <td align="center" id="ywnl_itemIds4"></td>

                                            </tr>
                                            <tr>
                                                <td align="center"><strong>服务态度</strong></td>
                                                <td align="center" id="fwtd_itemIds1"></td>
                                                <td align="center" id="fwtd_itemIds2"></td>
                                                <td align="center" id="fwtd_itemIds3"></td>
                                                <td align="center" id="fwtd_itemIds4"></td>

                                            </tr>
                                            <tr>
                                                <td align="center"><strong>工作效率</strong></td>
                                                <td align="center" id="gzxl_itemIds1"></td>
                                                <td align="center" id="gzxl_itemIds2"></td>
                                                <td align="center" id="gzxl_itemIds3"></td>
                                                <td align="center" id="gzxl_itemIds4"></td>

                                            </tr>
                                            <tr>
                                                <td align="center"><strong>工作作风</strong></td>
                                                <td align="center" id="gzzf_itemIds1"></td>
                                                <td align="center" id="gzzf_itemIds2"></td>
                                                <td align="center" id="gzzf_itemIds3"></td>
                                                <td align="center" id="gzzf_itemIds4"></td>

                                            </tr>
                                            <tr>
                                                <td align="center"><strong>政务公开</strong></td>
                                                <td align="center" id="zwgk_itemIds1"></td>
                                                <td align="center" id="zwgk_itemIds2"></td>
                                                <td align="center" id="zwgk_itemIds3"></td>
                                                <td align="center" id="zwgk_itemIds4"></td>
                                            </tr>
                                            <tr>
                                                <td align="center"><strong>廉洁自律</strong></td>
                                                <td align="center" id="ljzl_itemIds1"></td>
                                                <td align="center" id="ljzl_itemIds2"></td>
                                                <td align="center" id="ljzl_itemIds3"></td>
                                                <td align="center" id="ljzl_itemIds4"></td>
                                            </tr>
                                            <tr>
                                                <td colspan="1" align="left"><p><strong>当前我市民政工作存在哪些问题和不足：</strong></p></td>
                                                <td colspan="5" align="left" id="wthbz"></td>
                                            </tr>
                                            <tr>

                                            </tr>
                                            <tr>
                                                <td colspan="1" align="left"><p><strong></strong>对我市民政工作的意见和建议：</strong>
                                                </p></td>
                                                <td colspan="5" align="left" id="yjhjy"></td>
                                            </tr>
                                        </table>
                                        <br>
                                        <table width="100%"
                                               style="border-collapse:collapse; line-height:220%;" border="1"
                                               cellspacing="0" cellpadding="0" align="center">
                                            <tr>
                                                <td align="center" >姓名：</td>
                                                <td align="center" id="userName"></td>
                                                <td align="center">身份：</td>
                                                <td align="center" id="identity"></td>
                                                <td align="center">职务：</td>
                                                <td align="center" id="duties"></td>
                                            </tr>
                                            <tr>
                                                <td align="center">联系方式：</td>
                                                <td align="center" id="telph"></td>
                                                <td align="center">单位：</td>
                                                <td align="center" id="unit"></td>
                                                <td align="center">提交时间：</td>
                                                <td align="center" id="sumitDate"></td>
                                            </tr>
                                        </table>

                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
</body>
</html>