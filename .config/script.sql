CREATE TABLE cliente (
    id SMALLINT PRIMARY KEY,
    nome VARCHAR(30),
    limite INTEGER not null,
    saldo INTEGER not null default 0
);

CREATE TABLE transacao (
    id SMALLINT PRIMARY KEY,
    cliente_id SMALLINT,
    valor INTEGER,
    tipo CHAR(1),
    descricao varchar(10),
    realizada_em timestamp(6)
);

INSERT INTO cliente (id, nome, limite, saldo)
VALUES
    (1, 'o barato sai caro', 1000 * 100, 0),
    (2, 'zan corp ltda', 800 * 100, 0),
    (3, 'les cruders', 10000 * 100, 0),
    (4, 'padaria joia de cocaia', 100000 * 100, 0),
    (5, 'kid mais', 5000 * 100, 0);