package com.syz.globalexception.handler.utils;

import org.springframework.stereotype.Component;

@Component
public class PropertiesUtil {
    private static EnvContext envContext;

    public PropertiesUtil() {
    }

    private static EnvContext getInstance() {
        if (envContext == null) {
            envContext = (EnvContext) SpringUtil.getBean(EnvContext.class);
        }

        return envContext;
    }

    public static String getValue(String key) {
        Object obj = getInstance().getProValue(key);
        return StringUtil.null2Str(obj);
    }

    public static int getErrCode(String key) {
        try {
            String code = analiseError(0, key, true);
            return code != null ? Integer.valueOf(code) : -1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getErrMsg(String key) {
        return analiseError(1, key, false);
    }

    public static String getErrMsg(String key, Object... obj) {
        try {
            String message = StringUtil.null2Str(analiseError(1, key, false));
            message = String.format(message.replaceAll("\\{[^}]*\\}", "%s"), obj);
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getServiceId() {
        return getInstance().getProjectName();
    }

    private static String analiseError(int idx, String key, boolean code) {
        try {
            Object obj = getInstance().getErrorCodeProperties().get(key);
            String[] val = StringUtil.null2Str(obj).split("->");
            if (val.length == 2) {
                return val[idx];
            } else {
                return code ? null : key;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
