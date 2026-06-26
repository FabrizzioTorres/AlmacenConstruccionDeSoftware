INSERT INTO usuarios
(id_usuario,usu_nombre,usu_correo,usu_contrasena,usu_rol,usu_estado)
VALUES ('72391910','Dueño del Almacén','sistemaalmacen8080@gmail.com',
        '1234','Dueño','Activo');

INSERT INTO categorias
(cat_nombre, cat_estado, cat_descripcion)
VALUES
    ('Celulares','Activo','Equipos móviles para comunicación y acceso a internet'),

    ('Laptops','Activo','Computadoras portátiles para trabajo y estudio'),

    ('Tablets','Activo','Dispositivos táctiles de tamaño intermedio'),

    ('Routers','Activo','Equipos para distribución de redes inalámbricas'),

    ('Modems','Activo','Equipos para conexión a servicios de internet'),

    ('Impresoras','Activo','Dispositivos para impresión de documentos'),

    ('Monitores','Activo','Pantallas para visualización de información'),

    ('Teclados','Activo','Periféricos de entrada para escritura'),

    ('Mouse','Activo','Dispositivos apuntadores para computadoras'),

    ('Accesorios','Activo','Complementos tecnológicos diversos');

INSERT INTO proveedores
(id_proveedor,prov_nombre,prov_direccion,prov_estado,prov_descripcion,pro_rubro)
VALUES
    ('20100000001','Movistar Empresas','Av. Javier Prado 1234, Lima','Activo',
     'Proveedor de servicios de telecomunicaciones','Telecomunicaciones'),

    ('20100000002','Claro Empresas','Av. Arequipa 4500, Lima','Activo',
     'Proveedor de telefonía e internet empresarial','Telecomunicaciones'),

    ('20100000003','Entel Perú','Av. El Derby 250, Lima','Activo',
     'Proveedor de soluciones móviles y corporativas','Telecomunicaciones'),

    ('20100000004','Bitel Empresas','Av. San Felipe 321, Lima','Activo',
     'Proveedor de conectividad empresarial','Telecomunicaciones'),

    ('20100000005','Samsung Perú','Av. República de Panamá 9999, Lima','Activo',
     'Distribuidor de equipos electrónicos','Electrónica'),

    ('20100000006','Xiaomi Perú','Av. La Marina 800, Lima','Activo',
     'Proveedor de smartphones y accesorios','Electrónica'),

    ('20100000007','Lenovo Perú','Av. Primavera 1450, Lima','Activo',
     'Proveedor de laptops y equipos empresariales','Computación'),

    ('20100000008','HP Perú','Av. Canadá 2200, Lima','Activo',
     'Proveedor de impresoras y computadoras','Computación'),

    ('20100000009','Dell Technologies Perú','Av. Benavides 1800, Lima','Activo',
     'Proveedor de soluciones informáticas','Computación'),

    ('20100000010','TP-Link Perú','Av. Brasil 700, Lima','Activo',
     'Proveedor de equipos de red','Networking'),

    ('20100000011','Cisco Perú','Av. Canaval y Moreyra 500, Lima','Activo',
     'Proveedor de infraestructura de redes','Networking'),

    ('20100000012','MikroTik Perú','Av. Universitaria 3000, Lima','Activo',
     'Proveedor de routers y switches','Networking'),

    ('20100000013','Logitech Perú','Av. Salaverry 1600, Lima','Activo',
     'Proveedor de periféricos','Accesorios'),

    ('20100000014','Kingston Perú','Av. Angamos 950, Lima','Activo',
     'Proveedor de memorias y almacenamiento','Hardware'),

    ('20100000015','Seagate Perú','Av. Colonial 2200, Lima','Activo',
     'Proveedor de discos duros','Hardware'),

    ('20100000016','Epson Perú','Av. Guardia Civil 900, Lima','Activo',
     'Proveedor de impresoras','Impresión'),

    ('20100000017','Brother Perú','Av. Petit Thouars 3000, Lima','Activo',
     'Proveedor de equipos de impresión','Impresión'),

    ('20100000018','Intel Perú','Av. Paseo de la República 1000, Lima','Activo',
     'Proveedor de procesadores','Hardware'),

    ('20100000019','AMD Perú','Av. Pershing 1200, Lima','Activo',
     'Proveedor de procesadores y gráficos','Hardware'),

    ('20100000020','Nvidia Perú','Av. Ejército 450, Lima','Activo',
     'Proveedor de tarjetas gráficas','Hardware');

