package com.example.graph.utils;


import com.alibaba.fastjson2.JSONObject;
import com.example.graph.entity.MinioProp;
import io.minio.MinioClient;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MinioUtils {

    private static final Logger logger = LoggerFactory.getLogger(MinioUtils.class);
    @Autowired
    private MinioClient client;
    @Autowired
    private MinioProp minioProp;

    /**
     * 创建bucket
     *
     * @param bucketName bucket名称
     */
    @SneakyThrows
    public void createBucket(String bucketName) {
        if (!client.bucketExists(bucketName)) {
            client.makeBucket(bucketName);
        }
    }

    /**
     * 上传文件
     *
     * @param file       文件
     * @param bucketName 存储桶
     * @return
     */
    public JSONObject uploadFile(MultipartFile file, String bucketName) {
        JSONObject res = new JSONObject();
        res.put("code", 0);
        // 判断上传文件是否为空
        if (null == file || 0 == file.getSize()) {
            res.put("msg", "上传文件不能为空");
            res.put("code", 0);
            return res;
        }
        try {
            // 判断存储桶是否存在
            createBucket(bucketName);
            // 文件名
            String originalFilename = file.getOriginalFilename();
            // 新的文件名 = 存储桶名称_时间戳.后缀名
            String fileName = bucketName + "_" + System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
            // 开始上传
            client.putObject(bucketName, fileName, file.getInputStream(), file.getContentType());
            res.put("code", 1);
            res.put("msg", minioProp.getEndpoint() + "/" + bucketName + "/" + fileName);
            return res;
        }  catch (Exception e) {
            logger.error("上传文件失败：{}", e.getMessage());
            logger.error(e.getMessage(),e);
        }
        res.put("msg", "上传失败");
        res.put("code", 0);
        return res;
    }
}

