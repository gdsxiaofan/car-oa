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

	/**区域**/
	private String area;

	/**位置**/
	private String location;

	/**开始时间**/
	private java.util.Date fromDate;

	/**结束时间**/
	private java.util.Date toDate;

	/**是否有效：1：是，0：无效**/
	private Integer deleteStatus;

	/**创建时间**/
	private java.util.Date createTime;

	/**更新时间**/
	private java.util.Date updateTime;

	/**创建人id**/
	private Integer createUseId;



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

	public void setArea(String area){
		this.area=area;
	}

	public String getArea(){
		return this.area;
	}

	public void setLocation(String location){
		this.location=location;
	}

	public String getLocation(){
		return this.location;
	}

	public void setFromDate(java.util.Date fromDate){
		this.fromDate=fromDate;
	}

	public java.util.Date getFromDate(){
		return this.fromDate;
	}

	public void setToDate(java.util.Date toDate){
		this.toDate=toDate;
	}

	public java.util.Date getToDate(){
		return this.toDate;
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

	public void setCreateUseId(Integer createUseId){
		this.createUseId=createUseId;
	}

	public Integer getCreateUseId(){
		return this.createUseId;
	}

}