INSERT INTO destinos
(id_destino,des_nombre,des_direccion,des_contacto,des_estado,des_descripcion)
VALUES

    ('20500000001','Universidad Continental','Av. San Carlos 1980, Huancayo',
     'Logística UC','Activo','Institución universitaria privada'),

    ('20500000002','Universidad Nacional del Centro del Perú',
     'Av. Mariscal Castilla 3909, Huancayo',
     'Oficina de Patrimonio','Activo','Universidad pública'),

    ('20500000003',
     'Hospital Regional Daniel Alcides Carrion','Av. Daniel Alcides Carrion 1551, Huancayo',
     'Área de Logística','Activo','Hospital regional'),

    ('20500000004','Hospital El Carmen','Jr. Puno 911, Huancayo',
     'Abastecimiento','Activo','Centro hospitalario'),

    ('20500000005','Municipalidad Provincial de Huancayo',
     'Jr. Girón Ancash 799','Patrimonio','Activo','Entidad pública'),

    ('20500000006','Real Plaza Huancayo','Av. Ferrocarril 1035',
     'Administración','Activo','Centro comercial'),

    ('20500000007','Colegio Santa Isabel','Av. Giraldez 500','Dirección',
     'Activo','Institución educativa'),

    ('20500000008','Colegio María Inmaculada','Jr. Puno 250',
     'Administración','Activo','Institución educativa'),

    ('20500000009','SENATI Huancayo','Av. Evitamiento 2500','Logística',
     'Activo','Instituto tecnológico'),

    ('20500000010','TECSUP Huancayo','Av. Circunvalación 1200','Patrimonio',
     'Activo','Instituto superior'),

    ('20500000011','Caja Huancayo','Jr. Real 500','Tecnologías de Información',
     'Activo','Entidad financiera'),

    ('20500000012','Banco de la Nación Huancayo','Jr. Real 650','Administración',
     'Activo','Entidad financiera pública'),

    ('20500000013','SUNAT Huancayo','Av. Giraldez 800','Informática',
     'Activo','Entidad tributaria'),

    ('20500000014','Electrocentro','Jr. Ancash 450','Logística',
     'Activo','Empresa eléctrica'),

    ('20500000015','Sedam Huancayo','Av. Ferrocarril 2200','Abastecimiento',
     'Activo','Empresa de saneamiento'),

    ('20500000016','Gobierno Regional de Junín','Jr. Loreto 363',
     'Patrimonio','Activo','Entidad regional'),

    ('20500000017','Dirección Regional de Salud Junín','Jr. Real 1500',
     'Logística','Activo','Entidad de salud'),

    ('20500000018','Cámara de Comercio de Huancayo','Jr. Real 900',
     'Administración','Activo','Organización empresarial'),

    ('20500000019','Centro Internacional de Negocios','Av. Giraldez 400',
     'Gerencia','Activo','Centro empresarial'),

    ('20500000020','Parque Industrial de Huancayo','Av. Evitamiento Sur',
     'Logística','Activo','Complejo industrial');

