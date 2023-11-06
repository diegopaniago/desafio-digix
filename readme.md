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
```bash
make
```
2. Testar
```bash
make test
```
3. Executar
```bash
make run
```
Esse projeto executa automaticamente um seeder com 100 inscritos totalmente aleatórios. Caso queria desativar o seeder mude a flag `seeder` no arquivo de [properties](./cadastro/src/main/resources/application.properties) do modulo de cadastro.

## Api
Para testar as APIs é possível usar o Swagger.
- [Cadastro](http://localhost:8085/swagger-ui/index.html)
- [Selecao](http://localhost:8086/swagger-ui/index.html)<br/>
Payload base para facilitar o uso do cadastro:
```json
{
  "id": "",
  "titular": {
    "nome": "Diego Paniago",
    "cpf": "03464998100",
    "dataDeNascimento": "1992-04-28",
    "renda": 50000
  },
  "familia": [
    {
      "nome": "Caue Paniago",
      "cpf": "03464998100",
      "dataDeNascimento": "2023-11-05",
      "renda": 0
    }
  ]
}
```

## Acesso as dependencias
- Postgres jdbc:postgresql://localhost:5432/postgres | usuário e senha: postgres
- RabbitMq http://localhost:15672/ | usuário e senha: guest

## Arquitetura
Esse projeto possui uma arquitetura distribuida baseada em 2 contextos.
- Contexto de cadastro: Usando uma arquitetura hexagonal dada a diversidade de protocolos do projeto(http, jdbc e amqp) é focado em manter os dados de cadastro dos incritos.
- Contexto de selecão: Usando uma arquitetura hexagonal dada a diversidade de protocolos do projeto(http, jdbc e amqp) é focado em manter os dados pré-processados sempre que um novo inscrito é cadastrado no modulo de cadastro. O pré-processamento da seleção é feito de maneira assincrona usando RabbitMq.
- [Diagrama da arquitetura](https://drive.google.com/file/d/13PCPL_RVByoIiZtCk5v9Glv2VRQSm7GU/view?usp=sharing)

## Deploy
- Para realizar o deploy basta criar uma [nova release](https://github.com/diegopaniago/desafio-digix/releases/new).
- Respeite o versionamento de tags Ex.: v0.0.0

## Próximos passos
- [ ] Adicionar monitoria(APM) com Sentry
- [ ] Adicionar Logs com Elastic e Kibana
- [ ] Implementar autenticação com JWT
- [ ] Implementar auditoria