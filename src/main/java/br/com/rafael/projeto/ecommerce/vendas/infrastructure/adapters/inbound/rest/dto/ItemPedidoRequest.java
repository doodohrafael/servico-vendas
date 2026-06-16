package br.com.rafael.projeto.ecommerce.vendas.infrastructure.adapters.inbound.rest.dto;

import br.com.rafael.projeto.ecommerce.vendas.domain.model.ItemPedido;
import java.math.BigDecimal;
import java.util.UUID;

public record ItemPedidoRequest(
    UUID produtoId, String sku, String nome, BigDecimal precoUnitario, int quantidade
) {
    public ItemPedido toDomain() {
        return new ItemPedido(this.produtoId, this.sku, this.nome, this.precoUnitario, this.quantidade);
    }
}