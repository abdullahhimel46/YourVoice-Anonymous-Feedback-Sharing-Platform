package org.main.deshimulaclone.config;


import lombok.RequiredArgsConstructor;
import org.main.deshimulaclone.service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    final private CustomUserDetailService customUserDetailService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headersConfigurer -> headersConfigurer
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                )
                .authorizeHttpRequests(authorizerequests -> authorizerequests
                        .requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers("/user/**").hasAnyAuthority("ROLE_USER")
//                        .requestMatchers("/feedback","/feedback/*/edit").hasAnyAuthority("ROLE_USER")
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .anyRequest().permitAll()
                )
                .formLogin(formLoginConfigurer -> formLoginConfigurer
                        .loginPage("/login")
                        .failureUrl("/login?error=true")
                        .defaultSuccessUrl("/dashboard",true)
                )
                .logout(logoutConfigurer -> logoutConfigurer
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                )
                .userDetailsService(customUserDetailService)

        .build();
    }
}
