package top.codermhc.drugmanager.exception;

import javax.servlet.ServletException;
import javax.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;
import top.codermhc.drugmanager.utils.ResponseData;

/**
 * 全局异常处理
 *
 * @author Ye Minghui
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseData clientError(HttpClientErrorException e) {
        log.error(e.getMessage(), e.getCause());
        return new ResponseData(e.getStatusCode(), e.getMessage(), null);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseData notSupported(HttpMediaTypeNotSupportedException e) {
        log.error(e.getMessage(), e.getCause());
        return new ResponseData(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), null);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseData noHandlerFoundException(NoHandlerFoundException e) {
        log.error(e.getMessage(), e.getCause());
        return new ResponseData(HttpStatus.NOT_FOUND, "资源不存在", null);
    }

    /**
     * 参数异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseData methodArgumentException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e.getCause());
        return new ResponseData(HttpStatus.BAD_REQUEST, "参数异常", e.getBindingResult());
    }

    /**
     * Bean 验证异常
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseData validationException(ValidationException e) {
        log.error(e.getMessage(), e.getCause());
        return new ResponseData(HttpStatus.BAD_REQUEST, e.getMessage(), null);
    }

    /**
     * Shiro 默认异常，不应该被调用
     */
    @ExceptionHandler(ShiroException.class)
    public ResponseData shiroException(ShiroException e) {
        log.error(e.getMessage(), e.getCause());
        return new ResponseData(HttpStatus.FORBIDDEN, e.getMessage(), null);
    }

    /**
     * 用户名或密码错误异常
     */
    @ExceptionHandler({UnknownAccountException.class, IncorrectCredentialsException.class})
    public ResponseData userNameOrPasswordErrorException(Exception e) {
        log.error(e.getMessage(), e.getCause());
        return new ResponseData(HttpStatus.BAD_REQUEST, "用户名或密码错误", null);
    }

    @ExceptionHandler(AccountException.class)
    public ResponseData accountException(AccountException e) {
        log.error(e.getMessage(), e.getCause());
        return new ResponseData(HttpStatus.FORBIDDEN, e.getMessage(), null);
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(DrugmanagerException.class)
    public ResponseData drugmanagerException(DrugmanagerException e) {
        log.error(e.getMessage(), e.getCause());
        return new ResponseData(HttpStatus.EXPECTATION_FAILED, e.getMessage(), null);
    }

    /**
     * 兜底异常
     */
    @ExceptionHandler({Exception.class, ServletException.class})
    public ResponseData defaultException(Exception e) {
        log.error(e.getMessage(), e.getCause());
        return new ResponseData(HttpStatus.INTERNAL_SERVER_ERROR, "内部错误，请联系管理员", null);
    }

}
