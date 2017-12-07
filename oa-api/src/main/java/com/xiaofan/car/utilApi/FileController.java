package com.xiaofan.car.utilApi;

import com.sun.deploy.net.URLEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 提供模板下载的功能
 *
 * @author gongdaoshun
 * @date 2017/12/6 0006
 * @since 1.0.0
 */
@RequestMapping("v1/file")
@RestController
@Slf4j
public class FileController {

    /**
     * 下载工单设备上传信息模板
     *
     * @param response
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void getTpmDeviceTemplateFile(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userAgent = request.getHeader("User-Agent");

            String filename = "工单设备上传模板.xlsx";

            //针对IE或者以IE为内核的浏览器：
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                filename = URLEncoder.encode(filename, "UTF-8");

            } else {
                //非IE浏览器的处理：
                filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
            }
            InputStream is = this.getClass().getResourceAsStream("/template/工单设备上传模板.xlsx");

            // 设置response内容的类型
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", filename));
            response.setCharacterEncoding("UTF-8");
            /**
             * 将要下载的文件内容通过输出流写回到浏览器
             */
            OutputStream os = response.getOutputStream();
            int len = -1;
            byte[] b = new byte[1024 * 100];
            while ((len = is.read(b)) != -1) {
                os.write(b, 0, len);
                os.flush();
            }
            os.close();
            is.close();
        } catch (Exception e) {
            log.error("获取设备上传模板失败", e);
        }
    }
}
