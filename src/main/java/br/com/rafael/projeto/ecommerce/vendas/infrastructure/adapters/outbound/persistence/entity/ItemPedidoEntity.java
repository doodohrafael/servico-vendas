package br.com.rafael.projeto.ecommerce.vendas.infrastructure.adapters.outbound.persistence.entity;

import br.com.rafael.projeto.ecommerce.vendas.domain.model.ItemPedido;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "item_pedido")
public class ItemPedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(name = "produto_id", nullable = false)
    private UUID produtoId;

    @Column(name = "sku_snapshot", nullable = false)
    private String skuSnapshot;

    @Column(name = "nome_snapshot", nullable = false)
    private String nomeSnapshot;

    @Column(name = "preco_unitario_snapshot", nullable = false)
    private BigDecimal precoUnitarioSnapshot;

    @Column(nullable = false)
    private int quantidade;

    protected ItemPedidoEntity() {}

    public static ItemPedidoEntity fromDomain(ItemPedido item) {
        ItemPedidoEntity entity = new ItemPedidoEntity();
        entity.produtoId = item.getProdutoId();
        entity.skuSnapshot = item.getSkuSnapshot();
        entity.nomeSnapshot = item.getNomeSnapshot();
        entity.precoUnitarioSnapshot = item.getPrecoUnitarioSnapshot();
        entity.quantidade = item.getQuantidade();
        return entity;
    }

    public ItemPedido toDomain() {
        return new ItemPedido(
            this.produtoId, this.skuSnapshot, this.nomeSnapshot, 
            this.precoUnitarioSnapshot, this.quantidade
        );
    }
}