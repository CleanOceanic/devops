package br.com.fiap.CleanOceanic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/public/**").permitAll() // Acesso público
                .antMatchers("/swagger**").hasRole("ADMIN") // Acesso para administradores
                .antMatchers("/h2**").hasRole("ADMIN") // Acesso para administradores
                .antMatchers("/api/admin/**").hasRole("ADMIN") // Acesso para administradores
                .anyRequest().authenticated() // Qualquer outra requisição deve ser autenticada
                .and()
                .httpBasic() // Utilizando autenticação básica HTTP
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .invalidSessionUrl("/login") // Redireciona para a página de login se a sessão for inválida
                .maximumSessions(1) // Permite apenas uma sessão por usuário
                .expiredUrl("/login?expired") // Redireciona para a página de login se a sessão expirar
                .and()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutUrl("/logout") // URL de logout
                .logoutSuccessUrl("/login?logout")
                .and()
                .headers().frameOptions().sameOrigin();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
