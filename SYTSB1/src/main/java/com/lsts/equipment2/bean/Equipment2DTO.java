package com.lsts.equipment2.bean;

import java.util.Date;

/**
 * 设备预警、设备统计数据传输对象 EquipmentDTO
 * 
 * @author GaoYa
 * @date 2014-04-17 下午02:34:00
 */
public class Equipment2DTO {

	// Fields
	private String id;
	private String eq_name; // 设备名称
	private String eq_type; // 设备类型
	private int eq_validate_count; 	// 可用数量
	private String apply_name; 		// 借用人
	private Date apply_end_date; 	// 需求结束时间
	private float warn_days;		// 设备预警天数
	private String eq_status; 		// 设备状态 码表：BASE_EQ_STATUS
	private String eq_use_status; 	// 设备使用状态 码表：BASE_EQ_USE_STATUS

	/*设备统计*/
	/*设备数量（台）*/
	private int jy_count;		// 检验设备数量
	private int jy_cy_count;		// 承压检验设备数量
	private int jy_jd_count;		// 机电检验设备数量
	private int jy_nx_count;		// 能效测试设备数量
	private int jy_jz_count;		// 介质检验设备数量
	private int jy_aqf_count;		// 安全阀检验设备数量
	private int bg_count;		// 办公设备数量
	private int bg_jj_count;		// 办公家具数量
	private int bg_dz_count;		// 电子设备数量
	private int jl_count;		// 计量器具数量
	private int total_count;	// 设备总数
	
	/*设备价值（金额）*/
	private float jy_amount;		// 检验设备总额
	private float jy_cy_amount;		// 承压检验设备总额
	private float jy_jd_amount;		// 机电检验设备总额
	private float jy_nx_amount;		// 能效测试设备总额
	private float jy_jz_amount;		// 介质检验设备总额
	private float jy_aqf_amount;		// 安全阀检验设备总额
	private float bg_amount;		// 办公设备总额
	private float bg_jj_amount;		// 办公家具总额
	private float bg_dz_amount;		// 电子设备总额
	private float jl_amount;		// 计量器具总额
	private float total_amount;	// 设备总额
	
	/*完好在用数量（台）*/
	private int wh_jy_count;	// 完好在用检验设备数量
	private int wh_cy_count;		// 完好在用承压检验设备数量
	private int wh_jd_count;		// 完好在用机电检验设备数量
	private int wh_nx_count;		// 完好在用能效测试设备数量
	private int wh_jz_count;		// 完好在用介质检验设备数量
	private int wh_aqf_count;		// 完好在用安全阀检验设备数量
	private int wh_bg_count;	// 完好在用办公设备数量
	private int wh_jj_count;		// 完好在用办公家具数量
	private int wh_dz_count;		// 完好在用电子设备数量
	private int wh_jl_count;	// 完好在用计量器具数量
	private int wh_total_count;	// 完好在用设备总数
	
	/*待维修数量（台）*/
	private int dx_jy_count;	// 待维修检验设备数量
	private int dx_cy_count;		// 待维修承压检验设备数量
	private int dx_jd_count;		// 待维修机电检验设备数量
	private int dx_nx_count;		// 待维修能效测试设备数量
	private int dx_jz_count;		// 待维修介质检验设备数量
	private int dx_aqf_count;		// 待维修安全阀检验设备数量
	private int dx_bg_count;	// 待维修办公设备数量
	private int dx_jj_count;		// 待维修办公家具数量
	private int dx_dz_count;		// 待维修电子设备数量
	private int dx_jl_count;	// 待维修计量器具数量
	private int dx_total_count;	// 待维修设备总数
	
	/*待报废数量（台）*/
	private int df_jy_count;	// 待报废检验设备数量
	private int df_cy_count;		// 待报废承压检验设备数量
	private int df_jd_count;		// 待报废机电检验设备数量
	private int df_nx_count;		// 待报废能效测试设备数量
	private int df_jz_count;		// 待报废介质检验设备数量
	private int df_aqf_count;		// 待报废安全阀检验设备数量
	private int df_bg_count;	// 待报废办公设备数量
	private int df_jj_count;		// 待报废办公家具数量
	private int df_dz_count;		// 待报废电子设备数量
	private int df_jl_count;	// 待报废计量器具数量
	private int df_total_count;	// 待报废设备总数
	
