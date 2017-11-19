package com.xiaofan.car.api;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.biz.LoginBiz;
import com.xiaofan.car.biz.TpmBillBiz;
import com.xiaofan.car.persistence.model.Employee;
import com.xiaofan.car.persistence.param.TpmBillParam;
import com.xiaofan.car.persistence.param.TpmBillQueryParam;
import com.xiaofan.car.persistence.vo.JsonResult;
import com.xiaofan.car.persistence.vo.TpmBillVo;
import com.xiaofan.car.util.Constant;
import com.xiaofan.car.util.jwt.JwtUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

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
    @Autowired
    private LoginBiz loginBiz;
    @Autowired
    private TpmBillBiz tpmBillBiz;

    @ApiOperation(value = "获取工单信息列表", notes = "获取工单信息列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/getlist")
    public JsonResult<PageInfo<TpmBillVo>> getlist(TpmBillQueryParam param) {
        try{
            PageInfo<TpmBillVo> pageInfo = tpmBillBiz.getTpmBillList(param);
            return new JsonResult<PageInfo<TpmBillVo>>(1, "获取成功",pageInfo);
        }
        catch(Exception e){
            log.error("查询工单列表出错：",e);
            return new JsonResult<PageInfo<TpmBillVo>>(0, "系统异常",null);
        }


    }

    @ApiOperation(value = "获取待审核工单信息列表", notes = "获取待审核工单信息列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/getAuditlist")
    public JsonResult<PageInfo<TpmBillVo>> getAuditList(TpmBillQueryParam param){
        PageInfo<TpmBillVo> pageInfo = tpmBillBiz.getAuditTpmBillList(param);
        return new JsonResult<PageInfo<TpmBillVo>>(1, "获取成功",pageInfo);
    }

    @ApiOperation(value = "员工完成工单接口", notes = "员工完成工单接口", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/update")
    public JsonResult updateTpmBill(HttpServletRequest request,TpmBillParam tpmBillParam){
        String jwt = request.getHeader(Constant.AUTHORIZATION);
        tpmBillParam.setJwt(jwt);
        tpmBillBiz.updateTpmBillForFinished(tpmBillParam);
        return new JsonResult(1, "操作成功");
    }

    @ApiOperation(value = "组长审核工单接口", notes = "组长审核工单接口", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/audit")
    public JsonResult auditTpmBill(TpmBillParam tpmBillParam
    , HttpServletRequest request){
        String jwt = request.getHeader(Constant.AUTHORIZATION);
        //放入审核人员id
        tpmBillParam.setUserId(JwtUtil.parseJwt2Id(jwt));
        tpmBillBiz.updateTpmBillForFinished(tpmBillParam);

        return new JsonResult(1, "操作成功");
    }

    @ApiOperation(value = "获取工单详细信息", notes = "获取工单详细信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/detail")
    public JsonResult<TpmBillVo> getDetail(
            @RequestParam("tpmId")Integer tpmId
    ) {

        return new JsonResult<TpmBillVo>(1, "获取成功",tpmBillBiz.getTpmBillDetail(tpmId));
    }

    /**
     * 上传附件接口，后台会保存到服务器，并且落地到数据库中，返回当前保存附件的id
     * @return
     */
    @Deprecated
    @ApiOperation(value = "上传工单附件信息", notes = "上传工单附件信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/uploadAttachment")
    public JsonResult uploadAttachment(@RequestParam("tpmDetailId")Integer tpmDetailId, MultipartHttpServletRequest multiReq){
        return new JsonResult(1, "附件上传成功",null);
    }

    /**
     * 删除对应的附件信息
     * @param attachmentId
     * @return
     */
    @Deprecated
    @ApiOperation(value = "删除附件信息", notes = "删除附件信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/deleteAttachment")
    public JsonResult deleteAttachmentt(@RequestParam("attachmentId")Integer attachmentId){
        return new JsonResult(1, "附件上传成功","删除附件成功");
    }

    @ApiOperation(value = "根据工号密码修改工单状态", notes = "根据工号密码修改工单状态", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/checkUser")
    public JsonResult<Integer> updateOrderStatus(Employee employee) {
        Employee user = loginBiz.verificationForLogin(employee.getEmployeeNo(), employee.getEmployeePassword());
        if (user == null) {
            return new JsonResult<>(0, "工号或密码不正确");
        }
        return new JsonResult<>(1, "", user.getId());
    }
}
