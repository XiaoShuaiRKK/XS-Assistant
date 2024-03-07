package com.xs.assistant.util.Impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JWTUtil {
    private final static String SECRET = "!XS@577#_)++151da2wd1d25x15j";
    public final static Integer TIME_OUT_DAY = 7;
    public final static Integer NEED_CREATE_DAY = 3;

    public final static class JWTKey{
        public final static String REDIS_KEY = "jwt:";
        public final static String ID_NUMBER_KEY = "IDNumber";
        public final static String NAME_KEY = "Name";
    }

    public static String getToken(Map<String,String> map){
        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,3);
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }

    public static String getIDNumber(String token){
        return verify(token).getClaims().get(JWTKey.ID_NUMBER_KEY).asString();
    }

    public static String getName(String token){
        return verify(token).getClaims().get(JWTKey.NAME_KEY).asString();
    }

    public static boolean becomeDue(String token){
        Date timeout = verify(token).getExpiresAt();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,TIME_OUT_DAY - NEED_CREATE_DAY);
        return timeout.before(calendar.getTime());
    }

    public static Map<String, Claim> getTokenInfo(String token){
        return verify(token).getClaims();
    }
}
