package br.com.rafael.projeto.ecommerce.vendas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HealthCheckControllerTest {

    private HealthCheckController healthCheckController;

    @BeforeEach
    void setUp() {
        healthCheckController = new HealthCheckController();
    }

    @Test
    @DisplayName("Deve retornar a mensagem de aplicação rodando")
    void deveRetornarStatusDaAplicacao() {
        // Act
        var resposta = healthCheckController.status();

        // Assert
        assertThat(resposta).isEqualTo("Aplicação está rodando!!");
    }
}