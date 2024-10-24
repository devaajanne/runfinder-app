package app.runfinder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

        @Bean
        public SecurityFilterChain configure(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers("/login", "/signup", "/savenewuser").permitAll()
                                                .requestMatchers("/add", "/savenewgroup", "/edit/**",
                                                                "/saveeditedgroup", "/delete/**")
                                                .hasAnyAuthority("CONTRIBUTOR", "ADMIN")
                                                .anyRequest().authenticated())
                                .formLogin(formlogin -> formlogin.loginPage("/login")
                                                .defaultSuccessUrl("/home", true)
                                                .permitAll())
                                .logout(logout -> logout
                                                .permitAll())
                                .csrf(csrf -> csrf.disable());

                return http.build();
        }

        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}