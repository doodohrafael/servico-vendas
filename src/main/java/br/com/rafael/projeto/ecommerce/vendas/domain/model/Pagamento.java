package br.com.rafael.projeto.ecommerce.vendas.domain.model;

import java.math.BigDecimal;

public record Pagamento(
        String transacaoGatewayId,
        FormaPagamento formaPagamento,
        BigDecimal valorPago
) {}
