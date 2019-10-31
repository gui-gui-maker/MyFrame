package com.lsts.process.main;

import com.lsts.process.menu.main.MenuManager;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoreServlet extends HttpServlet
{
  private static Logger log = LoggerFactory.getLogger(MenuManager.class);
  private static final long serialVersionUID = 1186169314L;

  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    String respMessage = CoreService.processRequest(request);

    String signature = request.getParameter("signature");

    String timestamp = request.getParameter("timestamp");

    String nonce = request.getParameter("nonce");

    String echostr = request.getParameter("echostr");

    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@随机字符串" + echostr);

    PrintWriter out = response.getWriter();

    if (SignUtil.checkSignature(signature, timestamp, nonce)) {
      out.print(echostr);
    }

    out.close();
    out = null;

    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@验证是否通过呢");
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@启动恢复");

    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@编码格式");

    String respMessage = CoreService.processRequest(request);

    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@返回内容");

    PrintWriter out = response.getWriter();
    out.print(respMessage);
    out.close();
  }
}