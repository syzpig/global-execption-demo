package com.syz.globalexception.handler.utils;

import org.springframework.context.ApplicationEvent;

import java.util.Map;

public class EnvChangeEvent extends ApplicationEvent {
    private Map<String, Object> source;

    public EnvChangeEvent(Map<String, Object> source) {
        super(source);
        this.source = source;
    }

    public Map<String, Object> getSource() {
        return this.source;
    }
}