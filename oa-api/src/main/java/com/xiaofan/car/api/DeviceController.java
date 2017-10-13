package com.xiaofan.car.api;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.xiaofan.car.persistence.model.Device;
import com.xiaofan.car.persistence.param.DeviceInfoParam;
import com.xiaofan.car.persistence.param.DeviceParam;
import com.xiaofan.car.persistence.vo.DeviceInfoVo;
import com.xiaofan.car.persistence.vo.JsonResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:duhongda
 * @Description: 设备管理
 * @Date:Create in 15:16 2017/9/15
 */
@RestController
@RequestMapping("/v1/device")
@Slf4j
public class DeviceController {
    @ApiOperation(value = "获取所有设备", notes = "获取所有设备", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/getlist")
    public JsonResult<PageInfo<DeviceInfoVo>> getlist(DeviceParam param) {

        return new JsonResult<PageInfo<DeviceInfoVo>>(1, "获取成功");
    }
    @ApiOperation(value = "新增设备", notes = "新增设备", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/add")
    public JsonResult<DeviceInfoVo> add(DeviceInfoParam param) {

        return new JsonResult<DeviceInfoVo>(1, "新增成功");
    }
    @ApiOperation(value = "修改设备", notes = "修改设备", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PutMapping("/update")
    public JsonResult<DeviceInfoVo> update(DeviceInfoParam param) {

        return new JsonResult<DeviceInfoVo>(1, "修改成功");
    }

    @ApiOperation(value = "查询设备详情", notes = "查看设备详情", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/detail")
    public JsonResult<DeviceInfoVo> getDetail(DeviceInfoParam param) {

        return new JsonResult<DeviceInfoVo>(1, "获取成功");
    }

    @ApiOperation(value = "删除设备信息", notes = "删除设备信息", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @DeleteMapping("/delete")
    public JsonResult<DeviceInfoVo> delete(DeviceInfoParam param) {

        return new JsonResult<DeviceInfoVo>(1, "删除成功");
    }
}
