package com.xiaofan.car.dao.repository;

import com.xiaofan.car.persistence.model.TpmBill;
import com.xiaofan.car.persistence.param.TpmBillQueryParam;
import com.xiaofan.car.persistence.vo.TpmBillVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


/**
 * 
 * TpmBillMapper数据库操作接口类
 * 
 **/
@Repository
public interface TpmBillMapper{

    /**
     * 获取工单列表信息
     * @param tpmType
     * @param tpmStatus
     * @param tpmName
     * @return
     */
    public List<TpmBillVo> getTpmBillVoList(@Param("tpmType")Integer tpmType,
                                            @Param("tpmStatus")Integer tpmStatus,
                                            @Param("tpmName")String tpmName);

    /**
     *
     * @param tpmBill
     * @return
     */
    public boolean insertSelective(TpmBill tpmBill);

    public boolean updateByIdAndStatus(
            @Param("id")Integer id,
            @Param("toStatus")Integer toStatus,
            @Param("fromStatus")List<Integer> fromStatus
    );

    public TpmBillVo getTpmBillDetail(
            @Param("id") Integer id
    );
}