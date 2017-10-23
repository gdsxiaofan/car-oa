package com.xiaofan.car.persistence.param;

import lombok.Data;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/10/23
 * @since 1.0.0
 */
@Data
public class ArrangeParam {
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



}
