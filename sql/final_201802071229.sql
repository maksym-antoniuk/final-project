-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: final
-- ------------------------------------------------------
-- Server version	5.7.20-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cars` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(15) NOT NULL,
  `mark` varchar(45) NOT NULL,
  `model` varchar(80) NOT NULL,
  `exist` enum('yes','no') NOT NULL DEFAULT 'yes',
  `type_bodywork` enum('tank','bulk','car','animal','container') NOT NULL,
  `max_weight` float NOT NULL,
  `max_volume` float NOT NULL,
  `id_driver` int(11) NOT NULL,
  `status` enum('ok','broken') NOT NULL DEFAULT 'ok',
  PRIMARY KEY (`id`),
  UNIQUE KEY `number_UNIQUE` (`number`),
  KEY `fk_cars_users_idx` (`id_driver`),
  CONSTRAINT `fk_cars_users` FOREIGN KEY (`id_driver`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` VALUES (1,'LP12235','Mersedes','Bens','yes','tank',4010,34,8,'ok'),(2,'XА1234МТ','ГАЗ','3310','yes','container',3665,20.1,11,'ok'),(3,'NM58888K','TATA','123','yes','bulk',5000,40,8,'ok'),(4,'LL4678LK','ZAZ','789','yes','bulk',4700,35,11,'ok'),(5,'MA1997BL','TATA','mod4','yes','bulk',6060,45,11,'ok'),(6,'TY5678LL','KAMAZ','SUPER','yes','bulk',10000,35,11,'ok'),(8,'QW3456YT','Volvo','FL6','yes','container',12000,50,11,'ok'),(9,'QW4567MM','RENAUT','Magnum','yes','car',7000,40,12,'broken'),(10,'PL0980PP','ZAZ','LOL','yes','bulk',12341,40,12,'ok');
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journeys`
--

DROP TABLE IF EXISTS `journeys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `journeys` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` enum('new','on_process','old','canceled') NOT NULL,
  `price` float NOT NULL,
  `weight` float NOT NULL,
  `type` enum('bulk','liquid','animal','solid','car') NOT NULL,
  `volume` float NOT NULL COMMENT 'Volume in m^3',
  `id_manager` int(11) NOT NULL,
  `from` varchar(25) NOT NULL,
  `where` varchar(25) NOT NULL,
  `date_finish` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_journeys_users1_idx` (`id_manager`),
  CONSTRAINT `fk_journeys_users1` FOREIGN KEY (`id_manager`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journeys`
--

LOCK TABLES `journeys` WRITE;
/*!40000 ALTER TABLE `journeys` DISABLE KEYS */;
INSERT INTO `journeys` VALUES (1,'2018-01-22 23:51:26','old',100,50,'bulk',10,2,'50.0487095,36.2045063','50.050333,36.223891','2018-02-06 04:39:58'),(2,'2018-01-26 01:08:09','old',1090,187,'solid',3,1,'50.574927,36.038963','49.534715, 39.107652','2018-02-01 19:32:01'),(3,'2018-01-26 01:08:09','old',547,850,'car',6,1,'gfx','ck','2018-02-05 14:03:06'),(4,'2018-01-29 14:45:55','canceled',1400,4000,'bulk',20,1,'49.997831, 36.030585','49.519741, 30.904341',NULL),(5,'2018-01-31 15:41:17','old',1086,2000,'liquid',15,2,'50.741535, 25.296592','50.777338, 25.364103','2018-02-06 13:17:15'),(6,'2018-02-03 20:37:42','old',45678,4000,'bulk',20,1,'49.997831, 36.030585','51.321536, 36.777782','2018-02-06 13:36:56'),(7,'2018-02-03 20:38:05','old',1400,1223,'bulk',12.5,1,'49.997831, 36.030585','51.321536, 36.777782','2018-02-06 13:35:35'),(8,'2018-02-03 20:38:28','old',1111,690,'bulk',11,1,'51.318763, 36.878466','49.519741, 30.904341','2018-02-06 13:26:12'),(9,'2018-02-03 20:40:46','new',33333,4765,'liquid',45,1,'49.997831, 36.030585','51.321536, 36.777782',NULL),(10,'2018-02-03 20:41:35','old',4300,1232,'bulk',23,1,'49.887831, 37.030585','49.519345, 30.904323','2018-02-06 04:30:51'),(11,'2018-02-03 20:47:15','new',1500,500,'animal',2,1,'51.310063, 36.878466','49.519741, 30.904341',NULL),(12,'2018-02-04 23:44:07','new',1233,1290,'car',3,1,'51.318763, 36.878466','49.519741, 30.904341',NULL),(13,'2018-02-05 02:17:38','new',1112,5134,'car',9,1,'51.318763, 36.878466','49.519741, 30.904341',NULL),(14,'2018-02-05 02:18:09','new',5432,654,'liquid',3,1,'49.997831, 36.030585','51.321536, 36.777782',NULL),(15,'2018-02-06 02:01:44','new',12121,3729,'liquid',12,4,'51.318763, 36.878466','49.519741, 30.904341',NULL),(16,'2018-02-06 02:34:54','new',12,1232,'car',1211,4,'49.997831, 36.030585','51.321536, 36.777782',NULL),(17,'2018-02-06 02:36:28','new',8654,1234,'animal',12,4,'51.318763, 36.878466','49.519741, 30.904341',NULL),(18,'2018-02-06 02:40:11','old',5344,3255,'bulk',9,4,'51.318763, 36.878466','49.519345, 30.904323','2018-02-06 13:14:20'),(19,'2018-02-06 02:43:29','new',1234,908,'car',3,4,'49.887831, 37.030585','51.321536, 36.777782',NULL),(20,'2018-02-06 13:39:19','new',10000,3542,'bulk',12,1,'49.997831, 36.030585','51.321536, 36.777782',NULL),(21,'2018-02-06 13:39:45','old',2000,3333,'bulk',23,1,'49.997831, 36.030585','51.321536, 36.777782','2018-02-06 13:42:02');
/*!40000 ALTER TABLE `journeys` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journeys_has_cars`
--

DROP TABLE IF EXISTS `journeys_has_cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `journeys_has_cars` (
  `journeys_id` int(11) NOT NULL,
  `cars_id` int(11) NOT NULL,
  `accept` enum('yes','no') NOT NULL DEFAULT 'no',
  PRIMARY KEY (`journeys_id`,`cars_id`),
  KEY `fk_journeys_has_cars_cars1_idx` (`cars_id`),
  KEY `fk_journeys_has_cars_journeys1_idx` (`journeys_id`),
  CONSTRAINT `fk_journeys_has_cars_cars1` FOREIGN KEY (`cars_id`) REFERENCES `cars` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_journeys_has_cars_journeys1` FOREIGN KEY (`journeys_id`) REFERENCES `journeys` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journeys_has_cars`
--

LOCK TABLES `journeys_has_cars` WRITE;
/*!40000 ALTER TABLE `journeys_has_cars` DISABLE KEYS */;
INSERT INTO `journeys_has_cars` VALUES (1,3,'no'),(1,4,'no'),(1,5,'yes'),(2,2,'yes'),(3,9,'yes'),(5,1,'yes'),(6,4,'yes'),(7,6,'yes'),(8,3,'no'),(8,4,'no'),(8,5,'no'),(8,6,'yes'),(10,4,'no'),(10,6,'yes'),(18,5,'yes'),(21,6,'yes');
/*!40000 ALTER TABLE `journeys_has_cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `potential_cars`
--

DROP TABLE IF EXISTS `potential_cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `potential_cars` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(15) NOT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `max_weight` float NOT NULL,
  `users_id` int(11) DEFAULT NULL,
  `potential_users_id` int(11) DEFAULT NULL,
  `type_bodywork` enum('tank','bulk','animal','container','car') NOT NULL,
  `max_volume` float NOT NULL,
  `mark` varchar(45) NOT NULL,
  `model` varchar(80) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number_UNIQUE` (`number`),
  KEY `fk_potential_cars_users1_idx` (`users_id`),
  KEY `fk_potential_cars_potential_users1_idx` (`potential_users_id`),
  CONSTRAINT `fk_potential_cars_potential_users1` FOREIGN KEY (`potential_users_id`) REFERENCES `potential_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_potential_cars_users1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `potential_cars`
--

LOCK TABLES `potential_cars` WRITE;
/*!40000 ALTER TABLE `potential_cars` DISABLE KEYS */;
INSERT INTO `potential_cars` VALUES (1,'LKJ89L',NULL,5234,NULL,15,'tank',20,'RENAULT','Vty'),(2,'VA5678JK',NULL,1213,NULL,20,'animal',4,'Valter','Sctt'),(3,'aasqwfc',NULL,1234,8,NULL,'tank',8,'lndc','WQD'),(4,'ХА1234ХА',NULL,2500,8,NULL,'container',10,'ГАЗель','1234'),(5,'AS3421QW',NULL,4567,8,NULL,'tank',12,'LASTO4KA','MOYA'),(6,'FG67854F',NULL,4567,8,NULL,'tank',23,'LKLOLI','jdsiud');
/*!40000 ALTER TABLE `potential_cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `potential_users`
--

DROP TABLE IF EXISTS `potential_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `potential_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `email` varchar(255) NOT NULL,
  `role` enum('driver','manager') NOT NULL,
  `phone` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_UNIQUE` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `potential_users`
--

LOCK TABLES `potential_users` WRITE;
/*!40000 ALTER TABLE `potential_users` DISABLE KEYS */;
INSERT INTO `potential_users` VALUES (12,'ыукванпгрол','ячвасми','ysrdjfv@rydhfcjgv.yp','manager','1234567822'),(13,'adada','adas','asca@sd.gd','manager','0000000000'),(14,'Сергей','Valet','valet@gmail.com','manager','5555555555'),(15,'Aslan','Губернатор','aslan@www.ww','driver','1234432154'),(16,'qwertyu','ertyuio','qwert@sdfg.uy','manager','1234567896'),(17,'qwertyu','asdfghjk','qwertyui@ysdfg.zx','manager','4567834523'),(18,'oiuytr','khjgfds','dsdqa@eqw.kj','manager','5423763119'),(19,'wgraerg','agfvarewvd','qwewfcew@ewfw.sd','manager','4534234312'),(20,'Вальтер','Скотт','valter@scott.lo','driver','5634563456'),(21,'qwertyu','zxcvbn','asdfg@fgvl.oi','manager','3456789034'),(22,'Sgrswgf','Shjgll','qwertyu@vnmloiuyt.oi','manager','0987890875');
/*!40000 ALTER TABLE `potential_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` enum('admin','driver','manager') NOT NULL,
  `datareg` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `salary` float NOT NULL DEFAULT '0',
  `photo` varchar(45) DEFAULT NULL,
  `phone` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `phone_UNIQUE` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Maksym','Antoniuk','maksym.antoniuk@gmail.com','63a9f0ea7bb98050796b649e85481845','admin','2018-01-15 14:21:51',0,NULL,'0683424374'),(2,'Yevhen','Slyva','yevhen.slyva@nure.ua','63a9f0ea7bb98050796b649e85481845','manager','2018-01-22 23:51:15',3000,NULL,'0992795852'),(4,'Alina','Loskutova','alina.loskutova@nure.ua','63a9f0ea7bb98050796b649e85481845','manager','2018-01-27 23:44:15',220,NULL,'9876543210'),(5,'dfyg','fghl','stdyftuyg','5fd0ffe58249f28c28ba85cc58d022e0','manager','2018-01-28 00:12:38',67,NULL,'1234567899'),(8,'Артур','Пендрагон','dragon@lol.lol','63a9f0ea7bb98050796b649e85481845','driver','2018-01-28 00:48:36',2197,NULL,'1234545890'),(9,'Euhen','Slyva','u.trusov54@gmail.com','d41d8cd98f00b204e9800998ecf8427e','manager','2018-01-28 01:17:56',100000,NULL,'0976545674'),(10,'Max','Maxov','maksym.antoniuk@nure.ua','63a9f0ea7bb98050796b649e85481845','manager','2018-01-28 01:22:39',56666,NULL,'0987776665'),(11,'Серго','Абдулов','maksym.antoniuk@outlook.com','7bba59a0281608034217179582ff3c4a','driver','2018-01-29 15:46:27',59912,NULL,'0991231236'),(12,'Карина','Мала','pertsevaya.karina@gmail.com','73df301022cb0df731869015b2cd5c7f','driver','2018-02-05 13:55:50',547,NULL,'1234512345'),(15,'Gsdkjsd','Gsfs','antoniuk.tets@gmail.com','34e18d2deb16797584b459870c0e90d1','manager','2018-02-06 11:20:29',20000,NULL,'0943983849'),(16,'Lololol','Lolklo','lkjlj@vbnmvc.fgh','f88c3f842505f9b397d5745e48016264','manager','2018-02-06 11:20:36',-10000,NULL,'5639804396');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `final`.`users_BEFORE_INSERT` BEFORE INSERT ON `users` FOR EACH ROW
BEGIN
	set new.password = MD5(new.password);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-07 12:30:02