INSERT INTO usuarios
(id_usuario, usu_nombre, usu_correo, usu_contrasena, usu_rol, usu_estado)
VALUES

    ('72391911','Carlos Mendoza','cmendoza@multipro.com','1234','Dueño','Activo'),

    ('72391912','María Torres','mtorres@multipro.com','1234','Administrador','Activo'),
    ('72391913','Luis Gamarra','lgamarra@multipro.com','1234','Administrador','Activo'),
    ('72391914','Rosa Paredes','rparedes@multipro.com','1234','Administrador','Activo'),
    ('72391915','Juan Salazar','jsalazar@multipro.com','1234','Administrador','Activo'),
    ('72391916','Patricia Rojas','projas@multipro.com','1234','Administrador','Activo'),

    ('72391917','Pedro Castillo','pcastillo@multipro.com','1234','Asistente','Activo'),
    ('72391918','Diego Flores','dflores@multipro.com','1234','Asistente','Activo'),
    ('72391919','Ana Huamán','ahuaman@multipro.com','1234','Asistente','Activo'),
    ('72391920','José Quispe','jquispe@multipro.com','1234','Asistente','Activo'),
    ('72391921','Miguel Chávez','mchavez@multipro.com','1234','Asistente','Activo'),
    ('72391922','Lucía Ramos','lramos@multipro.com','1234','Asistente','Activo'),
    ('72391923','Kevin León','kleon@multipro.com','1234','Asistente','Activo'),
    ('72391924','Brenda Soto','bsoto@multipro.com','1234','Asistente','Activo'),
    ('72391925','Fernando Díaz','fdiaz@multipro.com','1234','Asistente','Activo'),
    ('72391926','Andrea Peña','apena@multipro.com','1234','Asistente','Activo'),
    ('72391927','Ricardo Silva','rsilva@multipro.com','1234','Asistente','Activo'),
    ('72391928','Valeria Núñez','vnunez@multipro.com','1234','Asistente','Activo'),
    ('72391929','Jorge Campos','jcampos@multipro.com','1234','Asistente','Activo');

INSERT INTO productos
(prod_nombre,stock, prod_estado, prod_descripcion, categoria_id)
VALUES

    ('Samsung Galaxy A35',0,'Activo','Smartphone Samsung gama media',1),
    ('Samsung Galaxy S24',0,'Activo','Smartphone Samsung gama alta',1),
    ('Xiaomi Redmi Note 13',0,'Activo','Smartphone Xiaomi',1),
    ('Xiaomi Poco X6',0,'Activo','Smartphone Poco',1),
    ('Motorola G84',0,'Activo','Smartphone Motorola',1),

    ('Lenovo ThinkPad E14',0,'Activo','Laptop empresarial Lenovo',2),
    ('HP ProBook 450',0,'Activo','Laptop empresarial HP',2),
    ('Dell Inspiron 15',0,'Activo','Laptop Dell',2),
    ('Asus Vivobook 15',0,'Activo','Laptop Asus',2),
    ('Acer Aspire 5',0,'Activo','Laptop Acer',2),

    ('Samsung Galaxy Tab S9',0,'Activo','Tablet Samsung',3),
    ('Xiaomi Pad 6',0,'Activo','Tablet Xiaomi',3),
    ('Lenovo Tab P12',0,'Activo','Tablet Lenovo',3),

    ('TP-Link Archer C80',0,'Activo','Router inalámbrico',4),
    ('TP-Link Archer AX23',0,'Activo','Router WiFi 6',4),
    ('Mikrotik RB750',0,'Activo','Router empresarial',4),
    ('Cisco RV340',0,'Activo','Router corporativo',4),

    ('Huawei HG8145',0,'Activo','Módem fibra óptica',5),
    ('ZTE F670L',0,'Activo','Módem GPON',5),
    ('Nokia G-240W',0,'Activo','Módem residencial',5),

    ('HP LaserJet M111w',0,'Activo','Impresora láser HP',6),
    ('Epson L3250',0,'Activo','Impresora multifuncional Epson',6),
    ('Brother DCP-T420W',0,'Activo','Impresora Brother',6),

    ('Samsung Odyssey G5',0,'Activo','Monitor gamer Samsung',7),
    ('LG UltraGear 24GN60R',0,'Activo','Monitor gamer LG',7),

    ('AOC 24B2XH',0,'Activo','Monitor Full HD',7),
    ('Dell P2422H',0,'Activo','Monitor empresarial Dell',7),

    ('Logitech K120',0,'Activo','Teclado USB',8),
    ('Redragon Kumara',0,'Activo','Teclado mecánico',8),
    ('HP 125 Wired',0,'Activo','Teclado HP',8),
    ('Microsoft Wired 600',0,'Activo','Teclado Microsoft',8),

    ('Logitech G203',0,'Activo','Mouse gamer Logitech',9),
    ('Logitech M170',0,'Activo','Mouse inalámbrico',9),
    ('HP X500',0,'Activo','Mouse HP',9),
    ('Redragon Cobra',0,'Activo','Mouse gamer Redragon',9),

    ('Memoria USB Kingston 64GB',0,'Activo','Unidad flash USB',10),
    ('Disco Externo Seagate 1TB',0,'Activo','Almacenamiento portátil',10),

    ('Samsung Galaxy A16',0,'Activo','Smartphone Samsung',1),
    ('Xiaomi Redmi 14C',0,'Activo','Smartphone Xiaomi',1),
    ('Honor X7B',0,'Activo','Smartphone Honor',1),

    ('Lenovo LOQ 15',0,'Activo','Laptop gamer Lenovo',2),
    ('HP Victus 15',0,'Activo','Laptop gamer HP',2),
    ('Dell G15',0,'Activo','Laptop gamer Dell',2),

    ('TP-Link Deco M4',0,'Activo','Sistema Mesh WiFi',4),
    ('Cisco Catalyst 1000',0,'Activo','Equipo de red empresarial',4),

    ('Epson EcoTank L5590',0,'Activo','Impresora empresarial',6),
    ('Brother HL-L2370DW',0,'Activo','Impresora láser Brother',6),

    ('LG 27MP400',0,'Activo','Monitor LG 27 pulgadas',7),

    ('Logitech MX Master 3S',0,'Activo','Mouse premium Logitech',9),

    ('SSD Kingston NV2 1TB',0,'Activo','Unidad SSD NVMe',10);

