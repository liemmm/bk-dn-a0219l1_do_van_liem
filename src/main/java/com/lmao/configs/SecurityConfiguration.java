package com.lmao.configs;

import com.lmao.areas.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomAuthFailureHandler customAuthFailureHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/register/**", "/login", "/bootstrap/**",
                        "/jquery/**", "/font-awesome/**", "/css/**",
                        "/image/get-initial-images-for-mainbar", "/image/get-dynamically-loaded-images-for-mainbar",
                        "/logo/**", "/fragments/**", "/scripts/**").permitAll()
                .antMatchers("/admin/**", "/category/create", "/category/edit").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login").permitAll()
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .failureHandler(customAuthFailureHandler)
                .and()
                    .rememberMe()
                    .rememberMeParameter("rememberMe")
                    .key("739124e39-e610-t9a+-926c-f86d46f5360a")
                    .rememberMeCookieName("LmaoRememberMe")
                    .tokenValiditySeconds(604800)
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/unauthorized")
                .and()
                    .csrf()
                    .csrfTokenRepository(csrfTokenRepository());
    }



    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setSessionAttributeName("_csrf");
        return repository;
    }
}
