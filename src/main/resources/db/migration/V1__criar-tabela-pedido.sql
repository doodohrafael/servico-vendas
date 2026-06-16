CREATE TABLE pedido (
     id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
     cliente_id UUID NOT NULL,
     status VARCHAR(30) NOT NULL,
     valor_total DECIMAL(19, 2) NOT NULL,
     data_criacao TIMESTAMP NOT NULL,
     ativo BOOLEAN NOT NULL DEFAULT TRUE,

     endereco_cep VARCHAR(8) NOT NULL,
     endereco_logradouro VARCHAR(150) NOT NULL,
     endereco_numero VARCHAR(20) NOT NULL,
     endereco_cidade VARCHAR(100) NOT NULL,
     endereco_estado VARCHAR(2) NOT NULL
);

CREATE INDEX idx_pedido_endereco_cidade ON pedido(endereco_cidade);
CREATE INDEX idx_pedido_endereco_estado ON pedido(endereco_estado);
CREATE INDEX idx_pedido_cliente_id ON pedido(cliente_id);