package com.xiaofan.car.api;

import com.xiaofan.car.biz.UserBiz;
import com.xiaofan.car.persistence.param.UserQueryParam;
import com.xiaofan.car.persistence.vo.JsonResult;
import com.xiaofan.car.persistence.vo.UserPermissionVo;
import com.xiaofan.car.util.Constant;
import com.xiaofan.car.util.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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
            HttpServletResponse response
    ){
        Integer userId= JwtUtil.parseJwt2Id(response.getHeader(Constant.AUTHORIZATION));
        JsonResult<UserPermissionVo> returnJson = new JsonResult<>();

        UserPermissionVo userPermissionVo = userBiz.getUserPermisson(userId);

        returnJson.setData(userPermissionVo);

        return returnJson;
    }
    @ApiOperation(value = "修改用户密码", notes = "修改用户密码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PutMapping("/updatePwd")
    public JsonResult<String> updatePwd(
            String oldPwd,String newPwd,
            HttpServletResponse response
    ){
        Integer userId= JwtUtil.parseJwt2Id(response.getHeader(Constant.AUTHORIZATION));
        Boolean rs= userBiz.updatePwd(userId,oldPwd,newPwd);
        if(rs) {
            return new JsonResult<>(1, "修改成功");
        }else {
            return new JsonResult<>(0, "旧密码不正确");
        }
    }

}

