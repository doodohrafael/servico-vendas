package br.com.rafael.projeto.ecommerce.vendas.infrastructure.adapters.inbound.rest.dto;

import br.com.rafael.projeto.ecommerce.vendas.domain.model.Pedido;

import java.math.BigDecimal;
import java.util.UUID;

public record PedidoResponse(
    UUID id,
    String status,
    BigDecimal valorTotal
) {
    public static PedidoResponse fromDomain(Pedido pedido) {
        return new PedidoResponse(
            pedido.getId(),
            pedido.getStatus().name(),
            pedido.getValorTotal()
        );
    }
}
