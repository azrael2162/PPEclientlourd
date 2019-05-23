#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------
drop database if exists autoEcole;
create database autoEcole;
use autoEcole;

#------------------------------------------------------------
# Table: mois
#------------------------------------------------------------

CREATE TABLE mois(
        idm      Int  Auto_increment  NOT NULL ,
        annee    Int NOT NULL ,
        num_mois Int NOT NULL
	,CONSTRAINT mois_PK PRIMARY KEY (idm)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: modele
#------------------------------------------------------------

CREATE TABLE modele(
        idmo     Int  Auto_increment  NOT NULL ,
        nom      Varchar (50) NOT NULL ,
        cylindre Int NOT NULL
	,CONSTRAINT modele_PK PRIMARY KEY (idmo)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: voiture
#------------------------------------------------------------

CREATE TABLE voiture(
        idv             Int  Auto_increment  NOT NULL ,
        immatriculation Varchar (50) NOT NULL ,
        date_achat      Date NOT NULL ,
        nb_km           Float NOT NULL ,
        idmo            Int NOT NULL
	,CONSTRAINT voiture_PK PRIMARY KEY (idv)

	,CONSTRAINT voiture_modele_FK FOREIGN KEY (idmo) REFERENCES modele(idmo)
)ENGINE=InnoDB;






#------------------------------------------------------------
# Table: grp
#------------------------------------------------------------

CREATE TABLE grp(
        idgrp Int  Auto_increment  NOT NULL ,
        nom   Varchar (50) NOT NULL
	,CONSTRAINT grp_PK PRIMARY KEY (idgrp)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: user
#------------------------------------------------------------

CREATE TABLE user(
        idu        Int    NOT NULL ,
        nom        Varchar (50) NOT NULL ,
        prenom     Varchar (50) NOT NULL ,
        idgrp      Int NOT NULL
	,CONSTRAINT user_PK PRIMARY KEY (idu)

	,CONSTRAINT user_grp_FK FOREIGN KEY (idgrp) REFERENCES grp(idgrp)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: moniteur
#------------------------------------------------------------

CREATE TABLE moniteur(
        idu           Int Auto_increment NOT NULL ,
        date_embauche Date NOT NULL ,
        nom           Varchar (50) NOT NULL ,
        prenom        Varchar (50) NOT NULL ,
        datenaissa    Date NOT NULL ,
        mail          VARCHAR (200),
        passwd        VARCHAR (255),
        idgrp         Int NOT NULL
	,CONSTRAINT moniteur_PK PRIMARY KEY (idu)

	,CONSTRAINT moniteur_grp0_FK FOREIGN KEY (idgrp) REFERENCES grp(idgrp)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: moniteur
#------------------------------------------------------------

CREATE TABLE admin(
        idu           Int Auto_increment NOT NULL ,
        date_embauche Date NOT NULL ,
        nom           Varchar (50) NOT NULL ,
        prenom        Varchar (50) NOT NULL ,
        mail          VARCHAR (200),
        passwd        VARCHAR (255),
        imageProfil   Varchar (255),
	idgrp         Int NOT NULL
	,CONSTRAINT admin_PK PRIMARY KEY (idu)

	,CONSTRAINT admin_grp0_FK FOREIGN KEY (idgrp) REFERENCES grp(idgrp)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: client
#------------------------------------------------------------

CREATE TABLE client(
        idu             Int Auto_increment NOT NULL ,
        adresse         Varchar (50) NOT NULL ,
        code_zip        Char (5) NOT NULL ,
        datenaissa      Date NOT NULL ,
        tel             Varchar (50) NOT NULL ,
        nom             Varchar (50) NOT NULL ,
        prenom          Varchar (50) NOT NULL ,
        mail            VARCHAR (200),
        passwd          VARCHAR (255),
        RandToken       VARCHAR (255),
        valider         int (1),
        idu_moniteur    Int  ,
        idgrp           Int NOT NULL
	,CONSTRAINT client_PK PRIMARY KEY (idu)

	,CONSTRAINT client_moniteur0_FK FOREIGN KEY (idu_moniteur) REFERENCES moniteur(idu)
	,CONSTRAINT client_grp1_FK FOREIGN KEY (idgrp) REFERENCES grp(idgrp)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: lecon
#------------------------------------------------------------

CREATE TABLE lecon(
        idl        Int  Auto_increment  NOT NULL ,
        idv        Int NOT NULL ,
	,CONSTRAINT lecon_PK PRIMARY KEY (idl)

	,CONSTRAINT lecon_voiture_FK FOREIGN KEY (idv) REFERENCES voiture(idv)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: rouler
#------------------------------------------------------------

CREATE TABLE rouler(
        idv Int NOT NULL ,
        idm Int NOT NULL ,
        km  Float NOT NULL
	,CONSTRAINT rouler_PK PRIMARY KEY (idv,idm)

	,CONSTRAINT rouler_voiture_FK FOREIGN KEY (idv) REFERENCES voiture(idv)
	,CONSTRAINT rouler_mois0_FK FOREIGN KEY (idm) REFERENCES mois(idm)
)ENGINE=InnoDB;


Create table planning(
  iduc Int NOT NULL,
  idum Int NOT NULL,
  idl  Int NOT NULL,
  datehd DateTIME,
  datehf DateTIME,
  etat enum("valider","en cours","annuler"),
  primary key(iduc,idum,idl,datehd),
  FOREIGN KEY (iduc) REFERENCES client(idu),
  FOREIGN KEY (idum) REFERENCES moniteur(idu),
  FOREIGN KEY (idl) REFERENCES lecon(idl)
);




INSERT INTO `modele` (`idmo`, `nom`, `cylindre`)
 VALUES ('1', 'ssdsdsd', '234');

INSERT INTO `voiture` (`idv`, `immatriculation`, `date_achat`, `nb_km`, `idmo`)
VALUES
('1', 'AA-304-BB', '2019-01-01', '1000', '1'),
 ('2', 'AA-304-BB', '2019-01-01', '200000', '1'),
 ('3', 'AA-304-BB', '2019-01-01', '300000', '1'),
 ('4', 'AA-304-BB', '2019-01-01', '200000', '1');

DELIMITER //
CREATE TRIGGER checkcar
AFTER  INSERT  on voiture for each row
begin
if new.nb_km >= 200000
THEN
INSERT INTO `rouler` (`idv`, `idm`, `km`) VALUES (new.idv,1,new.nb_km);
END IF;
END //
DELIMITER ;


INSERT INTO `grp` (`idgrp`, `nom`) VALUES
('2', 'client'),
('1', 'admin'),
('3', 'moniteur');

INSERT INTO `moniteur` (`idu`, `date_embauche`, `nom`, `prenom`, `datenaissa`, `mail`, `passwd`, `idgrp`) VALUES
(NULL, '2016-03-07', 'Pinelli', 'Vincent', '1978-12-03', 'vincent.pinelli@wanadoo.fr', 'f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9', '2');

INSERT INTO `moniteur` (`idu`, `date_embauche`, `nom`, `prenom`, `datenaissa`, `mail`, `passwd`, `idgrp`) VALUES
(NULL, '2018-12-02', 'Albert', 'Nicolas', '1990-12-02', 'sdsdd@sdsd.fr', 'f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9', '2'),
(NULL, '2015-12-18', 'Fleure', 'marie', '1985-12-01', 'sdsdd@yahoo.fr', 'f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9', '2');

INSERT INTO `admin` (`idu`, `date_embauche`, `nom`, `prenom`, `mail`, `passwd`, `idgrp`) VALUES (NULL, '2018-12-18', 'test', 'test', 'masdsdjsd@sdsdsd.fr', MD5('sdsdsdsd'), '1');

INSERT INTO `client` (`idu`, `adresse`, `code_zip`, `datenaissa`, `tel`, `nom`, `prenom`, `mail`, `passwd`, `RandToken`, `valider`, `idu_moniteur`, `idgrp`) VALUES (NULL, '9 rue osef', '92400', '1992-09-05', '002232323', 'sdsdsd', 'sdsdsd', 'sdsdsdsd', SHA1('slipknot'), NULL, '1', NULL, '2');


INSERT INTO `lecon` (`idl`, `date_heure`, `idv`, `idu`, `idu_client`) VALUES (NULL, '2019-03-18', '2', '1', '1');

CREATE VIEW liste_moniteur_occupe AS
SELECT  moniteur.idu ,nom , prenom, date_heure
FROM  moniteur
INNER JOIN lecon
ON  moniteur.idu = lecon.idu ;

CREATE view liste_moniteur_libre AS
SELECT *
FROM moniteur
WHERE idu
NOT IN (select idu from lecon);
