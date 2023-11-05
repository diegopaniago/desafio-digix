create table criterio_selecionado (
    pontos_obtidos integer,
    id varchar(255) not null,
    nome varchar(255),
    primary key (id)
);

create table selecao (
    id varchar(255) not null,
    participante_id varchar(255),
    primary key (id)
);

create table selecao_criterios_selecionados (
    criterios_selecionados_id varchar(255) not null unique,
    selecao_id varchar(255) not null
);

alter table if exists selecao_criterios_selecionados 
    add constraint FKj5x2ac8s8tay8nbqjg0auhpci 
    foreign key (criterios_selecionados_id) 
    references criterio_selecionado;

alter table if exists selecao_criterios_selecionados 
    add constraint FKrojcpj1rtpidkof7x0220k0st 
    foreign key (selecao_id) 
    references selecao;
