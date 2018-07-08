CREATE TABLE trans_detalle (
	id_trans BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,	
	numb_origen VARCHAR (50)  NOT NULL,
	numb_destino VARCHAR (50)  NOT NULL,
	monto DECIMAL(10,2) NOT NULL,	
	fecha DATETIME NULL,
	customer_id BIGINT UNSIGNED NOT NULL,
	PRIMARY KEY (id_trans),
	FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;