package com.xiaofan.car.handler;

import com.xiaofan.car.util.Constant;
import com.xiaofan.car.util.jwt.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @Author:duhongda
 * @Description: jwt登录token认证
 * @Date:Create in 17:25 2017/8/25
 */
public class JwtHandler implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String jwt = request.getHeader(Constant.AUTHORIZATION);
        Integer id = JwtUtil.parseJwt2Id(jwt);
        if (id == null) {
            response.setStatus(401);
            return false;
        }
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
