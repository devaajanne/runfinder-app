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
                http.authorizeHttpRequests(authorize -> authorize
                                // Permissions for all users including unauthenticated uers
                                .requestMatchers("/login/**", "/signup")
                                .permitAll()

                                // Permissions for USERS, CONTRIBUTORS and ADMINS
                                .requestMatchers("/home", "/upcomingrungroups",
                                                "/usersignups", "/savenewuser", "/signuptogroup/**",
                                                "/deletesignup/**")
                                .hasAnyAuthority("USER", "CONTRIBUTOR", "ADMIN")

                                // Permissions for CONTRIBUTORS and ADMINS
                                .requestMatchers("/userrungroups", "/addnewgroup", "/savenewgroup",
                                                "/editgroup/**",
                                                "/saveeditedgroup", "/deletegroup/**", "/zipcodes/**")
                                .hasAnyAuthority("CONTRIBUTOR", "ADMIN")

                                // Permissions for ADMINS
                                .requestMatchers("/allrungroups", "/allappusers", "/restoregroup/**",
                                                "/editappuserrole/**", "/saveeditedappuserrole")
                                .hasAnyAuthority("ADMIN")

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