DROP DATABASE IF EXISTS BrandonSoberaniskinal;
 
CREATE DATABASE IF NOT EXISTS BrandonSoberaniskinal;
 
USE BrandonSoberaniskinal;
 
create table Clientes(
	clienteId int not null auto_increment,
    nombre varchar(30) not null,
    apellido varchar(30) not null,
    telefono varchar(15),
    direccion varchar(150) not null,
    nit varchar(15) default '', 
    
    primary key PK_clienteId(clienteId)
); 

create table Cargos(
	cargoId int not null auto_increment,
    nombreCargo varchar(30) not null,
    descripcionCargo varchar(100) not null,
    
    primary key PK_cargoId(cargoId)
);

create table Compras (
	compraId int not null auto_increment,
    fechaCompra date not null,
    totalCompra decimal (10,2),
    
    primary key PK_compraId(compraId)
);

create table CategoriaProductos(
	categoriaProductoId int  not null auto_increment,
    nombreCategoria varchar(30) not null,
    descripcionCategoria varchar(100) not null,
    
    primary key PK_categoriaProductoId(categoriaProductoId)
);

create table Distribuidores(
	distribuidorId int not null auto_increment,
    nombreDistribuidor varchar (30)  not null,
    direccionDistribuidor varchar (200) not null,
    nitDistribuidor varchar (15) not null,
    telefonoDistribuidor varchar(15) not null,
    web varchar (50),
    
    primary key PK_distribuidorId(distribuidorId)
);

create table Productos(
	productoId int not null auto_increment,
    nombreProducto varchar(50) not null,
    descripcionProducto varchar(100) ,
    cantidadStock int not null,
    precioVentaUnitario decimal (10,2) not null,
    precioVentaMayor decimal(10,2) not null,
    precioCompra decimal(10,2) not null,
-- BloB 
	distribuidorId int not null,
	categoriaProductoId int not null,
    
    primary key PK_productoId(productoId),
	constraint FK_Productos_Distribuidores foreign key Productos(distribuidorId)
		references Distribuidores(distribuidorId),
	constraint FK_Productos_CategoriaProductos foreign key Productos(categoriaProductoId)
		references CategoriaProductos(categoriaProductoId)
);

create table DetallesCompras(
	detalleCompraId int not null auto_increment,
    cantidadCompra int not null,
    productoId int not null,
    compraId int not null,
    
    primary key PK_detalleCompraId(detalleCompraId),
    constraint FK_DetallesCompras_Productos foreign key DetallesCompras(productoId)
		references Productos(productoId),
    constraint FK_DetallesCompras_Compras foreign key DetallesCompras(compraId)
		references Compras(compraId)
    
);

create table Promociones(
	promocionId int not null auto_increment,
    precioPromocion decimal(10,2)not null,
    descripcionPromocion varchar(1000),
	fechaInicio date not null,
    fechaFinalizacion date not null,
    productoId int not null,
    
    primary key PK_promocionId(promocionId),
    constraint FK_Promociones_Productos foreign key Promociones(productoId)
		references Productos(productoId)
);

create table Empleados(
	empleadoId int not null auto_increment,
    nombreEmpleado varchar(30) not null,
    apellidoEmpleado varchar (30) not null,
    sueldo decimal (10,2) not null,
    horaEntrada time not null,
    horaSalida time not null,
    cargoId int not null,
    encargadoId int,
    
    primary key PK_Empleados(empleadoId),
    constraint FK_Empleados_Cargos foreign key Empleados(cargoId)
		references Cargos(cargoId),
	constraint FK_encargadoId foreign key Empleados(encargadoId)
		references Empleados(empleadoId)

);

create table Facturas(
	facturaId int not null auto_increment,
    fecha time not null,
    hora time not null,
    clienteId int not null,
	empleadoId int not null,
    total decimal (10,2),
    
    primary key PK_Facturas(facturaId),
    constraint FK_Facturas_Clientes foreign key Facturas(clienteId)
		references Clientes (clienteId),
	constraint PK_Facturas_Empleados foreign key Facturas(empleadoId)
		references Empleados(empleadoId)
);

create table DetallesFacturas(
	detalleFacturaId int not null auto_increment,
    facturaId int not null,
    productoId int not null,
    
    primary key PK_detalleFacturaId(detalleFacturaId),
    constraint FK_DetallesFacturas_Facturas foreign key DetallesFacturas(facturaId)
		references Facturas(facturaId),
	constraint FK_DetallesFacturas_Productos foreign key DetallesFacturas(productoId)
		references Productos(productoId)
);

create table TicketsSoportes(
	ticketSoporteId int not null auto_increment,
    descripcionTicket varchar(250) not null,
    estatus varchar (30) not null,
    clienteId int not null,
    facturaId int null,
    
	primary key PK_TicketsSoportes(ticketSoporteId),
    constraint FK_TicketsSoportes_Clientes foreign key TicketsSoportes(clienteId)
		references Clientes(clienteId),
	constraint FK_TicketsSoportes_Facturas foreign key TicketsSoportes(facturaId)
		references Facturas(facturaId)
	
);


-- Insertar datos en la tabla cargos
INSERT INTO cargos (cargoID, nombreCargo, descripcionCargo) VALUES
(1, 'Gerente', 'Encargado general'),
(2, 'Vendedor', 'Encargado de ventas'),
(3, 'Asistente de ventas', 'Apoyo en ventas');

