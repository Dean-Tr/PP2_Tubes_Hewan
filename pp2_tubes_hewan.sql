-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 17, 2022 at 08:41 AM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pp2_tubes_hewan`
--

-- --------------------------------------------------------

--
-- Table structure for table `habitat`
--

CREATE TABLE `habitat` (
  `id` varchar(255) NOT NULL,
  `nama_habitat` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `habitat`
--

INSERT INTO `habitat` (`id`, `nama_habitat`) VALUES
('219f03f7-cd9e-4d4a-930b-4085b7a9b157', 'Air Tawar'),
('485d259f-cba1-44bc-88f8-e5e3274b0e94', 'Air Laut'),
('516feadf-57bd-4d92-af61-6fa41dcbd4b2', 'Daratan');

-- --------------------------------------------------------

--
-- Table structure for table `hewan`
--

CREATE TABLE `hewan` (
  `id` varchar(255) NOT NULL,
  `jenis_hewan_id` varchar(255) DEFAULT NULL,
  `habitat_id` varchar(255) DEFAULT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `umur` varchar(255) DEFAULT NULL,
  `jenis_kelamin` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hewan`
--

INSERT INTO `hewan` (`id`, `jenis_hewan_id`, `habitat_id`, `nama`, `umur`, `jenis_kelamin`) VALUES
('45703a09-a3fd-46c7-aa89-a645f0b6e9f1', '23f1b38b-71b6-47e6-95cd-f5c22aab489a', '219f03f7-cd9e-4d4a-930b-4085b7a9b157', 'ritt', '2', 'Perempuan'),
('6446f3e9-8302-4426-9b05-69ec1e4a4baf', '54172458-dba4-4224-aaca-c85f44124939', '516feadf-57bd-4d92-af61-6fa41dcbd4b2', 'qwerty', '4', 'Laki-Laki'),
('c2646532-a46f-4801-9fdd-8774ada835f1', 'dd871c4e-48cb-4527-8855-1191ed54cb57', '516feadf-57bd-4d92-af61-6fa41dcbd4b2', 'pon', '8', 'Perempuan');

-- --------------------------------------------------------

--
-- Table structure for table `jenis_hewan`
--

CREATE TABLE `jenis_hewan` (
  `id` varchar(255) NOT NULL,
  `jenis` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `jenis_hewan`
--

INSERT INTO `jenis_hewan` (`id`, `jenis`) VALUES
('23f1b38b-71b6-47e6-95cd-f5c22aab489a', 'Ikan'),
('4aa0fef6-eca8-43fb-a1d9-2f8a77462d25', 'Burung'),
('54172458-dba4-4224-aaca-c85f44124939', 'Kucing'),
('dd871c4e-48cb-4527-8855-1191ed54cb57', 'Anjing');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `habitat`
--
ALTER TABLE `habitat`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hewan`
--
ALTER TABLE `hewan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `jenis_hewan`
--
ALTER TABLE `jenis_hewan`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
