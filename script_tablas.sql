CREATE SCHEMA `horarios_fp` ;

CREATE TABLE `asignatura` (
  `cod_asignatura` int NOT NULL,
  `nom_asignatura` varchar(45) DEFAULT NULL,
  `h_semana` int DEFAULT NULL,
  `h_total` int DEFAULT NULL,
  PRIMARY KEY (`cod_asignatura`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `oferta_educativa` (
  `cod_oferta` int NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `nom_oferta` varchar(45) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `tipo` enum('CFGM','CFGS') NOT NULL,
  PRIMARY KEY (`cod_oferta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `profesor` (
  `cod_profesor` int NOT NULL,
  `nom_profesor` varchar(45) DEFAULT NULL,
  `ape_profesor` varchar(45) DEFAULT NULL,
  `fecha_alta` date DEFAULT NULL,
  PRIMARY KEY (`cod_profesor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `tramo_horario` (
  `cod_horario` int NOT NULL,
  `dia` varchar(45) DEFAULT NULL,
  `h_inicio` varchar(45) DEFAULT NULL,
  `h_fin` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cod_horario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `curso` (
  `cod_curso` int NOT NULL,
  `cod_oferta` int NOT NULL,
  `cod_profesor` int NOT NULL,
  PRIMARY KEY (`cod_curso`,`cod_oferta`),
  KEY `cod_profesor_idx` (`cod_profesor`),
  KEY `cod_oferta_idx` (`cod_oferta`),
  CONSTRAINT `fk_cod_oferta_CURSO` FOREIGN KEY (`cod_oferta`) REFERENCES `oferta_educativa` (`cod_oferta`),
  CONSTRAINT `fk_cod_profesor_CURSO` FOREIGN KEY (`cod_profesor`) REFERENCES `profesor` (`cod_profesor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `curso_profesor_asignatura` (
  `cod_curso` int NOT NULL,
  `cod_profesor` int NOT NULL,
  `cod_asignatura` int NOT NULL,
  `cod_oferta` int NOT NULL,
  PRIMARY KEY (`cod_curso`,`cod_asignatura`,`cod_oferta`),
  KEY `cod_asignatura_idx` (`cod_asignatura`),
  KEY `cod_profesor_idx` (`cod_profesor`),
  KEY `cod_oferta_idx` (`cod_oferta`),
  CONSTRAINT `fk_cod_asignatura_CURSO_PROF_ASIG` FOREIGN KEY (`cod_asignatura`) REFERENCES `asignatura` (`cod_asignatura`),
  CONSTRAINT `fk_cod_curso_CURSO_PROF_ASIG` FOREIGN KEY (`cod_curso`) REFERENCES `curso` (`cod_curso`),
  CONSTRAINT `fk_cod_oferta_CURSO_PROF_ASIG` FOREIGN KEY (`cod_oferta`) REFERENCES `curso` (`cod_oferta`),
  CONSTRAINT `fk_cod_profesor_CURSO_PROF_ASIG` FOREIGN KEY (`cod_profesor`) REFERENCES `profesor` (`cod_profesor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `curso_tramohorario_asignatura` (
  `cod_horario` int NOT NULL,
  `cod_asignatura` int NOT NULL,
  `cod_curso` int NOT NULL,
  `cod_oferta` int NOT NULL,
  PRIMARY KEY (`cod_horario`,`cod_asignatura`,`cod_curso`,`cod_oferta`),
  KEY `cod_asignatura_idx` (`cod_asignatura`),
  KEY `cod_curso_idx` (`cod_curso`),
  KEY `cod_oferta_idx` (`cod_oferta`),
  CONSTRAINT `fk_cod_asignatura_CURSO_HORARIO_ASIG` FOREIGN KEY (`cod_asignatura`) REFERENCES `asignatura` (`cod_asignatura`),
  CONSTRAINT `fk_cod_curso_CURSO_HORARIO_ASIG` FOREIGN KEY (`cod_curso`) REFERENCES `curso` (`cod_curso`),
  CONSTRAINT `fk_cod_horario_CURSO_HORARIO_ASIG` FOREIGN KEY (`cod_horario`) REFERENCES `tramo_horario` (`cod_horario`),
  CONSTRAINT `fk_cod_oferta_CURSO_HORARIO_ASIG` FOREIGN KEY (`cod_oferta`) REFERENCES `curso` (`cod_oferta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
