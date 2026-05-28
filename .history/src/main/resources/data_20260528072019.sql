INSERT INTO `categorias` (`id`, `nombre`) 
VALUES (1, 'Abarrotes')
ON DUPLICATE KEY UPDATE `nombre`='Abarrotes';

INSERT INTO `productos` (`id`, `codigo_barras`, `nombre`, `precio`, `stock`, `categoria_id`, `activo`) 
VALUES (1, '7701234567890', 'Arroz Diana 1kg', 4500.0, 50, 1, true)
ON DUPLICATE KEY UPDATE `nombre`='Arroz Diana 1kg';

INSERT INTO `empleados` (`id`, `nombre`, `hire_date`, `salario`) 
VALUES ('E001', 'Cajero de Prueba', '2026-05-28', 1300000.0)
ON DUPLICATE KEY UPDATE `nombre`='Cajero de Prueba';