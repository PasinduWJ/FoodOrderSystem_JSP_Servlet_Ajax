-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: loginweb
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `forder`
--

DROP TABLE IF EXISTS `forder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forder` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uName` varchar(50) NOT NULL,
  `fId` int NOT NULL,
  `quantity` int NOT NULL,
  `cDateTime` datetime DEFAULT NULL,
  `uDateTime` datetime DEFAULT NULL,
  `updateBy` varchar(50) DEFAULT NULL,
  `delivery` tinyint(1) DEFAULT '0',
  `state` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forder`
--

LOCK TABLES `forder` WRITE;
/*!40000 ALTER TABLE `forder` DISABLE KEYS */;
INSERT INTO `forder` VALUES (2,'Pasindu',1,2,'2021-11-05 19:33:25','2021-11-05 19:33:25','Pasindu',0,1),(3,'Pasindu',3,1,'2021-11-05 19:33:25','2021-11-05 19:33:25','Pasindu',1,1),(4,'Pasindu',1,3,'2021-11-05 19:35:01','2021-11-07 18:15:15','Pasindu',0,0),(5,'Pasindu',1,2,'2021-11-05 20:11:44','2021-11-05 20:11:44','Pasindu',0,0),(6,'Pasindu',2,1,'2021-11-05 20:12:21','2021-11-05 20:12:21','Pasindu',0,0),(7,'Pasindu',2,2,'2021-11-05 20:12:29','2021-11-05 20:12:29','Pasindu',0,1),(8,'Pasindu',5,5,'2021-11-07 14:08:37','2021-11-07 16:10:10','Pasindu',0,0),(10,'Pasindu',5,4,'2021-11-07 16:12:07','2021-11-07 16:12:07','Pasindu',0,1),(11,'Desha',1,3,'2021-11-07 16:12:38','2021-11-07 16:12:49','Desha',0,1),(12,'Desha',5,2,'2021-11-07 16:13:01','2021-11-07 16:13:26','Desha',0,0),(13,'Pasindu',1,2,'2021-11-07 18:13:35','2021-11-07 18:13:59','Pasindu',0,1);
/*!40000 ALTER TABLE `forder` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-09 10:31:52
