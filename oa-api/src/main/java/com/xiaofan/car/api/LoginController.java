package com.xiaofan.car.api;

import com.xiaofan.car.biz.LoginBiz;
import com.xiaofan.car.dao.repository.LedgerInfoMapper;
import com.xiaofan.car.persistence.model.Employee;
import com.xiaofan.car.persistence.vo.JsonResult;
import com.xiaofan.car.util.Constant;
import com.xiaofan.car.util.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author:duhongda
 * @Description:
 * @Date:Create in 10:33 2017/8/25
 */
@RestController
@RequestMapping("login")
@Slf4j
public class LoginController {
    @Autowired
    private LedgerInfoMapper ledgerInfoMapper;
    @Autowired
    private LoginBiz loginBiz;
    /**
     * 登录
     *
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.POST)
    public JsonResult login(HttpServletResponse response,
                            String username,
                            String password) {
        Employee employee=loginBiz.verificationForLogin(username,password);
        if(employee!=null){
            String jwt = JwtUtil.getJWTString(employee.getId());
            response.setHeader(Constant.AUTHORIZATION,jwt);
            return new JsonResult(1, "登陆成功");
        }
        return new JsonResult(0,"用户名或密码不匹配");
    }

}
