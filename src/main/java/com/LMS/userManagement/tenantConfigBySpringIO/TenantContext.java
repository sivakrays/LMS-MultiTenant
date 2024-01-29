package com.LMS.userManagement.tenantConfigBySpringIO;


public class TenantContext {

    private static final ThreadLocal<String> TenantId = new InheritableThreadLocal<>();

    public static String getTenantId() {
        return TenantId.get();
    }

    public static void setTenantId(String tenantId) {
        TenantId.set(tenantId);
    }

    public static void clear() {
        TenantId.remove();
    }

}
