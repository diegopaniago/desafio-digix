create table participante (
    cadastrado_em timestamp(6),
    id varchar(255) not null,
    titular_id varchar(255) unique,
    primary key (id)
);

create table participante_familia (
    familia_id varchar(255) not null unique,
    participante_id varchar(255) not null
);

create table pessoa (
    data_de_nascimento date,
    renda numeric(38,2),
    cpf varchar(255),
    id varchar(255) not null,
    nome varchar(255),
    primary key (id)
);

alter table if exists participante 
    add constraint FKo4rwgmh094859t4q2n2bphloi 
    foreign key (titular_id) 
    references pessoa;

alter table if exists participante_familia 
    add constraint FKkltgu25q3cgorywfgm222acnc 
    foreign key (familia_id) 
    references pessoa;

alter table if exists participante_familia 
    add constraint FKbai5y8xwejcm02w0gmm137ca8 
    foreign key (participante_id) 
    references participante;
