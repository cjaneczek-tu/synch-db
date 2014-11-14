
\c template1
DROP DATABASE IF EXISTS synchdb;
CREATE DATABASE synchdb;
\c synchdb

CREATE TABLE Person (
 id SERIAL,
 vName VARCHAR(100) NOT NULL,
 nName VARCHAR(100) NOT NULL,
 wohnort VARCHAR(100),
 version INT NOT NULL,
 primary key (id)
);

CREATE TABLE Mitarbeiter (
 id SERIAL,
 mongehalt FLOAT(10),
 idPerson INT,
 version INT NOT NULL,
 primary key (id),
 FOREIGN KEY (idPerson) REFERENCES Person(id)
);

INSERT INTO Person VALUES(1,'Wolfgang','Mair','Pumperlgasse 18', 1);
INSERT INTO Person VALUES(2,'Christian','Janeczek','Burggasse 11', 1);
INSERT INTO Person VALUES(3,'Stefan','Stokic','Staugasse 28', 1);
INSERT INTO Person VALUES(4,'Dominik','Kodras','Dunkelgasse 47', 1);
INSERT INTO Person VALUES(5,'Daniel','Langer','Johannesbeergasse 4', 1);
INSERT INTO Person VALUES(6,'Sebastian','Mair','Pumperlgasse 18', 1);
INSERT INTO Person VALUES(7,'Helmuth','Brunner','Ringgasse 6', 1);
INSERT INTO Person VALUES(8,'Christopher','Sturz','Springasse 16', 1);
INSERT INTO Person VALUES(9,'Will','Smith','Kalachgasse 18', 1);
INSERT INTO Person VALUES(10,'Adrian','Bergler','Steigungsgasse 22', 1);

INSERT INTO Mitarbeiter VALUES(1,1000,1,1);
INSERT INTO Mitarbeiter VALUES(2,2000,2,1);
INSERT INTO Mitarbeiter VALUES(3,4000,3,1);
INSERT INTO Mitarbeiter VALUES(4,2200,4,1);
INSERT INTO Mitarbeiter VALUES(5,1200,5,1);
INSERT INTO Mitarbeiter VALUES(6,3100,6,1);
INSERT INTO Mitarbeiter VALUES(7,4100,7,1);
INSERT INTO Mitarbeiter VALUES(8,900,8,1);
INSERT INTO Mitarbeiter VALUES(9,1400,9,1);
INSERT INTO Mitarbeiter VALUES(10,700,10,1);