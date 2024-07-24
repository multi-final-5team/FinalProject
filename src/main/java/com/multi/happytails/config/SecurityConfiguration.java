package com.multi.happytails.config;

import com.multi.happytails.authentication.model.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final AuthenticationService authenticationService;

    @Autowired
    public SecurityConfiguration(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer configure(){
        return(web) -> web.ignoring().requestMatchers("/css/**", "/js/**", "/images/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//        Map<String, List<String>> permitListMap = authenticationService.getPermitListMap();
//        List<String> adminPermitList = permitListMap.get("adminPermitList");
//        List<String> memberPermitList = permitListMap.get("memberPermitList");
//
//        adminPermitList.forEach(url -> System.out.println("admin permit list : " + url));
//        memberPermitList.forEach(url -> System.out.println("member permit list : " + url));

        http
                .csrf((auth) -> auth.disable()
                )
                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("").permitAll()
//                        .anyRequest().authenticated()
                        .anyRequest().permitAll()
                );
        http
                .formLogin(form -> form
                        .loginPage(("/member/login"))
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/member/mypage", true)
                        .failureForwardUrl("/error/login")
                ).headers(headers -> headers
                        .frameOptions().sameOrigin());
        http
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .logoutSuccessUrl("/"))
                .exceptionHandling((exception) -> exception.accessDeniedPage("/error/denied")
                );

        return http.build();
    }
}
