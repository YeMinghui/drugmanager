package top.codermhc.drugmanager.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseData {

    HttpStatus code;
    String message;
    Object data;

    public ResponseData(HttpStatus code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
