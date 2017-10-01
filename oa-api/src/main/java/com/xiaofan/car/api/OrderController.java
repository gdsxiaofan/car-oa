package com.xiaofan.car.api;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.persistence.param.RoleListQueryParam;
import com.xiaofan.car.persistence.vo.JsonResult;
import com.xiaofan.car.persistence.vo.RoleVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: bean
 * @Description: 工单相关
 * @Date: Create in 2017/10/1-17:52
 */
@Controller
@Slf4j
@RequestMapping("/v1/order")
public class OrderController {
    @ApiOperation(value = "查找工单列表", notes = "工单", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/list")
    public JsonResult<PageInfo<RoleVo>> getRoleList(RoleListQueryParam roleListQueryParam) {
        JsonResult<PageInfo<RoleVo>> jsonReturn = new JsonResult<>();

        return jsonReturn;
    }
}
