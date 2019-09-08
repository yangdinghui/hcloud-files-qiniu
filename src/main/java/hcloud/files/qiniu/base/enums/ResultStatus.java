package hcloud.files.qiniu.base.enums;

/**
 * description 描述这个类的主要功能、用途
 * 创建时间 2019/9/1
 *
 * @author 杨丁辉
 */

public enum ResultStatus {
    SYSTEM_ERROR(Integer.valueOf(500), "系统错误"),
    PARAMETER_CHECK_ERROR(Integer.valueOf(501), "参数校验错误"),
    UNLOGIN_ERROR(Integer.valueOf(401), "用户未登录或登录状态超时失效"),
    AUTH_VALID_ERROR(Integer.valueOf(402), "用户权限不足"),
    ILLEGAL_REQUEST_ERROR(Integer.valueOf(403), "非法请求"),
    MD5_ENCRYPT_ERRO(Integer.valueOf(300), "MD5加密异常"),
    JAVABEANTOMAP_ERROR(Integer.valueOf(301), "JavaBean转Map异常"),
    FILEUPLOAD_ERROR(Integer.valueOf(302), "文件上传异常"),
    FILEDOWN_ERROR(Integer.valueOf(303), "文件下载异常"),
    FILETYPE_ERROR(Integer.valueOf(304), "不支持的文件类型上传"),
    REDIS_ERROR(Integer.valueOf(305), "Redis操作异常"),
    SERIALRULE_ERROR(Integer.valueOf(600), "流水号生成异常");

    private final Integer code;

    private final String msg;


    ResultStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Integer getCode() {
        return this.code;
    }


    public String getMsg() {
        return this.msg;
    }


    public String toString() {
        return this.code.toString();
    }


    public static ResultStatus getByCode(Integer code) {
        for (ResultStatus _enum : values()) {
            if (_enum.getCode() == code || _enum.getCode().equals(code)) {
                return _enum;
            }
        }
        return null;
    }
}
