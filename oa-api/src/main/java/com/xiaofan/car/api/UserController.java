package com.xiaofan.car.api;

import com.xiaofan.car.biz.UserBiz;
import com.xiaofan.car.persistence.param.UserQueryParam;
import com.xiaofan.car.persistence.vo.JsonResult;
import com.xiaofan.car.persistence.vo.UserPermissionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by gongdaoshun on 2017/8/26.
 */
@Api(value = "UserController", description = "用户相关api")
@RestController
@RequestMapping("/v1/user")
@Slf4j
public class UserController {

    @Autowired
    private UserBiz userBiz;

    @ApiOperation(value = "查找用户", notes = "查找用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/query/list")
    public JsonResult<String> queryUserList(
            @RequestBody UserQueryParam userQueryParam
    ){
        return null;
    }

    @ApiOperation(value = "获取当前用户、角色、权限信息", notes = "获取用户权限相关信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/query")
    public JsonResult<UserPermissionVo> getUserPermissionVo(
            @RequestParam("userId") Integer userId
    ){
        JsonResult<UserPermissionVo> returnJson = new JsonResult<>();

        UserPermissionVo userPermissionVo = userBiz.getUserPermisson(userId);

        returnJson.setData(userPermissionVo);

        return returnJson;
    }

}