	/*已报废数量（台）*/
	private int yf_jy_count;	// 已报废检验设备数量
	private int yf_cy_count;		// 已报废承压检验设备数量
	private int yf_jd_count;		// 已报废机电检验设备数量
	private int yf_nx_count;		// 已报废能效测试设备数量
	private int yf_jz_count;		// 已报废介质检验设备数量
	private int yf_aqf_count;		// 已报废安全阀检验设备数量
	private int yf_bg_count;	// 已报废办公设备数量
	private int yf_jj_count;		// 已报废办公家具数量
	private int yf_dz_count;		// 已报废电子设备数量
	private int yf_jl_count;	// 已报废计量器具数量
	private int yf_total_count;	// 已报废设备总数
	
	/*已报废设备金额*/
	private float yf_jy_amount;	// 已报废检验设备总额
	private float yf_cy_amount;		// 已报废承压检验设备总额
	private float yf_jd_amount;		// 已报废机电检验设备总额
	private float yf_nx_amount;		// 已报废能效测试设备总额
	private float yf_jz_amount;		// 已报废介质检验设备总额
	private float yf_aqf_amount;		// 已报废安全阀检验设备总额
	private float yf_bg_amount;	// 已报废办公设备总额
	private float yf_jj_amount;		// 已报废办公家具总额
	private float yf_dz_amount;		// 已报废电子设备总额
	private float yf_jl_amount;	// 已报废计量器具总额
	private float yf_total_amount;// 已报废设备总额
	
	/*已停用数量（台）*/
	private int ty_jy_count;	// 已停用检验设备数量
	private int ty_cy_count;		// 已停用承压检验设备数量
	private int ty_jd_count;		// 已停用机电检验设备数量
	private int ty_nx_count;		// 已停用能效测试设备数量
	private int ty_jz_count;		// 已停用介质检验设备数量
	private int ty_aqf_count;		// 已停用安全阀检验设备数量
	private int ty_bg_count;	// 已停用办公设备数量
	private int ty_jj_count;		// 已停用办公家具数量
	private int ty_dz_count;		// 已停用电子设备数量
	private int ty_jl_count;	// 已停用计量器具数量
	private int ty_total_count;	// 已停用设备总数
	
	/*需检定设备数量（台）*/
	private int jd_jy_count;	// 需检定检验设备数量
	private int jd_cy_count;		// 需检定承压检验设备数量
	private int jd_jd_count;		// 需检定机电检验设备数量
	private int jd_nx_count;		// 需检定能效测试设备数量
	private int jd_jz_count;		// 需检定介质检验设备数量
	private int jd_aqf_count;		// 需检定安全阀检验设备数量
	private int jd_bg_count;	// 需检定办公设备数量
	private int jd_jj_count;		// 需检定办公家具数量
	private int jd_dz_count;		// 需检定电子设备数量
	private int jd_jl_count;	// 需检定计量器具数量
	private int jd_total_count;	// 需检定设备总数
	
	/*需校准设备数量（台）*/
	private int jz_jy_count;	// 需校准检验设备数量
	private int jz_cy_count;		// 需校准承压检验设备数量
	private int jz_jd_count;		// 需校准机电检验设备数量
	private int jz_nx_count;		// 需校准能效测试设备数量
	private int jz_jz_count;		// 需校准介质检验设备数量
	private int jz_aqf_count;		// 需校准安全阀检验设备数量
	private int jz_bg_count;	// 需校准办公设备数量
	private int jz_jj_count;		// 需校准办公家具数量
	private int jz_dz_count;		// 需校准电子设备数量
	private int jz_jl_count;	// 需校准计量器具数量
	private int jz_total_count;	// 需校准设备总数

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEq_name() {
		return eq_name;
	}

	public void setEq_name(String eq_name) {
		this.eq_name = eq_name;
	}

	public String getEq_type() {
		return eq_type;
	}

	public void setEq_type(String eq_type) {
		this.eq_type = eq_type;
	}

	public int getEq_validate_count() {
		return eq_validate_count;
	}

