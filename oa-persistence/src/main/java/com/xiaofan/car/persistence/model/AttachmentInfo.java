package com.xiaofan.car.persistence.model;
import lombok.Data;


/**
 * 
 * 
 * 
 **/
@Data
public class AttachmentInfo{

	/****/
	private Integer id;

	/**业务id**/
	private Integer bizId;

	/**业务类型：1：工单，2：工单明细，3：设备，4：检查项**/
	private Integer bizType;

	/**附件名称**/
	private String attachmentName;

	/**附件url**/
	private String attachmentUrl;

	/**附件key**/
	private String attachmentKey;

	/**附件后缀**/
	private String attachmentExt;

	/**有效状态：1，有效；0无效**/
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

	public void setBizId(Integer bizId){
		this.bizId=bizId;
	}

	public Integer getBizId(){
		return this.bizId;
	}

	public void setBizType(Integer bizType){
		this.bizType=bizType;
	}

	public Integer getBizType(){
		return this.bizType;
	}

	public void setAttachmentName(String attachmentName){
		this.attachmentName=attachmentName;
	}

	public String getAttachmentName(){
		return this.attachmentName;
	}

	public void setAttachmentUrl(String attachmentUrl){
		this.attachmentUrl=attachmentUrl;
	}

	public String getAttachmentUrl(){
		return this.attachmentUrl;
	}

	public void setAttachmentKey(String attachmentKey){
		this.attachmentKey=attachmentKey;
	}

	public String getAttachmentKey(){
		return this.attachmentKey;
	}

	public void setAttachmentExt(String attachmentExt){
		this.attachmentExt=attachmentExt;
	}

	public String getAttachmentExt(){
		return this.attachmentExt;
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
