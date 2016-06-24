CREATE SEQUENCE SEQ_ADDRESS
 START WITH 1
 INCREMENT BY 1
 NOCACHE
 NOCYCLE;

CREATE TABLE ADDRESS
(
  ID NUMBER NOT NULL,
  STREET VARCHAR2(255) NOT NULL,
  CITY VARCHAR2(255) NOT NULL,
  STATE VARCHAR2(255) NOT NULL,
  ADDRESS_NUMBER VARCHAR2(10) NOT NULL,
  CEP NUMBER(5) DEFAULT '00000' NOT NULL,
  ADDRESS_DETAIL VARCHAR2(255), 
  NEIGHBORHOOD VARCHAR2(255),
  
  CONSTRAINT ADDRESS PRIMARY KEY (ID)
);