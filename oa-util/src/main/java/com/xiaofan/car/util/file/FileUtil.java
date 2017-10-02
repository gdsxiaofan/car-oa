package com.xiaofan.car.util.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by ppctest02 on 2017/4/26.
 */
@Slf4j
public class FileUtil {

    private FileUtil() {
        throw new IllegalAccessError("Utility class");
    }
    /**
     * 文件上传
     *
     * @param basePath
     * @param multipartFile
     * @param dir
     * @return
     * @throws IOException
     */
    public static String upload(String basePath, MultipartFile multipartFile, String dir) throws IOException {
        String saveFilePath = null;
        if (multipartFile != null && !multipartFile.isEmpty()) {
            try {
                //扩展名
                String ext = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
                //图片名称生成策略
                DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                //图片名称一部分
                StringBuilder format = new StringBuilder(df.format(new Date()));
                //随机三位数
                Random r = new Random();
                // n 1000   0-999   99
                for (int i = 0; i < 3; i++) {
                    format.append(r.nextInt(10));
                }
                //文件名称
                String fileName = dir + format + "." + ext;
                int hashCode = fileName.hashCode();
                int dir1 = hashCode & 0xff;
                int dir2 = (hashCode & 0xff) >> 4;
                //存放文件路径
                String uploadFilePath = basePath + File.separator + dir + File.separator + dir1 + File.separator + dir2;
                //访问文件夹路径
                saveFilePath = File.separator + dir + File.separator + dir1 + File.separator + dir2 + File.separator + fileName;
                //文件夹是否存在
                File filePath = new File(uploadFilePath);
                if (!filePath.exists() && !filePath.isDirectory()) {
                    filePath.mkdir();
                }
                FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(filePath, fileName));
                return saveFilePath;
            } catch (Exception e) {
                log.error("upload()", e);
            }
            return saveFilePath;
        }
        return null;
    }
}
