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

CREATE TABLE Koeriers(
    knr int primary key,
    knaam varchar(30),
    paswoord varchar(30));

CREATE TABLE Bediendes(
    bnr int primary key,
    bnaam varchar(30),
    paswoord varchar(30));
    

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
    knr int references Koeriers);