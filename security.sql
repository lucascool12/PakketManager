drop table groepen;
drop table gebruikers;

create table gebruikers (
gebruikersnaam varchar(20) primary key,
paswoord varchar(20) );

create table groepen (
gebruikersnaam varchar(20) references gebruikers primary key,
groep varchar(20) );

insert into gebruikers values ('10000', '10000');
insert into gebruikers values ('10001', '10001');
insert into gebruikers values ('20000', '20000');
insert into gebruikers values ('20001', '20001');

insert into groepen values ('10000', 'bediendes');
insert into groepen values ('10001', 'bediendes');
insert into groepen values ('20000', 'koeriers');
insert into groepen values ('20001', 'koeriers');