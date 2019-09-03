package hcloud.files.qiniu.modules.service.impl;

import com.qiniu.util.Auth;
import hcloud.files.qiniu.modules.service.FileUploadService;
import org.springframework.stereotype.Service;

/**
 * description 描述这个类的主要功能、用途
 * 创建时间 2019/9/3
 *
 * @author 杨丁辉
 */
@Service
public class FileUploadServiceImpl implements FileUploadService{

    @Override
    public void upload() {
        String accessKey = "access key";
        String secretKey = "secret key";
        String bucket = "bucket name";
        String key = "file key";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket, key);
        System.out.println(upToken);
    }

}
