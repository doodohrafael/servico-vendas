package br.com.rafael.projeto.ecommerce.vendas.domain.model;

import br.com.rafael.projeto.ecommerce.vendas.domain.shared.AssertionConcern;

import java.math.BigDecimal;
import java.util.UUID;

public class ItemPedido {

    private UUID produtoId;
    private String skuSnapshot;
    private String nomeSnapshot;
    private BigDecimal precoUnitarioSnapshot;
    private int quantidade;

    public ItemPedido(UUID produtoId, String skuSnapshot, String nomeSnapshot, BigDecimal precoUnitarioSnapshot, int quantidade) {
        AssertionConcern.assertNotNull(produtoId, "O ID do produto não pode ser nulo.");
        AssertionConcern.assertNotEmpty(skuSnapshot, "O SKU do produto é obrigatório.");
        AssertionConcern.assertNotEmpty(nomeSnapshot, "O nome do produto é obrigatório.");
        AssertionConcern.assertPositive(precoUnitarioSnapshot, "O preço unitário é inválido ou nulo.");
        AssertionConcern.assertPositive(quantidade, "A quantidade deve ser maior que zero.");

        this.produtoId = produtoId;
        this.skuSnapshot = skuSnapshot;
        this.nomeSnapshot = nomeSnapshot;
        this.precoUnitarioSnapshot = precoUnitarioSnapshot;
        this.quantidade = quantidade;
    }

    public BigDecimal calcularSubtotal() {
        return this.precoUnitarioSnapshot.multiply(BigDecimal.valueOf(this.quantidade));
    }

    public UUID getProdutoId() { return produtoId; }
    public String getSkuSnapshot() { return skuSnapshot; }
    public String getNomeSnapshot() { return nomeSnapshot; }
    public BigDecimal getPrecoUnitarioSnapshot() { return precoUnitarioSnapshot; }
    public int getQuantidade() { return quantidade; }

}
