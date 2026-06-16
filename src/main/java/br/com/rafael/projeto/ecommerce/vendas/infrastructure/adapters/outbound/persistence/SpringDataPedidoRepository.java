package br.com.rafael.projeto.ecommerce.vendas.infrastructure.adapters.outbound.persistence;

import br.com.rafael.projeto.ecommerce.vendas.infrastructure.adapters.outbound.persistence.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpringDataPedidoRepository extends JpaRepository<PedidoEntity, UUID> {
    
    List<PedidoEntity> findByEnderecoEntregaCidade(String cidade);
    
}