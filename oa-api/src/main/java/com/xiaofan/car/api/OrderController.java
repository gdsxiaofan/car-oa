package com.xiaofan.car.api;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.biz.LoginBiz;
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
    @ApiOperation(value = "根据工号密码修改工单状态", notes = "根据工号密码修改工单状态", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PutMapping("/do")
    public JsonResult<PageInfo<RoleVo>> updateOrderStatus(@RequestBody OrderParam orderParam) {
        Employee user=loginBiz.verificationForLogin(orderParam.getEmployee().getEmployeeNo(),orderParam.getEmployee().getEmployeePassword());
        if(user==null){
            return new JsonResult<>(0, "工号或密码不正确");
        }
        //改变工单状态 todo

        return new JsonResult<>();
    }

}
