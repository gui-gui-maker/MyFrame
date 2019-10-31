package com.khnt.rtbox.template.parse.tag;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

/**
 * @author ZQ
 * @version 2016年3月11日 下午1:34:48
 */
public abstract class BlankRound implements Round {
	public List<String> ruleOuts;// 排除规则
	public List<String> ruleIns;// 符合规则

	/**
	 * 在规则外的节点
	 * 
	 * @param element
	 * @return
	 */
	public boolean hasRuleOutTag(Element element) {
		return false;
	}

	/**
	 * 符合规则的节点
	 * 
	 * @param element
	 * @return
	 */
	public boolean hasRuleInTag(Element element) {
		return false;
	}

	/**
	 * 检查是否初始化规则，没有符合规则，则退出轮循标记
	 * 
	 * @return
	 */
	public boolean checkRuleNull() {
		if (ruleIns == null) {
			initRuleIns();
		}
		if (ruleIns.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 初始化符合规则
	 */
	public void initRuleIns() {
		ruleIns = new ArrayList<String>();
	}

}
