package br.com.rafael.projeto.ecommerce.vendas.infrastructure.adapters.inbound.rest.dto;

import br.com.rafael.projeto.ecommerce.vendas.domain.model.FormaPagamento;

import java.math.BigDecimal;

public record PagamentoRequest(
    String transacaoGatewayId,
    FormaPagamento forma,
    BigDecimal valorPago
) {}
