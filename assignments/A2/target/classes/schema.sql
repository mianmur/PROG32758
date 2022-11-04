CREATE TABLE regists
(
    id   LONG PRIMARY KEY AUTO_INCREMENT,
    fname varchar(50),
    lname varchar(50),
    role varchar(50),
    creationDate TIMESTAMP,
    lastModifiedDate TIMESTAMP
);