INSERT INTO transacciones
(tipo,fecha,hora,proveedor_id,responsable_id,asignado_id)
VALUES
    ('Ingreso','2026-01-05','08:30:00','20100000001','72391910','72391917'),
    ('Ingreso','2026-01-06','09:15:00','20100000002','72391911','72391918'),
    ('Ingreso','2026-01-07','10:00:00','20100000003','72391912','72391919'),
    ('Ingreso','2026-01-08','10:45:00','20100000004','72391913','72391920'),
    ('Ingreso','2026-01-09','11:20:00','20100000005','72391914','72391921'),

    ('Ingreso','2026-01-10','12:00:00','20100000006','72391915','72391922'),
    ('Ingreso','2026-01-11','12:45:00','20100000007','72391916','72391923'),
    ('Ingreso','2026-01-12','13:10:00','20100000008','72391910','72391924'),
    ('Ingreso','2026-01-13','13:50:00','20100000009','72391911','72391925'),
    ('Ingreso','2026-01-14','14:30:00','20100000010','72391912','72391926'),

    ('Ingreso','2026-01-15','15:15:00','20100000011','72391913','72391927'),
    ('Ingreso','2026-01-16','16:00:00','20100000012','72391914','72391928'),
    ('Ingreso','2026-01-17','16:40:00','20100000013','72391915','72391929'),
    ('Ingreso','2026-01-18','17:20:00','20100000014','72391916','72391917'),
    ('Ingreso','2026-01-19','18:00:00','20100000015','72391910','72391918'),

    ('Ingreso','2026-02-01','08:20:00','20100000016','72391911','72391919'),
    ('Ingreso','2026-02-03','09:10:00','20100000017','72391912','72391920'),
    ('Ingreso','2026-02-05','10:30:00','20100000018','72391913','72391921'),
    ('Ingreso','2026-02-07','11:40:00','20100000019','72391914','72391922'),
    ('Ingreso','2026-02-09','12:15:00','20100000020','72391915','72391923'),

    ('Ingreso','2026-02-11','13:00:00','20100000001','72391916','72391924'),
    ('Ingreso','2026-02-13','14:00:00','20100000002','72391910','72391925'),
    ('Ingreso','2026-02-15','15:00:00','20100000003','72391911','72391926'),
    ('Ingreso','2026-02-17','16:10:00','20100000004','72391912','72391927'),
    ('Ingreso','2026-02-19','17:30:00','20100000005','72391913','72391928'),

    ('Ingreso','2026-03-01','08:45:00','20100000006','72391914','72391929'),
    ('Ingreso','2026-03-03','10:00:00','20100000007','72391915','72391917'),
    ('Ingreso','2026-03-05','11:15:00','20100000008','72391916','72391918'),
    ('Ingreso','2026-03-07','13:20:00','20100000009','72391910','72391919'),
    ('Ingreso','2026-03-09','15:45:00','20100000010','72391911','72391920');

