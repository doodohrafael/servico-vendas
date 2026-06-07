CREATE TABLE pedidos (
     id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
     cliente_id VARCHAR(255) NOT NULL,
     valor_total DECIMAL(19, 2) NOT NULL,
     status VARCHAR(50) NOT NULL,
     data_criacao TIMESTAMP NOT NULL,
     ativo BOOLEAN NOT NULL DEFAULT TRUE
);