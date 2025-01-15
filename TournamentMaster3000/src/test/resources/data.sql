INSERT INTO joueur (id, nom, prenom, numero) VALUES (1, 'Doe', 'John', 1);
INSERT INTO joueur (id, nom, prenom, numero) VALUES (2, 'Doe', 'Jane', 2);
INSERT INTO joueur (id, nom, prenom, numero) VALUES (3, 'Doe', 'Jack', 3);
INSERT INTO joueur (id, nom, prenom, numero) VALUES (4, 'Doe', 'Jill', 4);
INSERT INTO joueur (id, nom, prenom, numero) VALUES (5, 'Doe', 'Jim', 5);
INSERT INTO joueur (id, nom, prenom, numero) VALUES (6, 'Doe', 'Jenny', 6);

INSERT INTO equipe (id, nom) VALUES (1, 'Equipe 1');
INSERT INTO equipe (id, nom) VALUES (2, 'Equipe 2');

INSERT INTO equipe_joueurs (equipe_id, joueurs_id) VALUES (1, 1);
INSERT INTO equipe_joueurs (equipe_id, joueurs_id) VALUES (1, 2);
INSERT INTO equipe_joueurs (equipe_id, joueurs_id) VALUES (1, 3);
INSERT INTO equipe_joueurs (equipe_id, joueurs_id) VALUES (2, 4);
INSERT INTO equipe_joueurs (equipe_id, joueurs_id) VALUES (2, 5);
INSERT INTO equipe_joueurs (equipe_id, joueurs_id) VALUES (2, 6);

INSERT INTO round (id, equipea_id, equipeb_id, scorea, scoreb, round_number) VALUES (1, 1, 2, 21, 14, 1);
INSERT INTO round (id, equipea_id, equipeb_id, scorea, scoreb, round_number) VALUES (2, 1, 2, 19, 21, 2);
INSERT INTO round (id, equipea_id, equipeb_id, scorea, scoreb, round_number) VALUES (3, 1, 2, 21, 17, 3);
INSERT INTO round (id, equipea_id, equipeb_id, scorea, scoreb, round_number) VALUES (4, 1, 2, 2, 1, 4);
INSERT INTO round (id, equipea_id, equipeb_id, scorea, scoreb, round_number) VALUES (5, 1, 2, 1, 2, 5);
INSERT INTO round (id, equipea_id, equipeb_id, scorea, scoreb, round_number) VALUES (6, 1, 2, 2, 1, 6);

INSERT INTO `match` (id, equipea_id, equipeb_id, status) VALUES (1, 1, 2, 2);
INSERT INTO `match` (id, equipea_id, equipeb_id, status) VALUES (2, 1, 2, 2);

INSERT INTO match_rounds (match_id, rounds_id) VALUES (1, 1);
INSERT INTO match_rounds (match_id, rounds_id) VALUES (1, 2);
INSERT INTO match_rounds (match_id, rounds_id) VALUES (1, 3);
INSERT INTO match_rounds (match_id, rounds_id) VALUES (2, 4);
INSERT INTO match_rounds (match_id, rounds_id) VALUES (2, 5);
INSERT INTO match_rounds (match_id, rounds_id) VALUES (2, 6);

INSERT INTO resultat (id, match_id) VALUES (1, 1);
INSERT INTO resultat (id, match_id) VALUES (2, 2);