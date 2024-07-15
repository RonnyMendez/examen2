drop database if exists examen1;
create database examen1;
use examen1;

INSERT INTO Usuarios (usuario, password, email, rol, nombre)
VALUES ('usuario1', 'password1', 'usuario1@example.com', 'admin', 'Nombre Usuario 1'),
       ('usuario2', 'password2', 'usuario2@example.com', 'user', 'Nombre Usuario 2'),
       ('usuario3', 'password3', 'usuario3@example.com', 'user', 'Nombre Usuario 3'),
       ('usuario4', 'password4', 'usuario4@example.com', 'admin', 'Nombre Usuario 4'),
       ('usuario5', 'password5', 'usuario5@example.com', 'user', 'Nombre Usuario 5');


INSERT INTO clientes (DNI, nombre, direccion, telefono)
VALUES ('12345678', 'Juan Perez', 'Av. Siempre Viva 123', '999888777'),
       ('87654321', 'Maria Rodriguez', 'Calle Falsa 456', '888777666'),
       ('45678912', 'Carlos Lopez', 'Jr. Pino 789', '777666555'),
       ('65432178', 'Ana Morales', 'Av. Arbol 321', '666555444'),
       ('78912345', 'Luis Sanchez', 'Calle Luna 987', '555444333');

INSERT INTO garajes (nombre, direccion)
VALUES ('Almacen Santa', 'Av. Santa 1152'),
       ('Almacen America', 'Av. America Norte 1257'),
       ('Almacen California', 'Los Granados 563');

INSERT INTO automoviles (matricula, modelo, color, marca, estado, galones, garaje_id)
VALUES ('ABC123', 'Modelo X', 'Rojo', 'Marca A', 'DISPONIBLE', 10, 1),
       ('DEF456', 'Modelo Y', 'Azul', 'Marca B', 'DISPONIBLE', 15, 1),
       ('GHI789', 'Modelo Z', 'Verde', 'Marca C', 'RESERVADO', 5, 2),
       ('JKL012', 'Modelo W', 'Blanco', 'Marca D', 'DISPONIBLE', 20, 3),
       ('MNO345', 'Modelo V', 'Negro', 'Marca E', 'RESERVADO', 8, 2);


select * from automoviles;
select * from clientes;
