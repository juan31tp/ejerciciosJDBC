DROP DATABASE IF EXISTS Horario;
CREATE DATABASE Horario;
USE Horario;

CREATE TABLE OfertaEducativa (
codOe char(3) PRIMARY KEY,
nombre varchar(70),
descripcion varchar(255),
tipo ENUM('CFGS','CFGM','FPB','ESO','BACH'),
fechaLey date);

CREATE TABLE Profesor(
codProf char(3) PRIMARY KEY,
nombre varchar(20),
apellidos varchar(40),
fechaAlta timestamp);


CREATE TABLE Curso(
codOe char(3),
codCurso char(2),
codTutor char(3) UNIQUE NOT NULL,
PRIMARY KEY(codOe, codCurso),
CONSTRAINT FK_codOe FOREIGN KEY (codOe)
REFERENCES OfertaEducativa(codOe) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FK_codTutor FOREIGN KEY (codTutor)
REFERENCES Profesor(codProf) ON DELETE RESTRICT ON UPDATE CASCADE);

CREATE TABLE TramoHorario(
codTramo char(2) PRIMARY KEY,
horaInicio TIME,
horaFin TIME,
dia ENUM('LUNES' , 'MARTES' , 'MIERCOLES' , 'JUEVES', 'VIERNES')
);

CREATE TABLE Asignatura(
codAsig varchar(6) PRIMARY KEY,
nombre varchar(80) NOT NULL,
horasSemanales tinyint unsigned,
horasTotales smallint unsigned
);

CREATE TABLE Reparto(
codOe char(3),
codCurso char(2),
codAsig VARCHAR(6),
codProf char(3),
PRIMARY KEY(codOe, codCurso, codAsig, codProf),
CONSTRAINT FK_CodOeYCurso FOREIGN KEY (codOe, codCurso)
REFERENCES Curso(codOe,codCurso) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FK_CodAsig FOREIGN KEY (codAsig)
REFERENCES Asignatura(codAsig) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FK_CodProf FOREIGN KEY (codProf)
REFERENCES Profesor(codProf) ON DELETE RESTRICT ON UPDATE CASCADE);

CREATE TABLE Horario(
codOe char(3),
codCurso char(2),
codAsig varchar(6),
codTramo char(2),
PRIMARY KEY(codOe, codCurso, codAsig, codTramo),
CONSTRAINT FK_CodOeCurso FOREIGN KEY (codOe, codCurso)
REFERENCES Curso(codOe,codCurso) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FK_CodAsignatura FOREIGN KEY (codAsig)
REFERENCES Asignatura(codAsig) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FK_CodTramo FOREIGN KEY (codTramo)
REFERENCES TramoHorario(codTramo) ON DELETE CASCADE ON UPDATE CASCADE);


