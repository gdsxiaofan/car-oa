package com.xiaofan.car.util.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @Author:duhongda
 * @Description:
 * @Date:Create in 15:31 2017/8/25
 */
@Slf4j
public class JwtUtil {

    private static String SECRET = "MY SECRET";

    private static final Long OUTTIME = 60 * 60 * 1000L;
//    private static final Long OUTTIME = 1000L;

    /**
     * @param id  用户主键id
     * @return
     */
    public static String getJWTString(Long id) {
        if (id == null) {
            throw new NullPointerException("null id is illegal");
        }
        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setHeaderParam("alg", "HS256")
                .setIssuer("Jersey-Security-Basic")
                .setAudience("user")
                .setExpiration(new Date(System.currentTimeMillis() + OUTTIME))
                .claim("role", "du")
                .setIssuedAt(new Date())
                .setId(String.valueOf(id))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    private JwtUtil() {
    }

    /**
     *
     * @param Jwt
     * @return  id：用户主键 如果为null则token失效  跳转登录页面
     */
    public static Long parseJwt2Id(String Jwt) {
        try {
            Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(Jwt);//compactJws为jwt字符串
            return Long.parseLong(parseClaimsJws.getBody().getId());//得到body后我们可以从body中获取我们需要的信息
        } catch (SignatureException | MalformedJwtException e) {
            // jwt 解析错误
            log.warn("Jwt:{} 解析错误");
            return null;
        } catch (ExpiredJwtException e) {
            // jwt 已经过期，在设置jwt的时候如果设置了过期时间，这里会自动判断jwt是否已经过期，如果过期则会抛出这个异常，我们可以抓住这个异常并作相关处理。
            log.warn("Jwt:{} 已过期");
            return null;
        }

        //比如 获取主题,当然，这是我们在生成jwt字符串的时候就已经存进来的
//        String subject = body.getSubject();

    }

    public static void main(String[] args) {
        Long start=System.currentTimeMillis();
       String a= getJWTString(2L);
       Long statr2=System.currentTimeMillis();
        System.out.println(statr2-start);
       Long id= parseJwt2Id(a);
        System.out.println(System.currentTimeMillis()-statr2);
    }
}
