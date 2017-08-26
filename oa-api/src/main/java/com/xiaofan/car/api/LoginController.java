package com.xiaofan.car.api;

import com.github.pagehelper.PageHelper;
import com.xiaofan.car.dao.repository.LedgerInfoMapper;
import com.xiaofan.car.persistence.model.LedgerInfo;
import com.xiaofan.car.persistence.vo.JsonResult;
import com.xiaofan.car.util.Constant;
import com.xiaofan.car.util.jwt.JwtUtil;
import com.xiaofan.car.util.lang.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
    /**
     * 登录
     *
     * @return
     */
    @RequestMapping("")
    public JsonResult Login(HttpServletResponse response,
                            String username,
                            String password) {
        log.info(">>>>>>>{}.>>>>>>>>{}.>>>>>>{}", username, password, MD5Util.encode(password));
        String jwt = JwtUtil.getJWTString(1L);
        response.setHeader(Constant.AUTHORIZATION,jwt);
        return new JsonResult(1, "sucecss", jwt);
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

        return new JsonResult(1, "sucecss", JwtUtil.getJWTString(1L));
    }


}
