package org.askforai._core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/signin", "/signup").permitAll() // 로그인과 회원가입은 누구나 접근 가능
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                .loginPage("/signin") // 로그인 페이지
                .defaultSuccessUrl("/") // 로그인 성공 후 이동할 URL
            )
            .csrf(csrf -> csrf.disable()); // CSRF 보호 비활성화 (필요에 따라 설정)

        return http.build();
    }
}
