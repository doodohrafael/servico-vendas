CREATE TABLE item_pedido (
      id BIGSERIAL PRIMARY KEY,
      pedido_id UUID NOT NULL,
      produto_id UUID NOT NULL,
      sku_snapshot VARCHAR(50) NOT NULL,
      nome_snapshot VARCHAR(150) NOT NULL,
      preco_unitario_snapshot NUMERIC(17, 2) NOT NULL,
      quantidade INT NOT NULL,
      ativo BOOLEAN NOT NULL DEFAULT TRUE,
      CONSTRAINT fk_pedido_item FOREIGN KEY (pedido_id) REFERENCES pedido(id) ON DELETE CASCADE
);

CREATE INDEX idx_item_pedido_produto_id ON item_pedido(produto_id);