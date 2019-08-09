
use backend_productos;

CREATE TABLE tbl_producto (
  codigo_prod bigint NOT NULL AUTO_INCREMENT,
  nombre_prod varchar(20),
  precio_prod int,
  PRIMARY KEY (codigo_prod)
);