	public void setEq_validate_count(int eq_validate_count) {
		this.eq_validate_count = eq_validate_count;
	}

	public String getApply_name() {
		return apply_name;
	}

	public void setApply_name(String apply_name) {
		this.apply_name = apply_name;
	}

	public float getWarn_days() {
		return warn_days;
	}

	public void setWarn_days(float warn_days) {
		this.warn_days = warn_days;
	}

	public String getEq_status() {
		return eq_status;
	}

	public void setEq_status(String eq_status) {
		this.eq_status = eq_status;
	}

	public String getEq_use_status() {
		return eq_use_status;
	}

	public void setEq_use_status(String eq_use_status) {
		this.eq_use_status = eq_use_status;
	}

	public Date getApply_end_date() {
		return apply_end_date;
	}

	public void setApply_end_date(Date apply_end_date) {
		this.apply_end_date = apply_end_date;
	}

	public int getJy_count() {
		return jy_count;
	}

	public void setJy_count(int jy_count) {
		this.jy_count = jy_count;
	}

	public int getJy_cy_count() {
		return jy_cy_count;
	}

	public void setJy_cy_count(int jy_cy_count) {
		this.jy_cy_count = jy_cy_count;
	}

	public int getJy_jd_count() {
		return jy_jd_count;
	}

	public void setJy_jd_count(int jy_jd_count) {
		this.jy_jd_count = jy_jd_count;
	}

	public int getJy_nx_count() {
		return jy_nx_count;
	}

	public void setJy_nx_count(int jy_nx_count) {
		this.jy_nx_count = jy_nx_count;
	}

	public int getJy_jz_count() {
		return jy_jz_count;
	}

	public void setJy_jz_count(int jy_jz_count) {
		this.jy_jz_count = jy_jz_count;
	}

	public int getJy_aqf_count() {
		return jy_aqf_count;
	}

	public void setJy_aqf_count(int jy_aqf_count) {
		this.jy_aqf_count = jy_aqf_count;
	}

	public int getBg_count() {
		return bg_count;
	}

	public void setBg_count(int bg_count) {
		this.bg_count = bg_count;
	}

	public int getBg_jj_count() {
		return bg_jj_count;
	}

	public void setBg_jj_count(int bg_jj_count) {
		this.bg_jj_count = bg_jj_count;
	}

	public int getBg_dz_count() {
		return bg_dz_count;
	}

	public void setBg_dz_count(int bg_dz_count) {
		this.bg_dz_count = bg_dz_count;
	}

	public int getJl_count() {
		return jl_count;
	}

	public void setJl_count(int jl_count) {
		this.jl_count = jl_count;
	}

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

	public float getJy_amount() {
		return jy_amount;
	}

	public void setJy_amount(float jy_amount) {
		this.jy_amount = jy_amount;
	}

	public float getJy_cy_amount() {
		return jy_cy_amount;
	}

	public void setJy_cy_amount(float jy_cy_amount) {
		this.jy_cy_amount = jy_cy_amount;
	}

	public float getJy_jd_amount() {
		return jy_jd_amount;
	}

	public void setJy_jd_amount(float jy_jd_amount) {
		this.jy_jd_amount = jy_jd_amount;
	}

	public float getJy_nx_amount() {
		return jy_nx_amount;
	}

	public void setJy_nx_amount(float jy_nx_amount) {
		this.jy_nx_amount = jy_nx_amount;
	}

	public float getJy_jz_amount() {
		return jy_jz_amount;
	}

	public void setJy_jz_amount(float jy_jz_amount) {
		this.jy_jz_amount = jy_jz_amount;
	}

	public float getJy_aqf_amount() {
		return jy_aqf_amount;
	}

	public void setJy_aqf_amount(float jy_aqf_amount) {
		this.jy_aqf_amount = jy_aqf_amount;
	}

	public float getBg_amount() {
		return bg_amount;
	}

	public void setBg_amount(float bg_amount) {
		this.bg_amount = bg_amount;
	}

	public float getBg_jj_amount() {
		return bg_jj_amount;
	}

	public void setBg_jj_amount(float bg_jj_amount) {
		this.bg_jj_amount = bg_jj_amount;
	}

	public float getBg_dz_amount() {
		return bg_dz_amount;
	}

