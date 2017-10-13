package com.xiaofan.car.persistence.model;
import lombok.Data;


/**
 * 
 * 
 * 
 **/
@Data
public class TpmBillDetail{

	/****/
	private Integer id;

	/**检查信息id**/
	private Integer checkId;

	/**系统名称**/
	private String systemName;

	/**检查点**/
	private String checkPoint;

	/**检查/维护工作**/
	private String checkComment;

	/**设定值**/
	private String setValue;

	/**检查标记：0，待确认，1，正常，2，有故障，3，修复**/
	private Integer checkStatus;

	/**有效状态：1，有效；0，无效**/
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

	public void setCheckId(Integer checkId){
		this.checkId=checkId;
	}

	public Integer getCheckId(){
		return this.checkId;
	}

	public void setSystemName(String systemName){
		this.systemName=systemName;
	}

	public String getSystemName(){
		return this.systemName;
	}

	public void setCheckPoint(String checkPoint){
		this.checkPoint=checkPoint;
	}

	public String getCheckPoint(){
		return this.checkPoint;
	}

	public void setCheckComment(String checkComment){
		this.checkComment=checkComment;
	}

	public String getCheckComment(){
		return this.checkComment;
	}

	public void setSetValue(String setValue){
		this.setValue=setValue;
	}

	public String getSetValue(){
		return this.setValue;
	}

	public void setCheckStatus(Integer checkStatus){
		this.checkStatus=checkStatus;
	}

	public Integer getCheckStatus(){
		return this.checkStatus;
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