INSERT INTO OfertaEducativa VALUES
("SMR","Grado Medio de Sistemas Microinformáticos y Redes","El CFGM SMR tiene una duración de 2000 horas
 repartidas entre dos cursos académicos incluyendo un trimestre de Formacion en centros de trabajo (FCT)
 en empresas del Sector",'CFGM','2009-07-07'),
("DAM","Grado Superior de Desarollo de Aplicaciones Multiplataforma","El CFGs DAM tiene una duración de 2000 horas repartidas entre dos cursos académicos incluyendo 400 horas de Formacion en centros de trabajo (FCT) en empresas del Sector",'CFGS','2011-06-16');

INSERT INTO Profesor VALUES
("AGL","Ana","Gutiérrez Lozano",'1999-09-01'),
("PJM","Pedro","Joya Máñez",'2000-09-01'),
("ARR","Alba","Rodrigez Ropero",'2020-09-01'),
("JGG","Javier","Graña González",'2011-09-01'),
("PBG","Pilar","Baena García",'2007-09-01'),
("MGD","María","Gallego Díaz",'2016-09-01'),
("VLH","Violeta","Lopez Herrera",'2020-09-20'),
("EGT","Eva","González Torres",'2018-10-17'),
("PLL","Pilar","López López",'2011-12-21'),
("PSA","Pedro","Soto Álvarez",'2013-09-01'),
("CVC","Carmelo","Villegas Cruz",'2019-09-01');

INSERT INTO Curso VALUES
("SMR","1A","PSA"),
("SMR","2A","JGG"),
("DAM","1A","AGL"),
("DAM","2A","PJM");

INSERT INTO Asignatura VALUES
("RED", "Redes Locales", 7,224),
("@RED", "Desdoble de Redes Locales", 3,96),
("SISM", "Sistemas operativos monopuestos", 5,160),
("MONT", "Montaje y mantenimiento de equipos", 7,224),
("@1MONT", "Desdoble de Montaje y mantenimiento de equipos", 1,32),
("@2MONT", "Desdoble de Montaje y mantenimiento de equipos", 2,64),
("APLI", "Aplicaciones ofimáticas", 8, 256),
("@APLI", "Desdoble de Aplicaciones ofimáticas", 3, 96),
("SEG", "Seguridad informática", 5,105),
("HLC", "Horas de Libre Configuración", 3,63),
("SISR", "Sistemas operativos en red",7,147),
("SERV","Servicios en red",7,147),
("APLIW","Aplicaciones web",4,84),
("EIEM", "Empresa e iniciativa empresarial", 4,84),
("SIST","Sistemas informáticos",6,192),
("@SIST","Desdoble de Sistemas informáticos",2,64),
("BDD","Bases de Datos",6,192),
("@1BDD","Desdoble de Bases de Datos 1",1,32),
("@2BDD","Desdoble de Bases de Datos 2",2,64),
("PROG","Programación",8, 256),
("@1PROG","Desdoble de Programación 1",5, 160),
("@2PROG","Desdoble de Programación 2",1, 32),
("ENT","Entornos de desarrollo",3,96),
("MARC","Lenguajes de marcas y sistemas de gestión de información", 4,128),
("FOL","Formación y orientación laboral",3,96),
("AD","Acceso a datos",5,105),
("DI","Desarrollo de interfaces",7,147),
("PSPRO","Programación de servicios y procesos",3,63),
("PMDMO","Programación multimedia y dispositivos móviles",4,84),
("EIE","Empresa e iniciativa emprendedora",4,84),
("SGE","Sistemas de gestión empresarial",4,84);


INSERT INTO TramoHorario VALUES
("L1", "08:15:00", "09:15:00", 'LUNES'), ("L2", "09:15:00", "10:15:00", 'LUNES'), ("L3", "10:15:00", "11:15:00", 'LUNES'),
("L4", "11:45:00", "12:45:00", 'LUNES'), ("L5", "12:45:00", "13:45:00", 'LUNES'), ("L6", "13:45:00", "14:45:00", 'LUNES'),
("M1", "08:15:00", "09:15:00", 'MARTES'), ("M2", "09:15:00", "10:15:00", 'MARTES'), ("M3", "10:15:00", "11:15:00", 'MARTES'),
("M4", "11:45:00", "12:45:00", 'MARTES'), ("M5", "12:45:00", "13:45:00", 'MARTES'), ("M6", "13:45:00", "14:45:00", 'MARTES'),
("X1", "08:15:00", "09:15:00", 'MIERCOLES'), ("X2", "09:15:00", "10:15:00", 'MIERCOLES'), ("X3", "10:15:00", "11:15:00", 'MIERCOLES'),
("X4", "11:45:00", "12:45:00", 'MIERCOLES'), ("X5", "12:45:00", "13:45:00", 'MIERCOLES'), ("X6", "13:45:00", "14:45:00", 'MIERCOLES'),
("J1", "08:15:00", "09:15:00", 'JUEVES'), ("J2", "09:15:00", "10:15:00", 'JUEVES'), ("J3", "10:15:00", "11:15:00", 'JUEVES'),
("J4", "11:45:00", "12:45:00", 'JUEVES'), ("J5", "12:45:00", "13:45:00", 'JUEVES'), ("J6", "13:45:00", "14:45:00", 'JUEVES'),
("V1", "08:15:00", "09:15:00", 'VIERNES'), ("V2", "09:15:00", "10:15:00", 'VIERNES'), ("V3", "10:15:00", "11:15:00", 'VIERNES'),
("V4", "11:45:00", "12:45:00", 'VIERNES'), ("V5", "12:45:00", "13:45:00", 'VIERNES'), ("V6", "13:45:00", "14:45:00", 'VIERNES');


INSERT INTO Reparto VALUES
("SMR","1A","RED","JGG"),("SMR","1A","@RED","MGD"),("SMR","1A","SISM","PLL"),
("SMR","1A","MONT","CVC"),("SMR","1A","@1MONT","PLL"),("SMR","1A","@2MONT","PSA"),
("SMR","1A","APLI","PSA"),("SMR","1A","@APLI","PBG"),("SMR","1A","FOL","EGT"),
("SMR","2A","SEG","AGL"),("SMR","2A","HLC","JGG"),("SMR","2A","SISR","PBG"),
("SMR","2A","SERV","JGG"),("SMR","2A","APLIW","PJM"),("SMR","2A","EIEM","EGT"),
("DAM","1A","SIST","PSA"),("DAM","1A","@SIST","PBG"),("DAM","1A","BDD","AGL"),
("DAM","1A","@1BDD","ARR"),("DAM","1A","@2BDD","MGD"),("DAM","1A","PROG","ARR"),
("DAM","1A","@1PROG","PJM"),("DAM","1A","@2PROG","MGD"),
("DAM","1A","ENT","ARR"),("DAM","1A","MARC","MGD"),("DAM","1A","FOL","EGT"),
("DAM","2A","AD","ARR"),("DAM","2A","DI","CVC"),("DAM","2A","PSPRO","PJM"),
("DAM","2A","PMDMO","PJM"),("DAM","2A","EIE","VLH"),("DAM","2A","SGE","PSA"),
("DAM","2A","HLC","MGD");

-- -- 1 CFGM
INSERT INTO Horario VALUES
("SMR", "1A", "SISM", "L1"), ("SMR", "1A", "APLI", "L2"),
("SMR", "1A", "APLI", "L3"), ("SMR", "1A", "MONT", "L4"),
("SMR", "1A", "MONT", "L5"), ("SMR", "1A", "RED", "M1"),
("SMR", "1A", "RED", "M2"), ("SMR", "1A", "APLI", "M3"),
("SMR", "1A", "APLI", "M4"), ("SMR", "1A", "FOL", "M5"),
("SMR", "1A", "RED", "X1"), ("SMR", "1A", "RED", "X2"),
("SMR", "1A", "APLI", "X3"), ("SMR", "1A", "MONT", "X4"),
("SMR", "1A", "MONT", "X5"), ("SMR", "1A", "SISM", "J1"),
("SMR", "1A", "APLI", "J2"), ("SMR", "1A", "APLI", "J3"),
("SMR", "1A", "RED", "J4"), ("SMR", "1A", "FOL", "J5"),
("SMR", "1A", "SISM", "V1"), ("SMR", "1A", "SISM", "V2"),
("SMR", "1A", "APLI", "V3"), ("SMR", "1A", "MONT", "V4"),
("SMR", "1A", "MONT", "V5"), ("SMR", "1A", "RED", "L6"),
("SMR", "1A", "SISM", "M6"), ("SMR", "1A", "RED", "X6"),
("SMR", "1A", "MONT", "J6"), ("SMR", "1A", "FOL", "V6"),
("SMR", "1A", "@APLI", "L2"),("SMR", "1A", "@RED", "L6"),
("SMR", "1A", "@APLI", "M4"),("SMR", "1A", "@1MONT", "X4"),
("SMR", "1A", "@2MONT", "X5"), ("SMR", "1A", "@RED", "X6"),
("SMR", "1A", "@APLI", "J3"),("SMR", "1A", "@RED", "J4"),
("SMR", "1A", "@2MONT", "J6"), ("SMR", "1A", "@APLI", "V3"),

-- 2 CFGM
("SMR","2A","SISR","L1"),("SMR","2A","HLC","L2"),("SMR","2A","SERV","L3"),
("SMR","2A","SERV","L4"),("SMR","2A","EIE","L5"),("SMR","2A","SISR","L6"),
("SMR","2A","SEG","M1"),("SMR","2A","APLIW","M2"),("SMR","2A","HLC","M3"),
("SMR","2A","SERV","M4"),("SMR","2A","SERV","M5"),("SMR","2A","SISR","M6"),
("SMR","2A","SISR","X1"),("SMR","2A","SISR","X2"),("SMR","2A","SERV","X3"),
("SMR","2A","APLIW","X4"),("SMR","2A","EIE","X5"),("SMR","2A","SEG","X6"),
("SMR","2A","APLIW","J1"),("SMR","2A","SEG","J2"),("SMR","2A","SERV","J3"),
("SMR","2A","EIE","J4"),("SMR","2A","HLC","J5"),("SMR","2A","SISR","J6"),
("SMR","2A","SEG","V1"),("SMR","2A","SISR","V2"),("SMR","2A","SERV","V3"),
("SMR","2A","EIE","V4"),("SMR","2A","APLIW","V5"),("SMR","2A","SEG","V6"),

-- 1 CFGS
("DAM","1A","BDD","L1"),("DAM","1A","PROG","L2"),("DAM","1A","PROG","L3"),
("DAM","1A","SIST","L4"),("DAM","1A","BDD","L5"),("DAM","1A","SIST","L6"),
("DAM","1A","ENT","M1"),("DAM","1A","PROG","M2"),("DAM","1A","PROG","M3"),
("DAM","1A","FOL","M4"),("DAM","1A","SIST","M5"),("DAM","1A","MARC","M6"),
("DAM","1A","MARC","X1"),("DAM","1A","BDD","X2"),("DAM","1A","SIST","X3"),
("DAM","1A","BDD","X4"),("DAM","1A","BDD","X5"),("DAM","1A","SIST","X6"),
("DAM","1A","ENT","J1"),("DAM","1A","PROG","J2"),("DAM","1A","PROG","J3"),
("DAM","1A","SIST","J4"),("DAM","1A","MARC","J5"),("DAM","1A","FOL","J6"),
("DAM","1A","ENT","V1"),("DAM","1A","PROG","V2"),("DAM","1A","PROG","V3"),
("DAM","1A","BDD","V4"),("DAM","1A","FOL","V5"),("DAM","1A","SIST","V6"),
("DAM","1A","@1BDD","X3"), ("DAM","1A","@2BDD","X2"), ("DAM","1A","@2BDD","V4"),
("DAM","1A","@1PROG","M2"), ("DAM","1A","@2PROG","M3"), ("DAM","1A","@2PROG","J2"),
("DAM","1A","@2PROG","J3"), ("DAM","1A","@2PROG","V2"), ("DAM","1A","@2PROG","V3"),

-- 2 CFGS
("DAM","2A","SGE","L1"),("DAM","2A","EIE","L2"),("DAM","2A","PSPRO","L3"),
("DAM","2A","AD","L4"),("DAM","2A","AD","L5"),("DAM","2A","DI","L6"),
("DAM","2A","SGE","M1"),("DAM","2A","EIE","M2"),("DAM","2A","HLC","M3"),
("DAM","2A","PMDMO","M4"),("DAM","2A","PMDMO","M5"),("DAM","2A","DI","M6"),
("DAM","2A","EIE","X1"),("DAM","2A","PMDMO","X2"),("DAM","2A","PMDMO","X3"),
("DAM","2A","HLC","X4"),("DAM","2A","PSPRO","X5"),("DAM","2A","DI","X6"),
("DAM","2A","EIE","J1"),("DAM","2A","DI","J2"),("DAM","2A","DI","J3"),
("DAM","2A","AD","J4"),("DAM","2A","AD","J5"),("DAM","2A","SGE","J6"),
("DAM","2A","HLC","V1"),("DAM","2A","DI","V2"),("DAM","2A","DI","V3"),
("DAM","2A","SGE","V4"),("DAM","2A","AD","V5"),("DAM","2A","PSPRO","V6");
