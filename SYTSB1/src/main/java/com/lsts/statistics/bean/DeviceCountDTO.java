package com.lsts.statistics.bean;


/**
 * 各部门检验业务统计

 * @author GaoYa
 * @date 2014-10-21 16:11:00
 */

public class DeviceCountDTO{

	private static final long serialVersionUID = 1L;
	
	/*各检验部门检验业务统计*/
	private String check_unit;	// 检验部门
	private Integer cur_dj_count;	// 当前定期检验数量
	private Integer cur_jj_count;	// 当前监督检验数量
	private Integer cur_wj_count;	// 当前委托检验数量
	private Integer cur_jy_total;	// 当前检验合计数量
	private Integer last_jy_total;	// 去年同期检验合计数量
	private Integer compare_count;	// 与去年同期比较
	private Integer dj_total;		// 定期检验合计
	private Integer jj_total;		// 监督检验合计
	private Integer wj_total;		// 委托检验合计
	private Integer total;			// 当前合计（总数）
	private Integer last_total;		// 去年同期检验合计（总数）
	private Integer compare_total;	// 与去年同期比较合计（总数）
	
	/*各部门已打印的电梯定检、监检统计*/
	private String  device_code;	// 设备类别代码
	private String  device_name;	// 设备类别名称
	private String jd1_dj_count;	// 机电一部定检已打印数量
	private String jd1_jj_count;	// 机电一部监检已打印数量
	private String jd1_wj_count;	// 机电一部委检已打印数量
	private String jd2_dj_count;	// 机电二部定检已打印数量
	private String jd2_jj_count;	// 机电二部监检已打印数量
	private String jd2_wj_count;	// 机电二部委检已打印数量
	private String jd3_dj_count;	// 机电三部定检已打印数量
	private String jd3_jj_count;	// 机电三部监检已打印数量
	private String jd3_wj_count;	// 机电三部委检已打印数量
	private String jd4_dj_count;	// 机电四部定检已打印数量
	private String jd4_jj_count;	// 机电四部监检已打印数量
	private String jd4_wj_count;	// 机电四部委检已打印数量
	private String jd5_dj_count;	// 机电五部定检已打印数量
	private String jd5_jj_count;	// 机电五部监检已打印数量
	private String jd5_wj_count;	// 机电五部委检已打印数量
	private String jd6_dj_count;	// 机电六部定检已打印数量
	private String jd6_jj_count;	// 机电六部监检已打印数量
	private String jd6_wj_count;	// 机电六部委检已打印数量
	private String jd1_dj_total;	// 机电一部定检合计
	private String jd1_jj_total;	// 机电一部监检合计
	private String jd1_wj_total;	// 机电一部委检合计
	private String jd2_dj_total;	// 机电二部定检合计
	private String jd2_jj_total;	// 机电二部监检合计
	private String jd2_wj_total;	// 机电二部委检合计
	private String jd3_dj_total;	// 机电三部定检合计
	private String jd3_jj_total;	// 机电三部监检合计
	private String jd3_wj_total;	// 机电三部委检合计
	private String jd4_dj_total;	// 机电四部定检合计
	private String jd4_jj_total;	// 机电四部监检合计
	private String jd4_wj_total;	// 机电四部委检合计
	private String jd5_dj_total;	// 机电五部定检合计
	private String jd5_jj_total;	// 机电五部监检合计
	private String jd5_wj_total;	// 机电五部委检合计
	private String jd6_dj_total;	// 机电六部定检合计
	private String jd6_jj_total;	// 机电六部监检合计
	private String jd6_wj_total;	// 机电六部委检合计
	private String all_total;		// 已打印总计
	
	/*各检验人员检验业务统计*/
	private String org_name;		// 所在部门名称
	private String user_name;		// 检验人员
	private Integer lr_count;		// 起草报告（报告录入）
	private Integer sh_count;		// 审核报告
	private Integer qf_count;		// 签发报告
	private Integer dy_count;		// 打印报告
	private Integer dyhgz_count;	// 打印合格证
	private Integer lq_count;		// 领取报告
	private Integer gd_count;		// 归档报告
	private Integer lr_total;		// 起草报告合计
	private Integer sh_total;		// 审核报告合计
	private Integer qf_total;		// 签发报告合计
	private Integer dy_total;		// 打印报告合计
	private Integer dyhgz_total;	// 打印合格证合计
	private Integer lq_total;		// 领取报告合计
	private Integer gd_total;		// 归档报告合计
	
