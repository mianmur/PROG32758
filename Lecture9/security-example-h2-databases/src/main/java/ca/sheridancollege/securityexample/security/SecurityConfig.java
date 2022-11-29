package ca.sheridancollege.securityexample.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private LoggingAccessDeniedHandler accessDeniedHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    @Lazy // Because we will use this elsewhere as well, we tell the framework
        // to hold-off when creating this bean. Otherwise there will be an error
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

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
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/", "/**").permitAll()
                    .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/secured")
                    .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);

        // This should never be done normally, we are only doing this to
        // access h2 database at the same location
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema()
                .passwordEncoder(passwordEncoder)
                .withUser("bugs").password(passwordEncoder.encode("bunny"))
                .roles("USER")
                    .and()
                .withUser("daffy").password(passwordEncoder.encode("duck"))
                .roles("USER", "MANAGER");
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager() throws Exception {
        JdbcUserDetailsManager jdbcUserDetailsManager =
                new JdbcUserDetailsManager();

        jdbcUserDetailsManager.setDataSource(dataSource);

        return jdbcUserDetailsManager;
    }
}
