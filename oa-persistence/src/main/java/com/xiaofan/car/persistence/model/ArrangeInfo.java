package com.xiaofan.car.persistence.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * 排班设置
 * 
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArrangeInfo{

	/**主键id**/
	private Integer id;

	private String arrangeName;

	/**早班开始时间：时分**/
	private String morningStart;

	/**早班结束时间：时分**/
	private String morningEnd;

	/**晚班开始时间：时分**/
	private String noonStart;

	/**晚班结束时间：时分**/
	private String noonEnd;

	/**是否启用：1,启用 2：停用**/
	private Integer isEffective;

	/**是否有效：1，删除状态**/
	private Integer deleteStatus;

	/**创建时间**/
	private java.util.Date createTime;

	/**更新时间**/
	private java.util.Date updateTime;

	/**创建人时间**/
	private Integer createUserId;


}
