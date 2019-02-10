package org.vision.service.admin.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


@Data
@AllArgsConstructor
public class ResponseData<T> implements Serializable {

    private static final long serialVersionUID = -502074009660099743L;
    private int code;
    private String message;
    private T data;

    public ResponseData(T data) {
        this.code = 0;
        this.data = data;
    }

    public ResponseData(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
