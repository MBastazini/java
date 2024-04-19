CREATE TABLE animal (
    id SERIAL PRIMARY KEY,
    genero varchar(255) NOT NULL,
    especie varchar(255) NOT NULL,
    raca varchar(255) NOT NULL,
    disponivel boolean NOT NULL,
    informacoesAdicionais varchar(255)
);