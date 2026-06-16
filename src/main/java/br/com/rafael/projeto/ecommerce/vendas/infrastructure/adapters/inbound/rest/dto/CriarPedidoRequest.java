package br.com.rafael.projeto.ecommerce.vendas.infrastructure.adapters.inbound.rest.dto;

import br.com.rafael.projeto.ecommerce.vendas.domain.model.EnderecoEntrega;
import br.com.rafael.projeto.ecommerce.vendas.domain.model.ItemPedido;

import java.util.List;
import java.util.UUID;

public record CriarPedidoRequest(
        UUID clienteId,
        EnderecoRequest endereco,
        List<ItemPedidoRequest> itens
) {
    public EnderecoEntrega extrairEndereco() {
        return this.endereco.toDomain();
    }

    public List<ItemPedido> extrairItens() {
        return this.itens.stream()
                .map(ItemPedidoRequest::toDomain)
                .toList();
    }
}