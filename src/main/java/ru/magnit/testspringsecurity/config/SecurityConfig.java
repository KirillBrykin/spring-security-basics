package ru.magnit.testspringsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.magnit.testspringsecurity.model.Role;

//  @Bean - добавляем для доступности метода, без него метод не будет работать
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String API_URL = "/api/**";

    // Аутентификация  - это возможноть заходить человеку в приложение. Примером аутентификации может быть
    // сравнение пароля, введенного пользователем, с паролем, который сохранен в базе данных сервера.

    // Авторизация - это проверка на конкретную роль, админ или пользователь,
    // может ли он что-либо делать (заходить на конкретыне разделы, добавлять контент, удалять и тд.).
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    // Переопределяем метод что бы использовать InMemory users
    // и хранить пользвоателей в приложении.
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        // создаем админа с ролью ADMIN
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("123"))
                .roles(Role.ADMIN.name())
                .build();

        // создаем пользователя с ролью USER
        UserDetails userAnton = User.builder()
                .username("anton")
                .password(passwordEncoder().encode("321"))
                .roles(Role.USER.name())
                .build();

        return new InMemoryUserDetailsManager(admin,userAnton);
    }

    // метод позволяет закодировать пароль с "силой" 12 пароль и получить хэш,
    // не обязательно кодировать но так безопаснее
    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}