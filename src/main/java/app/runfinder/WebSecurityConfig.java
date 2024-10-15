package app.runfinder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import app.runfinder.web.services.UserDetailServiceImpl;

@Configuration
public class WebSecurityConfig {

    private UserDetailServiceImpl userDetailServiceImpl;

    public WebSecurityConfig(UserDetailServiceImpl userDetailServiceImpl) {
        this.userDetailServiceImpl = userDetailServiceImpl;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/signup", "/rungrouplist").permitAll()
                        .requestMatchers("/add", "/delete/**", "/edit/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(formlogin -> formlogin.loginPage("/login")
                        .defaultSuccessUrl("/rungrouplist", true)
                        .permitAll())
                .logout(logout -> logout
                        .permitAll())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
    }
}