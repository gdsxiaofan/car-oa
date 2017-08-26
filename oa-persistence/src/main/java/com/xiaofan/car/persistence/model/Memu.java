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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Memu{

	/****/
	private Integer id;

	/**菜单名称**/
	private String name;

	/**菜单url**/
	private String href;

	/**待定**/
	private String icon;

	/**菜单层级：1最高**/
	private Integer orderTop;

	/**父菜单id**/
	private Integer parentId;

	/****/
	private Integer deleteStatus;

	/**创建时间**/
	private java.util.Date createTime;

	/**更新时间**/
	private java.util.Date updateTime;


}
