package br.com.rafael.projeto.ecommerce.vendas.infrastructure.adapters.inbound.rest.dto;

import br.com.rafael.projeto.ecommerce.vendas.domain.model.EnderecoEntrega;

public record EnderecoRequest(
    String cep, String logradouro, String numero, String cidade, String estado
) {
    public EnderecoEntrega toDomain() {
        return new EnderecoEntrega(this.cep, this.logradouro, this.numero, this.cidade, this.estado);
    }
}