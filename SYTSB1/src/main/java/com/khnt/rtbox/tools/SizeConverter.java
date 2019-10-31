package com.khnt.rtbox.tools;


/**
 * @author ZQ
 * @version 2017年3月7日 下午1:24:50 类说明
 */
public class SizeConverter {

	static int DPI = 96;

	/**
	 * （磅）pt=1/72(英寸), px=1/dpi(英寸)
	 * 
	 * 因此 pt=px*72/dpi
	 * 
	 * 以Windows下的96dpi来计算，pt=px*72/96=px*3/4
	 * 
	 * 默认用96 ，万一系统设置了高DPI或者用的苹果电脑， 就呵呵了。。 1440
	 * 
	 * @param pt
	 * @return
	 */

	public static int toPx(int pt) {
		System.out.println(pt * DPI / 72);
		int px = pt * DPI / 72;
		return px;
	}

	/**
	 * 大一号为26磅 size为52，推测换算机制为 size/2=实际 磅数
	 * 
	 * @param size
	 * @return
	 */
	public static int ToPt(int size) {
		int pt = size / 2;
		return pt;
	}

	/**
	 * 英寸与磅置换
	 * 
	 * @param in
	 * @return
	 */
	public static int inToPt(int in) {
		int pt = in * 72;
		return pt;
	}

	/**
	 * 
	 * @param size
	 * @return
	 */
	public static int wordWidthToPx(int size) {
		return size * DPI / 1440;
	}

	public static void main(String[] args) {
		System.out.println(ToPt(2330));
		System.out.println(toPx(2330 * 72 / 1440));
		System.out.println(wordWidthToPx(2330));
		// System.out.println(16838);
		// // 1英寸等于72磅 476
		// System.out.println(952 / 72 * 1440);

	}
}