-- Insertar datos en la tabla empleado
INSERT INTO empleado (empleadoId, nombreEmpleado, sueldo, horaEntrada, horaSalida, cargaId, ecargadoID) VALUES
(1, 'Juan Pérez', 2000.00, '08:00:00', '17:00:00', 1, NULL),
(2, 'María García', 1500.00, '09:00:00', '18:00:00', 2, 1),
(3, 'Pedro Martínez', 1200.00, '10:00:00', '19:00:00', 3, 1);

-- Insertar datos en la tabla cliente
INSERT INTO cliente (clienteID, Nombre, APELLIDO, TELEFONO, direccion, nit) VALUES
(1, 'Carlos', 'González', '123456789', 'Dirección 123', '123-456789-101-1'),
(2, 'Luis', 'López', '987654321', 'Dirección 456', '987-654321-202-2');

-- Insertar datos en la tabla facturas
INSERT INTO facturas (facturaID, fecha, hora, clienteId, empleadoId, TOTAL) VALUES
(1, '2024-04-24', '10:30:00', 1, 1, 150.50),
(2, '2024-04-25', '11:45:00', 2, 2, 200.75);

-- Insertar datos en la tabla ticketsSoporte
INSERT INTO ticketsSoporte (ticketsoporteId, descripcionTICKET, ESTATUS, CLIENTEID, facturaId) VALUES
(1, 'Problema con el sistema', 'En espera', 1, NULL),
(2, 'Consulta sobre producto', 'Resuelto', 2, 1);

-- Insertar datos en la tabla detalleFacturas
INSERT INTO detalleFacturas (detalleFacturasId, facturaId, productoID) VALUES
(1, 1, 101),
(2, 1, 102),
(3, 2, 103);

-- Insertar datos en la tabla promociones
INSERT INTO promociones (promocionId, precioPromocion, descripcion, fechaInicio, fechaFinalizacion, productoID) VALUES
(1, 10.00, 'Descuento de temporada', '2024-04-24', '2024-05-01', 101),
(2, 5.00, 'Oferta especial', '2024-04-25', '2024-05-02', 102);

-- Insertar datos en la tabla productos
INSERT INTO productos (productoId, nombreProducto, descripcionProducto, cantidadStock, precioVentaUnitario, precioVentaMayor, precioCompra, imagenProducto, distribuidorId, categoriaProductoId) VALUES
(101, 'Producto A', 'Descripción del Producto A', 50, 20.00, 18.00, 15.00, NULL, 1, 1),
(102, 'Producto B', 'Descripción del Producto B', 40, 30.00, 25.00, 22.00, NULL, 2, 2),
(103, 'Producto C', 'Descripción del Producto C', 30, 25.00, 20.00, 18.00, NULL, 3, 1);

-- Insertar datos en la tabla distribuidores
INSERT INTO distribuidores (distribuidoID, nombreDistribuidor, direccionDistribuidor, nitDistribuidor, telefonoDistribuidor, web) VALUES
(1, 'Distribuidor X', 'Dirección del Distribuidor X', '123456789', '987654321', 'www.distribuidorx.com'),
(2, 'Distribuidor Y', 'Dirección del Distribuidor Y', '987654321', '123456789', 'www.distribuidory.com');

-- Insertar datos en la tabla categoriaproductos
INSERT INTO categoriaproductos (categoriaproductoID, nombreCategoria, descripcionCategoria) VALUES
(1, 'Categoría A', 'Descripción de la Categoría A'),
(2, 'Categoría B', 'Descripción de la Categoría B');

-- Insertar datos en la tabla detalleCompra
INSERT INTO detalleCompra (detalleCompraId, cantidadCompra, productoId, compraID) VALUES
(1, 5, 101, 1),
(2, 3, 102, 2);

-- Procedimiento almacenado para agregar un empleado
DELIMITER $$
CREATE PROCEDURE AddEmployee(
    IN emp_name VARCHAR(50),
    IN emp_cargo_id INT,
    IN emp_supervisor_id INT
)
BEGIN
    INSERT INTO empleado(nombreEmpleado, cargaId, ecargadoID)
    VALUES (emp_name, emp_cargo_id, emp_supervisor_id);
END$$
DELIMITER ;

------- Clientes -------
DELIMITER $$ 
CREATE PROCEDURE sp_AgregarCliente(IN nom VARCHAR (30), IN ape VARCHAR (30), IN tel VARCHAR (15), IN dir VARCHAR (150), IN ni varchar(15))
BEGIN 	
	INSERT INTO Clientes (nombre, apellido,telefono,direccion,nit)VALUES 
		(nom, ape,tel,dir,ni);
END$$
DELIMITER ;

call sp_AgregarCliente('','','','','');

DELIMITER $$ 
CREATE PROCEDURE sp_ListarClientes()
BEGIN 
	SELECT
		Clientes.clienteId,
		Clientes.nombre,
		Clientes.apellido,
		Clientes.telefono,
		Clientes.direccion,
		Clientes.nit
			FROM Clientes;
END$$
DELIMITER ;

DELIMITER $$ 
CREATE PROCEDURE sp_EliminarCliente(IN cliId INT)
BEGIN
	DELETE
	FROM Clientes 
		WHERE clienteId =  cliId;
