PROJECT_NAME=servico-vendas
# Extrai a versão do pom.xml
VERSION=$(shell ./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout)
# Extrai o hash curto do git
HASH=$(shell git rev-parse --short HEAD)
# Concatena os dois
FULL_TAG=$(VERSION)-$(HASH)

build:
	./mvnw clean package
	@echo "--- Criando imagem com tag única: $(PROJECT_NAME):$(FULL_TAG) ---"
	docker build -t $(PROJECT_NAME):$(FULL_TAG) .
	@echo "--- Atualizando arquivo .env para o Docker Compose ---"
	@echo "IMAGE_TAG=$(FULL_TAG)" > .env

up:
	docker compose up -d

deploy: build up