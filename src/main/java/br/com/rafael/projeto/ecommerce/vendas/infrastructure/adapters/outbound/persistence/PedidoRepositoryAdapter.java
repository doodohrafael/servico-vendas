package br.com.rafael.projeto.ecommerce.vendas.infrastructure.adapters.outbound.persistence;

import br.com.rafael.projeto.ecommerce.vendas.application.ports.out.PedidoRepositoryPort;
import br.com.rafael.projeto.ecommerce.vendas.domain.model.Pedido;
import br.com.rafael.projeto.ecommerce.vendas.infrastructure.adapters.outbound.persistence.entity.PedidoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PedidoRepositoryAdapter implements PedidoRepositoryPort {

    private final SpringDataPedidoRepository springDataRepository;

    public PedidoRepositoryAdapter(SpringDataPedidoRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        PedidoEntity entity = PedidoEntity.fromDomain(pedido);
        PedidoEntity entitySalva = springDataRepository.save(entity);
        return entitySalva.toDomain();
    }

    @Override
    public Optional<Pedido> buscarPorId(UUID id) {
        return springDataRepository.findById(id)
                .map(PedidoEntity::toDomain);
    }

    @Override
    public List<Pedido> buscarPorCidade(String cidade) {
        List<PedidoEntity> entidades = springDataRepository.findByEnderecoEntregaCidade(cidade);
        return entidades.stream()
                .map(PedidoEntity::toDomain)
                .toList();
    }
}