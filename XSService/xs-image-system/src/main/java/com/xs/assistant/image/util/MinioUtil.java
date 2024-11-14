package com.xs.assistant.image.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xs.assistant.image.config.MinioConfig;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Component
@Slf4j
public class MinioUtil {
    private final MinioConfig prop;
    private final MinioClient minioClient;

    public MinioUtil(MinioConfig prop, MinioClient minioClient) {
        this.prop = prop;
        this.minioClient = minioClient;
    }

    /**
     * 查看存储bucket是否存在
     * @param bucketName
     * @return bool
     */
    public Boolean bucketExists(String bucketName) {
        boolean found;
        try {
            found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        }catch (Exception e) {
            log.error(e.getMessage());
            found = false;
        }
        return found;
    }

    /**
     * 创建存储bucket
     * @param bucketName
     * @return
     */
    public Boolean makeBucket(String bucketName) {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 删除bucket
     * @param bucketName
     * @return
     */
    public Boolean removeBucket(String bucketName) {
        try {
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
        }catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 获取全部bucket
     * @return
     */
    public List<Bucket> getAllBuckets() {
        List<Bucket> buckets;
        try {
            buckets = minioClient.listBuckets();
        } catch (Exception e){
            log.error(e.getMessage());
            buckets = Collections.emptyList();
        }
        return buckets;
    }

    /**
     * 文件上传
     * @param file
     * @return
     */
    public Optional<String> upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if(StringUtils.isBlank(originalFilename)) {
            throw new RuntimeException();
        }
        try {
            PutObjectArgs objectArgs = PutObjectArgs.builder().bucket(prop.getBucket()).object(originalFilename)
                    .stream(file.getInputStream(), file.getSize(), -1).contentType(file.getContentType()).build();
            minioClient.putObject(objectArgs);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
        return Optional.of(originalFilename);
    }

    /**
     * 预览图片
     * @param fileName
     * @return
     */
    public Optional<String> preview(String fileName){
        GetPresignedObjectUrlArgs build = GetPresignedObjectUrlArgs.builder()
                .bucket(prop.getBucket())
                .object(fileName)
                .method(Method.GET).build();
        String url;
        try {
            url = minioClient.getPresignedObjectUrl(build);
        }catch (Exception e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
        return Optional.of(url);
    }

    /**
     * 预览图片
     * @param fileName
     * @return
     */
    public Optional<String> previewFile(String bucket,String fileName){
        GetPresignedObjectUrlArgs build = GetPresignedObjectUrlArgs.builder()
                .bucket(bucket)
                .object(fileName)
                .method(Method.GET).build();
        String url;
        try {
            url = minioClient.getPresignedObjectUrl(build);
        }catch (Exception e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
        return Optional.of(url);
    }

    /**
     * 文件下载
     * @param fileName
     * @param res
     */
    public void download(String fileName, HttpServletResponse res) {
        GetObjectArgs objectArgs = GetObjectArgs.builder()
                .bucket(prop.getBucket())
                .object(fileName).build();
        try(GetObjectResponse response = minioClient.getObject(objectArgs);
            FastByteArrayOutputStream os = new FastByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = response.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            os.flush();
            byte[] bytes = os.toByteArray();
            res.setCharacterEncoding("UTF-8");
            res.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            try(ServletOutputStream stream = res.getOutputStream()) {
                stream.write(bytes);
                stream.flush();
            }
        }catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public List<String> listObjectsName(){
        return listObjectsName(prop.getBucket());
    }

    /**
     * 查看文件对象
     * @return 存储bucket内文件对象信息
     */
    public List<String> listObjectsName(String bucket){
        return listObjects(ListObjectsArgs.builder().bucket(bucket).build());
    }

    public List<String> listObjectsFolderName(String bucket,String folder){
        return listObjects(ListObjectsArgs.builder().bucket(bucket).prefix(folder).build());
    }

    private List<String> listObjects(ListObjectsArgs args) {
        Iterable<Result<Item>> results = minioClient.listObjects(args);
        List<String> items = new ArrayList<>();
        try {
            for(Result<Item> result : results) {
                items.add(result.get().objectName());
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return Collections.emptyList();
        }
        return items;
    }


    /**
     * 删除
     * @param fileName
     * @return
     */
    public boolean remove(String fileName){
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(prop.getBucket()).object(fileName).build());
        }catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }
}
