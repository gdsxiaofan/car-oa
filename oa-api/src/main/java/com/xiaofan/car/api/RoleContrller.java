package com.xiaofan.car.api;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.persistence.param.RoleListQueryParam;
import com.xiaofan.car.persistence.param.RoleParam;
import com.xiaofan.car.persistence.vo.EmployeeVo;
import com.xiaofan.car.persistence.vo.JsonResult;
import com.xiaofan.car.persistence.vo.RoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理
 *
 * @author gongdaoshun
 * @date 2017/8/26
 * @since 1.0.0
 */
@Api(value = "RoleContrller", description = "角色管理相关api")
@RestController
@RequestMapping("/v1/role")
@Slf4j
public class RoleContrller {


    @ApiOperation(value = "查找角色列表", notes = "查找角色", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping("/query")
    public JsonResult<PageInfo<RoleVo>> getRoleList(RoleListQueryParam roleListQueryParam){


        return null;
    }


    @ApiOperation(value = "查找用户信息", notes = "查找用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping("/query/user")
    public JsonResult<List<EmployeeVo>> getRoleUserList(
            @RequestParam("roleId") Integer roleId
    ){

        return null;
    }


    @ApiOperation(value = "更新对应的角色信息", notes = "更新角色", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping("/update")
    public JsonResult<String> updateRole(
            @RequestBody RoleParam roleParam
    ){


        return null;
    }


    @ApiOperation(value = "删除对应的角色", notes = "删除角色", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping("/delete")
    public JsonResult<String> deleteRole(@RequestParam("roleId")Integer roleId){


        return null;
    }

}
