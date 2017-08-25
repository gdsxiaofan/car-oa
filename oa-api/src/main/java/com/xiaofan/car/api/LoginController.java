package com.xiaofan.car.api;

import com.xiaofan.car.persistence.vo.JsonResult;
import com.xiaofan.car.util.jwt.JwtUtil;
import com.xiaofan.car.util.md5.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:duhongda
 * @Description:
 * @Date:Create in 10:33 2017/8/25
 */
@RestController
@RequestMapping("login")
@Slf4j
public class LoginController {
    /**
     *  登录
     * @return
     */
    @RequestMapping("")
    public JsonResult Login(String username,String password){
        log.info(">>>>>>>{}.>>>>>>>>{}.>>>>>>{}",username,password, MD5Util.encode(password));


        return new JsonResult(1,"sucecss", JwtUtil.getJWTString(1L));
    }
    /**
     *  登录
     * @return
     */
    @RequestMapping("/aaa")
    public JsonResult aaa(String username,String password){
        log.info(">>>>>>>{}.>>>>>>>>{}.>>>>>>{}",username,password, MD5Util.encode(password));


        return new JsonResult(1,"sucecss", JwtUtil.getJWTString(1L));
    }


}
