package com.lsts.process.main;

import com.khnt.base.DataBaseInf;
import com.khnt.base.Factory;
import com.khnt.utils.StringUtil;
import com.lsts.process.menu.pojo.AccessToken;
import com.lsts.process.menu.pojo.Button;
import com.lsts.process.menu.pojo.ComplexButton;
import com.lsts.process.menu.pojo.Menu;
import com.lsts.process.menu.pojo.ViewButton;
import com.lsts.process.menu.util.WeixinUtil;
import com.lsts.process.resposn.TextMessage;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

public class CoreService
{
  public static String processRequest(HttpServletRequest request)
  {
    String appId = "wx7a3c02acc4a17569";

    String appSecret = "0a9ebff40f469498fbad38aeeebc82aa";

    AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

    if (at != null)
    {
      int result = WeixinUtil.createMenu(getMenu(), at.getToken());

      if (result == 0) {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@菜单创建成功");
      }
      else
      {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@菜单创建失败，错误码" + result);
      }

    }

    String respMessage = null;
    try
    {
    	System.out.println("为什么进步了？什么原因呢 我也不知道  为什么呢 ");
      Map requestMap = MessageUtil.parseXml(request);
      System.out.println("为什么进步了？什么原因呢 我也不知道 原因是什么 "+requestMap);
      String fromUserName = (String)requestMap.get("FromUserName");
      

      String toUserName = (String)requestMap.get("ToUserName");

      String msgType = (String)requestMap.get("MsgType");

      String content = (String)requestMap.get("Content");

      System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+fromUserName+"^^^^^^^^^^^^"+toUserName+"^^^^^^^^^^^^^^^^^^^^^^"+requestMap);
      
      String resContent = "";

      
      TextMessage textMessage = new TextMessage();
      textMessage.setToUserName(fromUserName);
      textMessage.setFromUserName(toUserName);
      textMessage.setCreateTime(new Date().getTime());
      textMessage.setMsgType("text");
      textMessage.setFuncFlag(0);
System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+msgType);
      if (msgType.equals("event"))
      {
        String eventType = (String)requestMap.get("Event");

        if (eventType.equals("subscribe")) {
          resContent = "四川省特种设备检验研究院竭诚为您服务！\r\n点击左下角切换键盘模式后，输入使用单位关键字或设备注册代码可查询报告书状态。";
        } else if ((!(eventType.equals("unsubscribe"))) && 
          (eventType.equals("CLICK")))
        {
          String eventKey = (String)requestMap.get("EventKey");

          System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@key=" + eventKey);
          if(eventKey.equals("qian")){
            	resContent = "户名：四川省特种设备检验研究院\r\n账号：22910601040006383\r\n开户行：农行成都锦东支行\r\n地址：成都市成华区东风路北二巷4号";	
            }
        }

      }
      else if (msgType.equals("text"))
      {
        String sql;
        Statement queryStatement;
        ResultSet executeQuery;
        Connection conn = Factory.getDB().getConnetion();

        String temp = content.trim();
        
        if(temp.equals("测试")){
        	 textMessage.setContent("主要是看气质！！！");

             respMessage = MessageUtil.textMessageToXml(textMessage);
             
             return respMessage;  
        }

        System.out.println("!@!@!@!@!@!@!@");

        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(temp);

        System.out.println("!@!@!@!@!@!@!@22222");

        if (isNum.matches())
        	
        {
          if (temp.length() == 20)
          {
            sql = "select * from (select t.flow_note_name,t.inspection_conclusion,to_char(t.advance_time,'yyyy-MM-dd') as ins_date,t.advance_fees,t2.org_name,t2.telephone,t.is_validation from tzsb_inspection_info t ,base_device_document t1,sys_org t2 where  t1.id=t.fk_tsjc_device_document_id and t.data_status <> '99'    and t2.id=t.check_unit_id  and t1.device_registration_code ='" + 
              temp + "' order by ins_date desc )  where rownum=1";

            queryStatement = conn.createStatement();
            executeQuery = queryStatement.executeQuery(sql);

            while (executeQuery.next())
            {
              if ((executeQuery.getString("flow_note_name") != null) && (!("".equals(executeQuery.getString("flow_note_name"))))) {
                if ((executeQuery.getString("flow_note_name").equals("报告领取"))  )
                {
                	
                	
                  resContent = "流转状态:" + executeQuery.getString("flow_note_name") + "(" + executeQuery.getString("inspection_conclusion") + "/可领取报告)\r\n检验日期:" + executeQuery.getString("ins_date") + "\r\n收费金额:" + executeQuery.getString("advance_fees") + 
                    "(以开票为准)\r\n检验部门:" + executeQuery.getString("org_name") + "\r\n联系电话:" + executeQuery.getString("telephone");
                }else if((executeQuery.getString("flow_note_name").equals("报告归档"))){
                	resContent = "流转状态:" + executeQuery.getString("flow_note_name") + "(" + executeQuery.getString("inspection_conclusion") + "/报告已领取)\r\n检验日期:" + executeQuery.getString("ins_date") + "\r\n收费金额:" + executeQuery.getString("advance_fees") + 
                            "(以开票为准)\r\n检验部门:" + executeQuery.getString("org_name") + "\r\n联系电话:" + executeQuery.getString("telephone");
                }
                else {
                	if((executeQuery.getString("is_validation")!=null)){
                	if((executeQuery.getString("is_validation").equals("1"))){
                		resContent = "流转状态:成都市局验证(报告暂不可领取)\r\n检验日期:" + executeQuery.getString("ins_date") + "\r\n收费金额:" + executeQuery.getString("advance_fees") + 
                                "(以开票为准)\r\n检验部门:" + executeQuery.getString("org_name") + "\r\n联系电话:" + executeQuery.getString("telephone");
                	}else{
                		resContent = "流转状态:" + executeQuery.getString("flow_note_name") + "(报告暂不可领取)\r\n检验日期:" + executeQuery.getString("ins_date") + "\r\n收费金额:" + executeQuery.getString("advance_fees") + 
                                "(以开票为准)\r\n检验部门:" + executeQuery.getString("org_name") + "\r\n联系电话:" + executeQuery.getString("telephone");
                	}
                	}

                }
              }
              else {
            	  if((executeQuery.getString("is_validation")!=null)){
            	  if((executeQuery.getString("is_validation").equals("1"))){
            		  resContent = "流转状态:成都市局验证\r\n检验日期:" + executeQuery.getString("ins_date") + "\r\n收费金额:" + executeQuery.getString("advance_fees") + 
                              "(以开票为准)\r\n检验部门:" + executeQuery.getString("org_name") + "\r\n联系电话:" + executeQuery.getString("telephone");
            	  }else{
            		  resContent = "流转状态:" + executeQuery.getString("flow_note_name") + "\r\n检验日期:" + executeQuery.getString("ins_date") + "\r\n收费金额:" + executeQuery.getString("advance_fees") + 
                              "(以开票为准)\r\n检验部门:" + executeQuery.getString("org_name") + "\r\n联系电话:" + executeQuery.getString("telephone");
            	  }
            	  }
               
              }

            }

          }	
          else
          {
            resContent = "请输入正确的注册代码进行查询！PS：查询时请输入完整的使用单位名称或设备注册代码。";
          }

        }
        else if (temp.length() >= 3)
        {
          sql = "select * from (select t1.device_registration_code,t.flow_note_name,to_char(t.advance_time,'yyyy-MM-dd') as ins_date,t.advance_fees from tzsb_inspection_info t,base_device_document t1 where t.report_com_name like '%" + 
            temp + "%' and t1.id=t.fk_tsjc_device_document_id and t.data_status <> '99' " + 
            "and t.advance_time> add_months(sysdate,-12) and t.advance_time<sysdate) where rownum<2000";

          queryStatement = conn.createStatement();
          executeQuery = queryStatement.executeQuery(sql);

          int rowCount = 0;

          while (executeQuery.next())
          {
            ++rowCount;
          }

          String str = "\"http://m.scsei.org.cn/?action=queryreport&searchvalue=" + temp + "\"";
          resContent = "以\"" + temp + "\"查询到过去一年中有" + rowCount + "份报告/::D/::D/::D~" + "\r\n<a href=" + str + ">点我查看详细</a>";
        }
           
        else
        {
          resContent = "请输入三个或三个以上字符查询.";
        }

        if ("".equals(resContent))
        {
          resContent = "duang，没有匹配的数据，请重新输入!PS：查询时请输入完整的使用单位名称或设备注册代码。";
        }

        
        String id = StringUtil.getUUID();

        String sql2 = "insert into querymessageinfo (id,USERNAME,QUERYTYPE,QUERYTIME,QUERYCONTENT,RETURNCONTENT) values('" + id + "','" + fromUserName + "','" + msgType + "',sysdate,'" + temp + "','"+resContent+"' ) "; 

        Statement queryStatement2 = conn.createStatement();

        queryStatement2.executeUpdate(sql2);

        Factory.getDB().freeConnetion(conn);
      }
      else
      {
        resContent = "请输入文本格式！";
      }

