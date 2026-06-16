package br.com.rafael.projeto.ecommerce.vendas.application.ports.out;

import br.com.rafael.projeto.ecommerce.vendas.domain.model.Pedido;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoRepositoryPort {

    Pedido salvar(Pedido pedido);
    Optional<Pedido> buscarPorId(UUID id);
    List<Pedido> buscarPorCidade(String cidade);

}
