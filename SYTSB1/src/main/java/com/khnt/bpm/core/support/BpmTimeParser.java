package com.khnt.bpm.core.support;

import java.util.Date;

import com.khnt.core.exception.KhntException;

/**
 * 工作流中用于对定时设置使用到时间计算解析器接口规范。
 * 
 * 实现类需要将给定格式的定时描述转换为具体时间
 * 
 * @author hansel
 * 
 */
public interface BpmTimeParser {

	/**
	 * 将给定的时间描述解析为合适的时间对象
	 * 
	 * @param timeString
	 *            定时时间描述，例如“5,12,30”表示从现在开始5天12小时30分钟那个时间。
	 * @return
	 * @throws KhntException
	 */
	public Date parseDateTime(String timeString) throws KhntException;

	/**
	 * 将给定的时间描述解析为合适的时间对象
	 * 
	 * @param timeDesc
	 *            定时时间描述，格式为数组，长度为3。 第一位表示天数，第二位表示小时数，第三位表示分钟数;
	 *            例如["5","12","30"]表示从现在开始的5天12小时30分钟那个时间
	 * 
	 * @return
	 * @throws KhntException
	 */
	public Date parseDateTime(String[] timeDesc) throws KhntException;

	/**
	 * 计算从指定起始日期开始的定时时间
	 * 
	 * @param startDate
	 * @param timeDesc
	 *            定时时间描述，格式为数组，长度为3。 第一位表示天数，第二位表示小时数，第三位表示分钟数;
	 *            例如["5","12","30"]表示从现在开始的5天12小时30分钟那个时间
	 * @return
	 */
	public Date parseDateTime(Date startDate, String[] timeDesc);
}
