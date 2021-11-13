drop database if exists workwide;
create database workwide default character set utf8mb4;
use workwide;
SET FOREIGN_KEY_CHECKS = 0;

-- TABLAS SIN LLAVES FORÁNEAS

create table tipo_usuario(
id_tipo_usu int primary key not null auto_increment,
desc_tipo_usu nvarchar(20)
);

create table estado_trabajo(
id_est_trab int primary key not null auto_increment,
estado_trab nvarchar(20)
);

create table estado_soli(
id_est_soli int primary key not null auto_increment,
estado_soli nvarchar(20)
);

create table tipo_trabajo(
id_tipo_trab int primary key not null auto_increment,
nombre_tipo_trab nvarchar(40)
);

create table region(
id_region int primary key not null auto_increment,
nombre_region nvarchar(20)
);

-- TABLAS DE ENTIDADES

create table usuario(
id_usu int primary key not null auto_increment,
nombre_usu nvarchar(20),
apellido_usu nvarchar(20),
correo_usu nvarchar(40) not null,
contrasena_usu nvarchar(200) not null,
telefono_usu nvarchar(10),
profile_usu longblob,
banner_usu longblob,
desc_usu nvarchar(2000)
);

create table solicitud(
id_soli int primary key not null auto_increment,
fecha_inicio_soli date,
fecha_fin_soli date,
desc_soli nvarchar(500),
titulo_soli nvarchar(50)
);

create table mensaje(
id_msg int primary key not null auto_increment,
content_msg nvarchar(1000)
);

create table trabajo(
id_trab int primary key not null auto_increment,
titulo_trab nvarchar(50),
desc_trab nvarchar(500),
fecha_inicio_trab date,
fecha_fin_trab date
);

-- TABLAS DÉBILES

create table envia_solicitud(
id_soli int,
id_usu int,
foreign key (id_soli) references solicitud(id_soli),
foreign key (id_usu) references usuario(id_usu) ON DELETE cascade
);

create table recibe_solicitud(
id_soli int,
id_usu int,
foreign key (id_soli) references solicitud(id_soli),
foreign key (id_usu) references usuario(id_usu) ON DELETE cascade
);

create table envia_mensaje(
id_msg int,
id_usu int,
foreign key (id_msg) references mensaje(id_msg),
foreign key (id_usu) references usuario(id_usu) ON DELETE cascade
);

create table recibe_mensaje(
id_msg int,
id_usu int,
foreign key (id_msg) references mensaje(id_msg),
foreign key (id_usu) references usuario(id_usu) ON DELETE cascade
);

create table acepta_trabajo(
id_trab int,
id_usu int,
foreign key (id_trab) references trabajo(id_trab),
foreign key (id_usu) references usuario(id_usu) ON DELETE cascade
);

create table provee_trabajo(
id_trab int,
id_usu int,
foreign key (id_trab) references trabajo(id_trab),
foreign key (id_usu) references usuario(id_usu) ON DELETE cascade
);

create table relacion_usuario_trabajo(
id_usu int,
id_tipo_trab int,
foreign key (id_tipo_trab) references tipo_trabajo(id_tipo_trab),
foreign key (id_usu) references usuario(id_usu) ON DELETE cascade
);

create table relacion_usuario_tipoUsuario(
id_usu int auto_increment,
id_tipo_usu int,
foreign key (id_tipo_usu) references tipo_usuario(id_tipo_usu),
foreign key (id_usu) references usuario(id_usu) ON DELETE cascade
);

create table relacion_usuario_region(
id_usu int,
id_region int,
foreign key (id_region) references region(id_region),
foreign key (id_usu) references usuario(id_usu) ON DELETE cascade
);

create table relacion_trabajo_estado(
id_trab int,
id_est_trab int,
foreign key (id_est_trab) references estado_trabajo(id_est_trab),
foreign key (id_trab) references trabajo(id_trab)
);

create table relacion_solicitud_estado(
id_soli int,
id_est_soli int,
foreign key (id_est_soli) references estado_soli(id_est_soli),
foreign key (id_soli) references solicitud(id_soli)
);


-- DATOS PREVIOS NECESARIOS

