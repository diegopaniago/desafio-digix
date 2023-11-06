dependencies=docker
$(foreach deps,$(dependencies), \
    $(if $(shell command -v $(deps)),$(info Found $(deps)),$(error Not found [ $(deps) ] in PATH)))

test: upstart
	./mvnw test

upstart:
	docker compose down && docker compose up -d

build: upstart
	./mvnw package

run: upstart
	./mvnw -f cadastro/pom.xml spring-boot:run | ./mvnw -f selecao/pom.xml spring-boot:run