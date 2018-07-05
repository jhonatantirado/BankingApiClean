CREATE TABLE trans_detalle (
	id_trans BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,	
	numb_origen VARCHAR (60)  NOT NULL,
	numb_destino VARCHAR (60)  NOT NULL,
	balance DECIMAL(10,2) NULL,	
	islocked BOOLEAN NULL,
	bank_account_id BIGINT UNSIGNED NOT NULL,
	customer_id BIGINT UNSIGNED NOT NULL,
	PRIMARY KEY (id_trans),
	FOREIGN KEY (bank_account_id) REFERENCES bank_account (bank_account_id),
	FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;