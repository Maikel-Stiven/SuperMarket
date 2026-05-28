INSERT IGNORE INTO `categories` (`id`, `nombre`) 
VALUES (1, 'Abarrotes');


INSERT INTO `products` (`id`, `codigo_barras`, `nombre`, `precio`, `stock`, `activo`, `categoria_id`) 
VALUES (1, '7701234567890', 'Arroz Diana 1kg', 4500.0, 10, 1, 1)
ON DUPLICATE KEY UPDATE `nombre`='Arroz Diana 1kg';

INSERT INTO `empleados` (`id`, `nombre`, `hire_date`, `salario`) 
VALUES ('E001', 'Cajero de Prueba', '2026-05-28', 1300000.0)
ON DUPLICATE KEY UPDATE `nombre`='Cajero de Prueba';
