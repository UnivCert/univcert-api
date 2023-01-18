package com.univcert.backend;

package net.sinzak.server.common;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
public class PropertyUtil {
    public static final String SUCCESS_WORD ="success";

    public static JSONObject response(boolean bl){
        JSONObject obj = new JSONObject();
        obj.put(SUCCESS_WORD, bl);
        return obj;
    }

    public static JSONObject response(Object data){
        JSONObject obj = new JSONObject();
        obj.put(SUCCESS_WORD,true);
        obj.put("data",data);
        return obj;
    }

    public static JSONObject response(Long id){
        JSONObject obj = new JSONObject();
        obj.put("id",id);
        obj.put(SUCCESS_WORD,true);
        return obj;
    }

    public static JSONObject responseMessage(String message){ //그냥 json 리턴해줄때 씀
        JSONObject obj = new JSONObject();
        obj.put(SUCCESS_WORD, false);
        obj.put("message", message);
        return obj;
    }

}


