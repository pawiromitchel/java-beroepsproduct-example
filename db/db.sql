create database studenten;
use studenten;

CREATE USER 'student'@'localhost' IDENTIFIED BY PASSWORD '';
GRANT ALL PRIVILEGES ON * . * TO 'student'@'localhost' IDENTIFIED BY PASSWORD '' WITH GRANT OPTION MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0 ;
GRANT ALL PRIVILEGES ON `studenten` . * TO 'student'@'localhost';

CREATE TABLE IF NOT EXISTS `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vakNaam` varchar(32) NOT NULL DEFAULT '''''',
  `adres` varchar(32) NOT NULL DEFAULT '''''',
  `studierichting` varchar(32) NOT NULL DEFAULT '''''',
  `leeftijd` int(11) NOT NULL DEFAULT '0',
  `cijfergemiddelde` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

INSERT INTO `student` (`id`, `vakNaam`, `adres`, `studierichting`, `leeftijd`, `tentamenGemaakt`) VALUES
(1, 'Melvin', 'zinnia straat 23', 'Software Engineering', 20, 10),
(2, 'Hanna', 'Hackerstraat 1', 'Software Engineering', 23, 10),
(3, 'Kim', 'testserverstraat 3', 'Software Engineering', 19, 8),
(4, 'Anna', 'serverstraat 4', 'Software Engineering', 21, 4),
(5, 'click for virus', 'virusstraat 5', 'Virus Engineering', 18, 1);

CREATE TABLE IF NOT EXISTS `vak` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`vakNaam` varchar(32) NOT NULL DEFAULT '''''',
`tentamenGemaakt` boolean '''''',
`docent` varchar(32) NOT NULL DEFAULT '''''',
`studiePunten` int(11) NOT NULL DEFAULT '''''',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

INSERT INTO `vak` (`id`, `vakNaam`, `tentamenGemaakt`, `docent`, `studiepunten`) VALUES
(1, 'Java', false , 'M. Narain', 5),
(2, 'Database', false, 'S. Kromosoeto', 5),
(3, 'Bedrijfskunde', true, 'S. Sital', 6),
(4, 'Nederlands', true, 'L. van Zichem', 4);