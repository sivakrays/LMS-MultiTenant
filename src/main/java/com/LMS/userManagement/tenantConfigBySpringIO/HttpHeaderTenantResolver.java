package com.LMS.userManagement.tenantConfigBySpringIO;

import com.LMS.userManagement.securityConfig.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HttpHeaderTenantResolver implements TenantResolver<HttpServletRequest> {

    private final JwtService jwtService;


    //  private  final TenantHttpProperties tenantHttpProperties;
    @Override
    public String resolveTenantId(HttpServletRequest request) {
        String tenantIdInHeader = request.getHeader("tenantId");
       if (tenantIdInHeader==null) {
            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            final String tenantId;
                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                    return null;
                }

                jwt = authHeader.substring(7);
              tenantId=  jwtService.extractTenantId(jwt);
            return tenantId;
        }
        return tenantIdInHeader;
    }


}