END$$
DELIMITER ;

	
-- CALL sp_EliminarCliente(3);

DELIMITER $$ 
CREATE PROCEDURE sp_BuscarClientes(IN cliId INT)
BEGIN
	SELECT 	
		Clientes.clienteId,
		Clientes.nombre,
		Clientes.apellido,
		Clientes.telefono,
		Clientes.direccion,
		Clientes.nit
			FROM Clientes
			WHERE clienteId = cliId;
END$$
DELIMITER ;

-- CALL sp_BuscarClientes(2);

DELIMITER $$ 
CREATE PROCEDURE sp_EditarCliente(IN cliId INT,IN nom VARCHAR (30), IN ape VARCHAR (30), IN tel VARCHAR(15), IN dir VARCHAR(150), IN ni varchar(15))
BEGIN
	UPDATE Clientes
		SET
			nombre = nom,
			apellido = ape,
			telefono = tel,
			direccion = dir,
            nit = ni
			WHERE clienteId = cliId;
END$$
DELIMITER ;
CALL sp_EditarCliente(3, '' , '', '' , '','');

call sp_ListarClientes();

-- Cargos 

DELIMITER $$ 
CREATE PROCEDURE sp_AgregarCargos(IN nom VARCHAR (30), IN des VARCHAR (100))
BEGIN 	
	INSERT INTO Cargos (nombreCargo , descripcionCargo)VALUES 
		(nom, des);
END$$
DELIMITER ;

DELIMITER $$ 
CREATE PROCEDURE sp_ListarCargos()
BEGIN 
	SELECT
		Cargos.cargoId ,
		Cargos.nombreCargo  ,
		Cargos.descripcionCargo  
			FROM Cargos;
END$$
DELIMITER ;

DELIMITER $$ 
CREATE PROCEDURE sp_EliminarCargos(IN carId INT)
BEGIN
	DELETE
	FROM Cargos 
		WHERE cargoId =  carId;
END$$
DELIMITER ;

DELIMITER $$ 
CREATE PROCEDURE sp_BuscarCargos(IN carId INT)
BEGIN
	SELECT 	
		Cargos.cargoId ,
		Cargos.nombreCargo  ,
		Cargos.descripcionCargo  
			FROM Cargos
			WHERE cargoId = carId;
END$$
DELIMITER ;

DELIMITER $$ 
CREATE PROCEDURE sp_EditarCargos(IN carId INT,IN nom VARCHAR (30), IN des VARCHAR (100))
BEGIN
	UPDATE Cargos
		SET
			nombreCargo  = nom,
			descripcionCargo  = des
			WHERE cargoId  = carId;
END$$
DELIMITER ;

-- Compras 
DELIMITER $$ 
CREATE PROCEDURE sp_AgregarCompras(IN fec date, IN tot  decimal (10.2))
BEGIN 	
	INSERT INTO Compras (fechaCompra,totalCompra )VALUES 
		(fec, tot);
END$$
DELIMITER ;

DELIMITER $$ 
CREATE PROCEDURE sp_ListarCompras()
BEGIN 
	SELECT
		Compras.compraId ,
		Compras.fechaCompra, 
		Compras.totalCompra  
			FROM Compras;
END$$
DELIMITER ;

DELIMITER $$ 
CREATE PROCEDURE sp_EliminarCompras(IN compId  INT)
BEGIN
	DELETE
	FROM Compras 
		WHERE compraId  =  compId;
END$$
DELIMITER ;

DELIMITER $$ 
CREATE PROCEDURE sp_BuscarCompras(IN compId INT)
BEGIN
	SELECT 	
		Compras.compraId,
		Compras.fechaCompra, 
		Compras.totalCompra 
			FROM Compras
			WHERE compraId = compId;
END$$
DELIMITER ;

DELIMITER $$ 
CREATE PROCEDURE sp_EditarCompras(IN compId INT,IN fec date, IN tot  decimal (10.2))
BEGIN
	UPDATE Compras
		SET
			fechaCompra   = fec,
			totalCompra   = tot
			WHERE compraId   = compId;
END$$
DELIMITER ;

-- CategoriaProductos
DELIMITER $$ 
CREATE PROCEDURE sp_AgregarCategoriaProductos(IN nom VARCHAR (30), IN des VARCHAR (100))
BEGIN 	
	INSERT INTO CategoriaProductos (nombreCategoria , descripcionCategoria )VALUES 
		(nom, des);
END$$
DELIMITER ;

DELIMITER $$ 
CREATE PROCEDURE sp_ListarCategoriaProductos()
BEGIN 
	SELECT
		CategoriaProductos.categoriaProductoId, 
		CategoriaProductos.nombreCategoria,  
		CategoriaProductos.descripcionCategoria  
			FROM CategoriaProductos;
END$$
DELIMITER ;

DELIMITER $$ 
CREATE PROCEDURE sp_EliminarCategoriaProductos(IN catId  INT)
BEGIN
	DELETE
	FROM CategoriaProductos 
		WHERE categoriaProductoId  =  catId;
END$$
DELIMITER ;

DELIMITER $$ 
CREATE PROCEDURE sp_BuscarCategoriaProductos(IN catId INT)
BEGIN
	SELECT 	
		CategoriaProductos.categoriaProductoId, 
		CategoriaProductos.nombreCategoria,  
		CategoriaProductos.descripcionCategoria  
			FROM CategoriaProductos
			WHERE categoriaProductoId  = catId;	
