package com.xiaofan.car.persistence.model;
import lombok.Data;


/**
 * 
 * 
 * 
 **/
@Data
public class TpmBill{

	/****/
	private Integer id;

	/**工单名称**/
	private String tpmName;

	/**工单号**/
	private String tpmNo;

	/**设备id**/
	private Integer deviceId;

	/**巡检时间**/
	private java.util.Date checkTime;

	/**设备名称**/
	private String deviceName;

	/**设备位置**/
	private String deviceLocation;

	/**设备区域**/
	private String deviceArea;

	/**工单完成时间**/
	private java.util.Date finishTime;

	/**工单审核时间**/
	private java.util.Date auditTime;

	/**审核驳回原因**/
	private String rejectReason;

	/**工单状态：1，待处理，2：待审核，3：故障维修，4：修复完成，5处理完成**/
	private Integer tpmStatus;

	/**工单类型：1：巡检工单，2：维修工单**/
	private Integer tpmType;

	/**班次：1，早班；2，中班**/
	private Integer shiftsNo;

	/**发起维修的巡检工单id**/
	private Integer lastTpmId;

	/**服务描述**/
	private String serviceDescribe;

	/**有效状态：0，无效；1：有效**/
	private Integer deleteStatus;

	/**工单创建时间**/
	private java.util.Date createTime;

	/**更新时间**/
	private java.util.Date updateTime;



	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setTpmName(String tpmName){
		this.tpmName=tpmName;
	}

	public String getTpmName(){
		return this.tpmName;
	}

	public void setTpmNo(String tpmNo){
		this.tpmNo=tpmNo;
	}

	public String getTpmNo(){
		return this.tpmNo;
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

	public void setDeviceLocation(String deviceLocation){
		this.deviceLocation=deviceLocation;
	}

	public String getDeviceLocation(){
		return this.deviceLocation;
	}

	public void setDeviceArea(String deviceArea){
		this.deviceArea=deviceArea;
	}

	public String getDeviceArea(){
		return this.deviceArea;
	}

	public void setFinishTime(java.util.Date finishTime){
		this.finishTime=finishTime;
	}

	public java.util.Date getFinishTime(){
		return this.finishTime;
	}

	public void setAuditTime(java.util.Date auditTime){
		this.auditTime=auditTime;
	}

	public java.util.Date getAuditTime(){
		return this.auditTime;
	}

	public void setRejectReason(String rejectReason){
		this.rejectReason=rejectReason;
	}

	public String getRejectReason(){
		return this.rejectReason;
	}

	public void setTpmStatus(Integer tpmStatus){
		this.tpmStatus=tpmStatus;
	}

	public Integer getTpmStatus(){
		return this.tpmStatus;
	}

	public void setTpmType(Integer tpmType){
		this.tpmType=tpmType;
	}

	public Integer getTpmType(){
		return this.tpmType;
	}

	public void setShiftsNo(Integer shiftsNo){
		this.shiftsNo=shiftsNo;
	}

	public Integer getShiftsNo(){
		return this.shiftsNo;
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
