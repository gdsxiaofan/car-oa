package com.xiaofan.car.api;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.biz.RoleBiz;
import com.xiaofan.car.persistence.param.RoleListQueryParam;
import com.xiaofan.car.persistence.param.RoleParam;
import com.xiaofan.car.persistence.vo.JsonResult;
import com.xiaofan.car.persistence.vo.RoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
public class RoleContrlloer {

    @Autowired
    private RoleBiz roleBiz;

    @ApiOperation(value = "查找角色列表", notes = "查找角色", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping("/query")
    public JsonResult<PageInfo<RoleVo>> getRoleList(RoleListQueryParam roleListQueryParam){
        JsonResult<PageInfo<RoleVo>> jsonReturn = new JsonResult<>();
        try{
            PageInfo<RoleVo> roleVoPageInfo = roleBiz.getRoleList(roleListQueryParam);

            jsonReturn.setData(roleVoPageInfo);
        }
        catch(RuntimeException re){
            jsonReturn.setCode(0);
            jsonReturn.setMessage(re.getMessage());
        }
        catch (Exception e){
            jsonReturn.setCode(0);
            jsonReturn.setMessage("系统异常！");
        }

        return jsonReturn;
    }

    @ApiOperation(value = "更新对应的角色信息", notes = "更新角色", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping("/update")
    public JsonResult<String> updateRole(
            @RequestBody RoleParam roleParam
    ){
//todo

        return null;
    }


    @ApiOperation(value = "删除对应的角色", notes = "删除角色", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @DeleteMapping("/delete")
    public JsonResult<String> deleteRole(@RequestParam("roleId")Integer roleId){
        //todo

        return new JsonResult<>(1,"删除成功");
    }

}
