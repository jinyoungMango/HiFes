package hiFes.hiFes.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import hiFes.hiFes.handler.LoginFailureHandler;
import hiFes.hiFes.handler.LoginSuccessHandler;
import hiFes.hiFes.repository.user.HostUserRepository;
import hiFes.hiFes.repository.user.NormalUserRepository;
import hiFes.hiFes.service.user.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.RequestMapping;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtService jwtService;
    private final HostUserRepository hostUserRepository;
    private final NormalUserRepository normalUserRepository;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.formLogin().disable()
                .httpBasic().disable()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("host/sign-up").permitAll()
                .antMatchers("host/login").permitAll()
                .antMatchers( "/swagger-ui/**", "/v3/api-docs/").permitAll()
                .antMatchers("/","/css/**","/images/**","/js/**","/favicon.ico").permitAll();


        return http.build();
    }




}
