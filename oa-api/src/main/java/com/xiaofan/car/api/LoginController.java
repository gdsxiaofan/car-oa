package com.xiaofan.car.api;

import com.github.pagehelper.PageHelper;
import com.xiaofan.car.biz.LoginBiz;
import com.xiaofan.car.dao.repository.LedgerInfoMapper;
import com.xiaofan.car.persistence.model.Employee;
import com.xiaofan.car.persistence.model.LedgerInfo;
import com.xiaofan.car.persistence.vo.JsonResult;
import com.xiaofan.car.util.Constant;
import com.xiaofan.car.util.jwt.JwtUtil;
import com.xiaofan.car.util.lang.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    public JsonResult Login(HttpServletResponse response,
                            String username,
                            String password) {
        log.info("用户名：{}；密码：{}", username, password);
        Employee employee=loginBiz.verificationForLogin(username,password);
        if(employee!=null){
            String jwt = JwtUtil.getJWTString(employee.getId());
            response.setHeader(Constant.AUTHORIZATION,jwt);
            return new JsonResult(1, "登陆成功");
        }
        return new JsonResult(0,"用户名或密码不匹配");
    }

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping("/aaa")
    public JsonResult aaa(String username, String password) {
        log.info(">>>>>>>{}.>>>>>>>>{}.>>>>>>{}", username, password, MD5Util.encode(password));
        PageHelper.startPage(1, 10);
       List<LedgerInfo> list= ledgerInfoMapper.selectLedgerInfosByLedgerInfo(new LedgerInfo());

        return new JsonResult(1, "sucecss", JwtUtil.getJWTString(1));
    }


}