END$$
DELIMITER ;

DELIMITER $$ 
CREATE PROCEDURE sp_EditarCategoriaProductos(IN catId INT,IN nom VARCHAR (30), IN des VARCHAR (100))
BEGIN
	UPDATE CategoriaProductos
		SET
			nombreCategoria  = nom,
			descripcionCategoria = ape
			WHERE categoriaProductoId = catId;
END$$
DELIMITER ;

-- Distribuidores 
DELIMITER $$ 
CREATE PROCEDURE sp_AgregarDistribuidores(IN nom VARCHAR (30),IN dir VARCHAR (200), IN nit varchar(15), IN tel varchar(15),  IN web varchar(50))
BEGIN 	
	INSERT INTO Distribuidores (nombreDistribuidor , direccionDistribuidor ,nitDistribuidor ,telefonoDistribuidor ,web )VALUES 
		(nom, dir,nit,tel,web);
END$$
DELIMITER ; 

DELIMITER $$ 
CREATE PROCEDURE sp_ListarDistribuidores()
BEGIN 
	SELECT
		Distribuidores.distribuidorId,
		Distribuidores.nombreDistribuidor,
		Distribuidores.direccionDistribuidor,
		Distribuidores.nitDistribuidor,
		Distribuidores.telefonoDistribuidor,
		Distribuidores.web 
			FROM Distribuidores;
END$$
DELIMITER ;

DELIMITER $$ 
CREATE PROCEDURE sp_EliminarDistribuidores(IN disId INT)
BEGIN
	DELETE
	FROM Distribuidores 
		WHERE distribuidorId =  disId;
END$$
DELIMITER ;

DELIMITER $$ 
CREATE PROCEDURE sp_BuscarDistribuidores(IN disId INT)
BEGIN
	SELECT 	
		Distribuidores.distribuidorId,
		Distribuidores.nombreDistribuidor,
		Distribuidores.direccionDistribuidor,
		Distribuidores.nitDistribuidor,
		Distribuidores.telefonoDistribuidor,
		Distribuidores.web 
			FROM Distribuidores
			WHERE distribuidorId = disId;
END$$
DELIMITER ;

DELIMITER $$ 
CREATE PROCEDURE sp_EditarDistribuidores(IN disId INT,IN nom VARCHAR (30),IN dir VARCHAR (200), IN nit varchar(15), IN tel varchar(15),  IN web varchar(50))
BEGIN
	UPDATE Distribuidores
		SET
			nombreDistribuidor = nom,
			direccionDistribuidor = dir,
			nitDistribuidor = nit,
			telefonoDistribuidor = tel ,
            web = web
			WHERE clienteId = cliId;
END$$
DELIMITER ;

-- Productos
DELIMITER $$
create procedure sp_AgregarProducto(in nom varchar(50),in des varchar(100),in can int, in preU decimal(10,2),in preM decimal(10,2),in preC decimal(10,2), in ima blob, in disId int, in catId int)
	BEGIN
		insert into Productos(nombreProducto, descripcionProducto, cantidadStock, precioUnitario, precioVentaMayor, precioCompra, imagenProducto, distribuidorId, categoriaProductosId ) values
			(nom, des, can, preU, preM, preC, ima, disId, catId);
	END $$
DELIMITER ;

DELIMITER $$
create procedure sp_ListarProducto()
	BEGIN 
		select * from Productos;
    END $$
DELIMITER ;

DELIMITER $$
create procedure sp_BuscarProducto(in proId int)
	BEGIN 
		select * from Productos
        where productoId = proId;
    END $$
DELIMITER ;

DELIMITER $$
create procedure sp_EliminarProducto(in proId int)
	BEGIN
		delete from Productos
			where productoId = proId;
    END $$
DELIMITER ;

DELIMITER $$
create procedure sp_EditarProducto(in proId int, in nom varchar(50),in des varchar(100),in can int, in preU decimal(10,2),in preM decimal(10,2),in preC decimal(10,2), in ima blob, in disId int, in catId int )
	BEGIN
		update Productos	
			set 
            nombreProducto = nom,
            descripcionProduto = des,
            cantidadStock = can,
            precioVentaUnitario = preU,
            precioVentaMayor = preM,
            precioCompra = preC,
            imagenProducto = ima,
            distribuidorId = disId,
            categoriaProductosId = catId
            where productoId = proId;
    END $$
DELIMITER ;
-- DetalleCompra
DELIMITER $$
create procedure sp_AgregarDetalleCompra(in canC int, in proId int, in comId int)
	BEGIN
		insert into DetalleCompra(cantidadCompra, productoId, compraId) values
			(canC, proId, comId);
    END $$
DELIMITER ;

DELIMITER $$
create procedure sp_ListarDetalleCompra()
	BEGIN
		select
			DC.detalleCompraId,
            DC.cantidadCompra,
            DC.productoId,
            DC.compraId
				from DetalleCompra DC;
    END $$
DELIMITER ;

DELIMITER $$
create procedure sp_BuscarDetalleCompra(in detCId int)
	BEGIN
		select * from DetalleCompra
				where detalleCompraId = detCId;
    END $$
DELIMITER ;

