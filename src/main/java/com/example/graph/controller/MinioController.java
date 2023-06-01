package com.example.graph.controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.graph.utils.MinioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
public class MinioController {

    @Autowired
    private MinioUtils minioUtils;
    /**
     * 上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public String upload(@RequestParam(name = "file", required = false) MultipartFile file) {
        JSONObject res = null;
        try {
            res = minioUtils.uploadFile(file, "image");
        } catch (Exception e) {
            e.printStackTrace();

            res.put("code", 0);
            res.put("msg", "上传失败");
        }
        return res.toJSONString();
    }
}
