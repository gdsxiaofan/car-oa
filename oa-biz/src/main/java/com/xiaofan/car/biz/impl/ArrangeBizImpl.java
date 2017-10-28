package com.xiaofan.car.biz.impl;

import com.xiaofan.car.biz.ArrangeBiz;
import com.xiaofan.car.dao.repository.ArrangeInfoMapper;
import com.xiaofan.car.persistence.model.ArrangeInfo;
import com.xiaofan.car.persistence.param.ArrangeParam;
import com.xiaofan.car.persistence.vo.ArrangeInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.transaction.Transaction;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/10/23
 * @since 1.0.0
 */
@Slf4j
@Service
public class ArrangeBizImpl implements ArrangeBiz {

    @Autowired
    private ArrangeInfoMapper arrangeInfoMapper;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Override
    public List<ArrangeInfoVo> selectArrangeList() {
        List<ArrangeInfo> arrangeInfos = arrangeInfoMapper.selectArrangeInfoList();
        if(CollectionUtils.isEmpty(arrangeInfos)){
            return null;
        }
        List<ArrangeInfoVo> arrangeInfoVos = new ArrayList<>();
        for(ArrangeInfo arrangeInfo:arrangeInfos){
            ArrangeInfoVo arrangeInfoVo = dozerBeanMapper.map(arrangeInfo,ArrangeInfoVo.class);
            arrangeInfoVos.add(arrangeInfoVo);
        }

        return arrangeInfoVos;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean saveArrange(ArrangeParam arrangeParam) {

        ArrangeInfo arrangeInfo = dozerBeanMapper.map(arrangeParam,ArrangeInfo.class);
        arrangeInfo.setCreateTime(Calendar.getInstance().getTime());
        Integer num = arrangeInfoMapper.insertSelective(arrangeInfo);
        return num==1?true:false;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean updateArrange(ArrangeParam arrangeParam) {
        ArrangeInfo arrangeInfo = dozerBeanMapper.map(arrangeParam,ArrangeInfo.class);
        if(arrangeParam.getIsEffective()==1){
            arrangeInfoMapper.updateForPause(arrangeParam.getId());//停用其他的开启的排班
        }

        Integer num = arrangeInfoMapper.updateByPrimaryKeySelective(arrangeInfo);
        return num==1?true:false;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean deleteArrange(Integer id) {
        Integer num = arrangeInfoMapper.deleteArrangeInfo(id);
        return num==1?true:false;
    }
}
