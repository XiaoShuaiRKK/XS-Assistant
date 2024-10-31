package com.xs.assistant.image.controller;

import com.xs.DAO.ResponseResult;
import com.xs.assistant.image.config.MinioConfig;
import com.xs.assistant.image.util.MinioUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {
    private final MinioUtil minioUtil;
    private final MinioConfig prop;

    public FileController(MinioUtil minioUtil, MinioConfig prop) {
        this.minioUtil = minioUtil;
        this.prop = prop;
    }

    @PostMapping("/upload")
    public ResponseResult<String> uploadFile(@RequestPart("icon") MultipartFile file) {
        String fileName = minioUtil.upload(file).orElse(null);
        return ResponseResult.success(fileName);
    }

    @GetMapping("/preview")
    public ResponseResult<String> previewFile(@RequestParam("fileName") String fileName) {
        return ResponseResult.success(minioUtil.preview(fileName).orElse(null));
    }

    @GetMapping("/other/preview")
    public ResponseResult<String> previewOtherBucketFile(@RequestParam("bucket")String bucket, @RequestParam("fileName") String fileName) {
        return ResponseResult.success(minioUtil.previewFile(bucket,fileName).orElse(null));
    }
}