	public void setBg_dz_amount(float bg_dz_amount) {
		this.bg_dz_amount = bg_dz_amount;
	}

	public float getJl_amount() {
		return jl_amount;
	}

	public void setJl_amount(float jl_amount) {
		this.jl_amount = jl_amount;
	}

	public float getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(float total_amount) {
		this.total_amount = total_amount;
	}

	public int getWh_jy_count() {
		return wh_jy_count;
	}

	public void setWh_jy_count(int wh_jy_count) {
		this.wh_jy_count = wh_jy_count;
	}

	public int getWh_cy_count() {
		return wh_cy_count;
	}

	public void setWh_cy_count(int wh_cy_count) {
		this.wh_cy_count = wh_cy_count;
	}

	public int getWh_jd_count() {
		return wh_jd_count;
	}

	public void setWh_jd_count(int wh_jd_count) {
		this.wh_jd_count = wh_jd_count;
	}

	public int getWh_nx_count() {
		return wh_nx_count;
	}

	public void setWh_nx_count(int wh_nx_count) {
		this.wh_nx_count = wh_nx_count;
	}

	public int getWh_jz_count() {
		return wh_jz_count;
	}

	public void setWh_jz_count(int wh_jz_count) {
		this.wh_jz_count = wh_jz_count;
	}

	public int getWh_aqf_count() {
		return wh_aqf_count;
	}

	public void setWh_aqf_count(int wh_aqf_count) {
		this.wh_aqf_count = wh_aqf_count;
	}

	public int getWh_bg_count() {
		return wh_bg_count;
	}

	public void setWh_bg_count(int wh_bg_count) {
		this.wh_bg_count = wh_bg_count;
	}

	public int getWh_jj_count() {
		return wh_jj_count;
	}

	public void setWh_jj_count(int wh_jj_count) {
		this.wh_jj_count = wh_jj_count;
	}

	public int getWh_dz_count() {
		return wh_dz_count;
	}

	public void setWh_dz_count(int wh_dz_count) {
		this.wh_dz_count = wh_dz_count;
	}

	public int getWh_jl_count() {
		return wh_jl_count;
	}

	public void setWh_jl_count(int wh_jl_count) {
		this.wh_jl_count = wh_jl_count;
	}

	public int getWh_total_count() {
		return wh_total_count;
	}

	public void setWh_total_count(int wh_total_count) {
		this.wh_total_count = wh_total_count;
	}

	public int getDx_jy_count() {
		return dx_jy_count;
	}

	public void setDx_jy_count(int dx_jy_count) {
		this.dx_jy_count = dx_jy_count;
	}

	public int getDx_cy_count() {
		return dx_cy_count;
	}

	public void setDx_cy_count(int dx_cy_count) {
		this.dx_cy_count = dx_cy_count;
	}

	public int getDx_jd_count() {
		return dx_jd_count;
	}

	public void setDx_jd_count(int dx_jd_count) {
		this.dx_jd_count = dx_jd_count;
	}

	public int getDx_nx_count() {
		return dx_nx_count;
	}

	public void setDx_nx_count(int dx_nx_count) {
		this.dx_nx_count = dx_nx_count;
	}

	public int getDx_jz_count() {
		return dx_jz_count;
	}

	public void setDx_jz_count(int dx_jz_count) {
		this.dx_jz_count = dx_jz_count;
	}

	public int getDx_aqf_count() {
		return dx_aqf_count;
	}

	public void setDx_aqf_count(int dx_aqf_count) {
		this.dx_aqf_count = dx_aqf_count;
	}

	public int getDx_bg_count() {
		return dx_bg_count;
	}

	public void setDx_bg_count(int dx_bg_count) {
		this.dx_bg_count = dx_bg_count;
	}

	public int getDx_jj_count() {
		return dx_jj_count;
	}

	public void setDx_jj_count(int dx_jj_count) {
		this.dx_jj_count = dx_jj_count;
	}

	public int getDx_dz_count() {
		return dx_dz_count;
	}

	public void setDx_dz_count(int dx_dz_count) {
		this.dx_dz_count = dx_dz_count;
	}

	public int getDx_jl_count() {
		return dx_jl_count;
	}

