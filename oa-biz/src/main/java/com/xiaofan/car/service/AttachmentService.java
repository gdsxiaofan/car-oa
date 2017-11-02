package com.xiaofan.car.service;

import com.xiaofan.car.persistence.enumType.AttachmentBizTypeEnum;
import com.xiaofan.car.persistence.model.AttachmentInfo;
import com.xiaofan.car.persistence.vo.AttachmentVo;

import java.util.List;

/**
 *  附件相关接口定义
 *
 * @author gongdaoshun
 * @date 2017/10/16
 * @since 1.0.0
 */
public interface AttachmentService {

    /**
     * 保存附件信息
     * @param attachmentInfo
     * @return
     */
    public Integer saveAttachment(AttachmentInfo attachmentInfo);

    public List<AttachmentVo> getAttachmentVoList(Integer bizId, AttachmentBizTypeEnum attachmentBizType);

    /**
     * 新增业务关联对应的附件信息
     * @param attachmentIds
     * @param attachmentBizTypeEnum
     * @param bizId
     * @return
     */
    public boolean updateAttachment(List<Integer> attachmentIds,AttachmentBizTypeEnum attachmentBizTypeEnum,Integer bizId);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    AttachmentInfo getAttachmentById(Integer id);
    /**
     * 删除之前的附件信息
     * @param id
     * @return
     */
    void delAttachment(AttachmentBizTypeEnum attachmentBizTypeEnum, Integer id);
}

