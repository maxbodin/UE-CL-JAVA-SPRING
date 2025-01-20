-- joueur
CREATE TABLE joueur
(
    id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom    VARCHAR(255) NULL,
    prenom VARCHAR(255) NULL,
    numero INT          NOT NULL
);

-- equipe
CREATE TABLE equipe
(
    id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NULL
);

CREATE TABLE equipe_joueurs
(
    equipe_id  BIGINT NOT NULL,
    joueurs_id BIGINT NOT NULL
);

ALTER TABLE equipe_joueurs
    ADD CONSTRAINT uc_equipe_joueurs_joueurs UNIQUE (joueurs_id);

ALTER TABLE equipe_joueurs
    ADD CONSTRAINT fk_equjou_on_equipe FOREIGN KEY (equipe_id) REFERENCES equipe (id) ON DELETE CASCADE;

ALTER TABLE equipe_joueurs
    ADD CONSTRAINT fk_equjou_on_joueur FOREIGN KEY (joueurs_id) REFERENCES joueur (id) ON DELETE CASCADE;

-- round
CREATE TABLE round
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    equipea_id   BIGINT NULL,
    equipeb_id   BIGINT NULL,
    scorea       INT    NOT NULL,
    scoreb       INT    NOT NULL,
    round_number INT    NOT NULL
);

ALTER TABLE round
    ADD CONSTRAINT FK_ROUND_ON_EQUIPEA FOREIGN KEY (equipea_id) REFERENCES equipe (id) ON DELETE CASCADE;

ALTER TABLE round
    ADD CONSTRAINT FK_ROUND_ON_EQUIPEB FOREIGN KEY (equipeb_id) REFERENCES equipe (id) ON DELETE CASCADE;

-- match
CREATE TABLE `match`
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    equipea_id BIGINT   NULL,
    equipeb_id BIGINT   NULL,
    status     SMALLINT NULL
);

CREATE TABLE match_rounds
(
    match_id  BIGINT NOT NULL,
    rounds_id BIGINT NOT NULL
);

ALTER TABLE match_rounds
    ADD CONSTRAINT uc_match_rounds_rounds UNIQUE (rounds_id);

ALTER TABLE `match`
    ADD CONSTRAINT FK_MATCH_ON_EQUIPEA FOREIGN KEY (equipea_id) REFERENCES equipe (id) ON DELETE CASCADE;

ALTER TABLE `match`
    ADD CONSTRAINT FK_MATCH_ON_EQUIPEB FOREIGN KEY (equipeb_id) REFERENCES equipe (id) ON DELETE CASCADE;

ALTER TABLE match_rounds
    ADD CONSTRAINT fk_matrou_on_match FOREIGN KEY (match_id) REFERENCES `match` (id) ON DELETE CASCADE;

ALTER TABLE match_rounds
    ADD CONSTRAINT fk_matrou_on_round FOREIGN KEY (rounds_id) REFERENCES round (id) ON DELETE CASCADE;

-- resultat

CREATE TABLE resultat
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    match_id BIGINT NULL
);

ALTER TABLE resultat
    ADD CONSTRAINT FK_RESULTAT_ON_MATCH FOREIGN KEY (match_id) REFERENCES `match` (id) ON DELETE CASCADE;