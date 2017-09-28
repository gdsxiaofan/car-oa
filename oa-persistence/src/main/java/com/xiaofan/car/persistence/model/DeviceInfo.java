package com.xiaofan.car.persistence.model;
import lombok.Data;


/**
 * 
 * 
 * 
 **/
@Data
public class DeviceInfo{

	/****/
	private Integer id;

	/**巡检天数**/
	private Integer routingDays;

	/**设备名称**/
	private String deviceName;

	/**属性一**/
	private String property1;

	/**属性二**/
	private String property2;

	/**属性三**/
	private String property3;

	/**是否有效：1：是，0：无效**/
	private Integer deleteStatus;

	/**创建时间**/
	private java.util.Date createTime;

	/**更新时间**/
	private java.util.Date updateTime;



	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setRoutingDays(Integer routingDays){
		this.routingDays=routingDays;
	}

	public Integer getRoutingDays(){
		return this.routingDays;
	}

	public void setDeviceName(String deviceName){
		this.deviceName=deviceName;
	}

	public String getDeviceName(){
		return this.deviceName;
	}

	public void setProperty1(String property1){
		this.property1=property1;
	}

	public String getProperty1(){
		return this.property1;
	}

	public void setProperty2(String property2){
		this.property2=property2;
	}

	public String getProperty2(){
		return this.property2;
	}

	public void setProperty3(String property3){
		this.property3=property3;
	}

	public String getProperty3(){
		return this.property3;
	}

	public void setDeleteStatus(Integer deleteStatus){
		this.deleteStatus=deleteStatus;
	}

	public Integer getDeleteStatus(){
		return this.deleteStatus;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime=updateTime;
	}

	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

}
