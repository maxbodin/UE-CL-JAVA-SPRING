-- joueur
CREATE TABLE joueur
(
    id     BIGINT       NOT NULL,
    nom    VARCHAR(255) NULL,
    prenom VARCHAR(255) NULL,
    numero INT          NOT NULL,
    CONSTRAINT pk_joueur PRIMARY KEY (id)
);

-- equipe
CREATE TABLE equipe
(
    id  BIGINT       NOT NULL,
    nom VARCHAR(255) NULL,
    CONSTRAINT pk_equipe PRIMARY KEY (id)
);

CREATE TABLE equipe_joueurs
(
    equipe_id  BIGINT NOT NULL,
    joueurs_id BIGINT NOT NULL
);

ALTER TABLE equipe_joueurs
    ADD CONSTRAINT uc_equipe_joueurs_joueurs UNIQUE (joueurs_id);

ALTER TABLE equipe_joueurs
    ADD CONSTRAINT fk_equjou_on_equipe FOREIGN KEY (equipe_id) REFERENCES equipe (id);

ALTER TABLE equipe_joueurs
    ADD CONSTRAINT fk_equjou_on_joueur FOREIGN KEY (joueurs_id) REFERENCES joueur (id);

-- round
CREATE TABLE round
(
    id           BIGINT NOT NULL,
    equipea_id   BIGINT NULL,
    equipeb_id   BIGINT NULL,
    scorea       INT    NOT NULL,
    scoreb       INT    NOT NULL,
    round_number INT    NOT NULL,
    CONSTRAINT pk_round PRIMARY KEY (id)
);

ALTER TABLE round
    ADD CONSTRAINT FK_ROUND_ON_EQUIPEA FOREIGN KEY (equipea_id) REFERENCES equipe (id);

ALTER TABLE round
    ADD CONSTRAINT FK_ROUND_ON_EQUIPEB FOREIGN KEY (equipeb_id) REFERENCES equipe (id);

-- match
CREATE TABLE `match`
(
    id         BIGINT   NOT NULL,
    equipea_id BIGINT   NULL,
    equipeb_id BIGINT   NULL,
    status     SMALLINT NULL,
    CONSTRAINT pk_match PRIMARY KEY (id)
);

CREATE TABLE match_rounds
(
    match_id  BIGINT NOT NULL,
    rounds_id BIGINT NOT NULL
);

ALTER TABLE match_rounds
    ADD CONSTRAINT uc_match_rounds_rounds UNIQUE (rounds_id);

ALTER TABLE `match`
    ADD CONSTRAINT FK_MATCH_ON_EQUIPEA FOREIGN KEY (equipea_id) REFERENCES equipe (id);

ALTER TABLE `match`
    ADD CONSTRAINT FK_MATCH_ON_EQUIPEB FOREIGN KEY (equipeb_id) REFERENCES equipe (id);

ALTER TABLE match_rounds
    ADD CONSTRAINT fk_matrou_on_match FOREIGN KEY (match_id) REFERENCES `match` (id);

ALTER TABLE match_rounds
    ADD CONSTRAINT fk_matrou_on_round FOREIGN KEY (rounds_id) REFERENCES round (id);

-- resultat

CREATE TABLE resultat
(
    id       BIGINT NOT NULL,
    match_id BIGINT NULL,
    CONSTRAINT pk_resultat PRIMARY KEY (id)
);

ALTER TABLE resultat
    ADD CONSTRAINT FK_RESULTAT_ON_MATCH FOREIGN KEY (match_id) REFERENCES `match` (id);