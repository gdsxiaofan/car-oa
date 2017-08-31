package com.xiaofan.car.api;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.biz.RoleBiz;
import com.xiaofan.car.biz.UserBiz;
import com.xiaofan.car.persistence.model.Employee;
import com.xiaofan.car.persistence.param.UserQueryParam;
import com.xiaofan.car.persistence.vo.EmployeeVo;
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
    @Autowired
    private RoleBiz roleBiz;


    @ApiOperation(value = "查找用户信息", notes = "查找用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/query/list")
    public JsonResult<PageInfo<EmployeeVo>> getRoleUserList(
            UserQueryParam userQueryParam
    ) {
        JsonResult<PageInfo<EmployeeVo>> jsonReturn = new JsonResult<>();
        try {
            PageInfo<EmployeeVo> employeeVoList = roleBiz.getEmployeeByRoleIdNoName(userQueryParam);

            jsonReturn.setData(employeeVoList);
        } catch (RuntimeException re) {
            jsonReturn.setCode(0);
            jsonReturn.setMessage(re.getMessage());
        } catch (Exception e) {
            jsonReturn.setCode(0);
            jsonReturn.setMessage("系统异常！");
        }

        return jsonReturn;
    }

    @ApiOperation(value = "获取当前用户、角色、权限信息", notes = "获取用户权限相关信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/query")
    public JsonResult<UserPermissionVo> getUserPermissionVo(
            HttpServletResponse response
    ) {
        Integer userId = JwtUtil.parseJwt2Id(response.getHeader(Constant.AUTHORIZATION));
        JsonResult<UserPermissionVo> returnJson = new JsonResult<>();

        UserPermissionVo userPermissionVo = userBiz.getUserPermisson(userId);

        returnJson.setData(userPermissionVo);

        return returnJson;
    }

    @ApiOperation(value = "修改登录用户密码", notes = "修改登录用户密码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PutMapping("/updatePwd")
    public JsonResult<String> updatePwd(
            String oldPwd, String newPwd,
            HttpServletResponse response
    ) {
        Integer userId = JwtUtil.parseJwt2Id(response.getHeader(Constant.AUTHORIZATION));
        Boolean rs = userBiz.updatePwd(userId, oldPwd, newPwd);
        if (rs) {
            return new JsonResult<>(1, "修改成功");
        } else {
            return new JsonResult<>(0, "旧密码不正确");
        }
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PutMapping("/updateUser")
    public JsonResult<String> updateUser(
            @RequestBody Employee employee
    ) {
        try{
            userBiz.updateUser(employee);
        }catch (Exception e){
            return new JsonResult<>(0, "修改失败");
        }

        return new JsonResult<>(1, "修改成功");
    }

}

