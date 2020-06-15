
-- crear base de datos proyecto_citas
create database proyecto_citas;

-- activar la base de datos para su uso
use proyecto_citas;

-- crear la tabla de empleados
CREATE TABLE empleado (
  dni varchar(9) not null,
  nombre varchar(10) NOT NULL,
  apellidos varchar (30) not null,
  password varchar(10) NOT NULL,
  mesa int(2) NOT NULL,
  tipo_tramite int(1) NOT NULL,
  PRIMARY KEY (dni)
  ) ;
  
  -- insertar valores de ejemplo en la tabla de empleados
 INSERT INTO empleado VALUES 
	('45632548l','Stephanie','Cuncun Trastras','fifi',4,4),
    ('45667958l','Manolo','Garcia Rincon','lolo',1,1), 
    ('75987958l','Andres','Rodriguez Huertas','andi',2,2), 
    ('65487958l','Rosa','Pez Lolailo','ros',3,3);
    
-- comprobar resultado
-- select * from empleado;
-- resetear la tabla
-- TRUNCATE TABLE empleado;

-- crear la tabla de usuarios  
CREATE TABLE usuario (
  dni varchar(9) not null,
  nombre varchar(10) NOT NULL,
  apellidos varchar (30) not null,
  password varchar(10) NOT NULL,
  direccion varchar(50),
  cuenta_bancaria varchar(20),
  fecha_nacimiento varchar(10),
  estado varchar(14),
  PRIMARY KEY (dni)
  );

-- insertar valores de ejemplo en la tabla de usuarios
INSERT INTO usuario VALUES 
	('54632548l','Mar','Fito Rodrigez','fito','Calle Lago 3','ES151542235621662546', '03/05/1987','Baja'),
    ('54632525h','Lorena','Perez Lopez','lore','Calle Rio 2','ES151542235621663333', '10/06/1988','Baja'),
    ('526588l','Er','Flato Rodrigez','er','Calle Luis 3','ES159262235621662546', '03/05/1987','Alta'),
    ('123588l','Ic','Flaco Rodrigez','ic','Calle Pepe 3','ES159262235621654546', '03/05/1987', 'Suspendido'),
    ('526526l','Pe','Flato Rogez','pe','Calle Luis 3','ES159262268594662546', '03/05/1987', 'Baja'),
    ('5965489l','Pepe','Flato Rodri','pepe','Calle Jose 3','ES159265952621662546', '03/05/1987', 'Alta'),
    ('612588l','Nana','Maco Rodrigez','na','Calle Luis 3','ES159262231231662546', '03/05/1987', 'Baja'),
    ('123423l','Rosa','Flaco Rodrigez','ic','Calle Pepe 3','ES159262235621654546', '03/05/1987', 'Alta'),
    ('612346l','Lara','Flato Rogez','pe','Calle Luis 3','ES159262268594662546', '03/05/1987', 'Suspendido'),
    ('45685112l','Jose','Luis Perez','jose','Calle San Pedro 3','ES649157631546755546', '13/05/1990', 'Alta'),
    ('54568546l','Maria','Luis Perez','mar','Calle San Pedro 3','ES649157631546755546', '21/06/1992', 'Alta'),
    ('45685422q','Sandra','Luis Perez','san','Calle Puerto 10','ES456851568545451256', '21/10/1995', 'Alta'),
    ('12345678d', 'Juanito', 'Perez','456',null,null,null,null),
    ('98765432f', 'Carlos', 'Sastre','456',null,null,null,null),
    ('45612379o', 'Ramon', 'Mariano','sof',null,null,null,null);

-- comprobar resultado
-- select * from usuario;
-- resetear la tabla
-- TRUNCATE TABLE usuario;


-- crear la tabla de tipo de tramites
  create table tramites(
  id int (1) primary key,
  nombre_tramite varchar (12) not null
  );


 -- insertar valores dentro de la tabla
insert into tramites values (1, 'Altas'), (2, 'Bajas'), (3, 'Suspensiones'), (4, 'Supervision');

-- comprobar resultado
-- select * from tramites;
-- resetear la tabla
-- TRUNCATE TABLE tramites;

