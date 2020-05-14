

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
-- Table structure for table `issuebooks`
--

CREATE TABLE IF NOT EXISTS `issuebooks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookissueno` varchar(50) NOT NULL,
  `userid` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `usercontact` varchar(20) NOT NULL,
  `issueddate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `issuebooks`
--

INSERT INTO `issuebooks` (`id`, `bookissueno`, `userid`, `username`, `usercontact`, `issueddate`) VALUES
(10, 'A0001', 1, 'user1', '9934536782', '2020-02-19 18:43:16'),
(14, 'D0004', 4, 'user2', '9988766452', '2020-02-19 18:44:34'),
(15, 'E0005', 1, 'user1', '9934536782', '2020-02-19 18:46:12');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
