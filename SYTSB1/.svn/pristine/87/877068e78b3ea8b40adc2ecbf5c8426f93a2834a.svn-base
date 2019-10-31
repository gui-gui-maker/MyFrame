package com.scts.payment.wxpay.util;

import com.khnt.utils.DateUtil;
import com.scts.payment.order.constant.Constant;


import java.util.Date;
import java.util.Random;

/**
 * add by LatifLan，
 * 从com.syts.utils.StringUtils.java 拷贝过来
 * UUID 工具类
 *
 * @author GaoYa
 * @ClassName StringUtils
 * @JDK 1.7
 * @date 2018-06-13 下午17:22:00
 */
public class StringUtils {

    /**
     * 创建UUID
     *
     * @return
     */
    public static synchronized String makeUUID() {
        Date date = new Date();
        StringBuffer s = new StringBuffer(DateUtil.format(date, Constant.hmsDatePattern));
        return s.append((new Random().nextInt(900) + 100)).toString();
    }
}
