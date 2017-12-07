package com.xiaofan.car.api;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.biz.DeviceBiz;
import com.xiaofan.car.persistence.param.DeviceInfoParam;
import com.xiaofan.car.persistence.param.DeviceParam;
import com.xiaofan.car.persistence.vo.DeviceInfoVo;
import com.xiaofan.car.persistence.vo.JsonResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author:duhongda
 * @Description: 设备管理
 * @Date:Create in 15:16 2017/9/15
 */
@RestController
@RequestMapping("/v1/device")
@Slf4j
public class DeviceController {

    @Autowired
    private DeviceBiz deviceBiz;


    @ApiOperation(value = "获取所有设备", notes = "获取所有设备", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/getlist")
    public JsonResult<PageInfo<DeviceInfoVo>> getlist(DeviceParam param) {
        PageInfo<DeviceInfoVo> deviceInfoVoPageInfo = deviceBiz.getDeviceList(param);
        return new JsonResult<PageInfo<DeviceInfoVo>>(1, "获取成功",deviceInfoVoPageInfo);
    }
    @ApiOperation(value = "新增设备", notes = "新增设备", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/add")
    public JsonResult<String> add(@RequestBody DeviceInfoParam param) {
        deviceBiz.addDevice(param);
        return new JsonResult<String>(1, "新增成功");
    }
    @ApiOperation(value = "Excel批量新增设备", notes = "Excel批量新增设备", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/addList")
    public JsonResult<String> addList(@RequestParam MultipartFile file) {

        return new JsonResult<String>(1, "新增成功");
    }
    @ApiOperation(value = "修改设备", notes = "修改设备", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PutMapping("/update")
    public JsonResult<String> update(@RequestBody DeviceInfoParam param) {
        deviceBiz.updateDevice(param);
        return new JsonResult<String>(1, "修改成功");
    }

    @ApiOperation(value = "查询设备详情", notes = "查看设备详情", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/detail")
    public JsonResult<DeviceInfoVo> getDetail(@RequestParam("id")Integer id) {


            DeviceInfoVo deviceInfoVo = deviceBiz.getDeviceDetail(id);
        return new JsonResult<DeviceInfoVo>(1, "获取成功",deviceInfoVo);
    }

    @ApiOperation(value = "删除设备信息", notes = "删除设备信息", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @DeleteMapping("/delete")
    public JsonResult<DeviceInfoVo> delete(@RequestBody DeviceInfoParam param) {
        deviceBiz.deleteDevice(param.getId());

        //删除对应的服务项

        return new JsonResult<DeviceInfoVo>(1, "删除成功");
    }
}
