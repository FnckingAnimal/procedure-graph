package com.example.graph.entity.result;

import com.alibaba.fastjson2.JSON;
import com.example.graph.constvalue.Code;
import com.example.graph.constvalue.HintMessage;
import com.example.graph.utils.Utils;
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

    public ResponseEntity(Object data) {
        success();
        setData(data);
    }
    public ResponseEntity success(Object o){
        setData(o);
        success();
        return this;
    }

    public ResponseEntity success() {
        setMessage(HintMessage.SUCCESS);
        setCode(Code.SUCCESS);
        return this;
    }

    public ResponseEntity fail(String message, Object data) {
        if (Utils.isNotNull(data)) {
            setData(data);
        }
        fail(message);
        return this;
    }

    public ResponseEntity fail(String message) {
        setMessage(message);
        setCode(Code.FAILURE);
        return this;
    }

    public String toJSONString() {
        return JSON.toJSONString(this);
    }
}
