CREATE TABLE customer (
  customer_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  birthDate DATE NULL,
  documentNumber VARCHAR(50) NOT NULL,
  isactive BOOLEAN,
  cellphone VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  PRIMARY KEY (customer_id),
  INDEX IX_customer_last_first_name (last_name, first_name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;