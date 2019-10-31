package com.lsts.process.main;

import com.lsts.process.resposn.TextMessage;
import com.thoughtworks.xstream.XStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MessageUtil
{
  public static final String RESP_MESSAGE_TYPE_TEXT = "text";
  public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
  public static final String RESP_MESSAGE_TYPE_NEWS = "news";
  public static final String REQ_MESSAGE_TYPE_TEXT = "text";
  public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
  public static final String REQ_MESSAGE_TYPE_LINK = "link";
  public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
  public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
  public static final String REQ_MESSAGE_TYPE_EVENT = "event";
  public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
  public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
  public static final String EVENT_TYPE_CLICK = "CLICK";
  private static XStream xstream = new XStream();

  public static Map<String, String> parseXml(HttpServletRequest request)
    throws Exception
  {
    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@解析收到数据开始");

    Map map = new HashMap();

    InputStream inputStream = request.getInputStream();

    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@获取输入留=" + inputStream);

    SAXReader reader = new SAXReader();
    Document document = reader.read(inputStream);

    Element root = document.getRootElement();

    List elementList = root.elements();

    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@得到所有子节点=" + elementList);

    for (Iterator localIterator = elementList.iterator(); localIterator.hasNext(); ) { Element e = (Element)localIterator.next();
      map.put(e.getName(), e.getText());
    }

    inputStream.close();
    inputStream = null;

    return map;
  }

  public static String textMessageToXml(TextMessage textMessage)
  {
    xstream.alias("xml", textMessage.getClass());
    return xstream.toXML(textMessage);
  }
}