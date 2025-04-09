package com.bwtfs.server.controller;

import com.bwtfs.pojo.entity.Result;
import com.bwtfs.server.annotation.LockMapping;
import com.bwtfs.server.annotation.WebDev;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class FileController {

    @PostMapping("/upload")
    public Result upload(String fileName, MultipartFile file) throws IOException {
        log.info("文件上传开始：{}", fileName);
        String originalFilename = file.getOriginalFilename();
        String extention = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + extention;
        file.transferTo(new File("O:/" + newFileName));
        return Result.success();
    }

    @WebDev
    @LockMapping(value = "/download")
    public Result download(HttpServletRequest request) throws IOException {
        String method = request.getMethod();
        System.out.println("------------------------->HTTP方法：" + method);
        return Result.success();
    }
}
