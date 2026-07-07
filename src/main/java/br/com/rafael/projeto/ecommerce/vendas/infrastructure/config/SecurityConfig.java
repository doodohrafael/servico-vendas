package br.com.rafael.projeto.ecommerce.vendas.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Desabilita CSRF, pois em APIs REST stateless com JWT, ele não é necessário
            .csrf(AbstractHttpConfigurer::disable)
            // Configura as regras de acesso
            .authorizeHttpRequests(auth -> auth
                // Deixa o health check público (o nosso controller que testamos!)
                .requestMatchers("/").permitAll() 
                .requestMatchers("/api/v1/pedidos").permitAll()
                // Qualquer outra requisição exige autenticação
                .anyRequest().authenticated()
            )
            // Configura a API para usar JWT (Resource Server)
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {}));

        return http.build();
    }
}