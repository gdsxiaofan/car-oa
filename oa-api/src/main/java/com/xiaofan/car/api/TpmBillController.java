package com.xiaofan.car.api;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.xiaofan.car.persistence.param.DeviceParam;
import com.xiaofan.car.persistence.param.TpmBillParam;
import com.xiaofan.car.persistence.param.TpmBillQueryParam;
import com.xiaofan.car.persistence.vo.DeviceInfoVo;
import com.xiaofan.car.persistence.vo.JsonResult;
import com.xiaofan.car.persistence.vo.TpmBillVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 工单管理模块
 *
 * @author gongdaoshun
 * @date 2017/10/13
 * @since 1.0.0
 */
@RestController
@RequestMapping("/v1/tpm")
@Slf4j
public class TpmBillController {

    @ApiOperation(value = "获取工单信息列表", notes = "获取工单信息列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/getlist")
    public JsonResult<PageInfo<TpmBillVo>> getlist(TpmBillQueryParam param) {

        return new JsonResult<PageInfo<TpmBillVo>>(1, "获取成功");
    }

    @ApiOperation(value = "更新工单的状态", notes = "更新工单的状态", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PutMapping("/update")
    public JsonResult updateTpmBill(TpmBillParam tpmBillParam){
        return new JsonResult(1, "操作成功");
    }
}
