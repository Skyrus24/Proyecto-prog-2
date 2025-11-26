-- Creación de la base de datos
CREATE DATABASE IF NOT EXISTS `clinica` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `clinica`;

-- Creación de Tablas

CREATE TABLE `especialidades` (
  `id_especialidad` INT NOT NULL AUTO_INCREMENT,
  `nombre_especialidad` VARCHAR(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `descripcion` TEXT COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id_especialidad`),
  UNIQUE KEY `nombre_especialidad` (`nombre_especialidad`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `medicos` (
  `id_medico` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `apellidos` VARCHAR(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fecha_nacimiento` DATE NULL,
  `nacionalidad` VARCHAR(50) COLLATE utf8mb4_unicode_ci DEFAULT 'Paraguaya',
  `cedula` VARCHAR(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_especialidad` INT NOT NULL,
  `email` VARCHAR(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `telefono` VARCHAR(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `direccion` TEXT COLLATE utf8mb4_unicode_ci,
  `registro_profesional` VARCHAR(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fecha_registro_profesional` DATE NULL,
  `fecha_vencimiento_registro` DATE NULL,
  `estado_registro` ENUM('Activo', 'Inactivo', 'Suspendido', 'En trámite') COLLATE utf8mb4_unicode_ci DEFAULT 'Activo',
  PRIMARY KEY (`id_medico`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `registro_profesional` (`registro_profesional`),
  UNIQUE KEY `idx_medicos_cedula` (`cedula`),
  KEY `id_especialidad` (`id_especialidad`),
  CONSTRAINT `fk_medico_especialidad` FOREIGN KEY (`id_especialidad`) REFERENCES `especialidades` (`id_especialidad`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `pacientes` (
  `id_paciente` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `apellidos` VARCHAR(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `genero` ENUM('Masculino','Femenino','Otro') COLLATE utf8mb4_unicode_ci NOT NULL,
  `numero_documento` VARCHAR(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tipo_documento` ENUM('Cédula de Identidad (CI)', 'Pasaporte', 'Cédula Extranjera', 'Carnet de Migraciones', 'Sin Documento (NN)', 'Otro') COLLATE utf8mb4_unicode_ci NOT NULL,
  `direccion` TEXT COLLATE utf8mb4_unicode_ci,
  `ciudad` VARCHAR(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `barrio` VARCHAR(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `telefono` VARCHAR(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` VARCHAR(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `informacion_adicional` TEXT COLLATE utf8mb4_unicode_ci,
  `fecha_registro` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_paciente`),
  UNIQUE KEY `idx_documento` (`numero_documento`, `tipo_documento`),
  UNIQUE KEY `email` (`email`),
  KEY `idx_paciente_nombre` (`apellidos`,`nombre`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `consultorios` (
  `id_consultorio` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ubicacion` VARCHAR(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `capacidad` INT DEFAULT '1',
  PRIMARY KEY (`id_consultorio`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `citas` (
  `id_cita` INT NOT NULL AUTO_INCREMENT,
  `id_paciente` INT NOT NULL,
  `id_medico` INT NOT NULL,
  `id_consultorio` INT DEFAULT NULL,
  `fecha_hora_inicio` DATETIME NOT NULL,
  `fecha_hora_fin` DATETIME NOT NULL,
  `motivo_consulta` TEXT COLLATE utf8mb4_unicode_ci,
  `estado_cita` ENUM('Programada','Confirmada','Cancelada','Atendida','No Asistió') COLLATE utf8mb4_unicode_ci DEFAULT 'Programada',
  `tipo_cita` ENUM('Primera Vez','Seguimiento','Urgencia','Control') COLLATE utf8mb4_unicode_ci NOT NULL,
  `observaciones` TEXT COLLATE utf8mb4_unicode_ci,
  `fecha_creacion` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_cita`),
  KEY `fk_cita_paciente` (`id_paciente`),
  KEY `fk_cita_medico` (`id_medico`),
  KEY `fk_cita_consultorio` (`id_consultorio`),
  CONSTRAINT `fk_cita_consultorio` FOREIGN KEY (`id_consultorio`) REFERENCES `consultorios` (`id_consultorio`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_cita_medico` FOREIGN KEY (`id_medico`) REFERENCES `medicos` (`id_medico`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_cita_paciente` FOREIGN KEY (`id_paciente`) REFERENCES `pacientes` (`id_paciente`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `historial_clinico` (
  `id_historial` INT NOT NULL AUTO_INCREMENT,
  `id_cita` INT NOT NULL,
  `id_paciente` INT NOT NULL,
  `id_medico` INT NOT NULL,
  `fecha_registro` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `antecedentes` TEXT COLLATE utf8mb4_unicode_ci,
  `diagnostico` TEXT COLLATE utf8mb4_unicode_ci NOT NULL,
  `enfermedades` TEXT COLLATE utf8mb4_unicode_ci,
  `tratamiento` TEXT COLLATE utf8mb4_unicode_ci NOT NULL,
  `resultados_examenes` TEXT COLLATE utf8mb4_unicode_ci,
  `notas_evolucion` TEXT COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id_historial`),
  UNIQUE KEY `id_cita` (`id_cita`),
  KEY `id_paciente` (`id_paciente`),
  KEY `id_medico` (`id_medico`),
  CONSTRAINT `historial_clinico_ibfk_1` FOREIGN KEY (`id_cita`) REFERENCES `citas` (`id_cita`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `historial_clinico_ibfk_2` FOREIGN KEY (`id_paciente`) REFERENCES `pacientes` (`id_paciente`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `historial_clinico_ibfk_3` FOREIGN KEY (`id_medico`) REFERENCES `medicos` (`id_medico`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `horarios` (
  `id_horario` INT NOT NULL AUTO_INCREMENT,
  `id_medico` INT NOT NULL,
  `dia_semana` INT NOT NULL,
  `hora_inicio` TIME NOT NULL,
  `hora_fin` TIME NOT NULL,
  `fecha_inicio_validez` DATE DEFAULT NULL,
  `fecha_fin_validez` DATE DEFAULT NULL,
  PRIMARY KEY (`id_horario`),
  KEY `id_medico` (`id_medico`),
  CONSTRAINT `horarios_ibfk_1` FOREIGN KEY (`id_medico`) REFERENCES `medicos` (`id_medico`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `medicamentos` (
  `id_medicamento` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `descripcion` TEXT COLLATE utf8mb4_unicode_ci,
  `dosis_recomendada` VARCHAR(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_medicamento`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `recetas` (
  `id_receta` INT NOT NULL AUTO_INCREMENT,
  `id_historial` INT NOT NULL,
  `id_medicamento` INT NOT NULL,
  `cantidad` INT NOT NULL,
  `instrucciones` TEXT COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id_receta`),
  KEY `id_historial` (`id_historial`),
  KEY `id_medicamento` (`id_medicamento`),
  CONSTRAINT `recetas_ibfk_1` FOREIGN KEY (`id_historial`) REFERENCES `historial_clinico` (`id_historial`) ON DELETE CASCADE,
  CONSTRAINT `recetas_ibfk_2` FOREIGN KEY (`id_medicamento`) REFERENCES `medicamentos` (`id_medicamento`) ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `roles` (
  `id_rol` INT AUTO_INCREMENT PRIMARY KEY,
  `nombre_rol` VARCHAR(50) NOT NULL UNIQUE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `usuarios` (
  `id_usuario` INT AUTO_INCREMENT PRIMARY KEY,
  `nombre_usuario` VARCHAR(50) NOT NULL UNIQUE,
  `contrasena` VARCHAR(255) NOT NULL,
  `id_rol` INT NOT NULL,
  FOREIGN KEY (`id_rol`) REFERENCES `roles`(`id_rol`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- Inserción de Datos

INSERT INTO `especialidades`(`nombre_especialidad`,`descripcion`) VALUES 
('Medicina General','Atención primaria, diagnóstico y tratamiento de enfermedades comunes.'),
('Cardiología','Estudio, diagnóstico y tratamiento de las enfermedades del corazón.'),
('Pediatría','Atención médica de bebés, niños y adolescentes.'),
('Dermatología','Tratamiento de enfermedades de la piel, cabello y uñas.'),
('Traumatología','Lesiones del aparato locomotor (huesos, ligamentos, articulaciones).');

INSERT INTO `medicos`(`nombre`, `apellidos`, `fecha_nacimiento`, `nacionalidad`, `cedula`, `id_especialidad`, `email`, `telefono`, `direccion`, `registro_profesional`, `fecha_registro_profesional`, `fecha_vencimiento_registro`, `estado_registro`) VALUES 
('Juan','Pérez González','1980-04-10','Paraguaya','1111111',1,'juan.perez@clinica.com','0981111222','Av. Mcal. López 123, Asunción','REG-1001','2008-05-20','2028-05-20','Activo'),
('María','López Acosta','1985-09-22','Paraguaya','2222222',2,'maria.lopez@clinica.com','0971222333','Coronel Oviedo 456, Fernando de la Mora','REG-2002','2012-02-15','2027-02-15','Activo'),
('Carlos','Ramírez Sosa','1975-01-30','Paraguaya','3333333',3,'carlos.ramirez@clinica.com','0991333444','Av. Eusebio Ayala 789, Asunción','REG-3003','2005-11-01','2025-11-01','Activo'),
('Ana','Torres Benítez','1990-11-12','Paraguaya','4444444',4,'ana.torres@clinica.com','0982444555','Calle Última 159, Lambaré','REG-4004','2018-08-10','2028-08-10','Activo'),
('Roberto','Fernández Díaz','1982-07-05','Argentino','5555555',5,'roberto.fernandez@clinica.com','0972555123','Av. Aviadores del Chaco 321, Asunción','REG-5005','2010-03-25','2030-03-25','Activo');

INSERT INTO `pacientes`(`nombre`,`apellidos`,`fecha_nacimiento`,`genero`,`numero_documento`,`tipo_documento`,`direccion`,`telefono`,`email`) VALUES 
('Laura','Gómez Martínez','1990-05-15','Femenino','1234567','Cédula de Identidad (CI)','Av. Principal 123','0983000111','laura.gomez@email.com'),
('Pedro','Alcaraz Ruiz','1985-11-20','Masculino','2345678','Cédula de Identidad (CI)','Calle Palma 456','0973000222','pedro.alcaraz@email.com'),
('Sofía','Vázquez Silva','2015-03-10','Femenino','3456789','Cédula de Identidad (CI)','Barrio San Roque','0994000333','mama.sofia@email.com'),
('John','Smith','1988-08-01','Masculino','A1B23C45','Pasaporte','Main Street 456','+1-555-0101','john.smith@email.com'),
('Elena','Mendoza Cabral','1995-12-05','Femenino','E-98765','Cédula Extranjera','Av. Internacional 789','0975000555','elena.mendoza@email.com');

INSERT INTO `consultorios`(`nombre`,`ubicacion`,`capacidad`) VALUES 
('Consultorio 1','Planta Baja - Sala 1',1),
('Consultorio 2','Planta Baja - Sala 2',1),
('Consultorio 3','Primer Piso - Sala 1',1),
('Consultorio 4','Primer Piso - Sala 2',1),
('Consultorio de Urgencias','Emergencias',2);

INSERT INTO `citas`(`id_paciente`,`id_medico`,`id_consultorio`,`fecha_hora_inicio`,`fecha_hora_fin`,`motivo_consulta`,`estado_cita`,`tipo_cita`) VALUES 
(1,1,1,'2025-11-10 08:00:00','2025-11-10 08:30:00','Dolor de cabeza persistente.','Atendida','Primera Vez'),
(2,2,2,'2025-11-11 09:00:00','2025-11-11 09:30:00','Chequeo de presión arterial.','Atendida','Control'),
(3,3,3,'2025-11-12 14:00:00','2025-11-12 14:30:00','Fiebre alta y tos.','Atendida','Urgencia'),
(4,4,4,'2025-11-13 10:00:00','2025-11-13 10:30:00','Revisión de mancha en la piel.','Confirmada','Seguimiento'),
(5,5,5,'2025-11-14 15:00:00','2025-11-14 15:30:00','Golpe fuerte en el tobillo.','Programada','Urgencia');

INSERT INTO `historial_clinico`(`id_cita`,`id_paciente`,`id_medico`,`diagnostico`,`tratamiento`) VALUES 
(1,1,1,'Cefalea tensional.','Ibuprofeno 400mg cada 8h por 3 días.'),
(2,2,2,'Hipertensión Leve.','Enalapril 10mg diarios y dieta baja en sodio.'),
(3,3,3,'Faringitis viral.','Paracetamol y abundantes líquidos.'),
(4,4,4,'Dermatitis de contacto.','Crema corticoide y evitar agente irritante.'),
(5,5,5,'Esguince de tobillo grado I.','Reposo, hielo y antiinflamatorios.');

-- día_semana: 1=Lunes, 2=Martes, ..., 7=Domingo
INSERT INTO `horarios`(`id_medico`,`dia_semana`,`hora_inicio`,`hora_fin`) VALUES 
(1,1,'08:00:00','12:00:00'),
(2,2,'08:00:00','16:00:00'),
(3,3,'13:00:00','19:00:00'),
(4,4,'09:00:00','13:00:00'),
(5,5,'14:00:00','20:00:00');

INSERT INTO `medicamentos`(`nombre`,`descripcion`,`dosis_recomendada`) VALUES 
('Ibuprofeno','Analgésico y antiinflamatorio.','400 mg cada 8 horas.'),
('Enalapril','Antihipertensivo.','10 mg cada día.'),
('Paracetamol','Antipirético y analgésico.','500 mg cada 6 horas.'),
('Loratadina','Antihistamínico.','10 mg una vez al día.'),
('Amoxicilina','Antibiótico de amplio espectro.','500 mg cada 8 horas.');

INSERT INTO `recetas`(`id_historial`,`id_medicamento`,`cantidad`,`instrucciones`) VALUES 
(1,1,1,6,'Tomar con agua después de las comidas.'),
(2,2,2,30,'Tomar una tableta diaria, preferiblemente por la mañana.'),
(3,3,3,12,'Tomar cada 6 horas mientras haya fiebre.'),
(4,4,1,10,'Aplicar en la zona afectada 2 veces al día.'),
(5,5,1,10,'Tomar 1 comprimido cada 8 horas si hay dolor.');

INSERT INTO `roles` (nombre_rol) VALUES 
('Administrador'), 
('Medico'), 
('Recepcionista');

INSERT INTO `usuarios` (nombre_usuario, contrasena, id_rol) VALUES
('admin', SHA2('admin123', 256), 1),
('medico', SHA2('medico123', 256), 2),
('recepcion', SHA2('recep123', 256), 3);