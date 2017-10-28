package com.xiaofan.car.persistence.model;
import com.xiaofan.car.persistence.enumType.ShiftsNoEnum;
import lombok.Data;


/**
 * 
 * 员工信息
 * 
 **/
@Data
public class Employee{

	/**自增主键**/
	private Integer id;

	/**员工姓名**/
	private String employeeName;

	/**员工密码**/
	private String employeePassword;

	/**员工手机号码**/
	private String employeeMobile;

	/**员工号**/
	private String employeeNo;

	/**角色id**/
	private Integer roleId;

	/**入职时间**/
	private java.util.Date inductionTime;

	/**员工状态：1：在职，2休假，3：离职**/
	private Integer employeeStatus;

	/**创建时间**/
	private java.util.Date createTime;

	/**更新时间**/
	private java.util.Date updateTime;
	/**是否可用0:停用1:可用**/
	private Boolean isActive;

	/**删除状态：1：正常状态，0：删除**/
	private Integer deleteStatus;

	/** 排班设置 1：早班 2：晚班 0：无排班设置**/
	private Integer shiftsNo;

	private String shiftsNoName;

	public void setShiftsNo(Integer shiftsNo){
		this.shiftsNo = shiftsNo;
		this.shiftsNoName = ShiftsNoEnum.valuesOf(shiftsNo).getName();
	}


}
