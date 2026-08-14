CREATE DATABASE  IF NOT EXISTS `servicofinal` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `servicofinal`;
-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: servicofinal
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
  `nota` double NOT NULL,
  `comentario` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_avaliacao` (`projeto_id`,`avaliador_id`,`avaliado_id`),
  KEY `fk_avaliacao_avaliador` (`avaliador_id`),
  KEY `fk_avaliacao_avaliado` (`avaliado_id`),
  CONSTRAINT `fk_avaliacao_avaliado` FOREIGN KEY (`avaliado_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_avaliacao_avaliador` FOREIGN KEY (`avaliador_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_avaliacao_projeto` FOREIGN KEY (`projeto_id`) REFERENCES `projeto` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avaliacao`
--

LOCK TABLES `avaliacao` WRITE;
/*!40000 ALTER TABLE `avaliacao` DISABLE KEYS */;
INSERT INTO `avaliacao` VALUES (1,5,1,2,5,'Muito rápido e resolveu tudo numa visita só. Recomendo!'),(2,6,6,4,5,'Serviço bem feito e dentro do prazo combinado.'),(3,6,4,6,5,'Cliente super tranquila, pagamento na hora.');
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
  `score_total` double NOT NULL,
  `score_servicos` double NOT NULL,
  `score_orcamento` double NOT NULL,
  `score_historico` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_match` (`usuario_id`,`projeto_id`),
  KEY `fk_match_projeto` (`projeto_id`),
  CONSTRAINT `fk_match_projeto` FOREIGN KEY (`projeto_id`) REFERENCES `projeto` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_match_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_resultado`
--

LOCK TABLES `match_resultado` WRITE;
/*!40000 ALTER TABLE `match_resultado` DISABLE KEYS */;
INSERT INTO `match_resultado` VALUES (8,1,1,0.3,0,0,0),(9,4,6,0.7150000000000001,1,0,0.1);
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
  `mensagem` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` enum('ACEITA','RECUSADA','CANCELADA','EM_ANDAMENTO','CONCLUIDO','CANCELADO','CRIADO','ENVIADA','PROPOSTA','ARQUIVADO') COLLATE utf8mb4_unicode_ci NOT NULL,
  `enviado_em` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lida` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `projeto_id` (`projeto_id`),
  KEY `usuario_id` (`usuario_id`),
  CONSTRAINT `mensagem_ibfk_1` FOREIGN KEY (`projeto_id`) REFERENCES `projeto` (`id`) ON DELETE CASCADE,
  CONSTRAINT `mensagem_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
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
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensagem_seq`
--

