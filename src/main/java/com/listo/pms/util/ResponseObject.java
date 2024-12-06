package com.listo.pms.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseObject {
    public static Map<String, Object> createResponse(String message, Object data, int status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("data", data);
        response.put("status", status);
        return response;
    }
}