	public void setDx_jl_count(int dx_jl_count) {
		this.dx_jl_count = dx_jl_count;
	}

	public int getDx_total_count() {
		return dx_total_count;
	}

	public void setDx_total_count(int dx_total_count) {
		this.dx_total_count = dx_total_count;
	}

	public int getDf_jy_count() {
		return df_jy_count;
	}

	public void setDf_jy_count(int df_jy_count) {
		this.df_jy_count = df_jy_count;
	}

	public int getDf_cy_count() {
		return df_cy_count;
	}

	public void setDf_cy_count(int df_cy_count) {
		this.df_cy_count = df_cy_count;
	}

	public int getDf_jd_count() {
		return df_jd_count;
	}

	public void setDf_jd_count(int df_jd_count) {
		this.df_jd_count = df_jd_count;
	}

	public int getDf_nx_count() {
		return df_nx_count;
	}

	public void setDf_nx_count(int df_nx_count) {
		this.df_nx_count = df_nx_count;
	}

	public int getDf_jz_count() {
		return df_jz_count;
	}

	public void setDf_jz_count(int df_jz_count) {
		this.df_jz_count = df_jz_count;
	}

	public int getDf_aqf_count() {
		return df_aqf_count;
	}

	public void setDf_aqf_count(int df_aqf_count) {
		this.df_aqf_count = df_aqf_count;
	}

	public int getDf_bg_count() {
		return df_bg_count;
	}

	public void setDf_bg_count(int df_bg_count) {
		this.df_bg_count = df_bg_count;
	}

	public int getDf_jj_count() {
		return df_jj_count;
	}

	public void setDf_jj_count(int df_jj_count) {
		this.df_jj_count = df_jj_count;
	}

	public int getDf_dz_count() {
		return df_dz_count;
	}

	public void setDf_dz_count(int df_dz_count) {
		this.df_dz_count = df_dz_count;
	}

	public int getDf_jl_count() {
		return df_jl_count;
	}

	public void setDf_jl_count(int df_jl_count) {
		this.df_jl_count = df_jl_count;
	}

	public int getDf_total_count() {
		return df_total_count;
	}

	public void setDf_total_count(int df_total_count) {
		this.df_total_count = df_total_count;
	}

	public int getYf_jy_count() {
		return yf_jy_count;
	}

	public void setYf_jy_count(int yf_jy_count) {
		this.yf_jy_count = yf_jy_count;
	}

	public int getYf_cy_count() {
		return yf_cy_count;
	}

	public void setYf_cy_count(int yf_cy_count) {
		this.yf_cy_count = yf_cy_count;
	}

	public int getYf_jd_count() {
		return yf_jd_count;
	}

	public void setYf_jd_count(int yf_jd_count) {
		this.yf_jd_count = yf_jd_count;
	}

	public int getYf_nx_count() {
		return yf_nx_count;
	}

	public void setYf_nx_count(int yf_nx_count) {
		this.yf_nx_count = yf_nx_count;
	}

	public int getYf_jz_count() {
		return yf_jz_count;
	}

	public void setYf_jz_count(int yf_jz_count) {
		this.yf_jz_count = yf_jz_count;
	}

	public int getYf_aqf_count() {
		return yf_aqf_count;
	}

	public void setYf_aqf_count(int yf_aqf_count) {
		this.yf_aqf_count = yf_aqf_count;
	}

	public int getYf_bg_count() {
		return yf_bg_count;
	}

	public void setYf_bg_count(int yf_bg_count) {
		this.yf_bg_count = yf_bg_count;
	}

	public int getYf_jj_count() {
		return yf_jj_count;
	}

	public void setYf_jj_count(int yf_jj_count) {
		this.yf_jj_count = yf_jj_count;
	}

	public int getYf_dz_count() {
		return yf_dz_count;
	}

	public void setYf_dz_count(int yf_dz_count) {
		this.yf_dz_count = yf_dz_count;
	}

	public int getYf_jl_count() {
		return yf_jl_count;
	}

	public void setYf_jl_count(int yf_jl_count) {
		this.yf_jl_count = yf_jl_count;
	}

	public int getYf_total_count() {
		return yf_total_count;
	}

	public void setYf_total_count(int yf_total_count) {
		this.yf_total_count = yf_total_count;
	}

