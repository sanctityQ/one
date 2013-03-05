package com.sinosoft.one.monitor.db.oracle.model;
// Generated 2013-3-4 21:44:43 by One Data Tools 1.0.0


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

/**
 * EventSta.
* ORACLE事件统计表
 */
@Entity
@Table(name="GE_MONITOR_ORACLE_EVENT_STA"
)
public class EventSta  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	/**
    * 主键ID.
    */
    private String id;
    /**
    * 数据库ID.
    */
    private Info info;
    /**
    * 1:连接时间,2:活动连接数,3:缓冲区命中率,4.数据字典命中率,5.缓存库命中率.
    */
    private String eventType;
    /**
    * 活动连接数.
    */
    private Double min;
    /**
    * 活动连接数最大值.
    */
    private Double max;
    /**
    * 平均值.
    */
    private Double avg;
    /**
    * 记录时间.
    */
    private Date eventRecordTime;
    /**
    * 统计次数.
    */
    private long eventCount;

    public EventSta() {
    }

	
    public EventSta(String id) {
        this.id = id;
    }
   
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name="ID", unique=true, length=32)
    public String getId() {
    return this.id;
    }

    public void setId(String id) {
    this.id = id;
    }
    @ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="DATABASE_ID")
    public Info getInfo() {
    return this.info;
    }

    public void setInfo(Info info) {
    this.info = info;
    }
    
    @Column(name="EVENT_TYPE", length=2)
    public String getEventType() {
    return this.eventType;
    }

    public void setEventType(String eventType) {
    this.eventType = eventType;
    }
    
    @Column(name="MIN", precision=126, scale=0)
    public Double getMin() {
    return this.min;
    }

    public void setMin(Double min) {
    this.min = min;
    }
    
    @Column(name="MAX", precision=126, scale=0)
    public Double getMax() {
    return this.max;
    }

    public void setMax(Double max) {
    this.max = max;
    }
    
    @Column(name="AVG", precision=126, scale=0)
    public Double getAvg() {
    return this.avg;
    }

    public void setAvg(Double avg) {
    this.avg = avg;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="EVENT_RECORD_TIME")
    public Date getEventRecordTime() {
    return this.eventRecordTime;
    }

    public void setEventRecordTime(Date eventRecordTime) {
    this.eventRecordTime = eventRecordTime;
    }
    
    @Column(name="EVENT_COUNT")
    public long getEventCount() {
    return this.eventCount;
    }

    public void setEventCount(long eventCount) {
    this.eventCount = eventCount;
    }


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}


