-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 18, 2022 at 09:49 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `coursework`
--

-- --------------------------------------------------------

--
-- Table structure for table `w1838863_equipment`
--

CREATE TABLE `w1838863_equipment` (
  `w1838863_equipCode` int(11) NOT NULL,
  `w1838863_equipMake` varchar(50) NOT NULL,
  `w1838863_equipSeries` varchar(50) DEFAULT NULL,
  `w1838863_equipModel` varchar(50) DEFAULT NULL,
  `w1838863_equipCondition` varchar(50) DEFAULT NULL,
  `w1838863_equipStatus` varchar(50) NOT NULL,
  `w1838863_lastServicingDate` date NOT NULL,
  `w1838863_costPerDay` float NOT NULL,
  `w1838863_studioId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `w1838863_equipment`
--

INSERT INTO `w1838863_equipment` (`w1838863_equipCode`, `w1838863_equipMake`, `w1838863_equipSeries`, `w1838863_equipModel`, `w1838863_equipCondition`, `w1838863_equipStatus`, `w1838863_lastServicingDate`, `w1838863_costPerDay`, `w1838863_studioId`) VALUES
(901, 'Casio', 'WK 7600', 'Portable Keyboard', 'Excellent', 'Available', '2021-03-22', 129, 101),
(902, 'SubZero', 'DR 30', 'Drum/Keyboard Amp', 'Excellent', 'Available', '2021-04-20', 109, 102),
(903, 'Schecter', 'C-8 Deluxe', 'Electric Guitar', 'Excellent', 'Available', '2021-06-12', 209, 103),
(904, 'SubZero', 'Fret 8-String', 'Electric Guitar', 'Good', 'Available', '2021-02-19', 119, 104),
(905, 'Mapex', 'Tornado III', '18\" Drum Kit', 'Excellent', 'Available', '2021-05-12', 239, 105),
(906, 'Elkhart', '100CR', 'Cornet Package', 'Good', 'Available', '2021-09-10', 129, 106),
(907, 'Yamaha', 'MG10', 'Analog Mixer', 'Excellent', 'Available', '2021-06-22', 299, 107),
(908, 'Chandler', 'TG12345', 'Curve Bender', 'Good', 'Available', '2021-08-07', 99, 108);

-- --------------------------------------------------------

--
-- Table structure for table `w1838863_studio`
--

CREATE TABLE `w1838863_studio` (
  `w1838863_studioID` int(11) NOT NULL,
  `w1838863_studioName` varchar(50) DEFAULT NULL,
  `w1838863_studioAddress` varchar(100) NOT NULL,
  `w1838863_studioPCode` varchar(50) DEFAULT NULL,
  `w1838863_studioTelNo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `w1838863_studio`
--

INSERT INTO `w1838863_studio` (`w1838863_studioID`, `w1838863_studioName`, `w1838863_studioAddress`, `w1838863_studioPCode`, `w1838863_studioTelNo`) VALUES
(101, 'Blogos', '159 Church Lane, London', '201489', '+44 2085 336666'),
(102, 'Narcoto', '201 Rawson Street, Halifax', '375019', '+44 3445 610302'),
(103, 'Calonas', '109 George Street, London', '202589', '+44 2072 540151'),
(104, 'Begamgo', '49 Victoria Street, Blackpool', '147213', '+44 1253 751159'),
(105, 'Dengo', 'Kingsland Centre, London', '301109', '+44 2075 435616'),
(106, 'Mariola', '14 Highfield Road. London', '305512', '+44 2052 561149'),
(107, 'Alfots', '34 Ancoats Street, Manchester', '121407', '+44 2435 517077'),
(108, 'Manchso', '04 Longridge Road, London', '138460', '+44 3445 610000');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `w1838863_equipment`
--
ALTER TABLE `w1838863_equipment`
  ADD PRIMARY KEY (`w1838863_equipCode`),
  ADD KEY `se_sid_fk` (`w1838863_studioId`);

--
-- Indexes for table `w1838863_studio`
--
ALTER TABLE `w1838863_studio`
  ADD PRIMARY KEY (`w1838863_studioID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `w1838863_equipment`
--
ALTER TABLE `w1838863_equipment`
  ADD CONSTRAINT `se_sid_fk` FOREIGN KEY (`w1838863_studioId`) REFERENCES `w1838863_studio` (`w1838863_studioID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
