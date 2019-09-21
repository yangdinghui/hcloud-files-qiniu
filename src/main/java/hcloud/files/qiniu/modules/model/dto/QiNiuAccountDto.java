package hcloud.files.qiniu.modules.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description 描述这个类的主要功能、用途
 * 创建时间 2019/9/8
 *
 * @author 杨丁辉
 */
@Data
@ApiModel(value = "QiNiuAccountDto",description = "七牛云账户返回体")
public class QiNiuAccountDto {

    @ApiModelProperty("七牛云bucket")
    private String bucketname;

    @ApiModelProperty("七牛云RAM用户 KeyID")
    private String accesskeyid;

    @ApiModelProperty("七牛云RAM用户 KeySecret")
    private String accesskeysecret;

    @ApiModelProperty("七牛云公网endpoint")
    private String wanendpoint;

    @ApiModelProperty("七牛云私网endpoint")
    private String lanendpoint;

    @ApiModelProperty("是否允许公共读取，N-私有读取, Y-公共读取")
    private String publicread;

    @ApiModelProperty("备注说明")
    private String remark;

    @ApiModelProperty("创建日期")
    private String createDate;

    @ApiModelProperty("创建人姓名")
    private String createByName;

    @ApiModelProperty("修改日期")
    private String modifyDate;

    @ApiModelProperty("修改人姓名")
    private String modifyByName;
}
