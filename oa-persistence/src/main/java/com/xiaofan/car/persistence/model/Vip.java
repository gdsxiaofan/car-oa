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

}