	/*业务服务部综合统计2017-12-11*/
	private String category;		// 类别（业务类别）
	private String cate_sort;		// 类别排序
	
	private String dev_p_count;	// 打印台数
	private String rep_p_count;	// 打印检验报告份数	
	private String dev_lq_count;// 发放台数
	private String rep_lq_count;// 发放检验报份数
	
	
	public String getCheck_unit() {
		return check_unit;
	}
	public void setCheck_unit(String check_unit) {
		this.check_unit = check_unit;
	}
	public Integer getCur_dj_count() {
		return cur_dj_count;
	}
	public void setCur_dj_count(Integer cur_dj_count) {
		this.cur_dj_count = cur_dj_count;
	}
	public Integer getCur_jj_count() {
		return cur_jj_count;
	}
	public void setCur_jj_count(Integer cur_jj_count) {
		this.cur_jj_count = cur_jj_count;
	}
	public Integer getCur_jy_total() {
		return cur_jy_total;
	}
	public void setCur_jy_total(Integer cur_jy_total) {
		this.cur_jy_total = cur_jy_total;
	}
	public Integer getLast_jy_total() {
		return last_jy_total;
	}
	public void setLast_jy_total(Integer last_jy_total) {
		this.last_jy_total = last_jy_total;
	}
	public Integer getCompare_count() {
		return compare_count;
	}
	public void setCompare_count(Integer compare_count) {
		this.compare_count = compare_count;
	}
	public Integer getCur_wj_count() {
		return cur_wj_count;
	}
	public void setCur_wj_count(Integer cur_wj_count) {
		this.cur_wj_count = cur_wj_count;
	}
	public Integer getDj_total() {
		return dj_total;
	}
	public void setDj_total(Integer dj_total) {
		this.dj_total = dj_total;
	}
	public Integer getJj_total() {
		return jj_total;
	}
	public void setJj_total(Integer jj_total) {
		this.jj_total = jj_total;
	}
	public Integer getWj_total() {
		return wj_total;
	}
	public void setWj_total(Integer wj_total) {
		this.wj_total = wj_total;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getLast_total() {
		return last_total;
	}
	public void setLast_total(Integer last_total) {
		this.last_total = last_total;
	}
	public Integer getCompare_total() {
		return compare_total;
	}
	public void setCompare_total(Integer compare_total) {
		this.compare_total = compare_total;
	}
	public String getDevice_code() {
		return device_code;
	}
	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}
	public String getDevice_name() {
		return device_name;
	}
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}
	public String getJd1_dj_count() {
		return jd1_dj_count;
	}
	public void setJd1_dj_count(String jd1_dj_count) {
		this.jd1_dj_count = jd1_dj_count;
	}
	public String getJd1_jj_count() {
		return jd1_jj_count;
	}
	public void setJd1_jj_count(String jd1_jj_count) {
		this.jd1_jj_count = jd1_jj_count;
	}
	public String getJd1_wj_count() {
		return jd1_wj_count;
	}
	public void setJd1_wj_count(String jd1_wj_count) {
		this.jd1_wj_count = jd1_wj_count;
	}
	public String getJd2_dj_count() {
		return jd2_dj_count;
	}
	public void setJd2_dj_count(String jd2_dj_count) {
		this.jd2_dj_count = jd2_dj_count;
	}
	public String getJd2_jj_count() {
		return jd2_jj_count;
	}
	public void setJd2_jj_count(String jd2_jj_count) {
		this.jd2_jj_count = jd2_jj_count;
	}
	public String getJd2_wj_count() {
		return jd2_wj_count;
	}
	public void setJd2_wj_count(String jd2_wj_count) {
		this.jd2_wj_count = jd2_wj_count;
	}
	public String getJd3_dj_count() {
		return jd3_dj_count;
	}
	public void setJd3_dj_count(String jd3_dj_count) {
		this.jd3_dj_count = jd3_dj_count;
	}
	public String getJd3_jj_count() {
		return jd3_jj_count;
	}
	public void setJd3_jj_count(String jd3_jj_count) {
		this.jd3_jj_count = jd3_jj_count;
	}
	public String getJd3_wj_count() {
		return jd3_wj_count;
	}
	public void setJd3_wj_count(String jd3_wj_count) {
		this.jd3_wj_count = jd3_wj_count;
	}
	public String getJd4_dj_count() {
		return jd4_dj_count;
	}
	public void setJd4_dj_count(String jd4_dj_count) {
		this.jd4_dj_count = jd4_dj_count;
	}
	public String getJd4_jj_count() {
		return jd4_jj_count;
	}
	public void setJd4_jj_count(String jd4_jj_count) {
		this.jd4_jj_count = jd4_jj_count;
	}
	public String getJd4_wj_count() {
		return jd4_wj_count;
	}
	public void setJd4_wj_count(String jd4_wj_count) {
		this.jd4_wj_count = jd4_wj_count;
	}
	public String getJd5_dj_count() {
		return jd5_dj_count;
	}
	public void setJd5_dj_count(String jd5_dj_count) {
		this.jd5_dj_count = jd5_dj_count;
	}
	public String getJd5_jj_count() {
		return jd5_jj_count;
	}
	public void setJd5_jj_count(String jd5_jj_count) {
		this.jd5_jj_count = jd5_jj_count;
	}
	public String getJd5_wj_count() {
		return jd5_wj_count;
	}
	public void setJd5_wj_count(String jd5_wj_count) {
		this.jd5_wj_count = jd5_wj_count;
	}
	public String getJd6_dj_count() {
		return jd6_dj_count;
	}
	public void setJd6_dj_count(String jd6_dj_count) {
		this.jd6_dj_count = jd6_dj_count;
	}
	public String getJd6_jj_count() {
		return jd6_jj_count;
	}
	public void setJd6_jj_count(String jd6_jj_count) {
		this.jd6_jj_count = jd6_jj_count;
	}
	public String getJd6_wj_count() {
		return jd6_wj_count;
	}
	public void setJd6_wj_count(String jd6_wj_count) {
		this.jd6_wj_count = jd6_wj_count;
	}
	public String getJd1_dj_total() {
		return jd1_dj_total;
	}
	public void setJd1_dj_total(String jd1_dj_total) {
		this.jd1_dj_total = jd1_dj_total;
	}
	public String getJd1_jj_total() {
		return jd1_jj_total;
	}
	public void setJd1_jj_total(String jd1_jj_total) {
		this.jd1_jj_total = jd1_jj_total;
	}
	public String getJd1_wj_total() {
		return jd1_wj_total;
	}
	public void setJd1_wj_total(String jd1_wj_total) {
		this.jd1_wj_total = jd1_wj_total;
	}
	public String getJd2_dj_total() {
		return jd2_dj_total;
	}
	public void setJd2_dj_total(String jd2_dj_total) {
		this.jd2_dj_total = jd2_dj_total;
	}
	public String getJd2_jj_total() {
		return jd2_jj_total;
	}
	public void setJd2_jj_total(String jd2_jj_total) {
		this.jd2_jj_total = jd2_jj_total;
	}
	public String getJd2_wj_total() {
		return jd2_wj_total;
	}
	public void setJd2_wj_total(String jd2_wj_total) {
		this.jd2_wj_total = jd2_wj_total;
	}
	public String getJd3_dj_total() {
		return jd3_dj_total;
	}
	public void setJd3_dj_total(String jd3_dj_total) {
		this.jd3_dj_total = jd3_dj_total;
	}
	public String getJd3_jj_total() {
		return jd3_jj_total;
	}
	public void setJd3_jj_total(String jd3_jj_total) {
		this.jd3_jj_total = jd3_jj_total;
	}
	public String getJd3_wj_total() {
		return jd3_wj_total;
	}
	public void setJd3_wj_total(String jd3_wj_total) {
		this.jd3_wj_total = jd3_wj_total;
	}
	public String getJd4_dj_total() {
		return jd4_dj_total;
	}
	public void setJd4_dj_total(String jd4_dj_total) {
		this.jd4_dj_total = jd4_dj_total;
	}
	public String getJd4_jj_total() {
		return jd4_jj_total;
	}
	public void setJd4_jj_total(String jd4_jj_total) {
		this.jd4_jj_total = jd4_jj_total;
	}
	public String getJd4_wj_total() {
		return jd4_wj_total;
	}
	public void setJd4_wj_total(String jd4_wj_total) {
		this.jd4_wj_total = jd4_wj_total;
	}
	public String getJd5_dj_total() {
		return jd5_dj_total;
	}
	public void setJd5_dj_total(String jd5_dj_total) {
		this.jd5_dj_total = jd5_dj_total;
	}
	public String getJd5_jj_total() {
		return jd5_jj_total;
	}
	public void setJd5_jj_total(String jd5_jj_total) {
		this.jd5_jj_total = jd5_jj_total;
	}
	public String getJd5_wj_total() {
		return jd5_wj_total;
	}
	public void setJd5_wj_total(String jd5_wj_total) {
		this.jd5_wj_total = jd5_wj_total;
	}
	public String getJd6_dj_total() {
		return jd6_dj_total;
	}
	public void setJd6_dj_total(String jd6_dj_total) {
		this.jd6_dj_total = jd6_dj_total;
	}
	public String getJd6_jj_total() {
		return jd6_jj_total;
	}
	public void setJd6_jj_total(String jd6_jj_total) {
		this.jd6_jj_total = jd6_jj_total;
	}
	public String getJd6_wj_total() {
		return jd6_wj_total;
	}
	public void setJd6_wj_total(String jd6_wj_total) {
		this.jd6_wj_total = jd6_wj_total;
	}
	public String getAll_total() {
		return all_total;
	}
	public void setAll_total(String all_total) {
		this.all_total = all_total;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Integer getLr_count() {
		return lr_count;
	}
	public void setLr_count(Integer lr_count) {
		this.lr_count = lr_count;
	}
	public Integer getSh_count() {
		return sh_count;
	}
	public void setSh_count(Integer sh_count) {
		this.sh_count = sh_count;
	}
	public Integer getQf_count() {
		return qf_count;
	}
	public void setQf_count(Integer qf_count) {
		this.qf_count = qf_count;
	}
	public Integer getDy_count() {
		return dy_count;
	}
	public void setDy_count(Integer dy_count) {
		this.dy_count = dy_count;
	}
	public Integer getLq_count() {
		return lq_count;
	}
	public void setLq_count(Integer lq_count) {
		this.lq_count = lq_count;
	}
	public Integer getGd_count() {
		return gd_count;
	}
	public void setGd_count(Integer gd_count) {
		this.gd_count = gd_count;
	}
	public Integer getLr_total() {
		return lr_total;
	}
	public void setLr_total(Integer lr_total) {
		this.lr_total = lr_total;
	}
	public Integer getSh_total() {
		return sh_total;
	}
	public void setSh_total(Integer sh_total) {
		this.sh_total = sh_total;
	}
	public Integer getQf_total() {
		return qf_total;
	}
	public void setQf_total(Integer qf_total) {
		this.qf_total = qf_total;
	}
	public Integer getDy_total() {
		return dy_total;
	}
	public void setDy_total(Integer dy_total) {
		this.dy_total = dy_total;
	}
	public Integer getLq_total() {
		return lq_total;
	}
	public void setLq_total(Integer lq_total) {
		this.lq_total = lq_total;
	}
	public Integer getGd_total() {
		return gd_total;
	}
	public void setGd_total(Integer gd_total) {
		this.gd_total = gd_total;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public Integer getDyhgz_count() {
		return dyhgz_count;
	}
	public void setDyhgz_count(Integer dyhgz_count) {
		this.dyhgz_count = dyhgz_count;
	}
	public Integer getDyhgz_total() {
		return dyhgz_total;
	}
	public void setDyhgz_total(Integer dyhgz_total) {
		this.dyhgz_total = dyhgz_total;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCate_sort() {
		return cate_sort;
	}
	public void setCate_sort(String cate_sort) {
		this.cate_sort = cate_sort;
	}
	public String getDev_p_count() {
		return dev_p_count;
	}
	public void setDev_p_count(String dev_p_count) {
		this.dev_p_count = dev_p_count;
	}
	public String getRep_p_count() {
		return rep_p_count;
	}
	public void setRep_p_count(String rep_p_count) {
		this.rep_p_count = rep_p_count;
	}
	public String getDev_lq_count() {
		return dev_lq_count;
	}
	public void setDev_lq_count(String dev_lq_count) {
		this.dev_lq_count = dev_lq_count;
	}
	public String getRep_lq_count() {
		return rep_lq_count;
	}
	public void setRep_lq_count(String rep_lq_count) {
		this.rep_lq_count = rep_lq_count;
	}

}
