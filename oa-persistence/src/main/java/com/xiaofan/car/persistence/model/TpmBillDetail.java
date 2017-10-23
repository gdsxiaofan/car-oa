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

	private Integer tpmId;

}
