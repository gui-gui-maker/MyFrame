package com.khnt.rtdroc.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.khnt.base.Factory;

/**
 * @author ZQ
 * @version 2016年3月9日 下午5:31:04 类说明
 */
public class Utils {
    public static String attachmentPath = Factory.getSysPara().getProperty("attachmentPath");
    public static String attachmentPathType = Factory.getSysPara().getProperty("attachmentPathType", "relative");

    /**
     * 年月日时分秒命名方式
     *
     * @return
     */
    public static String dayDir() {
        return dateDir("yyyyMMddHHmmss");
    }

    /**
     * 年月命名方式
     *
     * @return
     */
    public static String monthDir() {
        return dateDir("yyyyMM");
    }

    /**
     * 日期命名方式
     *
     * @param format
     * @return
     */
    public static String dateDir(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    /**
     * 返回长度为【strLength】的随机数
     *
     * @param strLength
     * @return
     */

    public static String getFixLenthString(int strLength) {
        Random rm = new Random();
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);
        String fixLenthString = String.valueOf(pross).replace(".", "");
        return fixLenthString.substring(1, strLength + 1);
    }

    /**
     * 返回长度为【strLength】的随机数
     *
     * @param strLength
     * @return
     */

    public static int randomInt(int base) {
        Random rand = new Random();
        int randNum = rand.nextInt(99999) + base;
        return randNum;
    }

    /**
     * 转换成JSON字符输出
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static String toJsonString(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Inclusion.NON_NULL);
        return mapper.writeValueAsString(obj);
    }

    public static int countSub(String content, String sub, Integer count) {
        int position = content.indexOf(sub);
        if (position >= 0) {
            content = content.substring(position + sub.length(), content.length());
            count++;
            return countSub(content, sub, count);
        }
        return count;
    }

    public static String escape(String text) {
        String reg = "*.?+$^[](){}|\\/";
        StringBuilder val = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (reg.indexOf(c) >= 0) {
                val.append("\\\\" + c);
            } else {
                val.append(c);
            }
        }
        return val.toString();
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String rgbToHex(String rgb) throws Exception {
        String regex = "rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(rgb);
        StringBuilder hexValue = new StringBuilder();
        if (matcher.find()) {
            if (matcher.groupCount() != 3) {
                throw new Exception("error rgb..");
            }
            for (int i = 1; i <= matcher.groupCount(); i++) {
                String matchVal = matcher.group(i);
                int matchInt = Integer.parseInt(matchVal);
                if (matchInt < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(matchInt));
            }
            return hexValue.toString().toUpperCase();
        } else {
            return rgb.replace("#", "");
            // throw new Exception("error rgb..");
        }
    }

    public static boolean transMatch(String value, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    /**
     * 获取网站路径
     *
     * @return
     */
    public static String getWebPhysicalPath(String path) {
//        String attachmentPathType = Factory.getSysPara().getProperty("attachmentPathType", "relative");
//        String attachmentPath = Factory.getSysPara().getProperty("attachmentPath");
        if ("absolute".equals(attachmentPathType)) {
            return path;
        } else {
            return Factory.getWebRoot() + "/" + attachmentPath + "/" + path;
        }

    }

}
