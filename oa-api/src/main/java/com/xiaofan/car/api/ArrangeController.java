package com.xiaofan.car.api;

import com.xiaofan.car.biz.ArrangeBiz;
import com.xiaofan.car.persistence.param.ArrangeParam;
import com.xiaofan.car.persistence.vo.ArrangeInfoVo;
import com.xiaofan.car.persistence.vo.CheckInfoVo;
import com.xiaofan.car.persistence.vo.JsonResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 排班设置controller入口
 *
 * @author gongdaoshun
 * @date 2017/10/23
 * @since 1.0.0
 */
@RestController
@RequestMapping("/v1/arrange")
@Slf4j
public class ArrangeController {

    @Autowired
    private ArrangeBiz arrangeBiz;

    @ApiOperation(value = "获取所有排班设置", notes = "获取所有排班设置", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/getlist")
    public JsonResult<List<ArrangeInfoVo>> getlist() {
        List<ArrangeInfoVo> arrangeInfoVos = arrangeBiz.selectArrangeList();
        if(CollectionUtils.isEmpty(arrangeInfoVos)){
            return new JsonResult<List<ArrangeInfoVo>>(0, "未查询到数据",arrangeInfoVos);
        }
        return new JsonResult<List<ArrangeInfoVo>>(1, "获取成功",arrangeInfoVos);
    }
    @ApiOperation(value = "新增排班设置", notes = "新增排班设置", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/add")
    public JsonResult<String> add(@RequestBody ArrangeParam param) {

        if(!arrangeBiz.saveArrange(param)){
            return new JsonResult<String>(0, "新增失败，请检查数据");
        }
        return new JsonResult<String>(1, "新增成功");
    }
    @ApiOperation(value = "修改排班信息", notes = "修改排班信息", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PutMapping("/update")
    public JsonResult<String> update(@RequestBody ArrangeParam param) {
        if(!arrangeBiz.updateArrange(param)){
            return new JsonResult<String>(0, "修改失败，请检查数据");
        }
        return new JsonResult<String>(1, "修改成功");
    }

    @ApiOperation(value = "删除排班信息", notes = "删除排班信息", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @DeleteMapping("/delete/{id}")
    public JsonResult<String> delete(@PathVariable("id") Integer id) {
        arrangeBiz.deleteArrange(id);
        return new JsonResult<String>(1, "删除成功");
    }
}
