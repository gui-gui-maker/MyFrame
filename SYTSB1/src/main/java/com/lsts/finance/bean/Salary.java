package com.lsts.finance.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.khnt.core.crud.bean.BaseEntity;

@Entity
@Table(name = "TJY2_CW_SALARY_NEW")
public class Salary implements BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//${columnb.remarks}
	
	private String fkEmployeeBaseId;//外键ID

    private String name;//基本工资姓名
    
    //private String workDepartmentName;//部门名称
    
    private String telePhone;//手机号

    private java.math.BigDecimal jbgzGwgz;//基本工资岗位工资

    private java.math.BigDecimal jbgzXjgz;//基本工资薪级工资

    private java.math.BigDecimal jbgzBlbt;//基本工资保留补贴

    private java.math.BigDecimal jbgzLt;//基本工资粮贴

    private java.math.BigDecimal jbgzBfx;//基本工资补发项

    private java.math.BigDecimal jbgzXj;//基本工资小计

    private java.math.BigDecimal jxgzJcjxMy;//绩效工资基础绩效每月

    private java.math.BigDecimal jxgzJcjxBf;//绩效工资基础绩效补发

    private java.math.BigDecimal jxgzJdjx;//绩效工资季度绩效

    private java.math.BigDecimal jxgzBtZcbt;//绩效工资补贴驻场补贴

    private java.math.BigDecimal jxgzBtQt;//绩效工资补贴其他

    private java.math.BigDecimal jxgzBy;//绩效工资备用

    private java.math.BigDecimal kkxZynjMy;//扣款项职业年金每月

    private java.math.BigDecimal kkxZynjBk;//扣款项职业年金补扣

    private java.math.BigDecimal kkxYlbxMy;//扣款项医疗保险每月

    private java.math.BigDecimal kkxYlbxBf;//扣款项医疗保险补发
    
    private java.math.BigDecimal kkxOldbxMy;//扣款项养老保险每月

    private java.math.BigDecimal kkxOldbxBf;//扣款项养老保险补发

    private java.math.BigDecimal kkxSyMy;//扣款项失业每月

    private java.math.BigDecimal kkxSyBf;//扣款项失业补发

    private java.math.BigDecimal kkxGjjMy;//扣款项公积金每月

    private java.math.BigDecimal kkxGjjBf;//扣款项公积金补发

    private java.math.BigDecimal kkxGhjf;//扣款项工会经费

    private java.math.BigDecimal kkxSds;//扣款项所得税

    private java.math.BigDecimal jxgzXj;//绩效工资小计

    private java.math.BigDecimal jbgzDz;//基本工资独子

    private java.math.BigDecimal kkxBy;//扣款项备用

    private java.math.BigDecimal kkxXj;//扣款项小计

    private java.math.BigDecimal fsalary;//应发合计

    private String importId;//导入记录表  TJY2_IMPORT_DATA_RECORDS id

    private String createrId;//创建人id

    private String createrName;//创建人姓名

    public void setId(String value){
        this.id = value;
    }
    public void setFkEmployeeBaseId(String value){
        this.fkEmployeeBaseId = value;
    }
    public void setName(String value){
        this.name = value;
    }
    public void setTelePhone(String value){
        this.telePhone = value;
    }
    public void setJbgzGwgz(java.math.BigDecimal value){
        this.jbgzGwgz = value;
    }
    public void setJbgzXjgz(java.math.BigDecimal value){
        this.jbgzXjgz = value;
    }
    public void setJbgzBlbt(java.math.BigDecimal value){
        this.jbgzBlbt = value;
    }
    public void setJbgzLt(java.math.BigDecimal value){
        this.jbgzLt = value;
    }
    public void setJbgzBfx(java.math.BigDecimal value){
        this.jbgzBfx = value;
    }
    public void setJbgzXj(java.math.BigDecimal value){
        this.jbgzXj = value;
    }
    public void setJxgzJcjxMy(java.math.BigDecimal value){
        this.jxgzJcjxMy = value;
    }
    public void setJxgzJcjxBf(java.math.BigDecimal value){
        this.jxgzJcjxBf = value;
    }
    public void setJxgzJdjx(java.math.BigDecimal value){
        this.jxgzJdjx = value;
    }
    public void setJxgzBtZcbt(java.math.BigDecimal value){
        this.jxgzBtZcbt = value;
    }
    public void setJxgzBtQt(java.math.BigDecimal value){
        this.jxgzBtQt = value;
    }
    public void setJxgzBy(java.math.BigDecimal value){
        this.jxgzBy = value;
    }
    public void setKkxZynjMy(java.math.BigDecimal value){
        this.kkxZynjMy = value;
    }
    public void setKkxZynjBk(java.math.BigDecimal value){
        this.kkxZynjBk = value;
    }
    public void setKkxOldbxMy(java.math.BigDecimal value){
        this.kkxOldbxMy = value;
    }
    public void setKkxYlbxMy(java.math.BigDecimal value){
        this.kkxYlbxMy = value;
    }
    public void setKkxOldbxBf(java.math.BigDecimal value){
        this.kkxOldbxBf = value;
    }
    public void setKkxYlbxBf(java.math.BigDecimal value){
        this.kkxYlbxBf = value;
    }
    public void setKkxSyMy(java.math.BigDecimal value){
        this.kkxSyMy = value;
    }
    public void setKkxSyBf(java.math.BigDecimal value){
        this.kkxSyBf = value;
    }
    public void setKkxGjjMy(java.math.BigDecimal value){
        this.kkxGjjMy = value;
    }
    public void setKkxGjjBf(java.math.BigDecimal value){
        this.kkxGjjBf = value;
    }
    public void setKkxGhjf(java.math.BigDecimal value){
        this.kkxGhjf = value;
    }
    public void setKkxSds(java.math.BigDecimal value){
        this.kkxSds = value;
    }
    public void setJxgzXj(java.math.BigDecimal value){
        this.jxgzXj = value;
    }
    public void setJbgzDz(java.math.BigDecimal value){
        this.jbgzDz = value;
    }
    public void setKkxBy(java.math.BigDecimal value){
        this.kkxBy = value;
    }
    public void setKkxXj(java.math.BigDecimal value){
        this.kkxXj = value;
    }
    public void setFsalary(java.math.BigDecimal value){
        this.fsalary = value;
    }
    public void setImportId(String value){
        this.importId = value;
    }
    public void setCreaterId(String value){
        this.createrId = value;
    }
    public void setCreaterName(String value){
        this.createrName = value;
    }
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="ID",unique=true,nullable=false,insertable=true,updatable=true,length=32)
    public String getId(){
        return this.id;
    }
    @Column(name ="FK_EMPLOYEEBASE_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getFkEmployeeBaseId(){
        return this.fkEmployeeBaseId;
    }
    @Column(name ="NAME",unique=false,nullable=true,insertable=true,updatable=true,length=40)
    public String getName(){
        return this.name;
    }
    @Column(name ="TELEPHONE",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getTelePhone(){
        return this.telePhone;
    }
    @Column(name ="JBGZ_GWGZ",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getJbgzGwgz(){
        return this.jbgzGwgz;
    }
    @Column(name ="JBGZ_XJGZ",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getJbgzXjgz(){
        return this.jbgzXjgz;
    }
    @Column(name ="JBGZ_BLBT",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getJbgzBlbt(){
        return this.jbgzBlbt;
    }
    @Column(name ="JBGZ_LT",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getJbgzLt(){
        return this.jbgzLt;
    }
    @Column(name ="JBGZ_BFX",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getJbgzBfx(){
        return this.jbgzBfx;
    }
    @Column(name ="JBGZ_XJ",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getJbgzXj(){
        return this.jbgzXj;
    }
    @Column(name ="JXGZ_JCJX_MY",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getJxgzJcjxMy(){
        return this.jxgzJcjxMy;
    }
    @Column(name ="JXGZ_JCJX_BF",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getJxgzJcjxBf(){
        return this.jxgzJcjxBf;
    }
    @Column(name ="JXGZ_JDJX",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getJxgzJdjx(){
        return this.jxgzJdjx;
    }
    @Column(name ="JXGZ_BT_ZCBT",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getJxgzBtZcbt(){
        return this.jxgzBtZcbt;
    }
    @Column(name ="JXGZ_BT_QT",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getJxgzBtQt(){
        return this.jxgzBtQt;
    }
    @Column(name ="JXGZ_BY",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getJxgzBy(){
        return this.jxgzBy;
    }
    @Column(name ="KKX_ZYNJ_MY",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getKkxZynjMy(){
        return this.kkxZynjMy;
    }
    @Column(name ="KKX_ZYNJ_BK",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getKkxZynjBk(){
        return this.kkxZynjBk;
    }
    @Column(name ="KKX_OLDBX_MY",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getKkxOldbxMy(){
        return this.kkxOldbxMy;
    }
    @Column(name ="KKX_OLDBX_BF",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getKkxOldbxBf(){
        return this.kkxOldbxBf;
    }
    @Column(name ="KKX_YLBX_MY",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getKkxYlbxMy(){
        return this.kkxYlbxMy;
    }
    @Column(name ="KKX_YLBX_BF",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getKkxYlbxBf(){
        return this.kkxYlbxBf;
    }
    @Column(name ="KKX_SY_MY",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getKkxSyMy(){
        return this.kkxSyMy;
    }
    @Column(name ="KKX_SY_BF",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getKkxSyBf(){
        return this.kkxSyBf;
    }
    @Column(name ="KKX_GJJ_MY",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getKkxGjjMy(){
        return this.kkxGjjMy;
    }
    @Column(name ="KKX_GJJ_BF",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getKkxGjjBf(){
        return this.kkxGjjBf;
    }
    @Column(name ="KKX_GHJF",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getKkxGhjf(){
        return this.kkxGhjf;
    }
    @Column(name ="KKX_SDS",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getKkxSds(){
        return this.kkxSds;
    }
    @Column(name ="JXGZ_XJ",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getJxgzXj(){
        return this.jxgzXj;
    }
    @Column(name ="JBGZ_DZ",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getJbgzDz(){
        return this.jbgzDz;
    }
    @Column(name ="KKX_BY",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getKkxBy(){
        return this.kkxBy;
    }
    @Column(name ="KKX_XJ",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getKkxXj(){
        return this.kkxXj;
    }
    @Column(name ="FSALARY",unique=false,nullable=true,insertable=true,updatable=true,length=22)
    public java.math.BigDecimal getFsalary(){
        return this.fsalary;
    }
    @Column(name ="IMPORT_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getImportId(){
        return this.importId;
    }
    @Column(name ="CREATER_ID",unique=false,nullable=true,insertable=true,updatable=true,length=32)
    public String getCreaterId(){
        return this.createrId;
    }
    @Column(name ="CREATER_NAME",unique=false,nullable=true,insertable=true,updatable=true,length=40)
    public String getCreaterName(){
        return this.createrName;
    }


} 
