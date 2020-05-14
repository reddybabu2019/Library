

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE IF NOT EXISTS `books` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `bookno` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `author` varchar(100) NOT NULL,
  `publisher` varchar(100) NOT NULL,
  `quantity` int(10) NOT NULL,
  `issued` int(10) NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `bookno` (`bookno`),
  UNIQUE KEY `bookno_2` (`bookno`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`id`, `bookno`, `name`, `author`, `publisher`, `quantity`, `issued`, `added_date`) VALUES
(1, 'A0001', 'C In Depth', 'Ritch', 'BPB', 2, 2, '2020-01-20 19:37:56'),
(2, 'B0002', 'DBMS', 'Korth', 'Pearson', 3, 0, '2020-01-18 18:39:52'),
(3, 'C0003', 'Let''s see', 'Yashwant Kanetkar', 'BPB', 10, 0, '2020-01-16 23:02:14'),
(4, 'D0004', 'Java', 'Ritch', 'BPB', 2, 2, '2020-01-20 19:37:56'),
(5, 'E0005', 'C++', 'auth9', 'Pearson', 3, 0, '2020-01-18 18:39:52'),
(6, 'F0006', 'English', 'auth 10', 'BPB', 10, 0, '2020-01-16 23:02:14');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