-- crear la tabla para los tramites de alta
CREATE TABLE tramite_altas (
  id_alta int(3) not null,
  dni_usuario varchar(9) not null,
  dni_empleado varchar(9) NOT NULL,
  fecha_alta varchar (10) not null,
  tipo_plan varchar(10) NOT NULL,
  precio int(2) NOT NULL,
  tipo_tramite int(1) not null,
  corriente_pago boolean,
  PRIMARY KEY (id_alta),
  KEY emp_alt_fk (dni_empleado),
  KEY usu_alt_fk (dni_usuario),
  KEY tra_alt_fk (tipo_tramite),
  CONSTRAINT emp_alt_fk FOREIGN KEY (dni_empleado) REFERENCES empleado (dni),
  CONSTRAINT usu_alt_fk FOREIGN KEY (dni_usuario) REFERENCES usuario (dni),
  CONSTRAINT tra_alt_fk FOREIGN KEY (tipo_tramite) REFERENCES tramites (id)
  );

-- insertar valores de ejemplo en la tabla de tramites de alta
insert into tramite_altas values
	(1, '123423l', '45667958l', '2020/04/20', 'B', 25, 1, true),
    (2, '123588l', '45667958l', '2020/04/20', 'B', 25, 1, true),
    (3, '526588l', '45667958l', '2020/04/20', 'B', 25, 1, false),
    (4, '5965489l', '45632548l', '2020/06/01', 'C', 50, 1, false),
    (5, '612346l', '45632548l', '2020/06/01', 'A', 30, 1, false),
    (6, '45685112l', '45632548l', '2020/06/03', 'A', 30, 1, true),
    (7, '54568546l', '45667958l', '2020/06/03', 'B', 25, 1, true),
    (8, '45685422q', '45667958l', '2020/06/03', 'C', 50, 1, true);

-- comprobar resultado
-- select * from tramite_altas;
-- resetear la tabla
-- TRUNCATE TABLE tramite_altas;

-- crear la tabla para los tramites de baja
  CREATE TABLE tramite_bajas (
  id_baja int(3) not null,
  dni_usuario varchar(9) not null,
  dni_empleado varchar(9) NOT NULL,
  fecha_baja varchar (10) not null,
  motivo varchar(50) NOT NULL,
  corriente_pago bool NOT NULL,
  tipo_tramite int(1) not null,
  PRIMARY KEY (id_baja),
  KEY emp_baj_fk (dni_empleado),
  KEY usu_baj_fk (dni_usuario),
  KEY tra_baj_fk (tipo_tramite),
  CONSTRAINT emp_baj_fk FOREIGN KEY (dni_empleado) REFERENCES empleado (dni),
  CONSTRAINT usu_baj_fk FOREIGN KEY (dni_usuario) REFERENCES usuario (dni),
  CONSTRAINT tra_baj_fk FOREIGN KEY (tipo_tramite) REFERENCES tramites (id)
  );

-- insertar valores de ejemplo en la tabla de tramites de baja
insert into tramite_bajas values
	(1, '54632548l', '75987958l', '2020/04/20', 'TIE', true, 2),
	(2, '526526l', '75987958l', '2019/12/13', 'TIE,SAT', true, 2),
	(3, '54632525h', '75987958l', '2020/01/14', 'SAT,LOC', true, 2),
	(4, '612588l', '45632548l', '2020/03/20', 'DIN', false, 2);

-- comprobar resultado
-- select * from tramite_bajas;
-- resetear la tabla
-- TRUNCATE TABLE tramite_bajas;
 
--  crear la tabla para los tramites de suspensiones
CREATE TABLE tramite_suspensiones (
	id_suspension int(3) not null,
	dni_usuario varchar(9) not null,
	dni_empleado varchar(9) NOT NULL,
	fecha_ini_sus varchar (10) not null,
	fecha_fin_sus varchar  (10) not null,
	motivo_sus varchar(50) NOT NULL,
	corriente_pago bool NOT NULL,
	tipo_tramite int(1) not null,
	PRIMARY KEY (id_suspension),
	KEY emp_sus_fk (dni_empleado),
	KEY usu_sus_fk (dni_usuario),
	KEY tra_sus_fk (tipo_tramite),
	CONSTRAINT emp_sus_fk FOREIGN KEY (dni_empleado) REFERENCES empleado (dni),
	CONSTRAINT usu_sus_fk FOREIGN KEY (dni_usuario) REFERENCES usuario (dni),
	CONSTRAINT tra_sus_fk FOREIGN KEY (tipo_tramite) REFERENCES tramites (id)
	);
    
