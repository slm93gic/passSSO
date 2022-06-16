package com.sunsheen.common;

import com.sunsheen.common.core.PassFilterSSOAchieve;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class PassSSO {

    // 容器
    public static List<String> PassSSoPath = null;

    public static void start() {
        PassSSoPath = new ArrayList<String>();
        try {
            PassFilterSSOAchieve.registeredAnnotation(PassSSoPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
