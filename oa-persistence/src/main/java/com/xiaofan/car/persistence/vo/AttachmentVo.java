package com.xiaofan.car.persistence.vo;

import lombok.Data;

/**
 *  附件信息
 *
 * @author gongdaoshun
 * @date 2017/10/16
 * @since 1.0.0
 */
@Data
public class AttachmentVo {
    /****/
    private Integer id;

    /**业务id**/
    private Integer bizId;

    /**业务类型：1：工单，2：工单明细，3：设备，4：检查项**/
    private Integer bizType;

    /**附件名称**/
    private String attachmentName;

    /**附件url**/
    private String attachmentUrl;

    /**附件key**/
    private String attachmentKey;

    /**附件后缀**/
    private String attachmentExt;

}
