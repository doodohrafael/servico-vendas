package br.com.rafael.projeto.ecommerce.vendas.domain.model;

public record EnderecoEntrega(
        String cep,
        String logradouro,
        String numero,
        String estado,
        String cidade
) {}
