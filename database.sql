-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.23-1~dotdeb.3 - (Debian)
-- Server OS:                    debian-linux-gnu
-- HeidiSQL Version:             9.2.0.4956
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for ooj4
DROP DATABASE IF EXISTS `ooj4`;
CREATE DATABASE IF NOT EXISTS `ooj4` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_swedish_ci */;
USE `ooj4`;


-- Dumping structure for table ooj4.aluno
DROP TABLE IF EXISTS `aluno`;
CREATE TABLE IF NOT EXISTS `aluno` (
  `RA` varchar(6) COLLATE utf8_swedish_ci NOT NULL,
  `nome` varchar(45) COLLATE utf8_swedish_ci NOT NULL,
  PRIMARY KEY (`RA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- Dumping data for table ooj4.aluno: ~4 rows (approximately)
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` (`RA`, `nome`) VALUES
	('1', 'Airon Wellington'),
	('2', 'Carlos Filho'),
	('3', 'Carlos Gleyson'),
	('4', 'Rayckson Araujo');
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;


-- Dumping structure for table ooj4.aluno_disciplina
DROP TABLE IF EXISTS `aluno_disciplina`;
CREATE TABLE IF NOT EXISTS `aluno_disciplina` (
  `nota` float unsigned DEFAULT NULL,
  `RA` varchar(6) COLLATE utf8_swedish_ci DEFAULT NULL,
  `codigo_disciplina` int(11) unsigned DEFAULT NULL,
  KEY `FK_aluno_disciplina_aluno` (`RA`),
  KEY `FK_aluno_disciplina_disciplina` (`codigo_disciplina`),
  CONSTRAINT `FK_aluno_disciplina_aluno` FOREIGN KEY (`RA`) REFERENCES `aluno` (`RA`),
  CONSTRAINT `FK_aluno_disciplina_disciplina` FOREIGN KEY (`codigo_disciplina`) REFERENCES `disciplina` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- Dumping data for table ooj4.aluno_disciplina: ~0 rows (approximately)
/*!40000 ALTER TABLE `aluno_disciplina` DISABLE KEYS */;
INSERT INTO `aluno_disciplina` (`nota`, `RA`, `codigo_disciplina`) VALUES
	(3.5, '1', 1),
	(4.5, '1', 1),
	(7.5, '1', 1),
	(10, '1', 2),
	(10, '1', 2),
	(10, '1', 2),
	(5, '2', 1),
	(6, '2', 1),
	(7, '2', 1),
	(7, '2', 2),
	(7.5, '2', 2),
	(7.5, '2', 2),
	(8, '3', 3),
	(9, '3', 3),
	(8, '3', 3),
	(7.5, '3', 4),
	(8.5, '3', 4),
	(9.5, '3', 4),
	(9.8, '4', 3),
	(8.8, '4', 3),
	(7.8, '4', 3),
	(9.9, '4', 4),
	(9.9, '4', 4),
	(9.9, '4', 4);
/*!40000 ALTER TABLE `aluno_disciplina` ENABLE KEYS */;


-- Dumping structure for table ooj4.curso
DROP TABLE IF EXISTS `curso`;
CREATE TABLE IF NOT EXISTS `curso` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) COLLATE utf8_swedish_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- Dumping data for table ooj4.curso: ~2 rows (approximately)
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` (`id`, `nome`) VALUES
	(1, 'Especialização em Engenharia de Software'),
	(2, 'Especialização em Bancos de dados');
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;


-- Dumping structure for table ooj4.disciplina
DROP TABLE IF EXISTS `disciplina`;
CREATE TABLE IF NOT EXISTS `disciplina` (
  `codigo` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) COLLATE utf8_swedish_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- Dumping data for table ooj4.disciplina: ~4 rows (approximately)
/*!40000 ALTER TABLE `disciplina` DISABLE KEYS */;
INSERT INTO `disciplina` (`codigo`, `descricao`) VALUES
	(1, 'Engenharia de Requisitos'),
	(2, 'Programação Orientada a Objetos com Java'),
	(3, 'Bancos de dados relacionais'),
	(4, 'Bancos de dados geográficos');
/*!40000 ALTER TABLE `disciplina` ENABLE KEYS */;


-- Dumping structure for table ooj4.disciplina_curso
DROP TABLE IF EXISTS `disciplina_curso`;
CREATE TABLE IF NOT EXISTS `disciplina_curso` (
  `codigo_disciplina` int(11) unsigned DEFAULT NULL,
  `id_curso` int(11) unsigned DEFAULT NULL,
  KEY `FK_disciplina_curso_disciplina` (`codigo_disciplina`),
  KEY `FK_disciplina_curso_curso` (`id_curso`),
  CONSTRAINT `FK_disciplina_curso_curso` FOREIGN KEY (`id_curso`) REFERENCES `curso` (`id`),
  CONSTRAINT `FK_disciplina_curso_disciplina` FOREIGN KEY (`codigo_disciplina`) REFERENCES `disciplina` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- Dumping data for table ooj4.disciplina_curso: ~4 rows (approximately)
/*!40000 ALTER TABLE `disciplina_curso` DISABLE KEYS */;
INSERT INTO `disciplina_curso` (`codigo_disciplina`, `id_curso`) VALUES
	(1, 1),
	(2, 1),
	(3, 2),
	(4, 2);
/*!40000 ALTER TABLE `disciplina_curso` ENABLE KEYS */;


-- Dumping structure for table ooj4.resumo_notas
DROP TABLE IF EXISTS `resumo_notas`;
CREATE TABLE IF NOT EXISTS `resumo_notas` (
  `media` float unsigned DEFAULT NULL,
  `maior` float unsigned DEFAULT NULL,
  `menor` float unsigned DEFAULT NULL,
  `curso_id` int(11) unsigned DEFAULT NULL,
  `disciplina_id` int(11) unsigned DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

-- Dumping data for table ooj4.resumo_notas: ~4 rows (approximately)
/*!40000 ALTER TABLE `resumo_notas` DISABLE KEYS */;
INSERT INTO `resumo_notas` (`media`, `maior`, `menor`, `curso_id`, `disciplina_id`) VALUES
	(5.58333, 7.5, 3.5, 1, 1),
	(8.66667, 10, 7, 1, 2),
	(8.56667, 9.8, 7.8, 2, 3),
	(9.2, 9.9, 7.5, 2, 4);
/*!40000 ALTER TABLE `resumo_notas` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
