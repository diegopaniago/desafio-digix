dependencies=docker
$(foreach deps,$(dependencies), \
    $(if $(shell command -v $(deps)),$(info Found $(deps)),$(error Not found [ $(deps) ] in PATH)))

build:
	./mvnw clean package -DskipTests

test:
	docker compose down && docker compose up -d && ./mvnw clean test

run: build
	docker compose down && docker compose up -d && ./mvnw -f cadastro/pom.xml spring-boot:run