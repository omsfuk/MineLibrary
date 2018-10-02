package cn.omsfuk.library.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/assets/**", "/register").permitAll()
                .antMatchers("/admin/**").hasAuthority("admin")
                .antMatchers("/reader/**").hasAuthority("reader")
                .anyRequest().authenticated()
                .and().csrf().disable()
                .formLogin()
                .loginPage("/login")
                .successHandler((req, resp, auth) -> {
                    for (GrantedAuthority authority: auth.getAuthorities()) {
                        if (authority.getAuthority().equals("admin")) {
                            resp.sendRedirect("/admin/main");
                            return ;
                        }
                        if (authority.getAuthority().equals("reader")) {
                            resp.sendRedirect("/reader/main");
                            return ;
                        }
                    }
                })
                .permitAll();

        super.configure(http);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                if (charSequence == null || s == null) return false;
                return charSequence.equals(s);
            }
        };
    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super(true);
////        auth.userDetailsService(new UserSecurityService());
//    }
}
