package com.xiaofan.car.persistence.model;
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



	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setEmployeeName(String employeeName){
		this.employeeName=employeeName;
	}

	public String getEmployeeName(){
		return this.employeeName;
	}

	public void setEmployeePassword(String employeePassword){
		this.employeePassword=employeePassword;
	}

	public String getEmployeePassword(){
		return this.employeePassword;
	}

	public void setEmployeeMobile(String employeeMobile){
		this.employeeMobile=employeeMobile;
	}

	public String getEmployeeMobile(){
		return this.employeeMobile;
	}

	public void setEmployeeNo(String employeeNo){
		this.employeeNo=employeeNo;
	}

	public String getEmployeeNo(){
		return this.employeeNo;
	}

	public void setRoleId(Integer roleId){
		this.roleId=roleId;
	}

	public Integer getRoleId(){
		return this.roleId;
	}

	public void setInductionTime(java.util.Date inductionTime){
		this.inductionTime=inductionTime;
	}

	public java.util.Date getInductionTime(){
		return this.inductionTime;
	}

	public void setEmployeeStatus(Integer employeeStatus){
		this.employeeStatus=employeeStatus;
	}

	public Integer getEmployeeStatus(){
		return this.employeeStatus;
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

	public void setDeleteStatus(Integer deleteStatus){
		this.deleteStatus=deleteStatus;
	}

	public Integer getDeleteStatus(){
		return this.deleteStatus;
	}

}
