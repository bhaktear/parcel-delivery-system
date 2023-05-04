-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 04, 2023 at 03:11 PM
-- Server version: 8.0.32-0ubuntu0.20.04.2
-- PHP Version: 7.4.3-4ubuntu2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `parcel_delivery`
--

-- --------------------------------------------------------

--
-- Table structure for table `parcel_merchant`
--

CREATE TABLE `parcel_merchant` (
  `id` int NOT NULL,
  `user_id` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `merchant_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `district` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `thana` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `business_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fb_page` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `star_customer` tinyint(1) DEFAULT NULL,
  `star_customer_discount` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `parcel_merchant`
--

INSERT INTO `parcel_merchant` (`id`, `user_id`, `merchant_name`, `name`, `district`, `thana`, `address`, `business_type`, `fb_page`, `star_customer`, `star_customer_discount`, `status`) VALUES
(2, 'bhaktear', 'Bhaktear Uddin', 'Bhaktear Uddin', 'DHAKA', 'DHANMONDI', 'Bhaktear Uddin', NULL, NULL, NULL, NULL, 1),
(3, 'nishat', 'nishat rayhana', 'nishat rayhana', 'DHAKA', 'MIRPUR', 'nishat rayhana', NULL, NULL, NULL, NULL, 1),
(4, 'sagar', 'Sagar', 'Sagar', 'DHAKA', 'DHANMONDI', 'Sagar', NULL, NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `parcel_options`
--

CREATE TABLE `parcel_options` (
  `id` int NOT NULL,
  `code` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `meta` text NOT NULL,
  `published` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `parcel_options`
--

INSERT INTO `parcel_options` (`id`, `code`, `name`, `type`, `meta`, `published`) VALUES
(3, 3, 'Electronic Devices', 'order_type', '', 1),
(4, 4, 'Electronic Accessories', 'order_type', '', 1),
(5, 5, 'TV & Home Appliances', 'order_type', '', 1),
(6, 6, 'Health & Beauty', 'order_type', '', 1),
(7, 7, 'Babies & Toys', 'order_type', '', 1),
(8, 8, 'Groceries & Pets', 'order_type', '', 1),
(9, 9, 'Home & Lifestyle', 'order_type', '', 1),
(10, 10, 'Women\'s Fashion', 'order_type', '', 1),
(11, 11, 'Men\'s Fashion', 'order_type', '', 1),
(12, 12, 'Watches & Accessories', 'order_type', '', 1),
(13, 13, 'Sports & Outdoor', 'order_type', '', 1),
(14, 14, 'Automotive & Motorbike Accessories', 'order_type', '', 1),
(15, 15, 'Cash on Delivery', 'payment_method', '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `parcel_order`
--

CREATE TABLE `parcel_order` (
  `id` int NOT NULL,
  `invoice` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `merchant_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `merchant_mobile` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `order_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `customer_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `customer_mobile` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `customer_district` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `customer_thana` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `customer_address` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `product_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `product_details` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `product_price` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `delivery_charge` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `discount` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `total_delivery_charge` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `total_amount` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `payment_method` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` varchar(1) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `parcel_order`
--

INSERT INTO `parcel_order` (`id`, `invoice`, `user_id`, `merchant_name`, `merchant_mobile`, `order_type`, `customer_name`, `customer_mobile`, `customer_district`, `customer_thana`, `customer_address`, `product_type`, `product_details`, `product_price`, `delivery_charge`, `discount`, `total_delivery_charge`, `total_amount`, `payment_method`, `status`, `created_at`, `updated_on`) VALUES
(1, 'PDS000001', 'bhaktear', 'Bhaktear Uddin', '01520101525', 'Babies & Toys', 'sdsd', '01520101525', 'DHAKA', 'LALBAGH', 'sdds', 'sdds', 'dsds', '500', '80', '5', '75', '575.0', 'Cash on Delivery', '1', '2023-05-01 20:10:44', '2023-05-01 20:10:44'),
(4, 'PDS000004', 'bhaktear', 'Bhaktear Uddin', '01520101525', 'Choose One', 'dsdf', '01520101525', 'DHAKA', 'KOTWALI', 'ddf', 'fddf', 'fddf', '500', '80', '5', '75', '575.0', 'Cash on Delivery', '2', '2023-05-01 20:14:19', '2023-05-01 20:14:19'),
(5, 'PDS000005', 'bhaktear', 'Bhaktear Uddin', '01520101525', 'Electronic Accessories', 'dsdf', '01520101525', 'DHAKA', 'KOTWALI', 'ddf', 'fddf', 'fddf', '500', '80', '5', '75', '575.0', 'Cash on Delivery', '4', '2023-05-01 20:14:32', '2023-05-01 20:14:32'),
(6, 'IN000006', 'bhaktear', 'Bhaktear Uddin', '01520101525', 'TV & Home Appliances', 'gowrab', '01724416980', 'DHAKA', 'DHANMONDI', 'dhanmondi', 'Electronics', 'Headphone- 2 pc', '1500', '80', '0', '80', '1580.0', 'Cash on Delivery', '1', '2023-05-03 20:47:42', '2023-05-03 20:47:42'),
(7, 'IN000007', 'sagar', 'Sagar', '01520101525', 'Electronic Devices', 'saiket', '01520101525', 'DHAKA', 'GULSHAN', 'Gulsan', 'Headphone', 'Headphone - 3 pc', '3000', '80', '0', '80', '3080.0', 'Cash on Delivery', '1', '2023-05-04 19:41:49', '2023-05-04 19:41:49');

-- --------------------------------------------------------

--
-- Table structure for table `parcel_status`
--

CREATE TABLE `parcel_status` (
  `id` int NOT NULL,
  `code` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `published` varchar(1) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `parcel_status`
--

INSERT INTO `parcel_status` (`id`, `code`, `name`, `published`) VALUES
(1, '1', 'Pending', '1'),
(2, '2', 'Pickup complete', '1'),
(3, '3', 'Warehouse', '0'),
(4, '4', 'Delivered', '1'),
(5, '5', 'Payment', '0'),
(7, '6', 'Order Return', '0');

-- --------------------------------------------------------

--
-- Table structure for table `parcel_thana`
--

CREATE TABLE `parcel_thana` (
  `id` int NOT NULL,
  `thana_code` int NOT NULL,
  `dist_code` int NOT NULL,
  `div_code` int NOT NULL,
  `div_name` varchar(100) NOT NULL,
  `div_name_bn` varchar(255) NOT NULL,
  `dist_name` varchar(100) NOT NULL,
  `dist_name_bn` varchar(255) NOT NULL,
  `thana_name` varchar(100) NOT NULL,
  `thana_name_bn` varchar(255) NOT NULL,
  `location` tinyint(1) NOT NULL DEFAULT '1',
  `published` tinyint NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `parcel_thana`
--

INSERT INTO `parcel_thana` (`id`, `thana_code`, `dist_code`, `div_code`, `div_name`, `div_name_bn`, `dist_name`, `dist_name_bn`, `thana_name`, `thana_name_bn`, `location`, `published`) VALUES
(175, 31001, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'MIRPUR', '', 25, 1),
(176, 31002, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'MOHAMMADPUR', '', 25, 1),
(177, 31003, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'DHANMONDI', '', 25, 1),
(178, 31004, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'LALBAGH', '', 25, 1),
(179, 31005, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'KOTWALI', '', 25, 1),
(180, 31006, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'SUTRAPUR', '', 25, 1),
(181, 31007, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'CANTONMENT', '', 25, 1),
(182, 31008, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'MOTIJHEEL', '', 25, 1),
(183, 31009, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'RAMNA', '', 25, 1),
(184, 31010, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'TEJGAON', '', 25, 1),
(185, 31011, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'GULSHAN', '', 25, 1),
(186, 31012, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'DEMRA', '', 25, 1),
(187, 31013, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'KERANIGANJ', '', 25, 1),
(188, 31014, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'DOHAR', '', 0, 0),
(189, 31015, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'DHAMRAI', '', 0, 0),
(190, 31016, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'NAWABGANJ', '', 0, 0),
(191, 31017, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'SAVAR', '', 26, 1),
(192, 31018, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'NEW MARKET', '', 25, 1),
(193, 31019, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'KALABAGAN', '', 25, 1),
(194, 31020, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'HAZARIBAGH', 'হাজারীবাগ', 25, 1),
(195, 31021, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'TEJGAON INDUSTRIAL AREA', '', 25, 1),
(196, 31022, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'SHAHBAG', '', 25, 1),
(197, 31023, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'SHER E BANGLA NAGAR', '', 25, 1),
(198, 31024, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'SHAHJAHANPUR', '', 25, 1),
(199, 31025, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'PALTAN', '', 25, 1),
(200, 31026, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'KHILGAON', '', 25, 1),
(201, 31027, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'SABUJBAGH', '', 25, 1),
(202, 31028, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'RAMPURA', '', 25, 1),
(203, 31029, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'MUGDA', '', 25, 1),
(204, 31030, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'WARI', '', 25, 1),
(205, 31031, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'BANGSHAL', '', 25, 1),
(206, 31032, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'CHAWKBAZAR', '', 25, 1),
(207, 31033, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'GANDARIA', '', 25, 1),
(208, 31034, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'RUPNAGAR', '', 25, 1),
(209, 31035, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'PALLABI', '', 25, 1),
(210, 31036, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'ASULIA', '', 25, 1),
(211, 31037, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'ADABOR', '', 25, 1),
(212, 31038, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'JATRABARI', '', 25, 1),
(213, 31039, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'KAFRUL', '', 25, 1),
(214, 31040, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'BHASHANTEK', '', 25, 1),
(215, 31041, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'KAMRANGIR CHAR', '', 25, 1),
(216, 31042, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'SHAH ALI', '', 25, 1),
(217, 31043, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'DARUSSALAM', '', 25, 1),
(218, 31044, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'SHYAMPUR', '', 25, 1),
(219, 31045, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'KADAMTALI', '', 25, 1),
(220, 31046, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'BANANI', '', 25, 1),
(221, 31047, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'AIRPORT', '', 25, 1),
(222, 31048, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'DAKSHIN KHAN', '', 25, 1),
(223, 31049, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'UTTARA WEST', '', 25, 1),
(224, 31050, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'UTTAR KHAN', '', 25, 1),
(225, 31051, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'UTTARA', '', 25, 1),
(547, 31052, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'TURAG', '', 25, 1),
(548, 31053, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'KALYANPUR', '', 25, 1),
(554, 31054, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'BADDA', '', 25, 1),
(555, 31055, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'VATARA', '', 25, 1),
(557, 31056, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'KHILKHET', '', 25, 1),
(584, 31057, 310, 3, 'DHAKA', 'ঢাকা', 'DHAKA', 'ঢাকা', 'ASHULIA', '', 26, 1);

-- --------------------------------------------------------

--
-- Table structure for table `parcel_user`
--

CREATE TABLE `parcel_user` (
  `id` int NOT NULL,
  `user_id` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `mobile` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` varchar(1) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `published` varchar(1) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `parcel_user`
--

INSERT INTO `parcel_user` (`id`, `user_id`, `user_name`, `email`, `mobile`, `role`, `password`, `published`) VALUES
(1, 'admin', 'admin', 'bhaktear@gmail.com', '01520101525', '1', '123456', '1'),
(12, 'bhaktear', 'Bhaktear Uddin', 'bhaktear@gmail.com', '01520101525', '2', '123456', '1'),
(13, 'nishat', 'nishat rayhana', 'bhak@gmail.com', '01311111111', '2', '123456', '1'),
(14, 'sagar', 'Sagar', 'bha@gmail.com', '01520101525', '2', '123456', '1');

-- --------------------------------------------------------

--
-- Table structure for table `random_uid`
--

CREATE TABLE `random_uid` (
  `id` int NOT NULL,
  `user_id` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `random_uid`
--

INSERT INTO `random_uid` (`id`, `user_id`) VALUES
(8, '0AB43F2B'),
(3, '137b0d89'),
(4, '1be1941a'),
(6, '30127AE3'),
(2, '33409257'),
(1, '85662610'),
(5, '898546ee'),
(9, 'AA35BCA7'),
(7, 'BBEC265E'),
(10, 'E51FD0E3');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `parcel_merchant`
--
ALTER TABLE `parcel_merchant`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Indexes for table `parcel_options`
--
ALTER TABLE `parcel_options`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `parcel_order`
--
ALTER TABLE `parcel_order`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `invoice` (`invoice`);

--
-- Indexes for table `parcel_status`
--
ALTER TABLE `parcel_status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `parcel_thana`
--
ALTER TABLE `parcel_thana`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `thana` (`thana_code`);

--
-- Indexes for table `parcel_user`
--
ALTER TABLE `parcel_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Indexes for table `random_uid`
--
ALTER TABLE `random_uid`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `parcel_merchant`
--
ALTER TABLE `parcel_merchant`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `parcel_options`
--
ALTER TABLE `parcel_options`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `parcel_order`
--
ALTER TABLE `parcel_order`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `parcel_status`
--
ALTER TABLE `parcel_status`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `parcel_thana`
--
ALTER TABLE `parcel_thana`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=585;

--
-- AUTO_INCREMENT for table `parcel_user`
--
ALTER TABLE `parcel_user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `random_uid`
--
ALTER TABLE `random_uid`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
