package com.xiaofan.car.persistence.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/9/28
 * @since 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceInfoVo {

    private Integer id;
    private String serviceName;
    private String deviceName;
    private String property1;
    private String property2;
    private String property3;
    private String property4;
    private String serviceDescrible;
}
