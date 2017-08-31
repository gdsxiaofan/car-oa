package com.xiaofan.car.api;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.biz.RoleBiz;
import com.xiaofan.car.persistence.model.Memu;
import com.xiaofan.car.persistence.param.RoleListQueryParam;
import com.xiaofan.car.persistence.param.RoleParam;
import com.xiaofan.car.persistence.vo.JsonResult;
import com.xiaofan.car.persistence.vo.RoleVo;
import com.xiaofan.car.service.MemuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RoleContrlloer {

    @Autowired
    private RoleBiz roleBiz;
    @Autowired
    private MemuService memuService;

    @ApiOperation(value = "查找角色列表", notes = "查找角色", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/query")
    public JsonResult<PageInfo<RoleVo>> getRoleList(RoleListQueryParam roleListQueryParam) {
        JsonResult<PageInfo<RoleVo>> jsonReturn = new JsonResult<>();
        try {
            PageInfo<RoleVo> roleVoPageInfo = roleBiz.getRoleList(roleListQueryParam);

            jsonReturn.setData(roleVoPageInfo);
        } catch (RuntimeException re) {
            jsonReturn.setCode(0);
            jsonReturn.setMessage(re.getMessage());
        } catch (Exception e) {
            jsonReturn.setCode(0);
            jsonReturn.setMessage("系统异常！");
        }

        return jsonReturn;
    }

    @ApiOperation(value = "获取所有菜单", notes = "获取所有菜单", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/getAllMenu")
    public JsonResult<List<Memu>> getAllMenu() {
        List<Memu> memus = memuService.getAllMenu();

        return new JsonResult<List<Memu>>(1, "获取成功", memus);
    }

    @ApiOperation(value = "获取角色所有菜单", notes = "获取角色所有菜单", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/getAllMenuByRoleId")
    public JsonResult<RoleParam> getAllMenuByRoleId(Integer id) {
        RoleParam memus = memuService.getAllMenuByRoleId(id);

        return new JsonResult<RoleParam>(1, "获取成功", memus);
    }

    @ApiOperation(value = "更新对应的角色信息", notes = "更新角色", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PutMapping("/update")
    public JsonResult<String> updateRole(
            @RequestBody RoleParam roleParam
    ) {
        roleBiz.updateRole(roleParam);

        return new JsonResult<>(1, "更新成功");
    }

    @ApiOperation(value = "新增角色信息", notes = "新增角色", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/add")
    public JsonResult<String> addRole(
            @RequestBody RoleParam roleParam
    ) {
        roleBiz.insertRole(roleParam);

        return new JsonResult<>(1, "新增成功");
    }

    @ApiOperation(value = "删除对应的角色", notes = "删除角色", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @DeleteMapping("/delete")
    public JsonResult<String> deleteRole(@RequestParam("roleId") Integer roleId) {
        roleBiz.deleteRole(roleId);

        return new JsonResult<>(1, "删除成功");
    }

}
