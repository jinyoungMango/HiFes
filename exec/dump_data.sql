-- MySQL dump 10.19  Distrib 10.3.38-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: board
-- ------------------------------------------------------
-- Server version	10.3.38-MariaDB-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ARItem`
--

DROP TABLE IF EXISTS `ARItem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ARItem` (
  `itemId` bigint(20) NOT NULL AUTO_INCREMENT,
  `ARImage` varchar(255) NOT NULL,
  `ARLatitude` decimal(19,2) NOT NULL,
  `ARLongitude` decimal(19,2) NOT NULL,
  `fesAddress` tinytext NOT NULL,
  `festivalId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`itemId`),
  KEY `FKq4cinbw6d06chtuuas94wx8bw` (`festivalId`),
  CONSTRAINT `FKq4cinbw6d06chtuuas94wx8bw` FOREIGN KEY (`festivalId`) REFERENCES `OrganizedFestival` (`festivalId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ARItem`
--

LOCK TABLES `ARItem` WRITE;
/*!40000 ALTER TABLE `ARItem` DISABLE KEYS */;
/*!40000 ALTER TABLE `ARItem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Comment`
--

DROP TABLE IF EXISTS `Comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Comment` (
  `commentId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `content` text NOT NULL,
  `createdBy` bigint(20) DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  `postId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`commentId`),
  KEY `FK5j737efenxlssetaxq59xlcfu` (`parentId`),
  KEY `FKbsnt3bnnwaehdligan5csprgl` (`postId`),
  CONSTRAINT `FK5j737efenxlssetaxq59xlcfu` FOREIGN KEY (`parentId`) REFERENCES `Comment` (`commentId`),
  CONSTRAINT `FKbsnt3bnnwaehdligan5csprgl` FOREIGN KEY (`postId`) REFERENCES `Post` (`postId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Comment`
--

LOCK TABLES `Comment` WRITE;
/*!40000 ALTER TABLE `Comment` DISABLE KEYS */;
INSERT INTO `Comment` VALUES (2,'2023-08-17 17:04:43','2023-08-17 17:04:43','댓글',352,NULL,13),(3,'2023-08-17 17:04:50','2023-08-17 17:04:50','댓글',352,NULL,9),(4,'2023-08-17 17:05:16','2023-08-17 17:05:16','댓긍',352,NULL,14);
/*!40000 ALTER TABLE `Comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CompletedStampMission`
--

DROP TABLE IF EXISTS `CompletedStampMission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CompletedStampMission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `normal_user_id` bigint(20) DEFAULT NULL,
  `missionId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe3fwo3jli11b1xqb9ri70i02h` (`normal_user_id`),
  KEY `FKt4u3rikra4etbkk3oaxrf0xqn` (`missionId`),
  CONSTRAINT `FKe3fwo3jli11b1xqb9ri70i02h` FOREIGN KEY (`normal_user_id`) REFERENCES `normal_user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKt4u3rikra4etbkk3oaxrf0xqn` FOREIGN KEY (`missionId`) REFERENCES `StampMission` (`missionId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CompletedStampMission`
--

LOCK TABLES `CompletedStampMission` WRITE;
/*!40000 ALTER TABLE `CompletedStampMission` DISABLE KEYS */;
INSERT INTO `CompletedStampMission` VALUES (1,352,2),(2,352,4),(3,352,1),(4,352,12),(5,451,12),(6,301,12);
/*!40000 ALTER TABLE `CompletedStampMission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EventNotification`
--

DROP TABLE IF EXISTS `EventNotification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EventNotification` (
  `eventNotificationId` bigint(20) NOT NULL AUTO_INCREMENT,
  `programId` bigint(20) DEFAULT NULL,
  `normal_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`eventNotificationId`),
  KEY `FKk5dw7ambi3e66grt5yqnsyqpl` (`programId`),
  KEY `FKcnnr3xxnbvnt8a2s03hsgmlhk` (`normal_user_id`),
  CONSTRAINT `FKcnnr3xxnbvnt8a2s03hsgmlhk` FOREIGN KEY (`normal_user_id`) REFERENCES `normal_user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKk5dw7ambi3e66grt5yqnsyqpl` FOREIGN KEY (`programId`) REFERENCES `FestivalTable` (`programId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EventNotification`
--

LOCK TABLES `EventNotification` WRITE;
/*!40000 ALTER TABLE `EventNotification` DISABLE KEYS */;
/*!40000 ALTER TABLE `EventNotification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FestivalTable`
--

DROP TABLE IF EXISTS `FestivalTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FestivalTable` (
  `programId` bigint(20) NOT NULL AUTO_INCREMENT,
  `endTime` datetime NOT NULL,
  `programOutline` varchar(255) DEFAULT NULL,
  `programTitle` varchar(31) NOT NULL,
  `startTime` datetime NOT NULL,
  `festivalId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`programId`),
  KEY `FKfu7lwmmkrixf9xx3win19aikm` (`festivalId`),
  CONSTRAINT `FKfu7lwmmkrixf9xx3win19aikm` FOREIGN KEY (`festivalId`) REFERENCES `OrganizedFestival` (`festivalId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FestivalTable`
--

LOCK TABLES `FestivalTable` WRITE;
/*!40000 ALTER TABLE `FestivalTable` DISABLE KEYS */;
INSERT INTO `FestivalTable` VALUES (1,'2023-08-18 15:00:00','초등학생들을 위한 미술 체험 행사입니다','어린이 미술체험','2023-08-18 13:00:00',1),(2,'2023-08-19 12:00:00','중, 고등학생들을 위한 미술 경연대회','청소년 미술 전시 대회','2023-08-19 10:00:00',1),(3,'2023-08-20 18:00:00','미취학 어린이들을 위한 미술 오감체험','오감체험','2023-08-20 15:00:00',1),(4,'2023-08-18 15:00:00','초등학생들을 위한 미술 체험 행사입니다','어린이 미술체험','2023-08-18 13:00:00',2),(5,'2023-08-19 12:00:00','중, 고등학생들을 위한 미술 경연대회','청소년 미술 전시 대회','2023-08-19 10:00:00',2),(6,'2023-08-20 12:00:00','미취학 어린이들을 위한 미술 오감체험','오감체험','2023-08-20 10:00:00',2),(7,'2023-08-18 15:00:00','초등학생들을 위한 미술 체험 행사입니다','어린이 미술체험','2023-08-18 13:00:00',3),(8,'2023-08-19 12:00:00','중, 고등학생들을 위한 미술 경연대회','청소년 미술 전시 대회','2023-08-19 10:00:00',3),(9,'2023-08-20 12:00:00','미취학 어린이들을 위한 미술 오감체험','오감체험','2023-08-20 10:00:00',3),(10,'2023-08-18 19:00:00','초등학생들을 위한 미술 체험 행사입니다','팝업 스토어','2023-08-18 13:00:00',4),(11,'2023-08-19 21:00:00','중, 고등학생들을 위한 미술 경연대회','스탠드업 코미디','2023-08-20 19:00:00',4),(12,'2023-08-20 22:00:00','다양한 맥주들을 시음하고 가세요','맥주 시음회','2023-08-20 19:00:00',4),(13,'2023-08-30 17:00:00','어린이들이 봉사활동을 하면서 즐겁게 쓰레기 줍는 이벤트입니다','쓰줍쓰줍','2023-08-30 13:00:00',5),(14,'2023-08-31 21:00:00','환경 관련 퀴즈를 풉니다','지구의 날 퀴즈','2023-08-31 19:00:00',5),(15,'1899-12-31 00:00:00','','','1899-12-31 00:00:00',5),(16,'2023-08-22 17:00:00','친선 우호 도시 초정공연입니다','친선우호도시 초청공연','2023-08-22 13:00:00',6),(17,'2023-08-23 21:00:00','세계 음식을 먹는 행사','세계 음식전','2023-08-23 19:00:00',6),(18,'1899-12-31 00:00:00','','','1899-12-31 00:00:00',6),(19,'2023-08-18 19:00:00','맥주 관련 팝업스토어','팝업 스토어','2023-08-18 13:00:00',7),(20,'2023-08-19 21:00:00','유명 개그맨 초대','스탠드업 코미디','2023-08-19 19:00:00',7),(21,'2023-08-20 22:00:00','다양한 맥주들을 시음하고 가세요','맥주 시음회','2023-08-20 19:00:00',7),(22,'2023-08-18 16:00:00','어린이들과 함께 동심의 세계로 빠져들어 보아요','동요 대회','2023-08-18 14:00:00',8),(23,'2023-08-19 14:00:00','일반부, 청소년부로 나눠서 심사','백일장','2023-08-19 12:00:00',8),(24,'2023-08-18 16:00:00','어린이들과 함께 동심의 세계로 빠져들어 보아요','동요 대회','2023-08-18 14:00:00',9),(25,'2023-08-19 14:00:00','일반부, 청소년부로 나눠서 심사','백일장','2023-08-19 12:00:00',9),(26,'2023-08-18 16:00:00','천재 싱어송라이터 이무진의 공연','찐팬토크','2023-08-18 14:00:00',10),(27,'2023-08-19 14:00:00','류석영 교수님과의 특별한 시간','강연 투게더','2023-08-19 12:00:00',10),(28,'2023-08-18 16:00:00','천재 싱어송라이터 이무진의 공연','찐팬토크','2023-08-18 14:00:00',11),(29,'2023-08-19 14:00:00','류석영 교수님과의 특별한 시간','강연 투게더','2023-08-19 12:00:00',11);
/*!40000 ALTER TABLE `FestivalTable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GetGift`
--

DROP TABLE IF EXISTS `GetGift`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GetGift` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `itemId` bigint(20) DEFAULT NULL,
  `normal_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKai9cb8adx4yvpeq2hi4yydysq` (`itemId`),
  KEY `FKs6vlwc4kwywogund066wpr0ay` (`normal_user_id`),
  CONSTRAINT `FKai9cb8adx4yvpeq2hi4yydysq` FOREIGN KEY (`itemId`) REFERENCES `ARItem` (`itemId`) ON DELETE CASCADE,
  CONSTRAINT `FKs6vlwc4kwywogund066wpr0ay` FOREIGN KEY (`normal_user_id`) REFERENCES `normal_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GetGift`
--

LOCK TABLES `GetGift` WRITE;
/*!40000 ALTER TABLE `GetGift` DISABLE KEYS */;
/*!40000 ALTER TABLE `GetGift` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Hashtag`
--

DROP TABLE IF EXISTS `Hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Hashtag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Hashtag`
--

LOCK TABLES `Hashtag` WRITE;
/*!40000 ALTER TABLE `Hashtag` DISABLE KEYS */;
INSERT INTO `Hashtag` VALUES (1,'별바다'),(2,'부산'),(3,'20대'),(4,'바다'),(5,'싸피'),(6,'밋업'),(7,'이천'),(8,'모임');
/*!40000 ALTER TABLE `Hashtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `JoinedGroup`
--

DROP TABLE IF EXISTS `JoinedGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `JoinedGroup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `isLeader` bit(1) DEFAULT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `normalUserId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtd79r4p41umdsne2sljv7podr` (`groupId`),
  KEY `FKcokir9i5wya1a9394t4rlcwik` (`normalUserId`),
  CONSTRAINT `FKcokir9i5wya1a9394t4rlcwik` FOREIGN KEY (`normalUserId`) REFERENCES `normal_user` (`id`),
  CONSTRAINT `FKtd79r4p41umdsne2sljv7podr` FOREIGN KEY (`groupId`) REFERENCES `groups` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `JoinedGroup`
--

LOCK TABLES `JoinedGroup` WRITE;
/*!40000 ALTER TABLE `JoinedGroup` DISABLE KEYS */;
INSERT INTO `JoinedGroup` VALUES (1,'',1,300),(2,'\0',1,301),(3,'',2,352),(4,'\0',2,401),(5,'',3,352),(6,'\0',3,401),(7,'',4,352),(8,'',5,301),(9,'\0',5,401),(10,'',6,301),(11,'\0',6,300);
/*!40000 ALTER TABLE `JoinedGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Marker`
--

DROP TABLE IF EXISTS `Marker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Marker` (
  `markerId` bigint(20) NOT NULL AUTO_INCREMENT,
  `boothLatitude` decimal(10,7) DEFAULT NULL,
  `boothLongitude` decimal(10,7) DEFAULT NULL,
  `boothName` varchar(15) NOT NULL,
  `boothNo` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `festivalId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`markerId`),
  KEY `FK50pjvwqij8t3xr5pf9k86ksgj` (`festivalId`),
  CONSTRAINT `FK50pjvwqij8t3xr5pf9k86ksgj` FOREIGN KEY (`festivalId`) REFERENCES `OrganizedFestival` (`festivalId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Marker`
--

LOCK TABLES `Marker` WRITE;
/*!40000 ALTER TABLE `Marker` DISABLE KEYS */;
INSERT INTO `Marker` VALUES (1,36.1100000,128.4100000,'입구',0,'입구입니다',1),(2,36.1000000,128.4200000,'핫도그 부스',1,'맛있는 핫도그 판매',1),(3,36.1000000,128.4200000,'와따 짬뽕',2,'10년 전통의 중국집',1),(4,36.1000000,128.4200000,'안내 스태프 위치',3,'행사 안내 스태프 위치입니다',1),(5,36.1000000,128.4200000,'안전요원 위치',4,'안전요원 위치입니다',1),(6,36.1000000,128.4200000,'샌드위치 부스',5,'햄 샌드위치, 비건 샌드위치 등등 판매',1),(7,36.1000000,128.4200000,'음료 부스',6,'사이다, 콜라, 아이스티, 아메리카노 판매',1),(8,36.1000000,128.4200000,'화장실 위치',7,'화장실입니다',1),(9,36.0900000,128.4300000,'식당1',0,'식당1입니다.',2),(10,36.1000000,128.4000000,'화장실1',2,'화장실1 입니다.',2),(11,36.0894419,128.4326363,'판매1',1,'판매 1',3),(12,36.0938114,128.4467984,'먹거리1',2,'먹거리1',3),(13,36.0944356,128.4675694,'식당1',3,'식당1',3),(14,36.0922163,128.4395028,'스태프1',4,'스태프1',3),(15,36.0834074,128.4616471,'안전 요원1',5,'안전 요원1',3),(16,36.0859739,128.4452534,'화장실1',6,'화장실1',3),(17,36.0860432,128.4295464,'출입구1',1,'출입구1',3),(18,36.0869449,128.4117795,'화장실2',6,'화장실2',3),(19,36.1037807,128.4183670,'판매 부스',1,'판매 부스 입니다.',4),(20,36.1077854,128.4187318,'식당',3,'식당 입니다.',4),(22,37.5737323,126.9770554,'핫도그 부스',1,'핫도그를 팝니다',5),(23,37.5738853,126.9777850,'카페',2,'커피등 음료 판매',5),(24,37.5734516,126.9775597,'안내 스태프',3,'스태프 위치입니다',5),(25,37.5733156,126.9774524,'안전 요원 위치',4,'안전요원 위치입니다',5),(26,37.5731710,126.9774309,'화장실',5,'화장실입니다',5),(27,37.5728564,126.9765082,'행사 입구',6,'입구입니다',5),(28,36.0960653,128.4298257,'판매부스',1,'판매입니다',6),(29,36.0954411,128.4303406,'먹거리부스',2,'먹거리입니다',6),(30,36.0950943,128.4321860,'식당부스',3,'식당입니다',6),(31,36.0949556,128.4336022,'스태프 위치',4,'스태프 위치입니다',6),(32,36.0947475,128.4350613,'안전요원 위치',5,'안전요원 위치입니다',6),(33,36.0946435,128.4366063,'화장실',6,'화장실입니다',6),(34,36.0945395,128.4376362,'입구',7,'입구입니다',6),(35,36.0961693,128.4292678,'판매부스',1,'판매부스입니다',7),(36,36.0954758,128.4301261,'먹거리부스',2,'먹거리부스입니다',7),(37,36.0953370,128.4308556,'식당부스',3,'식당부스입니다',7),(38,36.0950596,128.4319714,'스태프 위치',4,'스태프 위치입니다',7),(39,36.0948169,128.4332160,'안전요원 위치',5,'안전요원 위치입니다',7),(40,36.0948169,128.4343747,'화장실',6,'화장실입니다',7),(41,36.0948516,128.4355334,'출입구',7,'출입구입니다',7),(42,36.0916005,128.4246436,'입구',7,'입구입니다',8),(43,36.0911583,128.4254161,'화장실',6,'화장실입니다',8),(44,35.8660692,128.5935638,'입구',7,'입구입니다',9),(45,35.8665387,128.5935799,'아뜰 카페',3,'카페 입니다',9),(46,35.8664170,128.5932526,'핫도그 부스',2,'핫도그 판매합니다',9),(47,35.8661344,128.5929147,'안내 스태프 위치',4,'안내해주는 스태프 위치입니다',9),(48,35.8661996,128.5930917,'안전요원 위치',5,'위급상황시 빠르게 달려나가겠습니다',9),(49,35.8659301,128.5938910,'화장실',6,'화장실입니다',9),(50,35.8663822,128.5936335,'토스트 부스',2,'토스트 판매',9),(51,37.5643780,127.0657614,'안전요원',5,'안전요원입니다',10),(52,36.1077765,128.4172997,'건전지 판매',1,'건전지 판매 부스입니다',11),(53,36.1072954,128.4172568,'커피 판매 부스',2,'아메리카노, 라떼 등 커피를 판매합니다',11),(54,36.1074991,128.4178898,'한식당',3,'한식을 판매합니다',11),(55,36.1084353,128.4173534,'안내 스태프 위치',4,'안내 스태프 위치입니다',11),(56,36.1081969,128.4167633,'안전 요원 위치',5,'안전요원 위치입니다',11),(57,36.1083096,128.4181580,'화장실',6,'화장실 위치입니다',11),(58,36.1076162,128.4184638,'입구',7,'입구',11);
/*!40000 ALTER TABLE `Marker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MyPic`
--

DROP TABLE IF EXISTS `MyPic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MyPic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `groupId` bigint(20) DEFAULT NULL,
  `normalUserId` bigint(20) DEFAULT NULL,
  `sharedPicId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlxe844x7j956x8koh4syya01t` (`groupId`),
  KEY `FK8aiv9egt9bgqp4kv4xdsl0ray` (`normalUserId`),
  KEY `FKnm6vglv2awp1w9vn50d7n4ymd` (`sharedPicId`),
  CONSTRAINT `FK8aiv9egt9bgqp4kv4xdsl0ray` FOREIGN KEY (`normalUserId`) REFERENCES `normal_user` (`id`),
  CONSTRAINT `FKlxe844x7j956x8koh4syya01t` FOREIGN KEY (`groupId`) REFERENCES `groups` (`id`),
  CONSTRAINT `FKnm6vglv2awp1w9vn50d7n4ymd` FOREIGN KEY (`sharedPicId`) REFERENCES `SharedPic` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MyPic`
--

LOCK TABLES `MyPic` WRITE;
/*!40000 ALTER TABLE `MyPic` DISABLE KEYS */;
INSERT INTO `MyPic` VALUES (1,1,NULL,1),(2,1,NULL,2),(3,2,NULL,3),(4,2,NULL,4),(5,3,NULL,5),(6,4,NULL,6);
/*!40000 ALTER TABLE `MyPic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrganizedFestival`
--

DROP TABLE IF EXISTS `OrganizedFestival`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrganizedFestival` (
  `festivalId` bigint(20) NOT NULL AUTO_INCREMENT,
  `fesAddress` tinytext NOT NULL,
  `fesEndDate` date NOT NULL,
  `fesLatitude` decimal(10,7) DEFAULT NULL,
  `fesLongitude` decimal(10,7) DEFAULT NULL,
  `fesOutline` text NOT NULL,
  `fesPosterPath` varchar(255) NOT NULL,
  `fesStartDate` date NOT NULL,
  `fesTitle` varchar(31) NOT NULL,
  `host_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`festivalId`),
  KEY `FKkql51ef9hiq4fd2w4jwbw25py` (`host_user_id`),
  CONSTRAINT `FKkql51ef9hiq4fd2w4jwbw25py` FOREIGN KEY (`host_user_id`) REFERENCES `host_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrganizedFestival`
--

LOCK TABLES `OrganizedFestival` WRITE;
/*!40000 ALTER TABLE `OrganizedFestival` DISABLE KEYS */;
INSERT INTO `OrganizedFestival` VALUES (1,'경상북도 구미시 진평동 번지 303호 1037-1 KR 킹덤오피스텔','2023-08-20',36.1000000,128.4100000,'행사 내용\n2023년 8월 17일부터 20일까지 인사동 마루아트센터에서 진행되는 \'대한민국 청년미술축제 : 아트그라운드2023\'은 폭넓은 장르의 청년 미술 작품 활동을 선보임으로써 청년 예술가와 관람객의 능동적인 소통을 하고자 기획되었다. 청년 예술가들이 장르와 장애 등의 제약에 구애받지 않고 자유롭게 예술작품을 전시할 기회를 얻음으로써 청년 예술가층에 대한 관심도 향상과 그로 인한 작품 활동 환경 개선 효과를 도모하고자 한다. 청년 아티스트의 작품 전시와 더불어 멘토링 프로그램, 아티스트 토크, 관람객 참여 프로그램 등을 기획하여 작가, 관람객 등 함께 모두가 어울려 즐길 수 있는 놀이터 같은 축제가 되기를 희망한다.','https://i9d104.p.ssafy.io/images/299d8013-0dd9-46d1-82d1-7ce062c0b328_미술축제.png','2023-08-17','대한민국 청년미술축제 ; 아트그라운드',1),(2,'경상북도 구미시 인동20길 70','2023-08-20',36.0900000,128.4200000,'신라 선화공주와 결혼한 백제무왕의 서동요 전설이 깃든 우리나라 최고(最古)의 인공연못 \"궁남지\"의 천만송이 연꽃을 배경으로 2023년 제21회 부여서동연꽃축제는 \"연꽃화원에 피어난 사랑이야기\"라는 주제로 펼쳐지는 대한민국 여름대표 축제이다.\n\n[행사내용]\n1. 대표프로그램\n\n- 궁남지 판타지 : 서동과 선화의 사랑과 연꽃을 모티브로 물위 수상무대에서 펼쳐지는 수상뮤지컬\n\n- 서동선화 별빛 퍼레이드 : 서동과 선화공주를 중심으로 전식의상을 착용한 연기자들이 펼치는 야간 퍼레이드\n\n- 궁남지 빛의 향연 : 연꽃화원에 피어난 사랑이야기 주제를 8가지 테마로 LED 조명과 홀로그램등으로 이루어진 빛의 향연\n\n- 별밤 드론아트쇼 : 궁남지의 환상적인 여름밤을 선사할 드론 아트쇼\n\n\n2. 공연프로그램 : 2030 궁남지 열린콘서트, 오늘은 궁남지 트롯, Falling in 연꽃정원 공연 등 7개 프로그램\n\n\n3. 경연프로그램 : 궁남지 RPG- 서동선화탐험대 등 3개프로그램\n\n\n4. 체험프로그램 : 연지 카누탐험,연쪽해설사와 함께하는 연꽃여행 등 22개 프로그램\n\n\n5. 이벤트프로그램 : 웰컴 투 마래방죽,연꽃정원 라디오방송국 등 5개 프로그램\n\n\n6. 시가지프로그램 : 연꽃축제와 함께하는 길거리 퍼포먼스 등 3개 프로그램\n\n\n7. 전시프로그램 : 시화전, 스토리보드 전시 2개 프로그램\n\n\n8. 연계프로그램 : 백마강테마파크, 부여 테마여행','https://i9d104.p.ssafy.io/images/bd8905b3-9267-448e-aa05-20a6bde51416_mahan.jpg','2023-08-18','연꽃 축제',2),(3,'경상북도 구미시 인동36길 45','2023-08-20',36.0899888,128.4319055,'테스트','https://i9d104.p.ssafy.io/images/d9d5384c-f2d6-42dc-b08b-783d043c657c_Busan.png','2023-08-18','별바다부산 페스타',3),(4,'경상북도 구미시 인동중앙로 28','2023-08-20',36.1075088,128.4220282,'울산 성남동 젊음의거리에서 처음으로 진행되는 수제맥주 페스티벌이다. \n\n여름 바캉스의 컨셉을 기반으로 축제를 기획하였다. 축제의 컨셉은 \"수제맥주와 함께 즐기는 도심 속 여름 바캉스\" 로 편안한 분위기에서 즐길 수 있게 구성하였다.\n\nMC조장길, 강태우, 뮤즈웨일 색소폰, 쓰바, 성영주, 오왠, 이제규, 박철현, DJ TAFF, DJ ONII, 프로젝트 와이브로, 노모밴드, 슈파스, 룬디마틴의 공연이 양일간 이루어질 예정이다. 이 외에도 맥주 마시기 대회, 건배사 대회, 맥주 브랜드 맞추기 대회 등 체험 프로그램들도 무대에서 함께 이루어질 예정이다.\n\n\n[행사내용]\n\n1. 메인프로그램 : 수제맥주 판매, 팝업스토어, 공연\n\n2. 부대프로그램 : 이벤트, 체험 프로그램 운영','https://i9d104.p.ssafy.io/images/6f8eff2b-693b-44be-8d51-baf685aa2dbb_Beer.png','2023-08-17','성남 비어 나잇',3),(5,'서울특별시 종로구 세종대로 172','2023-08-31',37.5724321,126.9769020,'4년 만에 광장에서 만나는 지구의 날 기념행사! \n\n드레스코드는 초록 계열 의상 또는 악세서리! 소지품코드는 일회용품 사용 안 하기!\n\n지구의 소중함을 일깨우고 재미있고 다양한 프로그램이 함께하는 흥겨운 축제의 장에 \n\n시민 여러분의 많은 관심과 참여 부탁드립니다~','https://i9d104.p.ssafy.io/images/dfaab8ff-7089-451c-ae4f-d43acd80fef7_지구의날.jpg','2023-08-30','2023 지구의날 쓰레기를 위한 지구는 없다',3),(6,'서울특별시 종로구 세종대로 175 세종이야기','2023-08-23',37.5725254,126.9756429,'세계도시와 전 세계인이 함께 즐기는 국내 최대 규모의 세계도시 문화교류축제, \n\n서울세계도시문화축제가 광화문광장과 청계광장, 청계천로 일원에서 더욱 다양한 공연과 새로운 프로그램을 갖추고 돌아왔다. \n\n마주하는 세계, 하나되는 우리 ! 서울 도심 속 세계 여행으로 모두를 초대한다.\n\n[행사내용]\n친선우호도시 초청공연, 세계 관광 홍보전, 세계 음식전, 세계 의상 체험전 등','https://i9d104.p.ssafy.io/images/eda8b25d-6462-4204-b49b-3d32793db941_서울세계.jpg','2023-08-22','서울 세계 도시 문화축제',3),(7,'강원특별자치도 홍천군 홍천읍 갈마곡리 도시산림공원 토리숲','2023-08-20',37.6892075,127.8931489,'홍천강 별빛음악 맥주축제는 전국 최대 규모의 맥주공장인 하이트 강원공장에서\n\n갓 생상된 생맥주와 홍천군에 소재한 수제맥주사(브라이트바흐, 에이앤씨브루잉, 용오름), \n\n마을에서 생산한 시골수제맥주, 세계맥주인 블랑과 파올라너를 맛볼 수 있다.\n\n또 전국 최초의 Wet(젖다) Dance 대회와 함께 신나는 EDM, 렙, 힙합 등 다양한 장르의 무대공연을 관람할 수 있다.\n\n[행사내용]\n\n1. 판매행사 : 홍천수제맥주, 하이트맥주, 세계맥주, 푸드트럭, 치킨, 일반 안주용 음식 등\n\n2. 향토음식 : 한우꼬치, 옥수수구이 등\n\n3. 체험행사 : 테라를 찾아라, 홍천맥주를 잡아라, 사진인화이벤트, LED포토존, 캐릭터포토존\n\n4. 무대이벤트 : 맥주 빨리 마시기, 맥주사진 찍기 등\n\n5. 시음존운영 : 메인존(공연), 재즈존(공원 분수대), 술기행(민속주) 등\n\n6. 연관행사 : 세계 Wet Dance 경연대회, 세계 Wet Dance 참가팀 퍼레이드[시내권], 시장연계 , 시내권 축제장 이원화 행사','https://i9d104.p.ssafy.io/images/6d11d4e6-2573-41d0-9448-0590160989fb_맥주 축제.png','2023-08-18','별빛 음악 맥주 축제',3),(8,'경북 구미시 인동26길 65 구미진평주공미래타운','2023-08-19',36.0912709,128.4241279,'도심 속 즐길거리가 가득한 강변축제에 모십니다.','https://i9d104.p.ssafy.io/images/575bd210-1818-4399-8b08-3333c88ed405_KakaoTalk_20230818_034419972.png','2023-08-18','2023 강변 축제',1),(9,'대구광역시 중구 수동 56-1','2023-08-19',35.8695864,128.5888258,'이천에서 도자기가 만들어졌다는 사실은 효양산과 장동리, 설봉산성 등에서 출토된 유물들로 확인할 수 있습니다. \n\n이 세지역에서는 대형 항아리와 옹기, 연대가 훨씬 오랜 무문토기, 선사시대 토기 파편 및 삼국시대 각 나라의 기와와 토기파편들도 함께 출토되었습니다.\n\n따라서 이천은 적어도 청동기시대부터 토기제작이 활발하게 이루어진 역사적인 배경을 갖고 있으며 \n\n그 후 백제와 고구려 점령기를 거친 후 삼국시대 후반까지 패권 각축장이었던 탓으로 삼국 토기문화의 흔적들이 혼재되어 나타났습니다.','https://i9d104.p.ssafy.io/images/d01e3671-569b-47df-9da4-5984eb279f75_dofestival.jpg','2023-08-18','이천 도자기 축제',4),(10,'경기도 과천시 대공원광장로 102','2023-08-19',37.4359624,127.0142283,'서울 세계 불꽃축제\n\n최고의 불꽃놀이를 감상하세요','https://i9d104.p.ssafy.io/images/c9224552-2d8f-43bc-9666-a50433a4f8cb_KakaoTalk_20230818_034451892.png','2023-08-18','서울 세계 불꽃축제',4),(11,'경상북도 구미시 인동가산로 14','2023-08-19',36.1071265,128.4179320,'모두가 하나되는 Meet Up 이번에는 대전으로 갑니다\n\nSSAFY 자문위원 류석영 교수님의 강연\n\n천재 싱어송 라이터 이무진과의 찐팬 토크\n\n이미지 컨설턴트 서혜전님의 퍼스널 컬러 컨설팅 등\n\n싸피인 여러분께 도움되는 행사로 가득채웠습니다!\n\n많은 참여 바랍니다\n\n대전에서 봐요!!!','https://i9d104.p.ssafy.io/images/91f5c268-b6d5-422c-aa41-a9e6c7c3625a_poster.JPG','2023-08-18','싸피 밋업',4);
/*!40000 ALTER TABLE `OrganizedFestival` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ParticipatedFes`
--

DROP TABLE IF EXISTS `ParticipatedFes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ParticipatedFes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `countMission` bigint(20) NOT NULL,
  `isCompleted` bit(1) NOT NULL,
  `participateTime` datetime NOT NULL,
  `normal_user_id` bigint(20) DEFAULT NULL,
  `festivalId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl8fm13am9wnmboyyfu0smejax` (`normal_user_id`),
  KEY `FKg5jg9p05k2m95lgufv944b365` (`festivalId`),
  CONSTRAINT `FKg5jg9p05k2m95lgufv944b365` FOREIGN KEY (`festivalId`) REFERENCES `OrganizedFestival` (`festivalId`) ON DELETE CASCADE,
  CONSTRAINT `FKl8fm13am9wnmboyyfu0smejax` FOREIGN KEY (`normal_user_id`) REFERENCES `normal_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ParticipatedFes`
--

LOCK TABLES `ParticipatedFes` WRITE;
/*!40000 ALTER TABLE `ParticipatedFes` DISABLE KEYS */;
INSERT INTO `ParticipatedFes` VALUES (1,2,'','2023-08-17 17:14:09',352,1),(2,1,'','2023-08-17 17:18:13',352,3),(3,3,'\0','2023-08-17 20:28:05',352,9),(4,3,'\0','2023-08-17 23:39:55',451,9),(5,3,'\0','2023-08-18 01:17:46',301,9);
/*!40000 ALTER TABLE `ParticipatedFes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Post`
--

DROP TABLE IF EXISTS `Post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Post` (
  `postId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `content` text NOT NULL,
  `createdBy` bigint(20) DEFAULT NULL,
  `hideReason` varchar(255) DEFAULT NULL,
  `imagePath` varchar(255) DEFAULT NULL,
  `isHidden` bit(1) DEFAULT NULL,
  `postType` varchar(255) NOT NULL,
  `rating` float DEFAULT NULL,
  `title` varchar(31) NOT NULL,
  `views` int(11) NOT NULL,
  `festivalId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`postId`),
  KEY `FKaq15p1d9d8vbnayy93bqnniew` (`festivalId`),
  CONSTRAINT `FKaq15p1d9d8vbnayy93bqnniew` FOREIGN KEY (`festivalId`) REFERENCES `OrganizedFestival` (`festivalId`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Post`
--

LOCK TABLES `Post` WRITE;
/*!40000 ALTER TABLE `Post` DISABLE KEYS */;
INSERT INTO `Post` VALUES (3,'2023-08-17 08:59:39','2023-08-17 18:00:15','공지입니다~~',3,NULL,NULL,'\0','notice',NULL,'공지입니다.',3,3),(5,'2023-08-17 12:50:02','2023-08-17 17:25:09','화장실 상태가 너무 더럽습니다... 청소 해주실수 있나요....',301,NULL,NULL,'\0','ask',NULL,'미술 전시회장 화장실 위생 관련 문의',8,1),(6,'2023-08-17 12:51:05','2023-08-17 15:09:34','바디 페인팅 전시 일정이 변경되었습니다.\n확인 바랍니다. 감사합니다.',1,NULL,NULL,'\0','notice',NULL,'바디 페인팅 전시 관련 공지',2,1),(9,'2023-08-17 13:44:57','2023-08-17 18:00:41','별바다축제!!',352,NULL,NULL,'\0','free',NULL,'재밌어요',5,3),(10,'2023-08-17 13:52:20','2023-08-17 20:13:40','문의해주신 화장실 위생 사항 조취하였습니다.',1,NULL,NULL,'\0','notice',NULL,'화장실 관련 공지입니다',3,1),(12,'2023-08-17 14:48:33','2023-08-17 14:48:35','인형 굿즈 부스 위치 주차장 옆으로 변경되었습니다',3,NULL,NULL,'\0','notice',NULL,'인형 굿즈 위치 변경',1,4),(13,'2023-08-17 17:04:11','2023-08-17 18:00:23','문의입니다',352,NULL,'/images/82aa45a8-3c3a-4000-9537-0a3a0c9fc951_20233318241037c00cb3-27d6-4f08-9c6f-657e93a7f204mobile.jpeg','','ask',NULL,'문의 있습니다',9,3),(14,'2023-08-17 17:05:10','2023-08-17 18:00:47','후기입니다..',352,NULL,'/images/cef01f7c-0782-4300-9d71-602bb28bb7f2_20233318259cee231e1-e4b1-43f6-b4a9-059a1fb290e7mobile.jpeg','\0','review',4,'후기입니다',3,3),(15,'2023-08-17 17:23:42','2023-08-17 17:23:49','미술 전시 일정이 변경되었습니다',1,NULL,NULL,'\0','notice',NULL,'일정 변경 공지입니다',1,1),(16,'2023-08-17 17:24:22','2023-08-17 17:24:44','캐리커쳐 부스가 우천으로 중단되었습니다. 죄송합니다',1,NULL,NULL,'\0','notice',NULL,'부스 운영 중단 안내입니다',1,1),(17,'2023-08-17 17:25:47','2023-08-17 17:25:47','너무 더럽습니다',401,NULL,NULL,'','ask',NULL,'쓰레기통 비워주세요',0,1),(18,'2023-08-17 17:34:28','2023-08-17 20:13:44','옥수수 구이 먹거리 부스가 추가되었습니다! 많은 이용 바랍니다.',1,NULL,NULL,'\0','notice',NULL,'먹거리 부스 추가 안내입니다',1,1),(19,'2023-08-17 17:36:08','2023-08-17 20:13:48','캐리커쳐 부스 시작 시간이 9시로 변경되었습니다',1,NULL,NULL,'\0','notice',NULL,'캐리커쳐 부스 안내입니다',1,1),(20,'2023-08-17 19:02:20','2023-08-17 19:02:20','먹거리 부스가 취소되었습니다',1,NULL,NULL,'\0','notice',NULL,'먹거리 부스 취소 안내',0,1),(21,'2023-08-17 20:26:54','2023-08-17 20:26:54','먹거리부스가 10시에 마감되었습니다 죄송합니다.',4,NULL,NULL,'\0','notice',NULL,'먹거리 부스 안내',0,9),(22,'2023-08-18 01:17:07','2023-08-18 02:49:02','컨설팅 부스 취소되었습니다',4,NULL,NULL,'\0','notice',NULL,'밋업 부스 안내',1,11),(23,'2023-08-18 02:27:42','2023-08-18 02:27:42','후기 게시판 내용 작성',300,NULL,'/images/b2cf5131-e67d-4e62-b49d-cf9d52308a58_202333181127439523df78-7bd0-4ffe-a2a0-9293a190dcb9mobile.jpeg','\0','review',3.5,'후기 게시판 작성',0,11),(24,'2023-08-18 02:28:18','2023-08-18 02:28:18','ㅇ',300,NULL,'/images/6077058c-c144-44cf-a221-01f98d294bcb_20233318112818fa09cc4c-a08c-4d2f-af46-353d970caaaemobile.jpeg','\0','review',5,'ㅇ',0,11),(25,'2023-08-18 02:48:50','2023-08-18 02:48:50','아주 좋았어요 맛있는게 많아요',301,NULL,'/images/c0864f23-7afb-47c2-9ccb-e83c33d717c2_20233318114850cd6e8d47-9db6-48c7-925c-7e78aa5e6dc1mobile.jpeg','\0','review',5,'후기입니다',0,11),(26,'2023-08-18 02:49:45','2023-08-18 02:49:45','최고의 미술축제',301,NULL,NULL,'\0','review',5,'최고예요',0,1),(27,'2023-08-18 02:58:47','2023-08-18 02:58:51','ㅛㅅㅅ',301,NULL,'/images/f0b39540-21a1-4b34-9c75-0e2354099d56_2023331811584620bd8199-31eb-49f9-a566-87f951a5a5f3mobile.webp','\0','review',5,'ㅅㄱㄱ',1,1);
/*!40000 ALTER TABLE `Post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RegisteredHashtag`
--

DROP TABLE IF EXISTS `RegisteredHashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RegisteredHashtag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `groupId` bigint(20) DEFAULT NULL,
  `hashtagId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9wlvqqtqmkq5l5exga41n5p8u` (`groupId`),
  KEY `FKg1gyaouautilpde4yuf6hgfj2` (`hashtagId`),
  CONSTRAINT `FK9wlvqqtqmkq5l5exga41n5p8u` FOREIGN KEY (`groupId`) REFERENCES `groups` (`id`),
  CONSTRAINT `FKg1gyaouautilpde4yuf6hgfj2` FOREIGN KEY (`hashtagId`) REFERENCES `Hashtag` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RegisteredHashtag`
--

LOCK TABLES `RegisteredHashtag` WRITE;
/*!40000 ALTER TABLE `RegisteredHashtag` DISABLE KEYS */;
INSERT INTO `RegisteredHashtag` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,NULL),(5,2,4),(6,2,3),(7,2,1),(8,2,NULL),(9,3,5),(10,3,6),(11,3,NULL),(12,4,7),(13,4,8),(14,4,3),(15,4,NULL),(16,5,6),(17,5,5),(18,5,NULL),(19,6,5),(20,6,6),(21,6,NULL);
/*!40000 ALTER TABLE `RegisteredHashtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SharedPic`
--

DROP TABLE IF EXISTS `SharedPic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SharedPic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sharedPic` varchar(255) DEFAULT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `normalUserId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl9yudodag23qp1lo93et0i66f` (`groupId`),
  KEY `FKaf6r37m1u2de8py53fn4eelrc` (`normalUserId`),
  CONSTRAINT `FKaf6r37m1u2de8py53fn4eelrc` FOREIGN KEY (`normalUserId`) REFERENCES `normal_user` (`id`),
  CONSTRAINT `FKl9yudodag23qp1lo93et0i66f` FOREIGN KEY (`groupId`) REFERENCES `groups` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SharedPic`
--

LOCK TABLES `SharedPic` WRITE;
/*!40000 ALTER TABLE `SharedPic` DISABLE KEYS */;
INSERT INTO `SharedPic` VALUES (1,'https://i9d104.p.ssafy.io/images/2023331710947a056372b-63c0-41fe-b8d0-b1f307c89667mobile.jpeg',1,NULL),(2,'https://i9d104.p.ssafy.io/images/20233317101728b806c2b5-49fd-47f3-865e-9bf7fb13da5emobile.jpeg',1,NULL),(3,'https://i9d104.p.ssafy.io/images/2023331826285538b1bd-63e4-43c0-8900-07f5e58ca365mobile.jpeg',2,NULL),(4,'https://i9d104.p.ssafy.io/images/20233318255452fdc4b54-1970-4cf8-9f98-dced4e9eecbfmobile.webp',2,NULL),(5,'https://i9d104.p.ssafy.io/images/2023331835729fa65a86a-0067-4652-85c5-508b3fb8d47bmobile.jpeg',3,NULL),(6,'https://i9d104.p.ssafy.io/images/20233318524499c840a5-7313-4b26-9f7c-9f827f50c80fmobile.jpeg',4,NULL);
/*!40000 ALTER TABLE `SharedPic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StampMission`
--

DROP TABLE IF EXISTS `StampMission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `StampMission` (
  `missionId` bigint(20) NOT NULL AUTO_INCREMENT,
  `missionLatitude` decimal(10,7) DEFAULT NULL,
  `missionLongitude` decimal(10,7) DEFAULT NULL,
  `missionOutline` varchar(255) NOT NULL,
  `missionTitle` varchar(31) NOT NULL,
  `festivalId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`missionId`),
  KEY `FK1fn0eykqmfp3abn9efvlp55es` (`festivalId`),
  CONSTRAINT `FK1fn0eykqmfp3abn9efvlp55es` FOREIGN KEY (`festivalId`) REFERENCES `OrganizedFestival` (`festivalId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StampMission`
--

LOCK TABLES `StampMission` WRITE;
/*!40000 ALTER TABLE `StampMission` DISABLE KEYS */;
INSERT INTO `StampMission` VALUES (1,36.1000000,128.4200000,'무료로 캐리커쳐를 받고 스탬프도 받으세요!','캐리커쳐 스탬프',1),(2,36.1000000,128.4200000,'어린이 핑거프린팅 체험하고 스탬프 받으세요!','핑거프린팅 스탬프',1),(3,36.1100000,128.4300000,'스탬프1 입니다.','스탬프1',2),(4,36.0999145,128.4173585,'스탬프1','스탬프1',3),(5,36.1058091,128.4225083,'스탬프 미션 입니다.','스탬프 미션',4),(6,37.5727459,126.9772163,'스탬프 입니다','지구지키기 스탬프',5),(7,36.0947475,128.4389666,'스탬프입니다','스탬프',6),(8,36.0951637,128.4367350,'스탬프입니다','스탬프',7),(9,36.0912797,128.4250942,'스탬프','스탬프',8),(10,35.8657605,128.5940627,'그림 속 윌리를 찾으면 스탬프를 드립니다','윌리를 찾아라 스탬프',9),(11,35.8662257,128.5941539,'달리기 하면 스탬프 드립니다','달려라 부스',9),(12,35.8659692,128.5942021,'틀린그림을 찾으면 스탬프를 드립니다','틀린그림을 찾아라',9),(13,36.1079542,128.4183941,'컨설팅 후 받는 스탬프입니다','컨설팅 스탬프',11);
/*!40000 ALTER TABLE `StampMission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserJoinFes`
--

DROP TABLE IF EXISTS `UserJoinFes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserJoinFes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `normalUserId` bigint(20) DEFAULT NULL,
  `festivalId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKna7iyr4wf914jk2cbhy07m3uk` (`normalUserId`),
  KEY `FKcfy9hiwea4yphsba5l3bw598m` (`festivalId`),
  CONSTRAINT `FKcfy9hiwea4yphsba5l3bw598m` FOREIGN KEY (`festivalId`) REFERENCES `OrganizedFestival` (`festivalId`),
  CONSTRAINT `FKna7iyr4wf914jk2cbhy07m3uk` FOREIGN KEY (`normalUserId`) REFERENCES `normal_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserJoinFes`
--

LOCK TABLES `UserJoinFes` WRITE;
/*!40000 ALTER TABLE `UserJoinFes` DISABLE KEYS */;
INSERT INTO `UserJoinFes` VALUES (27,301,1),(28,301,3),(33,300,1),(41,352,3),(42,352,4),(45,352,9),(46,301,4),(47,300,8),(48,301,11);
/*!40000 ALTER TABLE `UserJoinFes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `festivalId` bigint(20) DEFAULT NULL,
  `getterLatitude` decimal(19,2) DEFAULT NULL,
  `getterLongitude` decimal(19,2) DEFAULT NULL,
  `getterOutline` varchar(255) DEFAULT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  `groupPic` varchar(255) DEFAULT NULL,
  `maxPop` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (1,'가취가욥','2023-08-17 09:00:27',3,36.10,128.43,'ㅎ','별바다 부산 모임','https://i9d104.p.ssafy.io/images/202333176030c2756b50-4ec4-4a53-baf5-2d940f78db8cmobile.jpeg',4),(2,'같이 바다 즐겨요~~','2023-08-17 16:01:57',3,36.09,128.44,'진영씨 와줘요 ㅠ.ㅠ','바다 좋아 모임','https://i9d104.p.ssafy.io/images/2023331811572b7bde65-50e8-4139-965b-5b2051762157mobile.webp',5),(3,'싸피 밋업 같이갈 친구!!','2023-08-17 18:57:03',1,36.11,128.42,'신한은행으로 와','같이 가자 싸피','https://i9d104.p.ssafy.io/images/20233318357353e4d744-cf50-4733-ac04-6b3f0f0b0cb0mobile.webp',5),(4,'이천에 모여라','2023-08-17 20:23:34',9,35.87,128.59,'스시네코 앞으로','이천모임','https://i9d104.p.ssafy.io/images/20233318523338370ba92-21ad-46fe-9484-97cd62970697mobile.webp',5),(5,'같이 밋업 갈사람!','2023-08-18 00:02:31',8,36.11,128.42,'여기로 와','강변 모임','https://i9d104.p.ssafy.io/images/2023331892300d4a0b0b-713f-462a-ad0c-11ecd265dec9mobile.webp',6),(6,'같이가요','2023-08-18 01:14:20',11,36.11,128.42,'여기로 모이세요~','밋업','https://i9d104.p.ssafy.io/images/20233318101420d9856761-5409-446b-8e68-052179391b35mobile.webp',5);
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `host_user`
--

DROP TABLE IF EXISTS `host_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `host_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `orgCode` varchar(255) DEFAULT NULL,
  `orgNo` varchar(255) DEFAULT NULL,
  `organization` varchar(255) DEFAULT NULL,
  `phoneNo` varchar(255) DEFAULT NULL,
  `refreshToken` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `host_user`
--

LOCK TABLES `host_user` WRITE;
/*!40000 ALTER TABLE `host_user` DISABLE KEYS */;
INSERT INTO `host_user` VALUES (1,'vmfpel0425@naver.com','김도연','123','0269585385','사단법인 청년미술협회/사단법인 청년미술협회','01023458787','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE2OTM1MDM2NjB9.pveWqM8QHNhNyt8wFBvbuCzhu4mY0vJcQXJn4yvOZPXiS34aT0gh7OIU36WJfvr2jjR83tps6Lln1XXMW43p5g'),(2,'9132225@naver.com','김진영','D104','0536569963','SSAFY','01063631556','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE2OTM0NjczMzl9.enEcqUsCSnN5vVhOauwwKYEQvYj8cRYM2TBzNL-BBkEghQ3M14PAfd53malIfMwqLF-C4ajpVpziLZgxeGD1ow'),(3,'leehyunk6310@naver.com','이현근','Test','0101112222','Test','01011112222','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE2OTM0OTMzOTF9.33bH7MjmVnlcq7ylh9QQrvdPXnlk_o7wX2qyuIHsue9yoXrc2xldAfRfwi6igcaHdn5zpFW6FhcjW8lQ1Ljgmw'),(4,'hifes@kakao.com','hifes','198293','0223589878','Hifes','01088679809','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE2OTM1Mjk0MTF9.TUcDOPvcotjr-XkJ0Y7d3QeD_a28KbGj8xl23rbV0Lrsk0i7GErVI9UGH6wvF5bNRnDSZz6kan7ks-xA1ebVBg');
/*!40000 ALTER TABLE `host_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `normal_sequnce_name`
--

DROP TABLE IF EXISTS `normal_sequnce_name`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `normal_sequnce_name` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `normal_sequnce_name`
--

LOCK TABLES `normal_sequnce_name` WRITE;
/*!40000 ALTER TABLE `normal_sequnce_name` DISABLE KEYS */;
INSERT INTO `normal_sequnce_name` VALUES (550);
/*!40000 ALTER TABLE `normal_sequnce_name` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `normal_user`
--

DROP TABLE IF EXISTS `normal_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `normal_user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firebaseToken` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `phoneNo` varchar(255) DEFAULT NULL,
  `profilePic` varchar(255) DEFAULT NULL,
  `refreshToken` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `normal_user`
--

LOCK TABLES `normal_user` WRITE;
/*!40000 ALTER TABLE `normal_user` DISABLE KEYS */;
INSERT INTO `normal_user` VALUES (300,'9132225@naver.com','ecCHn9R1TLqc6sjhi8h_OK:APA91bGtajTkXgojlvZnfBbWCsBd7UbjarHMH5HOHRo-RcGK-VWVFjqpn12TyPSizAP-GQHPZJUrSz3yPGGGlYD9XoSLZwmARG3gXyV9xxQXBEm4OD9hr-0LMRD4Gikx7Awc-yMsCfr4','김진영','진영',NULL,'https://i9d104.p.ssafy.io/images/2023331742751168869b0-37d8-4b7e-881f-fb1193cd1ac9mobile.jpeg','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE2OTM1Mjc2ODB9.7JD67Iw_MlKQq-U4PyDguQgAdajKfhBDoDMMoKDqwJU6YCXiOidHprMkm_NetoRTMAIV6Q77RvGbadykZWkwyA'),(301,'vmfpel0425@naver.com','cwAPpspHQNCLFuXTaBVmUV:APA91bFJgtBPzLMdK_6UfTDP_pETKMcYeLeeYc7Mi2ZOJG_cWHmPMM6sDRN8pdUUVNCWD0j73-HQ5sQUapw7n5pC7fXEe0jbu3Vy7X-Yyptpu81yM02Nz03drG2e0EzxSXvc52BhZJnF','김도연','도도',NULL,'https://i9d104.p.ssafy.io/images/2023331743254290487b7-e081-4c0b-93ff-1be6bec2e3eamobile.webp','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE2OTM1MzA3Nzd9.7hsiZJeswUui6tN7tnaCkrSgCvL5B3eMqoT9GxObEnkrG2yHuBSzaKrsgxqqD4JRSiHIj1oDhJ01YxkAN2YuoQ'),(351,'sour97@naver.com','fXoz3BFgStC4x0mtl-q9I0:APA91bF2BqEp44wornz9dAgFst5UMN6JdLT6bdkHWkiu-QlRM3vV6THK-W3u0d-S7U9BKAOMRIthRLG1A6vaotjNvtzBwlDqJjLDlzEExu6mnMFZketr1-NCKn4C0DSquqE7j9HKiEw6','예원','정빵순',NULL,'https://i9d104.p.ssafy.io/images/2023331795035d20777fc-bf49-4a7c-b459-0b5e2ef53abfmobile.jpeg','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE2OTM1MDk5NTV9.IEXK5NTDDwIWNtBL6NEaog8EFuFbBjdDugJXxHzejJjpfQbVm5rkoyuU-GMd2Vbr66YAE_J9wRAVkV7srxdm5Q'),(352,'vmfpel0425@gmail.com','ecCHn9R1TLqc6sjhi8h_OK:APA91bGtajTkXgojlvZnfBbWCsBd7UbjarHMH5HOHRo-RcGK-VWVFjqpn12TyPSizAP-GQHPZJUrSz3yPGGGlYD9XoSLZwmARG3gXyV9xxQXBEm4OD9hr-0LMRD4Gikx7Awc-yMsCfr4','하위','고로케',NULL,'https://i9d104.p.ssafy.io/images/20233317952465be9adca-39e4-4c0e-9e27-81273f7e2ad1mobile.jpeg','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE2OTM1MzU0ODF9.fTC8rOEiN7i-6gaw8a3i1-l6y2oCYd8cam6ibvmBzcDXYY26Oj59l2n9INmLq8OnySDtwD5vuLxPtMJLCD-bfg'),(401,'hifes@kakao.com','ecCHn9R1TLqc6sjhi8h_OK:APA91bGtajTkXgojlvZnfBbWCsBd7UbjarHMH5HOHRo-RcGK-VWVFjqpn12TyPSizAP-GQHPZJUrSz3yPGGGlYD9XoSLZwmARG3gXyV9xxQXBEm4OD9hr-0LMRD4Gikx7Awc-yMsCfr4','hifes','야옹',NULL,'https://i9d104.p.ssafy.io/images/2023331814308ffef96d-1757-47ba-8139-cba25d29da70mobile.jpeg','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE2OTM0OTc4NzB9.XXTaAIOtcOgVHedY57SskA1NTqcqEyrRZg4grVis-sA4q0IwZmh9RltQK6pZ4JpWxsFAadDfpmJewJR4RcncPA'),(451,'dodoyeon0425@gmail.com','cwAPpspHQNCLFuXTaBVmUV:APA91bFJgtBPzLMdK_6UfTDP_pETKMcYeLeeYc7Mi2ZOJG_cWHmPMM6sDRN8pdUUVNCWD0j73-HQ5sQUapw7n5pC7fXEe0jbu3Vy7X-Yyptpu81yM02Nz03drG2e0EzxSXvc52BhZJnF','도도김','농담곰',NULL,'https://i9d104.p.ssafy.io/images/2023331882639594a8e9c-e050-4570-9adc-148f3d71411bmobile.jpeg','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE2OTM1MjQ0MDB9.T67Zd5u84k9PTw0qEEvwIvMdl1t1uJIfso9lJMELWeqQp72fb5WqtEQNqUPYjhEf01O2zD3FYlczcsgvxxTbQg');
/*!40000 ALTER TABLE `normal_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-18 17:32:55
