package com.xiaofan.car.biz;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.persistence.model.TpmBill;
import com.xiaofan.car.persistence.param.TpmBillParam;
import com.xiaofan.car.persistence.param.TpmBillQueryParam;
import com.xiaofan.car.persistence.vo.TpmBillVo;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 工单的相关biz层
 *
 * @author gongdaoshun
 * @date 2017/10/16
 * @since 1.0.0
 */
public interface TpmBillBiz {

    /**
     * 获取工单的列表信息
     * @param tpmBillQueryParam
     * @return
     */
    public PageInfo<TpmBillVo> getTpmBillList(TpmBillQueryParam tpmBillQueryParam);

    /**
     * 根据员工巡检、维修等更新对应的工单的信息
     * @param tpmBillParam
     * @return
     */
    public boolean updateTpmBillForFinished(TpmBillParam tpmBillParam);

    /**
     * 组长审核对应的工单等信息
     * @param tpmBillParam
     * @return
     */
//    public Integer updateTpmBillForAudit(TpmBillParam tpmBillParam);

    /**
     * 保存对应的附件信息
     * @param multiReq
     * @return
     */
    public String uploadAttachment(MultipartHttpServletRequest multiReq,Integer tpmDetailId,Integer tpmId);

    /**
     * 获取当前工单的详细信息
     * @param tpmBillId
     * @return
     */
    public TpmBillVo getTpmBillDetail(Integer tpmBillId);
}
