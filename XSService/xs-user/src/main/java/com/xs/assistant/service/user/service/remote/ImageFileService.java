package com.xs.assistant.service.user.service.remote;

import com.xs.DAO.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Service
@FeignClient(value = "XS-IMAGE-SYSTEM",path = "/xs_assistant/image/file")
public interface ImageFileService {
    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseResult<String> uploadFile(@RequestPart("icon") MultipartFile file);

    @GetMapping("/preview")
    ResponseResult<String> previewFile(@RequestParam("fileName") String fileName);
}
