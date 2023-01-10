drop table if exists peliculas;
drop table if exists tematica;

create table tematica(
	id serial primary key,
	tema varchar(100)
);
create table peliculas(
	id serial primary key,
	nombre varchar(250),
	puntuacion integer,
	duracion integer,
	codtematica integer	references tematica(id)
);

insert into tematica (tema) values
	('terror'),
	('suspense'),
	('acción'),
	('sci-fi'),
	('anime'),
	('comedia');
	
insert into peliculas ( nombre, puntuacion, duracion,codtematica) values
	('peli1',9,90,1),
	('peli2',4,60,4),
	('peli3',6,85,3),
	('peli4',7,95,2);

