package com.khnt.rtbox.template.parse.tag;

import java.util.List;

import com.khnt.rtbox.template.model.RtEntity;
import com.khnt.rtbox.template.parse.convert.RtCount;

/**
 * @author ZQ
 * @version 2016年3月14日 下午11:35:03 按照用户指定，订制正式DOCX模板
 * @param <RtEntity>
 */
public abstract class CustomRound implements Round {
	public RtCount count;
	public List<RtEntity> rtEntitys;

	
}
