package top.codermhc.drugmanager.exception;

import javax.servlet.ServletException;
import javax.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.thymeleaf.exceptions.TemplateEngineException;
import top.codermhc.drugmanager.Application;

/**
 * 全局异常处理
 *
 * @author Ye Minghui
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> clientError(HttpClientErrorException e) {
        log.error(e.getMessage(), e.getRootCause());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> noHandlerFoundException(NoHandlerFoundException e) {
        log.error(e.getMessage(), e.getRootCause());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("资源不存在");
    }

    @ExceptionHandler(TemplateEngineException.class)
    public ResponseEntity<Object> templateEngineException(TemplateEngineException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("内部错误，请联系管理员。");
    }

    /**
     * 参数异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e.getCause());
        return ResponseEntity.badRequest().body(e.getBindingResult());
    }

    /**
     * Bean 验证异常
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> validationException(ValidationException e) {
        log.error(e.getMessage(), e.getCause());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    /**
     * Shiro 默认异常，不应该被调用
     */
    @ExceptionHandler(ShiroException.class)
    public ResponseEntity<Object> shiroException(ShiroException e) {
        log.error(e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    /**
     * 用户名或密码错误异常
     */
    @ExceptionHandler({UnknownAccountException.class, IncorrectCredentialsException.class})
    public ResponseEntity<Object> userNameOrPasswordErrorException(Exception e) {
        log.error(e.getMessage(), e.getCause());
        return ResponseEntity.badRequest().body("用户名或密码错误");
    }

    @ExceptionHandler(AccountException.class)
    public ResponseEntity<Object> accountException(AccountException e) {
        log.error(e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(DrugmanagerException.class)
    public ResponseEntity<Object> drugmanagerException(DrugmanagerException e) {
        log.error(e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
    }

    /**
     * 兜底异常
     */
    @ExceptionHandler({Exception.class, ServletException.class})
    public ResponseEntity<Object> defaultException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("内部错误，请联系管理员。");
    }

}
