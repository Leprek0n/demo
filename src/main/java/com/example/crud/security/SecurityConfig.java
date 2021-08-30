package com.example.crud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//
//        http.logout()
//                .permitAll()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login?logout");
//
//
//        http
//                // делаем страницу регистрации недоступной для авторизированных пользователей
//                .authorizeRequests()
//                //страницы аутентификаци доступна всем
//                .antMatchers("/login","/hello").anonymous()
//                // защищенные URL
//                .antMatchers("/admin/**").access("hasAuthority('ADMIN')")
//                .antMatchers("/user/**").access("hasAuthority('USER')")
//                .anyRequest().authenticated();

        http
                .authorizeRequests()
                .antMatchers("/user").authenticated()
                .antMatchers("/hello").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .and()
                .logout();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }
}
