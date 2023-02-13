package ua.hillelit.lms.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * {@link WebSecurityConf}
 *
 * @author Dmytro Trotsenko on 2/9/23
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConf {

    private final UserDetailsService userDetailsService;

    /**
     * Password encoder bean is designed to password encryption
     *
     * @return encrypted password
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * DaoAuthenticationProvider bean gets information about the user from the UserDetailsService
     *
     * @return New DaoAuthenticationProvider
     */
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Security filter bean is designed to limit access to web resources
     *
     * @param http HttpSecurity
     * @return new configuration
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/css/**", "/img/**", "/home").permitAll()
                        .requestMatchers("/login").anonymous()
                        .requestMatchers("/products/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .successForwardUrl("/home")
                        .defaultSuccessUrl("/home")
                        .permitAll()
                )
                .exceptionHandling().accessDeniedPage("/403").and()
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

}
