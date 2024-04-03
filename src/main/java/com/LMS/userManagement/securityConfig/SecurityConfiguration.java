package com.LMS.userManagement.securityConfig;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
//@EnableWebMvc
public class SecurityConfiguration  {

   /* @Autowired
    @Qualifier("handlerExceptionResolver")
   private  HandlerExceptionResolver handlerExceptionResolver;*/

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final   AuthenticationProvider authenticationProvider;

  //  private final LogoutHandler logoutHandler;

 /*@Bean
 public JwtAuthenticationFilter jwtAuthFilter(){
        return new JwtAuthenticationFilter(handlerExceptionResolver);
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

       http.cors(AbstractHttpConfigurer::disable)
               .csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(auth->
                            auth   .requestMatchers("/lms/api/auth/**").permitAll()
                                    .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                               //     .requestMatchers("/actuator", "/actuator/**").permitAll()
                                    .requestMatchers("/lms/api/auth/refreshToken").permitAll()
                                    .requestMatchers("/lms/api/tenant/**").permitAll()
                                    .requestMatchers("/lms/api/admin/**").permitAll()
                                   .requestMatchers("/lms/api/user/getCourseCompletion").permitAll()
                                    .requestMatchers(HttpMethod.OPTIONS).permitAll()
                                   /*.requestMatchers("/lms/api/auth/saveAndEditProfile").permitAll()
                                    .requestMatchers("/lms/api/user/saveSection").permitAll()
                                    .requestMatchers("/lms/api/user/saveCourse").permitAll()
                                    .requestMatchers("/lms/api/user/deleteCourseById").permitAll()
*/                                   /* .requestMatchers("/lms/api/user/").hasRole("user")
                                    .requestMatchers("/lms/api/admin").hasRole("admin")*/
                                    .anyRequest().authenticated()
                         )
                .sessionManagement(sess->sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class)
             // .logout(logout -> logout.logoutUrl("lms/api/auth/logout")
              //         .addLogoutHandler(logoutHandler)
               //       .logoutSuccessHandler(((request, response, authentication) ->
                 //            SecurityContextHolder.clearContext())))
                       ;
        return http.build();
    }


   /* @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // This wildcard pattern matches any host from domain.com and url patterns like "https:microservice.division.domain.com/version1/some_endpoint"
                registry.addMapping("/**").allowedMethods("*").allowedOriginPatterns("http://localhost:3000");
            }
        };
    }*/
}