DELIMITER $$
create procedure sp_EliminarDetalleCompra(in detCId int)
	BEGIN
		delete
			from DetalleCompra
				where detalleCompraId = detCId;
    END $$
DELIMITER ;

DELIMITER $$
create procedure sp_EditarDetalleCompra(in detCId int, in canC int, in proId int, in comId int)
	BEGIN
		update DetalleCompra
			set
				cantidadCompra = canC,
                productoId = proId,
                compraId = comId
					where detalleCompraId = detCId;
    END $$
DELIMITER ;
-- Promociones
DELIMITER $$
create procedure sp_AgregarPromociones(prePro decimal (10,2), desPro varchar (100), fecI date, fecF date, in proId int)
BEGIN
	insert into Promociones(precioPromocion, descripcionPromocion, fechaInicio, fechaFinalizacion, productoId )values
    (prePro,desPro, fecI, fecF, proId);
    
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_ListarPromociones()
BEGIN
	select
    Promociones.promocionId,
    Promociones.precioPromocion,
    Promociones.descripcionPromocion,
    Promociones.fechaInicio,
    Promociones.fechaFinalizacion,
    Promociones.productoId
		FROM Promociones;

END $$
DELIMITER ;

DELIMITER $$
create procedure sp_EliminarPromociones(in proId int)
BEGIN
	delete
		from Promociones
        where promocionId = proId;
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_BuscarPromociones(in proId int)
BEGIN
	select
		Promociones.promocionId,
        Promociones.precioPromocion,
        Promociones.descripcionPromocion,
        Promociones.fechaInicio,
        Promociones.fechaFinalizacion,
        Promociones.productoId
			from Promociones
            where promocionId = proId;
END $$
DELIMITER  ;

DELIMITER $$
create procedure sp_EditarPromociones(in promId int, prePro decimal (10,2), desPro varchar (100), fecI date, fecF date, in proId int )
BEGIn
	update Promociones
		set
			precioPromocion = prePro,
            descripcionPromocion = desPro,
            fechaInicio = fecI,
            fechFinalizacion = fecF,
            profuctoId = proId
            where promocionId = promId;
END $$
DELIMITER ;

-- Empleados
DELIMITER $$
	create procedure sp_AgregarEmpleados (in nomEmp varchar (30), in apeEmp  varchar (30), in sud decimal (10, 2), in horEntr time, in horSld time, in cargId int, in encaId int)
		begin 
			insert into Empleados (nombreEmpleado , apellidoEmpleado, sueldo, horaEntrada, horaSalida, cargoId, encargadoId)
				values (nomEmp, apeEmp, sud, horEntr, horSld, cargId, encaId);
		end$$
DELIMITER ;


DELIMITER $$
	create procedure sp_ListarEmpleados ()
		begin 
			select 
				Empleados.nombreEmpleado,
                Empleados.apellidoEmpleado,
                Empleados.sueldo,
                Empleados.horaEntrada,
                Empleados.horaSalida,
                Empleados.cargoId,
                Empleados.encargadoId
					FROM Empleados;
		end $$
DELIMITER ;
CALL sp_ListarEmpleados ();

DELIMITER $$
	create procedure sp_EliminarEmpleados (in empId int)
		begin
			delete
				from Empleados
					where empleadoId = empId;
		end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_BuscarEmpleados (in empId int)
		begin 
			select
				Empleados.nombreEmpleado,
                Empleados.apellidoEmpleado,
                Empleados.sueldo,
                Empleados.horaEntrada,
                Empleados.horaSalida,
                Empleados.cargoId,
                Empleados.encargadoId
					from Empleados 
						where empleadoId = empId;
		end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_EditarEmpleados (in empId int, in nomEmp varchar (30), in apeEmp  varchar (30), in sud decimal (10, 2), in horEntr time, in horSld time, in cargId int, in encaId int)
		begin
			update Empleados
				set	
					nombreEmpleado = nomEmp,
					apellidoEmpleado = apeEmp,
					sueldo = sud,
					horaEntrada = horEntr, 
					horaSalida = horSld,
					cargoId = cargId,
					encargadoId = encaId
					where empleadoId = empId;
		end $$
DELIMITER ;

-- FACTURAS --

DELIMITER $$
	create procedure sp_AgregarFacturas (in fech date, in hor time, in tot decimal (10, 2), in cliId int, in empId int)
		begin 
			insert into Facturas (fecha, hora, total, clienteId, empleadoId)
				values (fech, hor, tot, cliId, empId);
		end$$
DELIMITER ;

DELIMITER $$
	create procedure sp_ListarFacturas ()
		begin 
			select 
				Facturas.facturaId,
				Facturas.fecha,
                Facturas.hora,
                Facturas.total,
                Facturas.clienteId,
                Facturas.empleadoId
					FROM Facturas;
		end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_EliminarFacturas (in facId int)
		begin
			delete
				from Facturas
					where facturaId = facId;
		end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_BuscarFacturas (in facId int)
		begin 
			select
				Facturas.fecha,
                Facturas.hora,
                Facturas.total,
                Facturas.clienteId,
                Facturas.empleadoId
					from Facturas
						where facturaId = facId;
		end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_EditarFacturas (in facId int, in fech date, in hor time, in tot decimal (10, 2), in cliId int, in empId int)
		begin
			update Facturas
				set	
					fecha = fech,
					hora = hor,
					total = tot,
					clienteId = cliId,
					empleadoId = empId
					where facturaId = facId;
		end $$
