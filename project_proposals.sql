-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 14, 2021 at 07:15 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gb_projects`
--

-- --------------------------------------------------------

--
-- Table structure for table `project_proposals`
--

CREATE TABLE `project_proposals` (
  `proposal_ID` int(11) NOT NULL,
  `projectName` varchar(50) NOT NULL,
  `budget` double NOT NULL,
  `completionDate` varchar(20) NOT NULL,
  `productCategory` varchar(20) NOT NULL,
  `sellOrNot` varchar(10) NOT NULL,
  `description` varchar(400) NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'pending',
  `userID` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `project_proposals`
--

INSERT INTO `project_proposals` (`proposal_ID`, `projectName`, `budget`, `completionDate`, `productCategory`, `sellOrNot`, `description`, `status`, `userID`) VALUES
(10, 'gbproject', 1000, '2020-12-12', 'IT', 'sell', 'description', 'accepted', ''),
(16, 'Project Q', 1000, '2021-12-12', 'category', 'not', 'desc', 'pending', '20'),
(19, 'project', 10000, '2021-05-19', 'category', 'sell', 'desc', 'pending', '5'),
(22, 'QQ', 10000, '2020-11-25', 'aa', 'sell', 'desc', 'pending', '10'),
(23, 'gbproject', 1000, '2020-12-12', 'IT', 'sell', 'description', 'accepted', '15'),
(24, 'project n', 1000, '2021-12-12', 'ss', 'not', 'dfd', 'accepted', '50'),
(25, 'Project A', 100000, '2020-12-12', 'category', 'sell', 'description', 'pending', '10'),
(26, 'Project A', 100000, '2020-12-12', 'category', 'sell', 'description', 'pending', '15'),
(27, 'project B', 1000, '2021', 'category', 'not', 'desc', 'pending', '47');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `project_proposals`
--
ALTER TABLE `project_proposals`
  ADD PRIMARY KEY (`proposal_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `project_proposals`
--
ALTER TABLE `project_proposals`
  MODIFY `proposal_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
