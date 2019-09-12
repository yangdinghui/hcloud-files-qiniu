package hcloud.files.qiniu.modules.service.impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import hcloud.files.qiniu.base.excetion.BusinessException;
import hcloud.files.qiniu.base.utils.DateUtil;
import hcloud.files.qiniu.modules.dao.FilesQiniuAccountDao;
import hcloud.files.qiniu.modules.model.dto.QiNiuAccountDto;
import hcloud.files.qiniu.modules.model.entity.FilesQiniuAccount;
import hcloud.files.qiniu.modules.service.FileUploadService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * description 描述这个类的主要功能、用途
 * 创建时间 2019/9/3
 *
 * @author 杨丁辉
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private FilesQiniuAccountDao filesQiniuAccountDao;

    @Override
    public String upload(MultipartFile file) throws IOException {
        String obj = "";
        List<FilesQiniuAccount> accountList = filesQiniuAccountDao.selectAll();
        QiNiuAccountDto accountDto = null;
        if (!CollectionUtils.isEmpty(accountList)) {
            FilesQiniuAccount account = accountList.get(0);
            accountDto = new QiNiuAccountDto();
            BeanUtils.copyProperties(account, accountDto);
        } else {
            throw new BusinessException(201, "未查找到账户配置");
        }
        String originalFilename = file.getOriginalFilename();
        String filePath = "";
        String fileName = originalFilename;
        DateUtil.getSecondTimestamp(new Date());
        String accessKey = accountDto.getAccesskey();
        String secretKey = accountDto.getSecretkey();
        String bucket = accountDto.getBucketname();
//        String key = "file key";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
//        System.out.println(upToken);
        Configuration cfg = new Configuration(Region.huanan());
        UploadManager uploadManager = new UploadManager(cfg);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM");
        String formatDate = dateFormat.format(new Date());

        InputStream inputStream = file.getInputStream();
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[600]; //buff用于存放循环读取的临时数据
        int rc = 0;
        while ((rc = inputStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] uploadBytes = swapStream.toByteArray();
        try {
            Response response = uploadManager.put(uploadBytes, formatDate + "/" + fileName, upToken);
            DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            obj = defaultPutRet.key;
            System.out.println(obj);
            return obj;
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
