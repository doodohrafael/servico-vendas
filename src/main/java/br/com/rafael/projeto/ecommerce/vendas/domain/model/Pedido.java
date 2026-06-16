package br.com.rafael.projeto.ecommerce.vendas.domain.model;

import br.com.rafael.projeto.ecommerce.vendas.domain.shared.AssertionConcern;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Pedido {

    private UUID id;
    private UUID clienteId;
    private StatusPedido status;
    private LocalDateTime dataCriacao;
    private BigDecimal valorTotal;

    private EnderecoEntrega enderecoEntrega;
    private List<ItemPedido> itens;

    private Pagamento pagamento;

    public Pedido(UUID clienteId, EnderecoEntrega enderecoEntrega) {
        AssertionConcern.assertNotNull(clienteId, "O ID do cliente é obrigatório");
        AssertionConcern.assertNotNull(enderecoEntrega, "O endereço de entrega é obrigatório");

        this.id = UUID.randomUUID();
        this.clienteId = clienteId;
        this.status = StatusPedido.CRIADO;
        this.enderecoEntrega = enderecoEntrega;
        this.dataCriacao = LocalDateTime.now();
        this.valorTotal = BigDecimal.ZERO;
        this.itens = new ArrayList<>();
    }

    private Pedido(UUID id, UUID clienteId, StatusPedido status, LocalDateTime dataCriacao,
                   BigDecimal valorTotal, EnderecoEntrega enderecoEntrega, List<ItemPedido> itens) {
        this.id = id;
        this.clienteId = clienteId;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.valorTotal = valorTotal;
        this.enderecoEntrega = enderecoEntrega;
        this.itens = new ArrayList<>(itens);
    }

    public static Pedido restaurar(UUID id, UUID clienteId, StatusPedido status,
                                   LocalDateTime dataCriacao, BigDecimal valorTotal,
                                   EnderecoEntrega enderecoEntrega, List<ItemPedido> itens) {
        return new Pedido(id, clienteId, status, dataCriacao, valorTotal, enderecoEntrega, itens);
    }

    public void adicionarItem(ItemPedido item) {
        AssertionConcern.assertNotNull(item, "Deve ter no minimo 1 item.");
        this.itens.add(item);
        this.calcularTotal();
    }

    public void calcularTotal() {
        this.valorTotal = this.itens.stream()
                .map(ItemPedido::calcularSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void pagar(Pagamento pagamento) {
        AssertionConcern.assertNotNull(pagamento, "Deve ter um pagamento.");
        if (this.status != StatusPedido.CRIADO) {
            throw new IllegalStateException("Apenas pedidos no status CRIADO podem ser pagos.");
        }

        if (pagamento.valorPago().compareTo(this.valorTotal) < 0) {
            throw new IllegalArgumentException("O valor do pagamento é menor que o total do pedido.");
        }

        this.pagamento = pagamento;
        this.status = StatusPedido.PAGO;
    }

    public void faturar() {
        if (this.status != StatusPedido.PAGO) {
            throw new IllegalStateException("O pedido precisa estar PAGO para ser FATURADO.");
        }
        this.status = StatusPedido.FATURADO;
    }

    public UUID getId() { return id; }
    public UUID getClienteId() { return clienteId; }
    public EnderecoEntrega getEnderecoEntrega() { return enderecoEntrega; }
    public StatusPedido getStatus() { return status; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public List<ItemPedido> getItens() { return Collections.unmodifiableList(itens); }

}
