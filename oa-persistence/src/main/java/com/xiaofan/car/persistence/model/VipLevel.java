package com.xiaofan.car.persistence.model;
import lombok.Data;


/**
 * 
 * 
 * 
 **/
@Data
public class VipLevel{

	/****/
	private Integer id;

	/**会员名称**/
	private String vipName;

	/**折扣费率**/
	private String discountRate;

	/****/
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

	public void setVipName(String vipName){
		this.vipName=vipName;
	}

	public String getVipName(){
		return this.vipName;
	}

	public void setDiscountRate(String discountRate){
		this.discountRate=discountRate;
	}

	public String getDiscountRate(){
		return this.discountRate;
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