	public float getYf_jy_amount() {
		return yf_jy_amount;
	}

	public void setYf_jy_amount(float yf_jy_amount) {
		this.yf_jy_amount = yf_jy_amount;
	}

	public float getYf_cy_amount() {
		return yf_cy_amount;
	}

	public void setYf_cy_amount(float yf_cy_amount) {
		this.yf_cy_amount = yf_cy_amount;
	}

	public float getYf_jd_amount() {
		return yf_jd_amount;
	}

	public void setYf_jd_amount(float yf_jd_amount) {
		this.yf_jd_amount = yf_jd_amount;
	}

	public float getYf_nx_amount() {
		return yf_nx_amount;
	}

	public void setYf_nx_amount(float yf_nx_amount) {
		this.yf_nx_amount = yf_nx_amount;
	}

	public float getYf_jz_amount() {
		return yf_jz_amount;
	}

	public void setYf_jz_amount(float yf_jz_amount) {
		this.yf_jz_amount = yf_jz_amount;
	}

	public float getYf_aqf_amount() {
		return yf_aqf_amount;
	}

	public void setYf_aqf_amount(float yf_aqf_amount) {
		this.yf_aqf_amount = yf_aqf_amount;
	}

	public float getYf_bg_amount() {
		return yf_bg_amount;
	}

	public void setYf_bg_amount(float yf_bg_amount) {
		this.yf_bg_amount = yf_bg_amount;
	}

	public float getYf_jj_amount() {
		return yf_jj_amount;
	}

	public void setYf_jj_amount(float yf_jj_amount) {
		this.yf_jj_amount = yf_jj_amount;
	}

	public float getYf_dz_amount() {
		return yf_dz_amount;
	}

	public void setYf_dz_amount(float yf_dz_amount) {
		this.yf_dz_amount = yf_dz_amount;
	}

	public float getYf_jl_amount() {
		return yf_jl_amount;
	}

	public void setYf_jl_amount(float yf_jl_amount) {
		this.yf_jl_amount = yf_jl_amount;
	}

	public float getYf_total_amount() {
		return yf_total_amount;
	}

	public void setYf_total_amount(float yf_total_amount) {
		this.yf_total_amount = yf_total_amount;
	}

	public int getTy_jy_count() {
		return ty_jy_count;
	}

	public void setTy_jy_count(int ty_jy_count) {
		this.ty_jy_count = ty_jy_count;
	}

	public int getTy_cy_count() {
		return ty_cy_count;
	}

	public void setTy_cy_count(int ty_cy_count) {
		this.ty_cy_count = ty_cy_count;
	}

	public int getTy_jd_count() {
		return ty_jd_count;
	}

	public void setTy_jd_count(int ty_jd_count) {
		this.ty_jd_count = ty_jd_count;
	}

	public int getTy_nx_count() {
		return ty_nx_count;
	}

	public void setTy_nx_count(int ty_nx_count) {
		this.ty_nx_count = ty_nx_count;
	}

	public int getTy_jz_count() {
		return ty_jz_count;
	}

	public void setTy_jz_count(int ty_jz_count) {
		this.ty_jz_count = ty_jz_count;
	}

	public int getTy_aqf_count() {
		return ty_aqf_count;
	}

	public void setTy_aqf_count(int ty_aqf_count) {
		this.ty_aqf_count = ty_aqf_count;
	}

	public int getTy_bg_count() {
		return ty_bg_count;
	}

	public void setTy_bg_count(int ty_bg_count) {
		this.ty_bg_count = ty_bg_count;
	}

	public int getTy_jj_count() {
		return ty_jj_count;
	}

	public void setTy_jj_count(int ty_jj_count) {
		this.ty_jj_count = ty_jj_count;
	}

	public int getTy_dz_count() {
		return ty_dz_count;
	}

	public void setTy_dz_count(int ty_dz_count) {
		this.ty_dz_count = ty_dz_count;
	}

	public int getTy_jl_count() {
		return ty_jl_count;
	}

	public void setTy_jl_count(int ty_jl_count) {
		this.ty_jl_count = ty_jl_count;
	}

	public int getTy_total_count() {
		return ty_total_count;
	}

