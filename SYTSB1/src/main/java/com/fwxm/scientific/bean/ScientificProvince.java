package com.fwxm.scientific.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.khnt.core.crud.bean.BaseEntity;

import java.util.Date;

/*******************************************************************************
 * 
 * <p>
 *  
 * </p>
 * 
 * @ClassName ScientificProvince
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2018-01-15 13:24:16
 */
@Entity
@Table(name = "TJY2_SCIENTIFIC_PROVINCE")
public class ScientificProvince implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String id;//
	private String projectName;//项目名称
	private String researchField;//研究领域
	private String declareUnit;//申报单位
	private String projectHeadId;//项目负责人id
	private String projectHead;//项目负责人
	private String phoneTel;//联系电话
	private String unitGk;//归口部门
	private Date startDate;//开始日期
	private Date endDate;//结束日期
	private Date submitDate;//报送日期
	private Date createDate;//填写日期
	private String createId;//填写人
	private String createMan;//填写人
	//private String p100001;//第一页内容
	private String p100002;//关键词
	//private String p200001;//第二页内容
	//private String p300001;//第三页内容
	//private String p400001;//第四页内容
	//private String p500001;//第五页内容
	//private String p600001;//第六页内容
	private String p700001;//第七页内容
	private String p700002;//
	private String p700003;//
	private String p700004;//
	private String p700005;//
	private String p700006;//
	private String p700007;//
	private String p700008;//
	private String p700009;//
	private String p700010;//
	private String p700011;//
	private String p700012;//
	private String p700013;//
	private String p700014;//
	private String p800001;//第八页内容
	private String p800002;//
	private String p800003;//
	private String p800004;//
	private String p800005;//
	private String p800006;//
	private String p800007;//
	private String p800008;//
	private String p800009;//
	private String p8000010;//
	private String p8000011;//
	private String p8000012;//
	private String p8000013;//
	private String p8000014;//
	private String p8000015;//
	private String p8000016;//
	private String p8000017;//
	private String p8000018;//
	private String p8000019;//
	private String p8000020;//
	private String p8000021;//
	private String p8000022;//
	private String p8000023;//
	private String p8000024;//
	private String p8000025;//
	private String p8000026;//
	private String p8000027;//
	private String p8000028;//
	private String p8000029;//
	private String p8000030;//
	private String p8000031;//第八页主要研究人员
	private String p8000032;//
	private String p8000033;//
	private String p8000034;//
	private String p8000035;//
	private String p8000036;//
	private String p8000037;//
	private String p8000038;//
	private String p8000039;//
	private String p8000040;//
	private String p8000041;//
	private String p8000042;//
	private String p8000043;//
	private String p8000044;//
	private String p8000045;//
	private String p8000046;//
	private String p8000047;//
	private String p8000048;//
	private String p8000049;//
	private String p8000050;//
	private String p8000051;//
	private String p8000052;//
	private String p8000053;//
	private String p8000054;//
	private String p8000055;//
	private String p8000056;//
	private String p8000057;//
	private String p8000058;//
	private String p8000059;//
	private String p8000060;//
	private String p8000061;//
	private String p8000062;//
	private String p8000063;//
	private String p8000064;//
	private String p8000065;//
	private String p8000066;//
	private String p8000067;//
	private String p8000068;//
	private String p8000069;//
	private String p8000070;//
	private String p8000071;//
	private String p8000072;//
	private String p8000073;//
	private String p8000074;//
	private String p8000075;//
	private String p8000076;//
	private String p8000077;//
	private String p8000078;//
	private String p8000079;//
	private String p8000080;//
	private String p8000081;//
	private String p8000082;//
	private String p8000083;//
	private String p8000084;//
	private String p8000085;//
	private String p8000086;//
	private String p8000087;//
	private String p8000088;//
	private String p8000089;//
	private String p8000090;//
	private String p8000091;//
	private String p8000092;//
	private String p8000093;//
	private String p8000094;//
	private String p8000095;//
	private String p8000096;//
	private String p8000097;//
	private String p8000098;//
	private String p8000099;//
	private String p8000100;//
	private String p8000101;//
	private String p8000102;//
	private String p8000103;//
	private String p8000104;//
	private String p8000105;//
	private String p8000106;//
	private String p8000107;//
	private String p8000108;//
	private String p8000109;//
	private String p8000110;//
	private String p8000111;//
	private String p8000112;//
	private String p8000113;//
	private String p8000114;//
	private String p8000115;//
	private String p8000116;//
	private String p8000117;//
	private String p8000118;//
	private String p8000119;//
	private String p8000120;//
	private String p8000121;//
	private String p8000122;//
	private String p8000123;//
	private String p8000124;//
	private String p8000125;//
	private String p8000126;//
	private String p8000127;//
	private String p8000128;//
	private String p8000129;//
	private String p8000130;//
	private String p8000131;//
	private String p8000132;//
	private String p8000133;//
	private String p8000134;//
	private String p8000135;//
	private String status;//状态
	private String auditName;//审核人
	private String auditId;//审核人id
	private String auditStatus;//审核状态
	private String projectNo;//申报编号
	private String jhNo;//计划编号
	private String classification;//密级
	private Date LastModifyDate;//最后修改时间
	private String LastModifyId;//最后修改人id
	private String LastModifyMan;//最后修改人
	private String remark;//备注
	

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Column(name="PROJECT_NAME")
	public String getProjectName(){
		return projectName;
	}
		
	public void setProjectName(String projectName){
		this.projectName=projectName;
	}

	@Column(name="RESEARCH_FIELD")
	public String getResearchField(){
		return researchField;
	}
		
	public void setResearchField(String researchField){
		this.researchField=researchField;
	}

	@Column(name="DECLARE_UNIT")
	public String getDeclareUnit(){
		return declareUnit;
	}
		
	public void setDeclareUnit(String declareUnit){
		this.declareUnit=declareUnit;
	}

	@Column(name="PROJECT_HEAD_ID")
	public String getProjectHeadId(){
		return projectHeadId;
	}
		
	public void setProjectHeadId(String projectHeadId){
		this.projectHeadId=projectHeadId;
	}

	@Column(name="PROJECT_HEAD")
	public String getProjectHead(){
		return projectHead;
	}
		
	public void setProjectHead(String projectHead){
		this.projectHead=projectHead;
	}

	@Column(name="PHONE_TEL")
	public String getPhoneTel(){
		return phoneTel;
	}
		
	public void setPhoneTel(String phoneTel){
		this.phoneTel=phoneTel;
	}

	@Column(name="UNIT_GK")
	public String getUnitGk(){
		return unitGk;
	}
		
	public void setUnitGk(String unitGk){
		this.unitGk=unitGk;
	}

	@Column(name="START_DATE")
	public Date getStartDate(){
		return startDate;
	}
		
	public void setStartDate(Date startDate){
		this.startDate=startDate;
	}

	@Column(name="END_DATE")
	public Date getEndDate(){
		return endDate;
	}
		
	public void setEndDate(Date endDate){
		this.endDate=endDate;
	}

	@Column(name="SUBMIT_DATE")
	public Date getSubmitDate(){
		return submitDate;
	}
		
	public void setSubmitDate(Date submitDate){
		this.submitDate=submitDate;
	}

	@Column(name="CREATE_DATE")
	public Date getCreateDate(){
		return createDate;
	}
		
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name="CREATE_ID")
	public String getCreateId(){
		return createId;
	}
		
	public void setCreateId(String createId){
		this.createId=createId;
	}
	@Column(name="CREATE_MAN")
	public String getCreateMan() {
		return createMan;
	}
	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	@Column(name="P100002")
	public String getP100002(){
		return p100002;
	}
		
	public void setP100002(String p100002){
		this.p100002=p100002;
	}


	@Column(name="P700001")
	public String getP700001(){
		return p700001;
	}
		
	public void setP700001(String p700001){
		this.p700001=p700001;
	}

	@Column(name="P700002")
	public String getP700002(){
		return p700002;
	}
		
	public void setP700002(String p700002){
		this.p700002=p700002;
	}

	@Column(name="P700003")
	public String getP700003(){
		return p700003;
	}
		
	public void setP700003(String p700003){
		this.p700003=p700003;
	}

	@Column(name="P700004")
	public String getP700004(){
		return p700004;
	}
		
	public void setP700004(String p700004){
		this.p700004=p700004;
	}

	@Column(name="P700005")
	public String getP700005(){
		return p700005;
	}
		
	public void setP700005(String p700005){
		this.p700005=p700005;
	}

	@Column(name="P700006")
	public String getP700006(){
		return p700006;
	}
		
	public void setP700006(String p700006){
		this.p700006=p700006;
	}

	@Column(name="P700007")
	public String getP700007(){
		return p700007;
	}
		
	public void setP700007(String p700007){
		this.p700007=p700007;
	}

	@Column(name="P700008")
	public String getP700008(){
		return p700008;
	}
		
	public void setP700008(String p700008){
		this.p700008=p700008;
	}

	@Column(name="P700009")
	public String getP700009(){
		return p700009;
	}
		
	public void setP700009(String p700009){
		this.p700009=p700009;
	}

	@Column(name="P700010")
	public String getP700010(){
		return p700010;
	}
		
	public void setP700010(String p700010){
		this.p700010=p700010;
	}

	@Column(name="P700011")
	public String getP700011(){
		return p700011;
	}
		
	public void setP700011(String p700011){
		this.p700011=p700011;
	}

	@Column(name="P700012")
	public String getP700012(){
		return p700012;
	}
		
	public void setP700012(String p700012){
		this.p700012=p700012;
	}

	@Column(name="P700013")
	public String getP700013(){
		return p700013;
	}
		
	public void setP700013(String p700013){
		this.p700013=p700013;
	}
	
	@Column(name="P700014")
	public String getP700014(){
		return p700014;
	}
		
	public void setP700014(String p700014){
		this.p700014=p700014;
	}

	@Column(name="P800001")
	public String getP800001(){
		return p800001;
	}
		
	public void setP800001(String p800001){
		this.p800001=p800001;
	}

	@Column(name="P800002")
	public String getP800002(){
		return p800002;
	}
		
	public void setP800002(String p800002){
		this.p800002=p800002;
	}

	@Column(name="P800003")
	public String getP800003(){
		return p800003;
	}
		
	public void setP800003(String p800003){
		this.p800003=p800003;
	}

	@Column(name="P800004")
	public String getP800004(){
		return p800004;
	}
		
	public void setP800004(String p800004){
		this.p800004=p800004;
	}

	@Column(name="P800005")
	public String getP800005(){
		return p800005;
	}
		
	public void setP800005(String p800005){
		this.p800005=p800005;
	}

	@Column(name="P800006")
	public String getP800006(){
		return p800006;
	}
		
	public void setP800006(String p800006){
		this.p800006=p800006;
	}

	@Column(name="P800007")
	public String getP800007(){
		return p800007;
	}
		
	public void setP800007(String p800007){
		this.p800007=p800007;
	}

	@Column(name="P800008")
	public String getP800008(){
		return p800008;
	}
		
	public void setP800008(String p800008){
		this.p800008=p800008;
	}

	@Column(name="P800009")
	public String getP800009(){
		return p800009;
	}
		
	public void setP800009(String p800009){
		this.p800009=p800009;
	}

	@Column(name="P8000010")
	public String getP8000010(){
		return p8000010;
	}
		
	public void setP8000010(String p8000010){
		this.p8000010=p8000010;
	}

	@Column(name="P8000011")
	public String getP8000011(){
		return p8000011;
	}
		
	public void setP8000011(String p8000011){
		this.p8000011=p8000011;
	}

	@Column(name="P8000012")
	public String getP8000012(){
		return p8000012;
	}
		
	public void setP8000012(String p8000012){
		this.p8000012=p8000012;
	}

	@Column(name="P8000013")
	public String getP8000013(){
		return p8000013;
	}
		
	public void setP8000013(String p8000013){
		this.p8000013=p8000013;
	}

	@Column(name="P8000014")
	public String getP8000014(){
		return p8000014;
	}
		
	public void setP8000014(String p8000014){
		this.p8000014=p8000014;
	}

	@Column(name="P8000015")
	public String getP8000015(){
		return p8000015;
	}
		
	public void setP8000015(String p8000015){
		this.p8000015=p8000015;
	}

	@Column(name="P8000016")
	public String getP8000016(){
		return p8000016;
	}
		
	public void setP8000016(String p8000016){
		this.p8000016=p8000016;
	}

	@Column(name="P8000017")
	public String getP8000017(){
		return p8000017;
	}
		
	public void setP8000017(String p8000017){
		this.p8000017=p8000017;
	}

	@Column(name="P8000018")
	public String getP8000018(){
		return p8000018;
	}
		
	public void setP8000018(String p8000018){
		this.p8000018=p8000018;
	}

	@Column(name="P8000019")
	public String getP8000019(){
		return p8000019;
	}
		
	public void setP8000019(String p8000019){
		this.p8000019=p8000019;
	}

	@Column(name="P8000020")
	public String getP8000020(){
		return p8000020;
	}
		
	public void setP8000020(String p8000020){
		this.p8000020=p8000020;
	}

	@Column(name="P8000021")
	public String getP8000021(){
		return p8000021;
	}
		
	public void setP8000021(String p8000021){
		this.p8000021=p8000021;
	}

	@Column(name="P8000022")
	public String getP8000022(){
		return p8000022;
	}
		
	public void setP8000022(String p8000022){
		this.p8000022=p8000022;
	}

	@Column(name="P8000023")
	public String getP8000023(){
		return p8000023;
	}
		
	public void setP8000023(String p8000023){
		this.p8000023=p8000023;
	}

	@Column(name="P8000024")
	public String getP8000024(){
		return p8000024;
	}
		
	public void setP8000024(String p8000024){
		this.p8000024=p8000024;
	}

	@Column(name="P8000025")
	public String getP8000025(){
		return p8000025;
	}
		
	public void setP8000025(String p8000025){
		this.p8000025=p8000025;
	}

	@Column(name="P8000026")
	public String getP8000026(){
		return p8000026;
	}
		
	public void setP8000026(String p8000026){
		this.p8000026=p8000026;
	}

	@Column(name="P8000027")
	public String getP8000027(){
		return p8000027;
	}
		
	public void setP8000027(String p8000027){
		this.p8000027=p8000027;
	}

	@Column(name="P8000028")
	public String getP8000028(){
		return p8000028;
	}
		
	public void setP8000028(String p8000028){
		this.p8000028=p8000028;
	}

	@Column(name="P8000029")
	public String getP8000029(){
		return p8000029;
	}
		
	public void setP8000029(String p8000029){
		this.p8000029=p8000029;
	}

	@Column(name="P8000030")
	public String getP8000030(){
		return p8000030;
	}
		
	public void setP8000030(String p8000030){
		this.p8000030=p8000030;
	}

	@Column(name="P8000031")
	public String getP8000031(){
		return p8000031;
	}
		
	public void setP8000031(String p8000031){
		this.p8000031=p8000031;
	}

	@Column(name="P8000032")
	public String getP8000032(){
		return p8000032;
	}
		
	public void setP8000032(String p8000032){
		this.p8000032=p8000032;
	}

	@Column(name="P8000033")
	public String getP8000033(){
		return p8000033;
	}
		
	public void setP8000033(String p8000033){
		this.p8000033=p8000033;
	}

	@Column(name="P8000034")
	public String getP8000034(){
		return p8000034;
	}
		
	public void setP8000034(String p8000034){
		this.p8000034=p8000034;
	}

	@Column(name="P8000035")
	public String getP8000035(){
		return p8000035;
	}
		
	public void setP8000035(String p8000035){
		this.p8000035=p8000035;
	}

	@Column(name="P8000036")
	public String getP8000036(){
		return p8000036;
	}
		
	public void setP8000036(String p8000036){
		this.p8000036=p8000036;
	}

	@Column(name="P8000037")
	public String getP8000037(){
		return p8000037;
	}
		
	public void setP8000037(String p8000037){
		this.p8000037=p8000037;
	}

	@Column(name="P8000038")
	public String getP8000038(){
		return p8000038;
	}
		
	public void setP8000038(String p8000038){
		this.p8000038=p8000038;
	}

	@Column(name="P8000039")
	public String getP8000039(){
		return p8000039;
	}
		
	public void setP8000039(String p8000039){
		this.p8000039=p8000039;
	}

	@Column(name="P8000040")
	public String getP8000040(){
		return p8000040;
	}
		
	public void setP8000040(String p8000040){
		this.p8000040=p8000040;
	}

	@Column(name="P8000041")
	public String getP8000041(){
		return p8000041;
	}
		
	public void setP8000041(String p8000041){
		this.p8000041=p8000041;
	}

	@Column(name="P8000042")
	public String getP8000042(){
		return p8000042;
	}
		
	public void setP8000042(String p8000042){
		this.p8000042=p8000042;
	}

	@Column(name="P8000043")
	public String getP8000043(){
		return p8000043;
	}
		
	public void setP8000043(String p8000043){
		this.p8000043=p8000043;
	}

	@Column(name="P8000044")
	public String getP8000044(){
		return p8000044;
	}
		
	public void setP8000044(String p8000044){
		this.p8000044=p8000044;
	}

	@Column(name="P8000045")
	public String getP8000045(){
		return p8000045;
	}
		
	public void setP8000045(String p8000045){
		this.p8000045=p8000045;
	}

	@Column(name="P8000046")
	public String getP8000046(){
		return p8000046;
	}
		
	public void setP8000046(String p8000046){
		this.p8000046=p8000046;
	}

	@Column(name="P8000047")
	public String getP8000047(){
		return p8000047;
	}
		
	public void setP8000047(String p8000047){
		this.p8000047=p8000047;
	}

	@Column(name="P8000048")
	public String getP8000048(){
		return p8000048;
	}
		
	public void setP8000048(String p8000048){
		this.p8000048=p8000048;
	}

	@Column(name="P8000049")
	public String getP8000049(){
		return p8000049;
	}
		
	public void setP8000049(String p8000049){
		this.p8000049=p8000049;
	}

	@Column(name="P8000050")
	public String getP8000050(){
		return p8000050;
	}
		
	public void setP8000050(String p8000050){
		this.p8000050=p8000050;
	}

	@Column(name="P8000051")
	public String getP8000051(){
		return p8000051;
	}
		
	public void setP8000051(String p8000051){
		this.p8000051=p8000051;
	}

	@Column(name="P8000052")
	public String getP8000052(){
		return p8000052;
	}
		
	public void setP8000052(String p8000052){
		this.p8000052=p8000052;
	}

	@Column(name="P8000053")
	public String getP8000053(){
		return p8000053;
	}
		
	public void setP8000053(String p8000053){
		this.p8000053=p8000053;
	}

	@Column(name="P8000054")
	public String getP8000054(){
		return p8000054;
	}
		
	public void setP8000054(String p8000054){
		this.p8000054=p8000054;
	}

	@Column(name="P8000055")
	public String getP8000055(){
		return p8000055;
	}
		
	public void setP8000055(String p8000055){
		this.p8000055=p8000055;
	}

	@Column(name="P8000056")
	public String getP8000056(){
		return p8000056;
	}
		
	public void setP8000056(String p8000056){
		this.p8000056=p8000056;
	}

	@Column(name="P8000057")
	public String getP8000057(){
		return p8000057;
	}
		
	public void setP8000057(String p8000057){
		this.p8000057=p8000057;
	}

	@Column(name="P8000058")
	public String getP8000058(){
		return p8000058;
	}
		
	public void setP8000058(String p8000058){
		this.p8000058=p8000058;
	}

	@Column(name="P8000059")
	public String getP8000059(){
		return p8000059;
	}
		
	public void setP8000059(String p8000059){
		this.p8000059=p8000059;
	}

	@Column(name="P8000060")
	public String getP8000060(){
		return p8000060;
	}
		
	public void setP8000060(String p8000060){
		this.p8000060=p8000060;
	}

	@Column(name="P8000061")
	public String getP8000061(){
		return p8000061;
	}
		
	public void setP8000061(String p8000061){
		this.p8000061=p8000061;
	}

	@Column(name="P8000062")
	public String getP8000062(){
		return p8000062;
	}
		
	public void setP8000062(String p8000062){
		this.p8000062=p8000062;
	}

	@Column(name="P8000063")
	public String getP8000063(){
		return p8000063;
	}
		
	public void setP8000063(String p8000063){
		this.p8000063=p8000063;
	}

	@Column(name="P8000064")
	public String getP8000064(){
		return p8000064;
	}
		
	public void setP8000064(String p8000064){
		this.p8000064=p8000064;
	}

	@Column(name="P8000065")
	public String getP8000065(){
		return p8000065;
	}
		
	public void setP8000065(String p8000065){
		this.p8000065=p8000065;
	}

	@Column(name="P8000066")
	public String getP8000066(){
		return p8000066;
	}
		
	public void setP8000066(String p8000066){
		this.p8000066=p8000066;
	}

	@Column(name="P8000067")
	public String getP8000067(){
		return p8000067;
	}
		
	public void setP8000067(String p8000067){
		this.p8000067=p8000067;
	}

	@Column(name="P8000068")
	public String getP8000068(){
		return p8000068;
	}
		
	public void setP8000068(String p8000068){
		this.p8000068=p8000068;
	}

	@Column(name="P8000069")
	public String getP8000069(){
		return p8000069;
	}
		
	public void setP8000069(String p8000069){
		this.p8000069=p8000069;
	}

	@Column(name="P8000070")
	public String getP8000070(){
		return p8000070;
	}
		
	public void setP8000070(String p8000070){
		this.p8000070=p8000070;
	}

	@Column(name="P8000071")
	public String getP8000071(){
		return p8000071;
	}
		
	public void setP8000071(String p8000071){
		this.p8000071=p8000071;
	}

	@Column(name="P8000072")
	public String getP8000072(){
		return p8000072;
	}
		
	public void setP8000072(String p8000072){
		this.p8000072=p8000072;
	}

	@Column(name="P8000073")
	public String getP8000073(){
		return p8000073;
	}
		
	public void setP8000073(String p8000073){
		this.p8000073=p8000073;
	}

	@Column(name="P8000074")
	public String getP8000074(){
		return p8000074;
	}
		
	public void setP8000074(String p8000074){
		this.p8000074=p8000074;
	}

	@Column(name="P8000075")
	public String getP8000075(){
		return p8000075;
	}
		
	public void setP8000075(String p8000075){
		this.p8000075=p8000075;
	}

	@Column(name="P8000076")
	public String getP8000076(){
		return p8000076;
	}
		
	public void setP8000076(String p8000076){
		this.p8000076=p8000076;
	}

	@Column(name="P8000077")
	public String getP8000077(){
		return p8000077;
	}
		
	public void setP8000077(String p8000077){
		this.p8000077=p8000077;
	}

	@Column(name="P8000078")
	public String getP8000078(){
		return p8000078;
	}
		
	public void setP8000078(String p8000078){
		this.p8000078=p8000078;
	}

	@Column(name="P8000079")
	public String getP8000079(){
		return p8000079;
	}
		
	public void setP8000079(String p8000079){
		this.p8000079=p8000079;
	}

	@Column(name="P8000080")
	public String getP8000080(){
		return p8000080;
	}
		
	public void setP8000080(String p8000080){
		this.p8000080=p8000080;
	}

	@Column(name="P8000081")
	public String getP8000081(){
		return p8000081;
	}
		
	public void setP8000081(String p8000081){
		this.p8000081=p8000081;
	}

	@Column(name="P8000082")
	public String getP8000082(){
		return p8000082;
	}
		
	public void setP8000082(String p8000082){
		this.p8000082=p8000082;
	}

	@Column(name="P8000083")
	public String getP8000083(){
		return p8000083;
	}
		
	public void setP8000083(String p8000083){
		this.p8000083=p8000083;
	}

	@Column(name="P8000084")
	public String getP8000084(){
		return p8000084;
	}
		
	public void setP8000084(String p8000084){
		this.p8000084=p8000084;
	}

	@Column(name="P8000085")
	public String getP8000085(){
		return p8000085;
	}
		
	public void setP8000085(String p8000085){
		this.p8000085=p8000085;
	}

	@Column(name="P8000086")
	public String getP8000086(){
		return p8000086;
	}
		
	public void setP8000086(String p8000086){
		this.p8000086=p8000086;
	}

	@Column(name="P8000087")
	public String getP8000087(){
		return p8000087;
	}
		
	public void setP8000087(String p8000087){
		this.p8000087=p8000087;
	}

	@Column(name="P8000088")
	public String getP8000088(){
		return p8000088;
	}
		
	public void setP8000088(String p8000088){
		this.p8000088=p8000088;
	}

	@Column(name="P8000089")
	public String getP8000089(){
		return p8000089;
	}
		
	public void setP8000089(String p8000089){
		this.p8000089=p8000089;
	}

	@Column(name="P8000090")
	public String getP8000090(){
		return p8000090;
	}
		
	public void setP8000090(String p8000090){
		this.p8000090=p8000090;
	}

	@Column(name="P8000091")
	public String getP8000091(){
		return p8000091;
	}
		
	public void setP8000091(String p8000091){
		this.p8000091=p8000091;
	}

	@Column(name="P8000092")
	public String getP8000092(){
		return p8000092;
	}
		
	public void setP8000092(String p8000092){
		this.p8000092=p8000092;
	}

	@Column(name="P8000093")
	public String getP8000093(){
		return p8000093;
	}
		
	public void setP8000093(String p8000093){
		this.p8000093=p8000093;
	}

	@Column(name="P8000094")
	public String getP8000094(){
		return p8000094;
	}
		
	public void setP8000094(String p8000094){
		this.p8000094=p8000094;
	}

	@Column(name="P8000095")
	public String getP8000095(){
		return p8000095;
	}
		
	public void setP8000095(String p8000095){
		this.p8000095=p8000095;
	}

	@Column(name="P8000096")
	public String getP8000096(){
		return p8000096;
	}
		
	public void setP8000096(String p8000096){
		this.p8000096=p8000096;
	}

	@Column(name="P8000097")
	public String getP8000097(){
		return p8000097;
	}
		
	public void setP8000097(String p8000097){
		this.p8000097=p8000097;
	}

	@Column(name="P8000098")
	public String getP8000098(){
		return p8000098;
	}
		
	public void setP8000098(String p8000098){
		this.p8000098=p8000098;
	}

	@Column(name="P8000099")
	public String getP8000099(){
		return p8000099;
	}
		
	public void setP8000099(String p8000099){
		this.p8000099=p8000099;
	}

	@Column(name="P8000100")
	public String getP8000100(){
		return p8000100;
	}
		
	public void setP8000100(String p8000100){
		this.p8000100=p8000100;
	}
	

	public String getP8000101() {
		return p8000101;
	}
	public void setP8000101(String p8000101) {
		this.p8000101 = p8000101;
	}
	public String getP8000102() {
		return p8000102;
	}
	public void setP8000102(String p8000102) {
		this.p8000102 = p8000102;
	}
	public String getP8000103() {
		return p8000103;
	}
	public void setP8000103(String p8000103) {
		this.p8000103 = p8000103;
	}
	public String getP8000104() {
		return p8000104;
	}
	public void setP8000104(String p8000104) {
		this.p8000104 = p8000104;
	}
	public String getP8000105() {
		return p8000105;
	}
	public void setP8000105(String p8000105) {
		this.p8000105 = p8000105;
	}
	public String getP8000106() {
		return p8000106;
	}
	public void setP8000106(String p8000106) {
		this.p8000106 = p8000106;
	}
	public String getP8000107() {
		return p8000107;
	}
	public void setP8000107(String p8000107) {
		this.p8000107 = p8000107;
	}
	public String getP8000108() {
		return p8000108;
	}
	public void setP8000108(String p8000108) {
		this.p8000108 = p8000108;
	}
	public String getP8000109() {
		return p8000109;
	}
	public void setP8000109(String p8000109) {
		this.p8000109 = p8000109;
	}
	public String getP8000110() {
		return p8000110;
	}
	public void setP8000110(String p8000110) {
		this.p8000110 = p8000110;
	}
	public String getP8000111() {
		return p8000111;
	}
	public void setP8000111(String p8000111) {
		this.p8000111 = p8000111;
	}
	public String getP8000112() {
		return p8000112;
	}
	public void setP8000112(String p8000112) {
		this.p8000112 = p8000112;
	}
	public String getP8000113() {
		return p8000113;
	}
	public void setP8000113(String p8000113) {
		this.p8000113 = p8000113;
	}
	public String getP8000114() {
		return p8000114;
	}
	public void setP8000114(String p8000114) {
		this.p8000114 = p8000114;
	}
	public String getP8000115() {
		return p8000115;
	}
	public void setP8000115(String p8000115) {
		this.p8000115 = p8000115;
	}
	public String getP8000116() {
		return p8000116;
	}
	public void setP8000116(String p8000116) {
		this.p8000116 = p8000116;
	}
	public String getP8000117() {
		return p8000117;
	}
	public void setP8000117(String p8000117) {
		this.p8000117 = p8000117;
	}
	public String getP8000118() {
		return p8000118;
	}
	public void setP8000118(String p8000118) {
		this.p8000118 = p8000118;
	}
	public String getP8000119() {
		return p8000119;
	}
	public void setP8000119(String p8000119) {
		this.p8000119 = p8000119;
	}
	public String getP8000120() {
		return p8000120;
	}
	public void setP8000120(String p8000120) {
		this.p8000120 = p8000120;
	}
	public String getP8000121() {
		return p8000121;
	}
	public void setP8000121(String p8000121) {
		this.p8000121 = p8000121;
	}
	public String getP8000122() {
		return p8000122;
	}
	public void setP8000122(String p8000122) {
		this.p8000122 = p8000122;
	}
	public String getP8000123() {
		return p8000123;
	}
	public void setP8000123(String p8000123) {
		this.p8000123 = p8000123;
	}
	public String getP8000124() {
		return p8000124;
	}
	public void setP8000124(String p8000124) {
		this.p8000124 = p8000124;
	}
	public String getP8000125() {
		return p8000125;
	}
	public void setP8000125(String p8000125) {
		this.p8000125 = p8000125;
	}
	public String getP8000126() {
		return p8000126;
	}
	public void setP8000126(String p8000126) {
		this.p8000126 = p8000126;
	}
	public String getP8000127() {
		return p8000127;
	}
	public void setP8000127(String p8000127) {
		this.p8000127 = p8000127;
	}
	public String getP8000128() {
		return p8000128;
	}
	public void setP8000128(String p8000128) {
		this.p8000128 = p8000128;
	}
	public String getP8000129() {
		return p8000129;
	}
	public void setP8000129(String p8000129) {
		this.p8000129 = p8000129;
	}
	public String getP8000130() {
		return p8000130;
	}
	public void setP8000130(String p8000130) {
		this.p8000130 = p8000130;
	}
	public String getP8000131() {
		return p8000131;
	}
	public void setP8000131(String p8000131) {
		this.p8000131 = p8000131;
	}
	public String getP8000132() {
		return p8000132;
	}
	public void setP8000132(String p8000132) {
		this.p8000132 = p8000132;
	}
	public String getP8000133() {
		return p8000133;
	}
	public void setP8000133(String p8000133) {
		this.p8000133 = p8000133;
	}
	public String getP8000134() {
		return p8000134;
	}
	public void setP8000134(String p8000134) {
		this.p8000134 = p8000134;
	}
	public String getP8000135() {
		return p8000135;
	}
	public void setP8000135(String p8000135) {
		this.p8000135 = p8000135;
	}
	@Column(name="STATUS")
	public String getStatus(){
		return status;
	}
		
	public void setStatus(String status){
		this.status=status;
	}

	@Column(name="AUDIT_NAME")
	public String getAuditName(){
		return auditName;
	}
		
	public void setAuditName(String auditName){
		this.auditName=auditName;
	}

	@Column(name="AUDIT_ID")
	public String getAuditId(){
		return auditId;
	}
		
	public void setAuditId(String auditId){
		this.auditId=auditId;
	}

	@Column(name="AUDIT_STATUS")
	public String getAuditStatus(){
		return auditStatus;
	}
		
	public void setAuditStatus(String auditStatus){
		this.auditStatus=auditStatus;
	}

	@Column(name="PROJECT_NO")
	public String getProjectNo(){
		return projectNo;
	}
		
	public void setProjectNo(String projectNo){
		this.projectNo=projectNo;
	}

	@Column(name="JH_NO")
	public String getJhNo(){
		return jhNo;
	}
		
	public void setJhNo(String jhNo){
		this.jhNo=jhNo;
	}

	@Column(name="CLASSIFICATION")
	public String getClassification(){
		return classification;
	}
		
	public void setClassification(String classification){
		this.classification=classification;
	}

	
	
	@Column(name="LAST_MODIFY_DATE")
	public Date getLastModifyDate() {
		return LastModifyDate;
	}
	public void setLastModifyDate(Date lastModifyDate) {
		LastModifyDate = lastModifyDate;
	}
	@Column(name="LAST_MODIFY_ID")
	public String getLastModifyId() {
		return LastModifyId;
	}
	public void setLastModifyId(String lastModifyId) {
		LastModifyId = lastModifyId;
	}
	@Column(name="LAST_MODIFY_MAN")
	public String getLastModifyMan() {
		return LastModifyMan;
	}
	public void setLastModifyMan(String lastModifyMan) {
		LastModifyMan = lastModifyMan;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/***************************************************************************
	 * bean说明
	 * 
	 * @return
	 */
	public String toString() {
		return "TJY2_SCIENTIFIC_PROVINCE:ID="+id;

	}
}
