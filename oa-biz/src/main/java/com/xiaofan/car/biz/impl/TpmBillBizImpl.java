package com.xiaofan.car.biz.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaofan.car.biz.TpmBillBiz;
import com.xiaofan.car.dao.repository.TpmBillMapper;
import com.xiaofan.car.persistence.enumType.TmpTypeEnum;
import com.xiaofan.car.persistence.model.TpmBill;
import com.xiaofan.car.persistence.param.TpmBillParam;
import com.xiaofan.car.persistence.param.TpmBillQueryParam;
import com.xiaofan.car.persistence.vo.TpmBillVo;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
public class TpmBillBizImpl implements TpmBillBiz{

    @Autowired
    TpmBillMapper tpmBillMapper;

    /**
     * 获取当前工单列表
     * @param tpmBillQueryParam
     * @return
     */
    @Override
    public PageInfo<TpmBillVo> getTpmBillList(TpmBillQueryParam tpmBillQueryParam) {
        // 1.分页查询处理分页数据
        PageHelper.startPage(tpmBillQueryParam.getPageNum(),tpmBillQueryParam.getPageSize());

        // 2.调用查询接口
        List<TpmBillVo> tpmBillVoList = tpmBillMapper.getTpmBillVoList(tpmBillQueryParam.getTpmType(),tpmBillQueryParam.getTpmStatus(),tpmBillQueryParam.getTpmBillName());

        if(CollectionUtils.isEmpty(tpmBillVoList)){
            return null;
        }
        // 3.组装数据
       PageInfo<TpmBillVo> tpmBillVoPageInfo = new PageInfo<>(tpmBillVoList);
        return tpmBillVoPageInfo;
    }

    /**
     * 员工完成工单调用的接口
     * @param tpmBillParam
     * @return
     */
    @Override
    public Integer updateTpmBillForFinished(TpmBillParam tpmBillParam) {
        // 1.校验参数
        Assert.notNull(tpmBillParam.getId(),"当前工单id不能为空");
        // 2.根据不同操作更新工单对应的状态,巡检工单
        if(TmpTypeEnum.ROUTING_INSPECTION.getCode()==tpmBillParam.getTmpType()){
        }

        return null;
    }

    /**
     * 组长审核工单调用的接口
     * @param tpmBillParam
     * @return
     */
    @Override
    public Integer updateTpmBillForAudit(TpmBillParam tpmBillParam) {
        return null;
    }

    /**
     * 上传附件信息
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
        return null;
    }
}
