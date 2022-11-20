--CREATE DATABASE jokes;

DROP TABLE IF EXISTS joke_flags;
DROP TABLE IF EXISTS flags;
DROP TABLE IF EXISTS joke;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS language;
DROP TABLE IF EXISTS types;

CREATE TABLE language(
	id int PRIMARY KEY, 
	name varchar(5)NOT NULL);
	
CREATE TABLE types(
	id int PRIMARY KEY, 
	type varchar(20) NOT NULL);
	
CREATE TABLE category(
	id int PRIMARY KEY, 
	name varchar(50) NOT NULL);
	
CREATE TABLE flags(
	id int PRIMARY KEY, 
	name varchar(20) NOT NULL);
	
CREATE TABLE joke(
	id int NOT NULL, 
	text varchar(1000), 
	text2 varchar(1000), 
	id_language int NOT NULL, 
	id_type int NOT NULL, 
	id_category int NOT NULL, 
	PRIMARY KEY (id, id_language), 
	FOREIGN KEY (id_language) REFERENCES language(id),
	FOREIGN KEY (id_type) REFERENCES  types(id),
	FOREIGN KEY (id_category) REFERENCES  category(id)
);

CREATE TABLE joke_flags (
	id_jokes int NOT NULL, 
	id_language int NOT NULL,
	id_flags int NOT NULL,
	PRIMARY KEY ( id_jokes, id_language, id_flags),
	FOREIGN KEY (id_jokes, id_language) REFERENCES joke(id, id_language),
	FOREIGN KEY (id_flags) REFERENCES flags(id)
);

--la función obtener_flags es necesaria para el método buscarTexto() de java.

CREATE OR REPLACE FUNCTION obtener_flags()
RETURNS table(name varchar(20)) AS
$BODY$
DECLARE
reg RECORD;
BEGIN
FOR REG  in SELECT flags.name FROM FLAGS LOOP

	name := reg.name;
	
	RETURN NEXT;
	END LOOP;
RETURN;
END
$BODY$ LANGUAGE 'plpgsql'

CREATE OR REPLACE FUNCTION buscar_texto(IN palabra varchar)
RETURNS table(id int, id_language int, idioma varchar(3), categoria varchar(10), tipo varchar(10), flags int,text varchar(1000), text2 varchar(1000)) as
$BODY$
DECLARE
reg RECORD;
BEGIN
FOR REG  in 
Select sub.id, sub.id_language, language.name as idioma, category.name as categoria,types.type as tipo, sub.id_flags as flags , sub.text, sub.text2 
from (select joke.id, joke.id_language, joke.id_category, joke.id_type, joke_flags.id_flags, joke.text, joke.text2
from joke
left outer join joke_flags 
on joke.id_language = joke_flags.id_language and joke.id = joke_flags.id_jokes
WHERE (lower(joke.text) like lower($1)or  LOWER(joke.text2) like LOWER($1))
order by id_language, id ) as sub, language, category, types
WHERE language.id = sub.id_language and category.id = sub.id_category and types.id = sub.id_type 
 order by sub.id_language, sub.id loop
	id := reg.id;
	id_language := reg.id_language;
	idioma := reg.idioma;
	categoria := reg.categoria;
	tipo := reg.tipo;
	flags := reg.flags;
	text := reg.text;
	text2 := reg.text2;
	RETURN NEXT;
	END LOOP;
RETURN;
END
$BODY$ LANGUAGE 'plpgsql'





CREATE OR REPLACE FUNCTION buscar_chiste_sin_flag()
RETURNS table(id int, id_language int, idioma varchar(3), categoria varchar(10), tipo varchar(10),text varchar(1000), text2 varchar(1000)) as $$

Select sub.id, sub.id_language, language.name as idioma, category.name as categoria,types.type as tipo, sub.text as texto1, sub.text2 as texto2 
from (select joke.id, joke.id_language, joke.id_category, joke.id_type, joke_flags.id_flags, joke.text, joke.text2
from joke
left outer join joke_flags 
on joke.id_language = joke_flags.id_language and joke.id = joke_flags.id_jokes	
order by id_language, id ) as sub, language, category, types 
where sub.id_flags is null and language.id = sub.id_language and category.id = sub.id_category and types.id = sub.id_type order by sub.id_language, sub.id;
$$ LANGUAGE sql