CREATE TABLE categorias
(cat_id          BIGINT AUTO_INCREMENT PRIMARY KEY,
 cat_nombre      VARCHAR(100) NOT NULL UNIQUE,
 cat_estado      VARCHAR(20)  NOT NULL,
cat_descripcion VARCHAR(500)
);
CREATE TABLE productos
(
prod_id          BIGINT AUTO_INCREMENT PRIMARY KEY,
prod_nombre      VARCHAR(100) NOT NULL,
stock            INT          NOT NULL,
prod_estado      VARCHAR(20)  NOT NULL,
prod_descripcion VARCHAR(500),
categoria_id     BIGINT,
CONSTRAINT fk_producto_categoria
FOREIGN KEY (categoria_id) REFERENCES categorias (cat_id)
);
CREATE TABLE usuarios
(
    id_usuario      VARCHAR(8) PRIMARY KEY,
    usu_nombre      VARCHAR(100)        NOT NULL,
    usu_correo      VARCHAR(100) UNIQUE NOT NULL,
    usu_contrasena  VARCHAR(100)        NOT NULL,
    usu_rol         VARCHAR(30)         NOT NULL,
    usu_estado      VARCHAR(20)         NOT NULL
);
CREATE TABLE proveedores
(
    id_proveedor     VARCHAR(11) PRIMARY KEY,
    prov_nombre      VARCHAR(100) NOT NULL,
    prov_direccion   VARCHAR(200),
    prov_estado      VARCHAR(20)  NOT NULL,
    prov_descripcion VARCHAR(500),
    pro_rubro        VARCHAR(100)
);
CREATE TABLE destinos
(
    id_destino      VARCHAR(20) PRIMARY KEY,
    des_nombre      VARCHAR(100) NOT NULL,
    des_direccion   VARCHAR(200),
    des_contacto    VARCHAR(100),
    des_estado      VARCHAR(20)  NOT NULL,
    des_descripcion VARCHAR(500)
);
CREATE TABLE transacciones
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo           VARCHAR(20) NOT NULL,
    fecha          DATE        NOT NULL,
    hora           TIME        NOT NULL,
    proveedor_id   VARCHAR(11),
    destino_id     VARCHAR(20),
    responsable_id VARCHAR(8),
    asignado_id    VARCHAR(8),
    CONSTRAINT fk_trans_proveedor
        FOREIGN KEY (proveedor_id)
            REFERENCES proveedores (id_proveedor),

    CONSTRAINT fk_trans_destino
        FOREIGN KEY (destino_id)
            REFERENCES destinos (id_destino),

    CONSTRAINT fk_trans_responsable
        FOREIGN KEY (responsable_id)
            REFERENCES usuarios (id_usuario),

    CONSTRAINT fk_trans_asignado
        FOREIGN KEY (asignado_id)
            REFERENCES usuarios (id_usuario)
);
CREATE TABLE detalle_transaccion
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    cantidad         INT    NOT NULL,
    stock_anterior   INT    NOT NULL,
    stock_resultante INT    NOT NULL,
    producto_id      BIGINT NOT NULL,
    transaccion_id   BIGINT NOT NULL,
    CONSTRAINT fk_detalle_producto
        FOREIGN KEY (producto_id)
            REFERENCES productos (prod_id),

    CONSTRAINT fk_detalle_transaccion
        FOREIGN KEY (transaccion_id)
            REFERENCES transacciones (id)
);