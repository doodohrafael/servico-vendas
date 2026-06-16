package br.com.rafael.projeto.ecommerce.vendas.infrastructure.adapters.outbound.persistence.entity;

import br.com.rafael.projeto.ecommerce.vendas.domain.model.EnderecoEntrega;
import br.com.rafael.projeto.ecommerce.vendas.domain.model.StatusPedido;
import br.com.rafael.projeto.ecommerce.vendas.domain.model.Pedido;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedido")
public class PedidoEntity {

    @Id
    private UUID id;

    @Column(name = "cliente_id", nullable = false)
    private UUID clienteId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Embedded
    private EnderecoEmbeddable enderecoEntrega;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    private List<ItemPedidoEntity> itens = new ArrayList<>();

    protected PedidoEntity() {}

    public static PedidoEntity fromDomain(Pedido pedido) {
        PedidoEntity entity = new PedidoEntity();
        entity.id = pedido.getId();
        entity.clienteId = pedido.getClienteId();
        entity.status = pedido.getStatus();
        entity.dataCriacao = pedido.getDataCriacao();
        entity.valorTotal = pedido.getValorTotal();
        
        EnderecoEntrega endDominio = pedido.getEnderecoEntrega();
        entity.enderecoEntrega = new EnderecoEmbeddable(
                endDominio.cep(), endDominio.logradouro(), endDominio.numero(), 
                endDominio.cidade(), endDominio.estado()
        );
        
        entity.itens = pedido.getItens().stream()
                .map(ItemPedidoEntity::fromDomain)
                .toList();
        return entity;
    }

    public Pedido toDomain() {
        EnderecoEntrega enderecoDominio = new EnderecoEntrega(
                this.enderecoEntrega.getCep(),
                this.enderecoEntrega.getLogradouro(),
                this.enderecoEntrega.getNumero(),
                this.enderecoEntrega.getCidade(),
                this.enderecoEntrega.getEstado()
        );

        return Pedido.restaurar(
                this.id,
                this.clienteId,
                this.status,
                this.dataCriacao,
                this.valorTotal,
                enderecoDominio,
                this.itens.stream().map(ItemPedidoEntity::toDomain).toList()
        );
    }
}