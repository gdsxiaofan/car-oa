package com.xiaofan.car.api;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.biz.LoginBiz;
import com.xiaofan.car.persistence.enumType.OrderType;
import com.xiaofan.car.persistence.model.Employee;
import com.xiaofan.car.persistence.param.OrderListQueryParam;
import com.xiaofan.car.persistence.param.OrderParam;
import com.xiaofan.car.persistence.param.RoleListQueryParam;
import com.xiaofan.car.persistence.vo.JsonResult;
import com.xiaofan.car.persistence.vo.RoleVo;
import com.xiaofan.car.util.file.FileUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @Author: bean
 * @Description: 工单相关
 * @Date: Create in 2017/10/1-17:52
 */
@RestController
@Slf4j
@RequestMapping("/v1/order")
public class OrderController {

    @Autowired
    private LoginBiz loginBiz;
    @Value(value = "${root-path}")
    private String ROOTPATH;

    @ApiOperation(value = "查找工单列表", notes = "工单", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/list")
    public JsonResult<PageInfo<RoleVo>> getOrderList(OrderListQueryParam orderListQueryParam) {
        JsonResult<PageInfo<RoleVo>> jsonReturn = new JsonResult<>();

        return jsonReturn;
    }

    @ApiOperation(value = "查找工单详情", notes = "工单", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/info")
    public JsonResult<PageInfo<RoleVo>> getOrderinfo(int id) {
        JsonResult<PageInfo<RoleVo>> jsonReturn = new JsonResult<>();

        return jsonReturn;
    }

    @ApiOperation(value = "根据工号密码修改工单状态", notes = "根据工号密码修改工单状态", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/checkUser")
    public JsonResult<Integer> updateOrderStatus(Employee employee) {
        Employee user = loginBiz.verificationForLogin(employee.getEmployeeNo(), employee.getEmployeePassword());
        if (user == null) {
            return new JsonResult<>(0, "工号或密码不正确");
        }
        return new JsonResult<>(1, "", user.getId());
    }

    /**
     * 巡检工单
     *
     * @param orderType 改变后的状态
     * @param desc      描述
     * @param file      图片
     * @return
     */
    @ApiOperation(value = "巡检工单", notes = "巡检工单", httpMethod = "PUT", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PostMapping("/doOrder")
    public JsonResult<Object> doOrder(
            HttpServletRequest request,
            Integer doUserId,
            OrderType orderType,
            String desc,
            MultipartFile[] file) {
        //todo
        List<String> imgs = new ArrayList<String>();

        try
        {
            for (MultipartFile x : file) {
                String filename = FileUtil.upload(ROOTPATH, x, UUID.randomUUID().toString());
                imgs.add(filename);
            }
            return new JsonResult<>(1, "",imgs );
        } catch (
                IOException e)

        {
            return new JsonResult<>(0, "文件上传失败");
        }

    }

}
