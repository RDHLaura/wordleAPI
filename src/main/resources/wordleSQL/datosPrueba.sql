use mydb;

insert into `Equipo` (nombre, puntos, logo)
values 
('Equipo1', 0, 'url_logo_equipo'),
('Equipo2', 0, 'url_logo_equipo'),
('Equipo3', 0, 'url_logo_equipo');

insert into `Juego` (dificultad, nombre, instrucciones, intentosMax)
values 
('FACIL', 'Juego1', 'instrucciones 1', 5),
('NORMAL', 'Juego2', 'instrucciones 2', 4),
('DIFICIL', 'Juego3', 'instrucciones 3', 3);

insert into `Jugador` (admin, nombre, clave, avatar, puntos, Equipo_idEquipo)
values 
(0, 'Jugador1', 'clave 1', '', 5, null),
(1, 'Jugador2', 'clave 2', '', 4, null),
(0, 'Jugador3', 'clave 3', '', 3, null);