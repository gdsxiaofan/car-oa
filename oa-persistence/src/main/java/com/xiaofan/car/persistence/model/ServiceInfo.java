package com.xiaofan.car.persistence.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * 
 * 
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceInfo{

	/****/
	private Integer id;

	/**服务名称**/
	private String serviceName;

	/**关联的设备id**/
	private Integer deviceId;

	/**任务描述**/
	private String serviceDescription;

	/**属性一**/
	private String property1;

	/**属性二**/
	private String property2;

	/**属性三**/
	private String property3;

	/**创建时间**/
	private java.util.Date createTime;

	/**更新时间**/
	private java.util.Date updateTime;

	/**属性四**/
	private String property4;

	/**任务描述**/
	private String comment;



	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setServiceName(String serviceName){
		this.serviceName=serviceName;
	}

	public String getServiceName(){
		return this.serviceName;
	}

	public void setDeviceId(Integer deviceId){
		this.deviceId=deviceId;
	}

	public Integer getDeviceId(){
		return this.deviceId;
	}

	public void setServiceDescription(String serviceDescription){
		this.serviceDescription=serviceDescription;
	}

	public String getServiceDescription(){
		return this.serviceDescription;
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

	public void setProperty4(String property4){
		this.property4=property4;
	}

	public String getProperty4(){
		return this.property4;
	}

	public void setComment(String comment){
		this.comment=comment;
	}

	public String getComment(){
		return this.comment;
	}

}