INSERT INTO detalle_transaccion
(cantidad, stock_anterior, stock_resultante, producto_id, transaccion_id)
VALUES
    (50,0,50,1,1),
    (30,0,30,2,2),
    (25,0,25,3,3),
    (40,0,40,4,4),
    (35,0,35,5,5),
    (60,0,60,6,6),
    (20,0,20,7,7),
    (45,0,45,8,8),
    (70,0,70,9,9),
    (55,0,55,10,10),
    (80,0,80,11,11),
    (65,0,65,12,12),
    (90,0,90,13,13),
    (100,0,100,14,14),
    (75,0,75,15,15),
    (50,0,50,16,16),
    (40,0,40,17,17),
    (60,0,60,18,18),
    (30,0,30,19,19),
    (45,0,45,20,20),
    (70,0,70,21,21),
    (50,0,50,22,22),
    (35,0,35,23,23),
    (40,0,40,24,24),
    (60,0,60,25,25),
    (80,0,80,26,26),
    (90,0,90,27,27),
    (55,0,55,28,28),
    (30,0,30,29,29),
    (45,0,45,30,30);

UPDATE productos SET stock = 50 WHERE prod_id = 1;
UPDATE productos SET stock = 30 WHERE prod_id = 2;
UPDATE productos SET stock = 25 WHERE prod_id = 3;
UPDATE productos SET stock = 40 WHERE prod_id = 4;
UPDATE productos SET stock = 35 WHERE prod_id = 5;
UPDATE productos SET stock = 60 WHERE prod_id = 6;
UPDATE productos SET stock = 20 WHERE prod_id = 7;
UPDATE productos SET stock = 45 WHERE prod_id = 8;
UPDATE productos SET stock = 70 WHERE prod_id = 9;
UPDATE productos SET stock = 55 WHERE prod_id = 10;

UPDATE productos SET stock = 80 WHERE prod_id = 11;
UPDATE productos SET stock = 65 WHERE prod_id = 12;
UPDATE productos SET stock = 90 WHERE prod_id = 13;
UPDATE productos SET stock = 100 WHERE prod_id = 14;
UPDATE productos SET stock = 75 WHERE prod_id = 15;
UPDATE productos SET stock = 50 WHERE prod_id = 16;
UPDATE productos SET stock = 40 WHERE prod_id = 17;
UPDATE productos SET stock = 60 WHERE prod_id = 18;
UPDATE productos SET stock = 30 WHERE prod_id = 19;
UPDATE productos SET stock = 45 WHERE prod_id = 20;

UPDATE productos SET stock = 70 WHERE prod_id = 21;
UPDATE productos SET stock = 50 WHERE prod_id = 22;
UPDATE productos SET stock = 35 WHERE prod_id = 23;
UPDATE productos SET stock = 40 WHERE prod_id = 24;
UPDATE productos SET stock = 60 WHERE prod_id = 25;
UPDATE productos SET stock = 80 WHERE prod_id = 26;
UPDATE productos SET stock = 90 WHERE prod_id = 27;
UPDATE productos SET stock = 55 WHERE prod_id = 28;
UPDATE productos SET stock = 30 WHERE prod_id = 29;
UPDATE productos SET stock = 45 WHERE prod_id = 30;