LOCK TABLES `mensagem_seq` WRITE;
/*!40000 ALTER TABLE `mensagem_seq` DISABLE KEYS */;
INSERT INTO `mensagem_seq` VALUES (251);
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
  `status` enum('ABERTO','EM_ANDAMENTO','CONCLUIDO','CANCELADO','ARQUIVADO') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'ABERTO',
  `score_risco` int NOT NULL DEFAULT '0',
  `criado_em` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `cidade` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_projeto_usuario` (`usuario_id`),
  CONSTRAINT `fk_projeto_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projeto`
--

LOCK TABLES `projeto` WRITE;
/*!40000 ALTER TABLE `projeto` DISABLE KEYS */;
INSERT INTO `projeto` VALUES (1,1,'Instalação de tomadas e disjuntor','Preciso trocar o quadro de disjuntores e instalar 3 tomadas novas na cozinha.',450,'ABERTO',0,'2026-08-11 18:00:00','Londrina'),(2,6,'Pintura de sala e cozinha','Pintura de dois ambientes, paredes já preparadas, tinta por conta do contratante.',1200,'ABERTO',0,'2026-08-13 11:45:00','Curitiba'),(3,1,'Reforma de banheiro completa','Troca de encanamento e revestimento do banheiro social, aproximadamente 6m².',3800,'CANCELADO',0,'2026-07-25 19:00:00','Londrina'),(4,7,'Instalação de ar condicionado split','Instalação de um split 12000 BTUs em quarto, já tenho o aparelho.',800,'CANCELADO',0,'2026-08-01 15:00:00','Maringá'),(5,1,'Consertos elétricos gerais','Alguns pontos de luz sem funcionar e um disjuntor que desarma sozinho.',600,'CONCLUIDO',0,'2026-07-10 12:00:00','Londrina'),(6,6,'Manutenção hidráulica na cozinha','Vazamento embaixo da pia e troca do sifão.',700,'CONCLUIDO',0,'2026-07-15 13:15:00','Curitiba'),(7,7,'Manutenção e poda do jardim','Poda de grama, cercas vivas e limpeza geral do jardim da frente.',350,'EM_ANDAMENTO',0,'2026-08-05 12:30:00','Maringá');
/*!40000 ALTER TABLE `projeto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projeto_dia_trabalho`
--

DROP TABLE IF EXISTS `projeto_dia_trabalho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projeto_dia_trabalho` (
  `projeto_id` bigint NOT NULL,
  `dia_semana` enum('DOMINGO','SEGUNDA','TERCA','QUARTA','QUINTA','SEXTA','SABADO') COLLATE utf8mb4_unicode_ci NOT NULL,
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
INSERT INTO `projeto_dia_trabalho` VALUES (1,'SEGUNDA'),(1,'QUARTA'),(7,'SABADO');
/*!40000 ALTER TABLE `projeto_dia_trabalho` ENABLE KEYS */;
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
INSERT INTO `projeto_seq` VALUES (251);
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
INSERT INTO `projeto_servico` VALUES (1,1),(5,1),(2,2),(3,3),(6,3),(7,5),(3,8),(4,14);
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
  `status` enum('PENDENTE','ACEITA','RECUSADA','CANCELADA','CONCLUIDA') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'PENDENTE',
  `enviado_em` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_proposta_usuario` (`usuario_id`),
  KEY `fk_proposta_projeto` (`projeto_id`),
  CONSTRAINT `fk_proposta_projeto` FOREIGN KEY (`projeto_id`) REFERENCES `projeto` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_proposta_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proposta`
--

LOCK TABLES `proposta` WRITE;
/*!40000 ALTER TABLE `proposta` DISABLE KEYS */;
INSERT INTO `proposta` VALUES (1,2,1,420,'Posso ir amanhã de manhã, material já incluso no valor.','PENDENTE','2026-08-12 12:00:00'),(2,5,3,3600,'Consigo iniciar essa semana, tenho experiência com reformas.','CANCELADA','2026-07-26 14:00:00'),(3,2,5,580,'Resolvo tudo em uma visita só.','CONCLUIDA','2026-07-11 17:30:00'),(4,4,6,650,'Troco o sifão e vedo o ponto do vazamento, garantia de 90 dias.','CONCLUIDA','2026-07-16 11:00:00'),(5,5,7,320,'Faço a poda completa e deixo o jardim limpo no mesmo dia.','ACEITA','2026-08-06 13:00:00');
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
  `descricao` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `telefone` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `senha` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `reputacao` double NOT NULL,
  `peso_servicos` double DEFAULT NULL,
  `peso_orcamento` double DEFAULT NULL,
  `peso_historico` double DEFAULT NULL,
  `status` enum('ATIVO','INATIVO','ARQUIVADO') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'ATIVO',
  `ultimo_login` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `cidade` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `telefone` (`telefone`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'João Silva','Gosto de sempre estar fazendo algo','joao@email.com','11999999999','12345',5,NULL,NULL,NULL,'ATIVO','2026-08-14 02:25:45','Londrina'),(2,'Carlos Oliveira',NULL,'carlos@email.com','11988887777','12345',5,NULL,NULL,NULL,'ATIVO','2026-08-12 23:10:16',NULL),(3,'Maria José',NULL,'maria@email.com','11988888888','12345',1.5,NULL,NULL,NULL,'ARQUIVADO','2026-05-20 16:00:00',NULL),(4,'Ana Beatriz Souza','Encanadora e pedreira, atendo Londrina e regiao.','ana.souza@email.com','11977776666','12345',5,NULL,NULL,NULL,'ATIVO','2026-08-14 02:26:49','Londrina'),(5,'Rafael Costa','Cuido de jardim, poda e paisagismo.','rafael.costa@email.com','11966665555','12345',5,NULL,NULL,NULL,'ATIVO','2026-08-10 21:05:00','Curitiba'),(6,'Fernanda Lima',NULL,'fernanda.lima@email.com','11955554444','12345',5,NULL,NULL,NULL,'ATIVO','2026-08-13 10:40:00','Curitiba'),(7,'Bruno Almeida',NULL,'bruno.almeida@email.com','11944443333','12345',5,NULL,NULL,NULL,'ATIVO','2026-08-12 00:15:00','Maringá');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_dia_trabalho`
--

DROP TABLE IF EXISTS `usuario_dia_trabalho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_dia_trabalho` (
  `usuario_id` bigint NOT NULL,
  `dia_semana` enum('DOMINGO','SEGUNDA','TERCA','QUARTA','QUINTA','SEXTA','SABADO') COLLATE utf8mb4_unicode_ci NOT NULL,
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
INSERT INTO `usuario_dia_trabalho` VALUES (2,'SEGUNDA'),(2,'QUARTA'),(2,'SEXTA'),(4,'TERCA'),(4,'QUINTA'),(5,'DOMINGO'),(5,'SABADO');
/*!40000 ALTER TABLE `usuario_dia_trabalho` ENABLE KEYS */;
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
INSERT INTO `usuario_servico` VALUES (2,1,'AVANCADO'),(2,6,'BASICO'),(4,3,'INTERMEDIARIO'),(4,4,'AVANCADO'),(5,5,'INTERMEDIARIO');
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

-- Dump completed on 2026-08-13 23:28:03
