package com.xiaofan.car.biz.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaofan.car.biz.TpmBillBiz;
import com.xiaofan.car.dao.repository.EmployeeMapper;
import com.xiaofan.car.dao.repository.TpmBillMapper;
import com.xiaofan.car.persistence.enumType.AttachmentBizTypeEnum;
import com.xiaofan.car.persistence.enumType.TmpStatusEnum;
import com.xiaofan.car.persistence.model.Employee;
import com.xiaofan.car.persistence.param.TpmBillParam;
import com.xiaofan.car.persistence.param.TpmBillQueryParam;
import com.xiaofan.car.persistence.vo.TpmBillVo;
import com.xiaofan.car.service.AttachmentService;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 工单相关的业务处理成
 *
 * @author gongdaoshun
 * @date 2017/10/16
 * @since 1.0.0
 */
@Service
@Slf4j
public class TpmBillBizImpl implements TpmBillBiz {

    @Autowired
    TpmBillMapper tpmBillMapper;

    @Autowired
    AttachmentService attachmentService;

    @Autowired
    EmployeeMapper employeeMapper;


    /**
     * 获取当前工单列表
     *
     * @param tpmBillQueryParam
     * @return
     */
    @Override
    public PageInfo<TpmBillVo> getTpmBillList(TpmBillQueryParam tpmBillQueryParam) throws ParseException {
        // 1.分页查询处理分页数据
        PageHelper.startPage(tpmBillQueryParam.getPageNum(), tpmBillQueryParam.getPageSize());

        // 2.调用查询接口
        Date startTime = null;
        Date endTime = null;
        if(StringUtils.isNotBlank(tpmBillQueryParam.getBegin())&&StringUtils.isNotBlank(tpmBillQueryParam.getEnd())){
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
            startTime = sdf.parse(tpmBillQueryParam.getBegin());
            endTime = sdf.parse(tpmBillQueryParam.getEnd());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endTime);
            calendar.add(Calendar.DATE,1);
            endTime = calendar.getTime();
        }

        List<TpmBillVo> tpmBillVoList = tpmBillMapper.getTpmBillVoList(tpmBillQueryParam.getTpmType(), tpmBillQueryParam.getTpmStatus(), tpmBillQueryParam.getTpmBillName(),startTime,endTime);
        tpmBillVoList.forEach(e->{
            e.setPendAttachements(attachmentService.getAttachmentVoList(e.getId(),AttachmentBizTypeEnum.PEND_TYPE));
            e.setRepairAttachements(attachmentService.getAttachmentVoList(e.getId(),AttachmentBizTypeEnum.REPAIR_TYPE));
            e.setDemoAttachements(attachmentService.getAttachmentVoList(e.getCheckId(),AttachmentBizTypeEnum.CHECK_TYPE));
        });
        // 3.组装数据
        PageInfo<TpmBillVo> tpmBillVoPageInfo = new PageInfo<>(tpmBillVoList);
        return tpmBillVoPageInfo;
    }

    @Override
    public PageInfo<TpmBillVo> getAuditTpmBillList(TpmBillQueryParam tpmBillQueryParam) {
        // 1.分页查询处理分页数据
        PageHelper.startPage(tpmBillQueryParam.getPageNum(), tpmBillQueryParam.getPageSize());

        // 2.调用查询接口
        List<Integer> tpmStatusList = new ArrayList<>();
        tpmStatusList.add(TmpStatusEnum.PENDED.getCode());
        tpmStatusList.add(TmpStatusEnum.REPAIRED.getCode());
        List<TpmBillVo> tpmBillVoList = tpmBillMapper.getTpmBillVoListByStatus(tpmStatusList,tpmBillQueryParam.getTpmBillName());

        tpmBillVoList.forEach(e->{
            e.setPendAttachements(attachmentService.getAttachmentVoList(e.getId(),AttachmentBizTypeEnum.PEND_TYPE));
            e.setRepairAttachements(attachmentService.getAttachmentVoList(e.getId(),AttachmentBizTypeEnum.REPAIR_TYPE));
            e.setDemoAttachements(attachmentService.getAttachmentVoList(e.getCheckId(),AttachmentBizTypeEnum.CHECK_TYPE));
        });
        // 3.组装数据
        PageInfo<TpmBillVo> tpmBillVoPageInfo = new PageInfo<>(tpmBillVoList);
        return tpmBillVoPageInfo;
    }

    /**
     * 员工完成工单调用的接口
     *
     * @param tpmBillParam
     * @return
     */
    @Override
    @Transactional
    public boolean updateTpmBillForFinished(TpmBillParam tpmBillParam) {
        // 1.校验参数
        Assert.notNull(tpmBillParam.getId(), "当前工单id不能为空");
        // 2.根据不同操作更新工单对应的状态,巡检工单
        boolean flag = false;
        Integer toStatus = 0;
        List<Integer> fromStatus = new ArrayList<>();
        toStatus = transformStatus(toStatus, fromStatus, tpmBillParam);
        // 查询当前处理的用户人员信息
        Integer userId = null;
        String userName = null;
        if(tpmBillParam.getUserId()!=null){
            Employee employee = employeeMapper.selectById(tpmBillParam.getUserId());
            userId = employee.getId();
            userName = employee.getEmployeeName();
        }
        Date dateNow = Calendar.getInstance().getTime();
        if(toStatus==TmpStatusEnum.PENDED.getCode()||toStatus==TmpStatusEnum.REPAIRING.getCode()){
            flag = tpmBillMapper.updateForXJ(tpmBillParam.getId(), toStatus, fromStatus
            ,userId,userName,dateNow,tpmBillParam.getDesc());
        }
        if(toStatus==TmpStatusEnum.REPAIRED.getCode()){
            flag = tpmBillMapper.updateForRepaired(tpmBillParam.getId(), toStatus, fromStatus
                    ,userId,userName,dateNow,tpmBillParam.getDesc());
        }
        if(toStatus==TmpStatusEnum.FINISHED.getCode()){
            flag = tpmBillMapper.updateForAudit(tpmBillParam.getId(), toStatus, fromStatus
                    ,userId,userName,dateNow,tpmBillParam.getDesc());
        }



//        flag = tpmBillMapper.updateByIdAndStatus(tpmBillParam.getId(), toStatus, fromStatus);

        // `````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````处理对应附件信息
        if (StringUtils.isNotBlank(tpmBillParam.getAttachmentIds())) {
            AttachmentBizTypeEnum attachmentBizTypeEnum = AttachmentBizTypeEnum.PEND_TYPE;
            String[] attachmentIdArray = tpmBillParam.getAttachmentIds().split(",");
            if (tpmBillParam.getDealType() == 4) {
                attachmentBizTypeEnum = AttachmentBizTypeEnum.REPAIR_TYPE;
            }
            List<Integer> attachmentIdList = new ArrayList<>();
            for (String aId : attachmentIdArray) {
                Integer id = Integer.valueOf(aId);
                attachmentIdList.add(id);
            }

            attachmentService.updateAttachment(attachmentIdList, attachmentBizTypeEnum, tpmBillParam.getId());

        }
        return flag;
    }

