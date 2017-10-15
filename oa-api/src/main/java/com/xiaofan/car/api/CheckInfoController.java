package com.xiaofan.car.api;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.persistence.model.CheckInfo;
import com.xiaofan.car.persistence.param.CheckInfoParam;
import com.xiaofan.car.persistence.param.DeviceInfoParam;
import com.xiaofan.car.persistence.param.ServiceParam;
import com.xiaofan.car.persistence.vo.CheckInfoVo;
import com.xiaofan.car.persistence.vo.DeviceInfoVo;
import com.xiaofan.car.persistence.vo.JsonResult;
import com.xiaofan.car.persistence.vo.ServiceInfoVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/10/13
 * @since 1.0.0
 */
@RestController
@RequestMapping("/v1/check")
@Slf4j
public class CheckInfoController {


    @ApiOperation(value = "获取所有设备服务", notes = "获取所有设备服务", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/getlist")
    public JsonResult<List<CheckInfoVo>> getlist(int deviceId) {
        List pageInfo=new ArrayList<>();
        CheckInfoVo checkInfoVo = CheckInfoVo.builder()
                .id(1)
                .checkPoint("checkpoint")
                .checkComment("comment")
                .setValue("value")
                .systemId(3)
                .build();
        checkInfoVo.setShiftsNo(2);
        pageInfo.add(checkInfoVo);
        return new JsonResult<List<CheckInfoVo>>(1, "获取成功",pageInfo);
    }
    @ApiOperation(value = "新增检查信息", notes = "新增检查信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/add")
    public JsonResult<CheckInfoVo> add(@RequestBody CheckInfoParam param) {
        return new JsonResult<CheckInfoVo>(1, "获取成功");
    }
    @ApiOperation(value = "修改检查信息", notes = "修改检查信息", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PutMapping("/update")
    public JsonResult<CheckInfoVo> update(@RequestBody CheckInfoParam param) {
        return new JsonResult<CheckInfoVo>(1, "修改成功");
    }

    @ApiOperation(value = "删除检查信息", notes = "删除检查信息", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @DeleteMapping("/delete")
    public JsonResult<CheckInfoVo> delete(@RequestBody CheckInfoParam param) {
        return new JsonResult<CheckInfoVo>(1, "获取成功");
    }

}
