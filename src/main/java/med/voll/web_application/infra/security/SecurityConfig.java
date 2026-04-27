package med.voll.web_application.infra.security;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService dadosUsuarioCadastrado(){
        UserDetails usuario1 = User.builder()
                .username("raphael@teste.com")
                .password("{noop}1234")
                .build();

        UserDetails usuario2 = User.builder()
                .username("tosco@teste.com")
                .password("{noop}1234")
                .build();

        return new InMemoryUserDetailsManager(usuario1,usuario2);
    }

    @Bean
    public SecurityFilterChain filtrosSeguranca(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(req -> {
                    req.requestMatchers("/css/**", "/js/**", "/assets/**").permitAll();
                    req.anyRequest().authenticated();
                }).formLogin(form -> form.loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll())
                .logout(logout -> logout.logoutSuccessUrl("/login?logout")
                        .permitAll())
                .build();
    }

}
