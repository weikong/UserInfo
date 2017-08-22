package com.king.flyme.controller;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public abstract class AbsController {

    public ResponseEntity ajax() {
        return ajax(0, null, null);
    }

    public ResponseEntity ajax(Object data) {
        if (data != null && data instanceof Exception)
            return ajax(-1, ((Exception)data).getMessage(), null);
        return ajax(0, null, data);
    }

    public ResponseEntity ajax(int code, String message) {
        return ajax(code, message, null);
    }

    public ResponseEntity ajax(int code, String message, Object data) {
        Map body = new HashMap(3);
        body.put("code", code);
        if (message != null) {
            body.put("message", message);
        }
        if (data != null) {
            body.put("data", data);
        }
        return ResponseEntity.ok(body);
    }

}
