package com.example.graph.entity.result;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.graph.constvalue.Code;
import com.example.graph.constvalue.HintMessage;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@Slf4j
public class ResponseEntity {
    Object data;
    Integer code;
    String message;
    public ResponseEntity (Object data){
        success();
        setData(data);
    }
    public void success(){
        setMessage(HintMessage.SUCCESS);
        setCode(Code.SUCCESS);
    }
    public String toJSONString(){
       return JSON.toJSONString(this);
    }
}
