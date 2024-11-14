package com.xs.assistant.image.controller;

import com.xs.DAO.ResponseResult;
import com.xs.assistant.image.config.MinioConfig;
import com.xs.assistant.image.util.MinioUtil;
import com.xs.assistant.redis.util.RedisUtil;
import io.minio.Result;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {
    private final MinioUtil minioUtil;
    private final MinioConfig prop;
    final RedisUtil redisUtil;

    public FileController(MinioUtil minioUtil, MinioConfig prop, RedisUtil redisUtil) {
        this.minioUtil = minioUtil;
        this.prop = prop;
        this.redisUtil = redisUtil;
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

    @GetMapping("/objects")
    public ResponseResult<List<String>> listObjects(@RequestParam("bucket")String bucket) {
        return ResponseResult.success(minioUtil.listObjectsName(bucket));
    }

    @GetMapping("/bucket/objects")
    public ResponseResult<List<Map.Entry<String,String>>> bucketAllObjects(@RequestParam("bucket")String bucket) {
        List<String> itemName = minioUtil.listObjectsName(bucket);
        List<Map.Entry<String,String>> entries = getEntryPreview(bucket,itemName);
        return ResponseResult.success(entries);
    }

    @GetMapping("/bucket/folder/objects")
    public ResponseResult<List<Map.Entry<String,String>>> bucketAllFolderObjects(@RequestParam("bucket")String bucket,
                                                                                 @RequestParam("folder")String folder) {
        List<String> itemName = minioUtil.listObjectsFolderName(bucket,folder);
        List<Map.Entry<String,String>> entries = getEntryPreview(bucket,itemName);
        return ResponseResult.success(entries);
    }

    @GetMapping("/redis")
    public void redisDataTest(){
        log.info("===========Redis Test================");
        Map<String,Object> map = new HashMap<>();
        for(int i=1;i<=100*10000;i++){
            map.put("key"+i,"value"+i);
        }
        redisUtil.pipeline(RedisUtil.SessionCallbackFactory.batchOperationsNoReturn(map,0,null,
                RedisUtil.SessionCallbackFactory.OperationsEnum.SET_FOREVER));
        log.info("===========Redis Test Over================");
    }

    private List<Map.Entry<String,String>> getEntryPreview(String bucket,List<String> itemNames) {
        List<Map.Entry<String,String>> entries = new ArrayList<>();
        itemNames.forEach(s-> {
            String url = minioUtil.previewFile(bucket,s).orElse("");
            entries.add(Map.entry(s,url));
        });
        return entries;
    }
}
