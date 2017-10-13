package com.xiaofan.car.persistence.model;
import lombok.Data;


/**
 * 
 * 
 * 
 **/
@Data
public class TpmProblemLog{

	/****/
	private Integer id;

	/**设备id**/
	private Integer deviceId;

	/**设备名称**/
	private String deviceName;

	/**问题发现时间**/
	private java.util.Date findDate;

	/**问题描述**/
	private String problemDes;

	/**措施**/
	private String measures;

	/**责任人id**/
	private Integer responsibleUseId;

	/**责任人姓名**/
	private String responsibleUseName;

	/**计划完成时间**/
	private java.util.Date planFinshDate;

	/**实际完成时间**/
	private java.util.Date realFinshDate;

	/**备注**/
	private String comment;

	/**有效状态：1：有效，0无效**/
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

	public void setDeviceId(Integer deviceId){
		this.deviceId=deviceId;
	}

	public Integer getDeviceId(){
		return this.deviceId;
	}

	public void setDeviceName(String deviceName){
		this.deviceName=deviceName;
	}

	public String getDeviceName(){
		return this.deviceName;
	}

	public void setFindDate(java.util.Date findDate){
		this.findDate=findDate;
	}

	public java.util.Date getFindDate(){
		return this.findDate;
	}

	public void setProblemDes(String problemDes){
		this.problemDes=problemDes;
	}

	public String getProblemDes(){
		return this.problemDes;
	}

	public void setMeasures(String measures){
		this.measures=measures;
	}

	public String getMeasures(){
		return this.measures;
	}

	public void setResponsibleUseId(Integer responsibleUseId){
		this.responsibleUseId=responsibleUseId;
	}

	public Integer getResponsibleUseId(){
		return this.responsibleUseId;
	}

	public void setResponsibleUseName(String responsibleUseName){
		this.responsibleUseName=responsibleUseName;
	}

	public String getResponsibleUseName(){
		return this.responsibleUseName;
	}

	public void setPlanFinshDate(java.util.Date planFinshDate){
		this.planFinshDate=planFinshDate;
	}

	public java.util.Date getPlanFinshDate(){
		return this.planFinshDate;
	}

	public void setRealFinshDate(java.util.Date realFinshDate){
		this.realFinshDate=realFinshDate;
	}

	public java.util.Date getRealFinshDate(){
		return this.realFinshDate;
	}

	public void setComment(String comment){
		this.comment=comment;
	}

	public String getComment(){
		return this.comment;
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
