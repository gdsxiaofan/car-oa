package com.xiaofan.car.api;

import com.xiaofan.car.biz.LedgerInfoBiz;
import com.xiaofan.car.persistence.param.LedgerInfoParam;
import com.xiaofan.car.persistence.vo.JsonResult;
import com.xiaofan.car.service.LedgerInfoService;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by gongdaoshun on 2017/8/5.
 */
@RestController
@RequestMapping("/v1/api/ledger")
@Slf4j
public class LedgerApi {

    @Autowired
    LedgerInfoBiz ledgerInfoBiz;
    /**
     *
     * @param ledgerInfoParam
     * @return
     */
    @RequestMapping(value="/apply",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<String> applyLedger(
            LedgerInfoParam ledgerInfoParam
    ){
        log.info("进入报备设备申请接口：{}", ledgerInfoParam.toString());
        JsonResult<String> jsonResult = new JsonResult<String>();

        Integer count = ledgerInfoBiz.applyLedgerInfo(ledgerInfoParam);
        if (count ==1){
            return jsonResult;
        }
        jsonResult.setCode(0);
        jsonResult.setMessage("error");
        return jsonResult;
    }
}
