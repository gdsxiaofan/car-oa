package com.xiaofan.car.biz;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by gongdaoshun on 2017/10/28.
 */
public interface AttachmentBiz {
    /**
     * 保存附件信息
     * @param multipartFile
     * @return
     */
    public Integer addAttachment(MultipartFile multipartFile) throws IOException;
}
