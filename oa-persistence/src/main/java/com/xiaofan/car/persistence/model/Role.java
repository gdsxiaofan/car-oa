package com.xiaofan.car.persistence.model;
import lombok.Data;


/**
 * 
 * 
 * 
 **/
@Data
public class Role{

	/****/
	private Integer id;

	/**角色名称**/
	private String roleName
roleName;

	/**父节点权限id**/
	private Integer parentRoleId;

	/**角色code**/
	private String roleCode;

	/**有效状态：1：有效 0：无效**/
	private Integer deleteStaus;

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

	public void setRoleName
roleName(String roleName
roleName){
		this.roleName
roleName=roleName
roleName;
	}

	public String getRoleName
roleName(){
		return this.roleName
roleName;
	}

	public void setParentRoleId(Integer parentRoleId){
		this.parentRoleId=parentRoleId;
	}

	public Integer getParentRoleId(){
		return this.parentRoleId;
	}

	public void setRoleCode(String roleCode){
		this.roleCode=roleCode;
	}

	public String getRoleCode(){
		return this.roleCode;
	}

	public void setDeleteStaus(Integer deleteStaus){
		this.deleteStaus=deleteStaus;
	}

	public Integer getDeleteStaus(){
		return this.deleteStaus;
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
