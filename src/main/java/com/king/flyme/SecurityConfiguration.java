package com.king.flyme;

import com.king.flyme.bean.Account;
import com.king.flyme.bean.CustUser;
import com.king.flyme.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Configuration
@ComponentScan
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Value("${secure.key:default-secure-key}")
    private String key;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http.authorizeRequests()
                .antMatchers("/", "/hello", "/home","/add/account","/index").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .permitAll()
                .and().logout()
                .logoutSuccessUrl("/login")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .permitAll()
                .and().exceptionHandling()
                .authenticationEntryPoint(new AjaxAuthenticationEntryPoint("/login"))
                .accessDeniedPage("/errorpage")
                .and().rememberMe()
                .key(key)
                .rememberMeCookieName("token")
                .userDetailsService(userDetailsService);
        //@formatter:on
    }

    @Service("userDetailsService")
    public static class CustomUserDetailsService implements UserDetailsService {

        @Autowired
        private AccountService accountService;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            // 获取用户信息
            Account account = accountService.findUserByAccount(username);
            if (account == null) {
                throw new UsernameNotFoundException("username not found.");
            }

            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

            // 计算用户状态
            boolean enabled = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;

            String password = account.getPassword();
            // 擦除用户密码
            account.setPassword("******");
            // 返回用户信息。
            return new CustUser(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities,account.getId().longValue(),account.getName());
        }

    }

    public class AjaxAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

        public AjaxAuthenticationEntryPoint(String loginFormUrl) {
            super(loginFormUrl);
        }

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
            if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");//对于ajax请求不重定向  而是返回错误代码
            } else {
                super.commence(request, response, authException);
            }
        }
    }
}

