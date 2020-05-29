package top.codermhc.drugmanager.config;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import top.codermhc.drugmanager.utils.ResponseData;

@ControllerAdvice
@Slf4j
public class WrapResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
        Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Object result;
        if (body instanceof ResponseData) {
            result = body;
        } else if (body instanceof Page) {
            Page page = (Page) body;
            Map<String, Object> data = new HashMap<>();
            data.put("pageSize", page.getSize());
            data.put("pages", page.getPages());
            data.put("page", page.getCurrent());
            data.put("total", page.getTotal());
            data.put("data", page.getRecords());
            result = new ResponseData(HttpStatus.OK, "success", data);
        } else {
            // 其他类型进行统一包装
            result = new ResponseData(HttpStatus.OK, "success", body);
        }
        return result;
    }
}
