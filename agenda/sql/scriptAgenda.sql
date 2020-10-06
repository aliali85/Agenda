CREATE DATABASE IF NOT EXISTS `grupo_12`;
USE grupo_12;
CREATE TABLE IF NOT EXISTS `persona`(
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `email` varchar(70),
  `fnac` varchar(10),
  `calle` varchar(50),
  `altura` varchar(8),
  `piso` varchar(5),
  `departamento` varchar(10),
  `localidad` varchar(30),
  `tcont` varchar(30),
  PRIMARY KEY (`idPersona`)
);
CREATE TABLE IF NOT EXISTS `ubicacion`(
  `idUbicacion` int(10) NOT NULL AUTO_INCREMENT,
  `localidad` varchar(30) NOT NULL,
  `provincia` varchar(30) NOT NULL,
  `pais` varchar(30) NOT NULL,
  PRIMARY KEY (`idUbicacion`)
);

insert into persona (nombre,telefono,email,fnac,calle,altura,piso,departamento,localidad,tcont)
				values ('cristian','1127839234','cristian@gmail.com','','','','','','','');
insert into persona (nombre,telefono,email,fnac,calle,altura,piso,departamento,localidad,tcont)
				values ('jose','1137286349','jose@gmail.com','','','','','','','');
insert into persona (nombre,telefono,email,fnac,calle,altura,piso,departamento,localidad,tcont) 
				values ('malena','1173927102','malena@gmail.com','','','','','','','');
insert into persona (nombre,telefono,email,fnac,calle,altura,piso,departamento,localidad,tcont)
				values('agustina','1182037485','agustina@gmail.com','','','','','','','');

insert into ubicacion (localidad,provincia,pais)
				values ('San Miguel','Buenos Aires','Argentina');
insert into ubicacion (localidad,provincia,pais) 
				values('Los Polvorines','Buenos Aires','Argentina');
insert into ubicacion (localidad,provincia,pais) 
				values('Moron','Buenos Aires','Argentina');
insert into ubicacion (localidad,provincia,pais) 
				values('Moreno','Buenos Aires','Argentina');