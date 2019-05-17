package com.syz.globalexception.handler.utils;

import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.*;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class EnvContext {
    private Map<String, Object> errorCodeProperties;
    private String projectName;
    private ConfigurableEnvironment env;

    public EnvContext(Environment environment) {
        this.env = (ConfigurableEnvironment) environment;
        this.refreshPropertiew();
        this.projectName = this.env.getProperty("spring.application.name");
    }

    @EventListener
    private void onApplicationEvent(EnvironmentChangeEvent event) {
        this.refreshPropertiew();
    }

    private void refreshPropertiew() {
        this.setProperties(this.env.getPropertySources());
        SpringUtil.getApplicationContext().publishEvent(new EnvChangeEvent(this.errorCodeProperties));
    }

    private void setProperties(PropertySources propertySources) {
        RelaxedNames errorKeyPrefixes = new RelaxedNames("error.");
        Map<String, Object> errorProperties = new LinkedHashMap();
        Iterator var4 = propertySources.iterator();

        while (true) {
            PropertySource source;
            do {
                if (!var4.hasNext()) {
                    this.errorCodeProperties = Collections.unmodifiableMap(errorProperties);
                    return;
                }

                source = (PropertySource) var4.next();
            } while (!(source instanceof EnumerablePropertySource));

            String[] var6 = ((EnumerablePropertySource) source).getPropertyNames();
            int var7 = var6.length;

            for (int var8 = 0; var8 < var7; ++var8) {
                String name = var6[var8];
                String key = this.getSubKey(name, errorKeyPrefixes);
                if (key != null && !errorProperties.containsKey(key)) {
                    errorProperties.put(key, source.getProperty(name));
                }
            }
        }
    }

    private String getSubKey(String name, RelaxedNames keyPrefix) {
        Iterator var3 = keyPrefix.iterator();

        String candidateKeyPrefix;
        do {
            if (!var3.hasNext()) {
                return null;
            }

            candidateKeyPrefix = (String) var3.next();
        } while (!name.startsWith(candidateKeyPrefix));

        return name;
    }

    public Map<String, Object> getErrorCodeProperties() {
        return this.errorCodeProperties;
    }

    public String getProValue(String key) {
        return this.env.getProperty(key);
    }

    public String getProjectName() {
        return this.projectName;
    }
}
