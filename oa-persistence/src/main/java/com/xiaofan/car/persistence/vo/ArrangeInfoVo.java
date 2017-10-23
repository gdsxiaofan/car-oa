package com.xiaofan.car.persistence.vo;

import com.xiaofan.car.persistence.enumType.IsEffectiveEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/10/23
 * @since 1.0.0
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ArrangeInfoVo {
    /**主键id**/
    private Integer id;

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

    /**创建人时间**/
    private Integer createUserId;

    private String isEffectiveName;

    public void setIsEffective(Integer isEffective){
        this.isEffective = isEffective;
        this.isEffectiveName= IsEffectiveEnum.valuesOf(isEffective).getName();
    }
}
