package hcloud.files.qiniu.base.handler;

import hcloud.files.qiniu.base.enums.ResultStatus;
import hcloud.files.qiniu.base.excetion.BusinessException;
import hcloud.files.qiniu.base.view.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * description 描述这个类的主要功能、用途
 * 创建时间 2019/9/1
 *
 * @author 杨丁辉
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({Exception.class})
    public ApiResponse<String> handlerException(Exception e) {
        if (e instanceof BusinessException) {
            log.error("业务异常" + e.getMessage());
            BusinessException businessException = (BusinessException)e;
            return ApiResponse.fail(businessException.getCode(), businessException.getMessage());
        }  if (e instanceof MethodArgumentNotValidException) {

            MethodArgumentNotValidException exception = (MethodArgumentNotValidException)e;
            List<ObjectError> errors = exception.getBindingResult().getAllErrors();
            StringBuffer sb = new StringBuffer();
            for (ObjectError error : errors) {
                String message = error.getDefaultMessage();
                sb.append(message).append(";");
            }

            String message = sb.toString().substring(0, sb.toString().lastIndexOf(";"));
            log.error("参数校验错误" + message);
            return ApiResponse.fail(Integer.valueOf(202), message);
        }  if (e instanceof ConstraintViolationException) {

            ConstraintViolationException exception = (ConstraintViolationException)e;
            StringBuffer sb = new StringBuffer();
            for (ConstraintViolation constraint : exception.getConstraintViolations()) {
                sb.append(constraint.getInvalidValue()).append("值不").append(constraint.getMessage()).append(";");
            }
            String message = sb.toString().substring(0, sb.toString().lastIndexOf(";"));
            log.error("参数校验错误" + message);
            return ApiResponse.fail(Integer.valueOf(202), message);
        }
        log.error("系统异常", e);
        return ApiResponse.fail(ResultStatus.SYSTEM_ERROR.getCode(), "系统后台数据处理异常");
    }
}