DELIMITER ;

-- DETALLE FACTURA --
DELIMITER $$
	create procedure sp_AgregarDetalleFactura  (in factId int, in proId int)
		begin 
			insert into DetalleFactura  (facturaId, productoId)
				values (factId, proId);
		end$$
DELIMITER ;

DELIMITER $$
	create procedure sp_ListarDetalleFactura  ()
		begin 
			select 
				DetalleFactura.detalleFacturaId,
				DetalleFactura.facturaId,
                DetalleFactura.productoId
					FROM DetalleFactura;
		end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_EliminarDetalleFactura   (in detaFacId int)
		begin
			delete
				from DetalleFactura  
					where detalleFacturaId  = detaFacId;
		end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_BuscarDetalleFactura  (in detaFacId int)
		begin 
			select
				DetalleFactura.facturaId,
                DetalleFactura.productoId
					from DetalleFactura 
						where detalleFacturaId  = detaFacId;
		end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_EditarDetalleFactura  (in detaFacId int, in factId int, in proId int)
		begin
			update DetalleFactura 
				set	
					facturaId = factId,
					productoId = proId
					where detalleFacturaId  = detaFacId;
		end $$
DELIMITER ;

-- TICKET SOPORTE --
DELIMITER $$
	create procedure sp_AgregarTicketSoporte (in descTick varchar (250), in est varchar (30), in cliId int, in facId int)
		begin 
			insert into Facturas (descripcionTicket, estatuts , clienteId , facturaId)
				values (descTick, est, cliId, facId);
		end$$
DELIMITER ;

DELIMITER $$
	create procedure sp_ListarTicketSoporte ()
		begin 
			select 
				TicketSoporte.ticketSoporteId ,
				TicketSoporte.descripcionTicket,
                TicketSoporte.estatuts,
                TicketSoporte.clienteId,
                TicketSoporte.facturaId
					FROM TicketSoporte;
		end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_EliminarTicketSoporte  (in tickSopId int)
		begin
			delete
				from TicketSoporte 
					where ticketSoporteId = tickSopId;
		end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_BuscarTicketSoporte (in tickSopId int)
	  begin 
		select
			TicketSoporte.descripcionTicket,
			TicketSoporte.estatuts,
			TicketSoporte.clienteId,
			TicketSoporte.facturaId
				from TicketSoporte
					where ticketSoporteId = tickSopId;
	end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_EditarTicketSoporte (in tickSopId int, in descTick varchar (250), in est varchar (30), in cliId int, in facId int)
	  begin
		update TicketSoporte
			set	
				descripcionTicket = descTick,
				estatuts = est,
				clienteId = cliId,
				facturaId = facId
				where ticketSoporteId = tickSopId;
	end $$
    
DELIMITER ;

-- creacion de los create  FUNCTION --
 
 
 -- Funciones Cliente
DELIMITER $$
CREATE FUNCTION fn_EditarCliente(cliId INT, nom VARCHAR(30), ape VARCHAR(30), tel VARCHAR(15), dir VARCHAR(150), ni VARCHAR(15)) RETURNS BOOLEAN
BEGIN
    DECLARE success BOOLEAN;
    UPDATE Clientes
        SET
            nombre = nom,
            apellido = ape,
            telefono = tel,
            direccion = dir,
            nit = ni
        WHERE clienteId = cliId;

    SET success = ROW_COUNT() > 0;
    RETURN success;
END$$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION fn_BuscarClientes(cliId INT) RETURNS TABLE (
    clienteId INT,
    nombre VARCHAR(30),
    apellido VARCHAR(30),
    telefono VARCHAR(15),
    direccion VARCHAR(150),
    nit VARCHAR(15)
)
BEGIN
    RETURN (
        SELECT 	
            Clientes.clienteId,
            Clientes.nombre,
            Clientes.apellido,
            Clientes.telefono,
            Clientes.direccion,
            Clientes.nit
        FROM Clientes
        WHERE clienteId = cliId
    );
END$$
DELIMITER ;

-- Funciones Cargo
DELIMITER $$
CREATE FUNCTION fn_EditarCargos(carId INT, nom VARCHAR(30), des VARCHAR(100)) RETURNS BOOLEAN
BEGIN
    DECLARE success BOOLEAN;
    UPDATE Cargos
        SET
            nombreCargo = nom,
            descripcionCargo = des
        WHERE cargoId = carId;

    SET success = ROW_COUNT() > 0;
    RETURN success;
END$$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION fn_BuscarProductos(proId INT)
S(
    productoId INT,
    nombreProducto VARCHAR(30),
    descripcionProducto VARCHAR(200),
    precioUnitario DECIMAL(10, 2),
    stockMinimo INT,
    stockMaximo INT,
    categoriaProductoId INT,
    distribuidorId INT
)
BEGIN
    RETURN (
        SELECT
            Productos.productoId,
            Productos.nombreProducto,
            Productos.descripcionProducto,
            Productos.precioUnitario,
            Productos.stockMinimo,
            Productos.stockMaximo,
            Productos.categoriaProductoId,
            Productos.distribuidorId
        FROM Productos
        WHERE productoId = proId
    );
END$$
DELIMITER ;




