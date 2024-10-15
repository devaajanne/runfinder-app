package app.runfinder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

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
}