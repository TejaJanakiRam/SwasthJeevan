-- MySQL dump 10.13  Distrib 8.4.6, for Linux (aarch64)
--
-- Host: localhost    Database: app_db
-- ------------------------------------------------------
-- Server version	8.4.6

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `app_db`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `app_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `app_db`;

--
-- Table structure for table `consent`
--

DROP TABLE IF EXISTS `consent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consent` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `doctor_id` bigint DEFAULT NULL,
  `ehr_id` bigint DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `patient_id` bigint DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `type` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `consent_chk_1` CHECK ((`status` between 0 and 3)),
  CONSTRAINT `consent_chk_2` CHECK ((`type` between 0 and 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consent`
--

LOCK TABLES `consent` WRITE;
/*!40000 ALTER TABLE `consent` DISABLE KEYS */;
/*!40000 ALTER TABLE `consent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consultation_record`
--

DROP TABLE IF EXISTS `consultation_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consultation_record` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `diagnosis` tinyint DEFAULT NULL,
  `disease` tinyint DEFAULT NULL,
  `doctor_id` bigint DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `issue_date` datetime(6) DEFAULT NULL,
  `medicines` varchar(2048) DEFAULT NULL,
  `notes` varchar(2048) DEFAULT NULL,
  `organization_id` bigint DEFAULT NULL,
  `patient_id` bigint DEFAULT NULL,
  `request_id` bigint DEFAULT NULL,
  `symptoms` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `consultation_record_chk_1` CHECK ((`diagnosis` between 0 and 6)),
  CONSTRAINT `consultation_record_chk_2` CHECK ((`disease` between 0 and 2))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultation_record`
--

LOCK TABLES `consultation_record` WRITE;
/*!40000 ALTER TABLE `consultation_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `consultation_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consultation_request`
--

DROP TABLE IF EXISTS `consultation_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consultation_request` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `doctor_id` bigint DEFAULT NULL,
  `organization_id` bigint DEFAULT NULL,
  `patient_id` bigint DEFAULT NULL,
  `referring_doctor_id` bigint DEFAULT NULL,
  `referring_organization_id` bigint DEFAULT NULL,
  `speciality` tinyint DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `type` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `consultation_request_chk_1` CHECK ((`speciality` between 0 and 11)),
  CONSTRAINT `consultation_request_chk_2` CHECK ((`status` between 0 and 3)),
  CONSTRAINT `consultation_request_chk_3` CHECK ((`type` between 0 and 2))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultation_request`
--

LOCK TABLES `consultation_request` WRITE;
/*!40000 ALTER TABLE `consultation_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `consultation_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consultation_session`
--

DROP TABLE IF EXISTS `consultation_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consultation_session` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `closure_reason` tinyint DEFAULT NULL,
  `consultation_request_id` bigint DEFAULT NULL,
  `doctor_id` bigint DEFAULT NULL,
  `organization_id` bigint DEFAULT NULL,
  `patient_id` bigint DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `supervisor_doctor_id` bigint DEFAULT NULL,
  `type` tinyint DEFAULT NULL,
  `webrtc_session_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `consultation_session_chk_1` CHECK ((`closure_reason` between 0 and 2)),
  CONSTRAINT `consultation_session_chk_2` CHECK ((`status` between 0 and 2)),
  CONSTRAINT `consultation_session_chk_3` CHECK ((`type` between 0 and 3))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultation_session`
--

LOCK TABLES `consultation_session` WRITE;
/*!40000 ALTER TABLE `consultation_session` DISABLE KEYS */;
/*!40000 ALTER TABLE `consultation_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor` (
  `gender` tinyint DEFAULT NULL,
  `registration_num` varchar(255) DEFAULT NULL,
  `id` bigint NOT NULL,
  `org_id` bigint DEFAULT NULL,
  `spec_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbr2tb5kipupoks61pd101gvli` (`org_id`),
  KEY `FKtkhbe0k6vkxh22dft92utbbun` (`spec_id`),
  CONSTRAINT `FKbr2tb5kipupoks61pd101gvli` FOREIGN KEY (`org_id`) REFERENCES `organization` (`id`),
  CONSTRAINT `FKisrj7dti092bxya7p8jt7acs7` FOREIGN KEY (`id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKtkhbe0k6vkxh22dft92utbbun` FOREIGN KEY (`spec_id`) REFERENCES `speciality` (`id`),
  CONSTRAINT `doctor_chk_1` CHECK ((`gender` between 0 and 2))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ehr`
--

DROP TABLE IF EXISTS `ehr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ehr` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `diagnosis_type` tinyint DEFAULT NULL,
  `document` blob,
  `end_date` datetime(6) DEFAULT NULL,
  `issue_date` datetime(6) DEFAULT NULL,
  `type` tinyint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `ehr_chk_1` CHECK ((`diagnosis_type` between 0 and 6)),
  CONSTRAINT `ehr_chk_2` CHECK ((`type` between 0 and 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ehr`
--

LOCK TABLES `ehr` WRITE;
/*!40000 ALTER TABLE `ehr` DISABLE KEYS */;
/*!40000 ALTER TABLE `ehr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization`
--

DROP TABLE IF EXISTS `organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `organization` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `registration_num` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization`
--

LOCK TABLES `organization` WRITE;
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization_admin`
--

DROP TABLE IF EXISTS `organization_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `organization_admin` (
  `id` bigint NOT NULL,
  `org_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_39sntg70lgx2j7jqqr8ktlx4l` (`org_id`),
  CONSTRAINT `FK36yt2q3sxlvwtvqax243nksdh` FOREIGN KEY (`org_id`) REFERENCES `organization` (`id`),
  CONSTRAINT `FK740wwgw3csti72y4v1cyyet9g` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization_admin`
--

LOCK TABLES `organization_admin` WRITE;
/*!40000 ALTER TABLE `organization_admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `organization_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `address` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `gender` tinyint DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKbhxnsr0osyqj98qqcexec5edv` FOREIGN KEY (`id`) REFERENCES `user` (`id`),
  CONSTRAINT `patient_chk_1` CHECK ((`gender` between 0 and 2))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `speciality`
--

DROP TABLE IF EXISTS `speciality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `speciality` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `speciality_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `speciality`
--

LOCK TABLES `speciality` WRITE;
/*!40000 ALTER TABLE `speciality` DISABLE KEYS */;
INSERT INTO `speciality` VALUES (1,'Pulmonology','SPEC002'),(2,'Gastroentrology','SPEC003'),(3,'Cardiology','SPEC001'),(4,'Opthalmology','SPEC004'),(5,'Orthopedics','SPEC005'),(6,'Urology','SPEC006'),(7,'Hepatology','SPEC007'),(8,'Rhinology','SPEC008'),(9,'Otology','SPEC009'),(10,'Neurology','SPEC010');
/*!40000 ALTER TABLE `speciality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_admin`
--

DROP TABLE IF EXISTS `system_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_admin` (
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKacd976no712rkwu5cjbhmbx78` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_admin`
--

LOCK TABLES `system_admin` WRITE;
/*!40000 ALTER TABLE `system_admin` DISABLE KEYS */;
INSERT INTO `system_admin` VALUES (1);
/*!40000 ALTER TABLE `system_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `type` tinyint DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_chk_1` CHECK ((`type` between 0 and 3))
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,NULL,'$2a$10$AHjST1czQwVLOXsiRlCXse2lVietSX6JLZ.apHEU0Sv5939c//4SS',NULL,3,'admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-28  2:37:46
