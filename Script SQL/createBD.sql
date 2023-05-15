CREATE TABLE `species` (
  `id` varchar(20) NOT NULL,
  `label` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `label_UNIQUE` (`label`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `breed` (
  `id` varchar(20) NOT NULL,
  `label` varchar(45) NOT NULL,
  `specification` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `id_idx` (`specification`),
  CONSTRAINT `specification` FOREIGN KEY (`specification`) REFERENCES `species` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `animal` (
  `code` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `sex` enum('F','M') NOT NULL,
  `isDangerous` tinyint NOT NULL,
  `weight` int NOT NULL,
  `arrivalDate` date NOT NULL,
  `nickName` varchar(20) DEFAULT NULL,
  `breed` varchar(20) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `id_idx` (`breed`),
  CONSTRAINT `breed` FOREIGN KEY (`breed`) REFERENCES `breed` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `medicine` (
  `name` varchar(20) NOT NULL,
  `unitOfMesurement` varchar(20) NOT NULL,
  `instruction` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`name`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `fonction` (
  `id` varchar(20) NOT NULL,
  `position` enum('CHIEF','SENIOR','JUNIOR') NOT NULL,
  `label` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `typeofcare` (
  `id` varchar(20) NOT NULL,
  `label` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `employee` (
  `matricule` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `supervisor` varchar(20) DEFAULT NULL,
  `position` varchar(20) NOT NULL,
  PRIMARY KEY (`matricule`),
  UNIQUE KEY `matricule_UNIQUE` (`matricule`),
  KEY `position_idx` (`position`),
  CONSTRAINT `position` FOREIGN KEY (`position`) REFERENCES `fonction` (`id`) ON DELETE CASCADE,
  CONSTRAINT `supervisor` FOREIGN KEY (`matricule`) REFERENCES `employee` (`matricule`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `caresheet` (
  `label` varchar(45) NOT NULL,
  `date` date NOT NULL,
  `animal` varchar(20) NOT NULL,
  PRIMARY KEY (`animal`,`date`),
  KEY `animal_idx` (`animal`),
  KEY `date_idx` (`date`),
  CONSTRAINT `animal` FOREIGN KEY (`animal`) REFERENCES `animal` (`code`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `treatment` (
  `idCare` int NOT NULL,
  `date` date NOT NULL,
  `animal` varchar(20) NOT NULL,
  `isBasic` tinyint NOT NULL,
  `description` varchar(45) NOT NULL,
  `classification` varchar(20) NOT NULL,
  PRIMARY KEY (`idCare`),
  KEY `date_idx` (`date`),
  KEY `animal_idx` (`animal`),
  KEY `classification_idx` (`classification`),
  CONSTRAINT `fk_animal_treatment` FOREIGN KEY (`animal`) REFERENCES `caresheet` (`animal`) ON DELETE CASCADE,
  CONSTRAINT `fk_classification_treatment` FOREIGN KEY (`classification`) REFERENCES `typeofcare` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_date_treatment` FOREIGN KEY (`date`) REFERENCES `caresheet` (`date`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `remark` (
  `number` int NOT NULL,
  `animal` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `description` varchar(45) NOT NULL,
  `isMadeByVeterinarian` varchar(45) NOT NULL,
  `author` varchar(20) NOT NULL,
  PRIMARY KEY (`number`,`animal`,`date`),
  UNIQUE KEY `number_UNIQUE` (`number`),
  KEY `animal_idx` (`animal`),
  KEY `date_idx` (`date`),
  KEY `author_idx` (`author`),
  CONSTRAINT `fk_remark_animal` FOREIGN KEY (`animal`) REFERENCES `caresheet` (`animal`) ON DELETE CASCADE,
  CONSTRAINT `fk_remark_author` FOREIGN KEY (`author`) REFERENCES `employee` (`matricule`) ON DELETE CASCADE,
  CONSTRAINT `fk_remark_date` FOREIGN KEY (`date`) REFERENCES `caresheet` (`date`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `modification` (
  `date` date NOT NULL,
  `animal` varchar(20) NOT NULL,
  `employee` varchar(20) NOT NULL,
  PRIMARY KEY (`date`,`animal`,`employee`),
  KEY `fk_animal_modification_idx` (`animal`),
  KEY `fk_employeel_modification_idx` (`employee`),
  CONSTRAINT `fk_animal_modification` FOREIGN KEY (`animal`) REFERENCES `caresheet` (`animal`) ON DELETE CASCADE,
  CONSTRAINT `fk_date_modification` FOREIGN KEY (`date`) REFERENCES `caresheet` (`date`) ON DELETE CASCADE,
  CONSTRAINT `fk_employeel_modification` FOREIGN KEY (`employee`) REFERENCES `employee` (`matricule`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `prescriptionsheet` (
  `number` int NOT NULL,
  `date` date NOT NULL,
  `prescription` int NOT NULL,
  `author` varchar(20) NOT NULL,
  PRIMARY KEY (`number`),
  KEY `fk_author_prescription_idx` (`author`),
  KEY `fk_prescription_prescription_idx` (`prescription`),
  CONSTRAINT `fk_author_prescription` FOREIGN KEY (`author`) REFERENCES `employee` (`matricule`) ON DELETE CASCADE,
  CONSTRAINT `fk_prescription_prescription` FOREIGN KEY (`prescription`) REFERENCES `treatment` (`idCare`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `preparationsheet` (
  `number` int NOT NULL,
  `date` date DEFAULT NULL,
  `quantity` double NOT NULL,
  `posology` varchar(45) NOT NULL,
  `creation` varchar(45) DEFAULT NULL,
  `detail` int NOT NULL,
  `preparation` varchar(20) DEFAULT NULL,
  `attachment` varchar(20) NOT NULL,
  PRIMARY KEY (`number`),
  KEY `fk_preparation_preparation_idx` (`preparation`),
  KEY `fk_attachment_preparation_idx` (`attachment`),
  KEY `fk_detail_preparation_idx` (`detail`),
  KEY `fk_creation_preparation_idx` (`creation`),
  CONSTRAINT `fk_attachment_preparation` FOREIGN KEY (`attachment`) REFERENCES `animal` (`code`) ON DELETE CASCADE,
  CONSTRAINT `fk_creation_preparation` FOREIGN KEY (`creation`) REFERENCES `employee` (`matricule`) ON DELETE CASCADE,
  CONSTRAINT `fk_detail_preparation` FOREIGN KEY (`detail`) REFERENCES `prescriptionsheet` (`number`) ON DELETE CASCADE,
  CONSTRAINT `fk_preparation_preparation` FOREIGN KEY (`preparation`) REFERENCES `medicine` (`name`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
