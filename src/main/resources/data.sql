-- Insertar categorías
INSERT INTO categorias (descripcion, nombre) VALUES
('Herramientas eléctricas', 'Electrónica'),
('Herramientas manuales', 'Manual'),
('Herramientas de medición', 'Medición');

-- Insertar roles
INSERT INTO roles (nombre) VALUES
('ADMIN'),
('PROVEEDOR'),
('CLIENTE');

-- Insertar permisos
INSERT INTO permisos (descripcion, nombre) VALUES
('Acceso total al sistema', 'Admin Full'),
('Acceso a gestión de herramientas', 'Gestión Herramientas'),
('Acceso a reservas y pagos', 'Gestión Reservas');

-- Insertar usuarios
INSERT INTO usuarios (activo, rol_id, direccion, email, nombre, password, telefono) VALUES
(true, 1, 'Calle 123, Ciudad', 'admin@ejemplo.com', 'Admin', 'hashed_password_1', '555-1234'),
(true, 2, 'Calle 456, Ciudad', 'proveedor@ejemplo.com', 'Proveedor1', 'hashed_password_2', '555-5678'),
(true, 3, 'Calle 789, Ciudad', 'cliente@ejemplo.com', 'Cliente1', 'hashed_password_3', '555-9012');

-- Insertar clientes
INSERT INTO clientes (usuario_id, razon_social, rfc) VALUES
(3, 'Cliente S.A.', 'RFC123456789');

-- Insertar proveedores
INSERT INTO proveedores (usuario_id, nombre_empresa, razon_social, rfc) VALUES
(2, 'Proveedores SA', 'Proveedor Sociedad Anónima', 'RFC987654321');

-- Insertar categorías de herramientas
-- Ya insertado en categorias, si quieres más específicas aquí

-- Insertar herramientas
INSERT INTO herramientas (activa, cantidad_disponible, costo_por_dia, categoria_id, proveedor_id, descripcion, marca, modelo, nombre) VALUES
(true, 10, 15.50, 1, 2, 'Taladro eléctrico profesional', 'Bosch', 'TX100', 'Taladro Bosch'),
(true, 5, 7.00, 2, 2, 'Martillo de acero', 'Stanley', 'ST200', 'Martillo Stanley');

-- Insertar reservas
INSERT INTO reservas (costo_total, fecha_fin, fecha_inicio, cliente_id, fecha_reserva, herramienta_id, proveedor_id, estado) VALUES
(155.00, '2025-06-10', '2025-06-01', 3, NOW(), 1, 2, 'Confirmada');

-- Insertar daños
INSERT INTO danos (costo_reparacion, fecha_reporte, herramienta_id, reserva_id, descripcion, estado) VALUES
(50.00, '2025-06-11', 1, 1, 'Daño en motor del taladro', 'Pendiente');

-- Insertar facturas
INSERT INTO facturas (fecha_emision, iva, subtotal, total, reserva_id, folio, pdf, rfc_emisor, rfc_receptor, xml) VALUES
('2025-06-01', 24.75, 130.25, 155.00, 1, 'FOL123456', 'factura1.pdf', 'RFC987654321', 'RFC123456789', 'factura1.xml');

-- Insertar pagos
INSERT INTO pagos (monto, fecha_pago, reserva_id, estado, metodo_pago, transaccion_id) VALUES
(155.00, NOW(), 1, 'Pagado', 'Tarjeta Crédito', 'TXN789456123');


-- Insertar notificaciones
INSERT INTO notificaciones (leida, fecha_creacion, usuario_id, mensaje, titulo) VALUES
(false, NOW(), 3, 'Su reserva ha sido confirmada.', 'Reserva Confirmada'),
(false, NOW(), 2, 'Nueva orden de reserva recibida.', 'Orden Recibida');
