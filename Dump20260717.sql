CREATE DATABASE  IF NOT EXISTS `servicofinal` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `servicofinal`;
-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: localhost    Database: servicofinal
-- ------------------------------------------------------
-- Server version	8.0.45

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
  `id` bigint NOT NULL AUTO_INCREMENT,
  `projeto_id` bigint NOT NULL,
  `avaliador_id` bigint NOT NULL,
  `avaliado_id` bigint NOT NULL,
  `nota` decimal(3,1) NOT NULL,
  `comentario` text COLLATE utf8mb4_unicode_ci,
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
  `id` bigint NOT NULL AUTO_INCREMENT,
  `usuario_id` bigint NOT NULL,
  `projeto_id` bigint NOT NULL,
  `score_total` decimal(5,2) NOT NULL,
  `score_servicos` decimal(5,2) NOT NULL,
  `score_orcamento` decimal(5,2) NOT NULL,
  `score_historico` decimal(5,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_match` (`usuario_id`,`projeto_id`),
  KEY `fk_match_projeto` (`projeto_id`),
  CONSTRAINT `fk_match_projeto` FOREIGN KEY (`projeto_id`) REFERENCES `projeto` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_match_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
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
  `id` bigint NOT NULL AUTO_INCREMENT,
  `usuario_id` bigint NOT NULL,
  `projeto_id` bigint NOT NULL,
  `mensagem` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` enum('ACEITA','RECUSADA','CANCELADA','EM_ANDAMENTO','CONCLUIDO','CANCELADO','CRIADO','ENVIADA','PROPOSTA') COLLATE utf8mb4_unicode_ci NOT NULL,
  `enviado_em` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `projeto_id` (`projeto_id`),
  KEY `usuario_id` (`usuario_id`),
  CONSTRAINT `mensagem_ibfk_1` FOREIGN KEY (`projeto_id`) REFERENCES `projeto` (`id`) ON DELETE CASCADE,
  CONSTRAINT `mensagem_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=460 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensagem`
--

LOCK TABLES `mensagem` WRITE;
/*!40000 ALTER TABLE `mensagem` DISABLE KEYS */;
INSERT INTO `mensagem` VALUES (352,1,2,'Proposta enviada com sucesso','ENVIADA','2026-07-17 00:59:23'),(402,1,152,'Projeto criado com succeso','CRIADO','2026-07-17 17:06:07'),(452,2,152,'Proposta enviada com sucesso','ENVIADA','2026-07-17 17:14:15'),(453,1,152,'Projeto cancelado com sucesso','CANCELADO','2026-07-17 17:15:40'),(454,2,152,'Sua proposta foi cancelada','CANCELADA','2026-07-17 17:15:40'),(455,1,202,'Projeto criado com succeso','CRIADO','2026-07-17 17:16:09'),(456,2,202,'Proposta enviada com sucesso','ENVIADA','2026-07-17 17:16:53'),(457,2,202,'Sua proposta foi aceita!!','ACEITA','2026-07-17 17:17:19'),(458,1,202,'Projeto cancelado com sucesso','CANCELADO','2026-07-17 17:17:28'),(459,2,202,'Sua proposta foi cancelada','CANCELADA','2026-07-17 17:17:28');
/*!40000 ALTER TABLE `mensagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensagem_seq`
--

DROP TABLE IF EXISTS `mensagem_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mensagem_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensagem_seq`
--

LOCK TABLES `mensagem_seq` WRITE;
/*!40000 ALTER TABLE `mensagem_seq` DISABLE KEYS */;
INSERT INTO `mensagem_seq` VALUES (551);
/*!40000 ALTER TABLE `mensagem_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projeto`
--

DROP TABLE IF EXISTS `projeto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projeto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `usuario_id` bigint NOT NULL,
  `titulo` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `descricao` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `orcamento` double NOT NULL,
  `horas_estimadas` int NOT NULL,
  `status` enum('ABERTO','EM_ANDAMENTO','CONCLUIDO','CANCELADO') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'ABERTO',
  `score_risco` int NOT NULL DEFAULT '0',
  `criado_em` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_projeto_usuario` (`usuario_id`),
  CONSTRAINT `fk_projeto_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projeto`
--

LOCK TABLES `projeto` WRITE;
/*!40000 ALTER TABLE `projeto` DISABLE KEYS */;
INSERT INTO `projeto` VALUES (1,1,'Teste','apenas um teste',100,12,'EM_ANDAMENTO',0,'2026-07-16 17:25:39'),(2,2,'Nossa','nossa',232,2,'ABERTO',0,'2026-07-16 17:49:14'),(52,1,'Caramba','testesOa',343,2,'CONCLUIDO',0,'2026-07-16 18:49:45'),(102,1,'ai meu cool','oi',100,7,'CANCELADO',0,'2026-07-17 00:29:57'),(152,1,'testes','wdas',324,3,'CANCELADO',0,'2026-07-17 17:06:06'),(202,1,'dfg','gfdg',234,3,'CANCELADO',0,'2026-07-17 17:16:09');
/*!40000 ALTER TABLE `projeto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projeto_seq`
--

DROP TABLE IF EXISTS `projeto_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projeto_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projeto_seq`
--

LOCK TABLES `projeto_seq` WRITE;
/*!40000 ALTER TABLE `projeto_seq` DISABLE KEYS */;
INSERT INTO `projeto_seq` VALUES (301);
/*!40000 ALTER TABLE `projeto_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projeto_servico`
--

DROP TABLE IF EXISTS `projeto_servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projeto_servico` (
  `projeto_id` bigint NOT NULL,
  `servico_id` bigint NOT NULL,
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
INSERT INTO `projeto_servico` VALUES (52,4),(152,4),(202,5),(1,6),(2,7),(102,8);
/*!40000 ALTER TABLE `projeto_servico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proposta`
--

DROP TABLE IF EXISTS `proposta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proposta` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `usuario_id` bigint NOT NULL,
  `projeto_id` bigint NOT NULL,
  `valor_proposto` double NOT NULL,
  `descricao` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` enum('PENDENTE','ACEITA','RECUSADA','CANCELADA') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'PENDENTE',
  `enviado_em` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_proposta_usuario` (`usuario_id`),
  KEY `fk_proposta_projeto` (`projeto_id`),
  CONSTRAINT `fk_proposta_projeto` FOREIGN KEY (`projeto_id`) REFERENCES `projeto` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_proposta_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proposta`
--

LOCK TABLES `proposta` WRITE;
/*!40000 ALTER TABLE `proposta` DISABLE KEYS */;
INSERT INTO `proposta` VALUES (1,2,1,200,'dalhe','ACEITA','2026-07-16 17:26:28'),(2,1,2,122,'fc','RECUSADA','2026-07-16 17:50:06'),(3,2,52,122,'wdfasd','ACEITA','2026-07-16 18:50:02'),(4,1,2,222,'testes','PENDENTE','2026-07-17 00:59:23'),(5,2,152,2,'23','CANCELADA','2026-07-17 17:14:15'),(6,2,202,3,'dsfw','CANCELADA','2026-07-17 17:16:53');
/*!40000 ALTER TABLE `proposta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servico`
--

DROP TABLE IF EXISTS `servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servico` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
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
  `next_val` bigint DEFAULT NULL
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
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `telefone` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `senha` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `reputacao` double NOT NULL,
  `horas_semana` int DEFAULT NULL,
  `peso_servicos` double DEFAULT NULL,
  `peso_orcamento` double DEFAULT NULL,
  `peso_historico` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `telefone` (`telefone`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'João Silva','joao@email.com','11999999999','12345',5,NULL,NULL,NULL,NULL),(2,'Teste','Teste@gmail.com','21321312','123456',5,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_servico`
--

DROP TABLE IF EXISTS `usuario_servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_servico` (
  `usuario_id` bigint NOT NULL,
  `servico_id` bigint NOT NULL,
  `nivel` enum('BASICO','INTERMEDIARIO','AVANCADO') COLLATE utf8mb4_unicode_ci NOT NULL,
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

-- Dump completed on 2026-07-17 14:26:05
