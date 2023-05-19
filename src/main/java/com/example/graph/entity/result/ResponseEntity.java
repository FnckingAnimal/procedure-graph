package com.example.graph.entity.result;

import lombok.Data;

@Data
public class ResponseEntity {
    String data;
    Integer code;
    String message;
}
