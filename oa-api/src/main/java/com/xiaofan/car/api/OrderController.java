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
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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
    public JsonResult<PageInfo<RoleVo>> updateOrderStatus(Employee employee) {
        Employee user = loginBiz.verificationForLogin(employee.getEmployeeNo(), employee.getEmployeePassword());
        if (user == null) {
            return new JsonResult<>(0, "工号或密码不正确");
        }
        return new JsonResult<>();
    }

    /**
     * 巡检工单
     * @param orderType 改变后的状态
     * @param desc   描述
     * @param file   图片
     * @return
     */
    @ApiOperation(value = "巡检工单", notes = "巡检工单", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PutMapping("/doOrder")
    public JsonResult<PageInfo<RoleVo>> doLook(
            OrderType orderType,
            String desc,
            @RequestParam(value = "file",required = false) MultipartFile[] file) {

        return new JsonResult<>();
    }

}
