package com.xiaofan.car.biz;

import com.xiaofan.car.persistence.param.ArrangeParam;
import com.xiaofan.car.persistence.vo.ArrangeInfoVo;

import java.util.List;

/**
 * 排班业务代码
 *
 * @author gongdaoshun
 * @date 2017/10/23
 * @since 1.0.0
 */
public interface ArrangeBiz {

    /**
     * 查询所有的排班设置
     * @return
     */
    public List<ArrangeInfoVo> selectArrangeList();

    /**
     * 保存相关的排班设置
     * @param arrangeParam
     * @return
     */
    public boolean saveArrange(ArrangeParam arrangeParam);

    /**
     * 更新对应的排班设置
     * @param arrangeParam
     * @return
     */
    public boolean updateArrange(ArrangeParam arrangeParam);

    /**
     * 删除对应的排班设置
     * @param id
     * @return
     */
    public boolean deleteArrange(Integer id);
}