UPDATE productos SET stock = 0 WHERE prod_id BETWEEN 31 AND 50;

INSERT INTO transacciones
(tipo,fecha,hora,destino_id,responsable_id,asignado_id)
VALUES
    ('Salida','2026-03-10','08:30:00','20500000001','72391910','72391917'),
    ('Salida','2026-03-10','09:00:00','20500000002','72391911','72391918'),
    ('Salida','2026-03-10','09:30:00','20500000003','72391912','72391919'),
    ('Salida','2026-03-10','10:00:00','20500000004','72391913','72391920'),
    ('Salida','2026-03-10','10:30:00','20500000005','72391914','72391921'),

    ('Salida','2026-03-10','11:00:00','20500000006','72391915','72391922'),
    ('Salida','2026-03-10','11:30:00','20500000007','72391916','72391923'),
    ('Salida','2026-03-10','12:00:00','20500000008','72391910','72391924'),
    ('Salida','2026-03-10','12:30:00','20500000009','72391911','72391925'),
    ('Salida','2026-03-10','13:00:00','20500000010','72391912','72391926'),

    ('Salida','2026-03-10','13:30:00','20500000011','72391913','72391927'),
    ('Salida','2026-03-10','14:00:00','20500000012','72391914','72391928'),
    ('Salida','2026-03-10','14:30:00','20500000013','72391915','72391929'),
    ('Salida','2026-03-10','15:00:00','20500000014','72391916','72391917'),
    ('Salida','2026-03-10','15:30:00','20500000015','72391910','72391918'),

    ('Salida','2026-03-10','16:00:00','20500000016','72391911','72391919'),
    ('Salida','2026-03-10','16:20:00','20500000017','72391912','72391920'),
    ('Salida','2026-03-10','16:40:00','20500000018','72391913','72391921'),
    ('Salida','2026-03-10','17:00:00','20500000019','72391914','72391922'),
    ('Salida','2026-03-10','17:20:00','20500000020','72391915','72391923'),

    ('Salida','2026-03-10','17:40:00','20500000001','72391916','72391924'),
    ('Salida','2026-03-10','18:00:00','20500000002','72391910','72391925'),
    ('Salida','2026-03-10','18:20:00','20500000003','72391911','72391926'),
    ('Salida','2026-03-10','18:40:00','20500000004','72391912','72391927'),
    ('Salida','2026-03-10','19:00:00','20500000005','72391913','72391928'),

    ('Salida','2026-03-10','19:20:00','20500000006','72391914','72391929'),
    ('Salida','2026-03-10','19:40:00','20500000007','72391915','72391917'),
    ('Salida','2026-03-10','20:00:00','20500000008','72391916','72391918'),
    ('Salida','2026-03-10','20:20:00','20500000009','72391910','72391919'),
    ('Salida','2026-03-10','20:40:00','20500000010','72391911','72391920');

INSERT INTO detalle_transaccion
(cantidad, stock_anterior, stock_resultante, producto_id, transaccion_id)
VALUES

    (20,50,30,1,31),
    (10,30,20,2,32),
    (8,25,17,3,33),
    (15,40,25,4,34),
    (10,35,25,5,35),

    (25,60,35,6,36),
    (8,20,12,7,37),
    (15,45,30,8,38),
    (30,70,40,9,39),
    (20,55,35,10,40),

    (30,80,50,11,41),
    (20,65,45,12,42),
    (35,90,55,13,43),
    (40,100,60,14,44),
    (25,75,50,15,45),

    (20,50,30,16,46),
    (15,40,25,17,47),
    (25,60,35,18,48),
    (10,30,20,19,49),
    (15,45,30,20,50),

    (30,70,40,21,51),
    (20,50,30,22,52),
    (10,35,25,23,53),
    (15,40,25,24,54),
    (25,60,35,25,55),

    (35,80,45,26,56),
    (40,90,50,27,57),
    (20,55,35,28,58),
    (10,30,20,29,59),
    (15,45,30,30,60);

