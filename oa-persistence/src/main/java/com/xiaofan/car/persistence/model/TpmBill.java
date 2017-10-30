package com.xiaofan.car.persistence.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * 
 * 
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TpmBill{

	/****/
	private Integer id;

	/**价差项id**/
	private Integer checkId;

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

	/**巡检工单的用户名**/
	private String finishUserName;

	/**巡检工单的用户id**/
	private Integer finishUserId;

	/**巡检备注**/
	private String finishComment;

	/**修复完成时间**/
	private java.util.Date reapiredTime;

	/**维修工单的人**/
	private Integer repairedUserId;

	/**维修员工名称**/
	private String repairedUserName;

	/**维修备注**/
	private String repairedComment;

	/**审核用户id**/
	private Integer auditUserId;

	/**审核人姓名**/
	private String auditUserName;

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

}
