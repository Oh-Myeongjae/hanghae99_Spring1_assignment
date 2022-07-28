package com.sparta.myblog.contorller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@NoArgsConstructor
@Getter
public class returnInfo {
    private boolean success;

    private Object data;

    private String msg;

    public returnInfo(boolean success, Object data, String msg) {
        this.success = success;
        this.data = data;
        this.msg = msg;
    }

    public Map<String,Object> Info(boolean check, Object data, String msg){
        Map<String,Object> objectMap = new LinkedHashMap<>();
        objectMap.put("success",check);
        objectMap.put("data",data);
        objectMap.put("error",msg);
        return  objectMap;
    }
}
