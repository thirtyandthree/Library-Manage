-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `BookID` int NOT NULL AUTO_INCREMENT,
  `Author` varchar(255) DEFAULT NULL,
  `Publisher` varchar(255) DEFAULT NULL,
  `Genre` varchar(100) DEFAULT NULL,
  `Price` decimal(10,2) DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  `Title` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`BookID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'曹雪芹','人民文学出版社','古典文学',120.00,30,'红楼梦','image/hongloumeng.jpg'),(2,'鲁迅','人民文学出版社','文学',45.00,40,'呐喊','image/nahan.jpg'),(3,'金庸','新世界出版社','武侠',150.00,50,'射雕英雄传','image/shediaoyingxiongzhuan.jpg'),(4,'老舍','人民文学出版社','现代文学',60.00,35,'骆驼祥子','image/luotuoxiangzi.jpg'),(5,'三毛','北京十月文艺出版社','散文',80.00,25,'撒哈拉的故事','image/sahaladegushi.jpg');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrowings`
--

DROP TABLE IF EXISTS `borrowings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrowings` (
  `BorrowingID` int NOT NULL AUTO_INCREMENT,
  `MemberID` int DEFAULT NULL,
  `BookID` int DEFAULT NULL,
  `BorrowDate` date DEFAULT NULL,
  `DueDate` date DEFAULT NULL,
  `ReturnDate` date DEFAULT NULL,
  PRIMARY KEY (`BorrowingID`),
  KEY `MemberID` (`MemberID`),
  KEY `BookID` (`BookID`),
  CONSTRAINT `borrowings_ibfk_1` FOREIGN KEY (`MemberID`) REFERENCES `members` (`MemberID`),
  CONSTRAINT `borrowings_ibfk_2` FOREIGN KEY (`BookID`) REFERENCES `books` (`BookID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowings`
--

LOCK TABLES `borrowings` WRITE;
/*!40000 ALTER TABLE `borrowings` DISABLE KEYS */;
INSERT INTO `borrowings` VALUES (1,1,1,'2023-03-01','2023-03-15','2023-03-14'),(2,2,3,'2023-03-05','2023-03-20','2023-03-18'),(3,3,2,'2023-03-10','2023-03-25',NULL),(4,4,5,'2023-03-15','2023-03-30','2023-03-29'),(5,5,4,'2023-03-20','2023-04-05','2023-04-03'),(6,1,1,'2023-11-23','2023-11-30','2023-11-30');
/*!40000 ALTER TABLE `borrowings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `MemberID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`MemberID`),
  UNIQUE KEY `Name` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (1,'张三','北京市朝阳区','001','zhangsan@qq.com','123456'),(2,'李四','上海市浦东新区','002','lisi@qq.com','123456'),(3,'王五','广州市天河区','003','wangwu@qq.com','123456'),(4,'赵六','深圳市南山区','004','zhaoliu@qq.com','123456'),(5,'周七','成都市锦江区','005','zhouqi@qq.com','123456'),(6,'西蒙','搞笑省搞笑市','hahahaha','ximeng@qq.com','123456'),(7,'王富贵','富贵省富贵市','18763427288','fugui@qq.com','123456'),(8,'李泽锋','山东省济南市','11111','zefengqq.com','123456'),(9,'舒金莉','中国','12111','jinliqq.com','123456');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `motto` text,
  `hometown` varchar(255) DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL,
  `workplace` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_member_name` (`name`),
  CONSTRAINT `fk_member_name` FOREIGN KEY (`name`) REFERENCES `members` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'张三','1992-02-20','永远向前看','北京市','本科','XYZ公司');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-24 17:09:26
