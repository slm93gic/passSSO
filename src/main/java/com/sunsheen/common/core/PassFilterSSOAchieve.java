package com.sunsheen.common.core;


import com.sunsheen.common.annotation.PassFilterSSO;
import com.sunsheen.common.utils.ClassAnnoScanerUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Path;

/**
 * @ClassName: PassFilterSSOUtils
 * @Description: (去掉多余的监听)
 * @author: SLM
 * @date: 2020年5月8日 上午10:26:37
 * @Copyright: 2020 www.sunsheen.cn Inc. All rights reserved.
 */
@SuppressWarnings("all")
public class PassFilterSSOAchieve {

    private static String BASE_SCANN_PACK = "com.sunsheen";


    /**
     * @Description: (获取注解信息)
     * @author: slm
     * @date: 2020年3月7日 下午6:11:05
     */
    public static void registeredAnnotation(List<String> PassSSoPath) throws Exception {
        List<Class> classsFromPackage = ClassAnnoScanerUtil.getClasssFromPackage(BASE_SCANN_PACK, Path.class);

        for (Class clazz : classsFromPackage) {

            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                Annotation annotation = method.getAnnotation(PassFilterSSO.class);
                if (annotation != null) {
                    Annotation Path = method.getAnnotation(Path.class);
                    if (Path != null) {
                        Path childAnno = (Path) method.getAnnotation(Path.class);
                        Path paretAnno = (Path) clazz.getAnnotation(Path.class);

                        String childPath = childAnno.value();
                        if (null != paretAnno) {
                            String parentPath = paretAnno.value();
                            if (childPath.startsWith("/")) {
                                childPath = parentPath + childPath;
                            } else {
                                childPath = parentPath + "/" + childPath;
                            }
                        }
                        childPath = childPath.replace("//", "/");
                        // 获取key 添加到容器
                        PassSSoPath.add(childPath);
                    }
                }

            }

        }

    }

}
