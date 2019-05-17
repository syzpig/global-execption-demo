package com.syz.globalexception.handler.utils;


    public class PropertiesUtil {
        private static EnvContext envContext;

        public PropertiesUtil() {
        }

        private static EnvContext getInstance() {
            if (envContext == null) {
                envContext = (EnvContext)SpringUtil.getBean(EnvContext.class);
            }

            return envContext;
        }

        public static String getValue(String key) {
            Object obj = getInstance().getProValue(key);
            return StringUtil.null2Str(obj);
        }

        public static int getErrCode(String key) {
            String code = analiseError(0, key, true);
            return code != null ? Integer.valueOf(code) : -1;
        }

        public static String getErrMsg(String key) {
            return analiseError(1, key, false);
        }

        public static String getErrMsg(String key, Object... obj) {
            String message = StringUtil.null2Str(analiseError(1, key, false));
            message = String.format(message.replaceAll("\\{[^}]*\\}", "%s"), obj);
            return message;
        }

        public static String getServiceId() {
            return getInstance().getProjectName();
        }

        private static String analiseError(int idx, String key, boolean code) {
            Object obj = getInstance().getErrorCodeProperties().get(key);
            String[] val = StringUtil.null2Str(obj).split("->");
            if (val.length == 2) {
                return val[idx];
            } else {
                return code ? null : key;
            }
        }
}
