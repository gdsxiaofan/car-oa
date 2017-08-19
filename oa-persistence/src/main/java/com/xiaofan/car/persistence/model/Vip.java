package com.xiaofan.car.persistence.model;
import lombok.Data;


/**
 * 
 * 
 * 
 **/
@Data
public class Vip{

	/****/
	private Integer id;

	/**vip账号**/
	private String vipNo;

	/**vip级别id**/
	private Integer vipLevelId;

	/**有效状态：1：有效，0：无效**/
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

	public void setVipNo(String vipNo){
		this.vipNo=vipNo;
	}

	public String getVipNo(){
		return this.vipNo;
	}

	public void setVipLevelId(Integer vipLevelId){
		this.vipLevelId=vipLevelId;
	}

	public Integer getVipLevelId(){
		return this.vipLevelId;
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
