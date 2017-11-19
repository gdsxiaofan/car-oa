package com.xiaofan.car.dao.repository;

import com.xiaofan.car.persistence.model.TpmBill;
import com.xiaofan.car.persistence.param.TpmBillQueryParam;
import com.xiaofan.car.persistence.vo.TpmBillVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
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
                                            @Param("tpmName")String tpmName,
                                            @Param("startTime")Date startTime,
                                            @Param("endTime")Date endTime);

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

    /**
     * 巡检处理
     * @param id
     * @param toStatus
     * @param fromStatus
     * @param userId
     * @param userName
     * @param nowDate
     * @param comment
     * @return
     */
    public boolean updateForXJ(@Param("id")Integer id,
                               @Param("toStatus")Integer toStatus,
                               @Param("fromStatus")List<Integer> fromStatus,
                               @Param("userId")Integer userId,
                               @Param("userName")String userName,
                               @Param("nowDate")Date nowDate,
                               @Param("comment")String comment);

    /**
     * 维修处理
     * @param id
     * @param toStatus
     * @param fromStatus
     * @param userId
     * @param userName
     * @param nowDate
     * @param comment
     * @return
     */
    public boolean updateForRepaired(@Param("id")Integer id,
                               @Param("toStatus")Integer toStatus,
                               @Param("fromStatus")List<Integer> fromStatus,
                               @Param("userId")Integer userId,
                               @Param("userName")String userName,
                               @Param("nowDate")Date nowDate,
                               @Param("comment")String comment);

    /**
     * 深痕完成
     * @param id
     * @param toStatus
     * @param fromStatus
     * @param userId
     * @param userName
     * @param nowDate
     * @param comment
     * @return
     */
    public boolean updateForAudit(@Param("id")Integer id,
                               @Param("toStatus")Integer toStatus,
                               @Param("fromStatus")List<Integer> fromStatus,
                               @Param("userId")Integer userId,
                               @Param("userName")String userName,
                               @Param("nowDate")Date nowDate,
                               @Param("comment")String comment);

    public TpmBillVo getTpmBillDetail(
            @Param("id") Integer id
    );

    public void insertTpmBillForList(
            @Param("tpmBillList")List<TpmBill> tpmBillList
    );

    /**
     *
     * @param tpmStatusList
     * @param tpmName
     * @return
     */
    public List<TpmBillVo> getTpmBillVoListByStatus(
                                            @Param("tpmStatusList")List<Integer> tpmStatusList,
                                            @Param("tpmName")String tpmName);

    public List<TpmBill> getTpmBillForOverdue(
            @Param("tpmStatusList")List<Integer> tpmStatusList,
            @Param("startTime")Date startTime,
            @Param("endTime")Date endTime
    );

    public void updateTpmBillList(
            @Param("tpmbills") List<TpmBill> tpmBills
    );
}