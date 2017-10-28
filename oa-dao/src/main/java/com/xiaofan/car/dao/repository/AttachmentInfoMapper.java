package com.xiaofan.car.dao.repository;

import com.sun.xml.internal.ws.api.message.Attachment;
import com.xiaofan.car.persistence.model.AttachmentInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 
 * AttachmentInfoMapper数据库操作接口类
 * 
 **/
@Repository
public interface AttachmentInfoMapper{

    /**
     * 保存对应的附件信息
     * @param attachmentInfo
     * @return
     */
    public Integer insertSelective(AttachmentInfo attachmentInfo);

    public List<AttachmentInfo> selectAttachmentByBizId(
            @Param("bizType")Integer bizType,
            @Param("bizId")Integer bizId
    );

    public boolean updateAttachment(
            @Param("bizType")Integer bizType,
            @Param("bizId")Integer bizId,
            @Param("id")Integer id
    );

    AttachmentInfo selectByPrimaryKey(@Param("id")Integer id);
}