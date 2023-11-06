# Desafio Digix [![CI](https://github.com/diegopaniago/desafio-digix/actions/workflows/ci.yml/badge.svg)](https://github.com/diegopaniago/desafio-digix/actions/workflows/ci.yml)
### Seleção de famílias aptas a ganharem uma casa popular

## Requisitos
Esse projeto usa docker e Java. <br/>
Tenha instalado:<br/>
- Docker(com docker compose plugin)
- Java JDK 17

## Como executar
Execute:
1. Baixar as dependencias do docker e do maven
```
make
```
2. Testar
```
make test
```
3. Executar
```
make run
```
Esse projeto executa automaticamente um seeder com 100 inscritos totalmente aleatórios. Caso queria desativar o seeder mude a flag no arquivo de [properties](./cadastro/src/main/resources/application.properties) do modulo de cadastro.

## Arquitetura
Esse projeto possui uma arquitetura distribuida baseada em 2 contextos.
- Contexto de cadastro: Usando uma arquitetura hexagonal dada a diversidade de protocolos do projeto(http, jdbc e amqp) é focado em manter os dados de cadastro dos incritos.
- Contexto de selecão: Usando uma arquitetura hexagonal dada a diversidade de protocolos do projeto(http, jdbc e amqp) é focado em manter os dados pré-processados sempre que um novo inscrito é cadastrado no modulo de cadastro. O pré-processamento da seleção é feito de maneiro assincrona usando RabbitMq.
- [Diagrama da arquitetura](https://drive.google.com/file/d/13PCPL_RVByoIiZtCk5v9Glv2VRQSm7GU/view?usp=sharing)


## Próximos passos
- [ ] Adicionar monitoria(APM) com Sentry
- [ ] Adicionar Logs com Elactic e Kibana
- [ ] Implementar autenticação com JWT
- [ ] Implementar auditoria