insert into tipo_usuario values
(1, "Administrador"),
(2, "Usuario"),
(3, "Trabajador"),
(4, "Empresa");

insert into estado_trabajo values
(1, "Pendiente"),
(2, "En progreso"),
(3, "Finalizado");

insert into estado_soli values
(1, "Pendiente"),
(2, "Aceptada"),
(3, "Rechazada");


-- VISUALIZAR LAS TABLAS

select * from usuario;
select * from trabajo;
select * from mensaje;
select * from solicitud;
select * from tipo_trabajo;
select * from region;
select * from estado_trabajo;
select * from estado_soli;
select * from tipo_usuario;
select * from acepta_trabajo;
select * from provee_trabajo;
select * from envia_mensaje;
select * from recibe_mensaje;
select * from envia_solicitud;
select * from recibe_solicitud;
select * from relacion_usuario_tipoUsuario;
select * from relacion_usuario_trabajo;
select * from relacion_usuario_region;



-- PROCEDIMIENTOS ALMACENADOS
drop procedure if exists desplegarUsuarios;
delimiter $$

create procedure desplegarUsuarios()
begin

select
usuario.nombre_usu,
usuario.apellido_usu,
usuario.profile_usu,
usuario.banner_usu,
tipo_trabajo.nombre_tipo_trab,
region.nombre_region
from relacion_usuario_tipoUsuario
inner join usuario on usuario.id_usu = relacion_usuario_tipoUsuario.id_usu
inner join relacion_usuario_trabajo on usuario.id_usu = relacion_usuario_trabajo.id_usu
inner join tipo_trabajo on relacion_usuario_trabajo.id_tipo_trab = tipo_trabajo.id_tipo_trab
inner join relacion_usuario_region on usuario.id_usu = relacion_usuario_region.id_usu
inner join region on relacion_usuario_region.id_region = region.id_region
where id_tipo_usu = 2;

end $$
delimiter ;
call desplegarUsuarios();






drop procedure if exists registrarUsuario;
delimiter $$

create procedure registrarUsuario(Nombre_usu nvarchar(20),
Apellido_usu nvarchar(20),
Correo_usu nvarchar(60),
Contra_usu nvarchar(200),
tipo_usu int,
telefono_usu nvarchar(11))
begin

insert into usuario (nombre_usu, apellido_usu, correo_usu, contrasena_usu, telefono_usu) values
(Nombre_usu, Apellido_usu, Correo_usu, Contra_usu, telefono_usu);
insert into relacion_usuario_tipoUsuario values 
(default, tipo_usu);

end $$
delimiter ;






drop procedure if exists complementarRegistro;
delimiter $$

create procedure complementarRegistro(tipo_trab int,
descripcion nvarchar(2000),
perfil longblob,
portada longblob,
region int,
idUsuario int)

begin

UPDATE usuario
SET profile_usu = perfil, banner_usu = portada, desc_usu = descripcion
WHERE id_usu = idUsuario;

INSERT INTO relacion_usuario_trabajo values
(idUsuario, tipo_trab);

INSERT INTO relacion_usuario_region values
(idUsuario, region);

end $$
delimiter ;








drop procedure if exists editarPerfilComp;
delimiter $$

create procedure editarPerfilComp(
nombren nvarchar(20),
apellidon nvarchar(20),
contrasenan nvarchar(20),
perfiln longblob,
portadan longblob,
descripcionn nvarchar(2000),
telefonon nvarchar(11),
idUsuario int,
regionn int
)

begin
UPDATE usuario
SET nombre_usu = nombren,
apellido_usu = apellidon,
contrasena_usu = contrasenan,
telefono_usu = telefonon,
desc_usu = descripcionn,
profile_usu = perfiln,
banner_usu = portadan
WHERE id_usu = idUsuario;

UPDATE relacion_usuario_region
SET id_region = regionn
WHERE id_usu = idUsuario;

end $$
delimiter ;






drop procedure if exists eliminarPerfil;
delimiter $$
create procedure eliminarPerfil(
idUsuario int
)
begin
DELETE FROM usuario
where id_usu = idUsuario;
end $$
delimiter ;

-- Se editó base de datos