-- insertar valores de ejemplo en la tabla de los tramites de suspension
insert into tramite_suspensiones values
(1, '123588l', '45632548l', '2020/03/15', '2020/06/15', 'Causas ajenas al gimnasio y al usuario', true, 3),
(2, '612346l', '65487958l', '2020/06/15', '2020/07/15', 'Solicitud del usuario', false, 3);

-- comprobar resultado
-- select * from tramite_suspensiones;
-- resetear la tabla
-- TRUNCATE TABLE tramite_suspensiones;
 
-- crear la tabla para las citas
CREATE TABLE citas (
  codigo_cita varchar(6) not null,
  dni_usuario varchar(9) not null,
  fecha varchar (10) not null,
  hora varchar (8) not null,
  llamado bool not null,
  tipo_tramite int(1) not null,
  orden int(3) not null,
  PRIMARY KEY (codigo_cita),
  KEY usu_sus_fk (dni_usuario),
  KEY tra_cit_fk (tipo_tramite),
  CONSTRAINT usu_cit_fk FOREIGN KEY (dni_usuario) REFERENCES usuario (dni),
  CONSTRAINT tra_cit_fk FOREIGN KEY (tipo_tramite) REFERENCES tramites (id)
  );

-- insertar valores de ejemplo en la tabla de las citas
insert into citas values 
	('ALT123','12345678d', '2020/06/16', '20:00', false, 1, 1),
	('ALT124','98765432f', '2020/06/16', '20:00', false, 1, 2),
    ('ALT125','612588l', '2020/06/16', '20:00', false, 1, 3),
    ('SUS126','123423l', '2020/06/16', '20:00', false, 3, 4),
    ('SUS127','45685112l', '2020/06/16', '20:00', false, 3, 5),
    ('SUS128','45685422q', '2020/06/16', '20:00', false, 3, 6),
    ('BAJ129','526588l', '2020/06/16', '20:00', false, 2, 7),
    ('BAJ130','54568546l', '2020/06/16', '20:00', false, 2, 8),
    ('BAJ131','5965489l', '2020/06/16', '20:00', false, 2, 9),
    ('SUP123','123588l', '2020/06/16','20:00', false, 4, 10),
    ('SUP125','612346l', '2020/06/16','20:00', false, 4, 11),
    ('SUP127','45612379o', '2020/06/16','20:00', false, 4, 12);
    
-- comprobar resultado
-- select * from citas order by orden asc;
-- resetear la tabla
-- TRUNCATE TABLE citas;

-- crear la tabla para almacenar el historial de tramitaciones  
  CREATE TABLE historial (
  id_historial int (3) not null,
  dni_usuario varchar(9) not null,
  dni_empleado varchar (9) not null,
  fecha_cita varchar (10) not null,
  hora_cita varchar (8) not null,
  hora_finalizado varchar (8) not null,
  realizado bool not null,
  tipo_tramite int(1) not null,
  PRIMARY KEY (id_historial),
  KEY usu_his_fk (dni_usuario),
  key emp_his_fk (dni_empleado),
  KEY tra_hist_fk (tipo_tramite),
  CONSTRAINT usu_his_fk FOREIGN KEY (dni_usuario) REFERENCES usuario (dni),
  CONSTRAINT emp_his_fk FOREIGN KEY (dni_empleado) REFERENCES empleado (dni),
  CONSTRAINT tra_his_fk FOREIGN KEY (tipo_tramite) REFERENCES tramites (id)
  );

-- comprobar resultado
-- select * from historial;
-- resetear tabla
-- TRUNCATE TABLE historial;


