package com.sunsheen.common;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@SuppressWarnings("all")
public class PassCheck {

    public static boolean isPass(HttpServletRequest request) {
        String url = request.getRequestURI();// 请求的地址
        // 免注解
        List<String> list = PassSSO.PassSSoPath;
        if (url.contains("/api/rest")) {
            url = url.replace("/BigAgent/api/rest/", "");
            return list.toString().contains(url);
        }
        return false;
    }
}
