package com.xiaofan.car.api;

import com.xiaofan.car.biz.UserBiz;
import com.xiaofan.car.persistence.param.UserQueryParam;
import com.xiaofan.car.persistence.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by gongdaoshun on 2017/8/26.
 */
@RestController
@RequestMapping("/v1/user")
@Slf4j
public class UserController {

    @Autowired
    private UserBiz userBiz;

    @GetMapping("/query/list")
    @ResponseBody
    public JsonResult<String> queryUserList(
            @RequestBody UserQueryParam userQueryParam
    ){
        return null;
    }

}