-- Funciones Compra
DELIMITER $$
CREATE FUNCTION fn_EditarCompras(compId INT, fec DATE, tot DECIMAL(10, 2)) RETURNS BOOLEAN
BEGIN
    DECLARE success BOOLEAN;
    UPDATE Compras
        SET
            fechaCompra = fec,
            totalCompra = tot
        WHERE compraId = compId;

    SET success = ROW_COUNT() > 0;
    RETURN success;
END$$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION fn_BuscarCompras(compId INT) RETURNS TABLE (
    compraId INT,
    fechaCompra DATE,
    totalCompra DECIMAL(10, 2)
)
BEGIN
    RETURN (
        SELECT 	
            Compras.compraId,
            Compras.fechaCompra, 
            Compras.totalCompra 
        FROM Compras
        WHERE compraId = compId
    );
END$$
DELIMITER ;

-- Funciones CategoriaProductos
DELIMITER $$
CREATE FUNCTION fn_EditarCategoriaProductos(catId INT, nom VARCHAR(30), des VARCHAR(100)) RETURNS BOOLEAN
BEGIN
    DECLARE success BOOLEAN;
    UPDATE CategoriaProductos
        SET
            nombreCategoria = nom,
            descripcionCategoria = des
        WHERE categoriaProductoId = catId;

    SET success = ROW_COUNT() > 0;
    RETURN success;
END$$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION fn_BuscarCategoriaProductos(catId INT) RETURNS TABLE (
    categoriaProductoId INT,
    nombreCategoria VARCHAR(30),
    descripcionCategoria VARCHAR(100)
)
BEGIN
    RETURN (
        SELECT 	
            CategoriaProductos.categoriaProductoId, 
            CategoriaProductos.nombreCategoria,  
            CategoriaProductos.descripcionCategoria  
        FROM CategoriaProductos
        WHERE categoriaProductoId = catId;	
    );
END$$
DELIMITER ;

-- Funciones Distribuidores 
DELIMITER $$
CREATE FUNCTION fn_EditarDistribuidores(disId INT, nom VARCHAR(30), dir VARCHAR(200), nit VARCHAR(15), tel VARCHAR(15), web VARCHAR(50)) RETURNS BOOLEAN
BEGIN
    DECLARE success BOOLEAN;
    UPDATE Distribuidores
        SET
            nombreDistribuidor = nom,
            direccionDistribuidor = dir,
            nitDistribuidor = nit,
            telefonoDistribuidor = tel,
            web = web
        WHERE distribuidorId = disId;

    SET success = ROW_COUNT() > 0;
    RETURN success;
END$$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION fn_BuscarDistribuidores(disId INT) RETURNS TABLE (
    distribuidorId INT,
    nombreDistribuidor VARCHAR(30),
    direccionDistribuidor VARCHAR(200),
    nitDistribuidor VARCHAR(15),
    telefonoDistribuidor VARCHAR(15),
    web VARCHAR(50)
)
BEGIN
    RETURN (
        SELECT 	
            Distribuidores.distribuidorId,
            Distribuidores.nombreDistribuidor,
            Distribuidores.direccionDistribuidor,
            Distribuidores.nitDistribuidor,
            Distribuidores.telefonoDistribuidor,
            Distribuidores.web 
        FROM Distribuidores
        WHERE distribuidorId = disId;
    );
END$$
DELIMITER ;

-- Funciones Empleados
DELIMITER $$
CREATE FUNCTION fn_EditarEmpleados(empId INT, nom VARCHAR(30), ape VARCHAR(30), tel VARCHAR(15), dir VARCHAR(150), nac DATE, gen ENUM('Masculino', 'Femenino'), fIn DATE, fOut DATE, sal DECIMAL(10, 2), carId INT) RETURNS BOOLEAN
BEGIN
    DECLARE success BOOLEAN;
    UPDATE Empleados
        SET
            nombreEmpleado = nom,
            apellidoEmpleado = ape,
            telefonoEmpleado = tel,
            direccionEmpleado = dir,
            fechaNacimientoEmpleado = nac,
            generoEmpleado = gen,
            fechaInicio = fIn,
            fechaSalida = fOut,
            salario = sal,
            cargoId = carId
        WHERE empleadoId = empId;

    SET success = ROW_COUNT() > 0;
    RETURN success;
END$$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION fn_BuscarEmpleados(empId INT) RETURNS TABLE (
    empleadoId INT,
    nombreEmpleado VARCHAR(30),
    apellidoEmpleado VARCHAR(30),
    telefonoEmpleado VARCHAR(15),
    direccionEmpleado VARCHAR(150),
    fechaNacimientoEmpleado DATE,
    generoEmpleado ENUM('Masculino', 'Femenino'),
    fechaInicio DATE,
    fechaSalida DATE,
    salario DECIMAL(10, 2),
    cargoId INT
)
BEGIN
    RETURN (
        SELECT 	
            Empleados.empleadoId,
            Empleados.nombreEmpleado,
            Empleados.apellidoEmpleado,
            Empleados.telefonoEmpleado,
            Empleados.direccionEmpleado,
            Empleados.fechaNacimientoEmpleado,
            Empleados.generoEmpleado,
            Empleados.fechaInicio,
            Empleados.fechaSalida,
            Empleados.salario,
            Empleados.cargoId 
        FROM Empleados
        WHERE empleadoId = empId;
    );
