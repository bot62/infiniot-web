--
-- Table structure for table `nodes`
--

DROP TABLE IF EXISTS `nodes`;
CREATE TABLE `nodes` (
  `id` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT 'node id',
  `type` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT 'node type',
  `bid` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT 'building id',
  `fid` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT 'floor id',
  `rid` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT 'room id',
  `battery` decimal(10,2) DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `insert_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `longitude` decimal(10,7) NOT NULL DEFAULT '0.0000000',
  `latitude` decimal(10,7) NOT NULL DEFAULT '0.0000000',
  PRIMARY KEY (`id`),
  KEY `SENSORS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `nodes`
--

INSERT INTO `nodes` VALUES ('INF_N_001','Zigbee','INFelec','1','B1 201',0.80,'','2015-10-17 14:44:07','2015-10-17 14:44:07',49.3836804,1.0755375),('INF_N_002','Zigbee','INFelec','1','B1 202',0.75,'','2015-10-17 14:44:07','2015-10-17 14:44:07',49.3836804,1.0755375),('INF_N_003','Zigbee','Irseem','0','Parking',0.08,'','2015-10-17 14:44:07','2015-10-17 14:44:07',49.3836804,1.0755375),('SDIS_N_001','Unknown','SDIS 76','0','Démonstrateur',0.55,'','2015-12-31 00:00:00',NULL,49.3585766,0.7230167);

--
-- Table structure for table `sensorParser`
--

DROP TABLE IF EXISTS `sensorParser`;
CREATE TABLE `sensorParser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_wasp` text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `id_secret` text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `frame_type` int(11) DEFAULT NULL,
  `frame_number` int(11) DEFAULT NULL,
  `sensor` text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `value` text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sync` int(1) NOT NULL DEFAULT '0',
  `raw` varchar(100) NOT NULL DEFAULT 'noraw',
  `parser_type` tinyint(3) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `time` (`timestamp`)
) ENGINE=MyISAM AUTO_INCREMENT=271 DEFAULT CHARSET=latin1;

--
-- Table structure for table `sensorParserRef`
--

DROP TABLE IF EXISTS `sensorParserRef`;
CREATE TABLE `sensorParserRef` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sensor` varchar(30) NOT NULL,
  `sensor_type` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sensorParserRef`
--

INSERT INTO `sensorParserRef` VALUES (1,'BAT','Battery'),(2,'HUMA','Humidity'),(3,'TCA','Temperature'),(4,'ACC','Accelerometer');

--
-- Table structure for table `sensors`
--

DROP TABLE IF EXISTS `sensors`;
CREATE TABLE `sensors` (
  `id` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT 'sensor id',
  `type` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `current_value` double NOT NULL,
  `bid` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT 'building id',
  `fid` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT 'floor id',
  `rid` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT 'room id',
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nid` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `nid` (`nid`),
  CONSTRAINT `fk_nodes` FOREIGN KEY (`nid`) REFERENCES `nodes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `sensors`
--

LOCK TABLES `sensors` WRITE;
/*!40000 ALTER TABLE `sensors` DISABLE KEYS */;
INSERT INTO `sensors` VALUES ('INF_TEMP_0001','Temperature',49.57223636337764,'A','1','A1 255','Infiniot Temperature sensor LIBE9025','INF_N_001','2015-11-08 17:22:58'),('INF_TEMP_0002','Temperature',57.20960490052551,'A','1','A1 256','Infiniot Temperature sensor LIBE9025','INF_N_001','2015-11-08 17:22:58'),('INF_TEMP_0003','Temperature',137.3313215899573,'A','1','A1 257','Infiniot Temperature sensor LIBE9025','INF_N_001','2015-11-08 17:22:58'),('INF_TEMP_0004','Temperature',113.02779942567255,'A','1','A1 258','Infiniot Temperature sensor LIBE9025','INF_N_001','2015-11-08 17:22:58'),('INF_TEMP_0005','Temperature',153.14503853595352,'A','1','B1 255','Infiniot Temperature sensor LIBE9025','INF_N_002','2015-11-08 17:22:58'),('INF_TEMP_0006','Temperature',24.64180058021266,'A','1','B1 256','Infiniot Temperature sensor LIBE9025','INF_N_002','2015-11-08 17:22:58'),('INF_TEMP_0007','Temperature',65.77389347066534,'A','1','B1 257','Infiniot Temperature sensor LIBE9025','INF_N_002','2015-11-08 17:22:58'),('INF_TEMP_0008','Temperature',53.94407179015137,'A','1','B1 258','Infiniot Temperature sensor LIBE9025','INF_N_002','2015-11-08 17:22:58'),('INF_TEMP_0009','Temperature',72.39868471622344,'A','1','C1 255','Infiniot Temperature sensor LIBE9025','INF_N_003','2015-11-08 17:22:58'),('INF_TEMP_0010','Temperature',200.16121438812579,'A','1','C1 256','Infiniot Temperature sensor LIBE9025','INF_N_003','2015-11-08 17:22:58'),('INF_TEMP_0011','Temperature',180.61002396942118,'A','1','C1 257','Infiniot Temperature sensor LIBE9025','INF_N_003','2015-11-08 17:22:58'),('INF_TEMP_0012','Temperature',101.56648286019124,'A','1','C1 258','Infiniot Temperature sensor LIBE9025','INF_N_003','2015-11-08 17:22:58'),('INF_TEMP_0013','Temperature',167.0023485701553,'A','1','D1 255','Infiniot Temperature sensor LIBE9025','INF_N_003','2015-11-08 17:22:58'),('INF_TEMP_0014','Temperature',128.31230044766545,'A','1','D1 256','Infiniot Temperature sensor LIBE9025','INF_N_003','2015-11-08 17:22:58'),('INF_TEMP_0015','Temperature',140.5544627053239,'A','1','D1 257','Infiniot Temperature sensor LIBE9025','INF_N_003','2015-11-08 17:22:58'),('INF_TEMP_0016','Temperature',116.83541836108584,'A','1','D1 258','Infiniot Temperature sensor LIBE9025','INF_N_003','2015-11-08 17:22:58');
/*!40000 ALTER TABLE `sensors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` char(40) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `insert_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

INSERT INTO `users` VALUES (1,'Infiniot','Infiniot','demo@infiniot.com','dc76e9f0c0006e8f919e0c515c66dbba3982f785','admin','2016-12-01 00:00:00');
