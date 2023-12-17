package ru.adrian.flovers.floversShop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.adrian.flovers.floversShop.security.AuthProvider;
/*
TODO: проработать все контроллеры и безопасность, обеспечить полную защиту информации пользователей
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration   {
    private  final AuthProvider authProvider;
@Autowired
    public SecurityConfiguration(AuthProvider provider) {
        this.authProvider = provider;
    }


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {//TODO: Провести настройку доступа пользователей к разлчиным старницам сразу как будут сделаны все запросы
            http.
          authorizeHttpRequests((authz)->{
          authz.requestMatchers("/auth/login","/auth/reg","/","/shop/**","/process_login").permitAll()

                  .anyRequest().authenticated();
          }).formLogin(form->
                            form.loginPage("/auth/login")
                                    .loginProcessingUrl("/process_login")
                                    .defaultSuccessUrl("/world/hello")
                                    .failureUrl("/auth/login?error")
                                    .permitAll())


                    .cors(cors->cors.disable())
                    .csrf(csrf->csrf.disable());
            return http.build();
        }


    @Bean
    public PasswordEncoder passwordEncoder() {
        // Используем NoOpPasswordEncoder только для тестового профиля
        return NoOpPasswordEncoder.getInstance();
    }


    //Настройка аутентификации, логика аутентификации
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authProvider);
    }

}
