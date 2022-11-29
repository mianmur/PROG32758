package ca.sheridancollege.securityexample.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private LoggingAccessDeniedHandler accessDeniedHandler;
    @Autowired
    public void setAccessDeniedHandler(LoggingAccessDeniedHandler accessDeniedHandler) {
        this.accessDeniedHandler=accessDeniedHandler;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/user/**").hasAnyRole("USER", "MANAGER")
                .antMatchers("/secured/**").hasAnyRole("USER", "MANAGER")
                .antMatchers("/manager/**").hasRole("MANAGER")
                .antMatchers("/", "/**")
                .permitAll()
                    .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/secured")
                    .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                    .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("bugs").password("bunny").roles("USER")
                .and()
                .withUser("daffy").password("duck").roles("USER", "MANAGER");
    }
}
