-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 19, 2019 at 03:17 PM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `csr`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `name`, `phone`, `email`, `address`) VALUES
(1, 'Rizwan', '01715253569', 'zshfkjhf@gmail.com', 'Kalai'),
(2, 'Lord Voldemort', '0166125874', 'voldy@hogwarts.edu', 'Hogwarts'),
(3, 'Thanos', '01553386861', 'thanos@nowhere.com', 'Black Hole'),
(5, 'Mr Strange', '0156545456', 'dr.strange@marvel.comics', 'Marvel, 30 Avenue Los Angeles'),
(6, 'Ratul\'s Al Mamun', '011111111111', 'rat@iubat.edu', 'LA, California'),
(7, 'Munni ', '015555555', 'munni@gmail.com', 'Uttara - 10, Road- 2, Dhaka'),
(11, 'Rezwana', 'Ferdous', 'rizwana@gmail.com', 'Uttara, Dhaka, Bangladesh');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `sl` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `age` int(3) NOT NULL,
  `password` varchar(20) NOT NULL,
  `role` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`sl`, `name`, `phone`, `gender`, `age`, `password`, `role`) VALUES
(2, 'Angela', '01717031859', 'female', 21, '1710', 'Admin'),
(3, 'Rizwan', '01689802565', 'Male', 24, '1920', 'Admin'),
(4, 'Munni', '01956895421', 'Female', 24, '1234', 'Stuff');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `pid` varchar(50) NOT NULL,
  `brand` varchar(50) NOT NULL,
  `model` varchar(50) NOT NULL,
  `year` varchar(50) NOT NULL,
  `hp` varchar(50) NOT NULL,
  `price` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `pid`, `brand`, `model`, `year`, `hp`, `price`) VALUES
(1, '', 'Lamborghini', 'Venono 15x', '4/17/2013', '900', '30000000000'),
(4, 'x14510', 'BMW', 'i8', '4/18/2012', '700', '5100021010'),
(7, 'Electric Palki', 'Tesla', 'Palki 2', '4/19/2019', '2', '50000');

-- --------------------------------------------------------

--
-- Table structure for table `report`
--

CREATE TABLE `report` (
  `id` int(11) NOT NULL,
  `productcode` varchar(50) NOT NULL,
  `customerid` varchar(50) NOT NULL,
  `buyername` varchar(50) NOT NULL,
  `sellingprice` varchar(50) NOT NULL,
  `profit` varchar(50) NOT NULL,
  `loss` varchar(50) NOT NULL,
  `sellingdate` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `report`
--

INSERT INTO `report` (`id`, `productcode`, `customerid`, `buyername`, `sellingprice`, `profit`, `loss`, `sellingdate`) VALUES
(1, '5', '1', 'Rizwan', '15', '5', '0', '6/2/2019'),
(2, 'Fairy Palki', '1', 'Rizwan', '30', '10', '0', '5/1/2019'),
(4, 'Nouka', '11', 'Rezwana', '20', '0', '40', '4/1/2019'),
(5, 'thc19', '3', 'Thanos', '4000000', '0', '1000000', '4/19/2019');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`sl`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `sl` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `report`
--
ALTER TABLE `report`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
