package com.sinosoft.one.monitor.model.warn;
// Generated 2013-1-8 18:00:00 by One Data Tools 1.0.0


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

/**
 * Email.
* 监控组件邮件预警对象配置
 */
@Entity
@Table(name="GE_MONITOR_EMAIL"
)
public class Email  implements java.io.Serializable {

    /**
    * 序号.
    */
    private String id;
    /**
    * 应用ID.
    */
    private String appId;
    /**
    * 邮箱持有者.
    */
    private String owner;
    /**
    * 邮箱地址.
    */
    private String address;
    /**
    * 描述.
    */
    private String descript;
    /**
    * 邮箱状态.
    */
    private String status;
    /**
    * 备注.
    */
    private String remark;
    /**
        */
    private Date createTime;

    public Email() {
    }

	
    public Email(String id, String address, String status) {
        this.id = id;
        this.address = address;
        this.status = status;
    }
   
    @Id 
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name="ID", unique=true, length=32)
    public String getId() {
    return this.id;
    }

    public void setId(String id) {
    this.id = id;
    }
    
    @Column(name="APP_ID", length=50)
    public String getAppId() {
    return this.appId;
    }

    public void setAppId(String appId) {
    this.appId = appId;
    }
    
    @Column(name="OWNER", length=100)
    public String getOwner() {
    return this.owner;
    }

    public void setOwner(String owner) {
    this.owner = owner;
    }
    
    @Column(name="ADDRESS", length=100)
    public String getAddress() {
    return this.address;
    }

    public void setAddress(String address) {
    this.address = address;
    }
    
    @Column(name="DESCRIPT", length=200)
    public String getDescript() {
    return this.descript;
    }

    public void setDescript(String descript) {
    this.descript = descript;
    }
    
    @Column(name="STATUS", length=1)
    public String getStatus() {
    return this.status;
    }

    public void setStatus(String status) {
    this.status = status;
    }
    
    @Column(name="REMARK", length=100)
    public String getRemark() {
    return this.remark;
    }

    public void setRemark(String remark) {
    this.remark = remark;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
    return this.createTime;
    }

    public void setCreateTime(Date createTime) {
    this.createTime = createTime;
    }


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}


