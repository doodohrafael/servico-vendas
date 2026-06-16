package br.com.rafael.projeto.ecommerce.vendas.application.ports.usecases;

import br.com.rafael.projeto.ecommerce.vendas.application.ports.out.PedidoRepositoryPort;
import br.com.rafael.projeto.ecommerce.vendas.domain.model.Pedido;
import br.com.rafael.projeto.ecommerce.vendas.domain.shared.AssertionConcern;

import java.util.UUID;

public class FaturarPedidoUseCase {

    private final PedidoRepositoryPort repositoryPort;

    public FaturarPedidoUseCase(PedidoRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    public Pedido executar(UUID pedidoId) {
        AssertionConcern.assertNotNull(pedidoId, "O ID do pedido é obrigatório para o faturamento.");

        Pedido pedido = repositoryPort.buscarPorId(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado."));

        pedido.faturar();
        return repositoryPort.salvar(pedido);
    }
}