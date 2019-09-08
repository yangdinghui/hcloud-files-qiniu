package hcloud.files.qiniu.base.view;

import com.github.pagehelper.PageInfo;
import hcloud.files.qiniu.base.enums.ResultStatus;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * description 描述这个类的主要功能、用途
 * 创建时间 2019/9/1
 *
 * @author 杨丁辉
 */
public class ApiResponse<T> extends Object {

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ApiResponse)) return false;
        ApiResponse<?> other = (ApiResponse) o;
        if (!other.canEqual(this)) return false;
        Object this$status = getStatus(), other$status = other.getStatus();
        if ((this$status == null) ? (other$status != null) : !this$status.equals(other$status)) return false;
        Object this$msg = getMsg(), other$msg = other.getMsg();
        if ((this$msg == null) ? (other$msg != null) : !this$msg.equals(other$msg)) return false;
        Object this$body = getBody(), other$body = other.getBody();
        return !((this$body == null) ? (other$body != null) : !this$body.equals(other$body));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ApiResponse;
    }

    public int hashCode() {
        int PRIME = 59;
       int result = 1;
        Object $status = getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        Object $msg = getMsg();
        result = result * 59 + (($msg == null) ? 43 : $msg.hashCode());
        Object $body = getBody();
        return result * 59 + (($body == null) ? 43 : $body.hashCode());
    }

    public String toString() {
        return "ApiResponse(status=" + getStatus() + ", msg=" + getMsg() + ", body=" + getBody() + ")";
    }


    public static final Integer SUCCESS_CODE = Integer.valueOf(200);
    public static final Integer FAIL_CODE = Integer.valueOf(201);
    private Integer status;
    private String msg;
    private T body;

    public Integer getStatus() {
        return this.status;
    }


    public String getMsg() {
        return this.msg;
    }


    public T getBody() {
        return (T) this.body;
    }

    public ApiResponse() {
        this.status = SUCCESS_CODE;
        this.msg = "SUCCESS";
    }

    public ApiResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ApiResponse(Integer status, String msg, T body) {
        this.status = status;
        this.msg = msg;
        this.body = body;
    }


    public static ApiResponse<String> success() {
        return new ApiResponse(SUCCESS_CODE, "请求成功");
    }


    public static ApiResponse<String> success(String msg) {
        return new ApiResponse(SUCCESS_CODE, msg);
    }


    public static <T> ApiResponse<T> success(String msg, T result) {
        return new ApiResponse(SUCCESS_CODE, msg, result);
    }


    public static <T> ApiResponse<T> success(T result) {
        return new ApiResponse(SUCCESS_CODE, "请求成功", result);
    }


    public static ApiResponse<String> success(Integer status, String msg) {
        return new ApiResponse(status, msg);
    }


    public static <T> ApiResponse<T> success(Integer status, String msg, T result) {
        return new ApiResponse(status, msg, result);
    }


    public static <T> ApiResponse<ListModel<T>> success(List<T> result) {
        ListModel<T> listModel = new ListModel<T>();
        listModel.setData(result);
        return new ApiResponse(SUCCESS_CODE, "请求成功", listModel);
    }


    public static <T> ApiResponse<PageModel<T>> success(String msg, PageInfo<T> pageInfo) {
        msg = StringUtils.isEmpty(msg) ? "请求成功" : msg;
        if (pageInfo == null) {
            return new ApiResponse(SUCCESS_CODE, msg, null);
        }
        PageModel<T> pageModel = new PageModel<T>();
        pageModel.setCurPage(Integer.valueOf(pageInfo.getPageNum()));
        pageModel.setPageSize(Integer.valueOf(pageInfo.getPageSize()));
        pageModel.setCount(Long.valueOf(pageInfo.getTotal()));
        pageModel.setPageCount(Integer.valueOf(pageInfo.getPages()));
        pageModel.setData(pageInfo.getList());
        return new ApiResponse(SUCCESS_CODE, msg, pageModel);
    }


    public static <T> ApiResponse<PageModel<T>> success(PageInfo<T> pageInfo) {
        return success("", pageInfo);
    }


    public static <T> ApiResponse<PageModel<T>> success(PageModel<T> pageModel) {
        if (pageModel == null) {
            return new ApiResponse(SUCCESS_CODE, "请求成功", null);
        }
        return new ApiResponse(SUCCESS_CODE, "请求成功", pageModel);
    }


    public static ApiResponse<String> fail() {
        return new ApiResponse(FAIL_CODE, "请求失败");
    }


    public static ApiResponse<String> fail(Integer code) {
        return new ApiResponse(code, ResultStatus.getByCode(code).getMsg());
    }


    public static ApiResponse<String> fail(String msg) {
        return new ApiResponse(FAIL_CODE, msg);
    }


    public static ApiResponse<String> fail(Integer code, String msg) {
        return new ApiResponse(code, msg);
    }


    public static <T> ApiResponse<T> fail(T result) {
        return new ApiResponse(FAIL_CODE, "请求失败", result);
    }


    public <T> ApiResponse<T> fail(String message, T result) {
        return new ApiResponse(FAIL_CODE, message, result);
    }


    public static <T> ApiResponse<T> fail(Integer code, String msg, T result) {
        return new ApiResponse(code, msg, result);
    }


    public static ApiResponse<String> fail(ResultStatus resultStatus) {
        return new ApiResponse(resultStatus.getCode(), resultStatus.getMsg());
    }
}
