package com.xiaofan.car.api;

import com.xiaofan.car.persistence.model.Device;
import com.xiaofan.car.persistence.param.DeviceParam;
import com.xiaofan.car.persistence.vo.JsonResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:duhongda
 * @Description: 设备服务管理
 * @Date:Create in 15:16 2017/9/15
 */
@RestController
@RequestMapping("/v1/service")
@Slf4j
public class ServiceController {
    @ApiOperation(value = "获取所有设备服务", notes = "获取所有设备服务", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/getlist")
    public JsonResult<DeviceParam> getlist(DeviceParam param) {

        return new JsonResult<DeviceParam>(1, "获取成功");
    }
    @ApiOperation(value = "新增设备服务", notes = "新增设备服务", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/add")
    public JsonResult<DeviceParam> add(Device param) {

        return new JsonResult<DeviceParam>(1, "获取成功");
    }
    @ApiOperation(value = "修改设备服务", notes = "修改设备服务", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PutMapping("/update")
    public JsonResult<DeviceParam> update(Device param) {

        return new JsonResult<DeviceParam>(1, "获取成功");
    }
}
