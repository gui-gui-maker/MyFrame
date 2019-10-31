package com.lsts.process.menu.main;

import com.lsts.process.menu.pojo.AccessToken;
import com.lsts.process.menu.pojo.Button;
import com.lsts.process.menu.pojo.CommonButton;
import com.lsts.process.menu.pojo.ComplexButton;
import com.lsts.process.menu.pojo.Menu;
import com.lsts.process.menu.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuManager
{
  private static Logger log = LoggerFactory.getLogger(MenuManager.class);

  public static void main(String[] args)
  {
    String appId = "wx7a3c02acc4a17569";

    String appSecret = "0a9ebff40f469498fbad38aeeebc82aa";

    AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

    if (at != null)
    {
      int result = WeixinUtil.createMenu(getMenu(), at.getToken());

      if (result == 0)
        log.info("菜单创建成功！");
      else
        log.info("菜单创建失败，错误码：" + result);
    }
  }

  private static Menu getMenu()
  {
    CommonButton btn11 = new CommonButton();
    btn11.setName("机构介绍");
    btn11.setType("click");
    btn11.setKey("11");

    CommonButton btn12 = new CommonButton();
    btn12.setName("业务范围");
    btn12.setType("click");
    btn12.setKey("12");

    CommonButton btn13 = new CommonButton();
    btn13.setName("检验资质");
    btn13.setType("click");
    btn13.setKey("13");

    CommonButton btn14 = new CommonButton();
    btn14.setName("党政领导");
    btn14.setType("click");
    btn14.setKey("14");

    CommonButton btn21 = new CommonButton();
    btn21.setName("内设部门");
    btn21.setType("click");
    btn21.setKey("21");

    CommonButton btn22 = new CommonButton();
    btn22.setName("通知公告");
    btn22.setType("click");
    btn22.setKey("22");

    CommonButton btn23 = new CommonButton();
    btn23.setName("特检动态");
    btn23.setType("click");
    btn23.setKey("23");

    CommonButton btn24 = new CommonButton();
    btn24.setName("收费标准");
    btn24.setType("click");
    btn24.setKey("24");

    CommonButton btn25 = new CommonButton();
    btn25.setName("新春团拜");
    btn25.setType("click");
    btn25.setKey("25");

    CommonButton btn31 = new CommonButton();
    btn31.setName("办事指南");
    btn31.setType("click");
    btn31.setKey("31");

    CommonButton btn32 = new CommonButton();
    btn32.setName("在线报检");
    btn32.setType("click");
    btn32.setKey("32");

    CommonButton btn33 = new CommonButton();
    btn33.setName("报告查询");
    btn33.setType("click");
    btn33.setKey("33");

    CommonButton btn34 = new CommonButton();
    btn34.setName("咨询投诉");
    btn34.setType("click");
    btn34.setKey("34");

    CommonButton btn35 = new CommonButton();
    btn35.setName("院长信箱");
    btn35.setType("click");
    btn35.setKey("35");

    ComplexButton mainBtn1 = new ComplexButton();
    mainBtn1.setName("机构");
    mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13, btn14, btn21 });

    ComplexButton mainBtn2 = new ComplexButton();
    mainBtn2.setName("动态");
    mainBtn2.setSub_button(new CommonButton[] { btn22, btn23, btn24, btn25 });

    ComplexButton mainBtn3 = new ComplexButton();
    mainBtn3.setName("办事");
    mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33, btn35, btn35 });

    Menu menu = new Menu();
    menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

    return menu;
  }
}