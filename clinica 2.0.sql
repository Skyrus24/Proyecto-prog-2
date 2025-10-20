/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 8.0.36 : Database - clinica
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`clinica` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `clinica`;

/*Table structure for table `citas` */

DROP TABLE IF EXISTS `citas`;

CREATE TABLE `citas` (
  `id_cita` int NOT NULL AUTO_INCREMENT,
  `id_paciente` int NOT NULL,
  `id_medico` int NOT NULL,
  `id_consultorio` int DEFAULT NULL,
  `fecha_hora_inicio` datetime NOT NULL,
  `fecha_hora_fin` datetime NOT NULL,
  `motivo_consulta` text COLLATE utf8mb4_unicode_ci,
  `estado_cita` enum('Programada','Confirmada','Cancelada','Atendida','No Asistió') COLLATE utf8mb4_unicode_ci DEFAULT 'Programada',
  `tipo_cita` enum('Primera Vez','Seguimiento','Urgencia','Control') COLLATE utf8mb4_unicode_ci NOT NULL,
  `observaciones` text COLLATE utf8mb4_unicode_ci,
  `fecha_creacion` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_cita`),
  KEY `fk_cita_paciente` (`id_paciente`),
  KEY `fk_cita_medico` (`id_medico`),
  KEY `fk_cita_consultorio` (`id_consultorio`),
  CONSTRAINT `fk_cita_consultorio` FOREIGN KEY (`id_consultorio`) REFERENCES `consultorios` (`id_consultorio`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_cita_medico` FOREIGN KEY (`id_medico`) REFERENCES `medicos` (`id_medico`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_cita_paciente` FOREIGN KEY (`id_paciente`) REFERENCES `pacientes` (`id_paciente`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `citas` */

insert  into `citas`(`id_cita`,`id_paciente`,`id_medico`,`id_consultorio`,`fecha_hora_inicio`,`fecha_hora_fin`,`motivo_consulta`,`estado_cita`,`tipo_cita`,`observaciones`,`fecha_creacion`) values 
(1,1,1,1,'2023-10-01 08:00:00','2023-10-01 08:30:00','Dolor de cabeza persistente.','Atendida','Primera Vez',NULL,'2025-10-14 13:04:02'),
(2,2,2,2,'2023-10-02 09:00:00','2023-10-02 09:30:00','Chequeo de presión arterial.','Atendida','Control',NULL,'2025-10-14 13:04:02'),
(3,3,3,3,'2023-10-03 14:00:00','2023-10-03 14:30:00','Fiebre alta y tos.','Atendida','Urgencia',NULL,'2025-10-14 13:04:02'),
(4,4,4,4,'2023-10-04 10:00:00','2023-10-04 10:30:00','Revisión de mancha en la piel.','Atendida','Seguimiento',NULL,'2025-10-14 13:04:02'),
(5,5,5,5,'2023-10-05 15:00:00','2023-10-05 15:30:00','Golpe fuerte en el tobillo.','Atendida','Urgencia',NULL,'2025-10-14 13:04:02');

/*Table structure for table `consultorios` */

DROP TABLE IF EXISTS `consultorios`;

CREATE TABLE `consultorios` (
  `id_consultorio` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ubicacion` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `capacidad` int DEFAULT '1',
  PRIMARY KEY (`id_consultorio`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `consultorios` */

insert  into `consultorios`(`id_consultorio`,`nombre`,`ubicacion`,`capacidad`) values 
(1,'Consultorio 1','Planta Baja - Sala 1',1),
(2,'Consultorio 2','Planta Baja - Sala 2',1),
(3,'Consultorio 3','Primer Piso - Sala 1',1),
(4,'Consultorio 4','Primer Piso - Sala 2',1),
(5,'Consultorio 5','Emergencias',2);

/*Table structure for table `especialidades` */

DROP TABLE IF EXISTS `especialidades`;

CREATE TABLE `especialidades` (
  `id_especialidad` int NOT NULL AUTO_INCREMENT,
  `nombre_especialidad` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `descripcion` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id_especialidad`),
  UNIQUE KEY `nombre_especialidad` (`nombre_especialidad`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `especialidades` */

insert  into `especialidades`(`id_especialidad`,`nombre_especialidad`,`descripcion`) values 
(1,'Medicina General','Atención primaria, diagnóstico y tratamiento de enfermedades comunes.'),
(2,'Cardiología','Estudio, diagnóstico y tratamiento de las enfermedades del corazón.'),
(3,'Pediatría','Atención médica de bebés, niños y adolescentes.'),
(4,'Dermatología','Tratamiento de enfermedades de la piel, cabello y uñas.'),
(5,'Traumatología','Lesiones del aparato locomotor (huesos, ligamentos, articulaciones).');

/*Table structure for table `historial_clinico` */

DROP TABLE IF EXISTS `historial_clinico`;

CREATE TABLE `historial_clinico` (
  `id_historial` int NOT NULL AUTO_INCREMENT,
  `id_cita` int NOT NULL,
  `id_paciente` int NOT NULL,
  `id_medico` int NOT NULL,
  `fecha_registro` datetime DEFAULT CURRENT_TIMESTAMP,
  `antecedentes` text COLLATE utf8mb4_unicode_ci,
  `diagnostico` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `enfermedades` text COLLATE utf8mb4_unicode_ci,
  `tratamiento` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `resultados_examenes` text COLLATE utf8mb4_unicode_ci,
  `notas_evolucion` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id_historial`),
  UNIQUE KEY `id_cita` (`id_cita`),
  KEY `id_paciente` (`id_paciente`),
  KEY `id_medico` (`id_medico`),
  CONSTRAINT `historial_clinico_ibfk_1` FOREIGN KEY (`id_cita`) REFERENCES `citas` (`id_cita`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `historial_clinico_ibfk_2` FOREIGN KEY (`id_paciente`) REFERENCES `pacientes` (`id_paciente`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `historial_clinico_ibfk_3` FOREIGN KEY (`id_medico`) REFERENCES `medicos` (`id_medico`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `historial_clinico` */

insert  into `historial_clinico`(`id_historial`,`id_cita`,`id_paciente`,`id_medico`,`fecha_registro`,`antecedentes`,`diagnostico`,`enfermedades`,`tratamiento`,`resultados_examenes`,`notas_evolucion`) values 
(1,1,1,1,'2025-10-14 13:04:02','Sin antecedentes patológicos.','Cefalea tensional.',NULL,'Ibuprofeno 400mg cada 8h por 3 días.','Examen físico normal.','Mejoría a las 24 horas.'),
(2,2,2,2,'2025-10-14 13:04:02','Padre hipertenso.','Hipertensión leve.',NULL,'Enalapril 10mg diarios.','ECG sin alteraciones.','Control en 1 mes.'),
(3,3,3,3,'2025-10-14 13:04:02','Asma bronquial.','Faringitis viral.',NULL,'Paracetamol y líquidos.','Hemograma normal.','Revisión en 3 días.'),
(4,4,4,4,'2025-10-14 13:04:02','Exposición solar.','Dermatitis de contacto.',NULL,'Crema corticoide y antihistamínico.',NULL,'Evitar agente irritante.'),
(5,5,5,5,'2025-10-14 13:04:02','Jugador de fútbol.','Esguince de tobillo.',NULL,'Inmovilización y reposo.','Radiografía sin fractura.','Fisioterapia en 1 semana.');

/*Table structure for table `horarios` */

DROP TABLE IF EXISTS `horarios`;

CREATE TABLE `horarios` (
  `id_horario` int NOT NULL AUTO_INCREMENT,
  `id_medico` int NOT NULL,
  `dia_semana` int NOT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL,
  `fecha_inicio_validez` date DEFAULT NULL,
  `fecha_fin_validez` date DEFAULT NULL,
  PRIMARY KEY (`id_horario`),
  KEY `id_medico` (`id_medico`),
  CONSTRAINT `horarios_ibfk_1` FOREIGN KEY (`id_medico`) REFERENCES `medicos` (`id_medico`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `horarios` */

insert  into `horarios`(`id_horario`,`id_medico`,`dia_semana`,`hora_inicio`,`hora_fin`,`fecha_inicio_validez`,`fecha_fin_validez`) values 
(1,1,1,'08:00:00','12:00:00',NULL,NULL),
(2,2,2,'08:00:00','16:00:00',NULL,NULL),
(3,3,3,'13:00:00','19:00:00',NULL,NULL),
(4,4,4,'09:00:00','13:00:00',NULL,NULL),
(5,5,5,'14:00:00','20:00:00',NULL,NULL);

/*Table structure for table `medicamentos` */

DROP TABLE IF EXISTS `medicamentos`;

CREATE TABLE `medicamentos` (
  `id_medicamento` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `descripcion` text COLLATE utf8mb4_unicode_ci,
  `dosis_recomendada` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_medicamento`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `medicamentos` */

insert  into `medicamentos`(`id_medicamento`,`nombre`,`descripcion`,`dosis_recomendada`) values 
(1,'Ibuprofeno','Analgésico y antiinflamatorio.','400 mg cada 8 horas.'),
(2,'Enalapril','Antihipertensivo.','10 mg cada día.'),
(3,'Paracetamol','Antipirético y analgésico.','500 mg cada 6 horas.'),
(4,'Loratadina','Antihistamínico.','10 mg una vez al día.'),
(5,'Amoxicilina','Antibiótico de amplio espectro.','500 mg cada 8 horas.');

/*Table structure for table `medicos` */

DROP TABLE IF EXISTS `medicos`;

CREATE TABLE `medicos` (
  `id_medico` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `apellidos` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_especialidad` int NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `telefono` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `numero_licencia` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_medico`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `numero_licencia` (`numero_licencia`),
  KEY `id_especialidad` (`id_especialidad`),
  CONSTRAINT `fk_medico_especialidad` FOREIGN KEY (`id_especialidad`) REFERENCES `especialidades` (`id_especialidad`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `medicos` */

insert  into `medicos`(`id_medico`,`nombre`,`apellidos`,`id_especialidad`,`email`,`telefono`,`numero_licencia`) values 
(1,'Juan','Pérez González',1,'juan.perez@clinica.com','0981111222','REG-1001'),
(2,'María','López Acosta',2,'maria.lopez@clinica.com','0971222333','REG-2002'),
(3,'Carlos','Ramírez Sosa',3,'carlos.ramirez@clinica.com','0991333444','REG-3003'),
(4,'Ana','Torres Benítez',4,'ana.torres@clinica.com','0982444555','REG-4004'),
(5,'Roberto','Fernández Díaz',5,'roberto.fernandez@clinica.com','0972555666','REG-5005');

/*Table structure for table `pacientes` */

DROP TABLE IF EXISTS `pacientes`;

CREATE TABLE `pacientes` (
  `id_paciente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `apellidos` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `genero` enum('Masculino','Femenino','Otro') COLLATE utf8mb4_unicode_ci NOT NULL,
  `numero_documento` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tipo_documento` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `direccion` text COLLATE utf8mb4_unicode_ci,
  `ciudad` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `barrio` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `telefono` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `informacion_adicional` text COLLATE utf8mb4_unicode_ci,
  `fecha_registro` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_paciente`),
  UNIQUE KEY `numero_documento` (`numero_documento`),
  UNIQUE KEY `email` (`email`),
  KEY `idx_paciente_nombre` (`apellidos`,`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `pacientes` */

insert  into `pacientes`(`id_paciente`,`nombre`,`apellidos`,`fecha_nacimiento`,`genero`,`numero_documento`,`tipo_documento`,`direccion`,`ciudad`,`barrio`,`telefono`,`email`,`informacion_adicional`,`fecha_registro`) values 
(1,'Laura','Gómez Martínez','1990-05-15','Femenino','1234567','CI','Av. Principal 123',NULL,NULL,'0983000111','laura.gomez@email.com',NULL,'2025-10-14 13:04:02'),
(2,'Pedro','Alcaraz Ruiz','1985-11-20','Masculino','2345678','CI','Calle Palma 456',NULL,NULL,'0973000222','pedro.alcaraz@email.com',NULL,'2025-10-14 13:04:02'),
(3,'Sofía','Vázquez Silva','2015-03-10','Femenino','3456789','CI','Barrio San Roque',NULL,NULL,'0994000333','mama.sofia@email.com',NULL,'2025-10-14 13:04:02'),
(4,'Miguel','Ortega Duarte','1978-08-25','Masculino','4567890','CI','Ruta 1 Km 20',NULL,NULL,'0985000444','miguel.ortega@email.com',NULL,'2025-10-14 13:04:02'),
(5,'Elena','Mendoza Cabral','1995-12-05','Femenino','5678901','CI','Centro Ciudad',NULL,NULL,'0975000555','elena.mendoza@email.com',NULL,'2025-10-14 13:04:02');

/*Table structure for table `recetas` */

DROP TABLE IF EXISTS `recetas`;

CREATE TABLE `recetas` (
  `id_receta` int NOT NULL AUTO_INCREMENT,
  `id_historial` int NOT NULL,
  `id_medicamento` int NOT NULL,
  `cantidad` int NOT NULL,
  `instrucciones` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id_receta`),
  KEY `id_historial` (`id_historial`),
  KEY `id_medicamento` (`id_medicamento`),
  CONSTRAINT `recetas_ibfk_1` FOREIGN KEY (`id_historial`) REFERENCES `historial_clinico` (`id_historial`) ON DELETE CASCADE,
  CONSTRAINT `recetas_ibfk_2` FOREIGN KEY (`id_medicamento`) REFERENCES `medicamentos` (`id_medicamento`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `recetas` */

insert  into `recetas`(`id_receta`,`id_historial`,`id_medicamento`,`cantidad`,`instrucciones`) values 
(1,1,1,6,'Tomar con agua después de las comidas.'),
(2,2,2,30,'Tomar una tableta diaria.'),
(3,3,3,12,'Tomar cada 6 horas mientras haya fiebre.'),
(4,4,4,10,'Tomar por 5 días consecutivos.'),
(5,5,1,8,'Solo si hay dolor intenso.');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
