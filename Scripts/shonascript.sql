

CREATE TABLE users ( 
	id                   serial  NOT NULL,
	name                 varchar(200)  ,
	isadmin              integer DEFAULT 0 ,
	passwo               varchar  ,
	user_name            varchar(100)  ,
	createdbyid          integer  ,
	createdat            date  ,
	CONSTRAINT pk_users PRIMARY KEY ( id )
 );

CREATE TABLE cars ( 
	id                   serial  NOT NULL,
	tk3ib                numeric  ,
	is_ours              integer DEFAULT 0 ,
	owner_name           varchar(200)  ,
	createdbyid          integer  ,
	createdat            date  ,
	CONSTRAINT pk_grarat_0 PRIMARY KEY ( id ),
	CONSTRAINT fk_cars FOREIGN KEY ( createdbyid ) REFERENCES users( id )    
 );

CREATE INDEX idx_grarat_0 ON cars ( is_ours );

CREATE INDEX idx_cars ON cars ( createdbyid );

CREATE TABLE client ( 
	id                   serial  NOT NULL,
	name                 varchar(200)  ,
	total_paid           numeric  ,
	total_rest           numeric  ,
	createdbyid          integer  ,
	createdat            date  ,
	CONSTRAINT pk_m7ger_owners_0 PRIMARY KEY ( id ),
	CONSTRAINT fk_client FOREIGN KEY ( createdbyid ) REFERENCES users( id )    
 );

CREATE INDEX idx_client ON client ( createdbyid );

CREATE TABLE m7ger ( 
	id                   serial  NOT NULL,
	name                 varchar(200)  ,
	total_paid           numeric  ,
	total_rest           numeric  ,
	m7gr_owner           varchar  ,
	createdbyid          integer  ,
	createdat            date  ,
	CONSTRAINT pk_m7ger_owners PRIMARY KEY ( id ),
	CONSTRAINT fk_m7ger FOREIGN KEY ( createdbyid ) REFERENCES users( id )    
 );

CREATE INDEX idx_m7ger ON m7ger ( createdbyid );

CREATE TABLE zalat ( 
	id                   serial  NOT NULL,
	name                 varchar(200)  ,
	price_per_meter_buy  numeric  ,
	m7gr_id              integer  ,
	price_per_meter_sell numeric  ,
	createdbyid          integer  ,
	createdat            date  ,
	CONSTRAINT pk_zalat PRIMARY KEY ( id ),
	CONSTRAINT fk_zalat FOREIGN KEY ( m7gr_id ) REFERENCES m7ger( id )    ,
	CONSTRAINT fk_zalat_0 FOREIGN KEY ( createdbyid ) REFERENCES users( id )    
 );

CREATE INDEX idx_zalat ON zalat ( m7gr_id );

CREATE INDEX idx_zalat_0 ON zalat ( createdbyid );

CREATE TABLE grarat ( 
	id                   serial  NOT NULL,
	tk3ib                numeric  ,
	m7gr_id              integer  ,
	owner_name           varchar(200)  ,
	createdbyid          integer  ,
	createdat            date  ,
	CONSTRAINT pk_grarat PRIMARY KEY ( id ),
	CONSTRAINT fk_grarat FOREIGN KEY ( m7gr_id ) REFERENCES m7ger( id )    ,
	CONSTRAINT fk_grarat_0 FOREIGN KEY ( createdbyid ) REFERENCES users( id )    
 );

CREATE INDEX idx_grarat ON grarat ( m7gr_id );

CREATE INDEX idx_grarat_1 ON grarat ( createdbyid );

CREATE TABLE sader_transaction ( 
	id                   serial  NOT NULL,
	client_id            integer  ,
	car_id               integer  ,
	tk3ib                numeric  ,
	price_per_meter      numeric  ,
	total_price          numeric  ,
	paid                 numeric  ,
	rest                 numeric  ,
	zalat_id             integer  ,
	car_price            numeric  ,
	isal_number          integer  NOT NULL,
	createdbyid          integer  ,
	createdat            date  ,
	CONSTRAINT pk_twrid_transaction_0 PRIMARY KEY ( id ),
	CONSTRAINT fk_sader_transaction FOREIGN KEY ( client_id ) REFERENCES client( id )    ,
	CONSTRAINT fk_sader_transaction_0 FOREIGN KEY ( car_id ) REFERENCES cars( id )    ,
	CONSTRAINT fk_sader_transaction_1 FOREIGN KEY ( zalat_id ) REFERENCES zalat( id )    ,
	CONSTRAINT fk_sader_transaction_2 FOREIGN KEY ( createdbyid ) REFERENCES users( id )    
 );

CREATE INDEX idx_twrid_transaction_2 ON sader_transaction ( client_id );

CREATE INDEX idx_twrid_transaction_3 ON sader_transaction ( car_id );

CREATE INDEX idx_twrid_transaction_4 ON sader_transaction ( zalat_id );

CREATE INDEX idx_sader_transaction ON sader_transaction ( createdbyid );

CREATE TABLE twrid_transaction ( 
	id                   serial  NOT NULL,
	m7gr_id              integer  ,
	grar_id              integer  ,
	tk3ib                numeric  ,
	price_per_meter      numeric  ,
	total_price          numeric  ,
	paid                 numeric  ,
	rest                 numeric  ,
	zalat_id             integer  ,
	garar_price          numeric  ,
	isal_number          integer  NOT NULL,
	createdbyid          integer  ,
	createdat            date  ,
	CONSTRAINT pk_twrid_transaction PRIMARY KEY ( id ),
	CONSTRAINT fk_twrid_transaction FOREIGN KEY ( m7gr_id ) REFERENCES m7ger( id )    ,
	CONSTRAINT fk_twrid_transaction_0 FOREIGN KEY ( grar_id ) REFERENCES grarat( id )    ,
	CONSTRAINT fk_twrid_transaction_1 FOREIGN KEY ( zalat_id ) REFERENCES zalat( id )    ,
	CONSTRAINT fk_twrid_transaction_2 FOREIGN KEY ( createdbyid ) REFERENCES users( id )    
 );

CREATE INDEX idx_twrid_transaction ON twrid_transaction ( m7gr_id );

CREATE INDEX idx_twrid_transaction_0 ON twrid_transaction ( grar_id );

CREATE INDEX idx_twrid_transaction_1 ON twrid_transaction ( zalat_id );

CREATE INDEX idx_twrid_transaction_5 ON twrid_transaction ( createdbyid );


