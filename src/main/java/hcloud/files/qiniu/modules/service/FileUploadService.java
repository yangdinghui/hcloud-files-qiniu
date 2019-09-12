package hcloud.files.qiniu.modules.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * description 描述这个类的主要功能、用途
 * 创建时间 2019/9/3
 *
 * @author 杨丁辉
 */
public interface FileUploadService {

    String upload(MultipartFile file) throws IOException;

}
