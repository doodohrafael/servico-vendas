package br.com.rafael.projeto.ecommerce.vendas.infrastructure.config;

import br.com.rafael.projeto.ecommerce.vendas.application.ports.out.PedidoRepositoryPort;
import br.com.rafael.projeto.ecommerce.vendas.application.ports.usecases.CriarPedidoUseCase;
import br.com.rafael.projeto.ecommerce.vendas.application.ports.usecases.FaturarPedidoUseCase;
import br.com.rafael.projeto.ecommerce.vendas.application.ports.usecases.PagarPedidoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;

import java.util.Properties;

@Configuration
public class UseCaseConfig {

    private final PlatformTransactionManager transactionManager;

    public UseCaseConfig(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    private TransactionProxyFactoryBean criarProxyTransacional(Object useCase) {
        TransactionProxyFactoryBean proxy = new TransactionProxyFactoryBean();
        proxy.setTransactionManager(transactionManager);
        proxy.setTarget(useCase);

        Properties transactionAttributes = new Properties();
        transactionAttributes.setProperty("executar*", "PROPAGATION_REQUIRED,-Exception");
        proxy.setTransactionAttributes(transactionAttributes);

        return proxy;
    }

    @Bean
    public CriarPedidoUseCase criarPedidoUseCase(PedidoRepositoryPort repositoryPort) {
        return new CriarPedidoUseCase(repositoryPort);
    }

    @Bean
    public PagarPedidoUseCase pagarPedidoUseCase(PedidoRepositoryPort repositoryPort) {
        return new PagarPedidoUseCase(repositoryPort);
    }

    @Bean
    public FaturarPedidoUseCase faturarPedidoUseCase(PedidoRepositoryPort repositoryPort) {
        return new FaturarPedidoUseCase(repositoryPort);
    }

}
