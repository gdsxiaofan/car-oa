package com.xiaofan.car.api;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.biz.ServiceBiz;
import com.xiaofan.car.persistence.model.Device;
import com.xiaofan.car.persistence.param.DeviceParam;
import com.xiaofan.car.persistence.param.ServiceInfoParam;
import com.xiaofan.car.persistence.param.ServiceParam;
import com.xiaofan.car.persistence.vo.JsonResult;
import com.xiaofan.car.persistence.vo.ServiceInfoVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ServiceBiz serviceBiz;

    @ApiOperation(value = "获取所有设备服务", notes = "获取所有设备服务", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/getlist")
    public JsonResult<PageInfo<ServiceInfoVo>> getlist(ServiceParam param) {
        PageInfo<ServiceInfoVo> pageInfo =  serviceBiz.getServiceInfoForList(param,null,null);
        return new JsonResult<PageInfo<ServiceInfoVo>>(1, "获取成功",pageInfo);
    }
    @ApiOperation(value = "新增设备服务", notes = "新增设备服务", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/add")
    public JsonResult<String> add(ServiceInfoParam param) {
        serviceBiz.saveServiceInfo(param,null,null);
        return new JsonResult<String>(1, "新增成功");
    }
    @ApiOperation(value = "修改设备服务", notes = "修改设备服务", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PutMapping("/update")
    public JsonResult<DeviceParam> update(ServiceInfoParam param) {
        serviceBiz.updateServiceInfo(param,null,null);
        return new JsonResult<DeviceParam>(1, "修改成功");
    }
    @ApiOperation(value = "删除设备服务", notes = "删除设备服务", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @DeleteMapping("/del")
    public JsonResult<DeviceParam> del(ServiceInfoParam param) {
        serviceBiz.deleteServiceInfo(param.getId(),null,null);
        return new JsonResult<DeviceParam>(1, "删除成功");
    }

    @ApiOperation(value = "获取服务详细信息", notes = "获取服务详细信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @DeleteMapping("/get")
    public JsonResult<ServiceInfoVo> getDetail(ServiceInfoParam param) {
        ServiceInfoVo serviceInfoVo = serviceBiz.getServiceInfoVoDetail(param.getId(),null,null);
        return new JsonResult<ServiceInfoVo>(1, "获取成功",serviceInfoVo);
    }
}

