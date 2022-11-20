CREATE DATABASE Cursos;

CREATE TABLE cursos (
	id serial PRIMARY KEY, 
	titulo varchar(100), 
	fechaIni Date);
CREATE TABLE alumnos (
	id int PRIMARY KEY, 
	nombre varchar(100), 
	codCurso int, 
	FOREIGN KEY(codCurso) REFERENCES cursos(id));
