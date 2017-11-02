package com.xiaofan.car.service.impl;

import com.xiaofan.car.dao.repository.AttachmentInfoMapper;
import com.xiaofan.car.persistence.enumType.AttachmentBizTypeEnum;
import com.xiaofan.car.persistence.model.AttachmentInfo;
import com.xiaofan.car.persistence.vo.AttachmentVo;
import com.xiaofan.car.service.AttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 附件相关接口实现
 * Created by gongdaoshun on 2017/10/28.
 */
@Service
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    AttachmentInfoMapper attachmentInfoMapper;

    @Autowired
    DozerBeanMapper dozerBeanMapper;

    @Override
    public Integer saveAttachment(AttachmentInfo attachmentInfo) {
        return attachmentInfoMapper.insertSelective(attachmentInfo);
    }

    @Override
    public List<AttachmentVo> getAttachmentVoList(Integer bizId, AttachmentBizTypeEnum attachmentBizType) {
        List<AttachmentInfo> attachmentInfos = attachmentInfoMapper.selectAttachmentByBizId(attachmentBizType.getCode(), bizId);
        List<AttachmentVo> attachmentVos = new ArrayList<>();

        for (AttachmentInfo attachmentInfo : attachmentInfos) {
            AttachmentVo attachmentVo = dozerBeanMapper.map(attachmentInfo, AttachmentVo.class);
            attachmentVos.add(attachmentVo);
        }

        return attachmentVos;

    }

    @Override
    public boolean updateAttachment(List<Integer> attachmentIds, AttachmentBizTypeEnum attachmentBizTypeEnum, Integer bizId) {
        for (Integer attachmentId : attachmentIds) {
            attachmentInfoMapper.updateAttachment(attachmentBizTypeEnum.getCode(), bizId, attachmentId);
        }

        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public AttachmentInfo getAttachmentById(Integer id) {
        return attachmentInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除之前的附件信息
     *
     * @param attachmentBizTypeEnum
     * @param id
     * @return
     */
    @Override
    public void delAttachment(AttachmentBizTypeEnum attachmentBizTypeEnum, Integer id) {
        attachmentInfoMapper.delAttachment(attachmentBizTypeEnum.getCode(), id);
    }
}