      System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@返回值=" + resContent);
      textMessage.setContent(resContent);

      respMessage = MessageUtil.textMessageToXml(textMessage);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    return respMessage;
  }

  private static Menu getMenu()
  {
    ViewButton btn11 = new ViewButton();
    btn11.setName("机构介绍");
    btn11.setType("view");
    btn11.setUrl("http://m.scsei.org.cn/?action=category&categoryid=33&page=");

    ViewButton btn12 = new ViewButton();
    btn12.setName("业务范围");
    btn12.setType("view");
    btn12.setUrl("http://m.scsei.org.cn/?action=entity&entityid=1442");

    ViewButton btn13 = new ViewButton();
    btn13.setName("检验资质");
    btn13.setType("view");
    btn13.setUrl("http://m.scsei.org.cn/?action=category&categoryid=48&page=");

    ViewButton btn14 = new ViewButton();
    btn14.setName("党政领导");
    btn14.setType("view");
    btn14.setUrl("http://m.scsei.org.cn/index.php?action=category&categoryid=45&page=");

    ViewButton btn21 = new ViewButton();
    btn21.setName("内设部门");
    btn21.setType("view");
    btn21.setUrl("http://m.scsei.org.cn/index.php?action=category&categoryid=46&page=");

    ViewButton btn22 = new ViewButton();
    btn22.setName("通知公告");
    btn22.setType("view");
    btn22.setUrl("http://www.scsei.org.cn");

    ViewButton btn23 = new ViewButton();
    btn23.setName("特检动态");
    btn23.setType("view");
    btn23.setUrl("http://m.scsei.org.cn/index.php?action=category&categoryid=57&page=");

    ViewButton btn24 = new ViewButton();
    btn24.setName("收费标准");
    btn24.setType("view");
    btn24.setUrl("http://m.scsei.org.cn");

    ViewButton btn31 = new ViewButton();
    btn31.setName("办事报检");
    btn31.setType("view");
    btn31.setUrl("http://m.scsei.org.cn/?action=banshi");

    ViewButton btn32 = new ViewButton();
    btn32.setName("在线报检");
    btn32.setType("click");
    btn32.setKey("qian");

    ViewButton btn33 = new ViewButton();
    btn33.setName("报告查询");
    btn33.setType("view");
    btn33.setUrl("http://m.scsei.org.cn/?action=banshi");

    ViewButton btn34 = new ViewButton();
    btn34.setName("咨询投诉");
    btn34.setType("view");
    btn34.setUrl("http://m.scsei.org.cn/?action=consultadd&cate=%E6%8A%95%E8%AF%89");

    ViewButton btn35 = new ViewButton();
    btn35.setName("院长信箱");
    btn35.setType("view");
    btn35.setUrl("http://m.scsei.org.cn/?action=messagestep1");

    ViewButton btn36 = new ViewButton();
    btn36.setName("大厅WIFI");
    btn36.setType("view");
    btn36.setUrl("http://wifi.weixin.qq.com/mbl/connect.xhtml?type=1");
    
    //扫码
    ViewButton btn361 = new ViewButton();
    btn361.setName("扫一扫");
    btn361.setType("scancode_push");
    btn361.setKey("rselfmenu");;
    
    ComplexButton mainBtn1 = new ComplexButton();
    mainBtn1.setName("机构");
    mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13, btn14, btn21 });

    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@返回值=" + mainBtn1);

    ComplexButton mainBtn3 = new ComplexButton();
    mainBtn3.setName("互动");
    mainBtn3.setSub_button(new Button[] { btn23, btn34, btn35 });

    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@返回值=" + mainBtn3);

    ComplexButton mainBtn2 = new ComplexButton();
    mainBtn2.setName("办事");
    mainBtn2.setSub_button(new Button[] { btn31, btn32, btn33, btn36,btn361 });

    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@返回值=" + mainBtn2);

    Menu menu = new Menu();
    menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@返回值=" + menu);

    return menu;
  }
}