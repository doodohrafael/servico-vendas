package br.com.rafael.projeto.ecommerce.vendas.infrastructure.adapters.inbound.rest;

import br.com.rafael.projeto.ecommerce.vendas.application.ports.usecases.CriarPedidoUseCase;
import br.com.rafael.projeto.ecommerce.vendas.application.ports.usecases.FaturarPedidoUseCase;
import br.com.rafael.projeto.ecommerce.vendas.application.ports.usecases.PagarPedidoUseCase;
import br.com.rafael.projeto.ecommerce.vendas.domain.model.Pagamento;
import br.com.rafael.projeto.ecommerce.vendas.domain.model.Pedido;
import br.com.rafael.projeto.ecommerce.vendas.infrastructure.adapters.inbound.rest.dto.CriarPedidoRequest;
import br.com.rafael.projeto.ecommerce.vendas.infrastructure.adapters.inbound.rest.dto.PagamentoRequest;
import br.com.rafael.projeto.ecommerce.vendas.infrastructure.adapters.inbound.rest.dto.PedidoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    private final CriarPedidoUseCase criarPedidoUseCase;
    private final PagarPedidoUseCase pagarPedidoUseCase;
    private final FaturarPedidoUseCase faturarPedidoUseCase;

    public PedidoController(CriarPedidoUseCase criarPedidoUseCase,
                            PagarPedidoUseCase pagarPedidoUseCase,
                            FaturarPedidoUseCase faturarPedidoUseCase) {
        this.criarPedidoUseCase = criarPedidoUseCase;
        this.pagarPedidoUseCase = pagarPedidoUseCase;
        this.faturarPedidoUseCase = faturarPedidoUseCase;
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> criarPedido(@RequestBody CriarPedidoRequest request) {
        var pedidoCriado = criarPedidoUseCase.executar(
                request.clienteId(), request.extrairEndereco(), request.extrairItens()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(PedidoResponse.fromDomain(pedidoCriado));
    }

    @PostMapping("/{id}/pagar")
    public ResponseEntity<PedidoResponse> pagarPedido(@PathVariable UUID id, @RequestBody PagamentoRequest request) {
        Pagamento pagamento = new Pagamento(request.transacaoGatewayId(), request.forma(), request.valorPago());
        Pedido pedidoPago = pagarPedidoUseCase.executar(id, pagamento);
        return ResponseEntity.ok(PedidoResponse.fromDomain(pedidoPago));
    }

    @PostMapping("/{id}/faturar")
    public ResponseEntity<PedidoResponse> faturarPedido(@PathVariable UUID id) {
        Pedido pedidoFaturado = faturarPedidoUseCase.executar(id);
        return ResponseEntity.ok(PedidoResponse.fromDomain(pedidoFaturado));
    }

}