/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  lucas
 * Created: 17-nov-2022
 */

DROP TABLE Pakketen;
DROP TABLE Koeriers;

drop table groepen;
drop table gebruikers;

create table gebruikers (
    gebruikersnaam varchar(20) primary key,
    paswoord varchar(20) );

create table groepen (
    gebruikersnaam varchar(20) references gebruikers primary key,
    groep varchar(20) );

CREATE TABLE Koeriers(
    knr int primary key,
    knaam varchar(20) references gebruikers);

CREATE TABLE Pakketen(
    pnr int primary key,
    pgewicht int,
    pstatus int,
    lnaam varchar(30),
    lstraat varchar(30),
    lnummer int,
    lpostcode int,
    lgemeente varchar(30),
    besteldatum date,
    pcommentaar varchar(80),
    knr int references Koeriers);

insert into gebruikers values ('10000', '10000');
insert into gebruikers values ('10001', '10001');
insert into gebruikers values ('20000', '20000');
insert into gebruikers values ('20001', '20001');

insert into groepen values ('10000', 'bediendes');
insert into groepen values ('10001', 'bediendes');
insert into groepen values ('20000', 'koeriers');
insert into groepen values ('20001', 'koeriers');

insert into koeriers values (0, '20000');
insert into koeriers values (1, '20001');