	public void setTy_total_count(int ty_total_count) {
		this.ty_total_count = ty_total_count;
	}

	public int getJd_jy_count() {
		return jd_jy_count;
	}

	public void setJd_jy_count(int jd_jy_count) {
		this.jd_jy_count = jd_jy_count;
	}

	public int getJd_cy_count() {
		return jd_cy_count;
	}

	public void setJd_cy_count(int jd_cy_count) {
		this.jd_cy_count = jd_cy_count;
	}

	public int getJd_jd_count() {
		return jd_jd_count;
	}

	public void setJd_jd_count(int jd_jd_count) {
		this.jd_jd_count = jd_jd_count;
	}

	public int getJd_nx_count() {
		return jd_nx_count;
	}

	public void setJd_nx_count(int jd_nx_count) {
		this.jd_nx_count = jd_nx_count;
	}

	public int getJd_jz_count() {
		return jd_jz_count;
	}

	public void setJd_jz_count(int jd_jz_count) {
		this.jd_jz_count = jd_jz_count;
	}

	public int getJd_aqf_count() {
		return jd_aqf_count;
	}

	public void setJd_aqf_count(int jd_aqf_count) {
		this.jd_aqf_count = jd_aqf_count;
	}

	public int getJd_bg_count() {
		return jd_bg_count;
	}

	public void setJd_bg_count(int jd_bg_count) {
		this.jd_bg_count = jd_bg_count;
	}

	public int getJd_jj_count() {
		return jd_jj_count;
	}

	public void setJd_jj_count(int jd_jj_count) {
		this.jd_jj_count = jd_jj_count;
	}

	public int getJd_dz_count() {
		return jd_dz_count;
	}

	public void setJd_dz_count(int jd_dz_count) {
		this.jd_dz_count = jd_dz_count;
	}

	public int getJd_jl_count() {
		return jd_jl_count;
	}

	public void setJd_jl_count(int jd_jl_count) {
		this.jd_jl_count = jd_jl_count;
	}

	public int getJd_total_count() {
		return jd_total_count;
	}

	public void setJd_total_count(int jd_total_count) {
		this.jd_total_count = jd_total_count;
	}

	public int getJz_jy_count() {
		return jz_jy_count;
	}

	public void setJz_jy_count(int jz_jy_count) {
		this.jz_jy_count = jz_jy_count;
	}

	public int getJz_cy_count() {
		return jz_cy_count;
	}

	public void setJz_cy_count(int jz_cy_count) {
		this.jz_cy_count = jz_cy_count;
	}

	public int getJz_jd_count() {
		return jz_jd_count;
	}

	public void setJz_jd_count(int jz_jd_count) {
		this.jz_jd_count = jz_jd_count;
	}

	public int getJz_nx_count() {
		return jz_nx_count;
	}

	public void setJz_nx_count(int jz_nx_count) {
		this.jz_nx_count = jz_nx_count;
	}

	public int getJz_jz_count() {
		return jz_jz_count;
	}

	public void setJz_jz_count(int jz_jz_count) {
		this.jz_jz_count = jz_jz_count;
	}

	public int getJz_aqf_count() {
		return jz_aqf_count;
	}

	public void setJz_aqf_count(int jz_aqf_count) {
		this.jz_aqf_count = jz_aqf_count;
	}

	public int getJz_bg_count() {
		return jz_bg_count;
	}

	public void setJz_bg_count(int jz_bg_count) {
		this.jz_bg_count = jz_bg_count;
	}

	public int getJz_jj_count() {
		return jz_jj_count;
	}

	public void setJz_jj_count(int jz_jj_count) {
		this.jz_jj_count = jz_jj_count;
	}

	public int getJz_dz_count() {
		return jz_dz_count;
	}

	public void setJz_dz_count(int jz_dz_count) {
		this.jz_dz_count = jz_dz_count;
	}

	public int getJz_jl_count() {
		return jz_jl_count;
	}

	public void setJz_jl_count(int jz_jl_count) {
		this.jz_jl_count = jz_jl_count;
	}

	public int getJz_total_count() {
		return jz_total_count;
	}

	public void setJz_total_count(int jz_total_count) {
		this.jz_total_count = jz_total_count;
	}

}