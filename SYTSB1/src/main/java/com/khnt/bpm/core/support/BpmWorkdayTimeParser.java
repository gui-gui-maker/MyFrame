package com.khnt.bpm.core.support;

import java.util.Calendar;
import java.util.Date;

import com.khnt.base.Factory;
import com.khnt.core.exception.KhntException;
import com.khnt.utils.StringUtil;

/**
 * 通用的工作日时间计算解析器，这是工作流中默认的定时时间解析器。
 * 
 * 该解析器按照工作日来计算设置的日期天数,即每周只作5天计算。
 * 
 * @author hansel
 * 
 */
public class BpmWorkdayTimeParser implements BpmTimeParser {

	static String PUBLIC_HOLIDAY = "";

	public BpmWorkdayTimeParser() {
		String holiday = Factory.getSysPara().getProperty("public_holiday");
		if (holiday != null)
			PUBLIC_HOLIDAY = holiday;
	}

	@Override
	public Date parseDateTime(String timeString) throws KhntException {
		if (StringUtil.isEmpty(timeString))
			throw new KhntException("需要解析的时间描述不能为空！");

		String[] timeDesc = timeString.split(",");
		return this.parseDateTime(timeDesc);
	}

	@Override
	public Date parseDateTime(String[] timeDesc) throws KhntException {
		return parseDateTime(new Date(), timeDesc);
	}

	public Date parseDateTime(Date startDate, String[] timeDesc) {
		if (timeDesc.length != 3)
			throw new KhntException("需要解析的时间描述格式不正确，必须为“天,时,分”格式");

		Calendar dateTime = Calendar.getInstance();
		dateTime.setTime(startDate);
		if (!StringUtil.isEmpty(timeDesc[0])) {
			int date = Integer.parseInt(timeDesc[0]);
			// 计算工作日，需把期间所有周末2天计入在内
			while (date > 0) {
				dateTime.add(Calendar.DATE, 1);
				int day = dateTime.get(Calendar.DAY_OF_WEEK);
				String monthDate = (dateTime.get(Calendar.MONTH) + 1) + "." + dateTime.get(Calendar.DAY_OF_MONTH);
				// 排除掉法定节假日和公休日
				if (day < 7 && day > 1 && !PUBLIC_HOLIDAY.contains(monthDate))
					date--;
			}
		}
		if (!StringUtil.isEmpty(timeDesc[1]))
			dateTime.add(Calendar.HOUR, Integer.parseInt(timeDesc[1]));
		if (!StringUtil.isEmpty(timeDesc[2]))
			dateTime.add(Calendar.MINUTE, Integer.parseInt(timeDesc[2]));
		return dateTime.getTime();
	}
}
