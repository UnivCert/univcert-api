package com.univcert.backend;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PropertyUtil {
    public static final String SUCCESS_WORD ="success";

    public static JSONObject response(boolean bl){
        JSONObject obj = new JSONObject();
        obj.put(SUCCESS_WORD, bl);
        return obj;
    }

    public static JSONObject response(){
        JSONObject obj = new JSONObject();
        obj.put(SUCCESS_WORD, true);
        obj.put("status",200);
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
        obj.put("code",400);
        obj.put(SUCCESS_WORD, false);
        obj.put("message", message);
        return obj;
    }

}


