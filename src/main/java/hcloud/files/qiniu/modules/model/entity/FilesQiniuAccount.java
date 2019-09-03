package hcloud.files.qiniu.modules.model.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "files_qiniu_account")
public class FilesQiniuAccount {
    /**
     * 七牛云bucket
     */
    @Id
    @Column(name = "bucketname")
    private String bucketname;

    /**
     * 七牛云RAM用户 KeyID
     */
    @Column(name = "accesskey")
    private String accesskey;

    /**
     * 七牛云RAM用户 KeySecret
     */
    @Column(name = "secretkey")
    private String secretkey;

    /**
     * 七牛云公网endpoint
     */
    @Column(name = "wanendpoint")
    private String wanendpoint;

    /**
     * 七牛云私网endpoint
     */
    @Column(name = "lanendpoint")
    private String lanendpoint;

    /**
     * 是否允许公共读取，N-私有读取, Y-公共读取
     */
    @Column(name = "publicread")
    private String publicread;

    /**
     * 备注说明
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 创建日期
     */
    @Column(name = "createDate")
    private Date createDate;

    /**
     * 创建人姓名
     */
    @Column(name = "createByName")
    private String createByName;

    /**
     * 修改日期
     */
    @Column(name = "modifyDate")
    private Date modifyDate;

    /**
     * 修改人姓名
     */
    @Column(name = "modifyByName")
    private String modifyByName;
}