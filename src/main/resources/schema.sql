CREATE TABLE cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    email VARCHAR(100)
);

CREATE TABLE producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    precio DOUBLE,
    stock INT
);

CREATE TABLE comprobante (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT,
    fecha TIMESTAMP,
    total DOUBLE,
    total_productos INT,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE linea_comprobante (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    producto_id BIGINT,
    comprobante_id BIGINT,
    cantidad INT,
    precio_unitario DOUBLE,
    FOREIGN KEY (producto_id) REFERENCES producto(id),
    FOREIGN KEY (comprobante_id) REFERENCES comprobante(id)
);