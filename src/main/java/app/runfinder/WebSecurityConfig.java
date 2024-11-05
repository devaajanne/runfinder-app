package app.runfinder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

        @Bean
        public SecurityFilterChain configure(HttpSecurity http) throws Exception {
                http.authorizeHttpRequests(authorize -> authorize

                                // Permissions to make REST API requests
                                .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/**").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/api/**").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/api/**").permitAll()

                                // Permissions for all users including unauthenticated uers
                                .requestMatchers("/", "/login/**", "/signup", "/savenewuser", "/runfinderstyles.css")
                                .permitAll()

                                // Permissions for USERS, CONTRIBUTORS and ADMINS
                                .requestMatchers("/home", "/upcomingrungroups",
                                                "/usersignups", "/signuptogroup/**",
                                                "/deletesignup/**", "/search", "/searchresults")
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

                                .httpBasic(Customizer.withDefaults())

                                .formLogin(formlogin -> formlogin.loginPage("/login")
                                                .defaultSuccessUrl("/home", true)
                                                .permitAll())

                                .logout(logout -> logout.permitAll())

                                .csrf(Customizer.withDefaults());

                return http.build();
        }

        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}