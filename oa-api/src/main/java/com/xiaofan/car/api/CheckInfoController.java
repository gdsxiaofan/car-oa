package com.xiaofan.car.api;

import com.xiaofan.car.persistence.model.CheckInfo;
import com.xiaofan.car.persistence.param.CheckInfoParam;
import com.xiaofan.car.persistence.param.DeviceInfoParam;
import com.xiaofan.car.persistence.vo.CheckInfoVo;
import com.xiaofan.car.persistence.vo.DeviceInfoVo;
import com.xiaofan.car.persistence.vo.JsonResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "新增检查信息", notes = "新增检查信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/add")
    public JsonResult<CheckInfoVo> add(CheckInfoParam param) {
        return new JsonResult<CheckInfoVo>(1, "获取成功");
    }
    @ApiOperation(value = "修改检查信息", notes = "修改检查信息", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PutMapping("/update")
    public JsonResult<CheckInfoVo> update(CheckInfoParam param) {
        return new JsonResult<CheckInfoVo>(1, "修改成功");
    }

    @ApiOperation(value = "删除检查信息", notes = "删除检查信息", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @DeleteMapping("/delete")
    public JsonResult<CheckInfoVo> delete(CheckInfoParam param) {
        return new JsonResult<CheckInfoVo>(1, "获取成功");
    }

}
