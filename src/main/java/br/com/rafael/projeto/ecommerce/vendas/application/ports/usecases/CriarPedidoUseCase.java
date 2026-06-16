package br.com.rafael.projeto.ecommerce.vendas.application.ports.usecases;

import br.com.rafael.projeto.ecommerce.vendas.application.ports.out.PedidoRepositoryPort;
import br.com.rafael.projeto.ecommerce.vendas.domain.model.EnderecoEntrega;
import br.com.rafael.projeto.ecommerce.vendas.domain.model.ItemPedido;
import br.com.rafael.projeto.ecommerce.vendas.domain.model.Pedido;
import br.com.rafael.projeto.ecommerce.vendas.domain.shared.AssertionConcern;

import java.util.List;
import java.util.UUID;

public class CriarPedidoUseCase {

    private final PedidoRepositoryPort repositoryPort;

    public CriarPedidoUseCase(PedidoRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    public Pedido executar(UUID clienteId, EnderecoEntrega enderecoEntrega, List<ItemPedido> itens) {
        AssertionConcern.assertNotNull(itens, "A lista de itens não pode ser nula.");
        if (itens.isEmpty()) {
            throw new IllegalArgumentException("Um pedido deve conter pelo menos 1 item.");
        }

        var novoPedido = new Pedido(clienteId, enderecoEntrega);
        itens.forEach(novoPedido::adicionarItem);

        return repositoryPort.salvar(novoPedido);
    }

}
