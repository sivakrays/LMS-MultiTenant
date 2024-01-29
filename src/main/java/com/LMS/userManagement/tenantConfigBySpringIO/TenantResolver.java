package com.LMS.userManagement.tenantConfigBySpringIO;

import org.springframework.lang.NonNull;

@FunctionalInterface
public interface TenantResolver<T> {
    String resolveTenantId(@NonNull T object);


    static final String DEFAULT_SCHEMA="public";
}