END$$
DELIMITER ;

-- Funciones Facturas
DELIMITER $$
CREATE FUNCTION fn_EditarFacturas(facId INT, fec DATE, tot DECIMAL(10, 2), cliId INT, empId INT) RETURNS BOOLEAN
BEGIN
    DECLARE success BOOLEAN;
    UPDATE Facturas
        SET
            fechaFactura = fec,
            totalFactura = tot,
            clienteId = cliId,
            empleadoId = empId
        WHERE facturaId = facId;

    SET success = ROW_COUNT() > 0;
    RETURN success;
END$$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION fn_BuscarFacturas(facId INT) RETURNS TABLE (
    facturaId INT,
    fechaFactura DATE,
    totalFactura DECIMAL(10, 2),
    clienteId INT,
    empleadoId INT
)
BEGIN
    RETURN (
        SELECT 	
            Facturas.facturaId,
            Facturas.fechaFactura,
            Facturas.totalFactura,
            Facturas.clienteId,
            Facturas.empleadoId
        FROM Facturas
        WHERE facturaId = facId;
    );
END$$
DELIMITER ;

-- Funciones DetalleFacturas
DELIMITER $$
CREATE FUNCTION fn_EditarDetalleFacturas(detFacId INT, facId INT, proId INT, cant INT, pre DECIMAL(10, 2), desc DECIMAL(5, 2)) RETURNS BOOLEAN
BEGIN
    DECLARE success BOOLEAN;
    UPDATE DetalleFacturas
        SET
            facturaId = facId,
            productoId = proId,
            cantidad = cant,
            precioUnitario = pre,
            descuento = desc
        WHERE detalleFacturaId = detFacId;

    SET success = ROW_COUNT() > 0;
    RETURN success;
END$$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION fn_BuscarDetalleFacturas(detFacId INT) RETURNS TABLE (
    detalleFacturaId INT,
    facturaId INT,
    productoId INT,
    cantidad INT,
    precioUnitario DECIMAL(10, 2),
    descuento DECIMAL(5, 2)
)
BEGIN
    RETURN (
        SELECT 	
            DetalleFacturas.detalleFacturaId,
            DetalleFacturas.facturaId,
            DetalleFacturas.productoId,
            DetalleFacturas.cantidad,
            DetalleFacturas.precioUnitario,
            DetalleFacturas.descuento
        FROM DetalleFacturas
        WHERE detalleFacturaId = detFacId;
    );
END$$
DELIMITER ;

-- Funciones Inventario
DELIMITER $$
CREATE FUNCTION fn_EditarInventario(invId INT, proId INT, cant INT, fec DATE) RETURNS BOOLEAN
BEGIN
    DECLARE success BOOLEAN;
    UPDATE Inventario
        SET
            productoId = proId,
            cantidad = cant,
            fechaInventario = fec
        WHERE inventarioId = invId;

    SET success = ROW_COUNT() > 0;
    RETURN success;
END$$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION fn_BuscarInventario(invId INT) RETURNS TABLE (
    inventarioId INT,
    productoId INT,
    cantidad INT,
    fechaInventario DATE
)
BEGIN
    RETURN (
        SELECT 	
            Inventario.inventarioId,
            Inventario.productoId,
            Inventario.cantidad,
            Inventario.fechaInventario
        FROM Inventario
        WHERE inventarioId = invId;
    );
END$$
DELIMITER ;

-- Funciones Productos
DELIMITER $$
CREATE FUNCTION fn_EditarProductos(proId INT, nom VARCHAR(30), des VARCHAR(200), precio DECIMAL(10, 2), stockMin INT, stockMax INT, catId INT, disId INT) RETURNS BOOLEAN
BEGIN
    DECLARE success BOOLEAN;
    UPDATE Productos
        SET
            nombreProducto = nom,
            descripcionProducto = des,
            precioUnitario = precio,
            stockMinimo = stockMin,
            stockMaximo = stockMax,
            categoriaProductoId = catId,
            distribuidorId = disId
        WHERE productoId = proId;

    SET success = ROW_COUNT() > 0;
    RETURN success;
END$$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION fn_BuscarProductos(proId INT) RETURNS TABLE (
    productoId INT,
    nombreProducto VARCHAR(30),
    descripcionProducto VARCHAR(200),
    precioUnitario DECIMAL(10, 2),
    stockMinimo INT,
    stockMaximo INT,
    categoriaProductoId INT,
    distribuidorId INT
)
BEGIN
    RETURN (
        SELECT 	
            Productos.productoId,
            Productos.nombreProducto,
            Productos.descripcionProducto,
            Productos.precioUnitario,
            Productos.stockMinimo,
            Productos.stockMaximo,
            Productos.categoriaProductoId,
            Productos.distribuidorId
        FROM Productos
        WHERE productoId = proId;
    );
END$$
DELIMITER ;

DELIMITER $$
DELIMITER ;


DELIMITER $$
DELIMITER ;
 
DELIMITER $$
DELIMITER ;

 
DELIMITER $$
DELIMITER ;

 
DELIMITER $$
DELIMITER ;

 
DELIMITER $$
DELIMITER ;

 
DELIMITER $$
DELIMITER ;

 
DELIMITER $$
DELIMITER ;


-- Creaciond de los triggers --
