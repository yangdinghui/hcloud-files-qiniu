package hcloud.files.qiniu.base.excetion;

import hcloud.files.qiniu.base.enums.ResultStatus;

/**
 * description 描述这个类的主要功能、用途
 * 创建时间 2019/9/8
 *
 * @author 杨丁辉
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Integer code;

    public BusinessException(ResultStatus resultStatus) {
        super(resultStatus.getMsg());
        this.code = resultStatus.getCode();
    }

    public BusinessException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }


    public Integer getCode() {
        return this.code;
    }


    public void setCode(Integer code) {
        this.code = code;
    }
}
