
DROP DATABASE IF EXISTS synch1;
CREATE DATABASE synch1;

USE synch1;

CREATE TABLE Abteilung (
 id INT NOT NULL AUTO_INCREMENT,
 name VARCHAR(50),
 primary key (id)
)engine = innodb;

CREATE TABLE Angestellter (
 id INT NOT NULL AUTO_INCREMENT,
 name VARCHAR(200),
 gehalt DECIMAL(10),
 anzjahre INT,
 wohnort VARCHAR(100),
 abteilung INT,
 primary key (id),
 FOREIGN KEY (abteilung) REFERENCES Abteilung(id)
)engine = innodb;

INSERT INTO Abteilung VALUES(1,'IT');
INSERT INTO Abteilung VALUES(2,'MI');
INSERT INTO Abteilung VALUES(3,'BioMed');
INSERT INTO Abteilung VALUES(4,'CH');
INSERT INTO Abteilung VALUES(5,'PH');
INSERT INTO Abteilung VALUES(6,'ET');
INSERT INTO Abteilung VALUES(7,'Mecha');
INSERT INTO Abteilung VALUES(8,'Ind');
INSERT INTO Abteilung VALUES(9,'WI');
INSERT INTO Abteilung VALUES(10,'HR');

INSERT INTO Angestellter VALUES(1,'Wolfgang Mair',1000,2,'Pumperlgasse 18',1);
INSERT INTO Angestellter VALUES(2,'Christian Janeczek',2000,1,'Burggasse 11',2);
INSERT INTO Angestellter VALUES(3,'Stefan Stokic',4000,4,'Staugasse 28',4);
INSERT INTO Angestellter VALUES(4,'Dominik Kodras',2200,5,'Dunkelgasse 47',3);
INSERT INTO Angestellter VALUES(5,'Daniel Langer',1200,3,'Johannesbeergasse 4',5);
INSERT INTO Angestellter VALUES(6,'Sebastian Mair',3100,3,'Pumperlgasse 18',7);
INSERT INTO Angestellter VALUES(7,'Helmuth Brunner',4100,3,'Ringgasse 6',6);
INSERT INTO Angestellter VALUES(8,'Christopher Sturz',900,1,'Springasse 16',8);
INSERT INTO Angestellter VALUES(9,'Will Smith',1400,7,'Kalachgasse 18',9);
INSERT INTO Angestellter VALUES(10,'Adrian Bergler',700,12,'Steigungsgasse 22',10);