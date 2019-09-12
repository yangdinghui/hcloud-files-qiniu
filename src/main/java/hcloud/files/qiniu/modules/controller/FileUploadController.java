package hcloud.files.qiniu.modules.controller;

import hcloud.files.qiniu.base.view.ApiResponse;
import hcloud.files.qiniu.modules.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * description 描述这个类的主要功能、用途
 * 创建时间 2019/9/2
 *
 * @author 杨丁辉
 */
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping(value = "/up")
    public String upload() {
//        fileUploadService.upload();
        return "files";
    }

    @PostMapping("/upload")
    public ApiResponse<String> upload(@RequestParam  MultipartFile file, HttpServletRequest request) throws IOException {
        if(file.isEmpty()){
            return ApiResponse.fail(201,"文件为空");
        }
        String upload = fileUploadService.upload(file);
        System.out.println(file);
        return ApiResponse.success(upload);
    }
}
