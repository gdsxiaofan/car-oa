package com.xiaofan.car.persistence.model;
import lombok.Data;


/**
 * 
 * 台账信息
 * 
 **/
@Data
public class LedgerInfo{

	/**自增主键**/
	private Integer id;

	/**设备编号**/
	private String deviceNo;

	/**录入人员**/
	private String createUser;

	/**创建时间**/
	private java.util.Date createTime;

	/**更新时间**/
	private java.util.Date updateTime;

	/**删除状态：1：正常状态，0：删除**/
	private Integer deleteStatus;

}
