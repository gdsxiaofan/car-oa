package com.xiaofan.car.utilApi;

import com.xiaofan.car.biz.AttachmentBiz;
import com.xiaofan.car.persistence.vo.JsonResult;
import com.xiaofan.car.util.file.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by ppctest02 on 2017/4/27.
 */
@RequestMapping("v1/image")
@RestController
public class ImageController {

    private static final Logger log = LoggerFactory.getLogger(ImageController.class);

    @Value(value = "${root-path}")
    private String ROOTPATH;

    @Autowired
    private AttachmentBiz attachmentBiz;

    /**
     * 上传图片
     *
     * @param file 图片
     * @return
     */
    @RequestMapping("/upPic")
    public JsonResult<String> upPic(
            HttpServletRequest request,
            @RequestParam("file") MultipartFile file

    ) {
        try {
//            String filename= FileUtil.upload(ROOTPATH , file, UUID.randomUUID().toString());
            Integer id = attachmentBiz.addAttachment(file);
            return new JsonResult<>(1,id.toString(),file.getOriginalFilename());
        } catch (Exception e) {
            log.error("上传图片失败 :{}",e.getMessage());
            return new JsonResult<>(0,"上传图片失败");
        }
    }

    @RequestMapping(value = "/sosOutImg/**")
    public void getImagess(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String pattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String finlpath = antPathMatcher.extractPathWithinPattern(pattern, path);
        File file = new File(FilenameUtils.concat(ROOTPATH, finlpath));
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/*");

//        ServletOutputStream sos = resp.getOutputStream();
        try(ServletOutputStream sos = resp.getOutputStream()) {
            if (!file.exists() || file.isDirectory()) {
                throw new FileNotFoundException();
            }
            FileUtils.copyFile(file, sos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            resp.sendError(404, "找不到对应文件");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
