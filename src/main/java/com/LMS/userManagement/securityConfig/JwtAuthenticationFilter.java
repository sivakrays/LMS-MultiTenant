package com.LMS.userManagement.securityConfig;

import com.LMS.userManagement.tenantConfigBySpringIO.TenantContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

//commented for Handler exception
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter  extends OncePerRequestFilter {


    private final JwtService jwtService;


  // private  HandlerExceptionResolver exceptionResolver;



    private final  UserDetailsService userDetailsService;


   /* @Autowired
    public  JwtAuthenticationFilter(HandlerExceptionResolver exceptionResolver){
        this.exceptionResolver=exceptionResolver;
    }*/


    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
         @NonNull   HttpServletResponse response,
          @NonNull  FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader=request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
//try {


    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        filterChain.doFilter(request, response);
        return;
    }

    jwt = authHeader.substring(7);
        String tenantId=     jwtService.extractTenantId(jwt);
        TenantContext.setTenantId(tenantId);
    userEmail = jwtService.extractUsername(jwt);





    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

       /* var isTokenValid=tokenRepository.findByToken(jwt)
                .map(t -> !t.getExpired() && !t.getRevoked())
                .orElse(false);*/

        if (jwtService.isTokenValid(jwt, userDetails)) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }
        TenantContext.clear();
    filterChain.doFilter(request, response);



/*}catch (Exception ex){
    exceptionResolver.resolveException(request,response,null,ex);
}*/
    }
}