//    /**
//     * 组长审核工单调用的接口
//     * @param tpmBillParam
//     * @return
//     */
//    @Override
//    public Integer updateTpmBillForAudit(TpmBillParam tpmBillParam) {
//        // 1.校验参数
//        Assert.notNull(tpmBillParam.getId(),"当前工单id不能为空");
//        // 2.根据不同操作更新工单对应的状态,巡检工单
//        boolean flag = false;
//        Integer toStatus =0;
//        List<Integer> fromStatus =new ArrayList<>();
//        toStatus=transformStatus(toStatus,fromStatus,tpmBillParam);
//
//        flag = tpmBillMapper.updateByIdAndStatus(tpmBillParam.getId(),toStatus,fromStatus);
//        return flag;
//    }

    /**
     * 上传附件信息
     *
     * @param multiReq
     * @param tpmDetailId
     * @param tpmId
     * @return
     */
    @Override
    public String uploadAttachment(MultipartHttpServletRequest multiReq, Integer tpmDetailId, Integer tpmId) {
        return null;
    }

    /**
     * 获取当前工单的详情
     *
     * @param tpmBillId
     * @return
     */
    @Override
    public TpmBillVo getTpmBillDetail(Integer tpmBillId) {
        return tpmBillMapper.getTpmBillDetail(tpmBillId);
    }


    private Integer transformStatus(Integer toStatus, List<Integer> fromStatus, TpmBillParam tpmBillParam) {
        switch (tpmBillParam.getDealType()) {
            case 2://巡检完成
                toStatus = TmpStatusEnum.PENDED.getCode();
                fromStatus.add(TmpStatusEnum.PENDING.getCode());
                break;
            case 3://待维修
                toStatus = TmpStatusEnum.REPAIRING.getCode();
                fromStatus.add(TmpStatusEnum.PENDING.getCode());
                break;
            case 4://维修完成
                toStatus = TmpStatusEnum.REPAIRED.getCode();
                fromStatus.add(TmpStatusEnum.REPAIRING.getCode());
                break;
            case 5://待审核
                toStatus = TmpStatusEnum.FINISHED.getCode();
                fromStatus.add(TmpStatusEnum.PENDED.getCode());
                fromStatus.add(TmpStatusEnum.REPAIRED.getCode());
                break;
        }
        return toStatus;
    }
}
