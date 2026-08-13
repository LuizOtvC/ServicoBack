CREATE DATABASE  IF NOT EXISTS `servicofinal` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `servicofinal`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: servicofinal
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `avaliacao`
--

DROP TABLE IF EXISTS `avaliacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avaliacao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `projeto_id` bigint(20) NOT NULL,
  `avaliador_id` bigint(20) NOT NULL,
  `avaliado_id` bigint(20) NOT NULL,
  `nota` double NOT NULL,
  `comentario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_avaliacao` (`projeto_id`,`avaliador_id`,`avaliado_id`),
  KEY `fk_avaliacao_avaliador` (`avaliador_id`),
  KEY `fk_avaliacao_avaliado` (`avaliado_id`),
  CONSTRAINT `fk_avaliacao_avaliado` FOREIGN KEY (`avaliado_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_avaliacao_avaliador` FOREIGN KEY (`avaliador_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_avaliacao_projeto` FOREIGN KEY (`projeto_id`) REFERENCES `projeto` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avaliacao`
--

LOCK TABLES `avaliacao` WRITE;
/*!40000 ALTER TABLE `avaliacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `avaliacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_resultado`
--

DROP TABLE IF EXISTS `match_resultado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `match_resultado` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `usuario_id` bigint(20) NOT NULL,
  `projeto_id` bigint(20) NOT NULL,
  `score_total` double NOT NULL,
  `score_servicos` double NOT NULL,
  `score_orcamento` double NOT NULL,
  `score_historico` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_match` (`usuario_id`,`projeto_id`),
  KEY `fk_match_projeto` (`projeto_id`),
  CONSTRAINT `fk_match_projeto` FOREIGN KEY (`projeto_id`) REFERENCES `projeto` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_match_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_resultado`
--

LOCK TABLES `match_resultado` WRITE;
/*!40000 ALTER TABLE `match_resultado` DISABLE KEYS */;
/*!40000 ALTER TABLE `match_resultado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensagem`
--

DROP TABLE IF EXISTS `mensagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mensagem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `usuario_id` bigint(20) NOT NULL,
  `projeto_id` bigint(20) NOT NULL,
  `mensagem` varchar(200) NOT NULL,
  `status` enum('ACEITA','RECUSADA','CANCELADA','EM_ANDAMENTO','CONCLUIDO','CANCELADO','CRIADO','ENVIADA','PROPOSTA','ARQUIVADO') NOT NULL,
  `enviado_em` timestamp NOT NULL DEFAULT current_timestamp(),
  `lida` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `projeto_id` (`projeto_id`),
  KEY `usuario_id` (`usuario_id`),
  CONSTRAINT `mensagem_ibfk_1` FOREIGN KEY (`projeto_id`) REFERENCES `projeto` (`id`) ON DELETE CASCADE,
  CONSTRAINT `mensagem_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensagem`
--

LOCK TABLES `mensagem` WRITE;
/*!40000 ALTER TABLE `mensagem` DISABLE KEYS */;
/*!40000 ALTER TABLE `mensagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensagem_seq`
--

DROP TABLE IF EXISTS `mensagem_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mensagem_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensagem_seq`
--

LOCK TABLES `mensagem_seq` WRITE;
/*!40000 ALTER TABLE `mensagem_seq` DISABLE KEYS */;
INSERT INTO `mensagem_seq` VALUES (151);
/*!40000 ALTER TABLE `mensagem_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projeto`
--

DROP TABLE IF EXISTS `projeto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projeto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `usuario_id` bigint(20) NOT NULL,
  `titulo` varchar(200) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `orcamento` double NOT NULL,
  `status` enum('ABERTO','EM_ANDAMENTO','CONCLUIDO','CANCELADO','ARQUIVADO') NOT NULL DEFAULT 'ABERTO',
  `score_risco` int(11) NOT NULL DEFAULT 0,
  `criado_em` timestamp NOT NULL DEFAULT current_timestamp(),
  `cidade` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_projeto_usuario` (`usuario_id`),
  CONSTRAINT `fk_projeto_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projeto`
--

LOCK TABLES `projeto` WRITE;
/*!40000 ALTER TABLE `projeto` DISABLE KEYS */;
/*!40000 ALTER TABLE `projeto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projeto_dia_trabalho`
--

DROP TABLE IF EXISTS `projeto_dia_trabalho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projeto_dia_trabalho` (
  `projeto_id` bigint(20) NOT NULL,
  `dia_semana` enum('DOMINGO','SEGUNDA','TERCA','QUARTA','QUINTA','SEXTA','SABADO') NOT NULL,
  PRIMARY KEY (`projeto_id`,`dia_semana`),
  UNIQUE KEY `UK2bph03pp6ubhx50nisi8sbgih` (`projeto_id`,`dia_semana`),
  CONSTRAINT `fk_projeto_dia` FOREIGN KEY (`projeto_id`) REFERENCES `projeto` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projeto_dia_trabalho`
--

LOCK TABLES `projeto_dia_trabalho` WRITE;
/*!40000 ALTER TABLE `projeto_dia_trabalho` DISABLE KEYS */;
/*!40000 ALTER TABLE `projeto_dia_trabalho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projeto_seq`
--

DROP TABLE IF EXISTS `projeto_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projeto_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projeto_seq`
--

LOCK TABLES `projeto_seq` WRITE;
/*!40000 ALTER TABLE `projeto_seq` DISABLE KEYS */;
INSERT INTO `projeto_seq` VALUES (151);
/*!40000 ALTER TABLE `projeto_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projeto_servico`
--

DROP TABLE IF EXISTS `projeto_servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projeto_servico` (
  `projeto_id` bigint(20) NOT NULL,
  `servico_id` bigint(20) NOT NULL,
  PRIMARY KEY (`projeto_id`,`servico_id`),
  KEY `servico_id` (`servico_id`),
  CONSTRAINT `projeto_servico_ibfk_1` FOREIGN KEY (`projeto_id`) REFERENCES `projeto` (`id`) ON DELETE CASCADE,
  CONSTRAINT `projeto_servico_ibfk_2` FOREIGN KEY (`servico_id`) REFERENCES `servico` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projeto_servico`
--

LOCK TABLES `projeto_servico` WRITE;
/*!40000 ALTER TABLE `projeto_servico` DISABLE KEYS */;
/*!40000 ALTER TABLE `projeto_servico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proposta`
--

DROP TABLE IF EXISTS `proposta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proposta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `usuario_id` bigint(20) NOT NULL,
  `projeto_id` bigint(20) NOT NULL,
  `valor_proposto` double NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `status` enum('PENDENTE','ACEITA','RECUSADA','CANCELADA','CONCLUIDA') NOT NULL DEFAULT 'PENDENTE',
  `enviado_em` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `fk_proposta_usuario` (`usuario_id`),
  KEY `fk_proposta_projeto` (`projeto_id`),
  CONSTRAINT `fk_proposta_projeto` FOREIGN KEY (`projeto_id`) REFERENCES `projeto` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_proposta_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proposta`
--

LOCK TABLES `proposta` WRITE;
/*!40000 ALTER TABLE `proposta` DISABLE KEYS */;
/*!40000 ALTER TABLE `proposta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servico`
--

DROP TABLE IF EXISTS `servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servico` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servico`
--

LOCK TABLES `servico` WRITE;
/*!40000 ALTER TABLE `servico` DISABLE KEYS */;
INSERT INTO `servico` VALUES (8,'Azulejista'),(11,'Diarista'),(1,'Eletricista'),(3,'Encanador'),(12,'Faxineiro'),(9,'Gesseiro'),(17,'Instalador de Cameras'),(18,'Instalador de Internet'),(5,'Jardineiro'),(6,'Marceneiro'),(20,'Mecanico'),(13,'Montador de Moveis'),(4,'Pedreiro'),(2,'Pintor'),(7,'Serralheiro'),(19,'Soldador'),(14,'Tecnico em Ar Condicionado'),(15,'Tecnico em Geladeira'),(16,'Tecnico em Maquina de Lavar'),(10,'Vidraceiro');
/*!40000 ALTER TABLE `servico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servico_seq`
--

DROP TABLE IF EXISTS `servico_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servico_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servico_seq`
--

LOCK TABLES `servico_seq` WRITE;
/*!40000 ALTER TABLE `servico_seq` DISABLE KEYS */;
INSERT INTO `servico_seq` VALUES (1);
/*!40000 ALTER TABLE `servico_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `descricao` varchar(1000) DEFAULT NULL,
  `email` varchar(150) NOT NULL,
  `telefone` varchar(150) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `reputacao` double NOT NULL,
  `peso_servicos` double DEFAULT NULL,
  `peso_orcamento` double DEFAULT NULL,
  `peso_historico` double DEFAULT NULL,
  `status` enum('ATIVO','INATIVO','ARQUIVADO') NOT NULL DEFAULT 'ATIVO',
  `ultimo_login` timestamp NOT NULL DEFAULT current_timestamp(),
  `cidade` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `telefone` (`telefone`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'João Silva','Gosto de sempre estar fazendo algo','joao@email.com','11999999999','12345',5,NULL,NULL,NULL,'ATIVO','2026-08-13 17:43:30','Londrina'),(2,'Carlos Oliveira',NULL,'carlos@email.com','11988887777','12345',5,NULL,NULL,NULL,'ATIVO','2026-08-12 20:10:16',NULL),(3,'Maria José',NULL,'maria@email.com','11988888888','12345',1.5,NULL,NULL,NULL,'ARQUIVADO','2026-05-20 13:00:00',NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_dia_trabalho`
--

DROP TABLE IF EXISTS `usuario_dia_trabalho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_dia_trabalho` (
  `usuario_id` bigint(20) NOT NULL,
  `dia_semana` enum('DOMINGO','SEGUNDA','TERCA','QUARTA','QUINTA','SEXTA','SABADO') NOT NULL,
  PRIMARY KEY (`usuario_id`,`dia_semana`),
  UNIQUE KEY `UKoa71kfmxq5jo6nh6b3gqsgrpd` (`usuario_id`,`dia_semana`),
  CONSTRAINT `usuario_dia_trabalho_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_dia_trabalho`
--

LOCK TABLES `usuario_dia_trabalho` WRITE;
/*!40000 ALTER TABLE `usuario_dia_trabalho` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_dia_trabalho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_servico`
--

DROP TABLE IF EXISTS `usuario_servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_servico` (
  `usuario_id` bigint(20) NOT NULL,
  `servico_id` bigint(20) NOT NULL,
  `nivel` enum('BASICO','INTERMEDIARIO','AVANCADO') NOT NULL,
  PRIMARY KEY (`usuario_id`,`servico_id`),
  KEY `servico_id` (`servico_id`),
  CONSTRAINT `usuario_servico_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE,
  CONSTRAINT `usuario_servico_ibfk_2` FOREIGN KEY (`servico_id`) REFERENCES `servico` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_servico`
--

LOCK TABLES `usuario_servico` WRITE;
/*!40000 ALTER TABLE `usuario_servico` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_servico` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-08-13 15:19:56
