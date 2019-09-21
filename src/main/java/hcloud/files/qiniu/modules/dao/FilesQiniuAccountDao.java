package hcloud.files.qiniu.modules.dao;

import hcloud.files.qiniu.modules.model.entity.FsQiniuAccount;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * description 描述这个类的主要功能、用途
 * 创建时间 2019/9/8
 *
 * @author 杨丁辉
 */
@Repository
public interface FilesQiniuAccountDao extends BaseMapper<FsQiniuAccount> {
}
