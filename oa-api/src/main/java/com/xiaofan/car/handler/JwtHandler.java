package com.xiaofan.car.handler;

import com.xiaofan.car.dao.repository.EmployeeMapper;
import com.xiaofan.car.persistence.model.Employee;
import com.xiaofan.car.util.Constant;
import com.xiaofan.car.util.json.JSONHelper;
import com.xiaofan.car.util.jwt.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:duhongda
 * @Description: jwt登录token认证
 * @Date:Create in 17:25 2017/8/25
 */
@Service
public class JwtHandler implements HandlerInterceptor {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String jwt = request.getHeader(Constant.AUTHORIZATION);
        if (StringUtils.isBlank(jwt)) {//判断jwt是否为空
            response.setStatus(401);
            return false;
        }
        Integer id = JwtUtil.parseJwt2Id(jwt);

        if (id == null) {//判断jwt是否为真或是否过期
            response.setStatus(401);
            return false;
        }
        //查询对应的用户信息
        Employee employee = employeeMapper.selectById(id);
        MDC.put("user", JSONHelper.obj2JSONString(employee));
        response.setHeader(Constant.AUTHORIZATION, JwtUtil.getJWTString(id));
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
