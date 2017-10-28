package com.xiaofan.car.biz.impl;

import com.xiaofan.car.biz.AttachmentBiz;
import com.xiaofan.car.persistence.model.AttachmentInfo;
import com.xiaofan.car.service.AttachmentService;
import com.xiaofan.car.util.file.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by gongdaoshun on 2017/10/28.
 */
@Service
@Slf4j
public class AttachmentBizImpl implements AttachmentBiz{

    @Value(value = "${root-path}")
    private String ROOTPATH;

    @Autowired
    private AttachmentService attachmentService;
    @Override
    public Integer addAttachment(MultipartFile multipartFile) throws IOException {

        // 1.保存附件到本地磁盘
        String filePath= FileUtil.upload(ROOTPATH , multipartFile, UUID.randomUUID().toString());

        // 2.保存当前附件记录到数据库中
        String fileName = multipartFile.getName();
        String ext = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

        AttachmentInfo attachmentInfo = new AttachmentInfo().builder()
                .attachmentExt(ext)
                .attachmentName(fileName)
                .attachmentUrl(filePath)
                .build();

        return attachmentService.saveAttachment(attachmentInfo);
    }
}
