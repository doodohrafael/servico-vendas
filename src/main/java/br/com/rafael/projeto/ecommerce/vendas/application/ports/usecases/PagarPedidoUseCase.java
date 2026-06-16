package br.com.rafael.projeto.ecommerce.vendas.application.ports.usecases;

import br.com.rafael.projeto.ecommerce.vendas.application.ports.out.PedidoRepositoryPort;
import br.com.rafael.projeto.ecommerce.vendas.domain.model.Pagamento;
import br.com.rafael.projeto.ecommerce.vendas.domain.model.Pedido;
import br.com.rafael.projeto.ecommerce.vendas.domain.shared.AssertionConcern;

import java.util.UUID;

public class PagarPedidoUseCase {

    private final PedidoRepositoryPort repositoryPort;

    public PagarPedidoUseCase(PedidoRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    public Pedido executar(UUID pedidoId, Pagamento pagamento) {
        AssertionConcern.assertNotNull(pedidoId, "O ID do pedido é obrigatório.");
        AssertionConcern.assertNotNull(pagamento, "Os dados de pagamento são obrigatórios.");

        Pedido pedido = repositoryPort.buscarPorId(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado."));

        pedido.pagar(pagamento);
        return repositoryPort.salvar(pedido);
    }

}
