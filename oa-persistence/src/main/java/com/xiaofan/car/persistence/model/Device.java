package com.xiaofan.car.persistence.model;
import lombok.Data;


/**
 * 
 * 
 * 
 **/
@Data
public class Device{

	/****/
	private Integer id;

	/**车牌号**/
	private String carNo;

	/**客户姓名**/
	private String customerName;

	/**客户手机号码**/
	private String customerMobile;

	/**vip账号**/
	private String vipNo;

	/**员工id**/
	private Integer employeeId;

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

	public void setCarNo(String carNo){
		this.carNo=carNo;
	}

	public String getCarNo(){
		return this.carNo;
	}

	public void setCustomerName(String customerName){
		this.customerName=customerName;
	}

	public String getCustomerName(){
		return this.customerName;
	}

	public void setCustomerMobile(String customerMobile){
		this.customerMobile=customerMobile;
	}

	public String getCustomerMobile(){
		return this.customerMobile;
	}

	public void setVipNo(String vipNo){
		this.vipNo=vipNo;
	}

	public String getVipNo(){
		return this.vipNo;
	}

	public void setEmployeeId(Integer employeeId){
		this.employeeId=employeeId;
	}

	public Integer getEmployeeId(){
		return this.employeeId;
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
