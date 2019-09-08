package hcloud.files.qiniu.modules.service.impl;

import com.qiniu.util.Auth;
import hcloud.files.qiniu.modules.dao.FilesQiniuAccountDao;
import hcloud.files.qiniu.modules.model.dto.QiNiuAccountDto;
import hcloud.files.qiniu.modules.model.entity.FilesQiniuAccount;
import hcloud.files.qiniu.modules.service.FileUploadService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    public void upload() {
        List<FilesQiniuAccount> accountList = filesQiniuAccountDao.selectAll();
        QiNiuAccountDto accountDto = null;
        if (!CollectionUtils.isEmpty(accountList)) {
            FilesQiniuAccount account = accountList.get(0);
            accountDto = new QiNiuAccountDto();
            BeanUtils.copyProperties(account, accountDto);
        }


        String accessKey = accountDto.getAccesskey();
        String secretKey = accountDto.getSecretkey();
        String bucket = accountDto.getBucketname();
        String key = "file key";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket, key);
        System.out.println(upToken);
    }

}
